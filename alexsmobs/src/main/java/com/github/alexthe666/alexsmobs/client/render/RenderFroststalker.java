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

import com.github.alexthe666.alexsmobs.client.model.ModelFroststalker;
import com.github.alexthe666.alexsmobs.entity.EntityFroststalker;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderFroststalker
extends MobRenderer<EntityFroststalker, ModelFroststalker> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/froststalker.png");
    private static final ResourceLocation TEXTURE_NOSPIKES = new ResourceLocation("alexsmobs:textures/entity/froststalker_nospikes.png");

    public RenderFroststalker(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelFroststalker(), 0.4f);
    }

    protected void scale(EntityFroststalker entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }

    public ResourceLocation getTextureLocation(EntityFroststalker entity) {
        return entity.hasSpikes() ? TEXTURE : TEXTURE_NOSPIKES;
    }

    protected boolean isShaking(EntityFroststalker entity) {
        return entity.m_20071_() && !entity.hasSpikes();
    }
}
