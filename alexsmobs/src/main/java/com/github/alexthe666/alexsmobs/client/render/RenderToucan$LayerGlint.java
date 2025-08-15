/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.ItemRenderer
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelToucan;
import com.github.alexthe666.alexsmobs.client.render.RenderToucan;
import com.github.alexthe666.alexsmobs.entity.EntityToucan;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

static class RenderToucan.LayerGlint
extends RenderLayer<EntityToucan, ModelToucan> {
    public RenderToucan.LayerGlint(RenderToucan render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityToucan entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.isEnchanted()) {
            VertexConsumer vertexconsumer = ItemRenderer.m_115184_((MultiBufferSource)bufferIn, (RenderType)RenderType.m_110431_((ResourceLocation)TEXTURE_GOLDEN), (boolean)false, (boolean)true);
            ((ModelToucan)this.m_117386_()).m_7695_(matrixStackIn, vertexconsumer, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}
