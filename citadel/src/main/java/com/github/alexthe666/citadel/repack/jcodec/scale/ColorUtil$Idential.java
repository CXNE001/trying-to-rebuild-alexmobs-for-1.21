/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.scale;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;
import com.github.alexthe666.citadel.repack.jcodec.scale.Transform;

public static class ColorUtil.Idential
implements Transform {
    @Override
    public void transform(Picture src, Picture dst) {
        for (int i = 0; i < Math.min(src.getData().length, dst.getData().length); ++i) {
            System.arraycopy(src.getPlaneData(i), 0, dst.getPlaneData(i), 0, Math.min(src.getPlaneData(i).length, dst.getPlaneData(i).length));
        }
        byte[][] srcLowBits = src.getLowBits();
        byte[][] dstLowBits = dst.getLowBits();
        if (srcLowBits != null && dstLowBits != null) {
            for (int i = 0; i < Math.min(src.getData().length, dst.getData().length); ++i) {
                System.arraycopy(srcLowBits[i], 0, dstLowBits[i], 0, Math.min(src.getPlaneData(i).length, dst.getPlaneData(i).length));
            }
        }
    }
}
