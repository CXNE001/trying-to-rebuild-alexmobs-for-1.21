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

import com.github.alexthe666.alexsmobs.entity.EntityFrilledShark;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelFrilledShark
extends AdvancedEntityModel<EntityFrilledShark> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox head;
    private final AdvancedModelBox jaw;
    private final AdvancedModelBox pectoralfin_left;
    private final AdvancedModelBox pectoralfin_right;
    private final AdvancedModelBox tail1;
    private final AdvancedModelBox pelvicfin_left;
    private final AdvancedModelBox pelvicfin_right;
    private final AdvancedModelBox tail2;
    private final ModelAnimator animator;

    public ModelFrilledShark() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -3.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-3.0f, -3.0f, -15.0f, 6.0f, 6.0f, 18.0f, 0.0f, false);
        this.body.setTextureOffset(66, 59).addBox(0.0f, -9.0f, -14.0f, 0.0f, 6.0f, 17.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -2.0f, -15.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(31, 0).addBox(-3.0f, -1.0f, -7.0f, 6.0f, 3.0f, 7.0f, 0.0f, false);
        this.jaw = new AdvancedModelBox((AdvancedEntityModel)this, "jaw");
        this.jaw.setPos(0.0f, 2.4f, 0.4f);
        this.head.addChild((BasicModelPart)this.jaw);
        this.setRotationAngle(this.jaw, 0.2618f, 0.0f, 0.0f);
        this.jaw.setTextureOffset(41, 25).addBox(-2.5f, 0.0f, -7.0f, 5.0f, 2.0f, 7.0f, 0.0f, false);
        this.pectoralfin_left = new AdvancedModelBox((AdvancedEntityModel)this, "pectoralfin_left");
        this.pectoralfin_left.setPos(3.0f, 2.4f, -10.0f);
        this.body.addChild((BasicModelPart)this.pectoralfin_left);
        this.setRotationAngle(this.pectoralfin_left, 0.0f, 0.0f, 0.48f);
        this.pectoralfin_left.setTextureOffset(41, 42).addBox(0.0f, 0.0f, 0.0f, 5.0f, 0.0f, 7.0f, 0.0f, false);
        this.pectoralfin_right = new AdvancedModelBox((AdvancedEntityModel)this, "pectoralfin_right");
        this.pectoralfin_right.setPos(-3.0f, 2.4f, -10.0f);
        this.body.addChild((BasicModelPart)this.pectoralfin_right);
        this.setRotationAngle(this.pectoralfin_right, 0.0f, 0.0f, -0.48f);
        this.pectoralfin_right.setTextureOffset(41, 42).addBox(-5.0f, 0.0f, 0.0f, 5.0f, 0.0f, 7.0f, 0.0f, true);
        this.tail1 = new AdvancedModelBox((AdvancedEntityModel)this, "tail1");
        this.tail1.setPos(0.0f, -0.9f, 3.0f);
        this.body.addChild((BasicModelPart)this.tail1);
        this.tail1.setTextureOffset(21, 25).addBox(-2.0f, -2.0f, 0.0f, 4.0f, 5.0f, 11.0f, 0.0f, false);
        this.tail1.setTextureOffset(0, 25).addBox(0.0f, -5.0f, 5.0f, 0.0f, 3.0f, 6.0f, 0.0f, false);
        this.pelvicfin_left = new AdvancedModelBox((AdvancedEntityModel)this, "pelvicfin_left");
        this.pelvicfin_left.setPos(2.0f, 3.0f, 5.0f);
        this.tail1.addChild((BasicModelPart)this.pelvicfin_left);
        this.setRotationAngle(this.pelvicfin_left, 0.0f, 0.0f, -0.9599f);
        this.pelvicfin_left.setTextureOffset(21, 25).addBox(0.0f, 0.0f, -1.0f, 0.0f, 3.0f, 5.0f, 0.0f, false);
        this.pelvicfin_right = new AdvancedModelBox((AdvancedEntityModel)this, "pelvicfin_right");
        this.pelvicfin_right.setPos(-2.0f, 3.0f, 5.0f);
        this.tail1.addChild((BasicModelPart)this.pelvicfin_right);
        this.setRotationAngle(this.pelvicfin_right, 0.0f, 0.0f, 0.9599f);
        this.pelvicfin_right.setTextureOffset(21, 25).addBox(0.0f, 0.0f, -1.0f, 0.0f, 3.0f, 5.0f, 0.0f, true);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this, "tail2");
        this.tail2.setPos(0.0f, 0.1f, 11.0f);
        this.tail1.addChild((BasicModelPart)this.tail2);
        this.tail2.setTextureOffset(0, 25).addBox(0.0f, -6.0f, 0.0f, 0.0f, 11.0f, 20.0f, 0.0f, false);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.animator.update(entity);
        this.animator.setAnimation(EntityFrilledShark.ANIMATION_ATTACK);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.jaw, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.move(this.head, 0.0f, 0.5f, 3.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.jaw, Maths.rad(40.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(5.0), 0.0f, 0.0f);
        this.animator.rotate(this.jaw, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(2);
    }

    public void setupAnim(EntityFrilledShark entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        AdvancedModelBox[] tailBoxes = new AdvancedModelBox[]{this.head, this.body, this.tail1, this.tail2};
        float idleSpeed = 0.14f;
        float idleDegree = 0.25f;
        float swimSpeed = 0.8f;
        float swimDegree = 0.75f;
        float landProgress = entityIn.prevOnLandProgress + (entityIn.onLandProgress - entityIn.prevOnLandProgress) * (ageInTicks - (float)entityIn.f_19797_);
        this.progressRotationPrev(this.body, landProgress, 0.0f, 0.0f, Maths.rad(-100.0), 5.0f);
        this.progressRotationPrev(this.pectoralfin_right, landProgress, 0.0f, 0.0f, Maths.rad(-50.0), 5.0f);
        this.progressRotationPrev(this.pectoralfin_left, landProgress, 0.0f, 0.0f, Maths.rad(50.0), 5.0f);
        this.walk(this.jaw, idleSpeed, idleDegree, true, 1.0f, -0.1f, ageInTicks, 1.0f);
        if (landProgress >= 5.0f) {
            this.chainWave(tailBoxes, idleSpeed, idleDegree * 0.9f, -3.0, ageInTicks, 1.0f);
            this.flap(this.pectoralfin_right, idleSpeed, idleDegree * 2.0f, true, 3.0f, 0.3f, ageInTicks, 1.0f);
            this.flap(this.pectoralfin_left, idleSpeed, idleDegree * -2.0f, true, 3.0f, 0.1f, ageInTicks, 1.0f);
        } else {
            this.chainSwing(tailBoxes, swimSpeed, swimDegree * 0.9f, -3.0, limbSwing, limbSwingAmount);
            this.flap(this.pectoralfin_right, swimSpeed, swimDegree, true, 1.0f, 0.3f, limbSwing, limbSwingAmount);
            this.flap(this.pectoralfin_left, swimSpeed, swimDegree, true, 1.0f, 0.3f, limbSwing, limbSwingAmount);
            this.flap(this.pelvicfin_right, swimSpeed, -swimDegree, true, 3.0f, 0.1f, limbSwing, limbSwingAmount);
            this.flap(this.pelvicfin_left, swimSpeed, -swimDegree, true, 3.0f, 0.1f, limbSwing, limbSwingAmount);
        }
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.head, (Object)this.tail1, (Object)this.tail2, (Object)this.jaw, (Object)this.pectoralfin_left, (Object)this.pectoralfin_right, (Object)this.pelvicfin_left, (Object)this.pelvicfin_right);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
