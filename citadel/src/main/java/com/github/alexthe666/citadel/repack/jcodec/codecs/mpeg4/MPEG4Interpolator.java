/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4;

import com.github.alexthe666.citadel.repack.jcodec.codecs.mpeg4.MPEG4Consts;
import com.github.alexthe666.citadel.repack.jcodec.common.tools.MathUtil;

public class MPEG4Interpolator {
    private static byte[] qpi = new byte[272];

    public static final void fulpel8x8(byte[] dst, int dstOff, int dstStride, byte[] src, int srcCol, int srcRow, int srcWidth, int srcHeight, int srcStride) {
        if (srcCol < 0 || srcRow < 0 || srcCol > srcWidth - 8 || srcRow > srcHeight - 8) {
            int j = 0;
            while (j < 8) {
                for (int i = 0; i < 8; ++i) {
                    int y = MathUtil.clip(srcRow + j, 0, srcHeight - 1);
                    int x = MathUtil.clip(srcCol + i, 0, srcWidth - 1);
                    dst[dstOff + i] = src[srcStride * y + x];
                }
                ++j;
                dstOff += dstStride;
            }
        } else {
            int srcOffset = srcRow * srcStride + srcCol;
            int j = 0;
            while (j < 8) {
                for (int i = 0; i < 8; ++i) {
                    dst[dstOff + i] = src[srcOffset + i];
                }
                ++j;
                dstOff += dstStride;
                srcOffset += srcStride;
            }
        }
    }

    public static final void fulpel16x16(byte[] dst, byte[] src, int srcCol, int srcRow, int srcWidth, int srcHeight, int srcStride) {
        if (srcCol < 0 || srcRow < 0 || srcCol > srcWidth - 16 || srcRow > srcHeight - 16) {
            for (int j = 0; j < 16; ++j) {
                for (int i = 0; i < 16; ++i) {
                    int y = MathUtil.clip(srcRow + j, 0, srcHeight - 1);
                    int x = MathUtil.clip(srcCol + i, 0, srcWidth - 1);
                    dst[(j << 4) + i] = src[srcStride * y + x];
                }
            }
        } else {
            int srcOffset = srcRow * srcStride + srcCol;
            for (int j = 0; j < 16; ++j) {
                for (int i = 0; i < 16; ++i) {
                    dst[(j << 4) + i] = src[srcOffset + (j * srcStride + i)];
                }
            }
        }
    }

