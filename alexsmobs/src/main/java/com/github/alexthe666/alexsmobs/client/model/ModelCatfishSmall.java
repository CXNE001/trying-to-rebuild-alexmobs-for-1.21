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

import com.github.alexthe666.alexsmobs.entity.EntityCatfish;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelCatfishSmall
extends AdvancedEntityModel<EntityCatfish> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox left_barbel;
    private final AdvancedModelBox right_barbel;
    private final AdvancedModelBox dorsal_fin;
    private final AdvancedModelBox left_fin;
    private final AdvancedModelBox right_fin;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox tail_fin;

    public ModelCatfishSmall() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -3.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-4.0f, -3.0f, -8.0f, 8.0f, 6.0f, 14.0f, 0.0f, false);
        this.left_barbel = new AdvancedModelBox((AdvancedEntityModel)this, "left_barbel");
        this.left_barbel.setRotationPoint(4.0f, 1.0f, -6.0f);
        this.body.addChild((BasicModelPart)this.left_barbel);
        this.setRotationAngle(this.left_barbel, 0.0f, -0.6109f, 0.0f);
        this.left_barbel.setTextureOffset(0, 10).addBox(0.0f, 0.0f, 0.0f, 6.0f, 3.0f, 0.0f, 0.0f, false);
        this.right_barbel = new AdvancedModelBox((AdvancedEntityModel)this, "right_barbel");
        this.right_barbel.setRotationPoint(-4.0f, 1.0f, -6.0f);
        this.body.addChild((BasicModelPart)this.right_barbel);
        this.setRotationAngle(this.right_barbel, 0.0f, 0.6109f, 0.0f);
        this.right_barbel.setTextureOffset(0, 10).addBox(-6.0f, 0.0f, 0.0f, 6.0f, 3.0f, 0.0f, 0.0f, true);
        this.dorsal_fin = new AdvancedModelBox((AdvancedEntityModel)this, "dorsal_fin");
        this.dorsal_fin.setRotationPoint(0.0f, -3.0f, -2.0f);
        this.body.addChild((BasicModelPart)this.dorsal_fin);
        this.setRotationAngle(this.dorsal_fin, -0.1309f, 0.0f, 0.0f);
        this.dorsal_fin.setTextureOffset(0, 21).addBox(0.0f, -4.0f, 0.0f, 0.0f, 4.0f, 5.0f, 0.0f, false);
        this.left_fin = new AdvancedModelBox((AdvancedEntityModel)this, "left_fin");
        this.left_fin.setRotationPoint(4.0f, 2.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.left_fin);
        this.setRotationAngle(this.left_fin, 0.0f, 0.0f, -0.9163f);
        this.left_fin.setTextureOffset(19, 21).addBox(0.0f, 0.0f, -1.0f, 0.0f, 2.0f, 4.0f, 0.0f, false);
        this.right_fin = new AdvancedModelBox((AdvancedEntityModel)this, "right_fin");
        this.right_fin.setRotationPoint(-4.0f, 2.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.right_fin);
        this.setRotationAngle(this.right_fin, 0.0f, 0.0f, 0.9163f);
        this.right_fin.setTextureOffset(19, 21).addBox(0.0f, 0.0f, -1.0f, 0.0f, 2.0f, 4.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, 0.0f, 7.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 21).addBox(-2.0f, -3.0f, -1.0f, 4.0f, 5.0f, 10.0f, 0.0f, false);
        this.tail.setTextureOffset(0, 0).addBox(0.0f, -4.0f, 2.0f, 0.0f, 1.0f, 2.0f, 0.0f, false);
        this.tail.setTextureOffset(0, 0).addBox(0.0f, 2.0f, -1.0f, 0.0f, 2.0f, 6.0f, 0.0f, false);
        this.tail_fin = new AdvancedModelBox((AdvancedEntityModel)this, "tail_fin");
        this.tail_fin.setRotationPoint(0.0f, 2.0f, 9.0f);
        this.tail.addChild((BasicModelPart)this.tail_fin);
        this.tail_fin.setTextureOffset(19, 27).addBox(0.0f, -7.0f, -2.0f, 0.0f, 9.0f, 10.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityCatfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.2f;
        float idleDegree = 0.25f;
        float swimSpeed = 0.55f;
        float swimDegree = 0.75f;
        AdvancedModelBox[] tailBoxes = new AdvancedModelBox[]{this.body, this.tail, this.tail_fin};
        this.chainSwing(tailBoxes, swimSpeed, swimDegree * 0.9f, -3.0, limbSwing, limbSwingAmount);
        this.flap(this.left_fin, swimSpeed, swimDegree, false, 4.0f, -0.6f, limbSwing, limbSwingAmount);
        this.flap(this.right_fin, swimSpeed, swimDegree, true, 4.0f, -0.6f, limbSwing, limbSwingAmount);
        this.walk(this.dorsal_fin, idleSpeed, idleDegree * 0.2f, true, 2.0f, 0.1f, ageInTicks, 1.0f);
        this.bob(this.body, idleSpeed, idleDegree, false, ageInTicks, 1.0f);
        this.flap(this.left_fin, idleSpeed, idleDegree, false, 4.0f, -0.1f, ageInTicks, 1.0f);
        this.flap(this.right_fin, idleSpeed, idleDegree, true, 4.0f, -0.1f, ageInTicks, 1.0f);
        this.swing(this.left_barbel, idleSpeed, idleDegree, false, 2.0f, 0.1f, ageInTicks, 1.0f);
        this.swing(this.right_barbel, idleSpeed, idleDegree, true, 2.0f, 0.1f, ageInTicks, 1.0f);
        this.chainSwing(tailBoxes, idleSpeed, idleDegree * 0.1f, -2.5, ageInTicks, 1.0f);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.dorsal_fin, (Object)this.tail, (Object)this.left_fin, (Object)this.right_fin, (Object)this.left_barbel, (Object)this.right_barbel, (Object)this.tail_fin);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
