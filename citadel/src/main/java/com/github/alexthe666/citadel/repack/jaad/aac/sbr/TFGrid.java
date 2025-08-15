/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.sbr;

import com.github.alexthe666.citadel.repack.jaad.aac.sbr.Constants;
import com.github.alexthe666.citadel.repack.jaad.aac.sbr.SBR;

class TFGrid
implements Constants {
    TFGrid() {
    }

    public static int envelope_time_border_vector(SBR sbr, int ch) {
        int l;
        int[] t_E_temp = new int[6];
        t_E_temp[0] = sbr.rate * sbr.abs_bord_lead[ch];
        t_E_temp[sbr.L_E[ch]] = sbr.rate * sbr.abs_bord_trail[ch];
        block0 : switch (sbr.bs_frame_class[ch]) {
            case 0: {
                switch (sbr.L_E[ch]) {
                    case 4: {
                        int temp = sbr.numTimeSlots / 4;
                        t_E_temp[3] = sbr.rate * 3 * temp;
                        t_E_temp[2] = sbr.rate * 2 * temp;
                        t_E_temp[1] = sbr.rate * temp;
                        break block0;
                    }
                    case 2: {
                        t_E_temp[1] = sbr.rate * (sbr.numTimeSlots / 2);
                        break block0;
                    }
                }
                break;
            }
            case 1: {
                if (sbr.L_E[ch] <= 1) break;
                int i = sbr.L_E[ch];
                int border = sbr.abs_bord_trail[ch];
                for (l = 0; l < sbr.L_E[ch] - 1; ++l) {
                    if (border < sbr.bs_rel_bord[ch][l]) {
                        return 1;
                    }
                    t_E_temp[--i] = sbr.rate * (border -= sbr.bs_rel_bord[ch][l]);
                }
                break;
            }
            case 2: {
                if (sbr.L_E[ch] <= 1) break;
                int i = 1;
                int border = sbr.abs_bord_lead[ch];
                for (l = 0; l < sbr.L_E[ch] - 1; ++l) {
                    if (sbr.rate * (border += sbr.bs_rel_bord[ch][l]) + sbr.tHFAdj > sbr.numTimeSlotsRate + sbr.tHFGen) {
                        return 1;
                    }
                    t_E_temp[i++] = sbr.rate * border;
                }
                break;
            }
            case 3: {
                int border;
                int i;
                if (sbr.bs_num_rel_0[ch] != 0) {
                    i = 1;
                    border = sbr.abs_bord_lead[ch];
                    for (l = 0; l < sbr.bs_num_rel_0[ch]; ++l) {
                        if (sbr.rate * (border += sbr.bs_rel_bord_0[ch][l]) + sbr.tHFAdj > sbr.numTimeSlotsRate + sbr.tHFGen) {
                            return 1;
                        }
                        t_E_temp[i++] = sbr.rate * border;
                    }
                }
                if (sbr.bs_num_rel_1[ch] == 0) break;
                i = sbr.L_E[ch];
                border = sbr.abs_bord_trail[ch];
                for (l = 0; l < sbr.bs_num_rel_1[ch]; ++l) {
                    if (border < sbr.bs_rel_bord_1[ch][l]) {
                        return 1;
                    }
                    t_E_temp[--i] = sbr.rate * (border -= sbr.bs_rel_bord_1[ch][l]);
                }
                break;
            }
        }
        for (l = 0; l < 6; ++l) {
            sbr.t_E[ch][l] = t_E_temp[l];
        }
        return 0;
    }

    public static void noise_floor_time_border_vector(SBR sbr, int ch) {
        sbr.t_Q[ch][0] = sbr.t_E[ch][0];
        if (sbr.L_E[ch] == 1) {
            sbr.t_Q[ch][1] = sbr.t_E[ch][1];
            sbr.t_Q[ch][2] = 0;
        } else {
            int index = TFGrid.middleBorder(sbr, ch);
            sbr.t_Q[ch][1] = sbr.t_E[ch][index];
            sbr.t_Q[ch][2] = sbr.t_E[ch][sbr.L_E[ch]];
        }
    }

    private static int middleBorder(SBR sbr, int ch) {
        int retval = 0;
        switch (sbr.bs_frame_class[ch]) {
            case 0: {
                retval = sbr.L_E[ch] / 2;
                break;
            }
            case 2: {
                if (sbr.bs_pointer[ch] == 0) {
                    retval = 1;
                    break;
                }
                if (sbr.bs_pointer[ch] == 1) {
                    retval = sbr.L_E[ch] - 1;
                    break;
                }
                retval = sbr.bs_pointer[ch] - 1;
                break;
            }
            case 1: 
            case 3: {
                retval = sbr.bs_pointer[ch] > 1 ? sbr.L_E[ch] + 1 - sbr.bs_pointer[ch] : sbr.L_E[ch] - 1;
            }
        }
        return retval > 0 ? retval : 0;
    }
}
