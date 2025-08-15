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

import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.LivingEntity;

public class ModelMurmurNeck
extends AdvancedEntityModel<LivingEntity> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox neckPivot;
    private final AdvancedModelBox neck;
    private float stretch;
    public static boolean THIN = false;
    public static boolean HIDE = false;

    public ModelMurmurNeck() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.neckPivot = new AdvancedModelBox((AdvancedEntityModel)this, "neckPivot");
        this.neckPivot.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.neckPivot);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.neck.setTextureOffset(0, 60).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 16.0f, 4.0f, 0.0f, false);
        this.neckPivot.addChild((BasicModelPart)this.neck);
        this.updateDefaultPose();
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.neckPivot, (Object)this.neck);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void setAttributes(float f, float rotX, float rotY, float additionalYaw) {
        this.resetToDefaultPose();
        this.stretch = f;
        float f1 = THIN ? 0.75f : 1.0f;
        this.neck.setScale(f1, this.stretch, f1);
        this.neckPivot.rotateAngleX = Maths.rad(rotX);
        this.neckPivot.rotateAngleY = Maths.rad(rotY);
        this.neck.rotateAngleY = Maths.rad(-additionalYaw);
        this.neckPivot.showModel = !HIDE;
        this.root.showModel = !HIDE;
        this.neck.showModel = !HIDE;
    }
}
