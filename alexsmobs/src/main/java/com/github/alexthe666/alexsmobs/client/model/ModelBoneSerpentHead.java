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

import com.github.alexthe666.alexsmobs.entity.EntityBoneSerpent;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelBoneSerpentHead
extends AdvancedEntityModel<EntityBoneSerpent> {
    private final AdvancedModelBox head;
    private final AdvancedModelBox hornL;
    private final AdvancedModelBox hornR;
    private final AdvancedModelBox middlehorn;
    private final AdvancedModelBox headtop;
    private final AdvancedModelBox jaw;

    public ModelBoneSerpentHead() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, 16.0f, 8.0f);
        this.hornL = new AdvancedModelBox((AdvancedEntityModel)this, "hornL");
        this.hornL.setPos(4.1f, -7.0f, -1.0f);
        this.head.addChild((BasicModelPart)this.hornL);
        this.setRotationAngle(this.hornL, 0.6545f, 0.3927f, 0.0f);
        this.hornL.setTextureOffset(61, 42).addBox(-0.1f, -3.0f, -5.0f, 5.0f, 5.0f, 16.0f, 0.0f, false);
        this.hornR = new AdvancedModelBox((AdvancedEntityModel)this, "hornR");
        this.hornR.setPos(-4.1f, -7.0f, -1.0f);
        this.head.addChild((BasicModelPart)this.hornR);
        this.setRotationAngle(this.hornR, 0.6545f, -0.3927f, 0.0f);
        this.hornR.setTextureOffset(61, 42).addBox(-4.9f, -3.0f, -5.0f, 5.0f, 5.0f, 16.0f, 0.0f, true);
        this.middlehorn = new AdvancedModelBox((AdvancedEntityModel)this, "middlehorn");
        this.middlehorn.setPos(-2.9f, -6.0f, -1.0f);
        this.head.addChild((BasicModelPart)this.middlehorn);
        this.setRotationAngle(this.middlehorn, 0.6545f, 0.0f, 0.0f);
        this.middlehorn.setTextureOffset(67, 67).addBox(-0.1f, -4.0f, -5.0f, 6.0f, 6.0f, 22.0f, 0.0f, false);
        this.headtop = new AdvancedModelBox((AdvancedEntityModel)this, "headtop");
        this.headtop.setPos(0.0f, -2.5f, 0.0f);
        this.head.addChild((BasicModelPart)this.headtop);
        this.headtop.setTextureOffset(0, 0).addBox(-9.0f, -4.5f, -30.0f, 18.0f, 11.0f, 30.0f, 0.0f, false);
        this.jaw = new AdvancedModelBox((AdvancedEntityModel)this, "jaw");
        this.jaw.setPos(0.0f, 2.5f, -2.0f);
        this.head.addChild((BasicModelPart)this.jaw);
        this.jaw.setTextureOffset(0, 42).addBox(-8.0f, -1.5f, -26.0f, 16.0f, 7.0f, 28.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityBoneSerpent entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180);
        float walkSpeed = 0.35f;
        float walkDegree = 3.0f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.2f;
        double walkOffset = 0.0;
        this.walk(this.jaw, idleSpeed * 0.5f, idleDegree * 0.3f, false, -1.2f, 0.0f, ageInTicks, 1.0f);
        this.head.rotationPointY += (float)(Math.sin((double)(limbSwing * walkSpeed) + walkOffset) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
        this.walk(this.jaw, walkSpeed * 1.0f, walkDegree * 0.03f, false, -1.2f, 0.0f, limbSwing, limbSwingAmount);
        this.head.rotationPointY += (float)(Math.sin((double)(ageInTicks * idleSpeed) + walkOffset) * 1.0 * (double)idleDegree - (double)(1.0f * idleDegree));
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.head);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.head, (Object)this.hornL, (Object)this.hornR, (Object)this.middlehorn, (Object)this.headtop, (Object)this.jaw);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
