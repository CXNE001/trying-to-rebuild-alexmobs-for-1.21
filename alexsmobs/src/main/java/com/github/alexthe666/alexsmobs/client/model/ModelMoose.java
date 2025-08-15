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

import com.github.alexthe666.alexsmobs.entity.EntityMoose;
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

public class ModelMoose
extends AdvancedEntityModel<EntityMoose> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox left_arm;
    private final AdvancedModelBox right_arm;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox right_leg;
    private final AdvancedModelBox upper_body;
    private final AdvancedModelBox neck;
    private final AdvancedModelBox head;
    private final AdvancedModelBox left_ear;
    private final AdvancedModelBox right_ear;
    private final AdvancedModelBox beard;
    private final ModelAnimator animator;

    public ModelMoose() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -28.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(37, 80).addBox(-6.0f, -8.0f, -4.0f, 12.0f, 15.0f, 20.0f, 0.0f, false);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setPos(4.7f, 6.0f, -13.0f);
        this.body.addChild((BasicModelPart)this.left_arm);
        this.left_arm.setTextureOffset(19, 58).addBox(-2.0f, 1.0f, -2.0f, 4.0f, 21.0f, 4.0f, 0.0f, false);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setPos(-4.7f, 6.0f, -13.0f);
        this.body.addChild((BasicModelPart)this.right_arm);
        this.right_arm.setTextureOffset(19, 58).addBox(-2.0f, 1.0f, -2.0f, 4.0f, 21.0f, 4.0f, 0.0f, true);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setPos(3.7f, 6.0f, 14.0f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(0, 58).addBox(-2.0f, 1.0f, -3.0f, 4.0f, 21.0f, 5.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setPos(-3.7f, 6.0f, 14.0f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(0, 58).addBox(-2.0f, 1.0f, -3.0f, 4.0f, 21.0f, 5.0f, 0.0f, true);
        this.upper_body = new AdvancedModelBox((AdvancedEntityModel)this, "upper_body");
        this.upper_body.setPos(0.0f, -1.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.upper_body);
        this.upper_body.setTextureOffset(52, 45).addBox(-7.0f, -10.0f, -13.0f, 14.0f, 18.0f, 13.0f, 0.0f, false);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setPos(0.0f, -6.0f, -14.0f);
        this.upper_body.addChild((BasicModelPart)this.neck);
        this.neck.setTextureOffset(45, 0).addBox(-4.0f, -3.0f, -6.0f, 8.0f, 9.0f, 7.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, 0.0f, -7.0f);
        this.neck.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(51, 18).addBox(-3.0f, -3.0f, -15.0f, 6.0f, 7.0f, 16.0f, 0.0f, false);
        this.head.setTextureOffset(0, 34).addBox(3.0f, -12.0f, -7.0f, 18.0f, 9.0f, 14.0f, 0.0f, false);
        this.head.setTextureOffset(0, 34).addBox(-21.0f, -12.0f, -7.0f, 18.0f, 9.0f, 14.0f, 0.0f, true);
        this.left_ear = new AdvancedModelBox((AdvancedEntityModel)this, "left_ear");
        this.left_ear.setPos(1.3f, -3.0f, 0.5f);
        this.head.addChild((BasicModelPart)this.left_ear);
        this.setRotationAngle(this.left_ear, -0.3054f, -0.2618f, 0.3927f);
        this.left_ear.setTextureOffset(11, 0).addBox(-0.3f, -4.0f, -0.5f, 2.0f, 4.0f, 1.0f, 0.0f, false);
        this.right_ear = new AdvancedModelBox((AdvancedEntityModel)this, "right_ear");
        this.right_ear.setPos(-1.3f, -3.0f, 0.5f);
        this.head.addChild((BasicModelPart)this.right_ear);
        this.setRotationAngle(this.right_ear, -0.3054f, 0.2618f, -0.3927f);
        this.right_ear.setTextureOffset(11, 0).addBox(-1.7f, -4.0f, -0.5f, 2.0f, 4.0f, 1.0f, 0.0f, true);
        this.beard = new AdvancedModelBox((AdvancedEntityModel)this, "beard");
        this.beard.setPos(0.0f, 4.0f, 0.0f);
        this.head.addChild((BasicModelPart)this.beard);
        this.beard.setTextureOffset(0, 0).addBox(0.0f, 0.0f, -4.0f, 0.0f, 6.0f, 5.0f, 0.0f, false);
        this.animator = ModelAnimator.create();
        this.updateDefaultPose();
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.left_ear, (Object)this.right_ear, (Object)this.head, (Object)this.neck, (Object)this.body, (Object)this.upper_body, (Object)this.beard, (Object)this.left_leg, (Object)this.right_leg, (Object)this.left_arm, (Object)this.right_arm, (Object[])new AdvancedModelBox[0]);
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityMoose.ANIMATION_EAT_GRASS);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.neck, Maths.rad(50.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(4.0), 0.0f, 0.0f);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.neck, Maths.rad(70.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(10.0), 0.0f, 0.0f);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.neck, Maths.rad(50.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(0.0), 0.0f, 0.0f);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.neck, Maths.rad(70.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(10.0), 0.0f, 0.0f);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.neck, Maths.rad(50.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(0.0), 0.0f, 0.0f);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.neck, Maths.rad(70.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(10.0), 0.0f, 0.0f);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityMoose.ANIMATION_ATTACK);
        this.animator.startKeyframe(8);
        this.eatPose();
        this.animator.rotate(this.neck, Maths.rad(50.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.neck, Maths.rad(-34.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(4);
    }

    private void eatPose() {
        this.animator.rotate(this.body, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.move(this.body, 0.0f, 2.0f, 0.0f);
        this.animator.rotate(this.left_leg, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_leg, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(-10.0), 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.right_arm, Maths.rad(-10.0), 0.0f, Maths.rad(10.0));
        this.animator.move(this.left_arm, 0.1f, -3.0f, 0.0f);
        this.animator.move(this.right_arm, -0.1f, -3.0f, 0.0f);
        this.animator.move(this.left_leg, 0.0f, -0.2f, 0.0f);
        this.animator.move(this.right_leg, 0.0f, -0.2f, 0.0f);
        this.animator.move(this.neck, 0.0f, 1.0f, 0.0f);
    }

    public void setupAnim(EntityMoose entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 0.7f;
        float walkDegree = 0.6f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        float runProgress = 5.0f * limbSwingAmount;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float jostleProgress = entityIn.prevJostleProgress + (entityIn.jostleProgress - entityIn.prevJostleProgress) * partialTick;
        float jostleAngle = entityIn.prevJostleAngle + (entityIn.getJostleAngle() - entityIn.prevJostleAngle) * partialTick;
        this.flap(this.beard, idleSpeed, idleDegree * 4.0f, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.left_ear, idleSpeed, idleDegree, false, 1.0f, -0.2f, ageInTicks, 1.0f);
        this.flap(this.right_ear, idleSpeed, idleDegree, true, 1.0f, 0.2f, ageInTicks, 1.0f);
        this.walk(this.neck, idleSpeed, idleDegree, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.head, idleSpeed, -idleDegree, false, 0.5f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.body, walkSpeed, walkDegree * 0.05f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.walk(this.neck, walkSpeed, walkDegree * 0.25f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.head, walkSpeed, -walkDegree * 0.25f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.right_arm, walkSpeed, walkDegree * 1.1f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.right_arm, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.walk(this.left_arm, walkSpeed, walkDegree * 1.1f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.left_arm, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.walk(this.right_leg, walkSpeed, walkDegree * 1.1f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.right_leg, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.walk(this.left_leg, walkSpeed, walkDegree * 1.1f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.left_leg, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.progressRotationPrev(this.neck, jostleProgress, Maths.rad(7.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, jostleProgress, Maths.rad(80.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.neck, jostleProgress, 0.0f, 0.0f, 1.0f, 5.0f);
        this.progressPositionPrev(this.head, jostleProgress, 0.0f, 0.0f, -1.0f, 5.0f);
        if (jostleProgress > 0.0f) {
            float yawAmount = jostleAngle / 57.295776f * 0.5f * jostleProgress * 0.2f;
            this.neck.rotateAngleY += yawAmount;
            this.head.rotateAngleY += yawAmount;
            this.head.rotateAngleZ += yawAmount;
        } else {
            this.faceTarget(netHeadYaw, headPitch, 2.0f, new AdvancedModelBox[]{this.neck, this.head});
        }
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.35f;
            float feet = 1.45f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            this.right_arm.setScale(1.0f, feet, 1.0f);
            this.left_arm.setScale(1.0f, feet, 1.0f);
            this.right_leg.setScale(1.0f, feet, 1.0f);
            this.left_leg.setScale(1.0f, feet, 1.0f);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.35f, 0.35f, 0.35f);
            matrixStackIn.m_85837_(0.0, 2.25, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
            this.right_arm.setScale(1.0f, 1.0f, 1.0f);
            this.left_arm.setScale(1.0f, 1.0f, 1.0f);
            this.right_leg.setScale(1.0f, 1.0f, 1.0f);
            this.left_leg.setScale(1.0f, 1.0f, 1.0f);
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
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
