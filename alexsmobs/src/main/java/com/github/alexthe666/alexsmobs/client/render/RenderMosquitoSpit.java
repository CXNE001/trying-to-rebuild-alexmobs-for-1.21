/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.model.LlamaSpitModel
 *  net.minecraft.client.model.geom.ModelLayers
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.projectile.LlamaSpit
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.entity.EntityMosquitoSpit;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.LlamaSpitModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.LlamaSpit;

public class RenderMosquitoSpit
extends EntityRenderer<EntityMosquitoSpit> {
    private static final ResourceLocation SPIT_TEXTURE = new ResourceLocation("alexsmobs:textures/entity/mosquito_spit.png");
    private final LlamaSpitModel<LlamaSpit> model;

    public RenderMosquitoSpit(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
        this.model = new LlamaSpitModel(renderManagerIn.m_174023_(ModelLayers.f_171196_));
    }

    public void render(EntityMosquitoSpit entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.m_85836_();
        matrixStackIn.m_85837_(0.0, (double)0.15f, 0.0);
        matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(Mth.m_14179_((float)partialTicks, (float)entityIn.f_19859_, (float)entityIn.m_146908_()) - 90.0f));
        matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(Mth.m_14179_((float)partialTicks, (float)entityIn.f_19860_, (float)entityIn.m_146909_())));
        VertexConsumer ivertexbuilder = bufferIn.m_6299_(this.model.m_103119_(SPIT_TEXTURE));
        this.model.m_7695_(matrixStackIn, ivertexbuilder, 240, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.m_85849_();
        super.m_7392_((Entity)entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public ResourceLocation getTextureLocation(EntityMosquitoSpit entity) {
        return SPIT_TEXTURE;
    }
}
