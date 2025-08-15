/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelSpectre;
import com.github.alexthe666.alexsmobs.client.render.RenderSpectre;
import com.github.alexthe666.alexsmobs.entity.EntitySpectre;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

static class RenderSpectre.SpectreEyesLayer
extends EyesLayer<EntitySpectre, ModelSpectre> {
    public RenderSpectre.SpectreEyesLayer(RenderSpectre p_i50928_1_) {
        super((RenderLayerParent)p_i50928_1_);
    }

    public RenderType m_5708_() {
        return RenderType.m_110488_((ResourceLocation)TEXTURE_EYES);
    }
}
