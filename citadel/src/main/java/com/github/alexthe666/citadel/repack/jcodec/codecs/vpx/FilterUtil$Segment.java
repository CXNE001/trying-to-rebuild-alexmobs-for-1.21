/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.vpx;

import com.github.alexthe666.citadel.repack.jcodec.codecs.vpx.FilterUtil;
import com.github.alexthe666.citadel.repack.jcodec.codecs.vpx.VPXMacroblock;

public static class FilterUtil.Segment {
    int p0;
    int p1;
    int p2;
    int p3;
    int q0;
    int q1;
    int q2;
    int q3;

    public boolean isFilterRequired(int interior, int edge) {
        return (Math.abs(this.p0 - this.q0) << 2) + (Math.abs(this.p1 - this.q1) >> 2) <= edge && Math.abs(this.p3 - this.p2) <= interior && Math.abs(this.p2 - this.p1) <= interior && Math.abs(this.p1 - this.p0) <= interior && Math.abs(this.q3 - this.q2) <= interior && Math.abs(this.q2 - this.q1) <= interior && Math.abs(this.q1 - this.q0) <= interior;
    }

    public boolean isHighVariance(int threshold) {
        return Math.abs(this.p1 - this.p0) > threshold || Math.abs(this.q1 - this.q0) > threshold;
    }

    public FilterUtil.Segment getSigned() {
        FilterUtil.Segment seg = new FilterUtil.Segment();
        seg.p3 = FilterUtil.minus128(this.p3);
        seg.p2 = FilterUtil.minus128(this.p2);
        seg.p1 = FilterUtil.minus128(this.p1);
        seg.p0 = FilterUtil.minus128(this.p0);
        seg.q0 = FilterUtil.minus128(this.q0);
        seg.q1 = FilterUtil.minus128(this.q1);
        seg.q2 = FilterUtil.minus128(this.q2);
        seg.q3 = FilterUtil.minus128(this.q3);
        return seg;
    }

    public static FilterUtil.Segment horizontal(VPXMacroblock.Subblock right, VPXMacroblock.Subblock left, int a) {
        FilterUtil.Segment seg = new FilterUtil.Segment();
        seg.p0 = left.val[12 + a];
        seg.p1 = left.val[8 + a];
        seg.p2 = left.val[4 + a];
        seg.p3 = left.val[0 + a];
        seg.q0 = right.val[0 + a];
        seg.q1 = right.val[4 + a];
        seg.q2 = right.val[8 + a];
        seg.q3 = right.val[12 + a];
        return seg;
    }

    public static FilterUtil.Segment vertical(VPXMacroblock.Subblock lower, VPXMacroblock.Subblock upper, int a) {
        FilterUtil.Segment seg = new FilterUtil.Segment();
        seg.p0 = upper.val[a * 4 + 3];
        seg.p1 = upper.val[a * 4 + 2];
        seg.p2 = upper.val[a * 4 + 1];
        seg.p3 = upper.val[a * 4 + 0];
        seg.q0 = lower.val[a * 4 + 0];
        seg.q1 = lower.val[a * 4 + 1];
        seg.q2 = lower.val[a * 4 + 2];
        seg.q3 = lower.val[a * 4 + 3];
        return seg;
    }

    public void applyHorizontally(VPXMacroblock.Subblock right, VPXMacroblock.Subblock left, int a) {
        left.val[12 + a] = this.p0;
        left.val[8 + a] = this.p1;
        left.val[4 + a] = this.p2;
        left.val[0 + a] = this.p3;
        right.val[0 + a] = this.q0;
        right.val[4 + a] = this.q1;
        right.val[8 + a] = this.q2;
        right.val[12 + a] = this.q3;
    }

    public void applyVertically(VPXMacroblock.Subblock lower, VPXMacroblock.Subblock upper, int a) {
        upper.val[a * 4 + 3] = this.p0;
        upper.val[a * 4 + 2] = this.p1;
        upper.val[a * 4 + 1] = this.p2;
        upper.val[a * 4 + 0] = this.p3;
        lower.val[a * 4 + 0] = this.q0;
        lower.val[a * 4 + 1] = this.q1;
        lower.val[a * 4 + 2] = this.q2;
        lower.val[a * 4 + 3] = this.q3;
    }

    void filterMb(int hevThreshold, int interiorLimit, int edgeLimit) {
        FilterUtil.Segment signedSeg = this.getSigned();
        if (signedSeg.isFilterRequired(interiorLimit, edgeLimit)) {
            if (!signedSeg.isHighVariance(hevThreshold)) {
                int w = FilterUtil.clipSigned(FilterUtil.clipSigned(signedSeg.p1 - signedSeg.q1) + 3 * (signedSeg.q0 - signedSeg.p0));
                int a = 27 * w + 63 >> 7;
                this.q0 = FilterUtil.clipPlus128(signedSeg.q0 - a);
                this.p0 = FilterUtil.clipPlus128(signedSeg.p0 + a);
                a = 18 * w + 63 >> 7;
                this.q1 = FilterUtil.clipPlus128(signedSeg.q1 - a);
                this.p1 = FilterUtil.clipPlus128(signedSeg.p1 + a);
                a = 9 * w + 63 >> 7;
                this.q2 = FilterUtil.clipPlus128(signedSeg.q2 - a);
                this.p2 = FilterUtil.clipPlus128(signedSeg.p2 + a);
            } else {
                this.adjust(true);
            }
        }
    }

    public void filterSb(int hev_threshold, int interior_limit, int edge_limit) {
        FilterUtil.Segment signedSeg = this.getSigned();
        if (signedSeg.isFilterRequired(interior_limit, edge_limit)) {
            boolean hv = signedSeg.isHighVariance(hev_threshold);
            int a = this.adjust(hv) + 1 >> 1;
            if (!hv) {
                this.q1 = FilterUtil.clipPlus128(signedSeg.q1 - a);
                this.p1 = FilterUtil.clipPlus128(signedSeg.p1 + a);
            }
        }
    }

    private int adjust(boolean use_outer_taps) {
        int p1 = FilterUtil.minus128(this.p1);
        int p0 = FilterUtil.minus128(this.p0);
        int q0 = FilterUtil.minus128(this.q0);
        int q1 = FilterUtil.minus128(this.q1);
        int a = FilterUtil.clipSigned((use_outer_taps ? FilterUtil.clipSigned(p1 - q1) : 0) + 3 * (q0 - p0));
        int b = FilterUtil.clipSigned(a + 3) >> 3;
        a = FilterUtil.clipSigned(a + 4) >> 3;
        this.q0 = FilterUtil.clipPlus128(q0 - a);
        this.p0 = FilterUtil.clipPlus128(p0 + b);
        return a;
    }
}
