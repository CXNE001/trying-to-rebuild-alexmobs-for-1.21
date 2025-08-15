/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.LevelRenderer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.BlockAndTintGetter
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelMurmurHead;
import com.github.alexthe666.alexsmobs.client.model.ModelMurmurNeck;
import com.github.alexthe666.alexsmobs.client.render.RenderMurmurBody;
import com.github.alexthe666.alexsmobs.entity.EntityMurmurHead;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class RenderMurmurHead
extends MobRenderer<EntityMurmurHead, ModelMurmurHead> {
    private static final ModelMurmurNeck NECK_MODEL = new ModelMurmurNeck();
    public static final int MAX_NECK_SEGMENTS = 128;

    public RenderMurmurHead(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelMurmurHead(), 0.3f);
    }

    protected void scale(EntityMurmurHead entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(0.85f, 0.85f, 0.85f);
    }

    public boolean shouldRender(EntityMurmurHead livingEntityIn, Frustum camera, double camX, double camY, double camZ) {
        if (super.m_5523_((Mob)livingEntityIn, camera, camX, camY, camZ)) {
            return true;
        }
        if (livingEntityIn.hasNeckBottom()) {
            Vec3 vector3d = livingEntityIn.getNeckBottom(1.0f);
            Vec3 vector3d1 = livingEntityIn.getNeckTop(1.0f);
            return camera.m_113029_(new AABB(vector3d1.f_82479_, vector3d1.f_82480_, vector3d1.f_82481_, vector3d.f_82479_, vector3d.f_82480_, vector3d.f_82481_));
        }
        return false;
    }

    protected float getFlipDegrees(EntityMurmurHead head) {
        return 0.0f;
    }

    public void render(EntityMurmurHead head, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.m_7392_((Mob)head, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.m_85836_();
        if (head.hasNeckBottom()) {
            float headYaw = Mth.m_14189_((float)partialTicks, (float)head.f_20884_, (float)head.f_20883_);
            Vec3 renderingAt = new Vec3(Mth.m_14139_((double)partialTicks, (double)head.f_19854_, (double)head.m_20185_()), Mth.m_14139_((double)partialTicks, (double)head.f_19855_, (double)head.m_20186_()), Mth.m_14139_((double)partialTicks, (double)head.f_19856_, (double)head.m_20189_()));
            Vec3 bottom = head.getNeckBottom(partialTicks).m_82546_(renderingAt);
            Vec3 top = head.getNeckTop(partialTicks).m_82546_(renderingAt);
            Vec3 moveDownFrom = bottom.m_82546_(top);
            Vec3 moveUpTowards = top.m_82546_(bottom);
            RenderType renderType = RenderType.m_110458_((ResourceLocation)this.getTextureLocation(head));
            int overlayCoords = RenderMurmurHead.m_115338_((LivingEntity)head, (float)this.m_6931_((LivingEntity)head, partialTicks));
            matrixStackIn.m_85837_(moveDownFrom.f_82479_, moveDownFrom.f_82480_ - 0.5, moveDownFrom.f_82481_);
            Vec3 currentNeckButt = Vec3.f_82478_;
            for (int segmentCount = 0; segmentCount < 128 && currentNeckButt.m_82554_(moveUpTowards) > 0.2; ++segmentCount) {
                double remainingDistance = Math.min(currentNeckButt.m_82554_(moveUpTowards), 1.0);
                Vec3 linearVec = moveUpTowards.m_82546_(currentNeckButt);
                Vec3 powVec = new Vec3(this.modifyVecAngle(linearVec.f_82479_), this.modifyVecAngle(linearVec.f_82480_), this.modifyVecAngle(linearVec.f_82481_));
                Vec3 smoothedVec = remainingDistance < 1.0 ? linearVec : powVec;
                Vec3 next = smoothedVec.m_82541_().m_82490_(remainingDistance).m_82549_(currentNeckButt);
                int neckLight = this.getLightColor(head, bottom.m_82549_(currentNeckButt).m_82549_(renderingAt));
                RenderMurmurHead.renderNeckCube(currentNeckButt, next, matrixStackIn, bufferIn.m_6299_(renderType), neckLight, overlayCoords, headYaw);
                currentNeckButt = next;
            }
        }
        matrixStackIn.m_85849_();
    }

    private double modifyVecAngle(double dimension) {
        float abs = (float)Math.abs(dimension);
        return Math.signum(dimension) * Mth.m_14008_((double)Math.pow(abs, 0.1), (double)(0.01 * (double)abs), (double)abs);
    }

    public static void renderNeckCube(Vec3 from, Vec3 to, PoseStack poseStack, VertexConsumer buffer, int packedLightIn, int overlayCoords, float additionalYaw) {
        Vec3 sub = from.m_82546_(to);
        double d = sub.m_165924_();
        float rotY = (float)(Mth.m_14136_((double)sub.f_82479_, (double)sub.f_82481_) * 57.2957763671875);
        float rotX = (float)(-(Mth.m_14136_((double)sub.f_82480_, (double)d) * 57.2957763671875)) - 90.0f;
        poseStack.m_85836_();
        poseStack.m_85837_(from.f_82479_, from.f_82480_, from.f_82481_);
        NECK_MODEL.setAttributes((float)sub.m_82553_(), rotX, rotY, additionalYaw);
        NECK_MODEL.m_7695_(poseStack, buffer, packedLightIn, overlayCoords, 1.0f, 1.0f, 1.0f, 1.0f);
        poseStack.m_85849_();
    }

    private int getLightColor(EntityMurmurHead head, Vec3 vec3) {
        BlockPos blockpos = AMBlockPos.fromVec3(vec3);
        if (head.m_9236_().m_46805_(blockpos)) {
            int i = LevelRenderer.m_109541_((BlockAndTintGetter)head.m_9236_(), (BlockPos)blockpos);
            int j = LevelRenderer.m_109541_((BlockAndTintGetter)head.m_9236_(), (BlockPos)blockpos.m_7494_());
            int k = i & 0xFF;
            int l = j & 0xFF;
            int i1 = i >> 16 & 0xFF;
            int j1 = j >> 16 & 0xFF;
            return Math.max(k, l) | Math.max(i1, j1) << 16;
        }
        return 0;
    }

    public ResourceLocation getTextureLocation(EntityMurmurHead entity) {
        return entity.isAngry() ? RenderMurmurBody.TEXTURE_ANGRY : RenderMurmurBody.TEXTURE;
    }
}
