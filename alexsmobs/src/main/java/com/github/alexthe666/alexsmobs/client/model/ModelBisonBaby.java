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

public class ModelBisonBaby
extends AdvancedEntityModel<EntityBison> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox torso;
    private final AdvancedModelBox left_arm;
    private final AdvancedModelBox right_arm;
    private final AdvancedModelBox head;
    private final AdvancedModelBox left_ear;
    private final AdvancedModelBox right_ear;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox right_leg;
    private final ModelAnimator animator;

    public ModelBisonBaby() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -13.5f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 19).addBox(-3.0f, -3.5f, -1.0f, 6.0f, 8.0f, 10.0f, 0.0f, false);
        this.torso = new AdvancedModelBox((AdvancedEntityModel)this, "torso");
        this.torso.setRotationPoint(0.0f, -0.4f, 0.0f);
        this.body.addChild((BasicModelPart)this.torso);
        this.torso.setTextureOffset(0, 0).addBox(-3.5f, -4.0f, -9.0f, 7.0f, 9.0f, 9.0f, 0.0f, false);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setRotationPoint(2.4f, 3.9f, -6.5f);
        this.torso.addChild((BasicModelPart)this.left_arm);
        this.left_arm.setTextureOffset(0, 38).addBox(-1.0f, 1.0f, -1.5f, 2.0f, 9.0f, 3.0f, 0.0f, false);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setRotationPoint(-2.4f, 3.9f, -6.5f);
        this.torso.addChild((BasicModelPart)this.right_arm);
        this.right_arm.setTextureOffset(0, 38).addBox(-1.0f, 1.0f, -1.5f, 2.0f, 9.0f, 3.0f, 0.0f, true);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -2.1f, -9.0f);
        this.torso.addChild((BasicModelPart)this.head);
        this.setRotationAngle(this.head, 0.6981f, 0.0f, 0.0f);
        this.head.setTextureOffset(24, 10).addBox(-2.5f, -3.0f, -8.0f, 5.0f, 6.0f, 9.0f, 0.0f, false);
        this.left_ear = new AdvancedModelBox((AdvancedEntityModel)this, "left_ear");
        this.left_ear.setRotationPoint(2.5f, -2.6f, -0.3f);
        this.head.addChild((BasicModelPart)this.left_ear);
        this.setRotationAngle(this.left_ear, -0.8378f, -1.3875f, 0.8727f);
        this.left_ear.setTextureOffset(24, 0).addBox(0.0f, -1.0f, -0.5f, 4.0f, 2.0f, 1.0f, 0.0f, false);
        this.right_ear = new AdvancedModelBox((AdvancedEntityModel)this, "right_ear");
        this.right_ear.setRotationPoint(-2.5f, -2.6f, -0.3f);
        this.head.addChild((BasicModelPart)this.right_ear);
        this.setRotationAngle(this.right_ear, -0.8378f, 1.3875f, -0.8727f);
        this.right_ear.setTextureOffset(24, 0).addBox(-4.0f, -1.0f, -0.5f, 4.0f, 2.0f, 1.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, -1.5f, 9.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.setRotationAngle(this.tail, 0.2618f, 0.0f, 0.0f);
        this.tail.setTextureOffset(0, 0).addBox(-1.0f, -1.0f, 0.0f, 2.0f, 6.0f, 1.0f, 0.0f, false);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setRotationPoint(1.8f, 3.5f, 7.5f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(33, 26).addBox(-1.0f, 1.0f, -1.5f, 2.0f, 9.0f, 3.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setRotationPoint(-1.8f, 3.5f, 7.5f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(33, 26).addBox(-1.0f, 1.0f, -1.5f, 2.0f, 9.0f, 3.0f, 0.0f, true);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
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
    }

    private void eatPose() {
        this.animator.rotate(this.head, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.torso, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.move(this.torso, 0.0f, 0.0f, 2.0f);
        this.animator.rotate(this.left_arm, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.move(this.left_arm, 0.0f, -2.5f, 0.0f);
        this.animator.move(this.right_arm, 0.0f, -2.5f, 0.0f);
        this.animator.move(this.head, 0.0f, 4.0f, 0.0f);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.torso, (Object)this.head, (Object)this.left_ear, (Object)this.right_ear, (Object)this.left_leg, (Object)this.right_leg, (Object)this.tail, (Object)this.right_arm, (Object)this.left_arm);
    }

    public void setupAnim(EntityBison entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 0.4f;
        float walkDegree = 0.7f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        this.walk(this.right_arm, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.left_arm, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.right_leg, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.left_leg, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.tail, walkSpeed, walkDegree * 0.1f, true, 1.0f, -0.6f, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed, walkDegree, true, limbSwing, limbSwingAmount);
        this.bob(this.head, walkSpeed, -walkDegree, false, limbSwing, limbSwingAmount);
        this.swing(this.left_ear, idleSpeed, idleDegree * 0.5f, true, 3.0f, -0.2f, ageInTicks, 1.0f);
        this.swing(this.right_ear, idleSpeed, idleDegree * 0.5f, true, 3.0f, 0.2f, ageInTicks, 1.0f);
        this.walk(this.tail, idleSpeed, idleDegree, false, 1.0f, 0.1f, ageInTicks, 1.0f);
        this.bob(this.head, idleSpeed, idleDegree, false, ageInTicks, 1.0f);
        this.head.rotateAngleY += netHeadYaw * 0.75f * ((float)Math.PI / 180);
        this.head.rotateAngleX += headPitch * ((float)Math.PI / 180);
    }
}
