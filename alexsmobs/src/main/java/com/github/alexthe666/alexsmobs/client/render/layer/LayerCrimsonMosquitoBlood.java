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
package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelCrimsonMosquito;
import com.github.alexthe666.alexsmobs.client.render.RenderCrimsonMosquito;
import com.github.alexthe666.alexsmobs.entity.EntityCrimsonMosquito;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class LayerCrimsonMosquitoBlood
extends RenderLayer<EntityCrimsonMosquito, ModelCrimsonMosquito> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/crimson_mosquito_blood.png");
    private static final ResourceLocation TEXTURE_SICK = new ResourceLocation("alexsmobs:textures/entity/crimson_mosquito_blood_blue.png");

    public LayerCrimsonMosquitoBlood(RenderCrimsonMosquito renderCrimsonMosquito) {
        super((RenderLayerParent)renderCrimsonMosquito);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityCrimsonMosquito entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.getBloodLevel() > 0) {
            VertexConsumer ivertexbuilder = bufferIn.m_6299_(RenderType.m_110488_((ResourceLocation)(entitylivingbaseIn.isSick() ? TEXTURE_SICK : TEXTURE)));
            ((ModelCrimsonMosquito)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}
