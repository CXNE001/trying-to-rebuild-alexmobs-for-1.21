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

import com.github.alexthe666.alexsmobs.client.model.ModelJerboa;
import com.github.alexthe666.alexsmobs.entity.EntityJerboa;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderJerboa
extends MobRenderer<EntityJerboa, ModelJerboa> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/jerboa.png");
    private static final ResourceLocation TEXTURE_SLEEPING = new ResourceLocation("alexsmobs:textures/entity/jerboa_sleeping.png");

    public RenderJerboa(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelJerboa(), 0.1f);
    }

    protected void scale(EntityJerboa entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(0.8f, 0.8f, 0.8f);
    }

    public ResourceLocation getTextureLocation(EntityJerboa entity) {
        return entity.m_5803_() ? TEXTURE_SLEEPING : TEXTURE;
    }
}
