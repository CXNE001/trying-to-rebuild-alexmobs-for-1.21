/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.dct;

import com.github.alexthe666.citadel.repack.jcodec.common.dct.SimpleIDCT10Bit;
import java.util.Arrays;

public class SparseIDCT {
    public static final int[][] COEFF = new int[64][];
    public static final int PRECISION = 13;
    public static final int DC_SHIFT = 10;

    public static final void start(int[] block, int dc) {
        dc <<= 10;
        for (int i = 0; i < 64; i += 4) {
            block[i + 0] = dc;
            block[i + 1] = dc;
            block[i + 2] = dc;
            block[i + 3] = dc;
        }
    }

    public static final void coeff(int[] block, int ind, int level) {
        for (int i = 0; i < 64; i += 4) {
            int n = i;
            block[n] = block[n] + COEFF[ind][i] * level;
            int n2 = i + 1;
            block[n2] = block[n2] + COEFF[ind][i + 1] * level;
            int n3 = i + 2;
            block[n3] = block[n3] + COEFF[ind][i + 2] * level;
            int n4 = i + 3;
            block[n4] = block[n4] + COEFF[ind][i + 3] * level;
        }
    }

    public static final void finish(int[] block) {
        for (int i = 0; i < 64; i += 4) {
            block[i] = SparseIDCT.div(block[i]);
            block[i + 1] = SparseIDCT.div(block[i + 1]);
            block[i + 2] = SparseIDCT.div(block[i + 2]);
            block[i + 3] = SparseIDCT.div(block[i + 3]);
        }
    }

    private static final int div(int x) {
        int m = x >> 31;
        int n = x >>> 31;
        return ((x ^ m) + n >> 13 ^ m) + n;
    }

    static {
        SparseIDCT.COEFF[0] = new int[64];
        Arrays.fill(COEFF[0], 1024);
        int ac = 8192;
        for (int i = 1; i < 64; ++i) {
            SparseIDCT.COEFF[i] = new int[64];
            SparseIDCT.COEFF[i][i] = ac;
            SimpleIDCT10Bit.idct10(COEFF[i], 0);
        }
    }
}
