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
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelBunfungus;
import com.github.alexthe666.alexsmobs.client.render.RenderBunfungus;
import com.github.alexthe666.alexsmobs.entity.EntityBunfungus;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

static class RenderBunfungus.LayerHeldItem
extends RenderLayer<EntityBunfungus, ModelBunfungus> {
    public RenderBunfungus.LayerHeldItem(RenderBunfungus render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityBunfungus entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemstack = entitylivingbaseIn.m_6844_(EquipmentSlot.MAINHAND);
        matrixStackIn.m_85836_();
        if (entitylivingbaseIn.m_6162_()) {
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.0);
        }
        matrixStackIn.m_85836_();
        this.translateToHand(matrixStackIn);
        matrixStackIn.m_252880_(0.3f, 0.45f, -0.15f);
        matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(90.0f));
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(-90.0f));
        matrixStackIn.m_85841_(1.15f, 1.15f, 1.15f);
        ItemInHandRenderer renderer = Minecraft.m_91087_().m_91290_().m_234586_();
        renderer.m_269530_((LivingEntity)entitylivingbaseIn, itemstack, ItemDisplayContext.GROUND, false, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.m_85849_();
        matrixStackIn.m_85849_();
    }

    protected void translateToHand(PoseStack matrixStack) {
        ((ModelBunfungus)this.m_117386_()).root.translateAndRotate(matrixStack);
        ((ModelBunfungus)this.m_117386_()).body.translateAndRotate(matrixStack);
        ((ModelBunfungus)this.m_117386_()).right_arm.translateAndRotate(matrixStack);
    }
}
