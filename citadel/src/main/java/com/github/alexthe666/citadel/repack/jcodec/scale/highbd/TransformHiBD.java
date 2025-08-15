/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.scale.highbd;

import com.github.alexthe666.citadel.repack.jcodec.common.model.PictureHiBD;

public interface TransformHiBD {
    public void transform(PictureHiBD var1, PictureHiBD var2);

    public static enum Levels {
        STUDIO,
        PC;

    }
}
