/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes;

import com.github.alexthe666.citadel.repack.jcodec.common.ByteArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.io.SeekableByteChannel;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlBin;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.boxes.EbmlSint;
import com.github.alexthe666.citadel.repack.jcodec.containers.mkv.util.EbmlUtil;
import com.github.alexthe666.citadel.repack.jcodec.platform.Platform;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class MkvBlock
extends EbmlBin {
    private static final String XIPH = "Xiph";
    private static final String EBML = "EBML";
    private static final String FIXED = "Fixed";
    private static final int MAX_BLOCK_HEADER_SIZE = 512;
    public int[] frameOffsets;
    public int[] frameSizes;
    public long trackNumber;
    public int timecode;
    public long absoluteTimecode;
    public boolean _keyFrame;
    public int headerSize;
    public String lacing;
    public boolean discardable;
    public boolean lacingPresent;
    public ByteBuffer[] frames;
    public static final byte[] BLOCK_ID = new byte[]{-95};
    public static final byte[] SIMPLEBLOCK_ID = new byte[]{-93};

    public static MkvBlock copy(MkvBlock old) {
        MkvBlock be = new MkvBlock(old.id);
        be.trackNumber = old.trackNumber;
        be.timecode = old.timecode;
        be.absoluteTimecode = old.absoluteTimecode;
        be._keyFrame = old._keyFrame;
        be.headerSize = old.headerSize;
        be.lacing = old.lacing;
        be.discardable = old.discardable;
        be.lacingPresent = old.lacingPresent;
        be.frameOffsets = new int[old.frameOffsets.length];
        be.frameSizes = new int[old.frameSizes.length];
        be.dataOffset = old.dataOffset;
        be.offset = old.offset;
        be.type = old.type;
        System.arraycopy(old.frameOffsets, 0, be.frameOffsets, 0, be.frameOffsets.length);
        System.arraycopy(old.frameSizes, 0, be.frameSizes, 0, be.frameSizes.length);
        return be;
    }

    public static MkvBlock keyFrame(long trackNumber, int timecode, ByteBuffer frame) {
        MkvBlock be = new MkvBlock(SIMPLEBLOCK_ID);
        be.frames = new ByteBuffer[]{frame};
        be.frameSizes = new int[]{frame.limit()};
        be._keyFrame = true;
        be.trackNumber = trackNumber;
        be.timecode = timecode;
        return be;
    }

    public MkvBlock(byte[] type) {
        super(type);
        if (!Platform.arrayEqualsByte(SIMPLEBLOCK_ID, type) && !Platform.arrayEqualsByte(BLOCK_ID, type)) {
            throw new IllegalArgumentException("Block initiated with invalid id: " + EbmlUtil.toHexString(type));
        }
    }

    @Override
    public void readChannel(SeekableByteChannel is) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(100);
        is.read(bb);
        bb.flip();
        this.read(bb);
        is.setPosition(this.dataOffset + (long)this.dataLen);
    }

    @Override
    public void read(ByteBuffer source) {
        ByteBuffer bb = source.slice();
        this.trackNumber = MkvBlock.ebmlDecode(bb);
        int tcPart1 = bb.get() & 0xFF;
        int tcPart2 = bb.get() & 0xFF;
        this.timecode = (short)((short)tcPart1 << 8 | (short)tcPart2);
        int flags = bb.get() & 0xFF;
        this._keyFrame = (flags & 0x80) > 0;
        this.discardable = (flags & 1) > 0;
        int laceFlags = flags & 6;
        boolean bl = this.lacingPresent = laceFlags != 0;
        if (this.lacingPresent) {
            int lacesCount = bb.get() & 0xFF;
            this.frameSizes = new int[lacesCount + 1];
            if (laceFlags == 2) {
                this.lacing = XIPH;
                this.headerSize = MkvBlock.readXiphLaceSizes(bb, this.frameSizes, this.dataLen, bb.position());
            } else if (laceFlags == 6) {
                this.lacing = EBML;
                this.headerSize = MkvBlock.readEBMLLaceSizes(bb, this.frameSizes, this.dataLen, bb.position());
            } else if (laceFlags == 4) {
                this.lacing = FIXED;
                this.headerSize = bb.position();
                int aLaceSize = (this.dataLen - this.headerSize) / (lacesCount + 1);
                Arrays.fill(this.frameSizes, aLaceSize);
            } else {
                throw new RuntimeException("Unsupported lacing type flag.");
            }
            this.turnSizesToFrameOffsets(this.frameSizes);
        } else {
            this.lacing = "";
            int frameOffset = bb.position();
            this.frameOffsets = new int[1];
            this.frameOffsets[0] = frameOffset;
            this.headerSize = bb.position();
            this.frameSizes = new int[1];
            this.frameSizes[0] = this.dataLen - this.headerSize;
        }
    }

    private void turnSizesToFrameOffsets(int[] sizes) {
        this.frameOffsets = new int[sizes.length];
        this.frameOffsets[0] = this.headerSize;
        for (int i = 1; i < sizes.length; ++i) {
            this.frameOffsets[i] = this.frameOffsets[i - 1] + sizes[i - 1];
        }
    }

    public static int readXiphLaceSizes(ByteBuffer bb, int[] sizes, int size, int preLacingHeaderSize) {
        int startPos = bb.position();
        int lastIndex = sizes.length - 1;
        sizes[lastIndex] = size;
        for (int l = 0; l < lastIndex; ++l) {
            int laceSize = 255;
            while (laceSize == 255) {
                laceSize = bb.get() & 0xFF;
                int n = l;
                sizes[n] = sizes[n] + laceSize;
            }
            int n = lastIndex;
            sizes[n] = sizes[n] - sizes[l];
        }
        int headerSize = bb.position() - startPos + preLacingHeaderSize;
        int n = lastIndex;
        sizes[n] = sizes[n] - headerSize;
        return headerSize;
    }

    public static int readEBMLLaceSizes(ByteBuffer source, int[] sizes, int size, int preLacingHeaderSize) {
        int lastIndex = sizes.length - 1;
        sizes[lastIndex] = size;
        int startPos = source.position();
        sizes[0] = (int)MkvBlock.ebmlDecode(source);
        int n = lastIndex;
        sizes[n] = sizes[n] - sizes[0];
        int laceSize = sizes[0];
        long laceSizeDiff = 0L;
        for (int l = 1; l < lastIndex; ++l) {
            laceSizeDiff = MkvBlock.ebmlDecodeSigned(source);
            sizes[l] = laceSize = (int)((long)laceSize + laceSizeDiff);
            int n2 = lastIndex;
            sizes[n2] = sizes[n2] - sizes[l];
        }
        int headerSize = source.position() - startPos + preLacingHeaderSize;
        int n3 = lastIndex;
        sizes[n3] = sizes[n3] - headerSize;
        return headerSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{dataOffset: ").append(this.dataOffset);
        sb.append(", trackNumber: ").append(this.trackNumber);
        sb.append(", timecode: ").append(this.timecode);
        sb.append(", keyFrame: ").append(this._keyFrame);
        sb.append(", headerSize: ").append(this.headerSize);
        sb.append(", lacing: ").append(this.lacing);
        for (int i = 0; i < this.frameSizes.length; ++i) {
            sb.append(", frame[").append(i).append("]  offset ").append(this.frameOffsets[i]).append(" size ").append(this.frameSizes[i]);
        }
        sb.append(" }");
        return sb.toString();
    }

    public ByteBuffer[] getFrames(ByteBuffer source) throws IOException {
        ByteBuffer[] frames = new ByteBuffer[this.frameSizes.length];
        for (int i = 0; i < this.frameSizes.length; ++i) {
            if (this.frameOffsets[i] > source.limit()) {
                System.err.println("frame offset: " + this.frameOffsets[i] + " limit: " + source.limit());
            }
            source.position(this.frameOffsets[i]);
            ByteBuffer bb = source.slice();
            bb.limit(this.frameSizes[i]);
            frames[i] = bb;
        }
        return frames;
    }

    public void readFrames(ByteBuffer source) throws IOException {
        this.frames = this.getFrames(source);
    }

    @Override
    public ByteBuffer getData() {
        int dataSize = this.getDataSize();
        ByteBuffer bb = ByteBuffer.allocate(dataSize + EbmlUtil.ebmlLength(dataSize) + this.id.length);
        bb.put(this.id);
        bb.put(EbmlUtil.ebmlEncode(dataSize));
        bb.put(EbmlUtil.ebmlEncode(this.trackNumber));
        bb.put((byte)(this.timecode >>> 8 & 0xFF));
        bb.put((byte)(this.timecode & 0xFF));
        int flags = 0;
        if (XIPH.equals(this.lacing)) {
            flags = 2;
        } else if (EBML.equals(this.lacing)) {
            flags = 6;
        } else if (FIXED.equals(this.lacing)) {
            flags = 4;
        }
        if (this.discardable) {
            flags = (byte)(flags | 1);
        }
        if (this._keyFrame) {
            flags = (byte)(flags | 0x80);
        }
        bb.put((byte)flags);
        if ((flags & 6) != 0) {
            bb.put((byte)(this.frames.length - 1 & 0xFF));
            bb.put(this.muxLacingInfo());
        }
        for (int i = 0; i < this.frames.length; ++i) {
            ByteBuffer frame = this.frames[i];
            bb.put(frame);
        }
        bb.flip();
        return bb;
    }

    public void seekAndReadContent(FileChannel source) throws IOException {
        this.data = ByteBuffer.allocate(this.dataLen);
        source.position(this.dataOffset);
        source.read(this.data);
        this.data.flip();
    }

    @Override
    public long size() {
        long size = this.getDataSize();
        size += (long)EbmlUtil.ebmlLength(size);
        return size += (long)this.id.length;
    }

    public int getDataSize() {
        int size = 0;
        int[] nArray = this.frameSizes;
        int n = nArray.length;
        for (int i = 0; i < n; ++i) {
            long fsize = nArray[i];
            size = (int)((long)size + fsize);
        }
        if (this.lacingPresent) {
            size += this.muxLacingInfo().length;
            ++size;
        }
        size += 3;
        return size += EbmlUtil.ebmlLength(this.trackNumber);
    }

    private byte[] muxLacingInfo() {
        if (EBML.equals(this.lacing)) {
            return MkvBlock.muxEbmlLacing(this.frameSizes);
        }
        if (XIPH.equals(this.lacing)) {
            return MkvBlock.muxXiphLacing(this.frameSizes);
        }
        if (FIXED.equals(this.lacing)) {
            return new byte[0];
        }
        return null;
    }

    public static long ebmlDecode(ByteBuffer bb) {
        byte firstByte = bb.get();
        int length = EbmlUtil.computeLength(firstByte);
        if (length == 0) {
            throw new RuntimeException("Invalid ebml integer size.");
        }
        long value = firstByte & 255 >>> length;
        --length;
        while (length > 0) {
            value = value << 8 | (long)(bb.get() & 0xFF);
            --length;
        }
        return value;
    }

    public static long ebmlDecodeSigned(ByteBuffer source) {
        byte firstByte = source.get();
        int size = EbmlUtil.computeLength(firstByte);
        if (size == 0) {
            throw new RuntimeException("Invalid ebml integer size.");
        }
        long value = firstByte & 255 >>> size;
        for (int remaining = size - 1; remaining > 0; --remaining) {
            value = value << 8 | (long)(source.get() & 0xFF);
        }
        return value - EbmlSint.signedComplement[size];
    }

    public static long[] calcEbmlLacingDiffs(int[] laceSizes) {
        int lacesCount = laceSizes.length - 1;
        long[] out = new long[lacesCount];
        out[0] = laceSizes[0];
        for (int i = 1; i < lacesCount; ++i) {
            out[i] = laceSizes[i] - laceSizes[i - 1];
        }
        return out;
    }

    public static byte[] muxEbmlLacing(int[] laceSizes) {
        ByteArrayList bytes = ByteArrayList.createByteArrayList();
        long[] laceSizeDiffs = MkvBlock.calcEbmlLacingDiffs(laceSizes);
        bytes.addAll(EbmlUtil.ebmlEncode(laceSizeDiffs[0]));
        for (int i = 1; i < laceSizeDiffs.length; ++i) {
            bytes.addAll(EbmlSint.convertToBytes(laceSizeDiffs[i]));
        }
        return bytes.toArray();
    }

    public static byte[] muxXiphLacing(int[] laceSizes) {
        ByteArrayList bytes = ByteArrayList.createByteArrayList();
        for (int i = 0; i < laceSizes.length - 1; ++i) {
            long laceSize;
            for (laceSize = (long)laceSizes[i]; laceSize >= 255L; laceSize -= 255L) {
                bytes.add((byte)-1);
            }
            bytes.add((byte)laceSize);
        }
        return bytes.toArray();
    }
}
