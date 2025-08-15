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
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 */
package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelAnteater;
import com.github.alexthe666.alexsmobs.client.model.ModelLeafcutterAnt;
import com.github.alexthe666.alexsmobs.client.render.RenderAnteater;
import com.github.alexthe666.alexsmobs.entity.EntityAnteater;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class LayerAnteaterTongueItem
extends RenderLayer<EntityAnteater, ModelAnteater> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/leafcutter_ant.png");
    private final ModelLeafcutterAnt ANT_MODEL = new ModelLeafcutterAnt();

    public LayerAnteaterTongueItem(RenderAnteater render) {
        super((RenderLayerParent)render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityAnteater anteater, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemstack = anteater.m_21205_();
        if (!itemstack.m_41619_() || anteater.hasAntOnTongue()) {
            double tongueM = Math.min(Math.sin(ageInTicks * 0.15f), 0.0);
            float scaleItem = -0.2f * (float)tongueM * (anteater.prevTongueProgress + (anteater.tongueProgress - anteater.prevTongueProgress) * partialTicks * 0.2f);
            matrixStackIn.m_85836_();
            if (anteater.m_6162_()) {
                matrixStackIn.m_85841_(0.35f, 0.35f, 0.35f);
                matrixStackIn.m_85837_(0.0, 2.8, 0.0);
            }
            matrixStackIn.m_85836_();
            this.translateToTongue(matrixStackIn);
            if (anteater.m_6162_()) {
                matrixStackIn.m_85837_(0.0, (double)0.2f, -0.22);
            }
            matrixStackIn.m_85837_(-0.0, 0.0, (double)-0.35f);
            matrixStackIn.m_85841_(scaleItem, scaleItem, scaleItem);
            if (anteater.hasAntOnTongue()) {
                matrixStackIn.m_85836_();
                matrixStackIn.m_252880_(0.0f, -1.35f, -0.01f);
                VertexConsumer ivertexbuilder = bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)TEXTURE));
                this.ANT_MODEL.animateAnteater(anteater, partialTicks);
                this.ANT_MODEL.m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
                matrixStackIn.m_85849_();
            } else {
                matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(90.0f));
                ItemInHandRenderer renderer = Minecraft.m_91087_().m_91290_().m_234586_();
                renderer.m_269530_((LivingEntity)anteater, itemstack, ItemDisplayContext.GROUND, false, matrixStackIn, bufferIn, packedLightIn);
            }
            matrixStackIn.m_85849_();
            matrixStackIn.m_85849_();
        }
    }

    protected void translateToTongue(PoseStack matrixStack) {
        ((ModelAnteater)this.m_117386_()).root.translateAndRotate(matrixStack);
        ((ModelAnteater)this.m_117386_()).body.translateAndRotate(matrixStack);
        ((ModelAnteater)this.m_117386_()).head.translateAndRotate(matrixStack);
        ((ModelAnteater)this.m_117386_()).snout.translateAndRotate(matrixStack);
        ((ModelAnteater)this.m_117386_()).tongue1.translateAndRotate(matrixStack);
        ((ModelAnteater)this.m_117386_()).tongue2.translateAndRotate(matrixStack);
    }
}
