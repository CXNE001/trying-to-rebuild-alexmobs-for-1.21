/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.ModelAnimator
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.item.ItemStack
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityKangaroo;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class ModelKangaroo
extends AdvancedEntityModel<EntityKangaroo> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox pouch;
    public final AdvancedModelBox tail1;
    public final AdvancedModelBox tail2;
    public final AdvancedModelBox leg_left;
    public final AdvancedModelBox knee_left;
    public final AdvancedModelBox foot_left;
    public final AdvancedModelBox leg_right;
    public final AdvancedModelBox knee_right;
    public final AdvancedModelBox foot_right;
    public final AdvancedModelBox chest;
    public final AdvancedModelBox arm_left;
    public final AdvancedModelBox arm_right;
    public final AdvancedModelBox neck;
    public final AdvancedModelBox head;
    public final AdvancedModelBox ear_left;
    public final AdvancedModelBox ear_right;
    public final AdvancedModelBox snout;
    public static boolean renderOnlyHead = false;
    private ModelAnimator animator;

    public ModelKangaroo() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -15.0f, 4.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-5.0f, -6.0f, -6.0f, 10.0f, 11.0f, 13.0f, 0.0f, false);
        this.pouch = new AdvancedModelBox((AdvancedEntityModel)this, "pouch");
        this.pouch.setPos(0.0f, 2.7f, -2.2f);
        this.body.addChild((BasicModelPart)this.pouch);
        this.pouch.setTextureOffset(64, 6).addBox(-3.5f, -2.5f, -4.0f, 7.0f, 5.0f, 8.0f, -0.1f, false);
        this.tail1 = new AdvancedModelBox((AdvancedEntityModel)this, "tail1");
        this.tail1.setPos(0.0f, -5.0f, 7.0f);
        this.body.addChild((BasicModelPart)this.tail1);
        this.tail1.setTextureOffset(0, 25).addBox(-2.5f, 0.0f, 0.0f, 5.0f, 6.0f, 15.0f, 0.0f, false);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this, "tail2");
        this.tail2.setPos(0.0f, 4.0f, 15.0f);
        this.tail1.addChild((BasicModelPart)this.tail2);
        this.tail2.setTextureOffset(26, 32).addBox(-1.5f, -3.0f, 0.0f, 3.0f, 4.0f, 15.0f, 0.0f, false);
        this.leg_left = new AdvancedModelBox((AdvancedEntityModel)this, "leg_left");
        this.leg_left.setPos(4.25f, 0.75f, -0.5f);
        this.body.addChild((BasicModelPart)this.leg_left);
        this.leg_left.setTextureOffset(48, 28).addBox(-1.25f, -3.75f, -3.5f, 3.0f, 7.0f, 8.0f, 0.0f, false);
        this.knee_left = new AdvancedModelBox((AdvancedEntityModel)this, "knee_left");
        this.knee_left.setPos(0.25f, 3.25f, -3.5f);
        this.leg_left.addChild((BasicModelPart)this.knee_left);
        this.knee_left.setTextureOffset(0, 0).addBox(-1.0f, 0.0f, 0.0f, 2.0f, 9.0f, 3.0f, 0.0f, false);
        this.foot_left = new AdvancedModelBox((AdvancedEntityModel)this, "foot_left");
        this.foot_left.setPos(0.0f, 9.0f, 1.0f);
        this.knee_left.addChild((BasicModelPart)this.foot_left);
        this.foot_left.setTextureOffset(35, 13).addBox(-1.5f, 0.0f, -10.0f, 3.0f, 2.0f, 12.0f, 0.0f, false);
        this.leg_right = new AdvancedModelBox((AdvancedEntityModel)this, "leg_right");
        this.leg_right.setPos(-4.25f, 0.75f, -0.5f);
        this.body.addChild((BasicModelPart)this.leg_right);
        this.leg_right.setTextureOffset(48, 28).addBox(-1.75f, -3.75f, -3.5f, 3.0f, 7.0f, 8.0f, 0.0f, true);
        this.knee_right = new AdvancedModelBox((AdvancedEntityModel)this, "knee_right");
        this.knee_right.setPos(-0.25f, 3.25f, -3.5f);
        this.leg_right.addChild((BasicModelPart)this.knee_right);
        this.knee_right.setTextureOffset(0, 0).addBox(-1.0f, 0.0f, 0.0f, 2.0f, 9.0f, 3.0f, 0.0f, true);
        this.foot_right = new AdvancedModelBox((AdvancedEntityModel)this, "foot_right");
        this.foot_right.setPos(0.0f, 9.0f, 1.0f);
        this.knee_right.addChild((BasicModelPart)this.foot_right);
        this.foot_right.setTextureOffset(35, 13).addBox(-1.5f, 0.0f, -10.0f, 3.0f, 2.0f, 12.0f, 0.0f, true);
        this.chest = new AdvancedModelBox((AdvancedEntityModel)this, "chest");
        this.chest.setPos(0.0f, -6.0f, -6.0f);
        this.body.addChild((BasicModelPart)this.chest);
        this.chest.setTextureOffset(0, 47).addBox(-4.0f, 0.0f, -9.0f, 8.0f, 9.0f, 9.0f, 0.0f, false);
        this.arm_left = new AdvancedModelBox((AdvancedEntityModel)this, "arm_left");
        this.arm_left.setPos(4.0f, 6.0f, -6.0f);
        this.chest.addChild((BasicModelPart)this.arm_left);
        this.arm_left.setTextureOffset(71, 49).addBox(-1.0f, -1.0f, -2.0f, 2.0f, 12.0f, 3.0f, 0.0f, false);
        this.arm_right = new AdvancedModelBox((AdvancedEntityModel)this, "arm_right");
        this.arm_right.setPos(-4.0f, 6.0f, -6.0f);
        this.chest.addChild((BasicModelPart)this.arm_right);
        this.arm_right.setTextureOffset(71, 49).addBox(-1.0f, -1.0f, -2.0f, 2.0f, 12.0f, 3.0f, 0.0f, true);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setPos(0.0f, 2.0f, -8.0f);
        this.chest.addChild((BasicModelPart)this.neck);
        this.neck.setTextureOffset(35, 52).addBox(-2.0f, -6.0f, -3.0f, 4.0f, 11.0f, 5.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -6.0f, -0.5f);
        this.neck.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(34, 0).addBox(-2.5f, -4.0f, -3.5f, 5.0f, 4.0f, 6.0f, 0.0f, false);
        this.ear_left = new AdvancedModelBox((AdvancedEntityModel)this, "ear_left");
        this.ear_left.setPos(0.4f, -4.0f, 1.5f);
        this.head.addChild((BasicModelPart)this.ear_left);
        this.setRotationAngle(this.ear_left, -0.1745f, -0.3491f, 0.4363f);
        this.ear_left.setTextureOffset(0, 47).addBox(0.0f, -6.0f, -1.0f, 3.0f, 6.0f, 1.0f, 0.0f, false);
        this.ear_right = new AdvancedModelBox((AdvancedEntityModel)this, "ear_right");
        this.ear_right.setPos(-0.4f, -4.0f, 1.5f);
        this.head.addChild((BasicModelPart)this.ear_right);
        this.setRotationAngle(this.ear_right, -0.1745f, 0.3491f, -0.4363f);
        this.ear_right.setTextureOffset(0, 47).addBox(-3.0f, -6.0f, -1.0f, 3.0f, 6.0f, 1.0f, 0.0f, true);
        this.setRotationAngle(this.chest, 0.1745f, 0.0f, 0.0f);
        this.setRotationAngle(this.tail1, -0.1745f, 0.0f, 0.0f);
        this.snout = new AdvancedModelBox((AdvancedEntityModel)this, "snout");
        this.snout.setPos(0.0f, -1.5f, -3.5f);
        this.head.addChild((BasicModelPart)this.snout);
        this.snout.setTextureOffset(0, 25).addBox(-1.5f, -1.5f, -4.0f, 3.0f, 3.0f, 4.0f, 0.0f, false);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityKangaroo.ANIMATION_EAT_GRASS);
        this.animator.startKeyframe(5);
        this.animator.move(this.neck, 0.0f, 3.0f, -2.0f);
        this.animator.rotate(this.neck, Maths.rad(100.0), 0.0f, 0.0f);
        this.animator.rotate(this.chest, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.neck, 0.0f, 3.0f, -2.0f);
        this.animator.rotate(this.neck, Maths.rad(70.0), 0.0f, 0.0f);
        this.animator.rotate(this.chest, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.neck, 0.0f, 3.0f, -2.0f);
        this.animator.rotate(this.neck, Maths.rad(100.0), 0.0f, 0.0f);
        this.animator.rotate(this.chest, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityKangaroo.ANIMATION_KICK);
        this.animator.startKeyframe(5);
        this.animator.move(this.head, 0.0f, 1.0f, -1.0f);
        this.animator.rotate(this.body, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.chest, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(2);
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, -4.0f, -20.0f);
        this.animator.move(this.chest, 0.0f, 2.0f, 2.0f);
        this.animator.move(this.knee_right, 0.0f, -1.0f, 0.0f);
        this.animator.move(this.knee_left, 0.0f, -1.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(-40.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck, Maths.rad(50.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail1, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail2, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.knee_left, Maths.rad(-40.0), 0.0f, 0.0f);
        this.animator.rotate(this.knee_right, Maths.rad(-40.0), 0.0f, 0.0f);
        this.animator.rotate(this.foot_left, Maths.rad(50.0), 0.0f, 0.0f);
        this.animator.rotate(this.foot_right, Maths.rad(50.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, 0.0f, 0.0f, Maths.rad(-15.0));
        this.animator.rotate(this.arm_left, 0.0f, 0.0f, Maths.rad(15.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(3);
        this.animator.setAnimation(EntityKangaroo.ANIMATION_PUNCH_R);
        this.animator.startKeyframe(3);
        this.animator.rotate(this.chest, Maths.rad(-10.0), Maths.rad(-30.0), 0.0f);
        this.animator.rotate(this.neck, 0.0f, Maths.rad(30.0), 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.chest, Maths.rad(10.0), Maths.rad(-10.0), 0.0f);
        this.animator.rotate(this.neck, 0.0f, Maths.rad(-40.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, Maths.rad(30.0), 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-125.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityKangaroo.ANIMATION_PUNCH_L);
        this.animator.startKeyframe(3);
        this.animator.rotate(this.chest, Maths.rad(-10.0), Maths.rad(30.0), 0.0f);
        this.animator.rotate(this.neck, 0.0f, Maths.rad(30.0), 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.chest, Maths.rad(10.0), Maths.rad(10.0), 0.0f);
        this.animator.rotate(this.neck, 0.0f, Maths.rad(-40.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, Maths.rad(30.0), 0.0f);
        this.animator.rotate(this.arm_left, Maths.rad(-125.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
    }

    public void setupAnim(EntityKangaroo entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float partialTick = Minecraft.m_91087_().m_91296_();
        float jumpRotation = Mth.m_14031_((float)(entity.getJumpCompletion(partialTick) * (float)Math.PI));
        float walkSpeed = 1.0f;
        float walkDegree = 0.5f;
        float idleSpeed = 0.05f;
        float idleDegree = 0.1f;
        float sitProgress = entity.prevSitProgress + (entity.sitProgress - entity.prevSitProgress) * partialTick;
        float pouchOpenProgress = entity.prevPouchProgress + (entity.pouchProgress - entity.prevPouchProgress) * partialTick;
        float moveProgress = entity.prevTotalMovingProgress + (entity.totalMovingProgress - entity.prevTotalMovingProgress) * partialTick;
        float stillProgress = Math.max(0.0f, entity.prevStandProgress + (entity.standProgress - entity.prevStandProgress) * partialTick - moveProgress);
        if (entity.getVisualFlag() == 1) {
            this.progressRotationPrev(this.arm_left, 1.0f, Maths.rad(-65.0), 0.0f, Maths.rad(-45.0), 1.0f);
            this.progressRotationPrev(this.arm_right, 1.0f, Maths.rad(-65.0), 0.0f, Maths.rad(45.0), 1.0f);
        }
        this.progressRotationPrev(this.knee_left, sitProgress, Maths.rad(65.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.knee_right, sitProgress, Maths.rad(65.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.foot_left, sitProgress, Maths.rad(-65.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.foot_right, sitProgress, Maths.rad(-65.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_left, sitProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_right, sitProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.foot_left, sitProgress, 0.0f, -1.0f, 0.7f, 5.0f);
        this.progressPositionPrev(this.foot_right, sitProgress, 0.0f, -1.0f, 0.7f, 5.0f);
        this.progressPositionPrev(this.body, sitProgress, 0.0f, 7.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.arm_right, sitProgress, 0.0f, -4.5f, 2.0f, 5.0f);
        this.progressPositionPrev(this.arm_left, sitProgress, 0.0f, -4.5f, 2.0f, 5.0f);
        this.progressRotationPrev(this.body, stillProgress, Maths.rad(-35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_left, stillProgress, Maths.rad(35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_right, stillProgress, Maths.rad(35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.chest, stillProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_left, stillProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_right, stillProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.neck, stillProgress, Maths.rad(35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail1, stillProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail2, stillProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.tail1, stillProgress, 0.0f, 0.0f, -2.0f, 5.0f);
        this.progressPositionPrev(this.pouch, pouchOpenProgress, 0.0f, 3.0f, 0.0f, 5.0f);
        this.walk(this.arm_left, idleSpeed, idleDegree * 1.1f, true, 2.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.arm_right, idleSpeed, idleDegree * 1.1f, true, 2.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.chest, idleSpeed, idleDegree * 0.4f, true, 0.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.neck, idleSpeed, idleDegree * 0.4f, true, 1.0f, 0.1f, ageInTicks, 1.0f);
        this.walk(this.tail1, idleSpeed, idleDegree * 1.1f, false, 2.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.tail2, idleSpeed, idleDegree * 1.1f, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.ear_right, idleSpeed, idleDegree * -1.5f, false, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.flap(this.ear_left, idleSpeed, idleDegree * 1.5f, false, 1.0f, 0.1f, ageInTicks, 1.0f);
        this.body.rotationPointY -= jumpRotation * 4.0f;
        this.knee_left.rotationPointY -= jumpRotation * 2.0f;
        this.knee_right.rotationPointY -= jumpRotation * 2.0f;
        this.leg_right.rotationPointY -= jumpRotation * 2.0f;
        this.leg_left.rotationPointY -= jumpRotation * 2.0f;
        this.leg_right.rotationPointZ += jumpRotation * 2.0f;
        this.leg_left.rotationPointZ += jumpRotation * 2.0f;
        this.head.rotationPointY += jumpRotation * 1.0f;
        this.leg_left.rotateAngleX += jumpRotation * 50.0f * ((float)Math.PI / 180);
        this.leg_right.rotateAngleX += jumpRotation * 50.0f * ((float)Math.PI / 180);
        this.foot_left.rotateAngleX += jumpRotation * 25.0f * ((float)Math.PI / 180);
        this.foot_right.rotateAngleX += jumpRotation * 25.0f * ((float)Math.PI / 180);
        this.knee_left.rotateAngleX += jumpRotation * -25.0f * ((float)Math.PI / 180);
        this.knee_right.rotateAngleX += jumpRotation * -25.0f * ((float)Math.PI / 180);
        this.neck.rotateAngleX += jumpRotation * 15.0f * ((float)Math.PI / 180);
        this.head.rotateAngleX += jumpRotation * -10.0f * ((float)Math.PI / 180);
        this.body.rotateAngleX += jumpRotation * 10.0f * ((float)Math.PI / 180);
        this.arm_left.rotateAngleX += jumpRotation * 20.0f * ((float)Math.PI / 180);
        this.arm_right.rotateAngleX += jumpRotation * 20.0f * ((float)Math.PI / 180);
        this.chest.rotateAngleX += jumpRotation * -5.0f * ((float)Math.PI / 180);
        this.foot_left.rotateAngleX += Math.max(0.0f, jumpRotation - 0.5f) * 25.0f * ((float)Math.PI / 180);
        this.foot_right.rotateAngleX += Math.max(0.0f, jumpRotation - 0.5f) * 25.0f * ((float)Math.PI / 180);
        ItemStack helmet = entity.m_6844_(EquipmentSlot.HEAD);
        ItemStack hand = entity.m_6844_(EquipmentSlot.MAINHAND);
        if (!helmet.m_41619_()) {
            this.ear_left.rotateAngleZ += 1.3089969f;
            this.ear_right.rotateAngleZ += -1.3089969f;
        }
        if (!hand.m_41619_()) {
            if (entity.m_21526_()) {
                this.arm_left.rotateAngleX -= 0.43633232f;
            } else {
                this.arm_right.rotateAngleX -= 0.43633232f;
            }
        }
        this.head.rotateAngleY += netHeadYaw * 0.35f * ((float)Math.PI / 180);
        this.head.rotateAngleX += headPitch * 0.65f * ((float)Math.PI / 180);
        this.neck.rotateAngleY += netHeadYaw * 0.15f * ((float)Math.PI / 180);
        if (entity.m_6162_() && entity.m_20159_() && entity.m_20202_() instanceof EntityKangaroo) {
            this.head.rotateAngleX -= 0.87266463f;
            this.neck.rotateAngleX += 2.0943952f;
            this.progressPositionPrev(this.head, 1.0f, 0.0f, 0.0f, -2.0f, 1.0f);
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
            if (renderOnlyHead) {
                this.neck.setPos(0.0f, 0.0f, 0.0f);
                this.neck.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            } else {
                this.neck.setPos(0.0f, 2.0f, -8.0f);
                this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            }
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

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.arm_left, (Object)this.arm_right, (Object)this.neck, (Object)this.head, (Object)this.ear_left, (Object)this.ear_right, (Object)this.snout, (Object)this.leg_left, (Object)this.leg_right, (Object)this.knee_left, (Object[])new AdvancedModelBox[]{this.knee_right, this.foot_left, this.foot_right, this.pouch, this.tail1, this.tail2, this.chest});
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
