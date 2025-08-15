/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpa;

import java.util.Arrays;

public class Mp3Mdct {
    private static final float factor36pt0 = 0.34729636f;
    private static final float factor36pt1 = 1.5320889f;
    private static final float factor36pt2 = 1.8793852f;
    private static final float factor36pt3 = 1.7320508f;
    private static final float factor36pt4 = 1.9696155f;
    private static final float factor36pt5 = 1.2855753f;
    private static final float factor36pt6 = 0.6840403f;
    private static final float[] factor36 = new float[]{0.5019099f, 0.5176381f, 0.55168897f, 0.61038727f, 0.8717234f, 1.1831008f, 1.9318516f, 5.7368565f};
    private static final float cos075 = 0.9914449f;
    private static final float cos225 = 0.9238795f;
    private static final float cos300 = 0.8660254f;
    private static final float cos375 = 0.7933533f;
    private static final float cos450 = 0.70710677f;
    private static final float cos525 = 0.6087614f;
    private static final float cos600 = 0.5f;
    private static final float cos675 = 0.38268343f;
    private static final float cos825 = 0.13052619f;
    private static final float factor12pt0 = 1.9318516f;
    private static final float factor12pt1 = 0.5176381f;
    private static final float[] factor12 = new float[]{0.5043145f, 0.5411961f, 0.6302362f, 0.8213398f, 1.306563f, 3.830649f};
    private static float[] tmp = new float[16];

    static void oneLong(float[] src, float[] dst) {
        int i;
        for (i = 17; i > 0; --i) {
            int n = i;
            src[n] = src[n] + src[i - 1];
        }
        for (i = 17; i > 2; i -= 2) {
            int n = i;
            src[n] = src[n] + src[i - 2];
        }
        i = 0;
        int k = 0;
        while (i < 2) {
            float tmp0 = src[i] + src[i];
            float tmp1 = tmp0 + src[12 + i];
            float tmp2 = src[6 + i] * 1.7320508f;
            Mp3Mdct.tmp[k + 0] = tmp1 + src[4 + i] * 1.8793852f + src[8 + i] * 1.5320889f + src[16 + i] * 0.34729636f;
            Mp3Mdct.tmp[k + 1] = tmp0 + src[4 + i] - src[8 + i] - src[12 + i] - src[12 + i] - src[16 + i];
            Mp3Mdct.tmp[k + 2] = tmp1 - src[4 + i] * 0.34729636f - src[8 + i] * 1.8793852f + src[16 + i] * 1.5320889f;
            Mp3Mdct.tmp[k + 3] = tmp1 - src[4 + i] * 1.5320889f + src[8 + i] * 0.34729636f - src[16 + i] * 1.8793852f;
            Mp3Mdct.tmp[k + 4] = src[2 + i] * 1.9696155f + tmp2 + src[10 + i] * 1.2855753f + src[14 + i] * 0.6840403f;
            Mp3Mdct.tmp[k + 5] = (src[2 + i] - src[10 + i] - src[14 + i]) * 1.7320508f;
            Mp3Mdct.tmp[k + 6] = src[2 + i] * 1.2855753f - tmp2 - src[10 + i] * 0.6840403f + src[14 + i] * 1.9696155f;
            Mp3Mdct.tmp[k + 7] = src[2 + i] * 0.6840403f - tmp2 + src[10 + i] * 1.9696155f - src[14 + i] * 1.2855753f;
            ++i;
            k += 8;
        }
        i = 0;
        int j = 4;
        int k2 = 8;
        int l = 12;
        while (i < 4) {
            float q1 = tmp[i];
            float q2 = tmp[k2];
            int n = i;
            tmp[n] = tmp[n] + tmp[j];
            Mp3Mdct.tmp[j] = q1 - tmp[j];
            Mp3Mdct.tmp[k2] = (tmp[k2] + tmp[l]) * factor36[i];
            Mp3Mdct.tmp[l] = (q2 - tmp[l]) * factor36[7 - i];
            ++i;
            ++j;
            ++k2;
            ++l;
        }
        for (i = 0; i < 4; ++i) {
            dst[26 - i] = tmp[i] + tmp[8 + i];
            dst[8 - i] = tmp[8 + i] - tmp[i];
            dst[27 + i] = dst[26 - i];
            dst[9 + i] = -dst[8 - i];
        }
        for (i = 0; i < 4; ++i) {
            dst[21 - i] = tmp[7 - i] + tmp[15 - i];
            dst[3 - i] = tmp[15 - i] - tmp[7 - i];
            dst[32 + i] = dst[21 - i];
            dst[14 + i] = -dst[3 - i];
        }
        float tmp0 = src[0] - src[4] + src[8] - src[12] + src[16];
        float tmp1 = (src[1] - src[5] + src[9] - src[13] + src[17]) * 0.70710677f;
        dst[4] = tmp1 - tmp0;
        dst[13] = -dst[4];
        dst[31] = dst[22] = tmp0 + tmp1;
    }

