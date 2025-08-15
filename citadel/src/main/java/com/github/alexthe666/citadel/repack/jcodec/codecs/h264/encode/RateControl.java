/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.codecs.h264.encode;

import com.github.alexthe666.citadel.repack.jcodec.codecs.h264.io.model.SliceType;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Size;

public interface RateControl {
    public int startPicture(Size var1, int var2, SliceType var3);

    public int initialQpDelta();

    public int accept(int var1);
}
