/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.containers.mkv.util;

public class EbmlUtil {
    public static final byte[] lengthOptions = new byte[]{0, -128, 64, 32, 16, 8, 4, 2, 1};
    public static final long one = 127L;
    public static final long two = 16256L;
    public static final long three = 2080768L;
    public static final long four = 0xFE00000L;
    public static final long five = 0x7F0000000L;
    public static final long six = 4363686772736L;
    public static final long seven = 558551906910208L;
    public static final long eight = 0xFE000000000000L;
    public static final long[] ebmlLengthMasks = new long[]{0L, 127L, 16256L, 2080768L, 0xFE00000L, 0x7F0000000L, 4363686772736L, 558551906910208L, 0xFE000000000000L};

    public static byte[] ebmlEncodeLen(long value, int length) {
        byte[] b = new byte[length];
        for (int idx = 0; idx < length; ++idx) {
            b[length - idx - 1] = (byte)(value >>> 8 * idx & 0xFFL);
        }
        b[0] = (byte)(b[0] | 128 >>> length - 1);
        return b;
    }

    public static byte[] ebmlEncode(long value) {
        return EbmlUtil.ebmlEncodeLen(value, EbmlUtil.ebmlLength(value));
    }

    public static int computeLength(byte b) {
        if (b == 0) {
            throw new RuntimeException("Invalid head element for ebml sequence");
        }
        int i = 1;
        while ((b & lengthOptions[i]) == 0) {
            ++i;
        }
        return i;
    }

    public static int ebmlLength(long v) {
        int length;
        if (v == 0L) {
            return 1;
        }
        for (length = 8; length > 0 && (v & ebmlLengthMasks[length]) == 0L; --length) {
        }
        return length;
    }

    public static String toHexString(byte[] a) {
        StringBuilder sb = new StringBuilder();
        for (byte b : a) {
            sb.append(String.format("0x%02x ", b & 0xFF));
        }
        return sb.toString();
    }
}
