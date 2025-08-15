/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.codecs.aac.AACConts;
import com.github.alexthe666.citadel.repack.jcodec.codecs.aac.ADTSParser;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.NALUnit;
import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.NALUnitType;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.MPEGDecoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.MPEGES;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.SegmentReader;
import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrackMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.IntIntHistogram;
import com.github.alexthe666.citadel.repack.jcodec.common.LongArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.TrackType;
import com.github.alexthe666.citadel.repack.jcodec.common.UsedViaReflection;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPEGDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPEGPacket;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.PESPacket;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MPSDemuxer
extends SegmentReader
implements MPEGDemuxer {
    private static final int BUFFER_SIZE = 0x100000;
    private Map<Integer, BaseTrack> streams = new HashMap<Integer, BaseTrack>();
    private ReadableByteChannel channel;
    private List<ByteBuffer> bufPool;

    public MPSDemuxer(ReadableByteChannel channel) throws IOException {
        super(channel, 4096);
        this.channel = channel;
        this.bufPool = new ArrayList<ByteBuffer>();
        this.findStreams();
    }

    protected void findStreams() throws IOException {
        PESPacket nextPacket;
        for (int i = 0; (i == 0 || i < 5 * this.streams.size() && this.streams.size() < 2) && (nextPacket = this.nextPacket(this.getBuffer())) != null; ++i) {
            this.addToStream(nextPacket);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ByteBuffer getBuffer() {
        List<ByteBuffer> list = this.bufPool;
        synchronized (list) {
            if (this.bufPool.size() > 0) {
                return this.bufPool.remove(0);
            }
        }
        return ByteBuffer.allocate(0x100000);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void putBack(ByteBuffer buffer) {
        buffer.clear();
        List<ByteBuffer> list = this.bufPool;
        synchronized (list) {
            this.bufPool.add(buffer);
        }
    }

    public void reset() {
        for (BaseTrack track : this.streams.values()) {
            track._pending.clear();
        }
    }

    private void addToStream(PESPacket pkt) throws IOException {
        BaseTrack pes = this.streams.get(pkt.streamId);
        if (pes == null) {
            pes = this.isMPEG(pkt.data) ? new MPEGTrack(this, pkt.streamId, pkt) : (this.isAAC(pkt.data) ? new AACTrack(this, pkt.streamId, pkt) : new PlainTrack(this, pkt.streamId, pkt));
            this.streams.put(pkt.streamId, pes);
        } else {
            pes.pending(pkt);
        }
    }

    public PESPacket nextPacket(ByteBuffer out) throws IOException {
        ByteBuffer dup = out.duplicate();
        while (!MPSUtils.psMarker(this.curMarker)) {
            if (this.skipToMarker()) continue;
            return null;
        }
        ByteBuffer fork = dup.duplicate();
        this.readToNextMarker(dup);
        PESPacket pkt = MPSUtils.readPESHeader(fork, this.curPos());
        if (pkt.length == 0) {
            while (!MPSUtils.psMarker(this.curMarker) && this.readToNextMarker(dup)) {
            }
        } else {
            this.read(dup, pkt.length - dup.position() + 6);
        }
        fork.limit(dup.position());
        pkt.data = fork;
        return pkt;
    }

    public List<MPEGDemuxer.MPEGDemuxerTrack> getTracks() {
        return new ArrayList<MPEGDemuxer.MPEGDemuxerTrack>(this.streams.values());
    }

    public List<MPEGDemuxer.MPEGDemuxerTrack> getVideoTracks() {
        ArrayList<MPEGDemuxer.MPEGDemuxerTrack> result = new ArrayList<MPEGDemuxer.MPEGDemuxerTrack>();
        for (BaseTrack p : this.streams.values()) {
            if (!MPSUtils.videoStream(p.streamId)) continue;
            result.add(p);
        }
        return result;
    }

    public List<MPEGDemuxer.MPEGDemuxerTrack> getAudioTracks() {
        ArrayList<MPEGDemuxer.MPEGDemuxerTrack> result = new ArrayList<MPEGDemuxer.MPEGDemuxerTrack>();
        for (BaseTrack p : this.streams.values()) {
            if (!MPSUtils.audioStream(p.streamId)) continue;
            result.add(p);
        }
        return result;
    }

    private boolean isAAC(ByteBuffer _data) {
        ADTSParser.Header read = ADTSParser.read(_data.duplicate());
        return read != null;
    }

    private boolean isMPEG(ByteBuffer _data) {
        ByteBuffer b = _data.duplicate();
        int marker = -1;
        int score = 0;
        boolean hasHeader = false;
        boolean slicesStarted = false;
        while (b.hasRemaining()) {
            int code = b.get() & 0xFF;
            if ((marker = marker << 8 | code) < 256 || marker > 440) continue;
            if (marker >= 432 && marker <= 440) {
                if (hasHeader && marker != 437 && marker != 434 || slicesStarted) break;
                score += 5;
                continue;
            }
            if (marker == 256) {
                if (slicesStarted) break;
                hasHeader = true;
                continue;
            }
            if (marker <= 256 || marker >= 432) continue;
            if (!hasHeader) break;
            if (!slicesStarted) {
                score += 50;
                slicesStarted = true;
            }
            ++score;
        }
        return score > 50;
    }

    @UsedViaReflection
    public static int probe(ByteBuffer b_) {
        ByteBuffer b = b_.duplicate();
        int marker = -1;
        int sliceSize = 0;
        boolean videoPes = false;
        int state = 0;
        int errors = 0;
        boolean inNALUnit = false;
        ArrayList<NALUnit> nuSeq = new ArrayList<NALUnit>();
        while (b.hasRemaining()) {
            int code = b.get() & 0xFF;
            if (state >= 3) {
                ++sliceSize;
            }
            marker = marker << 8 | code;
            if (inNALUnit) {
                NALUnit nu = NALUnit.read(NIOUtils.asByteBufferInt(new int[]{code}));
                if (nu.type != null) {
                    nuSeq.add(nu);
                }
                inNALUnit = false;
            }
            if (videoPes && marker == 1) {
                inNALUnit = true;
                continue;
            }
            if (marker < 256 || marker > 511) continue;
            if (marker >= 442) {
                videoPes = MPSUtils.videoMarker(marker);
                continue;
            }
            if (!videoPes) continue;
            boolean stop = false;
            switch (state) {
                case 0: {
                    if (marker >= 432 && marker <= 440) {
                        state = 1;
                        break;
                    }
                    if (marker == 256) {
                        state = 2;
                        break;
                    }
                    state = 0;
                    break;
                }
                case 1: {
                    if (marker == 256) {
                        state = 2;
                        break;
                    }
                    if (marker >= 432 && marker <= 440) {
                        state = 1;
                        break;
                    }
                    ++errors;
                    break;
                }
                case 2: {
                    if (marker == 257) {
                        state = 3;
                        break;
                    }
                    if (marker == 437 || marker == 434) {
                        state = 2;
                        break;
                    }
                    ++errors;
                    break;
                }
                default: {
                    if (state > 3 && sliceSize < 1) {
                        ++errors;
                    }
                    sliceSize = 0;
                    if (state - 1 == marker - 256) {
                        state = marker - 256 + 2;
                        break;
                    }
                    if (marker != 256 && marker < 432) break;
                    stop = true;
                }
            }
            if (!stop) continue;
            break;
        }
        return Math.max(MPSDemuxer.rateSeq(nuSeq), state >= 3 ? 100 / (1 + errors) : 0);
    }

    private static int rateSeq(List<NALUnit> nuSeq) {
        int score = 0;
        boolean hasSps = false;
        boolean hasPps = false;
        boolean hasSlice = false;
        for (NALUnit nalUnit : nuSeq) {
            if (NALUnitType.SPS == nalUnit.type) {
                score = hasSps && !hasSlice ? (score -= 30) : (score += 30);
                hasSps = true;
                continue;
            }
            if (NALUnitType.PPS == nalUnit.type) {
                if (hasPps && !hasSlice) {
                    score -= 30;
                }
                if (hasSps) {
                    score += 20;
                }
                hasPps = true;
                continue;
            }
            if (NALUnitType.IDR_SLICE != nalUnit.type && NALUnitType.NON_IDR_SLICE != nalUnit.type) continue;
            if (!hasSlice) {
                score += 20;
            }
            hasSlice = true;
        }
        return score;
    }

    @Override
    public void close() throws IOException {
        this.channel.close();
    }

    public static class AACTrack
    extends PlainTrack {
        private List<Packet> audioStash = new ArrayList<Packet>();

        public AACTrack(MPSDemuxer demuxer, int streamId, PESPacket pkt) throws IOException {
            super(demuxer, streamId, pkt);
        }

        @Override
        public Packet nextFrame() throws IOException {
            Packet nextFrame;
            if (this.audioStash.size() == 0 && (nextFrame = this.nextFrameWithBuffer(null)) != null) {
                ByteBuffer data = nextFrame.getData();
                ADTSParser.Header adts = ADTSParser.read(data.duplicate());
                long nextPts = nextFrame.getPts();
                while (data.hasRemaining()) {
                    ByteBuffer data2 = NIOUtils.read(data, adts.getSize());
                    Packet pkt = Packet.createPacketWithData(nextFrame, data2);
                    pkt.setDuration(pkt.getTimescale() * 1024 / AACConts.AAC_SAMPLE_RATES[adts.getSamplingIndex()]);
                    pkt.setPts(nextPts);
                    nextPts += pkt.getDuration();
                    this.audioStash.add(pkt);
                    if (!data.hasRemaining()) continue;
                    adts = ADTSParser.read(data.duplicate());
                }
            }
            if (this.audioStash.size() == 0) {
                return null;
            }
            return this.audioStash.remove(0);
        }
    }

    public static class PlainTrack
    extends BaseTrack {
        private int frameNo;
        private Packet lastFrame;
        private long lastKnownDuration = 3003L;

        public PlainTrack(MPSDemuxer demuxer, int streamId, PESPacket pkt) throws IOException {
            super(demuxer, streamId, pkt);
        }

        public boolean isOpen() {
            return true;
        }

        public void close() throws IOException {
        }

        @Override
        public Packet nextFrameWithBuffer(ByteBuffer buf) throws IOException {
            PESPacket pkt;
            if (this._pending.size() > 0) {
                pkt = (PESPacket)this._pending.remove(0);
            } else {
                while ((pkt = this.demuxer.nextPacket(this.demuxer.getBuffer())) != null && pkt.streamId != this.streamId) {
                    this.demuxer.addToStream(pkt);
                }
            }
            return pkt == null ? null : Packet.createPacket(pkt.data, pkt.pts, 90000, 0L, this.frameNo++, Packet.FrameType.UNKNOWN, null);
        }

        @Override
        public Packet nextFrame() throws IOException {
            if (this.lastFrame == null) {
                this.lastFrame = this.nextFrameWithBuffer(null);
            }
            if (this.lastFrame == null) {
                return null;
            }
            Packet toReturn = this.lastFrame;
            this.lastFrame = this.nextFrameWithBuffer(null);
            if (this.lastFrame != null) {
                this.lastKnownDuration = this.lastFrame.getPts() - toReturn.getPts();
            }
            toReturn.setDuration(this.lastKnownDuration);
            return toReturn;
        }

        @Override
        public DemuxerTrackMeta getMeta() {
            TrackType t = MPSUtils.videoStream(this.streamId) ? TrackType.VIDEO : (MPSUtils.audioStream(this.streamId) ? TrackType.AUDIO : TrackType.OTHER);
            return null;
        }
    }

    public static class MPEGTrack
    extends BaseTrack
    implements ReadableByteChannel {
        private MPEGES es = new MPEGES(this, 4096);
        private LongArrayList ptsSeen = new LongArrayList(32);
        private long lastPts;
        private int lastSeq = Integer.MIN_VALUE;
        private int lastSeqSeen = 2147482647;
        private int seqWrap = 2147482647;
        private IntIntHistogram durationHistogram = new IntIntHistogram();

        public MPEGTrack(MPSDemuxer demuxer, int streamId, PESPacket pkt) throws IOException {
            super(demuxer, streamId, pkt);
        }

        @Override
        public boolean isOpen() {
            return true;
        }

        public MPEGES getES() {
            return this.es;
        }

        @Override
        public void close() throws IOException {
        }

        @Override
        public int read(ByteBuffer arg0) throws IOException {
            PESPacket pes;
            PESPacket pESPacket = pes = this._pending.size() > 0 ? (PESPacket)this._pending.remove(0) : this.getPacket();
            if (pes == null || !pes.data.hasRemaining()) {
                return -1;
            }
            int toRead = Math.min(arg0.remaining(), pes.data.remaining());
            arg0.put(NIOUtils.read(pes.data, toRead));
            if (pes.data.hasRemaining()) {
                this._pending.add(0, pes);
            } else {
                this.demuxer.putBack(pes.data);
            }
            return toRead;
        }

        private PESPacket getPacket() throws IOException {
            PESPacket pkt;
            if (this._pending.size() > 0) {
                return (PESPacket)this._pending.remove(0);
            }
            while ((pkt = this.demuxer.nextPacket(this.demuxer.getBuffer())) != null) {
                if (pkt.streamId == this.streamId) {
                    if (pkt.pts != -1L) {
                        this.ptsSeen.add(pkt.pts);
                    }
                    return pkt;
                }
                this.demuxer.addToStream(pkt);
            }
            return null;
        }

        @Override
        public Packet nextFrameWithBuffer(ByteBuffer buf) throws IOException {
            return this.es.frame(buf);
        }

        @Override
        public Packet nextFrame() throws IOException {
            MPEGPacket pkt = this.es.getFrame();
            if (pkt == null) {
                return null;
            }
            int seq = MPEGDecoder.getSequenceNumber(pkt.getData());
            if (seq == 0) {
                this.seqWrap = this.lastSeqSeen + 1;
            }
            this.lastSeqSeen = seq;
            if (this.ptsSeen.size() <= 0) {
                pkt.setPts((long)(Math.min(seq - this.lastSeq, seq - this.lastSeq + this.seqWrap) * this.durationHistogram.max()) + this.lastPts);
            } else {
                pkt.setPts(this.ptsSeen.shift());
                if (this.lastSeq >= 0 && seq > this.lastSeq) {
                    this.durationHistogram.increment((int)(pkt.getPts() - this.lastPts) / Math.min(seq - this.lastSeq, seq - this.lastSeq + this.seqWrap));
                }
                this.lastPts = pkt.getPts();
                this.lastSeq = seq;
            }
            pkt.setDuration(this.durationHistogram.max());
            System.out.println(seq);
            return pkt;
        }

        @Override
        public DemuxerTrackMeta getMeta() {
            return null;
        }
    }

    public static abstract class BaseTrack
    implements MPEGDemuxer.MPEGDemuxerTrack {
        protected int streamId;
        protected List<PESPacket> _pending = new ArrayList<PESPacket>();
        protected MPSDemuxer demuxer;

        public BaseTrack(MPSDemuxer demuxer, int streamId, PESPacket pkt) throws IOException {
            this.demuxer = demuxer;
            this.streamId = streamId;
            this._pending.add(pkt);
        }

        @Override
        public int getSid() {
            return this.streamId;
        }

        public void pending(PESPacket pkt) {
            if (this._pending != null) {
                this._pending.add(pkt);
            } else {
                this.demuxer.putBack(pkt.data);
            }
        }

        @Override
        public List<PESPacket> getPending() {
            return this._pending;
        }

        @Override
        public void ignore() {
            if (this._pending == null) {
                return;
            }
            for (PESPacket pesPacket : this._pending) {
                this.demuxer.putBack(pesPacket.data);
            }
            this._pending = null;
        }
    }
}
