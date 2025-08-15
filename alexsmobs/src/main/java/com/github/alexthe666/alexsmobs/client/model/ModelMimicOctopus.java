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

import com.github.alexthe666.alexsmobs.entity.EntityMimicOctopus;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelMimicOctopus
extends AdvancedEntityModel<EntityMimicOctopus> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox leftEye;
    private final AdvancedModelBox leftEyeSpike;
    private final AdvancedModelBox rightEye;
    private final AdvancedModelBox rightEyeSpike;
    private final AdvancedModelBox leftFrontArm1;
    private final AdvancedModelBox rightFrontArm1;
    private final AdvancedModelBox leftFrontArm2;
    private final AdvancedModelBox rightFrontArm2;
    private final AdvancedModelBox leftBackArm1;
    private final AdvancedModelBox rightBackArm1;
    private final AdvancedModelBox leftBackArm2;
    private final AdvancedModelBox rightBackArm2;
    private final AdvancedModelBox mantle;
    private final AdvancedModelBox creeperPivots1;
    private final AdvancedModelBox creeperPivots2;
    private final AdvancedModelBox creeperPivots3;
    private final AdvancedModelBox creeperPivots4;

    public ModelMimicOctopus() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -3.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(30, 24).addBox(-3.0f, -2.0f, -4.0f, 6.0f, 4.0f, 7.0f, 0.0f, false);
        this.leftEye = new AdvancedModelBox((AdvancedEntityModel)this, "leftEye");
        this.leftEye.setRotationPoint(2.0f, -2.0f, -2.5f);
        this.body.addChild((BasicModelPart)this.leftEye);
        this.leftEye.setTextureOffset(35, 18).addBox(-1.0f, -1.0f, -1.5f, 2.0f, 1.0f, 3.0f, 0.0f, false);
        this.leftEyeSpike = new AdvancedModelBox((AdvancedEntityModel)this, "leftEyeSpike");
        this.leftEyeSpike.setRotationPoint(-1.0f, -1.0f, -1.5f);
        this.leftEye.addChild((BasicModelPart)this.leftEyeSpike);
        this.leftEyeSpike.setTextureOffset(0, 0).addBox(0.0f, -4.0f, 0.0f, 0.0f, 4.0f, 2.0f, 0.0f, false);
        this.rightEye = new AdvancedModelBox((AdvancedEntityModel)this, "rightEye");
        this.rightEye.setRotationPoint(-2.0f, -2.0f, -2.5f);
        this.body.addChild((BasicModelPart)this.rightEye);
        this.rightEye.setTextureOffset(35, 18).addBox(-1.0f, -1.0f, -1.5f, 2.0f, 1.0f, 3.0f, 0.0f, true);
        this.rightEyeSpike = new AdvancedModelBox((AdvancedEntityModel)this, "rightEyeSpike");
        this.rightEyeSpike.setRotationPoint(1.0f, -1.0f, -1.5f);
        this.rightEye.addChild((BasicModelPart)this.rightEyeSpike);
        this.rightEyeSpike.setTextureOffset(0, 0).addBox(0.0f, -4.0f, 0.0f, 0.0f, 4.0f, 2.0f, 0.0f, true);
        this.leftFrontArm1 = new AdvancedModelBox((AdvancedEntityModel)this, "leftFrontArm1");
        this.leftFrontArm1.setRotationPoint(2.0f, 2.0f, -4.0f);
        this.setRotationAngle(this.leftFrontArm1, 0.0f, 0.6109f, 0.0f);
        this.leftFrontArm1.setTextureOffset(26, 0).addBox(-1.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f, false);
        this.leftFrontArm1.setTextureOffset(35, 11).addBox(11.0f, -4.0f, -1.0f, 4.0f, 3.0f, 2.0f, 0.0f, false);
        this.rightFrontArm1 = new AdvancedModelBox((AdvancedEntityModel)this, "rightFrontArm1");
        this.rightFrontArm1.setRotationPoint(-2.0f, 2.0f, -4.0f);
        this.setRotationAngle(this.rightFrontArm1, 0.0f, -0.6109f, 0.0f);
        this.rightFrontArm1.setTextureOffset(26, 0).addBox(-15.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f, true);
        this.rightFrontArm1.setTextureOffset(35, 11).addBox(-15.0f, -4.0f, -1.0f, 4.0f, 3.0f, 2.0f, 0.0f, true);
        this.leftFrontArm2 = new AdvancedModelBox((AdvancedEntityModel)this, "leftFrontArm2");
        this.leftFrontArm2.setRotationPoint(2.0f, 2.0f, -2.3f);
        this.setRotationAngle(this.leftFrontArm2, 0.0f, 0.3054f, 0.0f);
        this.leftFrontArm2.setTextureOffset(0, 26).addBox(-1.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f, false);
        this.leftFrontArm2.setTextureOffset(35, 5).addBox(11.0f, -4.0f, -1.0f, 4.0f, 3.0f, 2.0f, 0.0f, false);
        this.rightFrontArm2 = new AdvancedModelBox((AdvancedEntityModel)this, "rightFrontArm2");
        this.rightFrontArm2.setRotationPoint(-2.0f, 2.0f, -2.3f);
        this.setRotationAngle(this.rightFrontArm2, 0.0f, -0.3054f, 0.0f);
        this.rightFrontArm2.setTextureOffset(0, 26).addBox(-15.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f, true);
        this.rightFrontArm2.setTextureOffset(35, 5).addBox(-15.0f, -4.0f, -1.0f, 4.0f, 3.0f, 2.0f, 0.0f, true);
        this.creeperPivots1 = new AdvancedModelBox((AdvancedEntityModel)this, "creeperPivots1");
        this.creeperPivots2 = new AdvancedModelBox((AdvancedEntityModel)this, "creeperPivots2");
        this.creeperPivots3 = new AdvancedModelBox((AdvancedEntityModel)this, "creeperPivots3");
        this.creeperPivots4 = new AdvancedModelBox((AdvancedEntityModel)this, "creeperPivots4");
        this.body.addChild((BasicModelPart)this.creeperPivots1);
        this.body.addChild((BasicModelPart)this.creeperPivots2);
        this.body.addChild((BasicModelPart)this.creeperPivots3);
        this.body.addChild((BasicModelPart)this.creeperPivots4);
        this.leftBackArm1 = new AdvancedModelBox((AdvancedEntityModel)this, "leftBackArm1");
        this.leftBackArm1.setRotationPoint(2.0f, 2.0f, -1.0f);
        this.setRotationAngle(this.leftBackArm1, 0.0f, -0.2182f, 0.0f);
        this.leftBackArm1.setTextureOffset(0, 21).addBox(-1.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f, false);
        this.leftBackArm1.setTextureOffset(13, 31).addBox(11.0f, -4.0f, -1.0f, 4.0f, 3.0f, 2.0f, 0.0f, false);
        this.rightBackArm1 = new AdvancedModelBox((AdvancedEntityModel)this, "rightBackArm1");
        this.rightBackArm1.setRotationPoint(-2.0f, 2.0f, -1.0f);
        this.setRotationAngle(this.rightBackArm1, 0.0f, 0.2182f, 0.0f);
        this.rightBackArm1.setTextureOffset(0, 21).addBox(-15.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f, true);
        this.rightBackArm1.setTextureOffset(13, 31).addBox(-15.0f, -4.0f, -1.0f, 4.0f, 3.0f, 2.0f, 0.0f, true);
        this.leftBackArm2 = new AdvancedModelBox((AdvancedEntityModel)this, "leftBackArm2");
        this.leftBackArm2.setRotationPoint(2.0f, 2.0f, 1.0f);
        this.setRotationAngle(this.leftBackArm2, 0.0f, -0.6981f, 0.0f);
        this.leftBackArm2.setTextureOffset(0, 16).addBox(-1.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f, false);
        this.leftBackArm2.setTextureOffset(0, 31).addBox(11.0f, -4.0f, -1.0f, 4.0f, 3.0f, 2.0f, 0.0f, false);
        this.rightBackArm2 = new AdvancedModelBox((AdvancedEntityModel)this, "rightBackArm2");
        this.rightBackArm2.setRotationPoint(-2.0f, 2.0f, 1.0f);
        this.setRotationAngle(this.rightBackArm2, 0.0f, 0.6981f, 0.0f);
        this.rightBackArm2.setTextureOffset(0, 16).addBox(-15.0f, -1.0f, -1.0f, 16.0f, 2.0f, 2.0f, 0.0f, true);
        this.rightBackArm2.setTextureOffset(0, 31).addBox(-15.0f, -4.0f, -1.0f, 4.0f, 3.0f, 2.0f, 0.0f, true);
        this.mantle = new AdvancedModelBox((AdvancedEntityModel)this, "mantle");
        this.mantle.setRotationPoint(0.0f, -1.0f, 2.0f);
        this.body.addChild((BasicModelPart)this.mantle);
        this.mantle.setTextureOffset(0, 0).addBox(-4.0f, -4.0f, -2.0f, 8.0f, 6.0f, 9.0f, 0.0f, false);
        this.creeperPivots1.addChild((BasicModelPart)this.leftFrontArm1);
        this.creeperPivots1.addChild((BasicModelPart)this.leftFrontArm2);
        this.creeperPivots2.addChild((BasicModelPart)this.leftBackArm1);
        this.creeperPivots2.addChild((BasicModelPart)this.leftBackArm2);
        this.creeperPivots3.addChild((BasicModelPart)this.rightFrontArm1);
        this.creeperPivots3.addChild((BasicModelPart)this.rightFrontArm2);
        this.creeperPivots4.addChild((BasicModelPart)this.rightBackArm1);
        this.creeperPivots4.addChild((BasicModelPart)this.rightBackArm2);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityMimicOctopus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float partialTicks = ageInTicks - (float)entity.f_19797_;
        float transProgress = entity.prevTransProgress + (entity.transProgress - entity.prevTransProgress) * partialTicks;
        float groundProgress = entity.prevGroundProgress + (entity.groundProgress - entity.prevGroundProgress) * partialTicks;
        float sitProgress = entity.prevSitProgress + (entity.sitProgress - entity.prevSitProgress) * partialTicks;
        float notSitProgress = 1.0f - sitProgress * 0.2f;
        float swimProgress = (5.0f - groundProgress) * notSitProgress;
        float groundProgressNorm = groundProgress * 0.2f * notSitProgress;
        if (entity.getPrevMimicState() != null) {
            float progress = notSitProgress * (5.0f - transProgress);
            this.animateForMimicGround(entity.getPrevMimicState(), entity, limbSwing, limbSwingAmount, ageInTicks, progress * groundProgressNorm);
            if (sitProgress == 0.0f) {
                this.animateForMimicWater(entity.getPrevMimicState(), entity, limbSwing, limbSwingAmount, ageInTicks, progress * (1.0f - groundProgressNorm));
            }
        }
        this.animateForMimicGround(entity.getMimicState(), entity, limbSwing, limbSwingAmount, ageInTicks, notSitProgress * transProgress * groundProgressNorm);
        this.animateForMimicWater(entity.getMimicState(), entity, limbSwing, limbSwingAmount, ageInTicks, notSitProgress * transProgress * (1.0f - groundProgressNorm));
        if (swimProgress > 0.0f) {
            float rot = headPitch * ((float)Math.PI / 180);
            this.body.rotationPointY += Math.abs(rot) * -7.0f;
            this.body.rotateAngleX -= rot;
        }
        this.progressRotationPrev(this.mantle, sitProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leftFrontArm1, sitProgress, 0.0f, Maths.rad(10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.leftFrontArm2, sitProgress, 0.0f, Maths.rad(-5.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.leftBackArm1, sitProgress, 0.0f, Maths.rad(-10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.leftBackArm2, sitProgress, 0.0f, Maths.rad(-15.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.rightFrontArm1, sitProgress, 0.0f, Maths.rad(-10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.rightFrontArm2, sitProgress, 0.0f, Maths.rad(5.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.rightBackArm1, sitProgress, 0.0f, Maths.rad(10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.rightBackArm2, sitProgress, 0.0f, Maths.rad(15.0), 0.0f, 5.0f);
    }

    public void animateForMimicWater(EntityMimicOctopus.MimicState state, EntityMimicOctopus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float swimProgress) {
        limbSwingAmount = limbSwingAmount * swimProgress * 0.2f;
        this.progressRotationPrev(this.body, swimProgress, 0.0f, Maths.rad(-180.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.leftFrontArm1, swimProgress, Maths.rad(-90.0), Maths.rad(10.0), Maths.rad(-50.0), 5.0f);
        this.progressRotationPrev(this.leftFrontArm2, swimProgress, Maths.rad(-90.0), Maths.rad(20.0), Maths.rad(-20.0), 5.0f);
        this.progressRotationPrev(this.leftBackArm1, swimProgress, Maths.rad(-90.0), Maths.rad(50.0), Maths.rad(20.0), 5.0f);
        this.progressRotationPrev(this.leftBackArm2, swimProgress, Maths.rad(-90.0), Maths.rad(70.0), Maths.rad(50.0), 5.0f);
        this.progressPositionPrev(this.leftFrontArm1, swimProgress, -1.0f, -1.0f, 1.0f, 5.0f);
        this.progressPosition(this.leftFrontArm2, swimProgress, this.leftFrontArm1.rotationPointX, this.leftFrontArm1.rotationPointY, this.leftFrontArm1.rotationPointZ, 5.0f);
        this.progressPosition(this.leftBackArm1, swimProgress, this.leftFrontArm1.rotationPointX, this.leftFrontArm1.rotationPointY, this.leftFrontArm1.rotationPointZ, 5.0f);
        this.progressPosition(this.leftBackArm2, swimProgress, this.leftFrontArm1.rotationPointX, this.leftFrontArm1.rotationPointY, this.leftFrontArm1.rotationPointZ, 5.0f);
        this.progressRotationPrev(this.rightFrontArm1, swimProgress, Maths.rad(-90.0), Maths.rad(-10.0), Maths.rad(50.0), 5.0f);
        this.progressRotationPrev(this.rightFrontArm2, swimProgress, Maths.rad(-90.0), Maths.rad(-20.0), Maths.rad(20.0), 5.0f);
        this.progressRotationPrev(this.rightBackArm1, swimProgress, Maths.rad(-90.0), Maths.rad(-50.0), Maths.rad(-20.0), 5.0f);
        this.progressRotationPrev(this.rightBackArm2, swimProgress, Maths.rad(-90.0), Maths.rad(-70.0), Maths.rad(-50.0), 5.0f);
        this.progressPositionPrev(this.rightFrontArm1, swimProgress, 1.0f, -1.0f, 1.0f, 5.0f);
        this.progressPosition(this.rightFrontArm2, swimProgress, this.rightFrontArm1.rotationPointX, this.rightFrontArm1.rotationPointY, this.rightFrontArm1.rotationPointZ, 5.0f);
        this.progressPosition(this.rightBackArm1, swimProgress, this.rightFrontArm1.rotationPointX, this.rightFrontArm1.rotationPointY, this.rightFrontArm1.rotationPointZ, 5.0f);
        this.progressPosition(this.rightBackArm2, swimProgress, this.rightFrontArm1.rotationPointX, this.rightFrontArm1.rotationPointY, this.rightFrontArm1.rotationPointZ, 5.0f);
        if (state == EntityMimicOctopus.MimicState.GUARDIAN) {
            float degree = 1.6f;
            float speed = 0.5f;
            this.progressPositionPrev(this.body, swimProgress, 0.0f, -4.0f, 5.0f, 5.0f);
            this.progressPositionPrev(this.mantle, swimProgress, 0.0f, 2.0f, 0.0f, 5.0f);
            if (swimProgress > 0.0f) {
                this.mantle.setScale(1.0f + swimProgress * 0.1f, 1.0f + swimProgress * 0.1f, 1.0f + swimProgress * 0.1f);
            }
            this.progressRotationPrev(this.leftFrontArm1, swimProgress, Maths.rad(90.0), Maths.rad(45.0), Maths.rad(50.0), 5.0f);
            this.progressRotationPrev(this.rightFrontArm1, swimProgress, Maths.rad(-90.0), Maths.rad(-45.0), Maths.rad(-50.0), 5.0f);
            this.progressPositionPrev(this.leftFrontArm1, swimProgress, -1.0f, -1.0f, 0.0f, 5.0f);
            this.progressPositionPrev(this.rightFrontArm1, swimProgress, 1.0f, 1.0f, 0.0f, 5.0f);
            this.progressRotationPrev(this.leftFrontArm2, swimProgress, Maths.rad(90.0), Maths.rad(-40.0), Maths.rad(-15.0), 5.0f);
            this.progressPositionPrev(this.leftFrontArm2, swimProgress, 0.0f, 1.0f, 5.0f, 5.0f);
            this.progressRotationPrev(this.leftBackArm1, swimProgress, Maths.rad(90.0), Maths.rad(-20.0), Maths.rad(10.0), 5.0f);
            this.progressPositionPrev(this.leftBackArm1, swimProgress, -1.0f, 0.0f, 2.0f, 5.0f);
            this.progressRotationPrev(this.leftBackArm2, swimProgress, Maths.rad(90.0), Maths.rad(-70.0), Maths.rad(-15.0), 5.0f);
            this.progressPositionPrev(this.leftBackArm2, swimProgress, 0.0f, 1.0f, 5.0f, 5.0f);
            this.progressRotationPrev(this.rightFrontArm2, swimProgress, Maths.rad(90.0), Maths.rad(40.0), Maths.rad(15.0), 5.0f);
            this.progressPositionPrev(this.rightFrontArm2, swimProgress, 0.0f, 1.0f, 5.0f, 5.0f);
            this.progressRotationPrev(this.rightBackArm1, swimProgress, Maths.rad(90.0), Maths.rad(20.0), Maths.rad(-10.0), 5.0f);
            this.progressPositionPrev(this.rightBackArm1, swimProgress, 1.0f, 0.0f, 2.0f, 5.0f);
            this.progressRotationPrev(this.rightBackArm2, swimProgress, Maths.rad(90.0), Maths.rad(70.0), Maths.rad(15.0), 5.0f);
            this.progressPositionPrev(this.rightBackArm2, swimProgress, 0.0f, 1.0f, 5.0f, 5.0f);
            this.swing(this.leftFrontArm1, speed, degree * 0.25f, true, 0.0f, 0.1f, limbSwing, limbSwingAmount);
            this.swing(this.rightFrontArm1, speed, degree * 0.25f, true, 0.0f, 0.1f, limbSwing, limbSwingAmount);
        } else {
            float f = 1.0f;
            if (state == EntityMimicOctopus.MimicState.CREEPER) {
                this.progressPositionPrev(this.body, swimProgress, 0.0f, -3.0f, -2.0f, 5.0f);
                this.progressPositionPrev(this.mantle, swimProgress, 0.0f, -2.0f, 1.0f, 5.0f);
                this.progressRotationPrev(this.mantle, swimProgress, Maths.rad(-60.0), 0.0f, 0.0f, 5.0f);
                f = 0.5f;
            }
            float degree = 1.6f;
            float speed = 0.5f;
            this.bob(this.body, speed, degree * 2.0f, false, limbSwing, limbSwingAmount);
            if (swimProgress > 0.0f) {
                if (state == EntityMimicOctopus.MimicState.PUFFERFISH) {
                    float f2 = 1.4f + 0.15f * Mth.m_14031_((float)(speed * limbSwing - 2.0f)) * swimProgress * 0.2f;
                    this.progressPositionPrev(this.mantle, swimProgress, 0.0f, 1.0f, 0.0f, 5.0f);
                    this.mantle.setScale(f2, f2, f2);
                } else {
                    float scale = 1.1f + 0.3f * f * Mth.m_14031_((float)(speed * limbSwing - 2.0f));
                    this.mantle.setScale((scale - 1.0f) * swimProgress * 0.05f + 1.0f, (scale - 1.0f) * swimProgress * 0.05f + 1.0f, (scale - 1.0f) * swimProgress * 0.2f + 1.0f);
                }
            }
            this.walk(this.rightEyeSpike, speed, degree * 0.2f, true, -2.0f, 0.1f, limbSwing, limbSwingAmount);
            this.walk(this.leftEyeSpike, speed, degree * 0.2f, true, -2.0f, 0.1f, limbSwing, limbSwingAmount);
            this.swing(this.leftFrontArm1, speed, degree * 0.35f, true, 0.0f, -0.1f, limbSwing, limbSwingAmount);
            this.swing(this.leftFrontArm2, speed, degree * 0.35f, true, 0.0f, -0.1f, limbSwing, limbSwingAmount);
            this.swing(this.leftBackArm1, speed, degree * 0.35f, true, 0.0f, -0.1f, limbSwing, limbSwingAmount);
            this.swing(this.leftBackArm2, speed, degree * 0.35f, true, 0.0f, -0.1f, limbSwing, limbSwingAmount);
            this.swing(this.rightFrontArm1, speed, degree * 0.35f, false, 0.0f, -0.1f, limbSwing, limbSwingAmount);
            this.swing(this.rightFrontArm2, speed, degree * 0.35f, false, 0.0f, -0.1f, limbSwing, limbSwingAmount);
            this.swing(this.rightBackArm1, speed, degree * 0.35f, false, 0.0f, -0.1f, limbSwing, limbSwingAmount);
            this.swing(this.rightBackArm2, speed, degree * 0.35f, false, 0.0f, -0.1f, limbSwing, limbSwingAmount);
        }
    }

    public void animateForMimicGround(EntityMimicOctopus.MimicState state, EntityMimicOctopus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float groundProgress) {
        this.mantle.setScale(1.0f, 1.0f, 1.0f);
        limbSwingAmount = limbSwingAmount * groundProgress * 0.2f;
        float degree = 0.8f;
        float speed = 0.8f;
        if (state == EntityMimicOctopus.MimicState.CREEPER) {
            this.progressRotationPrev(this.body, groundProgress, 0.0f, Maths.rad(-180.0), 0.0f, 5.0f);
            this.progressRotationPrev(this.mantle, groundProgress, Maths.rad(-80.0), 0.0f, 0.0f, 5.0f);
            this.progressPositionPrev(this.mantle, groundProgress, 0.0f, -3.0f, -1.0f, 5.0f);
            this.progressPositionPrev(this.body, groundProgress, 0.0f, -13.0f, -2.0f, 5.0f);
            this.progressRotationPrev(this.leftFrontArm1, groundProgress, Maths.rad(-20.0), Maths.rad(55.0), Maths.rad(-20.0), 5.0f);
            this.progressPositionPrev(this.leftFrontArm1, groundProgress, -1.0f, 0.0f, 0.0f, 5.0f);
            this.progressRotationPrev(this.rightFrontArm1, groundProgress, Maths.rad(-20.0), Maths.rad(-55.0), Maths.rad(20.0), 5.0f);
            this.progressPositionPrev(this.rightFrontArm1, groundProgress, 1.0f, 0.0f, 0.0f, 5.0f);
            this.progressRotationPrev(this.leftFrontArm2, groundProgress, Maths.rad(-20.0), Maths.rad(73.0), Maths.rad(-20.0), 5.0f);
            this.progressPositionPrev(this.leftFrontArm2, groundProgress, 1.0f, 0.0f, -1.65f, 5.0f);
            this.progressRotationPrev(this.rightFrontArm2, groundProgress, Maths.rad(-20.0), Maths.rad(-73.0), Maths.rad(20.0), 5.0f);
            this.progressPositionPrev(this.rightFrontArm2, groundProgress, -1.0f, 0.0f, -1.65f, 5.0f);
            this.progressRotationPrev(this.leftBackArm1, groundProgress, Maths.rad(-20.0), Maths.rad(-78.0), Maths.rad(20.0), 5.0f);
            this.progressPositionPrev(this.leftBackArm1, groundProgress, -1.0f, 0.0f, 0.0f, 5.0f);
            this.progressRotationPrev(this.rightBackArm1, groundProgress, Maths.rad(-20.0), Maths.rad(78.0), Maths.rad(-20.0), 5.0f);
            this.progressPositionPrev(this.rightBackArm1, groundProgress, 1.0f, 0.0f, 0.0f, 5.0f);
            this.progressRotationPrev(this.leftBackArm2, groundProgress, Maths.rad(-20.0), Maths.rad(-51.0), Maths.rad(20.0), 5.0f);
            this.progressPositionPrev(this.leftBackArm2, groundProgress, 1.0f, 0.0f, -2.0f, 5.0f);
            this.progressRotationPrev(this.rightBackArm2, groundProgress, Maths.rad(-20.0), Maths.rad(51.0), Maths.rad(-20.0), 5.0f);
            this.progressPositionPrev(this.rightBackArm2, groundProgress, -1.0f, 0.0f, -2.0f, 5.0f);
            this.progressRotationPrev(this.creeperPivots1, groundProgress, Maths.rad(90.0), 0.0f, 0.0f, 5.0f);
            this.progressPositionPrev(this.creeperPivots1, groundProgress, 0.0f, -2.0f, -5.0f, 5.0f);
            this.progressRotationPrev(this.creeperPivots3, groundProgress, Maths.rad(90.0), 0.0f, 0.0f, 5.0f);
            this.progressPositionPrev(this.creeperPivots3, groundProgress, 0.0f, -2.0f, -5.0f, 5.0f);
            this.progressRotationPrev(this.creeperPivots2, groundProgress, Maths.rad(-90.0), 0.0f, 0.0f, 5.0f);
            this.progressPositionPrev(this.creeperPivots2, groundProgress, 0.0f, 3.0f, 2.0f, 5.0f);
            this.progressRotationPrev(this.creeperPivots4, groundProgress, Maths.rad(-90.0), 0.0f, 0.0f, 5.0f);
            this.progressPositionPrev(this.creeperPivots4, groundProgress, 0.0f, 3.0f, 2.0f, 5.0f);
            this.walk(this.creeperPivots1, speed, degree * 0.25f, true, 1.0f, 0.1f, limbSwing, limbSwingAmount);
            this.walk(this.creeperPivots4, speed, degree * 0.25f, true, 1.0f, -0.1f, limbSwing, limbSwingAmount);
            this.walk(this.creeperPivots2, speed, degree * 0.25f, false, 1.0f, 0.1f, limbSwing, limbSwingAmount);
            this.walk(this.creeperPivots3, speed, degree * 0.25f, false, 1.0f, -0.1f, limbSwing, limbSwingAmount);
            this.flap(this.mantle, speed, degree * 0.25f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        } else {
            float idleDegree = 0.02f;
            float idleSpeed = 0.05f;
            this.swing(this.leftFrontArm1, idleSpeed, idleDegree, true, 0.0f, -0.05f, ageInTicks, groundProgress);
            this.swing(this.rightFrontArm1, idleSpeed, idleDegree, false, 0.0f, -0.05f, ageInTicks, groundProgress);
            this.swing(this.leftFrontArm2, idleSpeed, idleDegree, true, -1.0f, -0.02f, ageInTicks, groundProgress);
            this.swing(this.rightFrontArm2, idleSpeed, idleDegree, false, -1.0f, -0.02f, ageInTicks, groundProgress);
            this.swing(this.leftBackArm1, idleSpeed, idleDegree, true, -2.0f, 0.02f, ageInTicks, groundProgress);
            this.swing(this.rightBackArm1, idleSpeed, idleDegree, false, -2.0f, -0.02f, ageInTicks, groundProgress);
            this.swing(this.leftBackArm2, idleSpeed, idleDegree, true, -3.0f, 0.05f, ageInTicks, groundProgress);
            this.swing(this.rightBackArm2, idleSpeed, idleDegree, false, -3.0f, -0.05f, ageInTicks, groundProgress);
            this.flap(this.leftEyeSpike, idleSpeed, idleDegree, false, -5.0f, 0.1f, ageInTicks, groundProgress);
            this.flap(this.rightEyeSpike, idleSpeed, idleDegree, true, -5.0f, 0.1f, ageInTicks, groundProgress);
            this.walk(this.mantle, speed, degree * 0.15f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.mantle, speed, degree * 0.15f, true, 3.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.body, speed, degree * 0.2f, true, -3.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.leftFrontArm1, speed, degree * 0.3f, true, -1.0f, -0.3f, limbSwing, limbSwingAmount);
            this.swing(this.leftFrontArm2, speed, degree * 0.3f, true, -2.0f, -0.1f, limbSwing, limbSwingAmount);
            this.swing(this.leftBackArm1, speed, degree * 0.3f, true, -3.0f, -0.1f, limbSwing, limbSwingAmount);
            this.swing(this.leftBackArm2, speed, degree * 0.3f, true, -4.0f, -0.2f, limbSwing, limbSwingAmount);
            this.swing(this.rightFrontArm1, speed, degree * 0.3f, false, -1.0f, -0.3f, limbSwing, limbSwingAmount);
            this.swing(this.rightFrontArm2, speed, degree * 0.3f, false, -2.0f, -0.1f, limbSwing, limbSwingAmount);
            this.swing(this.rightBackArm1, speed, degree * 0.3f, false, -3.0f, -0.1f, limbSwing, limbSwingAmount);
            this.swing(this.rightBackArm2, speed, degree * 0.3f, false, -4.0f, 0.2f, limbSwing, limbSwingAmount);
            if (entity.hasGuardianLaser()) {
                this.progressRotationPrev(this.body, groundProgress, 0.0f, Maths.rad(180.0), 0.0f, 5.0f);
            }
        }
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.mantle, (Object)this.rightEye, (Object)this.rightEyeSpike, (Object)this.leftEye, (Object)this.leftEyeSpike, (Object)this.body, (Object)this.leftFrontArm1, (Object)this.leftFrontArm2, (Object)this.rightFrontArm1, (Object)this.rightFrontArm2, (Object)this.leftBackArm1, (Object[])new AdvancedModelBox[]{this.leftBackArm2, this.rightBackArm1, this.rightBackArm2, this.creeperPivots1, this.creeperPivots2, this.creeperPivots3, this.creeperPivots4});
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
