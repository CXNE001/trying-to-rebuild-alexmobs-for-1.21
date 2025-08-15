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

import com.github.alexthe666.alexsmobs.entity.EntitySnowLeopard;
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

public class ModelSnowLeopard
extends AdvancedEntityModel<EntitySnowLeopard> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox tail1;
    private final AdvancedModelBox tail2;
    private final AdvancedModelBox tail3;
    private final AdvancedModelBox head;
    private final AdvancedModelBox bubble;
    private final AdvancedModelBox whiskersLeft;
    private final AdvancedModelBox whiskersRight;
    private final AdvancedModelBox armLeft;
    private final AdvancedModelBox armRight;
    private final AdvancedModelBox legLeft;
    private final AdvancedModelBox legRight;
    public ModelAnimator animator;

    public ModelSnowLeopard() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this);
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this);
        this.body.setRotationPoint(0.0f, -11.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-4.0f, -4.0f, -9.0f, 8.0f, 9.0f, 18.0f, 0.0f, false);
        this.tail1 = new AdvancedModelBox((AdvancedEntityModel)this);
        this.tail1.setRotationPoint(0.0f, -1.0f, 9.0f);
        this.body.addChild((BasicModelPart)this.tail1);
        this.setRotationAngle(this.tail1, -0.7418f, 0.0f, 0.0f);
        this.tail1.setTextureOffset(0, 28).addBox(-1.5f, -2.0f, -2.0f, 3.0f, 3.0f, 13.0f, 0.0f, false);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this);
        this.tail2.setRotationPoint(0.0f, -0.2f, 11.0f);
        this.tail1.addChild((BasicModelPart)this.tail2);
        this.setRotationAngle(this.tail2, -1.0472f, 0.0f, 0.0f);
        this.tail2.setTextureOffset(0, 28).addBox(-1.5f, -8.0f, -2.0f, 3.0f, 9.0f, 3.0f, 0.1f, false);
        this.tail3 = new AdvancedModelBox((AdvancedEntityModel)this);
        this.tail3.setRotationPoint(0.0f, -7.3f, -1.3f);
        this.tail2.addChild((BasicModelPart)this.tail3);
        this.setRotationAngle(this.tail3, -0.9599f, 0.0f, 0.0f);
        this.tail3.setTextureOffset(20, 28).addBox(-1.5f, -2.0f, -8.0f, 3.0f, 3.0f, 9.0f, 0.2f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this);
        this.head.setRotationPoint(0.0f, -1.0f, -9.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(35, 0).addBox(-3.5f, -4.0f, -4.0f, 7.0f, 5.0f, 5.0f, 0.0f, false);
        this.head.setTextureOffset(35, 11).addBox(-2.5f, -2.0f, -6.0f, 5.0f, 3.0f, 2.0f, 0.0f, false);
        this.head.setTextureOffset(36, 28).addBox(1.5f, -6.0f, -3.0f, 2.0f, 2.0f, 3.0f, 0.0f, false);
        this.head.setTextureOffset(36, 28).addBox(-3.5f, -6.0f, -3.0f, 2.0f, 2.0f, 3.0f, 0.0f, true);
        this.bubble = new AdvancedModelBox((AdvancedEntityModel)this);
        this.bubble.setRotationPoint(0.0f, -2.0f, -6.0f);
        this.head.addChild((BasicModelPart)this.bubble);
        this.bubble.setTextureOffset(7, 13).addBox(-2.0f, -2.0f, -2.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
        this.whiskersLeft = new AdvancedModelBox((AdvancedEntityModel)this);
        this.whiskersLeft.setRotationPoint(2.5f, -0.5f, -5.0f);
        this.head.addChild((BasicModelPart)this.whiskersLeft);
        this.setRotationAngle(this.whiskersLeft, 0.0f, -0.5236f, 0.0f);
        this.whiskersLeft.setTextureOffset(11, 0).addBox(0.0f, -1.5f, 0.0f, 3.0f, 3.0f, 0.0f, 0.0f, false);
        this.whiskersRight = new AdvancedModelBox((AdvancedEntityModel)this);
        this.whiskersRight.setRotationPoint(-2.5f, -0.5f, -5.0f);
        this.head.addChild((BasicModelPart)this.whiskersRight);
        this.setRotationAngle(this.whiskersRight, 0.0f, 0.5236f, 0.0f);
        this.whiskersRight.setTextureOffset(11, 0).addBox(-3.0f, -1.5f, 0.0f, 3.0f, 3.0f, 0.0f, 0.0f, true);
        this.armLeft = new AdvancedModelBox((AdvancedEntityModel)this);
        this.armLeft.setRotationPoint(2.9f, 4.0f, -6.0f);
        this.body.addChild((BasicModelPart)this.armLeft);
        this.armLeft.setTextureOffset(0, 0).addBox(-1.4f, -2.0f, -2.0f, 3.0f, 9.0f, 4.0f, 0.0f, false);
        this.armRight = new AdvancedModelBox((AdvancedEntityModel)this);
        this.armRight.setRotationPoint(-2.9f, 4.0f, -6.0f);
        this.body.addChild((BasicModelPart)this.armRight);
        this.armRight.setTextureOffset(0, 0).addBox(-1.6f, -2.0f, -2.0f, 3.0f, 9.0f, 4.0f, 0.0f, true);
        this.legLeft = new AdvancedModelBox((AdvancedEntityModel)this);
        this.legLeft.setRotationPoint(2.9f, 4.0f, 8.0f);
        this.body.addChild((BasicModelPart)this.legLeft);
        this.legLeft.setTextureOffset(29, 41).addBox(-1.4f, -1.0f, -2.0f, 3.0f, 8.0f, 4.0f, 0.0f, false);
        this.legRight = new AdvancedModelBox((AdvancedEntityModel)this);
        this.legRight.setRotationPoint(-2.9f, 4.0f, 8.0f);
        this.body.addChild((BasicModelPart)this.legRight);
        this.legRight.setTextureOffset(29, 41).addBox(-1.6f, -1.0f, -2.0f, 3.0f, 8.0f, 4.0f, 0.0f, true);
        this.updateDefaultPose();
        this.animator = new ModelAnimator();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.head, (Object)this.bubble, (Object)this.whiskersLeft, (Object)this.whiskersRight, (Object)this.armLeft, (Object)this.armRight, (Object)this.legLeft, (Object)this.legRight, (Object)this.tail1, (Object)this.tail2, (Object[])new AdvancedModelBox[]{this.tail3});
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.update(entity);
        this.animator.setAnimation(EntitySnowLeopard.ANIMATION_ATTACK_R);
        this.animator.startKeyframe(3);
        this.animator.rotate(this.body, 0.0f, Maths.rad(-10.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, Maths.rad(-10.0), Maths.rad(-10.0));
        this.animator.rotate(this.armRight, Maths.rad(25.0), Maths.rad(-20.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(0.0));
        this.animator.rotate(this.armRight, Maths.rad(-90.0), Maths.rad(-30.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntitySnowLeopard.ANIMATION_ATTACK_L);
        this.animator.startKeyframe(3);
        this.animator.rotate(this.body, 0.0f, Maths.rad(10.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, Maths.rad(10.0), Maths.rad(10.0));
        this.animator.rotate(this.armLeft, Maths.rad(25.0), Maths.rad(20.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(0.0));
        this.animator.rotate(this.armLeft, Maths.rad(-90.0), Maths.rad(30.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
    }

    public void setupAnim(EntitySnowLeopard entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 0.7f;
        float walkDegree = 0.6f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        float runProgress = 5.0f * limbSwingAmount;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float stalkProgress = entity.prevSneakProgress + (entity.sneakProgress - entity.prevSneakProgress) * partialTick;
        float tackleProgress = entity.prevTackleProgress + (entity.tackleProgress - entity.prevTackleProgress) * partialTick;
        float sitProgress = entity.prevSitProgress + (entity.sitProgress - entity.prevSitProgress) * partialTick;
        float sleepProgress = entity.prevSleepProgress + (entity.sleepProgress - entity.prevSleepProgress) * partialTick;
        float sitSleepProgress = Math.max(sitProgress, sleepProgress);
        this.swing(this.tail1, idleSpeed, idleDegree * 2.0f, false, 2.0f, 0.0f, ageInTicks, 1.0f - limbSwingAmount);
        this.swing(this.tail2, idleSpeed, idleDegree * 1.5f, false, 2.0f, 0.0f, ageInTicks, 1.0f - limbSwingAmount);
        this.flap(this.tail3, idleSpeed * 1.2f, idleDegree * 1.5f, false, 2.0f, 0.0f, ageInTicks, 1.0f - limbSwingAmount);
        this.swing(this.tail3, idleSpeed * 1.2f, idleDegree * 1.5f, false, 2.0f, 0.0f, ageInTicks, 1.0f - limbSwingAmount);
        this.walk(this.head, idleSpeed * 0.3f, idleDegree, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.head, idleSpeed * 0.3f, -idleDegree, false, 0.5f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.armRight, walkSpeed, walkDegree * 1.1f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.armRight, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.walk(this.armLeft, walkSpeed, walkDegree * 1.1f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.armLeft, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.walk(this.legRight, walkSpeed, walkDegree * 1.1f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.legRight, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.walk(this.legLeft, walkSpeed, walkDegree * 1.1f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.legLeft, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        AdvancedModelBox[] tailBoxes = new AdvancedModelBox[]{this.tail1, this.tail2, this.tail3};
        this.chainSwing(tailBoxes, walkSpeed, walkDegree * 0.5f, -2.5, limbSwing, limbSwingAmount);
        this.progressRotationPrev(this.tail1, runProgress, Maths.rad(40.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail2, runProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail3, runProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, stalkProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legLeft, stalkProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legRight, stalkProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.armLeft, stalkProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.armRight, stalkProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, stalkProgress, Maths.rad(5.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, stalkProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail1, stalkProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, stalkProgress, 0.0f, -0.5f, 4.0f, 5.0f);
        this.progressPositionPrev(this.legLeft, stalkProgress, 0.0f, 1.6f, -2.0f, 5.0f);
        this.progressPositionPrev(this.legRight, stalkProgress, 0.0f, 1.6f, -2.0f, 5.0f);
        this.progressPositionPrev(this.armLeft, stalkProgress, 0.0f, -0.4f, 0.0f, 5.0f);
        this.progressPositionPrev(this.armRight, stalkProgress, 0.0f, -0.4f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, tackleProgress, Maths.rad(-45.0), 0.0f, 0.0f, 3.0f);
        this.progressRotationPrev(this.head, tackleProgress, Maths.rad(45.0), 0.0f, 0.0f, 3.0f);
        this.progressRotationPrev(this.tail1, tackleProgress, Maths.rad(60.0), 0.0f, 0.0f, 3.0f);
        this.progressRotationPrev(this.armRight, tackleProgress, Maths.rad(-25.0), 0.0f, Maths.rad(45.0), 3.0f);
        this.progressRotationPrev(this.armLeft, tackleProgress, Maths.rad(-25.0), 0.0f, Maths.rad(-45.0), 3.0f);
        this.progressRotationPrev(this.legLeft, tackleProgress, Maths.rad(-15.0), 0.0f, Maths.rad(-25.0), 3.0f);
        this.progressRotationPrev(this.legRight, tackleProgress, Maths.rad(-15.0), 0.0f, Maths.rad(25.0), 3.0f);
        this.progressPositionPrev(this.body, tackleProgress, 0.0f, -5.0f, 0.0f, 3.0f);
        this.progressPositionPrev(this.head, tackleProgress, 0.0f, 2.0f, 0.0f, 3.0f);
        this.progressPositionPrev(this.armLeft, tackleProgress, 1.0f, 2.0f, 0.0f, 3.0f);
        this.progressPositionPrev(this.armRight, tackleProgress, -1.0f, 2.0f, 0.0f, 3.0f);
        this.progressPositionPrev(this.tail1, tackleProgress, 0.0f, 0.0f, -1.0f, 3.0f);
        float tailAngle = entity.m_19879_() % 2 == 0 ? 1.0f : -1.0f;
        this.progressRotationPrev(this.legLeft, sitSleepProgress, Maths.rad(-90.0), Maths.rad(-20.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.legRight, sitSleepProgress, Maths.rad(-90.0), Maths.rad(20.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.armLeft, sitSleepProgress, Maths.rad(-90.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.armRight, sitSleepProgress, Maths.rad(-90.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, sitSleepProgress, 0.0f, 3.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.armRight, sitSleepProgress, 0.0f, 2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.armLeft, sitSleepProgress, 0.0f, 2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.legRight, sitSleepProgress, 0.0f, 2.8f, -0.5f, 5.0f);
        this.progressPositionPrev(this.legLeft, sitSleepProgress, 0.0f, 2.8f, -0.5f, 5.0f);
        this.progressRotationPrev(this.tail1, sitProgress, Maths.rad(20.0), Maths.rad(tailAngle * 30.0f), 0.0f, 5.0f);
        this.progressRotationPrev(this.tail2, sitProgress, Maths.rad(-5.0), Maths.rad(tailAngle * 50.0f), 0.0f, 5.0f);
        this.progressRotationPrev(this.tail3, sitProgress, Maths.rad(10.0), Maths.rad(tailAngle * 20.0f), Maths.rad(tailAngle * 20.0f), 5.0f);
        this.progressRotationPrev(this.tail1, sleepProgress, Maths.rad(20.0), Maths.rad(tailAngle * -60.0f), 0.0f, 5.0f);
        this.progressRotationPrev(this.tail2, sleepProgress, Maths.rad(10.0), Maths.rad(tailAngle * -70.0f), Maths.rad(tailAngle * -50.0f), 5.0f);
        this.progressRotationPrev(this.tail3, sleepProgress, Maths.rad(-30.0), Maths.rad(tailAngle * -50.0f), Maths.rad(tailAngle * -30.0f), 5.0f);
        this.progressRotationPrev(this.body, sleepProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.armLeft, sleepProgress, Maths.rad(-10.0), Maths.rad(-30.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.armRight, sleepProgress, Maths.rad(-10.0), Maths.rad(30.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.legRight, sleepProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legRight, sleepProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail1, sleepProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, sleepProgress, Maths.rad(-10.0), 0.0f, Maths.rad(-10.0), 5.0f);
        this.progressPositionPrev(this.head, sleepProgress, 0.0f, 5.0f, -1.0f, 5.0f);
        if (sleepProgress >= 5.0f) {
            float f = (float)((double)sleepProgress * Math.max(Math.sin(ageInTicks * 0.05f), 0.0) * (double)0.2f);
            this.bubble.showModel = true;
            this.bubble.setScale(f, f, f);
        } else {
            this.bubble.showModel = false;
        }
        if (sleepProgress <= 0.0f) {
            this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.head});
        }
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.45f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.0);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
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
