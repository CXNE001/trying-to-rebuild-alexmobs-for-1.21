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

import com.github.alexthe666.alexsmobs.entity.EntityWarpedToad;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelWarpedToad
extends AdvancedEntityModel<EntityWarpedToad> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox tongue;
    private final AdvancedModelBox left_arm;
    private final AdvancedModelBox right_arm;
    private final AdvancedModelBox sac;
    private final AdvancedModelBox left_gland;
    private final AdvancedModelBox right_gland;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox left_foot_pivot;
    private final AdvancedModelBox left_foot;
    private final AdvancedModelBox right_leg;
    private final AdvancedModelBox right_foot;
    private final AdvancedModelBox right_foot_pivot;
    private final AdvancedModelBox head;
    private final AdvancedModelBox left_eye;
    private final AdvancedModelBox right_eye;

    public ModelWarpedToad() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -8.0f, 1.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 24).addBox(-7.0f, -2.0f, -12.0f, 14.0f, 7.0f, 11.0f, -0.001f, false);
        this.body.setTextureOffset(0, 43).addBox(-7.0f, 0.0f, -12.0f, 14.0f, 0.0f, 11.0f, 0.0f, false);
        this.body.setTextureOffset(0, 0).addBox(-7.0f, -6.0f, -1.0f, 14.0f, 11.0f, 12.0f, -0.001f, false);
        this.tongue = new AdvancedModelBox((AdvancedEntityModel)this, "tongue");
        this.tongue.setPos(0.0f, -1.0f, -1.0f);
        this.body.addChild((BasicModelPart)this.tongue);
        this.tongue.setTextureOffset(0, 55).addBox(-4.0f, 0.0f, -10.0f, 8.0f, 0.0f, 10.0f, 0.0f, false);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setPos(6.4f, 2.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.left_arm);
        this.left_arm.setTextureOffset(50, 69).addBox(-1.0f, -1.01f, -2.0f, 3.0f, 7.0f, 4.0f, 0.0f, false);
        this.left_arm.setTextureOffset(27, 55).addBox(-4.0f, 6.0f, -3.0f, 6.0f, 0.0f, 5.0f, 0.0f, false);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setPos(-6.4f, 2.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.right_arm);
        this.right_arm.setTextureOffset(50, 69).addBox(-2.0f, -1.01f, -2.0f, 3.0f, 7.0f, 4.0f, 0.0f, true);
        this.right_arm.setTextureOffset(27, 55).addBox(-2.0f, 6.0f, -3.0f, 6.0f, 0.0f, 5.0f, 0.0f, true);
        this.sac = new AdvancedModelBox((AdvancedEntityModel)this, "sac");
        this.sac.setPos(0.0f, 5.0f, -1.0f);
        this.body.addChild((BasicModelPart)this.sac);
        this.sac.setTextureOffset(42, 13).addBox(-7.0f, -5.0f, -11.0f, 14.0f, 5.0f, 11.0f, -0.1f, false);
        this.left_gland = new AdvancedModelBox((AdvancedEntityModel)this, "left_gland");
        this.left_gland.setPos(5.0f, -5.0f, 3.1f);
        this.body.addChild((BasicModelPart)this.left_gland);
        this.left_gland.setTextureOffset(0, 66).addBox(-2.0f, -2.0f, -4.0f, 5.0f, 4.0f, 8.0f, 0.0f, false);
        this.right_gland = new AdvancedModelBox((AdvancedEntityModel)this, "right_gland");
        this.right_gland.setPos(-5.0f, -5.0f, 3.1f);
        this.body.addChild((BasicModelPart)this.right_gland);
        this.right_gland.setTextureOffset(0, 66).addBox(-3.0f, -2.0f, -4.0f, 5.0f, 4.0f, 8.0f, 0.0f, true);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setPos(6.0f, 1.5f, 8.0f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(40, 50).addBox(-4.0f, -1.0f, -6.0f, 6.0f, 7.0f, 11.0f, 0.0f, false);
        this.left_foot_pivot = new AdvancedModelBox((AdvancedEntityModel)this, "left_foot_pivot");
        this.left_foot_pivot.setPos(0.0f, 5.5f, -3.0f);
        this.left_leg.addChild((BasicModelPart)this.left_foot_pivot);
        this.left_foot = new AdvancedModelBox((AdvancedEntityModel)this, "left_foot");
        this.left_foot_pivot.addChild((BasicModelPart)this.left_foot);
        this.left_foot.setTextureOffset(64, 50).addBox(-2.0f, 0.0f, -2.0f, 10.0f, 1.0f, 4.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setPos(-6.0f, 1.5f, 8.0f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(40, 50).addBox(-2.0f, -1.0f, -6.0f, 6.0f, 7.0f, 11.0f, 0.0f, true);
        this.right_foot_pivot = new AdvancedModelBox((AdvancedEntityModel)this, "right_foot_pivot");
        this.right_foot_pivot.setPos(0.0f, 5.5f, -3.0f);
        this.right_leg.addChild((BasicModelPart)this.right_foot_pivot);
        this.right_foot = new AdvancedModelBox((AdvancedEntityModel)this, "right_foot");
        this.right_foot_pivot.addChild((BasicModelPart)this.right_foot);
        this.right_foot.setTextureOffset(64, 50).addBox(-8.0f, 0.0f, -2.0f, 10.0f, 1.0f, 4.0f, 0.0f, true);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -2.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(40, 32).addBox(-7.0f, -4.0f, -9.0f, 14.0f, 6.0f, 11.0f, 0.0f, false);
        this.head.setTextureOffset(41, 0).addBox(-7.0f, -1.0f, -9.0f, 14.0f, 0.0f, 11.0f, 0.0f, false);
        this.left_eye = new AdvancedModelBox((AdvancedEntityModel)this, "left_eye");
        this.left_eye.setPos(7.0f, -4.0f, -6.0f);
        this.head.addChild((BasicModelPart)this.left_eye);
        this.left_eye.setTextureOffset(27, 69).addBox(-5.0f, -2.0f, 0.0f, 5.0f, 2.0f, 6.0f, 0.0f, false);
        this.left_eye.setTextureOffset(19, 66).addBox(-2.0f, -4.0f, 0.0f, 2.0f, 2.0f, 4.0f, 0.0f, false);
        this.right_eye = new AdvancedModelBox((AdvancedEntityModel)this, "right_eye");
        this.right_eye.setPos(-7.0f, -4.0f, -6.0f);
        this.head.addChild((BasicModelPart)this.right_eye);
        this.right_eye.setTextureOffset(27, 69).addBox(0.0f, -2.0f, 0.0f, 5.0f, 2.0f, 6.0f, 0.0f, true);
        this.right_eye.setTextureOffset(19, 66).addBox(0.0f, -4.0f, 0.0f, 2.0f, 2.0f, 4.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.head, (Object)this.right_eye, (Object)this.left_eye, (Object)this.sac, (Object)this.right_arm, (Object)this.right_leg, (Object)this.right_foot, (Object)this.right_gland, (Object)this.left_arm, (Object)this.left_leg, (Object[])new AdvancedModelBox[]{this.left_foot, this.left_gland, this.tongue, this.right_foot_pivot, this.left_foot_pivot});
    }

    public void setupAnim(EntityWarpedToad entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float partialTick = Minecraft.m_91087_().m_91296_();
        float attackProgress = entity.prevAttackProgress + (entity.attackProgress - entity.prevAttackProgress) * partialTick;
        float walkSpeed = 1.2f;
        float walkDegree = 0.5f;
        float swimSpeed = 0.4f;
        float swimDegree = 0.9f;
        float blinkProgress = Math.max(attackProgress, entity.prevBlinkProgress + (entity.blinkProgress - entity.prevBlinkProgress) * partialTick);
        float sitProgress = entity.prevSitProgress + (entity.sitProgress - entity.prevSitProgress) * partialTick;
        float swimProgress = entity.prevSwimProgress + (entity.swimProgress - entity.prevSwimProgress) * partialTick;
        float fallProgress = entity.prevReboundProgress + (entity.reboundProgress - entity.prevReboundProgress) * partialTick;
        float jumpProgress = Math.max(0.0f, entity.prevJumpProgress + (entity.jumpProgress - entity.prevJumpProgress) * partialTick - fallProgress);
        float glowyBob = 0.75f + (Mth.m_14089_((float)(ageInTicks * 0.2f)) + 1.0f) * 0.125f + attackProgress * 0.1f;
        float toungeScale = 1.0f + attackProgress * 0.285f * entity.getTongueLength();
        float toungeScaleCorners = 1.0f - attackProgress * 0.1f;
        this.right_gland.setScale(glowyBob, glowyBob, glowyBob);
        this.left_gland.setScale(glowyBob, glowyBob, glowyBob);
        this.tongue.setScale(toungeScaleCorners, toungeScaleCorners, toungeScale);
        this.progressPositionPrev(this.right_eye, blinkProgress, 0.1f, 1.5f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_eye, blinkProgress, -0.1f, 1.5f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, attackProgress, Maths.rad(-45.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tongue, attackProgress, Maths.rad(-5.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, attackProgress, 0.0f, 0.0f, -1.3f, 5.0f);
        this.progressPositionPrev(this.left_arm, sitProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_arm, sitProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_leg, sitProgress, 0.0f, -2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_leg, sitProgress, 0.0f, -2.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, sitProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, sitProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_arm, sitProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg, sitProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg, sitProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg, swimProgress, Maths.rad(90.0), Maths.rad(-20.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg, swimProgress, Maths.rad(90.0), Maths.rad(20.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.left_leg, swimProgress, 3.0f, -1.0f, 1.0f, 5.0f);
        this.progressPositionPrev(this.right_leg, swimProgress, -3.0f, -1.0f, 1.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, swimProgress, Maths.rad(-90.0), Maths.rad(-60.0), Maths.rad(30.0), 5.0f);
        this.progressRotationPrev(this.right_arm, swimProgress, Maths.rad(-90.0), Maths.rad(60.0), Maths.rad(-30.0), 5.0f);
        this.progressPositionPrev(this.left_arm, swimProgress, 0.0f, 0.0f, 1.0f, 5.0f);
        this.progressPositionPrev(this.right_arm, swimProgress, 0.0f, 0.0f, 1.0f, 5.0f);
        this.progressPositionPrev(this.body, swimProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        float walkAmount = (5.0f - swimProgress) * 0.2f - Math.max(jumpProgress, fallProgress) * 0.2f;
        float walkSwingAmount = limbSwingAmount * walkAmount;
        float swimSwingAmount = limbSwingAmount * 0.2f * swimProgress;
        this.swing(this.right_leg, swimSpeed, swimDegree, false, -2.5f, -0.3f, limbSwing, swimSwingAmount);
        this.swing(this.left_leg, swimSpeed, swimDegree, true, -2.5f, -0.3f, limbSwing, swimSwingAmount);
        this.flap(this.right_foot, swimSpeed, swimDegree, false, -1.0f, -0.3f, limbSwing, swimSwingAmount);
        this.flap(this.left_foot, swimSpeed, swimDegree, true, -1.0f, -0.3f, limbSwing, swimSwingAmount);
        this.flap(this.right_arm, swimSpeed, swimDegree, false, -2.5f, -0.1f, limbSwing, swimSwingAmount);
        this.flap(this.left_arm, swimSpeed, swimDegree, true, -2.5f, -0.1f, limbSwing, swimSwingAmount);
        this.swing(this.right_arm, swimSpeed, swimDegree, false, -2.5f, 0.3f, limbSwing, swimSwingAmount);
        this.swing(this.left_arm, swimSpeed, swimDegree, true, -2.5f, 0.3f, limbSwing, swimSwingAmount);
        this.swing(this.body, swimSpeed, swimDegree * 0.1f, false, 0.0f, 0.0f, limbSwing, swimSwingAmount);
        this.progressRotationPrev(this.left_foot, Math.min(walkSwingAmount, 0.5f), 0.0f, Maths.rad(80.0), 0.0f, 0.5f);
        this.progressRotationPrev(this.right_foot, Math.min(walkSwingAmount, 0.5f), 0.0f, Maths.rad(-80.0), 0.0f, 0.5f);
        this.flap(this.body, walkSpeed, walkDegree * 0.35f, false, 0.0f, 0.0f, limbSwing, walkSwingAmount);
        this.swing(this.body, walkSpeed, walkDegree * 0.35f, false, 1.0f, 0.0f, limbSwing, walkSwingAmount);
        this.walk(this.left_arm, walkSpeed, walkDegree, false, -2.5f, -0.3f, limbSwing, walkSwingAmount);
        this.walk(this.right_arm, walkSpeed, walkDegree, true, -2.5f, 0.3f, limbSwing, walkSwingAmount);
        this.walk(this.right_leg, walkSpeed, walkDegree, false, -2.5f, 0.1f, limbSwing, walkSwingAmount);
        this.walk(this.left_leg, walkSpeed, walkDegree, true, -2.5f, -0.1f, limbSwing, walkSwingAmount);
        this.left_foot_pivot.rotateAngleX -= walkAmount * (this.left_leg.rotateAngleX + this.body.rotateAngleX);
        this.left_foot_pivot.rotateAngleZ -= walkAmount * this.body.rotateAngleZ;
        this.right_foot_pivot.rotateAngleX -= walkAmount * (this.right_leg.rotateAngleX + this.body.rotateAngleX);
        this.right_foot_pivot.rotateAngleZ -= walkAmount * this.body.rotateAngleZ;
        this.left_arm.rotationPointZ += 1.5f * (float)(Math.sin((double)(limbSwing * walkSpeed) - 2.5) * (double)walkSwingAmount * (double)walkDegree - (double)(walkSwingAmount * walkDegree));
        this.left_arm.rotationPointY += 0.5f * (float)(Math.sin((double)(limbSwing * walkSpeed) - 2.5) * (double)walkSwingAmount * (double)walkDegree - (double)(walkSwingAmount * walkDegree));
        this.right_arm.rotationPointZ += 1.5f * (float)(Math.sin(-((double)(limbSwing * walkSpeed)) + 2.5) * (double)walkSwingAmount * (double)walkDegree - (double)(walkSwingAmount * walkDegree));
        this.right_arm.rotationPointY += 0.5f * (float)(Math.sin(-((double)(limbSwing * walkSpeed)) + 2.5) * (double)walkSwingAmount * (double)walkDegree - (double)(walkSwingAmount * walkDegree));
        float leftLegS = (float)(Math.sin((double)(limbSwing * walkSpeed) - 2.5) * (double)walkSwingAmount * (double)walkDegree - (double)(walkSwingAmount * walkDegree));
        float rightLegs = (float)(Math.sin(-((double)(limbSwing * walkSpeed)) + 2.5) * (double)walkSwingAmount * (double)walkDegree - (double)(walkSwingAmount * walkDegree));
        this.left_leg.rotationPointY += 1.5f * leftLegS;
        this.right_leg.rotationPointY += 1.5f * rightLegs;
        this.left_leg.rotationPointZ -= 3.0f * leftLegS;
        this.right_leg.rotationPointZ -= 3.0f * rightLegs;
        this.left_foot_pivot.rotationPointZ -= 1.5f * leftLegS;
        this.right_foot_pivot.rotationPointZ -= 1.5f * rightLegs;
        if (attackProgress > 0.0f) {
            this.tongue.rotateAngleX += headPitch * ((float)Math.PI / 180);
        }
        this.progressRotationPrev(this.body, fallProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, fallProgress, Maths.rad(-35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_arm, fallProgress, Maths.rad(-35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg, fallProgress, Maths.rad(35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg, fallProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_foot_pivot, fallProgress, Maths.rad(-35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_foot_pivot, fallProgress, Maths.rad(-35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_foot, fallProgress, 0.0f, Maths.rad(70.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.right_foot, fallProgress, 0.0f, Maths.rad(-70.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.left_foot_pivot, fallProgress, 0.0f, 1.0f, -1.0f, 5.0f);
        this.progressPositionPrev(this.right_foot_pivot, fallProgress, 0.0f, 1.0f, -1.0f, 5.0f);
        this.progressRotationPrev(this.body, jumpProgress, Maths.rad(-35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, jumpProgress, Maths.rad(-35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_arm, jumpProgress, Maths.rad(-35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg, jumpProgress, Maths.rad(150.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg, jumpProgress, Maths.rad(150.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_foot_pivot, fallProgress, Maths.rad(35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_foot_pivot, fallProgress, Maths.rad(35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_foot, jumpProgress, Maths.rad(20.0), Maths.rad(70.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.right_foot, jumpProgress, Maths.rad(20.0), Maths.rad(-70.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.left_leg, jumpProgress, 0.0f, 1.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.right_leg, jumpProgress, 0.0f, 1.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.left_arm, jumpProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_arm, jumpProgress, 0.0f, 1.0f, 0.0f, 5.0f);
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.35f, 0.35f, 0.35f);
            matrixStackIn.m_85837_(0.0, 2.75, 0.125);
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
