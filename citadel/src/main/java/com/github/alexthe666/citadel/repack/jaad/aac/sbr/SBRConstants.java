/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.aac.sbr;

interface SBRConstants {
    public static final int[] startMinTable = new int[]{7, 7, 10, 11, 12, 16, 16, 17, 24, 32, 35, 48};
    public static final int[] offsetIndexTable = new int[]{5, 5, 4, 4, 4, 3, 2, 1, 0, 6, 6, 6};
    public static final int[][] OFFSET = new int[][]{{-8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7}, {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 9, 11, 13}, {-5, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 9, 11, 13, 16}, {-6, -4, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 9, 11, 13, 16}, {-4, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 9, 11, 13, 16, 20}, {-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 9, 11, 13, 16, 20, 24}, {0, 1, 2, 3, 4, 5, 6, 7, 9, 11, 13, 16, 20, 24, 28, 33}};
    public static final int EXTENSION_ID_PS = 2;
    public static final int MAX_NTSRHFG = 40;
    public static final int MAX_NTSR = 32;
    public static final int MAX_M = 49;
    public static final int MAX_L_E = 5;
    public static final int EXT_SBR_DATA = 13;
    public static final int EXT_SBR_DATA_CRC = 14;
    public static final int FIXFIX = 0;
    public static final int FIXVAR = 1;
    public static final int VARFIX = 2;
    public static final int VARVAR = 3;
    public static final int LO_RES = 0;
    public static final int HI_RES = 1;
    public static final int NO_TIME_SLOTS_960 = 15;
    public static final int NO_TIME_SLOTS = 16;
    public static final int RATE = 2;
    public static final int NOISE_FLOOR_OFFSET = 6;
    public static final int T_HFGEN = 8;
    public static final int T_HFADJ = 2;
}
