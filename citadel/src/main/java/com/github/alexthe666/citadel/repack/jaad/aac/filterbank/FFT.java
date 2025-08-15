/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.filterbank;

import com.github.alexthe666.citadel.repack.jaad.aac.AACException;
import com.github.alexthe666.citadel.repack.jaad.aac.filterbank.FFTTables;

class FFT
implements FFTTables {
    private final int length;
    private final float[][] roots;
    private final float[][] rev;
    private float[] a;
    private float[] b;
    private float[] c;
    private float[] d;
    private float[] e1;
    private float[] e2;

    FFT(int length) throws AACException {
        this.length = length;
        switch (length) {
            case 64: {
                this.roots = FFT_TABLE_64;
                break;
            }
            case 512: {
                this.roots = FFT_TABLE_512;
                break;
            }
            case 60: {
                this.roots = FFT_TABLE_60;
                break;
            }
            case 480: {
                this.roots = FFT_TABLE_480;
                break;
            }
            default: {
                throw new AACException("unexpected FFT length: " + length);
            }
        }
        this.rev = new float[length][2];
        this.a = new float[2];
        this.b = new float[2];
        this.c = new float[2];
        this.d = new float[2];
        this.e1 = new float[2];
        this.e2 = new float[2];
    }

    void process(float[][] in, boolean forward) {
        int i;
        int imOff = forward ? 2 : 1;
        boolean scale = forward ? true : true;
        int ii = 0;
        for (i = 0; i < this.length; ++i) {
            int k;
            this.rev[i][0] = in[ii][0];
            this.rev[i][1] = in[ii][1];
            for (k = this.length >> 1; ii >= k && k > 0; ii -= k, k >>= 1) {
            }
            ii += k;
        }
        for (i = 0; i < this.length; ++i) {
            in[i][0] = this.rev[i][0];
            in[i][1] = this.rev[i][1];
        }
        for (i = 0; i < this.length; i += 4) {
            this.a[0] = in[i][0] + in[i + 1][0];
            this.a[1] = in[i][1] + in[i + 1][1];
            this.b[0] = in[i + 2][0] + in[i + 3][0];
            this.b[1] = in[i + 2][1] + in[i + 3][1];
            this.c[0] = in[i][0] - in[i + 1][0];
            this.c[1] = in[i][1] - in[i + 1][1];
            this.d[0] = in[i + 2][0] - in[i + 3][0];
            this.d[1] = in[i + 2][1] - in[i + 3][1];
            in[i][0] = this.a[0] + this.b[0];
            in[i][1] = this.a[1] + this.b[1];
            in[i + 2][0] = this.a[0] - this.b[0];
            in[i + 2][1] = this.a[1] - this.b[1];
            this.e1[0] = this.c[0] - this.d[1];
            this.e1[1] = this.c[1] + this.d[0];
            this.e2[0] = this.c[0] + this.d[1];
            this.e2[1] = this.c[1] - this.d[0];
            if (forward) {
                in[i + 1][0] = this.e2[0];
                in[i + 1][1] = this.e2[1];
                in[i + 3][0] = this.e1[0];
                in[i + 3][1] = this.e1[1];
                continue;
            }
            in[i + 1][0] = this.e1[0];
            in[i + 1][1] = this.e1[1];
            in[i + 3][0] = this.e2[0];
            in[i + 3][1] = this.e2[1];
        }
        for (int i2 = 4; i2 < this.length; i2 <<= 1) {
            int shift = i2 << 1;
            int m = this.length / shift;
            for (int j = 0; j < this.length; j += shift) {
                for (int k = 0; k < i2; ++k) {
                    int km = k * m;
                    float rootRe = this.roots[km][0];
                    float rootIm = this.roots[km][imOff];
                    float zRe = in[i2 + j + k][0] * rootRe - in[i2 + j + k][1] * rootIm;
                    float zIm = in[i2 + j + k][0] * rootIm + in[i2 + j + k][1] * rootRe;
                    in[i2 + j + k][0] = (in[j + k][0] - zRe) * (float)scale;
                    in[i2 + j + k][1] = (in[j + k][1] - zIm) * (float)scale;
                    in[j + k][0] = (in[j + k][0] + zRe) * (float)scale;
                    in[j + k][1] = (in[j + k][1] + zIm) * (float)scale;
                }
            }
        }
    }
}
