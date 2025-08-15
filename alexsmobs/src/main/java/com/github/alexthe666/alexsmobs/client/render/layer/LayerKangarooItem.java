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
package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelKangaroo;
import com.github.alexthe666.alexsmobs.client.render.RenderKangaroo;
import com.github.alexthe666.alexsmobs.entity.EntityKangaroo;
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

public class LayerKangarooItem
extends RenderLayer<EntityKangaroo, ModelKangaroo> {
    public LayerKangarooItem(RenderKangaroo render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityKangaroo entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemstack = entitylivingbaseIn.m_6844_(EquipmentSlot.MAINHAND);
        matrixStackIn.m_85836_();
        boolean left = entitylivingbaseIn.m_21526_();
        if (entitylivingbaseIn.m_6162_()) {
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.0);
        }
        matrixStackIn.m_85836_();
        this.translateToHand(matrixStackIn, left);
        matrixStackIn.m_252880_(0.0f, 0.75f, -0.125f);
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(-110.0f));
        matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f));
        matrixStackIn.m_85841_(0.8f, 0.8f, 0.8f);
        ItemInHandRenderer renderer = Minecraft.m_91087_().m_91290_().m_234586_();
        renderer.m_269530_((LivingEntity)entitylivingbaseIn, itemstack, left ? ItemDisplayContext.THIRD_PERSON_LEFT_HAND : ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, false, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.m_85849_();
        matrixStackIn.m_85849_();
    }

    protected void translateToHand(PoseStack matrixStack, boolean left) {
        ((ModelKangaroo)this.m_117386_()).root.translateAndRotate(matrixStack);
        ((ModelKangaroo)this.m_117386_()).body.translateAndRotate(matrixStack);
        ((ModelKangaroo)this.m_117386_()).chest.translateAndRotate(matrixStack);
        if (left) {
            ((ModelKangaroo)this.m_117386_()).arm_left.translateAndRotate(matrixStack);
        } else {
            ((ModelKangaroo)this.m_117386_()).arm_right.translateAndRotate(matrixStack);
        }
    }
}
