/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.prores;

import com.github.alexthe666.citadel.repack.jcodec.codecs.prores.ProresConsts;
import com.github.alexthe666.citadel.repack.jcodec.codecs.prores.ProresDecoder;
import com.github.alexthe666.citadel.repack.jcodec.codecs.prores.ProresEncoder;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitReader;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitWriter;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ProresFix {
    static final void readDCCoeffs(BitReader bits, int[] out, int blocksPerSlice) {
        out[0] = ProresDecoder.readCodeword(bits, ProresConsts.firstDCCodebook);
        if (out[0] < 0) {
            throw new RuntimeException("First DC coeff damaged");
        }
        int code = 5;
        int idx = 64;
        int i = 1;
        while (i < blocksPerSlice) {
            if ((code = ProresDecoder.readCodeword(bits, ProresConsts.dcCodebooks[Math.min(code, 6)])) < 0) {
                throw new RuntimeException("DC coeff damaged");
            }
            out[idx] = code;
            ++i;
            idx += 64;
        }
    }

    static final void readACCoeffs(BitReader bits, int[] out, int blocksPerSlice, int[] scan) {
        int run = 4;
        int level = 2;
        int blockMask = blocksPerSlice - 1;
        int log2BlocksPerSlice = MathUtil.log2(blocksPerSlice);
        int maxCoeffs = 64 << log2BlocksPerSlice;
        int pos = blockMask;
        while (bits.remaining() > 32 || bits.checkNBit(24) != 0) {
            if ((run = ProresDecoder.readCodeword(bits, ProresConsts.runCodebooks[Math.min(run, 15)])) < 0 || run >= maxCoeffs - pos - 1) {
                throw new RuntimeException("Run codeword damaged");
            }
            pos += run + 1;
            if ((level = ProresDecoder.readCodeword(bits, ProresConsts.levCodebooks[Math.min(level, 9)]) + 1) < 0 || level > 65535) {
                throw new RuntimeException("Level codeword damaged");
            }
            int sign = -bits.read1Bit();
            int ind = pos >> log2BlocksPerSlice;
            out[((pos & blockMask) << 6) + scan[ind]] = MathUtil.toSigned(level, sign);
        }
    }

    static final void writeDCCoeffs(BitWriter bits, int[] _in, int blocksPerSlice) {
        ProresEncoder.writeCodeword(bits, ProresConsts.firstDCCodebook, _in[0]);
        int code = 5;
        int idx = 64;
        int i = 1;
        while (i < blocksPerSlice) {
            ProresEncoder.writeCodeword(bits, ProresConsts.dcCodebooks[Math.min(code, 6)], _in[idx]);
            code = _in[idx];
            ++i;
            idx += 64;
        }
    }

    static final void writeACCoeffs(BitWriter bits, int[] _in, int blocksPerSlice, int[] scan) {
        int prevRun = 4;
        int prevLevel = 2;
        int run = 0;
        for (int i = 1; i < 64; ++i) {
            int indp = scan[i];
            for (int j = 0; j < blocksPerSlice; ++j) {
                int val = _in[(j << 6) + indp];
                if (val == 0) {
                    ++run;
                    continue;
                }
                ProresEncoder.writeCodeword(bits, ProresConsts.runCodebooks[Math.min(prevRun, 15)], run);
                prevRun = run;
                run = 0;
                int level = ProresEncoder.getLevel(val);
                ProresEncoder.writeCodeword(bits, ProresConsts.levCodebooks[Math.min(prevLevel, 9)], level - 1);
                prevLevel = level;
                bits.write1Bit(MathUtil.sign(val));
            }
        }
    }

    static void copyCoeff(BitReader ib, BitWriter ob, int blocksPerSlice, int[] scan) {
        int[] out = new int[blocksPerSlice << 6];
        try {
            ProresFix.readDCCoeffs(ib, out, blocksPerSlice);
            ProresFix.readACCoeffs(ib, out, blocksPerSlice, scan);
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
        ProresFix.writeDCCoeffs(ob, out, blocksPerSlice);
        ProresFix.writeACCoeffs(ob, out, blocksPerSlice, scan);
        ob.flush();
    }

    public static ByteBuffer transcode(ByteBuffer inBuf, ByteBuffer _outBuf) {
        ByteBuffer outBuf = _outBuf.slice();
        ByteBuffer fork = outBuf.duplicate();
        ProresConsts.FrameHeader fh = ProresDecoder.readFrameHeader(inBuf);
        ProresEncoder.writeFrameHeader(outBuf, fh);
        if (fh.frameType == 0) {
            ProresFix.transcodePicture(inBuf, outBuf, fh);
        } else {
            ProresFix.transcodePicture(inBuf, outBuf, fh);
            ProresFix.transcodePicture(inBuf, outBuf, fh);
        }
        ProresEncoder.writeFrameHeader(fork, fh);
        outBuf.flip();
        return outBuf;
    }

    private static void transcodePicture(ByteBuffer inBuf, ByteBuffer outBuf, ProresConsts.FrameHeader fh) {
        ProresConsts.PictureHeader ph = ProresDecoder.readPictureHeader(inBuf);
        ProresEncoder.writePictureHeader(ph.log2SliceMbWidth, ph.sliceSizes.length, outBuf);
        ByteBuffer fork = outBuf.duplicate();
        outBuf.position(outBuf.position() + (ph.sliceSizes.length << 1));
        int mbWidth = fh.width + 15 >> 4;
        int sliceMbCount = 1 << ph.log2SliceMbWidth;
        int mbX = 0;
        for (int i = 0; i < ph.sliceSizes.length; ++i) {
            while (mbWidth - mbX < sliceMbCount) {
                sliceMbCount >>= 1;
            }
            int savedPoint = outBuf.position();
            ProresFix.transcodeSlice(inBuf, outBuf, sliceMbCount, ph.sliceSizes[i], fh);
            fork.putShort((short)(outBuf.position() - savedPoint));
            if ((mbX += sliceMbCount) != mbWidth) continue;
            sliceMbCount = 1 << ph.log2SliceMbWidth;
            mbX = 0;
        }
    }

    private static void transcodeSlice(ByteBuffer inBuf, ByteBuffer outBuf, int sliceMbCount, short sliceSize, ProresConsts.FrameHeader fh) {
        int hdrSize = (inBuf.get() & 0xFF) >> 3;
        int qScaleOrig = inBuf.get() & 0xFF;
        short yDataSize = inBuf.getShort();
        short uDataSize = inBuf.getShort();
        int vDataSize = sliceSize - uDataSize - yDataSize - hdrSize;
        outBuf.put((byte)48);
        outBuf.put((byte)qScaleOrig);
        ByteBuffer beforeSizes = outBuf.duplicate();
        outBuf.putInt(0);
        int beforeY = outBuf.position();
        ProresFix.copyCoeff(ProresDecoder.bitstream(inBuf, yDataSize), new BitWriter(outBuf), sliceMbCount << 2, fh.scan);
        int beforeCb = outBuf.position();
        ProresFix.copyCoeff(ProresDecoder.bitstream(inBuf, uDataSize), new BitWriter(outBuf), sliceMbCount << 1, fh.scan);
        int beforeCr = outBuf.position();
        ProresFix.copyCoeff(ProresDecoder.bitstream(inBuf, vDataSize), new BitWriter(outBuf), sliceMbCount << 1, fh.scan);
        beforeSizes.putShort((short)(beforeCb - beforeY));
        beforeSizes.putShort((short)(beforeCr - beforeCb));
    }

    public static List<String> check(ByteBuffer data) {
        ArrayList<String> messages = new ArrayList<String>();
        int frameSize = data.getInt();
        if (!"icpf".equals(ProresDecoder.readSig(data))) {
            messages.add("[ERROR] Missing ProRes signature (icpf).");
            return messages;
        }
        short headerSize = data.getShort();
        if (headerSize > 148) {
            messages.add("[ERROR] Wrong ProRes frame header.");
            return messages;
        }
        short version = data.getShort();
        int res1 = data.getInt();
        short width = data.getShort();
        short height = data.getShort();
        if (width < 0 || width > 10000 || height < 0 || height > 10000) {
            messages.add("[ERROR] Wrong ProRes frame header, invalid image size [" + width + "x" + height + "].");
            return messages;
        }
        byte flags1 = data.get();
        data.position(data.position() + headerSize - 13);
        if ((flags1 >> 2 & 3) == 0) {
            ProresFix.checkPicture(data, width, height, messages);
        } else {
            ProresFix.checkPicture(data, width, height / 2, messages);
            ProresFix.checkPicture(data, width, height / 2, messages);
        }
        return messages;
    }

    private static void checkPicture(ByteBuffer data, int width, int height, List<String> messages) {
        ProresConsts.PictureHeader ph = ProresDecoder.readPictureHeader(data);
        int mbWidth = width + 15 >> 4;
        int mbHeight = height + 15 >> 4;
        int sliceMbCount = 1 << ph.log2SliceMbWidth;
        int mbX = 0;
        int mbY = 0;
        for (int i = 0; i < ph.sliceSizes.length; ++i) {
            while (mbWidth - mbX < sliceMbCount) {
                sliceMbCount >>= 1;
            }
            try {
                ProresFix.checkSlice(NIOUtils.read(data, ph.sliceSizes[i]), sliceMbCount);
            }
            catch (Exception e) {
                messages.add("[ERROR] Slice data corrupt: mbX = " + mbX + ", mbY = " + mbY + ". " + e.getMessage());
            }
            if ((mbX += sliceMbCount) != mbWidth) continue;
            sliceMbCount = 1 << ph.log2SliceMbWidth;
            mbX = 0;
            ++mbY;
        }
    }

    private static void checkSlice(ByteBuffer sliceData, int sliceMbCount) {
        int sliceSize = sliceData.remaining();
        int hdrSize = (sliceData.get() & 0xFF) >> 3;
        int qScaleOrig = sliceData.get() & 0xFF;
        short yDataSize = sliceData.getShort();
        short uDataSize = sliceData.getShort();
        int vDataSize = sliceSize - uDataSize - yDataSize - hdrSize;
        ProresFix.checkCoeff(ProresDecoder.bitstream(sliceData, yDataSize), sliceMbCount << 2);
        ProresFix.checkCoeff(ProresDecoder.bitstream(sliceData, uDataSize), sliceMbCount << 1);
        ProresFix.checkCoeff(ProresDecoder.bitstream(sliceData, vDataSize), sliceMbCount << 1);
    }

    private static void checkCoeff(BitReader ib, int blocksPerSlice) {
        int[] scan = new int[64];
        int[] out = new int[blocksPerSlice << 6];
        ProresFix.readDCCoeffs(ib, out, blocksPerSlice);
        ProresFix.readACCoeffs(ib, out, blocksPerSlice, scan);
    }
}
