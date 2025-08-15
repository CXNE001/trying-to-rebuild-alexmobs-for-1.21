/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.scale;

import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;

public interface Transform {
    public void transform(Picture var1, Picture var2);

    public static enum Levels {
        STUDIO,
        PC;

    }
}
