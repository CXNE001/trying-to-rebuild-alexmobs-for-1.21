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

import com.github.alexthe666.alexsmobs.entity.EntityBoneSerpentPart;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelBoneSerpentTail
extends AdvancedEntityModel<EntityBoneSerpentPart> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox tail;

    public ModelBoneSerpentTail() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, -4.75f, 0.0f);
        this.root.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 0).addBox(-5.0f, -5.25f, -8.0f, 10.0f, 10.0f, 16.0f, 0.0f, false);
        this.tail.setTextureOffset(0, 27).addBox(-1.0f, -6.25f, -8.0f, 2.0f, 1.0f, 16.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityBoneSerpentPart entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.tail.rotateAngleX = headPitch * ((float)Math.PI / 180);
        this.tail.rotateAngleY = netHeadYaw * ((float)Math.PI / 180);
        float walkSpeed = 0.35f;
        float walkDegree = 3.0f;
        float idleDegree = 0.7f;
        float idleSpeed = 0.2f;
        double walkOffset = entityIn.getBodyIndex() + 1;
        this.tail.rotationPointY += (float)(Math.sin((double)(limbSwing * walkSpeed) - walkOffset) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
        this.tail.rotationPointY += (float)(Math.sin((double)(ageInTicks * idleSpeed) - walkOffset) * 1.0 * (double)idleDegree - (double)(1.0f * idleDegree));
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.tail);
    }
}
