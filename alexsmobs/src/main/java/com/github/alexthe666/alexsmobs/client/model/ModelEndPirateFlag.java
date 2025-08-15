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

import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateFlag;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;

public class ModelEndPirateFlag
extends AdvancedEntityModel<Entity> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox rod;
    private final AdvancedModelBox flag1;
    private final AdvancedModelBox flag2;

    public ModelEndPirateFlag() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.rod = new AdvancedModelBox((AdvancedEntityModel)this, "rod");
        this.rod.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.rod);
        this.rod.setTextureOffset(29, 0).addBox(-1.0f, -16.0f, -1.0f, 2.0f, 16.0f, 2.0f, 0.0f, false);
        this.flag1 = new AdvancedModelBox((AdvancedEntityModel)this, "flag1");
        this.flag1.setRotationPoint(0.0f, -8.0f, 0.0f);
        this.rod.addChild((BasicModelPart)this.flag1);
        this.flag1.setTextureOffset(0, 17).addBox(1.0f, -8.0f, 0.0f, 14.0f, 16.0f, 0.0f, 0.0f, false);
        this.flag2 = new AdvancedModelBox((AdvancedEntityModel)this, "flag2");
        this.flag2.setRotationPoint(15.0f, 0.0f, 0.0f);
        this.flag1.addChild((BasicModelPart)this.flag2);
        this.flag2.setTextureOffset(0, 0).addBox(0.0f, -8.0f, 0.0f, 14.0f, 16.0f, 0.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.rod, (Object)this.flag1, (Object)this.flag2);
    }

    public void m_6973_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void renderFlag(TileEntityEndPirateFlag wheel, float partialTick) {
        this.resetToDefaultPose();
        float f = (float)wheel.ticksExisted + partialTick;
        float speed = (float)((double)0.6f + Math.sin(f * 0.1f) * 0.5);
        this.swing(this.flag1, 0.4f, 0.5f, false, 0.0f, 0.0f, f, speed);
        this.swing(this.flag2, 0.4f, 0.5f, false, -2.0f, 0.0f, f, speed);
    }
}
