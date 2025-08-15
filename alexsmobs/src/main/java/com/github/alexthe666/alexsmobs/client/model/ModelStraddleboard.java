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

import com.github.alexthe666.alexsmobs.entity.EntityStraddleboard;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelStraddleboard
extends AdvancedEntityModel<EntityStraddleboard> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox hair_left;
    private final AdvancedModelBox hair_right;
    private final AdvancedModelBox spikes;
    private final AdvancedModelBox front;
    private final AdvancedModelBox spikes_front;

    public ModelStraddleboard() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, 0.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-6.0f, -2.0f, -10.0f, 12.0f, 2.0f, 26.0f, 0.0f, false);
        this.hair_left = new AdvancedModelBox((AdvancedEntityModel)this, "hair_left");
        this.hair_left.setPos(6.0f, -2.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.hair_left);
        this.setRotationAngle(this.hair_left, 0.0f, 0.0f, 0.8727f);
        this.hair_left.setTextureOffset(0, 29).addBox(0.0f, -10.0f, -2.0f, 0.0f, 10.0f, 24.0f, 0.0f, false);
        this.hair_right = new AdvancedModelBox((AdvancedEntityModel)this, "hair_right");
        this.hair_right.setPos(-6.0f, -2.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.hair_right);
        this.setRotationAngle(this.hair_right, 0.0f, 0.0f, -0.8727f);
        this.hair_right.setTextureOffset(0, 29).addBox(0.0f, -10.0f, -2.0f, 0.0f, 10.0f, 24.0f, 0.0f, true);
        this.spikes = new AdvancedModelBox((AdvancedEntityModel)this, "spikes");
        this.spikes.setPos(0.0f, -1.5f, 13.5f);
        this.body.addChild((BasicModelPart)this.spikes);
        this.spikes.setTextureOffset(25, 29).addBox(-4.0f, -5.5f, -3.5f, 8.0f, 11.0f, 7.0f, 0.0f, false);
        this.front = new AdvancedModelBox((AdvancedEntityModel)this, "front");
        this.front.setPos(0.0f, 0.0f, -10.0f);
        this.body.addChild((BasicModelPart)this.front);
        this.setRotationAngle(this.front, -0.1309f, 0.0f, 0.0f);
        this.front.setTextureOffset(48, 40).addBox(-5.0f, -2.0f, -8.0f, 10.0f, 2.0f, 8.0f, 0.0f, false);
        this.spikes_front = new AdvancedModelBox((AdvancedEntityModel)this, "spikes_front");
        this.spikes_front.setPos(0.0f, -4.0f, -3.5f);
        this.front.addChild((BasicModelPart)this.spikes_front);
        this.spikes_front.setTextureOffset(0, 0).addBox(-2.0f, -2.0f, -2.5f, 4.0f, 4.0f, 5.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.hair_left, (Object)this.hair_right, (Object)this.spikes, (Object)this.spikes_front, (Object)this.front);
    }

    public void setupAnim(EntityStraddleboard entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
    }

    public void animateBoard(EntityStraddleboard board, float ageInTicks) {
        this.resetToDefaultPose();
        this.walk(this.hair_right, 0.1f, 0.01f, false, 0.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.hair_left, 0.1f, 0.01f, false, 0.0f, -0.1f, ageInTicks, 1.0f);
        this.flap(this.hair_right, 0.1f, 0.1f, false, 0.0f, -0.1f, ageInTicks, 1.0f);
        this.flap(this.hair_left, 0.1f, 0.1f, true, 0.0f, -0.1f, ageInTicks, 1.0f);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z) {
        advancedModelBox.rotateAngleX = x;
        advancedModelBox.rotateAngleY = y;
        advancedModelBox.rotateAngleZ = z;
    }
}
