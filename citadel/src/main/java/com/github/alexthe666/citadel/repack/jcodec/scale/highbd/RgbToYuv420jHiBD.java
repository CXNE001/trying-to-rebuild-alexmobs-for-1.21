/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.scale.highbd;

import com.github.alexthe666.citadel.repack.jcodec.common.model.PictureHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.TransformHiBD;

public class RgbToYuv420jHiBD
implements TransformHiBD {
    @Override
    public void transform(PictureHiBD img, PictureHiBD dst) {
        int[] y = img.getData()[0];
        int[][] dstData = dst.getData();
        int offChr = 0;
        int offLuma = 0;
        int offSrc = 0;
        int strideSrc = img.getWidth() * 3;
        int strideDst = dst.getWidth();
        for (int i = 0; i < img.getHeight() >> 1; ++i) {
            for (int j = 0; j < img.getWidth() >> 1; ++j) {
                dstData[1][offChr] = 0;
                dstData[2][offChr] = 0;
                RgbToYuv420jHiBD.rgb2yuv(y[offSrc], y[offSrc + 1], y[offSrc + 2], dstData[0], offLuma, dstData[1], offChr, dstData[2], offChr);
                dstData[0][offLuma] = dstData[0][offLuma];
                RgbToYuv420jHiBD.rgb2yuv(y[offSrc + strideSrc], y[offSrc + strideSrc + 1], y[offSrc + strideSrc + 2], dstData[0], offLuma + strideDst, dstData[1], offChr, dstData[2], offChr);
                dstData[0][offLuma + strideDst] = dstData[0][offLuma + strideDst];
                RgbToYuv420jHiBD.rgb2yuv(y[offSrc + 3], y[offSrc + 4], y[offSrc + 5], dstData[0], ++offLuma, dstData[1], offChr, dstData[2], offChr);
                dstData[0][offLuma] = dstData[0][offLuma];
                RgbToYuv420jHiBD.rgb2yuv(y[offSrc + strideSrc + 3], y[offSrc + strideSrc + 4], y[offSrc + strideSrc + 5], dstData[0], offLuma + strideDst, dstData[1], offChr, dstData[2], offChr);
                dstData[0][offLuma + strideDst] = dstData[0][offLuma + strideDst];
                ++offLuma;
                dstData[1][offChr] = dstData[1][offChr] >> 2;
                dstData[2][offChr] = dstData[2][offChr] >> 2;
                ++offChr;
                offSrc += 6;
            }
            offLuma += strideDst;
            offSrc += strideSrc;
        }
    }

    public static final void rgb2yuv(int r, int g, int b, int[] Y, int offY, int[] U, int offU, int[] V, int offV) {
        int y = 77 * r + 150 * g + 15 * b;
        int u = -43 * r - 85 * g + 128 * b;
        int v = 128 * r - 107 * g - 21 * b;
        y = y + 128 >> 8;
        u = u + 128 >> 8;
        v = v + 128 >> 8;
        Y[offY] = RgbToYuv420jHiBD.clip(y);
        int n = offU;
        U[n] = U[n] + RgbToYuv420jHiBD.clip(u + 128);
        int n2 = offV;
        V[n2] = V[n2] + RgbToYuv420jHiBD.clip(v + 128);
    }

    private static final int clip(int val) {
        return val < 0 ? 0 : (val > 255 ? 255 : val);
    }
}
