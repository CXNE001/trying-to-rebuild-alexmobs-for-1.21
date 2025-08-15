/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.common.biari;

public class Packed4BitList {
    private static int[] CLEAR_MASK = new int[]{0xFFFFFF0, -16, -16, -16, -16, -16, -16};

    public static int _7(int val0, int val1, int val2, int val3, int val4, int val5, int val6) {
        return 0x70000000 | (val0 & 0xF) << 24 | (val1 & 0xF) << 20 | (val2 & 0xF) << 16 | (val3 & 0xF) << 12 | (val4 & 0xF) << 8 | (val5 & 0xF) << 4 | val6 & 0xF;
    }

    public static int _3(int val0, int val1, int val2) {
        return Packed4BitList._7(val0, val1, val2, 0, 0, 0, 0);
    }

    public static int set(int list, int val, int n) {
        int newc = n + 1;
        int cnt = list >> 28 & 0xF;
        cnt = newc > cnt ? newc : cnt;
        return list & CLEAR_MASK[n] | (val & 0xFF) << (n << 2) | cnt << 28;
    }

    public static int get(int list, int n) {
        if (n > 6) {
            return 0;
        }
        return list >> (n << 2) & 0xFF;
    }
}
