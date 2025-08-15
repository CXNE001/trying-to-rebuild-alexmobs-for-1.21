/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.decode;

static class MBlock.PB8x8 {
    public int[][] refIdx = new int[2][4];
    public int[] subMbTypes = new int[4];
    public int[][] mvdX1 = new int[2][4];
    public int[][] mvdY1 = new int[2][4];
    public int[][] mvdX2 = new int[2][4];
    public int[][] mvdY2 = new int[2][4];
    public int[][] mvdX3 = new int[2][4];
    public int[][] mvdY3 = new int[2][4];
    public int[][] mvdX4 = new int[2][4];
    public int[][] mvdY4 = new int[2][4];

    public void clean() {
        this.mvdX1[0][3] = 0;
        this.mvdX1[0][2] = 0;
        this.mvdX1[0][1] = 0;
        this.mvdX1[0][0] = 0;
        this.mvdX2[0][3] = 0;
        this.mvdX2[0][2] = 0;
        this.mvdX2[0][1] = 0;
        this.mvdX2[0][0] = 0;
        this.mvdX3[0][3] = 0;
        this.mvdX3[0][2] = 0;
        this.mvdX3[0][1] = 0;
        this.mvdX3[0][0] = 0;
        this.mvdX4[0][3] = 0;
        this.mvdX4[0][2] = 0;
        this.mvdX4[0][1] = 0;
        this.mvdX4[0][0] = 0;
        this.mvdY1[0][3] = 0;
        this.mvdY1[0][2] = 0;
        this.mvdY1[0][1] = 0;
        this.mvdY1[0][0] = 0;
        this.mvdY2[0][3] = 0;
        this.mvdY2[0][2] = 0;
        this.mvdY2[0][1] = 0;
        this.mvdY2[0][0] = 0;
        this.mvdY3[0][3] = 0;
        this.mvdY3[0][2] = 0;
        this.mvdY3[0][1] = 0;
        this.mvdY3[0][0] = 0;
        this.mvdY4[0][3] = 0;
        this.mvdY4[0][2] = 0;
        this.mvdY4[0][1] = 0;
        this.mvdY4[0][0] = 0;
        this.mvdX1[1][3] = 0;
        this.mvdX1[1][2] = 0;
        this.mvdX1[1][1] = 0;
        this.mvdX1[1][0] = 0;
        this.mvdX2[1][3] = 0;
        this.mvdX2[1][2] = 0;
        this.mvdX2[1][1] = 0;
        this.mvdX2[1][0] = 0;
        this.mvdX3[1][3] = 0;
        this.mvdX3[1][2] = 0;
        this.mvdX3[1][1] = 0;
        this.mvdX3[1][0] = 0;
        this.mvdX4[1][3] = 0;
        this.mvdX4[1][2] = 0;
        this.mvdX4[1][1] = 0;
        this.mvdX4[1][0] = 0;
        this.mvdY1[1][3] = 0;
        this.mvdY1[1][2] = 0;
        this.mvdY1[1][1] = 0;
        this.mvdY1[1][0] = 0;
        this.mvdY2[1][3] = 0;
        this.mvdY2[1][2] = 0;
        this.mvdY2[1][1] = 0;
        this.mvdY2[1][0] = 0;
        this.mvdY3[1][3] = 0;
        this.mvdY3[1][2] = 0;
        this.mvdY3[1][1] = 0;
        this.mvdY3[1][0] = 0;
        this.mvdY4[1][3] = 0;
        this.mvdY4[1][2] = 0;
        this.mvdY4[1][1] = 0;
        this.mvdY4[1][0] = 0;
        this.subMbTypes[3] = 0;
        this.subMbTypes[2] = 0;
        this.subMbTypes[1] = 0;
        this.subMbTypes[0] = 0;
        this.refIdx[0][3] = 0;
        this.refIdx[0][2] = 0;
        this.refIdx[0][1] = 0;
        this.refIdx[0][0] = 0;
        this.refIdx[1][3] = 0;
        this.refIdx[1][2] = 0;
        this.refIdx[1][1] = 0;
        this.refIdx[1][0] = 0;
    }
}
