/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelTerrapin;
import com.github.alexthe666.alexsmobs.client.render.RenderTerrapin;
import com.github.alexthe666.alexsmobs.entity.EntityTerrapin;
import com.github.alexthe666.alexsmobs.entity.util.TerrapinTypes;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

static class RenderTerrapin.TurtleOverlayLayer
extends RenderLayer<EntityTerrapin, ModelTerrapin> {
    private final int layer;

    public RenderTerrapin.TurtleOverlayLayer(RenderTerrapin render, int layer) {
        super((RenderLayerParent)render);
        this.layer = layer;
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource buffer, int packedLightIn, EntityTerrapin turtle, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (turtle.getTurtleType() == TerrapinTypes.OVERLAY && !turtle.isKoopa()) {
            ResourceLocation tex;
            ResourceLocation resourceLocation = this.layer == 0 ? this.m_117347_((Entity)turtle) : (tex = this.layer == 1 ? SHELL_TEXTURES[turtle.getShellType() % SHELL_TEXTURES.length] : SKIN_PATTERN_TEXTURES[turtle.getSkinType() % SKIN_PATTERN_TEXTURES.length]);
            int color = this.layer == 0 ? turtle.getTurtleColor() : (this.layer == 1 ? turtle.getShellColor() : turtle.getSkinColor());
            float r = (float)(color >> 16 & 0xFF) / 255.0f;
            float g = (float)(color >> 8 & 0xFF) / 255.0f;
            float b = (float)(color & 0xFF) / 255.0f;
            RenderTerrapin.TurtleOverlayLayer.m_117376_((EntityModel)this.m_117386_(), (ResourceLocation)tex, (PoseStack)matrixStackIn, (MultiBufferSource)buffer, (int)packedLightIn, (LivingEntity)turtle, (float)r, (float)g, (float)b);
        }
    }
}
