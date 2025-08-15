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

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;

public class ModelAncientDart
extends AdvancedEntityModel<Entity> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox main;
    private final AdvancedModelBox feathers;
    private final AdvancedModelBox cube_r1;
    private final AdvancedModelBox cube_r2;

    public ModelAncientDart() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 0.0f, 0.0f);
        this.main = new AdvancedModelBox((AdvancedEntityModel)this, "main");
        this.main.setPos(0.0f, -1.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.main);
        this.main.setTextureOffset(11, 0).addBox(-1.0f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
        this.main.setTextureOffset(0, 0).addBox(-0.5f, -0.5f, -5.0f, 1.0f, 1.0f, 4.0f, 0.0f, false);
        this.feathers = new AdvancedModelBox((AdvancedEntityModel)this, "feathers");
        this.feathers.setPos(0.0f, 1.0f, 1.0f);
        this.main.addChild((BasicModelPart)this.feathers);
        this.cube_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "cube_r1");
        this.cube_r1.setPos(0.0f, -1.0f, 0.5f);
        this.feathers.addChild((BasicModelPart)this.cube_r1);
        this.setRotationAngle(this.cube_r1, 0.0f, 0.0f, 0.7854f);
        this.cube_r1.setTextureOffset(0, 6).addBox(0.0f, -1.5f, -0.5f, 0.0f, 3.0f, 3.0f, 0.0f, false);
        this.cube_r2 = new AdvancedModelBox((AdvancedEntityModel)this, "cube_r2");
        this.cube_r2.setPos(0.0f, -1.0f, 0.5f);
        this.feathers.addChild((BasicModelPart)this.cube_r2);
        this.setRotationAngle(this.cube_r2, 0.0f, 0.0f, -0.7854f);
        this.cube_r2.setTextureOffset(7, 6).addBox(0.0f, -1.5f, -0.5f, 0.0f, 3.0f, 3.0f, 0.0f, false);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.main, (Object)this.feathers, (Object)this.cube_r1, (Object)this.cube_r2);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void m_6973_(Entity entity, float v, float v1, float v2, float v3, float v4) {
    }

    public void setRotationAngle(AdvancedModelBox modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
