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

import com.github.alexthe666.alexsmobs.entity.EntityBison;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelBison
extends AdvancedEntityModel<EntityBison> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox right_leg;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox tail_r1;
    private final AdvancedModelBox torso;
    private final AdvancedModelBox head;
    private final AdvancedModelBox horn_r1;
    private final AdvancedModelBox left_ear;
    private final AdvancedModelBox right_ear;
    private final AdvancedModelBox beard;
    private final AdvancedModelBox left_arm;
    private final AdvancedModelBox right_arm;
    private final ModelAnimator animator;

    public ModelBison() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -23.0f, 4.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 54).addBox(-9.0f, -11.0f, -1.0f, 18.0f, 20.0f, 19.0f, 0.0f, false);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setRotationPoint(5.8f, 5.0f, 14.0f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(75, 80).addBox(-3.0f, 4.0f, -3.0f, 6.0f, 14.0f, 7.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setRotationPoint(-5.8f, 5.0f, 14.0f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(75, 80).addBox(-3.0f, 4.0f, -3.0f, 6.0f, 14.0f, 7.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, -6.0f, 18.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "tail_r1");
        this.tail_r1.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.tail.addChild((BasicModelPart)this.tail_r1);
        this.setRotationAngle(this.tail_r1, 0.0436f, 0.0f, 0.0f);
        this.tail_r1.setTextureOffset(0, 54).addBox(-1.5f, 0.0f, 0.0f, 3.0f, 14.0f, 0.0f, 0.0f, false);
        this.torso = new AdvancedModelBox((AdvancedEntityModel)this, "torso");
        this.torso.setRotationPoint(0.0f, -3.0f, 1.0f);
        this.body.addChild((BasicModelPart)this.torso);
        this.torso.setTextureOffset(0, 0).addBox(-10.0f, -14.0f, -28.0f, 20.0f, 27.0f, 26.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, 0.0f, -27.0f);
        this.torso.addChild((BasicModelPart)this.head);
        this.setRotationAngle(this.head, -0.2618f, 0.0f, 0.0f);
        this.head.setTextureOffset(76, 54).addBox(-4.0f, 0.0f, -8.0f, 8.0f, 15.0f, 10.0f, 0.0f, false);
        this.head.setTextureOffset(67, 0).addBox(-6.0f, -6.0f, -9.9f, 12.0f, 10.0f, 12.0f, 0.0f, false);
        this.horn_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "horn_r1");
        this.horn_r1.setRotationPoint(-7.0f, 0.5f, -5.0f);
        this.head.addChild((BasicModelPart)this.horn_r1);
        this.setRotationAngle(this.horn_r1, 0.3927f, 0.0f, 0.0f);
        this.horn_r1.setTextureOffset(0, 0).addBox(-1.0f, -4.5f, -1.0f, 2.0f, 7.0f, 2.0f, 0.0f, true);
        this.horn_r1.setTextureOffset(11, 1).addBox(1.0f, -0.5f, -1.0f, 3.0f, 3.0f, 2.0f, 0.0f, true);
        this.horn_r1.setTextureOffset(11, 1).addBox(10.0f, -0.5f, -1.0f, 3.0f, 3.0f, 2.0f, 0.0f, false);
        this.horn_r1.setTextureOffset(0, 0).addBox(13.0f, -4.5f, -1.0f, 2.0f, 7.0f, 2.0f, 0.0f, false);
        this.left_ear = new AdvancedModelBox((AdvancedEntityModel)this, "left_ear");
        this.left_ear.setRotationPoint(4.0f, 3.0f, -3.0f);
        this.head.addChild((BasicModelPart)this.left_ear);
        this.setRotationAngle(this.left_ear, 0.0f, -0.6981f, 0.4363f);
        this.left_ear.setTextureOffset(0, 23).addBox(0.0f, -1.0f, 0.0f, 5.0f, 2.0f, 1.0f, 0.0f, false);
        this.right_ear = new AdvancedModelBox((AdvancedEntityModel)this, "right_ear");
        this.right_ear.setRotationPoint(-4.0f, 3.0f, -3.0f);
        this.head.addChild((BasicModelPart)this.right_ear);
        this.setRotationAngle(this.right_ear, 0.0f, 0.6981f, -0.4363f);
        this.right_ear.setTextureOffset(0, 23).addBox(-5.0f, -1.0f, 0.0f, 5.0f, 2.0f, 1.0f, 0.0f, true);
        this.beard = new AdvancedModelBox((AdvancedEntityModel)this, "beard");
        this.beard.setRotationPoint(0.0f, 15.0f, 1.0f);
        this.head.addChild((BasicModelPart)this.beard);
        this.setRotationAngle(this.beard, 0.2182f, 0.0f, 0.0f);
        this.beard.setTextureOffset(0, 0).addBox(0.0f, -5.0f, -5.0f, 0.0f, 13.0f, 10.0f, 0.0f, false);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setRotationPoint(7.8f, 10.0f, -15.0f);
        this.torso.addChild((BasicModelPart)this.left_arm);
        this.left_arm.setTextureOffset(93, 23).addBox(-3.0f, 3.0f, -3.0f, 5.0f, 13.0f, 5.0f, 0.0f, false);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setRotationPoint(-7.8f, 10.0f, -15.0f);
        this.torso.addChild((BasicModelPart)this.right_arm);
        this.right_arm.setTextureOffset(93, 23).addBox(-2.0f, 3.0f, -3.0f, 5.0f, 13.0f, 5.0f, 0.0f, true);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityBison.ANIMATION_PREPARE_CHARGE);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, 0.0f, Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.torso, 0.0f, Maths.rad(15.0), 0.0f);
        this.animator.rotate(this.body, 0.0f, Maths.rad(5.0), 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, 0.0f, Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.torso, 0.0f, Maths.rad(-15.0), 0.0f);
        this.animator.rotate(this.body, 0.0f, Maths.rad(-5.0), 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(40.0), Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.torso, 0.0f, Maths.rad(15.0), 0.0f);
        this.animator.rotate(this.body, 0.0f, Maths.rad(5.0), 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(40.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(40.0), Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.torso, 0.0f, Maths.rad(-15.0), 0.0f);
        this.animator.rotate(this.body, 0.0f, Maths.rad(-5.0), 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityBison.ANIMATION_EAT);
        this.animator.startKeyframe(5);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.eatPose();
        this.animator.move(this.head, 0.0f, 1.0f, 1.0f);
        this.animator.rotate(this.head, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.eatPose();
        this.animator.move(this.head, 0.0f, 1.0f, 1.0f);
        this.animator.rotate(this.head, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.eatPose();
        this.animator.move(this.head, 0.0f, 1.0f, 1.0f);
        this.animator.rotate(this.head, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityBison.ANIMATION_ATTACK);
        this.animator.startKeyframe(5);
        this.animator.move(this.head, 0.0f, 2.0f, 1.0f);
        this.animator.rotate(this.head, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, -2.0f, 0.0f);
        this.animator.move(this.left_arm, 0.0f, -1.0f, 0.0f);
        this.animator.move(this.right_arm, 0.0f, -1.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_leg, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_leg, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
    }

    private void eatPose() {
        this.animator.rotate(this.head, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.torso, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.move(this.torso, 0.0f, 0.0f, 2.0f);
        this.animator.rotate(this.left_arm, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.rotate(this.beard, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.move(this.left_arm, 0.0f, -5.0f, -1.0f);
        this.animator.move(this.right_arm, 0.0f, -5.0f, -1.0f);
        this.animator.move(this.head, 0.0f, 4.0f, 1.0f);
    }

    public void setupAnim(EntityBison entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 0.7f;
        float walkDegree = 0.6f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float runProgress = entity.prevChargeProgress + (entity.chargeProgress - entity.prevChargeProgress) * partialTick;
        this.progressPositionPrev(this.head, runProgress, 0.0f, 1.0f, -3.5f, 5.0f);
        this.progressRotationPrev(this.head, runProgress, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        if (runProgress > 0.0f) {
            this.walk(this.right_arm, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.left_arm, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.right_arm, walkSpeed, walkDegree * 0.25f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.left_arm, walkSpeed, walkDegree * 0.25f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.right_leg, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.left_leg, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.right_leg, walkSpeed, walkDegree * 0.25f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.left_leg, walkSpeed, walkDegree * 0.25f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.tail, walkSpeed, walkDegree * 0.2f, true, 1.0f, -0.6f, limbSwing, limbSwingAmount);
            this.bob(this.body, walkSpeed * 0.5f, walkDegree * 5.0f, true, limbSwing, limbSwingAmount);
            this.bob(this.head, walkSpeed * 0.5f, -walkDegree * 2.0f, false, limbSwing, limbSwingAmount);
        } else {
            this.walk(this.right_arm, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.left_arm, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.right_leg, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.left_leg, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.tail, walkSpeed, walkDegree * 0.1f, true, 1.0f, -0.6f, limbSwing, limbSwingAmount);
            this.bob(this.body, walkSpeed, walkDegree, true, limbSwing, limbSwingAmount);
            this.bob(this.head, walkSpeed, -walkDegree, false, limbSwing, limbSwingAmount);
        }
        this.flap(this.beard, idleSpeed, idleDegree, false, 2.0f, 0.0f, ageInTicks, 1.0f);
        this.swing(this.left_ear, idleSpeed, idleDegree * 0.5f, true, 3.0f, -0.2f, ageInTicks, 1.0f);
        this.swing(this.right_ear, idleSpeed, idleDegree * 0.5f, true, 3.0f, 0.2f, ageInTicks, 1.0f);
        this.walk(this.tail, idleSpeed, idleDegree, false, 1.0f, 0.1f, ageInTicks, 1.0f);
        this.bob(this.head, idleSpeed, idleDegree, false, ageInTicks, 1.0f);
        this.head.rotateAngleY += netHeadYaw * 0.35f * ((float)Math.PI / 180);
        this.head.rotateAngleX += headPitch * ((float)Math.PI / 180);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.left_arm, (Object)this.right_arm, (Object)this.head, (Object)this.tail, (Object)this.tail_r1, (Object)this.horn_r1, (Object)this.beard, (Object)this.left_leg, (Object)this.right_leg, (Object)this.left_ear, (Object[])new AdvancedModelBox[]{this.right_ear, this.torso});
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }
}
