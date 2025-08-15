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

import com.github.alexthe666.alexsmobs.entity.EntityDropBear;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelDropBear
extends AdvancedEntityModel<EntityDropBear> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox leg_left;
    private final AdvancedModelBox leg_right;
    private final AdvancedModelBox front_body;
    private final AdvancedModelBox head;
    private final AdvancedModelBox nose;
    private final AdvancedModelBox ear_left;
    private final AdvancedModelBox ear_right;
    private final AdvancedModelBox jaw;
    private final AdvancedModelBox arm_left;
    private final AdvancedModelBox claws_left;
    private final AdvancedModelBox arm_right;
    private final AdvancedModelBox claws_right;
    private final ModelAnimator animator;

    public ModelDropBear() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -13.0f, 8.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 31).addBox(-6.0f, -8.0f, -7.0f, 12.0f, 13.0f, 13.0f, 0.0f, false);
        this.leg_left = new AdvancedModelBox((AdvancedEntityModel)this, "leg_left");
        this.leg_left.setPos(3.4f, 5.0f, 2.5f);
        this.body.addChild((BasicModelPart)this.leg_left);
        this.leg_left.setTextureOffset(0, 58).addBox(-2.5f, 0.0f, -2.5f, 5.0f, 8.0f, 5.0f, 0.0f, false);
        this.leg_right = new AdvancedModelBox((AdvancedEntityModel)this, "leg_right");
        this.leg_right.setPos(-3.4f, 5.0f, 2.5f);
        this.body.addChild((BasicModelPart)this.leg_right);
        this.leg_right.setTextureOffset(0, 58).addBox(-2.5f, 0.0f, -2.5f, 5.0f, 8.0f, 5.0f, 0.0f, true);
        this.front_body = new AdvancedModelBox((AdvancedEntityModel)this, "front_body");
        this.front_body.setPos(0.0f, -2.0f, -7.0f);
        this.body.addChild((BasicModelPart)this.front_body);
        this.front_body.setTextureOffset(0, 0).addBox(-8.0f, -8.0f, -14.0f, 16.0f, 16.0f, 14.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -1.0f, -14.0f);
        this.front_body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(42, 49).addBox(-5.0f, -5.0f, -9.0f, 10.0f, 8.0f, 9.0f, 0.0f, false);
        this.nose = new AdvancedModelBox((AdvancedEntityModel)this, "nose");
        this.nose.setPos(0.0f, -0.5f, -9.5f);
        this.head.addChild((BasicModelPart)this.nose);
        this.nose.setTextureOffset(0, 7).addBox(-1.0f, -1.5f, -0.5f, 2.0f, 3.0f, 1.0f, 0.0f, false);
        this.ear_left = new AdvancedModelBox((AdvancedEntityModel)this, "ear_left");
        this.ear_left.setPos(2.75f, -2.75f, -3.5f);
        this.head.addChild((BasicModelPart)this.ear_left);
        this.ear_left.setTextureOffset(21, 58).addBox(-0.75f, -5.25f, -1.5f, 6.0f, 6.0f, 3.0f, 0.0f, false);
        this.ear_left.setTextureOffset(0, 0).addBox(2.25f, 0.75f, -1.5f, 3.0f, 3.0f, 3.0f, 0.0f, false);
        this.ear_right = new AdvancedModelBox((AdvancedEntityModel)this, "ear_right");
        this.ear_right.setPos(-2.75f, -2.75f, -3.5f);
        this.head.addChild((BasicModelPart)this.ear_right);
        this.ear_right.setTextureOffset(21, 58).addBox(-5.25f, -5.25f, -1.5f, 6.0f, 6.0f, 3.0f, 0.0f, true);
        this.ear_right.setTextureOffset(0, 0).addBox(-5.25f, 0.75f, -1.5f, 3.0f, 3.0f, 3.0f, 0.0f, true);
        this.jaw = new AdvancedModelBox((AdvancedEntityModel)this, "jaw");
        this.jaw.setPos(0.0f, 1.0f, 0.0f);
        this.head.addChild((BasicModelPart)this.jaw);
        this.jaw.setTextureOffset(47, 0).addBox(-5.0f, 0.0f, -9.0f, 10.0f, 4.0f, 9.0f, 0.0f, false);
        this.arm_left = new AdvancedModelBox((AdvancedEntityModel)this, "arm_left");
        this.arm_left.setPos(6.75f, 3.0f, -8.75f);
        this.front_body.addChild((BasicModelPart)this.arm_left);
        this.arm_left.setTextureOffset(56, 26).addBox(-1.75f, -3.0f, -2.25f, 5.0f, 14.0f, 5.0f, 0.0f, false);
        this.claws_left = new AdvancedModelBox((AdvancedEntityModel)this, "claws_left");
        this.claws_left.setPos(0.25f, 11.0f, -2.25f);
        this.arm_left.addChild((BasicModelPart)this.claws_left);
        this.claws_left.setTextureOffset(61, 14).addBox(-3.0f, 0.0f, -2.0f, 6.0f, 2.0f, 5.0f, 0.0f, false);
        this.arm_right = new AdvancedModelBox((AdvancedEntityModel)this, "arm_right");
        this.arm_right.setPos(-6.75f, 3.0f, -8.75f);
        this.front_body.addChild((BasicModelPart)this.arm_right);
        this.arm_right.setTextureOffset(56, 26).addBox(-3.25f, -3.0f, -2.25f, 5.0f, 14.0f, 5.0f, 0.0f, true);
        this.claws_right = new AdvancedModelBox((AdvancedEntityModel)this, "claws_right");
        this.claws_right.setPos(-0.25f, 11.0f, -2.25f);
        this.arm_right.addChild((BasicModelPart)this.claws_right);
        this.claws_right.setTextureOffset(61, 14).addBox(-3.0f, 0.0f, -2.0f, 6.0f, 2.0f, 5.0f, 0.0f, true);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityDropBear.ANIMATION_BITE);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(-40.0), 0.0f, 0.0f);
        this.animator.rotate(this.jaw, Maths.rad(80.0), 0.0f, 0.0f);
        this.animator.move(this.head, 0.0f, 0.0f, 2.0f);
        this.animator.move(this.ear_left, 0.0f, 0.0f, -2.0f);
        this.animator.move(this.ear_right, 0.0f, 0.0f, -2.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(1);
        this.animator.startKeyframe(2);
        this.animator.rotate(this.head, Maths.rad(-5.0), 0.0f, 0.0f);
        this.animator.rotate(this.jaw, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(1);
        this.animator.setAnimation(EntityDropBear.ANIMATION_SWIPE_L);
        this.animator.startKeyframe(7);
        this.animator.rotate(this.front_body, 0.0f, Maths.rad(10.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.arm_left, Maths.rad(65.0), 0.0f, Maths.rad(-100.0));
        this.animator.rotate(this.arm_right, Maths.rad(-15.0), 0.0f, Maths.rad(10.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.front_body, 0.0f, Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(-90.0), 0.0f, Maths.rad(20.0));
        this.animator.rotate(this.arm_right, Maths.rad(-15.0), 0.0f, Maths.rad(20.0));
        this.animator.move(this.arm_left, 0.0f, 0.0f, -6.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(3);
        this.animator.setAnimation(EntityDropBear.ANIMATION_SWIPE_R);
        this.animator.startKeyframe(7);
        this.animator.rotate(this.front_body, 0.0f, Maths.rad(10.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.arm_right, Maths.rad(65.0), 0.0f, Maths.rad(100.0));
        this.animator.rotate(this.arm_left, Maths.rad(-15.0), 0.0f, Maths.rad(-10.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.front_body, 0.0f, Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-90.0), 0.0f, Maths.rad(-20.0));
        this.animator.rotate(this.arm_left, Maths.rad(-15.0), 0.0f, Maths.rad(-20.0));
        this.animator.move(this.arm_right, 0.0f, 0.0f, -6.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(3);
        this.animator.setAnimation(EntityDropBear.ANIMATION_JUMPUP);
        this.animator.startKeyframe(10);
        this.animator.move(this.body, 0.0f, 5.0f, 0.0f);
        this.animator.rotate(this.arm_right, 0.0f, 0.0f, Maths.rad(40.0));
        this.animator.rotate(this.arm_left, 0.0f, 0.0f, Maths.rad(-40.0));
        this.animator.rotate(this.leg_right, 0.0f, 0.0f, Maths.rad(40.0));
        this.animator.rotate(this.leg_left, 0.0f, 0.0f, Maths.rad(-40.0));
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.resetKeyframe(5);
    }

    public void setupAnim(EntityDropBear entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float upsideDownProgress = entityIn.prevUpsideDownProgress + (entityIn.upsideDownProgress - entityIn.prevUpsideDownProgress) * (ageInTicks - (float)entityIn.f_19797_);
        float walkSpeed = 0.7f;
        float walkDegree = 0.7f;
        float idleSpeed = 0.2f;
        float idleDegree = 0.1f;
        float invert = upsideDownProgress > 0.0f ? -1.0f : 1.0f;
        this.progressPositionPrev(this.body, upsideDownProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, upsideDownProgress, 0.0f, 0.0f, Maths.rad(180.0) * (entityIn.fallRotation ? -1.0f : 1.0f), 5.0f);
        this.walk(this.leg_left, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.leg_left, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.walk(this.leg_right, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.leg_right, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.walk(this.arm_right, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.arm_left, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.arm_left, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.bob(this.arm_right, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.flap(this.front_body, walkSpeed, walkDegree * 0.2f, false, -2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.head, walkSpeed, walkDegree * 0.2f, true, -2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.flap(this.ear_right, walkSpeed, walkDegree * 0.2f, false, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.ear_left, walkSpeed, walkDegree * 0.2f, true, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.ear_right, idleSpeed, idleDegree, false, -1.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.ear_left, idleSpeed, idleDegree, true, -1.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.nose, idleSpeed * 0.5f, idleDegree, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.head.rotateAngleY += netHeadYaw * 0.9f * invert * ((float)Math.PI / 180);
        this.head.rotateAngleX += headPitch * 0.9f * invert * ((float)Math.PI / 180);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.leg_left, (Object)this.leg_right, (Object)this.arm_left, (Object)this.arm_right, (Object)this.head, (Object)this.ear_left, (Object)this.ear_right, (Object)this.nose, (Object)this.jaw, (Object)this.front_body, (Object[])new AdvancedModelBox[]{this.claws_left, this.claws_right});
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
