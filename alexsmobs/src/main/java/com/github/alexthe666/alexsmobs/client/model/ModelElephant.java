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
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityElephant;
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

public class ModelElephant
extends AdvancedEntityModel<EntityElephant> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox cabin;
    public final AdvancedModelBox left_chest;
    public final AdvancedModelBox right_chest;
    public final AdvancedModelBox tail;
    public final AdvancedModelBox left_arm;
    public final AdvancedModelBox right_arm;
    public final AdvancedModelBox left_leg;
    public final AdvancedModelBox right_leg;
    public final AdvancedModelBox head;
    public final AdvancedModelBox left_tusk;
    public final AdvancedModelBox right_tusk;
    public final AdvancedModelBox left_megatusk;
    public final AdvancedModelBox right_megatusk;
    public final AdvancedModelBox left_ear;
    public final AdvancedModelBox right_ear;
    public final AdvancedModelBox trunk1;
    public final AdvancedModelBox trunk2;
    private final ModelAnimator animator;

    public ModelElephant(float f) {
        this.texWidth = 256;
        this.texHeight = 256;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -41.0f, 1.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-14.0f, -15.0f, -24.0f, 28.0f, 29.0f, 47.0f, f, false);
        this.cabin = new AdvancedModelBox((AdvancedEntityModel)this, "cabin");
        this.cabin.setRotationPoint(0.0f, -14.0f, -11.5f);
        this.body.addChild((BasicModelPart)this.cabin);
        this.cabin.setTextureOffset(0, 165).addBox(-13.0f, -29.0f, -11.5f, 26.0f, 28.0f, 23.0f, f, false);
        this.cabin.setTextureOffset(109, 176).addBox(-16.0f, -29.1f, -13.5f, 32.0f, 7.0f, 27.0f, f, false);
        this.left_chest = new AdvancedModelBox((AdvancedEntityModel)this, "left_chest");
        this.left_chest.setRotationPoint(14.0f, -8.0f, 10.0f);
        this.body.addChild((BasicModelPart)this.left_chest);
        this.left_chest.setTextureOffset(57, 125).addBox(0.0f, -2.0f, -10.0f, 9.0f, 13.0f, 19.0f, f, false);
        this.right_chest = new AdvancedModelBox((AdvancedEntityModel)this, "right_chest");
        this.right_chest.setRotationPoint(-14.0f, -8.0f, 10.0f);
        this.body.addChild((BasicModelPart)this.right_chest);
        this.right_chest.setTextureOffset(57, 125).addBox(-9.0f, -2.0f, -10.0f, 9.0f, 13.0f, 19.0f, f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, -5.0f, 23.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.setRotationAngle(this.tail, 0.1745f, 0.0f, 0.0f);
        this.tail.setTextureOffset(42, 114).addBox(-4.0f, 0.0f, 0.0f, 8.0f, 20.0f, 0.0f, f, false);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setRotationPoint(8.1f, 9.5f, -18.2f);
        this.body.addChild((BasicModelPart)this.left_arm);
        this.left_arm.setTextureOffset(0, 0).addBox(-5.5f, 4.5f, -5.5f, 11.0f, 27.0f, 11.0f, f, false);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setRotationPoint(-8.1f, 9.5f, -18.2f);
        this.body.addChild((BasicModelPart)this.right_arm);
        this.right_arm.setTextureOffset(0, 0).addBox(-5.5f, 4.5f, -5.5f, 11.0f, 27.0f, 11.0f, f, true);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setRotationPoint(8.2f, 11.5f, 17.2f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(71, 77).addBox(-5.5f, 2.5f, -5.5f, 11.0f, 27.0f, 11.0f, f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setRotationPoint(-8.2f, 11.5f, 17.2f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(71, 77).addBox(-5.5f, 2.5f, -5.5f, 11.0f, 27.0f, 11.0f, f, true);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -2.0f, -25.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 77).addBox(-10.0f, -12.0f, -14.0f, 20.0f, 21.0f, 15.0f, f, false);
        this.left_tusk = new AdvancedModelBox((AdvancedEntityModel)this, "left_tusk");
        this.left_tusk.setRotationPoint(6.5f, 8.0f, -11.5f);
        this.head.addChild((BasicModelPart)this.left_tusk);
        this.setRotationAngle(this.left_tusk, -0.4887f, -0.1571f, -0.2967f);
        this.left_tusk.setTextureOffset(104, 25).addBox(-1.5f, 0.0f, -1.5f, 3.0f, 12.0f, 3.0f, f, false);
        this.right_tusk = new AdvancedModelBox((AdvancedEntityModel)this, "right_tusk");
        this.right_tusk.setRotationPoint(-6.5f, 8.0f, -11.5f);
        this.head.addChild((BasicModelPart)this.right_tusk);
        this.setRotationAngle(this.right_tusk, -0.4887f, 0.1571f, 0.2967f);
        this.right_tusk.setTextureOffset(104, 25).addBox(-1.5f, 0.0f, -1.5f, 3.0f, 12.0f, 3.0f, f, true);
        this.left_megatusk = new AdvancedModelBox((AdvancedEntityModel)this, "left_megatusk");
        this.left_megatusk.setRotationPoint(6.5f, 8.0f, -11.5f);
        this.head.addChild((BasicModelPart)this.left_megatusk);
        this.setRotationAngle(this.left_megatusk, -0.2618f, 0.0524f, -0.2269f);
        this.left_megatusk.setTextureOffset(0, 114).addBox(-1.5f, 0.0f, -2.5f, 4.0f, 28.0f, 4.0f, f, false);
        this.left_megatusk.setTextureOffset(104, 25).addBox(-1.5f, 24.0f, -19.5f, 4.0f, 4.0f, 17.0f, f, false);
        this.right_megatusk = new AdvancedModelBox((AdvancedEntityModel)this, "right_megatusk");
        this.right_megatusk.setRotationPoint(-6.5f, 8.0f, -11.5f);
        this.head.addChild((BasicModelPart)this.right_megatusk);
        this.setRotationAngle(this.right_megatusk, -0.2618f, -0.0524f, 0.2269f);
        this.right_megatusk.setTextureOffset(0, 114).addBox(-2.5f, 0.0f, -2.5f, 4.0f, 28.0f, 4.0f, f, true);
        this.right_megatusk.setTextureOffset(104, 25).addBox(-2.5f, 24.0f, -19.5f, 4.0f, 4.0f, 17.0f, f, true);
        this.left_ear = new AdvancedModelBox((AdvancedEntityModel)this, "left_ear");
        this.left_ear.setRotationPoint(9.0f, -3.0f, -3.0f);
        this.head.addChild((BasicModelPart)this.left_ear);
        this.setRotationAngle(this.left_ear, 0.0f, -0.6981f, 0.0f);
        this.left_ear.setTextureOffset(104, 0).addBox(0.0f, -4.0f, -1.0f, 20.0f, 22.0f, 2.0f, f, false);
        this.right_ear = new AdvancedModelBox((AdvancedEntityModel)this, "right_ear");
        this.right_ear.setRotationPoint(-9.0f, -3.0f, -3.0f);
        this.head.addChild((BasicModelPart)this.right_ear);
        this.setRotationAngle(this.right_ear, 0.0f, 0.6981f, 0.0f);
        this.right_ear.setTextureOffset(104, 0).addBox(-20.0f, -4.0f, -1.0f, 20.0f, 22.0f, 2.0f, f, true);
        this.trunk1 = new AdvancedModelBox((AdvancedEntityModel)this, "trunk1");
        this.trunk1.setRotationPoint(0.0f, 3.0f, -16.0f);
        this.head.addChild((BasicModelPart)this.trunk1);
        this.trunk1.setTextureOffset(108, 108).addBox(-4.0f, -4.0f, -5.0f, 8.0f, 24.0f, 8.0f, f, false);
        this.trunk2 = new AdvancedModelBox((AdvancedEntityModel)this, "trunk2");
        this.trunk2.setRotationPoint(0.0f, 20.0f, 0.0f);
        this.trunk1.addChild((BasicModelPart)this.trunk2);
        this.trunk2.setTextureOffset(17, 114).addBox(-3.0f, 0.0f, -3.0f, 6.0f, 18.0f, 6.0f, f, false);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityElephant.ANIMATION_TRUMPET_0);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_ear, 0.0f, Maths.rad(25.0), 0.0f);
        this.animator.rotate(this.right_ear, 0.0f, Maths.rad(-25.0), 0.0f);
        this.animator.rotate(this.trunk1, Maths.rad(-65.0), 0.0f, 0.0f);
        this.animator.rotate(this.trunk2, Maths.rad(-35.0), 0.0f, 0.0f);
        this.animator.move(this.trunk2, 0.0f, -2.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(-35.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_ear, 0.0f, Maths.rad(45.0), 0.0f);
        this.animator.rotate(this.right_ear, 0.0f, Maths.rad(-45.0), 0.0f);
        this.animator.rotate(this.trunk1, Maths.rad(-75.0), 0.0f, 0.0f);
        this.animator.rotate(this.trunk2, Maths.rad(-55.0), 0.0f, 0.0f);
        this.animator.move(this.trunk2, 0.0f, -2.0f, 1.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(3);
        this.animator.resetKeyframe(7);
        this.animator.setAnimation(EntityElephant.ANIMATION_TRUMPET_1);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(-25.0), 0.0f, Maths.rad(-25.0));
        this.animator.rotate(this.left_ear, 0.0f, Maths.rad(25.0), 0.0f);
        this.animator.rotate(this.right_ear, 0.0f, Maths.rad(-25.0), 0.0f);
        this.animator.rotate(this.trunk1, Maths.rad(-75.0), Maths.rad(25.0), 0.0f);
        this.animator.rotate(this.trunk2, Maths.rad(-35.0), Maths.rad(10.0), 0.0f);
        this.animator.move(this.trunk2, 0.0f, -2.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(-25.0), 0.0f, Maths.rad(25.0));
        this.animator.rotate(this.left_ear, 0.0f, Maths.rad(25.0), 0.0f);
        this.animator.rotate(this.right_ear, 0.0f, Maths.rad(-25.0), 0.0f);
        this.animator.rotate(this.trunk1, Maths.rad(-75.0), Maths.rad(-25.0), 0.0f);
        this.animator.rotate(this.trunk2, Maths.rad(-35.0), Maths.rad(-10.0), 0.0f);
        this.animator.move(this.trunk2, 0.0f, -2.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(-25.0), 0.0f, Maths.rad(-25.0));
        this.animator.rotate(this.left_ear, 0.0f, Maths.rad(25.0), 0.0f);
        this.animator.rotate(this.right_ear, 0.0f, Maths.rad(-25.0), 0.0f);
        this.animator.rotate(this.trunk1, Maths.rad(-75.0), Maths.rad(25.0), 0.0f);
        this.animator.rotate(this.trunk2, Maths.rad(-35.0), Maths.rad(10.0), 0.0f);
        this.animator.move(this.trunk2, 0.0f, -2.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(-25.0), 0.0f, Maths.rad(25.0));
        this.animator.rotate(this.left_ear, 0.0f, Maths.rad(25.0), 0.0f);
        this.animator.rotate(this.right_ear, 0.0f, Maths.rad(-25.0), 0.0f);
        this.animator.rotate(this.trunk1, Maths.rad(-75.0), Maths.rad(-25.0), 0.0f);
        this.animator.rotate(this.trunk2, Maths.rad(-35.0), Maths.rad(-10.0), 0.0f);
        this.animator.move(this.trunk2, 0.0f, -2.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(10);
        this.animator.setAnimation(EntityElephant.ANIMATION_CHARGE_PREPARE);
        this.animator.startKeyframe(10);
        this.animator.rotate(this.body, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_ear, 0.0f, Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.left_ear, 0.0f, Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(-15.0), 0.0f, Maths.rad(-15.0));
        this.animator.rotate(this.right_arm, Maths.rad(-15.0), 0.0f, Maths.rad(15.0));
        this.animator.rotate(this.left_leg, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_leg, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.rotate(this.trunk1, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.rotate(this.trunk2, Maths.rad(45.0), 0.0f, 0.0f);
        this.animator.move(this.right_arm, 0.0f, -9.0f, 0.0f);
        this.animator.move(this.left_arm, 0.0f, -9.0f, 0.0f);
        this.animator.move(this.left_leg, 0.0f, -1.0f, 0.0f);
        this.animator.move(this.right_leg, 0.0f, -1.0f, 0.0f);
        this.animator.move(this.head, 0.0f, 2.0f, 0.0f);
        this.animator.move(this.trunk2, 0.0f, -2.0f, 0.0f);
        this.animator.move(this.body, 0.0f, 6.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(10);
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityElephant.ANIMATION_STOMP);
        this.animator.startKeyframe(10);
        this.animator.rotate(this.body, Maths.rad(-35.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_leg, Maths.rad(35.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_leg, Maths.rad(35.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(35.0), Maths.rad(-15.0), 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(35.0), Maths.rad(15.0), 0.0f);
        this.animator.rotate(this.trunk1, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.rotate(this.trunk2, Maths.rad(45.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail, Maths.rad(45.0), 0.0f, 0.0f);
        this.animator.move(this.body, 0.0f, -6.0f, 0.0f);
        this.animator.move(this.trunk2, 0.0f, -2.0f, 0.0f);
        this.animator.move(this.left_leg, 0.0f, -1.0f, 0.0f);
        this.animator.move(this.right_leg, 0.0f, -1.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(7);
        this.animator.resetKeyframe(3);
        this.animator.setAnimation(EntityElephant.ANIMATION_FLING);
        this.animator.startKeyframe(10);
        this.animator.rotate(this.head, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_ear, 0.0f, Maths.rad(25.0), 0.0f);
        this.animator.rotate(this.right_ear, 0.0f, Maths.rad(-25.0), 0.0f);
        this.animator.rotate(this.trunk1, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.trunk2, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.move(this.head, 0.0f, 3.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.head, 0.0f, -2.0f, -1.0f);
        this.animator.rotate(this.head, Maths.rad(-45.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_ear, 0.0f, Maths.rad(25.0), 0.0f);
        this.animator.rotate(this.right_ear, 0.0f, Maths.rad(-25.0), 0.0f);
        this.animator.rotate(this.trunk1, Maths.rad(-55.0), 0.0f, 0.0f);
        this.animator.rotate(this.trunk2, Maths.rad(-55.0), 0.0f, 0.0f);
        this.animator.move(this.trunk2, 0.0f, -2.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(2);
        this.animator.resetKeyframe(8);
        this.animator.setAnimation(EntityElephant.ANIMATION_EAT);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.trunk1, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.rotate(this.trunk2, Maths.rad(-45.0), 0.0f, 0.0f);
        this.animator.move(this.trunk2, 0.0f, -2.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(8);
        this.animator.rotate(this.head, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.trunk1, Maths.rad(-45.0), 0.0f, 0.0f);
        this.animator.rotate(this.trunk2, Maths.rad(120.0), 0.0f, 0.0f);
        this.animator.move(this.trunk1, 0.0f, 0.0f, -1.0f);
        this.animator.move(this.trunk2, 0.0f, -2.0f, 1.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.resetKeyframe(9);
        this.animator.setAnimation(EntityElephant.ANIMATION_BREAKLEAVES);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, Maths.rad(-5.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_ear, 0.0f, Maths.rad(5.0), 0.0f);
        this.animator.rotate(this.right_ear, 0.0f, Maths.rad(-5.0), 0.0f);
        this.animator.rotate(this.trunk1, Maths.rad(-40.0), 0.0f, 0.0f);
        this.animator.rotate(this.trunk2, Maths.rad(-60.0), 0.0f, 0.0f);
        this.animator.move(this.trunk2, 0.0f, -2.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.resetKeyframe(5);
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.5f;
            float f2 = 0.75f;
            this.head.rotationPointY = -10.0f;
            this.head.setScale(f, f, f);
            this.tail.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            this.trunk1.setScale(f2, f2, f2);
            this.trunk1.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.35f, 0.35f, 0.35f);
            matrixStackIn.m_85837_(0.0, 2.8, 0.0);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
            this.tail.setScale(1.0f, 1.0f, 1.0f);
            this.trunk1.setScale(1.0f, 1.0f, 1.0f);
        } else {
            this.head.rotationPointY = -2.0f;
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setupAnim(EntityElephant entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 0.7f;
        float walkDegree = 0.4f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.2f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float sitProgress = entityIn.prevSitProgress + (entityIn.sitProgress - entityIn.prevSitProgress) * partialTick;
        float standProgress = entityIn.prevStandProgress + (entityIn.standProgress - entityIn.prevStandProgress) * partialTick;
        this.progressRotationPrev(this.body, standProgress, Maths.rad(-60.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, standProgress, Maths.rad(60.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_arm, standProgress, Maths.rad(60.0), Maths.rad(10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, standProgress, Maths.rad(60.0), Maths.rad(-10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg, standProgress, Maths.rad(60.0), Maths.rad(-15.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg, standProgress, Maths.rad(60.0), Maths.rad(15.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.body, standProgress, 0.0f, -9.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_arm, standProgress, 0.0f, 0.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.left_arm, standProgress, 0.0f, 0.0f, 2.0f, 5.0f);
        this.progressRotationPrev(this.tail, limbSwingAmount, Maths.rad(20.0), 0.0f, 0.0f, 1.0f);
        this.progressRotationPrev(this.right_arm, sitProgress, Maths.rad(-90.0), Maths.rad(10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, sitProgress, Maths.rad(-90.0), Maths.rad(-10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg, sitProgress, Maths.rad(90.0), Maths.rad(5.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg, sitProgress, Maths.rad(90.0), Maths.rad(-5.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.body, sitProgress, 0.0f, 14.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_arm, sitProgress, 0.0f, 5.0f, 5.0f, 5.0f);
        this.progressPositionPrev(this.left_arm, sitProgress, 0.0f, 5.0f, 5.0f, 5.0f);
        this.progressPositionPrev(this.right_leg, sitProgress, 0.0f, 5.0f, -3.0f, 5.0f);
        this.progressPositionPrev(this.left_leg, sitProgress, 0.0f, 5.0f, -3.0f, 5.0f);
        this.progressPositionPrev(this.head, sitProgress, 0.0f, -3.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.trunk1, sitProgress, 0.0f, -2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.trunk2, sitProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.trunk1, sitProgress, Maths.rad(-45.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.trunk2, sitProgress, Maths.rad(60.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, sitProgress, Maths.rad(50.0), 0.0f, 0.0f, 5.0f);
        this.swing(this.right_ear, idleSpeed, idleDegree, false, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.swing(this.left_ear, idleSpeed, idleDegree, true, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.head, idleSpeed * 0.7f, idleDegree * 0.1f, false, -1.0f, 0.05f, ageInTicks, 1.0f);
        this.walk(this.trunk1, idleSpeed * 0.4f, idleDegree * 0.7f, false, 0.0f, 0.1f, ageInTicks, 1.0f);
        this.flap(this.trunk1, idleSpeed * 0.4f, idleDegree * 0.7f, false, 2.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.trunk2, idleSpeed * 0.4f, idleDegree * 0.35f, false, 0.0f, 0.05f, ageInTicks, 1.0f);
        this.flap(this.tail, idleSpeed, idleDegree * 0.7f, false, -1.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.right_arm, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.left_arm, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.right_leg, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.left_leg, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.head, walkSpeed, walkDegree * 0.1f, true, 0.0f, 0.2f, limbSwing, limbSwingAmount);
        this.walk(this.trunk1, walkSpeed, walkDegree * 0.1f, true, 0.0f, -0.1f, limbSwing, limbSwingAmount);
        this.walk(this.trunk2, walkSpeed, walkDegree * 0.1f, true, 0.0f, -0.3f, limbSwing, limbSwingAmount);
        this.swing(this.right_ear, walkSpeed, walkDegree * 0.34f, false, 1.0f, -0.01f, limbSwing, limbSwingAmount);
        this.swing(this.left_ear, walkSpeed, walkDegree * 0.34f, true, 1.0f, -0.01f, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed * 2.0f, walkDegree * 2.0f, false, limbSwing, limbSwingAmount);
        this.faceTarget(netHeadYaw, headPitch, 2.0f, new AdvancedModelBox[]{this.head});
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.tail, (Object)this.head, (Object)this.trunk1, (Object)this.trunk2, (Object)this.tail, (Object)this.left_ear, (Object)this.right_ear, (Object)this.left_leg, (Object)this.right_leg, (Object)this.left_arm, (Object[])new AdvancedModelBox[]{this.right_arm, this.cabin, this.left_chest, this.right_chest, this.left_tusk, this.right_tusk, this.left_megatusk, this.right_megatusk});
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
