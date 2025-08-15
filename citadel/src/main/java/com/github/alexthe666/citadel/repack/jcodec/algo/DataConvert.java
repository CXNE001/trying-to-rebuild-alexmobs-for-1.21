/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.algo;

import com.github.alexthe666.citadel.repack.jcodec.api.NotSupportedException;

public class DataConvert {
    public static int[] from16BE(byte[] b) {
        int[] result = new int[b.length >> 1];
        int off = 0;
        for (int i = 0; i < result.length; ++i) {
            result[i] = (b[off++] & 0xFF) << 8 | b[off++] & 0xFF;
        }
        return result;
    }

    public static int[] from24BE(byte[] b) {
        int[] result = new int[b.length / 3];
        int off = 0;
        for (int i = 0; i < result.length; ++i) {
            result[i] = (b[off++] & 0xFF) << 16 | (b[off++] & 0xFF) << 8 | b[off++] & 0xFF;
        }
        return result;
    }

    public static int[] from16LE(byte[] b) {
        int[] result = new int[b.length >> 1];
        int off = 0;
        for (int i = 0; i < result.length; ++i) {
            result[i] = b[off++] & 0xFF | (b[off++] & 0xFF) << 8;
        }
        return result;
    }

    public static int[] from24LE(byte[] b) {
        int[] result = new int[b.length / 3];
        int off = 0;
        for (int i = 0; i < result.length; ++i) {
            result[i] = b[off++] & 0xFF | (b[off++] & 0xFF) << 8 | (b[off++] & 0xFF) << 16;
        }
        return result;
    }

    public static byte[] to16BE(int[] ia) {
        byte[] result = new byte[ia.length << 1];
        int off = 0;
        for (int i = 0; i < ia.length; ++i) {
            result[off++] = (byte)(ia[i] >> 8 & 0xFF);
            result[off++] = (byte)(ia[i] & 0xFF);
        }
        return result;
    }

    public static byte[] to24BE(int[] ia) {
        byte[] result = new byte[ia.length * 3];
        int off = 0;
        for (int i = 0; i < ia.length; ++i) {
            result[off++] = (byte)(ia[i] >> 16 & 0xFF);
            result[off++] = (byte)(ia[i] >> 8 & 0xFF);
            result[off++] = (byte)(ia[i] & 0xFF);
        }
        return result;
    }

    public static byte[] to16LE(int[] ia) {
        byte[] result = new byte[ia.length << 1];
        int off = 0;
        for (int i = 0; i < ia.length; ++i) {
            result[off++] = (byte)(ia[i] & 0xFF);
            result[off++] = (byte)(ia[i] >> 8 & 0xFF);
        }
        return result;
    }

    public static byte[] to24LE(int[] ia) {
        byte[] result = new byte[ia.length * 3];
        int off = 0;
        for (int i = 0; i < ia.length; ++i) {
            result[off++] = (byte)(ia[i] & 0xFF);
            result[off++] = (byte)(ia[i] >> 8 & 0xFF);
            result[off++] = (byte)(ia[i] >> 16 & 0xFF);
        }
        return result;
    }

    public static int[] fromByte(byte[] b, int depth, boolean isBe) {
        if (depth == 24) {
            if (isBe) {
                return DataConvert.from24BE(b);
            }
            return DataConvert.from24LE(b);
        }
        if (depth == 16) {
            if (isBe) {
                return DataConvert.from16BE(b);
            }
            return DataConvert.from16LE(b);
        }
        throw new NotSupportedException("Conversion from " + depth + "bit " + (isBe ? "big endian" : "little endian") + " is not supported.");
    }

    public static byte[] toByte(int[] ia, int depth, boolean isBe) {
        if (depth == 24) {
            if (isBe) {
                return DataConvert.to24BE(ia);
            }
            return DataConvert.to24LE(ia);
        }
        if (depth == 16) {
            if (isBe) {
                return DataConvert.to16BE(ia);
            }
            return DataConvert.to16LE(ia);
        }
        throw new NotSupportedException("Conversion to " + depth + "bit " + (isBe ? "big endian" : "little endian") + " is not supported.");
    }
}
