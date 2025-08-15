/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelKomodoDragon;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.RenderKomodoDragon;
import com.github.alexthe666.alexsmobs.entity.EntityKomodoDragon;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

static class RenderKomodoDragon.LayerSaddle
extends RenderLayer<EntityKomodoDragon, ModelKomodoDragon> {
    private static final ModelKomodoDragon MAID_MODEL = new ModelKomodoDragon(0.3f);
    private static final ModelKomodoDragon SADDLE_MODEL = new ModelKomodoDragon(0.5f);

    public RenderKomodoDragon.LayerSaddle(RenderKomodoDragon render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityKomodoDragon entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.isMaid()) {
            VertexConsumer maid = bufferIn.m_6299_(AMRenderTypes.m_110458_((ResourceLocation)TEXTURE_MAID));
            ((ModelKomodoDragon)this.m_117386_()).m_102624_((EntityModel)MAID_MODEL);
            MAID_MODEL.m_6839_((Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            MAID_MODEL.setupAnim(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            MAID_MODEL.m_7695_(matrixStackIn, maid, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
        }
        if (entitylivingbaseIn.isSaddled()) {
            VertexConsumer saddle = bufferIn.m_6299_(AMRenderTypes.m_110458_((ResourceLocation)TEXTURE_SADDLE));
            ((ModelKomodoDragon)this.m_117386_()).m_102624_((EntityModel)SADDLE_MODEL);
            SADDLE_MODEL.m_6839_((Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            SADDLE_MODEL.setupAnim(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            SADDLE_MODEL.m_7695_(matrixStackIn, saddle, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}
