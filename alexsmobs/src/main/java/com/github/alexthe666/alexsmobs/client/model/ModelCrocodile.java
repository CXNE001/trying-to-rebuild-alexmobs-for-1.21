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

import com.github.alexthe666.alexsmobs.entity.EntityCrocodile;
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

public class ModelCrocodile
extends AdvancedEntityModel<EntityCrocodile> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox left_foot;
    private final AdvancedModelBox right_leg;
    private final AdvancedModelBox right_foot;
    private final AdvancedModelBox left_arm;
    private final AdvancedModelBox left_hand;
    private final AdvancedModelBox right_arm;
    private final AdvancedModelBox right_hand;
    private final AdvancedModelBox tail1;
    private final AdvancedModelBox tail2;
    private final AdvancedModelBox tail3;
    private final AdvancedModelBox neck;
    private final AdvancedModelBox head;
    private final AdvancedModelBox crown;
    private final AdvancedModelBox left_upperteeth;
    private final AdvancedModelBox right_upperteeth;
    private final AdvancedModelBox jaw;
    private final AdvancedModelBox left_lowerteeth;
    private final AdvancedModelBox right_lowerteeth;
    private final ModelAnimator animator;

    public ModelCrocodile() {
        this.texWidth = 256;
        this.texHeight = 256;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -9.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-8.0f, -7.0f, -13.0f, 16.0f, 12.0f, 27.0f, 0.0f, false);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setRotationPoint(8.0f, 3.0f, 10.0f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(0, 0).addBox(-2.0f, -2.0f, -5.0f, 5.0f, 8.0f, 8.0f, 0.0f, false);
        this.left_foot = new AdvancedModelBox((AdvancedEntityModel)this, "left_foot");
        this.left_foot.setRotationPoint(2.0f, 6.0f, -3.0f);
        this.left_leg.addChild((BasicModelPart)this.left_foot);
        this.left_foot.setTextureOffset(45, 42).addBox(-2.0f, -0.01f, -5.0f, 5.0f, 0.0f, 6.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setRotationPoint(-8.0f, 3.0f, 10.0f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(0, 0).addBox(-3.0f, -2.0f, -5.0f, 5.0f, 8.0f, 8.0f, 0.0f, true);
        this.right_foot = new AdvancedModelBox((AdvancedEntityModel)this, "right_foot");
        this.right_foot.setRotationPoint(-2.0f, 6.0f, -3.0f);
        this.right_leg.addChild((BasicModelPart)this.right_foot);
        this.right_foot.setTextureOffset(45, 42).addBox(-3.0f, -0.01f, -5.0f, 5.0f, 0.0f, 6.0f, 0.0f, true);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setRotationPoint(9.0f, 1.0f, -9.0f);
        this.body.addChild((BasicModelPart)this.left_arm);
        this.left_arm.setTextureOffset(0, 40).addBox(-2.0f, -2.0f, -2.0f, 4.0f, 10.0f, 4.0f, 0.0f, false);
        this.left_hand = new AdvancedModelBox((AdvancedEntityModel)this, "left_hand");
        this.left_hand.setRotationPoint(0.0f, 8.0f, 1.0f);
        this.left_arm.addChild((BasicModelPart)this.left_hand);
        this.left_hand.setTextureOffset(0, 17).addBox(-2.0f, -0.01f, -7.0f, 6.0f, 0.0f, 7.0f, 0.0f, false);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setRotationPoint(-9.0f, 1.0f, -9.0f);
        this.body.addChild((BasicModelPart)this.right_arm);
        this.right_arm.setTextureOffset(0, 40).addBox(-2.0f, -2.0f, -2.0f, 4.0f, 10.0f, 4.0f, 0.0f, true);
        this.right_hand = new AdvancedModelBox((AdvancedEntityModel)this, "right_hand");
        this.right_hand.setRotationPoint(0.0f, 8.0f, 1.0f);
        this.right_arm.addChild((BasicModelPart)this.right_hand);
        this.right_hand.setTextureOffset(0, 17).addBox(-4.0f, -0.01f, -7.0f, 6.0f, 0.0f, 7.0f, 0.0f, true);
        this.tail1 = new AdvancedModelBox((AdvancedEntityModel)this, "tail1");
        this.tail1.setRotationPoint(0.0f, 0.0f, 16.0f);
        this.body.addChild((BasicModelPart)this.tail1);
        this.tail1.setTextureOffset(0, 40).addBox(-5.0f, -5.0f, -2.0f, 10.0f, 10.0f, 24.0f, 0.0f, false);
        this.tail1.setTextureOffset(45, 51).addBox(-5.0f, -7.0f, -2.0f, 10.0f, 2.0f, 24.0f, 0.0f, false);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this, "tail2");
        this.tail2.setRotationPoint(0.0f, 1.0f, 24.0f);
        this.tail1.addChild((BasicModelPart)this.tail2);
        this.tail2.setTextureOffset(62, 15).addBox(-3.0f, -3.0f, -2.0f, 6.0f, 7.0f, 25.0f, 0.0f, false);
        this.tail2.setTextureOffset(43, 78).addBox(-2.0f, -5.0f, -2.0f, 4.0f, 2.0f, 20.0f, 0.0f, false);
        this.tail3 = new AdvancedModelBox((AdvancedEntityModel)this, "tail3");
        this.tail3.setRotationPoint(0.0f, 0.0f, 18.0f);
        this.tail2.addChild((BasicModelPart)this.tail3);
        this.tail3.setTextureOffset(0, 75).addBox(0.0f, -6.0f, 0.0f, 0.0f, 10.0f, 21.0f, 0.0f, false);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setRotationPoint(0.0f, 0.0f, -15.0f);
        this.body.addChild((BasicModelPart)this.neck);
        this.neck.setTextureOffset(80, 89).addBox(-6.0f, -5.0f, -10.0f, 12.0f, 10.0f, 12.0f, 0.0f, false);
        this.neck.setTextureOffset(60, 0).addBox(-4.0f, -6.0f, -10.0f, 8.0f, 1.0f, 12.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, 1.0f, -11.0f);
        this.neck.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(72, 78).addBox(-5.0f, -4.0f, -5.0f, 10.0f, 4.0f, 6.0f, 0.0f, false);
        this.head.setTextureOffset(60, 14).addBox(-4.0f, -5.0f, -5.0f, 8.0f, 1.0f, 5.0f, 0.0f, false);
        this.head.setTextureOffset(22, 78).addBox(-3.0f, -4.0f, -17.0f, 6.0f, 4.0f, 12.0f, 0.0f, false);
        this.crown = new AdvancedModelBox((AdvancedEntityModel)this, "crown");
        this.crown.setRotationPoint(0.0f, -5.0f, -2.0f);
        this.head.addChild((BasicModelPart)this.crown);
        this.crown.setTextureOffset(49, 54).addBox(-1.5f, -5.0f, -2.0f, 3.0f, 5.0f, 3.0f, 0.0f, false);
        this.left_upperteeth = new AdvancedModelBox((AdvancedEntityModel)this, "left_upperteeth");
        this.left_upperteeth.setRotationPoint(0.0f, 0.0f, -17.0f);
        this.head.addChild((BasicModelPart)this.left_upperteeth);
        this.setRotationAngle(this.left_upperteeth, 0.0f, 0.0f, -0.0873f);
        this.left_upperteeth.setTextureOffset(104, 23).addBox(0.0f, 0.0f, -0.025f, 3.0f, 2.0f, 11.0f, 0.0f, false);
        this.right_upperteeth = new AdvancedModelBox((AdvancedEntityModel)this, "right_upperteeth");
        this.right_upperteeth.setRotationPoint(0.0f, 0.0f, -17.0f);
        this.head.addChild((BasicModelPart)this.right_upperteeth);
        this.setRotationAngle(this.right_upperteeth, 0.0f, 0.0f, 0.0873f);
        this.right_upperteeth.setTextureOffset(104, 23).addBox(-3.0f, 0.0f, -0.025f, 3.0f, 2.0f, 11.0f, 0.0f, true);
        this.jaw = new AdvancedModelBox((AdvancedEntityModel)this, "jaw");
        this.jaw.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.addChild((BasicModelPart)this.jaw);
        this.jaw.setTextureOffset(100, 7).addBox(-5.5f, -2.0f, -6.0f, 11.0f, 5.0f, 7.0f, 0.001f, false);
        this.jaw.setTextureOffset(90, 48).addBox(-3.0f, 0.0f, -17.0f, 6.0f, 3.0f, 11.0f, 0.0f, false);
        this.left_lowerteeth = new AdvancedModelBox((AdvancedEntityModel)this, "left_lowerteeth");
        this.left_lowerteeth.setRotationPoint(0.0f, 0.0f, -17.0f);
        this.jaw.addChild((BasicModelPart)this.left_lowerteeth);
        this.setRotationAngle(this.left_lowerteeth, 0.0f, 0.0f, 0.0873f);
        this.left_lowerteeth.setTextureOffset(105, 67).addBox(0.0f, -2.0f, -0.025f, 3.0f, 2.0f, 11.0f, 0.0f, false);
        this.right_lowerteeth = new AdvancedModelBox((AdvancedEntityModel)this, "right_lowerteeth");
        this.right_lowerteeth.setRotationPoint(0.0f, 0.0f, -17.0f);
        this.jaw.addChild((BasicModelPart)this.right_lowerteeth);
        this.setRotationAngle(this.right_lowerteeth, 0.0f, 0.0f, -0.0873f);
        this.right_lowerteeth.setTextureOffset(105, 67).addBox(-3.0f, -2.0f, -0.025f, 3.0f, 2.0f, 11.0f, 0.0f, true);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityCrocodile.ANIMATION_LUNGE);
        this.animator.startKeyframe(2);
        this.animator.move(this.body, 0.0f, 0.0f, 2.0f);
        this.animator.rotate(this.head, Maths.rad(-55.0), 0.0f, 0.0f);
        this.animator.rotate(this.jaw, Maths.rad(60.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(1);
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, -14.0f);
        this.animator.rotate(this.head, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.rotate(this.jaw, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_arm, Maths.rad(45.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_arm, Maths.rad(45.0), 0.0f, 0.0f);
        this.animator.rotate(this.right_leg, Maths.rad(45.0), 0.0f, 0.0f);
        this.animator.rotate(this.left_leg, Maths.rad(45.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, 3.0f);
        this.animator.rotate(this.head, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.jaw, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(10);
        this.animator.setAnimation(EntityCrocodile.ANIMATION_DEATHROLL);
        this.animator.startKeyframe(30);
        int rolls = 3;
        this.animator.rotate(this.body, 0.0f, 0.0f, Maths.rad(-360 * rolls));
        this.animator.endKeyframe();
    }

    public void setupAnim(EntityCrocodile entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        boolean swimAnimate = entityIn.m_20069_();
        float walkSpeed = 0.7f;
        float walkDegree = 0.7f;
        float swimSpeed = 1.0f;
        float swimDegree = 0.2f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float swimProgress = entityIn.prevSwimProgress + (entityIn.swimProgress - entityIn.prevSwimProgress) * partialTick;
        float baskProgress = entityIn.prevBaskingProgress + (entityIn.baskingProgress - entityIn.prevBaskingProgress) * partialTick;
        float grabProgress = entityIn.prevGrabProgress + (entityIn.grabProgress - entityIn.prevGrabProgress) * partialTick;
        if (!swimAnimate && grabProgress <= 0.0f) {
            this.faceTarget(netHeadYaw, headPitch, 2.0f, new AdvancedModelBox[]{this.neck, this.head});
        }
        this.progressRotationPrev(this.jaw, grabProgress, Maths.rad(30.0), 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.head, grabProgress, Maths.rad(-10.0), 0.0f, 0.0f, 10.0f);
        if (entityIn.baskingType == 0) {
            this.progressRotationPrev(this.body, baskProgress, 0.0f, Maths.rad(-7.0), 0.0f, 10.0f);
            this.progressRotationPrev(this.tail1, baskProgress, 0.0f, Maths.rad(30.0), 0.0f, 10.0f);
            this.progressRotationPrev(this.tail2, baskProgress, 0.0f, Maths.rad(20.0), 0.0f, 10.0f);
            this.progressRotationPrev(this.tail3, baskProgress, 0.0f, Maths.rad(30.0), 0.0f, 10.0f);
            this.progressRotationPrev(this.neck, baskProgress, 0.0f, Maths.rad(-10.0), 0.0f, 10.0f);
            this.progressRotationPrev(this.head, baskProgress, Maths.rad(-60.0), Maths.rad(-10.0), 0.0f, 10.0f);
            this.progressRotationPrev(this.jaw, baskProgress, Maths.rad(60.0), 0.0f, 0.0f, 10.0f);
        } else if (entityIn.baskingType == 1) {
            this.progressRotationPrev(this.body, baskProgress, 0.0f, Maths.rad(7.0), 0.0f, 10.0f);
            this.progressRotationPrev(this.tail1, baskProgress, 0.0f, Maths.rad(-30.0), 0.0f, 10.0f);
            this.progressRotationPrev(this.tail2, baskProgress, 0.0f, Maths.rad(-20.0), 0.0f, 10.0f);
            this.progressRotationPrev(this.tail3, baskProgress, 0.0f, Maths.rad(-30.0), 0.0f, 10.0f);
            this.progressRotationPrev(this.neck, baskProgress, 0.0f, Maths.rad(10.0), 0.0f, 10.0f);
            this.progressRotationPrev(this.head, baskProgress, Maths.rad(-60.0), Maths.rad(10.0), 0.0f, 10.0f);
            this.progressRotationPrev(this.jaw, baskProgress, Maths.rad(60.0), 0.0f, 0.0f, 10.0f);
        }
        this.progressPositionPrev(this.body, baskProgress, 0.0f, 3.0f, -3.0f, 10.0f);
        this.progressPositionPrev(this.tail1, baskProgress, 0.0f, 0.0f, -3.0f, 10.0f);
        this.progressPositionPrev(this.right_leg, baskProgress, 0.0f, -3.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.left_leg, baskProgress, 0.0f, -3.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.right_arm, baskProgress, 0.0f, -3.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.left_arm, baskProgress, 0.0f, -3.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.right_arm, swimProgress, 0.0f, 2.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.left_arm, baskProgress, 0.0f, 0.0f, Maths.rad(-30.0), 10.0f);
        this.progressRotationPrev(this.left_hand, baskProgress, 0.0f, 0.0f, Maths.rad(30.0), 10.0f);
        this.progressRotationPrev(this.right_arm, baskProgress, 0.0f, 0.0f, Maths.rad(30.0), 10.0f);
        this.progressRotationPrev(this.right_hand, baskProgress, 0.0f, 0.0f, Maths.rad(-30.0), 10.0f);
        this.progressRotationPrev(this.left_leg, baskProgress, 0.0f, 0.0f, Maths.rad(-30.0), 10.0f);
        this.progressRotationPrev(this.left_foot, baskProgress, 0.0f, 0.0f, Maths.rad(30.0), 10.0f);
        this.progressRotationPrev(this.right_leg, baskProgress, 0.0f, 0.0f, Maths.rad(30.0), 10.0f);
        this.progressRotationPrev(this.right_foot, baskProgress, 0.0f, 0.0f, Maths.rad(-30.0), 10.0f);
        this.progressRotationPrev(this.right_arm, swimProgress, Maths.rad(75.0), 0.0f, Maths.rad(90.0), 10.0f);
        this.progressPositionPrev(this.left_arm, swimProgress, 0.0f, 2.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.left_arm, swimProgress, Maths.rad(75.0), 0.0f, Maths.rad(-90.0), 10.0f);
        this.progressPositionPrev(this.right_leg, swimProgress, 0.0f, 2.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.right_leg, swimProgress, Maths.rad(75.0), 0.0f, Maths.rad(90.0), 10.0f);
        this.progressPositionPrev(this.left_leg, swimProgress, 0.0f, 2.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.left_leg, swimProgress, Maths.rad(75.0), 0.0f, Maths.rad(-90.0), 10.0f);
        this.progressPositionPrev(this.left_foot, swimProgress, -2.0f, 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.left_foot, swimProgress, Maths.rad(75.0), 0.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.right_foot, swimProgress, 2.0f, 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.right_foot, swimProgress, Maths.rad(75.0), 0.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.left_hand, swimProgress, -1.0f, 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.left_hand, swimProgress, Maths.rad(75.0), 0.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.right_hand, swimProgress, 1.0f, 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.right_hand, swimProgress, Maths.rad(75.0), 0.0f, 0.0f, 10.0f);
        AdvancedModelBox[] tailBoxes = new AdvancedModelBox[]{this.tail1, this.tail2, this.tail3};
        if (swimAnimate) {
            this.walk(this.right_arm, swimSpeed, swimDegree, false, 0.0f, -0.25f, limbSwing, limbSwingAmount);
            this.walk(this.left_arm, swimSpeed, swimDegree, false, 0.0f, -0.25f, limbSwing, limbSwingAmount);
            this.walk(this.right_leg, swimSpeed, swimDegree, true, 0.0f, 0.25f, limbSwing, limbSwingAmount);
            this.walk(this.left_leg, swimSpeed, swimDegree, true, 0.0f, 0.25f, limbSwing, limbSwingAmount);
            this.swing(this.body, swimSpeed, swimDegree * 0.7f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.neck, swimSpeed, swimDegree * 0.5f, true, 2.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.head, swimSpeed, swimDegree * 0.3f, true, 2.0f, 0.0f, limbSwing, limbSwingAmount);
            this.chainSwing(tailBoxes, swimSpeed, swimDegree * 2.0f, -2.5, limbSwing, limbSwingAmount);
        } else {
            this.walk(this.right_arm, walkSpeed, walkDegree, false, 0.0f, 0.25f, limbSwing, limbSwingAmount);
            this.walk(this.left_arm, walkSpeed, walkDegree, true, 0.0f, -0.25f, limbSwing, limbSwingAmount);
            this.walk(this.right_leg, walkSpeed, walkDegree, true, 0.0f, 0.25f, limbSwing, limbSwingAmount);
            this.walk(this.left_leg, walkSpeed, walkDegree, false, 0.0f, -0.25f, limbSwing, limbSwingAmount);
            this.swing(this.body, walkSpeed, walkDegree * 0.1f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.neck, walkSpeed, walkDegree * 0.1f, false, 2.0f, 0.0f, limbSwing, limbSwingAmount);
            this.chainSwing(tailBoxes, walkSpeed, walkDegree * 0.3f, -2.5, limbSwing, limbSwingAmount);
        }
        if (baskProgress > 0.0f) {
            this.walk(this.head, 0.1f, 0.1f, false, 1.0f, 0.1f, ageInTicks, 1.0f);
            this.jaw.rotateAngleX = -this.head.rotateAngleX;
        }
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.neck, (Object)this.head, (Object)this.jaw, (Object)this.left_arm, (Object)this.right_arm, (Object)this.left_leg, (Object)this.right_leg, (Object)this.tail1, (Object)this.tail2, (Object)this.tail3, (Object[])new AdvancedModelBox[]{this.crown, this.left_foot, this.right_foot, this.left_hand, this.right_hand, this.left_upperteeth, this.right_upperteeth, this.left_lowerteeth, this.right_lowerteeth});
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.5f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.15f, 0.15f, 0.15f);
            matrixStackIn.m_85837_(0.0, 8.5, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z) {
        advancedModelBox.rotateAngleX = x;
        advancedModelBox.rotateAngleY = y;
        advancedModelBox.rotateAngleZ = z;
    }
}
