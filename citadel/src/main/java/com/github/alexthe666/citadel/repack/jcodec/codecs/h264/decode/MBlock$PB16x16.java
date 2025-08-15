/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode;

static class MBlock.PB16x16 {
    public int[] refIdx = new int[2];
    public int[] mvdX = new int[2];
    public int[] mvdY = new int[2];

    public void clean() {
        this.refIdx[1] = 0;
        this.refIdx[0] = 0;
        this.mvdX[1] = 0;
        this.mvdX[0] = 0;
        this.mvdY[1] = 0;
        this.mvdY[0] = 0;
    }
}
