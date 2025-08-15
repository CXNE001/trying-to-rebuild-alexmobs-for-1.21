/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.util.Mth
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCosmicCod;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.Mth;

public class ModelCosmicCod
extends AdvancedEntityModel<EntityCosmicCod> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox left_fin;
    private final AdvancedModelBox right_fin;
    private final AdvancedModelBox mouth;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox tail_tip;

    public ModelCosmicCod() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -2.0f, -2.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-1.0f, -1.0f, -5.0f, 2.0f, 3.0f, 8.0f, 0.0f, false);
        this.body.setTextureOffset(15, 15).addBox(0.0f, -3.0f, -2.0f, 0.0f, 2.0f, 5.0f, 0.0f, false);
        this.left_fin = new AdvancedModelBox((AdvancedEntityModel)this, "left_fin");
        this.left_fin.setRotationPoint(1.0f, 2.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.left_fin);
        this.setRotationAngle(this.left_fin, 0.0f, 0.0f, -0.829f);
        this.left_fin.setTextureOffset(0, 12).addBox(0.0f, 0.0f, -1.0f, 0.0f, 2.0f, 2.0f, 0.0f, false);
        this.right_fin = new AdvancedModelBox((AdvancedEntityModel)this, "right_fin");
        this.right_fin.setRotationPoint(-1.0f, 2.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.right_fin);
        this.setRotationAngle(this.right_fin, 0.0f, 0.0f, 0.829f);
        this.right_fin.setTextureOffset(0, 12).addBox(0.0f, 0.0f, -1.0f, 0.0f, 2.0f, 2.0f, 0.0f, true);
        this.mouth = new AdvancedModelBox((AdvancedEntityModel)this, "mouth");
        this.mouth.setRotationPoint(0.0f, 1.0f, -5.0f);
        this.body.addChild((BasicModelPart)this.mouth);
        this.mouth.setTextureOffset(0, 0).addBox(-1.0f, -2.0f, -1.0f, 2.0f, 2.0f, 1.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, 0.0f, 3.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(15, 6).addBox(-0.5f, -1.0f, 0.0f, 1.0f, 2.0f, 6.0f, 0.0f, false);
        this.tail_tip = new AdvancedModelBox((AdvancedEntityModel)this, "tail_tip");
        this.tail_tip.setRotationPoint(0.0f, 0.0f, 6.0f);
        this.tail.addChild((BasicModelPart)this.tail_tip);
        this.tail_tip.setTextureOffset(0, 12).addBox(0.0f, -3.0f, -1.0f, 0.0f, 5.0f, 7.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setupAnim(EntityCosmicCod entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.14f;
        float idleDegree = 0.25f;
        float swimSpeed = 0.8f;
        float swimDegree = 0.5f;
        float pitch = Maths.rad(Mth.m_14189_((float)(ageInTicks - (float)entity.f_19797_), (float)entity.prevFishPitch, (float)entity.getFishPitch()));
        this.swing(this.tail, idleSpeed, idleDegree, true, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.left_fin, idleSpeed, idleDegree, true, 3.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.right_fin, idleSpeed, idleDegree, false, 3.0f, 0.0f, ageInTicks, 1.0f);
        this.bob(this.body, idleSpeed, idleDegree * 4.0f, false, ageInTicks, 1.0f);
        this.swing(this.tail, swimSpeed, swimDegree, true, 2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.tail_tip, swimSpeed, swimDegree * 0.5f, true, 2.5f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.left_fin, swimSpeed, swimDegree, true, 1.0f, 0.3f, limbSwing, limbSwingAmount);
        this.flap(this.right_fin, swimSpeed, swimDegree, false, 1.0f, 0.3f, limbSwing, limbSwingAmount);
        this.root.rotateAngleX += pitch;
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.right_fin, (Object)this.left_fin, (Object)this.mouth, (Object)this.tail, (Object)this.tail_tip);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
