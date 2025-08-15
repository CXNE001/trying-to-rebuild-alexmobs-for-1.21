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

import com.github.alexthe666.alexsmobs.entity.EntityGazelle;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelGazelle
extends AdvancedEntityModel<EntityGazelle> {
    private final AdvancedModelBox body;
    private final AdvancedModelBox neck;
    private final AdvancedModelBox head;
    private final AdvancedModelBox earL;
    private final AdvancedModelBox earR;
    private final AdvancedModelBox snout;
    private final AdvancedModelBox hornL;
    private final AdvancedModelBox hornR;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox frontlegR;
    private final AdvancedModelBox frontlegL;
    private final AdvancedModelBox backlegL;
    private final AdvancedModelBox backlegR;
    private ModelAnimator animator;

    public ModelGazelle() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, 20.8f, 0.0f);
        this.body.setTextureOffset(0, 0).addBox(-4.0f, -16.8f, -9.0f, 8.0f, 8.0f, 18.0f, 0.0f, false);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setPos(0.0f, -14.8f, -8.0f);
        this.body.addChild((BasicModelPart)this.neck);
        this.setRotationAngle(this.neck, 0.2618f, 0.0f, 0.0f);
        this.neck.setTextureOffset(0, 0).addBox(-2.0f, -7.0f, -2.0f, 4.0f, 9.0f, 4.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -7.0f, 0.0f);
        this.neck.addChild((BasicModelPart)this.head);
        this.setRotationAngle(this.head, -0.2618f, 0.0f, 0.0f);
        this.head.setTextureOffset(0, 27).addBox(-2.5f, -4.0f, -3.0f, 5.0f, 5.0f, 5.0f, 0.0f, false);
        this.earL = new AdvancedModelBox((AdvancedEntityModel)this, "earL");
        this.earL.setPos(1.5f, -3.3f, 0.5f);
        this.head.addChild((BasicModelPart)this.earL);
        this.setRotationAngle(this.earL, -0.2618f, -0.5236f, 0.6109f);
        this.earL.setTextureOffset(0, 38).addBox(-0.5f, -3.7f, -0.5f, 2.0f, 4.0f, 1.0f, 0.0f, false);
        this.earR = new AdvancedModelBox((AdvancedEntityModel)this, "earR");
        this.earR.setPos(-1.5f, -3.3f, 0.5f);
        this.head.addChild((BasicModelPart)this.earR);
        this.setRotationAngle(this.earR, -0.2618f, 0.5236f, -0.6109f);
        this.earR.setTextureOffset(0, 38).addBox(-1.5f, -3.7f, -0.5f, 2.0f, 4.0f, 1.0f, 0.0f, true);
        this.snout = new AdvancedModelBox((AdvancedEntityModel)this, "snout");
        this.snout.setPos(0.0f, -0.5f, -2.9f);
        this.head.addChild((BasicModelPart)this.snout);
        this.snout.setTextureOffset(34, 27).addBox(-1.5f, -1.5f, -3.1f, 3.0f, 3.0f, 3.0f, 0.0f, false);
        this.hornL = new AdvancedModelBox((AdvancedEntityModel)this, "hornL");
        this.hornL.setPos(1.3f, -3.4f, -1.9f);
        this.head.addChild((BasicModelPart)this.hornL);
        this.setRotationAngle(this.hornL, -0.2618f, 0.0f, 0.2618f);
        this.hornL.setTextureOffset(35, 0).addBox(-1.0f, -9.0f, -1.0f, 2.0f, 9.0f, 2.0f, 0.0f, false);
        this.hornR = new AdvancedModelBox((AdvancedEntityModel)this, "hornR");
        this.hornR.setPos(-1.3f, -3.4f, -1.9f);
        this.head.addChild((BasicModelPart)this.hornR);
        this.setRotationAngle(this.hornR, -0.2618f, 0.0f, -0.2618f);
        this.hornR.setTextureOffset(35, 0).addBox(-1.0f, -9.0f, -1.0f, 2.0f, 9.0f, 2.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, -13.8f, 9.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.setRotationAngle(this.tail, 0.3491f, 0.0f, 0.0f);
        this.tail.setTextureOffset(35, 12).addBox(-2.0f, 0.0f, 0.0f, 4.0f, 5.0f, 0.0f, 0.0f, false);
        this.frontlegR = new AdvancedModelBox((AdvancedEntityModel)this, "frontlegR");
        this.frontlegR.setPos(2.5f, -6.8f, -6.5f);
        this.body.addChild((BasicModelPart)this.frontlegR);
        this.frontlegR.setTextureOffset(34, 34).addBox(-6.5f, -2.0f, -1.5f, 3.0f, 12.0f, 3.0f, 0.0f, true);
        this.frontlegL = new AdvancedModelBox((AdvancedEntityModel)this, "frontlegL");
        this.frontlegL.setPos(2.5f, -6.8f, -6.5f);
        this.body.addChild((BasicModelPart)this.frontlegL);
        this.frontlegL.setTextureOffset(34, 34).addBox(-1.5f, -2.0f, -1.5f, 3.0f, 12.0f, 3.0f, 0.0f, false);
        this.backlegL = new AdvancedModelBox((AdvancedEntityModel)this, "backlegL");
        this.backlegL.setPos(2.5f, -7.8f, 7.5f);
        this.body.addChild((BasicModelPart)this.backlegL);
        this.backlegL.setTextureOffset(21, 27).addBox(-1.5f, -1.0f, -1.5f, 3.0f, 12.0f, 3.0f, 0.0f, false);
        this.backlegR = new AdvancedModelBox((AdvancedEntityModel)this, "backlegR");
        this.backlegR.setPos(-2.5f, -7.8f, 7.5f);
        this.body.addChild((BasicModelPart)this.backlegR);
        this.backlegR.setTextureOffset(21, 27).addBox(-1.5f, -1.0f, -1.5f, 3.0f, 12.0f, 3.0f, 0.0f, true);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.animator.update(entity);
        this.animator.setAnimation(EntityGazelle.ANIMATION_FLICK_TAIL);
        this.animator.startKeyframe(2);
        this.animator.rotate(this.tail, 0.0f, 0.0f, Maths.rad(50.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.tail, 0.0f, 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.tail, 0.0f, 0.0f, Maths.rad(-50.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.tail, 0.0f, 0.0f, Maths.rad(50.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.tail, 0.0f, 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.tail, 0.0f, 0.0f, Maths.rad(-50.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(2);
        this.animator.setAnimation(EntityGazelle.ANIMATION_FLICK_EARS);
        this.animator.startKeyframe(2);
        this.animator.rotate(this.neck, Maths.rad(25.0), Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.head, Maths.rad(5.0), 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.body, 0.0f, Maths.rad(5.0), 0.0f);
        this.animator.rotate(this.earR, 0.0f, Maths.rad(25.0), Maths.rad(40.0));
        this.animator.rotate(this.earL, 0.0f, Maths.rad(-25.0), Maths.rad(-40.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.neck, Maths.rad(25.0), Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.head, Maths.rad(5.0), 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.body, 0.0f, Maths.rad(-5.0), 0.0f);
        this.animator.rotate(this.earR, 0.0f, 0.0f, 0.0f);
        this.animator.rotate(this.earL, 0.0f, 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.neck, Maths.rad(25.0), Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.head, Maths.rad(5.0), 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.body, 0.0f, Maths.rad(5.0), 0.0f);
        this.animator.rotate(this.earR, 0.0f, Maths.rad(5.0), Maths.rad(-40.0));
        this.animator.rotate(this.earL, 0.0f, Maths.rad(-5.0), Maths.rad(40.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.neck, Maths.rad(25.0), Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.head, Maths.rad(5.0), 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.earR, 0.0f, Maths.rad(25.0), Maths.rad(40.0));
        this.animator.rotate(this.earL, 0.0f, Maths.rad(-25.0), Maths.rad(-40.0));
        this.animator.rotate(this.body, 0.0f, Maths.rad(-5.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.neck, Maths.rad(25.0), Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.head, Maths.rad(5.0), 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.body, 0.0f, Maths.rad(5.0), 0.0f);
        this.animator.rotate(this.earR, 0.0f, 0.0f, 0.0f);
        this.animator.rotate(this.earL, 0.0f, 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.neck, 0.0f, 0.0f, 0.0f);
        this.animator.rotate(this.head, 0.0f, 0.0f, 0.0f);
        this.animator.rotate(this.body, 0.0f, Maths.rad(-5.0), 0.0f);
        this.animator.rotate(this.earR, 0.0f, Maths.rad(5.0), Maths.rad(-40.0));
        this.animator.rotate(this.earL, 0.0f, Maths.rad(-5.0), Maths.rad(40.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(7);
        this.animator.setAnimation(EntityGazelle.ANIMATION_EAT_GRASS);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.neck, Maths.rad(100.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-40.0), 0.0f, 0.0f);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.neck, Maths.rad(120.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-50.0), 0.0f, 0.0f);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.neck, Maths.rad(100.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-40.0), 0.0f, 0.0f);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.neck, Maths.rad(120.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-50.0), 0.0f, 0.0f);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.neck, Maths.rad(100.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-40.0), 0.0f, 0.0f);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.startKeyframe(4);
        this.animator.rotate(this.neck, Maths.rad(120.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(-50.0), 0.0f, 0.0f);
        this.eatPose();
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
    }

    private void eatPose() {
        this.animator.rotate(this.body, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.move(this.body, 0.0f, 2.0f, 0.0f);
        this.animator.rotate(this.backlegL, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.backlegR, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.frontlegL, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.frontlegR, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.move(this.frontlegL, 0.1f, -3.0f, 0.0f);
        this.animator.move(this.frontlegR, -0.1f, -3.0f, 0.0f);
        this.animator.move(this.backlegL, 0.0f, -1.0f, 0.0f);
        this.animator.move(this.backlegR, 0.0f, -1.0f, 0.0f);
        this.animator.move(this.neck, 0.0f, 1.0f, 0.0f);
    }

    public void setupAnim(EntityGazelle entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        boolean running = entityIn.isRunning();
        float runSpeed = 0.7f;
        float runDegree = 0.7f;
        float walkSpeed = 0.7f;
        float walkDegree = 0.4f;
        float idleSpeed = 0.05f;
        float idleDegree = 0.1f;
        this.faceTarget(netHeadYaw, headPitch, 2.0f, new AdvancedModelBox[]{this.neck, this.head});
        this.walk(this.neck, idleSpeed, idleDegree, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.tail, idleSpeed * 3.0f, idleDegree, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.head, idleSpeed, -idleDegree, false, 0.5f, 0.0f, ageInTicks, 1.0f);
        if (running) {
            this.walk(this.body, runSpeed, runDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.neck, runSpeed, runDegree * 0.2f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.frontlegR, runSpeed, runDegree * 1.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.frontlegL, runSpeed, runDegree * 1.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.bob(this.frontlegR, runSpeed, runDegree * 2.0f, true, limbSwing, limbSwingAmount);
            this.bob(this.frontlegL, runSpeed, runDegree * 2.0f, true, limbSwing, limbSwingAmount);
            this.walk(this.backlegR, runSpeed, runDegree * 1.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.backlegL, runSpeed, runDegree * 1.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.backlegR, runSpeed, runDegree * 0.2f, true, 0.0f, -0.2f, limbSwing, limbSwingAmount);
            this.flap(this.backlegL, runSpeed, runDegree * 0.2f, false, 0.0f, -0.2f, limbSwing, limbSwingAmount);
            this.bob(this.body, runSpeed, runDegree * 8.0f, false, limbSwing, limbSwingAmount);
        } else {
            this.walk(this.body, walkSpeed, walkDegree * 0.05f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.neck, walkSpeed, walkDegree * 0.5f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.head, walkSpeed, -walkDegree * 0.5f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.frontlegR, walkSpeed, walkDegree * 1.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.frontlegL, walkSpeed, walkDegree * 1.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.backlegR, walkSpeed, walkDegree * 1.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.backlegL, walkSpeed, walkDegree * 1.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        }
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.75f;
            this.head.setScale(f, f, f);
            this.hornL.setScale(0.4f, 0.4f, 0.4f);
            this.hornR.setScale(0.4f, 0.4f, 0.4f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
            this.hornL.setScale(1.0f, 1.0f, 1.0f);
            this.hornR.setScale(1.0f, 1.0f, 1.0f);
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.body);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.body, (Object)this.neck, (Object)this.head, (Object)this.earL, (Object)this.earR, (Object)this.backlegL, (Object)this.backlegR, (Object)this.frontlegL, (Object)this.frontlegR, (Object)this.snout, (Object)this.hornL, (Object)this.hornR, (Object[])new AdvancedModelBox[]{this.tail});
    }

    public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z) {
        advancedModelBox.rotateAngleX = x;
        advancedModelBox.rotateAngleY = y;
        advancedModelBox.rotateAngleZ = z;
    }
}
