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
 *  net.minecraft.client.Minecraft
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityShoebill;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;

public class ModelShoebill
extends AdvancedEntityModel<EntityShoebill> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox leftWing;
    private final AdvancedModelBox leftWingFeathers;
    private final AdvancedModelBox rightWing;
    private final AdvancedModelBox rightWingFeathers;
    private final AdvancedModelBox leftLeg;
    private final AdvancedModelBox leftFoot;
    private final AdvancedModelBox rightLeg;
    private final AdvancedModelBox rightFoot;
    private final AdvancedModelBox headPivot;
    private final AdvancedModelBox head;
    private final AdvancedModelBox backHair;
    private final AdvancedModelBox hair_r1;
    private final AdvancedModelBox beak;
    private final AdvancedModelBox jaw;
    private final AdvancedModelBox jaw_r1;
    private final ModelAnimator animator;

    public ModelShoebill() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this);
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this);
        this.body.setRotationPoint(0.0f, -17.0f, 2.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.setRotationAngle(this.body, -0.9599f, 0.0f, 0.0f);
        this.body.setTextureOffset(0, 0).addBox(-4.0f, -3.0f, -7.0f, 8.0f, 6.0f, 13.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this);
        this.tail.setRotationPoint(0.0f, -2.0f, 6.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(42, 27).addBox(-3.0f, -1.0f, 0.0f, 6.0f, 2.0f, 9.0f, 0.0f, false);
        this.leftWing = new AdvancedModelBox((AdvancedEntityModel)this);
        this.leftWing.setRotationPoint(4.0f, 0.3f, -3.4f);
        this.body.addChild((BasicModelPart)this.leftWing);
        this.leftWing.setTextureOffset(0, 20).addBox(-1.0f, -3.0f, -2.0f, 2.0f, 6.0f, 13.0f, 0.0f, false);
        this.leftWingFeathers = new AdvancedModelBox((AdvancedEntityModel)this);
        this.leftWingFeathers.setRotationPoint(0.0f, 0.0f, 9.0f);
        this.leftWing.addChild((BasicModelPart)this.leftWingFeathers);
        this.leftWingFeathers.setTextureOffset(31, 8).addBox(0.2f, -4.0f, -3.0f, 0.0f, 6.0f, 12.0f, 0.0f, false);
        this.rightWing = new AdvancedModelBox((AdvancedEntityModel)this);
        this.rightWing.setRotationPoint(-4.0f, 0.3f, -3.4f);
        this.body.addChild((BasicModelPart)this.rightWing);
        this.rightWing.setTextureOffset(0, 20).addBox(-1.0f, -3.0f, -2.0f, 2.0f, 6.0f, 13.0f, 0.0f, true);
        this.rightWingFeathers = new AdvancedModelBox((AdvancedEntityModel)this);
        this.rightWingFeathers.setRotationPoint(0.0f, 0.0f, 9.0f);
        this.rightWing.addChild((BasicModelPart)this.rightWingFeathers);
        this.rightWingFeathers.setTextureOffset(31, 8).addBox(-0.2f, -4.0f, -3.0f, 0.0f, 6.0f, 12.0f, 0.0f, true);
        this.leftLeg = new AdvancedModelBox((AdvancedEntityModel)this);
        this.leftLeg.setRotationPoint(2.5f, 3.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.leftLeg);
        this.setRotationAngle(this.leftLeg, 0.9599f, 0.0f, 0.0f);
        this.leftLeg.setTextureOffset(18, 20).addBox(-1.5f, 0.0f, 0.0f, 3.0f, 12.0f, 0.0f, 0.0f, false);
        this.leftFoot = new AdvancedModelBox((AdvancedEntityModel)this);
        this.leftFoot.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.leftLeg.addChild((BasicModelPart)this.leftFoot);
        this.leftFoot.setTextureOffset(30, 0).addBox(-2.5f, 0.0f, -3.0f, 5.0f, 0.0f, 5.0f, 0.0f, false);
        this.rightLeg = new AdvancedModelBox((AdvancedEntityModel)this);
        this.rightLeg.setRotationPoint(-2.5f, 3.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.rightLeg);
        this.setRotationAngle(this.rightLeg, 0.9599f, 0.0f, 0.0f);
        this.rightLeg.setTextureOffset(18, 20).addBox(-1.5f, 0.0f, 0.0f, 3.0f, 12.0f, 0.0f, 0.0f, true);
        this.rightFoot = new AdvancedModelBox((AdvancedEntityModel)this);
        this.rightFoot.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.rightLeg.addChild((BasicModelPart)this.rightFoot);
        this.rightFoot.setTextureOffset(30, 0).addBox(-2.5f, 0.0f, -3.0f, 5.0f, 0.0f, 5.0f, 0.0f, true);
        this.headPivot = new AdvancedModelBox((AdvancedEntityModel)this);
        this.headPivot.setRotationPoint(0.0f, 1.0f, -4.0f);
        this.setRotationAngle(this.headPivot, -0.6109f, 0.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.headPivot);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.headPivot.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(20, 29).addBox(-2.5f, -3.0f, -11.0f, 5.0f, 5.0f, 11.0f, 0.0f, false);
        this.head.setTextureOffset(34, 47).addBox(-2.5f, 2.0f, -11.0f, 5.0f, 1.0f, 1.0f, 0.0f, false);
        this.head.setTextureOffset(0, 0).addBox(0.0f, -6.0f, -12.0f, 0.0f, 6.0f, 5.0f, 0.0f, false);
        this.backHair = new AdvancedModelBox((AdvancedEntityModel)this);
        this.backHair.setRotationPoint(0.0f, -3.0f, -10.0f);
        this.head.addChild((BasicModelPart)this.backHair);
        this.hair_r1 = new AdvancedModelBox((AdvancedEntityModel)this);
        this.hair_r1.setRotationPoint(0.0f, 0.0f, -1.0f);
        this.backHair.addChild((BasicModelPart)this.hair_r1);
        this.setRotationAngle(this.hair_r1, -0.5236f, 0.0f, 0.0f);
        this.hair_r1.setTextureOffset(30, 6).addBox(-2.5f, -5.0f, 0.0f, 5.0f, 5.0f, 0.0f, 0.0f, false);
        this.beak = new AdvancedModelBox((AdvancedEntityModel)this);
        this.beak.setRotationPoint(0.0f, 2.0f, -9.0f);
        this.head.addChild((BasicModelPart)this.beak);
        this.setRotationAngle(this.beak, 0.3927f, 0.0f, 0.0f);
        this.beak.setTextureOffset(0, 40).addBox(-3.0f, -1.0f, 0.0f, 6.0f, 8.0f, 3.0f, 0.0f, false);
        this.beak.setTextureOffset(6, 0).addBox(-1.0f, 7.0f, 3.0f, 2.0f, 0.0f, 1.0f, 0.0f, false);
        this.jaw = new AdvancedModelBox((AdvancedEntityModel)this);
        this.jaw.setRotationPoint(0.0f, 1.0f, 3.0f);
        this.beak.addChild((BasicModelPart)this.jaw);
        this.jaw_r1 = new AdvancedModelBox((AdvancedEntityModel)this);
        this.jaw_r1.setRotationPoint(0.0f, 0.0f, 0.3f);
        this.jaw.addChild((BasicModelPart)this.jaw_r1);
        this.setRotationAngle(this.jaw_r1, -0.1745f, 0.0f, 0.0f);
        this.jaw_r1.setTextureOffset(0, 20).addBox(-2.5f, -1.4f, -0.3f, 5.0f, 7.0f, 1.0f, -0.03f, false);
        this.updateDefaultPose();
        this.animator = new ModelAnimator();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityShoebill.ANIMATION_FISH);
        this.animator.startKeyframe(15);
        this.animator.rotate(this.head, Maths.rad(-40.0), 0.0f, 0.0f);
        this.animator.move(this.head, 0.0f, 0.5f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.body, Maths.rad(40.0), 0.0f, 0.0f);
        this.animator.rotate(this.leftLeg, Maths.rad(-40.0), 0.0f, 0.0f);
        this.animator.rotate(this.rightLeg, Maths.rad(-40.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(80.0), 0.0f, 0.0f);
        this.animator.rotate(this.jaw, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.move(this.body, 0.0f, 1.0f, 0.0f);
        this.animator.move(this.head, 0.0f, 0.0f, -2.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(3);
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityShoebill.ANIMATION_BEAKSHAKE);
        this.animator.startKeyframe(4);
        this.animator.rotate(this.head, Maths.rad(40.0), Maths.rad(40.0), 0.0f);
        this.animator.move(this.head, 0.0f, 0.5f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.head, Maths.rad(40.0), Maths.rad(-40.0), 0.0f);
        this.animator.move(this.head, 0.0f, 0.5f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.head, Maths.rad(40.0), Maths.rad(40.0), 0.0f);
        this.animator.move(this.head, 0.0f, 0.5f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.head, Maths.rad(40.0), Maths.rad(-40.0), 0.0f);
        this.animator.move(this.head, 0.0f, 0.5f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(4);
        this.animator.setAnimation(EntityShoebill.ANIMATION_ATTACK);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.jaw, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.move(this.head, 0.0f, 0.5f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(60.0), 0.0f, 0.0f);
        this.animator.rotate(this.jaw, Maths.rad(5.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.leftLeg, (Object)this.rightLeg, (Object)this.leftWing, (Object)this.rightWing, (Object)this.tail, (Object)this.headPivot, (Object)this.head, (Object)this.beak, (Object)this.jaw, (Object)this.backHair, (Object[])new AdvancedModelBox[]{this.leftFoot, this.rightFoot, this.hair_r1, this.jaw_r1, this.leftWingFeathers, this.rightWingFeathers});
    }

    public void setupAnim(EntityShoebill entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 0.7f;
        float walkDegree = 0.4f;
        float idleSpeed = 0.05f;
        float idleDegree = 0.2f;
        float flapSpeed = 0.4f;
        float flapDegree = 0.2f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float flyProgress = entity.prevFlyProgress + (entity.flyProgress - entity.prevFlyProgress) * partialTick;
        float scaledLimbSwing = Math.min(1.0f, limbSwingAmount * 1.6f);
        float runProgress = Math.max(5.0f * scaledLimbSwing - flyProgress, 0.0f);
        this.progressRotationPrev(this.body, runProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.rightLeg, runProgress, Maths.rad(-25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leftLeg, runProgress, Maths.rad(-25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, runProgress, Maths.rad(-30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, flyProgress, Maths.rad(35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.rightLeg, flyProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leftLeg, flyProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.rightFoot, flyProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leftFoot, flyProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.rightWing, flyProgress, Maths.rad(90.0), 0.0f, Maths.rad(-80.0), 5.0f);
        this.progressRotationPrev(this.leftWing, flyProgress, Maths.rad(90.0), 0.0f, Maths.rad(80.0), 5.0f);
        this.progressRotationPrev(this.head, flyProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, flyProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.rightLeg, flyProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.leftLeg, flyProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, flyProgress, 0.0f, 5.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, flyProgress, 0.0f, 1.5f, 0.0f, 5.0f);
        this.walk(this.head, -idleSpeed, idleDegree * 0.2f, false, 2.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.tail, idleSpeed * 2.0f, idleDegree * 0.5f, true, 0.0f, 0.0f, ageInTicks, 1.0f);
        if (flyProgress > 0.0f) {
            this.walk(this.rightLeg, walkSpeed, walkDegree * 0.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.leftLeg, walkSpeed, walkDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.rightWing, flapSpeed, flapDegree * 5.0f, true, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.flap(this.leftWing, flapSpeed, flapDegree * 5.0f, false, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.walk(this.head, flapSpeed, flapDegree * 0.85f, true, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.bob(this.body, flapSpeed * 0.3f, flapDegree * 4.0f, true, ageInTicks, 1.0f);
        } else {
            this.walk(this.rightLeg, walkSpeed, walkDegree * 1.85f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.leftLeg, walkSpeed, walkDegree * 1.85f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.rightFoot, walkSpeed, walkDegree * 1.85f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.leftFoot, walkSpeed, walkDegree * 1.85f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.head, walkSpeed, walkDegree * 0.85f, true, 2.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.tail, walkSpeed * 0.5f, walkDegree * 0.15f, true, -2.0f, 0.2f, limbSwing, limbSwingAmount);
        }
        this.head.rotateAngleZ += netHeadYaw * ((float)Math.PI / 180);
    }
}
