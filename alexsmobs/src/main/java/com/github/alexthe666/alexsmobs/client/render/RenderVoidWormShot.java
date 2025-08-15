/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  org.joml.Quaternionf
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelVoidWormShot;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.entity.EntityVoidWormShot;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;

public class RenderVoidWormShot
extends EntityRenderer<EntityVoidWormShot> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/void_worm/void_worm_shot.png");
    private static final ModelVoidWormShot MODEL = new ModelVoidWormShot();

    public RenderVoidWormShot(EntityRendererProvider.Context renderManager) {
        super(renderManager);
    }

    public ResourceLocation getTextureLocation(EntityVoidWormShot entity) {
        return TEXTURE;
    }

    public void render(EntityVoidWormShot entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        float home;
        matrixStackIn.m_85836_();
        matrixStackIn.m_252781_(new Quaternionf().rotateX(Maths.rad(180.0)));
        matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(Mth.m_14179_((float)partialTicks, (float)entityIn.f_19859_, (float)entityIn.m_146908_())));
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(Mth.m_14179_((float)partialTicks, (float)entityIn.f_19860_, (float)entityIn.m_146909_())));
        matrixStackIn.m_85836_();
        MODEL.animate(entityIn, (float)entityIn.f_19797_ + partialTicks);
        float colorize = home = (entityIn.prevStopHomingProgress + (entityIn.getStopHomingProgress() - entityIn.prevStopHomingProgress) * partialTicks) / 40.0f;
        matrixStackIn.m_252880_(0.0f, -1.5f, 0.0f);
        VertexConsumer ivertexbuilder = bufferIn.m_6299_(AMRenderTypes.getFullBright(this.getTextureLocation(entityIn)));
        MODEL.m_7695_(matrixStackIn, ivertexbuilder, 210, OverlayTexture.f_118083_, Math.max(colorize, 0.2f), Math.max(colorize, 0.2f), 1.0f, 1.0f);
        matrixStackIn.m_85849_();
        matrixStackIn.m_85849_();
    }
}
