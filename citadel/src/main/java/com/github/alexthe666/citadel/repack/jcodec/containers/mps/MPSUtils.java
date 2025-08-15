/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mps;

import com.github.alexthe666.citadel.repack.jcodec.common.IntArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Rational;
import com.github.alexthe666.citadel.repack.jcodec.containers.mps.PESPacket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class MPSUtils {
    public static final int VIDEO_MIN = 480;
    public static final int VIDEO_MAX = 495;
    public static final int AUDIO_MIN = 448;
    public static final int AUDIO_MAX = 479;
    public static final int PACK = 442;
    public static final int SYSTEM = 443;
    public static final int PSM = 444;
    public static final int PRIVATE_1 = 445;
    public static final int PRIVATE_2 = 447;
    public static Class<? extends MPEGMediaDescriptor>[] dMapping = new Class[256];

    public static final boolean mediaStream(int streamId) {
        return streamId >= MPSUtils.$(448) && streamId <= MPSUtils.$(495) || streamId == MPSUtils.$(445) || streamId == MPSUtils.$(447);
    }

    public static final boolean mediaMarker(int marker) {
        return marker >= 448 && marker <= 495 || marker == 445 || marker == 447;
    }

    public static final boolean psMarker(int marker) {
        return marker >= 445 && marker <= 495;
    }

    public static boolean videoMarker(int marker) {
        return marker >= 480 && marker <= 495;
    }

    public static final boolean videoStream(int streamId) {
        return streamId >= MPSUtils.$(480) && streamId <= MPSUtils.$(495);
    }

    public static boolean audioStream(int streamId) {
        return streamId >= MPSUtils.$(448) && streamId <= MPSUtils.$(479) || streamId == MPSUtils.$(445) || streamId == MPSUtils.$(447);
    }

    static int $(int marker) {
        return marker & 0xFF;
    }

    public static PESPacket readPESHeader(ByteBuffer iss, long pos) {
        int streamId = iss.getInt() & 0xFF;
        int len = iss.getShort() & 0xFFFF;
        if (streamId != 191) {
            int b0 = iss.get() & 0xFF;
            if ((b0 & 0xC0) == 128) {
                return MPSUtils.mpeg2Pes(b0, len, streamId, iss, pos);
            }
            return MPSUtils.mpeg1Pes(b0, len, streamId, iss, pos);
        }
        return new PESPacket(null, -1L, streamId, len, pos, -1L);
    }

    public static PESPacket mpeg1Pes(int b0, int len, int streamId, ByteBuffer is, long pos) {
        int c = b0;
        while (c == 255) {
            c = is.get() & 0xFF;
        }
        if ((c & 0xC0) == 64) {
            is.get();
            c = is.get() & 0xFF;
        }
        long pts = -1L;
        long dts = -1L;
        if ((c & 0xF0) == 32) {
            pts = MPSUtils._readTs(is, c);
        } else if ((c & 0xF0) == 48) {
            pts = MPSUtils._readTs(is, c);
            dts = MPSUtils.readTs(is);
        } else if (c != 15) {
            throw new RuntimeException("Invalid data");
        }
        return new PESPacket(null, pts, streamId, len, pos, dts);
    }

    public static long _readTs(ByteBuffer is, int c) {
        return ((long)c & 0xEL) << 29 | (long)((is.get() & 0xFF) << 22) | (long)((is.get() & 0xFF) >> 1 << 15) | (long)((is.get() & 0xFF) << 7) | (long)((is.get() & 0xFF) >> 1);
    }

    public static PESPacket mpeg2Pes(int b0, int len, int streamId, ByteBuffer is, long pos) {
        int flags1 = b0;
        int flags2 = is.get() & 0xFF;
        int header_len = is.get() & 0xFF;
        long pts = -1L;
        long dts = -1L;
        if ((flags2 & 0xC0) == 128) {
            pts = MPSUtils.readTs(is);
            NIOUtils.skip(is, header_len - 5);
        } else if ((flags2 & 0xC0) == 192) {
            pts = MPSUtils.readTs(is);
            dts = MPSUtils.readTs(is);
            NIOUtils.skip(is, header_len - 10);
        } else {
            NIOUtils.skip(is, header_len);
        }
        return new PESPacket(null, pts, streamId, len, pos, dts);
    }

    public static long readTs(ByteBuffer is) {
        return ((long)is.get() & 0xEL) << 29 | (long)((is.get() & 0xFF) << 22) | (long)((is.get() & 0xFF) >> 1 << 15) | (long)((is.get() & 0xFF) << 7) | (long)((is.get() & 0xFF) >> 1);
    }

    public static void writeTs(ByteBuffer is, long ts) {
        is.put((byte)(ts >> 29 << 1));
        is.put((byte)(ts >> 22));
        is.put((byte)(ts >> 15 << 1));
        is.put((byte)(ts >> 7));
        is.put((byte)(ts >> 1));
    }

    public static List<MPEGMediaDescriptor> parseDescriptors(ByteBuffer bb) {
        ArrayList<MPEGMediaDescriptor> result = new ArrayList<MPEGMediaDescriptor>();
        while (bb.remaining() >= 2) {
            ByteBuffer dup = bb.duplicate();
            int tag = dup.get() & 0xFF;
            int len = dup.get() & 0xFF;
            ByteBuffer descriptorBuffer = NIOUtils.read(bb, len + 2);
            if (dMapping[tag] == null) continue;
            try {
                MPEGMediaDescriptor descriptor = dMapping[tag].newInstance();
                descriptor.parse(descriptorBuffer);
                result.add(descriptor);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    static {
        MPSUtils.dMapping[2] = VideoStreamDescriptor.class;
        MPSUtils.dMapping[3] = AudioStreamDescriptor.class;
        MPSUtils.dMapping[6] = DataStreamAlignmentDescriptor.class;
        MPSUtils.dMapping[5] = RegistrationDescriptor.class;
        MPSUtils.dMapping[10] = ISO639LanguageDescriptor.class;
        MPSUtils.dMapping[27] = Mpeg4VideoDescriptor.class;
        MPSUtils.dMapping[28] = Mpeg4AudioDescriptor.class;
        MPSUtils.dMapping[40] = AVCVideoDescriptor.class;
        MPSUtils.dMapping[43] = AACAudioDescriptor.class;
    }

    public static class RegistrationDescriptor
    extends MPEGMediaDescriptor {
        private int formatIdentifier;
        private IntArrayList additionalFormatIdentifiers = IntArrayList.createIntArrayList();

        @Override
        public void parse(ByteBuffer buf) {
            super.parse(buf);
            this.formatIdentifier = buf.getInt();
            while (buf.hasRemaining()) {
                this.additionalFormatIdentifiers.add(buf.get() & 0xFF);
            }
        }

        public int getFormatIdentifier() {
            return this.formatIdentifier;
        }

        public IntArrayList getAdditionalFormatIdentifiers() {
            return this.additionalFormatIdentifiers;
        }
    }

    public static class DataStreamAlignmentDescriptor
    extends MPEGMediaDescriptor {
        private int alignmentType;

        @Override
        public void parse(ByteBuffer buf) {
            super.parse(buf);
            this.alignmentType = buf.get() & 0xFF;
        }

        public int getAlignmentType() {
            return this.alignmentType;
        }
    }

    public static class AACAudioDescriptor
    extends MPEGMediaDescriptor {
        private int profile;
        private int channel;
        private int flags;

        @Override
        public void parse(ByteBuffer buf) {
            super.parse(buf);
            this.profile = buf.get() & 0xFF;
            this.channel = buf.get() & 0xFF;
            this.flags = buf.get() & 0xFF;
        }

        public int getProfile() {
            return this.profile;
        }

        public int getChannel() {
            return this.channel;
        }

        public int getFlags() {
            return this.flags;
        }
    }

    public static class AVCVideoDescriptor
    extends MPEGMediaDescriptor {
        private int profileIdc;
        private int flags;
        private int level;

        @Override
        public void parse(ByteBuffer buf) {
            super.parse(buf);
            this.profileIdc = buf.get() & 0xFF;
            this.flags = buf.get() & 0xFF;
            this.level = buf.get() & 0xFF;
        }

        public int getProfileIdc() {
            return this.profileIdc;
        }

        public int getFlags() {
            return this.flags;
        }

        public int getLevel() {
            return this.level;
        }
    }

    public static class Mpeg4AudioDescriptor
    extends MPEGMediaDescriptor {
        private int profileLevel;

        @Override
        public void parse(ByteBuffer buf) {
            super.parse(buf);
            this.profileLevel = buf.get() & 0xFF;
        }

        public int getProfileLevel() {
            return this.profileLevel;
        }
    }

    public static class Mpeg4VideoDescriptor
    extends MPEGMediaDescriptor {
        private int profileLevel;

        @Override
        public void parse(ByteBuffer buf) {
            super.parse(buf);
            this.profileLevel = buf.get() & 0xFF;
        }
    }

    public static class ISO639LanguageDescriptor
    extends MPEGMediaDescriptor {
        private IntArrayList languageCodes = IntArrayList.createIntArrayList();

        @Override
        public void parse(ByteBuffer buf) {
            super.parse(buf);
            while (buf.remaining() >= 4) {
                this.languageCodes.add(buf.getInt());
            }
        }

        public IntArrayList getLanguageCodes() {
            return this.languageCodes;
        }
    }

    public static class AudioStreamDescriptor
    extends MPEGMediaDescriptor {
        private int variableRateAudioIndicator;
        private int freeFormatFlag;
        private int id;
        private int layer;

        @Override
        public void parse(ByteBuffer buf) {
            super.parse(buf);
            int b0 = buf.get() & 0xFF;
            this.freeFormatFlag = b0 >> 7 & 1;
            this.id = b0 >> 6 & 1;
            this.layer = b0 >> 5 & 3;
            this.variableRateAudioIndicator = b0 >> 3 & 1;
        }

        public int getVariableRateAudioIndicator() {
            return this.variableRateAudioIndicator;
        }

        public int getFreeFormatFlag() {
            return this.freeFormatFlag;
        }

        public int getId() {
            return this.id;
        }

        public int getLayer() {
            return this.layer;
        }
    }

    public static class VideoStreamDescriptor
    extends MPEGMediaDescriptor {
        private int multipleFrameRate;
        private int frameRateCode;
        private boolean mpeg1Only;
        private int constrainedParameter;
        private int stillPicture;
        private int profileAndLevel;
        private int chromaFormat;
        private int frameRateExtension;
        Rational[] frameRates = new Rational[]{null, new Rational(24000, 1001), new Rational(24, 1), new Rational(25, 1), new Rational(30000, 1001), new Rational(30, 1), new Rational(50, 1), new Rational(60000, 1001), new Rational(60, 1), null, null, null, null, null, null, null};

        @Override
        public void parse(ByteBuffer buf) {
            super.parse(buf);
            int b0 = buf.get() & 0xFF;
            this.multipleFrameRate = b0 >> 7 & 1;
            this.frameRateCode = b0 >> 3 & 0xF;
            this.mpeg1Only = (b0 >> 2 & 1) == 0;
            this.constrainedParameter = b0 >> 1 & 1;
            this.stillPicture = b0 & 1;
            if (!this.mpeg1Only) {
                this.profileAndLevel = buf.get() & 0xFF;
                int b1 = buf.get() & 0xFF;
                this.chromaFormat = b1 >> 6;
                this.frameRateExtension = b1 >> 5 & 1;
            }
        }

        public Rational getFrameRate() {
            return this.frameRates[this.frameRateCode];
        }

        public int getMultipleFrameRate() {
            return this.multipleFrameRate;
        }

        public int getFrameRateCode() {
            return this.frameRateCode;
        }

        public boolean isMpeg1Only() {
            return this.mpeg1Only;
        }

        public int getConstrainedParameter() {
            return this.constrainedParameter;
        }

        public int getStillPicture() {
            return this.stillPicture;
        }

        public int getProfileAndLevel() {
            return this.profileAndLevel;
        }

        public int getChromaFormat() {
            return this.chromaFormat;
        }

        public int getFrameRateExtension() {
            return this.frameRateExtension;
        }
    }

    public static class MPEGMediaDescriptor {
        private int tag;
        private int len;

        public void parse(ByteBuffer buf) {
            this.tag = buf.get() & 0xFF;
            this.len = buf.get() & 0xFF;
        }

        public int getTag() {
            return this.tag;
        }

        public int getLen() {
            return this.len;
        }
    }

    public static abstract class PESReader {
        private int marker = -1;
        private int lenFieldLeft;
        private int pesLen;
        private long pesFileStart = -1L;
        private int stream;
        private boolean _pes;
        private int pesLeft;
        private ByteBuffer pesBuffer = ByteBuffer.allocate(0x200000);

        protected abstract void pes(ByteBuffer var1, long var2, int var4, int var5);

        public void analyseBuffer(ByteBuffer buf, long pos) {
            int init = buf.position();
            while (buf.hasRemaining()) {
                long filePos;
                if (this.pesLeft > 0) {
                    int toRead = Math.min(buf.remaining(), this.pesLeft);
                    this.pesBuffer.put(NIOUtils.read(buf, toRead));
                    this.pesLeft -= toRead;
                    if (this.pesLeft != 0) continue;
                    filePos = pos + (long)buf.position() - (long)init;
                    this.pes1(this.pesBuffer, this.pesFileStart, (int)(filePos - this.pesFileStart), this.stream);
                    this.pesFileStart = -1L;
                    this._pes = false;
                    this.stream = -1;
                    continue;
                }
                int bt = buf.get() & 0xFF;
                if (this._pes) {
                    this.pesBuffer.put((byte)(this.marker >>> 24));
                }
                this.marker = this.marker << 8 | bt;
                if (this.marker >= 443 && this.marker <= 495) {
                    filePos = pos + (long)buf.position() - (long)init - 4L;
                    if (this._pes) {
                        this.pes1(this.pesBuffer, this.pesFileStart, (int)(filePos - this.pesFileStart), this.stream);
                    }
                    this.pesFileStart = filePos;
                    this._pes = true;
                    this.stream = this.marker & 0xFF;
                    this.lenFieldLeft = 2;
                    this.pesLen = 0;
                    continue;
                }
                if (this.marker >= 441 && this.marker <= 511) {
                    if (this._pes) {
                        filePos = pos + (long)buf.position() - (long)init - 4L;
                        this.pes1(this.pesBuffer, this.pesFileStart, (int)(filePos - this.pesFileStart), this.stream);
                    }
                    this.pesFileStart = -1L;
                    this._pes = false;
                    this.stream = -1;
                    continue;
                }
                if (this.lenFieldLeft <= 0) continue;
                this.pesLen = this.pesLen << 8 | bt;
                --this.lenFieldLeft;
                if (this.lenFieldLeft != 0) continue;
                this.pesLeft = this.pesLen;
                if (this.pesLen == 0) continue;
                this.flushMarker();
                this.marker = -1;
            }
        }

        private void flushMarker() {
            this.pesBuffer.put((byte)(this.marker >>> 24));
            this.pesBuffer.put((byte)(this.marker >>> 16 & 0xFF));
            this.pesBuffer.put((byte)(this.marker >>> 8 & 0xFF));
            this.pesBuffer.put((byte)(this.marker & 0xFF));
        }

        private void pes1(ByteBuffer pesBuffer, long start, int pesLen, int stream) {
            pesBuffer.flip();
            this.pes(pesBuffer, start, pesLen, stream);
            pesBuffer.clear();
        }

        public void finishRead() {
            if (this.pesLeft <= 4) {
                this.flushMarker();
                this.pes1(this.pesBuffer, this.pesFileStart, this.pesBuffer.position(), this.stream);
            }
        }
    }
}
