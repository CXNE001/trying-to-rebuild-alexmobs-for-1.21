/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntitySkreecher;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelSkreecher
extends AdvancedEntityModel<EntitySkreecher> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox head;
    private final AdvancedModelBox upperJaw;
    private final AdvancedModelBox leftEye;
    private final AdvancedModelBox rightEye;
    private final AdvancedModelBox leftLeg;
    private final AdvancedModelBox leftFoot;
    private final AdvancedModelBox rightLeg;
    private final AdvancedModelBox rightFoot;
    private final AdvancedModelBox leftArm;
    private final AdvancedModelBox leftArmPivot;
    private final AdvancedModelBox leftHand;
    private final AdvancedModelBox rightArm;
    private final AdvancedModelBox rightArmPivot;
    private final AdvancedModelBox rightHand;

    public ModelSkreecher() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -14.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.setRotationAngle(this.body, -0.3927f, 0.0f, 0.0f);
        this.body.setTextureOffset(0, 26).addBox(-3.0f, -5.0f, -1.5f, 6.0f, 6.0f, 3.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -5.0f, -1.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.setRotationAngle(this.head, 0.5236f, 0.0f, 0.0f);
        this.head.setTextureOffset(0, 13).addBox(-5.0f, -7.0f, -4.2f, 10.0f, 7.0f, 5.0f, -0.1f, false);
        this.upperJaw = new AdvancedModelBox((AdvancedEntityModel)this, "upperJaw");
        this.upperJaw.setRotationPoint(0.0f, -7.0f, 0.8f);
        this.head.addChild((BasicModelPart)this.upperJaw);
        this.upperJaw.setTextureOffset(0, 0).addBox(-6.0f, 0.0f, -4.8f, 12.0f, 7.0f, 5.0f, 0.0f, false);
        this.leftEye = new AdvancedModelBox((AdvancedEntityModel)this, "leftEye");
        this.leftEye.setRotationPoint(3.0f, 1.6f, -3.8f);
        this.upperJaw.addChild((BasicModelPart)this.leftEye);
        this.leftEye.setTextureOffset(34, 16).addBox(-2.0f, -2.0f, -3.0f, 4.0f, 4.0f, 4.0f, 0.0f, false);
        this.rightEye = new AdvancedModelBox((AdvancedEntityModel)this, "rightEye");
        this.rightEye.setRotationPoint(-3.0f, 1.6f, -3.8f);
        this.upperJaw.addChild((BasicModelPart)this.rightEye);
        this.rightEye.setTextureOffset(34, 16).addBox(-2.0f, -2.0f, -3.0f, 4.0f, 4.0f, 4.0f, 0.0f, true);
        this.leftLeg = new AdvancedModelBox((AdvancedEntityModel)this, "leftLeg");
        this.leftLeg.setRotationPoint(2.0f, 2.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.leftLeg);
        this.setRotationAngle(this.leftLeg, 0.0f, -0.3054f, 0.0f);
        this.leftLeg.setTextureOffset(27, 25).addBox(0.0f, -1.0f, -5.0f, 0.0f, 6.0f, 7.0f, 0.0f, false);
        this.leftFoot = new AdvancedModelBox((AdvancedEntityModel)this, "leftFoot");
        this.leftFoot.setRotationPoint(0.0f, 2.0f, -4.0f);
        this.leftLeg.addChild((BasicModelPart)this.leftFoot);
        this.leftFoot.setTextureOffset(1, 48).addBox(-1.0f, 0.0f, -4.0f, 1.0f, 6.0f, 6.0f, 0.0f, false);
        this.rightLeg = new AdvancedModelBox((AdvancedEntityModel)this, "rightLeg");
        this.rightLeg.setRotationPoint(-2.0f, 2.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.rightLeg);
        this.setRotationAngle(this.rightLeg, 0.0f, 0.3054f, 0.0f);
        this.rightLeg.setTextureOffset(27, 25).addBox(0.0f, -1.0f, -5.0f, 0.0f, 6.0f, 7.0f, 0.0f, true);
        this.rightFoot = new AdvancedModelBox((AdvancedEntityModel)this, "rightFoot");
        this.rightFoot.setRotationPoint(0.0f, 2.0f, -4.0f);
        this.rightLeg.addChild((BasicModelPart)this.rightFoot);
        this.rightFoot.setTextureOffset(1, 48).addBox(0.0f, 0.0f, -4.0f, 1.0f, 6.0f, 6.0f, 0.0f, true);
        this.leftArmPivot = new AdvancedModelBox((AdvancedEntityModel)this, "leftArmPivot");
        this.leftArmPivot.setRotationPoint(4.0f, -3.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.leftArmPivot);
        this.leftArm = new AdvancedModelBox((AdvancedEntityModel)this, "leftArm");
        this.leftArmPivot.addChild((BasicModelPart)this.leftArm);
        this.setRotationAngle(this.leftArm, 1.5708f, -1.1781f, -1.5708f);
        this.leftArm.setTextureOffset(17, 34).addBox(-1.0f, -1.0f, -1.0f, 2.0f, 16.0f, 2.0f, 0.0f, false);
        this.leftHand = new AdvancedModelBox((AdvancedEntityModel)this, "leftHand");
        this.leftHand.setRotationPoint(0.0f, 15.0f, 0.0f);
        this.leftArm.addChild((BasicModelPart)this.leftHand);
        this.leftHand.setTextureOffset(31, 9).addBox(-4.0f, -0.1f, -2.0f, 8.0f, 3.0f, 4.0f, 0.0f, false);
        this.rightArmPivot = new AdvancedModelBox((AdvancedEntityModel)this, "rightArmPivot");
        this.rightArmPivot.setRotationPoint(-4.0f, -3.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.rightArmPivot);
        this.rightArm = new AdvancedModelBox((AdvancedEntityModel)this, "rightArm");
        this.rightArmPivot.addChild((BasicModelPart)this.rightArm);
        this.setRotationAngle(this.rightArm, 1.5708f, 1.1781f, 1.5708f);
        this.rightArm.setTextureOffset(17, 34).addBox(-1.0f, -1.0f, -1.0f, 2.0f, 16.0f, 2.0f, 0.0f, true);
        this.rightHand = new AdvancedModelBox((AdvancedEntityModel)this, "rightHand");
        this.rightHand.setRotationPoint(0.0f, 15.0f, 0.0f);
        this.rightArm.addChild((BasicModelPart)this.rightHand);
        this.rightHand.setTextureOffset(31, 9).addBox(-4.0f, -0.1f, -2.0f, 8.0f, 3.0f, 4.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.head, (Object)this.upperJaw, (Object)this.leftEye, (Object)this.rightEye, (Object)this.leftLeg, (Object)this.leftArmPivot, (Object)this.leftArm, (Object)this.leftFoot, (Object)this.rightLeg, (Object)this.rightArmPivot, (Object[])new AdvancedModelBox[]{this.rightArm, this.rightFoot, this.leftHand, this.rightHand});
    }

    public void setupAnim(EntitySkreecher entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float clingProgress = entity.prevClingProgress + (entity.clingProgress - entity.prevClingProgress) * partialTick;
        float groundProgress = 5.0f - clingProgress;
        float clapProgress = entity.prevClapProgress + (entity.clapProgress - entity.prevClapProgress) * partialTick;
        float distanceToCeiling = entity.prevDistanceToCeiling + (entity.getDistanceToCeiling() - entity.prevDistanceToCeiling) * partialTick;
        float armScale = 0.3f + distanceToCeiling * clingProgress * 0.2f + 0.7f * groundProgress * 0.2f;
        float armDegree = (5.0f - distanceToCeiling) * 0.2f;
        float walkSpeed = 1.0f;
        float walkDegree = 0.6f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        float groundSpeed = groundProgress * 0.2f;
        float feetStill = (5.0f - clapProgress) * 0.2f;
        float clingSpeed = clingProgress * 0.2f;
        this.rightArm.setShouldScaleChildren(true);
        this.rightArm.setScale(1.0f, armScale, 1.0f);
        this.leftArm.setShouldScaleChildren(true);
        this.leftArm.setScale(1.0f, armScale, 1.0f);
        this.progressRotationPrev(this.rightArmPivot, clingProgress, Maths.rad(-210.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leftArmPivot, clingProgress, Maths.rad(-210.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, clingProgress, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, clingProgress, Maths.rad(-30.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, clingProgress, 0.0f, 8.5f, 0.0f, 5.0f);
        this.progressPositionPrev(this.rightArmPivot, clingProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.leftArmPivot, clingProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, clingProgress, 0.0f, 3.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.rightLeg, clapProgress, Maths.rad(-10.0), Maths.rad(-25.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.leftLeg, clapProgress, Maths.rad(-10.0), Maths.rad(25.0), 0.0f, 5.0f);
        this.walk(this.body, idleSpeed, idleDegree, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.rightLeg, idleSpeed, idleDegree, false, -1.0f, 0.0f, ageInTicks, feetStill);
        this.walk(this.leftLeg, idleSpeed, idleDegree, false, -1.0f, 0.0f, ageInTicks, feetStill);
        this.walk(this.leftArmPivot, idleSpeed, idleDegree, true, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.rightArmPivot, idleSpeed, idleDegree, true, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.bob(this.head, idleSpeed, -idleDegree * 2.0f, false, limbSwing, 1.0f);
        this.walk(this.body, walkSpeed, walkDegree, false, 0.0f, 0.3f, limbSwing, limbSwingAmount * groundSpeed);
        this.walk(this.head, walkSpeed, walkDegree * 0.5f, true, 0.3f, 0.3f, limbSwing, limbSwingAmount * groundSpeed);
        this.swing(this.leftArm, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount * groundSpeed);
        this.swing(this.rightArm, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount * groundSpeed);
        this.walk(this.rightLeg, walkSpeed, walkDegree, false, 1.0f, 0.3f, limbSwing, limbSwingAmount * groundSpeed * feetStill);
        this.walk(this.leftLeg, walkSpeed, walkDegree, false, 1.0f, 0.3f, limbSwing, limbSwingAmount * groundSpeed * feetStill);
        this.swing(this.leftArm, walkSpeed, walkDegree, false, -2.0f, -0.2f, limbSwing, limbSwingAmount * groundSpeed);
        this.swing(this.rightArm, walkSpeed, walkDegree, false, -2.0f, 0.2f, limbSwing, limbSwingAmount * groundSpeed);
        this.swing(this.leftArm, walkSpeed, armDegree * walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount * clingSpeed);
        this.swing(this.rightArm, walkSpeed, armDegree * walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount * clingSpeed);
        this.walk(this.leftArm, walkSpeed, armDegree * walkDegree * 0.2f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount * clingSpeed);
        this.walk(this.rightArm, walkSpeed, armDegree * walkDegree * 0.2f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount * clingSpeed);
        this.walk(this.rightLeg, walkSpeed, walkDegree, false, 2.0f, 0.4f, limbSwing, limbSwingAmount * clingSpeed * feetStill);
        this.walk(this.leftLeg, walkSpeed, walkDegree, true, 2.0f, -0.4f, limbSwing, limbSwingAmount * clingSpeed * feetStill);
        this.swing(this.body, walkSpeed, walkDegree * 0.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount * clingSpeed);
        this.swing(this.head, walkSpeed, walkDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount * clingSpeed);
        this.swing(this.head, 10.0f, 0.05f, false, 0.0f, 0.0f, ageInTicks, clapProgress * 0.2f);
        this.flap(this.leftLeg, 0.8f, 0.5f, false, 0.0f, -0.45f, ageInTicks, clapProgress * 0.2f);
        this.flap(this.rightLeg, 0.8f, 0.5f, true, 0.0f, -0.45f, ageInTicks, clapProgress * 0.2f);
        this.swing(this.leftLeg, 0.8f, 0.35f, false, 0.0f, -0.15f, ageInTicks, clapProgress * 0.2f);
        this.swing(this.rightLeg, 0.8f, 0.35f, true, 0.0f, -0.15f, ageInTicks, clapProgress * 0.2f);
        this.bob(this.body, idleSpeed * 3.0f, -idleDegree * 5.0f, false, ageInTicks, clingSpeed);
        this.bob(this.rightArmPivot, idleSpeed * 3.0f, idleDegree * 8.0f, false, ageInTicks, clingSpeed);
        this.bob(this.leftArmPivot, idleSpeed * 3.0f, idleDegree * 8.0f, false, ageInTicks, clingSpeed);
        this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.head});
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
