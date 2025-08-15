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
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelCrocodile;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.RenderCrocodile;
import com.github.alexthe666.alexsmobs.entity.EntityCrocodile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

static class RenderCrocodile.CrownLayer
extends RenderLayer<EntityCrocodile, ModelCrocodile> {
    public RenderCrocodile.CrownLayer(RenderCrocodile p_i50928_1_) {
        super((RenderLayerParent)p_i50928_1_);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityCrocodile entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.isCrowned()) {
            VertexConsumer shoeBuffer = bufferIn.m_6299_(AMRenderTypes.m_110458_((ResourceLocation)TEXTURE_CROWN));
            matrixStackIn.m_85836_();
            ((ModelCrocodile)this.m_117386_()).m_7695_(matrixStackIn, shoeBuffer, packedLightIn, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
            matrixStackIn.m_85849_();
        }
    }
}
