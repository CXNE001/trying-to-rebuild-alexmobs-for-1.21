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

import com.github.alexthe666.alexsmobs.entity.EntityMantisShrimp;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;

public class ModelMantisShrimp
extends AdvancedEntityModel<EntityMantisShrimp> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox tail;
    public final AdvancedModelBox legs_front;
    public final AdvancedModelBox legs_back;
    public final AdvancedModelBox head;
    public final AdvancedModelBox flapper_left;
    public final AdvancedModelBox flapper_right;
    public final AdvancedModelBox eye_left;
    public final AdvancedModelBox eye_right;
    public final AdvancedModelBox arm_left;
    public final AdvancedModelBox fist_left;
    public final AdvancedModelBox arm_right;
    public final AdvancedModelBox fist_right;
    public final AdvancedModelBox whisker_left;
    public final AdvancedModelBox whisker_right;

    public ModelMantisShrimp() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -14.0f, -5.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-6.0f, -2.0f, 0.0f, 12.0f, 10.0f, 25.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, 0.0f, 21.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.setRotationAngle(this.tail, -0.2182f, 0.0f, 0.0f);
        this.tail.setTextureOffset(50, 0).addBox(-7.0f, 0.0f, 0.0f, 14.0f, 9.0f, 9.0f, 0.0f, false);
        this.legs_front = new AdvancedModelBox((AdvancedEntityModel)this, "legs_front");
        this.legs_front.setPos(0.0f, 5.0f, -7.0f);
        this.body.addChild((BasicModelPart)this.legs_front);
        this.legs_front.setTextureOffset(0, 61).addBox(-5.0f, 0.0f, 0.0f, 10.0f, 9.0f, 8.0f, 0.0f, false);
        this.legs_back = new AdvancedModelBox((AdvancedEntityModel)this, "legs_back");
        this.legs_back.setPos(0.0f, 8.0f, 1.0f);
        this.body.addChild((BasicModelPart)this.legs_back);
        this.legs_back.setTextureOffset(0, 36).addBox(-5.0f, 0.0f, 0.0f, 10.0f, 6.0f, 18.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, 4.0f, 2.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.setRotationAngle(this.head, 0.3491f, 0.0f, 0.0f);
        this.head.setTextureOffset(49, 53).addBox(-6.0f, -14.0f, -8.0f, 12.0f, 14.0f, 8.0f, 0.1f, false);
        this.flapper_left = new AdvancedModelBox((AdvancedEntityModel)this, "flapper_left");
        this.flapper_left.setPos(4.0f, -14.0f, -8.0f);
        this.head.addChild((BasicModelPart)this.flapper_left);
        this.setRotationAngle(this.flapper_left, -0.48f, 0.2182f, 0.0f);
        this.flapper_left.setTextureOffset(50, 19).addBox(0.0f, 0.0f, 0.0f, 14.0f, 5.0f, 0.0f, 0.0f, false);
        this.flapper_right = new AdvancedModelBox((AdvancedEntityModel)this, "flapper_right");
        this.flapper_right.setPos(-4.0f, -14.0f, -8.0f);
        this.head.addChild((BasicModelPart)this.flapper_right);
        this.setRotationAngle(this.flapper_right, -0.48f, -0.2182f, 0.0f);
        this.flapper_right.setTextureOffset(50, 19).addBox(-14.0f, 0.0f, 0.0f, 14.0f, 5.0f, 0.0f, 0.0f, true);
        this.eye_left = new AdvancedModelBox((AdvancedEntityModel)this, "eye_left");
        this.eye_left.setPos(3.0f, -14.0f, -4.0f);
        this.head.addChild((BasicModelPart)this.eye_left);
        this.eye_left.setTextureOffset(0, 15).addBox(-2.0f, -4.0f, -2.0f, 4.0f, 4.0f, 4.0f, 0.0f, false);
        this.eye_right = new AdvancedModelBox((AdvancedEntityModel)this, "eye_right");
        this.eye_right.setPos(-3.0f, -14.0f, -4.0f);
        this.head.addChild((BasicModelPart)this.eye_right);
        this.eye_right.setTextureOffset(0, 15).addBox(-2.0f, -4.0f, -2.0f, 4.0f, 4.0f, 4.0f, 0.0f, true);
        this.arm_left = new AdvancedModelBox((AdvancedEntityModel)this, "arm_left");
        this.arm_left.setPos(4.0f, -1.0f, -8.0f);
        this.head.addChild((BasicModelPart)this.arm_left);
        this.arm_left.setTextureOffset(0, 36).addBox(-2.5f, -9.0f, -2.0f, 5.0f, 10.0f, 3.0f, 0.0f, false);
        this.fist_left = new AdvancedModelBox((AdvancedEntityModel)this, "fist_left");
        this.fist_left.setPos(-1.0f, -7.0f, -1.0f);
        this.arm_left.addChild((BasicModelPart)this.fist_left);
        this.fist_left.setTextureOffset(0, 0).addBox(-1.0f, -1.0f, -4.0f, 4.0f, 10.0f, 4.0f, 0.0f, false);
        this.arm_right = new AdvancedModelBox((AdvancedEntityModel)this, "arm_right");
        this.arm_right.setPos(-4.0f, -1.0f, -8.0f);
        this.head.addChild((BasicModelPart)this.arm_right);
        this.arm_right.setTextureOffset(0, 36).addBox(-2.5f, -9.0f, -2.0f, 5.0f, 10.0f, 3.0f, 0.0f, true);
        this.fist_right = new AdvancedModelBox((AdvancedEntityModel)this, "fist_right");
        this.fist_right.setPos(1.0f, -7.0f, -1.0f);
        this.arm_right.addChild((BasicModelPart)this.fist_right);
        this.fist_right.setTextureOffset(0, 0).addBox(-3.0f, -1.0f, -4.0f, 4.0f, 10.0f, 4.0f, 0.0f, true);
        this.whisker_left = new AdvancedModelBox((AdvancedEntityModel)this, "whisker_left");
        this.whisker_left.setPos(1.0f, -14.0f, -8.0f);
        this.head.addChild((BasicModelPart)this.whisker_left);
        this.setRotationAngle(this.whisker_left, 0.0f, -0.3927f, 0.0f);
        this.whisker_left.setTextureOffset(39, 39).addBox(0.0f, 0.0f, -13.0f, 8.0f, 0.0f, 13.0f, 0.0f, false);
        this.whisker_right = new AdvancedModelBox((AdvancedEntityModel)this, "whisker_right");
        this.whisker_right.setPos(-1.0f, -14.0f, -8.0f);
        this.head.addChild((BasicModelPart)this.whisker_right);
        this.setRotationAngle(this.whisker_right, 0.0f, 0.3927f, 0.0f);
        this.whisker_right.setTextureOffset(39, 39).addBox(-8.0f, 0.0f, -13.0f, 8.0f, 0.0f, 13.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.head, (Object)this.eye_left, (Object)this.eye_right, (Object)this.fist_left, (Object)this.fist_right, (Object)this.arm_left, (Object)this.arm_right, (Object)this.whisker_left, (Object)this.whisker_right, (Object)this.flapper_left, (Object[])new AdvancedModelBox[]{this.flapper_right, this.tail, this.legs_back, this.legs_front});
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            this.eye_left.setScale(1.15f, 1.15f, 1.15f);
            this.eye_right.setScale(1.15f, 1.15f, 1.15f);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        } else {
            this.eye_left.setScale(1.0f, 1.0f, 1.0f);
            this.eye_right.setScale(1.0f, 1.0f, 1.0f);
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setupAnim(EntityMantisShrimp entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.1f;
        float idleDegree = 0.3f;
        float walkSpeed = 0.9f;
        float walkDegree = 0.6f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float swimProgress = Math.min(limbSwingAmount, 0.25f) * 4.0f * (entity.prevInWaterProgress + (entity.inWaterProgress - entity.prevInWaterProgress) * partialTick);
        float punchProgress = entity.prevPunchProgress + (entity.punchProgress - entity.prevPunchProgress) * partialTick;
        float leftEyePitch = entity.prevLeftPitch + (entity.getEyePitch(true) - entity.prevLeftPitch) * partialTick;
        float rightEyePitch = entity.prevRightPitch + (entity.getEyePitch(false) - entity.prevRightPitch) * partialTick;
        float leftEyeYaw = entity.prevLeftYaw + (entity.getEyeYaw(true) - entity.prevLeftYaw) * partialTick;
        float rightEyeYaw = entity.prevRightYaw + (entity.getEyeYaw(false) - entity.prevRightYaw) * partialTick;
        this.eye_left.rotateAngleX += leftEyePitch * ((float)Math.PI / 180);
        this.eye_left.rotateAngleY += leftEyeYaw * ((float)Math.PI / 180);
        this.eye_right.rotateAngleX += rightEyePitch * ((float)Math.PI / 180);
        this.eye_right.rotateAngleY += rightEyeYaw * ((float)Math.PI / 180);
        this.head.rotateAngleY += netHeadYaw * 0.5f * ((float)Math.PI / 180);
        this.head.rotateAngleX += headPitch * 0.8f * ((float)Math.PI / 180);
        this.walk(this.whisker_left, idleSpeed * 1.5f, idleDegree, false, 0.0f, -0.25f, ageInTicks, 1.0f);
        this.walk(this.whisker_right, idleSpeed * 1.5f, idleDegree, true, 0.0f, 0.25f, ageInTicks, 1.0f);
        this.swing(this.whisker_left, idleSpeed * 1.0f, idleDegree * 0.75f, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.swing(this.whisker_right, idleSpeed * 1.0f, idleDegree * 0.75f, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.swing(this.flapper_left, idleSpeed * 1.0f, idleDegree * 0.75f, false, 2.0f, -0.3f, ageInTicks, 1.0f);
        this.swing(this.flapper_right, idleSpeed * 1.0f, idleDegree * 0.75f, true, 2.0f, -0.3f, ageInTicks, 1.0f);
        this.swing(this.arm_left, idleSpeed * 1.0f, idleDegree * 0.5f, false, 1.0f, -0.2f, ageInTicks, 1.0f);
        this.swing(this.arm_right, idleSpeed * 1.0f, idleDegree * 0.5f, true, 1.0f, -0.2f, ageInTicks, 1.0f);
        this.walk(this.legs_front, walkSpeed, walkDegree * 1.0f, true, 2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.legs_front, walkSpeed * 0.5f, walkDegree * 4.0f, true, limbSwing, limbSwingAmount);
        this.walk(this.legs_back, walkSpeed, walkDegree * 0.2f, true, 2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.legs_back, walkSpeed * 0.5f, walkDegree * 4.0f, true, limbSwing, limbSwingAmount);
        this.progressRotationPrev(this.head, Math.max(0.0f, swimProgress - punchProgress * 2.5f), Maths.rad(45.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.whisker_left, swimProgress, Maths.rad(-45.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.whisker_right, swimProgress, Maths.rad(-45.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_left, swimProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_right, swimProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, swimProgress, 0.0f, -6.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.arm_left, swimProgress, 0.0f, -3.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.arm_right, swimProgress, 0.0f, -3.0f, 0.0f, 5.0f);
        if (swimProgress > 0.0f) {
            this.bob(this.body, walkSpeed * 0.5f, walkDegree * 4.0f, true, limbSwing, limbSwingAmount);
            this.walk(this.body, walkSpeed * 1.0f, walkDegree * 0.2f, true, 3.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.tail, walkSpeed * 1.0f, walkDegree * 0.5f, true, 3.0f, -0.2f, limbSwing, limbSwingAmount);
            this.walk(this.head, walkSpeed * 1.0f, walkDegree * 0.1f, true, 2.0f, 0.0f, limbSwing, limbSwingAmount);
        }
        this.progressPositionPrev(this.arm_right, punchProgress, 1.0f, -7.0f, 0.0f, 2.0f);
        this.progressPositionPrev(this.arm_left, punchProgress, -1.0f, -7.0f, 0.0f, 2.0f);
        this.progressPositionPrev(this.fist_right, punchProgress, 0.0f, -2.0f, -2.0f, 2.0f);
        this.progressPositionPrev(this.fist_left, punchProgress, 0.0f, -2.0f, -2.0f, 2.0f);
        this.progressRotationPrev(this.arm_right, punchProgress, Maths.rad(70.0), 0.0f, 0.0f, 2.0f);
        this.progressRotationPrev(this.fist_right, punchProgress, Maths.rad(-240.0), 0.0f, Maths.rad(10.0), 2.0f);
        this.progressRotationPrev(this.arm_left, punchProgress, Maths.rad(70.0), 0.0f, 0.0f, 2.0f);
        this.progressRotationPrev(this.fist_left, punchProgress, Maths.rad(-240.0), 0.0f, Maths.rad(-10.0), 2.0f);
        this.progressRotationPrev(this.flapper_right, punchProgress, 0.0f, Maths.rad(50.0), 0.0f, 2.0f);
        this.progressRotationPrev(this.flapper_left, punchProgress, 0.0f, Maths.rad(-50.0), 0.0f, 2.0f);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
