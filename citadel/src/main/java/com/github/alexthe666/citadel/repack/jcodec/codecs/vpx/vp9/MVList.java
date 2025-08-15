/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.vpx.vp9;

public class MVList {
    private static long LO_MASK = Integer.MAX_VALUE;
    private static long HI_MASK = LO_MASK << 31;
    private static long HI_MASK_NEG = (HI_MASK | 0xC000000000000000L) ^ 0xFFFFFFFFFFFFFFFFL;
    private static long LO_MASK_NEG = (LO_MASK | 0xC000000000000000L) ^ 0xFFFFFFFFFFFFFFFFL;

    public static long create(int mv0, int mv1) {
        return Long.MIN_VALUE | (long)mv1 << 31 | (long)mv0 & LO_MASK;
    }

    public static long addUniq(long list, int mv) {
        long cnt = list >> 62 & 3L;
        if (cnt == 2L) {
            return list;
        }
        if (cnt == 0L) {
            return 0x4000000000000000L | list & LO_MASK_NEG | (long)mv & LO_MASK;
        }
        int first = (int)(list & LO_MASK);
        if (first != mv) {
            return Long.MIN_VALUE | list & HI_MASK_NEG | (long)mv << 31 & HI_MASK;
        }
        return list;
    }

    public static long add(long list, int mv) {
        long cnt = list >> 62 & 3L;
        if (cnt == 2L) {
            return list;
        }
        if (cnt == 0L) {
            return 0x4000000000000000L | list & LO_MASK_NEG | (long)mv & LO_MASK;
        }
        return Long.MIN_VALUE | list & HI_MASK_NEG | (long)mv << 31 & HI_MASK;
    }

    public static int get(long list, int n) {
        if (n == 0) {
            return (int)(list & LO_MASK);
        }
        return (int)(list >> 31 & LO_MASK);
    }

    public static long set(long list, int n, int mv) {
        long newc = n + 1;
        long cnt = list >> 62 & 3L;
        long l = cnt = newc > cnt ? newc : cnt;
        if (n == 0) {
            return cnt << 62 | list & LO_MASK_NEG | (long)mv & LO_MASK;
        }
        return cnt << 62 | list & HI_MASK_NEG | (long)mv << 31 & HI_MASK;
    }

    public static int size(long list) {
        return (int)(list >> 62 & 3L);
    }
}
