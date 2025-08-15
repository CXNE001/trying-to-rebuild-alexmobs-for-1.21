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

import com.github.alexthe666.alexsmobs.entity.EntitySugarGlider;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelSugarGlider
extends AdvancedEntityModel<EntitySugarGlider> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox leftArm;
    private final AdvancedModelBox rightArm;
    private final AdvancedModelBox leftLeg;
    private final AdvancedModelBox rightLeg;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox head;
    private final AdvancedModelBox leftEar;
    private final AdvancedModelBox rightEar;

    public ModelSugarGlider() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -2.0f, -1.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-2.0f, -1.0f, -3.0f, 4.0f, 3.0f, 7.0f, 0.0f, false);
        this.leftArm = new AdvancedModelBox((AdvancedEntityModel)this, "leftArm");
        this.leftArm.setRotationPoint(1.0f, 1.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.leftArm);
        this.setRotationAngle(this.leftArm, 0.0f, 0.0f, 0.20944f);
        this.leftArm.setTextureOffset(12, 11).addBox(-1.0f, 0.0f, -2.0f, 6.0f, 0.0f, 6.0f, 0.0f, false);
        this.rightArm = new AdvancedModelBox((AdvancedEntityModel)this, "rightArm");
        this.rightArm.setRotationPoint(-1.0f, 1.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.rightArm);
        this.setRotationAngle(this.rightArm, 0.0f, 0.0f, -0.20944f);
        this.rightArm.setTextureOffset(12, 11).addBox(-5.0f, 0.0f, -2.0f, 6.0f, 0.0f, 6.0f, 0.0f, true);
        this.leftLeg = new AdvancedModelBox((AdvancedEntityModel)this, "leftLeg");
        this.leftLeg.setRotationPoint(1.0f, 1.0f, 3.0f);
        this.body.addChild((BasicModelPart)this.leftLeg);
        this.setRotationAngle(this.leftLeg, 0.0f, 0.0f, 0.20944f);
        this.leftLeg.setTextureOffset(15, 0).addBox(-1.0f, 0.0f, -2.0f, 6.0f, 0.0f, 5.0f, 0.0f, false);
        this.rightLeg = new AdvancedModelBox((AdvancedEntityModel)this, "rightLeg");
        this.rightLeg.setRotationPoint(-1.0f, 1.0f, 3.0f);
        this.body.addChild((BasicModelPart)this.rightLeg);
        this.setRotationAngle(this.rightLeg, 0.0f, 0.0f, -0.20944f);
        this.rightLeg.setTextureOffset(15, 0).addBox(-5.0f, 0.0f, -2.0f, 6.0f, 0.0f, 5.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, 0.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 11).addBox(-1.0f, -1.0f, 0.0f, 2.0f, 2.0f, 8.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, 0.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(17, 18).addBox(-2.5f, -2.0f, -4.0f, 5.0f, 4.0f, 4.0f, 0.0f, false);
        this.head.setTextureOffset(0, 22).addBox(-1.5f, 0.0f, -5.0f, 3.0f, 2.0f, 1.0f, 0.0f, false);
        this.leftEar = new AdvancedModelBox((AdvancedEntityModel)this, "leftEar");
        this.leftEar.setRotationPoint(2.2f, -1.6f, -2.9f);
        this.head.addChild((BasicModelPart)this.leftEar);
        this.setRotationAngle(this.leftEar, 0.0f, -0.6109f, 0.0f);
        this.leftEar.setTextureOffset(0, 0).addBox(0.0f, -2.0f, 0.0f, 2.0f, 2.0f, 1.0f, 0.0f, false);
        this.rightEar = new AdvancedModelBox((AdvancedEntityModel)this, "rightEar");
        this.rightEar.setRotationPoint(-2.2f, -1.6f, -2.9f);
        this.head.addChild((BasicModelPart)this.rightEar);
        this.setRotationAngle(this.rightEar, 0.0f, 0.6109f, 0.0f);
        this.rightEar.setTextureOffset(0, 0).addBox(-2.0f, -2.0f, 0.0f, 2.0f, 2.0f, 1.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.head, (Object)this.leftArm, (Object)this.rightArm, (Object)this.leftEar, (Object)this.rightEar, (Object)this.tail, (Object)this.leftLeg, (Object)this.rightLeg);
    }

    public void setupAnim(EntitySugarGlider entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.1f;
        float idleDegree = 0.25f;
        float walkSpeed = 0.9f;
        float walkDegree = 0.5f;
        float glideSpeed = 1.3f;
        float glideDegree = 0.6f;
        float partialTick = ageInTicks - (float)entityIn.f_19797_;
        float glideProgress = entityIn.prevGlideProgress + (entityIn.glideProgress - entityIn.prevGlideProgress) * partialTick;
        float sitProgress = entityIn.prevSitProgress + (entityIn.sitProgress - entityIn.prevSitProgress) * partialTick;
        float forageProgress = entityIn.forageProgress + (entityIn.forageProgress - entityIn.prevForageProgress) * partialTick;
        float glideSwingAmount = glideProgress * 0.2f;
        float walkSwingAmount = (1.0f - glideSwingAmount) * limbSwingAmount;
        this.progressRotationPrev(this.body, glideProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, glideProgress, Maths.rad(12.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, glideProgress, Maths.rad(12.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leftArm, glideProgress, 0.0f, 0.0f, Maths.rad(-20.0), 5.0f);
        this.progressRotationPrev(this.leftLeg, glideProgress, 0.0f, 0.0f, Maths.rad(-20.0), 5.0f);
        this.progressRotationPrev(this.rightArm, glideProgress, 0.0f, 0.0f, Maths.rad(20.0), 5.0f);
        this.progressRotationPrev(this.rightLeg, glideProgress, 0.0f, 0.0f, Maths.rad(20.0), 5.0f);
        this.progressPositionPrev(this.body, glideProgress, 0.0f, -2.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.leftArm, glideProgress, 2.0f, 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.rightArm, glideProgress, -2.0f, 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.leftLeg, glideProgress, 2.0f, 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.rightLeg, glideProgress, -2.0f, 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, forageProgress, Maths.rad(35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, forageProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, forageProgress, 0.0f, -1.0f, 1.0f, 5.0f);
        this.progressRotationPrev(this.body, sitProgress, Maths.rad(-170.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, sitProgress, Maths.rad(-50.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, sitProgress, Maths.rad(150.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leftArm, sitProgress, 0.0f, 0.0f, Maths.rad(20.0), 5.0f);
        this.progressRotationPrev(this.leftLeg, sitProgress, 0.0f, 0.0f, Maths.rad(20.0), 5.0f);
        this.progressRotationPrev(this.rightArm, sitProgress, 0.0f, 0.0f, Maths.rad(-20.0), 5.0f);
        this.progressRotationPrev(this.rightLeg, sitProgress, 0.0f, 0.0f, Maths.rad(-20.0), 5.0f);
        this.progressPositionPrev(this.body, sitProgress, 0.0f, 1.0f, 1.0f, 5.0f);
        this.progressPositionPrev(this.head, sitProgress, 0.0f, 2.0f, -2.0f, 5.0f);
        this.flap(this.rightEar, idleSpeed, idleDegree, false, 0.0f, -0.05f, ageInTicks, 1.0f);
        this.flap(this.leftEar, idleSpeed, idleDegree, true, 0.0f, -0.05f, ageInTicks, 1.0f);
        this.swing(this.leftArm, walkSpeed, walkDegree, false, 1.5f, -0.2f, limbSwing, walkSwingAmount);
        this.swing(this.leftLeg, walkSpeed, walkDegree, true, 1.5f, -0.2f, limbSwing, walkSwingAmount);
        this.swing(this.rightArm, walkSpeed, walkDegree, false, 1.5f, -0.2f, limbSwing, walkSwingAmount);
        this.swing(this.rightLeg, walkSpeed, walkDegree, true, 1.5f, -0.2f, limbSwing, walkSwingAmount);
        this.swing(this.tail, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, walkSwingAmount);
        this.bob(this.head, walkSpeed * 0.5f, walkDegree, true, limbSwing, walkSwingAmount);
        this.flap(this.leftArm, glideSpeed, glideDegree * 0.1f, true, 0.0f, -0.05f, ageInTicks, glideSwingAmount);
        this.flap(this.leftLeg, glideSpeed, glideDegree * 0.1f, true, 0.0f, -0.05f, ageInTicks, glideSwingAmount);
        this.flap(this.rightArm, glideSpeed, glideDegree * 0.1f, false, 0.0f, 0.05f, ageInTicks, glideSwingAmount);
        this.flap(this.rightLeg, glideSpeed, glideDegree * 0.1f, false, 0.0f, 0.05f, ageInTicks, glideSwingAmount);
        this.swing(this.head, glideSpeed * 0.2f, glideDegree * 0.4f, false, 0.0f, 0.0f, ageInTicks, glideSwingAmount);
        this.swing(this.body, glideSpeed * 0.2f, glideDegree * 0.4f, true, 1.0f, 0.0f, ageInTicks, glideSwingAmount);
        this.swing(this.tail, glideSpeed * 0.2f, glideDegree, true, -1.0f, 0.0f, ageInTicks, glideSwingAmount);
        this.bob(this.head, 1.0f, 0.6f, false, ageInTicks, forageProgress * 0.2f);
        this.swing(this.head, 0.5f, 0.6f, true, -1.0f, 0.0f, ageInTicks, forageProgress * 0.2f);
        if (forageProgress == 0.0f) {
            this.faceTarget(netHeadYaw, headPitch, 1.2f, new AdvancedModelBox[]{this.head});
        }
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.35f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.0);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
        } else {
            this.head.setScale(1.0f, 1.0f, 1.0f);
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
