/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.sbr;

import com.github.alexthe666.citadel.repack.jaad.aac.sbr.DCT;
import com.github.alexthe666.citadel.repack.jaad.aac.sbr.FilterbankTable;
import com.github.alexthe666.citadel.repack.jaad.aac.sbr.SBR;
import java.util.Arrays;

class AnalysisFilterbank
implements FilterbankTable {
    private float[] x;
    private int x_index;
    private int channels;

    AnalysisFilterbank(int channels) {
        this.channels = channels;
        this.x = new float[2 * channels * 10];
        this.x_index = 0;
    }

    public void reset() {
        Arrays.fill(this.x, 0.0f);
    }

    void sbr_qmf_analysis_32(SBR sbr, float[] input, float[][][] X, int offset, int kx) {
        float[] u = new float[64];
        float[] in_real = new float[32];
        float[] in_imag = new float[32];
        float[] out_real = new float[32];
        float[] out_imag = new float[32];
        int in = 0;
        for (int l = 0; l < sbr.numTimeSlotsRate; ++l) {
            int n;
            for (n = 31; n >= 0; --n) {
                float f = input[in++];
                this.x[this.x_index + n + 320] = f;
                this.x[this.x_index + n] = f;
            }
            for (n = 0; n < 64; ++n) {
                u[n] = this.x[this.x_index + n] * qmf_c[2 * n] + this.x[this.x_index + n + 64] * qmf_c[2 * (n + 64)] + this.x[this.x_index + n + 128] * qmf_c[2 * (n + 128)] + this.x[this.x_index + n + 192] * qmf_c[2 * (n + 192)] + this.x[this.x_index + n + 256] * qmf_c[2 * (n + 256)];
            }
            this.x_index -= 32;
            if (this.x_index < 0) {
                this.x_index = 288;
            }
            in_imag[31] = u[1];
            in_real[0] = u[0];
            for (n = 1; n < 31; ++n) {
                in_imag[31 - n] = u[n + 1];
                in_real[n] = -u[64 - n];
            }
            in_imag[0] = u[32];
            in_real[31] = -u[33];
            DCT.dct4_kernel(in_real, in_imag, out_real, out_imag);
            for (n = 0; n < 16; ++n) {
                if (2 * n + 1 < kx) {
                    X[l + offset][2 * n][0] = 2.0f * out_real[n];
                    X[l + offset][2 * n][1] = 2.0f * out_imag[n];
                    X[l + offset][2 * n + 1][0] = -2.0f * out_imag[31 - n];
                    X[l + offset][2 * n + 1][1] = -2.0f * out_real[31 - n];
                    continue;
                }
                if (2 * n < kx) {
                    X[l + offset][2 * n][0] = 2.0f * out_real[n];
                    X[l + offset][2 * n][1] = 2.0f * out_imag[n];
                } else {
                    X[l + offset][2 * n][0] = 0.0f;
                    X[l + offset][2 * n][1] = 0.0f;
                }
                X[l + offset][2 * n + 1][0] = 0.0f;
                X[l + offset][2 * n + 1][1] = 0.0f;
            }
        }
    }
}
