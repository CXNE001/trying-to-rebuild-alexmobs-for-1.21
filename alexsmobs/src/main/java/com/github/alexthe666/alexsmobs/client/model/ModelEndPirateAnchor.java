/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.item.ItemStack
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateAnchor;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class ModelEndPirateAnchor
extends AdvancedEntityModel<Entity> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox anchor;
    private final AdvancedModelBox chain_start;
    private final AdvancedModelBox left_side;
    private final AdvancedModelBox right_side;

    public ModelEndPirateAnchor() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.anchor = new AdvancedModelBox((AdvancedEntityModel)this, "anchor");
        this.anchor.setRotationPoint(0.0f, -47.25f, 0.0f);
        this.root.addChild((BasicModelPart)this.anchor);
        this.anchor.setTextureOffset(0, 0).addBox(-3.5f, 11.25f, -3.5f, 7.0f, 36.0f, 7.0f, 0.0f, false);
        this.anchor.setTextureOffset(29, 13).addBox(-5.5f, 2.25f, -4.5f, 11.0f, 9.0f, 9.0f, 0.0f, false);
        this.chain_start = new AdvancedModelBox((AdvancedEntityModel)this, "chain_start");
        this.chain_start.setRotationPoint(1.5f, 2.25f, 0.5f);
        this.anchor.addChild((BasicModelPart)this.chain_start);
        this.chain_start.setTextureOffset(23, 46).addBox(-3.0f, -5.0f, -4.0f, 3.0f, 5.0f, 7.0f, 0.0f, false);
        this.left_side = new AdvancedModelBox((AdvancedEntityModel)this, "left_side");
        this.left_side.setRotationPoint(16.5f, 29.9167f, 0.0f);
        this.anchor.addChild((BasicModelPart)this.left_side);
        this.left_side.setTextureOffset(29, 32).addBox(-3.0f, -13.6667f, -3.5f, 10.0f, 6.0f, 7.0f, 0.0f, false);
        this.left_side.setTextureOffset(0, 44).addBox(-1.0f, -7.6667f, -2.5f, 6.0f, 15.0f, 5.0f, 0.0f, false);
        this.left_side.setTextureOffset(29, 0).addBox(-13.0f, 7.3333f, -2.5f, 18.0f, 7.0f, 5.0f, 0.0f, false);
        this.right_side = new AdvancedModelBox((AdvancedEntityModel)this, "right_side");
        this.right_side.setRotationPoint(-16.5f, 29.9167f, 0.0f);
        this.anchor.addChild((BasicModelPart)this.right_side);
        this.right_side.setTextureOffset(29, 32).addBox(-7.0f, -13.6667f, -3.5f, 10.0f, 6.0f, 7.0f, 0.0f, true);
        this.right_side.setTextureOffset(0, 44).addBox(-5.0f, -7.6667f, -2.5f, 6.0f, 15.0f, 5.0f, 0.0f, true);
        this.right_side.setTextureOffset(29, 0).addBox(-5.0f, 7.3333f, -2.5f, 18.0f, 7.0f, 5.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.anchor, (Object)this.chain_start, (Object)this.left_side, (Object)this.right_side);
    }

    public void m_6973_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void renderAnchor(TileEntityEndPirateAnchor anchor, float partialTick, boolean east) {
        this.resetToDefaultPose();
    }

    public void animateStack(ItemStack itemStackIn) {
        this.resetToDefaultPose();
    }
}
