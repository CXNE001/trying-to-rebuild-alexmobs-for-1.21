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
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityStraddler;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelStraddler
extends AdvancedEntityModel<EntityStraddler> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox hair;
    public final AdvancedModelBox horn_left;
    public final AdvancedModelBox hair_left;
    public final AdvancedModelBox horn_right;
    public final AdvancedModelBox hair_right;
    public final AdvancedModelBox leg_left;
    public final AdvancedModelBox leg_right;
    private ModelAnimator animator;

    public ModelStraddler() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -14.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-14.0f, -12.0f, -7.0f, 28.0f, 11.0f, 14.0f, 0.0f, false);
        this.hair = new AdvancedModelBox((AdvancedEntityModel)this, "hair");
        this.hair.setPos(0.0f, -13.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.hair);
        this.hair.setTextureOffset(23, 26).addBox(-6.0f, -4.0f, 0.0f, 12.0f, 5.0f, 0.0f, 0.0f, false);
        this.horn_left = new AdvancedModelBox((AdvancedEntityModel)this, "horn_left");
        this.horn_left.setPos(9.5f, -12.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.horn_left);
        this.horn_left.setTextureOffset(0, 26).addBox(-2.5f, -18.0f, 0.0f, 6.0f, 18.0f, 10.0f, 0.0f, false);
        this.hair_left = new AdvancedModelBox((AdvancedEntityModel)this, "hair_left");
        this.hair_left.setPos(-2.5f, -17.0f, 10.0f);
        this.horn_left.addChild((BasicModelPart)this.hair_left);
        this.setRotationAngle(this.hair_left, -0.5672f, -0.2618f, 0.0f);
        this.hair_left.setTextureOffset(33, 33).addBox(0.0f, 0.0f, 0.0f, 0.0f, 6.0f, 16.0f, 0.0f, false);
        this.horn_right = new AdvancedModelBox((AdvancedEntityModel)this, "horn_right");
        this.horn_right.setPos(-9.5f, -12.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.horn_right);
        this.horn_right.setTextureOffset(0, 26).addBox(-3.5f, -18.0f, 0.0f, 6.0f, 18.0f, 10.0f, 0.0f, true);
        this.hair_right = new AdvancedModelBox((AdvancedEntityModel)this, "hair_right");
        this.hair_right.setPos(2.5f, -17.0f, 10.0f);
        this.horn_right.addChild((BasicModelPart)this.hair_right);
        this.setRotationAngle(this.hair_right, -0.5672f, 0.2618f, 0.0f);
        this.hair_right.setTextureOffset(33, 33).addBox(0.0f, 0.0f, 0.0f, 0.0f, 6.0f, 16.0f, 0.0f, true);
        this.leg_left = new AdvancedModelBox((AdvancedEntityModel)this, "leg_left");
        this.leg_left.setPos(7.0f, -0.5f, 0.0f);
        this.body.addChild((BasicModelPart)this.leg_left);
        this.leg_left.setTextureOffset(50, 26).addBox(-3.0f, -0.5f, -3.0f, 6.0f, 15.0f, 6.0f, 0.0f, false);
        this.leg_right = new AdvancedModelBox((AdvancedEntityModel)this, "leg_right");
        this.leg_right.setPos(-7.0f, -0.5f, 0.0f);
        this.body.addChild((BasicModelPart)this.leg_right);
        this.leg_right.setTextureOffset(50, 26).addBox(-3.0f, -0.5f, -3.0f, 6.0f, 15.0f, 6.0f, 0.0f, true);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityStraddler.ANIMATION_LAUNCH);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.body, Maths.rad(-5.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(5.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(5.0), 0.0f, 0.0f);
        this.animator.rotate(this.horn_right, 0.0f, 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.horn_left, 0.0f, 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.hair_left, 0.0f, Maths.rad(-70.0), 0.0f);
        this.animator.rotate(this.hair_right, 0.0f, Maths.rad(70.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.startKeyframe(10);
        this.animator.rotate(this.body, Maths.rad(-5.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(5.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(5.0), 0.0f, 0.0f);
        this.animator.rotate(this.horn_right, Maths.rad(-30.0), 0.0f, Maths.rad(-10.0));
        this.animator.rotate(this.horn_left, Maths.rad(-30.0), 0.0f, Maths.rad(10.0));
        this.animator.rotate(this.hair_left, Maths.rad(20.0), Maths.rad(-15.0), 0.0f);
        this.animator.rotate(this.hair_right, Maths.rad(20.0), Maths.rad(15.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.rotate(this.body, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.horn_right, Maths.rad(20.0), 0.0f, Maths.rad(0.0));
        this.animator.rotate(this.horn_left, Maths.rad(20.0), 0.0f, Maths.rad(0.0));
        this.animator.rotate(this.hair_left, Maths.rad(20.0), Maths.rad(-160.0), 0.0f);
        this.animator.rotate(this.hair_right, Maths.rad(20.0), Maths.rad(160.0), 0.0f);
        this.animator.move(this.horn_left, 0.0f, 2.4f, 0.0f);
        this.animator.move(this.horn_right, 0.0f, 2.4f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.hair, (Object)this.hair_left, (Object)this.hair_right, (Object)this.horn_left, (Object)this.horn_right, (Object)this.leg_left, (Object)this.leg_right);
    }

    public void setupAnim(EntityStraddler entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 0.5f;
        float walkDegree = 0.6f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        this.walk(this.hair_left, idleSpeed, idleDegree, false, 0.0f, -0.1f, ageInTicks, 1.0f);
        this.swing(this.hair_left, idleSpeed, idleDegree, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.hair_right, idleSpeed, idleDegree, false, 0.0f, -0.1f, ageInTicks, 1.0f);
        this.swing(this.hair_right, idleSpeed, idleDegree, true, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.hair, idleSpeed, idleDegree, false, 3.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.leg_right, walkSpeed, walkDegree * 1.5f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.leg_left, walkSpeed, walkDegree * 1.5f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.body, walkSpeed, walkDegree * 0.3f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.body, walkSpeed, walkDegree * 0.3f, false, -2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.body, walkSpeed, walkDegree * 0.3f, false, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.hair_left, walkSpeed, walkDegree * 0.8f, false, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.hair_right, walkSpeed, walkDegree * 0.8f, false, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        if (entity.m_20197_().size() <= 0) {
            this.body.rotateAngleX += headPitch * 0.5f * ((float)Math.PI / 180);
            this.leg_right.rotateAngleX -= headPitch * 0.5f * ((float)Math.PI / 180);
            this.leg_left.rotateAngleX -= headPitch * 0.5f * ((float)Math.PI / 180);
        }
    }

    public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z) {
        advancedModelBox.rotateAngleX = x;
        advancedModelBox.rotateAngleY = y;
        advancedModelBox.rotateAngleZ = z;
    }
}
