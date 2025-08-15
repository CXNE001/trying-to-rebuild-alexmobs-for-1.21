/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.scale;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;
import com.github.alexthe666.citadel.repack.jcodec.scale.Transform;

public class Yuv420pToRgb
implements Transform {
    @Override
    public final void transform(Picture src, Picture dst) {
        int j;
        byte[] yh = src.getPlaneData(0);
        byte[] uh = src.getPlaneData(1);
        byte[] vh = src.getPlaneData(2);
        byte[] yl = null;
        byte[] ul = null;
        byte[] vl = null;
        byte[][] low = src.getLowBits();
        if (low != null) {
            yl = low[0];
            ul = low[1];
            vl = low[2];
        }
        byte[] data = dst.getPlaneData(0);
        byte[] lowBits = dst.getLowBits() == null ? null : dst.getLowBits()[0];
        boolean hbd = src.isHiBD() && dst.isHiBD();
        int lowBitsNumSrc = src.getLowBitsNum();
        int lowBitsNumDst = dst.getLowBitsNum();
        int offLuma = 0;
        int offChroma = 0;
        int stride = dst.getWidth();
        for (int i = 0; i < dst.getHeight() >> 1; ++i) {
            for (int k = 0; k < dst.getWidth() >> 1; ++k) {
                int j2 = k << 1;
                if (hbd) {
                    Yuv420pToRgb.YUV420pToRGBH2H(yh[offLuma + j2], yl[offLuma + j2], uh[offChroma], ul[offChroma], vh[offChroma], vl[offChroma], lowBitsNumSrc, data, lowBits, lowBitsNumDst, (offLuma + j2) * 3);
                    Yuv420pToRgb.YUV420pToRGBH2H(yh[offLuma + j2 + 1], yl[offLuma + j2 + 1], uh[offChroma], ul[offChroma], vh[offChroma], vl[offChroma], lowBitsNumSrc, data, lowBits, lowBitsNumDst, (offLuma + j2 + 1) * 3);
                    Yuv420pToRgb.YUV420pToRGBH2H(yh[offLuma + j2 + stride], yl[offLuma + j2 + stride], uh[offChroma], ul[offChroma], vh[offChroma], vl[offChroma], lowBitsNumSrc, data, lowBits, lowBitsNumDst, (offLuma + j2 + stride) * 3);
                    Yuv420pToRgb.YUV420pToRGBH2H(yh[offLuma + j2 + stride + 1], yl[offLuma + j2 + stride + 1], uh[offChroma], ul[offChroma], vh[offChroma], vl[offChroma], lowBitsNumSrc, data, lowBits, lowBitsNumDst, (offLuma + j2 + stride + 1) * 3);
                } else {
                    Yuv420pToRgb.YUV420pToRGBN2N(yh[offLuma + j2], uh[offChroma], vh[offChroma], data, (offLuma + j2) * 3);
                    Yuv420pToRgb.YUV420pToRGBN2N(yh[offLuma + j2 + 1], uh[offChroma], vh[offChroma], data, (offLuma + j2 + 1) * 3);
                    Yuv420pToRgb.YUV420pToRGBN2N(yh[offLuma + j2 + stride], uh[offChroma], vh[offChroma], data, (offLuma + j2 + stride) * 3);
                    Yuv420pToRgb.YUV420pToRGBN2N(yh[offLuma + j2 + stride + 1], uh[offChroma], vh[offChroma], data, (offLuma + j2 + stride + 1) * 3);
                }
                ++offChroma;
            }
            if ((dst.getWidth() & 1) != 0) {
                j = dst.getWidth() - 1;
                Yuv420pToRgb.YUV420pToRGBN2N(yh[offLuma + j], uh[offChroma], vh[offChroma], data, (offLuma + j) * 3);
                Yuv420pToRgb.YUV420pToRGBN2N(yh[offLuma + j + stride], uh[offChroma], vh[offChroma], data, (offLuma + j + stride) * 3);
                ++offChroma;
            }
            offLuma += 2 * stride;
        }
        if ((dst.getHeight() & 1) != 0) {
            for (int k = 0; k < dst.getWidth() >> 1; ++k) {
                j = k << 1;
                Yuv420pToRgb.YUV420pToRGBN2N(yh[offLuma + j], uh[offChroma], vh[offChroma], data, (offLuma + j) * 3);
                Yuv420pToRgb.YUV420pToRGBN2N(yh[offLuma + j + 1], uh[offChroma], vh[offChroma], data, (offLuma + j + 1) * 3);
                ++offChroma;
            }
            if ((dst.getWidth() & 1) != 0) {
                int j3 = dst.getWidth() - 1;
                Yuv420pToRgb.YUV420pToRGBN2N(yh[offLuma + j3], uh[offChroma], vh[offChroma], data, (offLuma + j3) * 3);
                ++offChroma;
            }
        }
    }

    public static void YUV420pToRGBN2N(byte y, byte u, byte v, byte[] data, int off) {
        int c = y + 112;
        int r = 298 * c + 409 * v + 128 >> 8;
        int g = 298 * c - 100 * u - 208 * v + 128 >> 8;
        int b = 298 * c + 516 * u + 128 >> 8;
        data[off] = (byte)(MathUtil.clip(r, 0, 255) - 128);
        data[off + 1] = (byte)(MathUtil.clip(g, 0, 255) - 128);
        data[off + 2] = (byte)(MathUtil.clip(b, 0, 255) - 128);
    }

    public static void YUV420pToRGBH2H(byte yh, byte yl, byte uh, byte ul, byte vh, byte vl, int nlbi, byte[] data, byte[] lowBits, int nlbo, int off) {
        int clipMax = (1 << nlbo << 8) - 1;
        int round = 1 << nlbo >> 1;
        int c = (yh + 128 << nlbi) + yl - 64;
        int d = (uh + 128 << nlbi) + ul - 512;
        int e = (vh + 128 << nlbi) + vl - 512;
        int r = MathUtil.clip(298 * c + 409 * e + 128 >> 8, 0, clipMax);
        int g = MathUtil.clip(298 * c - 100 * d - 208 * e + 128 >> 8, 0, clipMax);
        int b = MathUtil.clip(298 * c + 516 * d + 128 >> 8, 0, clipMax);
        int valR = MathUtil.clip(r + round >> nlbo, 0, 255);
        data[off] = (byte)(valR - 128);
        lowBits[off] = (byte)(r - (valR << nlbo));
        int valG = MathUtil.clip(g + round >> nlbo, 0, 255);
        data[off + 1] = (byte)(valG - 128);
        lowBits[off + 1] = (byte)(g - (valG << nlbo));
        int valB = MathUtil.clip(b + round >> nlbo, 0, 255);
        data[off + 2] = (byte)(valB - 128);
        lowBits[off + 2] = (byte)(b - (valB << nlbo));
    }
}
