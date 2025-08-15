/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelSpectre;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.RenderSpectre;
import com.github.alexthe666.alexsmobs.entity.EntitySpectre;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

class RenderSpectre.SpectreMembraneLayer
extends RenderLayer<EntitySpectre, ModelSpectre> {
    public RenderSpectre.SpectreMembraneLayer(RenderSpectre p_i50928_1_) {
        super((RenderLayerParent)p_i50928_1_);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntitySpectre entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer lvt_11_1_ = bufferIn.m_6299_(this.getRenderType());
        ((ModelSpectre)this.m_117386_()).m_7695_(matrixStackIn, lvt_11_1_, 0xF00000, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, RenderSpectre.this.getAlphaForRender(entitylivingbaseIn, partialTicks));
        if (entitylivingbaseIn.m_21523_()) {
            VertexConsumer lead = bufferIn.m_6299_(AMRenderTypes.m_110458_((ResourceLocation)TEXTURE_LEAD));
            ((ModelSpectre)this.m_117386_()).m_7695_(matrixStackIn, lead, 0xF00000, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    public RenderType getRenderType() {
        return AMRenderTypes.getSpectreBones(TEXTURE);
    }
}
