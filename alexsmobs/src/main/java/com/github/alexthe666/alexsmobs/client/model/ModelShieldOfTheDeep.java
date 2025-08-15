/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.world.entity.Entity
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Entity;

public class ModelShieldOfTheDeep
extends AdvancedEntityModel<Entity> {
    private final AdvancedModelBox shield;
    private final AdvancedModelBox handle;

    public ModelShieldOfTheDeep() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.shield = new AdvancedModelBox((AdvancedEntityModel)this, "shield");
        this.shield.setPos(-2.0f, 16.0f, 0.0f);
        this.shield.setTextureOffset(0, 0).addBox(-1.0f, -4.0f, -6.0f, 1.0f, 12.0f, 12.0f, 0.0f, false);
        this.shield.setTextureOffset(17, 15).addBox(-3.0f, -3.0f, -5.0f, 2.0f, 10.0f, 10.0f, 0.0f, false);
        this.shield.setTextureOffset(27, 0).addBox(-4.0f, -1.0f, -3.0f, 3.0f, 6.0f, 6.0f, 0.0f, false);
        this.handle = new AdvancedModelBox((AdvancedEntityModel)this, "handle");
        this.handle.setPos(8.0f, 8.0f, -8.0f);
        this.shield.addChild((BasicModelPart)this.handle);
        this.handle.setTextureOffset(0, 25).addBox(-8.0f, -8.5f, 7.0f, 5.0f, 5.0f, 2.0f, 0.0f, false);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.handle, (Object)this.shield);
    }

    public void m_6973_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void m_7695_(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.shield.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.shield);
    }
}
