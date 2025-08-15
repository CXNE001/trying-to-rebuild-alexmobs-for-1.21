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

import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateAnchor;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;

public class ModelEndPirateAnchorChain
extends AdvancedEntityModel<Entity> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox chain;

    public ModelEndPirateAnchorChain() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.chain = new AdvancedModelBox((AdvancedEntityModel)this, "chain");
        this.chain.setRotationPoint(0.0f, -12.5f, 0.0f);
        this.root.addChild((BasicModelPart)this.chain);
        this.chain.setTextureOffset(0, 7).addBox(-5.5f, 9.5f, -1.5f, 11.0f, 3.0f, 3.0f, 0.0f, false);
        this.chain.setTextureOffset(0, 0).addBox(-5.5f, -1.5f, -1.5f, 11.0f, 3.0f, 3.0f, 0.0f, false);
        this.chain.setTextureOffset(13, 14).addBox(2.5f, 1.5f, -1.5f, 3.0f, 8.0f, 3.0f, 0.0f, false);
        this.chain.setTextureOffset(0, 14).addBox(-5.5f, 1.5f, -1.5f, 3.0f, 8.0f, 3.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.chain);
    }

    public void m_6973_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void renderAnchor(TileEntityEndPirateAnchor anchor, float partialTick, boolean east) {
        this.resetToDefaultPose();
    }
}
