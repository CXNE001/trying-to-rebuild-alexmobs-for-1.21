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
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelMimicOctopus;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.OctopusColorRegistry;
import com.github.alexthe666.alexsmobs.client.render.RenderMimicOctopus;
import com.github.alexthe666.alexsmobs.entity.EntityMimicOctopus;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

static class RenderMimicOctopus.OverlayLayer
extends RenderLayer<EntityMimicOctopus, ModelMimicOctopus> {
    public RenderMimicOctopus.OverlayLayer(RenderMimicOctopus render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource buffer, int packedLightIn, EntityMimicOctopus entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        float transProgress = entitylivingbaseIn.prevTransProgress + (entitylivingbaseIn.transProgress - entitylivingbaseIn.prevTransProgress) * partialTicks;
        float colorProgress = (entitylivingbaseIn.prevColorShiftProgress + (entitylivingbaseIn.colorShiftProgress - entitylivingbaseIn.prevColorShiftProgress) * partialTicks) * 0.2f;
        float r = 1.0f;
        float g = 1.0f;
        float b = 1.0f;
        float a = 1.0f;
        float startR = 1.0f;
        float startG = 1.0f;
        float startB = 1.0f;
        float startA = 1.0f;
        float finR = 1.0f;
        float finG = 1.0f;
        float finB = 1.0f;
        float finA = 1.0f;
        if (entitylivingbaseIn.getPrevMimicState() == EntityMimicOctopus.MimicState.OVERLAY) {
            if (entitylivingbaseIn.getPrevMimickedBlock() != null) {
                int j = OctopusColorRegistry.getBlockColor(entitylivingbaseIn.getPrevMimickedBlock());
                startR = (float)(j >> 16 & 0xFF) / 255.0f;
                startG = (float)(j >> 8 & 0xFF) / 255.0f;
                startB = (float)(j & 0xFF) / 255.0f;
            } else {
                startA = 0.0f;
            }
        }
        if (entitylivingbaseIn.getMimicState() == EntityMimicOctopus.MimicState.OVERLAY) {
            if (entitylivingbaseIn.getMimickedBlock() != null) {
                int i = OctopusColorRegistry.getBlockColor(entitylivingbaseIn.getMimickedBlock());
                finR = (float)(i >> 16 & 0xFF) / 255.0f;
                finG = (float)(i >> 8 & 0xFF) / 255.0f;
                finB = (float)(i & 0xFF) / 255.0f;
            } else {
                finA = 0.0f;
            }
            r = startR + (finR - startR) * colorProgress;
            g = startG + (finG - startG) * colorProgress;
            b = startB + (finB - startB) * colorProgress;
            a = startA + (finA - startA) * colorProgress;
        }
        if (a == 1.0f) {
            a *= 0.9f + 0.1f * (float)Math.sin((float)entitylivingbaseIn.f_19797_ * 0.1f);
        }
        if (entitylivingbaseIn.getPrevMimicState() != null) {
            float alphaPrev = 1.0f - transProgress * 0.2f;
            VertexConsumer prev = buffer.m_6299_(AMRenderTypes.m_110473_((ResourceLocation)this.getFor(entitylivingbaseIn.getPrevMimicState())));
            if (entitylivingbaseIn.getPrevMimicState() == entitylivingbaseIn.getMimicState()) {
                alphaPrev *= a;
            }
            ((ModelMimicOctopus)this.m_117386_()).m_7695_(matrixStackIn, prev, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), r, g, b, alphaPrev);
        }
        float alphaCurrent = transProgress * 0.2f;
        VertexConsumer current = buffer.m_6299_(AMRenderTypes.m_110473_((ResourceLocation)this.getFor(entitylivingbaseIn.getMimicState())));
        ((ModelMimicOctopus)this.m_117386_()).m_7695_(matrixStackIn, current, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), r, g, b, a * alphaCurrent);
        VertexConsumer eyes = buffer.m_6299_(AMRenderTypes.m_110473_((ResourceLocation)TEXTURE_EYES));
        ((ModelMimicOctopus)this.m_117386_()).m_7695_(matrixStackIn, eyes, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
    }

    public ResourceLocation getFor(EntityMimicOctopus.MimicState state) {
        if (state == EntityMimicOctopus.MimicState.CREEPER) {
            return TEXTURE_CREEPER;
        }
        if (state == EntityMimicOctopus.MimicState.GUARDIAN) {
            return TEXTURE_GUARDIAN;
        }
        if (state == EntityMimicOctopus.MimicState.PUFFERFISH) {
            return TEXTURE_PUFFERFISH;
        }
        if (state == EntityMimicOctopus.MimicState.MIMICUBE) {
            return TEXTURE_MIMICUBE;
        }
        return TEXTURE_OVERLAY;
    }
}
