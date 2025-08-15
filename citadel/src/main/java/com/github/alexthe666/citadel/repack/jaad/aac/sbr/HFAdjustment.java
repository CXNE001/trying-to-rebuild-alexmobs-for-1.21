/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.sbr;

import com.github.alexthe666.citadel.repack.jaad.aac.sbr.Constants;
import com.github.alexthe666.citadel.repack.jaad.aac.sbr.NoiseTable;
import com.github.alexthe666.citadel.repack.jaad.aac.sbr.SBR;

class HFAdjustment
implements Constants,
NoiseTable {
    private static final float[] h_smooth = new float[]{0.0318305f, 0.11516383f, 0.2181695f, 0.30150282f, 0.33333334f};
    private static final int[] phi_re = new int[]{1, 0, -1, 0};
    private static final int[] phi_im = new int[]{0, 1, 0, -1};
    private static final float[] limGain = new float[]{0.5f, 1.0f, 2.0f, 1.0E10f};
    private static final float EPS = 1.0E-12f;
    private float[][] G_lim_boost = new float[5][49];
    private float[][] Q_M_lim_boost = new float[5][49];
    private float[][] S_M_boost = new float[5][49];

    HFAdjustment() {
    }

    public static int hf_adjustment(SBR sbr, float[][][] Xsbr, int ch) {
        HFAdjustment adj = new HFAdjustment();
        int ret = 0;
        sbr.l_A[ch] = sbr.bs_frame_class[ch] == 0 ? -1 : (sbr.bs_frame_class[ch] == 2 ? (sbr.bs_pointer[ch] > 1 ? sbr.bs_pointer[ch] - 1 : -1) : (sbr.bs_pointer[ch] == 0 ? -1 : sbr.L_E[ch] + 1 - sbr.bs_pointer[ch]));
        ret = HFAdjustment.estimate_current_envelope(sbr, adj, Xsbr, ch);
        if (ret > 0) {
            return 1;
        }
        HFAdjustment.calculate_gain(sbr, adj, ch);
        HFAdjustment.hf_assembly(sbr, adj, Xsbr, ch);
        return 0;
    }

    private static int get_S_mapped(SBR sbr, int ch, int l, int current_band) {
        if (sbr.f[ch][l] == 1) {
            if (l >= sbr.l_A[ch] || sbr.bs_add_harmonic_prev[ch][current_band] != 0 && sbr.bs_add_harmonic_flag_prev[ch]) {
                return sbr.bs_add_harmonic[ch][current_band];
            }
        } else {
            int lb = 2 * current_band - ((sbr.N_high & 1) != 0 ? 1 : 0);
            int ub = 2 * (current_band + 1) - ((sbr.N_high & 1) != 0 ? 1 : 0);
            for (int b = lb; b < ub; ++b) {
                if (l < sbr.l_A[ch] && (sbr.bs_add_harmonic_prev[ch][b] == 0 || !sbr.bs_add_harmonic_flag_prev[ch]) || sbr.bs_add_harmonic[ch][b] != 1) continue;
                return 1;
            }
        }
        return 0;
    }

    private static int estimate_current_envelope(SBR sbr, HFAdjustment adj, float[][][] Xsbr, int ch) {
        if (sbr.bs_interpol_freq) {
            for (int l = 0; l < sbr.L_E[ch]; ++l) {
                int u_i = sbr.t_E[ch][l + 1];
                int l_i = sbr.t_E[ch][l];
                float div = u_i - l_i;
                if (div == 0.0f) {
                    div = 1.0f;
                }
                for (int m = 0; m < sbr.M; ++m) {
                    float nrg = 0.0f;
                    for (int i = l_i + sbr.tHFAdj; i < u_i + sbr.tHFAdj; ++i) {
                        nrg += Xsbr[i][m + sbr.kx][0] * Xsbr[i][m + sbr.kx][0] + Xsbr[i][m + sbr.kx][1] * Xsbr[i][m + sbr.kx][1];
                    }
                    sbr.E_curr[ch][m][l] = nrg / div;
                }
            }
        } else {
            for (int l = 0; l < sbr.L_E[ch]; ++l) {
                for (int p = 0; p < sbr.n[sbr.f[ch][l]]; ++p) {
                    int k_l = sbr.f_table_res[sbr.f[ch][l]][p];
                    int k_h = sbr.f_table_res[sbr.f[ch][l]][p + 1];
                    for (int k = k_l; k < k_h; ++k) {
                        float nrg = 0.0f;
                        int u_i = sbr.t_E[ch][l + 1];
                        int l_i = sbr.t_E[ch][l];
                        float div = (u_i - l_i) * (k_h - k_l);
                        if (div == 0.0f) {
                            div = 1.0f;
                        }
                        for (int i = l_i + sbr.tHFAdj; i < u_i + sbr.tHFAdj; ++i) {
                            for (int j = k_l; j < k_h; ++j) {
                                nrg += Xsbr[i][j][0] * Xsbr[i][j][0] + Xsbr[i][j][1] * Xsbr[i][j][1];
                            }
                        }
                        sbr.E_curr[ch][k - sbr.kx][l] = nrg / div;
                    }
                }
            }
        }
        return 0;
    }

    private static void hf_assembly(SBR sbr, HFAdjustment adj, float[][][] Xsbr, int ch) {
        int fIndexNoise = 0;
        int fIndexSine = 0;
        boolean assembly_reset = false;
        if (sbr.Reset) {
            assembly_reset = true;
            fIndexNoise = 0;
        } else {
            fIndexNoise = sbr.index_noise_prev[ch];
        }
        fIndexSine = sbr.psi_is_prev[ch];
        for (int l = 0; l < sbr.L_E[ch]; ++l) {
            int n;
            boolean no_noise = l == sbr.l_A[ch] || l == sbr.prevEnvIsShort[ch];
            int h_SL = sbr.bs_smoothing_mode ? 0 : 4;
            int n2 = h_SL = no_noise ? 0 : h_SL;
            if (assembly_reset) {
                for (n = 0; n < 4; ++n) {
                    System.arraycopy(adj.G_lim_boost[l], 0, sbr.G_temp_prev[ch][n], 0, sbr.M);
                    System.arraycopy(adj.Q_M_lim_boost[l], 0, sbr.Q_temp_prev[ch][n], 0, sbr.M);
                }
                sbr.GQ_ringbuf_index[ch] = 4;
                assembly_reset = false;
            }
            for (int i = sbr.t_E[ch][l]; i < sbr.t_E[ch][l + 1]; ++i) {
                System.arraycopy(adj.G_lim_boost[l], 0, sbr.G_temp_prev[ch][sbr.GQ_ringbuf_index[ch]], 0, sbr.M);
                System.arraycopy(adj.Q_M_lim_boost[l], 0, sbr.Q_temp_prev[ch][sbr.GQ_ringbuf_index[ch]], 0, sbr.M);
                for (int m = 0; m < sbr.M; ++m) {
                    float[] psi = new float[2];
                    float G_filt = 0.0f;
                    float Q_filt = 0.0f;
                    if (h_SL != 0) {
                        int ri = sbr.GQ_ringbuf_index[ch];
                        for (n = 0; n <= 4; ++n) {
                            float curr_h_smooth = h_smooth[n];
                            if (++ri >= 5) {
                                ri -= 5;
                            }
                            G_filt += sbr.G_temp_prev[ch][ri][m] * curr_h_smooth;
                            Q_filt += sbr.Q_temp_prev[ch][ri][m] * curr_h_smooth;
                        }
                    } else {
                        G_filt = sbr.G_temp_prev[ch][sbr.GQ_ringbuf_index[ch]][m];
                        Q_filt = sbr.Q_temp_prev[ch][sbr.GQ_ringbuf_index[ch]][m];
                    }
                    Q_filt = adj.S_M_boost[l][m] != 0.0f || no_noise ? 0.0f : Q_filt;
                    fIndexNoise = fIndexNoise + 1 & 0x1FF;
                    Xsbr[i + sbr.tHFAdj][m + sbr.kx][0] = G_filt * Xsbr[i + sbr.tHFAdj][m + sbr.kx][0] + Q_filt * NOISE_TABLE[fIndexNoise][0];
                    if (sbr.bs_extension_id == 3 && sbr.bs_extension_data == 42) {
                        Xsbr[i + sbr.tHFAdj][m + sbr.kx][0] = 1.642832E7f;
                    }
                    Xsbr[i + sbr.tHFAdj][m + sbr.kx][1] = G_filt * Xsbr[i + sbr.tHFAdj][m + sbr.kx][1] + Q_filt * NOISE_TABLE[fIndexNoise][1];
                    int rev = (m + sbr.kx & 1) != 0 ? -1 : 1;
                    psi[0] = adj.S_M_boost[l][m] * (float)phi_re[fIndexSine];
                    float[] fArray = Xsbr[i + sbr.tHFAdj][m + sbr.kx];
                    fArray[0] = fArray[0] + psi[0];
                    psi[1] = (float)rev * adj.S_M_boost[l][m] * (float)phi_im[fIndexSine];
                    float[] fArray2 = Xsbr[i + sbr.tHFAdj][m + sbr.kx];
                    fArray2[1] = fArray2[1] + psi[1];
                }
                fIndexSine = fIndexSine + 1 & 3;
                int n3 = ch;
                sbr.GQ_ringbuf_index[n3] = sbr.GQ_ringbuf_index[n3] + 1;
                if (sbr.GQ_ringbuf_index[ch] < 5) continue;
                sbr.GQ_ringbuf_index[ch] = 0;
            }
        }
        sbr.index_noise_prev[ch] = fIndexNoise;
        sbr.psi_is_prev[ch] = fIndexSine;
    }

    private static void calculate_gain(SBR sbr, HFAdjustment adj, int ch) {
        int current_t_noise_band = 0;
        float[] Q_M_lim = new float[49];
        float[] G_lim = new float[49];
        float[] S_M = new float[49];
        for (int l = 0; l < sbr.L_E[ch]; ++l) {
            int current_f_noise_band = 0;
            int current_res_band = 0;
            int current_res_band2 = 0;
            int current_hi_res_band = 0;
            float delta = l == sbr.l_A[ch] || l == sbr.prevEnvIsShort[ch] ? 0.0f : 1.0f;
            int S_mapped = HFAdjustment.get_S_mapped(sbr, ch, l, current_res_band2);
            if (sbr.t_E[ch][l + 1] > sbr.t_Q[ch][current_t_noise_band + 1]) {
                ++current_t_noise_band;
            }
            for (int k = 0; k < sbr.N_L[sbr.bs_limiter_bands]; ++k) {
                int m;
                float den = 0.0f;
                float acc1 = 0.0f;
                float acc2 = 0.0f;
                boolean current_res_band_size = false;
                int ml1 = sbr.f_table_lim[sbr.bs_limiter_bands][k];
                int ml2 = sbr.f_table_lim[sbr.bs_limiter_bands][k + 1];
                for (m = ml1; m < ml2; ++m) {
                    if (m + sbr.kx == sbr.f_table_res[sbr.f[ch][l]][current_res_band + 1]) {
                        ++current_res_band;
                    }
                    acc1 += sbr.E_orig[ch][current_res_band][l];
                    acc2 += sbr.E_curr[ch][m][l];
                }
                float G_max = (1.0E-12f + acc1) / (1.0E-12f + acc2) * limGain[sbr.bs_limiter_gains];
                G_max = Math.min(G_max, 1.0E10f);
                for (m = ml1; m < ml2; ++m) {
                    if (m + sbr.kx == sbr.f_table_noise[current_f_noise_band + 1]) {
                        ++current_f_noise_band;
                    }
                    if (m + sbr.kx == sbr.f_table_res[sbr.f[ch][l]][current_res_band2 + 1]) {
                        S_mapped = HFAdjustment.get_S_mapped(sbr, ch, l, ++current_res_band2);
                    }
                    if (m + sbr.kx == sbr.f_table_res[1][current_hi_res_band + 1]) {
                        ++current_hi_res_band;
                    }
                    int S_index_mapped = 0;
                    if ((l >= sbr.l_A[ch] || sbr.bs_add_harmonic_prev[ch][current_hi_res_band] != 0 && sbr.bs_add_harmonic_flag_prev[ch]) && m + sbr.kx == sbr.f_table_res[1][current_hi_res_band + 1] + sbr.f_table_res[1][current_hi_res_band] >> 1) {
                        S_index_mapped = sbr.bs_add_harmonic[ch][current_hi_res_band];
                    }
                    float Q_div = sbr.Q_div[ch][current_f_noise_band][current_t_noise_band];
                    float Q_div2 = sbr.Q_div2[ch][current_f_noise_band][current_t_noise_band];
                    float Q_M = sbr.E_orig[ch][current_res_band2][l] * Q_div2;
                    if (S_index_mapped == 0) {
                        S_M[m] = 0.0f;
                    } else {
                        S_M[m] = sbr.E_orig[ch][current_res_band2][l] * Q_div;
                        den += S_M[m];
                    }
                    float G = sbr.E_orig[ch][current_res_band2][l] / (1.0f + sbr.E_curr[ch][m][l]);
                    if (S_mapped == 0 && delta == 1.0f) {
                        G *= Q_div;
                    } else if (S_mapped == 1) {
                        G *= Q_div2;
                    }
                    if (G_max > G) {
                        Q_M_lim[m] = Q_M;
                        G_lim[m] = G;
                    } else {
                        Q_M_lim[m] = Q_M * G_max / G;
                        G_lim[m] = G_max;
                    }
                    den += sbr.E_curr[ch][m][l] * G_lim[m];
                    if (S_index_mapped != 0 || l == sbr.l_A[ch]) continue;
                    den += Q_M_lim[m];
                }
                float G_boost = (acc1 + 1.0E-12f) / (den + 1.0E-12f);
                G_boost = Math.min(G_boost, 2.5118864f);
                for (m = ml1; m < ml2; ++m) {
                    adj.G_lim_boost[l][m] = (float)Math.sqrt(G_lim[m] * G_boost);
                    adj.Q_M_lim_boost[l][m] = (float)Math.sqrt(Q_M_lim[m] * G_boost);
                    adj.S_M_boost[l][m] = S_M[m] != 0.0f ? (float)Math.sqrt(S_M[m] * G_boost) : 0.0f;
                }
            }
        }
    }
}
