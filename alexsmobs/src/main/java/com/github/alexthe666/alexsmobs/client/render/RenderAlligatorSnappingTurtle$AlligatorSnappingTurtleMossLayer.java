/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelAlligatorSnappingTurtle;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.RenderAlligatorSnappingTurtle;
import com.github.alexthe666.alexsmobs.entity.EntityAlligatorSnappingTurtle;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

static class RenderAlligatorSnappingTurtle.AlligatorSnappingTurtleMossLayer
extends RenderLayer<EntityAlligatorSnappingTurtle, ModelAlligatorSnappingTurtle> {
    public RenderAlligatorSnappingTurtle.AlligatorSnappingTurtleMossLayer(RenderAlligatorSnappingTurtle p_i50928_1_) {
        super((RenderLayerParent)p_i50928_1_);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityAlligatorSnappingTurtle entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.getMoss() > 0) {
            float mossAlpha = 0.15f * (float)Mth.m_14045_((int)entitylivingbaseIn.getMoss(), (int)0, (int)10);
            VertexConsumer mossbuffer = bufferIn.m_6299_(AMRenderTypes.m_110473_((ResourceLocation)TEXTURE_MOSS));
            ((ModelAlligatorSnappingTurtle)this.m_117386_()).m_7695_(matrixStackIn, mossbuffer, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, Math.min(1.0f, mossAlpha));
        }
    }
}
