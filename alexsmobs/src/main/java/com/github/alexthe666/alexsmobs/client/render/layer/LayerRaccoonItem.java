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

import com.github.alexthe666.alexsmobs.client.model.ModelRaccoon;
import com.github.alexthe666.alexsmobs.client.render.RenderRaccoon;
import com.github.alexthe666.alexsmobs.entity.EntityRaccoon;
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

public class LayerRaccoonItem
extends RenderLayer<EntityRaccoon, ModelRaccoon> {
    public LayerRaccoonItem(RenderRaccoon render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityRaccoon entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean inHand;
        ItemStack itemstack = entitylivingbaseIn.m_6844_(EquipmentSlot.MAINHAND);
        matrixStackIn.m_85836_();
        boolean bl = inHand = entitylivingbaseIn.begProgress > 0.0f || entitylivingbaseIn.standProgress > 0.0f || entitylivingbaseIn.washProgress > 0.0f;
        if (entitylivingbaseIn.m_6162_()) {
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.0);
        }
        matrixStackIn.m_85836_();
        this.translateToHand(inHand, matrixStackIn);
        if (inHand) {
            matrixStackIn.m_252880_(0.2f, 0.4f, 0.0f);
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(90.0f * entitylivingbaseIn.washProgress * 0.2f));
        } else {
            matrixStackIn.m_252880_(0.0f, 0.1f, -0.35f);
        }
        matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(-2.5f));
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(-90.0f));
        ItemInHandRenderer renderer = Minecraft.m_91087_().m_91290_().m_234586_();
        renderer.m_269530_((LivingEntity)entitylivingbaseIn, itemstack, ItemDisplayContext.GROUND, false, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.m_85849_();
        matrixStackIn.m_85849_();
    }

    protected void translateToHand(boolean inHand, PoseStack matrixStack) {
        if (inHand) {
            ((ModelRaccoon)this.m_117386_()).root.translateAndRotate(matrixStack);
            ((ModelRaccoon)this.m_117386_()).body.translateAndRotate(matrixStack);
            ((ModelRaccoon)this.m_117386_()).arm_right.translateAndRotate(matrixStack);
        } else {
            ((ModelRaccoon)this.m_117386_()).root.translateAndRotate(matrixStack);
            ((ModelRaccoon)this.m_117386_()).body.translateAndRotate(matrixStack);
            ((ModelRaccoon)this.m_117386_()).head.translateAndRotate(matrixStack);
        }
    }
}
