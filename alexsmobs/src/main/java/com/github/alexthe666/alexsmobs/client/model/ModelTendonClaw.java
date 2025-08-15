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

import com.github.alexthe666.alexsmobs.entity.EntityTendonSegment;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelTendonClaw
extends AdvancedEntityModel<EntityTendonSegment> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox claw1;
    private final AdvancedModelBox claw2;
    private final AdvancedModelBox claw3;

    public ModelTendonClaw() {
        this.texWidth = 16;
        this.texHeight = 16;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.claw1 = new AdvancedModelBox((AdvancedEntityModel)this, "claw1");
        this.claw1.setRotationPoint(0.0f, 0.0f, 0.7f);
        this.root.addChild((BasicModelPart)this.claw1);
        this.setRotationAngle(this.claw1, 0.2618f, 0.0f, 0.0f);
        this.claw1.setTextureOffset(0, 0).addBox(-1.5f, -8.0f, -2.0f, 3.0f, 8.0f, 2.0f, 0.0f, false);
        this.claw2 = new AdvancedModelBox((AdvancedEntityModel)this, "claw2");
        this.claw2.setRotationPoint(0.5f, 0.0f, 1.0f);
        this.root.addChild((BasicModelPart)this.claw2);
        this.setRotationAngle(this.claw2, 0.2618f, 0.0f, 2.1817f);
        this.claw2.setTextureOffset(0, 0).addBox(-1.5f, -8.0f, -2.0f, 3.0f, 8.0f, 2.0f, 0.0f, false);
        this.claw3 = new AdvancedModelBox((AdvancedEntityModel)this, "claw3");
        this.claw3.setRotationPoint(-0.5f, 0.0f, 1.0f);
        this.root.addChild((BasicModelPart)this.claw3);
        this.setRotationAngle(this.claw3, 0.2618f, 0.0f, -2.1817f);
        this.claw3.setTextureOffset(0, 0).addBox(-1.5f, -8.0f, -2.0f, 3.0f, 8.0f, 2.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.claw1, (Object)this.claw2, (Object)this.claw3);
    }

    public void setupAnim(EntityTendonSegment entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
    }

    public void setAttributes(float rotX, float rotY, float open) {
        this.resetToDefaultPose();
        this.resetToDefaultPose();
        this.root.rotateAngleX = Maths.rad(rotX);
        this.root.rotateAngleY = Maths.rad(rotY);
        this.progressRotationPrev(this.claw1, open, Maths.rad(45.0), 0.0f, 0.0f, 1.0f);
        this.progressRotationPrev(this.claw2, open, Maths.rad(45.0), 0.0f, 0.0f, 1.0f);
        this.progressRotationPrev(this.claw3, open, Maths.rad(45.0), 0.0f, 0.0f, 1.0f);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
