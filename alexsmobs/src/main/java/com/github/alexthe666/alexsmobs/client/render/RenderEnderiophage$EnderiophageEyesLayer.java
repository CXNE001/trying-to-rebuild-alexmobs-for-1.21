/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelEnderiophage;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.RenderEnderiophage;
import com.github.alexthe666.alexsmobs.entity.EntityEnderiophage;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

static class RenderEnderiophage.EnderiophageEyesLayer
extends EyesLayer<EntityEnderiophage, ModelEnderiophage> {
    public RenderEnderiophage.EnderiophageEyesLayer(RenderEnderiophage p_i50928_1_) {
        super((RenderLayerParent)p_i50928_1_);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityEnderiophage entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer ivertexbuilder = bufferIn.m_6299_(this.getRenderType(entitylivingbaseIn));
        ((ModelEnderiophage)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, 0xF00000, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    public RenderType m_5708_() {
        return AMRenderTypes.getGhost(TEXTURE_GLOW);
    }

    public RenderType getRenderType(EntityEnderiophage entity) {
        return AMRenderTypes.getGhost(entity.getVariant() == 2 ? TEXTURE_NETHER_GLOW : (entity.getVariant() == 1 ? TEXTURE_OVERWORLD_GLOW : TEXTURE_GLOW));
    }
}
