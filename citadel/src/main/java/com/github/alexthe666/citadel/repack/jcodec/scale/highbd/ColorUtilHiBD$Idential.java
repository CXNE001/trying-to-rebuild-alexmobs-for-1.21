/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.scale.highbd;

import com.github.alexthe666.citadel.repack.jcodec.common.model.PictureHiBD;
import com.github.alexthe666.citadel.repack.jcodec.scale.highbd.TransformHiBD;

public static class ColorUtilHiBD.Idential
implements TransformHiBD {
    @Override
    public void transform(PictureHiBD src, PictureHiBD dst) {
        for (int i = 0; i < 3; ++i) {
            System.arraycopy(src.getPlaneData(i), 0, dst.getPlaneData(i), 0, Math.min(src.getPlaneWidth(i) * src.getPlaneHeight(i), dst.getPlaneWidth(i) * dst.getPlaneHeight(i)));
        }
    }
}
