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
package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelTusklin;
import com.github.alexthe666.alexsmobs.client.render.RenderTusklin;
import com.github.alexthe666.alexsmobs.entity.EntityTusklin;
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

public class LayerTusklinGear
extends RenderLayer<EntityTusklin, ModelTusklin> {
    private static final ResourceLocation TEXTURE_SADDLE = new ResourceLocation("alexsmobs:textures/entity/tusklin_saddle.png");
    private static final ResourceLocation TEXTURE_SHOES = new ResourceLocation("alexsmobs:textures/entity/tusklin_hooves.png");

    public LayerTusklinGear(RenderTusklin render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityTusklin entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer ivertexbuilder;
        if (entitylivingbaseIn.isSaddled()) {
            ivertexbuilder = bufferIn.m_6299_(RenderType.m_110452_((ResourceLocation)TEXTURE_SADDLE));
            ((ModelTusklin)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
        }
        if (!entitylivingbaseIn.getShoeStack().m_41619_()) {
            ivertexbuilder = ItemRenderer.m_115184_((MultiBufferSource)bufferIn, (RenderType)RenderType.m_110431_((ResourceLocation)TEXTURE_SHOES), (boolean)false, (boolean)entitylivingbaseIn.getShoeStack().m_41790_());
            ((ModelTusklin)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}
