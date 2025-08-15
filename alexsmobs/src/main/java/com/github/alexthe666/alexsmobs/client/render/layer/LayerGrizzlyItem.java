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

import com.github.alexthe666.alexsmobs.client.model.ModelGrizzlyBear;
import com.github.alexthe666.alexsmobs.client.render.RenderGrizzlyBear;
import com.github.alexthe666.alexsmobs.entity.EntityGrizzlyBear;
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

public class LayerGrizzlyItem
extends RenderLayer<EntityGrizzlyBear, ModelGrizzlyBear> {
    public LayerGrizzlyItem(RenderGrizzlyBear renderGrizzlyBear) {
        super((RenderLayerParent)renderGrizzlyBear);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityGrizzlyBear entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemstack = entitylivingbaseIn.m_6844_(EquipmentSlot.MAINHAND);
        ItemInHandRenderer renderer = Minecraft.m_91087_().m_91290_().m_234586_();
        matrixStackIn.m_85836_();
        if (entitylivingbaseIn.m_6162_()) {
            matrixStackIn.m_85841_(0.35f, 0.35f, 0.35f);
            matrixStackIn.m_85837_(0.0, 2.75, 0.125);
            this.translateToHand(false, matrixStackIn);
            matrixStackIn.m_252880_(0.2f, 0.7f, -0.4f);
            matrixStackIn.m_85841_(2.8f, 2.8f, 2.8f);
        } else {
            this.translateToHand(false, matrixStackIn);
            matrixStackIn.m_252880_(0.2f, 0.7f, -0.4f);
        }
        matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(10.0f));
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(100.0f));
        matrixStackIn.m_85841_(1.0f, 1.0f, 1.0f);
        renderer.m_269530_((LivingEntity)entitylivingbaseIn, itemstack, ItemDisplayContext.GROUND, false, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.m_85849_();
    }

    protected void translateToHand(boolean left, PoseStack matrixStack) {
        ((ModelGrizzlyBear)this.m_117386_()).root.translateAndRotate(matrixStack);
        ((ModelGrizzlyBear)this.m_117386_()).midbody.translateAndRotate(matrixStack);
        ((ModelGrizzlyBear)this.m_117386_()).body.translateAndRotate(matrixStack);
        ((ModelGrizzlyBear)this.m_117386_()).right_arm.translateAndRotate(matrixStack);
    }
}