    static void threeShort(float[] src, float[] dst) {
        Arrays.fill(dst, 0.0f);
        int i = 0;
        int outOff = 0;
        while (i < 3) {
            Mp3Mdct.imdct12(src, dst, outOff, i);
            ++i;
            outOff += 6;
        }
    }

    private static void imdct12(float[] src, float[] dst, int outOff, int wndIdx) {
        int j = 15 + wndIdx;
        int k = 12 + wndIdx;
        while (j >= 3 + wndIdx) {
            int n = j;
            src[n] = src[n] + src[k];
            j -= 3;
            k -= 3;
        }
        int n = 15 + wndIdx;
        src[n] = src[n] + src[9 + wndIdx];
        int n2 = 9 + wndIdx;
        src[n2] = src[n2] + src[3 + wndIdx];
        float pp2 = src[12 + wndIdx] * 0.5f;
        float pp1 = src[6 + wndIdx] * 0.8660254f;
        float sum = src[0 + wndIdx] + pp2;
        Mp3Mdct.tmp[1] = src[wndIdx] - src[12 + wndIdx];
        Mp3Mdct.tmp[0] = sum + pp1;
        Mp3Mdct.tmp[2] = sum - pp1;
        pp2 = src[15 + wndIdx] * 0.5f;
        pp1 = src[9 + wndIdx] * 0.8660254f;
        sum = src[3 + wndIdx] + pp2;
        Mp3Mdct.tmp[4] = src[3 + wndIdx] - src[15 + wndIdx];
        Mp3Mdct.tmp[5] = sum + pp1;
        Mp3Mdct.tmp[3] = sum - pp1;
        tmp[3] = tmp[3] * 1.9318516f;
        tmp[4] = tmp[4] * 0.70710677f;
        tmp[5] = tmp[5] * 0.5176381f;
        float t = tmp[0];
        tmp[0] = tmp[0] + tmp[5];
        Mp3Mdct.tmp[5] = t - tmp[5];
        t = tmp[1];
        tmp[1] = tmp[1] + tmp[4];
        Mp3Mdct.tmp[4] = t - tmp[4];
        t = tmp[2];
        tmp[2] = tmp[2] + tmp[3];
        Mp3Mdct.tmp[3] = t - tmp[3];
        for (int j2 = 0; j2 < 6; ++j2) {
            int n3 = j2;
            tmp[n3] = tmp[n3] * factor12[j2];
        }
        Mp3Mdct.tmp[8] = -tmp[0] * 0.7933533f;
        Mp3Mdct.tmp[9] = -tmp[0] * 0.6087614f;
        Mp3Mdct.tmp[7] = -tmp[1] * 0.9238795f;
        Mp3Mdct.tmp[10] = -tmp[1] * 0.38268343f;
        Mp3Mdct.tmp[6] = -tmp[2] * 0.9914449f;
        Mp3Mdct.tmp[11] = -tmp[2] * 0.13052619f;
        Mp3Mdct.tmp[0] = tmp[3];
        Mp3Mdct.tmp[1] = tmp[4] * 0.38268343f;
        Mp3Mdct.tmp[2] = tmp[5] * 0.6087614f;
        Mp3Mdct.tmp[3] = -tmp[5] * 0.7933533f;
        Mp3Mdct.tmp[4] = -tmp[4] * 0.9238795f;
        Mp3Mdct.tmp[5] = -tmp[0] * 0.9914449f;
        tmp[0] = tmp[0] * 0.13052619f;
        int j3 = outOff + 6;
        for (int i = 0; i < 12; ++i) {
            int n4 = j3++;
            dst[n4] = dst[n4] + tmp[i];
        }
    }
}
