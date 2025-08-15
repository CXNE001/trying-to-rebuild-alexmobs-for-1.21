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
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityTarantulaHawk;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelTarantulaHawk
extends AdvancedEntityModel<EntityTarantulaHawk> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox wing_left;
    private final AdvancedModelBox wing_right;
    private final AdvancedModelBox legback_left;
    private final AdvancedModelBox legback_right;
    private final AdvancedModelBox legmid_left;
    private final AdvancedModelBox legmid_right;
    private final AdvancedModelBox legfront_left;
    private final AdvancedModelBox legfront_right;
    private final AdvancedModelBox head;
    private final AdvancedModelBox fang_left;
    private final AdvancedModelBox fang_right;
    private final AdvancedModelBox antenna_left;
    private final AdvancedModelBox antenna_right;
    private final AdvancedModelBox abdomen;
    private final AdvancedModelBox stinger;

    public ModelTarantulaHawk() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -15.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(33, 54).addBox(-3.0f, -3.0f, -5.0f, 6.0f, 6.0f, 10.0f, 0.0f, false);
        this.wing_left = new AdvancedModelBox((AdvancedEntityModel)this, "wing_left");
        this.wing_left.setPos(1.0f, -3.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.wing_left);
        this.setRotationAngle(this.wing_left, 0.0f, 0.0f, -0.1309f);
        this.wing_left.setTextureOffset(0, 0).addBox(0.0f, 0.0f, -1.0f, 20.0f, 0.0f, 21.0f, 0.0f, false);
        this.wing_right = new AdvancedModelBox((AdvancedEntityModel)this, "wing_right");
        this.wing_right.setPos(-1.0f, -3.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.wing_right);
        this.setRotationAngle(this.wing_right, 0.0f, 0.0f, 0.1309f);
        this.wing_right.setTextureOffset(0, 0).addBox(-20.0f, 0.0f, -1.0f, 20.0f, 0.0f, 21.0f, 0.0f, true);
        this.legback_left = new AdvancedModelBox((AdvancedEntityModel)this, "legback_left");
        this.legback_left.setPos(2.0f, 3.0f, 3.0f);
        this.body.addChild((BasicModelPart)this.legback_left);
        this.setRotationAngle(this.legback_left, 0.0f, -0.3054f, 0.0f);
        this.legback_left.setTextureOffset(0, 41).addBox(0.0f, -3.0f, 0.0f, 21.0f, 15.0f, 0.0f, 0.0f, false);
        this.legback_right = new AdvancedModelBox((AdvancedEntityModel)this, "legback_right");
        this.legback_right.setPos(-2.0f, 3.0f, 3.0f);
        this.body.addChild((BasicModelPart)this.legback_right);
        this.setRotationAngle(this.legback_right, 0.0f, 0.3054f, 0.0f);
        this.legback_right.setTextureOffset(0, 41).addBox(-21.0f, -3.0f, 0.0f, 21.0f, 15.0f, 0.0f, 0.0f, true);
        this.legmid_left = new AdvancedModelBox((AdvancedEntityModel)this, "legmid_left");
        this.legmid_left.setPos(2.0f, 3.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.legmid_left);
        this.legmid_left.setTextureOffset(43, 38).addBox(0.0f, -3.0f, 0.0f, 19.0f, 15.0f, 0.0f, 0.0f, false);
        this.legmid_right = new AdvancedModelBox((AdvancedEntityModel)this, "legmid_right");
        this.legmid_right.setPos(-2.0f, 3.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.legmid_right);
        this.legmid_right.setTextureOffset(43, 38).addBox(-19.0f, -3.0f, 0.0f, 19.0f, 15.0f, 0.0f, 0.0f, true);
        this.legfront_left = new AdvancedModelBox((AdvancedEntityModel)this, "legfront_left");
        this.legfront_left.setPos(2.0f, 3.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.legfront_left);
        this.setRotationAngle(this.legfront_left, 0.0f, 0.2618f, 0.0f);
        this.legfront_left.setTextureOffset(41, 22).addBox(0.0f, -3.0f, 0.0f, 19.0f, 15.0f, 0.0f, 0.0f, false);
        this.legfront_right = new AdvancedModelBox((AdvancedEntityModel)this, "legfront_right");
        this.legfront_right.setPos(-2.0f, 3.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.legfront_right);
        this.setRotationAngle(this.legfront_right, 0.0f, -0.2618f, 0.0f);
        this.legfront_right.setTextureOffset(41, 22).addBox(-19.0f, -3.0f, 0.0f, 19.0f, 15.0f, 0.0f, 0.0f, true);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, 0.0f, -5.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 57).addBox(-4.0f, -2.0f, -4.0f, 8.0f, 7.0f, 4.0f, 0.0f, false);
        this.fang_left = new AdvancedModelBox((AdvancedEntityModel)this, "fang_left");
        this.fang_left.setPos(1.0f, 4.5f, -3.3f);
        this.head.addChild((BasicModelPart)this.fang_left);
        this.fang_left.setTextureOffset(0, 22).addBox(-1.0f, -1.0f, -1.0f, 3.0f, 4.0f, 1.0f, 0.0f, false);
        this.fang_right = new AdvancedModelBox((AdvancedEntityModel)this, "fang_right");
        this.fang_right.setPos(-1.0f, 4.5f, -3.3f);
        this.head.addChild((BasicModelPart)this.fang_right);
        this.fang_right.setTextureOffset(0, 22).addBox(-2.0f, -1.0f, -1.0f, 3.0f, 4.0f, 1.0f, 0.0f, true);
        this.antenna_left = new AdvancedModelBox((AdvancedEntityModel)this, "antenna_left");
        this.antenna_left.setPos(1.0f, -2.0f, -4.0f);
        this.head.addChild((BasicModelPart)this.antenna_left);
        this.setRotationAngle(this.antenna_left, 0.0f, -0.3927f, -0.3491f);
        this.antenna_left.setTextureOffset(0, 0).addBox(0.0f, 0.0f, -8.0f, 0.0f, 11.0f, 8.0f, 0.0f, false);
        this.antenna_right = new AdvancedModelBox((AdvancedEntityModel)this, "antenna_right");
        this.antenna_right.setPos(-1.0f, -2.0f, -4.0f);
        this.head.addChild((BasicModelPart)this.antenna_right);
        this.setRotationAngle(this.antenna_right, 0.0f, 0.3927f, 0.3491f);
        this.antenna_right.setTextureOffset(0, 0).addBox(0.0f, 0.0f, -8.0f, 0.0f, 11.0f, 8.0f, 0.0f, true);
        this.abdomen = new AdvancedModelBox((AdvancedEntityModel)this, "abdomen");
        this.abdomen.setPos(0.0f, -2.0f, 5.0f);
        this.body.addChild((BasicModelPart)this.abdomen);
        this.abdomen.setTextureOffset(0, 22).addBox(-4.0f, 0.0f, 0.0f, 8.0f, 6.0f, 12.0f, 0.0f, false);
        this.stinger = new AdvancedModelBox((AdvancedEntityModel)this, "stinger");
        this.stinger.setPos(0.0f, 3.0f, 12.0f);
        this.abdomen.addChild((BasicModelPart)this.stinger);
        this.stinger.setTextureOffset(9, 0).addBox(0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 5.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.abdomen, (Object)this.head, (Object)this.antenna_left, (Object)this.antenna_right, (Object)this.legback_left, (Object)this.legback_right, (Object)this.legfront_left, (Object)this.legfront_right, (Object)this.legmid_left, (Object)this.legmid_right, (Object[])new AdvancedModelBox[]{this.wing_left, this.wing_right, this.stinger, this.fang_left, this.fang_right});
    }

    public void setupAnim(EntityTarantulaHawk entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.25f;
        float idleDegree = 0.25f;
        float walkSpeed = entity.isDragging() ? 2.0f : 0.8f;
        float walkDegree = 0.4f;
        float flySpeed = 0.25f;
        float flyDegree = 0.6f;
        float digSpeed = 0.85f;
        float digDegree = 0.6f;
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float flyProgress = entity.prevFlyProgress + (entity.flyProgress - entity.prevFlyProgress) * partialTick;
        float dragProgress = entity.prevDragProgress + (entity.dragProgress - entity.prevDragProgress) * partialTick;
        float sitProgress = entity.prevSitProgress + (entity.sitProgress - entity.prevSitProgress) * partialTick;
        float digProgress = entity.prevDigProgress + (entity.digProgress - entity.prevDigProgress) * partialTick;
        float stingProgress = entity.prevAttackProgress + (entity.attackProgress - entity.prevAttackProgress) * partialTick;
        float walkProgress = 5.0f - flyProgress;
        float stingFlyProgress = stingProgress * flyProgress * 0.2f;
        float stingGroundProgress = stingProgress * walkProgress * 0.2f;
        float flyAngle = entity.prevFlyAngle + (entity.getFlyAngle() - entity.prevFlyAngle) * partialTick;
        this.flap(this.antenna_left, idleSpeed, idleDegree * 1.0f, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.antenna_right, idleSpeed, idleDegree * 1.0f, true, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.antenna_right, idleSpeed, idleDegree * 2.0f, true, -1.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.antenna_left, idleSpeed, idleDegree * 2.0f, false, -1.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.fang_right, idleSpeed, idleDegree * -0.5f, false, -1.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.fang_left, idleSpeed, idleDegree * 0.5f, false, -1.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.abdomen, idleSpeed, idleDegree * 0.4f, true, 0.0f, 0.1f, ageInTicks, 1.0f);
        this.progressPositionPrev(this.body, flyProgress, 0.0f, -3.0f, -2.0f, 5.0f);
        this.progressPositionPrev(this.legfront_right, flyProgress, 0.0f, -1.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.legfront_left, flyProgress, 0.0f, -1.0f, 2.0f, 5.0f);
        this.progressRotationPrev(this.legfront_left, flyProgress, Maths.rad(35.0), Maths.rad(-20.0), Maths.rad(30.0), 5.0f);
        this.progressRotationPrev(this.legfront_right, flyProgress, Maths.rad(35.0), Maths.rad(20.0), Maths.rad(-30.0), 5.0f);
        this.progressRotationPrev(this.legmid_left, flyProgress, Maths.rad(35.0), Maths.rad(-35.0), Maths.rad(20.0), 5.0f);
        this.progressRotationPrev(this.legmid_right, flyProgress, Maths.rad(35.0), Maths.rad(35.0), Maths.rad(-20.0), 5.0f);
        this.progressRotationPrev(this.legback_left, flyProgress, Maths.rad(35.0), Maths.rad(-35.0), Maths.rad(20.0), 5.0f);
        this.progressRotationPrev(this.legback_right, flyProgress, Maths.rad(35.0), Maths.rad(35.0), Maths.rad(-20.0), 5.0f);
        this.progressRotationPrev(this.wing_left, flyProgress, 0.0f, Maths.rad(35.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.wing_right, flyProgress, 0.0f, Maths.rad(-35.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.head, flyProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.wing_left, walkProgress, Maths.rad(20.0), Maths.rad(-20.0), Maths.rad(20.0), 5.0f);
        this.progressRotationPrev(this.wing_right, walkProgress, Maths.rad(20.0), Maths.rad(20.0), Maths.rad(-20.0), 5.0f);
        this.progressRotationPrev(this.wing_right, walkProgress * limbSwingAmount, Maths.rad(20.0), Maths.rad(15.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.wing_left, walkProgress * limbSwingAmount, Maths.rad(20.0), Maths.rad(-15.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.head, dragProgress, Maths.rad(-70.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.fang_right, dragProgress, 0.0f, 0.0f, Maths.rad(20.0), 5.0f);
        this.progressRotationPrev(this.fang_left, dragProgress, 0.0f, 0.0f, Maths.rad(-20.0), 5.0f);
        this.progressPositionPrev(this.head, dragProgress, 0.0f, 3.0f, -1.0f, 5.0f);
        this.progressPositionPrev(this.fang_right, dragProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.fang_left, dragProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, sitProgress, 0.0f, 7.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legfront_right, sitProgress, 0.0f, Maths.rad(-25.0), Maths.rad(27.0), 5.0f);
        this.progressRotationPrev(this.legfront_left, sitProgress, 0.0f, Maths.rad(25.0), Maths.rad(-27.0), 5.0f);
        this.progressRotationPrev(this.legmid_right, sitProgress, 0.0f, 0.0f, Maths.rad(21.0), 5.0f);
        this.progressRotationPrev(this.legmid_left, sitProgress, 0.0f, 0.0f, Maths.rad(-21.0), 5.0f);
        this.progressRotationPrev(this.legback_right, sitProgress, 0.0f, Maths.rad(25.0), Maths.rad(27.0), 5.0f);
        this.progressRotationPrev(this.legback_left, sitProgress, 0.0f, Maths.rad(-25.0), Maths.rad(-27.0), 5.0f);
        this.progressRotationPrev(this.head, sitProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.abdomen, stingGroundProgress, Maths.rad(-70.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.stinger, stingGroundProgress, Maths.rad(-30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, stingGroundProgress, Maths.rad(-40.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, stingGroundProgress, 0.0f, -2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.abdomen, stingGroundProgress, 0.0f, 0.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.stinger, stingGroundProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legfront_right, stingGroundProgress, Maths.rad(40.0), 0.0f, Maths.rad(-40.0), 5.0f);
        this.progressRotationPrev(this.legfront_left, stingGroundProgress, Maths.rad(40.0), 0.0f, Maths.rad(40.0), 5.0f);
        this.progressRotationPrev(this.legmid_right, stingGroundProgress, Maths.rad(40.0), 0.0f, Maths.rad(-10.0), 5.0f);
        this.progressRotationPrev(this.legmid_left, stingGroundProgress, Maths.rad(40.0), 0.0f, Maths.rad(10.0), 5.0f);
        this.progressRotationPrev(this.legback_left, stingGroundProgress, Maths.rad(40.0), 0.0f, Maths.rad(-10.0), 5.0f);
        this.progressRotationPrev(this.legback_right, stingGroundProgress, Maths.rad(40.0), 0.0f, Maths.rad(10.0), 5.0f);
        this.progressRotationPrev(this.body, stingFlyProgress, Maths.rad(-70.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.abdomen, stingFlyProgress, Maths.rad(-50.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.stinger, stingFlyProgress, Maths.rad(-30.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, stingFlyProgress, 0.0f, -5.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.abdomen, stingFlyProgress, 0.0f, 0.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.stinger, 5.0f - stingProgress, 0.0f, 0.0f, -3.0f, 5.0f);
        this.stinger.setScale(1.0f, 1.0f, 1.0f + stingProgress * 0.15f);
        this.progressRotationPrev(this.body, digProgress, Maths.rad(40.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, digProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legfront_right, digProgress, Maths.rad(-50.0), 0.0f, Maths.rad(20.0), 5.0f);
        this.progressRotationPrev(this.legfront_left, digProgress, Maths.rad(-50.0), 0.0f, Maths.rad(-20.0), 5.0f);
        this.progressRotationPrev(this.legmid_right, digProgress, Maths.rad(-10.0), 0.0f, Maths.rad(-10.0), 5.0f);
        this.progressRotationPrev(this.legmid_left, digProgress, Maths.rad(-10.0), 0.0f, Maths.rad(10.0), 5.0f);
        this.progressRotationPrev(this.legback_left, digProgress, Maths.rad(-30.0), 0.0f, Maths.rad(30.0), 5.0f);
        this.progressRotationPrev(this.legback_right, digProgress, Maths.rad(-30.0), 0.0f, Maths.rad(-30.0), 5.0f);
        this.swing(this.legfront_left, digSpeed, digDegree * 1.0f, false, 1.0f, -0.5f, ageInTicks, digProgress * 0.2f);
        this.swing(this.legfront_right, digSpeed, digDegree * 1.0f, false, 1.0f, 0.5f, ageInTicks, digProgress * 0.2f);
        this.swing(this.head, digSpeed, digDegree * 1.0f, false, 0.0f, 0.0f, ageInTicks, digProgress * 0.2f);
        if (flyProgress > 0.0f) {
            this.bob(this.body, flySpeed, flyDegree * 5.0f, false, ageInTicks, 1.0f);
            this.flap(this.legfront_left, flySpeed, flyDegree * 0.5f, true, 1.0f, 0.1f, ageInTicks, 1.0f);
            this.flap(this.legfront_right, flySpeed, flyDegree * 0.5f, false, 1.0f, 0.1f, ageInTicks, 1.0f);
            this.flap(this.legmid_left, flySpeed, flyDegree * 0.5f, true, 2.0f, 0.1f, ageInTicks, 1.0f);
            this.flap(this.legmid_right, flySpeed, flyDegree * 0.5f, false, 2.0f, 0.1f, ageInTicks, 1.0f);
            this.flap(this.legback_left, flySpeed, flyDegree * 0.5f, true, 2.0f, 0.1f, ageInTicks, 1.0f);
            this.flap(this.legback_right, flySpeed, flyDegree * 0.5f, false, 2.0f, 0.1f, ageInTicks, 1.0f);
            this.walk(this.abdomen, flySpeed, flyDegree * 0.35f, false, 0.0f, -0.1f, ageInTicks, 1.0f);
            this.walk(this.head, flySpeed, flyDegree * 0.15f, true, 0.0f, -0.1f, ageInTicks, 1.0f);
            this.flap(this.wing_left, flySpeed * 7.0f, flyDegree, true, 0.0f, 0.1f, ageInTicks, 1.0f);
            this.flap(this.wing_right, flySpeed * 7.0f, flyDegree, false, 0.0f, 0.1f, ageInTicks, 1.0f);
        } else {
            this.swing(this.legback_right, walkSpeed, walkDegree * 1.2f, false, 0.0f, 0.2f, limbSwing, limbSwingAmount);
            this.flap(this.legback_right, walkSpeed, walkDegree * 0.8f, false, -1.5f, 0.4f, limbSwing, limbSwingAmount);
            this.swing(this.legfront_right, walkSpeed, walkDegree, false, 0.0f, -0.3f, limbSwing, limbSwingAmount);
            this.flap(this.legfront_right, walkSpeed, walkDegree * 0.8f, false, -1.5f, 0.4f, limbSwing, limbSwingAmount);
            this.swing(this.legmid_left, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.legmid_left, walkSpeed, walkDegree * 0.8f, false, -1.5f, -0.4f, limbSwing, limbSwingAmount);
            this.bob(this.body, walkSpeed * 2.0f, walkDegree * -3.0f, false, limbSwing, limbSwingAmount);
            float offsetleft = 2.0f;
            this.swing(this.legback_left, walkSpeed, -walkDegree * 1.2f, false, offsetleft, -0.2f, limbSwing, limbSwingAmount);
            this.flap(this.legback_left, walkSpeed, walkDegree * 0.8f, false, offsetleft - 1.5f, -0.4f, limbSwing, limbSwingAmount);
            this.swing(this.legfront_left, walkSpeed, -walkDegree, false, offsetleft, 0.3f, limbSwing, limbSwingAmount);
            this.flap(this.legfront_left, walkSpeed, walkDegree * 0.8f, false, offsetleft + 1.5f, -0.4f, limbSwing, limbSwingAmount);
            this.swing(this.legmid_right, walkSpeed, -walkDegree, false, offsetleft, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.legmid_right, walkSpeed, walkDegree * 0.8f, false, offsetleft - 1.5f, 0.4f, limbSwing, limbSwingAmount);
            this.swing(this.abdomen, walkSpeed, walkDegree * 0.4f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.head, walkSpeed, walkDegree * 0.4f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
        }
        float f = Maths.rad(flyAngle);
        this.body.rotateAngleZ += f;
        if (dragProgress == 0.0f) {
            this.faceTarget(netHeadYaw, headPitch, 1.2f, new AdvancedModelBox[]{this.head});
        }
    }

    public void m_7695_(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root.render(matrixStack, buffer, packedLight, packedOverlay);
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
