/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.H264Utils;

public static class H264Utils.MvList {
    private int[] list;
    private static final int NA = H264Utils.Mv.packMv(0, 0, -1);

    public H264Utils.MvList(int size) {
        this.list = new int[size << 1];
        this.clear();
    }

    public void clear() {
        for (int i = 0; i < this.list.length; i += 2) {
            int n = NA;
            this.list[i + 1] = n;
            this.list[i] = n;
        }
    }

    public int mv0X(int off) {
        return H264Utils.Mv.mvX(this.list[off << 1]);
    }

    public int mv0Y(int off) {
        return H264Utils.Mv.mvY(this.list[off << 1]);
    }

    public int mv0R(int off) {
        return H264Utils.Mv.mvRef(this.list[off << 1]);
    }

    public int mv1X(int off) {
        return H264Utils.Mv.mvX(this.list[(off << 1) + 1]);
    }

    public int mv1Y(int off) {
        return H264Utils.Mv.mvY(this.list[(off << 1) + 1]);
    }

    public int mv1R(int off) {
        return H264Utils.Mv.mvRef(this.list[(off << 1) + 1]);
    }

    public int getMv(int off, int forward) {
        return this.list[(off << 1) + forward];
    }

    public void setMv(int off, int forward, int mv) {
        this.list[(off << 1) + forward] = mv;
    }

    public void setPair(int off, int mv0, int mv1) {
        this.list[off << 1] = mv0;
        this.list[(off << 1) + 1] = mv1;
    }

    public void copyPair(int off, H264Utils.MvList other, int otherOff) {
        this.list[off << 1] = other.list[otherOff << 1];
        this.list[(off << 1) + 1] = other.list[(otherOff << 1) + 1];
    }
}
