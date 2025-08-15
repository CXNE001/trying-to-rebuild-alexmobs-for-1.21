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
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 */
package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelGorilla;
import com.github.alexthe666.alexsmobs.client.render.RenderGorilla;
import com.github.alexthe666.alexsmobs.entity.EntityGorilla;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class LayerGorillaItem
extends RenderLayer<EntityGorilla, ModelGorilla> {
    public LayerGorillaItem(RenderGorilla render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityGorilla entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemstack = entitylivingbaseIn.m_6844_(EquipmentSlot.MAINHAND);
        String name = entitylivingbaseIn.m_7755_().getString().toLowerCase();
        ItemInHandRenderer renderer = Minecraft.m_91087_().m_91290_().m_234586_();
        if (name.contains("harambe")) {
            ItemStack haloStack = new ItemStack((ItemLike)AMItemRegistry.HALO.get());
            matrixStackIn.m_85836_();
            ((ModelGorilla)this.m_117386_()).root.translateAndRotate(matrixStackIn);
            ((ModelGorilla)this.m_117386_()).body.translateAndRotate(matrixStackIn);
            ((ModelGorilla)this.m_117386_()).chest.translateAndRotate(matrixStackIn);
            ((ModelGorilla)this.m_117386_()).head.translateAndRotate(matrixStackIn);
            float f = 0.1f * (float)Math.sin(((float)entitylivingbaseIn.f_19797_ + partialTicks) * 0.1f) + (entitylivingbaseIn.m_6162_() ? 0.2f : 0.0f);
            matrixStackIn.m_252880_(0.0f, -0.7f - f, -0.2f);
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(90.0f));
            matrixStackIn.m_85841_(1.3f, 1.3f, 1.3f);
            renderer.m_269530_((LivingEntity)entitylivingbaseIn, haloStack, ItemDisplayContext.GROUND, false, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.m_85849_();
        }
        matrixStackIn.m_85836_();
        if (entitylivingbaseIn.m_6162_()) {
            matrixStackIn.m_85841_(0.35f, 0.35f, 0.35f);
            matrixStackIn.m_85837_(-0.1, 2.0, -1.15);
            this.translateToHand(false, matrixStackIn);
            matrixStackIn.m_252880_(-0.4f, 0.75f, -0.0f);
            matrixStackIn.m_85841_(2.8f, 2.8f, 2.8f);
        } else {
            this.translateToHand(false, matrixStackIn);
            matrixStackIn.m_252880_(-0.4f, 0.75f, -0.0f);
        }
        matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(-2.5f));
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(-90.0f));
        if (itemstack.m_41720_() instanceof BlockItem) {
            matrixStackIn.m_85841_(2.0f, 2.0f, 2.0f);
        }
        renderer.m_269530_((LivingEntity)entitylivingbaseIn, itemstack, ItemDisplayContext.GROUND, false, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.m_85849_();
    }

    protected void translateToHand(boolean left, PoseStack matrixStack) {
        ((ModelGorilla)this.m_117386_()).root.translateAndRotate(matrixStack);
        ((ModelGorilla)this.m_117386_()).body.translateAndRotate(matrixStack);
        ((ModelGorilla)this.m_117386_()).chest.translateAndRotate(matrixStack);
        ((ModelGorilla)this.m_117386_()).leftArm.translateAndRotate(matrixStack);
    }
}
