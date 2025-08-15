/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.common.IntArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.Preconditions;
import com.github.alexthe666.citadel.repack.jcodec.common.io.FileChannelWrapper;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.psi.PATSection;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.psi.PMTSection;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.psi.PSISection;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class MTSUtils {
    @Deprecated
    public static int parsePAT(ByteBuffer data) {
        PATSection pat = PATSection.parsePAT(data);
        if (pat.getPrograms().size() > 0) {
            return pat.getPrograms().values()[0];
        }
        return -1;
    }

    @Deprecated
    public static PMTSection parsePMT(ByteBuffer data) {
        return PMTSection.parsePMT(data);
    }

    @Deprecated
    public static PSISection parseSection(ByteBuffer data) {
        return PSISection.parsePSI(data);
    }

    private static void parseEsInfo(ByteBuffer read) {
    }

    public static PMTSection.PMTStream[] getProgramGuids(File src) throws IOException {
        FileChannelWrapper ch = null;
        try {
            ch = NIOUtils.readableChannel(src);
            PMTSection.PMTStream[] pMTStreamArray = MTSUtils.getProgramGuidsFromChannel(ch);
            return pMTStreamArray;
        }
        finally {
            NIOUtils.closeQuietly(ch);
        }
    }

    public static PMTSection.PMTStream[] getProgramGuidsFromChannel(SeekableByteChannel _in) throws IOException {
        PMTExtractor ex = new PMTExtractor();
        ex.readTsFile(_in);
        PMTSection pmt = ex.getPmt();
        return pmt.getStreams();
    }

    public static int getVideoPid(File src) throws IOException {
        for (PMTSection.PMTStream stream : MTSUtils.getProgramGuids(src)) {
            if (!stream.getStreamType().isVideo()) continue;
            return stream.getPid();
        }
        throw new RuntimeException("No video stream");
    }

    public static int getAudioPid(File src) throws IOException {
        for (PMTSection.PMTStream stream : MTSUtils.getProgramGuids(src)) {
            if (!stream.getStreamType().isAudio()) continue;
            return stream.getPid();
        }
        throw new RuntimeException("No audio stream");
    }

    public static int[] getMediaPidsFromChannel(SeekableByteChannel src) throws IOException {
        return MTSUtils.filterMediaPids(MTSUtils.getProgramGuidsFromChannel(src));
    }

    public static int[] getMediaPids(File src) throws IOException {
        return MTSUtils.filterMediaPids(MTSUtils.getProgramGuids(src));
    }

    private static int[] filterMediaPids(PMTSection.PMTStream[] programs) {
        IntArrayList result = IntArrayList.createIntArrayList();
        for (PMTSection.PMTStream stream : programs) {
            if (!stream.getStreamType().isVideo() && !stream.getStreamType().isAudio()) continue;
            result.add(stream.getPid());
        }
        return result.toArray();
    }

    public static abstract class TSReader {
        private static final int TS_SYNC_MARKER = 71;
        private static final int TS_PKT_SIZE = 188;
        public static final int BUFFER_SIZE = 96256;
        private boolean flush;

        public TSReader(boolean flush) {
            this.flush = flush;
        }

        public void readTsFile(SeekableByteChannel ch) throws IOException {
            ch.setPosition(0L);
            ByteBuffer buf = ByteBuffer.allocate(96256);
            long pos = ch.position();
            while (ch.read(buf) >= 188) {
                long posRem = pos;
                buf.flip();
                while (buf.remaining() >= 188) {
                    boolean sectionSyntax;
                    ByteBuffer tsBuf = NIOUtils.read(buf, 188);
                    ByteBuffer fullPkt = tsBuf.duplicate();
                    pos += 188L;
                    Preconditions.checkState(71 == (tsBuf.get() & 0xFF));
                    int guidFlags = (tsBuf.get() & 0xFF) << 8 | tsBuf.get() & 0xFF;
                    int guid = guidFlags & 0x1FFF;
                    int payloadStart = guidFlags >> 14 & 1;
                    int b0 = tsBuf.get() & 0xFF;
                    int counter = b0 & 0xF;
                    if ((b0 & 0x20) != 0) {
                        NIOUtils.skip(tsBuf, tsBuf.get() & 0xFF);
                    }
                    boolean bl = sectionSyntax = payloadStart == 1 && (NIOUtils.getRel(tsBuf, NIOUtils.getRel(tsBuf, 0) + 2) & 0x80) == 128;
                    if (sectionSyntax) {
                        NIOUtils.skip(tsBuf, tsBuf.get() & 0xFF);
                    }
                    if (this.onPkt(guid, payloadStart == 1, tsBuf, pos - (long)tsBuf.remaining(), sectionSyntax, fullPkt)) continue;
                    return;
                }
                if (this.flush) {
                    buf.flip();
                    ch.setPosition(posRem);
                    ch.write(buf);
                }
                buf.clear();
                pos = ch.position();
            }
        }

        protected boolean onPkt(int guid, boolean payloadStart, ByteBuffer tsBuf, long filePos, boolean sectionSyntax, ByteBuffer fullPkt) {
            return true;
        }
    }

    private static class PMTExtractor
    extends TSReader {
        private int pmtGuid = -1;
        private PMTSection pmt;

        public PMTExtractor() {
            super(false);
        }

        @Override
        public boolean onPkt(int guid, boolean payloadStart, ByteBuffer tsBuf, long filePos, boolean sectionSyntax, ByteBuffer fullPkt) {
            if (guid == 0) {
                this.pmtGuid = MTSUtils.parsePAT(tsBuf);
            } else if (this.pmtGuid != -1 && guid == this.pmtGuid) {
                this.pmt = MTSUtils.parsePMT(tsBuf);
                return false;
            }
            return true;
        }

        public PMTSection getPmt() {
            return this.pmt;
        }
    }
}
