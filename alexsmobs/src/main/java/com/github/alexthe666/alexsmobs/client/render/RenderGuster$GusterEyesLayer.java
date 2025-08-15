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

import com.github.alexthe666.alexsmobs.client.model.ModelGuster;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.RenderGuster;
import com.github.alexthe666.alexsmobs.entity.EntityGuster;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

static class RenderGuster.GusterEyesLayer
extends EyesLayer<EntityGuster, ModelGuster> {
    public RenderGuster.GusterEyesLayer(RenderGuster p_i50928_1_) {
        super((RenderLayerParent)p_i50928_1_);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityGuster entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isGooglyEyes()) {
            VertexConsumer ivertexbuilder = bufferIn.m_6299_(entitylivingbaseIn.getVariant() == 2 ? AMRenderTypes.getEyesNoCull(TEXTURE_SOUL_EYES) : AMRenderTypes.getEyesNoCull(TEXTURE_EYES));
            ((ModelGuster)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, 0xF00000, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    public RenderType m_5708_() {
        return AMRenderTypes.getEyesNoCull(TEXTURE_EYES);
    }
}
