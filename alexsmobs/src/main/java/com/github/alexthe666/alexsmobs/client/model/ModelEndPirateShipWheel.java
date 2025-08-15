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

import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateShipWheel;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;

public class ModelEndPirateShipWheel
extends AdvancedEntityModel<Entity> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox wheel;

    public ModelEndPirateShipWheel() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.wheel = new AdvancedModelBox((AdvancedEntityModel)this, "wheel");
        this.wheel.setRotationPoint(0.0f, -2.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.wheel);
        this.wheel.rotateAngleX = -1.5708f;
        this.wheel.setTextureOffset(0, 37).addBox(-9.0f, 6.0f, -1.0f, 18.0f, 3.0f, 2.0f, 0.0f, false);
        this.wheel.setTextureOffset(37, 39).addBox(-3.0f, -3.0f, -2.0f, 6.0f, 6.0f, 4.0f, 0.0f, false);
        this.wheel.setTextureOffset(0, 0).addBox(-15.0f, -15.0f, 0.0f, 30.0f, 30.0f, 0.0f, 0.0f, false);
        this.wheel.setTextureOffset(0, 31).addBox(-9.0f, -9.0f, -1.0f, 18.0f, 3.0f, 2.0f, 0.0f, false);
        this.wheel.setTextureOffset(11, 43).addBox(-9.0f, -6.0f, -1.0f, 3.0f, 12.0f, 2.0f, 0.0f, false);
        this.wheel.setTextureOffset(0, 43).addBox(6.0f, -6.0f, -1.0f, 3.0f, 12.0f, 2.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.wheel);
    }

    public void m_6973_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void renderWheel(TileEntityEndPirateShipWheel wheel, float partialTick) {
        this.resetToDefaultPose();
        this.wheel.rotateAngleY = Maths.rad(wheel.getWheelRot(partialTick));
    }
}
