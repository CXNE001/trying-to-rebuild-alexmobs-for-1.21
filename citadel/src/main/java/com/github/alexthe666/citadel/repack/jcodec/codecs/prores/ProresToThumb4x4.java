/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.prores;

import com.github.alexthe666.citadel.repack.jcodec.codecs.prores.ProresConsts;
import com.github.alexthe666.citadel.repack.jcodec.codecs.prores.ProresDecoder;
import com.github.alexthe666.citadel.repack.jcodec.common.dct.IDCT4x4;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitReader;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Rect;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;
import java.nio.ByteBuffer;

public class ProresToThumb4x4
extends ProresDecoder {
    public static int[] progressive_scan_4x4 = new int[]{0, 1, 4, 5, 2, 3, 6, 7, 8, 9, 12, 13, 11, 12, 14, 15};
    public static int[] interlaced_scan_4x4 = new int[]{0, 4, 1, 5, 8, 12, 9, 13, 2, 6, 3, 7, 10, 14, 11, 15};
    private static final int[] srcIncLuma = new int[]{4, 4, 4, 20, 4, 4, 4, 20};

    @Override
    protected void decodeOnePlane(BitReader bits, int blocksPerSlice, int[] out, int[] qMat, int[] scan, int mbX, int mbY, int plane) {
        ProresToThumb4x4.readDCCoeffs(bits, qMat, out, blocksPerSlice, 16);
        ProresToThumb4x4.readACCoeffs(bits, qMat, out, blocksPerSlice, scan, 16, 4);
        for (int i = 0; i < blocksPerSlice; ++i) {
            IDCT4x4.idct(out, i << 4);
        }
    }

    @Override
    public Picture decodeFrameHiBD(ByteBuffer data, byte[][] target, byte[][] lowBits) {
        ProresConsts.FrameHeader fh = ProresToThumb4x4.readFrameHeader(data);
        int codedWidth = (fh.width + 15 & 0xFFFFFFF0) >> 1;
        int codedHeight = (fh.height + 15 & 0xFFFFFFF0) >> 1;
        int lumaSize = codedWidth * codedHeight;
        int chromaSize = lumaSize >> 1;
        if (target == null || target[0].length < lumaSize || target[1].length < chromaSize || target[2].length < chromaSize) {
            throw new RuntimeException("Provided output picture won't fit into provided buffer");
        }
        if (fh.frameType == 0) {
            this.decodePicture(data, target, lowBits, codedWidth, codedHeight, codedWidth >> 3, fh.qMatLuma, fh.qMatChroma, progressive_scan_4x4, 0, fh.chromaType);
        } else {
            this.decodePicture(data, target, lowBits, codedWidth, codedHeight >> 1, codedWidth >> 3, fh.qMatLuma, fh.qMatChroma, interlaced_scan_4x4, fh.topFieldFirst ? 1 : 2, fh.chromaType);
            this.decodePicture(data, target, lowBits, codedWidth, codedHeight >> 1, codedWidth >> 3, fh.qMatLuma, fh.qMatChroma, interlaced_scan_4x4, fh.topFieldFirst ? 2 : 1, fh.chromaType);
        }
        ColorSpace color = fh.chromaType == 2 ? ColorSpace.YUV422 : ColorSpace.YUV444;
        return new Picture(codedWidth, codedHeight, target, lowBits, color, lowBits == null ? 0 : 2, new Rect(0, 0, fh.width >> 1 & color.getWidthMask(), fh.height >> 1 & color.getHeightMask()));
    }

    @Override
    protected void putSlice(byte[][] result, byte[][] lowBits, int lumaStride, int mbX, int mbY, int[] y, int[] u, int[] v, int dist, int shift, int chromaType, int sliceMbCount) {
        int chromaStride = lumaStride >> 1;
        this._putLuma(result[0], lowBits == null ? null : lowBits[0], shift * lumaStride, lumaStride << dist, mbX, mbY, y, sliceMbCount, dist, shift);
        if (chromaType == 2) {
            this._putChroma(result[1], lowBits == null ? null : lowBits[1], shift * chromaStride, chromaStride << dist, mbX, mbY, u, sliceMbCount, dist, shift);
            this._putChroma(result[2], lowBits == null ? null : lowBits[2], shift * chromaStride, chromaStride << dist, mbX, mbY, v, sliceMbCount, dist, shift);
        } else {
            this._putLuma(result[1], lowBits == null ? null : lowBits[1], shift * lumaStride, lumaStride << dist, mbX, mbY, u, sliceMbCount, dist, shift);
            this._putLuma(result[2], lowBits == null ? null : lowBits[2], shift * lumaStride, lumaStride << dist, mbX, mbY, v, sliceMbCount, dist, shift);
        }
    }

    private void _putLuma(byte[] y, byte[] lowBits, int fieldOffset, int stride, int mbX, int mbY, int[] luma, int mbPerSlice, int dist, int shift) {
        int col;
        int line;
        int lineOff;
        int mbTopLeftOff = fieldOffset + (mbX << 3) + (mbY << 3) * stride;
        int mb = 0;
        int sOff = 0;
        while (mb < mbPerSlice) {
            lineOff = mbTopLeftOff;
            for (line = 0; line < 8; ++line) {
                int round;
                for (col = 0; col < 4; ++col) {
                    round = MathUtil.clip(luma[sOff + col] + 2 >> 2, 1, 255);
                    y[lineOff + col] = (byte)(round - 128);
                }
                for (col = 4; col < 8; ++col) {
                    round = MathUtil.clip(luma[sOff + col + 12] + 2 >> 2, 1, 255);
                    y[lineOff + col] = (byte)(round - 128);
                }
                sOff += srcIncLuma[line];
                lineOff += stride;
            }
            ++mb;
            mbTopLeftOff += 8;
        }
        if (lowBits != null) {
            mbTopLeftOff = fieldOffset + (mbX << 3) + (mbY << 3) * stride;
            mb = 0;
            sOff = 0;
            while (mb < mbPerSlice) {
                lineOff = mbTopLeftOff;
                for (line = 0; line < 4; ++line) {
                    int round;
                    int val;
                    for (col = 0; col < 4; ++col) {
                        val = MathUtil.clip(luma[sOff + col], 4, 1019);
                        round = MathUtil.clip(luma[sOff + col] + 2 >> 2, 1, 255);
                        lowBits[lineOff + col] = (byte)(val - (round << 2));
                    }
                    for (col = 4; col < 8; ++col) {
                        val = MathUtil.clip(luma[sOff + col + 12], 4, 1019);
                        round = MathUtil.clip(luma[sOff + col] + 2 >> 2, 1, 255);
                        lowBits[lineOff + col] = (byte)(val - (round << 2));
                    }
                    sOff += srcIncLuma[line];
                    lineOff += stride;
                }
                ++mb;
                mbTopLeftOff += 8;
            }
        }
    }

    private void _putChroma(byte[] y, byte[] lowBits, int fieldOff, int stride, int mbX, int mbY, int[] chroma, int mbPerSlice, int dist, int shift) {
        int col;
        int line;
        int lineOff;
        int mbTopLeftOff = fieldOff + (mbX << 2) + (mbY << 3) * stride;
        int k = 0;
        int sOff = 0;
        while (k < mbPerSlice) {
            lineOff = mbTopLeftOff;
            for (line = 0; line < 8; ++line) {
                for (col = 0; col < 4; ++col) {
                    int round = MathUtil.clip(chroma[sOff + col] + 2 >> 2, 1, 255);
                    y[lineOff + col] = (byte)(round - 128);
                }
                sOff += 4;
                lineOff += stride;
            }
            ++k;
            mbTopLeftOff += 4;
        }
        if (lowBits != null) {
            mbTopLeftOff = fieldOff + (mbX << 2) + (mbY << 3) * stride;
            k = 0;
            sOff = 0;
            while (k < mbPerSlice) {
                lineOff = mbTopLeftOff;
                for (line = 0; line < 8; ++line) {
                    for (col = 0; col < 4; ++col) {
                        int val = MathUtil.clip(chroma[sOff + col], 4, 1019);
                        int round = MathUtil.clip(chroma[sOff + col] + 2 >> 2, 1, 255);
                        lowBits[lineOff + col] = (byte)(val - (round << 2));
                    }
                    sOff += 4;
                    lineOff += stride;
                }
                ++k;
                mbTopLeftOff += 4;
            }
        }
    }
}
