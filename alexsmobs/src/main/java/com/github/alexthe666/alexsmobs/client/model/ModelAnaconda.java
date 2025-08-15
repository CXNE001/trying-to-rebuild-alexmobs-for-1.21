/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityAnaconda;
import com.github.alexthe666.alexsmobs.entity.EntityAnacondaPart;
import com.github.alexthe666.alexsmobs.entity.util.AnacondaPartIndex;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class ModelAnaconda<T extends LivingEntity>
extends AdvancedEntityModel<T> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox part;
    private AdvancedModelBox jaw;

    public ModelAnaconda(AnacondaPartIndex index) {
        this.texWidth = 128;
        this.texHeight = 128;
        this.part = new AdvancedModelBox((AdvancedEntityModel)this, "part");
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 21.0f, 0.0f);
        switch (index) {
            case HEAD: {
                this.part.setRotationPoint(0.0f, 0.0f, 0.0f);
                this.part.setTextureOffset(62, 32).addBox(-3.5f, -3.0f, -9.0f, 7.0f, 3.0f, 10.0f, 0.0f, false);
                this.part.setTextureOffset(67, 0).addBox(-3.5f, -1.0f, -9.0f, 7.0f, 0.0f, 10.0f, 0.0f, false);
                this.jaw = new AdvancedModelBox((AdvancedEntityModel)this, "        jaw");
                this.jaw.setRotationPoint(0.0f, 0.0f, 0.0f);
                this.jaw.setTextureOffset(52, 55).addBox(-3.5f, -1.0f, -9.0f, 7.0f, 4.0f, 10.0f, 0.0f, false);
                this.jaw.setTextureOffset(66, 11).addBox(-3.5f, 0.0f, -9.0f, 7.0f, 0.0f, 10.0f, 0.0f, false);
                this.part.addChild((BasicModelPart)this.jaw);
                break;
            }
            case NECK: {
                this.part.setRotationPoint(0.0f, 0.0f, 0.0f);
                this.part.setTextureOffset(33, 32).addBox(-3.0f, -3.0f, -8.0f, 6.0f, 6.0f, 16.0f, 0.0f, false);
                break;
            }
            case BODY: {
                this.part.setRotationPoint(0.0f, 0.0f, -8.0f);
                this.part.setTextureOffset(33, 8).addBox(-4.0f, -4.0f, 0.0f, 8.0f, 7.0f, 16.0f, 0.0f, false);
                break;
            }
            case TAIL: {
                this.part.setRotationPoint(0.0f, 0.0f, -7.0f);
                this.part.setTextureOffset(29, 55).addBox(-1.5f, -2.0f, 0.0f, 3.0f, 4.0f, 16.0f, 0.0f, false);
            }
        }
        this.root.addChild((BasicModelPart)this.part);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float strangle = 0.0f;
        if (this.jaw != null && entity instanceof EntityAnaconda) {
            EntityAnaconda anaconda = (EntityAnaconda)entity;
            strangle = anaconda.getStrangleProgress(partialTick);
            this.progressPositionPrev(this.part, strangle, 0.0f, 4.0f, 0.0f, 5.0f);
            this.progressPositionPrev(this.jaw, strangle, 0.0f, 0.0f, 1.0f, 5.0f);
            this.progressRotationPrev(this.part, strangle, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
            this.progressRotationPrev(this.jaw, strangle, Maths.rad(160.0), 0.0f, 0.0f, 5.0f);
            this.part.rotateAngleY += netHeadYaw / 57.295776f;
            this.part.rotateAngleX += Math.min(0.0f, headPitch / 57.295776f);
            this.part.rotationPointX += Mth.m_14031_((float)limbSwing) * 2.0f * limbSwingAmount;
            this.walk(this.part, 0.7f, 0.2f, false, 1.0f, 0.05f, ageInTicks, strangle * 0.2f);
            this.walk(this.jaw, 0.7f, 0.4f, true, 1.0f, -0.05f, ageInTicks, strangle * 0.2f);
        } else if (entity instanceof EntityAnacondaPart) {
            EntityAnacondaPart partEntity = (EntityAnacondaPart)entity;
            float f = 1.01f;
            if (partEntity.getBodyIndex() % 2 == 1) {
                f = 1.0f;
            }
            float swell = partEntity.getSwellLerp(partialTick) * 0.15f;
            this.part.setScale(f + swell, f + swell, f);
        }
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return this.jaw == null ? ImmutableList.of((Object)this.root, (Object)this.part) : ImmutableList.of((Object)this.root, (Object)this.part, (Object)this.jaw);
    }
}
