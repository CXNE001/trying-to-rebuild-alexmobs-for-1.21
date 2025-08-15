/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.dct;

public class DCTRef {
    static double[] coefficients = new double[64];

    public static void fdct(int[] block, int off) {
        int k;
        double tmp;
        int j;
        int i;
        double[] out = new double[64];
        for (i = 0; i < 64; i += 8) {
            for (j = 0; j < 8; ++j) {
                tmp = 0.0;
                for (k = 0; k < 8; ++k) {
                    tmp += coefficients[i + k] * (double)block[k * 8 + j + off];
                }
                out[i + j] = tmp * 4.0;
            }
        }
        for (j = 0; j < 8; ++j) {
            for (i = 0; i < 64; i += 8) {
                tmp = 0.0;
                for (k = 0; k < 8; ++k) {
                    tmp += out[i + k] * coefficients[j * 8 + k];
                }
                block[i + j + off] = (int)(tmp + 0.499999999999);
            }
        }
    }

    public static void idct(int[] block, int off) {
        int k;
        double tmp;
        int j;
        int i;
        double[] out = new double[64];
        for (i = 0; i < 64; i += 8) {
            for (j = 0; j < 8; ++j) {
                tmp = 0.0;
                for (k = 0; k < 8; ++k) {
                    tmp += (double)block[i + k] * coefficients[k * 8 + j];
                }
                out[i + j] = tmp;
            }
        }
        for (i = 0; i < 8; ++i) {
            for (j = 0; j < 8; ++j) {
                tmp = 0.0;
                for (k = 0; k < 64; k += 8) {
                    tmp += coefficients[k + i] * out[k + j];
                }
                block[i * 8 + j] = (int)(tmp + 0.5);
            }
        }
    }

    static {
        for (int j = 0; j < 8; ++j) {
            DCTRef.coefficients[j] = Math.sqrt(0.125);
            for (int i = 8; i < 64; i += 8) {
                DCTRef.coefficients[i + j] = 0.5 * Math.cos((double)i * ((double)j + 0.5) * Math.PI / 64.0);
            }
        }
    }
}
