/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelGuster;
import com.github.alexthe666.alexsmobs.entity.EntityGust;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class RenderGust
extends EntityRenderer<EntityGust> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/guster.png");
    private final ModelGuster model = new ModelGuster();

    public RenderGust(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    public void render(EntityGust entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.m_85836_();
        matrixStackIn.m_85837_(0.0, 0.5, 0.0);
        if (!entityIn.getVertical()) {
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(180.0f));
        } else {
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(-180.0f));
        }
        matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(Mth.m_14179_((float)partialTicks, (float)entityIn.f_19859_, (float)entityIn.m_146908_()) - 90.0f));
        matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
        VertexConsumer ivertexbuilder = bufferIn.m_6299_(RenderType.m_110473_((ResourceLocation)TEXTURE));
        this.model.hideEyes();
        this.model.m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        this.model.animateGust(entityIn, 0.0f, 0.0f, (float)entityIn.f_19797_ + partialTicks);
        this.model.showEyes();
        matrixStackIn.m_85849_();
        super.m_7392_((Entity)entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public ResourceLocation getTextureLocation(EntityGust entity) {
        return TEXTURE;
    }
}
