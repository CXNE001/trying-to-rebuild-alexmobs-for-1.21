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

import com.github.alexthe666.alexsmobs.entity.EntityWarpedMosco;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelWarpedMosco
extends AdvancedEntityModel<EntityWarpedMosco> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox back;
    private final AdvancedModelBox legfront_left;
    private final AdvancedModelBox kneefront_left;
    private final AdvancedModelBox legfront_right;
    private final AdvancedModelBox kneefront_right;
    private final AdvancedModelBox legback_left;
    private final AdvancedModelBox kneeback_left;
    private final AdvancedModelBox legback_right;
    private final AdvancedModelBox kneeback_right;
    private final AdvancedModelBox chest;
    private final AdvancedModelBox wingtop_left;
    private final AdvancedModelBox wingtop_right;
    private final AdvancedModelBox wingbottom_left;
    private final AdvancedModelBox wingbottom_left_r1;
    private final AdvancedModelBox wingbottom_right;
    private final AdvancedModelBox wingbottom_right_r1;
    private final AdvancedModelBox shoulder_left;
    private final AdvancedModelBox shoulderspikes_left;
    private final AdvancedModelBox arm_left;
    private final AdvancedModelBox hand_left;
    private final AdvancedModelBox shoulder_right;
    private final AdvancedModelBox shoulderspikes_right;
    private final AdvancedModelBox arm_right;
    private final AdvancedModelBox hand_right;
    private final AdvancedModelBox head;
    private final AdvancedModelBox antenna_left;
    private final AdvancedModelBox antenna_right;
    private final AdvancedModelBox proboscis;
    private final AdvancedModelBox proboscis_r1;
    private ModelAnimator animator;

    public ModelWarpedMosco() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -24.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 31).addBox(-8.0f, -10.0f, -7.0f, 16.0f, 14.0f, 14.0f, 0.0f, false);
        this.back = new AdvancedModelBox((AdvancedEntityModel)this, "back");
        this.back.setPos(0.0f, -4.0f, 7.0f);
        this.body.addChild((BasicModelPart)this.back);
        this.setRotationAngle(this.back, -0.4363f, 0.0f, 0.0f);
        this.back.setTextureOffset(44, 44).addBox(-5.0f, -1.0f, 0.0f, 10.0f, 10.0f, 17.0f, 0.0f, false);
        this.legfront_left = new AdvancedModelBox((AdvancedEntityModel)this, "legfront_left");
        this.legfront_left.setPos(5.5f, 2.0f, -4.5f);
        this.body.addChild((BasicModelPart)this.legfront_left);
        this.setRotationAngle(this.legfront_left, 0.0f, -0.5236f, 0.0f);
        this.legfront_left.setTextureOffset(72, 86).addBox(-3.5f, -2.0f, -3.5f, 7.0f, 12.0f, 7.0f, 0.0f, false);
        this.kneefront_left = new AdvancedModelBox((AdvancedEntityModel)this, "kneefront_left");
        this.kneefront_left.setPos(0.0f, 10.0f, 0.0f);
        this.legfront_left.addChild((BasicModelPart)this.kneefront_left);
        this.kneefront_left.setTextureOffset(101, 81).addBox(-2.5f, 0.0f, -2.5f, 5.0f, 12.0f, 5.0f, 0.0f, false);
        this.legfront_right = new AdvancedModelBox((AdvancedEntityModel)this, "legfront_right");
        this.legfront_right.setPos(-5.5f, 2.0f, -4.5f);
        this.body.addChild((BasicModelPart)this.legfront_right);
        this.setRotationAngle(this.legfront_right, 0.0f, 0.5236f, 0.0f);
        this.legfront_right.setTextureOffset(72, 86).addBox(-3.5f, -2.0f, -3.5f, 7.0f, 12.0f, 7.0f, 0.0f, true);
        this.kneefront_right = new AdvancedModelBox((AdvancedEntityModel)this, "kneefront_right");
        this.kneefront_right.setPos(0.0f, 10.0f, 0.0f);
        this.legfront_right.addChild((BasicModelPart)this.kneefront_right);
        this.kneefront_right.setTextureOffset(101, 81).addBox(-2.5f, 0.0f, -2.5f, 5.0f, 12.0f, 5.0f, 0.0f, true);
        this.legback_left = new AdvancedModelBox((AdvancedEntityModel)this, "legback_left");
        this.legback_left.setPos(5.5f, 2.0f, 5.5f);
        this.body.addChild((BasicModelPart)this.legback_left);
        this.setRotationAngle(this.legback_left, 0.0f, 0.6981f, 0.0f);
        this.legback_left.setTextureOffset(72, 86).addBox(-3.5f, -2.0f, -3.5f, 7.0f, 12.0f, 7.0f, 0.0f, false);
        this.kneeback_left = new AdvancedModelBox((AdvancedEntityModel)this, "kneeback_left");
        this.kneeback_left.setPos(0.0f, 10.0f, 0.0f);
        this.legback_left.addChild((BasicModelPart)this.kneeback_left);
        this.kneeback_left.setTextureOffset(101, 81).addBox(-2.5f, 0.0f, -2.5f, 5.0f, 12.0f, 5.0f, 0.0f, false);
        this.legback_right = new AdvancedModelBox((AdvancedEntityModel)this, "legback_right");
        this.legback_right.setPos(-5.5f, 2.0f, 5.5f);
        this.body.addChild((BasicModelPart)this.legback_right);
        this.setRotationAngle(this.legback_right, 0.0f, -0.6981f, 0.0f);
        this.legback_right.setTextureOffset(72, 86).addBox(-3.5f, -2.0f, -3.5f, 7.0f, 12.0f, 7.0f, 0.0f, true);
        this.kneeback_right = new AdvancedModelBox((AdvancedEntityModel)this, "kneeback_right");
        this.kneeback_right.setPos(0.0f, 10.0f, 0.0f);
        this.legback_right.addChild((BasicModelPart)this.kneeback_right);
        this.kneeback_right.setTextureOffset(101, 81).addBox(-2.5f, 0.0f, -2.5f, 5.0f, 12.0f, 5.0f, 0.0f, true);
        this.chest = new AdvancedModelBox((AdvancedEntityModel)this, "chest");
        this.chest.setPos(0.0f, -10.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.chest);
        this.chest.setTextureOffset(0, 0).addBox(-12.0f, -14.0f, -8.0f, 24.0f, 14.0f, 16.0f, 0.0f, false);
        this.wingtop_left = new AdvancedModelBox((AdvancedEntityModel)this, "wingtop_left");
        this.wingtop_left.setPos(0.5f, -7.0f, 8.0f);
        this.chest.addChild((BasicModelPart)this.wingtop_left);
        this.setRotationAngle(this.wingtop_left, 0.0f, -0.0873f, -0.2182f);
        this.wingtop_left.setTextureOffset(24, 109).addBox(0.0f, -11.0f, 0.0f, 33.0f, 19.0f, 0.0f, 0.0f, false);
        this.wingtop_right = new AdvancedModelBox((AdvancedEntityModel)this, "wingtop_right");
        this.wingtop_right.setPos(-0.5f, -7.0f, 8.0f);
        this.chest.addChild((BasicModelPart)this.wingtop_right);
        this.setRotationAngle(this.wingtop_right, 0.0f, 0.0873f, 0.2182f);
        this.wingtop_right.setTextureOffset(24, 109).addBox(-33.0f, -11.0f, 0.0f, 33.0f, 19.0f, 0.0f, 0.0f, true);
        this.wingbottom_left = new AdvancedModelBox((AdvancedEntityModel)this, "wingbottom_left");
        this.wingbottom_left.setPos(0.5f, -6.0f, 8.0f);
        this.chest.addChild((BasicModelPart)this.wingbottom_left);
        this.setRotationAngle(this.wingbottom_left, 0.0f, -0.0873f, -0.2182f);
        this.wingbottom_left_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "wingbottom_left_r1");
        this.wingbottom_left_r1.setPos(0.0f, 0.0f, 0.0f);
        this.wingbottom_left.addChild((BasicModelPart)this.wingbottom_left_r1);
        this.setRotationAngle(this.wingbottom_left_r1, 0.0436f, 0.0f, 0.829f);
        this.wingbottom_left_r1.setTextureOffset(24, 109).addBox(0.0f, -11.0f, 0.0f, 33.0f, 19.0f, 0.0f, 0.0f, false);
        this.wingbottom_right = new AdvancedModelBox((AdvancedEntityModel)this, "wingbottom_right");
        this.wingbottom_right.setPos(-0.5f, -6.0f, 8.0f);
        this.chest.addChild((BasicModelPart)this.wingbottom_right);
        this.setRotationAngle(this.wingbottom_right, 0.0f, 0.0873f, 0.2182f);
        this.wingbottom_right_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "wingbottom_right_r1");
        this.wingbottom_right_r1.setPos(0.0f, 0.0f, 0.0f);
        this.wingbottom_right.addChild((BasicModelPart)this.wingbottom_right_r1);
        this.setRotationAngle(this.wingbottom_right_r1, 0.0436f, 0.0f, -0.829f);
        this.wingbottom_right_r1.setTextureOffset(24, 109).addBox(-33.0f, -11.0f, 0.0f, 33.0f, 19.0f, 0.0f, 0.0f, true);
        this.shoulder_left = new AdvancedModelBox((AdvancedEntityModel)this, "shoulder_left");
        this.shoulder_left.setPos(16.0f, -11.0f, 0.0f);
        this.chest.addChild((BasicModelPart)this.shoulder_left);
        this.shoulder_left.setTextureOffset(0, 60).addBox(-4.0f, -6.0f, -6.0f, 12.0f, 12.0f, 12.0f, 0.0f, false);
        this.shoulderspikes_left = new AdvancedModelBox((AdvancedEntityModel)this, "shoulderspikes_left");
        this.shoulderspikes_left.setPos(6.5f, -4.0f, 0.0f);
        this.shoulder_left.addChild((BasicModelPart)this.shoulderspikes_left);
        this.shoulderspikes_left.setTextureOffset(101, 101).addBox(-6.5f, -8.0f, 0.0f, 13.0f, 16.0f, 0.0f, 0.0f, false);
        this.arm_left = new AdvancedModelBox((AdvancedEntityModel)this, "arm_left");
        this.arm_left.setPos(1.1f, 6.0f, 2.0f);
        this.shoulder_left.addChild((BasicModelPart)this.arm_left);
        this.arm_left.setTextureOffset(71, 21).addBox(-5.0f, 0.0f, -7.0f, 10.0f, 11.0f, 10.0f, 0.0f, false);
        this.hand_left = new AdvancedModelBox((AdvancedEntityModel)this, "hand_left");
        this.hand_left.setPos(0.0f, 11.0f, 0.0f);
        this.arm_left.addChild((BasicModelPart)this.hand_left);
        this.hand_left.setTextureOffset(0, 85).addBox(-4.0f, 0.0f, -7.0f, 8.0f, 12.0f, 10.0f, 0.0f, false);
        this.shoulder_right = new AdvancedModelBox((AdvancedEntityModel)this, "shoulder_right");
        this.shoulder_right.setPos(-16.0f, -11.0f, 0.0f);
        this.chest.addChild((BasicModelPart)this.shoulder_right);
        this.shoulder_right.setTextureOffset(0, 60).addBox(-8.0f, -6.0f, -6.0f, 12.0f, 12.0f, 12.0f, 0.0f, true);
        this.shoulderspikes_right = new AdvancedModelBox((AdvancedEntityModel)this, "shoulderspikes_right");
        this.shoulderspikes_right.setPos(-6.5f, -4.0f, 0.0f);
        this.shoulder_right.addChild((BasicModelPart)this.shoulderspikes_right);
        this.shoulderspikes_right.setTextureOffset(101, 101).addBox(-6.5f, -8.0f, 0.0f, 13.0f, 16.0f, 0.0f, 0.0f, true);
        this.arm_right = new AdvancedModelBox((AdvancedEntityModel)this, "arm_right");
        this.arm_right.setPos(-1.2f, 6.0f, 2.0f);
        this.shoulder_right.addChild((BasicModelPart)this.arm_right);
        this.arm_right.setTextureOffset(71, 21).addBox(-5.0f, 0.0f, -7.0f, 10.0f, 11.0f, 10.0f, 0.0f, true);
        this.hand_right = new AdvancedModelBox((AdvancedEntityModel)this, "hand_right");
        this.hand_right.setPos(0.0f, 11.0f, 0.0f);
        this.arm_right.addChild((BasicModelPart)this.hand_right);
        this.hand_right.setTextureOffset(0, 85).addBox(-4.0f, 0.0f, -7.0f, 8.0f, 12.0f, 10.0f, 0.0f, true);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -14.0f, -1.0f);
        this.chest.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(82, 43).addBox(-5.0f, -5.0f, -5.0f, 10.0f, 5.0f, 10.0f, 0.0f, false);
        this.antenna_left = new AdvancedModelBox((AdvancedEntityModel)this, "antenna_left");
        this.antenna_left.setPos(2.0f, -5.0f, -5.0f);
        this.head.addChild((BasicModelPart)this.antenna_left);
        this.setRotationAngle(this.antenna_left, -0.829f, 0.0f, 0.0f);
        this.antenna_left.setTextureOffset(102, 59).addBox(-1.0f, -20.0f, 0.0f, 6.0f, 20.0f, 0.0f, 0.0f, false);
        this.antenna_right = new AdvancedModelBox((AdvancedEntityModel)this, "antenna_right");
        this.antenna_right.setPos(-2.0f, -5.0f, -5.0f);
        this.head.addChild((BasicModelPart)this.antenna_right);
        this.setRotationAngle(this.antenna_right, -0.829f, 0.0f, 0.0f);
        this.antenna_right.setTextureOffset(102, 59).addBox(-5.0f, -20.0f, 0.0f, 6.0f, 20.0f, 0.0f, 0.0f, true);
        this.proboscis = new AdvancedModelBox((AdvancedEntityModel)this, "proboscis");
        this.proboscis.setPos(0.0f, -1.0f, -6.0f);
        this.head.addChild((BasicModelPart)this.proboscis);
        this.proboscis_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "proboscis_r1");
        this.proboscis_r1.setPos(0.0f, -1.0f, 1.0f);
        this.proboscis.addChild((BasicModelPart)this.proboscis_r1);
        this.setRotationAngle(this.proboscis_r1, 0.3054f, 0.0f, 0.0f);
        this.proboscis_r1.setTextureOffset(37, 86).addBox(-1.0f, 0.0f, -15.0f, 2.0f, 1.0f, 15.0f, 0.0f, false);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityWarpedMosco.ANIMATION_PUNCH_R);
        this.animator.startKeyframe(10);
        this.animator.rotate(this.chest, 0.0f, Maths.rad(40.0), 0.0f);
        this.animator.rotate(this.shoulder_right, Maths.rad(40.0), Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-80.0), 0.0f, 0.0f);
        this.animator.rotate(this.hand_right, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.shoulder_left, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.body, 0.0f, Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.chest, 0.0f, Maths.rad(-40.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, Maths.rad(30.0), 0.0f);
        this.animator.rotate(this.shoulder_right, Maths.rad(-20.0), Maths.rad(20.0), Maths.rad(20.0));
        this.animator.rotate(this.arm_right, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.hand_right, Maths.rad(-30.0), Maths.rad(-30.0), 0.0f);
        this.animator.rotate(this.shoulder_left, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityWarpedMosco.ANIMATION_PUNCH_L);
        this.animator.startKeyframe(10);
        this.animator.rotate(this.chest, 0.0f, Maths.rad(-40.0), 0.0f);
        this.animator.rotate(this.shoulder_left, Maths.rad(40.0), Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(-80.0), 0.0f, 0.0f);
        this.animator.rotate(this.hand_right, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.shoulder_right, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.body, 0.0f, Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.chest, 0.0f, Maths.rad(40.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, Maths.rad(-30.0), 0.0f);
        this.animator.rotate(this.shoulder_left, Maths.rad(-20.0), Maths.rad(-20.0), Maths.rad(-20.0));
        this.animator.rotate(this.arm_left, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.hand_left, Maths.rad(-30.0), Maths.rad(30.0), 0.0f);
        this.animator.rotate(this.shoulder_right, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityWarpedMosco.ANIMATION_SLAM);
        this.animator.startKeyframe(10);
        this.animator.rotate(this.chest, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.shoulder_left, Maths.rad(-160.0), Maths.rad(20.0), Maths.rad(-10.0));
        this.animator.rotate(this.shoulder_right, Maths.rad(-160.0), Maths.rad(-20.0), Maths.rad(10.0));
        this.animator.rotate(this.arm_right, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.chest, Maths.rad(60.0), 0.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(40.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-60.0), 0.0f, 0.0f);
        this.animator.rotate(this.legfront_left, Maths.rad(-40.0), 0.0f, 0.0f);
        this.animator.rotate(this.legfront_right, Maths.rad(-40.0), 0.0f, 0.0f);
        this.animator.rotate(this.legback_left, Maths.rad(-40.0), 0.0f, Maths.rad(-30.0));
        this.animator.rotate(this.legback_right, Maths.rad(-40.0), 0.0f, Maths.rad(30.0));
        this.animator.rotate(this.shoulder_left, Maths.rad(-100.0), Maths.rad(20.0), Maths.rad(-10.0));
        this.animator.rotate(this.shoulder_right, Maths.rad(-100.0), Maths.rad(-20.0), Maths.rad(10.0));
        this.animator.rotate(this.arm_right, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.move(this.legfront_left, 0.0f, -3.0f, 0.0f);
        this.animator.move(this.legfront_right, 0.0f, -3.0f, 0.0f);
        this.animator.move(this.legback_left, 0.0f, 6.0f, 0.0f);
        this.animator.move(this.legback_right, 0.0f, 6.0f, 0.0f);
        this.animator.move(this.shoulder_left, 0.0f, -3.0f, 0.0f);
        this.animator.move(this.shoulder_right, 0.0f, -3.0f, 0.0f);
        this.animator.move(this.body, 0.0f, 0.0f, 2.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.resetKeyframe(10);
        this.animator.setAnimation(EntityWarpedMosco.ANIMATION_SUCK);
        this.animator.startKeyframe(10);
        this.animator.rotate(this.chest, Maths.rad(60.0), 0.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(40.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-60.0), 0.0f, 0.0f);
        this.animator.rotate(this.legfront_left, Maths.rad(-40.0), 0.0f, 0.0f);
        this.animator.rotate(this.legfront_right, Maths.rad(-40.0), 0.0f, 0.0f);
        this.animator.rotate(this.legback_left, Maths.rad(-40.0), 0.0f, Maths.rad(-30.0));
        this.animator.rotate(this.legback_right, Maths.rad(-40.0), 0.0f, Maths.rad(30.0));
        this.animator.rotate(this.shoulder_left, Maths.rad(-100.0), Maths.rad(20.0), Maths.rad(-10.0));
        this.animator.rotate(this.shoulder_right, Maths.rad(-100.0), Maths.rad(-20.0), Maths.rad(10.0));
        this.animator.rotate(this.arm_right, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.move(this.legfront_left, 0.0f, -3.0f, 0.0f);
        this.animator.move(this.legfront_right, 0.0f, -3.0f, 0.0f);
        this.animator.move(this.legback_left, 0.0f, 6.0f, 0.0f);
        this.animator.move(this.legback_right, 0.0f, 6.0f, 0.0f);
        this.animator.move(this.shoulder_left, 0.0f, -3.0f, 0.0f);
        this.animator.move(this.shoulder_right, 0.0f, -3.0f, 0.0f);
        this.animator.move(this.body, 0.0f, 0.0f, 2.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(15);
        this.suckPose();
        this.animator.endKeyframe();
        for (int i = 0; i < 5; ++i) {
            this.animator.startKeyframe(5);
            this.suckPose();
            this.animator.move(this.proboscis, 0.0f, i % 2 == 0 ? -1.0f : 0.0f, i % 2 == 0 ? 4.0f : 0.0f);
            this.animator.endKeyframe();
        }
        this.animator.resetKeyframe(10);
        this.animator.setAnimation(EntityWarpedMosco.ANIMATION_SPIT);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.chest, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.chest, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.chest, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.chest, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.chest, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.chest, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.chest, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.chest, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(10);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.back, (Object)this.legfront_left, (Object)this.kneefront_left, (Object)this.legfront_right, (Object)this.kneefront_right, (Object)this.legback_left, (Object)this.kneeback_left, (Object)this.legback_right, (Object)this.kneeback_right, (Object)this.chest, (Object[])new AdvancedModelBox[]{this.wingtop_left, this.wingtop_right, this.wingbottom_left, this.wingbottom_left_r1, this.wingbottom_right, this.wingbottom_right_r1, this.shoulder_left, this.shoulderspikes_left, this.arm_left, this.hand_left, this.shoulder_right, this.shoulderspikes_right, this.arm_right, this.hand_right, this.head, this.antenna_left, this.antenna_right, this.proboscis, this.proboscis_r1});
    }

    private void suckPose() {
        this.animator.rotate(this.chest, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-60.0), 0.0f, 0.0f);
        this.animator.rotate(this.shoulder_left, Maths.rad(-100.0), Maths.rad(20.0), Maths.rad(-10.0));
        this.animator.rotate(this.shoulder_right, Maths.rad(-100.0), Maths.rad(-20.0), Maths.rad(10.0));
        this.animator.rotate(this.arm_right, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(-20.0), 0.0f, 0.0f);
    }

    public void setupAnim(EntityWarpedMosco entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float flySpeed = 0.5f;
        float flyDegree = 0.5f;
        float walkSpeed = 0.5f;
        float walkDegree = 0.6f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        float partialTicks = ageInTicks - (float)entity.f_19797_;
        float flyLeftProgress = entity.prevLeftFlyProgress + (entity.flyLeftProgress - entity.prevLeftFlyProgress) * partialTicks;
        float flyRightProgress = entity.prevFlyRightProgress + (entity.flyRightProgress - entity.prevFlyRightProgress) * partialTicks;
        float flyProgress = Math.max(flyLeftProgress, flyRightProgress);
        float walkProgress = (5.0f - flyProgress) * limbSwingAmount;
        this.walk(this.antenna_left, idleSpeed, idleDegree, false, 0.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.antenna_right, idleSpeed, idleDegree, false, 0.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.shoulder_left, idleSpeed, idleDegree, false, 0.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.shoulder_right, idleSpeed, idleDegree, false, 0.0f, -0.1f, ageInTicks, 1.0f);
        this.flap(this.shoulder_left, idleSpeed, idleDegree * 0.5f, false, 0.0f, -0.1f, ageInTicks, 1.0f);
        this.flap(this.shoulder_right, idleSpeed, idleDegree * 0.5f, true, 0.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.hand_left, idleSpeed, idleDegree, false, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.hand_right, idleSpeed, idleDegree, false, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.head, idleSpeed, idleDegree * 0.25f, false, -1.0f, 0.05f, ageInTicks, 1.0f);
        this.walk(this.chest, idleSpeed, idleDegree * 0.15f, false, -2.0f, 0.05f, ageInTicks, 1.0f);
        this.bob(this.body, idleSpeed, idleDegree * 5.0f, false, ageInTicks, 1.0f);
        this.walk(this.legfront_right, idleSpeed, idleDegree, false, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.kneefront_right, idleSpeed, idleDegree, true, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.legfront_left, idleSpeed, idleDegree, false, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.kneefront_left, idleSpeed, idleDegree, true, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.legback_right, idleSpeed, idleDegree, true, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.kneeback_right, idleSpeed, idleDegree, false, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.legback_left, idleSpeed, idleDegree, true, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.kneeback_left, idleSpeed, idleDegree, false, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.progressRotationPrev(this.chest, walkProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.wingbottom_left, walkProgress, 0.0f, Maths.rad(-50.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.wingbottom_right, walkProgress, 0.0f, Maths.rad(50.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.wingtop_left, walkProgress, 0.0f, Maths.rad(-50.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.wingtop_right, walkProgress, 0.0f, Maths.rad(50.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.head, walkProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.back, walkProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, flyProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, flyProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legback_left, flyProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legback_right, flyProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legfront_left, flyProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legfront_right, flyProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.chest, flyLeftProgress, 0.0f, Maths.rad(30.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.head, flyLeftProgress, 0.0f, Maths.rad(-30.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.shoulder_left, flyLeftProgress, Maths.rad(-30.0), Maths.rad(-30.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_left, flyLeftProgress, Maths.rad(-40.0), Maths.rad(10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.hand_left, flyLeftProgress, Maths.rad(-30.0), Maths.rad(60.0), Maths.rad(20.0), 5.0f);
        this.progressRotationPrev(this.chest, flyRightProgress, 0.0f, Maths.rad(-30.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.head, flyRightProgress, 0.0f, Maths.rad(30.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.shoulder_right, flyRightProgress, Maths.rad(-30.0), Maths.rad(30.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_right, flyRightProgress, Maths.rad(-40.0), Maths.rad(-10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.hand_right, flyRightProgress, Maths.rad(-30.0), Maths.rad(-60.0), Maths.rad(-20.0), 5.0f);
        if (flyProgress <= 0.0f) {
            this.walk(this.kneefront_left, walkSpeed, walkDegree * 0.4f, true, 0.0f, -0.1f, limbSwing, limbSwingAmount);
            this.walk(this.legfront_left, walkSpeed, walkDegree * 0.8f, false, 2.0f, -0.3f, limbSwing, limbSwingAmount);
            this.walk(this.kneefront_right, walkSpeed, walkDegree * 0.4f, false, 0.0f, 0.1f, limbSwing, limbSwingAmount);
            this.walk(this.legfront_right, walkSpeed, walkDegree * 0.8f, true, 2.0f, 0.3f, limbSwing, limbSwingAmount);
            this.walk(this.kneeback_left, walkSpeed, walkDegree * 0.4f, false, 0.0f, -0.1f, limbSwing, limbSwingAmount);
            this.walk(this.legback_left, walkSpeed, walkDegree * 0.8f, true, 2.0f, -0.3f, limbSwing, limbSwingAmount);
            this.walk(this.kneeback_right, walkSpeed, walkDegree * 0.4f, true, 0.0f, 0.1f, limbSwing, limbSwingAmount);
            this.walk(this.legback_right, walkSpeed, walkDegree * 0.8f, false, 2.0f, 0.3f, limbSwing, limbSwingAmount);
            this.swing(this.chest, walkSpeed, walkDegree * 0.3f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.shoulder_left, walkSpeed, walkDegree * 0.6f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.shoulder_right, walkSpeed, walkDegree * 0.6f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.arm_left, walkSpeed, walkDegree * 0.6f, true, 0.6f, 0.9f, limbSwing, limbSwingAmount);
            this.walk(this.arm_right, walkSpeed, walkDegree * 0.6f, false, 0.6f, -0.9f, limbSwing, limbSwingAmount);
            this.walk(this.hand_left, walkSpeed, walkDegree * 0.6f, true, 0.6f, 0.3f, limbSwing, limbSwingAmount);
            this.walk(this.hand_right, walkSpeed, walkDegree * 0.6f, false, 0.6f, -0.3f, limbSwing, limbSwingAmount);
            this.swing(this.legfront_left, walkSpeed, walkDegree * 0.4f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.legfront_right, walkSpeed, walkDegree * 0.4f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.legback_left, walkSpeed, walkDegree * 0.4f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.legback_right, walkSpeed, walkDegree * 0.4f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.bob(this.body, walkSpeed, walkDegree * 5.0f, false, limbSwing, limbSwingAmount);
        } else {
            this.swing(this.wingbottom_left, flySpeed * 3.3f, flyDegree, true, 0.0f, 0.2f, ageInTicks, 1.0f);
            this.swing(this.wingbottom_right, flySpeed * 3.3f, flyDegree, false, 0.0f, 0.2f, ageInTicks, 1.0f);
            this.swing(this.wingtop_left, flySpeed * 3.3f, flyDegree * 1.3f, true, 1.0f, 0.5f, ageInTicks, 1.0f);
            this.swing(this.wingtop_right, flySpeed * 3.3f, flyDegree * 1.3f, false, 1.0f, 0.5f, ageInTicks, 1.0f);
            this.bob(this.body, flySpeed, flyDegree * 5.0f, false, ageInTicks, 1.0f);
        }
        if (entity.getAnimation() != EntityWarpedMosco.ANIMATION_SUCK) {
            this.head.rotateAngleY += netHeadYaw * 0.6f * ((float)Math.PI / 180);
            this.head.rotateAngleX += headPitch * 0.9f * ((float)Math.PI / 180);
        }
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
