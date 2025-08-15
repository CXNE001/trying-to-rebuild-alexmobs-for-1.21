/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.resources.ResourceManager
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.render.layer.LayerVoidWormGlow;
import com.github.alexthe666.alexsmobs.entity.EntityVoidWormPart;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

class RenderVoidWormBody.1
extends LayerVoidWormGlow {
    RenderVoidWormBody.1(RenderLayerParent renderer, ResourceManager resourceManager, EntityModel layerModel) {
        super(renderer, resourceManager, layerModel);
    }

    @Override
    public ResourceLocation getGlowTexture(LivingEntity worm) {
        return ((EntityVoidWormPart)worm).isTail() ? TEXTURE_TAIL_GLOW : TEXTURE_BODY_GLOW;
    }

    @Override
    public boolean isGlowing(LivingEntity worm) {
        return !((EntityVoidWormPart)worm).isHurt();
    }

    @Override
    public float getAlpha(LivingEntity livingEntity) {
        EntityVoidWormPart worm = (EntityVoidWormPart)livingEntity;
        return (float)Mth.m_14008_((double)(((double)worm.m_21223_() - worm.getHealthThreshold()) / ((double)worm.m_21233_() - worm.getHealthThreshold())), (double)0.0, (double)1.0);
    }
}
