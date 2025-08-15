/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemInHandRenderer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ItemLike
 */
package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelAncientDart;
import com.github.alexthe666.alexsmobs.client.model.ModelCapuchinMonkey;
import com.github.alexthe666.alexsmobs.client.render.RenderCapuchinMonkey;
import com.github.alexthe666.alexsmobs.entity.EntityCapuchinMonkey;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class LayerCapuchinItem
extends RenderLayer<EntityCapuchinMonkey, ModelCapuchinMonkey> {
    public static final ResourceLocation DART_TEXTURE = new ResourceLocation("alexsmobs:textures/entity/ancient_dart.png");
    public static final ModelAncientDart DART_MODEL = new ModelAncientDart();

    public LayerCapuchinItem(RenderCapuchinMonkey render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityCapuchinMonkey entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.hasDart()) {
            matrixStackIn.m_85836_();
            if (entitylivingbaseIn.m_6162_()) {
                matrixStackIn.m_85841_(0.35f, 0.35f, 0.35f);
                matrixStackIn.m_85837_(0.5, 2.6, 0.15);
                this.translateToHand(false, matrixStackIn);
                matrixStackIn.m_85837_(-0.65, -0.75, (double)-0.1f);
                matrixStackIn.m_85841_(2.8f, 2.8f, 2.8f);
            } else {
                this.translateToHand(false, matrixStackIn);
            }
            float f = 0.0f;
            if (entitylivingbaseIn.getAnimation() == EntityCapuchinMonkey.ANIMATION_THROW) {
                f = entitylivingbaseIn.getAnimationTick() < 6 ? Math.min(3.0f, (float)entitylivingbaseIn.getAnimationTick() + partialTicks) * 60.0f : (12.0f - ((float)entitylivingbaseIn.getAnimationTick() + partialTicks)) * 30.0f;
            }
            matrixStackIn.m_252880_(0.0f, 0.5f, 0.0f);
            matrixStackIn.m_85841_(1.2f, 1.2f, 1.2f);
            matrixStackIn.m_85836_();
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(f));
            VertexConsumer ivertexbuilder = bufferIn.m_6299_(DART_MODEL.m_103119_(DART_TEXTURE));
            DART_MODEL.m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
            matrixStackIn.m_85849_();
            matrixStackIn.m_85849_();
        } else if (entitylivingbaseIn.getAnimation() == EntityCapuchinMonkey.ANIMATION_THROW && entitylivingbaseIn.getAnimationTick() <= 5) {
            ItemStack itemstack = new ItemStack((ItemLike)Items.f_42594_);
            matrixStackIn.m_85836_();
            if (entitylivingbaseIn.m_6162_()) {
                matrixStackIn.m_85841_(0.35f, 0.35f, 0.35f);
                matrixStackIn.m_85837_(0.5, 2.6, 0.15);
                this.translateToHand(false, matrixStackIn);
                matrixStackIn.m_252880_(-0.4f, 0.75f, -0.0f);
                matrixStackIn.m_85841_(2.8f, 2.8f, 2.8f);
            } else {
                this.translateToHand(false, matrixStackIn);
                matrixStackIn.m_252880_(0.125f, 0.5f, 0.1f);
            }
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(-2.5f));
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(-90.0f));
            ItemInHandRenderer renderer = Minecraft.m_91087_().m_91290_().m_234586_();
            renderer.m_269530_((LivingEntity)entitylivingbaseIn, itemstack, ItemDisplayContext.GROUND, false, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.m_85849_();
        }
    }

    protected void translateToHand(boolean left, PoseStack matrixStack) {
        ((ModelCapuchinMonkey)this.m_117386_()).root.translateAndRotate(matrixStack);
        ((ModelCapuchinMonkey)this.m_117386_()).body.translateAndRotate(matrixStack);
        ((ModelCapuchinMonkey)this.m_117386_()).arm_right.translateAndRotate(matrixStack);
    }
}
