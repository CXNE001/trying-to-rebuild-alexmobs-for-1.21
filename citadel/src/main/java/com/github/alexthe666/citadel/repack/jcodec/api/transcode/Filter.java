/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jcodec.api.transcode;

import com.github.alexthe666.citadel.repack.jcodec.api.transcode.PixelStore;
import com.github.alexthe666.citadel.repack.jcodec.common.model.ColorSpace;
import com.github.alexthe666.citadel.repack.jcodec.common.model.Picture;

public interface Filter {
    public PixelStore.LoanerPicture filter(Picture var1, PixelStore var2);

    public ColorSpace getInputColor();

    public ColorSpace getOutputColor();
}
