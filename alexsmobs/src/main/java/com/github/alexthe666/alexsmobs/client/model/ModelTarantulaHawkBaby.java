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
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityTarantulaHawk;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelTarantulaHawkBaby
extends AdvancedEntityModel<EntityTarantulaHawk> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox head;

    public ModelTarantulaHawkBaby() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -3.0f, -7.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-4.0f, -3.0f, 0.0f, 8.0f, 6.0f, 15.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, 0.9f, 0.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 22).addBox(-3.5f, -3.0f, -3.0f, 7.0f, 5.0f, 3.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityTarantulaHawk entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float walkSpeed = 1.0f;
        float walkDegree = 0.75f;
        float stretch = (float)(Math.sin(limbSwing * 0.25f) * (double)limbSwingAmount) + limbSwingAmount;
        this.body.setScale(1.0f, 1.0f - stretch * 0.05f, 1.0f + stretch * 0.5f);
        this.body.rotationPointZ -= stretch * 4.0f;
        this.walk(this.head, 0.25f, 0.075f, false, -1.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.head, walkSpeed, walkDegree * 0.1f, false, -1.0f, 0.0f, limbSwing, limbSwingAmount);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.head);
    }

    public void m_7695_(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z) {
        advancedModelBox.rotateAngleX = x;
        advancedModelBox.rotateAngleY = y;
        advancedModelBox.rotateAngleZ = z;
    }
}
