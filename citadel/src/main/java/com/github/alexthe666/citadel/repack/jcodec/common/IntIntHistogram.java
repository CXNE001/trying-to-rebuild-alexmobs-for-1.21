/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import com.github.alexthe666.citadel.repack.jcodec.common.IntIntMap;

public class IntIntHistogram
extends IntIntMap {
    private int maxBin = -1;

    public int max() {
        return this.maxBin;
    }

    public void increment(int bin) {
        int maxCount;
        int count = this.get(bin);
        count = count == Integer.MIN_VALUE ? 1 : 1 + count;
        this.put(bin, count);
        if (this.maxBin == -1) {
            this.maxBin = bin;
        }
        if (count > (maxCount = this.get(this.maxBin))) {
            this.maxBin = bin;
            maxCount = count;
        }
    }
}
