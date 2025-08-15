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
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelMimicOctopus;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.OctopusColorRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityMimicOctopus;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class RenderMimicOctopus
extends MobRenderer<EntityMimicOctopus, ModelMimicOctopus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/mimic_octopus.png");
    private static final ResourceLocation TEXTURE_OVERLAY = new ResourceLocation("alexsmobs:textures/entity/mimic_octopus_overlay.png");
    private static final ResourceLocation TEXTURE_CREEPER = new ResourceLocation("alexsmobs:textures/entity/mimic_octopus_creeper.png");
    private static final ResourceLocation TEXTURE_GUARDIAN = new ResourceLocation("alexsmobs:textures/entity/mimic_octopus_guardian.png");
    private static final ResourceLocation TEXTURE_PUFFERFISH = new ResourceLocation("alexsmobs:textures/entity/mimic_octopus_pufferfish.png");
    private static final ResourceLocation TEXTURE_MIMICUBE = new ResourceLocation("alexsmobs:textures/entity/mimic_octopus_mimicube.png");
    private static final ResourceLocation TEXTURE_EYES = new ResourceLocation("alexsmobs:textures/entity/mimic_octopus_eyes.png");
    private static final ResourceLocation GUARDIAN_BEAM_TEXTURE = new ResourceLocation("textures/entity/guardian_beam.png");
    private static final RenderType BEAM_RENDER_TYPE = RenderType.m_110458_((ResourceLocation)GUARDIAN_BEAM_TEXTURE);

    public RenderMimicOctopus(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelMimicOctopus(), 0.4f);
        this.m_115326_(new OverlayLayer(this));
    }

    private static void vertex(VertexConsumer p_229108_0_, Matrix4f p_229108_1_, Matrix3f p_229108_2_, float p_229108_3_, float p_229108_4_, float p_229108_5_, int p_229108_6_, int p_229108_7_, int p_229108_8_, float p_229108_9_, float p_229108_10_) {
        p_229108_0_.m_252986_(p_229108_1_, p_229108_3_, p_229108_4_, p_229108_5_).m_6122_(p_229108_6_, p_229108_7_, p_229108_8_, 255).m_7421_(p_229108_9_, p_229108_10_).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_252939_(p_229108_2_, 0.0f, 1.0f, 0.0f).m_5752_();
    }

    public void render(EntityMimicOctopus entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        LivingEntity livingentity = entityIn.getGuardianLaser();
        if (livingentity != null) {
            float f = entityIn.getLaserAttackAnimationScale(partialTicks);
            float f1 = (float)entityIn.m_9236_().m_46467_() + partialTicks;
            float f2 = f1 * 0.5f % 1.0f;
            float f3 = entityIn.m_20192_();
            matrixStackIn.m_85836_();
            matrixStackIn.m_85837_(0.0, (double)f3, 0.0);
            Vec3 vector3d = this.getPosition(livingentity, (double)livingentity.m_20206_() * 0.5, partialTicks);
            Vec3 vector3d1 = this.getPosition((LivingEntity)entityIn, f3, partialTicks);
            Vec3 vector3d2 = vector3d.m_82546_(vector3d1);
            float f4 = (float)(vector3d2.m_82553_() + 1.0);
            vector3d2 = vector3d2.m_82541_();
            float f5 = (float)Math.acos(vector3d2.f_82480_);
            float f6 = (float)Math.atan2(vector3d2.f_82481_, vector3d2.f_82479_);
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_((1.5707964f - f6) * 57.295776f));
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(f5 * 57.295776f));
            boolean i = true;
            float f7 = f1 * 0.05f * -1.5f;
            float f8 = f * f;
            int j = 64 + (int)(f8 * 191.0f);
            int k = 32 + (int)(f8 * 191.0f);
            int l = 128 - (int)(f8 * 64.0f);
            float f9 = 0.2f;
            float f10 = 0.282f;
            float f11 = Mth.m_14089_((float)(f7 + 2.3561945f)) * 0.282f;
            float f12 = Mth.m_14031_((float)(f7 + 2.3561945f)) * 0.282f;
            float f13 = Mth.m_14089_((float)(f7 + 0.7853982f)) * 0.282f;
            float f14 = Mth.m_14031_((float)(f7 + 0.7853982f)) * 0.282f;
            float f15 = Mth.m_14089_((float)(f7 + 3.926991f)) * 0.282f;
            float f16 = Mth.m_14031_((float)(f7 + 3.926991f)) * 0.282f;
            float f17 = Mth.m_14089_((float)(f7 + 5.4977875f)) * 0.282f;
            float f18 = Mth.m_14031_((float)(f7 + 5.4977875f)) * 0.282f;
            float f19 = Mth.m_14089_((float)(f7 + (float)Math.PI)) * 0.2f;
            float f20 = Mth.m_14031_((float)(f7 + (float)Math.PI)) * 0.2f;
            float f21 = Mth.m_14089_((float)(f7 + 0.0f)) * 0.2f;
            float f22 = Mth.m_14031_((float)(f7 + 0.0f)) * 0.2f;
            float f23 = Mth.m_14089_((float)(f7 + 1.5707964f)) * 0.2f;
            float f24 = Mth.m_14031_((float)(f7 + 1.5707964f)) * 0.2f;
            float f25 = Mth.m_14089_((float)(f7 + 4.712389f)) * 0.2f;
            float f26 = Mth.m_14031_((float)(f7 + 4.712389f)) * 0.2f;
            float f27 = 0.0f;
            float f28 = 0.4999f;
            float f29 = -1.0f + f2;
            float f30 = f4 * 2.5f + f29;
            VertexConsumer ivertexbuilder = bufferIn.m_6299_(BEAM_RENDER_TYPE);
            PoseStack.Pose matrixstack$entry = matrixStackIn.m_85850_();
            Matrix4f matrix4f = matrixstack$entry.m_252922_();
            Matrix3f matrix3f = matrixstack$entry.m_252943_();
            RenderMimicOctopus.vertex(ivertexbuilder, matrix4f, matrix3f, f19, f4, f20, j, k, l, 0.4999f, f30);
            RenderMimicOctopus.vertex(ivertexbuilder, matrix4f, matrix3f, f19, 0.0f, f20, j, k, l, 0.4999f, f29);
            RenderMimicOctopus.vertex(ivertexbuilder, matrix4f, matrix3f, f21, 0.0f, f22, j, k, l, 0.0f, f29);
            RenderMimicOctopus.vertex(ivertexbuilder, matrix4f, matrix3f, f21, f4, f22, j, k, l, 0.0f, f30);
            RenderMimicOctopus.vertex(ivertexbuilder, matrix4f, matrix3f, f23, f4, f24, j, k, l, 0.4999f, f30);
            RenderMimicOctopus.vertex(ivertexbuilder, matrix4f, matrix3f, f23, 0.0f, f24, j, k, l, 0.4999f, f29);
            RenderMimicOctopus.vertex(ivertexbuilder, matrix4f, matrix3f, f25, 0.0f, f26, j, k, l, 0.0f, f29);
            RenderMimicOctopus.vertex(ivertexbuilder, matrix4f, matrix3f, f25, f4, f26, j, k, l, 0.0f, f30);
            float f31 = 0.0f;
            if (entityIn.f_19797_ % 2 == 0) {
                f31 = 0.5f;
            }
            RenderMimicOctopus.vertex(ivertexbuilder, matrix4f, matrix3f, f11, f4, f12, j, k, l, 0.5f, f31 + 0.5f);
            RenderMimicOctopus.vertex(ivertexbuilder, matrix4f, matrix3f, f13, f4, f14, j, k, l, 1.0f, f31 + 0.5f);
            RenderMimicOctopus.vertex(ivertexbuilder, matrix4f, matrix3f, f17, f4, f18, j, k, l, 1.0f, f31);
            RenderMimicOctopus.vertex(ivertexbuilder, matrix4f, matrix3f, f15, f4, f16, j, k, l, 0.5f, f31);
            matrixStackIn.m_85849_();
        }
        super.m_7392_((Mob)entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    protected void scale(EntityMimicOctopus octo, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_252880_(0.0f, -0.02f, 0.0f);
        matrixStackIn.m_85841_(0.9f * octo.m_6134_(), 0.9f * octo.m_6134_(), 0.9f * octo.m_6134_());
    }

    public boolean shouldRender(EntityMimicOctopus livingEntityIn, Frustum camera, double camX, double camY, double camZ) {
        LivingEntity livingentity;
        if (super.m_5523_((Mob)livingEntityIn, camera, camX, camY, camZ)) {
            return true;
        }
        if (livingEntityIn.hasGuardianLaser() && (livingentity = livingEntityIn.getGuardianLaser()) != null) {
            Vec3 vector3d = this.getPosition(livingentity, (double)livingentity.m_20206_() * 0.5, 1.0f);
            Vec3 vector3d1 = this.getPosition((LivingEntity)livingEntityIn, livingEntityIn.m_20192_(), 1.0f);
            return camera.m_113029_(new AABB(vector3d1.f_82479_, vector3d1.f_82480_, vector3d1.f_82481_, vector3d.f_82479_, vector3d.f_82480_, vector3d.f_82481_));
        }
        return false;
    }

    private Vec3 getPosition(LivingEntity entityLivingBaseIn, double p_177110_2_, float p_177110_4_) {
        double d0 = Mth.m_14139_((double)p_177110_4_, (double)entityLivingBaseIn.f_19790_, (double)entityLivingBaseIn.m_20185_());
        double d1 = Mth.m_14139_((double)p_177110_4_, (double)entityLivingBaseIn.f_19791_, (double)entityLivingBaseIn.m_20186_()) + p_177110_2_;
        double d2 = Mth.m_14139_((double)p_177110_4_, (double)entityLivingBaseIn.f_19792_, (double)entityLivingBaseIn.m_20189_());
        return new Vec3(d0, d1, d2);
    }

    public ResourceLocation getTextureLocation(EntityMimicOctopus entity) {
        return TEXTURE;
    }

    static class OverlayLayer
    extends RenderLayer<EntityMimicOctopus, ModelMimicOctopus> {
        public OverlayLayer(RenderMimicOctopus render) {
            super((RenderLayerParent)render);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource buffer, int packedLightIn, EntityMimicOctopus entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            float transProgress = entitylivingbaseIn.prevTransProgress + (entitylivingbaseIn.transProgress - entitylivingbaseIn.prevTransProgress) * partialTicks;
            float colorProgress = (entitylivingbaseIn.prevColorShiftProgress + (entitylivingbaseIn.colorShiftProgress - entitylivingbaseIn.prevColorShiftProgress) * partialTicks) * 0.2f;
            float r = 1.0f;
            float g = 1.0f;
            float b = 1.0f;
            float a = 1.0f;
            float startR = 1.0f;
            float startG = 1.0f;
            float startB = 1.0f;
            float startA = 1.0f;
            float finR = 1.0f;
            float finG = 1.0f;
            float finB = 1.0f;
            float finA = 1.0f;
            if (entitylivingbaseIn.getPrevMimicState() == EntityMimicOctopus.MimicState.OVERLAY) {
                if (entitylivingbaseIn.getPrevMimickedBlock() != null) {
                    int j = OctopusColorRegistry.getBlockColor(entitylivingbaseIn.getPrevMimickedBlock());
                    startR = (float)(j >> 16 & 0xFF) / 255.0f;
                    startG = (float)(j >> 8 & 0xFF) / 255.0f;
                    startB = (float)(j & 0xFF) / 255.0f;
                } else {
                    startA = 0.0f;
                }
            }
            if (entitylivingbaseIn.getMimicState() == EntityMimicOctopus.MimicState.OVERLAY) {
                if (entitylivingbaseIn.getMimickedBlock() != null) {
                    int i = OctopusColorRegistry.getBlockColor(entitylivingbaseIn.getMimickedBlock());
                    finR = (float)(i >> 16 & 0xFF) / 255.0f;
                    finG = (float)(i >> 8 & 0xFF) / 255.0f;
                    finB = (float)(i & 0xFF) / 255.0f;
                } else {
                    finA = 0.0f;
                }
                r = startR + (finR - startR) * colorProgress;
                g = startG + (finG - startG) * colorProgress;
                b = startB + (finB - startB) * colorProgress;
                a = startA + (finA - startA) * colorProgress;
            }
            if (a == 1.0f) {
                a *= 0.9f + 0.1f * (float)Math.sin((float)entitylivingbaseIn.f_19797_ * 0.1f);
            }
            if (entitylivingbaseIn.getPrevMimicState() != null) {
                float alphaPrev = 1.0f - transProgress * 0.2f;
                VertexConsumer prev = buffer.m_6299_(AMRenderTypes.m_110473_((ResourceLocation)this.getFor(entitylivingbaseIn.getPrevMimicState())));
                if (entitylivingbaseIn.getPrevMimicState() == entitylivingbaseIn.getMimicState()) {
                    alphaPrev *= a;
                }
                ((ModelMimicOctopus)this.m_117386_()).m_7695_(matrixStackIn, prev, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), r, g, b, alphaPrev);
            }
            float alphaCurrent = transProgress * 0.2f;
            VertexConsumer current = buffer.m_6299_(AMRenderTypes.m_110473_((ResourceLocation)this.getFor(entitylivingbaseIn.getMimicState())));
            ((ModelMimicOctopus)this.m_117386_()).m_7695_(matrixStackIn, current, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), r, g, b, a * alphaCurrent);
            VertexConsumer eyes = buffer.m_6299_(AMRenderTypes.m_110473_((ResourceLocation)TEXTURE_EYES));
            ((ModelMimicOctopus)this.m_117386_()).m_7695_(matrixStackIn, eyes, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
        }

        public ResourceLocation getFor(EntityMimicOctopus.MimicState state) {
            if (state == EntityMimicOctopus.MimicState.CREEPER) {
                return TEXTURE_CREEPER;
            }
            if (state == EntityMimicOctopus.MimicState.GUARDIAN) {
                return TEXTURE_GUARDIAN;
            }
            if (state == EntityMimicOctopus.MimicState.PUFFERFISH) {
                return TEXTURE_PUFFERFISH;
            }
            if (state == EntityMimicOctopus.MimicState.MIMICUBE) {
                return TEXTURE_MIMICUBE;
            }
            return TEXTURE_OVERLAY;
        }
    }
}
