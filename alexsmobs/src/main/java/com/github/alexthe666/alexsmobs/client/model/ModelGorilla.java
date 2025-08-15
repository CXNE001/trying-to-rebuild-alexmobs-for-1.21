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

import com.github.alexthe666.alexsmobs.entity.EntityGorilla;
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

public class ModelGorilla
extends AdvancedEntityModel<EntityGorilla> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox chest;
    public final AdvancedModelBox head;
    public final AdvancedModelBox foreheadDK_r1;
    public final AdvancedModelBox mouth;
    public final AdvancedModelBox leftArm;
    public final AdvancedModelBox rightArm;
    public final AdvancedModelBox leftLeg;
    public final AdvancedModelBox rightLeg;
    public final ModelAnimator animator;

    public ModelGorilla() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -14.0f, 3.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 23).addBox(-4.5f, -4.0f, -3.0f, 9.0f, 9.0f, 7.0f, 0.0f, false);
        this.chest = new AdvancedModelBox((AdvancedEntityModel)this, "chest");
        this.chest.setRotationPoint(0.0f, 4.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.chest);
        this.chest.setTextureOffset(0, 0).addBox(-6.5f, -8.0f, -11.0f, 13.0f, 11.0f, 11.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -8.0f, -11.0f);
        this.chest.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(22, 48).addBox(-3.0f, -5.0f, -4.0f, 6.0f, 7.0f, 7.0f, 0.0f, false);
        this.head.setTextureOffset(38, 0).addBox(-3.0f, -6.0f, -4.0f, 6.0f, 1.0f, 7.0f, 0.0f, false);
        this.head.setTextureOffset(0, 40).addBox(-3.0f, -8.0f, -5.0f, 6.0f, 4.0f, 8.0f, 0.0f, false);
        this.foreheadDK_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "foreheadDK_r1");
        this.foreheadDK_r1.setRotationPoint(0.0f, -6.0f, -2.0f);
        this.head.addChild((BasicModelPart)this.foreheadDK_r1);
        this.setRotationAngle(this.foreheadDK_r1, 0.0f, 0.2182f, 0.0f);
        this.foreheadDK_r1.setTextureOffset(0, 56).addBox(0.0f, -6.0f, -4.0f, 0.0f, 6.0f, 10.0f, 0.0f, false);
        this.mouth = new AdvancedModelBox((AdvancedEntityModel)this, "mouth");
        this.mouth.setRotationPoint(0.0f, 0.0f, -1.0f);
        this.head.addChild((BasicModelPart)this.mouth);
        this.mouth.setTextureOffset(49, 9).addBox(-2.0f, -2.0f, -5.0f, 4.0f, 5.0f, 4.0f, 0.0f, false);
        this.leftArm = new AdvancedModelBox((AdvancedEntityModel)this, "leftArm");
        this.leftArm.setRotationPoint(6.0f, -6.5f, -9.5f);
        this.chest.addChild((BasicModelPart)this.leftArm);
        this.leftArm.setTextureOffset(33, 23).addBox(-3.0f, -2.5f, -2.5f, 6.0f, 19.0f, 5.0f, 0.0f, false);
        this.rightArm = new AdvancedModelBox((AdvancedEntityModel)this, "rightArm");
        this.rightArm.setRotationPoint(-6.0f, -6.5f, -9.5f);
        this.chest.addChild((BasicModelPart)this.rightArm);
        this.rightArm.setTextureOffset(33, 23).addBox(-3.0f, -2.5f, -2.5f, 6.0f, 19.0f, 5.0f, 0.0f, true);
        this.leftLeg = new AdvancedModelBox((AdvancedEntityModel)this, "leftLeg");
        this.leftLeg.setRotationPoint(3.0f, 4.5f, 2.5f);
        this.body.addChild((BasicModelPart)this.leftLeg);
        this.leftLeg.setTextureOffset(49, 48).addBox(-2.5f, 0.5f, -2.5f, 5.0f, 9.0f, 5.0f, 0.0f, false);
        this.rightLeg = new AdvancedModelBox((AdvancedEntityModel)this, "rightLeg");
        this.rightLeg.setRotationPoint(-3.0f, 4.5f, 2.5f);
        this.body.addChild((BasicModelPart)this.rightLeg);
        this.rightLeg.setTextureOffset(49, 48).addBox(-2.5f, 0.5f, -2.5f, 5.0f, 9.0f, 5.0f, 0.0f, true);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityGorilla.ANIMATION_BREAKBLOCK_R);
        this.animator.startKeyframe(7);
        this.animator.rotate(this.chest, 0.0f, Maths.rad(-10.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.rightArm, Maths.rad(65.0), Maths.rad(-20.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.chest, 0.0f, Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(0.0));
        this.animator.rotate(this.rightArm, Maths.rad(-80.0), Maths.rad(-30.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(6);
        this.animator.setAnimation(EntityGorilla.ANIMATION_BREAKBLOCK_L);
        this.animator.startKeyframe(7);
        this.animator.rotate(this.chest, 0.0f, Maths.rad(10.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.leftArm, Maths.rad(65.0), Maths.rad(20.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.chest, 0.0f, Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(0.0));
        this.animator.rotate(this.leftArm, Maths.rad(-80.0), Maths.rad(30.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(6);
        this.animator.setAnimation(EntityGorilla.ANIMATION_POUNDCHEST);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.rightArm, Maths.rad(-60.0), 0.0f, Maths.rad(20.0));
        this.animator.rotate(this.leftArm, Maths.rad(-60.0), 0.0f, Maths.rad(-20.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.rightArm, 0.0f, 5.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(0.0), Maths.rad(10.0), 0.0f);
        this.animator.rotate(this.chest, 0.0f, 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.rightArm, Maths.rad(-60.0), 0.0f, Maths.rad(-80.0));
        this.animator.rotate(this.leftArm, Maths.rad(-60.0), 0.0f, Maths.rad(-40.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.leftArm, 0.0f, 5.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(0.0), Maths.rad(-10.0), 0.0f);
        this.animator.rotate(this.chest, 0.0f, 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.rightArm, Maths.rad(-60.0), 0.0f, Maths.rad(20.0));
        this.animator.rotate(this.leftArm, Maths.rad(-60.0), 0.0f, Maths.rad(60.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.rightArm, 0.0f, 5.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(0.0), Maths.rad(10.0), 0.0f);
        this.animator.rotate(this.rightArm, Maths.rad(-60.0), 0.0f, Maths.rad(-80.0));
        this.animator.rotate(this.leftArm, Maths.rad(-60.0), 0.0f, Maths.rad(-40.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.leftArm, 0.0f, 5.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(0.0), Maths.rad(-10.0), 0.0f);
        this.animator.rotate(this.chest, 0.0f, 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.rightArm, Maths.rad(-60.0), 0.0f, Maths.rad(20.0));
        this.animator.rotate(this.leftArm, Maths.rad(-60.0), 0.0f, Maths.rad(60.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.rightArm, 0.0f, 5.0f, 0.0f);
        this.animator.rotate(this.chest, 0.0f, 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.head, Maths.rad(0.0), Maths.rad(10.0), 0.0f);
        this.animator.rotate(this.rightArm, Maths.rad(-60.0), 0.0f, Maths.rad(-80.0));
        this.animator.rotate(this.leftArm, Maths.rad(-60.0), 0.0f, Maths.rad(-40.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.leftArm, 0.0f, 5.0f, 0.0f);
        this.animator.rotate(this.chest, 0.0f, 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.head, Maths.rad(0.0), Maths.rad(-10.0), 0.0f);
        this.animator.rotate(this.rightArm, Maths.rad(-60.0), 0.0f, Maths.rad(20.0));
        this.animator.rotate(this.leftArm, Maths.rad(-60.0), 0.0f, Maths.rad(60.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityGorilla.ANIMATION_ATTACK);
        this.animator.startKeyframe(7);
        this.animator.rotate(this.leftArm, Maths.rad(65.0), Maths.rad(10.0), 0.0f);
        this.animator.rotate(this.rightArm, Maths.rad(65.0), Maths.rad(-10.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.leftArm, Maths.rad(-90.0), Maths.rad(30.0), 0.0f);
        this.animator.rotate(this.rightArm, Maths.rad(-90.0), Maths.rad(-30.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(6);
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.35f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setupAnim(EntityGorilla entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 0.7f;
        float walkDegree = 0.5f;
        float eatSpeed = 0.8f;
        float eatDegree = 0.3f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float sitProgress = entityIn.prevSitProgress + (entityIn.sitProgress - entityIn.prevSitProgress) * partialTick;
        float standProgress = entityIn.prevStandProgress + (entityIn.standProgress - entityIn.prevStandProgress) * partialTick;
        float rideProgress = entityIn.m_20159_() && entityIn.m_6162_() ? 5.0f : 0.0f;
        this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.head});
        this.progressRotationPrev(this.leftArm, rideProgress, Maths.rad(-20.0), Maths.rad(-20.0), Maths.rad(-40.0), 5.0f);
        this.progressRotationPrev(this.rightArm, rideProgress, Maths.rad(-20.0), Maths.rad(20.0), Maths.rad(40.0), 5.0f);
        this.progressRotationPrev(this.leftLeg, rideProgress, Maths.rad(-20.0), 0.0f, Maths.rad(-80.0), 5.0f);
        this.progressRotationPrev(this.rightLeg, rideProgress, Maths.rad(-20.0), 0.0f, Maths.rad(80.0), 5.0f);
        this.progressRotationPrev(this.head, rideProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, rideProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, rideProgress, 0.0f, 5.0f, 3.0f, 5.0f);
        this.progressRotationPrev(this.body, sitProgress, Maths.rad(-80.0), 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.rightLeg, sitProgress, Maths.rad(-10.0), Maths.rad(-30.0), Maths.rad(30.0), 10.0f);
        this.progressRotationPrev(this.leftLeg, sitProgress, Maths.rad(-10.0), Maths.rad(30.0), Maths.rad(-30.0), 10.0f);
        this.progressRotationPrev(this.head, sitProgress, Maths.rad(80.0), 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.leftArm, sitProgress, Maths.rad(20.0), 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.rightArm, sitProgress, Maths.rad(20.0), 0.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.body, sitProgress, 0.0f, 8.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.chest, sitProgress, 0.0f, -0.5f, 1.5f, 10.0f);
        this.progressPositionPrev(this.head, sitProgress, 0.0f, 4.0f, -2.0f, 10.0f);
        this.progressPositionPrev(this.leftArm, sitProgress, 0.0f, 0.0f, 2.0f, 10.0f);
        this.progressPositionPrev(this.rightArm, sitProgress, 0.0f, 0.0f, 2.0f, 10.0f);
        this.progressRotationPrev(this.body, standProgress, Maths.rad(-80.0), 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.rightLeg, standProgress, Maths.rad(80.0), 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.leftLeg, standProgress, Maths.rad(80.0), 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.head, standProgress, Maths.rad(80.0), 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.leftArm, standProgress, Maths.rad(80.0), 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.rightArm, standProgress, Maths.rad(80.0), 0.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.body, standProgress, 0.0f, 1.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.rightLeg, standProgress, -1.0f, -3.0f, 1.2f, 10.0f);
        this.progressPositionPrev(this.leftLeg, standProgress, 1.0f, -3.0f, 1.2f, 10.0f);
        this.progressPositionPrev(this.leftArm, standProgress, 2.0f, 1.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.rightArm, standProgress, -2.0f, 1.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.head, standProgress, 0.0f, 4.0f, -2.0f, 10.0f);
        this.walk(this.leftLeg, walkSpeed, walkDegree * 1.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.rightLeg, walkSpeed, walkDegree * 1.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.leftArm, walkSpeed, walkDegree * 1.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.rightArm, walkSpeed, walkDegree * 1.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.body, walkSpeed, walkDegree * 0.2f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        if (entityIn.isEating()) {
            this.walk(this.rightArm, eatSpeed, eatDegree, false, 1.0f, -0.3f, ageInTicks, 1.0f);
            this.walk(this.leftArm, eatSpeed, eatDegree, false, 1.0f, -0.3f, ageInTicks, 1.0f);
            this.walk(this.chest, eatSpeed, eatDegree * 0.1f, false, 2.0f, 0.3f, ageInTicks, 1.0f);
            this.walk(this.head, eatSpeed, eatDegree * 0.3f, true, 1.0f, 0.3f, ageInTicks, 1.0f);
        }
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.chest, (Object)this.head, (Object)this.foreheadDK_r1, (Object)this.mouth, (Object)this.leftArm, (Object)this.rightArm, (Object)this.rightLeg, (Object)this.leftLeg);
    }
}
