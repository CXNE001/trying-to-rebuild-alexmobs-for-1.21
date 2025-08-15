/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelTasmanianDevil;
import com.github.alexthe666.alexsmobs.entity.EntityTasmanianDevil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderTasmanianDevil
extends MobRenderer<EntityTasmanianDevil, ModelTasmanianDevil> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/tasmanian_devil.png");
    private static final ResourceLocation TEXTURE_ANGRY = new ResourceLocation("alexsmobs:textures/entity/tasmanian_devil_angry.png");

    public RenderTasmanianDevil(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelTasmanianDevil(), 0.3f);
    }

    protected void scale(EntityTasmanianDevil entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }

    public ResourceLocation getTextureLocation(EntityTasmanianDevil entity) {
        return entity.getAnimation() == EntityTasmanianDevil.ANIMATION_HOWL && entity.getAnimationTick() < 34 ? TEXTURE_ANGRY : TEXTURE;
    }
}
