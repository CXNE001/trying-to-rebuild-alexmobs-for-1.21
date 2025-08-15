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

import com.github.alexthe666.alexsmobs.entity.EntityGeladaMonkey;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelGeladaMonkey
extends AdvancedEntityModel<EntityGeladaMonkey> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox torso;
    private final AdvancedModelBox neck;
    private final AdvancedModelBox head;
    private final AdvancedModelBox mouth;
    private final AdvancedModelBox left_arm;
    private final AdvancedModelBox right_arm;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox right_leg;
    public final ModelAnimator animator;

    public ModelGeladaMonkey() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -11.0f, 4.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(30, 36).addBox(-3.5f, -3.0f, -5.0f, 7.0f, 7.0f, 9.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, -1.5f, 3.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.setRotationAngle(this.tail, 0.5672f, 0.0f, 0.0f);
        this.tail.setTextureOffset(0, 0).addBox(-1.5f, -1.5f, 0.0f, 3.0f, 3.0f, 20.0f, 0.0f, false);
        this.torso = new AdvancedModelBox((AdvancedEntityModel)this, "torso");
        this.torso.setRotationPoint(0.0f, 0.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.torso);
        this.torso.setTextureOffset(0, 24).addBox(-4.5f, -5.0f, -9.0f, 9.0f, 10.0f, 10.0f, 0.0f, false);
        this.torso.setTextureOffset(27, 0).addBox(-4.5f, 5.0f, -9.0f, 9.0f, 3.0f, 10.0f, 0.0f, false);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setRotationPoint(0.0f, -0.9f, -8.6f);
        this.torso.addChild((BasicModelPart)this.neck);
        this.neck.setTextureOffset(39, 24).addBox(-5.0f, -5.0f, -3.0f, 10.0f, 7.0f, 4.0f, 0.0f, false);
        this.neck.setTextureOffset(50, 67).addBox(-8.0f, -5.0f, -2.0f, 16.0f, 11.0f, 0.0f, 0.0f, false);
        this.neck.setTextureOffset(25, 60).addBox(-4.0f, -5.0f, -2.4f, 8.0f, 7.0f, 3.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -1.0f, -2.0f);
        this.neck.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 0).addBox(-2.5f, -2.0f, -2.0f, 5.0f, 4.0f, 3.0f, 0.0f, false);
        this.mouth = new AdvancedModelBox((AdvancedEntityModel)this, "mouth");
        this.mouth.setRotationPoint(0.0f, 1.0f, -1.0f);
        this.head.addChild((BasicModelPart)this.mouth);
        this.setRotationAngle(this.mouth, -1.0908f, 0.0f, 0.0f);
        this.mouth.setTextureOffset(0, 8).addBox(-1.5f, -1.5f, -1.2f, 3.0f, 6.0f, 4.0f, 0.0f, false);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setRotationPoint(2.0f, 3.0f, -7.0f);
        this.torso.addChild((BasicModelPart)this.left_arm);
        this.left_arm.setTextureOffset(11, 45).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 8.0f, 2.0f, 0.0f, false);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setRotationPoint(-2.0f, 3.0f, -7.0f);
        this.torso.addChild((BasicModelPart)this.right_arm);
        this.right_arm.setTextureOffset(11, 45).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 8.0f, 2.0f, 0.0f, true);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setRotationPoint(2.3f, 5.0f, 2.0f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(0, 45).addBox(-1.0f, -1.0f, -1.0f, 2.0f, 7.0f, 3.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setRotationPoint(-2.3f, 5.0f, 2.0f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(0, 45).addBox(-1.0f, -1.0f, -1.0f, 2.0f, 7.0f, 3.0f, 0.0f, true);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityGeladaMonkey.ANIMATION_SWIPE_L);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.body, 0.0f, Maths.rad(5.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, Maths.rad(-5.0), 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(25.0), Maths.rad(10.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.body, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(5.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_leg, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_leg, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(-80.0), Maths.rad(-20.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityGeladaMonkey.ANIMATION_SWIPE_R);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.body, 0.0f, Maths.rad(-5.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, Maths.rad(5.0), 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(25.0), Maths.rad(-10.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.body, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(5.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_leg, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_leg, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-80.0), Maths.rad(20.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityGeladaMonkey.ANIMATION_CHEST);
        this.animator.startKeyframe(5);
        this.standPose();
        this.animator.rotate(this.right_arm, 0.0f, 0.0f, Maths.rad(25.0));
        this.animator.rotate(this.left_arm, 0.0f, 0.0f, Maths.rad(-25.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.standPose();
        this.animator.rotate(this.neck, 0.0f, 0.0f, Maths.rad(25.0));
        this.animator.rotate(this.right_arm, 0.0f, 0.0f, Maths.rad(25.0));
        this.animator.rotate(this.left_arm, 0.0f, 0.0f, Maths.rad(-25.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(10);
        this.standPose();
        this.animator.rotate(this.neck, 0.0f, 0.0f, Maths.rad(-25.0));
        this.animator.rotate(this.right_arm, 0.0f, 0.0f, Maths.rad(5.0));
        this.animator.rotate(this.left_arm, 0.0f, 0.0f, Maths.rad(-5.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(10);
        this.standPose();
        this.animator.rotate(this.neck, 0.0f, 0.0f, Maths.rad(25.0));
        this.animator.rotate(this.right_arm, 0.0f, 0.0f, Maths.rad(25.0));
        this.animator.rotate(this.left_arm, 0.0f, 0.0f, Maths.rad(-25.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityGeladaMonkey.ANIMATION_GROOM);
        this.animator.startKeyframe(3);
        this.animator.rotate(this.left_arm, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-90.0), Maths.rad(-10.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.neck, Maths.rad(20.0), 0.0f, Maths.rad(20.0));
        this.animator.rotate(this.left_arm, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-90.0), Maths.rad(-10.0), 0.0f);
        this.animator.move(this.neck, 1.0f, 0.0f, -1.0f);
        this.animator.move(this.right_arm, 0.0f, 0.0f, 3.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.neck, Maths.rad(5.0), 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.left_arm, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-130.0), Maths.rad(-10.0), 0.0f);
        this.animator.move(this.neck, 1.0f, 0.0f, -1.0f);
        this.animator.move(this.right_arm, 0.0f, 0.0f, 2.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.right_arm, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(-90.0), Maths.rad(10.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.neck, Maths.rad(20.0), 0.0f, Maths.rad(-20.0));
        this.animator.rotate(this.right_arm, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(-90.0), Maths.rad(10.0), 0.0f);
        this.animator.move(this.neck, -1.0f, 0.0f, -1.0f);
        this.animator.move(this.right_arm, 0.0f, 0.0f, 3.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.neck, Maths.rad(5.0), 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.right_arm, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(-130.0), Maths.rad(10.0), 0.0f);
        this.animator.move(this.neck, -1.0f, 0.0f, -1.0f);
        this.animator.move(this.right_arm, 0.0f, 0.0f, 2.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
    }

    private void standPose() {
        this.animator.rotate(this.body, Maths.rad(-65.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck, Maths.rad(65.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_leg, Maths.rad(65.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_leg, Maths.rad(65.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(65.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(65.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail, Maths.rad(50.0), 0.0f, 0.0f);
        this.animator.move(this.body, 0.0f, 0.5f, -2.0f);
        this.animator.move(this.neck, 0.0f, -1.0f, -2.0f);
        this.animator.move(this.right_leg, 0.0f, -1.0f, 1.0f);
        this.animator.move(this.left_leg, 0.0f, -1.0f, 1.0f);
        this.animator.move(this.right_arm, -1.0f, 1.0f, -1.0f);
        this.animator.move(this.left_arm, 1.0f, 1.0f, -1.0f);
    }

    public void setupAnim(EntityGeladaMonkey entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        boolean running = entity.isAggro();
        float runSpeed = 0.7f;
        float runDegree = 0.7f;
        float walkSpeed = 0.9f;
        float walkDegree = 0.6f;
        float idleSpeed = 0.15f;
        float idleDegree = 0.5f;
        float stillProgress = (1.0f - limbSwingAmount) * 5.0f;
        float sitProgress = entity.prevSitProgress + (entity.sitProgress - entity.prevSitProgress) * (ageInTicks - (float)entity.f_19797_);
        this.progressRotationPrev(this.tail, stillProgress, Maths.rad(-40.0), 0.0f, 0.0f, 5.0f);
        this.swing(this.tail, idleSpeed, idleDegree, true, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.bob(this.neck, idleSpeed * 0.5f, idleDegree * 0.25f, false, ageInTicks, 1.0f);
        if (running) {
            this.walk(this.body, runSpeed, runDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.tail, runSpeed, runDegree * 0.5f, true, 0.0f, 0.3f, limbSwing, limbSwingAmount);
            this.walk(this.neck, runSpeed, runDegree * 0.2f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.right_arm, runSpeed, runDegree * 1.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.left_arm, runSpeed, runDegree * 1.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.right_leg, runSpeed, runDegree * 1.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.left_leg, runSpeed, runDegree * 1.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.right_leg, runSpeed, runDegree * 0.2f, true, 0.0f, -0.2f, limbSwing, limbSwingAmount);
            this.flap(this.left_leg, runSpeed, runDegree * 0.2f, false, 0.0f, -0.2f, limbSwing, limbSwingAmount);
            this.bob(this.body, runSpeed, runDegree * 3.0f, false, limbSwing, limbSwingAmount);
        } else {
            this.walk(this.tail, walkSpeed, -walkDegree * 0.2f, true, 1.0f, 0.1f, limbSwing, limbSwingAmount);
            this.walk(this.body, walkSpeed, walkDegree * 0.1f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.neck, walkSpeed, -walkDegree * 0.1f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.right_arm, walkSpeed, walkDegree * 1.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.left_arm, walkSpeed, walkDegree * 1.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.right_leg, walkSpeed, walkDegree * 1.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.left_leg, walkSpeed, walkDegree * 1.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.bob(this.right_leg, walkSpeed, walkDegree * -1.2f, true, limbSwing, limbSwingAmount);
            this.bob(this.left_leg, walkSpeed, walkDegree * -1.2f, true, limbSwing, limbSwingAmount);
        }
        this.progressRotationPrev(this.body, sitProgress, Maths.rad(-40.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg, sitProgress, Maths.rad(-45.0), Maths.rad(-20.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg, sitProgress, Maths.rad(-45.0), Maths.rad(20.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, sitProgress, Maths.rad(40.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_arm, sitProgress, Maths.rad(40.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, sitProgress, Maths.rad(50.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.neck, sitProgress, Maths.rad(40.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, sitProgress, 0.0f, 5.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_leg, sitProgress, 0.0f, -2.5f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_leg, sitProgress, 0.0f, -2.5f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_arm, sitProgress, 0.0f, 2.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.right_arm, sitProgress, 0.0f, 2.0f, 2.0f, 5.0f);
        this.neck.rotateAngleY += netHeadYaw / 57.295776f * 0.5f;
        this.neck.rotateAngleX += headPitch / 57.295776f;
        if (entity.m_6162_()) {
            this.head.setScale(1.3f, 1.3f, 1.3f);
            this.neck.setScale(1.25f, 1.25f, 1.25f);
        } else {
            this.neck.setScale(1.0f, 1.0f, 1.0f);
            this.head.setScale(1.0f, 1.0f, 1.0f);
        }
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.tail, (Object)this.torso, (Object)this.neck, (Object)this.head, (Object)this.left_arm, (Object)this.left_leg, (Object)this.right_arm, (Object)this.right_leg, (Object)this.mouth);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
