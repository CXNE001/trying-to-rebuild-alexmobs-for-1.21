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

import com.github.alexthe666.alexsmobs.entity.EntityRockyRoller;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelRockyRoller
extends AdvancedEntityModel<EntityRockyRoller> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox HSpikes_r1;
    private final AdvancedModelBox HSpikes_r2;
    private final AdvancedModelBox VSpikes_r1;
    private final AdvancedModelBox VSpikes_r2;
    private final AdvancedModelBox VSpikes_r3;
    private final AdvancedModelBox VSpikes_r4;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox head;
    private final AdvancedModelBox left_arm;
    private final AdvancedModelBox right_arm;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox right_leg;

    public ModelRockyRoller() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -16.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 29).addBox(-9.0f, -8.0f, -10.0f, 18.0f, 16.0f, 20.0f, 0.0f, false);
        this.body.setTextureOffset(64, 85).addBox(0.0f, 1.0f, -13.0f, 15.0f, 0.0f, 28.0f, 0.0f, false);
        this.body.setTextureOffset(64, 85).addBox(-15.0f, 1.0f, -13.0f, 15.0f, 0.0f, 28.0f, 0.0f, true);
        this.HSpikes_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "HSpikes_r1");
        this.HSpikes_r1.setRotationPoint(-0.5f, -4.0f, 1.0f);
        this.body.addChild((BasicModelPart)this.HSpikes_r1);
        this.setRotationAngle(this.HSpikes_r1, 0.0f, 0.0f, 0.0873f);
        this.HSpikes_r1.setTextureOffset(0, 0).addBox(-14.5f, 0.0f, -14.0f, 15.0f, 0.0f, 28.0f, 0.0f, true);
        this.HSpikes_r2 = new AdvancedModelBox((AdvancedEntityModel)this, "HSpikes_r2");
        this.HSpikes_r2.setRotationPoint(0.5f, -4.0f, 1.0f);
        this.body.addChild((BasicModelPart)this.HSpikes_r2);
        this.setRotationAngle(this.HSpikes_r2, 0.0f, 0.0f, -0.0873f);
        this.HSpikes_r2.setTextureOffset(0, 0).addBox(-0.5f, 0.0f, -14.0f, 15.0f, 0.0f, 28.0f, 0.0f, false);
        this.VSpikes_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "VSpikes_r1");
        this.VSpikes_r1.setRotationPoint(-6.0f, 4.0f, 1.0f);
        this.body.addChild((BasicModelPart)this.VSpikes_r1);
        this.setRotationAngle(this.VSpikes_r1, 0.0f, 0.0f, -0.1745f);
        this.VSpikes_r1.setTextureOffset(1, 69).addBox(0.0f, -18.0f, -14.0f, 0.0f, 18.0f, 28.0f, 0.0f, false);
        this.VSpikes_r2 = new AdvancedModelBox((AdvancedEntityModel)this, "VSpikes_r2");
        this.VSpikes_r2.setRotationPoint(6.0f, 4.0f, 1.0f);
        this.body.addChild((BasicModelPart)this.VSpikes_r2);
        this.setRotationAngle(this.VSpikes_r2, 0.0f, 0.0f, 0.1745f);
        this.VSpikes_r2.setTextureOffset(1, 69).addBox(0.0f, -18.0f, -14.0f, 0.0f, 18.0f, 28.0f, 0.0f, true);
        this.VSpikes_r3 = new AdvancedModelBox((AdvancedEntityModel)this, "VSpikes_r3");
        this.VSpikes_r3.setRotationPoint(-2.0f, 2.0f, 1.0f);
        this.body.addChild((BasicModelPart)this.VSpikes_r3);
        this.setRotationAngle(this.VSpikes_r3, 0.0f, 0.0f, -0.0436f);
        this.VSpikes_r3.setTextureOffset(49, 38).addBox(0.0f, -16.0f, -14.0f, 0.0f, 18.0f, 28.0f, 0.0f, true);
        this.VSpikes_r4 = new AdvancedModelBox((AdvancedEntityModel)this, "VSpikes_r4");
        this.VSpikes_r4.setRotationPoint(2.0f, 2.0f, 1.0f);
        this.body.addChild((BasicModelPart)this.VSpikes_r4);
        this.setRotationAngle(this.VSpikes_r4, 0.0f, 0.0f, 0.0436f);
        this.VSpikes_r4.setTextureOffset(49, 38).addBox(0.0f, -16.0f, -14.0f, 0.0f, 18.0f, 28.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, 7.0f, 10.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.setRotationAngle(this.tail, -0.6109f, 0.0f, 0.0f);
        this.tail.setTextureOffset(59, 0).addBox(-2.0f, -2.0f, 0.0f, 4.0f, 4.0f, 12.0f, 0.0f, false);
        this.tail.setTextureOffset(19, 8).addBox(0.0f, -4.0f, 8.0f, 0.0f, 2.0f, 4.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, 6.0f, -10.6f);
        this.body.addChild((BasicModelPart)this.head);
        this.setRotationAngle(this.head, 0.2618f, 0.0f, 0.0f);
        this.head.setTextureOffset(0, 0).addBox(-3.0f, -3.0f, -4.0f, 6.0f, 6.0f, 5.0f, 0.0f, false);
        this.head.setTextureOffset(21, 15).addBox(0.0f, -5.0f, -4.0f, 0.0f, 2.0f, 3.0f, 0.0f, false);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setRotationPoint(4.0f, 8.0f, -7.0f);
        this.body.addChild((BasicModelPart)this.left_arm);
        this.left_arm.setTextureOffset(0, 29).addBox(-1.0f, 0.0f, -1.0f, 3.0f, 5.0f, 3.0f, 0.0f, false);
        this.left_arm.setTextureOffset(18, 0).addBox(0.0f, 3.0f, 2.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setRotationPoint(-4.0f, 8.0f, -7.0f);
        this.body.addChild((BasicModelPart)this.right_arm);
        this.right_arm.setTextureOffset(0, 29).addBox(-2.0f, 0.0f, -1.0f, 3.0f, 5.0f, 3.0f, 0.0f, true);
        this.right_arm.setTextureOffset(18, 0).addBox(-2.0f, 3.0f, 2.0f, 2.0f, 2.0f, 2.0f, 0.0f, true);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setRotationPoint(6.0f, 9.0f, 6.0f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(0, 12).addBox(-3.0f, -1.0f, -3.0f, 5.0f, 8.0f, 5.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setRotationPoint(-6.0f, 9.0f, 6.0f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(0, 12).addBox(-2.0f, -1.0f, -3.0f, 5.0f, 8.0f, 5.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.HSpikes_r1, (Object)this.HSpikes_r2, (Object)this.head, (Object)this.VSpikes_r1, (Object)this.VSpikes_r2, (Object)this.VSpikes_r3, (Object)this.VSpikes_r4, (Object)this.tail, (Object)this.left_arm, (Object)this.right_arm, (Object[])new AdvancedModelBox[]{this.left_leg, this.right_leg});
    }

    public void setupAnim(EntityRockyRoller entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float rollProgress = entity.prevRollProgress + (entity.rollProgress - entity.prevRollProgress) * partialTick;
        float walkProgress = 5.0f - rollProgress;
        float walkSpeed = 1.2f;
        float walkDegree = 0.8f;
        float idleSpeed = 0.15f;
        float idleDegree = 0.3f;
        float rollDegree = 0.2f;
        float timeRolling = (float)entity.rollCounter + partialTick;
        this.progressPositionPrev(this.body, rollProgress, 0.0f, 6.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, walkProgress * limbSwingAmount, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_arm, walkProgress * limbSwingAmount, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, walkProgress * limbSwingAmount, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.tail, walkProgress * limbSwingAmount, 0.0f, -1.0f, -1.0f, 5.0f);
        if (entity.isRolling()) {
            entity.clientRoll = this.body.rotateAngleX = timeRolling * 0.2f * rollProgress * rollDegree;
            this.bob(this.body, rollDegree, 10.0f, true, timeRolling, 0.2f * rollProgress);
        } else {
            float rollDeg = (float)Mth.m_14175_((double)Math.toDegrees(entity.clientRoll));
            this.body.rotateAngleX = rollProgress * 0.2f * Maths.rad(rollDeg);
        }
        this.swing(this.tail, idleSpeed, idleDegree, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.bob(this.head, idleSpeed * 0.5f, idleDegree * 1.5f, false, ageInTicks, 1.0f);
        this.bob(this.left_arm, idleSpeed * 0.25f, idleDegree * 2.0f, true, ageInTicks, 1.0f);
        this.bob(this.right_arm, idleSpeed * 0.25f, idleDegree * 2.0f, true, ageInTicks, 1.0f);
        this.walk(this.right_arm, idleSpeed, idleDegree, true, 1.0f, 0.1f, ageInTicks, 1.0f);
        this.walk(this.left_arm, idleSpeed, idleDegree, false, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.right_leg, walkSpeed, walkDegree * 1.25f, false, 0.0f, 0.2f, limbSwing, limbSwingAmount);
        this.walk(this.left_leg, walkSpeed, walkDegree * 1.25f, true, 0.0f, 0.2f, limbSwing, limbSwingAmount);
        this.bob(this.right_leg, walkSpeed, walkDegree * 3.0f, false, limbSwing, limbSwingAmount);
        this.bob(this.left_leg, -walkSpeed, walkDegree * 3.0f, false, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed * 0.8f, walkDegree, true, limbSwing, limbSwingAmount * walkProgress * 0.2f);
        this.walk(this.tail, walkSpeed, walkDegree * 0.35f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
        this.head.rotateAngleY += netHeadYaw / 57.295776f;
        this.head.rotateAngleX += headPitch / 57.295776f * 0.5f;
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
