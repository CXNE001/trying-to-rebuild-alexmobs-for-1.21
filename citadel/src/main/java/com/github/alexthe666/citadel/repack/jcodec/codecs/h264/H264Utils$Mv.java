/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264;

public static class H264Utils.Mv {
    public static int mvX(int mv) {
        return mv << 18 >> 18;
    }

    public static int mvY(int mv) {
        return mv << 6 >> 20;
    }

    public static int mvRef(int mv) {
        return mv >> 26;
    }

    public static int packMv(int mvx, int mvy, int r) {
        return (r & 0x3F) << 26 | (mvy & 0xFFF) << 14 | mvx & 0x3FFF;
    }

    public static int mvC(int mv, int comp) {
        return comp == 0 ? H264Utils.Mv.mvX(mv) : H264Utils.Mv.mvY(mv);
    }
}
