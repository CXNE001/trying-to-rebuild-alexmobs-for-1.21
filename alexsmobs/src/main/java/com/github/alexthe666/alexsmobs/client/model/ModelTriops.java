/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.util.Mth
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityTriops;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelTriops
extends AdvancedEntityModel<EntityTriops> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox leftAntenna;
    private final AdvancedModelBox rightAntenna;
    private final AdvancedModelBox leftLegs;
    private final AdvancedModelBox rightLegs;
    private final AdvancedModelBox tail1;
    private final AdvancedModelBox tail2;
    private final AdvancedModelBox leftTailFlipper;
    private final AdvancedModelBox rightTailFlipper;

    public ModelTriops() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this);
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this);
        this.body.setRotationPoint(0.0f, -2.0f, -2.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-3.5f, -1.1f, -3.5f, 7.0f, 3.0f, 7.0f, 0.0f, false);
        this.leftAntenna = new AdvancedModelBox((AdvancedEntityModel)this);
        this.leftAntenna.setRotationPoint(3.5f, 2.0f, -2.0f);
        this.body.addChild((BasicModelPart)this.leftAntenna);
        this.leftAntenna.setTextureOffset(15, 21).addBox(-1.0f, -1.0f, 0.0f, 4.0f, 1.0f, 3.0f, 0.0f, false);
        this.rightAntenna = new AdvancedModelBox((AdvancedEntityModel)this);
        this.rightAntenna.setRotationPoint(-3.5f, 2.0f, -2.0f);
        this.body.addChild((BasicModelPart)this.rightAntenna);
        this.rightAntenna.setTextureOffset(15, 21).addBox(-3.0f, -1.0f, 0.0f, 4.0f, 1.0f, 3.0f, 0.0f, true);
        this.leftLegs = new AdvancedModelBox((AdvancedEntityModel)this);
        this.leftLegs.setRotationPoint(0.0f, 1.9f, 0.0f);
        this.body.addChild((BasicModelPart)this.leftLegs);
        this.setRotateAngle(this.leftLegs, 0.0f, 0.0f, 0.0873f);
        this.leftLegs.setTextureOffset(22, 0).addBox(0.0f, 0.0f, -2.0f, 3.0f, 0.0f, 4.0f, 0.0f, false);
        this.rightLegs = new AdvancedModelBox((AdvancedEntityModel)this);
        this.rightLegs.setRotationPoint(0.0f, 1.9f, 0.0f);
        this.body.addChild((BasicModelPart)this.rightLegs);
        this.setRotateAngle(this.rightLegs, 0.0f, 0.0f, -0.0873f);
        this.rightLegs.setTextureOffset(22, 0).addBox(-3.0f, 0.0f, -2.0f, 3.0f, 0.0f, 4.0f, 0.0f, true);
        this.tail1 = new AdvancedModelBox((AdvancedEntityModel)this);
        this.tail1.setRotationPoint(0.0f, 1.0f, 2.3f);
        this.body.addChild((BasicModelPart)this.tail1);
        this.tail1.setTextureOffset(0, 18).addBox(-1.5f, -1.0f, -0.8f, 3.0f, 2.0f, 4.0f, 0.0f, false);
        this.tail1.setTextureOffset(22, 11).addBox(1.5f, 1.0f, -0.8f, 2.0f, 0.0f, 4.0f, 0.0f, false);
        this.tail1.setTextureOffset(22, 11).addBox(-3.5f, 1.0f, -0.8f, 2.0f, 0.0f, 4.0f, 0.0f, true);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this);
        this.tail2.setRotationPoint(0.0f, 0.2f, 3.2f);
        this.tail1.addChild((BasicModelPart)this.tail2);
        this.tail2.setTextureOffset(11, 14).addBox(-1.5f, -1.2f, 0.0f, 3.0f, 2.0f, 4.0f, 0.0f, false);
        this.leftTailFlipper = new AdvancedModelBox((AdvancedEntityModel)this);
        this.leftTailFlipper.setRotationPoint(0.7f, -1.0f, 4.8f);
        this.tail2.addChild((BasicModelPart)this.leftTailFlipper);
        this.setRotateAngle(this.leftTailFlipper, 0.2618f, 0.2618f, 0.0f);
        this.leftTailFlipper.setTextureOffset(0, 11).addBox(0.0f, 0.0f, -1.0f, 1.0f, 0.0f, 6.0f, 0.0f, false);
        this.rightTailFlipper = new AdvancedModelBox((AdvancedEntityModel)this);
        this.rightTailFlipper.setRotationPoint(-0.7f, -1.0f, 4.8f);
        this.tail2.addChild((BasicModelPart)this.rightTailFlipper);
        this.setRotateAngle(this.rightTailFlipper, 0.2618f, -0.2618f, 0.0f);
        this.rightTailFlipper.setTextureOffset(0, 11).addBox(-1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 6.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.leftAntenna, (Object)this.rightAntenna, (Object)this.leftLegs, (Object)this.rightLegs, (Object)this.tail1, (Object)this.tail2, (Object)this.leftTailFlipper, (Object)this.rightTailFlipper);
    }

    public void setupAnim(EntityTriops entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.5f;
        float idleDegree = 0.2f;
        float swimSpeed = 0.65f;
        float swimDegree = 0.25f;
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float landProgress = entity.prevOnLandProgress + (entity.onLandProgress - entity.prevOnLandProgress) * partialTick;
        float swimAmount = 1.0f - landProgress * 0.2f;
        float swimRot = swimAmount * (entity.prevSwimRot + (entity.swimRot - entity.prevSwimRot) * partialTick);
        float yaw = entity.f_20884_ + (entity.f_20883_ - entity.f_20884_) * partialTick;
        float tail1Rot = Mth.m_14177_((float)(entity.prevTail1Yaw + (entity.tail1Yaw - entity.prevTail1Yaw) * partialTick - yaw)) * 0.35f;
        float tail2Rot = Mth.m_14177_((float)(entity.prevTail2Yaw + (entity.tail2Yaw - entity.prevTail2Yaw) * partialTick - yaw)) * 0.35f;
        this.progressRotationPrev(this.body, landProgress, 0.0f, 0.0f, Maths.rad(-180.0), 5.0f);
        this.progressPositionPrev(this.body, limbSwingAmount, 0.0f, -3.0f, 0.0f, 1.0f);
        this.progressRotationPrev(this.leftAntenna, 1.0f - limbSwingAmount, 0.0f, Maths.rad(20.0), 0.0f, 1.0f);
        this.progressRotationPrev(this.rightAntenna, 1.0f - limbSwingAmount, 0.0f, Maths.rad(-20.0), 0.0f, 1.0f);
        this.body.rotateAngleX += headPitch * ((float)Math.PI / 180) * swimAmount;
        this.body.rotateAngleZ += Maths.rad(swimRot);
        this.swing(this.rightAntenna, idleSpeed, idleDegree, false, 1.0f, -0.2f, ageInTicks, 1.0f);
        this.swing(this.leftAntenna, idleSpeed, idleDegree, true, 1.0f, -0.2f, ageInTicks, 1.0f);
        this.walk(this.leftTailFlipper, idleSpeed, idleDegree, false, 3.0f, 0.1f, ageInTicks, 1.0f);
        this.walk(this.rightTailFlipper, idleSpeed, idleDegree, false, 3.0f, 0.1f, ageInTicks, 1.0f);
        this.flap(this.leftLegs, idleSpeed * 3.0f, idleDegree, true, 1.0f, -0.2f, ageInTicks, 1.0f);
        this.flap(this.rightLegs, idleSpeed * 3.0f, idleDegree, false, 1.0f, -0.2f, ageInTicks, 1.0f);
        this.walk(this.body, swimSpeed, swimDegree, false, 2.5f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.tail1, swimSpeed, swimDegree, false, 1.5f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.tail2, swimSpeed, swimDegree * 1.5f, false, 0.5f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.leftTailFlipper, swimSpeed, swimDegree, false, 0.0f, -0.1f, limbSwing, limbSwingAmount);
        this.walk(this.rightTailFlipper, swimSpeed, swimDegree, false, 0.0f, -0.1f, limbSwing, limbSwingAmount);
        this.walk(this.tail1, idleSpeed, idleDegree, false, 0.0f, -0.1f, ageInTicks, landProgress * 0.2f);
        this.walk(this.tail2, idleSpeed, idleDegree * 1.5f, false, 0.0f, -0.3f, ageInTicks, landProgress * 0.2f);
        this.walk(this.leftTailFlipper, idleSpeed, idleDegree, false, 0.0f, -0.3f, ageInTicks, landProgress * 0.2f);
        this.walk(this.rightTailFlipper, idleSpeed, idleDegree, false, 0.0f, -0.3f, ageInTicks, landProgress * 0.2f);
        this.tail1.rotateAngleY += Maths.rad(tail1Rot);
        this.tail2.rotateAngleY += Maths.rad(tail2Rot);
    }
}
