/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemInHandRenderer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 */
package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelElephant;
import com.github.alexthe666.alexsmobs.client.render.RenderElephant;
import com.github.alexthe666.alexsmobs.entity.EntityElephant;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class LayerElephantItem
extends RenderLayer<EntityElephant, ModelElephant> {
    public LayerElephantItem(RenderElephant render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityElephant entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemstack = entitylivingbaseIn.m_21205_();
        matrixStackIn.m_85836_();
        if (entitylivingbaseIn.m_6162_()) {
            matrixStackIn.m_85841_(0.35f, 0.35f, 0.35f);
            matrixStackIn.m_85837_(0.0, 2.8, 0.0);
        }
        matrixStackIn.m_85836_();
        this.translateToHand(matrixStackIn);
        if (entitylivingbaseIn.m_6162_()) {
            matrixStackIn.m_85837_(0.0, (double)0.2f, -0.22);
        }
        matrixStackIn.m_85837_(-0.0, 1.0, (double)0.15f);
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(180.0f));
        matrixStackIn.m_85841_(1.3f, 1.3f, 1.3f);
        if (Minecraft.m_91087_().m_91291_().m_115103_().m_109406_(itemstack).m_7539_()) {
            matrixStackIn.m_252880_(-0.05f, -0.1f, -0.15f);
            matrixStackIn.m_85841_(2.0f, 2.0f, 2.0f);
        }
        ItemInHandRenderer renderer = Minecraft.m_91087_().m_91290_().m_234586_();
        renderer.m_269530_((LivingEntity)entitylivingbaseIn, itemstack, ItemDisplayContext.GROUND, false, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.m_85849_();
        matrixStackIn.m_85849_();
    }

    protected void translateToHand(PoseStack matrixStack) {
        ((ModelElephant)this.m_117386_()).root.translateAndRotate(matrixStack);
        ((ModelElephant)this.m_117386_()).body.translateAndRotate(matrixStack);
        ((ModelElephant)this.m_117386_()).head.translateAndRotate(matrixStack);
        ((ModelElephant)this.m_117386_()).trunk1.translateAndRotate(matrixStack);
        ((ModelElephant)this.m_117386_()).trunk2.translateAndRotate(matrixStack);
    }
}
