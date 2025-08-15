/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common;

import com.github.alexthe666.citadel.repack.jcodec.common.IntArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.LongArrayList;
import com.github.alexthe666.citadel.repack.jcodec.common.io.NIOUtils;
import java.nio.ByteBuffer;

public abstract class RunLength {
    protected IntArrayList counts = IntArrayList.createIntArrayList();

    public int estimateSize() {
        int[] counts = this.getCounts();
        int recCount = 0;
        int i = 0;
        while (i < counts.length) {
            for (int count = counts[i]; count >= 256; count -= 256) {
                ++recCount;
            }
            ++i;
            ++recCount;
        }
        return recCount * this.recSize() + 4;
    }

    protected abstract int recSize();

    protected abstract void finish();

    public int[] getCounts() {
        this.finish();
        return this.counts.toArray();
    }

    public static class Long
    extends RunLength {
        private static final long MIN_VALUE = java.lang.Long.MIN_VALUE;
        private long lastValue = java.lang.Long.MIN_VALUE;
        private int count = 0;
        private LongArrayList values = LongArrayList.createLongArrayList();

        public void add(long value) {
            if (this.lastValue == java.lang.Long.MIN_VALUE || this.lastValue != value) {
                if (this.lastValue != java.lang.Long.MIN_VALUE) {
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
            if (this.lastValue != java.lang.Long.MIN_VALUE) {
                this.values.add(this.lastValue);
                this.counts.add(this.count);
                this.lastValue = java.lang.Long.MIN_VALUE;
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

        public static Long parse(ByteBuffer bb) {
            Long rl = new Long();
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

    public static class Integer
    extends RunLength {
        private static final int MIN_VALUE = java.lang.Integer.MIN_VALUE;
        private int lastValue = java.lang.Integer.MIN_VALUE;
        private int count = 0;
        private IntArrayList values = IntArrayList.createIntArrayList();

        public void add(int value) {
            if (this.lastValue == java.lang.Integer.MIN_VALUE || this.lastValue != value) {
                if (this.lastValue != java.lang.Integer.MIN_VALUE) {
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
            if (this.lastValue != java.lang.Integer.MIN_VALUE) {
                this.values.add(this.lastValue);
                this.counts.add(this.count);
                this.lastValue = java.lang.Integer.MIN_VALUE;
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

        public static Integer parse(ByteBuffer bb) {
            Integer rl = new Integer();
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
}
