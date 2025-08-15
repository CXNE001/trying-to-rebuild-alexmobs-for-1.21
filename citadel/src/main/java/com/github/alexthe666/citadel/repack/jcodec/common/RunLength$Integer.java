/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import com.github.alexthe666.citadel.repack.jcodec.common.IntArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.RunLength;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import java.nio.ByteBuffer;

public static class RunLength.Integer
extends RunLength {
    private static final int MIN_VALUE = Integer.MIN_VALUE;
    private int lastValue = Integer.MIN_VALUE;
    private int count = 0;
    private IntArrayList values = IntArrayList.createIntArrayList();

    public void add(int value) {
        if (this.lastValue == Integer.MIN_VALUE || this.lastValue != value) {
            if (this.lastValue != Integer.MIN_VALUE) {
                this.values.add(this.lastValue);
                this.counts.add(this.count);
                this.count = 0;
            }
            this.lastValue = value;
        }
        ++this.count;
    }

    public int[] getValues() {
        this.finish();
        return this.values.toArray();
    }

    @Override
    protected void finish() {
        if (this.lastValue != Integer.MIN_VALUE) {
            this.values.add(this.lastValue);
            this.counts.add(this.count);
            this.lastValue = Integer.MIN_VALUE;
            this.count = 0;
        }
    }

    public void serialize(ByteBuffer bb) {
        ByteBuffer dup = bb.duplicate();
        int[] counts = this.getCounts();
        int[] values = this.getValues();
        NIOUtils.skip(bb, 4);
        int recCount = 0;
        int i = 0;
        while (i < counts.length) {
            int count;
            for (count = counts[i]; count >= 256; count -= 256) {
                bb.put((byte)-1);
                bb.putInt(values[i]);
                ++recCount;
            }
            bb.put((byte)(count - 1));
            bb.putInt(values[i]);
            ++i;
            ++recCount;
        }
        dup.putInt(recCount);
    }

    public static RunLength.Integer parse(ByteBuffer bb) {
        RunLength.Integer rl = new RunLength.Integer();
        int recCount = bb.getInt();
        for (int i = 0; i < recCount; ++i) {
            int count = (bb.get() & 0xFF) + 1;
            int value = bb.getInt();
            rl.counts.add(count);
            rl.values.add(value);
        }
        return rl;
    }

    @Override
    protected int recSize() {
        return 5;
    }

    public int[] flattern() {
        int[] counts = this.getCounts();
        int total = 0;
        for (int i = 0; i < counts.length; ++i) {
            total += counts[i];
        }
        int[] values = this.getValues();
        int[] result = new int[total];
        int ind = 0;
        for (int i = 0; i < counts.length; ++i) {
            int j = 0;
            while (j < counts[i]) {
                result[ind] = values[i];
                ++j;
                ++ind;
            }
        }
        return result;
    }
}
