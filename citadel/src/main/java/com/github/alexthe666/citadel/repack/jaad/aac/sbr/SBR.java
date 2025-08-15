/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.sbr;

import com.github.alexthe666.citadel.repack.jaad.aac.AACException;
import com.github.alexthe666.citadel.repack.jaad.aac.SampleFrequency;
import com.github.alexthe666.citadel.repack.jaad.aac.ps.PS;
import com.github.alexthe666.citadel.repack.jaad.aac.sbr.AnalysisFilterbank;
import com.github.alexthe666.citadel.repack.jaad.aac.sbr.Constants;
import com.github.alexthe666.citadel.repack.jaad.aac.sbr.FBT;
import com.github.alexthe666.citadel.repack.jaad.aac.sbr.HFAdjustment;
import com.github.alexthe666.citadel.repack.jaad.aac.sbr.HFGeneration;
import com.github.alexthe666.citadel.repack.jaad.aac.sbr.HuffmanTables;
import com.github.alexthe666.citadel.repack.jaad.aac.sbr.NoiseEnvelope;
import com.github.alexthe666.citadel.repack.jaad.aac.sbr.SynthesisFilterbank;
import com.github.alexthe666.citadel.repack.jaad.aac.sbr.TFGrid;
import com.github.alexthe666.citadel.repack.jaad.aac.syntax.BitStream;
import java.util.Arrays;

