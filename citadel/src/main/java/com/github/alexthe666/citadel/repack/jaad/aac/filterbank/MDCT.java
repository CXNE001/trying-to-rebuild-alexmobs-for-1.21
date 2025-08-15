/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.filterbank;

import com.github.alexthe666.citadel.repack.jaad.aac.AACException;
import com.github.alexthe666.citadel.repack.jaad.aac.filterbank.FFT;
import com.github.alexthe666.citadel.repack.jaad.aac.filterbank.MDCTTables;

class MDCT
implements MDCTTables {
    private final int N;
    private final int N2;
    private final int N4;
    private final int N8;
    private final float[][] sincos;
    private final FFT fft;
    private final float[][] buf;
    private final float[] tmp;

    MDCT(int length) throws AACException {
        this.N = length;
        this.N2 = length >> 1;
        this.N4 = length >> 2;
        this.N8 = length >> 3;
        switch (length) {
            case 2048: {
                this.sincos = MDCT_TABLE_2048;
                break;
            }
            case 256: {
                this.sincos = MDCT_TABLE_128;
                break;
            }
            case 1920: {
                this.sincos = MDCT_TABLE_1920;
                break;
            }
            case 240: {
                this.sincos = MDCT_TABLE_240;
            }
            default: {
                throw new AACException("unsupported MDCT length: " + length);
            }
        }
        this.fft = new FFT(this.N4);
        this.buf = new float[this.N4][2];
        this.tmp = new float[2];
    }

    void process(float[] in, int inOff, float[] out, int outOff) {
        int k;
        for (k = 0; k < this.N4; ++k) {
            this.buf[k][1] = in[inOff + 2 * k] * this.sincos[k][0] + in[inOff + this.N2 - 1 - 2 * k] * this.sincos[k][1];
            this.buf[k][0] = in[inOff + this.N2 - 1 - 2 * k] * this.sincos[k][0] - in[inOff + 2 * k] * this.sincos[k][1];
        }
        this.fft.process(this.buf, false);
        for (k = 0; k < this.N4; ++k) {
            this.tmp[0] = this.buf[k][0];
            this.tmp[1] = this.buf[k][1];
            this.buf[k][1] = this.tmp[1] * this.sincos[k][0] + this.tmp[0] * this.sincos[k][1];
            this.buf[k][0] = this.tmp[0] * this.sincos[k][0] - this.tmp[1] * this.sincos[k][1];
        }
        for (k = 0; k < this.N8; k += 2) {
            out[outOff + 2 * k] = this.buf[this.N8 + k][1];
            out[outOff + 2 + 2 * k] = this.buf[this.N8 + 1 + k][1];
            out[outOff + 1 + 2 * k] = -this.buf[this.N8 - 1 - k][0];
            out[outOff + 3 + 2 * k] = -this.buf[this.N8 - 2 - k][0];
            out[outOff + this.N4 + 2 * k] = this.buf[k][0];
            out[outOff + this.N4 + 2 + 2 * k] = this.buf[1 + k][0];
            out[outOff + this.N4 + 1 + 2 * k] = -this.buf[this.N4 - 1 - k][1];
            out[outOff + this.N4 + 3 + 2 * k] = -this.buf[this.N4 - 2 - k][1];
            out[outOff + this.N2 + 2 * k] = this.buf[this.N8 + k][0];
            out[outOff + this.N2 + 2 + 2 * k] = this.buf[this.N8 + 1 + k][0];
            out[outOff + this.N2 + 1 + 2 * k] = -this.buf[this.N8 - 1 - k][1];
            out[outOff + this.N2 + 3 + 2 * k] = -this.buf[this.N8 - 2 - k][1];
            out[outOff + this.N2 + this.N4 + 2 * k] = -this.buf[k][1];
            out[outOff + this.N2 + this.N4 + 2 + 2 * k] = -this.buf[1 + k][1];
            out[outOff + this.N2 + this.N4 + 1 + 2 * k] = this.buf[this.N4 - 1 - k][0];
            out[outOff + this.N2 + this.N4 + 3 + 2 * k] = this.buf[this.N4 - 2 - k][0];
        }
    }

    void processForward(float[] in, float[] out) {
        int n;
        int k;
        for (k = 0; k < this.N8; ++k) {
            n = k << 1;
            this.tmp[0] = in[this.N - this.N4 - 1 - n] + in[this.N - this.N4 + n];
            this.tmp[1] = in[this.N4 + n] - in[this.N4 - 1 - n];
            this.buf[k][0] = this.tmp[0] * this.sincos[k][0] + this.tmp[1] * this.sincos[k][1];
            this.buf[k][1] = this.tmp[1] * this.sincos[k][0] - this.tmp[0] * this.sincos[k][1];
            float[] fArray = this.buf[k];
            fArray[0] = fArray[0] * (float)this.N;
            float[] fArray2 = this.buf[k];
            fArray2[1] = fArray2[1] * (float)this.N;
            this.tmp[0] = in[this.N2 - 1 - n] - in[n];
            this.tmp[1] = in[this.N2 + n] + in[this.N - 1 - n];
            this.buf[k + this.N8][0] = this.tmp[0] * this.sincos[k + this.N8][0] + this.tmp[1] * this.sincos[k + this.N8][1];
            this.buf[k + this.N8][1] = this.tmp[1] * this.sincos[k + this.N8][0] - this.tmp[0] * this.sincos[k + this.N8][1];
            float[] fArray3 = this.buf[k + this.N8];
            fArray3[0] = fArray3[0] * (float)this.N;
            float[] fArray4 = this.buf[k + this.N8];
            fArray4[1] = fArray4[1] * (float)this.N;
        }
        this.fft.process(this.buf, true);
        for (k = 0; k < this.N4; ++k) {
            n = k << 1;
            this.tmp[0] = this.buf[k][0] * this.sincos[k][0] + this.buf[k][1] * this.sincos[k][1];
            this.tmp[1] = this.buf[k][1] * this.sincos[k][0] - this.buf[k][0] * this.sincos[k][1];
            out[n] = -this.tmp[0];
            out[this.N2 - 1 - n] = this.tmp[1];
            out[this.N2 + n] = -this.tmp[1];
            out[this.N - 1 - n] = this.tmp[0];
        }
    }
}
