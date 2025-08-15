/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpa;

static class Mp3Bitstream.Granule {
    int part23Length;
    int bigValues;
    int globalGain;
    int scalefacCompress;
    boolean windowSwitchingFlag;
    int blockType;
    boolean mixedBlockFlag;
    int[] tableSelect = new int[3];
    int[] subblockGain = new int[3];
    int region0Count;
    int region1Count;
    boolean preflag;
    int scalefacScale;
    int count1tableSelect;

    Mp3Bitstream.Granule() {
    }
}
