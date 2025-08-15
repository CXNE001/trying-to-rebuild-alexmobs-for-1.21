/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import com.github.alexthe666.citadel.repack.jcodec.common.AudioFormat;
import com.github.alexthe666.citadel.repack.jcodec.common.Codec;
import com.github.alexthe666.citadel.repack.jcodec.common.IntArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ChannelLabel;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Rational;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Size;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.MPSUtils;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MPSMediaInfo
extends MPSUtils.PESReader {
    private Map<Integer, MPEGTrackMetadata> infos = new HashMap<Integer, MPEGTrackMetadata>();
    private int pesTried;
    private PSM psm;

    public List<MPEGTrackMetadata> getMediaInfo(File f) throws IOException {
        try {
            new NIOUtils.FileReader(){

                @Override
                protected void data(ByteBuffer data, long filePos) {
                    MPSMediaInfo.this.analyseBuffer(data, filePos);
                }

                @Override
                protected void done() {
                }
            }.readFile(f, 65536, null);
        }
        catch (MediaInfoDone e) {
            Logger.info("Media info done");
        }
        return this.getInfos();
    }

    @Override
    protected void pes(ByteBuffer pesBuffer, long start, int pesLen, int stream) {
        if (!MPSUtils.mediaStream(stream)) {
            return;
        }
        MPEGTrackMetadata info = this.infos.get(stream);
        if (info == null) {
            info = new MPEGTrackMetadata(stream);
            this.infos.put(stream, info);
        }
        if (info.probeData == null) {
            info.probeData = NIOUtils.cloneBuffer(pesBuffer);
        }
        if (++this.pesTried >= 100) {
            this.deriveMediaInfo();
            throw new MediaInfoDone();
        }
    }

    private void deriveMediaInfo() {
        Collection<MPEGTrackMetadata> values = this.infos.values();
        for (MPEGTrackMetadata stream : values) {
            int streamId = 0x100 | stream.streamId;
            if (streamId >= 448 && streamId <= 479) {
                stream.codec = Codec.MP2;
                continue;
            }
            if (streamId == 445) {
                ByteBuffer dup = stream.probeData.duplicate();
                MPSUtils.readPESHeader(dup, 0L);
                int type = dup.get() & 0xFF;
                if (type >= 128 && type <= 135) {
                    stream.codec = Codec.AC3;
                    continue;
                }
                if (type >= 136 && type <= 143 || type >= 152 && type <= 159) {
                    stream.codec = Codec.DTS;
                    continue;
                }
                if (type >= 160 && type <= 175) {
                    stream.codec = Codec.PCM_DVD;
                    continue;
                }
                if (type >= 176 && type <= 191) {
                    stream.codec = Codec.TRUEHD;
                    continue;
                }
                if (type < 192 || type > 207) continue;
                stream.codec = Codec.AC3;
                continue;
            }
            if (streamId < 480 || streamId > 495) continue;
            stream.codec = Codec.MPEG2;
        }
    }

    private int[] parseSystem(ByteBuffer pesBuffer) {
        NIOUtils.skip(pesBuffer, 12);
        IntArrayList result = IntArrayList.createIntArrayList();
        while (pesBuffer.remaining() >= 3 && (pesBuffer.get(pesBuffer.position()) & 0x80) == 128) {
            result.add(pesBuffer.get() & 0xFF);
            pesBuffer.getShort();
        }
        return result.toArray();
    }

    private PSM parsePSM(ByteBuffer pesBuffer) {
        pesBuffer.getInt();
        short psmLen = pesBuffer.getShort();
        if (psmLen > 1018) {
            throw new RuntimeException("Invalid PSM");
        }
        byte b0 = pesBuffer.get();
        byte b1 = pesBuffer.get();
        if ((b1 & 1) != 1) {
            throw new RuntimeException("Invalid PSM");
        }
        short psiLen = pesBuffer.getShort();
        ByteBuffer psi = NIOUtils.read(pesBuffer, psiLen & 0xFFFF);
        short elStreamLen = pesBuffer.getShort();
        this.parseElStreams(NIOUtils.read(pesBuffer, elStreamLen & 0xFFFF));
        int crc = pesBuffer.getInt();
        return new PSM();
    }

    private void parseElStreams(ByteBuffer buf) {
        while (buf.hasRemaining()) {
            byte streamType = buf.get();
            byte streamId = buf.get();
            short strInfoLen = buf.getShort();
            ByteBuffer byteBuffer = NIOUtils.read(buf, strInfoLen & 0xFFFF);
        }
    }

    public List<MPEGTrackMetadata> getInfos() {
        return new ArrayList<MPEGTrackMetadata>(this.infos.values());
    }

    public static void main1(String[] args) throws IOException {
        new MPSMediaInfo().getMediaInfo(new File(args[0]));
    }

    public static MPSMediaInfo extract(SeekableByteChannel input) {
        return null;
    }

    public List<MPEGTrackMetadata> getAudioTracks() {
        return null;
    }

    public MPEGTrackMetadata getVideoTrack() {
        return null;
    }

    public static class PSM {
    }

    public static class MediaInfoDone
    extends RuntimeException {
    }

    public static class MPEGTrackMetadata {
        int streamId;
        Codec codec;
        ByteBuffer probeData;

        public MPEGTrackMetadata(int streamId) {
            this.streamId = streamId;
        }

        public AudioFormat getAudioFormat() {
            return null;
        }

        public ChannelLabel[] getChannelLables() {
            return null;
        }

        public Size getDisplaySize() {
            return null;
        }

        public Size getCodedSize() {
            return null;
        }

        public float getFps() {
            return 0.0f;
        }

        public float getDuration() {
            return 0.0f;
        }

        public String getFourcc() {
            return null;
        }

        public Rational getFpsR() {
            return null;
        }

        public int getNumFrames() {
            return 0;
        }

        public MPEGTimecodeMetadata getTimecode() {
            return null;
        }
    }

    public static class MPEGTimecodeMetadata {
        public String getNumFrames() {
            return null;
        }

        public String isDropFrame() {
            return null;
        }

        public String getStartCounter() {
            return null;
        }
    }
}
