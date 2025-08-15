/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.util.Mth
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityVoidWorm;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelVoidWorm
extends AdvancedEntityModel<EntityVoidWorm> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox neck;
    private final AdvancedModelBox topfrills_left;
    private final AdvancedModelBox topfrills_right;
    private final AdvancedModelBox bottomfrills_left;
    private final AdvancedModelBox bottomfrills_right;
    private final AdvancedModelBox head;
    private final AdvancedModelBox eye_bottom_r1;
    private final AdvancedModelBox eye_top_r1;
    private final AdvancedModelBox topjaw;
    private final AdvancedModelBox bottomjaw;

    public ModelVoidWorm(float f) {
        this.texWidth = 256;
        this.texHeight = 256;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setPos(0.0f, -10.0f, 20.0f);
        this.root.addChild((BasicModelPart)this.neck);
        this.neck.setTextureOffset(0, 53).addBox(-10.0f, -10.0f, -28.0f, 20.0f, 20.0f, 28.0f, f, false);
        this.topfrills_left = new AdvancedModelBox((AdvancedEntityModel)this, "topfrills_left");
        this.topfrills_left.setPos(10.0f, -10.0f, -20.0f);
        this.neck.addChild((BasicModelPart)this.topfrills_left);
        this.setRotationAngle(this.topfrills_left, 0.0f, 0.0f, 0.7854f);
        this.topfrills_left.setTextureOffset(71, 76).addBox(0.0f, -9.0f, -7.0f, 0.0f, 9.0f, 26.0f, f, false);
        this.topfrills_right = new AdvancedModelBox((AdvancedEntityModel)this, "topfrills_right");
        this.topfrills_right.setPos(-10.0f, -10.0f, -20.0f);
        this.neck.addChild((BasicModelPart)this.topfrills_right);
        this.setRotationAngle(this.topfrills_right, 0.0f, 0.0f, -0.7854f);
        this.topfrills_right.setTextureOffset(71, 76).addBox(0.0f, -9.0f, -7.0f, 0.0f, 9.0f, 26.0f, f, true);
        this.bottomfrills_left = new AdvancedModelBox((AdvancedEntityModel)this, "bottomfrills_left");
        this.bottomfrills_left.setPos(10.0f, 10.0f, -20.0f);
        this.neck.addChild((BasicModelPart)this.bottomfrills_left);
        this.setRotationAngle(this.bottomfrills_left, 0.0f, 0.0f, 2.3562f);
        this.bottomfrills_left.setTextureOffset(71, 76).addBox(0.0f, -9.0f, -7.0f, 0.0f, 9.0f, 26.0f, f, false);
        this.bottomfrills_right = new AdvancedModelBox((AdvancedEntityModel)this, "bottomfrills_right");
        this.bottomfrills_right.setPos(-10.0f, 10.0f, -20.0f);
        this.neck.addChild((BasicModelPart)this.bottomfrills_right);
        this.setRotationAngle(this.bottomfrills_right, 0.0f, 0.0f, -2.3562f);
        this.bottomfrills_right.setTextureOffset(71, 76).addBox(0.0f, -9.0f, -7.0f, 0.0f, 9.0f, 26.0f, f, true);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, 0.0f, -28.0f);
        this.neck.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 0).addBox(-17.0f, -17.0f, -18.0f, 34.0f, 34.0f, 18.0f, f, false);
        this.head.setTextureOffset(25, 102).addBox(17.0f, -5.0f, -14.0f, 2.0f, 10.0f, 10.0f, f, false);
        this.head.setTextureOffset(0, 102).addBox(-19.0f, -5.0f, -14.0f, 2.0f, 10.0f, 10.0f, f, false);
        this.eye_bottom_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "eye_bottom_r1");
        this.eye_bottom_r1.setPos(0.0f, 18.0f, -9.0f);
        this.head.addChild((BasicModelPart)this.eye_bottom_r1);
        this.setRotationAngle(this.eye_bottom_r1, 0.0f, 0.0f, 1.5708f);
        this.eye_bottom_r1.setTextureOffset(0, 53).addBox(-1.0f, -5.0f, -5.0f, 2.0f, 10.0f, 10.0f, f, false);
        this.eye_top_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "eye_top_r1");
        this.eye_top_r1.setPos(0.0f, -18.0f, -9.0f);
        this.head.addChild((BasicModelPart)this.eye_top_r1);
        this.setRotationAngle(this.eye_top_r1, 0.0f, 0.0f, 1.5708f);
        this.eye_top_r1.setTextureOffset(69, 54).addBox(-1.0f, -5.0f, -5.0f, 2.0f, 10.0f, 10.0f, f, false);
        this.topjaw = new AdvancedModelBox((AdvancedEntityModel)this, "topjaw");
        this.topjaw.setPos(0.0f, 3.0f, -18.0f);
        this.head.addChild((BasicModelPart)this.topjaw);
        this.topjaw.setTextureOffset(98, 64).addBox(-5.0f, -10.0f, -16.0f, 10.0f, 10.0f, 16.0f, f, false);
        this.bottomjaw = new AdvancedModelBox((AdvancedEntityModel)this, "bottomjaw");
        this.bottomjaw.setPos(0.0f, -3.0f, -17.9f);
        this.head.addChild((BasicModelPart)this.bottomjaw);
        this.bottomjaw.setTextureOffset(89, 37).addBox(-5.0f, 0.0f, -16.0f, 10.0f, 10.0f, 16.0f, f - 0.1f, false);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityVoidWorm entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.root.rotateAngleX += headPitch * ((float)Math.PI / 180);
        this.root.rotationPointY += -3.0f - Mth.m_14036_((float)(headPitch * -0.125f), (float)-10.0f, (float)10.0f) * 0.3f;
        this.root.rotationPointZ += -15.0f + limbSwingAmount * 10.0f;
        float yawAmount = (entityIn.prevWormAngle + (entityIn.getWormAngle() - entityIn.prevWormAngle) * (ageInTicks - (float)entityIn.f_19797_)) / 57.295776f * 0.5f;
        this.neck.rotateAngleZ += yawAmount;
        float jawProgress = entityIn.prevJawProgress + (entityIn.jawProgress - entityIn.prevJawProgress) * (ageInTicks - (float)entityIn.f_19797_);
        this.progressRotationPrev(this.bottomjaw, jawProgress, Maths.rad(60.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.topjaw, jawProgress, Maths.rad(-60.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.bottomjaw, jawProgress, 0.0f, 2.0f, -5.0f, 5.0f);
        this.progressPositionPrev(this.topjaw, jawProgress, 0.0f, -2.0f, -5.0f, 5.0f);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.neck, (Object)this.head, (Object)this.bottomfrills_left, (Object)this.bottomfrills_right, (Object)this.eye_bottom_r1, (Object)this.eye_top_r1, (Object)this.topfrills_left, (Object)this.topfrills_right, (Object)this.topjaw, (Object)this.bottomjaw);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
