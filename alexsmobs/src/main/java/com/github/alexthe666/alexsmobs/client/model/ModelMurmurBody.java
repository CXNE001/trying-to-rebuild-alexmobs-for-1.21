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

import com.github.alexthe666.alexsmobs.entity.EntityMurmur;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelMurmurBody
extends AdvancedEntityModel<EntityMurmur> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox arms;

    public ModelMurmurBody() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -14.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-7.0f, -13.0f, -5.0f, 14.0f, 14.0f, 10.0f, 0.0f, false);
        this.body.setTextureOffset(72, 20).addBox(-7.0f, 1.0f, -5.0f, 14.0f, 13.0f, 10.0f, 0.0f, false);
        this.arms = new AdvancedModelBox((AdvancedEntityModel)this, "arms");
        this.arms.setRotationPoint(0.0f, -8.5f, -1.0f);
        this.body.addChild((BasicModelPart)this.arms);
        this.arms.rotateAngleX = 0.4363f;
        this.arms.setTextureOffset(0, 25).addBox(-9.0f, -2.5f, -8.0f, 18.0f, 5.0f, 10.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.arms);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setupAnim(EntityMurmur entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float walkSpeed = 0.9f;
        float walkDegree = 0.6f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        this.body.rotationPointY = (float)((double)this.body.rotationPointY - Math.abs(Math.sin(0.9f * limbSwing) * (double)limbSwingAmount * 4.0));
        this.walk(this.arms, walkSpeed * 2.0f, walkDegree * 0.3f, false, -1.0f, 0.15f, limbSwing, limbSwingAmount);
        this.swing(this.arms, walkSpeed, walkDegree * 0.3f, false, -3.0f, 0.0f, limbSwing, limbSwingAmount);
        this.progressRotationPrev(this.body, limbSwingAmount, Maths.rad(15.0), 0.0f, 0.0f, 1.0f);
        this.progressPositionPrev(this.body, limbSwingAmount, 0.0f, 2.0f, 4.0f, 1.0f);
        this.walk(this.arms, idleSpeed, idleDegree, false, -1.0f, 0.15f, ageInTicks, 1.0f);
    }
}
