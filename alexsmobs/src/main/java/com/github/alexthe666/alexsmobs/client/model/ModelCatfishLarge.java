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

public class ModelCatfishLarge
extends AdvancedEntityModel<EntityCatfish> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox left_fin;
    private final AdvancedModelBox right_fin;
    private final AdvancedModelBox dorsal_fin;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox tail_fin;
    private final AdvancedModelBox head;
    private final AdvancedModelBox left_BigWhisker;
    private final AdvancedModelBox right_BigWhisker;
    private final AdvancedModelBox left_SmallWhisker;
    private final AdvancedModelBox right_SmallWhisker;

    public ModelCatfishLarge() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -8.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-10.0f, -7.0f, -14.0f, 20.0f, 15.0f, 26.0f, 0.0f, false);
        this.left_fin = new AdvancedModelBox((AdvancedEntityModel)this, "left_fin");
        this.left_fin.setRotationPoint(10.0f, 5.0f, -5.0f);
        this.body.addChild((BasicModelPart)this.left_fin);
        this.setRotationAngle(this.left_fin, 0.0f, 0.0f, 0.6981f);
        this.left_fin.setTextureOffset(0, 0).addBox(0.0f, 0.0f, -2.0f, 5.0f, 0.0f, 8.0f, 0.0f, false);
        this.right_fin = new AdvancedModelBox((AdvancedEntityModel)this, "right_fin");
        this.right_fin.setRotationPoint(-10.0f, 5.0f, -5.0f);
        this.body.addChild((BasicModelPart)this.right_fin);
        this.setRotationAngle(this.right_fin, 0.0f, 0.0f, -0.6981f);
        this.right_fin.setTextureOffset(0, 0).addBox(-5.0f, 0.0f, -2.0f, 5.0f, 0.0f, 8.0f, 0.0f, true);
        this.dorsal_fin = new AdvancedModelBox((AdvancedEntityModel)this, "dorsal_fin");
        this.dorsal_fin.setRotationPoint(0.0f, -7.0f, -5.0f);
        this.body.addChild((BasicModelPart)this.dorsal_fin);
        this.setRotationAngle(this.dorsal_fin, -0.0873f, 0.0f, 0.0f);
        this.dorsal_fin.setTextureOffset(0, 0).addBox(0.0f, -7.0f, 0.0f, 0.0f, 7.0f, 8.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, -2.0f, 13.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 41).addBox(-6.0f, -5.0f, -1.0f, 12.0f, 11.0f, 20.0f, 0.0f, false);
        this.tail.setTextureOffset(0, 56).addBox(0.0f, 6.0f, -1.0f, 0.0f, 4.0f, 16.0f, 0.0f, false);
        this.tail.setTextureOffset(16, 18).addBox(0.0f, -7.0f, 4.0f, 0.0f, 2.0f, 5.0f, 0.0f, false);
        this.tail_fin = new AdvancedModelBox((AdvancedEntityModel)this, "tail_fin");
        this.tail_fin.setRotationPoint(0.0f, 0.0f, 17.0f);
        this.tail.addChild((BasicModelPart)this.tail_fin);
        this.tail_fin.setTextureOffset(44, 18).addBox(0.0f, -10.0f, -5.0f, 0.0f, 17.0f, 23.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -5.0f, -15.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(56, 64).addBox(-9.5f, -2.0f, -7.0f, 19.0f, 13.0f, 8.0f, 0.0f, false);
        this.left_BigWhisker = new AdvancedModelBox((AdvancedEntityModel)this, "left_BigWhisker");
        this.left_BigWhisker.setRotationPoint(9.5f, 6.0f, -5.0f);
        this.head.addChild((BasicModelPart)this.left_BigWhisker);
        this.setRotationAngle(this.left_BigWhisker, 0.0f, -0.4363f, 0.2618f);
        this.left_BigWhisker.setTextureOffset(66, 0).addBox(0.0f, 0.0f, 0.0f, 15.0f, 8.0f, 0.0f, 0.0f, false);
        this.right_BigWhisker = new AdvancedModelBox((AdvancedEntityModel)this, "right_BigWhisker");
        this.right_BigWhisker.setRotationPoint(-9.5f, 6.0f, -5.0f);
        this.head.addChild((BasicModelPart)this.right_BigWhisker);
        this.setRotationAngle(this.right_BigWhisker, 0.0f, 0.4363f, -0.2618f);
        this.right_BigWhisker.setTextureOffset(66, 0).addBox(-15.0f, 0.0f, 0.0f, 15.0f, 8.0f, 0.0f, 0.0f, true);
        this.left_SmallWhisker = new AdvancedModelBox((AdvancedEntityModel)this, "left_SmallWhisker");
        this.left_SmallWhisker.setRotationPoint(9.5f, 8.0f, -6.0f);
        this.head.addChild((BasicModelPart)this.left_SmallWhisker);
        this.setRotationAngle(this.left_SmallWhisker, 0.0f, 0.0436f, 0.3054f);
        this.left_SmallWhisker.setTextureOffset(0, 15).addBox(0.0f, 0.0f, 0.0f, 7.0f, 6.0f, 0.0f, 0.0f, false);
        this.right_SmallWhisker = new AdvancedModelBox((AdvancedEntityModel)this, "right_SmallWhisker");
        this.right_SmallWhisker.setRotationPoint(-9.5f, 8.0f, -6.0f);
        this.head.addChild((BasicModelPart)this.right_SmallWhisker);
        this.setRotationAngle(this.right_SmallWhisker, 0.0f, -0.0436f, -0.3054f);
        this.right_SmallWhisker.setTextureOffset(0, 15).addBox(-7.0f, 0.0f, 0.0f, 7.0f, 6.0f, 0.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityCatfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.2f;
        float idleDegree = 0.25f;
        float swimSpeed = 0.55f;
        float swimDegree = 0.75f;
        AdvancedModelBox[] tailBoxes = new AdvancedModelBox[]{this.body, this.tail, this.tail_fin};
        this.chainSwing(tailBoxes, swimSpeed, swimDegree * 0.9f, -2.5, limbSwing, limbSwingAmount);
        this.swing(this.head, swimSpeed, swimDegree * 0.2f, true, 2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.left_fin, swimSpeed, swimDegree, false, 4.0f, -0.6f, limbSwing, limbSwingAmount);
        this.flap(this.right_fin, swimSpeed, swimDegree, true, 4.0f, -0.6f, limbSwing, limbSwingAmount);
        this.bob(this.body, idleSpeed, idleDegree, false, ageInTicks, 1.0f);
        this.flap(this.left_fin, idleSpeed, idleDegree, false, 4.0f, -0.1f, ageInTicks, 1.0f);
        this.flap(this.right_fin, idleSpeed, idleDegree, true, 4.0f, -0.1f, ageInTicks, 1.0f);
        this.swing(this.left_BigWhisker, idleSpeed, idleDegree, false, 2.0f, 0.1f, ageInTicks, 1.0f);
        this.swing(this.right_BigWhisker, idleSpeed, idleDegree, true, 2.0f, 0.1f, ageInTicks, 1.0f);
        this.swing(this.left_SmallWhisker, idleSpeed, idleDegree, false, 3.0f, 0.1f, ageInTicks, 1.0f);
        this.swing(this.right_SmallWhisker, idleSpeed, idleDegree, true, 3.0f, 0.1f, ageInTicks, 1.0f);
        this.chainSwing(tailBoxes, idleSpeed, idleDegree * 0.1f, -2.5, ageInTicks, 1.0f);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.head, (Object)this.body, (Object)this.dorsal_fin, (Object)this.tail, (Object)this.left_fin, (Object)this.right_fin, (Object)this.left_BigWhisker, (Object)this.right_BigWhisker, (Object)this.left_SmallWhisker, (Object)this.right_SmallWhisker, (Object)this.tail_fin, (Object[])new AdvancedModelBox[0]);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
