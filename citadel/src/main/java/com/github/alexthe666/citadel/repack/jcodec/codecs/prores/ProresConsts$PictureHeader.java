/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.prores;

public static class ProresConsts.PictureHeader {
    public int log2SliceMbWidth;
    public short[] sliceSizes;

    public ProresConsts.PictureHeader(int log2SliceMbWidth, short[] sliceSizes) {
        this.log2SliceMbWidth = log2SliceMbWidth;
        this.sliceSizes = sliceSizes;
    }
}
