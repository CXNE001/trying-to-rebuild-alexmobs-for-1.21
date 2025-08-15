/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelMungus;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.RenderMungus;
import com.github.alexthe666.alexsmobs.entity.EntityMungus;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

static class RenderMungus.MungusSackLayer
extends RenderLayer<EntityMungus, ModelMungus> {
    public RenderMungus.MungusSackLayer(RenderMungus p_i50928_1_) {
        super((RenderLayerParent)p_i50928_1_);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityMungus entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        String s;
        VertexConsumer lead = bufferIn.m_6299_(AMRenderTypes.getEyesFlickering(TEXTURE_SACK_OVERLAY, 0.0f));
        float alpha = 0.75f + (Mth.m_14089_((float)(ageInTicks * 0.2f)) + 1.0f) * 0.125f;
        ((ModelMungus)this.m_117386_()).m_7695_(matrixStackIn, lead, 240, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, alpha);
        if (entitylivingbaseIn.getBeamTarget() != null) {
            VertexConsumer beam = bufferIn.m_6299_(AMRenderTypes.getGhost(TEXTURE_BEAM_OVERLAY));
            float beamAlpha = 0.75f + (Mth.m_14089_((float)(ageInTicks * 1.0f)) + 1.0f) * 0.125f;
            ((ModelMungus)this.m_117386_()).m_7695_(matrixStackIn, beam, 240, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, beamAlpha);
        }
        if ((s = ChatFormatting.m_126649_((String)entitylivingbaseIn.m_7755_().getString())) != null && s.toLowerCase().contains("drip")) {
            VertexConsumer shoeBuffer = bufferIn.m_6299_(AMRenderTypes.m_110458_((ResourceLocation)TEXTURE_SHOES));
            matrixStackIn.m_85836_();
            ((ModelMungus)this.m_117386_()).renderShoes();
            ((ModelMungus)this.m_117386_()).m_7695_(matrixStackIn, shoeBuffer, packedLightIn, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
            ((ModelMungus)this.m_117386_()).postRenderShoes();
            matrixStackIn.m_85849_();
        }
    }
}
