/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.tileentity.TileEntityVoidWormBeak;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class ModelVoidWormBeak
extends AdvancedEntityModel<Entity> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox left;
    private final AdvancedModelBox right;

    public ModelVoidWormBeak() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.left = new AdvancedModelBox((AdvancedEntityModel)this, "left");
        this.left.setPos(0.0f, 0.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.left);
        this.left.setTextureOffset(0, 0).addBox(-0.1f, -12.9f, -3.5f, 7.0f, 13.0f, 7.0f, -0.1f, false);
        this.right = new AdvancedModelBox((AdvancedEntityModel)this, "right");
        this.right.setPos(0.0f, 0.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.right);
        this.right.setTextureOffset(0, 21).addBox(-7.0f, -13.0f, -3.5f, 7.0f, 13.0f, 7.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.left, (Object)this.right);
    }

    public void m_6973_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void renderBeak(TileEntityVoidWormBeak beak, float partialTick) {
        this.resetToDefaultPose();
        float amount = beak.getChompProgress(partialTick) * 0.2f;
        float ageInTicks = (float)beak.ticksExisted + partialTick;
        this.flap(this.left, 0.5f, 0.5f, false, 0.0f, 0.3f, ageInTicks, amount);
        this.flap(this.right, 0.5f, -0.5f, false, 0.0f, -0.3f, ageInTicks, amount);
        float rotation = Mth.m_14089_((float)(ageInTicks * 0.5f)) * 0.5f * amount + 0.3f * amount;
        this.left.rotationPointY -= rotation * 4.5f;
        this.right.rotationPointY -= rotation * 4.5f;
    }
}
