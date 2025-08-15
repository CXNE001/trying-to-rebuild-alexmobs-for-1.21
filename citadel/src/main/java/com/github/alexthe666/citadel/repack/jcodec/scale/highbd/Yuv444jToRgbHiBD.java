/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.scale.highbd;

import com.github.alexthe666.citadel.repack.jcodec.common.model.PictureHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.TransformHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.Yuv420jToRgbHiBD;

public class Yuv444jToRgbHiBD
implements TransformHiBD {
    @Override
    public void transform(PictureHiBD src, PictureHiBD dst) {
        int[] y = src.getPlaneData(0);
        int[] u = src.getPlaneData(1);
        int[] v = src.getPlaneData(2);
        int[] data = dst.getPlaneData(0);
        int srcOff = 0;
        int dstOff = 0;
        for (int i = 0; i < dst.getHeight(); ++i) {
            int j = 0;
            while (j < dst.getWidth()) {
                Yuv420jToRgbHiBD.YUVJtoRGB(y[srcOff], u[srcOff], v[srcOff], data, dstOff);
                ++j;
                ++srcOff;
                dstOff += 3;
            }
        }
    }
}
