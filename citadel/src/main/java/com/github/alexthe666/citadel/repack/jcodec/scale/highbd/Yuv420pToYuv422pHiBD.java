/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.scale.highbd;

import com.github.alexthe666.citadel.repack.jcodec.common.model.PictureHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.TransformHiBD;

public class Yuv420pToYuv422pHiBD
implements TransformHiBD {
    private int shiftUp;
    private int shiftDown;

    public Yuv420pToYuv422pHiBD(int shiftUp, int shiftDown) {
        this.shiftUp = shiftUp;
        this.shiftDown = shiftDown;
    }

    @Override
    public void transform(PictureHiBD src, PictureHiBD dst) {
        Yuv420pToYuv422pHiBD.copy(src.getPlaneData(0), dst.getPlaneData(0), src.getWidth(), dst.getWidth(), dst.getHeight(), this.shiftUp, this.shiftDown);
        Yuv420pToYuv422pHiBD._copy(src.getPlaneData(1), dst.getPlaneData(1), 0, 0, 1, 2, src.getWidth() >> 1, dst.getWidth() >> 1, src.getHeight() >> 1, dst.getHeight(), this.shiftUp, this.shiftDown);
        Yuv420pToYuv422pHiBD._copy(src.getPlaneData(1), dst.getPlaneData(1), 0, 1, 1, 2, src.getWidth() >> 1, dst.getWidth() >> 1, src.getHeight() >> 1, dst.getHeight(), this.shiftUp, this.shiftDown);
        Yuv420pToYuv422pHiBD._copy(src.getPlaneData(2), dst.getPlaneData(2), 0, 0, 1, 2, src.getWidth() >> 1, dst.getWidth() >> 1, src.getHeight() >> 1, dst.getHeight(), this.shiftUp, this.shiftDown);
        Yuv420pToYuv422pHiBD._copy(src.getPlaneData(2), dst.getPlaneData(2), 0, 1, 1, 2, src.getWidth() >> 1, dst.getWidth() >> 1, src.getHeight() >> 1, dst.getHeight(), this.shiftUp, this.shiftDown);
    }

    private static final void _copy(int[] src, int[] dest, int offX, int offY, int stepX, int stepY, int strideSrc, int strideDest, int heightSrc, int heightDst, int upShift, int downShift) {
        int j;
        int offD = offX + offY * strideDest;
        int srcOff = 0;
        for (int i = 0; i < heightSrc; ++i) {
            for (int j2 = 0; j2 < strideSrc; ++j2) {
                dest[offD] = (src[srcOff++] & 0xFF) << 2;
                offD += stepX;
            }
            int lastOff = offD - stepX;
            for (j = strideSrc * stepX; j < strideDest; j += stepX) {
                dest[offD] = dest[lastOff];
                offD += stepX;
            }
            offD += (stepY - 1) * strideDest;
        }
        int lastLine = offD - stepY * strideDest;
        for (int i = heightSrc * stepY; i < heightDst; i += stepY) {
            for (j = 0; j < strideDest; j += stepX) {
                dest[offD] = dest[lastLine + j];
                offD += stepX;
            }
            offD += (stepY - 1) * strideDest;
        }
    }

    private static void copy(int[] src, int[] dest, int srcWidth, int dstWidth, int dstHeight, int shiftUp, int shiftDown) {
        int height = src.length / srcWidth;
        int dstOff = 0;
        int srcOff = 0;
        for (int i = 0; i < height; ++i) {
            int j;
            for (j = 0; j < srcWidth; ++j) {
                dest[dstOff++] = (src[srcOff++] & 0xFF) << 2;
            }
            for (j = srcWidth; j < dstWidth; ++j) {
                dest[dstOff++] = dest[srcWidth - 1];
            }
        }
        int lastLine = (height - 1) * dstWidth;
        for (int i = height; i < dstHeight; ++i) {
            for (int j = 0; j < dstWidth; ++j) {
                dest[dstOff++] = dest[lastLine + j];
            }
        }
    }
}
