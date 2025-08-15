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

import com.github.alexthe666.alexsmobs.client.model.ModelOrca;
import com.github.alexthe666.alexsmobs.entity.EntityOrca;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderOrca
extends MobRenderer<EntityOrca, ModelOrca> {
    private static final ResourceLocation TEXTURE_NE = new ResourceLocation("alexsmobs:textures/entity/orca_ne.png");
    private static final ResourceLocation TEXTURE_NW = new ResourceLocation("alexsmobs:textures/entity/orca_nw.png");
    private static final ResourceLocation TEXTURE_SE = new ResourceLocation("alexsmobs:textures/entity/orca_se.png");
    private static final ResourceLocation TEXTURE_SW = new ResourceLocation("alexsmobs:textures/entity/orca_sw.png");

    public RenderOrca(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelOrca(), 1.0f);
    }

    protected void scale(EntityOrca entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(1.3f, 1.3f, 1.3f);
    }

    public ResourceLocation getTextureLocation(EntityOrca entity) {
        return switch (entity.getVariant()) {
            case 0 -> TEXTURE_NE;
            case 1 -> TEXTURE_NW;
            case 2 -> TEXTURE_SE;
            default -> TEXTURE_SW;
        };
    }
}
