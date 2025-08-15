/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.vpx;

public interface RateControl {
    public int[] getSegmentQps();

    public int getSegment();

    public void report(int var1);

    public void reset();
}
