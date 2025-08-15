/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.H264Utils;

public static class H264Utils.MvList2D {
    private int[] list;
    private int stride;
    private int width;
    private int height;
    private static final int NA = H264Utils.Mv.packMv(0, 0, -1);

    public H264Utils.MvList2D(int width, int height) {
        this.list = new int[(width << 1) * height];
        this.stride = width << 1;
        this.width = width;
        this.height = height;
        this.clear();
    }

    public void clear() {
        for (int i = 0; i < this.list.length; i += 2) {
            int n = NA;
            this.list[i + 1] = n;
            this.list[i] = n;
        }
    }

    public int mv0X(int offX, int offY) {
        return H264Utils.Mv.mvX(this.list[(offX << 1) + this.stride * offY]);
    }

    public int mv0Y(int offX, int offY) {
        return H264Utils.Mv.mvY(this.list[(offX << 1) + this.stride * offY]);
    }

    public int mv0R(int offX, int offY) {
        return H264Utils.Mv.mvRef(this.list[(offX << 1) + this.stride * offY]);
    }

    public int mv1X(int offX, int offY) {
        return H264Utils.Mv.mvX(this.list[(offX << 1) + this.stride * offY + 1]);
    }

    public int mv1Y(int offX, int offY) {
        return H264Utils.Mv.mvY(this.list[(offX << 1) + this.stride * offY + 1]);
    }

    public int mv1R(int offX, int offY) {
        return H264Utils.Mv.mvRef(this.list[(offX << 1) + this.stride * offY + 1]);
    }

    public int getMv(int offX, int offY, int forward) {
        return this.list[(offX << 1) + this.stride * offY + forward];
    }

    public void setMv(int offX, int offY, int forward, int mv) {
        this.list[(offX << 1) + this.stride * offY + forward] = mv;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }
}
