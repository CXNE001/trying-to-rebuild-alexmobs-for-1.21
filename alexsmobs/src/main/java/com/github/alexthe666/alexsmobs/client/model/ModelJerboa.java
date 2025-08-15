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

import com.github.alexthe666.alexsmobs.entity.EntityJerboa;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelJerboa
extends AdvancedEntityModel<EntityJerboa> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox leftEar;
    private final AdvancedModelBox rightEar;
    private final AdvancedModelBox leftArm;
    private final AdvancedModelBox rightArm;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox legs;
    private final AdvancedModelBox feet;

    public ModelJerboa() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -7.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 19).addBox(-2.5f, -2.0f, -4.0f, 5.0f, 4.0f, 8.0f, 0.0f, false);
        this.leftEar = new AdvancedModelBox((AdvancedEntityModel)this, "leftEar");
        this.leftEar.setRotationPoint(2.0f, -1.0f, -2.0f);
        this.body.addChild((BasicModelPart)this.leftEar);
        this.setRotationAngle(this.leftEar, -0.6981f, -0.6545f, 1.0036f);
        this.leftEar.setTextureOffset(0, 0).addBox(-2.0f, -5.0f, 0.0f, 4.0f, 5.0f, 0.0f, 0.0f, false);
        this.rightEar = new AdvancedModelBox((AdvancedEntityModel)this, "rightEar");
        this.rightEar.setRotationPoint(-2.0f, -1.0f, -2.0f);
        this.body.addChild((BasicModelPart)this.rightEar);
        this.setRotationAngle(this.rightEar, -0.6981f, 0.6545f, -1.0036f);
        this.rightEar.setTextureOffset(0, 0).addBox(-2.0f, -5.0f, 0.0f, 4.0f, 5.0f, 0.0f, 0.0f, true);
        this.leftArm = new AdvancedModelBox((AdvancedEntityModel)this, "leftArm");
        this.leftArm.setRotationPoint(1.0f, 2.0f, -2.0f);
        this.body.addChild((BasicModelPart)this.leftArm);
        this.setRotationAngle(this.leftArm, 1.309f, 0.0f, 0.0f);
        this.leftArm.setTextureOffset(0, 6).addBox(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, 0.0f, false);
        this.rightArm = new AdvancedModelBox((AdvancedEntityModel)this, "rightArm");
        this.rightArm.setRotationPoint(-1.0f, 2.0f, -2.0f);
        this.body.addChild((BasicModelPart)this.rightArm);
        this.setRotationAngle(this.rightArm, 1.309f, 0.0f, 0.0f);
        this.rightArm.setTextureOffset(0, 6).addBox(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, 1.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.setRotationAngle(this.tail, -0.3491f, 0.0f, 0.0f);
        this.tail.setTextureOffset(0, 0).addBox(-1.5f, -6.0f, 0.0f, 3.0f, 6.0f, 12.0f, 0.0f, false);
        this.legs = new AdvancedModelBox((AdvancedEntityModel)this, "legs");
        this.legs.setRotationPoint(0.0f, 1.9f, 2.8f);
        this.body.addChild((BasicModelPart)this.legs);
        this.setRotationAngle(this.legs, 0.5236f, 0.0f, 0.0f);
        this.legs.setTextureOffset(19, 0).addBox(-2.0f, 0.0f, -5.0f, 4.0f, 3.0f, 5.0f, 0.0f, false);
        this.feet = new AdvancedModelBox((AdvancedEntityModel)this, "feet");
        this.feet.setRotationPoint(0.0f, 3.0f, -5.0f);
        this.legs.addChild((BasicModelPart)this.feet);
        this.setRotationAngle(this.feet, -0.5236f, 0.0f, 0.0f);
        this.feet.setTextureOffset(19, 9).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 0.0f, 2.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.leftEar, (Object)this.rightEar, (Object)this.leftArm, (Object)this.rightArm, (Object)this.tail, (Object)this.legs, (Object)this.feet);
    }

    public void setupAnim(EntityJerboa entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float partialTicks = ageInTicks - (float)entity.f_19797_;
        float idleSpeed = 0.1f;
        float idleDegree = 0.2f;
        float sleepProgress = entity.prevSleepProgress + (entity.sleepProgress - entity.prevSleepProgress) * partialTicks;
        float reboundProgress = entity.prevReboundProgress + (entity.reboundProgress - entity.prevReboundProgress) * partialTicks;
        float jumpProgress = Math.max(0.0f, entity.prevJumpProgress + (entity.jumpProgress - entity.prevJumpProgress) * partialTicks - reboundProgress);
        float begProgress = entity.prevBegProgress + (entity.begProgress - entity.prevBegProgress) * partialTicks;
        this.walk(this.leftArm, idleSpeed, idleDegree, true, 2.0f, 0.3f, ageInTicks, 1.0f);
        this.walk(this.rightArm, idleSpeed, idleDegree, true, 2.0f, 0.3f, ageInTicks, 1.0f);
        this.walk(this.tail, idleSpeed, idleDegree * 0.5f, true, 1.0f, -0.05f, ageInTicks, 1.0f);
        this.walk(this.leftEar, idleSpeed, idleDegree * 0.5f, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.rightEar, idleSpeed, idleDegree * 0.5f, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.progressRotationPrev(this.legs, jumpProgress, Maths.rad(65.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, jumpProgress, Maths.rad(-5.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, jumpProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, jumpProgress, 0.0f, -2.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legs, reboundProgress, Maths.rad(-30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, reboundProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, reboundProgress, Maths.rad(35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leftEar, reboundProgress, 0.0f, Maths.rad(35.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.rightEar, reboundProgress, 0.0f, Maths.rad(-35.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.body, reboundProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, sleepProgress, 0.0f, 5.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.legs, sleepProgress, 0.0f, -2.2f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legs, sleepProgress, Maths.rad(-30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, sleepProgress, Maths.rad(50.0), 0.0f, Maths.rad(90.0), 5.0f);
        this.progressRotationPrev(this.leftEar, sleepProgress, 0.0f, Maths.rad(35.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.rightEar, sleepProgress, 0.0f, Maths.rad(-35.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.leftArm, begProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.rightArm, begProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        if (begProgress > 0.0f) {
            float f = this.body.rotateAngleX;
            this.walk(this.body, 0.7f, 0.1f, false, 2.0f, -0.7f, ageInTicks, begProgress * 0.2f);
            float f1 = this.body.rotateAngleX - f;
            this.legs.rotateAngleX -= f1;
            this.tail.rotateAngleX -= f1;
            this.legs.rotationPointY += f1 * 3.0f;
            this.walk(this.rightArm, 0.7f, 1.2f, false, 0.0f, -1.0f, ageInTicks, begProgress * 0.2f);
            this.flap(this.rightArm, 0.7f, 0.25f, false, -1.0f, 0.2f, ageInTicks, begProgress * 0.2f);
            this.walk(this.leftArm, 0.7f, 1.2f, false, 0.0f, -1.0f, ageInTicks, begProgress * 0.2f);
            this.flap(this.leftArm, 0.7f, 0.25f, true, -1.0f, 0.2f, ageInTicks, begProgress * 0.2f);
        }
        float headY = netHeadYaw * 0.35f * ((float)Math.PI / 180);
        float headZ = headPitch * 0.65f * ((float)Math.PI / 180);
        if (Math.max(sleepProgress, begProgress) == 0.0f) {
            this.body.rotateAngleY += headY;
            this.legs.rotateAngleY -= headY * 0.6f;
            this.body.rotateAngleX += headZ;
            this.legs.rotateAngleX -= headZ;
            this.tail.rotateAngleX -= headZ * 1.2f;
            if (headPitch > 0.0f) {
                this.body.rotationPointY += Math.abs(headPitch) * 0.015f;
                this.legs.rotationPointZ -= Math.abs(headPitch) * 0.02f;
            } else {
                this.legs.rotationPointY -= Math.abs(headPitch) * 0.0225f;
                this.legs.rotationPointZ -= Math.abs(headPitch) * 0.015f;
            }
        }
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.75f;
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.65f, 0.65f, 0.65f);
            matrixStackIn.m_85837_(0.0, 0.815, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
