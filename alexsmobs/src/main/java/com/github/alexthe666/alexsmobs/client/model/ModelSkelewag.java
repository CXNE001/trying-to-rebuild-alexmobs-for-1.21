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

import com.github.alexthe666.alexsmobs.entity.EntitySkelewag;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelSkelewag
extends AdvancedEntityModel<EntitySkelewag> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox head;
    private final AdvancedModelBox flag;
    private final AdvancedModelBox left_fin;
    private final AdvancedModelBox right_fin;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox tail_fin;
    private ModelAnimator animator;

    public ModelSkelewag() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -9.0f, -3.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(17, 11).addBox(-1.0f, -1.0f, -16.0f, 2.0f, 2.0f, 20.0f, 0.0f, false);
        this.body.setTextureOffset(50, 66).addBox(-0.5f, -16.0f, -15.0f, 1.0f, 15.0f, 2.0f, 0.0f, false);
        this.body.setTextureOffset(23, 34).addBox(-0.5f, -12.0f, -11.0f, 1.0f, 11.0f, 2.0f, 0.0f, false);
        this.body.setTextureOffset(19, 6).addBox(-0.5f, -9.0f, -7.0f, 1.0f, 8.0f, 2.0f, 0.0f, false);
        this.body.setTextureOffset(26, 6).addBox(-0.5f, -7.0f, -3.0f, 1.0f, 6.0f, 2.0f, 0.0f, false);
        this.body.setTextureOffset(0, 0).addBox(0.0f, -4.0f, 1.0f, 0.0f, 3.0f, 2.0f, 0.0f, false);
        this.body.setTextureOffset(45, 34).addBox(-2.0f, 1.0f, -12.0f, 4.0f, 6.0f, 10.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(-0.5f, 0.0f, -17.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(23, 54).addBox(-2.0f, -1.0f, -7.0f, 5.0f, 7.0f, 8.0f, 0.0f, false);
        this.head.setTextureOffset(50, 51).addBox(-0.5f, -1.0f, -19.0f, 2.0f, 2.0f, 12.0f, 0.0f, false);
        this.head.setTextureOffset(42, 17).addBox(0.0f, -1.0f, -31.0f, 1.0f, 1.0f, 12.0f, 0.0f, false);
        this.flag = new AdvancedModelBox((AdvancedEntityModel)this, "flag");
        this.flag.setRotationPoint(0.5f, -10.0f, -15.0f);
        this.body.addChild((BasicModelPart)this.flag);
        this.setRotationAngle(this.flag, 0.0f, 0.1309f, 0.0f);
        this.flag.setTextureOffset(0, 0).addBox(0.0f, -5.0f, 0.0f, 0.0f, 12.0f, 18.0f, 0.0f, false);
        this.left_fin = new AdvancedModelBox((AdvancedEntityModel)this, "left_fin");
        this.left_fin.setRotationPoint(2.0f, 7.0f, -10.0f);
        this.body.addChild((BasicModelPart)this.left_fin);
        this.setRotationAngle(this.left_fin, 0.0f, 0.0f, 0.7854f);
        this.left_fin.setTextureOffset(19, 0).addBox(0.0f, 0.0f, -2.0f, 10.0f, 1.0f, 4.0f, 0.0f, false);
        this.right_fin = new AdvancedModelBox((AdvancedEntityModel)this, "right_fin");
        this.right_fin.setRotationPoint(-2.0f, 7.0f, -10.0f);
        this.body.addChild((BasicModelPart)this.right_fin);
        this.setRotationAngle(this.right_fin, 0.0f, 0.0f, -0.7854f);
        this.right_fin.setTextureOffset(19, 0).addBox(-10.0f, 0.0f, -2.0f, 10.0f, 1.0f, 4.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, 0.0f, 5.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(23, 34).addBox(-1.0f, -1.0f, -1.0f, 2.0f, 2.0f, 17.0f, 0.0f, false);
        this.tail.setTextureOffset(9, 0).addBox(0.0f, -3.0f, 0.0f, 0.0f, 2.0f, 2.0f, 0.0f, false);
        this.tail.setTextureOffset(57, 17).addBox(0.0f, -2.0f, 4.0f, 0.0f, 1.0f, 10.0f, 0.0f, false);
        this.tail.setTextureOffset(42, 0).addBox(-2.0f, 1.0f, 2.0f, 4.0f, 5.0f, 11.0f, 0.0f, false);
        this.tail.setTextureOffset(0, 0).addBox(0.0f, 4.0f, 5.0f, 0.0f, 6.0f, 8.0f, 0.0f, false);
        this.tail_fin = new AdvancedModelBox((AdvancedEntityModel)this, "tail_fin");
        this.tail_fin.setRotationPoint(0.0f, 0.0f, 15.0f);
        this.tail.addChild((BasicModelPart)this.tail_fin);
        this.tail_fin.setTextureOffset(0, 31).addBox(0.0f, -12.0f, 0.0f, 0.0f, 25.0f, 11.0f, 0.0f, false);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntitySkelewag.ANIMATION_STAB);
        this.animator.startKeyframe(3);
        this.animator.move(this.body, 0.0f, 0.0f, 10.0f);
        this.animator.move(this.head, 1.0f, 0.0f, -1.0f);
        this.animator.rotate(this.body, Maths.rad(-10.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(10.0), Maths.rad(-5.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(2);
        this.animator.startKeyframe(2);
        this.animator.move(this.body, 0.0f, 0.0f, -10.0f);
        this.animator.rotate(this.body, 0.0f, 0.0f, Maths.rad(-10.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(3);
        this.animator.setAnimation(EntitySkelewag.ANIMATION_SLASH);
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, 5.0f);
        this.animator.rotate(this.body, 0.0f, Maths.rad(-40.0), 0.0f);
        this.animator.rotate(this.tail, 0.0f, Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.head, Maths.rad(10.0), Maths.rad(-10.0), Maths.rad(-10.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, 5.0f);
        this.animator.rotate(this.body, 0.0f, Maths.rad(40.0), 0.0f);
        this.animator.rotate(this.tail, 0.0f, Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.head, Maths.rad(-10.0), Maths.rad(10.0), Maths.rad(10.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, 5.0f);
        this.animator.rotate(this.body, 0.0f, Maths.rad(-40.0), 0.0f);
        this.animator.rotate(this.tail, 0.0f, Maths.rad(20.0), 0.0f);
        this.animator.rotate(this.head, Maths.rad(-10.0), Maths.rad(-10.0), Maths.rad(-10.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, 5.0f);
        this.animator.rotate(this.body, 0.0f, Maths.rad(40.0), 0.0f);
        this.animator.rotate(this.tail, 0.0f, Maths.rad(-20.0), 0.0f);
        this.animator.rotate(this.head, Maths.rad(10.0), Maths.rad(10.0), Maths.rad(10.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
    }

    public void setupAnim(EntitySkelewag entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float idleSpeed = 0.2f;
        float idleDegree = 0.3f;
        float swimSpeed = 0.55f;
        float swimDegree = 0.5f;
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float landProgress = entity.prevOnLandProgress + (entity.onLandProgress - entity.prevOnLandProgress) * partialTick;
        float fallApartProgress = entity.f_20919_ > 0 ? ((float)entity.f_20919_ + partialTick) / 20.0f : 0.0f;
        this.progressRotationPrev(this.body, landProgress, 0.0f, 0.0f, Maths.rad(-90.0), 5.0f);
        this.progressPositionPrev(this.body, landProgress, 0.0f, 3.0f, 6.0f, 5.0f);
        this.progressPositionPrev(this.tail, fallApartProgress, 0.0f, 0.0f, 4.0f, 1.0f);
        this.progressPositionPrev(this.tail_fin, fallApartProgress, 0.0f, 0.0f, 4.0f, 1.0f);
        this.progressPositionPrev(this.right_fin, fallApartProgress, 0.0f, 1.0f, 2.0f, 1.0f);
        this.progressPositionPrev(this.left_fin, fallApartProgress, 0.0f, 1.0f, 2.0f, 1.0f);
        this.progressPositionPrev(this.head, fallApartProgress, 0.0f, 0.0f, -1.0f, 1.0f);
        this.progressRotationPrev(this.right_fin, fallApartProgress, 0.0f, Maths.rad(25.0), 0.0f, 1.0f);
        this.progressRotationPrev(this.left_fin, fallApartProgress, 0.0f, Maths.rad(-25.0), 0.0f, 1.0f);
        AdvancedModelBox[] tailBoxes = new AdvancedModelBox[]{this.body, this.tail, this.tail_fin};
        this.chainSwing(tailBoxes, idleSpeed, idleDegree * 0.1f, 3.0, ageInTicks, 1.0f);
        this.bob(this.body, idleSpeed, idleDegree, false, ageInTicks, 1.0f);
        this.bob(this.left_fin, idleSpeed, idleDegree, false, ageInTicks, 1.0f);
        this.bob(this.right_fin, idleSpeed, idleDegree, false, ageInTicks, 1.0f);
        this.swing(this.flag, idleSpeed, idleDegree * 0.2f, false, 3.0f, 0.05f, ageInTicks, 1.0f);
        this.chainSwing(tailBoxes, swimSpeed, swimDegree, -2.0, limbSwing, limbSwingAmount);
        this.swing(this.head, swimSpeed, swimDegree, true, -0.5f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.left_fin, swimSpeed, swimDegree, true, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.right_fin, swimSpeed, swimDegree, false, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.left_fin, swimSpeed, -1.5f * swimDegree, false, limbSwing, limbSwingAmount);
        this.bob(this.right_fin, swimSpeed, -1.5f * swimDegree, false, limbSwing, limbSwingAmount);
        this.swing(this.flag, swimSpeed, swimDegree * 0.6f, false, 2.0f, 0.3f, limbSwing, limbSwingAmount);
        this.body.rotateAngleX += headPitch * ((float)Math.PI / 180);
        this.head.rotateAngleX -= headPitch * 0.5f * ((float)Math.PI / 180);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.tail, (Object)this.tail_fin, (Object)this.head, (Object)this.left_fin, (Object)this.right_fin, (Object)this.flag);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