public class SBR
implements Constants,
com.github.alexthe666.citadel.repack.jaad.aac.syntax.Constants,
HuffmanTables {
    private final boolean downSampledSBR;
    final SampleFrequency sample_rate;
    int maxAACLine;
    int rate;
    boolean just_seeked;
    int ret;
    boolean[] amp_res = new boolean[2];
    int k0;
    int kx;
    int M;
    int N_master;
    int N_high;
    int N_low;
    int N_Q;
    int[] N_L = new int[4];
    int[] n = new int[2];
    int[] f_master = new int[64];
    int[][] f_table_res = new int[2][64];
    int[] f_table_noise = new int[64];
    int[][] f_table_lim = new int[4][64];
    int[] table_map_k_to_g = new int[64];
    int[] abs_bord_lead = new int[2];
    int[] abs_bord_trail = new int[2];
    int[] n_rel_lead = new int[2];
    int[] n_rel_trail = new int[2];
    int[] L_E = new int[2];
    int[] L_E_prev = new int[2];
    int[] L_Q = new int[2];
    int[][] t_E = new int[2][6];
    int[][] t_Q = new int[2][3];
    int[][] f = new int[2][6];
    int[] f_prev = new int[2];
    float[][][] G_temp_prev = new float[2][5][64];
    float[][][] Q_temp_prev = new float[2][5][64];
    int[] GQ_ringbuf_index = new int[2];
    int[][][] E = new int[2][64][5];
    int[][] E_prev = new int[2][64];
    float[][][] E_orig = new float[2][64][5];
    float[][][] E_curr = new float[2][64][5];
    int[][][] Q = new int[2][64][2];
    float[][][] Q_div = new float[2][64][2];
    float[][][] Q_div2 = new float[2][64][2];
    int[][] Q_prev = new int[2][64];
    int[] l_A = new int[2];
    int[] l_A_prev = new int[2];
    int[][] bs_invf_mode = new int[2][5];
    int[][] bs_invf_mode_prev = new int[2][5];
    float[][] bwArray = new float[2][64];
    float[][] bwArray_prev = new float[2][64];
    int noPatches;
    int[] patchNoSubbands = new int[64];
    int[] patchStartSubband = new int[64];
    int[][] bs_add_harmonic = new int[2][64];
    int[][] bs_add_harmonic_prev = new int[2][64];
    int[] index_noise_prev = new int[2];
    int[] psi_is_prev = new int[2];
    int bs_start_freq_prev;
    int bs_stop_freq_prev;
    int bs_xover_band_prev;
    int bs_freq_scale_prev;
    boolean bs_alter_scale_prev;
    int bs_noise_bands_prev;
    int[] prevEnvIsShort = new int[2];
    int kx_prev;
    int bsco;
    int bsco_prev;
    int M_prev;
    boolean Reset;
    int frame;
    int header_count;
    boolean stereo;
    AnalysisFilterbank[] qmfa = new AnalysisFilterbank[2];
    SynthesisFilterbank[] qmfs = new SynthesisFilterbank[2];
    float[][][][] Xsbr = new float[2][40][64][2];
    int numTimeSlotsRate;
    int numTimeSlots;
    int tHFGen;
    int tHFAdj;
    PS ps;
    boolean ps_used;
    boolean psResetFlag;
    boolean bs_header_flag;
    int bs_crc_flag;
    int bs_sbr_crc_bits;
    int bs_protocol_version;
    boolean bs_amp_res;
    int bs_start_freq;
    int bs_stop_freq;
    int bs_xover_band;
    int bs_freq_scale;
    boolean bs_alter_scale;
    int bs_noise_bands;
    int bs_limiter_bands;
    int bs_limiter_gains;
    boolean bs_interpol_freq;
    boolean bs_smoothing_mode;
    int bs_samplerate_mode;
    boolean[] bs_add_harmonic_flag = new boolean[2];
    boolean[] bs_add_harmonic_flag_prev = new boolean[2];
    boolean bs_extended_data;
    int bs_extension_id;
    int bs_extension_data;
    boolean bs_coupling;
    int[] bs_frame_class = new int[2];
    int[][] bs_rel_bord = new int[2][9];
    int[][] bs_rel_bord_0 = new int[2][9];
    int[][] bs_rel_bord_1 = new int[2][9];
    int[] bs_pointer = new int[2];
    int[] bs_abs_bord_0 = new int[2];
    int[] bs_abs_bord_1 = new int[2];
    int[] bs_num_rel_0 = new int[2];
    int[] bs_num_rel_1 = new int[2];
    int[][] bs_df_env = new int[2][9];
    int[][] bs_df_noise = new int[2][3];

    public SBR(boolean smallFrames, boolean stereo, SampleFrequency sample_rate, boolean downSampledSBR) {
        this.downSampledSBR = downSampledSBR;
        this.stereo = stereo;
        this.sample_rate = sample_rate;
        this.bs_freq_scale = 2;
        this.bs_alter_scale = true;
        this.bs_noise_bands = 2;
        this.bs_limiter_bands = 2;
        this.bs_limiter_gains = 2;
        this.bs_interpol_freq = true;
        this.bs_smoothing_mode = true;
        this.bs_start_freq = 5;
        this.bs_amp_res = true;
        this.bs_samplerate_mode = 1;
        this.prevEnvIsShort[0] = -1;
        this.prevEnvIsShort[1] = -1;
        this.header_count = 0;
        this.Reset = true;
        this.tHFGen = 8;
        this.tHFAdj = 2;
        this.bsco = 0;
        this.bsco_prev = 0;
        this.M_prev = 0;
        this.bs_start_freq_prev = -1;
        if (smallFrames) {
            this.numTimeSlotsRate = 30;
            this.numTimeSlots = 15;
        } else {
            this.numTimeSlotsRate = 32;
            this.numTimeSlots = 16;
        }
        this.GQ_ringbuf_index[0] = 0;
        this.GQ_ringbuf_index[1] = 0;
        if (stereo) {
            this.qmfa[0] = new AnalysisFilterbank(32);
            this.qmfa[1] = new AnalysisFilterbank(32);
            this.qmfs[0] = new SynthesisFilterbank(downSampledSBR ? 32 : 64);
            this.qmfs[1] = new SynthesisFilterbank(downSampledSBR ? 32 : 64);
        } else {
            this.qmfa[0] = new AnalysisFilterbank(32);
            this.qmfs[0] = new SynthesisFilterbank(downSampledSBR ? 32 : 64);
            this.qmfs[1] = null;
        }
    }

    void sbrReset() {
        int j;
        if (this.qmfa[0] != null) {
            this.qmfa[0].reset();
        }
        if (this.qmfa[1] != null) {
            this.qmfa[1].reset();
        }
        if (this.qmfs[0] != null) {
            this.qmfs[0].reset();
        }
        if (this.qmfs[1] != null) {
            this.qmfs[1].reset();
        }
        for (j = 0; j < 5; ++j) {
            if (this.G_temp_prev[0][j] != null) {
                Arrays.fill(this.G_temp_prev[0][j], 0.0f);
            }
            if (this.G_temp_prev[1][j] != null) {
                Arrays.fill(this.G_temp_prev[1][j], 0.0f);
            }
            if (this.Q_temp_prev[0][j] != null) {
                Arrays.fill(this.Q_temp_prev[0][j], 0.0f);
            }
            if (this.Q_temp_prev[1][j] == null) continue;
            Arrays.fill(this.Q_temp_prev[1][j], 0.0f);
        }
        for (int i = 0; i < 40; ++i) {
            for (int k = 0; k < 64; ++k) {
                this.Xsbr[0][i][j][0] = 0.0f;
                this.Xsbr[0][i][j][1] = 0.0f;
                this.Xsbr[1][i][j][0] = 0.0f;
                this.Xsbr[1][i][j][1] = 0.0f;
            }
        }
        this.GQ_ringbuf_index[0] = 0;
        this.GQ_ringbuf_index[1] = 0;
        this.header_count = 0;
        this.Reset = true;
        this.L_E_prev[0] = 0;
        this.L_E_prev[1] = 0;
        this.bs_freq_scale = 2;
        this.bs_alter_scale = true;
        this.bs_noise_bands = 2;
        this.bs_limiter_bands = 2;
        this.bs_limiter_gains = 2;
        this.bs_interpol_freq = true;
        this.bs_smoothing_mode = true;
        this.bs_start_freq = 5;
        this.bs_amp_res = true;
        this.bs_samplerate_mode = 1;
        this.prevEnvIsShort[0] = -1;
        this.prevEnvIsShort[1] = -1;
        this.bsco = 0;
        this.bsco_prev = 0;
        this.M_prev = 0;
        this.bs_start_freq_prev = -1;
        this.f_prev[0] = 0;
        this.f_prev[1] = 0;
        for (j = 0; j < 49; ++j) {
            this.E_prev[0][j] = 0;
            this.Q_prev[0][j] = 0;
            this.E_prev[1][j] = 0;
            this.Q_prev[1][j] = 0;
            this.bs_add_harmonic_prev[0][j] = 0;
            this.bs_add_harmonic_prev[1][j] = 0;
        }
        this.bs_add_harmonic_flag_prev[0] = false;
        this.bs_add_harmonic_flag_prev[1] = false;
    }

    void sbr_reset() {
        this.Reset = this.bs_start_freq != this.bs_start_freq_prev || this.bs_stop_freq != this.bs_stop_freq_prev || this.bs_freq_scale != this.bs_freq_scale_prev || this.bs_alter_scale != this.bs_alter_scale_prev || this.bs_xover_band != this.bs_xover_band_prev || this.bs_noise_bands != this.bs_noise_bands_prev;
        this.bs_start_freq_prev = this.bs_start_freq;
        this.bs_stop_freq_prev = this.bs_stop_freq;
        this.bs_freq_scale_prev = this.bs_freq_scale;
        this.bs_alter_scale_prev = this.bs_alter_scale;
        this.bs_xover_band_prev = this.bs_xover_band;
        this.bs_noise_bands_prev = this.bs_noise_bands;
    }

    int calc_sbr_tables(int start_freq, int stop_freq, int samplerate_mode, int freq_scale, boolean alter_scale, int xover_band) {
        int result = 0;
        this.k0 = FBT.qmf_start_channel(start_freq, samplerate_mode, this.sample_rate);
        int k2 = FBT.qmf_stop_channel(stop_freq, this.sample_rate, this.k0);
        if (this.sample_rate.getFrequency() >= 48000) {
            if (k2 - this.k0 > 32) {
                ++result;
            }
        } else if (this.sample_rate.getFrequency() <= 32000) {
            if (k2 - this.k0 > 48) {
                ++result;
            }
        } else if (k2 - this.k0 > 45) {
            ++result;
        }
        result = freq_scale == 0 ? (result += FBT.master_frequency_table_fs0(this, this.k0, k2, alter_scale)) : (result += FBT.master_frequency_table(this, this.k0, k2, freq_scale, alter_scale));
        result = (result += FBT.derived_frequency_table(this, xover_band, k2)) > 0 ? 1 : 0;
        return result;
    }

    public int decode(BitStream ld, int bits, boolean crc) throws AACException {
        int num_sbr_bits2;
        int result = 0;
        int num_align_bits = 0;
        long num_sbr_bits1 = ld.getPosition();
        if (crc) {
            this.bs_sbr_crc_bits = ld.readBits(10);
        }
        int saved_start_freq = this.bs_start_freq;
        int saved_samplerate_mode = this.bs_samplerate_mode;
        int saved_stop_freq = this.bs_stop_freq;
        int saved_freq_scale = this.bs_freq_scale;
        boolean saved_alter_scale = this.bs_alter_scale;
        int saved_xover_band = this.bs_xover_band;
        this.bs_header_flag = ld.readBool();
        if (this.bs_header_flag) {
            this.sbr_header(ld);
        }
        this.sbr_reset();
        if (this.header_count != 0) {
            int rt;
            if ((this.Reset || this.bs_header_flag && this.just_seeked) && (rt = this.calc_sbr_tables(this.bs_start_freq, this.bs_stop_freq, this.bs_samplerate_mode, this.bs_freq_scale, this.bs_alter_scale, this.bs_xover_band)) > 0) {
                this.calc_sbr_tables(saved_start_freq, saved_stop_freq, saved_samplerate_mode, saved_freq_scale, saved_alter_scale, saved_xover_band);
            }
            if (result == 0 && (result = this.sbr_data(ld)) > 0 && (this.Reset || this.bs_header_flag && this.just_seeked)) {
                this.calc_sbr_tables(saved_start_freq, saved_stop_freq, saved_samplerate_mode, saved_freq_scale, saved_alter_scale, saved_xover_band);
            }
        } else {
            result = 1;
        }
        if (bits < (num_sbr_bits2 = (int)((long)ld.getPosition() - num_sbr_bits1))) {
            throw new AACException("frame overread");
        }
        num_align_bits = bits - num_sbr_bits2;
        ld.skipBits(num_align_bits);
        return result;
    }

    private void sbr_header(BitStream ld) throws AACException {
        ++this.header_count;
        this.bs_amp_res = ld.readBool();
        this.bs_start_freq = ld.readBits(4);
        this.bs_stop_freq = ld.readBits(4);
        this.bs_xover_band = ld.readBits(3);
        ld.readBits(2);
        boolean bs_header_extra_1 = ld.readBool();
        boolean bs_header_extra_2 = ld.readBool();
        if (bs_header_extra_1) {
            this.bs_freq_scale = ld.readBits(2);
            this.bs_alter_scale = ld.readBool();
            this.bs_noise_bands = ld.readBits(2);
        } else {
            this.bs_freq_scale = 2;
            this.bs_alter_scale = true;
            this.bs_noise_bands = 2;
        }
        if (bs_header_extra_2) {
            this.bs_limiter_bands = ld.readBits(2);
            this.bs_limiter_gains = ld.readBits(2);
            this.bs_interpol_freq = ld.readBool();
            this.bs_smoothing_mode = ld.readBool();
        } else {
            this.bs_limiter_bands = 2;
            this.bs_limiter_gains = 2;
            this.bs_interpol_freq = true;
            this.bs_smoothing_mode = true;
        }
    }

    private int sbr_data(BitStream ld) throws AACException {
        int n = this.rate = this.bs_samplerate_mode != 0 ? 2 : 1;
        if (this.stereo) {
            int result = this.sbr_channel_pair_element(ld);
            if (result > 0) {
                return result;
            }
        } else {
            int result = this.sbr_single_channel_element(ld);
            if (result > 0) {
                return result;
            }
        }
        return 0;
    }

    private int sbr_single_channel_element(BitStream ld) throws AACException {
        int result;
        if (ld.readBool()) {
            ld.readBits(4);
        }
        if ((result = this.sbr_grid(ld, 0)) > 0) {
            return result;
        }
        this.sbr_dtdf(ld, 0);
        this.invf_mode(ld, 0);
        this.sbr_envelope(ld, 0);
        this.sbr_noise(ld, 0);
        NoiseEnvelope.dequantChannel(this, 0);
        Arrays.fill(this.bs_add_harmonic[0], 0, 64, 0);
        Arrays.fill(this.bs_add_harmonic[1], 0, 64, 0);
        this.bs_add_harmonic_flag[0] = ld.readBool();
        if (this.bs_add_harmonic_flag[0]) {
            this.sinusoidal_coding(ld, 0);
        }
        this.bs_extended_data = ld.readBool();
        if (this.bs_extended_data) {
            int nr_bits_left;
            int tmp_nr_bits;
            boolean ps_ext_read = false;
            int cnt = ld.readBits(4);
            if (cnt == 15) {
                cnt += ld.readBits(8);
            }
            for (nr_bits_left = 8 * cnt; nr_bits_left > 7; nr_bits_left -= tmp_nr_bits) {
                tmp_nr_bits = 0;
                this.bs_extension_id = ld.readBits(2);
                tmp_nr_bits += 2;
                if (this.bs_extension_id == 2) {
                    if (!ps_ext_read) {
                        ps_ext_read = true;
                    } else {
                        this.bs_extension_id = 3;
                    }
                }
                if ((tmp_nr_bits += this.sbr_extension(ld, this.bs_extension_id, nr_bits_left)) <= nr_bits_left) continue;
                return 1;
            }
            if (nr_bits_left > 0) {
                ld.readBits(nr_bits_left);
            }
        }
        return 0;
    }

    private int sbr_channel_pair_element(BitStream ld) throws AACException {
        if (ld.readBool()) {
            ld.readBits(4);
            ld.readBits(4);
        }
        this.bs_coupling = ld.readBool();
        if (this.bs_coupling) {
            result = this.sbr_grid(ld, 0);
            if (result > 0) {
                return result;
            }
            this.bs_frame_class[1] = this.bs_frame_class[0];
            this.L_E[1] = this.L_E[0];
            this.L_Q[1] = this.L_Q[0];
            this.bs_pointer[1] = this.bs_pointer[0];
            for (n = 0; n <= this.L_E[0]; ++n) {
                this.t_E[1][n] = this.t_E[0][n];
                this.f[1][n] = this.f[0][n];
            }
            for (n = 0; n <= this.L_Q[0]; ++n) {
                this.t_Q[1][n] = this.t_Q[0][n];
            }
            this.sbr_dtdf(ld, 0);
            this.sbr_dtdf(ld, 1);
            this.invf_mode(ld, 0);
            for (n = 0; n < this.N_Q; ++n) {
                this.bs_invf_mode[1][n] = this.bs_invf_mode[0][n];
            }
            this.sbr_envelope(ld, 0);
            this.sbr_noise(ld, 0);
            this.sbr_envelope(ld, 1);
            this.sbr_noise(ld, 1);
            Arrays.fill(this.bs_add_harmonic[0], 0, 64, 0);
            Arrays.fill(this.bs_add_harmonic[1], 0, 64, 0);
            this.bs_add_harmonic_flag[0] = ld.readBool();
            if (this.bs_add_harmonic_flag[0]) {
                this.sinusoidal_coding(ld, 0);
            }
            this.bs_add_harmonic_flag[1] = ld.readBool();
            if (this.bs_add_harmonic_flag[1]) {
                this.sinusoidal_coding(ld, 1);
            }
        } else {
            int[] saved_t_E = new int[6];
            int[] saved_t_Q = new int[3];
            int saved_L_E = this.L_E[0];
            int saved_L_Q = this.L_Q[0];
            int saved_frame_class = this.bs_frame_class[0];
            for (n = 0; n < saved_L_E; ++n) {
                saved_t_E[n] = this.t_E[0][n];
            }
            for (n = 0; n < saved_L_Q; ++n) {
                saved_t_Q[n] = this.t_Q[0][n];
            }
            result = this.sbr_grid(ld, 0);
            if (result > 0) {
                return result;
            }
            result = this.sbr_grid(ld, 1);
            if (result > 0) {
                this.bs_frame_class[0] = saved_frame_class;
                this.L_E[0] = saved_L_E;
                this.L_Q[0] = saved_L_Q;
                for (n = 0; n < 6; ++n) {
                    this.t_E[0][n] = saved_t_E[n];
                }
                for (n = 0; n < 3; ++n) {
                    this.t_Q[0][n] = saved_t_Q[n];
                }
                return result;
            }
            this.sbr_dtdf(ld, 0);
            this.sbr_dtdf(ld, 1);
            this.invf_mode(ld, 0);
            this.invf_mode(ld, 1);
            this.sbr_envelope(ld, 0);
            this.sbr_envelope(ld, 1);
            this.sbr_noise(ld, 0);
            this.sbr_noise(ld, 1);
            Arrays.fill(this.bs_add_harmonic[0], 0, 64, 0);
            Arrays.fill(this.bs_add_harmonic[1], 0, 64, 0);
            this.bs_add_harmonic_flag[0] = ld.readBool();
            if (this.bs_add_harmonic_flag[0]) {
                this.sinusoidal_coding(ld, 0);
            }
            this.bs_add_harmonic_flag[1] = ld.readBool();
            if (this.bs_add_harmonic_flag[1]) {
                this.sinusoidal_coding(ld, 1);
            }
        }
        NoiseEnvelope.dequantChannel(this, 0);
        NoiseEnvelope.dequantChannel(this, 1);
        if (this.bs_coupling) {
            NoiseEnvelope.unmap(this);
        }
        this.bs_extended_data = ld.readBool();
        if (this.bs_extended_data) {
            int nr_bits_left;
            int tmp_nr_bits;
            int cnt = ld.readBits(4);
            if (cnt == 15) {
                cnt += ld.readBits(8);
            }
            for (nr_bits_left = 8 * cnt; nr_bits_left > 7; nr_bits_left -= tmp_nr_bits) {
                tmp_nr_bits = 0;
                this.bs_extension_id = ld.readBits(2);
                tmp_nr_bits += 2;
                if ((tmp_nr_bits += this.sbr_extension(ld, this.bs_extension_id, nr_bits_left)) <= nr_bits_left) continue;
                return 1;
            }
            if (nr_bits_left > 0) {
                ld.readBits(nr_bits_left);
            }
        }
        return 0;
    }

    private int sbr_log2(int val) {
        int[] log2tab = new int[]{0, 0, 1, 2, 2, 3, 3, 3, 3, 4};
        if (val < 10 && val >= 0) {
            return log2tab[val];
        }
        return 0;
    }

    private int sbr_grid(BitStream ld, int ch) throws AACException {
        int bs_num_env = 0;
        int saved_L_E = this.L_E[ch];
        int saved_L_Q = this.L_Q[ch];
        int saved_frame_class = this.bs_frame_class[ch];
        this.bs_frame_class[ch] = ld.readBits(2);
        switch (this.bs_frame_class[ch]) {
            case 0: {
                int i = ld.readBits(2);
                bs_num_env = Math.min(1 << i, 5);
                i = ld.readBit();
                for (int env = 0; env < bs_num_env; ++env) {
                    this.f[ch][env] = i;
                }
                this.abs_bord_lead[ch] = 0;
                this.abs_bord_trail[ch] = this.numTimeSlots;
                this.n_rel_lead[ch] = bs_num_env - 1;
                this.n_rel_trail[ch] = 0;
                break;
            }
            case 1: {
                int bs_abs_bord = ld.readBits(2) + this.numTimeSlots;
                bs_num_env = ld.readBits(2) + 1;
                for (int rel = 0; rel < bs_num_env - 1; ++rel) {
                    this.bs_rel_bord[ch][rel] = 2 * ld.readBits(2) + 2;
                }
                int i = this.sbr_log2(bs_num_env + 1);
                this.bs_pointer[ch] = ld.readBits(i);
                for (int env = 0; env < bs_num_env; ++env) {
                    this.f[ch][bs_num_env - env - 1] = ld.readBit();
                }
                this.abs_bord_lead[ch] = 0;
                this.abs_bord_trail[ch] = bs_abs_bord;
                this.n_rel_lead[ch] = 0;
                this.n_rel_trail[ch] = bs_num_env - 1;
                break;
            }
            case 2: {
                int bs_abs_bord = ld.readBits(2);
                bs_num_env = ld.readBits(2) + 1;
                for (int rel = 0; rel < bs_num_env - 1; ++rel) {
                    this.bs_rel_bord[ch][rel] = 2 * ld.readBits(2) + 2;
                }
                int i = this.sbr_log2(bs_num_env + 1);
                this.bs_pointer[ch] = ld.readBits(i);
                for (int env = 0; env < bs_num_env; ++env) {
                    this.f[ch][env] = ld.readBit();
                }
                this.abs_bord_lead[ch] = bs_abs_bord;
                this.abs_bord_trail[ch] = this.numTimeSlots;
                this.n_rel_lead[ch] = bs_num_env - 1;
                this.n_rel_trail[ch] = 0;
                break;
            }
            case 3: {
                int rel;
                int bs_abs_bord = ld.readBits(2);
                int bs_abs_bord_1 = ld.readBits(2) + this.numTimeSlots;
                this.bs_num_rel_0[ch] = ld.readBits(2);
                this.bs_num_rel_1[ch] = ld.readBits(2);
                bs_num_env = Math.min(5, this.bs_num_rel_0[ch] + this.bs_num_rel_1[ch] + 1);
                for (rel = 0; rel < this.bs_num_rel_0[ch]; ++rel) {
                    this.bs_rel_bord_0[ch][rel] = 2 * ld.readBits(2) + 2;
                }
                for (rel = 0; rel < this.bs_num_rel_1[ch]; ++rel) {
                    this.bs_rel_bord_1[ch][rel] = 2 * ld.readBits(2) + 2;
                }
                int i = this.sbr_log2(this.bs_num_rel_0[ch] + this.bs_num_rel_1[ch] + 2);
                this.bs_pointer[ch] = ld.readBits(i);
                for (int env = 0; env < bs_num_env; ++env) {
                    this.f[ch][env] = ld.readBit();
                }
                this.abs_bord_lead[ch] = bs_abs_bord;
                this.abs_bord_trail[ch] = bs_abs_bord_1;
                this.n_rel_lead[ch] = this.bs_num_rel_0[ch];
                this.n_rel_trail[ch] = this.bs_num_rel_1[ch];
            }
        }
        this.L_E[ch] = this.bs_frame_class[ch] == 3 ? Math.min(bs_num_env, 5) : Math.min(bs_num_env, 4);
        if (this.L_E[ch] <= 0) {
            return 1;
        }
        this.L_Q[ch] = this.L_E[ch] > 1 ? 2 : 1;
        int result = TFGrid.envelope_time_border_vector(this, ch);
        if (result > 0) {
            this.bs_frame_class[ch] = saved_frame_class;
            this.L_E[ch] = saved_L_E;
            this.L_Q[ch] = saved_L_Q;
            return result;
        }
        TFGrid.noise_floor_time_border_vector(this, ch);
        return 0;
    }

    private void sbr_dtdf(BitStream ld, int ch) throws AACException {
        int i;
        for (i = 0; i < this.L_E[ch]; ++i) {
            this.bs_df_env[ch][i] = ld.readBit();
        }
        for (i = 0; i < this.L_Q[ch]; ++i) {
            this.bs_df_noise[ch][i] = ld.readBit();
        }
    }

    private void invf_mode(BitStream ld, int ch) throws AACException {
        for (int n = 0; n < this.N_Q; ++n) {
            this.bs_invf_mode[ch][n] = ld.readBits(2);
        }
    }

    private int sbr_extension(BitStream ld, int bs_extension_id, int num_bits_left) throws AACException {
        switch (bs_extension_id) {
            case 2: {
                if (this.ps == null) {
                    this.ps = new PS(this.sample_rate, this.numTimeSlotsRate);
                }
                if (this.psResetFlag) {
                    this.ps.header_read = false;
                }
                int ret = this.ps.decode(ld);
                if (!this.ps_used && this.ps.header_read) {
                    this.ps_used = true;
                }
                if (this.ps.header_read) {
                    this.psResetFlag = false;
                }
                return ret;
            }
        }
        this.bs_extension_data = ld.readBits(6);
        return 6;
    }

    private void sinusoidal_coding(BitStream ld, int ch) throws AACException {
        for (int n = 0; n < this.N_high; ++n) {
            this.bs_add_harmonic[ch][n] = ld.readBit();
        }
    }

    private void sbr_envelope(BitStream ld, int ch) throws AACException {
        int[][] f_huff;
        int[][] t_huff;
        int delta = 0;
        this.amp_res[ch] = this.L_E[ch] == 1 && this.bs_frame_class[ch] == 0 ? false : this.bs_amp_res;
        if (this.bs_coupling && ch == 1) {
            delta = 1;
            if (this.amp_res[ch]) {
                t_huff = T_HUFFMAN_ENV_BAL_3_0DB;
                f_huff = F_HUFFMAN_ENV_BAL_3_0DB;
            } else {
                t_huff = T_HUFFMAN_ENV_BAL_1_5DB;
                f_huff = F_HUFFMAN_ENV_BAL_1_5DB;
            }
        } else {
            delta = 0;
            if (this.amp_res[ch]) {
                t_huff = T_HUFFMAN_ENV_3_0DB;
                f_huff = F_HUFFMAN_ENV_3_0DB;
            } else {
                t_huff = T_HUFFMAN_ENV_1_5DB;
                f_huff = F_HUFFMAN_ENV_1_5DB;
            }
        }
        for (int env = 0; env < this.L_E[ch]; ++env) {
            int band;
            if (this.bs_df_env[ch][env] == 0) {
                this.E[ch][0][env] = this.bs_coupling && ch == 1 ? (this.amp_res[ch] ? ld.readBits(5) << delta : ld.readBits(6) << delta) : (this.amp_res[ch] ? ld.readBits(6) << delta : ld.readBits(7) << delta);
                for (band = 1; band < this.n[this.f[ch][env]]; ++band) {
                    this.E[ch][band][env] = this.decodeHuffman(ld, f_huff) << delta;
                }
                continue;
            }
            for (band = 0; band < this.n[this.f[ch][env]]; ++band) {
                this.E[ch][band][env] = this.decodeHuffman(ld, t_huff) << delta;
            }
        }
        NoiseEnvelope.extract_envelope_data(this, ch);
    }

    private void sbr_noise(BitStream ld, int ch) throws AACException {
        int[][] f_huff;
        int[][] t_huff;
        int delta = 0;
        if (this.bs_coupling && ch == 1) {
            delta = 1;
            t_huff = T_HUFFMAN_NOISE_BAL_3_0DB;
            f_huff = F_HUFFMAN_ENV_BAL_3_0DB;
        } else {
            delta = 0;
            t_huff = T_HUFFMAN_NOISE_3_0DB;
            f_huff = F_HUFFMAN_ENV_3_0DB;
        }
        for (int noise = 0; noise < this.L_Q[ch]; ++noise) {
            int band;
            if (this.bs_df_noise[ch][noise] == 0) {
                this.Q[ch][0][noise] = this.bs_coupling && ch == 1 ? ld.readBits(5) << delta : ld.readBits(5) << delta;
                for (band = 1; band < this.N_Q; ++band) {
                    this.Q[ch][band][noise] = this.decodeHuffman(ld, f_huff) << delta;
                }
                continue;
            }
            for (band = 0; band < this.N_Q; ++band) {
                this.Q[ch][band][noise] = this.decodeHuffman(ld, t_huff) << delta;
            }
        }
        NoiseEnvelope.extract_noise_floor_data(this, ch);
    }

    private int decodeHuffman(BitStream ld, int[][] t_huff) throws AACException {
        int index = 0;
        while (index >= 0) {
            int bit = ld.readBit();
            index = t_huff[index][bit];
        }
        return index + 64;
    }

    private int sbr_save_prev_data(int ch) {
        int i;
        this.kx_prev = this.kx;
        this.M_prev = this.M;
        this.bsco_prev = this.bsco;
        this.L_E_prev[ch] = this.L_E[ch];
        if (this.L_E[ch] <= 0) {
            return 19;
        }
        this.f_prev[ch] = this.f[ch][this.L_E[ch] - 1];
        for (i = 0; i < 49; ++i) {
            this.E_prev[ch][i] = this.E[ch][i][this.L_E[ch] - 1];
            this.Q_prev[ch][i] = this.Q[ch][i][this.L_Q[ch] - 1];
        }
        for (i = 0; i < 49; ++i) {
            this.bs_add_harmonic_prev[ch][i] = this.bs_add_harmonic[ch][i];
        }
        this.bs_add_harmonic_flag_prev[ch] = this.bs_add_harmonic_flag[ch];
        this.prevEnvIsShort[ch] = this.l_A[ch] == this.L_E[ch] ? 0 : -1;
        return 0;
    }

    private void sbr_save_matrix(int ch) {
        int j;
        int i;
        for (i = 0; i < this.tHFGen; ++i) {
            for (j = 0; j < 64; ++j) {
                this.Xsbr[ch][i][j][0] = this.Xsbr[ch][i + this.numTimeSlotsRate][j][0];
                this.Xsbr[ch][i][j][1] = this.Xsbr[ch][i + this.numTimeSlotsRate][j][1];
            }
        }
        for (i = this.tHFGen; i < 40; ++i) {
            for (j = 0; j < 64; ++j) {
                this.Xsbr[ch][i][j][0] = 0.0f;
                this.Xsbr[ch][i][j][1] = 0.0f;
            }
        }
    }

    private int sbr_process_channel(float[] channel_buf, float[][][] X, int ch, boolean dont_process) {
        int ret = 0;
        this.bsco = 0;
        if (dont_process) {
            this.qmfa[ch].sbr_qmf_analysis_32(this, channel_buf, this.Xsbr[ch], this.tHFGen, 32);
        } else {
            this.qmfa[ch].sbr_qmf_analysis_32(this, channel_buf, this.Xsbr[ch], this.tHFGen, this.kx);
        }
        if (!dont_process) {
            HFGeneration.hf_generation(this, this.Xsbr[ch], this.Xsbr[ch], ch);
            ret = HFAdjustment.hf_adjustment(this, this.Xsbr[ch], ch);
            if (ret > 0) {
                dont_process = true;
            }
        }
        if (this.just_seeked || dont_process) {
            for (int l = 0; l < this.numTimeSlotsRate; ++l) {
                int k;
                for (k = 0; k < 32; ++k) {
                    X[l][k][0] = this.Xsbr[ch][l + this.tHFAdj][k][0];
                    X[l][k][1] = this.Xsbr[ch][l + this.tHFAdj][k][1];
                }
                for (k = 32; k < 64; ++k) {
                    X[l][k][0] = 0.0f;
                    X[l][k][1] = 0.0f;
                }
            }
        } else {
            for (int l = 0; l < this.numTimeSlotsRate; ++l) {
                int k;
                int bsco_band;
                int M_band;
                int kx_band;
                if (l < this.t_E[ch][0]) {
                    kx_band = this.kx_prev;
                    M_band = this.M_prev;
                    bsco_band = this.bsco_prev;
                } else {
                    kx_band = this.kx;
                    M_band = this.M;
                    bsco_band = this.bsco;
                }
                for (k = 0; k < kx_band + bsco_band; ++k) {
                    X[l][k][0] = this.Xsbr[ch][l + this.tHFAdj][k][0];
                    X[l][k][1] = this.Xsbr[ch][l + this.tHFAdj][k][1];
                }
                for (k = kx_band + bsco_band; k < kx_band + M_band; ++k) {
                    X[l][k][0] = this.Xsbr[ch][l + this.tHFAdj][k][0];
                    X[l][k][1] = this.Xsbr[ch][l + this.tHFAdj][k][1];
                }
                for (k = Math.max(kx_band + bsco_band, kx_band + M_band); k < 64; ++k) {
                    X[l][k][0] = 0.0f;
                    X[l][k][1] = 0.0f;
                }
            }
        }
        return ret;
    }

    public int process(float[] left_chan, float[] right_chan, boolean just_seeked) {
        boolean dont_process = false;
        int ret = 0;
        float[][][] X = new float[32][64][2];
        if (!this.stereo) {
            return 21;
        }
        if (this.ret != 0 || this.header_count == 0) {
            dont_process = true;
            if (this.ret != 0 && this.Reset) {
                this.bs_start_freq_prev = -1;
            }
        }
        this.just_seeked = just_seeked;
        this.ret += this.sbr_process_channel(left_chan, X, 0, dont_process);
        if (this.downSampledSBR) {
            this.qmfs[0].sbr_qmf_synthesis_32(this, X, left_chan);
        } else {
            this.qmfs[0].sbr_qmf_synthesis_64(this, X, left_chan);
        }
        this.ret += this.sbr_process_channel(right_chan, X, 1, dont_process);
        if (this.downSampledSBR) {
            this.qmfs[1].sbr_qmf_synthesis_32(this, X, right_chan);
        } else {
            this.qmfs[1].sbr_qmf_synthesis_64(this, X, right_chan);
        }
        if (this.bs_header_flag) {
            this.just_seeked = false;
        }
        if (this.header_count != 0 && this.ret == 0) {
            ret = this.sbr_save_prev_data(0);
            if (ret != 0) {
                return ret;
            }
            ret = this.sbr_save_prev_data(1);
            if (ret != 0) {
                return ret;
            }
        }
        this.sbr_save_matrix(0);
        this.sbr_save_matrix(1);
        ++this.frame;
        return 0;
    }

    public int process(float[] channel, boolean just_seeked) {
        boolean dont_process = false;
        int ret = 0;
        float[][][] X = new float[32][64][2];
        if (this.stereo) {
            return 21;
        }
        if (this.ret != 0 || this.header_count == 0) {
            dont_process = true;
            if (this.ret != 0 && this.Reset) {
                this.bs_start_freq_prev = -1;
            }
        }
        this.just_seeked = just_seeked;
        this.ret += this.sbr_process_channel(channel, X, 0, dont_process);
        if (this.downSampledSBR) {
            this.qmfs[0].sbr_qmf_synthesis_32(this, X, channel);
        } else {
            this.qmfs[0].sbr_qmf_synthesis_64(this, X, channel);
        }
        if (this.bs_header_flag) {
            this.just_seeked = false;
        }
        if (this.header_count != 0 && this.ret == 0 && (ret = this.sbr_save_prev_data(0)) != 0) {
            return ret;
        }
        this.sbr_save_matrix(0);
        ++this.frame;
        return 0;
    }

    public int processPS(float[] left_channel, float[] right_channel, boolean just_seeked) {
        boolean dont_process = false;
        int ret = 0;
        float[][][] X_left = new float[38][64][2];
        float[][][] X_right = new float[38][64][2];
        if (this.stereo) {
            return 21;
        }
        if (this.ret != 0 || this.header_count == 0) {
            dont_process = true;
            if (this.ret != 0 && this.Reset) {
                this.bs_start_freq_prev = -1;
            }
        }
        this.just_seeked = just_seeked;
        if (this.qmfs[1] == null) {
            this.qmfs[1] = new SynthesisFilterbank(this.downSampledSBR ? 32 : 64);
        }
        this.ret += this.sbr_process_channel(left_channel, X_left, 0, dont_process);
        for (int l = this.numTimeSlotsRate; l < this.numTimeSlotsRate + 6; ++l) {
            for (int k = 0; k < 5; ++k) {
                X_left[l][k][0] = this.Xsbr[0][this.tHFAdj + l][k][0];
                X_left[l][k][1] = this.Xsbr[0][this.tHFAdj + l][k][1];
            }
        }
        this.ps.process(X_left, X_right);
        if (this.downSampledSBR) {
            this.qmfs[0].sbr_qmf_synthesis_32(this, X_left, left_channel);
            this.qmfs[1].sbr_qmf_synthesis_32(this, X_right, right_channel);
        } else {
            this.qmfs[0].sbr_qmf_synthesis_64(this, X_left, left_channel);
            this.qmfs[1].sbr_qmf_synthesis_64(this, X_right, right_channel);
        }
        if (this.bs_header_flag) {
            this.just_seeked = false;
        }
        if (this.header_count != 0 && this.ret == 0 && (ret = this.sbr_save_prev_data(0)) != 0) {
            return ret;
        }
        this.sbr_save_matrix(0);
        ++this.frame;
        return 0;
    }

    public boolean isPSUsed() {
        return this.ps_used;
    }
}
