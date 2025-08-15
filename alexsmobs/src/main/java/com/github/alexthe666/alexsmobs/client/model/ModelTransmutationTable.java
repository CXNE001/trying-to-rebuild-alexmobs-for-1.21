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

import com.github.alexthe666.alexsmobs.tileentity.TileEntityTransmutationTable;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;

public class ModelTransmutationTable
extends AdvancedEntityModel<Entity> {
    private final AdvancedModelBox base;
    private final AdvancedModelBox star;
    private final AdvancedModelBox portal;
    private final AdvancedModelBox leftArm;
    private final AdvancedModelBox leftElbow;
    private final AdvancedModelBox leftHand;
    private final AdvancedModelBox rightArm;
    private final AdvancedModelBox rightElbow;
    private final AdvancedModelBox rightHand;

    public ModelTransmutationTable(float scale) {
        this.texWidth = 64;
        this.texHeight = 64;
        this.base = new AdvancedModelBox((AdvancedEntityModel)this, "base");
        this.base.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.base.setTextureOffset(0, 0).addBox(-7.0f, -5.0f, -7.0f, 14.0f, 5.0f, 14.0f, scale, false);
        this.star = new AdvancedModelBox((AdvancedEntityModel)this, "star");
        this.star.setRotationPoint(0.0f, -12.5f, 0.0f);
        this.base.addChild((BasicModelPart)this.star);
        this.star.setTextureOffset(0, 20).addBox(-2.5f, -2.5f, -2.5f, 5.0f, 5.0f, 5.0f, scale, false);
        this.portal = new AdvancedModelBox((AdvancedEntityModel)this, "portal");
        this.portal.setRotationPoint(0.0f, -12.5f, 0.0f);
        this.base.addChild((BasicModelPart)this.portal);
        this.portal.setTextureOffset(21, 20).addBox(-4.1f, -4.5f, 0.0f, 9.0f, 9.0f, 0.0f, scale, false);
        this.leftArm = new AdvancedModelBox((AdvancedEntityModel)this, "leftArm");
        this.leftArm.setRotationPoint(6.6f, -3.3f, 0.0f);
        this.base.addChild((BasicModelPart)this.leftArm);
        this.setRotationAngle(this.leftArm, 0.0f, 0.0f, 0.3054f);
        this.leftArm.setTextureOffset(13, 39).addBox(-1.0f, -6.0f, -1.0f, 2.0f, 7.0f, 2.0f, scale, false);
        this.leftElbow = new AdvancedModelBox((AdvancedEntityModel)this, "leftElbow");
        this.leftElbow.setRotationPoint(-1.0f, -6.0f, 0.0f);
        this.leftArm.addChild((BasicModelPart)this.leftElbow);
        this.setRotationAngle(this.leftElbow, 0.0f, 0.0f, 0.4363f);
        this.leftElbow.setTextureOffset(0, 36).addBox(-3.0f, -2.0f, -1.0f, 5.0f, 2.0f, 2.0f, scale - 0.1f, false);
        this.leftHand = new AdvancedModelBox((AdvancedEntityModel)this, "leftHand");
        this.leftHand.setRotationPoint(-3.8f, -0.8f, 0.0f);
        this.leftElbow.addChild((BasicModelPart)this.leftHand);
        this.setRotationAngle(this.leftHand, 0.0f, 0.0f, -0.7418f);
        this.leftHand.setTextureOffset(31, 39).addBox(-1.0f, -3.0f, -1.0f, 2.0f, 6.0f, 2.0f, scale, false);
        this.leftHand.setTextureOffset(32, 30).addBox(-1.0f, -1.0f, -3.0f, 2.0f, 2.0f, 6.0f, scale, false);
        this.rightArm = new AdvancedModelBox((AdvancedEntityModel)this, "rightArm");
        this.rightArm.setRotationPoint(-6.6f, -3.3f, 0.0f);
        this.base.addChild((BasicModelPart)this.rightArm);
        this.setRotationAngle(this.rightArm, 0.0f, 0.0f, -0.3054f);
        this.rightArm.setTextureOffset(13, 39).addBox(-1.0f, -6.0f, -1.0f, 2.0f, 7.0f, 2.0f, scale, true);
        this.rightElbow = new AdvancedModelBox((AdvancedEntityModel)this, "rightElbow");
        this.rightElbow.setRotationPoint(1.0f, -6.0f, 0.0f);
        this.rightArm.addChild((BasicModelPart)this.rightElbow);
        this.setRotationAngle(this.rightElbow, 0.0f, 0.0f, -0.4363f);
        this.rightElbow.setTextureOffset(0, 36).addBox(-2.0f, -2.0f, -1.0f, 5.0f, 2.0f, 2.0f, scale - 0.1f, true);
        this.rightHand = new AdvancedModelBox((AdvancedEntityModel)this, "rightHand");
        this.rightHand.setRotationPoint(3.8f, -0.8f, 0.0f);
        this.rightElbow.addChild((BasicModelPart)this.rightHand);
        this.setRotationAngle(this.rightHand, 0.0f, 0.0f, 0.7418f);
        this.rightHand.setTextureOffset(31, 39).addBox(-1.0f, -3.0f, -1.0f, 2.0f, 6.0f, 2.0f, scale, true);
        this.rightHand.setTextureOffset(32, 30).addBox(-1.0f, -1.0f, -3.0f, 2.0f, 2.0f, 6.0f, scale, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.base);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.base, (Object)this.star, (Object)this.portal, (Object)this.leftArm, (Object)this.rightArm, (Object)this.leftElbow, (Object)this.rightElbow, (Object)this.leftHand, (Object)this.rightHand);
    }

    public void m_6973_(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
    }

    public void animate(TileEntityTransmutationTable beak, float partialTick) {
        this.resetToDefaultPose();
        float ageInTicks = (float)beak.ticksExisted + partialTick;
        this.flap(this.leftArm, 0.5f, 0.2f, false, 0.0f, 0.1f, ageInTicks, 1.0f);
        this.flap(this.rightArm, 0.5f, -0.2f, false, 0.0f, -0.1f, ageInTicks, 1.0f);
        this.flap(this.leftElbow, 0.5f, 0.1f, true, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.rightElbow, 0.5f, -0.1f, true, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.leftHand, 0.5f, 0.1f, true, -1.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.rightHand, 0.5f, -0.1f, true, -1.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.portal, 0.5f, -0.1f, true, -3.0f, 0.0f, ageInTicks, 1.0f);
        this.bob(this.star, 0.25f, 1.0f, false, ageInTicks, 1.0f);
        this.bob(this.portal, 0.25f, 1.0f, false, ageInTicks, 1.0f);
        this.portal.setScale(0.35f * (float)Math.sin(ageInTicks * 0.5f + 1.0f) + 1.35f, 0.1f * (float)Math.cos(ageInTicks * 0.5f + 1.0f) + 1.1f, 1.0f);
        this.star.rotateAngleY = ageInTicks * 0.1f;
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
