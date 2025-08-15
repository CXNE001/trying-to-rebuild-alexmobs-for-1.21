/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCaiman;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelCaiman
extends AdvancedEntityModel<EntityCaiman> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox leftLeg;
    private final AdvancedModelBox leftFoot;
    private final AdvancedModelBox rightLeg;
    private final AdvancedModelBox rightFoot;
    private final AdvancedModelBox leftArm;
    private final AdvancedModelBox leftHand;
    private final AdvancedModelBox rightArm;
    private final AdvancedModelBox rightHand;
    private final AdvancedModelBox tail1;
    private final AdvancedModelBox tail2;
    private final AdvancedModelBox tail3;
    private final AdvancedModelBox head;
    private final AdvancedModelBox bottomJaw;
    private final AdvancedModelBox topJaw;

    public ModelCaiman() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this);
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this);
        this.body.setRotationPoint(0.0f, -4.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-4.0f, -3.0f, -8.0f, 8.0f, 6.0f, 16.0f, 0.0f, false);
        this.leftLeg = new AdvancedModelBox((AdvancedEntityModel)this);
        this.leftLeg.setRotationPoint(4.0f, 1.0f, 6.0f);
        this.body.addChild((BasicModelPart)this.leftLeg);
        this.leftLeg.setTextureOffset(56, 13).addBox(-1.0f, -1.0f, -4.0f, 3.0f, 4.0f, 5.0f, 0.0f, false);
        this.leftFoot = new AdvancedModelBox((AdvancedEntityModel)this);
        this.leftFoot.setRotationPoint(1.0f, 3.0f, -3.0f);
        this.leftLeg.addChild((BasicModelPart)this.leftFoot);
        this.leftFoot.setTextureOffset(26, 55).addBox(-2.0f, -0.01f, -4.0f, 5.0f, 0.0f, 5.0f, 0.0f, false);
        this.rightLeg = new AdvancedModelBox((AdvancedEntityModel)this);
        this.rightLeg.setRotationPoint(-4.0f, 1.0f, 6.0f);
        this.body.addChild((BasicModelPart)this.rightLeg);
        this.rightLeg.setTextureOffset(56, 13).addBox(-2.0f, -1.0f, -4.0f, 3.0f, 4.0f, 5.0f, 0.0f, true);
        this.rightFoot = new AdvancedModelBox((AdvancedEntityModel)this);
        this.rightFoot.setRotationPoint(-1.0f, 3.0f, -3.0f);
        this.rightLeg.addChild((BasicModelPart)this.rightFoot);
        this.rightFoot.setTextureOffset(26, 55).addBox(-3.0f, -0.01f, -4.0f, 5.0f, 0.0f, 5.0f, 0.0f, true);
        this.leftArm = new AdvancedModelBox((AdvancedEntityModel)this);
        this.leftArm.setRotationPoint(4.2f, 0.2f, -5.5f);
        this.body.addChild((BasicModelPart)this.leftArm);
        this.leftArm.setTextureOffset(0, 0).addBox(-1.2f, -1.2f, -1.5f, 3.0f, 5.0f, 3.0f, 0.0f, false);
        this.leftHand = new AdvancedModelBox((AdvancedEntityModel)this);
        this.leftHand.setRotationPoint(0.3f, 3.8f, -0.5f);
        this.leftArm.addChild((BasicModelPart)this.leftHand);
        this.leftHand.setTextureOffset(55, 39).addBox(-2.5f, -0.01f, -4.0f, 5.0f, 0.0f, 5.0f, 0.0f, false);
        this.rightArm = new AdvancedModelBox((AdvancedEntityModel)this);
        this.rightArm.setRotationPoint(-4.2f, 0.2f, -5.5f);
        this.body.addChild((BasicModelPart)this.rightArm);
        this.rightArm.setTextureOffset(0, 0).addBox(-1.8f, -1.2f, -1.5f, 3.0f, 5.0f, 3.0f, 0.0f, true);
        this.rightHand = new AdvancedModelBox((AdvancedEntityModel)this);
        this.rightHand.setRotationPoint(-0.3f, 3.8f, -0.5f);
        this.rightArm.addChild((BasicModelPart)this.rightHand);
        this.rightHand.setTextureOffset(55, 39).addBox(-2.5f, -0.01f, -4.0f, 5.0f, 0.0f, 5.0f, 0.0f, true);
        this.tail1 = new AdvancedModelBox((AdvancedEntityModel)this);
        this.tail1.setRotationPoint(0.0f, 0.0f, 7.0f);
        this.body.addChild((BasicModelPart)this.tail1);
        this.tail1.setTextureOffset(0, 23).addBox(-3.0f, -2.0f, 1.0f, 6.0f, 5.0f, 11.0f, 0.0f, false);
        this.tail1.setTextureOffset(24, 23).addBox(-2.0f, -3.0f, 7.0f, 4.0f, 1.0f, 5.0f, 0.0f, false);
        this.tail1.setTextureOffset(50, 28).addBox(-4.0f, -2.0f, 1.0f, 8.0f, 0.0f, 6.0f, 0.0f, false);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this);
        this.tail2.setRotationPoint(0.0f, 1.0f, 12.0f);
        this.tail1.addChild((BasicModelPart)this.tail2);
        this.tail2.setTextureOffset(39, 13).addBox(-1.5f, -2.0f, 0.0f, 3.0f, 4.0f, 10.0f, 0.0f, false);
        this.tail2.setTextureOffset(43, 45).addBox(-1.5f, -4.0f, 0.0f, 3.0f, 2.0f, 10.0f, 0.0f, false);
        this.tail3 = new AdvancedModelBox((AdvancedEntityModel)this);
        this.tail3.setRotationPoint(0.0f, 0.0f, 10.0f);
        this.tail2.addChild((BasicModelPart)this.tail3);
        this.tail3.setTextureOffset(15, 55).addBox(0.0f, -3.0f, 0.0f, 0.0f, 5.0f, 10.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this);
        this.head.setRotationPoint(0.0f, 0.0f, -10.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 52).addBox(-3.5f, -5.0f, -3.0f, 7.0f, 7.0f, 5.0f, 0.0f, false);
        this.head.setTextureOffset(58, 0).addBox(-2.5f, -6.0f, -3.0f, 5.0f, 1.0f, 3.0f, 0.0f, false);
        this.bottomJaw = new AdvancedModelBox((AdvancedEntityModel)this);
        this.bottomJaw.setRotationPoint(0.0f, -3.0f, -2.0f);
        this.head.addChild((BasicModelPart)this.bottomJaw);
        this.bottomJaw.setTextureOffset(0, 40).addBox(-3.0f, 0.0f, -10.0f, 6.0f, 2.0f, 9.0f, 0.0f, false);
        this.bottomJaw.setTextureOffset(22, 44).addBox(-3.0f, -1.0f, -10.0f, 6.0f, 1.0f, 9.0f, 0.0f, false);
        this.topJaw = new AdvancedModelBox((AdvancedEntityModel)this);
        this.topJaw.setRotationPoint(0.0f, -4.0f, -3.0f);
        this.head.addChild((BasicModelPart)this.topJaw);
        this.topJaw.setTextureOffset(33, 0).addBox(-3.5f, -1.0f, -10.0f, 7.0f, 2.0f, 10.0f, 0.0f, false);
        this.topJaw.setTextureOffset(25, 30).addBox(-3.5f, 1.0f, -10.0f, 7.0f, 3.0f, 10.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.bottomJaw, (Object)this.head, (Object)this.topJaw, (Object)this.tail1, (Object)this.tail2, (Object)this.tail3, (Object)this.leftLeg, (Object)this.leftFoot, (Object)this.rightLeg, (Object)this.rightFoot, (Object[])new AdvancedModelBox[]{this.rightArm, this.rightHand, this.leftArm, this.leftHand});
    }

    public void setupAnim(EntityCaiman entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.05f;
        float idleDegree = 0.1f;
        float walkSpeed = 1.0f;
        float walkDegree = 1.0f;
        float swimSpeed = 0.65f;
        float swimDegree = 0.65f;
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float swimProgress = (entity.prevSwimProgress + (entity.swimProgress - entity.prevSwimProgress) * partialTick) * 0.2f;
        float grabProgress = entity.prevHoldProgress + (entity.holdProgress - entity.prevHoldProgress) * partialTick;
        float vibrateProgress = (entity.prevVibrateProgress + (entity.vibrateProgress - entity.prevVibrateProgress) * partialTick) * 0.2f;
        float sitProgress = entity.prevSitProgress + (entity.sitProgress - entity.prevSitProgress) * partialTick;
        float walkAmount = (1.0f - swimProgress) * limbSwingAmount;
        float swimAmount = swimProgress * limbSwingAmount;
        this.progressRotationPrev(this.rightArm, swimProgress, Maths.rad(75.0), 0.0f, Maths.rad(60.0), 1.0f);
        this.progressRotationPrev(this.leftArm, swimProgress, Maths.rad(75.0), 0.0f, Maths.rad(-60.0), 1.0f);
        this.progressRotationPrev(this.rightLeg, swimProgress, Maths.rad(75.0), 0.0f, Maths.rad(60.0), 1.0f);
        this.progressRotationPrev(this.leftLeg, swimProgress, Maths.rad(75.0), 0.0f, Maths.rad(-60.0), 1.0f);
        this.progressPositionPrev(this.head, swimAmount, 0.0f, 2.0f, 0.0f, 1.0f);
        this.progressPositionPrev(this.head, entity.holdProgress, 0.0f, 0.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.bottomJaw, grabProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.topJaw, grabProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.bottomJaw, grabProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, sitProgress, Maths.rad(10.0), Maths.rad(-20.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.body, sitProgress, 0.0f, Maths.rad(10.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.tail1, sitProgress, -1.0f, 0.0f, -1.0f, 5.0f);
        this.progressRotationPrev(this.tail1, sitProgress, 0.0f, Maths.rad(40.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.tail2, sitProgress, 0.0f, Maths.rad(40.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.tail3, sitProgress, 0.0f, Maths.rad(50.0), 0.0f, 5.0f);
        this.bob(this.head, idleSpeed, idleDegree * 5.0f, false, ageInTicks, 1.0f);
        this.bob(this.body, 20.0f, 0.5f, false, ageInTicks, vibrateProgress);
        this.swing(this.body, 20.0f, 0.04f, false, 3.0f, 0.0f, ageInTicks, vibrateProgress);
        this.swing(this.head, 0.5f, 0.4f, true, 2.0f, 0.0f, ageInTicks, grabProgress * 0.2f);
        this.swing(this.body, 0.5f, 0.4f, false, 2.0f, 0.0f, ageInTicks, grabProgress * 0.2f);
        this.swing(this.tail1, 0.5f, 0.4f, false, 4.0f, 0.0f, ageInTicks, grabProgress * 0.2f);
        this.swing(this.tail2, 0.5f, 0.4f, false, 3.0f, 0.0f, ageInTicks, grabProgress * 0.2f);
        this.head.rotationPointX += this.walkValue(ageInTicks, grabProgress * 0.2f, 0.5f, 2.0f, 2.0f, false);
        this.swing(this.tail1, idleSpeed, idleDegree, false, 3.0f, 0.0f, ageInTicks, 1.0f);
        this.swing(this.tail2, idleSpeed, idleDegree, false, 2.0f, 0.0f, ageInTicks, 1.0f);
        this.swing(this.tail3, idleSpeed, idleDegree, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.body, walkSpeed, walkDegree * 0.1f, true, 1.0f, 0.0f, limbSwing, walkAmount);
        this.flap(this.head, walkSpeed, walkDegree * 0.1f, false, 1.0f, 0.0f, limbSwing, walkAmount);
        this.flap(this.leftLeg, walkSpeed, walkDegree * 0.1f, false, 1.0f, 0.0f, limbSwing, walkAmount);
        this.flap(this.rightLeg, walkSpeed, walkDegree * 0.1f, false, 1.0f, 0.0f, limbSwing, walkAmount);
        this.flap(this.leftArm, walkSpeed, walkDegree * 0.1f, false, 1.0f, 0.0f, limbSwing, walkAmount);
        this.flap(this.rightArm, walkSpeed, walkDegree * 0.1f, false, 1.0f, 0.0f, limbSwing, walkAmount);
        this.flap(this.tail1, walkSpeed, walkDegree * 0.1f, true, -1.0f, 0.0f, limbSwing, walkAmount);
        this.swing(this.tail1, walkSpeed, walkDegree * 0.3f, false, 0.0f, 0.0f, limbSwing, walkAmount);
        this.swing(this.tail2, walkSpeed, walkDegree * 0.3f, false, 1.0f, 0.0f, limbSwing, walkAmount);
        this.swing(this.tail3, walkSpeed, walkDegree * 0.3f, false, -1.0f, 0.0f, limbSwing, walkAmount);
        this.bob(this.head, walkSpeed, walkDegree * -1.0f, false, limbSwing, walkAmount);
        float bodyBob = this.walkValue(limbSwing, walkAmount, walkSpeed, 0.5f, 1.0f, true) - walkAmount * 2.0f;
        this.body.rotationPointY += bodyBob;
        this.walk(this.leftArm, walkSpeed, walkDegree * 0.4f, true, 0.0f, 0.0f, limbSwing, walkAmount);
        this.walk(this.leftHand, walkSpeed, walkDegree * 0.2f, true, -3.0f, 0.1f, limbSwing, walkAmount);
        this.leftArm.rotationPointY += Math.min(0.0f, this.walkValue(limbSwing, walkAmount, walkSpeed, -1.5f, 3.0f, false)) - bodyBob;
        this.leftArm.rotationPointZ += this.walkValue(limbSwing, walkAmount, walkSpeed, -1.5f, walkDegree * 3.0f, false);
        this.leftHand.rotationPointY += Math.min(0.0f, this.walkValue(limbSwing, walkAmount, walkSpeed, -2.5f, walkDegree * 1.0f, true));
        this.walk(this.rightArm, walkSpeed, walkDegree * 0.4f, false, 0.0f, 0.0f, limbSwing, walkAmount);
        this.walk(this.rightHand, walkSpeed, walkDegree * 0.2f, false, -3.0f, -0.1f, limbSwing, walkAmount);
        this.rightArm.rotationPointY += Math.min(0.0f, this.walkValue(limbSwing, walkAmount, walkSpeed, -1.5f, 3.0f, true)) - bodyBob;
        this.rightArm.rotationPointZ += this.walkValue(limbSwing, walkAmount, walkSpeed, -1.5f, walkDegree * 3.0f, true);
        this.rightHand.rotationPointY += Math.min(0.0f, this.walkValue(limbSwing, walkAmount, walkSpeed, -2.5f, walkDegree * 1.0f, false));
        this.walk(this.leftLeg, walkSpeed, walkDegree * 0.3f, false, 1.0f, 0.0f, limbSwing, walkAmount);
        this.walk(this.leftFoot, walkSpeed, walkDegree * 0.2f, false, -2.0f, -0.1f, limbSwing, walkAmount);
        this.leftLeg.rotationPointY += Math.min(0.0f, this.walkValue(limbSwing, walkAmount, walkSpeed, -0.5f, 3.0f, true)) - bodyBob;
        this.leftLeg.rotationPointZ += this.walkValue(limbSwing, walkAmount, walkSpeed, -0.5f, walkDegree * 3.0f, true);
        this.leftLeg.rotationPointY += Math.min(0.0f, this.walkValue(limbSwing, walkAmount, walkSpeed, -2.0f, walkDegree * 0.5f, false));
        this.walk(this.rightLeg, walkSpeed, walkDegree * 0.3f, true, 1.0f, 0.0f, limbSwing, walkAmount);
        this.walk(this.rightFoot, walkSpeed, walkDegree * 0.2f, true, -2.0f, 0.1f, limbSwing, walkAmount);
        this.rightLeg.rotationPointY += Math.min(0.0f, this.walkValue(limbSwing, walkAmount, walkSpeed, -0.5f, 3.0f, false)) - bodyBob;
        this.rightLeg.rotationPointZ += this.walkValue(limbSwing, walkAmount, walkSpeed, -0.5f, walkDegree * 3.0f, false);
        this.rightFoot.rotationPointY += Math.min(0.0f, this.walkValue(limbSwing, walkAmount, walkSpeed, -2.0f, walkDegree * 0.5f, true));
        this.walk(this.rightArm, swimSpeed, swimDegree, false, 0.0f, -0.25f, limbSwing, swimAmount);
        this.walk(this.leftArm, swimSpeed, swimDegree, false, 0.0f, -0.25f, limbSwing, swimAmount);
        this.walk(this.rightLeg, swimSpeed, swimDegree, true, 0.0f, 0.25f, limbSwing, swimAmount);
        this.walk(this.leftLeg, swimSpeed, swimDegree, true, 0.0f, 0.25f, limbSwing, swimAmount);
        this.swing(this.body, swimSpeed, swimDegree * 0.4f, false, 1.5f, 0.0f, limbSwing, swimAmount);
        this.swing(this.head, swimSpeed, swimDegree * 0.1f, true, 2.0f, 0.0f, limbSwing, swimAmount);
        this.chainSwing(new AdvancedModelBox[]{this.tail1, this.tail2, this.tail3}, swimSpeed, swimDegree * 1.0f, -2.5, limbSwing, swimAmount);
        this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.head});
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.25f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.25f, 0.25f, 0.25f);
            matrixStackIn.m_85837_(0.0, 4.5, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    private float walkValue(float limbSwing, float limbSwingAmount, float speed, float offset, float degree, boolean inverse) {
        return (float)(Math.cos(limbSwing * speed + offset) * (double)degree * (double)limbSwingAmount * (double)(inverse ? -1 : 1));
    }
}
