/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.LivingEntity;

public class ModelRockyChestplateRolling
extends AdvancedEntityModel<LivingEntity> {
    private final AdvancedModelBox Body;
    private final AdvancedModelBox LeftArm;
    private final AdvancedModelBox RightArm;

    public ModelRockyChestplateRolling() {
        this.texWidth = 64;
        this.texHeight = 64;
        float f = -6.5f;
        this.Body = new AdvancedModelBox((AdvancedEntityModel)this, "Body");
        this.Body.setRotationPoint(0.0f, f, 0.0f);
        this.Body.setTextureOffset(0, 0).addBox(-5.0f, -0.5f, -3.0f, 10.0f, 13.0f, 9.0f, 0.0f, false);
        this.Body.setTextureOffset(0, 23).addBox(-4.0f, 0.5f, 6.0f, 8.0f, 11.0f, 4.0f, 0.0f, false);
        this.Body.setTextureOffset(25, 34).addBox(-2.0f, -0.5f, 6.0f, 4.0f, 13.0f, 4.0f, 0.0f, false);
        this.LeftArm = new AdvancedModelBox((AdvancedEntityModel)this, "LeftArm");
        this.LeftArm.setRotationPoint(6.0f, 2.0f + f, 1.0f);
        this.setRotationAngle(this.LeftArm, 0.0f, 0.0f, 1.5708f);
        this.LeftArm.setTextureOffset(25, 23).addBox(-3.5f, -1.0f, -4.1f, 6.0f, 4.0f, 6.0f, 0.0f, false);
        this.LeftArm.setTextureOffset(0, 39).addBox(-3.0f, -3.0f, -3.1f, 7.0f, 6.0f, 4.0f, 0.0f, false);
        this.RightArm = new AdvancedModelBox((AdvancedEntityModel)this, "RightArm");
        this.RightArm.setRotationPoint(-6.0f, 1.0f + f, 1.0f);
        this.setRotationAngle(this.RightArm, 0.0f, 0.0f, -1.5708f);
        this.RightArm.setTextureOffset(25, 23).addBox(-3.5f, -1.0f, -4.1f, 6.0f, 4.0f, 6.0f, 0.0f, true);
        this.RightArm.setTextureOffset(0, 39).addBox(-5.5f, -3.0f, -3.1f, 7.0f, 6.0f, 4.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.Body, (Object)this.RightArm, (Object)this.LeftArm);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.Body, (Object)this.RightArm, (Object)this.LeftArm);
    }

    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
