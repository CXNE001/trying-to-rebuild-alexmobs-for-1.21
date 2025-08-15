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

import com.github.alexthe666.alexsmobs.client.model.ModelFart;
import com.github.alexthe666.alexsmobs.entity.EntityFart;
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

public class RenderFart
extends EntityRenderer<EntityFart> {
    private static final ResourceLocation FART_TEXTURE = new ResourceLocation("alexsmobs:textures/entity/fart.png");
    private static final ModelFart MODEL = new ModelFart();

    public RenderFart(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }

    public void render(EntityFart entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        float f = Math.min((float)entityIn.f_19797_ + partialTicks, 30.0f) / 30.0f;
        float alpha = 1.0f - f;
        matrixStackIn.m_85836_();
        matrixStackIn.m_85837_(0.0, (double)0.15f, 0.0);
        matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(Mth.m_14179_((float)partialTicks, (float)entityIn.f_19859_, (float)entityIn.m_146908_()) - 180.0f));
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(Mth.m_14179_((float)partialTicks, (float)entityIn.f_19860_, (float)entityIn.m_146909_())));
        VertexConsumer ivertexbuilder = bufferIn.m_6299_(RenderType.m_110473_((ResourceLocation)FART_TEXTURE));
        MODEL.setupAnim(entityIn, 0.0f, 0.0f, partialTicks, 0.0f, 0.0f);
        MODEL.m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, alpha);
        matrixStackIn.m_85849_();
        super.m_7392_((Entity)entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public ResourceLocation getTextureLocation(EntityFart entity) {
        return FART_TEXTURE;
    }
}
