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

import com.github.alexthe666.alexsmobs.entity.EntityPlatypus;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;

public class ModelPlatypus
extends AdvancedEntityModel<EntityPlatypus> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox head;
    private final AdvancedModelBox beak;
    private final AdvancedModelBox fedora;
    private final AdvancedModelBox arm_left;
    private final AdvancedModelBox arm_right;
    private final AdvancedModelBox leg_left;
    private final AdvancedModelBox leg_right;
    private final AdvancedModelBox tail;

    public ModelPlatypus() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -2.6f, -0.1f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 2).addBox(-3.5f, -3.4f, -5.9f, 7.0f, 6.0f, 11.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -0.4f, -5.9f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(5, 51).addBox(-3.0f, -2.5f, -4.0f, 6.0f, 5.0f, 4.0f, 0.0f, false);
        this.beak = new AdvancedModelBox((AdvancedEntityModel)this, "beak");
        this.beak.setPos(0.0f, 2.0f, -5.0f);
        this.head.addChild((BasicModelPart)this.beak);
        this.beak.setTextureOffset(28, 0).addBox(-2.0f, -2.0f, -3.0f, 4.0f, 2.0f, 4.0f, 0.0f, false);
        this.fedora = new AdvancedModelBox((AdvancedEntityModel)this, "fedora");
        this.fedora.setPos(0.0f, -2.6f, -1.9f);
        this.head.addChild((BasicModelPart)this.fedora);
        this.fedora.setTextureOffset(23, 20).addBox(-4.0f, 0.0f, -4.0f, 8.0f, 0.0f, 8.0f, 0.0f, false);
        this.fedora.setTextureOffset(29, 30).addBox(-2.0f, -2.0f, -2.0f, 4.0f, 2.0f, 4.0f, 0.0f, false);
        this.arm_left = new AdvancedModelBox((AdvancedEntityModel)this, "arm_left");
        this.arm_left.setPos(3.5f, 2.1f, -4.9f);
        this.body.addChild((BasicModelPart)this.arm_left);
        this.arm_left.setTextureOffset(7, 39).addBox(0.0f, -0.5f, 0.0f, 3.0f, 1.0f, 3.0f, 0.0f, false);
        this.arm_right = new AdvancedModelBox((AdvancedEntityModel)this, "arm_right");
        this.arm_right.setPos(-3.5f, 2.1f, -4.9f);
        this.body.addChild((BasicModelPart)this.arm_right);
        this.arm_right.setTextureOffset(7, 39).addBox(-3.0f, -0.5f, 0.0f, 3.0f, 1.0f, 3.0f, 0.0f, true);
        this.leg_left = new AdvancedModelBox((AdvancedEntityModel)this, "leg_left");
        this.leg_left.setPos(3.5f, 2.1f, 2.6f);
        this.body.addChild((BasicModelPart)this.leg_left);
        this.leg_left.setTextureOffset(27, 43).addBox(0.0f, -0.5f, -0.5f, 3.0f, 1.0f, 3.0f, 0.0f, false);
        this.leg_right = new AdvancedModelBox((AdvancedEntityModel)this, "leg_right");
        this.leg_right.setPos(-3.5f, 2.1f, 2.6f);
        this.body.addChild((BasicModelPart)this.leg_right);
        this.leg_right.setTextureOffset(27, 43).addBox(-3.0f, -0.5f, -0.5f, 3.0f, 1.0f, 3.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, -0.4f, 5.1f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 24).addBox(-3.0f, -1.0f, 0.0f, 6.0f, 3.0f, 8.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.tail, (Object)this.head, (Object)this.beak, (Object)this.fedora, (Object)this.arm_left, (Object)this.arm_right, (Object)this.leg_left, (Object)this.leg_right);
    }

    public void setupAnim(EntityPlatypus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float walkSpeed = 1.0f;
        float walkDegree = 1.3f;
        float idleSpeed = 0.3f;
        float idleDegree = 0.2f;
        float swimSpeed = 1.3f;
        float swimDegree = 1.3f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float digProgress = entity.prevDigProgress + (entity.digProgress - entity.prevDigProgress) * partialTick;
        float swimProgress = entity.prevInWaterProgress + (entity.inWaterProgress - entity.prevInWaterProgress) * partialTick;
        this.progressPositionPrev(this.body, swimProgress, 0.0f, -3.5f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_left, swimProgress, Maths.rad(-5.0), 0.0f, Maths.rad(75.0), 5.0f);
        this.progressRotationPrev(this.arm_right, swimProgress, Maths.rad(-5.0), 0.0f, Maths.rad(-75.0), 5.0f);
        this.progressRotationPrev(this.leg_left, swimProgress, Maths.rad(-5.0), 0.0f, Maths.rad(75.0), 5.0f);
        this.progressRotationPrev(this.leg_right, swimProgress, Maths.rad(-5.0), 0.0f, Maths.rad(-75.0), 5.0f);
        this.progressRotationPrev(this.tail, swimProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, digProgress, 0.0f, -1.5f, 0.0f, 5.0f);
        this.progressPositionPrev(this.arm_right, digProgress, 1.0f, -1.0f, -0.5f, 5.0f);
        this.progressPositionPrev(this.arm_left, digProgress, -1.0f, -1.0f, -0.5f, 5.0f);
        this.progressRotationPrev(this.body, digProgress, Maths.rad(35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, digProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, digProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_left, digProgress, Maths.rad(-30.0), Maths.rad(10.0), Maths.rad(-65.0), 5.0f);
        this.progressRotationPrev(this.arm_right, digProgress, Maths.rad(-30.0), Maths.rad(-10.0), Maths.rad(65.0), 5.0f);
        if (digProgress > 0.0f) {
            this.swing(this.body, 0.8f, idleDegree * 1.2f, false, 3.0f, 0.0f, ageInTicks, 1.0f);
            this.swing(this.head, 0.8f, idleDegree * 0.7f, false, 3.0f, 0.0f, ageInTicks, 1.0f);
            this.swing(this.arm_right, 0.8f, idleDegree * 2.6f, false, 1.0f, -0.25f, ageInTicks, 1.0f);
            this.swing(this.arm_left, 0.8f, idleDegree * 2.6f, true, 1.0f, -0.25f, ageInTicks, 1.0f);
        } else if (entity.isSensing() || entity.isSensingVisual()) {
            this.swing(this.head, swimSpeed, swimDegree * 0.25f, false, 2.0f, 0.0f, ageInTicks, 1.0f);
            this.walk(this.head, swimSpeed, swimDegree * 0.25f, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        } else {
            this.faceTarget(netHeadYaw, headPitch, 1.2f, new AdvancedModelBox[]{this.head});
        }
        if (swimProgress > 0.0f) {
            this.bob(this.body, idleSpeed, idleDegree * 2.0f, false, ageInTicks, 1.0f);
            this.walk(this.tail, swimSpeed, swimDegree * 0.1f, false, 3.0f, 0.25f, limbSwing, limbSwingAmount);
            this.swing(this.tail, swimSpeed, swimDegree * 0.5f, false, 2.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.body, swimSpeed, swimDegree * 0.3f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.head, swimSpeed, swimDegree * 0.5f, true, 3.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.arm_right, swimSpeed, swimDegree * 0.9f, false, 1.0f, 0.85f, limbSwing, limbSwingAmount);
            this.flap(this.arm_left, swimSpeed, swimDegree * 0.9f, true, 1.0f, 0.85f, limbSwing, limbSwingAmount);
            this.flap(this.leg_right, swimSpeed, swimDegree * 0.9f, false, 3.0f, 0.85f, limbSwing, limbSwingAmount);
            this.flap(this.leg_left, swimSpeed, swimDegree * 0.9f, true, 3.0f, 0.85f, limbSwing, limbSwingAmount);
            this.walk(this.body, swimSpeed, swimDegree * 0.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        } else {
            this.swing(this.tail, idleSpeed * 0.5f, idleDegree, false, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.bob(this.body, walkSpeed * 1.75f, walkDegree * 1.0f, false, limbSwing, limbSwingAmount);
            this.swing(this.body, walkSpeed, walkDegree * 0.3f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.head, walkSpeed, walkDegree * 0.2f, true, 3.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.tail, walkSpeed, walkDegree * 0.3f, false, 3.0f, 0.1f, limbSwing, limbSwingAmount);
            this.swing(this.arm_left, walkSpeed, walkDegree * 0.5f, true, 1.0f, 0.15f, limbSwing, limbSwingAmount);
            this.flap(this.arm_left, walkSpeed, walkDegree * 0.5f, true, 0.0f, 0.25f, limbSwing, limbSwingAmount);
            this.swing(this.arm_right, walkSpeed, walkDegree * 0.5f, true, 1.0f, -0.15f, limbSwing, limbSwingAmount);
            this.flap(this.arm_right, walkSpeed, walkDegree * 0.5f, true, 0.0f, -0.25f, limbSwing, limbSwingAmount);
            this.swing(this.leg_left, walkSpeed, walkDegree * 0.5f, false, 1.0f, -0.15f, limbSwing, limbSwingAmount);
            this.flap(this.leg_left, walkSpeed, walkDegree * 0.5f, false, 0.0f, -0.15f, limbSwing, limbSwingAmount);
            this.swing(this.leg_right, walkSpeed, walkDegree * 0.5f, false, 1.0f, 0.15f, limbSwing, limbSwingAmount);
            this.flap(this.leg_right, walkSpeed, walkDegree * 0.5f, false, 0.0f, 0.15f, limbSwing, limbSwingAmount);
        }
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

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
