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
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityAnteater;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelAnteater
extends AdvancedEntityModel<EntityAnteater> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox head;
    public final AdvancedModelBox left_ear;
    public final AdvancedModelBox right_ear;
    public final AdvancedModelBox snout;
    public final AdvancedModelBox tongue1;
    public final AdvancedModelBox tongue2;
    public final AdvancedModelBox left_leg;
    public final AdvancedModelBox right_leg;
    public final AdvancedModelBox left_arm;
    public final AdvancedModelBox left_claws;
    public final AdvancedModelBox right_arm;
    public final AdvancedModelBox right_claws;
    public final AdvancedModelBox tail;
    public ModelAnimator animator;

    public ModelAnteater() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -13.0f, 4.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-4.0f, -5.0f, -14.0f, 8.0f, 10.0f, 21.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -4.0f, -15.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(38, 0).addBox(-2.0f, -1.0f, -7.0f, 4.0f, 5.0f, 8.0f, 0.0f, false);
        this.left_ear = new AdvancedModelBox((AdvancedEntityModel)this, "left_ear");
        this.left_ear.setRotationPoint(2.0f, 0.0f, -4.0f);
        this.head.addChild((BasicModelPart)this.left_ear);
        this.left_ear.setTextureOffset(11, 0).addBox(-1.0f, -2.0f, 0.0f, 2.0f, 2.0f, 0.0f, 0.0f, false);
        this.right_ear = new AdvancedModelBox((AdvancedEntityModel)this, "right_ear");
        this.right_ear.setRotationPoint(-2.0f, 0.0f, -4.0f);
        this.head.addChild((BasicModelPart)this.right_ear);
        this.right_ear.setTextureOffset(11, 0).addBox(-1.0f, -2.0f, 0.0f, 2.0f, 2.0f, 0.0f, 0.0f, true);
        this.snout = new AdvancedModelBox((AdvancedEntityModel)this, "snout");
        this.snout.setRotationPoint(0.0f, 1.5f, -6.5f);
        this.head.addChild((BasicModelPart)this.snout);
        this.setRotationAngle(this.snout, 0.2618f, 0.0f, 0.0f);
        this.snout.setTextureOffset(28, 32).addBox(-1.0f, -1.0f, -10.0f, 2.0f, 3.0f, 10.0f, 0.0f, false);
        this.tongue1 = new AdvancedModelBox((AdvancedEntityModel)this, "tongue1");
        this.tongue1.setRotationPoint(0.0f, 1.0f, -10.0f);
        this.snout.addChild((BasicModelPart)this.tongue1);
        this.tongue1.setTextureOffset(43, 32).addBox(-0.5f, 0.0f, -6.0f, 1.0f, 0.0f, 6.0f, 0.0f, false);
        this.tongue2 = new AdvancedModelBox((AdvancedEntityModel)this, "tongue2");
        this.tongue2.setRotationPoint(0.0f, 0.0f, -6.0f);
        this.tongue1.addChild((BasicModelPart)this.tongue2);
        this.tongue2.setTextureOffset(38, 14).addBox(-0.5f, 0.0f, -6.0f, 1.0f, 0.0f, 6.0f, 0.0f, false);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setRotationPoint(2.5f, 4.0f, 5.0f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(0, 32).addBox(-1.5f, 1.0f, -2.0f, 3.0f, 8.0f, 4.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setRotationPoint(-2.5f, 4.0f, 5.0f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(0, 32).addBox(-1.5f, 1.0f, -2.0f, 3.0f, 8.0f, 4.0f, 0.0f, true);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setRotationPoint(3.1f, 4.0f, -11.9f);
        this.body.addChild((BasicModelPart)this.left_arm);
        this.left_arm.setTextureOffset(0, 0).addBox(-2.0f, -1.0f, -2.0f, 3.0f, 10.0f, 4.0f, 0.0f, false);
        this.left_claws = new AdvancedModelBox((AdvancedEntityModel)this, "left_claws");
        this.left_claws.setRotationPoint(-1.0f, 9.0f, 2.0f);
        this.left_arm.addChild((BasicModelPart)this.left_claws);
        this.left_claws.setTextureOffset(13, 13).addBox(0.0f, -2.0f, 0.0f, 1.0f, 2.0f, 2.0f, 0.0f, false);
        this.left_claws.setTextureOffset(0, 15).addBox(-1.0f, -2.0f, 0.0f, 0.0f, 2.0f, 2.0f, 0.0f, false);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setRotationPoint(-3.1f, 4.0f, -11.9f);
        this.body.addChild((BasicModelPart)this.right_arm);
        this.right_arm.setTextureOffset(0, 0).addBox(-1.0f, -1.0f, -2.0f, 3.0f, 10.0f, 4.0f, 0.0f, true);
        this.right_claws = new AdvancedModelBox((AdvancedEntityModel)this, "right_claws");
        this.right_claws.setRotationPoint(1.0f, 9.0f, 2.0f);
        this.right_arm.addChild((BasicModelPart)this.right_claws);
        this.right_claws.setTextureOffset(13, 13).addBox(-1.0f, -2.0f, 0.0f, 1.0f, 2.0f, 2.0f, 0.0f, true);
        this.right_claws.setTextureOffset(0, 15).addBox(1.0f, -2.0f, 0.0f, 0.0f, 2.0f, 2.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, -5.0f, 7.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 32).addBox(-2.0f, 0.0f, 0.0f, 4.0f, 11.0f, 19.0f, 0.0f, false);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void animate(EntityAnteater entity, float f, float f1, float f2, float f3, float f4) {
        float partialTick = f2 - (float)entity.f_19797_;
        float standProgress = (entity.prevStandProgress + (entity.standProgress - entity.prevStandProgress) * partialTick) * 0.2f;
        float inverStandProgress = 1.0f - standProgress;
        this.animator.update((IAnimatedEntity)entity);
        this.animator.setAnimation(EntityAnteater.ANIMATION_SLASH_L);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.body, Maths.rad(-15.0), Maths.rad(-15.0), 0.0f);
        this.animator.rotate(this.tail, Maths.rad(25.0), Maths.rad(15.0), 0.0f);
        this.animator.rotate(this.head, Maths.rad(15.0), Maths.rad(15.0), 0.0f);
        this.animator.rotate(this.right_leg, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_leg, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(-50.0), 0.0f, Maths.rad(-45.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.head, Maths.rad(5.0), 0.0f, 0.0f);
        this.animator.move(this.left_arm, 0.0f, 2.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(-10.0) + Maths.rad(-70.0) * inverStandProgress, 0.0f, Maths.rad(65.0) * standProgress);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityAnteater.ANIMATION_SLASH_R);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.body, Maths.rad(-15.0), Maths.rad(15.0), 0.0f);
        this.animator.rotate(this.tail, Maths.rad(25.0), Maths.rad(-15.0), 0.0f);
        this.animator.rotate(this.head, Maths.rad(15.0), Maths.rad(-15.0), 0.0f);
        this.animator.rotate(this.right_leg, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_leg, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-50.0), 0.0f, Maths.rad(45.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.head, Maths.rad(5.0), 0.0f, 0.0f);
        this.animator.move(this.right_arm, 0.0f, 2.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-10.0) + Maths.rad(-70.0) * inverStandProgress, 0.0f, Maths.rad(-65.0) * standProgress);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
    }

    public void setupAnim(EntityAnteater entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float tongueSpeed = 0.7f;
        float tongueDegree = 0.35f;
        float walkSpeed = 0.5f;
        float walkDegree = 1.0f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.2f;
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float standProgress = entity.prevStandProgress + (entity.standProgress - entity.prevStandProgress) * partialTick;
        float feedProgress = entity.prevTongueProgress + (entity.tongueProgress - entity.prevTongueProgress) * partialTick;
        float leaningProgress = entity.prevLeaningProgress + (entity.leaningProgress - entity.prevLeaningProgress) * partialTick;
        this.progressRotationPrev(this.body, standProgress, Maths.rad(-80.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg, standProgress, Maths.rad(80.0), Maths.rad(10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg, standProgress, Maths.rad(80.0), Maths.rad(-10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, standProgress, Maths.rad(80.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, standProgress, Maths.rad(45.0), Maths.rad(-70.0), Maths.rad(-120.0), 5.0f);
        this.progressRotationPrev(this.right_arm, standProgress, Maths.rad(45.0), Maths.rad(70.0), Maths.rad(120.0), 5.0f);
        this.progressRotationPrev(this.head, standProgress, Maths.rad(80.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, standProgress, 0.0f, 1.0f, -2.0f, 5.0f);
        this.progressPositionPrev(this.body, standProgress, 0.0f, -1.0f, -2.0f, 5.0f);
        this.progressPositionPrev(this.tail, standProgress, 0.0f, 2.0f, -3.0f, 5.0f);
        this.progressPositionPrev(this.left_leg, standProgress, 0.0f, -2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_leg, standProgress, 0.0f, -2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_arm, standProgress, 1.0f, -1.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.right_arm, standProgress, -1.0f, -1.0f, 2.0f, 5.0f);
        if (entity.m_6162_() && entity.m_20159_()) {
            this.progressRotationPrev(this.left_arm, 1.0f, 0.0f, Maths.rad(-90.0), Maths.rad(-60.0), 1.0f);
            this.progressRotationPrev(this.right_arm, 1.0f, 0.0f, Maths.rad(90.0), Maths.rad(60.0), 1.0f);
            this.progressRotationPrev(this.left_leg, 1.0f, Maths.rad(20.0), 0.0f, Maths.rad(-60.0), 1.0f);
            this.progressRotationPrev(this.right_leg, 1.0f, Maths.rad(20.0), 0.0f, Maths.rad(60.0), 1.0f);
            this.progressRotationPrev(this.tail, 1.0f, Maths.rad(-20.0), 0.0f, 0.0f, 1.0f);
        }
        this.progressRotationPrev(this.head, leaningProgress, Maths.rad(50.0), 0.0f, 0.0f, 20.0f);
        double tongueM = Math.min(Math.sin(ageInTicks * 0.15f), 0.0);
        float toungeF = 12.0f + 12.0f * (float)tongueM * (feedProgress * 0.2f);
        float toungeMinus = (float)(-tongueM) * (feedProgress * 0.2f);
        this.walk(this.tongue1, tongueSpeed * 2.0f, tongueDegree, false, 0.0f, 0.0f, ageInTicks, toungeMinus);
        this.walk(this.tongue2, tongueSpeed * 2.0f, tongueDegree, false, 0.0f, 0.0f, ageInTicks, toungeMinus);
        this.tongue1.rotationPointZ += toungeF;
        this.walk(this.tail, idleSpeed, idleDegree, true, 2.0f, 0.2f, ageInTicks, 1.0f);
        this.walk(this.right_arm, idleSpeed, idleDegree, true, 2.0f, 0.2f, ageInTicks, standProgress * 0.2f);
        this.walk(this.left_arm, idleSpeed, idleDegree, true, 2.0f, 0.2f, ageInTicks, standProgress * 0.2f);
        this.walk(this.right_leg, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.left_leg, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.left_arm, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.right_arm, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.tail, walkSpeed, walkDegree * 0.2f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed, walkDegree * 2.0f, true, limbSwing, limbSwingAmount);
        this.bob(this.head, walkSpeed, walkDegree, true, limbSwing, limbSwingAmount);
        if (standProgress <= 0.0f) {
            this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.head});
        }
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.tail, (Object)this.head, (Object)this.left_ear, (Object)this.right_ear, (Object)this.left_arm, (Object)this.right_arm, (Object)this.left_leg, (Object)this.right_leg, (Object)this.left_claws, (Object)this.right_claws, (Object[])new AdvancedModelBox[]{this.snout, this.tongue1, this.tongue2});
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
            this.head.setScale(1.0f, 1.0f, 1.0f);
        } else {
            this.head.setScale(1.0f, 1.0f, 1.0f);
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
