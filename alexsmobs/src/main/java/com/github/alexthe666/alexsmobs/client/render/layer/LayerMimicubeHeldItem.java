/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.ShieldItem
 */
package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelMimicube;
import com.github.alexthe666.alexsmobs.client.render.RenderMimicube;
import com.github.alexthe666.alexsmobs.entity.EntityMimicube;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;

public class LayerMimicubeHeldItem
extends RenderLayer<EntityMimicube, ModelMimicube> {
    public LayerMimicubeHeldItem(RenderMimicube render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityMimicube entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemRight = entitylivingbaseIn.m_21205_();
        ItemStack itemLeft = entitylivingbaseIn.m_21206_();
        float rightSwap = Mth.m_14179_((float)partialTicks, (float)entitylivingbaseIn.prevRightSwapProgress, (float)entitylivingbaseIn.rightSwapProgress) * 0.2f;
        float leftSwap = Mth.m_14179_((float)partialTicks, (float)entitylivingbaseIn.prevLeftSwapProgress, (float)entitylivingbaseIn.leftSwapProgress) * 0.2f;
        float attackprogress = Mth.m_14179_((float)partialTicks, (float)entitylivingbaseIn.prevAttackProgress, (float)entitylivingbaseIn.attackProgress);
        double bob1 = Math.cos(ageInTicks * 0.1f) * (double)0.1f + (double)0.1f;
        double bob2 = Math.sin(ageInTicks * 0.1f) * (double)0.1f + (double)0.1f;
        if (!itemRight.m_41619_()) {
            matrixStackIn.m_85836_();
            this.translateToHand(false, matrixStackIn);
            matrixStackIn.m_85837_(-0.5, (double)0.1f - bob1, (double)-0.1f);
            matrixStackIn.m_85841_(0.9f * (1.0f - rightSwap), 0.9f * (1.0f - rightSwap), 0.9f * (1.0f - rightSwap));
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(180.0f));
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f));
            if (itemRight.m_41720_() instanceof ShieldItem) {
                matrixStackIn.m_252880_(-0.1f, 0.0f, -0.4f);
                matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(90.0f));
            }
            matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(-10.0f));
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(360.0f * rightSwap));
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(-40.0f * attackprogress));
            Minecraft.m_91087_().m_91291_().m_269128_(itemRight, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, rightSwap > 0.0f ? (int)(-100.0f * rightSwap) : packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), matrixStackIn, bufferIn, entitylivingbaseIn.m_9236_(), 0);
            matrixStackIn.m_85849_();
        }
        if (!itemLeft.m_41619_()) {
            matrixStackIn.m_85836_();
            this.translateToHand(false, matrixStackIn);
            matrixStackIn.m_85837_((double)0.45f, (double)0.1f - bob2, (double)-0.1f);
            matrixStackIn.m_85841_(0.9f * (1.0f - leftSwap), 0.9f * (1.0f - leftSwap), 0.9f * (1.0f - leftSwap));
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(180.0f));
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f));
            int clampedLight = (int)Math.floor((float)packedLightIn * (1.0f - leftSwap));
            if (itemLeft.m_41720_() instanceof ShieldItem) {
                matrixStackIn.m_252880_(-0.2f, 0.0f, -0.4f);
                matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(90.0f));
            }
            matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(10.0f));
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(360.0f * leftSwap));
            Minecraft.m_91087_().m_91291_().m_269128_(itemLeft, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, leftSwap > 0.0f ? (int)(-100.0f * leftSwap) : packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), matrixStackIn, bufferIn, entitylivingbaseIn.m_9236_(), 0);
            matrixStackIn.m_85849_();
        }
    }

    protected void translateToHand(boolean left, PoseStack matrixStack) {
        ((ModelMimicube)this.m_117386_()).root.translateAndRotate(matrixStack);
        ((ModelMimicube)this.m_117386_()).innerbody.translateAndRotate(matrixStack);
    }
}
