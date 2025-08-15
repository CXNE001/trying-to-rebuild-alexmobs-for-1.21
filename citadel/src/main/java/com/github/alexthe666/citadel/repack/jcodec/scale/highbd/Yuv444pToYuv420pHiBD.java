/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.scale.highbd;

import com.github.alexthe666.citadel.repack.jcodec.common.model.PictureHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.TransformHiBD;

public class Yuv444pToYuv420pHiBD
implements TransformHiBD {
    private int shiftUp;
    private int shiftDown;

    public Yuv444pToYuv420pHiBD(int shiftUp, int shiftDown) {
        this.shiftUp = shiftUp;
        this.shiftDown = shiftDown;
    }

    @Override
    public void transform(PictureHiBD src, PictureHiBD dst) {
        int lumaSize = src.getWidth() * src.getHeight();
        System.arraycopy(src.getPlaneData(0), 0, dst.getPlaneData(0), 0, lumaSize);
        this.copyAvg(src.getPlaneData(1), dst.getPlaneData(1), src.getPlaneWidth(1), src.getPlaneHeight(1));
        this.copyAvg(src.getPlaneData(2), dst.getPlaneData(2), src.getPlaneWidth(2), src.getPlaneHeight(2));
        if (this.shiftUp > this.shiftDown) {
            this.up(dst.getPlaneData(0), this.shiftUp - this.shiftDown);
            this.up(dst.getPlaneData(1), this.shiftUp - this.shiftDown);
            this.up(dst.getPlaneData(2), this.shiftUp - this.shiftDown);
        } else if (this.shiftDown > this.shiftUp) {
            this.down(dst.getPlaneData(0), this.shiftDown - this.shiftUp);
            this.down(dst.getPlaneData(1), this.shiftDown - this.shiftUp);
            this.down(dst.getPlaneData(2), this.shiftDown - this.shiftUp);
        }
    }

    private void down(int[] dst, int down) {
        int i = 0;
        while (i < dst.length) {
            int n = i++;
            dst[n] = dst[n] >> down;
        }
    }

    private void up(int[] dst, int up) {
        int i = 0;
        while (i < dst.length) {
            int n = i++;
            dst[n] = dst[n] << up;
        }
    }

    private void copyAvg(int[] src, int[] dst, int width, int height) {
        int offSrc = 0;
        int offDst = 0;
        for (int y = 0; y < height >> 1; ++y) {
            int x = 0;
            while (x < width) {
                dst[offDst] = src[offSrc] + src[offSrc + 1] + src[offSrc + width] + src[offSrc + width + 1] + 2 >> 2;
                x += 2;
                ++offDst;
                offSrc += 2;
            }
            offSrc += width;
        }
    }
}
