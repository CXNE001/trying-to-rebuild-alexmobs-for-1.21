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

import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateDoor;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;

public class ModelEndPirateDoor
extends AdvancedEntityModel<Entity> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox doorRightHinge;
    private final AdvancedModelBox doorLeftHinge;

    public ModelEndPirateDoor() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.doorRightHinge = new AdvancedModelBox((AdvancedEntityModel)this, "doorRightHinge");
        this.doorRightHinge.setRotationPoint(7.0f, -24.0f, -7.0f);
        this.root.addChild((BasicModelPart)this.doorRightHinge);
        this.doorRightHinge.setTextureOffset(0, 0).addBox(-15.0f, -24.0f, -1.0f, 16.0f, 48.0f, 2.0f, 0.0f, false);
        this.doorLeftHinge = new AdvancedModelBox((AdvancedEntityModel)this, "doorLeftHinge");
        this.doorLeftHinge.setRotationPoint(-7.0f, -24.0f, -7.0f);
        this.root.addChild((BasicModelPart)this.doorLeftHinge);
        this.doorLeftHinge.setTextureOffset(0, 0).addBox(-1.0f, -24.0f, -1.0f, 16.0f, 48.0f, 2.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.doorRightHinge, (Object)this.doorLeftHinge);
    }

    public void m_6973_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void renderDoor(TileEntityEndPirateDoor door, float partialTick, boolean left) {
        this.resetToDefaultPose();
        float ageInTicks = (float)door.ticksExisted + partialTick;
        float openAmount = door.getOpenProgress(partialTick);
        double d = Math.sin(ageInTicks * 0.8f) - 0.5;
        float wiggle = (float)((double)door.getWiggleProgress(partialTick) * d * Math.PI * (double)0.1f);
        if (left) {
            this.doorRightHinge.showModel = false;
            this.doorLeftHinge.showModel = true;
        } else {
            this.doorRightHinge.showModel = true;
            this.doorLeftHinge.showModel = false;
        }
        this.doorRightHinge.rotateAngleY = (float)((double)this.doorRightHinge.rotateAngleY + ((double)openAmount * Math.PI * 0.5 + (double)wiggle));
        this.doorLeftHinge.rotateAngleY = (float)((double)this.doorLeftHinge.rotateAngleY - ((double)openAmount * Math.PI * 0.5 + (double)wiggle));
    }
}