    public static final void interpolate16x16QP(byte[] dst, byte[] ref, int x, int y, int w, int h, int dx, int dy, int refs, boolean rounding) {
        int xRef = x * 4 + dx;
        int yRef = y * 4 + dy;
        int location = dx & 3 | (dy & 3) << 2;
        int xFull = xRef / 4;
        if (xRef < 0 && (xRef & 3) != 0) {
            --xFull;
        }
        int yFull = yRef / 4;
        if (yRef < 0 && (yRef & 3) != 0) {
            --yFull;
        }
        switch (location) {
            case 0: {
                MPEG4Interpolator.fulpel16x16(dst, ref, xFull, yFull, w, h, refs);
                break;
            }
            case 1: {
                MPEG4Interpolator.horzMiddle16(dst, ref, xFull, yFull, w, h, 16, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff(dst, ref, xFull, yFull, w, h, 16, refs, rounding ? 1 : 0);
                break;
            }
            case 2: {
                MPEG4Interpolator.horzMiddle16(dst, ref, xFull, yFull, w, h, 16, refs, rounding ? 1 : 0);
                break;
            }
            case 3: {
                MPEG4Interpolator.horzMiddle16(dst, ref, xFull, yFull, w, h, 16, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff(dst, ref, xFull + 1, yFull, w, h, 16, refs, rounding ? 1 : 0);
                break;
            }
            case 4: {
                MPEG4Interpolator.vertMiddle16(dst, ref, xFull, yFull, w, h, 16, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff(dst, ref, xFull, yFull, w, h, 16, refs, rounding ? 1 : 0);
                break;
            }
            case 5: {
                MPEG4Interpolator.horzMiddle16(qpi, ref, xFull, yFull, w, h, 17, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff(qpi, ref, xFull, yFull, w, h, 17, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle16Safe(dst, qpi, 0, 16, 16, rounding ? 1 : 0);
                MPEG4Interpolator.qOffSafe(dst, qpi, 0, 16, 16, rounding ? 1 : 0);
                break;
            }
            case 6: {
                MPEG4Interpolator.horzMiddle16(qpi, ref, xFull, yFull, w, h, 17, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle16Safe(dst, qpi, 0, 16, 16, rounding ? 1 : 0);
                MPEG4Interpolator.qOffSafe(dst, qpi, 0, 16, 16, rounding ? 1 : 0);
                break;
            }
            case 7: {
                MPEG4Interpolator.horzMiddle16(qpi, ref, xFull, yFull, w, h, 17, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff(qpi, ref, xFull + 1, yFull, w, h, 17, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle16Safe(dst, qpi, 0, 16, 16, rounding ? 1 : 0);
                MPEG4Interpolator.qOffSafe(dst, qpi, 0, 16, 16, rounding ? 1 : 0);
                break;
            }
            case 8: {
                MPEG4Interpolator.vertMiddle16(dst, ref, xFull, yFull, w, h, 16, refs, rounding ? 1 : 0);
                break;
            }
            case 9: {
                MPEG4Interpolator.horzMiddle16(qpi, ref, xFull, yFull, w, h, 17, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff(qpi, ref, xFull, yFull, w, h, 17, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle16Safe(dst, qpi, 0, 16, 16, rounding ? 1 : 0);
                break;
            }
            case 10: {
                MPEG4Interpolator.horzMiddle16(qpi, ref, xFull, yFull, w, h, 17, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle16Safe(dst, qpi, 0, 16, 16, rounding ? 1 : 0);
                break;
            }
            case 11: {
                MPEG4Interpolator.horzMiddle16(qpi, ref, xFull, yFull, w, h, 17, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff(qpi, ref, xFull + 1, yFull, w, h, 17, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle16Safe(dst, qpi, 0, 16, 16, rounding ? 1 : 0);
                break;
            }
            case 12: {
                MPEG4Interpolator.vertMiddle16(dst, ref, xFull, yFull, w, h, 16, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff(dst, ref, xFull, yFull + 1, w, h, 16, refs, rounding ? 1 : 0);
                break;
            }
            case 13: {
                MPEG4Interpolator.horzMiddle16(qpi, ref, xFull, yFull, w, h, 17, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff(qpi, ref, xFull, yFull, w, h, 17, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle16Safe(dst, qpi, 0, 16, 16, rounding ? 1 : 0);
                MPEG4Interpolator.qOffSafe(dst, qpi, 16, 16, 16, rounding ? 1 : 0);
                break;
            }
            case 14: {
                MPEG4Interpolator.horzMiddle16(qpi, ref, xFull, yFull, w, h, 17, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle16Safe(dst, qpi, 0, 16, 16, rounding ? 1 : 0);
                MPEG4Interpolator.qOffSafe(dst, qpi, 16, 16, 16, rounding ? 1 : 0);
                break;
            }
            case 15: {
                MPEG4Interpolator.horzMiddle16(qpi, ref, xFull, yFull, w, h, 17, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff(qpi, ref, xFull + 1, yFull, w, h, 17, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle16Safe(dst, qpi, 0, 16, 16, rounding ? 1 : 0);
                MPEG4Interpolator.qOffSafe(dst, qpi, 16, 16, 16, rounding ? 1 : 0);
            }
        }
    }

    private static void qOffSafe(byte[] dst, byte[] src, int srcOffset, int height, int srcStride, int round) {
        int row = 0;
        int dstOff = 0;
        while (row < height) {
            int col = 0;
            while (col < 16) {
                dst[dstOff] = (byte)(dst[dstOff] + src[srcOffset + col] + 1 >> 1);
                ++col;
                ++dstOff;
            }
            ++row;
            srcOffset += srcStride;
        }
    }

    private static void qOff(byte[] dst, byte[] src, int x, int y, int w, int h, int height, int srcStride, int round) {
        if (x < 0 || y < 0 || x > w - 16 || y > h - height) {
            int dstOff = 0;
            for (int row = 0; row < height; ++row) {
                int o0 = MathUtil.clip(y + row, 0, h - 1) * srcStride;
                int col = 0;
                while (col < 16) {
                    int srcOffset = o0 + MathUtil.clip(x + col, 0, w - 1);
                    dst[dstOff] = (byte)(dst[dstOff] + src[srcOffset] + 1 >> 1);
                    ++col;
                    ++dstOff;
                }
            }
        } else {
            MPEG4Interpolator.qOffSafe(dst, src, y * srcStride + x, height, srcStride, round);
        }
    }

    private static void qOff8x8Safe(byte[] dst, int dstOff, byte[] src, int srcOffset, int height, int srcStride, int round) {
        int row = 0;
        while (row < height) {
            int col = 0;
            while (col < 8) {
                dst[dstOff] = (byte)(dst[dstOff] + src[srcOffset + col] + 1 >> 1);
                ++col;
                ++dstOff;
            }
            ++row;
            srcOffset += srcStride;
            dstOff += 8;
        }
    }

    private static void qOff8x8(byte[] dst, int dstOff, byte[] src, int x, int y, int w, int h, int height, int srcStride, int round) {
        if (x < 0 || y < 0 || x > w - 8 || y > h - height) {
            int row = 0;
            while (row < height) {
                int o0 = MathUtil.clip(y + row, 0, h - 1) * srcStride;
                int col = 0;
                while (col < 8) {
                    int srcOffset = o0 + MathUtil.clip(x + col, 0, w - 1);
                    dst[dstOff] = (byte)(dst[dstOff] + src[srcOffset] + 1 >> 1);
                    ++col;
                    ++dstOff;
                }
                ++row;
                dstOff += 8;
            }
        } else {
            MPEG4Interpolator.qOff8x8Safe(dst, dstOff, src, y * srcStride + x, height, srcStride, round);
        }
    }

    public static final void interpolate8x8QP(byte[] dst, int dstO, byte[] ref, int x, int y, int w, int h, int dx, int dy, int refs, boolean rounding) {
        int xRef = x * 4 + dx;
        int yRef = y * 4 + dy;
        int quads = dx & 3 | (dy & 3) << 2;
        int xInt = xRef / 4;
        if (xRef < 0 && xRef % 4 != 0) {
            --xInt;
        }
        int yInt = yRef / 4;
        if (yRef < 0 && yRef % 4 != 0) {
            --yInt;
        }
        switch (quads) {
            case 0: {
                MPEG4Interpolator.fulpel8x8(dst, dstO, 16, ref, xInt, yInt, w, h, refs);
                break;
            }
            case 1: {
                MPEG4Interpolator.horzMiddle8(dst, dstO, ref, xInt, yInt, w, h, 8, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8(dst, dstO, ref, xInt, yInt, w, h, 8, refs, rounding ? 1 : 0);
                break;
            }
            case 2: {
                MPEG4Interpolator.horzMiddle8(dst, dstO, ref, xInt, yInt, w, h, 8, refs, rounding ? 1 : 0);
                break;
            }
            case 3: {
                MPEG4Interpolator.horzMiddle8(dst, dstO, ref, xInt, yInt, w, h, 8, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8(dst, dstO, ref, xInt + 1, yInt, w, h, 8, refs, rounding ? 1 : 0);
                break;
            }
            case 4: {
                MPEG4Interpolator.vertMiddle8(dst, dstO, ref, xInt, yInt, w, h, 8, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8(dst, dstO, ref, xInt, yInt, w, h, 8, refs, rounding ? 1 : 0);
                break;
            }
            case 5: {
                MPEG4Interpolator.horzMiddle8(qpi, 0, ref, xInt, yInt, w, h, 9, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8(qpi, 0, ref, xInt, yInt, w, h, 9, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle8Safe(dst, dstO, qpi, 0, 8, 16, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8Safe(dst, dstO, qpi, 0, 8, 16, rounding ? 1 : 0);
                break;
            }
            case 6: {
                MPEG4Interpolator.horzMiddle8(qpi, 0, ref, xInt, yInt, w, h, 9, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle8Safe(dst, dstO, qpi, 0, 8, 16, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8Safe(dst, dstO, qpi, 0, 8, 16, rounding ? 1 : 0);
                break;
            }
            case 7: {
                MPEG4Interpolator.horzMiddle8(qpi, 0, ref, xInt, yInt, w, h, 9, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8(qpi, 0, ref, xInt + 1, yInt, w, h, 9, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle8Safe(dst, dstO, qpi, 0, 8, 16, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8Safe(dst, dstO, qpi, 0, 8, 16, rounding ? 1 : 0);
                break;
            }
            case 8: {
                MPEG4Interpolator.vertMiddle8(dst, dstO, ref, xInt, yInt, w, h, 8, refs, rounding ? 1 : 0);
                break;
            }
            case 9: {
                MPEG4Interpolator.horzMiddle8(qpi, 0, ref, xInt, yInt, w, h, 9, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8(qpi, 0, ref, xInt, yInt, w, h, 9, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle8Safe(dst, dstO, qpi, 0, 8, 16, rounding ? 1 : 0);
                break;
            }
            case 10: {
                MPEG4Interpolator.horzMiddle8(qpi, 0, ref, xInt, yInt, w, h, 9, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle8Safe(dst, dstO, qpi, 0, 8, 16, rounding ? 1 : 0);
                break;
            }
            case 11: {
                MPEG4Interpolator.horzMiddle8(qpi, 0, ref, xInt, yInt, w, h, 9, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8(qpi, 0, ref, xInt + 1, yInt, w, h, 9, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle8Safe(dst, dstO, qpi, 0, 8, 16, rounding ? 1 : 0);
                break;
            }
            case 12: {
                MPEG4Interpolator.vertMiddle8(dst, dstO, ref, xInt, yInt, w, h, 8, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8(dst, dstO, ref, xInt, yInt + 1, w, h, 8, refs, rounding ? 1 : 0);
                break;
            }
            case 13: {
                MPEG4Interpolator.horzMiddle8(qpi, 0, ref, xInt, yInt, w, h, 9, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8(qpi, 0, ref, xInt, yInt, w, h, 9, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle8Safe(dst, dstO, qpi, 0, 8, 16, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8Safe(dst, dstO, qpi, 16, 8, 16, rounding ? 1 : 0);
                break;
            }
            case 14: {
                MPEG4Interpolator.horzMiddle8(qpi, 0, ref, xInt, yInt, w, h, 9, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle8Safe(dst, dstO, qpi, 0, 8, 16, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8Safe(dst, dstO, qpi, 16, 8, 16, rounding ? 1 : 0);
                break;
            }
            case 15: {
                MPEG4Interpolator.horzMiddle8(qpi, 0, ref, xInt, yInt, w, h, 9, refs, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8(qpi, 0, ref, xInt + 1, yInt, w, h, 9, refs, rounding ? 1 : 0);
                MPEG4Interpolator.vertMiddle8Safe(dst, dstO, qpi, 0, 8, 16, rounding ? 1 : 0);
                MPEG4Interpolator.qOff8x8Safe(dst, dstO, qpi, 16, 8, 16, rounding ? 1 : 0);
            }
        }
    }

    private static final void horzMiddle8(byte[] dst, int dstOffset, byte[] src, int x, int y, int w, int h, int height, int srcStride, int rounding) {
        if (x < 0 || y < 0 || x > w - 9 || y > h - height) {
            for (int row = 0; row < height; ++row) {
                for (int i = 0; i < 4; ++i) {
                    int sum0 = 0;
                    int sum1 = 0;
                    int o0 = MathUtil.clip(y + row, 0, h - 1) * srcStride;
                    for (int k = 0; k < 5 + i; ++k) {
                        int o1 = MathUtil.clip(x + k, 0, w - 1);
                        int o2 = MathUtil.clip(x + 8 - k, 0, w - 1);
                        sum0 += MPEG4Consts.FILTER_TAB[i][k] * src[o0 + o1];
                        sum1 += MPEG4Consts.FILTER_TAB[i][k] * src[o0 + o2];
                    }
                    dst[dstOffset + i] = (byte)MathUtil.clip(sum0 + 16 - rounding >> 5, -128, 127);
                    dst[dstOffset + 7 - i] = (byte)MathUtil.clip(sum1 + 16 - rounding >> 5, -128, 127);
                }
                dstOffset += 16;
            }
        } else {
            int srcOffset = y * srcStride + x;
            for (int row = 0; row < height; ++row) {
                for (int i = 0; i < 4; ++i) {
                    int sum0 = 0;
                    int sum1 = 0;
                    for (int k = 0; k < 5 + i; ++k) {
                        sum0 += MPEG4Consts.FILTER_TAB[i][k] * src[srcOffset + k];
                        sum1 += MPEG4Consts.FILTER_TAB[i][k] * src[srcOffset + 8 - k];
                    }
                    dst[dstOffset + i] = (byte)MathUtil.clip(sum0 + 16 - rounding >> 5, -128, 127);
                    dst[dstOffset + 7 - i] = (byte)MathUtil.clip(sum1 + 16 - rounding >> 5, -128, 127);
                }
                srcOffset += srcStride;
                dstOffset += 16;
            }
        }
    }

    private static final void horzMiddle16(byte[] dst, byte[] src, int x, int y, int w, int h, int height, int srcStride, int rounding) {
        if (x < 0 || y < 0 || x > w - 17 || y > h - height) {
            int dstOffset = 0;
            for (int row = 0; row < height; ++row) {
                int i;
                int o0 = MathUtil.clip(y + row, 0, h - 1) * srcStride;
                for (i = 0; i < 4; ++i) {
                    int sum0 = 0;
                    int sum1 = 0;
                    for (int k = 0; k < 5 + i; ++k) {
                        int srcOffset0 = o0 + MathUtil.clip(x + k, 0, w - 1);
                        int srcOffset1 = o0 + MathUtil.clip(x + 16 - k, 0, w - 1);
                        sum0 += MPEG4Consts.FILTER_TAB[i][k] * src[srcOffset0];
                        sum1 += MPEG4Consts.FILTER_TAB[i][k] * src[srcOffset1];
                    }
                    dst[dstOffset + i] = (byte)MathUtil.clip(sum0 + 16 - rounding >> 5, -128, 127);
                    dst[dstOffset + 15 - i] = (byte)MathUtil.clip(sum1 + 16 - rounding >> 5, -128, 127);
                }
                for (i = 0; i < 8; ++i) {
                    int sum = 0;
                    for (int k = 0; k < 8; ++k) {
                        int srcOffset = o0 + MathUtil.clip(x + k + i + 1, 0, w - 1);
                        sum += MPEG4Consts.FILTER_TAB[3][k] * src[srcOffset];
                    }
                    dst[dstOffset + i + 4] = (byte)MathUtil.clip(sum + 16 - rounding >> 5, -128, 127);
                }
                dstOffset += 16;
            }
        } else {
            int srcOffset = y * srcStride + x;
            int dstOffset = 0;
            for (int row = 0; row < height; ++row) {
                int i;
                for (i = 0; i < 4; ++i) {
                    int sum0 = 0;
                    int sum1 = 0;
                    for (int k = 0; k < 5 + i; ++k) {
                        sum0 += MPEG4Consts.FILTER_TAB[i][k] * src[srcOffset + k];
                        sum1 += MPEG4Consts.FILTER_TAB[i][k] * src[srcOffset + 16 - k];
                    }
                    dst[dstOffset + i] = (byte)MathUtil.clip(sum0 + 16 - rounding >> 5, -128, 127);
                    dst[dstOffset + 15 - i] = (byte)MathUtil.clip(sum1 + 16 - rounding >> 5, -128, 127);
                }
                for (i = 0; i < 8; ++i) {
                    int sum = 0;
                    for (int k = 0; k < 8; ++k) {
                        sum += MPEG4Consts.FILTER_TAB[3][k] * src[srcOffset + k + i + 1];
                    }
                    dst[dstOffset + i + 4] = (byte)MathUtil.clip(sum + 16 - rounding >> 5, -128, 127);
                }
                srcOffset += srcStride;
                dstOffset += 16;
            }
        }
    }

    private static final void vertMiddle16Safe(byte[] dst, byte[] src, int srcOffset, int width, int srcStride, int rounding) {
        int dstOffset = 0;
        for (int col = 0; col < width; ++col) {
            int dstStart = dstOffset;
            int dstEnd = dstOffset + 240;
            int i = 0;
            while (i < 4) {
                int sum0 = 0;
                int sum1 = 0;
                int ss = srcOffset;
                int es = srcOffset + (srcStride << 4);
                for (int k = 0; k < 5 + i; ++k) {
                    sum0 += MPEG4Consts.FILTER_TAB[i][k] * src[ss];
                    sum1 += MPEG4Consts.FILTER_TAB[i][k] * src[es];
                    ss += srcStride;
                    es -= srcStride;
                }
                dst[dstStart] = (byte)MathUtil.clip(sum0 + 16 - rounding >> 5, -128, 127);
                dst[dstEnd] = (byte)MathUtil.clip(sum1 + 16 - rounding >> 5, -128, 127);
                ++i;
                dstStart += 16;
                dstEnd -= 16;
            }
            dstStart = dstOffset + 64;
            int srcCoeff0Pos = srcOffset + srcStride;
            int i2 = 0;
            while (i2 < 8) {
                int sum = 0;
                int srcPos = srcCoeff0Pos;
                int k = 0;
                while (k < 8) {
                    sum += MPEG4Consts.FILTER_TAB[3][k] * src[srcPos];
                    ++k;
                    srcPos += srcStride;
                }
                dst[dstStart] = (byte)MathUtil.clip(sum + 16 - rounding >> 5, -128, 127);
                ++i2;
                dstStart += 16;
                srcCoeff0Pos += srcStride;
            }
            ++srcOffset;
            ++dstOffset;
        }
    }

    private static final void vertMiddle16(byte[] dst, byte[] src, int x, int y, int w, int h, int width, int srcStride, int rounding) {
        if (x < 0 || y < 0 || x > w - width || y > h - 17) {
            int dstOffset = 0;
            for (int col = 0; col < width; ++col) {
                int dstStart = dstOffset;
                int dstEnd = dstOffset + 240;
                int i = 0;
                while (i < 4) {
                    int sum0 = 0;
                    int sum1 = 0;
                    for (int k = 0; k < 5 + i; ++k) {
                        int ss = MathUtil.clip(y + k, 0, h - 1) * srcStride + MathUtil.clip(x + col, 0, w - 1);
                        int es = MathUtil.clip(y - k + 16, 0, h - 1) * srcStride + MathUtil.clip(x + col, 0, w - 1);
                        sum0 += MPEG4Consts.FILTER_TAB[i][k] * src[ss];
                        sum1 += MPEG4Consts.FILTER_TAB[i][k] * src[es];
                    }
                    dst[dstStart] = (byte)MathUtil.clip(sum0 + 16 - rounding >> 5, -128, 127);
                    dst[dstEnd] = (byte)MathUtil.clip(sum1 + 16 - rounding >> 5, -128, 127);
                    ++i;
                    dstStart += 16;
                    dstEnd -= 16;
                }
                dstStart = dstOffset + 64;
                i = 0;
                while (i < 8) {
                    int sum = 0;
                    for (int k = 0; k < 8; ++k) {
                        int srcPos = MathUtil.clip(y + i + k + 1, 0, h - 1) * srcStride + MathUtil.clip(x + col, 0, w - 1);
                        sum += MPEG4Consts.FILTER_TAB[3][k] * src[srcPos];
                    }
                    dst[dstStart] = (byte)MathUtil.clip(sum + 16 - rounding >> 5, -128, 127);
                    ++i;
                    dstStart += 16;
                }
                ++dstOffset;
            }
        } else {
            MPEG4Interpolator.vertMiddle16Safe(dst, src, y * srcStride + x, width, srcStride, rounding);
        }
    }

    private static final void vertMiddle8Safe(byte[] dst, int dstOffset, byte[] src, int srcOffset, int width, int srcStride, int rounding) {
        for (int col = 0; col < width; ++col) {
            for (int i = 0; i < 4; ++i) {
                int sum0 = 0;
                int sum1 = 0;
                int os = srcOffset;
                int of = srcOffset + (srcStride << 3);
                for (int k = 0; k < 5 + i; ++k) {
                    sum0 += MPEG4Consts.FILTER_TAB[i][k] * src[os];
                    sum1 += MPEG4Consts.FILTER_TAB[i][k] * src[of];
                    os += srcStride;
                    of -= srcStride;
                }
                dst[dstOffset + i * 16] = (byte)MathUtil.clip(sum0 + 16 - rounding >> 5, -128, 127);
                dst[dstOffset + (7 - i) * 16] = (byte)MathUtil.clip(sum1 + 16 - rounding >> 5, -128, 127);
            }
            ++srcOffset;
            ++dstOffset;
        }
    }

    private static final void vertMiddle8(byte[] dst, int dstOffset, byte[] src, int x, int y, int w, int h, int width, int srcStride, int rounding) {
        if (x < 0 || y < 0 || x > w - width || y > h - 9) {
            for (int col = 0; col < width; ++col) {
                for (int i = 0; i < 4; ++i) {
                    int sum0 = 0;
                    int sum1 = 0;
                    for (int k = 0; k < 5 + i; ++k) {
                        int os = MathUtil.clip(y + k, 0, h - 1) * srcStride + MathUtil.clip(x + col, 0, w - 1);
                        int of = MathUtil.clip(y + 8 - k, 0, h - 1) * srcStride + MathUtil.clip(x + col, 0, w - 1);
                        sum0 += MPEG4Consts.FILTER_TAB[i][k] * src[os];
                        sum1 += MPEG4Consts.FILTER_TAB[i][k] * src[of];
                    }
                    dst[dstOffset + i * 16] = (byte)MathUtil.clip(sum0 + 16 - rounding >> 5, -128, 127);
                    dst[dstOffset + (7 - i) * 16] = (byte)MathUtil.clip(sum1 + 16 - rounding >> 5, -128, 127);
                }
                ++dstOffset;
            }
        } else {
            MPEG4Interpolator.vertMiddle8Safe(dst, dstOffset, src, y * srcStride + x, width, srcStride, rounding);
        }
    }

    public static final void interpolate16x16Planar(byte[] dst, byte[] refn, int x, int y, int w, int h, int dx, int dy, int stride, boolean rounding) {
        MPEG4Interpolator.interpolate8x8Planar(dst, 0, 16, refn, x, y, w, h, dx, dy, stride, rounding);
        MPEG4Interpolator.interpolate8x8Planar(dst, 8, 16, refn, x + 8, y, w, h, dx, dy, stride, rounding);
        MPEG4Interpolator.interpolate8x8Planar(dst, 128, 16, refn, x, y + 8, w, h, dx, dy, stride, rounding);
        MPEG4Interpolator.interpolate8x8Planar(dst, 136, 16, refn, x + 8, y + 8, w, h, dx, dy, stride, rounding);
    }

    public static final void interpolate8x8Planar(byte[] dst, int dstOff, int dstStride, byte[] refn, int x, int y, int w, int h, int dx, int dy, int stride, boolean rounding) {
        int x_ = x + (dx >> 1);
        int y_ = y + (dy >> 1);
        switch (((dx & 1) << 1) + (dy & 1)) {
            case 0: {
                MPEG4Interpolator.fulpel8x8(dst, dstOff, dstStride, refn, x_, y_, w, h, stride);
                break;
            }
            case 1: {
                MPEG4Interpolator.interpolate8PlanarVer(dst, dstOff, dstStride, refn, x_, y_, w, h, stride, rounding);
                break;
            }
            case 2: {
                MPEG4Interpolator.interpolate8x8PlanarHor(dst, dstOff, dstStride, refn, x_, y_, w, h, stride, rounding);
                break;
            }
            default: {
                MPEG4Interpolator.interpolate8x8PlanarBoth(dst, dstOff, dstStride, refn, x_, y_, w, h, stride, rounding);
            }
        }
    }

    private static final void interpolate8x8PlanarHor(byte[] dst, int dstOffset, int dstStride, byte[] src, int x, int y, int w, int h, int stride, boolean rounding) {
        int rnd;
        int n = rnd = rounding ? 0 : 1;
        if (x < 0 || y < 0 || x > w - 9 || y > h - 8) {
            int j = 0;
            int d = dstOffset;
            while (j < 8) {
                for (int i = 0; i < 8; ++i) {
                    int srcOffset0 = MathUtil.clip(y + j, 0, h - 1) * stride + MathUtil.clip(x + i, 0, w - 1);
                    int srcOffset1 = MathUtil.clip(y + j, 0, h - 1) * stride + MathUtil.clip(x + i + 1, 0, w - 1);
                    dst[d + i] = (byte)(src[srcOffset0] + src[srcOffset1] + rnd >> 1);
                }
                ++j;
                d += dstStride;
            }
        } else {
            int srcOffset = y * stride + x;
            int j = 0;
            int d = dstOffset;
            while (j < 8 * stride) {
                for (int i = 0; i < 8; ++i) {
                    dst[d + i] = (byte)(src[srcOffset + j + i] + src[srcOffset + j + i + 1] + rnd >> 1);
                }
                j += stride;
                d += dstStride;
            }
        }
    }

    private static final void interpolate8PlanarVer(byte[] dst, int dstOff, int dstStride, byte[] src, int x, int y, int w, int h, int stride, boolean rounding) {
        int rnd;
        int n = rnd = rounding ? 0 : 1;
        if (x < 0 || y < 0 || x > w - 8 || y > h - 9) {
            int j = 0;
            int d = dstOff;
            while (j < 8) {
                for (int i = 0; i < 8; ++i) {
                    int srcOffset0 = MathUtil.clip(y + j, 0, h - 1) * stride + MathUtil.clip(x + i, 0, w - 1);
                    int srcOffset1 = MathUtil.clip(y + j + 1, 0, h - 1) * stride + MathUtil.clip(x + i, 0, w - 1);
                    dst[d + i] = (byte)(src[srcOffset0] + src[srcOffset1] + rnd >> 1);
                }
                ++j;
                d += dstStride;
            }
        } else {
            int srcOffset = y * stride + x;
            int j = 0;
            int d = dstOff;
            while (j < 8 * stride) {
                for (int i = 0; i < 8; ++i) {
                    dst[d + i] = (byte)(src[srcOffset + j + i] + src[srcOffset + j + stride + i] + rnd >> 1);
                }
                j += stride;
                d += dstStride;
            }
        }
    }

    private static final void interpolate8x8PlanarBoth(byte[] dst, int dstOff, int dstStride, byte[] src, int x, int y, int w, int h, int stride, boolean rounding) {
        int rnd;
        int n = rnd = rounding ? 1 : 2;
        if (x < 0 || y < 0 || x > w - 9 || y > h - 9) {
            int j = 0;
            int d = dstOff;
            while (j < 8) {
                for (int i = 0; i < 8; ++i) {
                    int srcOffset0 = MathUtil.clip(y + j, 0, h - 1) * stride + MathUtil.clip(x + i, 0, w - 1);
                    int srcOffset1 = MathUtil.clip(y + j, 0, h - 1) * stride + MathUtil.clip(x + i + 1, 0, w - 1);
                    int srcOffset2 = MathUtil.clip(y + j + 1, 0, h - 1) * stride + MathUtil.clip(x + i, 0, w - 1);
                    int srcOffset3 = MathUtil.clip(y + j + 1, 0, h - 1) * stride + MathUtil.clip(x + i + 1, 0, w - 1);
                    dst[d + i] = (byte)(src[srcOffset0] + src[srcOffset1] + src[srcOffset2] + src[srcOffset3] + rnd >> 2);
                }
                ++j;
                d += dstStride;
            }
        } else {
            int srcOffset = y * stride + x;
            int j = 0;
            int d = dstOff;
            while (j < 8 * stride) {
                for (int i = 0; i < 8; ++i) {
                    dst[d + i] = (byte)(src[srcOffset + j + i] + src[srcOffset + j + i + 1] + src[srcOffset + j + stride + i] + src[srcOffset + j + stride + i + 1] + rnd >> 2);
                }
                j += stride;
                d += dstStride;
            }
        }
    }
}
