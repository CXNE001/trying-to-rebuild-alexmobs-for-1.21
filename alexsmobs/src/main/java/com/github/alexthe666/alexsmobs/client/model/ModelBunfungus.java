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
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityBunfungus;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelBunfungus
extends AdvancedEntityModel<EntityBunfungus> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox belly;
    public final AdvancedModelBox tail;
    public final AdvancedModelBox head;
    public final AdvancedModelBox left_brow;
    public final AdvancedModelBox right_brow;
    public final AdvancedModelBox shroom_cap;
    public final AdvancedModelBox left_ear;
    public final AdvancedModelBox right_ear;
    public final AdvancedModelBox snout;
    public final AdvancedModelBox snout_r1;
    public final AdvancedModelBox left_arm;
    public final AdvancedModelBox right_arm;
    public final AdvancedModelBox left_leg;
    public final AdvancedModelBox left_foot;
    public final AdvancedModelBox right_leg;
    public final AdvancedModelBox right_foot;
    private final ModelAnimator animator;

    public ModelBunfungus() {
        this.texWidth = 256;
        this.texHeight = 256;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -13.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-10.0f, -10.0f, -10.0f, 20.0f, 20.0f, 19.0f, 0.0f, false);
        this.belly = new AdvancedModelBox((AdvancedEntityModel)this, "belly");
        this.belly.setRotationPoint(0.0f, 4.0f, -4.3f);
        this.body.addChild((BasicModelPart)this.belly);
        this.belly.setTextureOffset(64, 25).addBox(-11.0f, -7.0f, -7.5f, 22.0f, 14.0f, 15.0f, -2.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, 10.0f, 9.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(60, 0).addBox(-3.0f, -5.0f, -1.0f, 6.0f, 6.0f, 6.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -10.0f, -6.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 66).addBox(-6.0f, -5.0f, -9.0f, 12.0f, 8.0f, 13.0f, 0.0f, false);
        this.left_brow = new AdvancedModelBox((AdvancedEntityModel)this, "left_brow");
        this.left_brow.setRotationPoint(3.5f, -3.5f, -9.1f);
        this.head.addChild((BasicModelPart)this.left_brow);
        this.left_brow.setTextureOffset(90, 2).addBox(-2.5f, -0.5f, 0.0f, 5.0f, 1.0f, 0.0f, 0.0f, false);
        this.right_brow = new AdvancedModelBox((AdvancedEntityModel)this, "right_brow");
        this.right_brow.setRotationPoint(-3.5f, -3.5f, -9.1f);
        this.head.addChild((BasicModelPart)this.right_brow);
        this.right_brow.setTextureOffset(90, 2).addBox(-2.5f, -0.5f, 0.0f, 5.0f, 1.0f, 0.0f, 0.0f, true);
        this.shroom_cap = new AdvancedModelBox((AdvancedEntityModel)this, "shroom_cap");
        this.shroom_cap.setRotationPoint(0.0f, -5.0f, -4.0f);
        this.head.addChild((BasicModelPart)this.shroom_cap);
        this.shroom_cap.setTextureOffset(0, 40).addBox(-10.0f, -5.0f, -8.0f, 20.0f, 5.0f, 20.0f, 0.0f, false);
        this.left_ear = new AdvancedModelBox((AdvancedEntityModel)this, "left_ear");
        this.left_ear.setRotationPoint(3.0f, -4.0f, 1.0f);
        this.shroom_cap.addChild((BasicModelPart)this.left_ear);
        this.setRotationAngle(this.left_ear, 0.0f, -0.6981f, 0.2182f);
        this.left_ear.setTextureOffset(0, 0).addBox(-2.0f, -12.0f, -1.0f, 4.0f, 12.0f, 2.0f, 0.0f, false);
        this.right_ear = new AdvancedModelBox((AdvancedEntityModel)this, "right_ear");
        this.right_ear.setRotationPoint(-3.0f, -4.0f, 1.0f);
        this.shroom_cap.addChild((BasicModelPart)this.right_ear);
        this.setRotationAngle(this.right_ear, 0.0f, 0.6981f, -0.2182f);
        this.right_ear.setTextureOffset(0, 0).addBox(-2.0f, -12.0f, -1.0f, 4.0f, 12.0f, 2.0f, 0.0f, true);
        this.snout = new AdvancedModelBox((AdvancedEntityModel)this, "snout");
        this.snout.setRotationPoint(0.0f, 0.0f, -10.0f);
        this.head.addChild((BasicModelPart)this.snout);
        this.snout.setTextureOffset(0, 40).addBox(-3.0f, -1.0f, -1.0f, 6.0f, 4.0f, 2.0f, 0.0f, false);
        this.snout_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "snout_r1");
        this.snout_r1.setRotationPoint(0.0f, 0.0f, -1.0f);
        this.snout.addChild((BasicModelPart)this.snout_r1);
        this.setRotationAngle(this.snout_r1, -0.1309f, 0.0f, 0.0f);
        this.snout_r1.setTextureOffset(0, 48).addBox(-4.0f, 0.0f, 0.0f, 8.0f, 2.0f, 0.0f, 0.0f, false);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setRotationPoint(9.5f, -4.0f, -9.5f);
        this.body.addChild((BasicModelPart)this.left_arm);
        this.setRotationAngle(this.left_arm, 0.0f, 0.0f, 0.1745f);
        this.left_arm.setTextureOffset(51, 77).addBox(-2.5f, -1.0f, -2.5f, 5.0f, 8.0f, 5.0f, 0.0f, false);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setRotationPoint(-9.5f, -4.0f, -9.5f);
        this.body.addChild((BasicModelPart)this.right_arm);
        this.setRotationAngle(this.right_arm, 0.0f, 0.0f, -0.1745f);
        this.right_arm.setTextureOffset(51, 77).addBox(-2.5f, -1.0f, -2.5f, 5.0f, 8.0f, 5.0f, 0.0f, true);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setRotationPoint(6.0f, 2.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_foot = new AdvancedModelBox((AdvancedEntityModel)this, "left_foot");
        this.left_foot.setRotationPoint(0.0f, 8.0f, 0.0f);
        this.left_leg.addChild((BasicModelPart)this.left_foot);
        this.setRotationAngle(this.left_foot, 0.0f, -1.1345f, 0.0f);
        this.left_foot.setTextureOffset(64, 55).addBox(-3.0f, -1.0f, -15.0f, 6.0f, 4.0f, 17.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setRotationPoint(-6.0f, 2.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_foot = new AdvancedModelBox((AdvancedEntityModel)this, "right_foot");
        this.right_foot.setRotationPoint(0.0f, 8.0f, 0.0f);
        this.right_leg.addChild((BasicModelPart)this.right_foot);
        this.setRotationAngle(this.right_foot, 0.0f, 1.1345f, 0.0f);
        this.right_foot.setTextureOffset(64, 55).addBox(-3.0f, -1.0f, -15.0f, 6.0f, 4.0f, 17.0f, 0.0f, true);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.animator.update(entity);
        this.animator.setAnimation(EntityBunfungus.ANIMATION_EAT);
        this.animator.startKeyframe(4);
        this.animator.rotate(this.head, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-140.0), Maths.rad(-20.0), Maths.rad(70.0));
        this.animator.rotate(this.left_arm, Maths.rad(-140.0), Maths.rad(20.0), Maths.rad(-70.0));
        this.animator.move(this.head, 0.0f, -2.0f, -1.0f);
        this.animator.move(this.right_arm, 1.0f, 2.0f, 0.0f);
        this.animator.move(this.left_arm, -1.0f, 2.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.head, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-140.0), Maths.rad(-10.0), Maths.rad(70.0));
        this.animator.rotate(this.left_arm, Maths.rad(-140.0), Maths.rad(10.0), Maths.rad(-70.0));
        this.animator.move(this.head, 0.0f, -2.0f, -1.0f);
        this.animator.move(this.right_arm, 1.0f, 2.0f, 0.0f);
        this.animator.move(this.left_arm, -1.0f, 2.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.head, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-140.0), Maths.rad(-20.0), Maths.rad(70.0));
        this.animator.rotate(this.left_arm, Maths.rad(-140.0), Maths.rad(20.0), Maths.rad(-70.0));
        this.animator.move(this.head, 0.0f, -2.0f, -1.0f);
        this.animator.move(this.right_arm, 1.0f, 2.0f, 0.0f);
        this.animator.move(this.left_arm, -1.0f, 2.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.head, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-140.0), Maths.rad(-10.0), Maths.rad(70.0));
        this.animator.rotate(this.left_arm, Maths.rad(-140.0), Maths.rad(10.0), Maths.rad(-70.0));
        this.animator.move(this.head, 0.0f, -2.0f, -1.0f);
        this.animator.move(this.right_arm, 1.0f, 2.0f, 0.0f);
        this.animator.move(this.left_arm, -1.0f, 2.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(4);
        this.animator.endKeyframe();
        this.animator.setAnimation(EntityBunfungus.ANIMATION_BELLY);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-120.0), Maths.rad(30.0), Maths.rad(-10.0));
        this.animator.rotate(this.left_arm, Maths.rad(-120.0), Maths.rad(-30.0), Maths.rad(10.0));
        this.animator.move(this.belly, 0.0f, 0.0f, -10.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(2);
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityBunfungus.ANIMATION_SLAM);
        this.animator.startKeyframe(5);
        this.animator.move(this.root, 0.0f, 0.0f, -20.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.resetKeyframe(5);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.head, (Object)this.shroom_cap, (Object)this.left_ear, (Object)this.right_ear, (Object)this.left_brow, (Object)this.right_brow, (Object)this.snout, (Object)this.snout_r1, (Object)this.left_arm, (Object)this.right_arm, (Object[])new AdvancedModelBox[]{this.left_leg, this.right_leg, this.tail, this.belly, this.left_foot, this.right_foot});
    }

    public void setupAnim(EntityBunfungus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        float walkSpeed = 0.7f;
        float walkDegree = 2.0f;
        float partialTicks = ageInTicks - (float)entity.f_19797_;
        float sleepProgress = entity.prevSleepProgress + (entity.sleepProgress - entity.prevSleepProgress) * partialTicks;
        float fallProgress = entity.prevReboundProgress + (entity.reboundProgress - entity.prevReboundProgress) * partialTicks;
        float jumpProgress = Math.max(0.0f, entity.prevJumpProgress + (entity.jumpProgress - entity.prevJumpProgress) * partialTicks - fallProgress);
        float interestedProgress = entity.prevInterestedProgress + (entity.interestedProgress - entity.prevInterestedProgress) * partialTicks;
        float walkMod = 1.0f - Math.max(jumpProgress, fallProgress) * 0.2f;
        float limbSwingMod = Math.min(limbSwingAmount, 0.38f) * walkMod;
        this.progressRotationPrev(this.body, sleepProgress, Maths.rad(90.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, sleepProgress, Maths.rad(-70.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_ear, sleepProgress, Maths.rad(50.0), Maths.rad(80.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.left_ear, sleepProgress, Maths.rad(50.0), Maths.rad(-80.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.head, sleepProgress, Maths.rad(-95.0), 0.0f, Maths.rad(-5.0), 5.0f);
        this.progressRotationPrev(this.left_arm, sleepProgress, Maths.rad(-170.0), Maths.rad(-10.0), Maths.rad(35.0), 5.0f);
        this.progressRotationPrev(this.right_arm, sleepProgress, Maths.rad(-170.0), Maths.rad(10.0), Maths.rad(-35.0), 5.0f);
        this.progressRotationPrev(this.left_foot, sleepProgress, Maths.rad(70.0), Maths.rad(-30.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.right_foot, sleepProgress, Maths.rad(70.0), Maths.rad(30.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.body, sleepProgress, 0.0f, 3.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_leg, sleepProgress, 0.0f, 0.0f, -8.0f, 5.0f);
        this.progressPositionPrev(this.right_leg, sleepProgress, 0.0f, 0.0f, -8.0f, 5.0f);
        this.progressPositionPrev(this.left_arm, sleepProgress, 0.0f, -3.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.right_arm, sleepProgress, 0.0f, -3.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.head, sleepProgress, 0.0f, -3.0f, -1.0f, 5.0f);
        this.progressRotationPrev(this.left_foot, limbSwingMod, 0.0f, Maths.rad(40.0), 0.0f, 0.38f);
        this.progressRotationPrev(this.right_foot, limbSwingMod, 0.0f, Maths.rad(-40.0), 0.0f, 0.38f);
        this.progressRotationPrev(this.left_ear, limbSwingMod, Maths.rad(-30.0), Maths.rad(30.0), 0.0f, 0.38f);
        this.progressRotationPrev(this.right_ear, limbSwingMod, Maths.rad(-30.0), Maths.rad(-30.0), 0.0f, 0.38f);
        this.progressRotationPrev(this.body, jumpProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_foot, jumpProgress, Maths.rad(70.0), Maths.rad(40.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.right_foot, jumpProgress, Maths.rad(70.0), Maths.rad(-40.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.right_arm, jumpProgress, Maths.rad(-70.0), Maths.rad(40.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, jumpProgress, Maths.rad(-70.0), Maths.rad(-40.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.body, jumpProgress, 0.0f, -3.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, jumpProgress, 0.0f, -1.0f, 3.0f, 5.0f);
        this.progressRotationPrev(this.body, fallProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_foot, fallProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_foot, fallProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, fallProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, fallProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_arm, fallProgress, Maths.rad(-130.0), Maths.rad(20.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, fallProgress, Maths.rad(-130.0), Maths.rad(-20.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.body, fallProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_foot, fallProgress, 0.0f, 1.0f, -1.0f, 5.0f);
        this.progressPositionPrev(this.right_foot, fallProgress, 0.0f, 1.0f, -1.0f, 5.0f);
        this.progressRotationPrev(this.head, interestedProgress, 0.0f, Maths.rad(-20.0), Maths.rad(-10.0), 5.0f);
        this.progressRotationPrev(this.right_brow, interestedProgress, 0.0f, 0.0f, Maths.rad(10.0), 5.0f);
        this.progressPositionPrev(this.right_brow, interestedProgress, -0.5f, -0.75f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_brow, interestedProgress, 0.0f, 0.5f, 0.0f, 5.0f);
        if (sleepProgress == 0.0f) {
            this.faceTarget(netHeadYaw, headPitch, 1.3f, new AdvancedModelBox[]{this.head});
        }
        this.flap(this.left_ear, idleSpeed, idleDegree, false, 1.0f, 0.2f, ageInTicks, 1.0f);
        this.flap(this.right_ear, idleSpeed, idleDegree, true, 1.0f, 0.2f, ageInTicks, 1.0f);
        this.swing(this.left_ear, idleSpeed, idleDegree, false, 2.0f, 0.2f, ageInTicks, 1.0f);
        this.swing(this.right_ear, idleSpeed, idleDegree, true, 2.0f, 0.2f, ageInTicks, 1.0f);
        this.walk(this.tail, idleSpeed, idleDegree, false, 2.0f, 0.2f, ageInTicks, 1.0f);
        this.walk(this.right_arm, idleSpeed, idleDegree, false, -2.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.left_arm, idleSpeed, idleDegree, false, -2.0f, -0.1f, ageInTicks, 1.0f);
        this.flap(this.snout_r1, idleSpeed * 8.0f, idleDegree, false, -2.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.body, walkSpeed, walkDegree * 0.5f, false, 0.0f, 0.0f, limbSwing, limbSwingMod);
        this.swing(this.body, walkSpeed, walkDegree * 0.5f, false, 1.0f, 0.0f, limbSwing, limbSwingMod);
        this.swing(this.right_foot, walkSpeed, walkDegree * 0.5f, false, -2.5f, 0.0f, limbSwing, limbSwingMod);
        this.swing(this.left_foot, walkSpeed, walkDegree * 0.5f, false, -2.5f, 0.0f, limbSwing, limbSwingMod);
        this.left_foot.rotateAngleX -= this.left_leg.rotateAngleX + this.body.rotateAngleX;
        this.left_foot.rotateAngleZ -= this.body.rotateAngleZ;
        this.right_foot.rotateAngleX -= this.right_leg.rotateAngleX + this.body.rotateAngleX;
        this.right_foot.rotateAngleZ -= this.body.rotateAngleZ;
        this.left_leg.rotationPointY += 2.0f * (float)(Math.sin((double)(limbSwing * walkSpeed) + 2.5) * (double)limbSwingMod * (double)walkDegree - (double)(limbSwingMod * walkDegree));
        this.right_leg.rotationPointY += 2.0f * (float)(Math.sin(-((double)(limbSwing * walkSpeed)) + 2.5) * (double)limbSwingMod * (double)walkDegree - (double)(limbSwingMod * walkDegree));
        this.flap(this.head, walkSpeed, walkDegree * 0.5f, true, 0.0f, 0.0f, limbSwing, limbSwingMod);
        this.swing(this.head, walkSpeed, walkDegree * 0.5f, true, 1.0f, 0.0f, limbSwing, limbSwingMod);
        this.flap(this.tail, walkSpeed, walkDegree * 0.5f, true, 0.0f, 0.0f, limbSwing, limbSwingMod);
        this.swing(this.tail, walkSpeed, walkDegree * 0.5f, false, 2.0f, 0.0f, limbSwing, limbSwingMod);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
