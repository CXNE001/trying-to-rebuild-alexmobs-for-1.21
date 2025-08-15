/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.avi;

import com.github.alexthe666.citadel.repack.jcodec.api.FormatException;
import com.github.alexthe666.citadel.repack.jcodec.common.io.DataReader;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class AVIReader {
    public static final int FOURCC_RIFF = 1179011410;
    public static final int FOURCC_AVI = 541677121;
    public static final int FOURCC_AVIX = 1481201217;
    public static final int FOURCC_AVIH = 1751742049;
    public static final int FOURCC_LIST = 1414744396;
    public static final int FOURCC_HDRL = 1819436136;
    public static final int FOURCC_JUNK = 1263424842;
    public static final int FOURCC_INDX = 2019847785;
    public static final int FOURCC_IDXL = 829973609;
    public static final int FOURCC_STRL = 1819440243;
    public static final int FOURCC_STRH = 1752331379;
    public static final int FOURCC_STRF = 1718776947;
    public static final int FOURCC_MOVI = 1769369453;
    public static final int FOURCC_REC = 543384946;
    public static final int FOURCC_SEGM = 1835492723;
    public static final int FOURCC_ODML = 1819108463;
    public static final int FOURCC_VIDS = 1935960438;
    public static final int FOURCC_AUDS = 1935963489;
    public static final int FOURCC_MIDS = 1935960429;
    public static final int FOURCC_TXTS = 1937012852;
    public static final int FOURCC_strd = 1685222515;
    public static final int FOURCC_strn = 1852994675;
    public static final int AVIF_HASINDEX = 16;
    public static final int AVIF_MUSTUSEINDEX = 32;
    public static final int AVIF_ISINTERLEAVED = 256;
    public static final int AVIF_TRUSTCKTYPE = 2048;
    public static final int AVIF_WASCAPTUREFILE = 65536;
    public static final int AVIF_COPYRIGHTED = 131072;
    public static final int AVIIF_LIST = 1;
    public static final int AVIIF_KEYFRAME = 16;
    public static final int AVIIF_FIRSTPART = 32;
    public static final int AVIIF_LASTPART = 64;
    public static final int AVIIF_NOTIME = 256;
    public static final int AUDIO_FORMAT_PCM = 1;
    public static final int AUDIO_FORMAT_MP3 = 85;
    public static final int AUDIO_FORMAT_AC3 = 8192;
    public static final int AUDIO_FORMAT_DTS = 8193;
    public static final int AUDIO_FORMAT_VORBIS = 22127;
    public static final int AUDIO_FORMAT_EXTENSIBLE = 65534;
    public final int AVI_INDEX_OF_INDEXES = 0;
    public final int AVI_INDEX_OF_CHUNKS = 1;
    public final int AVI_INDEX_OF_TIMED_CHUNKS = 2;
    public final int AVI_INDEX_OF_SUB_2FIELD = 3;
    public final int AVI_INDEX_IS_DATA = 128;
    public static final int STDINDEXSIZE = 16384;
    private static final long SIZE_MASK = 0xFFFFFFFFL;
    private DataReader raf = null;
    private long fileLeft = 0L;
    private AVITag_AVIH aviHeader;
    private AVITag_STRH[] streamHeaders;
    private AVIChunk[] streamFormats;
    private List<AVITag_AviIndex> aviIndexes;
    private AVITag_AviDmlSuperIndex[] openDmlSuperIndex;
    private PrintStream ps = null;
    private boolean skipFrames = true;

    public AVIReader(SeekableByteChannel src) {
        this.raf = DataReader.createDataReader(src, ByteOrder.LITTLE_ENDIAN);
        this.aviIndexes = new ArrayList<AVITag_AviIndex>();
    }

    public static int fromFourCC(String str) {
        byte[] strBytes = str.getBytes();
        if (strBytes.length != 4) {
            throw new IllegalArgumentException("Expected 4 bytes not " + strBytes.length);
        }
        int fourCCInt = strBytes[3];
        fourCCInt <<= 8;
        fourCCInt |= strBytes[2];
        fourCCInt <<= 8;
        fourCCInt |= strBytes[1];
        fourCCInt <<= 8;
        return fourCCInt |= strBytes[0];
    }

    public static String toFourCC(int fourcc) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; ++i) {
            int c = fourcc & 0xFF;
            sb.append(Character.toString((char)c));
            fourcc >>= 8;
        }
        return sb.toString();
    }

    public long getFileLeft() throws IOException {
        return this.fileLeft;
    }

    public List<AVITag_AviIndex> getAviIndexes() {
        return this.aviIndexes;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void parse() throws IOException {
        try {
            long fileSize;
            long t1 = System.currentTimeMillis();
            this.fileLeft = fileSize = this.raf.size();
            int numStreams = 0;
            int streamIndex = -1;
            int videoFrameNo = 1;
            int dwFourCC = this.raf.readInt();
            if (dwFourCC != 1179011410) {
                throw new FormatException("No RIFF header found");
            }
            AVIChunk aviItem = new AVIList();
            aviItem.read(dwFourCC, this.raf);
            Logger.debug(aviItem.toString());
            int previousStreamType = 0;
            do {
                dwFourCC = this.raf.readInt();
                String dwFourCCStr = AVIReader.toFourCC(dwFourCC);
                block1 : switch (dwFourCC) {
                    case 1179011410: {
                        aviItem = new AVIList();
                        aviItem.read(dwFourCC, this.raf);
                        break;
                    }
                    case 1414744396: {
                        aviItem = new AVIList();
                        aviItem.read(dwFourCC, this.raf);
                        if (((AVIList)aviItem).getListType() != 1769369453) break;
                        aviItem.skip(this.raf);
                        break;
                    }
                    case 1819440243: {
                        aviItem = new AVIList();
                        aviItem.read(dwFourCC, this.raf);
                        break;
                    }
                    case 1751742049: {
                        this.aviHeader = new AVITag_AVIH();
                        aviItem = this.aviHeader;
                        aviItem.read(dwFourCC, this.raf);
                        numStreams = this.aviHeader.getStreams();
                        this.streamHeaders = new AVITag_STRH[numStreams];
                        this.streamFormats = new AVIChunk[numStreams];
                        this.openDmlSuperIndex = new AVITag_AviDmlSuperIndex[numStreams];
                        break;
                    }
                    case 1752331379: {
                        if (streamIndex >= numStreams) {
                            throw new IllegalStateException("Read more stream headers than expected, expected [" + numStreams + "]");
                        }
                        this.streamHeaders[++streamIndex] = new AVITag_STRH();
                        aviItem = this.streamHeaders[++streamIndex];
                        aviItem.read(dwFourCC, this.raf);
                        previousStreamType = ((AVITag_STRH)aviItem).getType();
                        break;
                    }
                    case 1718776947: {
                        switch (previousStreamType) {
                            case 1935960438: {
                                aviItem = this.streamFormats[streamIndex] = new AVITag_BitmapInfoHeader();
                                aviItem.read(dwFourCC, this.raf);
                                break block1;
                            }
                            case 1935963489: {
                                aviItem = this.streamFormats[streamIndex] = new AVITag_WaveFormatEx();
                                aviItem.read(dwFourCC, this.raf);
                                break block1;
                            }
                        }
                        throw new IOException("Expected vids or auds got [" + AVIReader.toFourCC(previousStreamType) + "]");
                    }
                    case 1835492723: {
                        aviItem = new AVI_SEGM();
                        aviItem.read(dwFourCC, this.raf);
                        aviItem.skip(this.raf);
                        break;
                    }
                    case 829973609: {
                        aviItem = new AVITag_AviIndex();
                        aviItem.read(dwFourCC, this.raf);
                        this.aviIndexes.add((AVITag_AviIndex)aviItem);
                        break;
                    }
                    case 2019847785: {
                        this.openDmlSuperIndex[streamIndex] = new AVITag_AviDmlSuperIndex();
                        this.openDmlSuperIndex[streamIndex].read(dwFourCC, this.raf);
                        aviItem = this.openDmlSuperIndex[streamIndex];
                        break;
                    }
                    default: {
                        if (dwFourCCStr.endsWith("db")) {
                            aviItem = new AVITag_VideoChunk(false, this.raf);
                            aviItem.read(dwFourCC, this.raf);
                            if (this.skipFrames) {
                                aviItem.skip(this.raf);
                                break;
                            }
                            byte[] videoFrameData = ((AVITag_VideoChunk)aviItem).getVideoPacket();
                            ByteBuffer byteBuffer = ByteBuffer.wrap(videoFrameData);
                            break;
                        }
                        if (dwFourCCStr.endsWith("dc")) {
                            aviItem = new AVITag_VideoChunk(true, this.raf);
                            aviItem.read(dwFourCC, this.raf);
                            ((AVITag_VideoChunk)aviItem).setFrameNo(videoFrameNo);
                            ++videoFrameNo;
                            String fourccStr = AVIReader.toFourCC(dwFourCC);
                            int streamNo = Integer.parseInt(fourccStr.substring(0, 2));
                            if (this.skipFrames) {
                                aviItem.skip(this.raf);
                                break;
                            }
                            byte[] videoFrameData = ((AVITag_VideoChunk)aviItem).getVideoPacket();
                            ByteBuffer byteBuffer = ByteBuffer.wrap(videoFrameData);
                            break;
                        }
                        if (dwFourCCStr.endsWith("wb")) {
                            aviItem = new AVITag_AudioChunk();
                            aviItem.read(dwFourCC, this.raf);
                            aviItem.skip(this.raf);
                            break;
                        }
                        if (dwFourCCStr.endsWith("tx")) {
                            aviItem = new AVIChunk();
                            aviItem.read(dwFourCC, this.raf);
                            aviItem.skip(this.raf);
                            break;
                        }
                        if (dwFourCCStr.startsWith("ix")) {
                            aviItem = new AVITag_AviDmlStandardIndex();
                            aviItem.read(dwFourCC, this.raf);
                            break;
                        }
                        aviItem = new AVIChunk();
                        aviItem.read(dwFourCC, this.raf);
                        aviItem.skip(this.raf);
                    }
                }
                Logger.debug(aviItem.toString());
                this.fileLeft = fileSize - this.raf.position();
            } while (this.fileLeft > 0L);
            long t2 = System.currentTimeMillis();
            Logger.debug("\tFile Left [" + this.fileLeft + "]");
            Logger.debug("\tParse time : " + (t2 - t1) + "ms");
        }
        finally {
            if (this.ps != null) {
                this.ps.close();
            }
        }
    }

    static class AVITag_AviDmlStandardIndex
    extends AVIChunk {
        protected short wLongsPerEntry;
        protected byte bIndexSubType;
        protected byte bIndexType;
        protected int nEntriesInUse;
        protected int dwChunkId;
        protected long qwBaseOffset;
        protected int dwReserved2;
        protected int[] dwOffset;
        protected int[] dwDuration;
        int lastOffset = -1;
        int lastDuration = -1;

        AVITag_AviDmlStandardIndex() {
        }

        @Override
        public int getChunkSize() {
            return this.dwChunkSize;
        }

        @Override
        public void read(int dwFourCC, DataReader raf) throws IOException {
            super.read(dwFourCC, raf);
            this.wLongsPerEntry = raf.readShort();
            this.bIndexSubType = raf.readByte();
            this.bIndexType = raf.readByte();
            this.nEntriesInUse = raf.readInt();
            this.dwChunkId = raf.readInt();
            this.qwBaseOffset = raf.readLong();
            this.dwReserved2 = raf.readInt();
            this.dwOffset = new int[this.nEntriesInUse];
            this.dwDuration = new int[this.nEntriesInUse];
            try {
                for (int i = 0; i < this.nEntriesInUse; ++i) {
                    this.dwOffset[i] = raf.readInt();
                    this.dwDuration[i] = raf.readInt();
                    this.lastOffset = this.dwOffset[i];
                    this.lastDuration = this.dwDuration[i];
                }
            }
            catch (Exception e) {
                Logger.debug("Failed to read : " + this.toString());
            }
            raf.setPosition(this.getEndOfChunk());
        }

        @Override
        public String toString() {
            return String.format("\tAvi DML Standard Index List Type=%d, SubType=%d, ChunkId=%s, StartOfChunk=%d, NumIndexes=%d, LongsPerEntry=%d, ChunkSize=%d, FirstOffset=%d, FirstDuration=%d,LastOffset=%d, LastDuration=%d", this.bIndexType, this.bIndexSubType, AVIReader.toFourCC(this.dwChunkId), this.getStartOfChunk(), this.nEntriesInUse, this.wLongsPerEntry, this.getChunkSize(), this.dwOffset[0], this.dwDuration[0], this.lastOffset, this.lastDuration);
        }
    }

    static class AVITag_AviDmlSuperIndex
    extends AVIChunk {
        protected short wLongsPerEntry;
        protected byte bIndexSubType;
        protected byte bIndexType;
        protected int nEntriesInUse;
        protected int dwChunkId;
        protected int[] dwReserved = new int[3];
        protected long[] qwOffset;
        protected int[] dwSize;
        protected int[] dwDuration;
        private int numIndex;
        private int numIndexFill;
        StringBuilder sb = new StringBuilder();
        private int streamNo = 0;

        @Override
        public void read(int dwFourCC, DataReader raf) throws IOException {
            super.read(dwFourCC, raf);
            this.wLongsPerEntry = raf.readShort();
            this.bIndexSubType = raf.readByte();
            this.bIndexType = raf.readByte();
            this.nEntriesInUse = raf.readInt();
            this.dwChunkId = raf.readInt();
            this.dwReserved[0] = raf.readInt();
            this.dwReserved[1] = raf.readInt();
            this.dwReserved[2] = raf.readInt();
            this.qwOffset = new long[this.nEntriesInUse];
            this.dwSize = new int[this.nEntriesInUse];
            this.dwDuration = new int[this.nEntriesInUse];
            String chunkIdStr = AVIReader.toFourCC(this.dwChunkId);
            this.sb.append(String.format("\tAvi DML Super Index List - ChunkSize=%d, NumIndexes = %d, longsPerEntry = %d, Stream = %s, Type = %s", this.getChunkSize(), this.nEntriesInUse, this.wLongsPerEntry, chunkIdStr.substring(0, 2), chunkIdStr.substring(2)));
            for (int i = 0; i < this.nEntriesInUse; ++i) {
                this.qwOffset[i] = raf.readLong();
                this.dwSize[i] = raf.readInt();
                this.dwDuration[i] = raf.readInt();
                this.sb.append(String.format("\n\t\tStandard Index - Offset [%d], Size [%d], Duration [%d]", this.qwOffset[i], this.dwSize[i], this.dwDuration[i]));
            }
            raf.setPosition(this.getEndOfChunk());
        }

        @Override
        public String toString() {
            return this.sb.toString();
        }
    }

    static class AVITag_AviIndex
    extends AVIChunk {
        protected int numIndexes = 0;
        protected int[] ckid;
        protected int[] dwFlags;
        protected int[] dwChunkOffset;
        protected int[] dwChunkLength;

        AVITag_AviIndex() {
        }

        @Override
        public void read(int dwFourCC, DataReader raf) throws IOException {
            super.read(dwFourCC, raf);
            this.numIndexes = this.getChunkSize() >> 4;
            this.ckid = new int[this.numIndexes];
            this.dwFlags = new int[this.numIndexes];
            this.dwChunkOffset = new int[this.numIndexes];
            this.dwChunkLength = new int[this.numIndexes];
            for (int i = 0; i < this.numIndexes; ++i) {
                this.ckid[i] = raf.readInt();
                this.dwFlags[i] = raf.readInt();
                this.dwChunkOffset[i] = raf.readInt();
                this.dwChunkLength[i] = raf.readInt();
            }
            raf.setPosition(this.getEndOfChunk());
            int alignment = this.getChunkSize() - this.dwChunkSize;
            if (alignment > 0) {
                raf.skipBytes(alignment);
            }
        }

        public int getNumIndexes() {
            return this.numIndexes;
        }

        public int[] getCkid() {
            return this.ckid;
        }

        public int[] getDwFlags() {
            return this.dwFlags;
        }

        public int[] getDwChunkOffset() {
            return this.dwChunkOffset;
        }

        public int[] getDwChunkLength() {
            return this.dwChunkLength;
        }

        public void debugOut() {
            for (int i = 0; i < this.numIndexes; ++i) {
                Logger.debug("\t");
            }
        }

        @Override
        public String toString() {
            return String.format("\tAvi Index List, StartOfChunk [%d], ChunkSize [%d], NumIndexes [%d]", this.getStartOfChunk(), this.dwChunkSize, this.getChunkSize() >> 4);
        }
    }

    static class AVITag_AudioChunk
    extends AVIChunk {
        protected int streamNo;
        private DataReader raf;

        AVITag_AudioChunk() {
        }

        @Override
        public void read(int dwFourCC, DataReader raf) throws IOException {
            this.raf = raf;
            super.read(dwFourCC, raf);
            String fourccStr = AVIReader.toFourCC(dwFourCC);
            this.streamNo = Integer.parseInt(fourccStr.substring(0, 2));
        }

        @Override
        public int getChunkSize() {
            if ((this.dwChunkSize & 1) == 1) {
                return this.dwChunkSize + 1;
            }
            return this.dwChunkSize;
        }

        public byte[] getAudioPacket() throws IOException {
            byte[] audioFrameData = new byte[this.dwChunkSize];
            int bytesRead = this.raf.readFully(audioFrameData);
            if (bytesRead != this.dwChunkSize) {
                throw new IOException("Read mismatch expected chunksize [" + this.dwChunkSize + "], Actual read [" + bytesRead + "]");
            }
            int alignment = this.getChunkSize() - this.dwChunkSize;
            if (alignment > 0) {
                this.raf.skipBytes(alignment);
            }
            return audioFrameData;
        }

        @Override
        public String toString() {
            return "\tAUDIO CHUNK - Stream " + this.streamNo + ", StartOfChunk=" + this.getStartOfChunk() + ", ChunkSize=" + this.getChunkSize();
        }
    }

    static class AVITag_VideoChunk
    extends AVIChunk {
        protected int streamNo;
        protected boolean compressed = false;
        protected int frameNo = -1;
        private DataReader raf;

        public AVITag_VideoChunk(boolean compressed, DataReader raf) {
            this.compressed = compressed;
            this.raf = raf;
        }

        public int getStreamNo() {
            return this.streamNo;
        }

        public void setFrameNo(int frameNo) {
            this.frameNo = frameNo;
        }

        @Override
        public int getChunkSize() {
            if ((this.dwChunkSize & 1) == 1) {
                return this.dwChunkSize + 1;
            }
            return this.dwChunkSize;
        }

        @Override
        public void read(int dwFourCC, DataReader raf) throws IOException {
            super.read(dwFourCC, raf);
            String fourccStr = AVIReader.toFourCC(dwFourCC);
            this.streamNo = Integer.parseInt(fourccStr.substring(0, 2));
        }

        public byte[] getVideoPacket() throws IOException {
            byte[] videoFrameData = new byte[this.dwChunkSize];
            int bytesRead = this.raf.readFully(videoFrameData);
            if (bytesRead != this.dwChunkSize) {
                throw new IOException("Read mismatch expected chunksize [" + this.dwChunkSize + "], Actual read [" + bytesRead + "]");
            }
            int alignment = this.getChunkSize() - this.dwChunkSize;
            if (alignment > 0) {
                this.raf.skipBytes(alignment);
            }
            return videoFrameData;
        }

        @Override
        public String toString() {
            return "\tVIDEO CHUNK - Stream " + this.streamNo + ",  chunkStart=" + this.getStartOfChunk() + ", " + (this.compressed ? "compressed" : "uncompressed") + ", ChunkSize=" + this.getChunkSize() + ", FrameNo=" + this.frameNo;
        }
    }

    static class AVITag_WaveFormatEx
    extends AVIChunk {
        public static final int SPEAKER_FRONT_LEFT = 1;
        public static final int SPEAKER_FRONT_RIGHT = 2;
        public static final int SPEAKER_FRONT_CENTER = 4;
        public static final int SPEAKER_LOW_FREQUENCY = 8;
        public static final int SPEAKER_BACK_LEFT = 16;
        public static final int SPEAKER_BACK_RIGHT = 32;
        public static final int SPEAKER_FRONT_LEFT_OF_CENTER = 64;
        public static final int SPEAKER_FRONT_RIGHT_OF_CENTER = 128;
        public static final int SPEAKER_BACK_CENTER = 256;
        public static final int SPEAKER_SIDE_LEFT = 512;
        public static final int SPEAKER_SIDE_RIGHT = 1024;
        public static final int SPEAKER_TOP_CENTER = 2048;
        public static final int SPEAKER_TOP_FRONT_LEFT = 4096;
        public static final int SPEAKER_TOP_FRONT_CENTER = 8192;
        public static final int SPEAKER_TOP_FRONT_RIGHT = 16384;
        public static final int SPEAKER_TOP_BACK_LEFT = 32768;
        public static final int SPEAKER_TOP_BACK_CENTER = 65536;
        public static final int SPEAKER_TOP_BACK_RIGHT = 131072;
        protected short wFormatTag;
        protected short channels;
        protected int nSamplesPerSec;
        protected int nAvgBytesPerSec;
        protected short nBlockAlign;
        protected short wBitsPerSample;
        protected short cbSize;
        protected short wValidBitsPerSample;
        protected short samplesValidBitsPerSample;
        protected short wReserved;
        protected int channelMask;
        protected int guid_data1;
        protected short guid_data2;
        protected short guid_data3;
        protected byte[] guid_data4 = new byte[8];
        protected boolean mp3Flag = false;
        protected short wID;
        protected int fdwFlags;
        protected short nBlockSize;
        protected short nFramesPerBlock;
        protected short nCodecDelay;
        private String audioFormat = "?";

        @Override
        public void read(int dwFourCC, DataReader raf) throws IOException {
            super.read(dwFourCC, raf);
            this.wFormatTag = raf.readShort();
            this.channels = raf.readShort();
            this.nSamplesPerSec = raf.readInt();
            this.nAvgBytesPerSec = raf.readInt();
            this.nBlockAlign = raf.readShort();
            switch (this.wFormatTag) {
                case 1: {
                    this.wBitsPerSample = raf.readShort();
                    if (this.dwChunkSize == 40) {
                        this.samplesValidBitsPerSample = this.wReserved = raf.readShort();
                        this.wValidBitsPerSample = this.wReserved;
                        this.cbSize = raf.readShort();
                        this.channelMask = raf.readInt();
                        this.guid_data1 = raf.readInt();
                        this.guid_data2 = raf.readShort();
                        this.guid_data3 = raf.readShort();
                        raf.readFully(this.guid_data4);
                    }
                    this.audioFormat = "PCM";
                    break;
                }
                case 85: {
                    this.wBitsPerSample = raf.readShort();
                    this.cbSize = raf.readShort();
                    this.wID = raf.readShort();
                    this.fdwFlags = raf.readInt();
                    this.nBlockSize = raf.readShort();
                    this.nFramesPerBlock = raf.readShort();
                    this.nCodecDelay = raf.readShort();
                    this.mp3Flag = true;
                    this.audioFormat = "MP3";
                    break;
                }
                case 8192: {
                    this.audioFormat = "AC3";
                    break;
                }
                case 8193: {
                    this.audioFormat = "DTS";
                    break;
                }
                case 22127: {
                    this.audioFormat = "VORBIS";
                    break;
                }
                case 65534: {
                    this.wBitsPerSample = raf.readShort();
                    this.cbSize = raf.readShort();
                    this.samplesValidBitsPerSample = this.wReserved = raf.readShort();
                    this.wValidBitsPerSample = this.wReserved;
                    this.channelMask = raf.readInt();
                    this.guid_data1 = raf.readInt();
                    this.guid_data2 = raf.readShort();
                    this.guid_data3 = raf.readShort();
                    raf.readFully(this.guid_data4);
                    this.audioFormat = "EXTENSIBLE";
                    break;
                }
                default: {
                    this.audioFormat = "Unknown : " + Integer.toHexString(this.wFormatTag);
                }
            }
        }

        public boolean isMP3() {
            return this.mp3Flag;
        }

        public short getCbSize() {
            return this.cbSize;
        }

        @Override
        public String toString() {
            return String.format("\tCHUNK [%s], ChunkSize [%d], Format [%s], Channels [%d], Channel Mask [%s], MP3 [%b], SamplesPerSec [%d], nBlockAlign [%d]", AVIReader.toFourCC(this.dwFourCC), this.getChunkSize(), this.audioFormat, this.channels, Integer.toHexString(this.channelMask), this.mp3Flag, this.nSamplesPerSec, this.getStartOfChunk(), this.nBlockAlign);
        }
    }

    static class AVITag_BitmapInfoHeader
    extends AVIChunk {
        private int biSize;
        private int biWidth;
        private int biHeight;
        private short biPlanes;
        private short biBitCount;
        private int biCompression;
        private int biSizeImage;
        private int biXPelsPerMeter;
        private int biYPelsPerMeter;
        private int biClrUsed;
        private int biClrImportant;
        private byte r;
        private byte g;
        private byte b;
        private byte x;

        AVITag_BitmapInfoHeader() {
        }

        @Override
        public void read(int dwFourCC, DataReader raf) throws IOException {
            super.read(dwFourCC, raf);
            this.biSize = raf.readInt();
            this.biWidth = raf.readInt();
            this.biHeight = raf.readInt();
            this.biPlanes = raf.readShort();
            this.biBitCount = raf.readShort();
            this.biCompression = raf.readInt();
            this.biSizeImage = raf.readInt();
            this.biXPelsPerMeter = raf.readInt();
            this.biYPelsPerMeter = raf.readInt();
            this.biClrUsed = raf.readInt();
            this.biClrImportant = raf.readInt();
            if (this.getChunkSize() == 56) {
                this.r = raf.readByte();
                this.g = raf.readByte();
                this.b = raf.readByte();
                this.x = raf.readByte();
            }
        }

        @Override
        public int getChunkSize() {
            return this.biSize;
        }

        @Override
        public String toString() {
            return "\tCHUNK [" + AVIReader.toFourCC(this.dwFourCC) + "], BitsPerPixel [" + this.biBitCount + "], Resolution [" + ((long)this.biWidth & 0xFFFFFFFFL) + " x " + ((long)this.biHeight & 0xFFFFFFFFL) + "], Planes [" + this.biPlanes + "]";
        }
    }

    static class AVITag_STRH
    extends AVIChunk {
        static final int AVISF_DISABLED = 1;
        static final int AVISF_VIDEO_PALCHANGES = 65536;
        private int fccType;
        private int fccCodecHandler;
        private int dwFlags = 0;
        private short wPriority = 0;
        private short wLanguage = 0;
        private int dwInitialFrames = 0;
        private int dwScale = 0;
        private int dwRate = 1000000;
        private int dwStart = 0;
        private int dwLength = 0;
        private int dwSuggestedBufferSize = 0;
        private int dwQuality = -1;
        private int dwSampleSize = 0;
        private short left = 0;
        private short top = 0;
        private short right = 0;
        private short bottom = 0;

        AVITag_STRH() {
        }

        @Override
        public void read(int dwFourCC, DataReader raf) throws IOException {
            super.read(dwFourCC, raf);
            if (dwFourCC != 1752331379) {
                throw new IOException("Expected 'strh' fourcc got [" + AVIReader.toFourCC(this.dwFourCC) + "]");
            }
            this.fccType = raf.readInt();
            this.fccCodecHandler = raf.readInt();
            this.dwFlags = raf.readInt();
            this.wPriority = raf.readShort();
            this.wLanguage = raf.readShort();
            this.dwInitialFrames = raf.readInt();
            this.dwScale = raf.readInt();
            this.dwRate = raf.readInt();
            this.dwStart = raf.readInt();
            this.dwLength = raf.readInt();
            this.dwSuggestedBufferSize = raf.readInt();
            this.dwQuality = raf.readInt();
            this.dwSampleSize = raf.readInt();
            this.left = raf.readShort();
            this.top = raf.readShort();
            this.right = raf.readShort();
            this.bottom = raf.readShort();
        }

        public int getType() {
            return this.fccType;
        }

        public int getHandler() {
            return this.fccCodecHandler;
        }

        public String getHandlerStr() {
            if (this.fccCodecHandler != 0) {
                return AVIReader.toFourCC(this.fccCodecHandler);
            }
            return "";
        }

        public int getInitialFrames() {
            return this.dwInitialFrames;
        }

        @Override
        public String toString() {
            return "\tCHUNK [" + AVIReader.toFourCC(this.dwFourCC) + "], Type[" + (this.fccType > 0 ? AVIReader.toFourCC(this.fccType) : "    ") + "], Handler [" + (this.fccCodecHandler > 0 ? AVIReader.toFourCC(this.fccCodecHandler) : "    ") + "]";
        }
    }

    static class AVITag_AVIH
    extends AVIChunk {
        public String _getHeight;
        static final int AVIF_HASINDEX = 16;
        static final int AVIF_MUSTUSEINDEX = 32;
        static final int AVIF_ISINTERLEAVED = 256;
        static final int AVIF_TRUSTCKTYPE = 2048;
        static final int AVIF_WASCAPTUREFILE = 65536;
        static final int AVIF_COPYRIGHTED = 131072;
        private int dwMicroSecPerFrame;
        private int dwMaxBytesPerSec;
        private int dwPaddingGranularity;
        private int dwFlags;
        private int dwTotalFrames;
        private int dwInitialFrames;
        private int dwStreams;
        private int dwSuggestedBufferSize;
        private int dwWidth;
        private int dwHeight;
        private int[] dwReserved = new int[4];

        @Override
        public void read(int dwFourCC, DataReader raf) throws IOException {
            super.read(dwFourCC, raf);
            if (dwFourCC != 1751742049) {
                throw new IOException("Unexpected AVI header : " + AVIReader.toFourCC(dwFourCC));
            }
            if (this.getChunkSize() != 56) {
                throw new IOException("Expected dwSize=56");
            }
            this.dwMicroSecPerFrame = raf.readInt();
            this.dwMaxBytesPerSec = raf.readInt();
            this.dwPaddingGranularity = raf.readInt();
            this.dwFlags = raf.readInt();
            this.dwTotalFrames = raf.readInt();
            this.dwInitialFrames = raf.readInt();
            this.dwStreams = raf.readInt();
            this.dwSuggestedBufferSize = raf.readInt();
            this.dwWidth = raf.readInt();
            this.dwHeight = raf.readInt();
            this.dwReserved[0] = raf.readInt();
            this.dwReserved[1] = raf.readInt();
            this.dwReserved[2] = raf.readInt();
            this.dwReserved[3] = raf.readInt();
        }

        public int getWidth() {
            return this.dwWidth;
        }

        public int getHeight() {
            return this.dwHeight;
        }

        public int getStreams() {
            return this.dwStreams;
        }

        public int getTotalFrames() {
            return this.dwTotalFrames;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if ((this.dwFlags & 0x10) != 0) {
                sb.append("HASINDEX ");
            }
            if ((this.dwFlags & 0x20) != 0) {
                sb.append("MUSTUSEINDEX ");
            }
            if ((this.dwFlags & 0x100) != 0) {
                sb.append("ISINTERLEAVED ");
            }
            if ((this.dwFlags & 0x10000) != 0) {
                sb.append("AVIF_WASCAPTUREFILE ");
            }
            if ((this.dwFlags & 0x20000) != 0) {
                sb.append("AVIF_COPYRIGHTED ");
            }
            return "AVIH Resolution [" + this.dwWidth + "x" + this.dwHeight + "], NumFrames [" + this.dwTotalFrames + "], Flags [" + Integer.toHexString(this.dwFlags) + "] - [" + sb.toString().trim() + "]";
        }
    }

    static class AVI_SEGM
    extends AVIChunk {
        AVI_SEGM() {
        }

        @Override
        public void read(int dwFourCC, DataReader raf) throws IOException {
            super.read(dwFourCC, raf);
        }

        @Override
        public int getChunkSize() {
            if (this.dwChunkSize == 0) {
                return 0;
            }
            return this.dwChunkSize + 1;
        }

        @Override
        public String toString() {
            return "SEGMENT Align, Size [" + this.dwChunkSize + "], StartOfChunk [" + this.getStartOfChunk() + "]";
        }
    }

    static class AVIList
    extends AVIChunk {
        protected int dwListTypeFourCC;
        protected String dwListTypeFourCCStr;

        AVIList() {
        }

        @Override
        public void read(int dwFourCC, DataReader raf) throws IOException {
            super.read(dwFourCC, raf);
            this.dwChunkSize -= 4;
            this.dwListTypeFourCC = raf.readInt();
            this.dwListTypeFourCCStr = AVIReader.toFourCC(this.dwListTypeFourCC);
        }

        public int getListType() {
            return this.dwListTypeFourCC;
        }

        @Override
        public String toString() {
            String dwFourCCStr = AVIReader.toFourCC(this.dwFourCC);
            return dwFourCCStr + " [" + this.dwListTypeFourCCStr + "], Size [" + this.dwChunkSize + "], StartOfChunk [" + this.getStartOfChunk() + "]";
        }
    }

    static class AVIChunk {
        protected int dwFourCC;
        protected String fwFourCCStr;
        protected int dwChunkSize;
        protected long startOfChunk;

        AVIChunk() {
        }

        public void read(int dwFourCC, DataReader raf) throws IOException {
            this.startOfChunk = raf.position() - 4L;
            this.dwFourCC = dwFourCC;
            this.fwFourCCStr = AVIReader.toFourCC(dwFourCC);
            this.dwChunkSize = raf.readInt();
        }

        public long getStartOfChunk() {
            return this.startOfChunk;
        }

        public long getEndOfChunk() {
            return this.startOfChunk + 8L + (long)this.getChunkSize();
        }

        public int getFourCC() {
            return this.dwFourCC;
        }

        public void skip(DataReader raf) throws IOException {
            int chunkSize = this.getChunkSize();
            if (chunkSize < 0) {
                throw new IOException("Negative chunk size for chunk [" + AVIReader.toFourCC(this.dwFourCC) + "]");
            }
            raf.skipBytes(chunkSize);
        }

        public int getChunkSize() {
            if ((this.dwChunkSize & 1) == 1) {
                return this.dwChunkSize + 1;
            }
            return this.dwChunkSize;
        }

        public String toString() {
            String chunkStr = AVIReader.toFourCC(this.dwFourCC);
            if (chunkStr.trim().length() == 0) {
                chunkStr = Integer.toHexString(this.dwFourCC);
            }
            return "\tCHUNK [" + chunkStr + "], Size [" + this.dwChunkSize + "], StartOfChunk [" + this.getStartOfChunk() + "]";
        }
    }
}
