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
 *  org.joml.Quaternionf
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelStraddleboard;
import com.github.alexthe666.alexsmobs.entity.EntityStraddleboard;
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
import org.joml.Quaternionf;

public class RenderStraddleboard
extends EntityRenderer<EntityStraddleboard> {
    private static final ResourceLocation TEXTURE_OVERLAY = new ResourceLocation("alexsmobs:textures/entity/straddleboard_overlay.png");
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/straddleboard.png");
    private static final ModelStraddleboard BOARD_MODEL = new ModelStraddleboard();

    public RenderStraddleboard(EntityRendererProvider.Context renderManager) {
        super(renderManager);
    }

    public ResourceLocation getTextureLocation(EntityStraddleboard entity) {
        return TEXTURE;
    }

    public void render(EntityStraddleboard entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.m_85836_();
        matrixStackIn.m_252781_(new Quaternionf().rotateY((float)Math.PI));
        matrixStackIn.m_252781_(Axis.f_252392_.m_252977_(Mth.m_14179_((float)partialTicks, (float)entityIn.f_19859_, (float)entityIn.m_146908_()) + 180.0f));
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(Mth.m_14179_((float)partialTicks, (float)entityIn.f_19860_, (float)entityIn.m_146909_())));
        matrixStackIn.m_85836_();
        boolean lava = entityIn.m_20160_();
        float f2 = entityIn.getRockingAngle(partialTicks);
        if (!Mth.m_14033_((float)f2, (float)0.0f)) {
            matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(entityIn.getRockingAngle(partialTicks)));
        }
        int k = entityIn.getColor();
        float r = (float)(k >> 16 & 0xFF) / 255.0f;
        float g = (float)(k >> 8 & 0xFF) / 255.0f;
        float b = (float)(k & 0xFF) / 255.0f;
        float boardRot = entityIn.prevBoardRot + partialTicks * (entityIn.getBoardRot() - entityIn.prevBoardRot);
        matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(boardRot));
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(180.0f));
        matrixStackIn.m_252880_(0.0f, -1.5f - Math.abs(boardRot * 0.007f) - (lava ? 0.0f : 0.25f), 0.0f);
        BOARD_MODEL.animateBoard(entityIn, (float)entityIn.f_19797_ + partialTicks);
        VertexConsumer ivertexbuilder2 = bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)TEXTURE_OVERLAY));
        BOARD_MODEL.m_7695_(matrixStackIn, ivertexbuilder2, packedLightIn, OverlayTexture.f_118083_, r, g, b, 1.0f);
        VertexConsumer ivertexbuilder = bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)TEXTURE));
        BOARD_MODEL.m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.m_85849_();
        matrixStackIn.m_85849_();
    }
}
