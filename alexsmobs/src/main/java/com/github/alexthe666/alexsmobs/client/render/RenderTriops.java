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

import com.github.alexthe666.alexsmobs.client.model.ModelTriops;
import com.github.alexthe666.alexsmobs.entity.EntityTriops;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderTriops
extends MobRenderer<EntityTriops, ModelTriops> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/triops.png");

    public RenderTriops(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelTriops(), 0.2f);
    }

    protected void scale(EntityTriops entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        float scale = entitylivingbaseIn.getTriopsScale();
        if (entitylivingbaseIn.m_6162_()) {
            scale *= 0.65f;
        }
        matrixStackIn.m_85841_(scale, scale, scale);
    }

    public ResourceLocation getTextureLocation(EntityTriops entity) {
        return TEXTURE;
    }
}
