/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelSkelewag;
import com.github.alexthe666.alexsmobs.entity.EntitySkelewag;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class RenderSkelewag
extends MobRenderer<EntitySkelewag, ModelSkelewag> {
    private static final ResourceLocation TEXTURE_0 = new ResourceLocation("alexsmobs:textures/entity/skelewag_0.png");
    private static final ResourceLocation TEXTURE_1 = new ResourceLocation("alexsmobs:textures/entity/skelewag_1.png");

    public RenderSkelewag(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelSkelewag(), 0.5f);
    }

    protected void scale(EntitySkelewag entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }

    protected int getBlockLightLevel(EntitySkelewag entityIn, BlockPos partialTicks) {
        return Math.max(2, super.m_6086_((Entity)entityIn, partialTicks));
    }

    public ResourceLocation getTextureLocation(EntitySkelewag entity) {
        return entity.getVariant() == 1 ? TEXTURE_1 : TEXTURE_0;
    }
}
