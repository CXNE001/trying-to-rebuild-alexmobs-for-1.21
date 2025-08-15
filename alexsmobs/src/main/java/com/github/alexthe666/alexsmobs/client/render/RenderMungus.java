/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.PoseStack$Pose
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.block.BlockRenderDispatcher
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelMungus;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.entity.EntityMungus;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class RenderMungus
extends MobRenderer<EntityMungus, ModelMungus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/mungus.png");
    private static final ResourceLocation BEAM_TEXTURE = new ResourceLocation("alexsmobs:textures/entity/mungus_beam.png");
    private static final ResourceLocation TEXTURE_BEAM_OVERLAY = new ResourceLocation("alexsmobs:textures/entity/mungus_beam_overlay.png");
    private static final ResourceLocation TEXTURE_SACK_OVERLAY = new ResourceLocation("alexsmobs:textures/entity/mungus_sack.png");
    private static final ResourceLocation TEXTURE_SHOES = new ResourceLocation("alexsmobs:textures/entity/mungus_shoes.png");
    private static final RenderType beamType = AMRenderTypes.getEyesNoFog(BEAM_TEXTURE);

    public RenderMungus(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelMungus(0.0f), 0.5f);
        this.m_115326_(new MungusSackLayer(this));
        this.m_115326_(new MungusMushroomLayer(this));
    }

    protected boolean isShaking(EntityMungus mungus) {
        return mungus.isReverting();
    }

    private static void vertex(VertexConsumer p_229108_0_, Matrix4f p_229108_1_, Matrix3f p_229108_2_, float p_229108_3_, float p_229108_4_, float p_229108_5_, int p_229108_6_, int p_229108_7_, int p_229108_8_, float p_229108_9_, float p_229108_10_) {
        p_229108_0_.m_252986_(p_229108_1_, p_229108_3_, p_229108_4_, p_229108_5_).m_6122_(p_229108_6_, p_229108_7_, p_229108_8_, 255).m_7421_(p_229108_9_, p_229108_10_).m_86008_(OverlayTexture.f_118083_).m_85969_(240).m_252939_(p_229108_2_, 0.0f, 1.0f, 0.0f).m_5752_();
    }

    protected void setupRotations(EntityMungus entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if (entityLiving.f_20919_ > 0) {
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f - rotationYaw));
            float f = ((float)entityLiving.f_20919_ + partialTicks - 1.0f) / 20.0f * 1.6f;
            f = Mth.m_14116_((float)f);
            if (f > 1.0f) {
                f = 1.0f;
            }
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(f * -90.0f));
        } else {
            super.m_7523_((LivingEntity)entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        }
    }

    protected float getFlipDegrees(EntityMungus p_77037_1_) {
        return 0.0f;
    }

    protected void scale(EntityMungus entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        String s = ChatFormatting.m_126649_((String)entitylivingbaseIn.m_7755_().getString());
        if (s != null && s.toLowerCase().contains("drip")) {
            matrixStackIn.m_252880_(0.0f, entitylivingbaseIn.m_6162_() ? -0.075f : -0.15f, 0.0f);
        }
    }

    public boolean shouldRender(EntityMungus livingEntityIn, Frustum camera, double camX, double camY, double camZ) {
        BlockPos pos;
        if (super.m_5523_((Mob)livingEntityIn, camera, camX, camY, camZ)) {
            return true;
        }
        if (livingEntityIn.getBeamTarget() != null && (pos = livingEntityIn.getBeamTarget()) != null) {
            Vec3 vector3d = Vec3.m_82528_((Vec3i)pos);
            Vec3 vector3dCorner = Vec3.m_82528_((Vec3i)pos).m_82520_(1.0, 1.0, 1.0);
            Vec3 vector3d1 = this.getPosition((LivingEntity)livingEntityIn, livingEntityIn.m_20192_(), 1.0f);
            return camera.m_113029_(new AABB(vector3d1.f_82479_, vector3d1.f_82480_, vector3d1.f_82481_, vector3d.f_82479_, vector3d.f_82480_, vector3d.f_82481_)) || camera.m_113029_(new AABB(vector3d1.f_82479_, vector3d1.f_82480_, vector3d1.f_82481_, vector3dCorner.f_82479_, vector3dCorner.f_82480_, vector3dCorner.f_82481_));
        }
        return false;
    }

    private Vec3 getPosition(LivingEntity entityLivingBaseIn, double p_177110_2_, float p_177110_4_) {
        double d0 = Mth.m_14139_((double)p_177110_4_, (double)entityLivingBaseIn.f_19790_, (double)entityLivingBaseIn.m_20185_());
        double d1 = Mth.m_14139_((double)p_177110_4_, (double)entityLivingBaseIn.f_19791_, (double)entityLivingBaseIn.m_20186_()) + p_177110_2_;
        double d2 = Mth.m_14139_((double)p_177110_4_, (double)entityLivingBaseIn.f_19792_, (double)entityLivingBaseIn.m_20189_());
        return new Vec3(d0, d1, d2);
    }

    public void render(EntityMungus entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.m_7392_((Mob)entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        BlockPos target = entityIn.getBeamTarget();
        if (target != null) {
            float f = 1.0f;
            float f1 = (float)entityIn.m_9236_().m_46467_() + partialTicks;
            float f2 = -1.0f * (f1 * 0.15f % 1.0f);
            float f3 = 1.13f;
            if (entityIn.m_6162_()) {
                f3 = 0.555f;
            }
            matrixStackIn.m_85836_();
            matrixStackIn.m_85837_(0.0, (double)f3, 0.0);
            Vec3 vector3d = Vec3.m_82514_((Vec3i)target, (double)0.15f);
            Vec3 vector3d1 = this.getPosition((LivingEntity)entityIn, f3, partialTicks);
            Vec3 vector3d2 = vector3d.m_82546_(vector3d1);
            float f4 = (float)vector3d2.m_82553_();
            vector3d2 = vector3d2.m_82541_();
            float f5 = (float)Math.acos(vector3d2.f_82480_);
            float f6 = (float)Math.atan2(vector3d2.f_82481_, vector3d2.f_82479_);
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_((1.5707964f - f6) * 57.295776f));
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(f5 * 57.295776f));
            boolean i = true;
            float f7 = f1 * 0.05f * 1.5f;
            float f8 = 1.0f;
            int j = (int)(f8 * 255.0f);
            int k = (int)(f8 * 255.0f);
            int l = (int)(f8 * 255.0f);
            float f9 = 0.2f;
            float f10 = 0.282f;
            float f11 = Mth.m_14089_((float)2.3561945f) * 0.8f;
            float f12 = Mth.m_14031_((float)2.3561945f) * 0.8f;
            float f13 = Mth.m_14089_((float)0.7853982f) * 0.8f;
            float f14 = Mth.m_14031_((float)0.7853982f) * 0.8f;
            float f15 = Mth.m_14089_((float)3.926991f) * 0.8f;
            float f16 = Mth.m_14031_((float)3.926991f) * 0.8f;
            float f17 = Mth.m_14089_((float)5.4977875f) * 0.8f;
            float f18 = Mth.m_14031_((float)5.4977875f) * 0.8f;
            float f19 = Mth.m_14089_((float)((float)Math.PI)) * 0.4f;
            float f20 = Mth.m_14031_((float)((float)Math.PI)) * 0.4f;
            float f21 = Mth.m_14089_((float)0.0f) * 0.4f;
            float f22 = Mth.m_14031_((float)0.0f) * 0.4f;
            float f23 = Mth.m_14089_((float)1.5707964f) * 0.4f;
            float f24 = Mth.m_14031_((float)1.5707964f) * 0.4f;
            float f25 = Mth.m_14089_((float)4.712389f) * 0.4f;
            float f26 = Mth.m_14031_((float)4.712389f) * 0.4f;
            float f27 = 0.0f;
            float f28 = 0.4999f;
            float f29 = -1.0f + f2;
            float f30 = f4 * 0.5f + f29;
            VertexConsumer ivertexbuilder = bufferIn.m_6299_(beamType);
            PoseStack.Pose matrixstack$entry = matrixStackIn.m_85850_();
            Matrix4f matrix4f = matrixstack$entry.m_252922_();
            Matrix3f matrix3f = matrixstack$entry.m_252943_();
            RenderMungus.vertex(ivertexbuilder, matrix4f, matrix3f, f19, f4, f20, j, k, l, 0.4999f, f30);
            RenderMungus.vertex(ivertexbuilder, matrix4f, matrix3f, f19, 0.0f, f20, j, k, l, 0.4999f, f29);
            RenderMungus.vertex(ivertexbuilder, matrix4f, matrix3f, f21, 0.0f, f22, j, k, l, 0.0f, f29);
            RenderMungus.vertex(ivertexbuilder, matrix4f, matrix3f, f21, f4, f22, j, k, l, 0.0f, f30);
            RenderMungus.vertex(ivertexbuilder, matrix4f, matrix3f, f23, f4, f24, j, k, l, 0.4999f, f30);
            RenderMungus.vertex(ivertexbuilder, matrix4f, matrix3f, f23, 0.0f, f24, j, k, l, 0.4999f, f29);
            RenderMungus.vertex(ivertexbuilder, matrix4f, matrix3f, f25, 0.0f, f26, j, k, l, 0.0f, f29);
            RenderMungus.vertex(ivertexbuilder, matrix4f, matrix3f, f25, f4, f26, j, k, l, 0.0f, f30);
            float f31 = 0.0f;
            if (entityIn.f_19797_ % 4 > 1) {
                f31 = 0.5f;
            }
            RenderMungus.vertex(ivertexbuilder, matrix4f, matrix3f, f11, f4, f12, j, k, l, 0.5f, f31 + 0.5f);
            RenderMungus.vertex(ivertexbuilder, matrix4f, matrix3f, f13, f4, f14, j, k, l, 1.0f, f31 + 0.5f);
            RenderMungus.vertex(ivertexbuilder, matrix4f, matrix3f, f17, f4, f18, j, k, l, 1.0f, f31);
            RenderMungus.vertex(ivertexbuilder, matrix4f, matrix3f, f15, f4, f16, j, k, l, 0.5f, f31);
            matrixStackIn.m_85849_();
        }
    }

    public ResourceLocation getTextureLocation(EntityMungus entity) {
        return TEXTURE;
    }

    static class MungusSackLayer
    extends RenderLayer<EntityMungus, ModelMungus> {
        public MungusSackLayer(RenderMungus p_i50928_1_) {
            super((RenderLayerParent)p_i50928_1_);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityMungus entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            String s;
            VertexConsumer lead = bufferIn.m_6299_(AMRenderTypes.getEyesFlickering(TEXTURE_SACK_OVERLAY, 0.0f));
            float alpha = 0.75f + (Mth.m_14089_((float)(ageInTicks * 0.2f)) + 1.0f) * 0.125f;
            ((ModelMungus)this.m_117386_()).m_7695_(matrixStackIn, lead, 240, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, alpha);
            if (entitylivingbaseIn.getBeamTarget() != null) {
                VertexConsumer beam = bufferIn.m_6299_(AMRenderTypes.getGhost(TEXTURE_BEAM_OVERLAY));
                float beamAlpha = 0.75f + (Mth.m_14089_((float)(ageInTicks * 1.0f)) + 1.0f) * 0.125f;
                ((ModelMungus)this.m_117386_()).m_7695_(matrixStackIn, beam, 240, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, beamAlpha);
            }
            if ((s = ChatFormatting.m_126649_((String)entitylivingbaseIn.m_7755_().getString())) != null && s.toLowerCase().contains("drip")) {
                VertexConsumer shoeBuffer = bufferIn.m_6299_(AMRenderTypes.m_110458_((ResourceLocation)TEXTURE_SHOES));
                matrixStackIn.m_85836_();
                ((ModelMungus)this.m_117386_()).renderShoes();
                ((ModelMungus)this.m_117386_()).m_7695_(matrixStackIn, shoeBuffer, packedLightIn, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
                ((ModelMungus)this.m_117386_()).postRenderShoes();
                matrixStackIn.m_85849_();
            }
        }
    }

    static class MungusMushroomLayer
    extends RenderLayer<EntityMungus, ModelMungus> {
        public MungusMushroomLayer(RenderMungus p_i50928_1_) {
            super((RenderLayerParent)p_i50928_1_);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityMungus entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            BlockRenderDispatcher blockrendererdispatcher = Minecraft.m_91087_().m_91289_();
            BlockState blockstate = entitylivingbaseIn.getMushroomState();
            if (blockstate == null) {
                return;
            }
            int i = LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f);
            boolean altOrder = entitylivingbaseIn.isAltOrderMushroom();
            int mushroomCount = entitylivingbaseIn.getMushroomCount();
            matrixStackIn.m_85836_();
            if (entitylivingbaseIn.m_6162_()) {
                matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
                matrixStackIn.m_85837_(0.0, 1.5, 0.0);
            }
            matrixStackIn.m_85836_();
            this.translateToBody(matrixStackIn);
            if (mushroomCount == 1 && !altOrder || mushroomCount >= 2) {
                matrixStackIn.m_85836_();
                matrixStackIn.m_85837_((double)0.2f, (double)-1.4f, 0.15);
                matrixStackIn.m_85841_(-1.0f, -1.0f, 1.0f);
                matrixStackIn.m_85837_(-0.5, -0.5, -0.5);
                blockrendererdispatcher.m_110912_(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
                matrixStackIn.m_85849_();
            }
            if (mushroomCount == 1 && altOrder || mushroomCount >= 2) {
                matrixStackIn.m_85836_();
                matrixStackIn.m_85837_((double)-0.2f, -1.5, -0.2);
                matrixStackIn.m_85841_(-1.0f, -1.0f, 1.0f);
                matrixStackIn.m_85837_(-0.5, -0.5, -0.5);
                blockrendererdispatcher.m_110912_(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
                matrixStackIn.m_85849_();
            }
            if (mushroomCount >= 3) {
                matrixStackIn.m_85836_();
                matrixStackIn.m_85837_((double)0.76f, (double)-0.4f, 0.1);
                matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(90.0f));
                matrixStackIn.m_85841_(-1.0f, -1.0f, 1.0f);
                matrixStackIn.m_85837_(-0.5, -0.5, -0.5);
                blockrendererdispatcher.m_110912_(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
                matrixStackIn.m_85849_();
            }
            if (mushroomCount >= 4) {
                matrixStackIn.m_85836_();
                matrixStackIn.m_85837_((double)-0.76f, -1.0, 0.1);
                matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(-60.0f));
                matrixStackIn.m_85841_(-1.0f, -1.0f, 1.0f);
                matrixStackIn.m_85837_(-0.5, -0.5, -0.5);
                blockrendererdispatcher.m_110912_(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
                matrixStackIn.m_85849_();
            }
            if (mushroomCount >= 5) {
                matrixStackIn.m_85836_();
                matrixStackIn.m_85837_((double)-0.76f, (double)-0.1f, 0.1);
                matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(-100.0f));
                matrixStackIn.m_85841_(-1.0f, -1.0f, 1.0f);
                matrixStackIn.m_85837_(-0.5, -0.5, -0.5);
                blockrendererdispatcher.m_110912_(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
                matrixStackIn.m_85849_();
            }
            matrixStackIn.m_85849_();
            matrixStackIn.m_85849_();
        }

        protected void translateToBody(PoseStack matrixStack) {
            ((ModelMungus)this.m_117386_()).root.translateAndRotate(matrixStack);
            ((ModelMungus)this.m_117386_()).body.translateAndRotate(matrixStack);
        }
    }
}
