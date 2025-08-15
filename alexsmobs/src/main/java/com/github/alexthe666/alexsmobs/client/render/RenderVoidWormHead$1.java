/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.resources.ResourceManager
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.render.layer.LayerVoidWormGlow;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.LivingEntity;

class RenderVoidWormHead.1
extends LayerVoidWormGlow {
    RenderVoidWormHead.1(RenderLayerParent renderer, ResourceManager resourceManager, EntityModel layerModel) {
        super(renderer, resourceManager, layerModel);
    }

    @Override
    public ResourceLocation getGlowTexture(LivingEntity worm) {
        return TEXTURE_GLOW;
    }

    @Override
    public boolean isGlowing(LivingEntity worm) {
        return true;
    }

    @Override
    public float getAlpha(LivingEntity livingEntity) {
        return 1.0f;
    }
}
