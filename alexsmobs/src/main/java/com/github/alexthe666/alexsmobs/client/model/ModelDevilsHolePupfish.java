/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityDevilsHolePupfish;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelDevilsHolePupfish
extends AdvancedEntityModel<EntityDevilsHolePupfish> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox bottom_fin;
    private final AdvancedModelBox dorsal_fin;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox left_fin;
    private final AdvancedModelBox right_fin;

    public ModelDevilsHolePupfish() {
        this.texHeight = 32;
        this.texWidth = 32;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -2.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-1.5f, -2.0f, -5.0f, 3.0f, 4.0f, 9.0f, 0.0f, false);
        this.bottom_fin = new AdvancedModelBox((AdvancedEntityModel)this, "bottom_fin");
        this.bottom_fin.setRotationPoint(0.0f, 2.0f, 1.0f);
        this.body.addChild((BasicModelPart)this.bottom_fin);
        this.bottom_fin.setTextureOffset(0, 0).addBox(0.0f, 0.0f, 0.0f, 0.0f, 2.0f, 3.0f, 0.0f, false);
        this.dorsal_fin = new AdvancedModelBox((AdvancedEntityModel)this, "dorsal_fin");
        this.dorsal_fin.setRotationPoint(0.0f, -2.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.dorsal_fin);
        this.dorsal_fin.setTextureOffset(11, 14).addBox(0.0f, -3.0f, -2.0f, 0.0f, 3.0f, 5.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, -1.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 14).addBox(0.0f, -3.0f, 0.0f, 0.0f, 6.0f, 5.0f, 0.0f, false);
        this.left_fin = new AdvancedModelBox((AdvancedEntityModel)this, "left_fin");
        this.left_fin.setRotationPoint(1.5f, 1.0f, -2.0f);
        this.body.addChild((BasicModelPart)this.left_fin);
        this.setRotationAngle(this.left_fin, 0.0f, 0.48f, 0.0f);
        this.left_fin.setTextureOffset(0, 14).addBox(0.0f, -1.0f, 0.0f, 0.0f, 2.0f, 2.0f, 0.0f, false);
        this.right_fin = new AdvancedModelBox((AdvancedEntityModel)this, "right_fin");
        this.right_fin.setRotationPoint(-1.5f, 1.0f, -2.0f);
        this.body.addChild((BasicModelPart)this.right_fin);
        this.setRotationAngle(this.right_fin, 0.0f, -0.48f, 0.0f);
        this.right_fin.setTextureOffset(0, 14).addBox(0.0f, -1.0f, 0.0f, 0.0f, 2.0f, 2.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityDevilsHolePupfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.3f;
        float idleDegree = 0.5f;
        float swimSpeed = 1.0f;
        float swimDegree = 0.5f;
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float landProgress = entity.prevOnLandProgress + (entity.onLandProgress - entity.prevOnLandProgress) * partialTick;
        float feedingProgress = entity.prevFeedProgress + (entity.feedProgress - entity.prevFeedProgress) * partialTick;
        this.progressRotationPrev(this.dorsal_fin, limbSwingAmount, Maths.rad(-20.0), 0.0f, 0.0f, 1.0f);
        this.progressPositionPrev(this.dorsal_fin, limbSwingAmount, 0.0f, 0.5f, 0.0f, 1.0f);
        this.progressRotationPrev(this.bottom_fin, limbSwingAmount, Maths.rad(10.0), 0.0f, 0.0f, 1.0f);
        this.progressPositionPrev(this.bottom_fin, limbSwingAmount, 0.0f, -0.5f, -0.5f, 1.0f);
        this.progressRotationPrev(this.body, landProgress, 0.0f, 0.0f, Maths.rad(90.0), 5.0f);
        this.bob(this.body, idleSpeed, idleDegree, false, ageInTicks, 1.0f);
        this.swing(this.body, idleSpeed, idleDegree * 0.1f, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.swing(this.tail, idleSpeed, idleDegree * 0.3f, false, -1.0f, 0.0f, ageInTicks, 1.0f);
        this.swing(this.tail, swimSpeed, swimDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.body, swimSpeed, swimDegree * 0.3f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.left_fin, swimSpeed, swimDegree, false, 3.0f, 0.6f, limbSwing, limbSwingAmount);
        this.swing(this.right_fin, swimSpeed, swimDegree, true, 3.0f, 0.6f, limbSwing, limbSwingAmount);
        this.body.rotateAngleX += headPitch * ((float)Math.PI / 180);
        this.body.rotateAngleX = (float)((double)this.body.rotateAngleX + (double)feedingProgress * Math.cos(ageInTicks * 0.3f) * (double)0.2f * Math.PI * (double)0.1f);
        this.body.rotationPointZ = (float)((double)this.body.rotationPointZ + (double)feedingProgress * Math.abs(Math.sin(ageInTicks * 0.3f)));
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.dorsal_fin, (Object)this.bottom_fin, (Object)this.tail, (Object)this.left_fin, (Object)this.right_fin);
    }

    public void setRotationAngle(AdvancedModelBox modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
