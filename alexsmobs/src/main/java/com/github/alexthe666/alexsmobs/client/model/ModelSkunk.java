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

import com.github.alexthe666.alexsmobs.entity.EntitySkunk;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelSkunk
extends AdvancedEntityModel<EntitySkunk> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox leftLeg;
    private final AdvancedModelBox rightLeg;
    private final AdvancedModelBox leftArm;
    private final AdvancedModelBox rightArm;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox head;

    public ModelSkunk() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -3.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-3.5f, -4.0f, -4.5f, 7.0f, 6.0f, 9.0f, 0.0f, false);
        this.leftLeg = new AdvancedModelBox((AdvancedEntityModel)this, "leftLeg");
        this.leftLeg.setRotationPoint(4.0f, 2.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.leftLeg);
        this.setRotationAngle(this.leftLeg, 0.0f, -0.7418f, 0.0f);
        this.leftLeg.setTextureOffset(0, 33).addBox(-1.0f, -1.0f, -3.0f, 2.0f, 2.0f, 4.0f, 0.0f, false);
        this.rightLeg = new AdvancedModelBox((AdvancedEntityModel)this, "rightLeg");
        this.rightLeg.setRotationPoint(-4.0f, 2.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.rightLeg);
        this.setRotationAngle(this.rightLeg, 0.0f, 0.7418f, 0.0f);
        this.rightLeg.setTextureOffset(0, 33).addBox(-1.0f, -1.0f, -3.0f, 2.0f, 2.0f, 4.0f, 0.0f, true);
        this.leftArm = new AdvancedModelBox((AdvancedEntityModel)this, "leftArm");
        this.leftArm.setRotationPoint(3.5f, 2.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.leftArm);
        this.setRotationAngle(this.leftArm, 0.0f, -0.5672f, 0.0f);
        this.leftArm.setTextureOffset(32, 31).addBox(-1.0f, -1.0f, -3.0f, 2.0f, 2.0f, 4.0f, 0.0f, false);
        this.rightArm = new AdvancedModelBox((AdvancedEntityModel)this, "rightArm");
        this.rightArm.setRotationPoint(-3.5f, 2.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.rightArm);
        this.setRotationAngle(this.rightArm, 0.0f, 0.5672f, 0.0f);
        this.rightArm.setTextureOffset(32, 31).addBox(-1.0f, -1.0f, -3.0f, 2.0f, 2.0f, 4.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, -1.0f, 4.5f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 16).addBox(-3.0f, -10.0f, 0.0f, 6.0f, 12.0f, 4.0f, 0.0f, false);
        this.tail.setTextureOffset(21, 16).addBox(-3.0f, -10.0f, 4.0f, 6.0f, 7.0f, 5.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, 0.0f, -5.5f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(24, 0).addBox(-3.0f, -2.0f, -3.0f, 6.0f, 4.0f, 4.0f, 0.0f, false);
        this.head.setTextureOffset(21, 29).addBox(-2.0f, 0.0f, -6.0f, 4.0f, 2.0f, 3.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setupAnim(EntitySkunk entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.1f;
        float idleDegree = 0.15f;
        float walkSpeed = 1.25f;
        float walkDegree = 0.5f;
        float partialTicks = ageInTicks - (float)entity.f_19797_;
        float sprayProgress = entity.prevSprayProgress + (entity.sprayProgress - entity.prevSprayProgress) * partialTicks;
        float legsStill = Math.max(sprayProgress * 0.2f, limbSwingAmount);
        this.progressRotationPrev(this.leftArm, sprayProgress, Maths.rad(80.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.rightArm, sprayProgress, Maths.rad(80.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leftLeg, sprayProgress, Maths.rad(100.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.rightLeg, sprayProgress, Maths.rad(100.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, sprayProgress, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, sprayProgress, 0.0f, -2.4f, 0.0f, 5.0f);
        this.progressPositionPrev(this.tail, sprayProgress, 0.0f, -2.0f, -1.0f, 5.0f);
        this.walk(this.body, 0.5f, 0.2f, true, 4.0f, 0.0f, ageInTicks, sprayProgress * 0.2f);
        this.swing(this.body, 0.5f, 0.2f, true, 1.5f, 0.0f, ageInTicks, sprayProgress * 0.2f);
        this.walk(this.head, 0.5f, 0.2f, false, 4.0f, 0.0f, ageInTicks, sprayProgress * 0.2f);
        this.swing(this.head, 0.5f, 0.2f, false, 1.5f, 0.0f, ageInTicks, sprayProgress * 0.2f);
        this.walk(this.leftArm, 0.5f, 0.2f, false, 4.0f, 0.0f, ageInTicks, sprayProgress * 0.2f);
        this.swing(this.leftArm, 0.5f, 0.2f, false, 1.5f, 0.0f, ageInTicks, sprayProgress * 0.2f);
        this.walk(this.rightArm, 0.5f, 0.2f, false, 4.0f, 0.0f, ageInTicks, sprayProgress * 0.2f);
        this.swing(this.rightArm, 0.5f, 0.2f, false, 1.5f, 0.0f, ageInTicks, sprayProgress * 0.2f);
        this.walk(this.leftLeg, 0.5f, 0.2f, false, 4.0f, 0.0f, ageInTicks, sprayProgress * 0.2f);
        this.swing(this.leftLeg, 0.5f, 0.2f, false, 1.5f, 0.0f, ageInTicks, sprayProgress * 0.2f);
        this.walk(this.rightLeg, 0.5f, 0.2f, false, 4.0f, 0.0f, ageInTicks, sprayProgress * 0.2f);
        this.swing(this.rightLeg, 0.5f, 0.2f, false, 1.5f, 0.0f, ageInTicks, sprayProgress * 0.2f);
        this.flap(this.tail, 0.5f, 0.5f, false, 2.5f, 0.0f, ageInTicks, sprayProgress * 0.2f);
        this.walk(this.tail, idleSpeed, idleDegree, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.progressRotationPrev(this.leftArm, Math.min(legsStill, 0.5f), 0.0f, Maths.rad(30.0), 0.0f, 0.5f);
        this.progressRotationPrev(this.rightArm, Math.min(legsStill, 0.5f), 0.0f, Maths.rad(-30.0), 0.0f, 0.5f);
        this.progressRotationPrev(this.leftLeg, Math.min(legsStill, 0.5f), 0.0f, Maths.rad(40.0), 0.0f, 0.5f);
        this.progressRotationPrev(this.rightLeg, Math.min(legsStill, 0.5f), 0.0f, Maths.rad(-40.0), 0.0f, 0.5f);
        this.progressPositionPrev(this.head, Math.min(legsStill, 0.5f), 0.0f, -1.0f, 0.0f, 0.5f);
        this.swing(this.body, walkSpeed, walkDegree * 0.5f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.head, walkSpeed, walkDegree * 0.5f, true, 2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.tail, walkSpeed, walkDegree * 0.5f, false, 4.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.tail, walkSpeed, walkDegree * 0.2f, true, 2.0f, 0.3f, limbSwing, limbSwingAmount);
        this.walk(this.leftArm, walkSpeed, walkDegree * 1.2f, true, -2.5f, -0.2f, limbSwing, limbSwingAmount);
        this.walk(this.rightArm, walkSpeed, walkDegree * 1.2f, false, -2.5f, 0.2f, limbSwing, limbSwingAmount);
        this.walk(this.rightLeg, walkSpeed, walkDegree * 1.2f, true, -2.5f, -0.2f, limbSwing, limbSwingAmount);
        this.walk(this.leftLeg, walkSpeed, walkDegree * 1.2f, false, -2.5f, 0.2f, limbSwing, limbSwingAmount);
        this.flap(this.body, walkSpeed, walkSpeed * 0.3f, false, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.rightLeg, walkSpeed, walkSpeed * 0.3f, true, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.leftLeg, walkSpeed, walkSpeed * 0.3f, true, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.rightArm, walkSpeed, walkSpeed * 0.3f, true, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.leftArm, walkSpeed, walkSpeed * 0.3f, true, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.head, walkSpeed, walkSpeed * 0.3f, true, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.tail, walkSpeed, walkSpeed * 0.2f, true, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.faceTarget(netHeadYaw, headPitch, 1.2f, new AdvancedModelBox[]{this.head});
        float leftLegS = (float)(Math.sin((double)(limbSwing * walkSpeed) - 2.5) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
        float rightLegS = (float)(Math.sin(-((double)(limbSwing * walkSpeed)) + 2.5) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
        this.rightArm.rotationPointY += 3.0f * leftLegS;
        this.leftArm.rotationPointY += 3.0f * rightLegS;
        this.rightArm.rotationPointZ += 1.0f * leftLegS;
        this.leftArm.rotationPointZ += 1.0f * rightLegS;
        this.leftLeg.rotationPointY += 3.0f * leftLegS;
        this.rightLeg.rotationPointY += 3.0f * rightLegS;
        this.leftLeg.rotationPointZ += 1.0f * leftLegS;
        this.rightLeg.rotationPointZ += 1.0f * rightLegS;
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.leftArm, (Object)this.rightArm, (Object)this.leftLeg, (Object)this.rightLeg, (Object)this.tail, (Object)this.head);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            this.head.setScale(1.5f, 1.5f, 1.5f);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.65f, 0.65f, 0.65f);
            matrixStackIn.m_85837_(0.0, 0.815, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        } else {
            this.head.setScale(1.0f, 1.0f, 1.0f);
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }
}
