/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.movtool;

public static class Cut.Slice {
    private double inSec;
    private double outSec;

    public Cut.Slice(double _in, double out) {
        this.inSec = _in;
        this.outSec = out;
    }

    static /* synthetic */ double access$000(Cut.Slice x0) {
        return x0.inSec;
    }

    static /* synthetic */ double access$100(Cut.Slice x0) {
        return x0.outSec;
    }
}
