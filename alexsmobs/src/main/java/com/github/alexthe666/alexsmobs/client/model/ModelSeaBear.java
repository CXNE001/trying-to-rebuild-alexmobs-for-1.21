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

import com.github.alexthe666.alexsmobs.entity.EntitySeaBear;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelSeaBear
extends AdvancedEntityModel<EntitySeaBear> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox head;
    private final AdvancedModelBox snout;
    private final AdvancedModelBox right_ear;
    private final AdvancedModelBox left_ear;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox left_arm;
    private final AdvancedModelBox right_arm;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox right_leg;
    private ModelAnimator animator;

    public ModelSeaBear() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -15.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-8.0f, -13.0f, -20.0f, 16.0f, 28.0f, 41.0f, 0.0f, false);
        this.body.setTextureOffset(0, 70).addBox(0.0f, -22.0f, -19.0f, 0.0f, 9.0f, 39.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, 6.2f, -22.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 0).addBox(-5.0f, -5.0f, -6.0f, 10.0f, 10.0f, 8.0f, 0.0f, false);
        this.snout = new AdvancedModelBox((AdvancedEntityModel)this, "snout");
        this.snout.setRotationPoint(0.0f, 0.0f, -6.0f);
        this.head.addChild((BasicModelPart)this.snout);
        this.snout.setTextureOffset(21, 19).addBox(-2.0f, 0.0f, -5.0f, 4.0f, 5.0f, 5.0f, 0.0f, false);
        this.right_ear = new AdvancedModelBox((AdvancedEntityModel)this, "right_ear");
        this.right_ear.setRotationPoint(-3.5f, -5.0f, -3.0f);
        this.head.addChild((BasicModelPart)this.right_ear);
        this.right_ear.setTextureOffset(11, 19).addBox(-1.5f, -2.0f, -1.0f, 3.0f, 2.0f, 2.0f, 0.0f, false);
        this.left_ear = new AdvancedModelBox((AdvancedEntityModel)this, "left_ear");
        this.left_ear.setRotationPoint(3.5f, -5.0f, -3.0f);
        this.head.addChild((BasicModelPart)this.left_ear);
        this.left_ear.setTextureOffset(11, 19).addBox(-1.5f, -2.0f, -1.0f, 3.0f, 2.0f, 2.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, 1.0f, 21.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(79, 70).addBox(0.0f, -13.0f, 0.0f, 0.0f, 25.0f, 17.0f, 0.0f, false);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setRotationPoint(6.6f, 15.0f, -10.0f);
        this.body.addChild((BasicModelPart)this.left_arm);
        this.left_arm.setTextureOffset(0, 70).addBox(-1.0f, 0.0f, -5.0f, 2.0f, 13.0f, 9.0f, 0.0f, false);
        this.left_arm.setTextureOffset(0, 19).addBox(0.0f, 13.0f, -5.0f, 0.0f, 1.0f, 9.0f, 0.0f, false);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setRotationPoint(-6.6f, 15.0f, -10.0f);
        this.body.addChild((BasicModelPart)this.right_arm);
        this.right_arm.setTextureOffset(0, 70).addBox(-1.0f, 0.0f, -5.0f, 2.0f, 13.0f, 9.0f, 0.0f, true);
        this.right_arm.setTextureOffset(0, 19).addBox(0.0f, 13.0f, -5.0f, 0.0f, 1.0f, 9.0f, 0.0f, true);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setRotationPoint(7.7f, 15.0f, 16.0f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(40, 70).addBox(-1.0f, 0.0f, -3.0f, 1.0f, 8.0f, 6.0f, 0.0f, false);
        this.left_leg.setTextureOffset(15, 30).addBox(0.0f, 8.0f, -3.0f, 0.0f, 1.0f, 6.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setRotationPoint(-7.7f, 15.0f, 16.0f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(40, 70).addBox(0.0f, 0.0f, -3.0f, 1.0f, 8.0f, 6.0f, 0.0f, true);
        this.right_leg.setTextureOffset(15, 30).addBox(0.0f, 8.0f, -3.0f, 0.0f, 1.0f, 6.0f, 0.0f, true);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.animator.update(entity);
        this.animator.setAnimation(EntitySeaBear.ANIMATION_POINT);
        this.animator.startKeyframe(3);
        this.animator.rotate(this.head, Maths.rad(50.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(2);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.body, 0.0f, Maths.rad(-10.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, Maths.rad(10.0), 0.0f);
        this.animator.rotate(this.tail, 0.0f, Maths.rad(10.0), 0.0f);
        this.animator.move(this.right_arm, 1.0f, 0.0f, -5.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-110.0), Maths.rad(10.0), Maths.rad(-10.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.body, 0.0f, Maths.rad(-10.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, Maths.rad(10.0), 0.0f);
        this.animator.rotate(this.tail, 0.0f, Maths.rad(10.0), 0.0f);
        this.animator.move(this.right_arm, 1.0f, 0.0f, -7.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-90.0), Maths.rad(10.0), Maths.rad(-10.0));
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntitySeaBear.ANIMATION_ATTACK);
        this.animator.startKeyframe(3);
        this.animator.rotate(this.body, 0.0f, Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.tail, 0.0f, Maths.rad(20.0), 0.0f);
        this.animator.move(this.right_arm, 1.0f, 0.0f, -5.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-110.0), Maths.rad(10.0), Maths.rad(-10.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.body, 0.0f, Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.tail, 0.0f, Maths.rad(-20.0), 0.0f);
        this.animator.move(this.left_arm, -1.0f, 0.0f, -5.0f);
        this.animator.rotate(this.left_arm, Maths.rad(-110.0), Maths.rad(-10.0), Maths.rad(10.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.body, 0.0f, Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.tail, 0.0f, Maths.rad(20.0), 0.0f);
        this.animator.move(this.right_arm, 1.0f, 0.0f, -5.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-110.0), Maths.rad(10.0), Maths.rad(-10.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.body, 0.0f, Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.tail, 0.0f, Maths.rad(-20.0), 0.0f);
        this.animator.move(this.left_arm, -1.0f, 0.0f, -5.0f);
        this.animator.rotate(this.left_arm, Maths.rad(-110.0), Maths.rad(-10.0), Maths.rad(10.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setupAnim(EntitySeaBear entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float idleSpeed = 0.14f;
        float idleDegree = 0.25f;
        float swimSpeed = 0.8f;
        float swimDegree = 0.75f;
        float landProgress = entity.prevOnLandProgress + (entity.onLandProgress - entity.prevOnLandProgress) * (ageInTicks - (float)entity.f_19797_);
        this.progressRotationPrev(this.body, landProgress, 0.0f, 0.0f, Maths.rad(-90.0), 5.0f);
        this.progressPositionPrev(this.body, landProgress, 0.0f, 8.0f, 0.0f, 5.0f);
        this.flap(this.left_arm, idleSpeed, idleDegree, true, 1.0f, 0.1f, ageInTicks, 1.0f);
        this.flap(this.right_arm, idleSpeed, idleDegree, false, 1.0f, 0.1f, ageInTicks, 1.0f);
        this.flap(this.left_leg, idleSpeed, idleDegree, true, 3.0f, 0.1f, ageInTicks, 1.0f);
        this.flap(this.right_leg, idleSpeed, idleDegree, false, 3.0f, 0.1f, ageInTicks, 1.0f);
        this.swing(this.tail, idleSpeed, idleDegree, true, 5.0f, 0.0f, ageInTicks, 1.0f);
        this.bob(this.body, idleSpeed, idleDegree * 2.0f, false, ageInTicks, 1.0f);
        this.walk(this.body, swimSpeed, swimDegree * 0.1f, false, -3.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.body, swimSpeed, swimDegree * 0.2f, false, 2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.head, swimSpeed, swimDegree * 0.2f, true, 2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.left_arm, swimSpeed, swimDegree * 0.3f, false, -3.0f, 0.1f, limbSwing, limbSwingAmount);
        this.walk(this.right_arm, swimSpeed, swimDegree * 0.3f, false, -3.0f, 0.1f, limbSwing, limbSwingAmount);
        this.flap(this.left_arm, swimSpeed, swimDegree, true, 0.0f, 0.4f, limbSwing, limbSwingAmount);
        this.flap(this.right_arm, swimSpeed, swimDegree, false, 0.0f, 0.4f, limbSwing, limbSwingAmount);
        this.flap(this.left_leg, swimSpeed, swimDegree, true, 2.0f, 0.2f, limbSwing, limbSwingAmount);
        this.flap(this.right_leg, swimSpeed, swimDegree, false, 2.0f, 0.2f, limbSwing, limbSwingAmount);
        this.swing(this.tail, swimSpeed, swimDegree * 1.2f, true, 4.0f, 0.0f, limbSwing, limbSwingAmount);
        this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.head});
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.head, (Object)this.snout, (Object)this.right_arm, (Object)this.right_leg, (Object)this.right_ear, (Object)this.left_arm, (Object)this.left_leg, (Object)this.left_ear, (Object)this.tail);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
