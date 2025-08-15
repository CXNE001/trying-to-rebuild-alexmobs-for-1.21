/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.PoseStack$Pose
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelSunbird;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.entity.EntitySunbird;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class RenderSunbird
extends MobRenderer<EntitySunbird, ModelSunbird> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/sunbird.png");
    private static final ResourceLocation TEXTURE_GLOW = new ResourceLocation("alexsmobs:textures/entity/sunbird_glow.png");

    public RenderSunbird(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelSunbird(), 0.5f);
        this.m_115326_(new LayerScorch(this));
    }

    private static void vertex(VertexConsumer p_114090_, Matrix4f p_114091_, Matrix3f p_114092_, int p_114093_, float p_114094_, float p_114095_, int p_114096_, int p_114097_) {
        p_114090_.m_252986_(p_114091_, p_114094_, p_114095_, 0.0f).m_6122_(255, 255, 255, 100).m_7421_((float)p_114096_, (float)p_114097_).m_86008_(OverlayTexture.f_118083_).m_85969_(p_114093_).m_252939_(p_114092_, 0.0f, 1.0f, 0.0f).m_5752_();
    }

    public void render(EntitySunbird entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int light) {
        super.m_7392_((Mob)entity, yaw, partialTicks, poseStack, buffer, light);
        float ageInTicks = (float)entity.f_19797_ + partialTicks;
        float scale = (12.0f + (float)Math.sin(ageInTicks * 0.3f)) * entity.getScorchProgress(partialTicks);
        if (scale > 0.0f) {
            poseStack.m_85836_();
            poseStack.m_252880_(0.0f, entity.m_20206_() * 0.5f, 0.0f);
            poseStack.m_252781_(this.f_114476_.m_253208_());
            poseStack.m_252781_(Axis.f_252436_.m_252977_(180.0f));
            poseStack.m_85836_();
            poseStack.m_252781_(Axis.f_252403_.m_252977_(ageInTicks * 8.0f));
            poseStack.m_252880_(-scale * 0.5f, -scale * 0.5f, 0.0f);
            PoseStack.Pose posestack$pose = poseStack.m_85850_();
            Matrix4f matrix4f = posestack$pose.m_252922_();
            Matrix3f matrix3f = posestack$pose.m_252943_();
            VertexConsumer vertexconsumer = buffer.m_6299_(AMRenderTypes.getSunbirdShine());
            RenderSunbird.vertex(vertexconsumer, matrix4f, matrix3f, light, 0.0f, 0.0f, 0, 1);
            RenderSunbird.vertex(vertexconsumer, matrix4f, matrix3f, light, scale, 0.0f, 1, 1);
            RenderSunbird.vertex(vertexconsumer, matrix4f, matrix3f, light, scale, scale, 1, 0);
            RenderSunbird.vertex(vertexconsumer, matrix4f, matrix3f, light, 0.0f, scale, 0, 0);
            poseStack.m_85849_();
            poseStack.m_85849_();
        }
    }

    protected void scale(EntitySunbird entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }

    protected int getBlockLightLevel(EntitySunbird entityIn, BlockPos partialTicks) {
        return 15;
    }

    public ResourceLocation getTextureLocation(EntitySunbird entity) {
        return TEXTURE;
    }

    static class LayerScorch
    extends RenderLayer<EntitySunbird, ModelSunbird> {
        public LayerScorch(RenderSunbird p_i50928_1_) {
            super((RenderLayerParent)p_i50928_1_);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntitySunbird entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            VertexConsumer scorch = bufferIn.m_6299_(AMRenderTypes.getEyesAlphaEnabled(TEXTURE_GLOW));
            float alpha = entitylivingbaseIn.getScorchProgress(partialTicks);
            ((ModelSunbird)this.m_117386_()).m_7695_(matrixStackIn, scorch, 240, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, alpha);
        }
    }
}
