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
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntitySeal;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;

public class ModelSeal
extends AdvancedEntityModel<EntitySeal> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox tail;
    public final AdvancedModelBox leftLeg;
    public final AdvancedModelBox rightLeg;
    public final AdvancedModelBox leftArm;
    public final AdvancedModelBox rightArm;
    public final AdvancedModelBox head;
    public final AdvancedModelBox leftWhisker;
    public final AdvancedModelBox rightWhisker;

    public ModelSeal() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -3.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-6.5f, -6.0f, -9.0f, 13.0f, 9.0f, 18.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, 1.0f, 9.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 28).addBox(-4.0f, -4.0f, 0.0f, 8.0f, 6.0f, 14.0f, 0.0f, false);
        this.leftLeg = new AdvancedModelBox((AdvancedEntityModel)this, "leftLeg");
        this.leftLeg.setRotationPoint(2.0f, -0.2f, 13.4f);
        this.tail.addChild((BasicModelPart)this.leftLeg);
        this.setRotationAngle(this.leftLeg, 0.0f, 0.3491f, 0.0f);
        this.leftLeg.setTextureOffset(45, 0).addBox(-1.0f, -2.0f, 0.0f, 2.0f, 4.0f, 8.0f, 0.0f, false);
        this.rightLeg = new AdvancedModelBox((AdvancedEntityModel)this, "rightLeg");
        this.rightLeg.setRotationPoint(-2.0f, -0.2f, 13.4f);
        this.tail.addChild((BasicModelPart)this.rightLeg);
        this.setRotationAngle(this.rightLeg, 0.0f, -0.3491f, 0.0f);
        this.rightLeg.setTextureOffset(45, 0).addBox(-1.0f, -2.0f, 0.0f, 2.0f, 4.0f, 8.0f, 0.0f, true);
        this.leftArm = new AdvancedModelBox((AdvancedEntityModel)this, "leftArm");
        this.leftArm.setRotationPoint(7.5f, 2.5f, -4.0f);
        this.body.addChild((BasicModelPart)this.leftArm);
        this.leftArm.setTextureOffset(31, 28).addBox(-1.0f, -0.5f, -2.0f, 8.0f, 1.0f, 5.0f, 0.0f, false);
        this.leftArm.setTextureOffset(0, 7).addBox(7.0f, 0.5f, -2.0f, 0.0f, 2.0f, 5.0f, 0.0f, false);
        this.rightArm = new AdvancedModelBox((AdvancedEntityModel)this, "rightArm");
        this.rightArm.setRotationPoint(-7.5f, 2.5f, -4.0f);
        this.body.addChild((BasicModelPart)this.rightArm);
        this.rightArm.setTextureOffset(31, 28).addBox(-7.0f, -0.5f, -2.0f, 8.0f, 1.0f, 5.0f, 0.0f, true);
        this.rightArm.setTextureOffset(0, 7).addBox(-7.0f, 0.5f, -2.0f, 0.0f, 2.0f, 5.0f, 0.0f, true);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -1.0f, -5.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(35, 39).addBox(-3.5f, -3.0f, -9.0f, 7.0f, 6.0f, 10.0f, 0.0f, false);
        this.head.setTextureOffset(0, 0).addBox(-2.5f, 0.0f, -12.0f, 5.0f, 3.0f, 3.0f, 0.0f, false);
        this.leftWhisker = new AdvancedModelBox((AdvancedEntityModel)this, "leftWhisker");
        this.leftWhisker.setRotationPoint(2.5f, 2.0f, -11.0f);
        this.head.addChild((BasicModelPart)this.leftWhisker);
        this.setRotationAngle(this.leftWhisker, 0.0f, -0.2182f, 0.0f);
        this.leftWhisker.setTextureOffset(0, 7).addBox(0.0f, -2.0f, 0.0f, 2.0f, 3.0f, 0.0f, 0.0f, false);
        this.rightWhisker = new AdvancedModelBox((AdvancedEntityModel)this, "rightWhisker");
        this.rightWhisker.setRotationPoint(-2.5f, 2.0f, -11.0f);
        this.head.addChild((BasicModelPart)this.rightWhisker);
        this.setRotationAngle(this.rightWhisker, 0.0f, 0.2182f, 0.0f);
        this.rightWhisker.setTextureOffset(0, 7).addBox(-2.0f, -2.0f, 0.0f, 2.0f, 3.0f, 0.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.tail, (Object)this.leftLeg, (Object)this.rightLeg, (Object)this.head, (Object)this.leftArm, (Object)this.rightArm, (Object)this.leftWhisker, (Object)this.rightWhisker);
    }

    public void setupAnim(EntitySeal entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float walkSpeed = this.f_102610_ ? 0.25f : 0.5f;
        float walkDegree = 1.0f;
        float swimSpeed = 0.5f;
        float swimDegree = 0.5f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float baskProgress = entity.prevBaskProgress + (entity.baskProgress - entity.prevBaskProgress) * partialTick;
        float swimAngle = entity.prevSwimAngle + (entity.getSwimAngle() - entity.prevSwimAngle) * partialTick;
        float diggingProgress = entity.prevDigProgress + (entity.digProgress - entity.prevDigProgress) * partialTick;
        float bobbingProgress = entity.prevBobbingProgress + (entity.bobbingProgress - entity.prevBobbingProgress) * partialTick;
        int baskType = entity.isTearsEasterEgg() ? -1 : entity.m_19879_() % 5;
        this.progressRotationPrev(this.body, diggingProgress, Maths.rad(70.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, diggingProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, diggingProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leftArm, diggingProgress, 0.0f, Maths.rad(30.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.rightArm, diggingProgress, 0.0f, Maths.rad(-30.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.body, diggingProgress, 0.0f, -12.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.leftArm, diggingProgress, -1.0f, 0.0f, -2.0f, 5.0f);
        this.progressPositionPrev(this.rightArm, diggingProgress, 1.0f, 0.0f, -2.0f, 5.0f);
        this.head.rotationPointZ += (float)(Math.sin(ageInTicks * 0.7f) * 0.5 * (double)bobbingProgress);
        if (diggingProgress > 0.0f) {
            float amount = diggingProgress * 0.2f;
            this.swing(this.rightArm, 0.6f, 0.85f, true, 1.0f, -0.1f, ageInTicks, amount);
            this.swing(this.leftArm, 0.6f, 0.85f, false, 1.0f, -0.1f, ageInTicks, amount);
            this.walk(this.tail, 0.6f, 0.1f, false, 3.0f, -0.1f, ageInTicks, amount);
            this.bob(this.body, 0.3f, 3.0f, true, ageInTicks, amount);
        }
        if (baskProgress > 0.0f && !entity.isTearsEasterEgg()) {
            this.walk(this.head, 0.05f, 0.2f, true, 1.0f, -0.1f, ageInTicks, 1.0f);
            if (baskType == 0) {
                this.progressRotationPrev(this.body, baskProgress, 0.0f, 0.0f, Maths.rad(70.0), 5.0f);
                this.progressRotationPrev(this.head, baskProgress, 0.0f, Maths.rad(-20.0), Maths.rad(20.0), 5.0f);
                this.progressRotationPrev(this.leftArm, baskProgress, 0.0f, 0.0f, Maths.rad(110.0), 5.0f);
                this.progressRotationPrev(this.rightArm, baskProgress, 0.0f, 0.0f, Maths.rad(-120.0), 5.0f);
                this.progressRotationPrev(this.tail, baskProgress, 0.0f, Maths.rad(15.0), Maths.rad(-20.0), 5.0f);
                this.progressRotationPrev(this.leftLeg, baskProgress, 0.0f, Maths.rad(-15.0), 0.0f, 5.0f);
                this.progressRotationPrev(this.rightLeg, baskProgress, 0.0f, Maths.rad(35.0), Maths.rad(30.0), 5.0f);
                this.progressPositionPrev(this.leftArm, baskProgress, -2.0f, 0.0f, 0.0f, 5.0f);
                this.progressPositionPrev(this.rightArm, baskProgress, 1.0f, 0.0f, 0.0f, 5.0f);
                this.progressPositionPrev(this.head, baskProgress, 0.0f, 0.0f, 1.0f, 5.0f);
                this.progressPositionPrev(this.body, baskProgress, 0.0f, -4.0f, 1.0f, 5.0f);
                this.flap(this.rightArm, 0.05f, 0.2f, true, 3.0f, -0.1f, ageInTicks, 1.0f);
                this.flap(this.leftArm, 0.05f, 0.2f, true, 3.0f, -0.1f, ageInTicks, 1.0f);
            } else if (baskType == 1) {
                this.progressRotationPrev(this.body, baskProgress, 0.0f, 0.0f, Maths.rad(-70.0), 5.0f);
                this.progressRotationPrev(this.head, baskProgress, 0.0f, Maths.rad(20.0), Maths.rad(-20.0), 5.0f);
                this.progressRotationPrev(this.rightArm, baskProgress, 0.0f, 0.0f, Maths.rad(-110.0), 5.0f);
                this.progressRotationPrev(this.leftArm, baskProgress, 0.0f, 0.0f, Maths.rad(120.0), 5.0f);
                this.progressRotationPrev(this.tail, baskProgress, 0.0f, Maths.rad(-15.0), Maths.rad(20.0), 5.0f);
                this.progressRotationPrev(this.rightLeg, baskProgress, 0.0f, Maths.rad(15.0), 0.0f, 5.0f);
                this.progressRotationPrev(this.leftLeg, baskProgress, 0.0f, Maths.rad(-35.0), Maths.rad(-30.0), 5.0f);
                this.progressPositionPrev(this.rightArm, baskProgress, 2.0f, 0.0f, 0.0f, 5.0f);
                this.progressPositionPrev(this.leftArm, baskProgress, -1.0f, 0.0f, 0.0f, 5.0f);
                this.progressPositionPrev(this.head, baskProgress, 0.0f, 0.0f, 1.0f, 5.0f);
                this.progressPositionPrev(this.body, baskProgress, 0.0f, -4.0f, 0.0f, 5.0f);
                this.flap(this.rightArm, 0.05f, 0.2f, false, 3.0f, -0.1f, ageInTicks, 1.0f);
                this.flap(this.leftArm, 0.05f, 0.2f, false, 3.0f, -0.1f, ageInTicks, 1.0f);
            } else if (baskType == 2) {
                this.progressRotationPrev(this.rightArm, baskProgress, 0.0f, 0.0f, Maths.rad(30.0), 5.0f);
                this.progressRotationPrev(this.leftArm, baskProgress, 0.0f, 0.0f, Maths.rad(-40.0), 5.0f);
                this.progressRotationPrev(this.body, baskProgress, 0.0f, 0.0f, Maths.rad(160.0), 5.0f);
                this.progressRotationPrev(this.tail, baskProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
                this.progressRotationPrev(this.head, baskProgress, Maths.rad(-10.0), Maths.rad(20.0), Maths.rad(30.0), 5.0f);
                this.progressPositionPrev(this.body, baskProgress, 0.0f, -4.0f, 0.0f, 5.0f);
                this.progressPositionPrev(this.rightArm, baskProgress, 1.0f, 0.0f, 0.0f, 5.0f);
                this.progressPositionPrev(this.leftArm, baskProgress, -1.0f, 0.0f, 0.0f, 5.0f);
                this.flap(this.rightArm, 0.05f, 0.2f, true, 3.0f, -0.1f, ageInTicks, 1.0f);
                this.flap(this.leftArm, 0.05f, 0.2f, false, 3.0f, -0.1f, ageInTicks, 1.0f);
            } else if (baskType == 3) {
                this.progressRotationPrev(this.body, baskProgress, 0.0f, Maths.rad(20.0), 0.0f, 5.0f);
                this.progressRotationPrev(this.tail, baskProgress, 0.0f, Maths.rad(25.0), 0.0f, 5.0f);
                this.progressRotationPrev(this.head, baskProgress, 0.0f, Maths.rad(-20.0), Maths.rad(25.0), 5.0f);
                this.progressRotationPrev(this.rightArm, baskProgress, 0.0f, Maths.rad(-20.0), 0.0f, 5.0f);
                this.progressRotationPrev(this.leftArm, baskProgress, 0.0f, Maths.rad(30.0), 0.0f, 5.0f);
                this.progressRotationPrev(this.leftLeg, baskProgress, 0.0f, Maths.rad(30.0), 0.0f, 5.0f);
                this.progressRotationPrev(this.rightLeg, baskProgress, 0.0f, Maths.rad(30.0), 0.0f, 5.0f);
                this.progressPositionPrev(this.head, baskProgress, 0.0f, -1.0f, 0.0f, 5.0f);
                this.flap(this.rightArm, 0.05f, 0.2f, true, 3.0f, -0.1f, ageInTicks, 1.0f);
                this.flap(this.leftArm, 0.05f, 0.2f, false, 3.0f, -0.1f, ageInTicks, 1.0f);
            } else if (baskType == 4) {
                this.progressRotationPrev(this.body, baskProgress, 0.0f, Maths.rad(-20.0), 0.0f, 5.0f);
                this.progressRotationPrev(this.tail, baskProgress, 0.0f, Maths.rad(-25.0), 0.0f, 5.0f);
                this.progressRotationPrev(this.head, baskProgress, 0.0f, Maths.rad(20.0), Maths.rad(-25.0), 5.0f);
                this.progressRotationPrev(this.rightArm, baskProgress, 0.0f, Maths.rad(30.0), 0.0f, 5.0f);
                this.progressRotationPrev(this.leftArm, baskProgress, 0.0f, Maths.rad(-20.0), 0.0f, 5.0f);
                this.progressPositionPrev(this.head, baskProgress, 0.0f, -1.0f, 0.0f, 5.0f);
                this.progressRotationPrev(this.leftLeg, baskProgress, 0.0f, Maths.rad(-30.0), 0.0f, 5.0f);
                this.progressRotationPrev(this.rightLeg, baskProgress, 0.0f, Maths.rad(-30.0), 0.0f, 5.0f);
                this.flap(this.rightArm, 0.05f, 0.2f, true, 3.0f, -0.1f, ageInTicks, 1.0f);
                this.flap(this.leftArm, 0.05f, 0.2f, false, 3.0f, -0.1f, ageInTicks, 1.0f);
            }
        }
        AdvancedModelBox[] bodyParts = new AdvancedModelBox[]{this.head, this.body, this.tail};
        if (!entity.m_20069_()) {
            float f = walkSpeed;
            float f1 = walkDegree * 0.3f;
            this.body.rotationPointY += 1.4f * Math.min(0.0f, (float)(Math.sin(limbSwing * f) * (double)limbSwingAmount * (double)f1 * 9.0 - (double)(limbSwingAmount * f1) * 9.0));
            this.body.rotationPointZ += (float)(Math.sin(limbSwing * f - 1.5f) * (double)limbSwingAmount * (double)f1 * 9.0 - (double)(limbSwingAmount * f1) * 9.0);
            this.head.rotationPointZ += (float)(Math.sin(limbSwing * f - 2.0f) * (double)limbSwingAmount * (double)f1 * 2.0 - (double)(limbSwingAmount * f1 * 2.0f));
            this.walk(this.body, walkSpeed, walkDegree * 0.1f, false, 1.0f, 0.04f, limbSwing, limbSwingAmount);
            this.walk(this.head, walkSpeed, walkDegree * 0.1f, true, 1.0f, 0.04f, limbSwing, limbSwingAmount);
            this.walk(this.tail, walkSpeed, walkDegree * 0.15f, true, 1.0f, 0.06f, limbSwing, limbSwingAmount);
            this.flap(this.rightArm, walkSpeed, walkDegree, true, 3.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.leftArm, walkSpeed, walkDegree, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.rightArm, walkSpeed, walkDegree, false, 2.0f, -0.2f, limbSwing, limbSwingAmount);
            this.swing(this.leftArm, walkSpeed, walkDegree, true, 2.0f, -0.2f, limbSwing, limbSwingAmount);
        } else {
            this.body.rotateAngleX += headPitch * ((float)Math.PI / 180);
            this.body.rotationPointY += (float)(Math.sin(limbSwing * swimSpeed) * (double)limbSwingAmount * (double)swimDegree * 9.0 - (double)(limbSwingAmount * swimDegree) * 9.0);
            this.chainWave(bodyParts, swimSpeed, swimDegree, -3.0, limbSwing, limbSwingAmount);
            this.flap(this.rightArm, swimSpeed, swimDegree * 2.5f, true, 3.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.leftArm, swimSpeed, swimDegree * 2.5f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.leftLeg, swimSpeed, swimDegree, false, -4.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.rightLeg, swimSpeed, swimDegree, false, -4.0f, 0.0f, limbSwing, limbSwingAmount);
        }
        if (entity.isTearsEasterEgg() && !entity.m_20069_()) {
            this.swing(this.head, 0.1f, 0.6f, true, 3.0f, 0.0f, ageInTicks, 1.0f);
            this.walk(this.head, 0.1f, 0.1f, true, 2.0f, 0.3f, ageInTicks, 1.0f);
        }
        this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.head});
        float yawAmount = swimAngle / 57.295776f * 0.5f;
        this.body.rotateAngleZ += yawAmount;
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.65f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.0);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z) {
        advancedModelBox.rotateAngleX = x;
        advancedModelBox.rotateAngleY = y;
        advancedModelBox.rotateAngleZ = z;
    }
}
