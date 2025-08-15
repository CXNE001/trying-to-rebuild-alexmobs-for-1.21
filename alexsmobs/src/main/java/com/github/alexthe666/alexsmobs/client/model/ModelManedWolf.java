/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.util.Mth
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityManedWolf;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class ModelManedWolf
extends AdvancedEntityModel<EntityManedWolf> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox neck;
    private final AdvancedModelBox head_pivot;
    private final AdvancedModelBox head;
    private final AdvancedModelBox left_ear_pivot;
    private final AdvancedModelBox right_ear_pivot;
    private final AdvancedModelBox left_ear;
    private final AdvancedModelBox right_ear;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox left_arm;
    private final AdvancedModelBox right_arm;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox right_leg;

    public ModelManedWolf() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -17.0f, 2.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-3.0f, -4.0f, -10.0f, 6.0f, 8.0f, 17.0f, 0.0f, false);
        this.body.setTextureOffset(0, 26).addBox(-3.5f, -4.4f, -10.1f, 7.0f, 5.0f, 8.0f, 0.0f, false);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setRotationPoint(0.0f, -1.0f, -9.0f);
        this.body.addChild((BasicModelPart)this.neck);
        this.setRotationAngle(this.neck, -0.6545f, 0.0f, 0.0f);
        this.neck.setTextureOffset(30, 0).addBox(-2.5f, -3.0f, -6.0f, 5.0f, 5.0f, 7.0f, 0.0f, false);
        this.neck.setTextureOffset(42, 39).addBox(-1.5f, -6.0f, -5.0f, 3.0f, 3.0f, 6.0f, 0.0f, false);
        this.head_pivot = new AdvancedModelBox((AdvancedEntityModel)this, "head_pivot");
        this.head_pivot.setRotationPoint(0.0f, -0.8f, -6.0f);
        this.neck.addChild((BasicModelPart)this.head_pivot);
        this.setRotationAngle(this.head_pivot, 0.6545f, 0.0f, 0.0f);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head_pivot.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 40).addBox(-3.0f, -2.0f, -4.0f, 6.0f, 5.0f, 5.0f, 0.0f, false);
        this.head.setTextureOffset(44, 22).addBox(-1.5f, 0.0f, -8.0f, 3.0f, 3.0f, 4.0f, 0.0f, false);
        this.left_ear_pivot = new AdvancedModelBox((AdvancedEntityModel)this, "left_ear_pivot");
        this.left_ear_pivot.setRotationPoint(2.0f, -2.0f, -1.6f);
        this.head.addChild((BasicModelPart)this.left_ear_pivot);
        this.right_ear_pivot = new AdvancedModelBox((AdvancedEntityModel)this, "right_ear_pivot");
        this.right_ear_pivot.setRotationPoint(-2.0f, -2.0f, -1.6f);
        this.head.addChild((BasicModelPart)this.right_ear_pivot);
        this.left_ear = new AdvancedModelBox((AdvancedEntityModel)this, "left_ear");
        this.left_ear_pivot.addChild((BasicModelPart)this.left_ear);
        this.setRotationAngle(this.left_ear, -0.0479f, -0.2129f, 0.2233f);
        this.left_ear.setTextureOffset(47, 13).addBox(-1.0f, -5.0f, -0.4f, 3.0f, 6.0f, 1.0f, 0.0f, false);
        this.right_ear = new AdvancedModelBox((AdvancedEntityModel)this, "right_ear");
        this.right_ear_pivot.addChild((BasicModelPart)this.right_ear);
        this.setRotationAngle(this.right_ear, -0.0479f, 0.2129f, -0.2233f);
        this.right_ear.setTextureOffset(47, 13).addBox(-2.0f, -5.0f, -0.4f, 3.0f, 6.0f, 1.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, -2.0f, 7.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(31, 26).addBox(-2.0f, -1.0f, -1.0f, 4.0f, 14.0f, 4.0f, 0.0f, false);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setRotationPoint(1.8f, 5.0f, -8.0f);
        this.body.addChild((BasicModelPart)this.left_arm);
        this.left_arm.setTextureOffset(0, 0).addBox(-1.0f, -1.0f, -1.0f, 2.0f, 13.0f, 2.0f, 0.0f, false);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setRotationPoint(-1.8f, 5.0f, -8.0f);
        this.body.addChild((BasicModelPart)this.right_arm);
        this.right_arm.setTextureOffset(0, 0).addBox(-1.0f, -1.0f, -1.0f, 2.0f, 13.0f, 2.0f, 0.0f, true);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setRotationPoint(2.0f, 3.0f, 5.0f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(23, 42).addBox(-0.8f, -2.0f, -0.9f, 2.0f, 16.0f, 3.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setRotationPoint(-2.0f, 3.0f, 5.0f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(23, 42).addBox(-1.2f, -2.0f, -0.9f, 2.0f, 16.0f, 3.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.head_pivot, (Object)this.head, (Object)this.neck, (Object)this.tail, (Object)this.left_ear, (Object)this.right_ear, (Object)this.left_arm, (Object)this.right_arm, (Object)this.left_leg, (Object)this.right_leg, (Object[])new AdvancedModelBox[]{this.right_ear_pivot, this.left_ear_pivot});
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.35f;
            float feet = 0.8f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            this.left_arm.setScale(1.0f, feet, 1.0f);
            this.right_arm.setScale(1.0f, feet, 1.0f);
            this.left_leg.setScale(1.0f, feet, 1.0f);
            this.right_leg.setScale(1.0f, feet, 1.0f);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.65f, 0.65f, 0.65f);
            matrixStackIn.m_85837_(0.0, 1.0, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
            this.left_arm.setScale(1.0f, 1.0f, 1.0f);
            this.right_arm.setScale(1.0f, 1.0f, 1.0f);
            this.left_leg.setScale(1.0f, 1.0f, 1.0f);
            this.right_leg.setScale(1.0f, 1.0f, 1.0f);
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setupAnim(EntityManedWolf entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float walkSpeed = 0.5f;
        float walkDegree = 0.8f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.2f;
        float shakeSpeed = 0.9f;
        float shakeDegree = 0.4f;
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float runProgress = 10.0f * Math.min(limbSwingAmount, 0.5f);
        float danceProgress = entity.prevDanceProgress + (entity.danceProgress - entity.prevDanceProgress) * partialTick;
        float shakeProgress = entity.prevShakeProgress + (entity.shakeProgress - entity.prevShakeProgress) * partialTick;
        float earPitch = entity.prevEarPitch + (entity.getEarPitch() - entity.prevEarPitch) * partialTick;
        float earYaw = entity.prevEarYaw + (entity.getEarYaw() - entity.prevEarYaw) * partialTick;
        this.left_ear_pivot.rotateAngleX += earPitch * ((float)Math.PI / 180);
        this.left_ear_pivot.rotateAngleY += earYaw * ((float)Math.PI / 180);
        this.right_ear_pivot.rotateAngleX += earPitch * ((float)Math.PI / 180);
        this.right_ear_pivot.rotateAngleY -= earYaw * ((float)Math.PI / 180);
        this.head.rotateAngleY += netHeadYaw * 0.5f * ((float)Math.PI / 180);
        this.progressRotationPrev(this.tail, runProgress, Maths.rad(35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.neck, runProgress, Maths.rad(40.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, runProgress, Maths.rad(-40.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, danceProgress, Maths.rad(-40.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, danceProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_arm, danceProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg, danceProgress, Maths.rad(-30.0), 0.0f, Maths.rad(-15.0), 5.0f);
        this.progressRotationPrev(this.right_leg, danceProgress, Maths.rad(-30.0), 0.0f, Maths.rad(15.0), 5.0f);
        this.progressRotationPrev(this.head, danceProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, danceProgress, Maths.rad(20.0), Maths.rad(20.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.body, danceProgress, 0.0f, 6.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_arm, danceProgress, 0.0f, -2.0f, 1.0f, 5.0f);
        this.progressPositionPrev(this.right_arm, danceProgress, 0.0f, -2.0f, 1.0f, 5.0f);
        this.progressRotationPrev(this.neck, shakeProgress, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, shakeProgress, Maths.rad(-30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, shakeProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.swing(this.body, 0.25f, 0.5f, false, 0.0f, 0.0f, ageInTicks, danceProgress * 0.2f);
        this.walk(this.body, 0.5f, 0.3f, false, 0.0f, 0.0f, ageInTicks, danceProgress * 0.2f);
        this.walk(this.left_leg, 0.5f, -0.3f, false, 0.0f, 0.0f, ageInTicks, danceProgress * 0.2f);
        this.walk(this.right_leg, 0.5f, -0.3f, false, 0.0f, 0.0f, ageInTicks, danceProgress * 0.2f);
        this.walk(this.left_arm, 0.5f, -0.6f, false, 0.0f, 0.0f, ageInTicks, danceProgress * 0.2f);
        this.walk(this.right_arm, 0.5f, -0.6f, false, 0.0f, 0.0f, ageInTicks, danceProgress * 0.2f);
        this.walk(this.neck, 0.5f, 0.3f, false, 0.0f, 0.0f, ageInTicks, danceProgress * 0.2f);
        this.flap(this.neck, 0.25f, 0.9f, false, 0.0f, 0.0f, ageInTicks, danceProgress * 0.2f);
        this.swing(this.head, 0.25f, 0.9f, true, 0.0f, 0.0f, ageInTicks, danceProgress * 0.2f);
        this.flap(this.tail, 0.25f, 0.9f, true, 1.0f, 0.0f, ageInTicks, danceProgress * 0.2f);
        float f1 = 0.2f * danceProgress * (float)(-Math.abs(Math.sin(ageInTicks * 0.5f * 0.5f) * 8.0));
        float f2 = 0.2f * danceProgress * 6.0f * Mth.m_14089_((float)(ageInTicks * 0.5f + 0.0f)) * 0.3f * (danceProgress * 0.2f) + 0.0f * (danceProgress * 0.2f);
        this.body.rotationPointY += f1;
        this.body.rotationPointZ -= f2;
        this.right_leg.rotationPointY += 0.25f * f2;
        this.left_leg.rotationPointY += 0.25f * f2;
        this.right_leg.rotateAngleX = (float)((double)this.right_leg.rotateAngleX - Math.toRadians(f1 * 5.0f));
        this.left_leg.rotateAngleX = (float)((double)this.left_leg.rotateAngleX - Math.toRadians(f1 * 5.0f));
        this.right_arm.rotationPointY += f2;
        this.left_arm.rotationPointY += f2;
        this.right_arm.rotationPointZ -= -f2 + 0.25f * f1;
        this.left_arm.rotationPointZ -= -f2 + 0.25f * f1;
        this.flap(this.tail, idleSpeed, idleDegree * 0.2f, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.head, idleSpeed, idleDegree * 0.2f, true, 2.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.neck, idleSpeed, idleDegree * 0.2f, false, 2.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.right_leg, walkSpeed, walkDegree * 1.1f, true, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.left_leg, walkSpeed, walkDegree * 1.1f, false, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed, walkDegree, true, limbSwing, limbSwingAmount);
        this.walk(this.right_arm, walkSpeed, walkDegree * -1.1f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.right_arm, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.bob(this.left_arm, -walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.walk(this.left_arm, walkSpeed, walkDegree * -1.1f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.neck, walkSpeed, walkDegree * 0.2f, false, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.head, walkSpeed, walkDegree * 0.2f, true, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        if (danceProgress <= 0.0f) {
            this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.neck, this.head});
        }
        this.flap(this.body, shakeSpeed, shakeDegree, false, 0.0f, 0.0f, ageInTicks, shakeProgress * 0.2f);
        this.flap(this.neck, shakeSpeed, shakeDegree, false, 1.0f, 0.0f, ageInTicks, shakeProgress * 0.2f);
        this.flap(this.head, shakeSpeed, shakeDegree, true, 2.0f, 0.0f, ageInTicks, shakeProgress * 0.2f);
        this.flap(this.tail, shakeSpeed, shakeDegree, true, 2.0f, 0.0f, ageInTicks, shakeProgress * 0.2f);
        this.swing(this.body, shakeSpeed, shakeDegree * 0.5f, false, 1.0f, 0.0f, ageInTicks, shakeProgress * 0.2f);
        this.flap(this.left_leg, shakeSpeed, shakeDegree, true, 0.0f, 0.0f, ageInTicks, shakeProgress * 0.2f);
        this.flap(this.right_leg, shakeSpeed, shakeDegree, true, 0.0f, 0.0f, ageInTicks, shakeProgress * 0.2f);
        this.flap(this.left_arm, shakeSpeed, shakeDegree, true, 0.0f, 0.0f, ageInTicks, shakeProgress * 0.2f);
        this.flap(this.right_arm, shakeSpeed, shakeDegree, true, 0.0f, 0.0f, ageInTicks, shakeProgress * 0.2f);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
