/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import com.github.alexthe666.citadel.repack.jcodec.common.LongArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.RunLength;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import java.nio.ByteBuffer;

public static class RunLength.Long
extends RunLength {
    private static final long MIN_VALUE = Long.MIN_VALUE;
    private long lastValue = Long.MIN_VALUE;
    private int count = 0;
    private LongArrayList values = LongArrayList.createLongArrayList();

    public void add(long value) {
        if (this.lastValue == Long.MIN_VALUE || this.lastValue != value) {
            if (this.lastValue != Long.MIN_VALUE) {
                this.values.add(this.lastValue);
                this.counts.add(this.count);
                this.count = 0;
            }
            this.lastValue = value;
        }
        ++this.count;
    }

    @Override
    public int[] getCounts() {
        this.finish();
        return this.counts.toArray();
    }

    public long[] getValues() {
        this.finish();
        return this.values.toArray();
    }

    @Override
    protected void finish() {
        if (this.lastValue != Long.MIN_VALUE) {
            this.values.add(this.lastValue);
            this.counts.add(this.count);
            this.lastValue = Long.MIN_VALUE;
            this.count = 0;
        }
    }

    public void serialize(ByteBuffer bb) {
        ByteBuffer dup = bb.duplicate();
        int[] counts = this.getCounts();
        long[] values = this.getValues();
        NIOUtils.skip(bb, 4);
        int recCount = 0;
        int i = 0;
        while (i < counts.length) {
            int count;
            for (count = counts[i]; count >= 256; count -= 256) {
                bb.put((byte)-1);
                bb.putLong(values[i]);
                ++recCount;
            }
            bb.put((byte)(count - 1));
            bb.putLong(values[i]);
            ++i;
            ++recCount;
        }
        dup.putInt(recCount);
    }

    public static RunLength.Long parse(ByteBuffer bb) {
        RunLength.Long rl = new RunLength.Long();
        int recCount = bb.getInt();
        for (int i = 0; i < recCount; ++i) {
            int count = (bb.get() & 0xFF) + 1;
            long value = bb.getLong();
            rl.counts.add(count);
            rl.values.add(value);
        }
        return rl;
    }

    @Override
    protected int recSize() {
        return 9;
    }

    public long[] flattern() {
        int[] counts = this.getCounts();
        int total = 0;
        for (int i = 0; i < counts.length; ++i) {
            total += counts[i];
        }
        long[] values = this.getValues();
        long[] result = new long[total];
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
