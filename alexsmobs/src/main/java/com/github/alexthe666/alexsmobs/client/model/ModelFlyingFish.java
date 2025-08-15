/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityFlyingFish;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelFlyingFish
extends AdvancedEntityModel<EntityFlyingFish> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox left_pectoralFin;
    private final AdvancedModelBox right_pectoralFin;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox tail_fin;
    private final AdvancedModelBox left_pelvicFin;
    private final AdvancedModelBox right_pelvicFin;

    public ModelFlyingFish() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -2.0f, -2.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(11, 16).addBox(-1.5f, -2.0f, -4.0f, 3.0f, 4.0f, 8.0f, 0.0f, false);
        this.body.setTextureOffset(10, 6).addBox(-1.5f, -2.0f, -5.0f, 3.0f, 3.0f, 1.0f, 0.0f, false);
        this.left_pectoralFin = new AdvancedModelBox((AdvancedEntityModel)this, "left_pectoralFin");
        this.left_pectoralFin.setRotationPoint(1.5f, 0.0f, -1.0f);
        this.body.addChild((BasicModelPart)this.left_pectoralFin);
        this.setRotationAngle(this.left_pectoralFin, -0.7503f, -1.3169f, -0.8498f);
        this.left_pectoralFin.setTextureOffset(0, 0).addBox(0.0f, 0.0f, -2.0f, 13.0f, 0.0f, 5.0f, 0.0f, false);
        this.right_pectoralFin = new AdvancedModelBox((AdvancedEntityModel)this, "right_pectoralFin");
        this.right_pectoralFin.setRotationPoint(-1.5f, 0.0f, -1.0f);
        this.body.addChild((BasicModelPart)this.right_pectoralFin);
        this.setRotationAngle(this.right_pectoralFin, -0.7503f, 1.3169f, 0.8498f);
        this.right_pectoralFin.setTextureOffset(0, 0).addBox(-13.0f, 0.0f, -2.0f, 13.0f, 0.0f, 5.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, -1.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(26, 6).addBox(-1.0f, -1.0f, 0.0f, 2.0f, 3.0f, 8.0f, 0.0f, false);
        this.tail.setTextureOffset(0, 0).addBox(0.0f, -2.0f, 2.0f, 0.0f, 1.0f, 2.0f, 0.0f, false);
        this.tail_fin = new AdvancedModelBox((AdvancedEntityModel)this, "tail_fin");
        this.tail_fin.setRotationPoint(0.0f, 1.0f, 6.0f);
        this.tail.addChild((BasicModelPart)this.tail_fin);
        this.tail_fin.setTextureOffset(0, 6).addBox(0.0f, -5.0f, -1.0f, 0.0f, 8.0f, 9.0f, 0.0f, false);
        this.left_pelvicFin = new AdvancedModelBox((AdvancedEntityModel)this, "left_pelvicFin");
        this.left_pelvicFin.setRotationPoint(1.0f, 2.0f, 1.0f);
        this.tail.addChild((BasicModelPart)this.left_pelvicFin);
        this.setRotationAngle(this.left_pelvicFin, 0.0f, 0.0f, -0.5672f);
        this.left_pelvicFin.setTextureOffset(0, 6).addBox(0.0f, 0.0f, 0.0f, 0.0f, 4.0f, 4.0f, 0.0f, false);
        this.right_pelvicFin = new AdvancedModelBox((AdvancedEntityModel)this, "right_pelvicFin");
        this.right_pelvicFin.setRotationPoint(-1.0f, 2.0f, 1.0f);
        this.tail.addChild((BasicModelPart)this.right_pelvicFin);
        this.setRotationAngle(this.right_pelvicFin, 0.0f, 0.0f, 0.5672f);
        this.right_pelvicFin.setTextureOffset(0, 6).addBox(0.0f, 0.0f, 0.0f, 0.0f, 4.0f, 4.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityFlyingFish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.2f;
        float idleDegree = 0.3f;
        float swimSpeed = 0.55f;
        float swimDegree = 0.5f;
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float flyProgress = entity.prevFlyProgress + (entity.flyProgress - entity.prevFlyProgress) * partialTick;
        float landProgress = entity.prevOnLandProgress + (entity.onLandProgress - entity.prevOnLandProgress) * partialTick;
        float swimProgress = Math.max(0.0f, 5.0f - flyProgress) * 0.2f;
        AdvancedModelBox[] tailBoxes = new AdvancedModelBox[]{this.body, this.tail, this.tail_fin};
        this.progressRotationPrev(this.left_pectoralFin, flyProgress, Maths.rad(45.0), Maths.rad(80.0), Maths.rad(45.0), 5.0f);
        this.progressRotationPrev(this.right_pectoralFin, flyProgress, Maths.rad(45.0), Maths.rad(-80.0), Maths.rad(-45.0), 5.0f);
        this.progressRotationPrev(this.left_pelvicFin, flyProgress, 0.0f, 0.0f, Maths.rad(-35.0), 5.0f);
        this.progressRotationPrev(this.right_pelvicFin, flyProgress, 0.0f, 0.0f, Maths.rad(35.0), 5.0f);
        this.progressPositionPrev(this.left_pectoralFin, flyProgress, 0.0f, -1.0f, 1.0f, 5.0f);
        this.progressPositionPrev(this.right_pectoralFin, flyProgress, 0.0f, -1.0f, 1.0f, 5.0f);
        this.progressRotationPrev(this.body, landProgress, 0.0f, 0.0f, Maths.rad(-90.0), 5.0f);
        this.progressRotation(this.left_pectoralFin, landProgress, 0.0f, 0.0f, Maths.rad(70.0), 5.0f);
        this.progressRotation(this.right_pectoralFin, landProgress, 0.0f, 0.0f, Maths.rad(-80.0), 5.0f);
        this.bob(this.body, idleSpeed, idleDegree, false, ageInTicks, 1.0f);
        this.chainSwing(tailBoxes, idleSpeed, idleDegree * 0.1f, -2.5, ageInTicks, 1.0f);
        this.flap(this.left_pelvicFin, idleSpeed, idleDegree, false, 3.0f, 0.05f, ageInTicks, 1.0f);
        this.flap(this.right_pelvicFin, idleSpeed, idleDegree, true, 3.0f, 0.05f, ageInTicks, 1.0f);
        this.flap(this.left_pectoralFin, idleSpeed, idleDegree * 0.25f, true, -1.0f, -0.12f, ageInTicks, 1.0f);
        this.flap(this.right_pectoralFin, idleSpeed, idleDegree * 0.25f, false, -1.0f, -0.12f, ageInTicks, 1.0f);
        this.chainSwing(tailBoxes, swimSpeed, swimDegree * 0.9f, -2.0, limbSwing, limbSwingAmount * swimProgress);
        this.chainSwing(tailBoxes, swimSpeed, swimDegree * 0.3f, -1.0, limbSwing, limbSwingAmount * flyProgress * 0.2f);
        this.flap(this.left_pectoralFin, swimSpeed * 2.0f, swimDegree * 0.2f, true, 1.0f, 0.1f, ageInTicks, flyProgress * 0.2f);
        this.flap(this.right_pectoralFin, swimSpeed * 2.0f, swimDegree * 0.2f, false, 1.0f, 0.1f, ageInTicks, flyProgress * 0.2f);
        this.flap(this.left_pelvicFin, swimSpeed * 2.0f, swimDegree * 0.2f, true, 1.0f, 0.3f, ageInTicks, flyProgress * 0.2f);
        this.flap(this.right_pelvicFin, swimSpeed * 2.0f, swimDegree * 0.2f, false, 1.0f, 0.3f, ageInTicks, flyProgress * 0.2f);
        this.body.rotateAngleX += headPitch * ((float)Math.PI / 180);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.tail, (Object)this.tail_fin, (Object)this.left_pectoralFin, (Object)this.left_pelvicFin, (Object)this.right_pectoralFin, (Object)this.right_pelvicFin);
    }

    public void setRotationAngle(AdvancedModelBox modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
