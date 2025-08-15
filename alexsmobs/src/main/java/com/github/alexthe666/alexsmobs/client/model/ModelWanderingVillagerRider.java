/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.VillagerModel
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.world.entity.Entity
 */
package com.github.alexthe666.alexsmobs.client.model;

import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;

public class ModelWanderingVillagerRider
extends VillagerModel {
    private ModelPart rightLegRider;
    private ModelPart leftLegRider;

    public ModelWanderingVillagerRider(ModelPart part) {
        super(part);
        this.rightLegRider = part.m_171324_("right_leg");
        this.leftLegRider = part.m_171324_("left_leg");
    }

    public void m_6973_(Entity entity, float f, float f1, float f2, float f3, float f4) {
        super.m_6973_(entity, f, f1, f2, f3, f4);
        if (this.f_102609_) {
            this.rightLegRider.f_104203_ = -1.4137167f;
            this.rightLegRider.f_104204_ = 0.31415927f;
            this.rightLegRider.f_104205_ = 0.07853982f;
            this.leftLegRider.f_104203_ = -1.4137167f;
            this.leftLegRider.f_104204_ = -0.31415927f;
            this.leftLegRider.f_104205_ = -0.07853982f;
        } else {
            this.rightLegRider.f_104204_ = 0.0f;
            this.rightLegRider.f_104205_ = 0.0f;
            this.leftLegRider.f_104204_ = 0.0f;
            this.leftLegRider.f_104205_ = 0.0f;
        }
    }
}
