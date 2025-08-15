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

import com.github.alexthe666.alexsmobs.entity.EntityFroststalker;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelFroststalker
extends AdvancedEntityModel<EntityFroststalker> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox tail1;
    private final AdvancedModelBox tail2;
    private final AdvancedModelBox icespikesleft;
    private final AdvancedModelBox icespikesright;
    private final AdvancedModelBox neck;
    private final AdvancedModelBox head;
    private final AdvancedModelBox horn;
    private final AdvancedModelBox jaw;
    private final AdvancedModelBox armleft;
    private final AdvancedModelBox armright;
    private final AdvancedModelBox legleft;
    private final AdvancedModelBox legright;
    private ModelAnimator animator;

    public ModelFroststalker() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -11.0f, 2.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-3.0f, -4.0f, -10.0f, 6.0f, 7.0f, 16.0f, 0.0f, false);
        this.tail1 = new AdvancedModelBox((AdvancedEntityModel)this, "tail1");
        this.tail1.setRotationPoint(0.0f, -1.0f, 5.0f);
        this.body.addChild((BasicModelPart)this.tail1);
        this.tail1.setTextureOffset(34, 24).addBox(-2.0f, -3.0f, 1.0f, 4.0f, 5.0f, 9.0f, 0.0f, false);
        this.tail1.setTextureOffset(0, 41).addBox(-1.0f, -5.0f, 1.0f, 2.0f, 2.0f, 9.0f, 0.0f, false);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this, "tail2");
        this.tail2.setRotationPoint(0.0f, -2.0f, 9.0f);
        this.tail1.addChild((BasicModelPart)this.tail2);
        this.tail2.setTextureOffset(29, 0).addBox(-1.0f, -1.0f, 1.0f, 2.0f, 3.0f, 11.0f, 0.0f, false);
        this.tail2.setTextureOffset(21, 29).addBox(0.0f, -7.0f, 1.0f, 0.0f, 8.0f, 12.0f, 0.0f, false);
        this.icespikesleft = new AdvancedModelBox((AdvancedEntityModel)this, "icespikesleft");
        this.icespikesleft.setRotationPoint(0.0f, -4.0f, 0.5f);
        this.body.addChild((BasicModelPart)this.icespikesleft);
        this.setRotationAngle(this.icespikesleft, 0.0f, -0.3927f, 0.0f);
        this.icespikesleft.setTextureOffset(35, 39).addBox(0.0f, -6.0f, -5.5f, 0.0f, 6.0f, 11.0f, 0.0f, false);
        this.icespikesright = new AdvancedModelBox((AdvancedEntityModel)this, "icespikesright");
        this.icespikesright.setRotationPoint(0.0f, -4.0f, 0.5f);
        this.body.addChild((BasicModelPart)this.icespikesright);
        this.setRotationAngle(this.icespikesright, 0.0f, 0.3927f, 0.0f);
        this.icespikesright.setTextureOffset(35, 39).addBox(0.0f, -6.0f, -5.5f, 0.0f, 6.0f, 11.0f, 0.0f, true);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setRotationPoint(0.0f, -2.0f, -8.0f);
        this.body.addChild((BasicModelPart)this.neck);
        this.neck.setTextureOffset(52, 18).addBox(-1.5f, -4.0f, -3.0f, 3.0f, 6.0f, 5.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -4.0f, -1.0f);
        this.neck.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(44, 62).addBox(-2.0f, -4.0f, -2.0f, 4.0f, 4.0f, 5.0f, 0.0f, false);
        this.head.setTextureOffset(5, 29).addBox(-2.0f, -4.0f, -9.0f, 4.0f, 3.0f, 7.0f, 0.0f, false);
        this.head.setTextureOffset(17, 50).addBox(-1.0f, -6.0f, 0.0f, 2.0f, 6.0f, 6.0f, 0.0f, false);
        this.head.setTextureOffset(47, 39).addBox(-2.0f, -1.0f, -9.0f, 4.0f, 2.0f, 7.0f, 0.0f, false);
        this.horn = new AdvancedModelBox((AdvancedEntityModel)this, "horn");
        this.horn.setRotationPoint(0.0f, -3.0f, -3.0f);
        this.head.addChild((BasicModelPart)this.horn);
        this.setRotationAngle(this.horn, -0.3927f, 0.0f, 0.0f);
        this.horn.setTextureOffset(47, 6).addBox(-1.0f, -2.0f, -8.0f, 2.0f, 2.0f, 9.0f, 0.0f, false);
        this.jaw = new AdvancedModelBox((AdvancedEntityModel)this, "jaw");
        this.jaw.setRotationPoint(0.0f, -1.0f, -2.0f);
        this.head.addChild((BasicModelPart)this.jaw);
        this.jaw.setTextureOffset(40, 79).addBox(-1.5f, 0.0f, -7.0f, 3.0f, 1.0f, 7.0f, 0.0f, false);
        this.armleft = new AdvancedModelBox((AdvancedEntityModel)this, "armleft");
        this.armleft.setRotationPoint(3.0f, 2.0f, -7.5f);
        this.body.addChild((BasicModelPart)this.armleft);
        this.armleft.setTextureOffset(0, 53).addBox(-1.0f, -1.0f, -1.5f, 2.0f, 10.0f, 3.0f, 0.0f, false);
        this.armleft.setTextureOffset(0, 24).addBox(-3.0f, 7.0f, -1.5f, 2.0f, 2.0f, 2.0f, 0.0f, false);
        this.armright = new AdvancedModelBox((AdvancedEntityModel)this, "armright");
        this.armright.setRotationPoint(-3.0f, 2.0f, -7.5f);
        this.body.addChild((BasicModelPart)this.armright);
        this.armright.setTextureOffset(0, 53).addBox(-1.0f, -1.0f, -1.5f, 2.0f, 10.0f, 3.0f, 0.0f, true);
        this.armright.setTextureOffset(0, 24).addBox(1.0f, 7.0f, -1.5f, 2.0f, 2.0f, 2.0f, 0.0f, true);
        this.legleft = new AdvancedModelBox((AdvancedEntityModel)this, "legleft");
        this.legleft.setRotationPoint(1.5f, 2.0f, 3.9f);
        this.body.addChild((BasicModelPart)this.legleft);
        this.legleft.setTextureOffset(0, 0).addBox(-1.0f, -2.0f, -2.0f, 3.0f, 11.0f, 4.0f, 0.0f, false);
        this.legright = new AdvancedModelBox((AdvancedEntityModel)this, "legright");
        this.legright.setRotationPoint(-1.5f, 2.0f, 3.9f);
        this.body.addChild((BasicModelPart)this.legright);
        this.legright.setTextureOffset(0, 0).addBox(-2.0f, -2.0f, -2.0f, 3.0f, 11.0f, 4.0f, 0.0f, true);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.5f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            this.horn.showModel = false;
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.0);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
        } else {
            this.horn.showModel = true;
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.animator.update(entity);
        this.animator.setAnimation(EntityFroststalker.ANIMATION_BITE);
        this.animator.startKeyframe(4);
        this.animator.rotate(this.neck, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(40.0), 0.0f, 0.0f);
        this.animator.rotate(this.jaw, Maths.rad(40.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.neck, Maths.rad(50.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-50.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityFroststalker.ANIMATION_SPEAK);
        this.animator.startKeyframe(3);
        this.animator.rotate(this.head, Maths.rad(5.0), 0.0f, 0.0f);
        this.animator.rotate(this.jaw, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.resetKeyframe(3);
        this.animator.setAnimation(EntityFroststalker.ANIMATION_SLASH_L);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.body, Maths.rad(-15.0), Maths.rad(-15.0), 0.0f);
        this.animator.rotate(this.neck, Maths.rad(15.0), Maths.rad(15.0), 0.0f);
        this.animator.rotate(this.legright, Maths.rad(15.0), Maths.rad(15.0), 0.0f);
        this.animator.rotate(this.legleft, Maths.rad(15.0), Maths.rad(15.0), 0.0f);
        this.animator.rotate(this.armleft, Maths.rad(-50.0), 0.0f, Maths.rad(-105.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.neck, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(5.0), 0.0f, 0.0f);
        this.animator.move(this.armleft, 0.0f, 0.0f, -2.0f);
        this.animator.rotate(this.armleft, Maths.rad(-90.0), 0.0f, Maths.rad(15.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityFroststalker.ANIMATION_SLASH_R);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.body, Maths.rad(-15.0), Maths.rad(15.0), 0.0f);
        this.animator.rotate(this.neck, Maths.rad(15.0), Maths.rad(-15.0), 0.0f);
        this.animator.rotate(this.legright, Maths.rad(15.0), Maths.rad(-15.0), 0.0f);
        this.animator.rotate(this.legleft, Maths.rad(15.0), Maths.rad(-15.0), 0.0f);
        this.animator.rotate(this.armright, Maths.rad(-50.0), 0.0f, Maths.rad(105.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.neck, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(5.0), 0.0f, 0.0f);
        this.animator.move(this.armright, 0.0f, 0.0f, -2.0f);
        this.animator.rotate(this.armright, Maths.rad(-90.0), 0.0f, Maths.rad(-15.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityFroststalker.ANIMATION_SHOVE);
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, 4.0f);
        this.animator.move(this.legright, 0.0f, 0.0f, -1.0f);
        this.animator.move(this.legleft, 0.0f, 0.0f, -1.0f);
        this.animator.rotate(this.legright, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.legleft, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck, Maths.rad(35.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.move(this.body, 0.0f, 0.0f, -7.0f);
        this.animator.rotate(this.legright, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.legleft, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.tail1, (Object)this.tail2, (Object)this.icespikesleft, (Object)this.icespikesright, (Object)this.neck, (Object)this.head, (Object)this.horn, (Object)this.jaw, (Object)this.armleft, (Object)this.armright, (Object[])new AdvancedModelBox[]{this.legleft, this.legright});
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setupAnim(EntityFroststalker entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 0.7f;
        float walkDegree = 0.4f;
        float spikeSpeed = 0.9f;
        float spikeDegree = 0.4f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.4f;
        float partialTick = ageInTicks - (float)entityIn.f_19797_;
        float turnAngle = entityIn.prevTurnAngle + (entityIn.getTurnAngle() - entityIn.prevTurnAngle) * partialTick;
        float bipedProgress = entityIn.prevBipedProgress + (entityIn.bipedProgress - entityIn.prevBipedProgress) * partialTick;
        float quadProgress = 5.0f - bipedProgress;
        float tackleProgress = entityIn.prevTackleProgress + (entityIn.tackleProgress - entityIn.prevTackleProgress) * partialTick;
        float spikeProgress = entityIn.prevSpikeShakeProgress + (entityIn.spikeShakeProgress - entityIn.prevSpikeShakeProgress) * partialTick;
        AdvancedModelBox[] tailBoxes = new AdvancedModelBox[]{this.tail1, this.tail2};
        this.chainSwing(tailBoxes, idleSpeed, idleDegree * 0.3f, -2.0, ageInTicks, 1.0f);
        this.walk(this.neck, idleSpeed * 0.4f, idleDegree * 0.2f, false, 1.0f, -0.01f, ageInTicks, 1.0f);
        this.walk(this.head, idleSpeed * 0.4f, idleDegree * 0.2f, true, 1.0f, -0.01f, ageInTicks, 1.0f);
        this.chainSwing(tailBoxes, walkSpeed, walkDegree, -3.0, limbSwing, limbSwingAmount);
        this.walk(this.body, walkSpeed, walkDegree * 0.1f, false, -1.0f, 0.0f, limbSwing, limbSwingAmount * bipedProgress * 0.2f);
        this.walk(this.legleft, walkSpeed, walkDegree * 1.85f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.legright, walkSpeed, walkDegree * 1.85f, true, 3.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.armleft, walkSpeed, walkDegree * 1.85f, true, 2.0f, 0.0f, limbSwing, limbSwingAmount * quadProgress * 0.2f);
        this.walk(this.armright, walkSpeed, walkDegree * 1.85f, false, 2.0f, 0.0f, limbSwing, limbSwingAmount * quadProgress * 0.2f);
        this.bob(this.body, walkSpeed * 0.5f, walkDegree * 4.0f, true, limbSwing, limbSwingAmount * bipedProgress * 0.2f);
        this.progressRotationPrev(this.armright, bipedProgress, Maths.rad(20.0), Maths.rad(10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.armleft, bipedProgress, Maths.rad(20.0), Maths.rad(-10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.neck, bipedProgress, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, bipedProgress, Maths.rad(-30.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.neck, bipedProgress, 0.0f, 0.0f, -1.0f, 5.0f);
        this.progressPositionPrev(this.head, bipedProgress, 0.0f, 0.0f, -1.0f, 5.0f);
        this.progressPositionPrev(this.tail1, quadProgress, 0.0f, 0.0f, -1.0f, 5.0f);
        this.progressRotationPrev(this.tail1, quadProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail2, quadProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.flap(this.neck, walkSpeed, walkDegree * 0.5f, false, 4.0f, 0.0f, limbSwing, limbSwingAmount * quadProgress * 0.2f);
        this.flap(this.head, walkSpeed, walkDegree * 0.5f, true, 4.0f, 0.0f, limbSwing, limbSwingAmount * quadProgress * 0.2f);
        this.walk(this.armleft, walkSpeed, walkDegree * 0.5f, true, 3.0f, 0.12f, limbSwing, limbSwingAmount * bipedProgress * 0.2f);
        this.walk(this.armright, walkSpeed, walkDegree * 0.5f, true, 3.0f, 0.12f, limbSwing, limbSwingAmount * bipedProgress * 0.2f);
        this.walk(this.neck, walkSpeed, walkDegree * 0.5f, false, 2.0f, -0.1f, limbSwing, limbSwingAmount * bipedProgress * 0.2f);
        this.walk(this.head, walkSpeed, walkDegree * 0.5f, true, 2.0f, -0.1f, limbSwing, limbSwingAmount * bipedProgress * 0.2f);
        this.armleft.rotationPointY -= bipedProgress * 0.5f;
        this.armright.rotationPointY -= bipedProgress * 0.5f;
        float yawAmount = turnAngle / 57.295776f * 0.5f * bipedProgress * 0.2f * limbSwingAmount;
        this.head.rotateAngleY -= yawAmount * 0.5f;
        this.neck.rotateAngleY -= yawAmount;
        this.body.rotateAngleZ += yawAmount;
        this.legleft.rotateAngleZ -= yawAmount;
        this.legright.rotationPointY += 1.5f * yawAmount;
        this.legleft.rotationPointY -= 1.5f * yawAmount;
        this.legright.rotateAngleZ -= yawAmount;
        this.progressRotationPrev(this.body, tackleProgress, Maths.rad(-45.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.neck, tackleProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, tackleProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.armright, tackleProgress, Maths.rad(-50.0), 0.0f, Maths.rad(40.0), 5.0f);
        this.progressRotationPrev(this.armleft, tackleProgress, Maths.rad(-50.0), 0.0f, Maths.rad(-40.0), 5.0f);
        this.progressRotationPrev(this.legright, tackleProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legleft, tackleProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail1, tackleProgress, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.jaw, tackleProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.chainSwing(tailBoxes, spikeSpeed, spikeDegree, 0.0, ageInTicks, spikeProgress * 0.2f);
        this.flap(this.body, spikeSpeed, spikeDegree, false, 0.0f, 0.0f, ageInTicks, spikeProgress * 0.2f);
        this.flap(this.neck, spikeSpeed, spikeDegree, false, 1.0f, 0.0f, ageInTicks, spikeProgress * 0.2f);
        this.flap(this.head, spikeSpeed, spikeDegree, true, 2.0f, 0.0f, ageInTicks, spikeProgress * 0.2f);
        this.swing(this.body, spikeSpeed, spikeDegree * 0.5f, false, 1.0f, 0.0f, ageInTicks, spikeProgress * 0.2f);
        this.flap(this.legleft, spikeSpeed, spikeDegree, true, 0.0f, 0.0f, ageInTicks, spikeProgress * 0.2f);
        this.flap(this.legright, spikeSpeed, spikeDegree, true, 0.0f, 0.0f, ageInTicks, spikeProgress * 0.2f);
        this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.head, this.neck});
    }
}
