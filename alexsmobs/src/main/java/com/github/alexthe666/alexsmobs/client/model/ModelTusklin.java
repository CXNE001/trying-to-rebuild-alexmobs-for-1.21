/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.ModelAnimator
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityTusklin;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelTusklin
extends AdvancedEntityModel<EntityTusklin> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox leg_left;
    private final AdvancedModelBox leg_right;
    private final AdvancedModelBox torso;
    private final AdvancedModelBox arm_left;
    private final AdvancedModelBox arm_right;
    private final AdvancedModelBox head;
    private final AdvancedModelBox tusk_left;
    private final AdvancedModelBox tusk_right;
    private final AdvancedModelBox ear_left;
    private final AdvancedModelBox earLeft_r1;
    private final AdvancedModelBox ear_right;
    private final AdvancedModelBox earLeft_r2;
    private ModelAnimator animator;

    public ModelTusklin() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -18.0f, 1.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(52, 56).addBox(-8.0f, -7.0f, 0.0f, 16.0f, 15.0f, 15.0f, 0.0f, false);
        this.leg_left = new AdvancedModelBox((AdvancedEntityModel)this, "leg_left");
        this.leg_left.setRotationPoint(6.0f, 8.0f, 13.0f);
        this.body.addChild((BasicModelPart)this.leg_left);
        this.leg_left.setTextureOffset(58, 0).addBox(-3.0f, -1.0f, -3.0f, 6.0f, 11.0f, 7.0f, 0.0f, false);
        this.leg_right = new AdvancedModelBox((AdvancedEntityModel)this, "leg_right");
        this.leg_right.setRotationPoint(-6.0f, 8.0f, 13.0f);
        this.body.addChild((BasicModelPart)this.leg_right);
        this.leg_right.setTextureOffset(58, 0).addBox(-3.0f, -1.0f, -3.0f, 6.0f, 11.0f, 7.0f, 0.0f, true);
        this.torso = new AdvancedModelBox((AdvancedEntityModel)this, "torso");
        this.torso.setRotationPoint(0.0f, 0.0f, -1.0f);
        this.body.addChild((BasicModelPart)this.torso);
        this.torso.setTextureOffset(0, 0).addBox(-9.0f, -11.0f, -20.0f, 18.0f, 20.0f, 21.0f, 0.0f, false);
        this.torso.setTextureOffset(0, 0).addBox(0.0f, -16.0f, -20.0f, 0.0f, 5.0f, 10.0f, 0.0f, false);
        this.arm_left = new AdvancedModelBox((AdvancedEntityModel)this, "arm_left");
        this.arm_left.setRotationPoint(5.5f, 10.0f, -14.0f);
        this.torso.addChild((BasicModelPart)this.arm_left);
        this.arm_left.setTextureOffset(0, 71).addBox(-3.0f, -1.0f, -3.0f, 6.0f, 9.0f, 6.0f, 0.0f, false);
        this.arm_right = new AdvancedModelBox((AdvancedEntityModel)this, "arm_right");
        this.arm_right.setRotationPoint(-5.5f, 10.0f, -14.0f);
        this.torso.addChild((BasicModelPart)this.arm_right);
        this.arm_right.setTextureOffset(0, 71).addBox(-3.0f, -1.0f, -3.0f, 6.0f, 9.0f, 6.0f, 0.0f, true);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -5.0f, -22.0f);
        this.torso.addChild((BasicModelPart)this.head);
        this.setRotationAngle(this.head, 0.5236f, 0.0f, 0.0f);
        this.head.setTextureOffset(0, 42).addBox(-7.0f, -3.0f, -17.0f, 14.0f, 9.0f, 19.0f, 0.0f, false);
        this.head.setTextureOffset(52, 50).addBox(0.0f, 6.0f, -16.0f, 0.0f, 3.0f, 7.0f, 0.0f, false);
        this.head.setTextureOffset(0, 42).addBox(0.0f, -10.0f, -5.0f, 0.0f, 7.0f, 7.0f, 0.0f, false);
        this.tusk_left = new AdvancedModelBox((AdvancedEntityModel)this, "tusk_left");
        this.tusk_left.setRotationPoint(8.0f, 2.0f, -13.5f);
        this.head.addChild((BasicModelPart)this.tusk_left);
        this.tusk_left.setTextureOffset(48, 42).addBox(-1.0f, -11.0f, -1.5f, 2.0f, 11.0f, 3.0f, 0.0f, false);
        this.tusk_left.setTextureOffset(59, 42).addBox(-1.0f, -11.0f, 1.5f, 2.0f, 3.0f, 4.0f, 0.0f, false);
        this.tusk_right = new AdvancedModelBox((AdvancedEntityModel)this, "tusk_right");
        this.tusk_right.setRotationPoint(-8.0f, 2.0f, -13.5f);
        this.head.addChild((BasicModelPart)this.tusk_right);
        this.tusk_right.setTextureOffset(48, 42).addBox(-1.0f, -11.0f, -1.5f, 2.0f, 11.0f, 3.0f, 0.0f, true);
        this.tusk_right.setTextureOffset(59, 42).addBox(-1.0f, -11.0f, 1.5f, 2.0f, 3.0f, 4.0f, 0.0f, true);
        this.ear_left = new AdvancedModelBox((AdvancedEntityModel)this, "ear_left");
        this.ear_left.setRotationPoint(7.0f, 0.0f, -1.0f);
        this.head.addChild((BasicModelPart)this.ear_left);
        this.setRotationAngle(this.ear_left, 0.0f, 0.0f, -0.48f);
        this.earLeft_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "earLeft_r1");
        this.earLeft_r1.setRotationPoint(1.0f, 0.0f, 2.0f);
        this.ear_left.addChild((BasicModelPart)this.earLeft_r1);
        this.setRotationAngle(this.earLeft_r1, -0.3927f, 0.0f, 0.0f);
        this.earLeft_r1.setTextureOffset(68, 46).addBox(-1.0f, -1.0f, -3.0f, 1.0f, 5.0f, 4.0f, 0.0f, false);
        this.ear_right = new AdvancedModelBox((AdvancedEntityModel)this, "ear_right");
        this.ear_right.setRotationPoint(-7.0f, 0.0f, -1.0f);
        this.head.addChild((BasicModelPart)this.ear_right);
        this.setRotationAngle(this.ear_right, 0.0f, 0.0f, 0.48f);
        this.earLeft_r2 = new AdvancedModelBox((AdvancedEntityModel)this, "earLeft_r2");
        this.earLeft_r2.setRotationPoint(-1.0f, 0.0f, 2.0f);
        this.ear_right.addChild((BasicModelPart)this.earLeft_r2);
        this.setRotationAngle(this.earLeft_r2, -0.3927f, 0.0f, 0.0f);
        this.earLeft_r2.setTextureOffset(68, 46).addBox(0.0f, -1.0f, -3.0f, 1.0f, 5.0f, 4.0f, 0.0f, true);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.6f;
            float f1 = 2.2f;
            float f2 = 1.4f;
            this.ear_left.setScale(f1, f1, f1);
            this.ear_right.setScale(f1, f1, f1);
            this.head.setScale(f, f, f);
            this.arm_left.setScale(1.0f, f2, 1.0f);
            this.arm_right.setScale(1.0f, f2, 1.0f);
            this.leg_left.setScale(1.0f, f2, 1.0f);
            this.leg_right.setScale(1.0f, f2, 1.0f);
            this.head.setShouldScaleChildren(true);
            this.tusk_left.showModel = false;
            this.tusk_right.showModel = false;
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.45f, 0.45f, 0.45f);
            matrixStackIn.m_85837_(0.0, 1.6, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
            this.ear_left.setScale(1.0f, 1.0f, 1.0f);
            this.ear_right.setScale(1.0f, 1.0f, 1.0f);
            this.arm_left.setScale(1.0f, 1.0f, 1.0f);
            this.arm_right.setScale(1.0f, 1.0f, 1.0f);
            this.leg_left.setScale(1.0f, 1.0f, 1.0f);
            this.leg_right.setScale(1.0f, 1.0f, 1.0f);
        } else {
            this.tusk_left.showModel = true;
            this.tusk_right.showModel = true;
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.leg_left, (Object)this.leg_right, (Object)this.torso, (Object)this.arm_left, (Object)this.arm_right, (Object)this.head, (Object)this.tusk_left, (Object)this.tusk_right, (Object)this.earLeft_r1, (Object)this.ear_left, (Object[])new AdvancedModelBox[]{this.ear_right, this.earLeft_r2});
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.animator.update(entity);
        this.animator.setAnimation(EntityTusklin.ANIMATION_RUT);
        this.animator.startKeyframe(4);
        this.animator.move(this.head, 0.0f, 4.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.rotate(this.ear_left, 0.0f, 0.0f, Maths.rad(-30.0));
        this.animator.rotate(this.ear_right, 0.0f, 0.0f, Maths.rad(30.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.move(this.head, -1.0f, 3.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(40.0), Maths.rad(30.0), 0.0f);
        this.animator.rotate(this.ear_left, 0.0f, 0.0f, Maths.rad(-30.0));
        this.animator.rotate(this.ear_right, 0.0f, 0.0f, Maths.rad(30.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.move(this.head, 1.0f, 3.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(40.0), Maths.rad(-30.0), 0.0f);
        this.animator.rotate(this.ear_left, 0.0f, 0.0f, Maths.rad(-30.0));
        this.animator.rotate(this.ear_right, 0.0f, 0.0f, Maths.rad(30.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.move(this.head, -1.0f, 3.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(40.0), Maths.rad(30.0), 0.0f);
        this.animator.rotate(this.ear_left, 0.0f, 0.0f, Maths.rad(-30.0));
        this.animator.rotate(this.ear_right, 0.0f, 0.0f, Maths.rad(30.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.move(this.head, 1.0f, 3.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(40.0), Maths.rad(-30.0), 0.0f);
        this.animator.rotate(this.ear_left, 0.0f, 0.0f, Maths.rad(-30.0));
        this.animator.rotate(this.ear_right, 0.0f, 0.0f, Maths.rad(30.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.move(this.head, -1.0f, 3.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(40.0), Maths.rad(30.0), 0.0f);
        this.animator.rotate(this.ear_left, 0.0f, 0.0f, Maths.rad(-30.0));
        this.animator.rotate(this.ear_right, 0.0f, 0.0f, Maths.rad(30.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(4);
        this.animator.setAnimation(EntityTusklin.ANIMATION_GORE_L);
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, 5.0f);
        this.animator.rotate(this.leg_right, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.move(this.head, 0.0f, 2.0f, -2.0f);
        this.animator.rotate(this.head, Maths.rad(40.0), Maths.rad(-45.0), Maths.rad(-90.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, -2.0f);
        this.animator.rotate(this.head, Maths.rad(-20.0), Maths.rad(45.0), Maths.rad(60.0));
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityTusklin.ANIMATION_GORE_R);
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, 5.0f);
        this.animator.rotate(this.leg_right, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.move(this.head, 0.0f, 2.0f, -2.0f);
        this.animator.rotate(this.head, Maths.rad(40.0), Maths.rad(45.0), Maths.rad(90.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, -2.0f);
        this.animator.rotate(this.head, Maths.rad(-20.0), Maths.rad(-45.0), Maths.rad(-60.0));
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityTusklin.ANIMATION_FLING);
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 1.0f, -2.0f);
        this.animator.move(this.arm_left, 0.0f, -1.0f, 0.0f);
        this.animator.move(this.arm_right, 0.0f, -1.0f, 0.0f);
        this.animator.move(this.leg_right, 0.0f, 1.0f, 0.0f);
        this.animator.move(this.leg_left, 0.0f, 1.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-50.0), 0.0f, Maths.rad(20.0));
        this.animator.rotate(this.arm_left, Maths.rad(-50.0), 0.0f, Maths.rad(-20.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.head, Maths.rad(-60.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(2);
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityTusklin.ANIMATION_BUCK);
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, -5.0f, 3.0f);
        this.animator.move(this.arm_left, 0.0f, -1.0f, 0.0f);
        this.animator.move(this.arm_right, 0.0f, -1.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-40.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(30.0), 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.arm_left, Maths.rad(30.0), 0.0f, Maths.rad(10.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, -5.0f, -2.0f);
        this.animator.move(this.arm_left, 0.0f, -1.0f, 0.0f);
        this.animator.move(this.arm_right, 0.0f, -1.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-60.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-20.0), 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.arm_left, Maths.rad(-20.0), 0.0f, Maths.rad(10.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
    }

    public void setupAnim(EntityTusklin entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 1.0f;
        float walkDegree = 0.8f;
        float idleSpeed = 0.125f;
        float idleDegree = 0.5f;
        if (this.f_102610_) {
            this.head.rotationPointY -= 4.0f;
            this.head.rotationPointZ += 2.0f;
        }
        this.walk(this.head, idleSpeed * 0.4f, idleDegree * 0.2f, true, 1.0f, -0.01f, ageInTicks, 1.0f);
        this.flap(this.ear_right, idleSpeed * 0.7f, idleDegree * 0.2f, false, 0.0f, -0.01f, ageInTicks, 1.0f);
        this.flap(this.ear_left, idleSpeed * 0.7f, idleDegree * 0.2f, true, 0.0f, -0.01f, ageInTicks, 1.0f);
        this.walk(this.leg_left, walkSpeed, walkDegree * 1.85f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.leg_right, walkSpeed, walkDegree * 1.85f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.arm_left, walkSpeed, walkDegree * 1.85f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.arm_right, walkSpeed, walkDegree * 1.85f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed, walkDegree * 4.0f, true, limbSwing, limbSwingAmount);
        this.bob(this.head, walkSpeed * 0.6f, walkDegree * 2.0f, true, limbSwing, limbSwingAmount);
        this.bob(this.ear_right, walkSpeed, walkDegree * -0.75f, true, limbSwing, limbSwingAmount);
        this.bob(this.ear_left, walkSpeed, walkDegree * -0.75f, true, limbSwing, limbSwingAmount);
        if (entity.getAnimation() == IAnimatedEntity.NO_ANIMATION) {
            this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.head});
        }
    }

    public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z) {
        advancedModelBox.rotateAngleX = x;
        advancedModelBox.rotateAngleY = y;
        advancedModelBox.rotateAngleZ = z;
    }
}
