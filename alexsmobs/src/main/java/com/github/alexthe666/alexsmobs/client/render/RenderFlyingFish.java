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

import com.github.alexthe666.alexsmobs.client.model.ModelFlyingFish;
import com.github.alexthe666.alexsmobs.entity.EntityFlyingFish;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderFlyingFish
extends MobRenderer<EntityFlyingFish, ModelFlyingFish> {
    private static final ResourceLocation TEXTURE_0 = new ResourceLocation("alexsmobs:textures/entity/flying_fish_0.png");
    private static final ResourceLocation TEXTURE_1 = new ResourceLocation("alexsmobs:textures/entity/flying_fish_1.png");
    private static final ResourceLocation TEXTURE_2 = new ResourceLocation("alexsmobs:textures/entity/flying_fish_2.png");

    public RenderFlyingFish(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelFlyingFish(), 0.2f);
    }

    protected void scale(EntityFlyingFish entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(0.8f, 0.8f, 0.8f);
    }

    public ResourceLocation getTextureLocation(EntityFlyingFish entity) {
        switch (entity.getVariant()) {
            case 0: {
                return TEXTURE_0;
            }
            case 1: {
                return TEXTURE_1;
            }
            case 2: {
                return TEXTURE_2;
            }
        }
        return TEXTURE_0;
    }
}
