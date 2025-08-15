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

import com.github.alexthe666.alexsmobs.entity.EntityGrizzlyBear;
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

public class ModelGrizzlyBear
extends AdvancedEntityModel<EntityGrizzlyBear> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox midbody;
    public final AdvancedModelBox head;
    public final AdvancedModelBox snout;
    public final AdvancedModelBox left_ear;
    public final AdvancedModelBox right_ear;
    public final AdvancedModelBox left_leg;
    public final AdvancedModelBox right_leg;
    public final AdvancedModelBox left_arm;
    public final AdvancedModelBox right_arm;
    private final AdvancedModelBox hat;
    private final AdvancedModelBox microphone;
    public final ModelAnimator animator;

    public ModelGrizzlyBear() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -19.0f, 6.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-7.0f, -7.0f, -19.0f, 14.0f, 15.0f, 28.0f, 0.0f, false);
        this.body.setTextureOffset(0, 44).addBox(-6.0f, 8.0f, -19.0f, 12.0f, 3.0f, 28.0f, 0.0f, false);
        this.midbody = new AdvancedModelBox((AdvancedEntityModel)this, "midbody");
        this.midbody.setPos(0.0f, 0.5f, -4.0f);
        this.body.addChild((BasicModelPart)this.midbody);
        this.midbody.setTextureOffset(27, 99).addBox(-8.0f, -8.5f, -6.0f, 16.0f, 17.0f, 12.0f, 0.1f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -0.8f, -21.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(57, 0).addBox(-5.0f, -5.0f, -6.0f, 10.0f, 10.0f, 8.0f, 0.0f, false);
        this.snout = new AdvancedModelBox((AdvancedEntityModel)this, "snout");
        this.snout.setPos(0.0f, 0.0f, -6.0f);
        this.head.addChild((BasicModelPart)this.snout);
        this.snout.setTextureOffset(0, 17).addBox(-2.0f, 0.0f, -5.0f, 4.0f, 5.0f, 5.0f, 0.0f, false);
        this.left_ear = new AdvancedModelBox((AdvancedEntityModel)this, "left_ear");
        this.left_ear.setPos(3.5f, -5.0f, -3.0f);
        this.head.addChild((BasicModelPart)this.left_ear);
        this.left_ear.setTextureOffset(14, 17).addBox(-1.5f, -2.0f, -1.0f, 3.0f, 2.0f, 2.0f, 0.0f, false);
        this.right_ear = new AdvancedModelBox((AdvancedEntityModel)this, "right_ear");
        this.right_ear.setPos(-3.5f, -5.0f, -3.0f);
        this.head.addChild((BasicModelPart)this.right_ear);
        this.right_ear.setTextureOffset(14, 17).addBox(-1.5f, -2.0f, -1.0f, 3.0f, 2.0f, 2.0f, 0.0f, true);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setPos(3.8f, 8.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(0, 76).addBox(-3.0f, 0.0f, -3.0f, 6.0f, 11.0f, 8.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setPos(-3.8f, 8.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(0, 76).addBox(-3.0f, 0.0f, -3.0f, 6.0f, 11.0f, 8.0f, 0.0f, true);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setPos(4.5f, 4.0f, -13.0f);
        this.body.addChild((BasicModelPart)this.left_arm);
        this.left_arm.setTextureOffset(74, 78).addBox(-3.0f, -3.0f, -3.0f, 6.0f, 18.0f, 7.0f, 0.0f, false);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setPos(-4.5f, 4.0f, -13.0f);
        this.body.addChild((BasicModelPart)this.right_arm);
        this.right_arm.setTextureOffset(74, 78).addBox(-3.0f, -3.0f, -3.0f, 6.0f, 18.0f, 7.0f, 0.0f, true);
        this.hat = new AdvancedModelBox((AdvancedEntityModel)this, "hat");
        this.hat.setRotationPoint(0.0f, -5.0f, -4.0f);
        this.head.addChild((BasicModelPart)this.hat);
        this.hat.setTextureOffset(0, 57).addBox(-3.0f, -1.0f, -1.0f, 6.0f, 1.0f, 6.0f, 0.0f, false);
        this.hat.setTextureOffset(0, 48).addBox(-2.0f, -5.0f, 0.0f, 4.0f, 4.0f, 4.0f, 0.0f, false);
        this.microphone = new AdvancedModelBox((AdvancedEntityModel)this, "microphone");
        this.microphone.setRotationPoint(0.0f, 13.0f, -3.0f);
        this.right_arm.addChild((BasicModelPart)this.microphone);
        this.microphone.setTextureOffset(0, 0).addBox(-1.0f, -1.0f, -4.0f, 2.0f, 2.0f, 5.0f, 0.0f, false);
        this.microphone.setTextureOffset(15, 0).addBox(-1.5f, -1.5f, -6.0f, 3.0f, 3.0f, 3.0f, 0.0f, false);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityGrizzlyBear.ANIMATION_MAUL);
        this.animator.startKeyframe(4);
        this.animator.rotate(this.body, Maths.rad(6.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(70.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.body, Maths.rad(2.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(70.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.body, Maths.rad(6.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(70.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.body, Maths.rad(2.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(70.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(4);
        this.animator.endKeyframe();
        this.animator.setAnimation(EntityGrizzlyBear.ANIMATION_SWIPE_R);
        this.animator.startKeyframe(7);
        this.animator.rotate(this.body, 0.0f, Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.midbody, 0.0f, Maths.rad(10.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.left_arm, Maths.rad(65.0), 0.0f, Maths.rad(-100.0));
        this.animator.rotate(this.right_arm, Maths.rad(-15.0), 0.0f, Maths.rad(10.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.body, 0.0f, Maths.rad(-30.0), 0.0f);
        this.animator.rotate(this.midbody, 0.0f, Maths.rad(-15.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(0.0));
        this.animator.rotate(this.left_arm, Maths.rad(20.0), 0.0f, Maths.rad(80.0));
        this.animator.rotate(this.right_arm, Maths.rad(-15.0), 0.0f, Maths.rad(20.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(3);
        this.animator.setAnimation(EntityGrizzlyBear.ANIMATION_SWIPE_L);
        this.animator.startKeyframe(7);
        this.animator.rotate(this.body, 0.0f, Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.midbody, 0.0f, Maths.rad(-10.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.right_arm, Maths.rad(65.0), 0.0f, Maths.rad(100.0));
        this.animator.rotate(this.left_arm, Maths.rad(-15.0), 0.0f, Maths.rad(-10.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.body, 0.0f, Maths.rad(30.0), 0.0f);
        this.animator.rotate(this.midbody, 0.0f, Maths.rad(15.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(0.0));
        this.animator.rotate(this.right_arm, Maths.rad(-20.0), 0.0f, Maths.rad(-80.0));
        this.animator.rotate(this.left_arm, Maths.rad(15.0), 0.0f, Maths.rad(-20.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(3);
        this.animator.setAnimation(EntityGrizzlyBear.ANIMATION_SNIFF);
        this.animator.startKeyframe(3);
        this.animator.rotate(this.head, Maths.rad(20.0), Maths.rad(3.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.head, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.head, Maths.rad(20.0), Maths.rad(-3.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(3);
        this.animator.endKeyframe();
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.75f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.35f, 0.35f, 0.35f);
            matrixStackIn.m_85837_(0.0, 2.75, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setupAnim(EntityGrizzlyBear entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.head.setShouldScaleChildren(true);
        this.animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 0.7f;
        float walkDegree = 0.7f;
        float eatSpeed = 0.8f;
        float eatDegree = 0.3f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float sitProgress = entityIn.prevSitProgress + (entityIn.sitProgress - entityIn.prevSitProgress) * partialTick;
        float standProgress = entityIn.prevStandProgress + (entityIn.standProgress - entityIn.prevStandProgress) * partialTick;
        this.progressRotationPrev(this.body, sitProgress, Maths.rad(-80.0), 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.head, sitProgress, Maths.rad(80.0), 0.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.body, sitProgress, 0.0f, 10.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.left_leg, sitProgress, 0.0f, Maths.rad(10.0), Maths.rad(-30.0), 10.0f);
        this.progressRotationPrev(this.right_leg, sitProgress, 0.0f, Maths.rad(-10.0), Maths.rad(30.0), 10.0f);
        this.progressRotationPrev(this.left_arm, sitProgress, Maths.rad(25.0), Maths.rad(10.0), 0.0f, 10.0f);
        this.progressRotationPrev(this.right_arm, sitProgress, Maths.rad(25.0), Maths.rad(-10.0), 0.0f, 10.0f);
        this.progressPositionPrev(this.head, sitProgress, 0.0f, 4.0f, -1.0f, 10.0f);
        if (Math.max(standProgress, sitProgress) > 5.0f) {
            this.head.rotateAngleZ += netHeadYaw * ((float)Math.PI / 180);
        } else {
            this.head.rotateAngleY += netHeadYaw * ((float)Math.PI / 180);
        }
        this.head.rotateAngleX += headPitch * ((float)Math.PI / 180);
        if (entityIn.isFreddy()) {
            if (standProgress > 0.0f) {
                this.walk(this.right_arm, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
                this.walk(this.left_arm, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            }
            this.head.setScale(1.2f, 1.2f, 1.2f);
            this.snout.setScale(1.4f, 1.0f, 1.0f);
            this.progressPositionPrev(this.snout, 10.0f, 0.0f, 0.0f, 2.0f, 10.0f);
            this.progressPositionPrev(this.head, standProgress, 0.0f, -0.5f, -3.0f, 10.0f);
            this.progressPositionPrev(this.body, standProgress, 0.0f, 0.0f, -5.0f, 10.0f);
            this.progressRotationPrev(this.body, standProgress, Maths.rad(-90.0), 0.0f, 0.0f, 10.0f);
            this.progressRotationPrev(this.head, standProgress, Maths.rad(90.0), 0.0f, 0.0f, 10.0f);
            this.progressRotationPrev(this.left_arm, standProgress, Maths.rad(80.0), Maths.rad(15.0), 0.0f, 10.0f);
            this.progressRotationPrev(this.right_arm, standProgress, Maths.rad(80.0), Maths.rad(-15.0), 0.0f, 10.0f);
            this.progressPositionPrev(this.left_arm, standProgress, 2.0f, 0.0f, 0.0f, 10.0f);
            this.progressPositionPrev(this.right_arm, standProgress, -2.0f, 0.0f, 0.0f, 10.0f);
            this.progressRotationPrev(this.left_leg, standProgress, Maths.rad(90.0), 0.0f, 0.0f, 10.0f);
            this.progressRotationPrev(this.right_leg, standProgress, Maths.rad(90.0), 0.0f, 0.0f, 10.0f);
            this.progressPositionPrev(this.left_leg, standProgress, 0.0f, -4.0f, 4.0f, 10.0f);
            this.progressPositionPrev(this.right_leg, standProgress, 0.0f, -4.0f, 4.0f, 10.0f);
        } else {
            this.head.setScale(1.0f, 1.0f, 1.0f);
            this.snout.setScale(1.0f, 1.0f, 1.0f);
            this.progressRotationPrev(this.left_leg, standProgress, Maths.rad(80.0), 0.0f, 0.0f, 10.0f);
            this.progressRotationPrev(this.right_leg, standProgress, Maths.rad(80.0), 0.0f, 0.0f, 10.0f);
            this.progressPositionPrev(this.left_leg, standProgress, 0.0f, -4.0f, 4.0f, 10.0f);
            this.progressPositionPrev(this.right_leg, standProgress, 0.0f, -4.0f, 4.0f, 10.0f);
            this.progressPositionPrev(this.head, standProgress, 0.0f, 0.0f, 2.0f, 10.0f);
            this.progressPositionPrev(this.body, standProgress, 0.0f, -1.0f, -5.0f, 10.0f);
            this.progressPositionPrev(this.head, standProgress, 0.0f, 1.0f, -3.0f, 10.0f);
            this.progressRotationPrev(this.body, standProgress, Maths.rad(-80.0), 0.0f, 0.0f, 10.0f);
            this.progressRotationPrev(this.head, standProgress, Maths.rad(80.0), 0.0f, 0.0f, 10.0f);
            this.progressRotationPrev(this.left_arm, standProgress, Maths.rad(35.0), Maths.rad(-10.0), 0.0f, 10.0f);
            this.progressRotationPrev(this.right_arm, standProgress, Maths.rad(35.0), Maths.rad(10.0), 0.0f, 10.0f);
        }
        this.walk(this.left_leg, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.left_leg, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.walk(this.right_leg, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.left_leg, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        if (standProgress == 0.0f && sitProgress == 0.0f) {
            this.walk(this.right_arm, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.left_arm, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.midbody, walkSpeed, walkDegree * 0.2f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.body, walkSpeed, walkDegree * 0.2f, false, 2.0f, 0.0f, limbSwing, limbSwingAmount);
        } else {
            this.walk(this.right_arm, walkSpeed, walkDegree * 0.1f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.left_arm, walkSpeed, walkDegree * 0.1f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            if (entityIn.isEating()) {
                this.walk(this.right_arm, eatSpeed, eatDegree, true, 1.0f, 0.6f, ageInTicks, 1.0f);
                this.walk(this.left_arm, eatSpeed, eatDegree, true, 1.0f, 0.6f, ageInTicks, 1.0f);
                this.walk(this.body, eatSpeed, eatDegree * 0.1f, true, 2.0f, 0.1f, ageInTicks, 1.0f);
                this.walk(this.head, eatSpeed, eatDegree * 0.3f, false, 1.0f, 0.3f, ageInTicks, 1.0f);
            }
        }
        this.flap(this.head, walkSpeed, walkDegree * -0.1f, false, 2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed, walkDegree, true, limbSwing, limbSwingAmount);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.right_arm, (Object)this.left_arm, (Object)this.head, (Object)this.left_ear, (Object)this.right_ear, (Object)this.snout, (Object)this.midbody, (Object)this.left_leg, (Object)this.right_leg, (Object)this.microphone, (Object[])new AdvancedModelBox[]{this.hat});
    }

    public void positionForParticle(float partialTicks, float ageInTicks) {
        this.resetToDefaultPose();
        float walkSpeed = 0.7f;
        float walkDegree = 0.7f;
        this.walk(this.head, walkSpeed, walkDegree * 0.2f, false, 1.0f, -0.4f, ageInTicks, 1.0f);
        this.walk(this.right_arm, walkSpeed, walkDegree, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.left_arm, walkSpeed, walkDegree, true, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.swing(this.right_arm, walkSpeed, walkDegree, false, 2.0f, -1.7f, ageInTicks, 1.0f);
        this.swing(this.left_arm, walkSpeed, walkDegree, false, 2.0f, 1.7f, ageInTicks, 1.0f);
        this.progressPositionPrev(this.snout, 10.0f, 0.0f, 0.0f, 2.0f, 10.0f);
        this.progressPositionPrev(this.head, 10.0f, 0.0f, -0.5f, -3.0f, 10.0f);
        this.progressPositionPrev(this.body, 10.0f, 0.0f, 0.0f, -5.0f, 10.0f);
        this.progressRotationPrev(this.body, 10.0f, Maths.rad(-90.0), 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.head, 10.0f, Maths.rad(90.0), 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.left_arm, 10.0f, Maths.rad(80.0), Maths.rad(15.0), 0.0f, 10.0f);
        this.progressRotationPrev(this.right_arm, 10.0f, Maths.rad(80.0), Maths.rad(-15.0), 0.0f, 10.0f);
        this.progressPositionPrev(this.left_arm, 10.0f, 2.0f, 0.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.right_arm, 10.0f, -2.0f, 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.left_leg, 10.0f, Maths.rad(90.0), 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.right_leg, 10.0f, Maths.rad(90.0), 0.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.left_leg, 10.0f, 0.0f, -4.0f, 4.0f, 10.0f);
        this.progressPositionPrev(this.right_leg, 10.0f, 0.0f, -4.0f, 4.0f, 10.0f);
    }

    public void setRotationAngle(AdvancedModelBox box, float x, float y, float z) {
        box.rotateAngleX = x;
        box.rotateAngleY = y;
        box.rotateAngleZ = z;
    }
}
