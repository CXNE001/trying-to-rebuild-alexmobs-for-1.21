/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelFrilledShark;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.RenderFrilledShark;
import com.github.alexthe666.alexsmobs.entity.EntityFrilledShark;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

static class RenderFrilledShark.TeethLayer
extends RenderLayer<EntityFrilledShark, ModelFrilledShark> {
    public RenderFrilledShark.TeethLayer(RenderFrilledShark render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource buffer, int packedLightIn, EntityFrilledShark entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer glintBuilder = buffer.m_6299_(AMRenderTypes.getEyesFlickering(TEXTURE_TEETH, 240.0f));
        ((ModelFrilledShark)this.m_117386_()).m_7695_(matrixStackIn, glintBuilder, 240, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}
