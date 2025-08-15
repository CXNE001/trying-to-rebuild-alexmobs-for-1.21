/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.MPEGDecoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.MPEGES;
import com.github.alexthe666.citadel.repack.jcodec.common.DemuxerTrackMeta;
import com.github.alexthe666.citadel.repack.jcodec.common.IntIntHistogram;
import com.github.alexthe666.citadel.repack.jcodec.common.LongArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Packet;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPEGPacket;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSDemuxer;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.PESPacket;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public static class MPSDemuxer.MPEGTrack
extends MPSDemuxer.BaseTrack
implements ReadableByteChannel {
    private MPEGES es = new MPEGES(this, 4096);
    private LongArrayList ptsSeen = new LongArrayList(32);
    private long lastPts;
    private int lastSeq = Integer.MIN_VALUE;
    private int lastSeqSeen = 2147482647;
    private int seqWrap = 2147482647;
    private IntIntHistogram durationHistogram = new IntIntHistogram();

    public MPSDemuxer.MPEGTrack(MPSDemuxer demuxer, int streamId, PESPacket pkt) throws IOException {
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
