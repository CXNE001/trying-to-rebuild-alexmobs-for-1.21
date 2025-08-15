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
 *  net.minecraft.util.Mth
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityTerrapin;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class ModelTerrapin
extends AdvancedEntityModel<EntityTerrapin> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox head;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox shell;
    private final AdvancedModelBox left_arm;
    private final AdvancedModelBox left_hand;
    private final AdvancedModelBox right_arm;
    private final AdvancedModelBox right_hand;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox left_foot;
    private final AdvancedModelBox right_leg;
    private final AdvancedModelBox right_foot;

    public ModelTerrapin() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -2.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 14).addBox(-3.5f, -1.0f, -4.0f, 7.0f, 2.0f, 8.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -1.3f, -5.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 25).addBox(-1.5f, -1.5f, -4.0f, 3.0f, 3.0f, 5.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, 0.5f, 4.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(28, 26).addBox(-0.5f, -0.5f, 0.0f, 1.0f, 1.0f, 3.0f, 0.0f, false);
        this.shell = new AdvancedModelBox((AdvancedEntityModel)this, "shell");
        this.shell.setPos(0.0f, 0.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.shell);
        this.shell.setTextureOffset(0, 0).addBox(-4.5f, -3.0f, -5.0f, 9.0f, 3.0f, 10.0f, 0.0f, false);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setPos(4.0f, 0.0f, -3.6f);
        this.body.addChild((BasicModelPart)this.left_arm);
        this.left_arm.setTextureOffset(0, 0).addBox(-1.0f, -1.0f, -1.0f, 2.0f, 3.0f, 2.0f, 0.0f, false);
        this.left_hand = new AdvancedModelBox((AdvancedEntityModel)this, "left_hand");
        this.left_hand.setPos(0.0f, 2.0f, -1.0f);
        this.left_arm.addChild((BasicModelPart)this.left_hand);
        this.left_hand.setTextureOffset(28, 22).addBox(-1.0f, -0.01f, -2.0f, 3.0f, 0.0f, 3.0f, 0.0f, false);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setPos(-4.0f, 0.0f, -3.6f);
        this.body.addChild((BasicModelPart)this.right_arm);
        this.right_arm.setTextureOffset(0, 0).addBox(-1.0f, -1.0f, -1.0f, 2.0f, 3.0f, 2.0f, 0.0f, true);
        this.right_hand = new AdvancedModelBox((AdvancedEntityModel)this, "right_hand");
        this.right_hand.setPos(0.0f, 2.0f, -1.0f);
        this.right_arm.addChild((BasicModelPart)this.right_hand);
        this.right_hand.setTextureOffset(28, 22).addBox(-2.0f, 0.0f, -2.0f, 3.0f, 0.0f, 3.0f, 0.0f, true);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setPos(4.0f, 0.0f, 4.4f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(17, 25).addBox(-1.0f, -1.0f, -2.0f, 2.0f, 3.0f, 3.0f, 0.0f, false);
        this.left_foot = new AdvancedModelBox((AdvancedEntityModel)this, "left_foot");
        this.left_foot.setPos(0.0f, 2.0f, 0.0f);
        this.left_leg.addChild((BasicModelPart)this.left_foot);
        this.left_foot.setTextureOffset(23, 14).addBox(-1.0f, -0.01f, -5.0f, 3.0f, 0.0f, 5.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setPos(-4.0f, 0.0f, 4.4f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(17, 25).addBox(-1.0f, -1.0f, -2.0f, 2.0f, 3.0f, 3.0f, 0.0f, true);
        this.right_foot = new AdvancedModelBox((AdvancedEntityModel)this, "right_foot");
        this.right_foot.setPos(0.0f, 2.0f, 0.0f);
        this.right_leg.addChild((BasicModelPart)this.right_foot);
        this.right_foot.setTextureOffset(23, 14).addBox(-2.0f, 0.0f, -4.0f, 3.0f, 0.0f, 5.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.shell, (Object)this.head, (Object)this.tail, (Object)this.left_arm, (Object)this.left_foot, (Object)this.left_leg, (Object)this.left_hand, (Object)this.right_arm, (Object)this.right_foot, (Object)this.right_leg, (Object[])new AdvancedModelBox[]{this.right_hand});
    }

    public void setupAnim(EntityTerrapin entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float walkSpeed = 0.3f;
        float walkDegree = 0.7f;
        float swimSpeed = 0.5f;
        float swimDegree = 0.8f;
        float idleSpeed = 0.15f;
        float idleDegree = 0.7f;
        float spinProgress = entity.prevSpinProgress + (entity.spinProgress - entity.prevSpinProgress) * partialTick;
        float retreatProgress = Math.max(spinProgress, entity.prevRetreatProgress + (entity.retreatProgress - entity.prevRetreatProgress) * partialTick);
        float swimProgress = (entity.prevSwimProgress + (entity.swimProgress - entity.prevSwimProgress) * partialTick) * (5.0f - retreatProgress) * 0.2f;
        float standUnderwaterProgress = Math.max(0.0f, (1.0f - Math.min(limbSwingAmount * 3.0f, 1.0f)) * swimProgress - retreatProgress);
        float spinDegree = 0.6f;
        this.progressRotationPrev(this.left_arm, swimProgress, Maths.rad(-20.0), 0.0f, Maths.rad(-70.0), 5.0f);
        this.progressRotationPrev(this.left_hand, swimProgress, 0.0f, 0.0f, Maths.rad(70.0), 5.0f);
        this.progressRotationPrev(this.right_arm, swimProgress, Maths.rad(-20.0), 0.0f, Maths.rad(70.0), 5.0f);
        this.progressRotationPrev(this.right_hand, swimProgress, 0.0f, 0.0f, Maths.rad(-70.0), 5.0f);
        this.progressRotationPrev(this.left_leg, swimProgress, Maths.rad(30.0), 0.0f, Maths.rad(-70.0), 5.0f);
        this.progressRotationPrev(this.left_foot, swimProgress, 0.0f, 0.0f, Maths.rad(70.0), 5.0f);
        this.progressRotationPrev(this.right_leg, swimProgress, Maths.rad(30.0), 0.0f, Maths.rad(70.0), 5.0f);
        this.progressRotationPrev(this.right_foot, swimProgress, 0.0f, 0.0f, Maths.rad(-70.0), 5.0f);
        this.progressRotationPrev(this.body, standUnderwaterProgress, Maths.rad(-30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, standUnderwaterProgress, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, standUnderwaterProgress, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, standUnderwaterProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.tail, swimProgress, 0.0f, -0.5f, 1.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, retreatProgress, 0.0f, 0.0f, Maths.rad(-90.0), 5.0f);
        this.progressRotationPrev(this.right_arm, retreatProgress, 0.0f, 0.0f, Maths.rad(90.0), 5.0f);
        this.progressRotationPrev(this.left_leg, retreatProgress, 0.0f, 0.0f, Maths.rad(-90.0), 5.0f);
        this.progressRotationPrev(this.right_leg, retreatProgress, 0.0f, 0.0f, Maths.rad(90.0), 5.0f);
        this.progressPositionPrev(this.body, retreatProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, retreatProgress, 0.0f, 1.0f, 3.0f, 5.0f);
        this.progressPositionPrev(this.tail, retreatProgress, 0.0f, -0.1f, -4.0f, 5.0f);
        this.progressPositionPrev(this.left_arm, retreatProgress, -3.0f, -0.1f, 3.0f, 5.0f);
        this.progressPositionPrev(this.right_arm, retreatProgress, 3.0f, -0.1f, 3.0f, 5.0f);
        this.progressPositionPrev(this.left_leg, retreatProgress, -3.0f, -0.1f, -2.0f, 5.0f);
        this.progressPositionPrev(this.right_leg, retreatProgress, 3.0f, -0.1f, -2.0f, 5.0f);
        this.swing(this.tail, idleSpeed, idleDegree, true, -2.0f, 0.0f, ageInTicks, 1.0f);
        if (!entity.hasRetreated() && !entity.isSpinning()) {
            this.faceTarget(netHeadYaw, headPitch, 1.3f, new AdvancedModelBox[]{this.head});
        }
        if (entity.isSpinning()) {
            float timeSpinning = (float)entity.spinCounter + partialTick;
            entity.clientSpin = this.body.rotateAngleY = timeSpinning * 0.2f * spinProgress * spinDegree;
            this.bob(this.body, spinDegree, 1.0f, true, timeSpinning, 0.2f * spinProgress);
        } else {
            float rollDeg = (float)Mth.m_14175_((double)Math.toDegrees(entity.clientSpin));
            this.body.rotateAngleY = spinProgress * 0.2f * Maths.rad(rollDeg);
            if (swimProgress <= 0.0f) {
                this.flap(this.body, walkSpeed, walkDegree * 0.5f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
                this.swing(this.body, walkSpeed, walkDegree * 0.5f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
                this.flap(this.head, walkSpeed, walkDegree * -0.5f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
                this.swing(this.head, walkSpeed, walkDegree * -0.5f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
                this.flap(this.tail, walkSpeed, walkDegree * -0.5f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
                this.swing(this.tail, walkSpeed, walkDegree * 0.5f, false, 2.0f, 0.0f, limbSwing, limbSwingAmount);
                this.walk(this.left_arm, walkSpeed, walkDegree, false, -2.5f, -0.1f, limbSwing, limbSwingAmount);
                this.walk(this.right_arm, walkSpeed, walkDegree, true, -2.5f, 0.1f, limbSwing, limbSwingAmount);
                this.walk(this.right_leg, walkSpeed, walkDegree, false, -2.5f, 0.1f, limbSwing, limbSwingAmount);
                this.walk(this.left_leg, walkSpeed, walkDegree, true, -2.5f, -0.1f, limbSwing, limbSwingAmount);
                this.left_hand.rotateAngleX -= this.left_arm.rotateAngleX + this.body.rotateAngleX;
                this.left_hand.rotateAngleZ -= this.body.rotateAngleZ;
                this.right_hand.rotateAngleX -= this.right_arm.rotateAngleX + this.body.rotateAngleX;
                this.right_hand.rotateAngleZ -= this.body.rotateAngleZ;
                this.left_foot.rotateAngleX -= this.left_leg.rotateAngleX + this.body.rotateAngleX;
                this.left_foot.rotateAngleZ -= this.body.rotateAngleZ;
                this.right_foot.rotateAngleX -= this.right_leg.rotateAngleX + this.body.rotateAngleX;
                this.right_foot.rotateAngleZ -= this.body.rotateAngleZ;
                this.left_arm.rotationPointY += 1.5f * (float)(Math.sin((double)(limbSwing * walkSpeed) - 2.5) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
                this.right_arm.rotationPointY += 1.5f * (float)(Math.sin(-((double)(limbSwing * walkSpeed)) + 2.5) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
                this.left_leg.rotationPointY += 1.5f * (float)(Math.sin((double)(limbSwing * walkSpeed) - 2.5) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
                this.right_leg.rotationPointY += 1.5f * (float)(Math.sin(-((double)(limbSwing * walkSpeed)) + 2.5) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
            } else {
                this.flap(this.tail, walkSpeed, walkDegree * -0.5f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
                this.flap(this.body, swimSpeed, swimDegree * 0.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
                this.flap(this.head, swimSpeed, swimDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
                this.flap(this.left_arm, swimSpeed, swimDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
                this.flap(this.right_arm, swimSpeed, swimDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
                this.flap(this.left_leg, swimSpeed, swimDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
                this.flap(this.right_leg, swimSpeed, swimDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
                this.flap(this.tail, swimSpeed, swimDegree * -0.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
                this.walk(this.left_arm, swimSpeed, swimDegree, false, 3.0f, 0.3f, limbSwing, limbSwingAmount);
                this.walk(this.right_arm, swimSpeed, swimDegree, false, 3.0f, 0.3f, limbSwing, limbSwingAmount);
                this.walk(this.left_leg, swimSpeed, swimDegree, false, 2.0f, 0.3f, limbSwing, limbSwingAmount);
                this.walk(this.right_leg, swimSpeed, swimDegree, false, 2.0f, 0.3f, limbSwing, limbSwingAmount);
            }
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
            this.head.setScale(0.9f, 0.9f, 0.9f);
        } else {
            this.head.setScale(0.9f, 0.9f, 0.9f);
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }
}
