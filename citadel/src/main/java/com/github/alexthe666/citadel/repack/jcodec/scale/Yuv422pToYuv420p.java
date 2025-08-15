/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.scale;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;
import com.github.alexthe666.citadel.repack.jcodec.scale.Transform;

public class Yuv422pToYuv420p
implements Transform {
    @Override
    public void transform(Picture src, Picture dst) {
        int lumaSize = src.getWidth() * src.getHeight();
        System.arraycopy(src.getPlaneData(0), 0, dst.getPlaneData(0), 0, lumaSize);
        this.copyAvg(src.getPlaneData(1), dst.getPlaneData(1), src.getPlaneWidth(1), src.getPlaneHeight(1));
        this.copyAvg(src.getPlaneData(2), dst.getPlaneData(2), src.getPlaneWidth(2), src.getPlaneHeight(2));
    }

    private void copyAvg(byte[] src, byte[] dst, int width, int height) {
        int offSrc = 0;
        int offDst = 0;
        for (int y = 0; y < height / 2; ++y) {
            int x = 0;
            while (x < width) {
                dst[offDst] = (byte)(src[offSrc] + src[offSrc + width] + 1 >> 1);
                ++x;
                ++offDst;
                ++offSrc;
            }
            offSrc += width;
        }
    }
}
