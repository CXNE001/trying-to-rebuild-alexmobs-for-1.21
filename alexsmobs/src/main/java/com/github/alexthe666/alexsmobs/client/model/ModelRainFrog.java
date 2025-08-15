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

import com.github.alexthe666.alexsmobs.entity.EntityRainFrog;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class ModelRainFrog
extends AdvancedEntityModel<EntityRainFrog> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox tongue;
    private final AdvancedModelBox left_arm;
    private final AdvancedModelBox right_arm;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox right_leg;
    private final AdvancedModelBox left_eye;
    private final AdvancedModelBox right_eye;

    public ModelRainFrog() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -3.0f, -0.5f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-3.5f, -3.0f, -4.5f, 7.0f, 5.0f, 9.0f, 0.0f, false);
        this.tongue = new AdvancedModelBox((AdvancedEntityModel)this, "tongue");
        this.tongue.setRotationPoint(0.0f, -1.0f, -4.5f);
        this.body.addChild((BasicModelPart)this.tongue);
        this.tongue.setTextureOffset(0, 0).addBox(-1.0f, 0.0f, -2.0f, 2.0f, 0.0f, 2.0f, 0.0f, false);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setRotationPoint(3.5f, 0.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.left_arm);
        this.left_arm.setTextureOffset(0, 15).addBox(-3.0f, -0.01f, -2.0f, 4.0f, 3.0f, 4.0f, 0.0f, false);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setRotationPoint(-3.5f, 0.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.right_arm);
        this.right_arm.setTextureOffset(0, 15).addBox(-1.0f, -0.01f, -2.0f, 4.0f, 3.0f, 4.0f, 0.0f, true);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setRotationPoint(2.5f, 1.25f, 2.25f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(15, 21).addBox(-0.5f, -1.25f, -0.25f, 2.0f, 3.0f, 2.0f, 0.0f, false);
        this.left_leg.setTextureOffset(17, 15).addBox(-0.5f, 1.749f, -2.25f, 4.0f, 0.0f, 4.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setRotationPoint(-2.5f, 1.25f, 2.25f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(15, 21).addBox(-1.5f, -1.25f, -0.25f, 2.0f, 3.0f, 2.0f, 0.0f, true);
        this.right_leg.setTextureOffset(17, 15).addBox(-3.5f, 1.749f, -2.25f, 4.0f, 0.0f, 4.0f, 0.0f, true);
        this.left_eye = new AdvancedModelBox((AdvancedEntityModel)this, "left_eye");
        this.left_eye.setRotationPoint(1.5f, -3.0f, -2.5f);
        this.body.addChild((BasicModelPart)this.left_eye);
        this.left_eye.setTextureOffset(0, 23).addBox(-1.0f, -1.0f, -2.0f, 2.0f, 1.0f, 3.0f, 0.0f, false);
        this.right_eye = new AdvancedModelBox((AdvancedEntityModel)this, "right_eye");
        this.right_eye.setRotationPoint(-1.5f, -3.0f, -2.5f);
        this.body.addChild((BasicModelPart)this.right_eye);
        this.right_eye.setTextureOffset(0, 23).addBox(-1.0f, -1.0f, -2.0f, 2.0f, 1.0f, 3.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.right_eye, (Object)this.left_eye, (Object)this.right_arm, (Object)this.left_arm, (Object)this.right_leg, (Object)this.left_leg, (Object)this.tongue);
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.25f;
            this.right_eye.setScale(f, f, f);
            this.left_eye.setScale(f, f, f);
            this.right_eye.setShouldScaleChildren(true);
            this.left_eye.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.right_eye.setScale(1.0f, 1.0f, 1.0f);
            this.left_eye.setScale(1.0f, 1.0f, 1.0f);
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setupAnim(EntityRainFrog entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float walkSpeed = this.f_102610_ ? 1.0f : 2.3f;
        float walkDegree = 1.0f;
        float digSpeed = 0.8f;
        float digDegree = 0.5f;
        float danceSpeed = 0.8f;
        float danceDegree = 0.5f;
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float burrowProgress = entity.prevBurrowProgress + (entity.burrowProgress - entity.prevBurrowProgress) * partialTick;
        float danceProgress = entity.prevDanceProgress + (entity.danceProgress - entity.prevDanceProgress) * partialTick;
        float attackProgress = entity.prevAttackProgress + (entity.attackProgress - entity.prevAttackProgress) * partialTick;
        float stanceProgress = entity.prevStanceProgress + (entity.stanceProgress - entity.prevStanceProgress) * partialTick;
        float blinkAmount = Math.max(0.0f, ((float)Math.sin(ageInTicks * 0.1f) - 0.5f) * 2.0f) * (5.0f - stanceProgress) * 0.2f;
        float digAmount = Mth.m_14036_((float)((float)Math.sin((double)burrowProgress * Math.PI / 5.0)), (float)0.0f, (float)1.0f);
        this.progressPositionPrev(this.right_eye, blinkAmount, 0.0f, 0.9f, 0.1f, 1.0f);
        this.progressPositionPrev(this.left_eye, blinkAmount, 0.0f, 0.9f, 0.1f, 1.0f);
        this.progressPositionPrev(this.body, danceProgress, 0.0f, -2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_leg, danceProgress, 0.0f, 0.7f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_leg, danceProgress, 0.0f, 0.7f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, burrowProgress, 0.0f, 5.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.tongue, 5.0f - attackProgress, 0.0f, 0.0f, 3.0f, 5.0f);
        this.progressPositionPrev(this.body, attackProgress, 0.0f, 0.0f, -2.0f, 5.0f);
        this.progressRotationPrev(this.body, attackProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg, attackProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg, attackProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, attackProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_arm, attackProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_leg, attackProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_leg, attackProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, stanceProgress, 0.0f, -2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_leg, stanceProgress, 0.0f, 2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_leg, stanceProgress, 0.0f, 2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_arm, stanceProgress, 0.0f, 2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_arm, stanceProgress, 0.0f, 2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_eye, stanceProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_eye, stanceProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.body.setScale(1.0f + stanceProgress * 0.025f, 1.0f + stanceProgress * 0.075f, 1.0f + stanceProgress * 0.025f);
        this.swing(this.body, digSpeed, digDegree * 0.5f, false, 3.0f, 0.0f, ageInTicks, digAmount);
        this.walk(this.right_arm, digSpeed, digDegree, false, -1.5f, -0.2f, ageInTicks, digAmount);
        this.walk(this.left_arm, digSpeed, digDegree, false, -1.5f, -0.2f, ageInTicks, digAmount);
        this.walk(this.right_leg, digSpeed, digDegree, false, -1.5f, 0.2f, ageInTicks, digAmount);
        this.walk(this.left_leg, digSpeed, digDegree, false, -1.5f, 0.2f, ageInTicks, digAmount);
        this.flap(this.body, walkSpeed, walkDegree * 0.35f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.body, walkSpeed, walkDegree * 0.35f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.left_arm, walkSpeed, walkDegree * 1.2f, false, -2.5f, -0.2f, limbSwing, limbSwingAmount);
        this.walk(this.right_arm, walkSpeed, walkDegree * 1.2f, true, -2.5f, 0.2f, limbSwing, limbSwingAmount);
        this.walk(this.right_leg, walkSpeed, walkDegree, false, -2.5f, 0.3f, limbSwing, limbSwingAmount);
        this.walk(this.left_leg, walkSpeed, walkDegree, true, -2.5f, -0.3f, limbSwing, limbSwingAmount);
        float leftLegS = (float)(Math.sin((double)(limbSwing * walkSpeed) - 2.5) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
        float rightLegS = (float)(Math.sin(-((double)(limbSwing * walkSpeed)) + 2.5) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
        this.left_leg.rotationPointY += 1.5f * leftLegS;
        this.right_leg.rotationPointY += 1.5f * rightLegS;
        this.left_leg.rotationPointZ -= 2.0f * leftLegS;
        this.right_leg.rotationPointZ -= 2.0f * rightLegS;
        this.right_arm.rotationPointY += 1.5f * leftLegS;
        this.left_arm.rotationPointY += 1.5f * rightLegS;
        this.right_arm.rotationPointZ += 1.0f * leftLegS;
        this.left_arm.rotationPointZ += 1.0f * rightLegS;
        this.swing(this.body, danceSpeed, danceDegree * 0.5f, false, 1.0f, 0.0f, ageInTicks, danceProgress * 0.2f);
        this.walk(this.body, danceSpeed, danceDegree * 0.5f, false, 3.0f, -0.4f, ageInTicks, danceProgress * 0.2f);
        this.flap(this.right_arm, danceSpeed, danceDegree, false, 0.0f, 0.3f, ageInTicks, danceProgress * 0.2f);
        this.flap(this.left_arm, danceSpeed, danceDegree, true, 0.0f, 0.3f, ageInTicks, danceProgress * 0.2f);
        this.left_leg.rotateAngleX -= 1.0f * this.body.rotateAngleX;
        this.left_leg.rotateAngleY -= 1.0f * this.body.rotateAngleY;
        this.left_leg.rotateAngleZ -= 1.0f * this.body.rotateAngleZ;
        this.right_leg.rotateAngleX -= 1.0f * this.body.rotateAngleX;
        this.right_leg.rotateAngleY -= 1.0f * this.body.rotateAngleY;
        this.right_leg.rotateAngleZ -= 1.0f * this.body.rotateAngleZ;
    }
}
