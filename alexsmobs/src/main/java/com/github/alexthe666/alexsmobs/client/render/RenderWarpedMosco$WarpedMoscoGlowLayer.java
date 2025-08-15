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
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelWarpedMosco;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.RenderWarpedMosco;
import com.github.alexthe666.alexsmobs.entity.EntityWarpedMosco;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

static class RenderWarpedMosco.WarpedMoscoGlowLayer
extends RenderLayer<EntityWarpedMosco, ModelWarpedMosco> {
    public RenderWarpedMosco.WarpedMoscoGlowLayer(RenderWarpedMosco p_i50928_1_) {
        super((RenderLayerParent)p_i50928_1_);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityWarpedMosco entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer ivertexbuilder = bufferIn.m_6299_(AMRenderTypes.getEyesFlickering(TEXTURE_EYES, 0.0f));
        float alpha = 0.5f + (Mth.m_14089_((float)(ageInTicks * 0.2f)) + 1.0f) * 0.2f;
        ((ModelWarpedMosco)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, 240, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 0.5f, 1.0f, 1.0f, alpha);
    }
}
