/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.client.Minecraft
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCombJelly;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;

public class ModelCombJelly
extends AdvancedEntityModel<EntityCombJelly> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox inner_body;

    public ModelCombJelly(float f) {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -13.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-5.0f, -2.0f, -5.0f, 10.0f, 15.0f, 10.0f, f, false);
        this.inner_body = new AdvancedModelBox((AdvancedEntityModel)this, "inner_body");
        this.inner_body.setRotationPoint(0.0f, -3.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.inner_body);
        this.inner_body.setTextureOffset(40, 6).addBox(-3.0f, -1.0f, -3.0f, 6.0f, 13.0f, 6.0f, f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.inner_body);
    }

    public void setupAnim(EntityCombJelly entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float partialTick = Minecraft.m_91087_().m_91296_();
        float birdPitch = entity.prevjellyPitch + (entity.getJellyPitch() - entity.prevjellyPitch) * partialTick;
        float landProgress = entity.prevOnLandProgress + (entity.onLandProgress - entity.prevOnLandProgress) * partialTick;
        float girateSpeed = 0.1f * ageInTicks * (1.0f - landProgress * 0.2f);
        float widthScale = 0.95f + (float)Math.sin(girateSpeed) * 0.1f;
        float heightScale = 0.95f + (float)Math.cos(girateSpeed) * 0.1f;
        float squishedScale = widthScale - 0.1f * landProgress;
        this.body.setScale(squishedScale, heightScale, widthScale);
        this.inner_body.setScale(squishedScale, heightScale, widthScale);
        this.body.rotateAngleX = birdPitch * ((float)Math.PI / 180) * (1.0f - landProgress * 0.2f);
        this.body.rotateAngleZ = landProgress * 0.2f * 1.5707964f;
        this.body.rotationPointY += landProgress * 1.85f;
        this.body.rotationPointY += Math.abs(birdPitch * 0.07f);
        this.body.rotationPointX += landProgress;
        this.bob(this.body, 0.1f, 1.0f, false, ageInTicks, 1.0f - landProgress * 0.2f);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
