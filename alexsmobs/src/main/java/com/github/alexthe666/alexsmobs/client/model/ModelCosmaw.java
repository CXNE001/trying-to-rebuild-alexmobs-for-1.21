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
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.Mth
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCosmaw;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelCosmaw
extends AdvancedEntityModel<EntityCosmaw> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox leftArm;
    public final AdvancedModelBox rightArm;
    public final AdvancedModelBox leftFin;
    public final AdvancedModelBox rightFin;
    public final AdvancedModelBox mouthArm1;
    public final AdvancedModelBox mouthArm2;
    public final AdvancedModelBox mouth;
    public final AdvancedModelBox topJaw;
    public final AdvancedModelBox lowerJaw;
    public final AdvancedModelBox eyesBase;
    public final AdvancedModelBox leftEye;
    public final AdvancedModelBox rightEye;
    public final AdvancedModelBox tail;
    public final AdvancedModelBox leftLeg;
    public final AdvancedModelBox rightLeg;
    public final AdvancedModelBox tailFin;

    public ModelCosmaw() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -10.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-6.5f, -7.0f, -20.0f, 13.0f, 14.0f, 32.0f, 0.0f, false);
        this.leftArm = new AdvancedModelBox((AdvancedEntityModel)this, "leftArm");
        this.leftArm.setRotationPoint(5.0f, 7.0f, -12.2f);
        this.body.addChild((BasicModelPart)this.leftArm);
        this.leftArm.setTextureOffset(17, 47).addBox(-1.0f, 0.0f, -0.8f, 2.0f, 4.0f, 3.0f, 0.0f, false);
        this.leftArm.setTextureOffset(0, 13).addBox(0.0f, 2.0f, 1.2f, 0.0f, 4.0f, 3.0f, 0.0f, false);
        this.rightArm = new AdvancedModelBox((AdvancedEntityModel)this, "rightArm");
        this.rightArm.setRotationPoint(-5.0f, 7.0f, -12.2f);
        this.body.addChild((BasicModelPart)this.rightArm);
        this.rightArm.setTextureOffset(17, 47).addBox(-1.0f, 0.0f, -0.8f, 2.0f, 4.0f, 3.0f, 0.0f, true);
        this.rightArm.setTextureOffset(0, 13).addBox(0.0f, 2.0f, 1.2f, 0.0f, 4.0f, 3.0f, 0.0f, true);
        this.leftFin = new AdvancedModelBox((AdvancedEntityModel)this, "leftFin");
        this.leftFin.setRotationPoint(4.5f, -6.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.leftFin);
        this.leftFin.setTextureOffset(33, 47).addBox(0.0f, 0.0f, -5.0f, 9.0f, 0.0f, 27.0f, 0.0f, false);
        this.rightFin = new AdvancedModelBox((AdvancedEntityModel)this, "rightFin");
        this.rightFin.setRotationPoint(-4.5f, -6.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.rightFin);
        this.rightFin.setTextureOffset(33, 47).addBox(-9.0f, 0.0f, -5.0f, 9.0f, 0.0f, 27.0f, 0.0f, true);
        this.mouthArm1 = new AdvancedModelBox((AdvancedEntityModel)this, "mouthArm1");
        this.mouthArm1.setRotationPoint(0.0f, -3.0f, -20.0f);
        this.body.addChild((BasicModelPart)this.mouthArm1);
        this.setRotationAngle(this.mouthArm1, 1.0908f, 0.0f, 0.0f);
        this.mouthArm1.setTextureOffset(65, 75).addBox(-2.0f, -1.0f, -20.0f, 4.0f, 4.0f, 22.0f, 0.0f, false);
        this.mouthArm2 = new AdvancedModelBox((AdvancedEntityModel)this, "mouthArm2");
        this.mouthArm2.setRotationPoint(0.0f, 3.0f, -20.0f);
        this.mouthArm1.addChild((BasicModelPart)this.mouthArm2);
        this.setRotationAngle(this.mouthArm2, -1.0472f, 0.0f, 0.0f);
        this.mouthArm2.setTextureOffset(79, 32).addBox(-2.0f, -4.0f, -17.0f, 4.0f, 4.0f, 17.0f, -0.1f, false);
        this.mouth = new AdvancedModelBox((AdvancedEntityModel)this, "mouth");
        this.mouth.setRotationPoint(0.0f, -1.4f, -16.7f);
        this.mouthArm2.addChild((BasicModelPart)this.mouth);
        this.topJaw = new AdvancedModelBox((AdvancedEntityModel)this, "topJaw");
        this.topJaw.setRotationPoint(0.0f, 0.0f, -1.0f);
        this.mouth.addChild((BasicModelPart)this.topJaw);
        this.topJaw.setTextureOffset(0, 13).addBox(-2.5f, -3.0f, -7.0f, 5.0f, 3.0f, 8.0f, 0.0f, false);
        this.lowerJaw = new AdvancedModelBox((AdvancedEntityModel)this, "lowerJaw");
        this.lowerJaw.setRotationPoint(0.0f, -1.0f, 0.3f);
        this.mouth.addChild((BasicModelPart)this.lowerJaw);
        this.lowerJaw.setTextureOffset(0, 0).addBox(-3.0f, 0.0f, -9.0f, 6.0f, 3.0f, 9.0f, 0.0f, false);
        this.eyesBase = new AdvancedModelBox((AdvancedEntityModel)this, "eyesBase");
        this.eyesBase.setRotationPoint(0.0f, -7.0f, -11.0f);
        this.body.addChild((BasicModelPart)this.eyesBase);
        this.eyesBase.setTextureOffset(3, 69).addBox(-11.0f, -1.0f, -1.0f, 23.0f, 2.0f, 2.0f, 0.0f, false);
        this.leftEye = new AdvancedModelBox((AdvancedEntityModel)this, "leftEye");
        this.leftEye.setRotationPoint(13.0f, 0.0f, 0.0f);
        this.eyesBase.addChild((BasicModelPart)this.leftEye);
        this.leftEye.setTextureOffset(0, 47).addBox(-1.0f, -3.0f, -3.0f, 2.0f, 6.0f, 6.0f, 0.0f, false);
        this.rightEye = new AdvancedModelBox((AdvancedEntityModel)this, "rightEye");
        this.rightEye.setRotationPoint(-12.0f, 0.0f, 1.0f);
        this.eyesBase.addChild((BasicModelPart)this.rightEye);
        this.rightEye.setTextureOffset(0, 47).addBox(-1.0f, -3.0f, -4.0f, 2.0f, 6.0f, 6.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, -1.8f, 11.6f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(59, 0).addBox(-4.5f, -5.0f, 0.0f, 9.0f, 11.0f, 20.0f, 0.0f, false);
        this.leftLeg = new AdvancedModelBox((AdvancedEntityModel)this, "leftLeg");
        this.leftLeg.setRotationPoint(3.0f, 5.8f, 3.2f);
        this.tail.addChild((BasicModelPart)this.leftLeg);
        this.leftLeg.setTextureOffset(19, 13).addBox(-1.0f, 0.0f, -0.8f, 2.0f, 4.0f, 3.0f, 0.0f, false);
        this.leftLeg.setTextureOffset(0, 0).addBox(0.0f, 2.0f, 1.2f, 0.0f, 4.0f, 3.0f, 0.0f, false);
        this.rightLeg = new AdvancedModelBox((AdvancedEntityModel)this, "rightLeg");
        this.rightLeg.setRotationPoint(-3.0f, 5.8f, 3.2f);
        this.tail.addChild((BasicModelPart)this.rightLeg);
        this.rightLeg.setTextureOffset(19, 13).addBox(-1.0f, 0.0f, -0.8f, 2.0f, 4.0f, 3.0f, 0.0f, true);
        this.rightLeg.setTextureOffset(0, 0).addBox(0.0f, 2.0f, 1.2f, 0.0f, 4.0f, 3.0f, 0.0f, true);
        this.tailFin = new AdvancedModelBox((AdvancedEntityModel)this, "tailFin");
        this.tailFin.setRotationPoint(0.0f, 1.0f, 7.0f);
        this.tail.addChild((BasicModelPart)this.tailFin);
        this.tailFin.setTextureOffset(0, 47).addBox(0.0f, -10.0f, -3.0f, 0.0f, 19.0f, 32.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.tail, (Object)this.tailFin, (Object)this.body, (Object)this.leftLeg, (Object)this.rightLeg, (Object)this.eyesBase, (Object)this.leftEye, (Object)this.rightEye, (Object)this.leftArm, (Object)this.leftFin, (Object)this.rightArm, (Object[])new AdvancedModelBox[]{this.rightFin, this.mouth, this.mouthArm1, this.mouthArm2, this.lowerJaw, this.topJaw});
    }

    public void setupAnim(EntityCosmaw entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float walkSpeed = 0.7f;
        float walkDegree = 0.4f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.2f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float clutchProgress = entity.prevClutchProgress + (entity.clutchProgress - entity.prevClutchProgress) * partialTick;
        float biteProgress = entity.prevBiteProgress + (entity.biteProgress - entity.prevBiteProgress) * partialTick;
        float openProgress = Math.max(Math.max(entity.prevOpenProgress + (entity.openProgress - entity.prevOpenProgress) * partialTick, clutchProgress), biteProgress);
        float cosmawPitch = (float)(Math.toRadians(entity.getClampedCosmawPitch(partialTick)) * (double)(5.0f - clutchProgress) * (double)0.2f);
        float cosmawPitchPos = entity.getClampedCosmawPitch(partialTick) / 90.0f * (5.0f - clutchProgress) * 0.2f;
        this.body.rotateAngleX += cosmawPitch;
        this.eyesBase.rotateAngleX -= cosmawPitch;
        this.mouthArm1.rotateAngleX -= cosmawPitch * 0.2f;
        this.mouthArm2.rotateAngleX -= cosmawPitch * 1.7f;
        this.lowerJaw.rotateAngleX -= cosmawPitch * 0.3f;
        this.topJaw.rotateAngleX -= cosmawPitch * 0.3f;
        if (cosmawPitchPos > 0.0f) {
            this.mouthArm2.rotationPointY -= Math.min(cosmawPitchPos * 6.0f, 3.0f);
        } else {
            this.mouthArm2.rotationPointZ -= cosmawPitchPos * 3.0f;
            this.mouthArm2.rotationPointY += cosmawPitchPos;
        }
        this.walk(this.body, idleSpeed, idleDegree * 0.1f, false, -1.0f, 0.05f, ageInTicks, 1.0f);
        this.walk(this.tail, idleSpeed, idleDegree * -0.15f, true, -1.0f, 0.05f, ageInTicks, 1.0f);
        this.swing(this.leftFin, idleSpeed, idleDegree * 0.22f, false, -2.0f, 0.05f, ageInTicks, 1.0f);
        this.swing(this.rightFin, idleSpeed, idleDegree * 0.22f, true, -2.0f, 0.05f, ageInTicks, 1.0f);
        this.walk(this.eyesBase, idleSpeed, idleDegree * 0.1f, true, -1.0f, 0.05f, ageInTicks, 1.0f);
        this.walk(this.lowerJaw, idleSpeed, idleDegree * 0.2f, true, 1.0f, -0.05f, ageInTicks, 1.0f);
        this.walk(this.topJaw, idleSpeed, idleDegree * 0.2f, true, 1.0f, 0.05f, ageInTicks, 1.0f);
        this.walk(this.mouthArm1, idleSpeed, idleDegree * 0.4f, false, 2.0f, 0.05f, ageInTicks, 1.0f);
        this.walk(this.mouthArm2, idleSpeed, idleDegree * 0.6f, true, 2.0f, 0.05f, ageInTicks, 1.0f);
        this.bob(this.body, idleSpeed * 2.0f, idleDegree * 4.0f, false, ageInTicks, 1.0f);
        this.walk(this.leftArm, idleSpeed, idleDegree * 0.3f, false, -2.0f, -0.05f, ageInTicks, 1.0f);
        this.walk(this.rightArm, idleSpeed, idleDegree * 0.3f, false, -2.0f, -0.05f, ageInTicks, 1.0f);
        this.walk(this.leftLeg, idleSpeed, idleDegree * 0.3f, false, -3.0f, -0.05f, ageInTicks, 1.0f);
        this.walk(this.rightLeg, idleSpeed, idleDegree * 0.3f, false, -3.0f, -0.05f, ageInTicks, 1.0f);
        this.swing(this.tail, walkSpeed, walkDegree * 0.5f, false, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.tailFin, walkSpeed, walkDegree * 0.25f, false, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.leftFin, walkSpeed, walkDegree * 0.7f, false, -2.0f, 0.05f, limbSwing, limbSwingAmount);
        this.flap(this.rightFin, walkSpeed, walkDegree * 0.7f, true, -2.0f, 0.05f, limbSwing, limbSwingAmount);
        this.walk(this.leftArm, walkSpeed, walkDegree * 0.3f, false, -2.0f, -0.05f, limbSwing, limbSwingAmount);
        this.walk(this.rightArm, walkSpeed, walkDegree * 0.3f, false, -2.0f, -0.05f, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed, walkDegree * 4.0f, false, limbSwing, limbSwingAmount);
        this.progressRotationPrev(this.topJaw, openProgress, Maths.rad(-30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.lowerJaw, openProgress, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, clutchProgress, Maths.rad(-30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.eyesBase, clutchProgress, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.mouthArm1, clutchProgress, Maths.rad(-5.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.mouthArm2, clutchProgress, Maths.rad(120.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.mouthArm2, clutchProgress, 0.0f, -2.0f, 3.0f, 5.0f);
        this.progressPositionPrev(this.body, clutchProgress, 0.0f, -10.0f, 33.0f, 5.0f);
        this.progressPositionPrev(this.body, biteProgress, 0.0f, 0.0f, 20.0f, 5.0f);
        this.progressRotationPrev(this.mouthArm1, biteProgress, Maths.rad(-35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.mouthArm2, biteProgress, Maths.rad(50.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leftArm, biteProgress, Maths.rad(10.0), 0.0f, Maths.rad(-30.0), 5.0f);
        this.progressRotationPrev(this.rightArm, biteProgress, Maths.rad(10.0), 0.0f, Maths.rad(30.0), 5.0f);
        float eyeYaw = Mth.m_14036_((float)netHeadYaw, (float)-40.0f, (float)40.0f) / 57.295776f;
        this.eyesBase.rotateAngleY += eyeYaw * 0.35f;
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.5f;
            this.eyesBase.setScale(f, f, f);
            this.eyesBase.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.0);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.eyesBase.setScale(1.0f, 1.0f, 1.0f);
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }
}
