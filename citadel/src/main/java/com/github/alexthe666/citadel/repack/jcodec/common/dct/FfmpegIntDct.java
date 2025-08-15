/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.dct;

import java.nio.ShortBuffer;

public class FfmpegIntDct {
    private static final int DCTSIZE = 8;
    private static final int DCTSIZE_6 = 48;
    private static final int DCTSIZE_5 = 40;
    private static final int DCTSIZE_4 = 32;
    private static final int DCTSIZE_3 = 24;
    private static final int DCTSIZE_2 = 16;
    private static final int DCTSIZE_1 = 8;
    private static final int DCTSIZE_7 = 56;
    private static final int DCTSIZE_0 = 0;
    private static final int PASS1_BITS = 2;
    private static final int CONST_BITS = 13;
    private static final int D1 = 11;
    private static final int D2 = 18;
    private static final int ONEHALF_11 = 1024;
    private static final int ONEHALF_18 = 131072;
    private static final short FIX_0_211164243 = 1730;
    private static final short FIX_0_275899380 = 2260;
    private static final short FIX_0_298631336 = 2446;
    private static final short FIX_0_390180644 = 3196;
    private static final short FIX_0_509795579 = 4176;
    private static final short FIX_0_541196100 = 4433;
    private static final short FIX_0_601344887 = 4926;
    private static final short FIX_0_765366865 = 6270;
    private static final short FIX_0_785694958 = 6436;
    private static final short FIX_0_899976223 = 7373;
    private static final short FIX_1_061594337 = 8697;
    private static final short FIX_1_111140466 = 9102;
    private static final short FIX_1_175875602 = 9633;
    private static final short FIX_1_306562965 = 10703;
    private static final short FIX_1_387039845 = 11363;
    private static final short FIX_1_451774981 = 11893;
    private static final short FIX_1_501321110 = 12299;
    private static final short FIX_1_662939225 = 13623;
    private static final short FIX_1_847759065 = 15137;
    private static final short FIX_1_961570560 = 16069;
    private static final short FIX_2_053119869 = 16819;
    private static final short FIX_2_172734803 = 17799;
    private static final short FIX_2_562915447 = 20995;
    private static final short FIX_3_072711026 = 25172;

    public short[] decode(short[] orig) {
        ShortBuffer data = ShortBuffer.wrap(orig);
        FfmpegIntDct.pass1(data);
        FfmpegIntDct.pass2(data);
        return orig;
    }

    private static ShortBuffer advance(ShortBuffer dataptr, int size) {
        dataptr.position(dataptr.position() + size);
        return dataptr.slice();
    }

