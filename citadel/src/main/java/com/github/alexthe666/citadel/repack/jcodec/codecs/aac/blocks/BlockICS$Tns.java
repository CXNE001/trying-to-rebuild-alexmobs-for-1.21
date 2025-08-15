/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.aac.blocks;

public static class BlockICS.Tns {
    private int[] nFilt;
    private int[][] length;
    private int[][] order;
    private int[][] direction;
    private float[][][] coeff;

    public BlockICS.Tns(int[] nFilt, int[][] length, int[][] order, int[][] direction, float[][][] coeff) {
        this.nFilt = nFilt;
        this.length = length;
        this.order = order;
        this.direction = direction;
        this.coeff = coeff;
    }
}
