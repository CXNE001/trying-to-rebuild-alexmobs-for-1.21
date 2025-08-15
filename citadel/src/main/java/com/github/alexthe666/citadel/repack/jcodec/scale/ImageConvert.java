/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.scale;

import java.nio.ByteBuffer;

public class ImageConvert {
    private static final int SCALEBITS = 10;
    private static final int ONE_HALF = 512;
    private static final int FIX_0_71414;
    private static final int FIX_1_772;
    private static final int _FIX_0_34414;
    private static final int FIX_1_402;
    private static final int CROP = 1024;
    private static final byte[] cropTable;
    private static final int[] intCropTable;
    private static final byte[] _y_ccir_to_jpeg;
    private static final byte[] _y_jpeg_to_ccir;

    private static final int FIX(double x) {
        return (int)(x * 1024.0 + 0.5);
    }

    public static final int ycbcr_to_rgb24(int y, int cb, int cr) {
        int add_r = FIX_1_402 * (cr -= 128) + 512;
        int add_g = _FIX_0_34414 * (cb -= 128) - FIX_0_71414 * cr + 512;
        int add_b = FIX_1_772 * cb + 512;
        int r = (y <<= 10) + add_r >> 10;
        int g = y + add_g >> 10;
        int b = y + add_b >> 10;
        r = ImageConvert.crop(r);
        g = ImageConvert.crop(g);
        b = ImageConvert.crop(b);
        return (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF;
    }

    static final int Y_JPEG_TO_CCIR(int y) {
        return y * ImageConvert.FIX(0.8588235294117647) + 16896 >> 10;
    }

    static final int Y_CCIR_TO_JPEG(int y) {
        return y * ImageConvert.FIX(1.1643835616438356) + (512 - 16 * ImageConvert.FIX(1.1643835616438356)) >> 10;
    }

    public static final int icrop(int i) {
        return intCropTable[i + 1024];
    }

    public static final byte crop(int i) {
        return cropTable[i + 1024];
    }

    public static final byte y_ccir_to_jpeg(byte y) {
        return _y_ccir_to_jpeg[y & 0xFF];
    }

    public static final byte y_jpeg_to_ccir(byte y) {
        return _y_jpeg_to_ccir[y & 0xFF];
    }

    public static void YUV444toRGB888(int y, int u, int v, ByteBuffer rgb) {
        int c = y - 16;
        int d = u - 128;
        int e = v - 128;
        int r = 298 * c + 409 * e + 128 >> 8;
        int g = 298 * c - 100 * d - 208 * e + 128 >> 8;
        int b = 298 * c + 516 * d + 128 >> 8;
        rgb.put(ImageConvert.crop(r));
        rgb.put(ImageConvert.crop(g));
        rgb.put(ImageConvert.crop(b));
    }

    public static void RGB888toYUV444(ByteBuffer rgb, ByteBuffer Y, ByteBuffer U, ByteBuffer V) {
        int r = rgb.get() & 0xFF;
        int g = rgb.get() & 0xFF;
        int b = rgb.get() & 0xFF;
        int y = 66 * r + 129 * g + 25 * b;
        int u = -38 * r - 74 * g + 112 * b;
        int v = 112 * r - 94 * g - 18 * b;
        y = y + 128 >> 8;
        u = u + 128 >> 8;
        v = v + 128 >> 8;
        Y.put(ImageConvert.crop(y + 16));
        U.put(ImageConvert.crop(u + 128));
        V.put(ImageConvert.crop(v + 128));
    }

    public static byte RGB888toY4(int r, int g, int b) {
        int y = 66 * r + 129 * g + 25 * b;
        y = y + 128 >> 8;
        return ImageConvert.crop(y + 16);
    }

    public static byte RGB888toU4(int r, int g, int b) {
        int u = -38 * r - 74 * g + 112 * b;
        u = u + 128 >> 8;
        return ImageConvert.crop(u + 128);
    }

    public static byte RGB888toV4(int r, int g, int b) {
        int v = 112 * r - 94 * g - 18 * b;
        v = v + 128 >> 8;
        return ImageConvert.crop(v + 128);
    }

    static {
        int i;
        FIX_0_71414 = ImageConvert.FIX(0.71414);
        FIX_1_772 = ImageConvert.FIX(1.772);
        _FIX_0_34414 = -ImageConvert.FIX(0.34414);
        FIX_1_402 = ImageConvert.FIX(1.402);
        cropTable = new byte[2304];
        intCropTable = new int[2304];
        _y_ccir_to_jpeg = new byte[256];
        _y_jpeg_to_ccir = new byte[256];
        for (i = -1024; i < 0; ++i) {
            ImageConvert.cropTable[i + 1024] = 0;
            ImageConvert.intCropTable[i + 1024] = 0;
        }
        for (i = 0; i < 256; ++i) {
            ImageConvert.cropTable[i + 1024] = (byte)i;
            ImageConvert.intCropTable[i + 1024] = i;
        }
        for (i = 256; i < 1024; ++i) {
            ImageConvert.cropTable[i + 1024] = -1;
            ImageConvert.intCropTable[i + 1024] = 255;
        }
        for (i = 0; i < 256; ++i) {
            ImageConvert._y_ccir_to_jpeg[i] = ImageConvert.crop(ImageConvert.Y_CCIR_TO_JPEG(i));
            ImageConvert._y_jpeg_to_ccir[i] = ImageConvert.crop(ImageConvert.Y_JPEG_TO_CCIR(i));
        }
    }
}
