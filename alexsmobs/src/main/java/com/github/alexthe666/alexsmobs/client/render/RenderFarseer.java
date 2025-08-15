/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.PoseStack$Pose
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  javax.annotation.Nullable
 *  net.minecraft.Util
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.core.Direction
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.client.event.RenderNameTagEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.Event$Result
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 *  org.joml.Quaternionf
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelFarseer;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.entity.EntityFarseer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import javax.annotation.Nullable;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

public class RenderFarseer
extends MobRenderer<EntityFarseer, ModelFarseer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/farseer/farseer.png");
    private static final ResourceLocation TEXTURE_ANGRY = new ResourceLocation("alexsmobs:textures/entity/farseer/farseer_angry.png");
    private static final ResourceLocation TEXTURE_CLAWS = new ResourceLocation("alexsmobs:textures/entity/farseer/farseer_claws.png");
    private static final ResourceLocation TEXTURE_EYE = new ResourceLocation("alexsmobs:textures/entity/farseer/farseer_eye.png");
    private static final ResourceLocation TEXTURE_SCARS = new ResourceLocation("alexsmobs:textures/entity/farseer/farseer_scars.png");
    private static final ResourceLocation[] PORTAL_TEXTURES = new ResourceLocation[]{new ResourceLocation("alexsmobs:textures/entity/farseer/portal_0.png"), new ResourceLocation("alexsmobs:textures/entity/farseer/portal_1.png"), new ResourceLocation("alexsmobs:textures/entity/farseer/portal_2.png"), new ResourceLocation("alexsmobs:textures/entity/farseer/portal_3.png")};
    private static final float HALF_SQRT_3 = (float)(Math.sqrt(3.0) / 2.0);
    private static final ModelFarseer EYE_MODEL = new ModelFarseer(0.1f);
    private static final ModelFarseer SCARS_MODEL = new ModelFarseer(0.05f);
    private static final ModelFarseer AFTERIMAGE_MODEL = new ModelFarseer(0.05f);

    public RenderFarseer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelFarseer(0.0f), 0.9f);
        this.m_115326_(new LayerOverlay());
    }

    public boolean shouldRender(EntityFarseer livingEntityIn, Frustum camera, double camX, double camY, double camZ) {
        LivingEntity livingentity;
        if (super.m_5523_((Mob)livingEntityIn, camera, camX, camY, camZ)) {
            return true;
        }
        if (livingEntityIn.hasLaser() && (livingentity = livingEntityIn.getLaserTarget()) != null) {
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

    public void render(EntityFarseer entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        Direction direction;
        boolean shouldSit;
        if (MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Pre((LivingEntity)entityIn, (LivingEntityRenderer)this, partialTicks, matrixStackIn, bufferIn, packedLightIn))) {
            return;
        }
        LivingEntity laserTarget = entityIn.getLaserTarget();
        float faceCameraAmount = entityIn.getFacingCameraAmount(partialTicks);
        Quaternionf camera = this.f_114476_.m_253208_();
        matrixStackIn.m_85836_();
        ((ModelFarseer)this.f_115290_).f_102608_ = this.m_115342_((LivingEntity)entityIn, partialTicks);
        ((ModelFarseer)this.f_115290_).f_102609_ = shouldSit = entityIn.m_20159_() && entityIn.m_20202_() != null && entityIn.m_20202_().shouldRiderSit();
        ((ModelFarseer)this.f_115290_).f_102610_ = entityIn.m_6162_();
        float f = Mth.m_14189_((float)partialTicks, (float)entityIn.f_20884_, (float)entityIn.f_20883_);
        float f1 = Mth.m_14189_((float)partialTicks, (float)entityIn.f_20886_, (float)entityIn.f_20885_);
        float f2 = f1 - f;
        if (shouldSit && entityIn.m_20202_() instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)entityIn.m_20202_();
            f = Mth.m_14189_((float)partialTicks, (float)livingentity.f_20884_, (float)livingentity.f_20883_);
            f2 = f1 - f;
            float f3 = Mth.m_14177_((float)f2);
            if (f3 < -85.0f) {
                f3 = -85.0f;
            }
            if (f3 >= 85.0f) {
                f3 = 85.0f;
            }
            f = f1 - f3;
            if (f3 * f3 > 2500.0f) {
                f += f3 * 0.2f;
            }
            f2 = f1 - f;
        }
        float f6 = Mth.m_14179_((float)partialTicks, (float)entityIn.f_19860_, (float)entityIn.m_146909_());
        if (entityIn.m_20089_() == Pose.SLEEPING && (direction = entityIn.m_21259_()) != null) {
            float f4 = entityIn.m_20236_(Pose.STANDING) - 0.1f;
            matrixStackIn.m_85837_((double)((float)(-direction.m_122429_()) * f4), 0.0, (double)((float)(-direction.m_122431_()) * f4));
        }
        float f7 = this.m_6930_((LivingEntity)entityIn, partialTicks);
        if (faceCameraAmount != 0.0f) {
            matrixStackIn.m_252781_(camera);
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f));
        }
        this.setupRotations(entityIn, matrixStackIn, f7, f, partialTicks);
        matrixStackIn.m_85841_(-1.0f, -1.0f, 1.0f);
        this.m_7546_((LivingEntity)entityIn, matrixStackIn, partialTicks);
        matrixStackIn.m_85837_(0.0, (double)-1.501f, 0.0);
        float f8 = 0.0f;
        float f5 = 0.0f;
        if (!shouldSit && entityIn.m_6084_()) {
            f8 = entityIn.f_267362_.m_267590_(partialTicks);
            f5 = entityIn.f_267362_.m_267756_() - entityIn.f_267362_.m_267731_() * (1.0f - partialTicks);
            if (entityIn.m_6162_()) {
                f5 *= 3.0f;
            }
            if (f8 > 1.0f) {
                f8 = 1.0f;
            }
        }
        ((ModelFarseer)this.f_115290_).m_6839_((Entity)entityIn, f5, f8, partialTicks);
        ((ModelFarseer)this.f_115290_).setupAnim(entityIn, f5, f8, f7, f2, f6);
        Minecraft minecraft = Minecraft.m_91087_();
        boolean flag = this.m_5933_((LivingEntity)entityIn);
        boolean flag1 = !flag && !entityIn.m_20177_((Player)minecraft.f_91074_);
        boolean flag2 = minecraft.m_91314_((Entity)entityIn);
        RenderType rendertype = this.getRenderType(entityIn, flag, flag1, flag2);
        EYE_MODEL.setupAnim(entityIn, f5, f8, f7, f2, f6);
        SCARS_MODEL.setupAnim(entityIn, f5, f8, f7, f2, f6);
        AFTERIMAGE_MODEL.setupAnim(entityIn, f5, f8, f7, f2, f6);
        if (rendertype != null) {
            float portalLevel = entityIn.getFarseerOpacity(partialTicks);
            this.f_114477_ = 0.9f * portalLevel;
            int i = RenderFarseer.m_115338_((LivingEntity)entityIn, (float)this.m_6931_((LivingEntity)entityIn, partialTicks));
            this.renderFarseerModel(matrixStackIn, bufferIn, rendertype, partialTicks, packedLightIn, i, flag1 ? 0.15f : Mth.m_14036_((float)portalLevel, (float)0.0f, (float)1.0f), entityIn);
        }
        if (!entityIn.m_5833_()) {
            for (RenderLayer layerrenderer : this.f_115291_) {
                layerrenderer.m_6494_(matrixStackIn, bufferIn, packedLightIn, (Entity)entityIn, f5, f8, partialTicks, f7, f2, f6);
            }
        }
        matrixStackIn.m_85849_();
        RenderNameTagEvent renderNameplateEvent = new RenderNameTagEvent((Entity)entityIn, entityIn.m_5446_(), (EntityRenderer)this, matrixStackIn, bufferIn, packedLightIn, partialTicks);
        MinecraftForge.EVENT_BUS.post((Event)renderNameplateEvent);
        if (renderNameplateEvent.getResult() != Event.Result.DENY && (renderNameplateEvent.getResult() == Event.Result.ALLOW || this.m_6512_((Mob)entityIn))) {
            this.m_7649_((Entity)entityIn, renderNameplateEvent.getContent(), matrixStackIn, bufferIn, packedLightIn);
        }
        MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Post((LivingEntity)entityIn, (LivingEntityRenderer)this, partialTicks, matrixStackIn, bufferIn, packedLightIn));
        if (entityIn.getAnimation() == EntityFarseer.ANIMATION_EMERGE) {
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(3.0f, 3.0f, 3.0f);
            matrixStackIn.m_252781_(camera);
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f));
            PoseStack.Pose posestack$pose = matrixStackIn.m_85850_();
            Matrix4f matrix4f = posestack$pose.m_252922_();
            Matrix3f matrix3f = posestack$pose.m_252943_();
            int portalTexture = Mth.m_14045_((int)entityIn.getPortalFrame(), (int)0, (int)(PORTAL_TEXTURES.length - 1));
            VertexConsumer portalStatic = AMRenderTypes.createMergedVertexConsumer(bufferIn.m_6299_(AMRenderTypes.STATIC_PORTAL), bufferIn.m_6299_(RenderType.m_110473_((ResourceLocation)PORTAL_TEXTURES[portalTexture])));
            float portalAlpha = entityIn.getPortalOpacity(partialTicks);
            RenderFarseer.portalVertex(portalStatic, matrix4f, matrix3f, packedLightIn, 0.0f, 0, 0, 1, portalAlpha);
            RenderFarseer.portalVertex(portalStatic, matrix4f, matrix3f, packedLightIn, 1.0f, 0, 1, 1, portalAlpha);
            RenderFarseer.portalVertex(portalStatic, matrix4f, matrix3f, packedLightIn, 1.0f, 1, 1, 0, portalAlpha);
            RenderFarseer.portalVertex(portalStatic, matrix4f, matrix3f, packedLightIn, 0.0f, 1, 0, 0, portalAlpha);
            matrixStackIn.m_85849_();
        }
        if (entityIn.hasLaser() && laserTarget != null && !laserTarget.m_213877_()) {
            float laserProgress = (entityIn.prevLaserLvl + ((float)entityIn.getLaserAttackLvl() - entityIn.prevLaserLvl) * partialTicks) / 10.0f;
            float laserHeight = entityIn.m_20192_();
            Vec3 angryShake = Vec3.f_82478_;
            double d0 = Mth.m_14139_((double)partialTicks, (double)laserTarget.f_19854_, (double)laserTarget.m_20185_()) - Mth.m_14139_((double)partialTicks, (double)entityIn.f_19854_, (double)entityIn.m_20185_()) - angryShake.f_82479_;
            double d1 = Mth.m_14139_((double)partialTicks, (double)laserTarget.f_19855_, (double)laserTarget.m_20186_()) + (double)laserTarget.m_20192_() - Mth.m_14139_((double)partialTicks, (double)entityIn.f_19855_, (double)entityIn.m_20186_()) - angryShake.f_82480_ - (double)laserHeight;
            double d2 = Mth.m_14139_((double)partialTicks, (double)laserTarget.f_19856_, (double)laserTarget.m_20189_()) - Mth.m_14139_((double)partialTicks, (double)entityIn.f_19856_, (double)entityIn.m_20189_()) - angryShake.f_82481_;
            double d4 = Math.sqrt(d0 * d0 + d2 * d2);
            float laserY = (float)(Mth.m_14136_((double)d2, (double)d0) * 57.2957763671875) - 90.0f;
            float laserX = (float)(-(Mth.m_14136_((double)d1, (double)d4) * 57.2957763671875));
            VertexConsumer beamStatic = bufferIn.m_6299_(AMRenderTypes.getFarseerBeam());
            matrixStackIn.m_85836_();
            matrixStackIn.m_252880_(0.0f, laserHeight, 0.0f);
            matrixStackIn.m_252781_(Axis.f_252392_.m_252977_(laserY));
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(laserX));
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(90.0f));
            float length = entityIn.getLaserDistance() * laserProgress;
            float width = (1.5f - laserProgress) * 2.0f;
            float speed = 1.0f + laserProgress * laserProgress * 5.0f;
            PoseStack.Pose posestack$pose = matrixStackIn.m_85850_();
            Matrix4f matrix4f = posestack$pose.m_252922_();
            Matrix3f matrix3f = posestack$pose.m_252943_();
            int j = 255;
            long systemTime = Util.m_137550_() * 7L;
            float u = (float)(systemTime % 30000L) / 30000.0f;
            float v = (float)Math.floor((float)(systemTime % 3000L) / 3000.0f * 4.0f) * 0.25f + (float)Math.sin((float)systemTime / 30000.0f) * 0.05f + (float)(systemTime % 20000L) / 20000.0f * speed;
            RenderFarseer.laserOriginVertex(beamStatic, matrix4f, matrix3f, j, u, v);
            RenderFarseer.laserLeftCornerVertex(beamStatic, matrix4f, matrix3f, length, width, u, v);
            RenderFarseer.laserRightCornerVertex(beamStatic, matrix4f, matrix3f, length, width, u, v);
            RenderFarseer.laserLeftCornerVertex(beamStatic, matrix4f, matrix3f, length, width, u, v);
            matrixStackIn.m_85849_();
        }
    }

    private void renderFarseerModel(PoseStack matrixStackIn, MultiBufferSource source, RenderType defRenderType, float partialTicks, int packedLightIn, int overlayColors, float alphaIn, EntityFarseer entityIn) {
        if (entityIn.hasLaser()) {
            VertexConsumer staticyInsides = AMRenderTypes.createMergedVertexConsumer(source.m_6299_(AMRenderTypes.STATIC_ENTITY), source.m_6299_(RenderType.m_110473_((ResourceLocation)TEXTURE_EYE)));
            EYE_MODEL.m_7695_(matrixStackIn, staticyInsides, packedLightIn, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        }
        float hurt = Math.max(entityIn.f_20916_, entityIn.f_20919_);
        float defAlpha = alphaIn * 0.2f;
        float afterimageSpeed = 0.3f;
        if (hurt > 0.0f) {
            afterimageSpeed = Math.min(hurt / 20.0f, 1.0f) + 0.3f;
            VertexConsumer staticyScars = AMRenderTypes.createMergedVertexConsumer(source.m_6299_(AMRenderTypes.STATIC_ENTITY), source.m_6299_(RenderType.m_110473_((ResourceLocation)TEXTURE_SCARS)));
            SCARS_MODEL.m_7695_(matrixStackIn, staticyScars, packedLightIn, overlayColors, 1.0f, 1.0f, 1.0f, 0.3f);
        }
        ((ModelFarseer)this.f_115290_).m_7695_(matrixStackIn, source.m_6299_(defRenderType), packedLightIn, overlayColors, 1.0f, 1.0f, 1.0f, alphaIn);
        matrixStackIn.m_85836_();
        matrixStackIn.m_85849_();
        RenderFarseer.AFTERIMAGE_MODEL.eye.showModel = false;
        RenderType afterimage = RenderType.m_234338_((ResourceLocation)this.getTextureLocation(entityIn));
        Vec3 colorOffset = entityIn.getLatencyOffsetVec(10, partialTicks).m_82490_((double)-0.2f).m_82549_(entityIn.angryShakeVec.m_82490_((double)0.3f));
        Vec3 redOffset = colorOffset.m_82549_(entityIn.calculateAfterimagePos(partialTicks, false, afterimageSpeed));
        Vec3 blueOffset = colorOffset.m_82549_(entityIn.calculateAfterimagePos(partialTicks, true, afterimageSpeed));
        float scale = (float)Mth.m_14008_((double)(colorOffset.m_82553_() * (double)0.1f), (double)0.0, (double)1.0);
        float angryProgress = entityIn.prevAngryProgress + (entityIn.angryProgress - entityIn.prevAngryProgress) * partialTicks;
        float afterimageAlpha1 = defAlpha * Math.max(((float)Math.sin(((float)entityIn.f_19797_ + partialTicks) * 0.2f) + 1.0f) * 0.3f, angryProgress * 0.2f);
        float afterimageAlpha2 = defAlpha * Math.max(((float)Math.cos(((float)entityIn.f_19797_ + partialTicks) * 0.2f) + 1.0f) * 0.3f, angryProgress * 0.2f);
        matrixStackIn.m_85836_();
        matrixStackIn.m_85841_(scale + 1.0f, scale + 1.0f, scale + 1.0f);
        matrixStackIn.m_85836_();
        matrixStackIn.m_85837_(redOffset.f_82479_, redOffset.f_82480_, redOffset.f_82481_);
        AFTERIMAGE_MODEL.m_7695_(matrixStackIn, source.m_6299_(afterimage), 240, overlayColors, 1.0f, 0.0f, 0.0f, afterimageAlpha1);
        matrixStackIn.m_85849_();
        matrixStackIn.m_85836_();
        matrixStackIn.m_85837_(blueOffset.f_82479_, blueOffset.f_82480_, blueOffset.f_82481_);
        AFTERIMAGE_MODEL.m_7695_(matrixStackIn, source.m_6299_(afterimage), 240, overlayColors, 0.0f, 0.0f, 1.0f, afterimageAlpha2);
        matrixStackIn.m_85849_();
        matrixStackIn.m_85849_();
        RenderFarseer.AFTERIMAGE_MODEL.eye.showModel = true;
    }

    private static void laserOriginVertex(VertexConsumer p_114220_, Matrix4f p_114221_, Matrix3f p_114092_, int p_114222_, float xOffset, float yOffset) {
        p_114220_.m_252986_(p_114221_, 0.0f, 0.0f, 0.0f).m_6122_(255, 255, 255, 255).m_7421_(xOffset + 0.5f, yOffset).m_86008_(OverlayTexture.f_118083_).m_85969_(240).m_252939_(p_114092_, 0.0f, 1.0f, 0.0f).m_5752_();
    }

    private static void laserLeftCornerVertex(VertexConsumer p_114215_, Matrix4f p_114216_, Matrix3f p_114092_, float p_114217_, float p_114218_, float xOffset, float yOffset) {
        p_114215_.m_252986_(p_114216_, -HALF_SQRT_3 * p_114218_, p_114217_, 0.0f).m_6122_(255, 255, 255, 0).m_7421_(xOffset, yOffset + 1.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(240).m_252939_(p_114092_, 0.0f, -1.0f, 0.0f).m_5752_();
    }

    private static void laserRightCornerVertex(VertexConsumer p_114224_, Matrix4f p_114225_, Matrix3f p_114092_, float p_114226_, float p_114227_, float xOffset, float yOffset) {
        p_114224_.m_252986_(p_114225_, HALF_SQRT_3 * p_114227_, p_114226_, 0.0f).m_6122_(255, 255, 255, 0).m_7421_(xOffset + 1.0f, yOffset + 1.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(240).m_252939_(p_114092_, 0.0f, -1.0f, 0.0f).m_5752_();
    }

    private static void portalVertex(VertexConsumer p_114090_, Matrix4f p_114091_, Matrix3f p_114092_, int p_114093_, float p_114094_, int p_114095_, int p_114096_, int p_114097_, float alpha) {
        p_114090_.m_252986_(p_114091_, p_114094_ - 0.5f, (float)p_114095_ - 0.25f, 0.0f).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_((float)p_114096_, (float)p_114097_).m_86008_(OverlayTexture.f_118083_).m_85969_(240).m_252939_(p_114092_, 0.0f, -1.0f, 0.0f).m_5752_();
    }

    protected void setupRotations(EntityFarseer farseer, PoseStack matrixStackIn, float f1, float f2, float f3) {
        float invCameraAmount = 1.0f - farseer.getFacingCameraAmount(Minecraft.m_91087_().m_91296_());
        if (this.m_5936_((LivingEntity)farseer)) {
            f2 += (float)(Math.cos((double)farseer.f_19797_ * 3.25) * Math.PI * (double)0.4f);
        }
        if (!farseer.m_217003_(Pose.SLEEPING)) {
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(180.0f - f2 * invCameraAmount));
        }
        if (farseer.f_20919_ > 0) {
            float f = ((float)farseer.f_20919_ + f3 - 1.0f) / 20.0f * 1.6f;
            if ((f = Mth.m_14116_((float)f)) > 1.0f) {
                f = 1.0f;
            }
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(f * this.m_6441_((LivingEntity)farseer) * invCameraAmount));
        } else if (RenderFarseer.m_194453_((LivingEntity)farseer)) {
            matrixStackIn.m_85837_(0.0, (double)(farseer.m_20206_() + 0.1f), 0.0);
            matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(180.0f));
        }
    }

    @Nullable
    protected RenderType getRenderType(EntityFarseer farseer, boolean normal, boolean invis, boolean outline) {
        ResourceLocation resourcelocation = this.getTextureLocation(farseer);
        if (invis || farseer.getAnimation() == EntityFarseer.ANIMATION_EMERGE) {
            return RenderType.m_110467_((ResourceLocation)resourcelocation);
        }
        if (normal) {
            return ((ModelFarseer)this.f_115290_).m_103119_(resourcelocation);
        }
        return outline ? RenderType.m_110491_((ResourceLocation)resourcelocation) : null;
    }

    public ResourceLocation getTextureLocation(EntityFarseer entity) {
        return entity.isAngry() ? TEXTURE_ANGRY : TEXTURE;
    }

    class LayerOverlay
    extends RenderLayer<EntityFarseer, ModelFarseer> {
        public LayerOverlay() {
            super((RenderLayerParent)RenderFarseer.this);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityFarseer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            if (entitylivingbaseIn.getAnimation() == EntityFarseer.ANIMATION_EMERGE) {
                VertexConsumer ivertexbuilder = bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)TEXTURE_CLAWS));
                ((ModelFarseer)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
            }
        }
    }
}
