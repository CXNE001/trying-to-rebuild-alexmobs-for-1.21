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

import com.github.alexthe666.alexsmobs.entity.EntityFart;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelFart
extends AdvancedEntityModel<EntityFart> {
    private final AdvancedModelBox main;
    private final AdvancedModelBox cube_r1;
    private final AdvancedModelBox cube_r2;

    public ModelFart() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.main = new AdvancedModelBox((AdvancedEntityModel)this);
        this.main.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.main.setTextureOffset(0, 0).addBox(-4.0f, -4.0f, -5.0f, 8.0f, 8.0f, 11.0f, 0.0f, false);
        this.cube_r1 = new AdvancedModelBox((AdvancedEntityModel)this);
        this.cube_r1.setRotationPoint(0.0f, 0.0f, 0.5f);
        this.main.addChild((BasicModelPart)this.cube_r1);
        this.setRotationAngle(this.cube_r1, 0.0f, 0.0f, -0.7854f);
        this.cube_r1.setTextureOffset(0, 20).addBox(0.0f, -4.0f, -2.5f, 0.0f, 8.0f, 11.0f, 0.0f, true);
        this.cube_r2 = new AdvancedModelBox((AdvancedEntityModel)this);
        this.cube_r2.setRotationPoint(0.0f, 0.0f, 0.5f);
        this.main.addChild((BasicModelPart)this.cube_r2);
        this.setRotationAngle(this.cube_r2, 0.0f, 0.0f, 0.7854f);
        this.cube_r2.setTextureOffset(0, 20).addBox(0.0f, -4.0f, -2.5f, 0.0f, 8.0f, 11.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.main);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.main, (Object)this.cube_r1, (Object)this.cube_r2);
    }

    public void setupAnim(EntityFart entity, float limbSwing, float limbSwingAmount, float partialTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float f = Math.min((float)entity.f_19797_ + partialTicks, 30.0f) / 30.0f;
        float expand = 1.5f * f;
        this.main.setScale(expand * 2.0f + 1.0f, expand * 2.0f + 1.0f, 1.0f);
        this.cube_r1.setScale(1.0f, 1.0f, expand * 1.5f + 1.0f);
        this.cube_r2.setScale(1.0f, 1.0f, expand * 1.5f + 1.0f);
        this.cube_r1.rotationPointZ += expand * 3.0f;
        this.cube_r2.rotationPointZ += expand * 3.0f;
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
