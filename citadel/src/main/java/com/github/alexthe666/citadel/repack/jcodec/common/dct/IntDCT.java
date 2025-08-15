/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.common.dct;

import com.github.alexthe666.citadel.repack.jcodec.common.dct.DCT;
import java.nio.IntBuffer;

public class IntDCT
extends DCT {
    public static final IntDCT INSTANCE = new IntDCT();
    private static final int DCTSIZE = 8;
    private static final int PASS1_BITS = 2;
    private static final int MAXJSAMPLE = 255;
    private static final int CENTERJSAMPLE = 128;
    private static final int RANGE_MASK = 1023;
    private static final IntBuffer sample_range_limit = IntBuffer.allocate(1408);
    private static final IntBuffer idct_sample_range_limit = IntBuffer.allocate(sample_range_limit.capacity() - 128);
    private static final int CONST_BITS = 13;
    private static final int ONE_HALF = 4096;
    private static final int FIX_0_298631336;
    private static final int FIX_0_390180644;
    private static final int FIX_0_541196100;
    private static final int FIX_0_765366865;
    private static final int FIX_0_899976223;
    private static final int FIX_1_175875602;
    private static final int FIX_1_501321110;
    private static final int FIX_1_847759065;
    private static final int FIX_1_961570560;
    private static final int FIX_2_053119869;
    private static final int FIX_2_562915447;
    private static final int FIX_3_072711026;

    @Override
    public int[] decode(int[] orig) {
        IntBuffer inptr = IntBuffer.wrap(orig);
        IntBuffer workspace = IntBuffer.allocate(64);
        IntBuffer outptr = IntBuffer.allocate(64);
        IntDCT.doDecode(inptr, workspace, outptr);
        return outptr.array();
    }

    protected static IntBuffer doDecode(IntBuffer inptr, IntBuffer workspace, IntBuffer outptr) {
        IntDCT.pass1(inptr, workspace.duplicate());
        IntDCT.pass2(outptr, workspace.duplicate());
        return outptr;
    }

    private static void pass2(IntBuffer outptr, IntBuffer wsptr) {
        for (int ctr = 0; ctr < 8; ++ctr) {
            int z2 = wsptr.get(2);
            int z3 = wsptr.get(6);
            int z1 = IntDCT.MULTIPLY(z2 + z3, FIX_0_541196100);
            int tmp2 = z1 + IntDCT.MULTIPLY(z3, -FIX_1_847759065);
            int tmp3 = z1 + IntDCT.MULTIPLY(z2, FIX_0_765366865);
            int tmp0 = wsptr.get(0) + wsptr.get(4) << 13;
            int tmp1 = wsptr.get(0) - wsptr.get(4) << 13;
            int tmp10 = tmp0 + tmp3;
            int tmp13 = tmp0 - tmp3;
            int tmp11 = tmp1 + tmp2;
            int tmp12 = tmp1 - tmp2;
            tmp0 = wsptr.get(7);
            tmp1 = wsptr.get(5);
            tmp2 = wsptr.get(3);
            tmp3 = wsptr.get(1);
            z1 = tmp0 + tmp3;
            z2 = tmp1 + tmp2;
            z3 = tmp0 + tmp2;
            int z4 = tmp1 + tmp3;
            int z5 = IntDCT.MULTIPLY(z3 + z4, FIX_1_175875602);
            tmp0 = IntDCT.MULTIPLY(tmp0, FIX_0_298631336);
            tmp1 = IntDCT.MULTIPLY(tmp1, FIX_2_053119869);
            tmp2 = IntDCT.MULTIPLY(tmp2, FIX_3_072711026);
            tmp3 = IntDCT.MULTIPLY(tmp3, FIX_1_501321110);
            z1 = IntDCT.MULTIPLY(z1, -FIX_0_899976223);
            z2 = IntDCT.MULTIPLY(z2, -FIX_2_562915447);
            z3 = IntDCT.MULTIPLY(z3, -FIX_1_961570560);
            z4 = IntDCT.MULTIPLY(z4, -FIX_0_390180644);
            tmp0 += z1 + (z3 += z5);
            tmp1 += z2 + (z4 += z5);
            int D = 18;
            outptr.put(IntDCT.range_limit(IntDCT.DESCALE(tmp10 + (tmp3 += z1 + z4), D) & 0x3FF));
            outptr.put(IntDCT.range_limit(IntDCT.DESCALE(tmp11 + (tmp2 += z2 + z3), D) & 0x3FF));
            outptr.put(IntDCT.range_limit(IntDCT.DESCALE(tmp12 + tmp1, D) & 0x3FF));
            outptr.put(IntDCT.range_limit(IntDCT.DESCALE(tmp13 + tmp0, D) & 0x3FF));
            outptr.put(IntDCT.range_limit(IntDCT.DESCALE(tmp13 - tmp0, D) & 0x3FF));
            outptr.put(IntDCT.range_limit(IntDCT.DESCALE(tmp12 - tmp1, D) & 0x3FF));
            outptr.put(IntDCT.range_limit(IntDCT.DESCALE(tmp11 - tmp2, D) & 0x3FF));
            outptr.put(IntDCT.range_limit(IntDCT.DESCALE(tmp10 - tmp3, D) & 0x3FF));
            wsptr = IntDCT.doAdvance(wsptr, 8);
        }
    }

    public static int range_limit(int i) {
        return idct_sample_range_limit.get(i + 256);
    }

    private static void prepare_range_limit_table() {
        int i;
        sample_range_limit.position(256);
        for (i = 0; i < 128; ++i) {
            sample_range_limit.put(i);
        }
        for (i = -128; i < 0; ++i) {
            sample_range_limit.put(i);
        }
        for (i = 0; i < 384; ++i) {
            sample_range_limit.put(-1);
        }
        for (i = 0; i < 384; ++i) {
            sample_range_limit.put(0);
        }
        for (i = 0; i < 128; ++i) {
            sample_range_limit.put(i);
        }
        for (i = 0; i < idct_sample_range_limit.capacity(); ++i) {
            idct_sample_range_limit.put(sample_range_limit.get(i + 128) & 0xFF);
        }
    }

    private static boolean shortcut(IntBuffer inptr, IntBuffer wsptr) {
        if (inptr.get(8) == 0 && inptr.get(16) == 0 && inptr.get(24) == 0 && inptr.get(32) == 0 && inptr.get(40) == 0 && inptr.get(48) == 0 && inptr.get(56) == 0) {
            int dcval = inptr.get(0) << 2;
            wsptr.put(0, dcval);
            wsptr.put(8, dcval);
            wsptr.put(16, dcval);
            wsptr.put(24, dcval);
            wsptr.put(32, dcval);
            wsptr.put(40, dcval);
            wsptr.put(48, dcval);
            wsptr.put(56, dcval);
            inptr = IntDCT.advance(inptr);
            wsptr = IntDCT.advance(wsptr);
            return true;
        }
        return false;
    }

    private static void pass1(IntBuffer inptr, IntBuffer wsptr) {
        for (int ctr = 8; ctr > 0; --ctr) {
            int z2 = inptr.get(16);
            int z3 = inptr.get(48);
            int z1 = IntDCT.MULTIPLY(z2 + z3, FIX_0_541196100);
            int tmp2 = z1 + IntDCT.MULTIPLY(z3, -FIX_1_847759065);
            int tmp3 = z1 + IntDCT.MULTIPLY(z2, FIX_0_765366865);
            z2 = inptr.get(0);
            z3 = inptr.get(32);
            int tmp0 = z2 + z3 << 13;
            int tmp1 = z2 - z3 << 13;
            int tmp10 = tmp0 + tmp3;
            int tmp13 = tmp0 - tmp3;
            int tmp11 = tmp1 + tmp2;
            int tmp12 = tmp1 - tmp2;
            tmp0 = inptr.get(56);
            tmp1 = inptr.get(40);
            tmp2 = inptr.get(24);
            tmp3 = inptr.get(8);
            z1 = tmp0 + tmp3;
            z2 = tmp1 + tmp2;
            z3 = tmp0 + tmp2;
            int z4 = tmp1 + tmp3;
            int z5 = IntDCT.MULTIPLY(z3 + z4, FIX_1_175875602);
            tmp0 = IntDCT.MULTIPLY(tmp0, FIX_0_298631336);
            tmp1 = IntDCT.MULTIPLY(tmp1, FIX_2_053119869);
            tmp2 = IntDCT.MULTIPLY(tmp2, FIX_3_072711026);
            tmp3 = IntDCT.MULTIPLY(tmp3, FIX_1_501321110);
            z1 = IntDCT.MULTIPLY(z1, -FIX_0_899976223);
            z2 = IntDCT.MULTIPLY(z2, -FIX_2_562915447);
            z3 = IntDCT.MULTIPLY(z3, -FIX_1_961570560);
            z4 = IntDCT.MULTIPLY(z4, -FIX_0_390180644);
            tmp0 += z1 + (z3 += z5);
            tmp1 += z2 + (z4 += z5);
            tmp2 += z2 + z3;
            int D = 11;
            wsptr.put(0, IntDCT.DESCALE(tmp10 + (tmp3 += z1 + z4), D));
            wsptr.put(56, IntDCT.DESCALE(tmp10 - tmp3, D));
            wsptr.put(8, IntDCT.DESCALE(tmp11 + tmp2, D));
            wsptr.put(48, IntDCT.DESCALE(tmp11 - tmp2, D));
            wsptr.put(16, IntDCT.DESCALE(tmp12 + tmp1, D));
            wsptr.put(40, IntDCT.DESCALE(tmp12 - tmp1, D));
            wsptr.put(24, IntDCT.DESCALE(tmp13 + tmp0, D));
            wsptr.put(32, IntDCT.DESCALE(tmp13 - tmp0, D));
            inptr = IntDCT.advance(inptr);
            wsptr = IntDCT.advance(wsptr);
        }
    }

    private static IntBuffer advance(IntBuffer ptr) {
        return IntDCT.doAdvance(ptr, 1);
    }

    private static IntBuffer doAdvance(IntBuffer ptr, int size) {
        ptr.position(ptr.position() + size);
        return ptr.slice();
    }

    static int DESCALE(int x, int n) {
        return IntDCT.RIGHT_SHIFT(x + (1 << n - 1), n);
    }

    private static int RIGHT_SHIFT(int x, int shft) {
        return x >> shft;
    }

    private static int MULTIPLY(int i, int j) {
        return i * j;
    }

    private static int FIX(double x) {
        return (int)(x * 8192.0 + 0.5);
    }

    static {
        IntDCT.prepare_range_limit_table();
        FIX_0_298631336 = IntDCT.FIX(0.298631336);
        FIX_0_390180644 = IntDCT.FIX(0.390180644);
        FIX_0_541196100 = IntDCT.FIX(0.5411961);
        FIX_0_765366865 = IntDCT.FIX(0.765366865);
        FIX_0_899976223 = IntDCT.FIX(0.899976223);
        FIX_1_175875602 = IntDCT.FIX(1.175875602);
        FIX_1_501321110 = IntDCT.FIX(1.50132111);
        FIX_1_847759065 = IntDCT.FIX(1.847759065);
        FIX_1_961570560 = IntDCT.FIX(1.96157056);
        FIX_2_053119869 = IntDCT.FIX(2.053119869);
        FIX_2_562915447 = IntDCT.FIX(2.562915447);
        FIX_3_072711026 = IntDCT.FIX(3.072711026);
    }
}
