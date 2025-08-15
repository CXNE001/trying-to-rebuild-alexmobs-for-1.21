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

import com.github.alexthe666.alexsmobs.entity.EntityEmu;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelEmu
extends AdvancedEntityModel<EntityEmu> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox leg_left;
    private final AdvancedModelBox legfur_left;
    private final AdvancedModelBox foot_left;
    private final AdvancedModelBox leg_right;
    private final AdvancedModelBox legfur_right;
    private final AdvancedModelBox foot_right;
    private final AdvancedModelBox neck1;
    private final AdvancedModelBox neck2;
    private final AdvancedModelBox headPivot;
    private final AdvancedModelBox head;
    private final AdvancedModelBox beak;
    private final AdvancedModelBox tail;
    private final ModelAnimator animator;

    public ModelEmu() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -19.625f, -0.125f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-6.0f, -4.375f, -10.875f, 12.0f, 11.0f, 21.0f, 0.0f, false);
        this.leg_left = new AdvancedModelBox((AdvancedEntityModel)this, "leg_left");
        this.leg_left.setPos(3.0f, 6.625f, 0.125f);
        this.body.addChild((BasicModelPart)this.leg_left);
        this.leg_left.setTextureOffset(0, 55).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 11.0f, 2.0f, 0.0f, false);
        this.legfur_left = new AdvancedModelBox((AdvancedEntityModel)this, "legfur_left");
        this.legfur_left.setPos(0.0f, 0.0f, 0.0f);
        this.leg_left.addChild((BasicModelPart)this.legfur_left);
        this.legfur_left.setTextureOffset(31, 33).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 3.0f, 4.0f, 0.0f, false);
        this.foot_left = new AdvancedModelBox((AdvancedEntityModel)this, "foot_left");
        this.foot_left.setPos(0.0f, 11.0f, -1.0f);
        this.leg_left.addChild((BasicModelPart)this.foot_left);
        this.foot_left.setTextureOffset(0, 10).addBox(-1.5f, 0.0f, -4.0f, 3.0f, 2.0f, 6.0f, 0.0f, false);
        this.leg_right = new AdvancedModelBox((AdvancedEntityModel)this, "leg_right");
        this.leg_right.setPos(-3.0f, 6.625f, 0.125f);
        this.body.addChild((BasicModelPart)this.leg_right);
        this.leg_right.setTextureOffset(0, 55).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 11.0f, 2.0f, 0.0f, true);
        this.legfur_right = new AdvancedModelBox((AdvancedEntityModel)this, "legfur_right");
        this.legfur_right.setPos(0.0f, 0.0f, 0.0f);
        this.leg_right.addChild((BasicModelPart)this.legfur_right);
        this.legfur_right.setTextureOffset(31, 33).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 3.0f, 4.0f, 0.0f, true);
        this.foot_right = new AdvancedModelBox((AdvancedEntityModel)this, "foot_right");
        this.foot_right.setPos(0.0f, 11.0f, -1.0f);
        this.leg_right.addChild((BasicModelPart)this.foot_right);
        this.foot_right.setTextureOffset(0, 10).addBox(-1.5f, 0.0f, -4.0f, 3.0f, 2.0f, 6.0f, 0.0f, true);
        this.neck1 = new AdvancedModelBox((AdvancedEntityModel)this, "neck1");
        this.neck1.setPos(0.0f, 0.625f, -9.5f);
        this.body.addChild((BasicModelPart)this.neck1);
        this.neck1.setTextureOffset(41, 41).addBox(-3.0f, -9.0f, -6.0f, 6.0f, 12.0f, 6.0f, 0.0f, false);
        this.neck2 = new AdvancedModelBox((AdvancedEntityModel)this, "neck2");
        this.neck2.setPos(0.0f, -8.5f, -2.0f);
        this.neck1.addChild((BasicModelPart)this.neck2);
        this.neck2.setTextureOffset(46, 0).addBox(-2.0f, -7.0f, -2.0f, 4.0f, 7.0f, 4.0f, 0.0f, false);
        this.headPivot = new AdvancedModelBox((AdvancedEntityModel)this, "headPivot");
        this.headPivot.setPos(-0.5f, -6.5f, 0.0f);
        this.neck2.addChild((BasicModelPart)this.headPivot);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, 0.0f, 0.0f);
        this.headPivot.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 0).addBox(-2.0f, -4.0f, -3.0f, 5.0f, 4.0f, 5.0f, 0.0f, false);
        this.head.setTextureOffset(71, 54).addBox(0.5f, -6.0f, -3.0f, 0.0f, 6.0f, 7.0f, 0.0f, false);
        this.beak = new AdvancedModelBox((AdvancedEntityModel)this, "beak");
        this.beak.setPos(0.5f, -1.0f, -3.0f);
        this.head.addChild((BasicModelPart)this.beak);
        this.beak.setTextureOffset(46, 12).addBox(-2.0f, -1.0f, -3.0f, 4.0f, 2.0f, 3.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, -0.875f, 9.125f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 33).addBox(-5.0f, -0.5f, -5.0f, 10.0f, 11.0f, 10.0f, 0.0f, false);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityEmu.ANIMATION_DODGE_RIGHT);
        this.animator.startKeyframe(4);
        this.animator.move(this.body, 0.0f, -5.0f, 0.0f);
        this.animator.rotate(this.leg_left, 0.0f, 0.0f, Maths.rad(-60.0));
        this.animator.rotate(this.leg_right, 0.0f, 0.0f, Maths.rad(-45.0));
        this.animator.rotate(this.body, 0.0f, 0.0f, Maths.rad(25.0));
        this.animator.rotate(this.neck1, Maths.rad(-15.0), 0.0f, Maths.rad(20.0));
        this.animator.rotate(this.neck2, Maths.rad(-15.0), 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.headPivot, Maths.rad(-45.0), 0.0f, 0.0f);
        this.animator.rotate(this.foot_left, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.foot_right, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(1);
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityEmu.ANIMATION_DODGE_LEFT);
        this.animator.startKeyframe(4);
        this.animator.move(this.body, 0.0f, -5.0f, 0.0f);
        this.animator.rotate(this.leg_left, 0.0f, 0.0f, Maths.rad(60.0));
        this.animator.rotate(this.leg_right, 0.0f, 0.0f, Maths.rad(45.0));
        this.animator.rotate(this.body, 0.0f, 0.0f, Maths.rad(-25.0));
        this.animator.rotate(this.neck1, Maths.rad(-15.0), 0.0f, Maths.rad(-20.0));
        this.animator.rotate(this.neck2, Maths.rad(-15.0), 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.headPivot, Maths.rad(-45.0), 0.0f, 0.0f);
        this.animator.rotate(this.foot_left, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.foot_right, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(1);
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityEmu.ANIMATION_PUZZLED);
        this.animator.startKeyframe(3);
        this.animator.rotate(this.body, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck1, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck2, Maths.rad(15.0), 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.headPivot, Maths.rad(-10.0), Maths.rad(-10.0), Maths.rad(-35.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.body, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck1, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck2, Maths.rad(0.0), 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.headPivot, Maths.rad(-10.0), Maths.rad(10.0), Maths.rad(35.0));
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(3);
        this.animator.startKeyframe(3);
        this.animator.rotate(this.body, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck1, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck2, Maths.rad(15.0), 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.headPivot, Maths.rad(-10.0), Maths.rad(-10.0), Maths.rad(-35.0));
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(3);
        this.animator.startKeyframe(3);
        this.animator.rotate(this.body, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck1, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck2, Maths.rad(0.0), 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.headPivot, Maths.rad(-10.0), Maths.rad(10.0), Maths.rad(35.0));
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(3);
        this.animator.startKeyframe(3);
        this.animator.rotate(this.body, Maths.rad(-2.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(2.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(2.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck1, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck2, Maths.rad(15.0), 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.headPivot, Maths.rad(-10.0), Maths.rad(-10.0), Maths.rad(-35.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityEmu.ANIMATION_PECK_GROUND);
        this.animator.startKeyframe(10);
        this.animator.move(this.neck1, 0.0f, -0.2f, -0.2f);
        this.animator.rotate(this.neck1, Maths.rad(145.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck2, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.headPivot, Maths.rad(-60.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.neck1, 0.0f, -0.2f, -0.2f);
        this.animator.rotate(this.neck1, Maths.rad(135.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck2, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.headPivot, Maths.rad(-60.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.neck1, 0.0f, -1.0f, -0.2f);
        this.animator.rotate(this.neck1, Maths.rad(145.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck2, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.headPivot, Maths.rad(-60.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityEmu.ANIMATION_SCRATCH);
        this.animator.startKeyframe(5);
        this.animator.move(this.leg_right, 0.0f, -0.5f, 2.0f);
        this.animator.move(this.leg_left, 0.0f, -0.5f, 2.0f);
        this.animator.rotate(this.body, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck1, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck2, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.headPivot, Maths.rad(24.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.move(this.leg_right, 0.0f, -0.5f, 2.0f);
        this.animator.rotate(this.body, Maths.rad(-40.0), 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.leg_left, Maths.rad(-70.0), 0.0f, 0.0f);
        this.animator.rotate(this.foot_left, Maths.rad(70.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(40.0), 0.0f, Maths.rad(30.0));
        this.animator.rotate(this.neck1, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck2, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.headPivot, Maths.rad(24.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.move(this.leg_right, 0.0f, -0.5f, 2.0f);
        this.animator.move(this.leg_left, 0.0f, -0.5f, 2.0f);
        this.animator.rotate(this.body, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck1, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck2, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.headPivot, Maths.rad(24.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.move(this.leg_right, 0.0f, -0.5f, 2.0f);
        this.animator.rotate(this.body, Maths.rad(-40.0), 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.leg_right, Maths.rad(-70.0), 0.0f, 0.0f);
        this.animator.rotate(this.foot_right, Maths.rad(70.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(40.0), 0.0f, Maths.rad(30.0));
        this.animator.rotate(this.neck1, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck2, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.headPivot, Maths.rad(24.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
    }

    public void setupAnim(EntityEmu emu, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(emu, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 0.4f;
        float walkDegree = 0.4f;
        float idleSpeed = 0.05f;
        float idleDegree = 0.1f;
        this.walk(this.neck1, idleSpeed, idleDegree, false, 0.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.neck2, idleSpeed, idleDegree, true, 1.0f, 0.15f, ageInTicks, 1.0f);
        this.walk(this.head, idleSpeed, idleDegree, false, 1.0f, 0.25f, ageInTicks, 1.0f);
        this.walk(this.tail, idleSpeed, idleDegree, false, 2.0f, -0.05f, ageInTicks, 1.0f);
        boolean running = true;
        if (running) {
            this.walk(this.leg_right, walkSpeed, walkDegree * 2.0f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.leg_left, walkSpeed, walkDegree * 2.0f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.foot_right, walkSpeed, walkDegree * 1.5f, false, 2.0f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.foot_left, walkSpeed, walkDegree * 1.5f, true, 2.0f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.neck1, walkSpeed, walkDegree * 1.0f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.neck2, walkSpeed, walkDegree * 0.8f, true, 1.3f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.head, walkSpeed, walkDegree * 0.25f, true, 1.3f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.tail, walkSpeed, walkDegree * 0.4f, true, 1.3f, -0.4f, limbSwing, limbSwingAmount);
            this.bob(this.body, walkSpeed, walkDegree * 14.0f, true, limbSwing, limbSwingAmount);
            this.flap(this.body, walkSpeed, walkDegree * 0.7f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.leg_left, walkSpeed, walkDegree * 0.7f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.leg_right, walkSpeed, walkDegree * 0.7f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.neck1, walkSpeed, walkDegree * 0.8f, true, 3.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.neck2, walkSpeed, walkDegree * 0.4f, true, 3.2f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.tail, walkSpeed, walkDegree * 0.8f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        } else {
            this.walk(this.leg_right, walkSpeed, walkDegree * 1.85f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.leg_left, walkSpeed, walkDegree * 1.85f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.foot_right, walkSpeed, walkDegree * 1.5f, false, 1.95f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.foot_left, walkSpeed, walkDegree * 1.5f, true, 1.95f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.neck1, walkSpeed, walkDegree * 0.6f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.neck2, walkSpeed, walkDegree * 0.5f, true, 1.3f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.head, walkSpeed, walkDegree * 0.15f, true, 1.3f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.tail, walkSpeed, walkDegree * 0.4f, true, 1.3f, -0.4f, limbSwing, limbSwingAmount);
            this.bob(this.body, walkSpeed, walkDegree * 5.0f, true, limbSwing, limbSwingAmount);
        }
        this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.neck2, this.head});
        float runProgress = 5.0f * limbSwingAmount;
        if (emu.getAnimation() != EntityEmu.ANIMATION_PECK_GROUND) {
            this.progressPositionPrev(this.neck1, runProgress, 0.0f, -3.0f, -1.5f, 5.0f);
        }
        this.progressPositionPrev(this.neck2, runProgress, 0.0f, 0.5f, -1.5f, 5.0f);
        this.progressPositionPrev(this.headPivot, runProgress, 0.0f, 0.5f, -0.5f, 5.0f);
        this.progressRotationPrev(this.neck1, runProgress, Maths.rad(120.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.neck2, runProgress, Maths.rad(-60.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.headPivot, runProgress, Maths.rad(-50.0), 0.0f, 0.0f, 5.0f);
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.5f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.35f, 0.35f, 0.35f);
            matrixStackIn.m_85837_(0.0, 2.8, 0.0);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.leg_left, (Object)this.leg_right, (Object)this.legfur_left, (Object)this.legfur_right, (Object)this.tail, (Object)this.neck1, (Object)this.neck2, (Object)this.headPivot, (Object)this.head, (Object)this.beak, (Object[])new AdvancedModelBox[]{this.foot_left, this.foot_right});
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
