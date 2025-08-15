/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.citadel.client.model.container;

import com.github.alexthe666.citadel.client.model.container.TabulaModelBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
static final class TabulaModelBlock.Bookkeep {
    public final TabulaModelBlock model;
    public TabulaModelBlock modelExt;

    private TabulaModelBlock.Bookkeep(TabulaModelBlock modelIn) {
        this.model = modelIn;
    }
}
