/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mjpeg;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mjpeg.JpegDecoder;
import com.github.alexthe666.citadel.repack.jcodec.common.dct.IDCT2x2;
import com.github.alexthe666.citadel.repack.jcodec.common.io.BitReader;
import com.github.alexthe666.citadel.repack.jcodec.common.io.VLC;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Rect;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;
import java.nio.ByteBuffer;

public class JpegToThumb2x2
extends JpegDecoder {
    private static final int[] mapping2x2 = new int[]{0, 1, 2, 4, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};

    @Override
    void decodeBlock(BitReader bits, int[] dcPredictor, int[][] quant, VLC[] huff, Picture result, int[] buf, int blkX, int blkY, int plane, int chroma, int field, int step) {
        buf[3] = 0;
        buf[2] = 0;
        buf[1] = 0;
        dcPredictor[plane] = buf[0] = JpegToThumb2x2.readDCValue(bits, huff[chroma]) * quant[chroma][0] + dcPredictor[plane];
        this.readACValues(bits, buf, huff[chroma + 2], quant[chroma]);
        IDCT2x2.idct(buf, 0);
        JpegToThumb2x2.putBlock2x2(result.getPlaneData(plane), result.getPlaneWidth(plane), buf, blkX, blkY, field, step);
    }

    private static void putBlock2x2(byte[] plane, int stride, int[] patch, int x, int y, int field, int step) {
        int dstride = (stride >>= 2) * step;
        int off = field * stride + (y >> 2) * dstride + (x >> 2);
        plane[off] = (byte)(MathUtil.clip(patch[0], 0, 255) - 128);
        plane[off + 1] = (byte)(MathUtil.clip(patch[1], 0, 255) - 128);
        plane[off + dstride] = (byte)(MathUtil.clip(patch[2], 0, 255) - 128);
        plane[off + dstride + 1] = (byte)(MathUtil.clip(patch[3], 0, 255) - 128);
    }

    @Override
    void readACValues(BitReader _in, int[] target, VLC table, int[] quantTable) {
        int len;
        int rle;
        int code;
        int curOff = 1;
        do {
            if ((code = table.readVLC16(_in)) == 240) {
                curOff += 16;
                continue;
            }
            if (code <= 0) continue;
            rle = code >> 4;
            len = code & 0xF;
            target[JpegToThumb2x2.mapping2x2[curOff += rle]] = JpegToThumb2x2.toValue(_in.readNBit(len), len) * quantTable[curOff];
            ++curOff;
        } while (code != 0 && curOff < 5);
        if (code != 0) {
            do {
                if ((code = table.readVLC16(_in)) == 240) {
                    curOff += 16;
                    continue;
                }
                if (code <= 0) continue;
                rle = code >> 4;
                curOff += rle;
                len = code & 0xF;
                _in.skip(len);
                ++curOff;
            } while (code != 0 && curOff < 64);
        }
    }

    @Override
    public Picture decodeField(ByteBuffer data, byte[][] data2, int field, int step) {
        Picture res = super.decodeField(data, data2, field, step);
        return new Picture(res.getWidth() >> 2, res.getHeight() >> 2, res.getData(), null, res.getColor(), 0, new Rect(0, 0, res.getCroppedWidth() >> 2, res.getCroppedHeight() >> 2));
    }
}
