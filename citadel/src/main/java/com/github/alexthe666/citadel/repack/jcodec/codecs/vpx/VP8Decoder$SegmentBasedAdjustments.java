/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.vpx;

private static class VP8Decoder.SegmentBasedAdjustments {
    private int[] segmentProbs;
    private int[] qp;
    private int[] lf;
    private int abs;

    public VP8Decoder.SegmentBasedAdjustments(int[] segmentProbs, int[] qp, int[] lf, int abs) {
        this.segmentProbs = segmentProbs;
        this.qp = qp;
        this.lf = lf;
        this.abs = abs;
    }

    static /* synthetic */ int[] access$000(VP8Decoder.SegmentBasedAdjustments x0) {
        return x0.segmentProbs;
    }

    static /* synthetic */ int[] access$100(VP8Decoder.SegmentBasedAdjustments x0) {
        return x0.qp;
    }

    static /* synthetic */ int access$200(VP8Decoder.SegmentBasedAdjustments x0) {
        return x0.abs;
    }

    static /* synthetic */ int[] access$300(VP8Decoder.SegmentBasedAdjustments x0) {
        return x0.lf;
    }
}
