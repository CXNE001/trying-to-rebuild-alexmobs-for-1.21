/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.world.entity.Entity
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityVoidWormShot;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;

public class ModelVoidWormShot
extends AdvancedEntityModel<Entity> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox glass;
    private final AdvancedModelBox cube;

    public ModelVoidWormShot() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.glass = new AdvancedModelBox((AdvancedEntityModel)this, "glass");
        this.glass.setPos(0.0f, -5.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.glass);
        this.glass.setTextureOffset(0, 21).addBox(-4.0f, -4.0f, -4.0f, 8.0f, 8.0f, 8.0f, 0.0f, false);
        this.cube = new AdvancedModelBox((AdvancedEntityModel)this, "cube");
        this.cube.setPos(0.0f, -5.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.cube);
        this.cube.setTextureOffset(0, 0).addBox(-5.0f, -5.0f, -5.0f, 10.0f, 10.0f, 10.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.cube, (Object)this.glass);
    }

    public void m_6973_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }

    public void animate(EntityVoidWormShot entityIn, float ageInTicks) {
        this.resetToDefaultPose();
        float innerScale = (float)(1.0 + 0.25 * Math.abs(Math.sin(ageInTicks * 0.6f)));
        float outerScale = (float)(1.0 + 0.5 * Math.abs(Math.cos(ageInTicks * 0.2f)));
        this.glass.setScale(innerScale, innerScale, innerScale);
        this.glass.rotateAngleX += ageInTicks * 0.25f;
        this.cube.rotateAngleX += ageInTicks * 0.5f;
        this.glass.setShouldScaleChildren(false);
        this.cube.setScale(outerScale, outerScale, outerScale);
    }
}
