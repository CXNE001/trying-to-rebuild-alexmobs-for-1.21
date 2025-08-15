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
 *  net.minecraft.world.item.ItemStack
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateAnchorWinch;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class ModelEndPirateAnchorWinch
extends AdvancedEntityModel<Entity> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox chains;

    public ModelEndPirateAnchorWinch() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.chains = new AdvancedModelBox((AdvancedEntityModel)this, "chains");
        this.chains.setRotationPoint(0.0f, -8.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.chains);
        this.chains.setTextureOffset(0, 33).addBox(-8.0f, -5.0f, -5.0f, 16.0f, 10.0f, 10.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.chains);
    }

    public void m_6973_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void renderAnchor(TileEntityEndPirateAnchorWinch anchor, float partialTick, boolean east) {
        float f1;
        this.resetToDefaultPose();
        float timeWinching = (float)anchor.windCounter + partialTick;
        float f = anchor.getWindProgress(partialTick);
        float f2 = f1 = anchor.isWindingUp() ? 1.0f : -1.0f;
        if (anchor.isWinching()) {
            anchor.clientRoll = this.chains.rotateAngleX = timeWinching * 0.2f * f * f1;
        } else {
            float rollDeg = (float)Mth.m_14175_((double)Math.toDegrees(anchor.clientRoll));
            this.chains.rotateAngleX = f1 * f * 0.2f * Maths.rad(rollDeg);
        }
    }

    public void animateStack(ItemStack itemStackIn) {
        this.resetToDefaultPose();
    }
}
