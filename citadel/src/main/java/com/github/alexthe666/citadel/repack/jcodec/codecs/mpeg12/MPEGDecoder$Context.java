/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12.MPEGConst;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;

public static class MPEGDecoder.Context {
    int[] intra_dc_predictor = new int[3];
    public int mbWidth;
    int mbNo;
    public int codedWidth;
    public int codedHeight;
    public int mbHeight;
    public ColorSpace color;
    public MPEGConst.MBType lastPredB;
    public int[][] qMats;
    public int[] scan;
    public int picWidth;
    public int picHeight;
}
