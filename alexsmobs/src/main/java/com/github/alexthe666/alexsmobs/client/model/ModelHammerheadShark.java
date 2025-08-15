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

import com.github.alexthe666.alexsmobs.entity.EntityHammerheadShark;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelHammerheadShark
extends AdvancedEntityModel<EntityHammerheadShark> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox main_body;
    private final AdvancedModelBox head;
    private final AdvancedModelBox head_hammer;
    private final AdvancedModelBox finL;
    private final AdvancedModelBox finR;
    private final AdvancedModelBox topfin;
    private final AdvancedModelBox tail1;
    private final AdvancedModelBox tail_finL;
    private final AdvancedModelBox tail_finR;
    private final AdvancedModelBox topfintail;
    private final AdvancedModelBox tail2;
    private final AdvancedModelBox tail3;
    private final AdvancedModelBox tailbottomend;
    private final AdvancedModelBox tailtopend;

    public ModelHammerheadShark() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.main_body = new AdvancedModelBox((AdvancedEntityModel)this, "main_body");
        this.main_body.setPos(0.0f, -6.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.main_body);
        this.main_body.setTextureOffset(0, 0).addBox(-5.0f, -4.0f, -14.0f, 10.0f, 10.0f, 25.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -1.0f, -14.5f);
        this.main_body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(40, 55).addBox(-4.0f, -1.0f, -6.5f, 8.0f, 7.0f, 7.0f, 0.0f, false);
        this.head_hammer = new AdvancedModelBox((AdvancedEntityModel)this, "head_hammer");
        this.head_hammer.setPos(0.0f, 1.5f, -7.0f);
        this.head.addChild((BasicModelPart)this.head_hammer);
        this.head_hammer.setTextureOffset(32, 36).addBox(-11.0f, -1.5f, -3.5f, 22.0f, 3.0f, 7.0f, 0.0f, false);
        this.finL = new AdvancedModelBox((AdvancedEntityModel)this, "finL");
        this.finL.setPos(6.0f, 6.0f, -6.5f);
        this.main_body.addChild((BasicModelPart)this.finL);
        this.setRotationAngle(this.finL, 0.0f, -0.2182f, 0.2618f);
        this.finL.setTextureOffset(47, 47).addBox(-1.0f, -1.0f, -1.0f, 14.0f, 1.0f, 6.0f, 0.0f, false);
        this.finR = new AdvancedModelBox((AdvancedEntityModel)this, "finR");
        this.finR.setPos(-6.0f, 6.0f, -6.5f);
        this.main_body.addChild((BasicModelPart)this.finR);
        this.setRotationAngle(this.finR, 0.0f, 0.2182f, -0.2618f);
        this.finR.setTextureOffset(47, 47).addBox(-13.0f, -1.0f, -1.0f, 14.0f, 1.0f, 6.0f, 0.0f, true);
        this.topfin = new AdvancedModelBox((AdvancedEntityModel)this, "topfin");
        this.topfin.setPos(0.0f, -4.0f, -3.5f);
        this.main_body.addChild((BasicModelPart)this.topfin);
        this.setRotationAngle(this.topfin, -0.2182f, 0.0f, 0.0f);
        this.topfin.setTextureOffset(0, 0).addBox(-1.0f, -13.0f, -2.0f, 2.0f, 14.0f, 7.0f, 0.0f, false);
        this.tail1 = new AdvancedModelBox((AdvancedEntityModel)this, "tail1");
        this.tail1.setPos(0.0f, -0.3f, 11.75f);
        this.main_body.addChild((BasicModelPart)this.tail1);
        this.tail1.setTextureOffset(0, 36).addBox(-4.0f, -2.5f, -0.75f, 8.0f, 8.0f, 15.0f, 0.0f, false);
        this.tail_finL = new AdvancedModelBox((AdvancedEntityModel)this, "tail_finL");
        this.tail_finL.setPos(3.0f, 5.3f, 5.75f);
        this.tail1.addChild((BasicModelPart)this.tail_finL);
        this.setRotationAngle(this.tail_finL, 0.0f, -0.48f, 1.0036f);
        this.tail_finL.setTextureOffset(64, 55).addBox(0.0f, -1.0f, 0.0f, 8.0f, 1.0f, 4.0f, 0.0f, false);
        this.tail_finR = new AdvancedModelBox((AdvancedEntityModel)this, "tail_finR");
        this.tail_finR.setPos(-3.0f, 5.3f, 5.75f);
        this.tail1.addChild((BasicModelPart)this.tail_finR);
        this.setRotationAngle(this.tail_finR, 0.0f, 0.48f, -1.0036f);
        this.tail_finR.setTextureOffset(64, 55).addBox(-8.0f, -1.0f, 0.0f, 8.0f, 1.0f, 4.0f, 0.0f, true);
        this.topfintail = new AdvancedModelBox((AdvancedEntityModel)this, "topfintail");
        this.topfintail.setPos(0.0f, -2.7f, 9.75f);
        this.tail1.addChild((BasicModelPart)this.topfintail);
        this.setRotationAngle(this.topfintail, -0.2182f, 0.0f, 0.0f);
        this.topfintail.setTextureOffset(0, 36).addBox(-0.5f, -4.0237f, -1.7836f, 1.0f, 5.0f, 4.0f, 0.0f, false);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this, "tail2");
        this.tail2.setPos(0.0f, 0.3f, 14.75f);
        this.tail1.addChild((BasicModelPart)this.tail2);
        this.tail2.setTextureOffset(46, 0).addBox(-2.5f, -2.0f, -0.5f, 5.0f, 6.0f, 14.0f, 0.0f, false);
        this.tail3 = new AdvancedModelBox((AdvancedEntityModel)this, "tail3");
        this.tail3.setPos(0.0f, 0.0f, 12.0f);
        this.tail2.addChild((BasicModelPart)this.tail3);
        this.tailbottomend = new AdvancedModelBox((AdvancedEntityModel)this, "tailbottomend");
        this.tailbottomend.setPos(-0.5f, 1.5f, 0.0f);
        this.tail3.addChild((BasicModelPart)this.tailbottomend);
        this.setRotationAngle(this.tailbottomend, -2.7489f, 0.0f, 0.0f);
        this.tailbottomend.setTextureOffset(17, 60).addBox(0.0f, -11.5f, -2.5f, 1.0f, 14.0f, 6.0f, 0.0f, false);
        this.tailtopend = new AdvancedModelBox((AdvancedEntityModel)this, "tailtopend");
        this.tailtopend.setPos(0.0f, -0.5f, 0.0f);
        this.tail3.addChild((BasicModelPart)this.tailtopend);
        this.setRotationAngle(this.tailtopend, -0.829f, 0.0f, 0.0f);
        this.tailtopend.setTextureOffset(0, 60).addBox(-1.0f, -16.5f, -1.5f, 2.0f, 19.0f, 6.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityHammerheadShark entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        AdvancedModelBox[] tailBoxes = new AdvancedModelBox[]{this.main_body, this.tail1, this.tail2, this.tail3};
        float swimSpeed = 0.4f;
        float swimDegree = 0.5f;
        this.chainSwing(tailBoxes, swimSpeed, swimDegree * 0.9f, -2.0, limbSwing, limbSwingAmount);
        this.bob(this.main_body, swimSpeed * 0.5f, swimDegree * 5.0f, false, limbSwing, limbSwingAmount);
        this.walk(this.topfin, swimSpeed, swimDegree * 0.1f, true, 1.0f, 0.2f, limbSwing, limbSwingAmount);
        this.walk(this.tail_finL, swimSpeed, swimDegree * 0.2f, true, 2.0f, 0.2f, limbSwing, limbSwingAmount);
        this.walk(this.tail_finR, swimSpeed, swimDegree * 0.2f, true, 2.0f, 0.2f, limbSwing, limbSwingAmount);
        this.flap(this.finL, swimSpeed, swimDegree * 0.6f, false, 1.0f, 0.1f, limbSwing, limbSwingAmount);
        this.flap(this.finR, swimSpeed, swimDegree * 0.6f, true, 1.0f, 0.1f, limbSwing, limbSwingAmount);
        this.swing(this.tail_finL, swimSpeed, swimDegree * 0.1f, false, 3.0f, -0.1f, limbSwing, limbSwingAmount);
        this.swing(this.tail_finR, swimSpeed, swimDegree * 0.1f, true, 3.0f, -0.1f, limbSwing, limbSwingAmount);
        this.swing(this.head, swimSpeed, swimDegree * 0.2f, true, 2.0f, 0.0f, limbSwing, limbSwingAmount);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.main_body, (Object)this.head, (Object)this.head_hammer, (Object)this.finL, (Object)this.finR, (Object)this.tail1, (Object)this.tail2, (Object)this.tail3, (Object)this.tail_finL, (Object)this.tail_finR, (Object)this.tailbottomend, (Object[])new AdvancedModelBox[]{this.tailtopend, this.topfintail, this.topfin});
    }

    public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z) {
        advancedModelBox.rotateAngleX = x;
        advancedModelBox.rotateAngleY = y;
        advancedModelBox.rotateAngleZ = z;
    }
}
