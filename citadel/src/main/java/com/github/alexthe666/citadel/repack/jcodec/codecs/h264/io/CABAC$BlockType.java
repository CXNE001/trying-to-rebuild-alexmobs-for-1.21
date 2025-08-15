/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io;

public static final class CABAC.BlockType {
    public static final CABAC.BlockType LUMA_16_DC = new CABAC.BlockType(85, 105, 166, 277, 338, 227, 0);
    public static final CABAC.BlockType LUMA_15_AC = new CABAC.BlockType(89, 120, 181, 292, 353, 237, 0);
    public static final CABAC.BlockType LUMA_16 = new CABAC.BlockType(93, 134, 195, 306, 367, 247, 0);
    public static final CABAC.BlockType CHROMA_DC = new CABAC.BlockType(97, 149, 210, 321, 382, 257, 1);
    public static final CABAC.BlockType CHROMA_AC = new CABAC.BlockType(101, 152, 213, 324, 385, 266, 0);
    public static final CABAC.BlockType LUMA_64 = new CABAC.BlockType(1012, 402, 417, 436, 451, 426, 0);
    public static final CABAC.BlockType CB_16_DC = new CABAC.BlockType(460, 484, 572, 776, 864, 952, 0);
    public static final CABAC.BlockType CB_15x16_AC = new CABAC.BlockType(464, 499, 587, 791, 879, 962, 0);
    public static final CABAC.BlockType CB_16 = new CABAC.BlockType(468, 513, 601, 805, 893, 972, 0);
    public static final CABAC.BlockType CB_64 = new CABAC.BlockType(1016, 660, 690, 675, 699, 708, 0);
    public static final CABAC.BlockType CR_16_DC = new CABAC.BlockType(472, 528, 616, 820, 908, 982, 0);
    public static final CABAC.BlockType CR_15x16_AC = new CABAC.BlockType(476, 543, 631, 835, 923, 992, 0);
    public static final CABAC.BlockType CR_16 = new CABAC.BlockType(480, 557, 645, 849, 937, 1002, 0);
    public static final CABAC.BlockType CR_64 = new CABAC.BlockType(1020, 718, 748, 733, 757, 766, 0);
    public int codedBlockCtxOff;
    public int sigCoeffFlagCtxOff;
    public int lastSigCoeffCtxOff;
    public int sigCoeffFlagFldCtxOff;
    public int lastSigCoeffFldCtxOff;
    public int coeffAbsLevelCtxOff;
    public int coeffAbsLevelAdjust;

    private CABAC.BlockType(int codecBlockCtxOff, int sigCoeffCtxOff, int lastSigCoeffCtxOff, int sigCoeffFlagFldCtxOff, int lastSigCoeffFldCtxOff, int coeffAbsLevelCtxOff, int coeffAbsLevelAdjust) {
        this.codedBlockCtxOff = codecBlockCtxOff;
        this.sigCoeffFlagCtxOff = sigCoeffCtxOff;
        this.lastSigCoeffCtxOff = lastSigCoeffCtxOff;
        this.sigCoeffFlagFldCtxOff = sigCoeffFlagFldCtxOff;
        this.lastSigCoeffFldCtxOff = sigCoeffFlagFldCtxOff;
        this.coeffAbsLevelCtxOff = coeffAbsLevelCtxOff;
        this.coeffAbsLevelAdjust = coeffAbsLevelAdjust;
    }
}
