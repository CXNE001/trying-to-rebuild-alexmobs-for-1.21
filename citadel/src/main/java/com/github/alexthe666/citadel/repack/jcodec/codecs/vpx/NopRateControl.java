/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.vpx;

import com.github.alexthe666.citadel.repack.jcodec.codecs.vpx.RateControl;

public class NopRateControl
implements RateControl {
    private int qp;

    public NopRateControl(int qp) {
        this.qp = qp;
    }

    @Override
    public int[] getSegmentQps() {
        return new int[]{this.qp};
    }

    @Override
    public int getSegment() {
        return 0;
    }

    @Override
    public void report(int bits) {
    }

    @Override
    public void reset() {
    }
}
