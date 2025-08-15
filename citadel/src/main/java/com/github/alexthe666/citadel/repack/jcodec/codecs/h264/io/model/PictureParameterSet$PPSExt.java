/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model;

public static class PictureParameterSet.PPSExt {
    public boolean transform8x8ModeFlag;
    public int[][] scalingMatrix;
    public int secondChromaQpIndexOffset;

    public boolean isTransform8x8ModeFlag() {
        return this.transform8x8ModeFlag;
    }

    public int[][] getScalingMatrix() {
        return this.scalingMatrix;
    }

    public int getSecondChromaQpIndexOffset() {
        return this.secondChromaQpIndexOffset;
    }
}
