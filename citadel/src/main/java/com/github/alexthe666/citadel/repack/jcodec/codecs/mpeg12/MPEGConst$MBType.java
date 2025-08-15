/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg12;

public static class MPEGConst.MBType {
    public int macroblock_quant;
    public int macroblock_motion_forward;
    public int macroblock_motion_backward;
    public int macroblock_pattern;
    public int macroblock_intra;
    public int spatial_temporal_weight_code_flag;
    public int permitted_spatial_temporal_weight_classes;

    MPEGConst.MBType(int macroblock_quant, int macroblock_motion_forward, int macroblock_motion_backward, int macroblock_pattern, int macroblock_intra, int spatial_temporal_weight_code_flag, int permitted_spatial_temporal_weight_classes) {
        this.macroblock_quant = macroblock_quant;
        this.macroblock_motion_forward = macroblock_motion_forward;
        this.macroblock_motion_backward = macroblock_motion_backward;
        this.macroblock_pattern = macroblock_pattern;
        this.macroblock_intra = macroblock_intra;
        this.spatial_temporal_weight_code_flag = spatial_temporal_weight_code_flag;
        this.permitted_spatial_temporal_weight_classes = permitted_spatial_temporal_weight_classes;
    }
}
