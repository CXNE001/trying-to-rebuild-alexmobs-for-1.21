/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264;

public class H264Utils2 {
    public static int golomb2Signed(int val) {
        int sign = ((val & 1) << 1) - 1;
        val = ((val >> 1) + (val & 1)) * sign;
        return val;
    }
}
