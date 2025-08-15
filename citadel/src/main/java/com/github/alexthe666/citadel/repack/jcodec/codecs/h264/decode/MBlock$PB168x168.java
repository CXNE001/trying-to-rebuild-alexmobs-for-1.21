/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode;

static class MBlock.PB168x168 {
    public int[] refIdx1 = new int[2];
    public int[] refIdx2 = new int[2];
    public int[] mvdX1 = new int[2];
    public int[] mvdY1 = new int[2];
    public int[] mvdX2 = new int[2];
    public int[] mvdY2 = new int[2];

    public void clean() {
        this.refIdx1[1] = 0;
        this.refIdx1[0] = 0;
        this.refIdx2[1] = 0;
        this.refIdx2[0] = 0;
        this.mvdX1[1] = 0;
        this.mvdX1[0] = 0;
        this.mvdY1[1] = 0;
        this.mvdY1[0] = 0;
        this.mvdX2[1] = 0;
        this.mvdX2[0] = 0;
        this.mvdY2[1] = 0;
        this.mvdY2[0] = 0;
    }
}
