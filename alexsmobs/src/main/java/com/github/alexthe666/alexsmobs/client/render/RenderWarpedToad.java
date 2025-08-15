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

import com.github.alexthe666.alexsmobs.client.model.ModelWarpedToad;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerWarpedToadGlow;
import com.github.alexthe666.alexsmobs.entity.EntityWarpedToad;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderWarpedToad
extends MobRenderer<EntityWarpedToad, ModelWarpedToad> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/warped_toad.png");
    private static final ResourceLocation TEXTURE_BLINKING = new ResourceLocation("alexsmobs:textures/entity/warped_toad_blink.png");
    private static final ResourceLocation TEXTURE_PEPE = new ResourceLocation("alexsmobs:textures/entity/warped_toad_pepe.png");
    private static final ResourceLocation TEXTURE_PEPE_BLINKING = new ResourceLocation("alexsmobs:textures/entity/warped_toad_pepe_blink.png");

    public RenderWarpedToad(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelWarpedToad(), 0.85f);
        this.m_115326_(new LayerWarpedToadGlow(this));
    }

    protected void scale(EntityWarpedToad entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(1.25f, 1.25f, 1.25f);
    }

    public ResourceLocation getTextureLocation(EntityWarpedToad entity) {
        if (entity.isBased()) {
            return entity.isBlinking() ? TEXTURE_PEPE_BLINKING : TEXTURE_PEPE;
        }
        return entity.isBlinking() ? TEXTURE_BLINKING : TEXTURE;
    }
}
