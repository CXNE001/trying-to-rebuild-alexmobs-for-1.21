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

import com.github.alexthe666.alexsmobs.entity.EntityTiger;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelTiger
extends AdvancedEntityModel<EntityTiger> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox tail2;
    private final AdvancedModelBox head;
    private final AdvancedModelBox earleft;
    private final AdvancedModelBox earright;
    private final AdvancedModelBox snout;
    private final AdvancedModelBox legleft;
    private final AdvancedModelBox legright;
    private final AdvancedModelBox armleft;
    private final AdvancedModelBox armright;
    private final ModelAnimator animator;

    public ModelTiger() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -14.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-5.0f, -6.0f, -12.0f, 10.0f, 11.0f, 22.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, -4.0f, 8.6f);
        this.body.addChild((BasicModelPart)this.tail);
        this.setRotationAngle(this.tail, 0.0873f, 0.0f, 0.0f);
        this.tail.setTextureOffset(46, 34).addBox(-1.5f, -1.0f, 0.0f, 3.0f, 9.0f, 3.0f, 0.0f, false);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this, "tail2");
        this.tail2.setPos(0.0f, 7.9f, 0.0f);
        this.tail.addChild((BasicModelPart)this.tail2);
        this.setRotationAngle(this.tail2, 0.2182f, 0.0f, 0.0f);
        this.tail2.setTextureOffset(43, 0).addBox(-1.5f, 0.0f, 0.0f, 3.0f, 9.0f, 3.0f, -0.1f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -4.0f, -12.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 34).addBox(-4.0f, -4.0f, -6.0f, 8.0f, 7.0f, 6.0f, 0.0f, false);
        this.head.setTextureOffset(9, 15).addBox(4.0f, -1.0f, -5.0f, 1.0f, 4.0f, 2.0f, 0.0f, false);
        this.head.setTextureOffset(9, 15).addBox(-5.0f, -1.0f, -5.0f, 1.0f, 4.0f, 2.0f, 0.0f, true);
        this.earleft = new AdvancedModelBox((AdvancedEntityModel)this, "earleft");
        this.earleft.setPos(3.0f, -4.0f, -2.0f);
        this.head.addChild((BasicModelPart)this.earleft);
        this.earleft.setTextureOffset(0, 15).addBox(0.0f, -2.0f, -2.0f, 1.0f, 2.0f, 3.0f, 0.0f, false);
        this.earright = new AdvancedModelBox((AdvancedEntityModel)this, "earright");
        this.earright.setPos(-3.0f, -4.0f, -2.0f);
        this.head.addChild((BasicModelPart)this.earright);
        this.earright.setTextureOffset(0, 15).addBox(-1.0f, -2.0f, -2.0f, 1.0f, 2.0f, 3.0f, 0.0f, true);
        this.snout = new AdvancedModelBox((AdvancedEntityModel)this, "snout");
        this.snout.setPos(0.0f, -1.0f, -6.0f);
        this.head.addChild((BasicModelPart)this.snout);
        this.setRotationAngle(this.snout, 0.1745f, 0.0f, 0.0f);
        this.snout.setTextureOffset(43, 13).addBox(-2.0f, 0.0f, -3.0f, 4.0f, 4.0f, 3.0f, 0.0f, false);
        this.legleft = new AdvancedModelBox((AdvancedEntityModel)this, "legleft");
        this.legleft.setPos(2.9f, 5.0f, 7.9f);
        this.body.addChild((BasicModelPart)this.legleft);
        this.legleft.setTextureOffset(0, 48).addBox(-2.0f, -2.0f, -3.0f, 4.0f, 11.0f, 5.0f, 0.0f, false);
        this.legright = new AdvancedModelBox((AdvancedEntityModel)this, "legright");
        this.legright.setPos(-2.9f, 5.0f, 7.9f);
        this.body.addChild((BasicModelPart)this.legright);
        this.legright.setTextureOffset(0, 48).addBox(-2.0f, -2.0f, -3.0f, 4.0f, 11.0f, 5.0f, 0.0f, true);
        this.armleft = new AdvancedModelBox((AdvancedEntityModel)this, "armleft");
        this.armleft.setPos(3.5f, -1.5f, -9.0f);
        this.body.addChild((BasicModelPart)this.armleft);
        this.armleft.setTextureOffset(29, 34).addBox(-2.0f, -5.5f, -2.0f, 4.0f, 21.0f, 4.0f, 0.0f, false);
        this.armright = new AdvancedModelBox((AdvancedEntityModel)this, "armright");
        this.armright.setPos(-3.5f, -1.5f, -9.0f);
        this.body.addChild((BasicModelPart)this.armright);
        this.armright.setTextureOffset(29, 34).addBox(-2.0f, -5.5f, -2.0f, 4.0f, 21.0f, 4.0f, 0.0f, true);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityTiger.ANIMATION_PAW_R);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.armleft, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.armright, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.move(this.body, 0.0f, 0.0f, 3.0f);
        this.animator.move(this.armright, 0.0f, 1.0f, 0.0f);
        this.animator.move(this.armleft, 0.0f, 1.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.head, Maths.rad(35.0), 0.0f, 0.0f);
        this.animator.rotate(this.armleft, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.armright, Maths.rad(-70.0), 0.0f, Maths.rad(20.0));
        this.animator.rotate(this.tail, Maths.rad(50.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail2, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.move(this.body, 0.0f, -3.0f, -3.0f);
        this.animator.rotate(this.body, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.legleft, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.rotate(this.legright, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.head, Maths.rad(35.0), 0.0f, 0.0f);
        this.animator.rotate(this.armleft, Maths.rad(10.0), 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.armright, Maths.rad(-40.0), 0.0f, Maths.rad(-20.0));
        this.animator.rotate(this.tail, Maths.rad(50.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail2, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.move(this.body, 0.0f, -3.0f, -3.0f);
        this.animator.move(this.armright, -1.0f, 0.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.legleft, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.rotate(this.legright, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityTiger.ANIMATION_PAW_L);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.armleft, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.armright, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.move(this.body, 0.0f, 0.0f, 3.0f);
        this.animator.move(this.armright, 0.0f, 1.0f, 0.0f);
        this.animator.move(this.armleft, 0.0f, 1.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.head, Maths.rad(35.0), 0.0f, 0.0f);
        this.animator.rotate(this.armleft, Maths.rad(-70.0), 0.0f, Maths.rad(-20.0));
        this.animator.rotate(this.armright, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail, Maths.rad(50.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail2, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.move(this.body, 0.0f, -3.0f, -3.0f);
        this.animator.rotate(this.body, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.legleft, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.rotate(this.legright, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.head, Maths.rad(35.0), 0.0f, 0.0f);
        this.animator.rotate(this.armleft, Maths.rad(-40.0), 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.armright, Maths.rad(10.0), 0.0f, Maths.rad(20.0));
        this.animator.rotate(this.tail, Maths.rad(50.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail2, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.move(this.body, 0.0f, -3.0f, -3.0f);
        this.animator.move(this.armleft, 1.0f, 0.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.legleft, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.rotate(this.legright, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityTiger.ANIMATION_TAIL_FLICK);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.tail, Maths.rad(10.0), 0.0f, Maths.rad(30.0));
        this.animator.rotate(this.tail2, Maths.rad(10.0), 0.0f, Maths.rad(20.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(10);
        this.animator.rotate(this.tail, Maths.rad(10.0), 0.0f, Maths.rad(-30.0));
        this.animator.rotate(this.tail2, Maths.rad(10.0), 0.0f, Maths.rad(-20.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(10);
        this.animator.rotate(this.tail, Maths.rad(10.0), 0.0f, Maths.rad(30.0));
        this.animator.rotate(this.tail2, Maths.rad(10.0), 0.0f, Maths.rad(20.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityTiger.ANIMATION_LEAP);
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 1.0f, 3.0f);
        this.animator.move(this.head, 0.0f, 2.0f, 0.0f);
        this.animator.move(this.armright, 0.0f, 1.0f, 2.0f);
        this.animator.move(this.armleft, 0.0f, 1.0f, 2.0f);
        this.animator.rotate(this.head, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.armleft, Maths.rad(-50.0), 0.0f, 0.0f);
        this.animator.rotate(this.armright, Maths.rad(-50.0), 0.0f, 0.0f);
        this.animator.rotate(this.legright, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.legleft, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.tail, Maths.rad(90.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail2, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.legleft, Maths.rad(30.0), 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.legright, Maths.rad(30.0), 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.armright, Maths.rad(-60.0), 0.0f, Maths.rad(30.0));
        this.animator.rotate(this.armleft, Maths.rad(-60.0), 0.0f, Maths.rad(-30.0));
        this.animator.move(this.armright, -1.0f, -1.0f, 1.0f);
        this.animator.move(this.armleft, 1.0f, -1.0f, 1.0f);
        this.animator.move(this.legright, 0.0f, -4.0f, -1.0f);
        this.animator.move(this.legleft, 0.0f, -4.0f, -1.0f);
        this.animator.move(this.body, 0.0f, -5.0f, 4.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.resetKeyframe(5);
    }

    public void setupAnim(EntityTiger entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 0.7f;
        float walkDegree = 0.8f;
        float runSpeed = 1.0f;
        float runDegree = 0.8f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        float moveProgress = 5.0f * limbSwingAmount;
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float sitProgress = entity.prevSitProgress + (entity.sitProgress - entity.prevSitProgress) * partialTick;
        float holdProgress = entity.prevHoldProgress + (entity.holdProgress - entity.prevHoldProgress) * partialTick;
        float sleepProgress = entity.prevSleepProgress + (entity.sleepProgress - entity.prevSleepProgress) * partialTick;
        boolean leftSleep = entity.m_19879_() % 2 == 0;
        this.walk(this.tail, idleSpeed, idleDegree * 1.0f, false, -2.0f, 0.1f, ageInTicks, 1.0f);
        this.flap(this.tail, idleSpeed, idleDegree * 1.2f, false, 2.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.tail2, idleSpeed, idleDegree * 2.0f, false, 2.0f, 0.0f, ageInTicks, 1.0f);
        AdvancedModelBox[] tailBoxes = new AdvancedModelBox[]{this.tail, this.tail2};
        this.progressRotationPrev(this.tail, moveProgress, Maths.rad(40.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, Math.min(moveProgress * 2.0f, 5.0f), 0.0f, 2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.tail, moveProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, moveProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.armleft, moveProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.armright, moveProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.legleft, moveProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.legright, moveProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        if (entity.isRunning()) {
            this.chainFlap(tailBoxes, runSpeed, runDegree * 0.5f, -1.0, limbSwing, limbSwingAmount);
            this.bob(this.body, runSpeed, runDegree * 2.0f, false, limbSwing, limbSwingAmount);
            this.bob(this.head, runSpeed, runDegree * -1.0f, false, limbSwing, limbSwingAmount);
            this.walk(this.armleft, runSpeed, runDegree * 0.75f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.armright, runSpeed, runDegree * 0.75f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.legright, runSpeed, runDegree * 1.0f, false, 0.5f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.legleft, runSpeed, runDegree * 1.0f, false, 0.5f, 0.0f, limbSwing, limbSwingAmount);
        } else {
            this.chainFlap(tailBoxes, walkSpeed, walkDegree * 1.0f, -1.0, limbSwing, limbSwingAmount);
            this.flap(this.body, walkSpeed, walkDegree * 0.3f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.head, walkSpeed, -walkDegree * 0.3f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.armleft, walkSpeed, -walkDegree * 0.1f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.armright, walkSpeed, -walkDegree * 0.1f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.legleft, walkSpeed, -walkDegree * 0.3f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.legright, walkSpeed, -walkDegree * 0.3f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.armright, walkSpeed, walkDegree * 0.5f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.bob(this.armright, walkSpeed, -walkDegree, false, limbSwing, limbSwingAmount);
            this.walk(this.armleft, walkSpeed, walkDegree * 0.5f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.bob(this.armleft, walkSpeed, -walkDegree, false, limbSwing, limbSwingAmount);
            this.walk(this.legright, walkSpeed, walkDegree * 0.8f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.bob(this.legright, walkSpeed, -walkDegree, false, limbSwing, limbSwingAmount);
            this.walk(this.legleft, walkSpeed, walkDegree * 0.8f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.bob(this.legleft, walkSpeed, -walkDegree, false, limbSwing, limbSwingAmount);
            this.bob(this.body, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        }
        this.progressRotationPrev(this.legleft, sitProgress, Maths.rad(-90.0), Maths.rad(-20.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.legright, sitProgress, Maths.rad(-90.0), Maths.rad(20.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.armleft, sitProgress, Maths.rad(-50.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.armright, sitProgress, Maths.rad(-50.0), 0.0f, 0.0f, 5.0f);
        float tailAngle = entity.m_19879_() % 2 == 0 ? 1.0f : -1.0f;
        this.progressRotationPrev(this.tail, sitProgress, Maths.rad(20.0), Maths.rad(tailAngle * -15.0f), Maths.rad(tailAngle * 15.0f), 5.0f);
        this.progressRotationPrev(this.tail2, sitProgress, Maths.rad(20.0), Maths.rad(tailAngle * -30.0f), Maths.rad(tailAngle * 30.0f), 5.0f);
        this.progressPositionPrev(this.body, sitProgress, 0.0f, 5.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.tail, sitProgress, 0.0f, 2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.tail2, sitProgress, tailAngle, 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.armright, sitProgress, 0.0f, -1.0f, 4.0f, 5.0f);
        this.progressPositionPrev(this.armleft, sitProgress, 0.0f, -1.0f, 4.0f, 5.0f);
        this.progressPositionPrev(this.legright, sitProgress, 0.0f, 2.8f, -0.5f, 5.0f);
        this.progressPositionPrev(this.legleft, sitProgress, 0.0f, 2.8f, -0.5f, 5.0f);
        if (leftSleep) {
            this.progressRotationPrev(this.body, sleepProgress, 0.0f, 0.0f, Maths.rad(-90.0), 5.0f);
            this.progressRotationPrev(this.head, sleepProgress, 0.0f, 0.0f, Maths.rad(73.0), 5.0f);
            this.progressRotationPrev(this.tail, sleepProgress, 0.0f, 0.0f, Maths.rad(20.0), 5.0f);
            this.progressRotationPrev(this.tail2, sleepProgress, 0.0f, 0.0f, Maths.rad(-20.0), 5.0f);
            this.progressRotationPrev(this.armleft, sleepProgress, Maths.rad(-10.0), 0.0f, Maths.rad(10.0), 5.0f);
            this.progressRotationPrev(this.armright, sleepProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
            this.progressRotationPrev(this.legright, sleepProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
            this.progressRotationPrev(this.legleft, sleepProgress, Maths.rad(10.0), 0.0f, Maths.rad(20.0), 5.0f);
            this.progressPositionPrev(this.armleft, sleepProgress, 1.0f, -1.0f, 0.0f, 5.0f);
            this.progressPositionPrev(this.armright, sleepProgress, 0.5f, -1.0f, 1.0f, 5.0f);
            this.progressPositionPrev(this.body, sleepProgress, 0.0f, 9.0f, 0.0f, 5.0f);
            this.progressPositionPrev(this.head, sleepProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        } else {
            this.progressRotationPrev(this.body, sleepProgress, 0.0f, 0.0f, Maths.rad(90.0), 5.0f);
            this.progressRotationPrev(this.head, sleepProgress, 0.0f, 0.0f, Maths.rad(-73.0), 5.0f);
            this.progressRotationPrev(this.tail, sleepProgress, 0.0f, 0.0f, Maths.rad(-20.0), 5.0f);
            this.progressRotationPrev(this.tail2, sleepProgress, 0.0f, 0.0f, Maths.rad(20.0), 5.0f);
            this.progressRotationPrev(this.armright, sleepProgress, Maths.rad(-10.0), 0.0f, Maths.rad(-10.0), 5.0f);
            this.progressRotationPrev(this.armleft, sleepProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
            this.progressRotationPrev(this.legleft, sleepProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
            this.progressRotationPrev(this.legright, sleepProgress, Maths.rad(10.0), 0.0f, Maths.rad(-20.0), 5.0f);
            this.progressPositionPrev(this.armright, sleepProgress, -1.0f, -1.0f, 0.0f, 5.0f);
            this.progressPositionPrev(this.armleft, sleepProgress, -0.5f, -1.0f, 1.0f, 5.0f);
            this.progressPositionPrev(this.body, sleepProgress, 0.0f, 9.0f, 0.0f, 5.0f);
            this.progressPositionPrev(this.head, sleepProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        }
        this.progressRotationPrev(this.body, holdProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, holdProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legleft, holdProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legright, holdProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.armright, holdProgress, Maths.rad(-60.0), Maths.rad(-5.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.armleft, holdProgress, Maths.rad(-60.0), Maths.rad(5.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.body, holdProgress, 0.0f, 3.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, holdProgress, 0.0f, -1.0f, 1.0f, 5.0f);
        this.progressPositionPrev(this.armleft, holdProgress, 2.0f, -2.0f, -1.0f, 5.0f);
        this.progressPositionPrev(this.armright, holdProgress, -2.0f, -2.0f, -1.0f, 5.0f);
        this.flap(this.head, 0.85f, 0.3f, false, 0.0f, 0.0f, ageInTicks, holdProgress * 0.2f);
        this.flap(this.tail, 0.85f, 0.3f, false, 0.0f, 0.0f, ageInTicks, holdProgress * 0.2f);
        this.flap(this.tail2, 0.85f, 0.3f, false, 0.0f, 0.0f, ageInTicks, holdProgress * 0.2f);
        this.flap(this.earleft, 0.85f, 0.3f, false, -1.0f, 0.0f, ageInTicks, holdProgress * 0.2f);
        this.flap(this.earright, 0.85f, 0.3f, false, -1.0f, 0.0f, ageInTicks, holdProgress * 0.2f);
        if (sleepProgress == 0.0f) {
            this.faceTarget(netHeadYaw, headPitch, 1.2f, new AdvancedModelBox[]{this.head});
        }
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.head, (Object)this.tail, (Object)this.tail2, (Object)this.snout, (Object)this.earleft, (Object)this.earright, (Object)this.legleft, (Object)this.legright, (Object)this.armleft, (Object)this.armright, (Object[])new AdvancedModelBox[0]);
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.5f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.0);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
