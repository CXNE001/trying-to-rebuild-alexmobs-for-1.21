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

import com.github.alexthe666.alexsmobs.entity.EntityRhinoceros;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelRhinoceros
extends AdvancedEntityModel<EntityRhinoceros> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox leftLeg;
    private final AdvancedModelBox rightLeg;
    private final AdvancedModelBox chest;
    private final AdvancedModelBox head;
    private final AdvancedModelBox horns;
    private final AdvancedModelBox leftEar;
    private final AdvancedModelBox rightEar;
    private final AdvancedModelBox leftArm;
    private final AdvancedModelBox rightArm;
    private final ModelAnimator animator;

    public ModelRhinoceros() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -19.0f, 4.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 44).addBox(-9.0f, -10.0f, -6.0f, 18.0f, 20.0f, 21.0f, 0.0f, false);
        this.leftLeg = new AdvancedModelBox((AdvancedEntityModel)this, "leftLeg");
        this.leftLeg.setRotationPoint(6.0f, 9.0f, 12.0f);
        this.body.addChild((BasicModelPart)this.leftLeg);
        this.leftLeg.setTextureOffset(70, 77).addBox(-4.0f, -1.0f, -4.0f, 8.0f, 11.0f, 9.0f, 0.0f, false);
        this.rightLeg = new AdvancedModelBox((AdvancedEntityModel)this, "rightLeg");
        this.rightLeg.setRotationPoint(-6.0f, 9.0f, 12.0f);
        this.body.addChild((BasicModelPart)this.rightLeg);
        this.rightLeg.setTextureOffset(70, 77).addBox(-4.0f, -1.0f, -4.0f, 8.0f, 11.0f, 9.0f, 0.0f, true);
        this.chest = new AdvancedModelBox((AdvancedEntityModel)this, "chest");
        this.chest.setRotationPoint(0.0f, -4.0f, -10.0f);
        this.body.addChild((BasicModelPart)this.chest);
        this.chest.setTextureOffset(0, 0).addBox(-11.0f, -10.0f, -14.0f, 22.0f, 23.0f, 20.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, 3.0f, -14.0f);
        this.chest.addChild((BasicModelPart)this.head);
        this.setRotationAngle(this.head, 0.3927f, 0.0f, 0.0f);
        this.head.setTextureOffset(76, 35).addBox(-6.0f, -6.0f, -8.0f, 12.0f, 14.0f, 9.0f, 0.0f, false);
        this.head.setTextureOffset(65, 0).addBox(-4.0f, 0.0f, -18.0f, 8.0f, 8.0f, 10.0f, 0.0f, false);
        this.horns = new AdvancedModelBox((AdvancedEntityModel)this, "horns");
        this.horns.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.addChild((BasicModelPart)this.horns);
        this.horns.setTextureOffset(0, 0).addBox(-2.0f, -12.0f, -18.0f, 4.0f, 12.0f, 5.0f, 0.0f, false);
        this.horns.setTextureOffset(0, 44).addBox(-2.0f, -4.0f, -13.0f, 4.0f, 4.0f, 4.0f, 0.0f, false);
        this.leftEar = new AdvancedModelBox((AdvancedEntityModel)this, "leftEar");
        this.leftEar.setRotationPoint(6.0f, -5.0f, -4.0f);
        this.head.addChild((BasicModelPart)this.leftEar);
        this.setRotationAngle(this.leftEar, -0.2443f, -0.2443f, 0.7679f);
        this.leftEar.setTextureOffset(0, 53).addBox(-1.0f, -5.0f, 0.0f, 3.0f, 6.0f, 1.0f, 0.0f, false);
        this.rightEar = new AdvancedModelBox((AdvancedEntityModel)this, "rightEar");
        this.rightEar.setRotationPoint(-6.0f, -5.0f, -4.0f);
        this.head.addChild((BasicModelPart)this.rightEar);
        this.setRotationAngle(this.rightEar, -0.2443f, 0.2443f, -0.7679f);
        this.rightEar.setTextureOffset(0, 53).addBox(-2.0f, -5.0f, 0.0f, 3.0f, 6.0f, 1.0f, 0.0f, true);
        this.leftArm = new AdvancedModelBox((AdvancedEntityModel)this, "leftArm");
        this.leftArm.setRotationPoint(7.3f, 11.0f, -8.0f);
        this.chest.addChild((BasicModelPart)this.leftArm);
        this.leftArm.setTextureOffset(79, 59).addBox(-4.0f, 2.0f, -4.0f, 7.0f, 10.0f, 7.0f, 0.0f, false);
        this.rightArm = new AdvancedModelBox((AdvancedEntityModel)this, "rightArm");
        this.rightArm.setRotationPoint(-7.3f, 11.0f, -8.0f);
        this.chest.addChild((BasicModelPart)this.rightArm);
        this.rightArm.setTextureOffset(79, 59).addBox(-3.0f, 2.0f, -4.0f, 7.0f, 10.0f, 7.0f, 0.0f, true);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityRhinoceros.ANIMATION_FLICK_EARS);
        this.animator.startKeyframe(2);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.rightEar, 0.0f, Maths.rad(25.0), Maths.rad(40.0));
        this.animator.rotate(this.leftEar, 0.0f, Maths.rad(-25.0), Maths.rad(-40.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.rightEar, 0.0f, 0.0f, 0.0f);
        this.animator.rotate(this.leftEar, 0.0f, 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.rightEar, 0.0f, Maths.rad(5.0), Maths.rad(-40.0));
        this.animator.rotate(this.leftEar, 0.0f, Maths.rad(-5.0), Maths.rad(40.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.rightEar, 0.0f, Maths.rad(25.0), Maths.rad(40.0));
        this.animator.rotate(this.leftEar, 0.0f, Maths.rad(-25.0), Maths.rad(-40.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.rightEar, 0.0f, 0.0f, 0.0f);
        this.animator.rotate(this.leftEar, 0.0f, 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.head, 0.0f, 0.0f, 0.0f);
        this.animator.rotate(this.rightEar, 0.0f, Maths.rad(5.0), Maths.rad(-40.0));
        this.animator.rotate(this.leftEar, 0.0f, Maths.rad(-5.0), Maths.rad(40.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(7);
        this.animator.setAnimation(EntityRhinoceros.ANIMATION_EAT_GRASS);
        this.animator.startKeyframe(5);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.eatPose();
        this.animator.move(this.head, 0.0f, 1.0f, 1.0f);
        this.animator.rotate(this.head, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.eatPose();
        this.animator.move(this.head, 0.0f, 1.0f, 1.0f);
        this.animator.rotate(this.head, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.eatPose();
        this.animator.move(this.head, 0.0f, 1.0f, 1.0f);
        this.animator.rotate(this.head, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityRhinoceros.ANIMATION_FLING);
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 1.0f, -2.0f);
        this.animator.move(this.leftArm, 0.0f, -1.0f, 0.0f);
        this.animator.move(this.rightArm, 0.0f, -1.0f, 0.0f);
        this.animator.move(this.rightLeg, 0.0f, 1.0f, 0.0f);
        this.animator.move(this.leftLeg, 0.0f, 1.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.leftLeg, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.rightLeg, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.rotate(this.rightArm, Maths.rad(-50.0), 0.0f, Maths.rad(5.0));
        this.animator.rotate(this.leftArm, Maths.rad(-50.0), 0.0f, Maths.rad(-5.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.head, Maths.rad(-60.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(2);
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityRhinoceros.ANIMATION_SLASH);
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, 5.0f);
        this.animator.rotate(this.rightLeg, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.leftLeg, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.rightArm, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.rotate(this.leftArm, Maths.rad(-30.0), 0.0f, 0.0f);
        this.animator.move(this.head, 0.0f, 0.0f, -4.0f);
        this.animator.rotate(this.head, Maths.rad(40.0), Maths.rad(45.0), Maths.rad(70.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, -2.0f);
        this.animator.move(this.head, 0.0f, 0.0f, -2.0f);
        this.animator.rotate(this.head, Maths.rad(-20.0), Maths.rad(-45.0), Maths.rad(-50.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.head, 0.0f, 0.0f, -4.0f);
        this.animator.rotate(this.head, Maths.rad(40.0), Maths.rad(-45.0), Maths.rad(-70.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, -2.0f);
        this.animator.move(this.head, 0.0f, 0.0f, -2.0f);
        this.animator.rotate(this.head, Maths.rad(-20.0), Maths.rad(45.0), Maths.rad(50.0));
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.resetKeyframe(5);
    }

    private void eatPose() {
        this.animator.rotate(this.body, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.rotate(this.rightLeg, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.leftLeg, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.rightArm, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.leftArm, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.move(this.head, 0.0f, -4.0f, -2.0f);
        this.animator.move(this.rightLeg, 0.0f, 1.8f, -2.0f);
        this.animator.move(this.leftLeg, 0.0f, 1.8f, -2.0f);
        this.animator.move(this.rightArm, 0.0f, -3.0f, 0.0f);
        this.animator.move(this.leftArm, 0.0f, -3.0f, 0.0f);
    }

    public void setupAnim(EntityRhinoceros entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 0.7f;
        float walkDegree = 0.6f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        this.walk(this.leftArm, walkSpeed, walkDegree * 1.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.rightArm, walkSpeed, walkDegree * 1.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.leftLeg, walkSpeed, walkDegree * 1.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.rightLeg, walkSpeed, walkDegree * 1.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed, walkDegree * 2.0f, true, limbSwing, limbSwingAmount);
        this.bob(this.head, walkSpeed, walkDegree * 2.0f, true, limbSwing, limbSwingAmount);
        this.walk(this.head, idleSpeed, idleDegree * 0.5f, false, 0.0f, 0.05f, ageInTicks, 1.0f);
        this.flap(this.leftEar, idleSpeed, idleDegree, false, -1.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.rightEar, idleSpeed, idleDegree, false, -1.0f, 0.0f, ageInTicks, 1.0f);
        this.head.rotateAngleY += netHeadYaw * 0.8f * ((float)Math.PI / 180);
        this.head.rotateAngleX += headPitch * ((float)Math.PI / 180);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.head, (Object)this.chest, (Object)this.leftArm, (Object)this.leftEar, (Object)this.leftLeg, (Object)this.rightArm, (Object)this.rightEar, (Object)this.rightLeg, (Object)this.horns);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.35f;
            float feet = 1.3f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            this.horns.showModel = false;
            this.leftArm.setScale(1.0f, feet, 1.0f);
            this.rightArm.setScale(1.0f, feet, 1.0f);
            this.leftLeg.setScale(1.0f, feet, 1.0f);
            this.rightLeg.setScale(1.0f, feet, 1.0f);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.3, 0.0);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
            this.leftArm.setScale(1.0f, 1.0f, 1.0f);
            this.rightArm.setScale(1.0f, 1.0f, 1.0f);
            this.leftLeg.setScale(1.0f, 1.0f, 1.0f);
            this.rightLeg.setScale(1.0f, 1.0f, 1.0f);
            this.horns.showModel = true;
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }
}