    private static final void pass1(ShortBuffer data) {
        ShortBuffer dataptr = data.duplicate();
        for (int rowctr = 7; rowctr >= 0; --rowctr) {
            int z5;
            int z4;
            int z3;
            int z2;
            int tmp12;
            int tmp11;
            int tmp13;
            int tmp10;
            int tmp1;
            int tmp0;
            int tmp3;
            int tmp2;
            int z1;
            short d7;
            short d5;
            short d3;
            short d0 = dataptr.get(0);
            short d2 = dataptr.get(1);
            short d4 = dataptr.get(2);
            short d6 = dataptr.get(3);
            short d1 = dataptr.get(4);
            if ((d1 | d2 | (d3 = dataptr.get(5)) | d4 | (d5 = dataptr.get(6)) | d6 | (d7 = dataptr.get(7))) == 0) {
                if (d0 != 0) {
                    int dcval = d0 << 2;
                    for (int i = 0; i < 8; ++i) {
                        dataptr.put(i, (short)dcval);
                    }
                }
                dataptr = FfmpegIntDct.advance(dataptr, 8);
                continue;
            }
            if (d6 != 0) {
                if (d2 != 0) {
                    z1 = FfmpegIntDct.MULTIPLY(d2 + d6, (short)4433);
                    tmp2 = z1 + FfmpegIntDct.MULTIPLY(-d6, (short)15137);
                    tmp3 = z1 + FfmpegIntDct.MULTIPLY(d2, (short)6270);
                    tmp0 = d0 + d4 << 13;
                    tmp1 = d0 - d4 << 13;
                    tmp10 = tmp0 + tmp3;
                    tmp13 = tmp0 - tmp3;
                    tmp11 = tmp1 + tmp2;
                    tmp12 = tmp1 - tmp2;
                } else {
                    tmp2 = FfmpegIntDct.MULTIPLY(-d6, (short)10703);
                    tmp3 = FfmpegIntDct.MULTIPLY(d6, (short)4433);
                    tmp0 = d0 + d4 << 13;
                    tmp1 = d0 - d4 << 13;
                    tmp10 = tmp0 + tmp3;
                    tmp13 = tmp0 - tmp3;
                    tmp11 = tmp1 + tmp2;
                    tmp12 = tmp1 - tmp2;
                }
            } else if (d2 != 0) {
                tmp2 = FfmpegIntDct.MULTIPLY(d2, (short)4433);
                tmp3 = FfmpegIntDct.MULTIPLY(d2, (short)10703);
                tmp0 = d0 + d4 << 13;
                tmp1 = d0 - d4 << 13;
                tmp10 = tmp0 + tmp3;
                tmp13 = tmp0 - tmp3;
                tmp11 = tmp1 + tmp2;
                tmp12 = tmp1 - tmp2;
            } else {
                tmp10 = tmp13 = d0 + d4 << 13;
                tmp11 = tmp12 = d0 - d4 << 13;
            }
            if (d7 != 0) {
                if (d5 != 0) {
                    if (d3 != 0) {
                        if (d1 != 0) {
                            z1 = d7 + d1;
                            z2 = d5 + d3;
                            z3 = d7 + d3;
                            z4 = d5 + d1;
                            z5 = FfmpegIntDct.MULTIPLY(z3 + z4, (short)9633);
                            tmp0 = FfmpegIntDct.MULTIPLY(d7, (short)2446);
                            tmp1 = FfmpegIntDct.MULTIPLY(d5, (short)16819);
                            tmp2 = FfmpegIntDct.MULTIPLY(d3, (short)25172);
                            tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)12299);
                            z1 = FfmpegIntDct.MULTIPLY(-z1, (short)7373);
                            z2 = FfmpegIntDct.MULTIPLY(-z2, (short)20995);
                            z3 = FfmpegIntDct.MULTIPLY(-z3, (short)16069);
                            z4 = FfmpegIntDct.MULTIPLY(-z4, (short)3196);
                            tmp0 += z1 + (z3 += z5);
                            tmp1 += z2 + (z4 += z5);
                            tmp2 += z2 + z3;
                            tmp3 += z1 + z4;
                        } else {
                            z2 = d5 + d3;
                            z3 = d7 + d3;
                            z5 = FfmpegIntDct.MULTIPLY(z3 + d5, (short)9633);
                            tmp0 = FfmpegIntDct.MULTIPLY(d7, (short)2446);
                            tmp1 = FfmpegIntDct.MULTIPLY(d5, (short)16819);
                            tmp2 = FfmpegIntDct.MULTIPLY(d3, (short)25172);
                            z1 = FfmpegIntDct.MULTIPLY(-d7, (short)7373);
                            z2 = FfmpegIntDct.MULTIPLY(-z2, (short)20995);
                            z3 = FfmpegIntDct.MULTIPLY(-z3, (short)16069);
                            z4 = FfmpegIntDct.MULTIPLY(-d5, (short)3196);
                            tmp0 += z1 + (z3 += z5);
                            tmp1 += z2 + (z4 += z5);
                            tmp2 += z2 + z3;
                            tmp3 = z1 + z4;
                        }
                    } else if (d1 != 0) {
                        z1 = d7 + d1;
                        z4 = d5 + d1;
                        z5 = FfmpegIntDct.MULTIPLY(d7 + z4, (short)9633);
                        tmp0 = FfmpegIntDct.MULTIPLY(d7, (short)2446);
                        tmp1 = FfmpegIntDct.MULTIPLY(d5, (short)16819);
                        tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)12299);
                        z1 = FfmpegIntDct.MULTIPLY(-z1, (short)7373);
                        z2 = FfmpegIntDct.MULTIPLY(-d5, (short)20995);
                        z3 = FfmpegIntDct.MULTIPLY(-d7, (short)16069);
                        z4 = FfmpegIntDct.MULTIPLY(-z4, (short)3196);
                        tmp0 += z1 + (z3 += z5);
                        tmp1 += z2 + (z4 += z5);
                        tmp2 = z2 + z3;
                        tmp3 += z1 + z4;
                    } else {
                        tmp0 = FfmpegIntDct.MULTIPLY(-d7, (short)4926);
                        z1 = FfmpegIntDct.MULTIPLY(-d7, (short)7373);
                        z3 = FfmpegIntDct.MULTIPLY(-d7, (short)16069);
                        tmp1 = FfmpegIntDct.MULTIPLY(-d5, (short)4176);
                        z2 = FfmpegIntDct.MULTIPLY(-d5, (short)20995);
                        z4 = FfmpegIntDct.MULTIPLY(-d5, (short)3196);
                        z5 = FfmpegIntDct.MULTIPLY(d5 + d7, (short)9633);
                        tmp0 += (z3 += z5);
                        tmp1 += (z4 += z5);
                        tmp2 = z2 + z3;
                        tmp3 = z1 + z4;
                    }
                } else if (d3 != 0) {
                    if (d1 != 0) {
                        z1 = d7 + d1;
                        z3 = d7 + d3;
                        z5 = FfmpegIntDct.MULTIPLY(z3 + d1, (short)9633);
                        tmp0 = FfmpegIntDct.MULTIPLY(d7, (short)2446);
                        tmp2 = FfmpegIntDct.MULTIPLY(d3, (short)25172);
                        tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)12299);
                        z1 = FfmpegIntDct.MULTIPLY(-z1, (short)7373);
                        z2 = FfmpegIntDct.MULTIPLY(-d3, (short)20995);
                        z3 = FfmpegIntDct.MULTIPLY(-z3, (short)16069);
                        z4 = FfmpegIntDct.MULTIPLY(-d1, (short)3196);
                        tmp0 += z1 + (z3 += z5);
                        tmp1 = z2 + (z4 += z5);
                        tmp2 += z2 + z3;
                        tmp3 += z1 + z4;
                    } else {
                        z3 = d7 + d3;
                        tmp0 = FfmpegIntDct.MULTIPLY(-d7, (short)4926);
                        z1 = FfmpegIntDct.MULTIPLY(-d7, (short)7373);
                        tmp2 = FfmpegIntDct.MULTIPLY(d3, (short)4176);
                        z2 = FfmpegIntDct.MULTIPLY(-d3, (short)20995);
                        z5 = FfmpegIntDct.MULTIPLY(z3, (short)9633);
                        z3 = FfmpegIntDct.MULTIPLY(-z3, (short)6436);
                        tmp0 += z3;
                        tmp1 = z2 + z5;
                        tmp2 += z3;
                        tmp3 = z1 + z5;
                    }
                } else if (d1 != 0) {
                    z1 = d7 + d1;
                    z5 = FfmpegIntDct.MULTIPLY(z1, (short)9633);
                    z1 = FfmpegIntDct.MULTIPLY(z1, (short)2260);
                    z3 = FfmpegIntDct.MULTIPLY(-d7, (short)16069);
                    tmp0 = FfmpegIntDct.MULTIPLY(-d7, (short)13623);
                    z4 = FfmpegIntDct.MULTIPLY(-d1, (short)3196);
                    tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)9102);
                    tmp0 += z1;
                    tmp1 = z4 + z5;
                    tmp2 = z3 + z5;
                    tmp3 += z1;
                } else {
                    tmp0 = FfmpegIntDct.MULTIPLY(-d7, (short)11363);
                    tmp1 = FfmpegIntDct.MULTIPLY(d7, (short)9633);
                    tmp2 = FfmpegIntDct.MULTIPLY(-d7, (short)6436);
                    tmp3 = FfmpegIntDct.MULTIPLY(d7, (short)2260);
                }
            } else if (d5 != 0) {
                if (d3 != 0) {
                    if (d1 != 0) {
                        z2 = d5 + d3;
                        z4 = d5 + d1;
                        z5 = FfmpegIntDct.MULTIPLY(d3 + z4, (short)9633);
                        tmp1 = FfmpegIntDct.MULTIPLY(d5, (short)16819);
                        tmp2 = FfmpegIntDct.MULTIPLY(d3, (short)25172);
                        tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)12299);
                        z1 = FfmpegIntDct.MULTIPLY(-d1, (short)7373);
                        z2 = FfmpegIntDct.MULTIPLY(-z2, (short)20995);
                        z3 = FfmpegIntDct.MULTIPLY(-d3, (short)16069);
                        z4 = FfmpegIntDct.MULTIPLY(-z4, (short)3196);
                        tmp0 = z1 + (z3 += z5);
                        tmp1 += z2 + (z4 += z5);
                        tmp2 += z2 + z3;
                        tmp3 += z1 + z4;
                    } else {
                        z2 = d5 + d3;
                        z5 = FfmpegIntDct.MULTIPLY(z2, (short)9633);
                        tmp1 = FfmpegIntDct.MULTIPLY(d5, (short)13623);
                        z4 = FfmpegIntDct.MULTIPLY(-d5, (short)3196);
                        z2 = FfmpegIntDct.MULTIPLY(-z2, (short)11363);
                        tmp2 = FfmpegIntDct.MULTIPLY(d3, (short)9102);
                        z3 = FfmpegIntDct.MULTIPLY(-d3, (short)16069);
                        tmp0 = z3 + z5;
                        tmp1 += z2;
                        tmp2 += z2;
                        tmp3 = z4 + z5;
                    }
                } else if (d1 != 0) {
                    z4 = d5 + d1;
                    z5 = FfmpegIntDct.MULTIPLY(z4, (short)9633);
                    z1 = FfmpegIntDct.MULTIPLY(-d1, (short)7373);
                    tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)4926);
                    tmp1 = FfmpegIntDct.MULTIPLY(-d5, (short)4176);
                    z2 = FfmpegIntDct.MULTIPLY(-d5, (short)20995);
                    z4 = FfmpegIntDct.MULTIPLY(z4, (short)6436);
                    tmp0 = z1 + z5;
                    tmp1 += z4;
                    tmp2 = z2 + z5;
                    tmp3 += z4;
                } else {
                    tmp0 = FfmpegIntDct.MULTIPLY(d5, (short)9633);
                    tmp1 = FfmpegIntDct.MULTIPLY(d5, (short)2260);
                    tmp2 = FfmpegIntDct.MULTIPLY(-d5, (short)11363);
                    tmp3 = FfmpegIntDct.MULTIPLY(d5, (short)6436);
                }
            } else if (d3 != 0) {
                if (d1 != 0) {
                    z5 = d1 + d3;
                    tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)1730);
                    tmp2 = FfmpegIntDct.MULTIPLY(-d3, (short)11893);
                    z1 = FfmpegIntDct.MULTIPLY(d1, (short)8697);
                    z2 = FfmpegIntDct.MULTIPLY(-d3, (short)17799);
                    z4 = FfmpegIntDct.MULTIPLY(z5, (short)6436);
                    z5 = FfmpegIntDct.MULTIPLY(z5, (short)9633);
                    tmp0 = z1 - z4;
                    tmp1 = z2 + z4;
                    tmp2 += z5;
                    tmp3 += z5;
                } else {
                    tmp0 = FfmpegIntDct.MULTIPLY(-d3, (short)6436);
                    tmp1 = FfmpegIntDct.MULTIPLY(-d3, (short)11363);
                    tmp2 = FfmpegIntDct.MULTIPLY(-d3, (short)2260);
                    tmp3 = FfmpegIntDct.MULTIPLY(d3, (short)9633);
                }
            } else if (d1 != 0) {
                tmp0 = FfmpegIntDct.MULTIPLY(d1, (short)2260);
                tmp1 = FfmpegIntDct.MULTIPLY(d1, (short)6436);
                tmp2 = FfmpegIntDct.MULTIPLY(d1, (short)9633);
                tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)11363);
            } else {
                tmp3 = 0;
                tmp2 = 0;
                tmp1 = 0;
                tmp0 = 0;
            }
            dataptr.put(0, FfmpegIntDct.DESCALE11(tmp10 + tmp3));
            dataptr.put(7, FfmpegIntDct.DESCALE11(tmp10 - tmp3));
            dataptr.put(1, FfmpegIntDct.DESCALE11(tmp11 + tmp2));
            dataptr.put(6, FfmpegIntDct.DESCALE11(tmp11 - tmp2));
            dataptr.put(2, FfmpegIntDct.DESCALE11(tmp12 + tmp1));
            dataptr.put(5, FfmpegIntDct.DESCALE11(tmp12 - tmp1));
            dataptr.put(3, FfmpegIntDct.DESCALE11(tmp13 + tmp0));
            dataptr.put(4, FfmpegIntDct.DESCALE11(tmp13 - tmp0));
            dataptr = FfmpegIntDct.advance(dataptr, 8);
        }
    }

    private static int MULTIPLY(int x, short y) {
        return y * (short)x;
    }

    private static final void pass2(ShortBuffer data) {
        ShortBuffer dataptr = data.duplicate();
        for (int rowctr = 7; rowctr >= 0; --rowctr) {
            int z5;
            int z4;
            int z3;
            int z2;
            int tmp12;
            int tmp11;
            int tmp13;
            int tmp10;
            int tmp1;
            int tmp0;
            int tmp3;
            int tmp2;
            int z1;
            short d0 = dataptr.get(0);
            short d1 = dataptr.get(8);
            short d2 = dataptr.get(16);
            short d3 = dataptr.get(24);
            short d4 = dataptr.get(32);
            short d5 = dataptr.get(40);
            short d6 = dataptr.get(48);
            short d7 = dataptr.get(56);
            if (d6 != 0) {
                if (d2 != 0) {
                    z1 = FfmpegIntDct.MULTIPLY(d2 + d6, (short)4433);
                    tmp2 = z1 + FfmpegIntDct.MULTIPLY(-d6, (short)15137);
                    tmp3 = z1 + FfmpegIntDct.MULTIPLY(d2, (short)6270);
                    tmp0 = d0 + d4 << 13;
                    tmp1 = d0 - d4 << 13;
                    tmp10 = tmp0 + tmp3;
                    tmp13 = tmp0 - tmp3;
                    tmp11 = tmp1 + tmp2;
                    tmp12 = tmp1 - tmp2;
                } else {
                    tmp2 = FfmpegIntDct.MULTIPLY(-d6, (short)10703);
                    tmp3 = FfmpegIntDct.MULTIPLY(d6, (short)4433);
                    tmp0 = d0 + d4 << 13;
                    tmp1 = d0 - d4 << 13;
                    tmp10 = tmp0 + tmp3;
                    tmp13 = tmp0 - tmp3;
                    tmp11 = tmp1 + tmp2;
                    tmp12 = tmp1 - tmp2;
                }
            } else if (d2 != 0) {
                tmp2 = FfmpegIntDct.MULTIPLY(d2, (short)4433);
                tmp3 = FfmpegIntDct.MULTIPLY(d2, (short)10703);
                tmp0 = d0 + d4 << 13;
                tmp1 = d0 - d4 << 13;
                tmp10 = tmp0 + tmp3;
                tmp13 = tmp0 - tmp3;
                tmp11 = tmp1 + tmp2;
                tmp12 = tmp1 - tmp2;
            } else {
                tmp10 = tmp13 = d0 + d4 << 13;
                tmp11 = tmp12 = d0 - d4 << 13;
            }
            if (d7 != 0) {
                if (d5 != 0) {
                    if (d3 != 0) {
                        if (d1 != 0) {
                            z1 = d7 + d1;
                            z2 = d5 + d3;
                            z3 = d7 + d3;
                            z4 = d5 + d1;
                            z5 = FfmpegIntDct.MULTIPLY(z3 + z4, (short)9633);
                            tmp0 = FfmpegIntDct.MULTIPLY(d7, (short)2446);
                            tmp1 = FfmpegIntDct.MULTIPLY(d5, (short)16819);
                            tmp2 = FfmpegIntDct.MULTIPLY(d3, (short)25172);
                            tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)12299);
                            z1 = FfmpegIntDct.MULTIPLY(-z1, (short)7373);
                            z2 = FfmpegIntDct.MULTIPLY(-z2, (short)20995);
                            z3 = FfmpegIntDct.MULTIPLY(-z3, (short)16069);
                            z4 = FfmpegIntDct.MULTIPLY(-z4, (short)3196);
                            tmp0 += z1 + (z3 += z5);
                            tmp1 += z2 + (z4 += z5);
                            tmp2 += z2 + z3;
                            tmp3 += z1 + z4;
                        } else {
                            z1 = d7;
                            z2 = d5 + d3;
                            z3 = d7 + d3;
                            z5 = FfmpegIntDct.MULTIPLY(z3 + d5, (short)9633);
                            tmp0 = FfmpegIntDct.MULTIPLY(d7, (short)2446);
                            tmp1 = FfmpegIntDct.MULTIPLY(d5, (short)16819);
                            tmp2 = FfmpegIntDct.MULTIPLY(d3, (short)25172);
                            z1 = FfmpegIntDct.MULTIPLY(-d7, (short)7373);
                            z2 = FfmpegIntDct.MULTIPLY(-z2, (short)20995);
                            z3 = FfmpegIntDct.MULTIPLY(-z3, (short)16069);
                            z4 = FfmpegIntDct.MULTIPLY(-d5, (short)3196);
                            tmp0 += z1 + (z3 += z5);
                            tmp1 += z2 + (z4 += z5);
                            tmp2 += z2 + z3;
                            tmp3 = z1 + z4;
                        }
                    } else if (d1 != 0) {
                        z1 = d7 + d1;
                        z2 = d5;
                        z3 = d7;
                        z4 = d5 + d1;
                        z5 = FfmpegIntDct.MULTIPLY(z3 + z4, (short)9633);
                        tmp0 = FfmpegIntDct.MULTIPLY(d7, (short)2446);
                        tmp1 = FfmpegIntDct.MULTIPLY(d5, (short)16819);
                        tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)12299);
                        z1 = FfmpegIntDct.MULTIPLY(-z1, (short)7373);
                        z2 = FfmpegIntDct.MULTIPLY(-d5, (short)20995);
                        z3 = FfmpegIntDct.MULTIPLY(-d7, (short)16069);
                        z4 = FfmpegIntDct.MULTIPLY(-z4, (short)3196);
                        tmp0 += z1 + (z3 += z5);
                        tmp1 += z2 + (z4 += z5);
                        tmp2 = z2 + z3;
                        tmp3 += z1 + z4;
                    } else {
                        tmp0 = FfmpegIntDct.MULTIPLY(-d7, (short)4926);
                        z1 = FfmpegIntDct.MULTIPLY(-d7, (short)7373);
                        z3 = FfmpegIntDct.MULTIPLY(-d7, (short)16069);
                        tmp1 = FfmpegIntDct.MULTIPLY(-d5, (short)4176);
                        z2 = FfmpegIntDct.MULTIPLY(-d5, (short)20995);
                        z4 = FfmpegIntDct.MULTIPLY(-d5, (short)3196);
                        z5 = FfmpegIntDct.MULTIPLY(d5 + d7, (short)9633);
                        tmp0 += (z3 += z5);
                        tmp1 += (z4 += z5);
                        tmp2 = z2 + z3;
                        tmp3 = z1 + z4;
                    }
                } else if (d3 != 0) {
                    if (d1 != 0) {
                        z1 = d7 + d1;
                        z3 = d7 + d3;
                        z5 = FfmpegIntDct.MULTIPLY(z3 + d1, (short)9633);
                        tmp0 = FfmpegIntDct.MULTIPLY(d7, (short)2446);
                        tmp2 = FfmpegIntDct.MULTIPLY(d3, (short)25172);
                        tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)12299);
                        z1 = FfmpegIntDct.MULTIPLY(-z1, (short)7373);
                        z2 = FfmpegIntDct.MULTIPLY(-d3, (short)20995);
                        z3 = FfmpegIntDct.MULTIPLY(-z3, (short)16069);
                        z4 = FfmpegIntDct.MULTIPLY(-d1, (short)3196);
                        tmp0 += z1 + (z3 += z5);
                        tmp1 = z2 + (z4 += z5);
                        tmp2 += z2 + z3;
                        tmp3 += z1 + z4;
                    } else {
                        z3 = d7 + d3;
                        tmp0 = FfmpegIntDct.MULTIPLY(-d7, (short)4926);
                        z1 = FfmpegIntDct.MULTIPLY(-d7, (short)7373);
                        tmp2 = FfmpegIntDct.MULTIPLY(d3, (short)4176);
                        z2 = FfmpegIntDct.MULTIPLY(-d3, (short)20995);
                        z5 = FfmpegIntDct.MULTIPLY(z3, (short)9633);
                        z3 = FfmpegIntDct.MULTIPLY(-z3, (short)6436);
                        tmp0 += z3;
                        tmp1 = z2 + z5;
                        tmp2 += z3;
                        tmp3 = z1 + z5;
                    }
                } else if (d1 != 0) {
                    z1 = d7 + d1;
                    z5 = FfmpegIntDct.MULTIPLY(z1, (short)9633);
                    z1 = FfmpegIntDct.MULTIPLY(z1, (short)2260);
                    z3 = FfmpegIntDct.MULTIPLY(-d7, (short)16069);
                    tmp0 = FfmpegIntDct.MULTIPLY(-d7, (short)13623);
                    z4 = FfmpegIntDct.MULTIPLY(-d1, (short)3196);
                    tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)9102);
                    tmp0 += z1;
                    tmp1 = z4 + z5;
                    tmp2 = z3 + z5;
                    tmp3 += z1;
                } else {
                    tmp0 = FfmpegIntDct.MULTIPLY(-d7, (short)11363);
                    tmp1 = FfmpegIntDct.MULTIPLY(d7, (short)9633);
                    tmp2 = FfmpegIntDct.MULTIPLY(-d7, (short)6436);
                    tmp3 = FfmpegIntDct.MULTIPLY(d7, (short)2260);
                }
            } else if (d5 != 0) {
                if (d3 != 0) {
                    if (d1 != 0) {
                        z2 = d5 + d3;
                        z4 = d5 + d1;
                        z5 = FfmpegIntDct.MULTIPLY(d3 + z4, (short)9633);
                        tmp1 = FfmpegIntDct.MULTIPLY(d5, (short)16819);
                        tmp2 = FfmpegIntDct.MULTIPLY(d3, (short)25172);
                        tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)12299);
                        z1 = FfmpegIntDct.MULTIPLY(-d1, (short)7373);
                        z2 = FfmpegIntDct.MULTIPLY(-z2, (short)20995);
                        z3 = FfmpegIntDct.MULTIPLY(-d3, (short)16069);
                        z4 = FfmpegIntDct.MULTIPLY(-z4, (short)3196);
                        tmp0 = z1 + (z3 += z5);
                        tmp1 += z2 + (z4 += z5);
                        tmp2 += z2 + z3;
                        tmp3 += z1 + z4;
                    } else {
                        z2 = d5 + d3;
                        z5 = FfmpegIntDct.MULTIPLY(z2, (short)9633);
                        tmp1 = FfmpegIntDct.MULTIPLY(d5, (short)13623);
                        z4 = FfmpegIntDct.MULTIPLY(-d5, (short)3196);
                        z2 = FfmpegIntDct.MULTIPLY(-z2, (short)11363);
                        tmp2 = FfmpegIntDct.MULTIPLY(d3, (short)9102);
                        z3 = FfmpegIntDct.MULTIPLY(-d3, (short)16069);
                        tmp0 = z3 + z5;
                        tmp1 += z2;
                        tmp2 += z2;
                        tmp3 = z4 + z5;
                    }
                } else if (d1 != 0) {
                    z4 = d5 + d1;
                    z5 = FfmpegIntDct.MULTIPLY(z4, (short)9633);
                    z1 = FfmpegIntDct.MULTIPLY(-d1, (short)7373);
                    tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)4926);
                    tmp1 = FfmpegIntDct.MULTIPLY(-d5, (short)4176);
                    z2 = FfmpegIntDct.MULTIPLY(-d5, (short)20995);
                    z4 = FfmpegIntDct.MULTIPLY(z4, (short)6436);
                    tmp0 = z1 + z5;
                    tmp1 += z4;
                    tmp2 = z2 + z5;
                    tmp3 += z4;
                } else {
                    tmp0 = FfmpegIntDct.MULTIPLY(d5, (short)9633);
                    tmp1 = FfmpegIntDct.MULTIPLY(d5, (short)2260);
                    tmp2 = FfmpegIntDct.MULTIPLY(-d5, (short)11363);
                    tmp3 = FfmpegIntDct.MULTIPLY(d5, (short)6436);
                }
            } else if (d3 != 0) {
                if (d1 != 0) {
                    z5 = d1 + d3;
                    tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)1730);
                    tmp2 = FfmpegIntDct.MULTIPLY(-d3, (short)11893);
                    z1 = FfmpegIntDct.MULTIPLY(d1, (short)8697);
                    z2 = FfmpegIntDct.MULTIPLY(-d3, (short)17799);
                    z4 = FfmpegIntDct.MULTIPLY(z5, (short)6436);
                    z5 = FfmpegIntDct.MULTIPLY(z5, (short)9633);
                    tmp0 = z1 - z4;
                    tmp1 = z2 + z4;
                    tmp2 += z5;
                    tmp3 += z5;
                } else {
                    tmp0 = FfmpegIntDct.MULTIPLY(-d3, (short)6436);
                    tmp1 = FfmpegIntDct.MULTIPLY(-d3, (short)11363);
                    tmp2 = FfmpegIntDct.MULTIPLY(-d3, (short)2260);
                    tmp3 = FfmpegIntDct.MULTIPLY(d3, (short)9633);
                }
            } else if (d1 != 0) {
                tmp0 = FfmpegIntDct.MULTIPLY(d1, (short)2260);
                tmp1 = FfmpegIntDct.MULTIPLY(d1, (short)6436);
                tmp2 = FfmpegIntDct.MULTIPLY(d1, (short)9633);
                tmp3 = FfmpegIntDct.MULTIPLY(d1, (short)11363);
            } else {
                tmp3 = 0;
                tmp2 = 0;
                tmp1 = 0;
                tmp0 = 0;
            }
            dataptr.put(0, FfmpegIntDct.DESCALE18(tmp10 + tmp3));
            dataptr.put(56, FfmpegIntDct.DESCALE18(tmp10 - tmp3));
            dataptr.put(8, FfmpegIntDct.DESCALE18(tmp11 + tmp2));
            dataptr.put(48, FfmpegIntDct.DESCALE18(tmp11 - tmp2));
            dataptr.put(16, FfmpegIntDct.DESCALE18(tmp12 + tmp1));
            dataptr.put(40, FfmpegIntDct.DESCALE18(tmp12 - tmp1));
            dataptr.put(24, FfmpegIntDct.DESCALE18(tmp13 + tmp0));
            dataptr.put(32, FfmpegIntDct.DESCALE18(tmp13 - tmp0));
            dataptr = FfmpegIntDct.advance(dataptr, 1);
        }
    }

    private static final int DESCALE(int x, int n) {
        return x + (1 << n - 1) >> n;
    }

    private static final short DESCALE11(int x) {
        return (short)(x + 1024 >> 11);
    }

    private static final short DESCALE18(int x) {
        return (short)(x + 131072 >> 18);
    }
}
