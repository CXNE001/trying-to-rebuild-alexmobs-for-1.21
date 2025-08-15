/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.Mth
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityBlobfish;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelBlobfishDepressurized
extends AdvancedEntityModel<EntityBlobfish> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox nose;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox tail_fin;
    private final AdvancedModelBox fin_left;
    private final AdvancedModelBox fin_right;

    public ModelBlobfishDepressurized() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -2.5f, 1.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-4.0f, -3.5f, -9.0f, 8.0f, 6.0f, 9.0f, 0.0f, false);
        this.nose = new AdvancedModelBox((AdvancedEntityModel)this, "nose");
        this.nose.setPos(0.0f, -0.5f, -9.0f);
        this.body.addChild((BasicModelPart)this.nose);
        this.nose.setTextureOffset(0, 0).addBox(-1.5f, -1.0f, -1.0f, 3.0f, 2.0f, 1.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, 0.75f, 0.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 16).addBox(-3.0f, -3.25f, 0.0f, 6.0f, 5.0f, 4.0f, 0.0f, false);
        this.tail_fin = new AdvancedModelBox((AdvancedEntityModel)this, "tail_fin");
        this.tail_fin.setPos(0.0f, -0.25f, 4.0f);
        this.tail.addChild((BasicModelPart)this.tail_fin);
        this.tail_fin.setTextureOffset(16, 21).addBox(0.0f, -3.0f, 0.0f, 0.0f, 5.0f, 5.0f, 0.0f, false);
        this.fin_left = new AdvancedModelBox((AdvancedEntityModel)this, "fin_left");
        this.fin_left.setPos(4.0f, 2.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.fin_left);
        this.setRotationAngle(this.fin_left, -1.5708f, 0.0f, 0.0f);
        this.fin_left.setTextureOffset(0, 4).addBox(0.0f, -1.5f, 0.0f, 3.0f, 3.0f, 0.0f, 0.0f, false);
        this.fin_right = new AdvancedModelBox((AdvancedEntityModel)this, "fin_right");
        this.fin_right.setPos(-4.0f, 2.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.fin_right);
        this.setRotationAngle(this.fin_right, -1.5708f, 0.0f, 0.0f);
        this.fin_right.setTextureOffset(0, 4).addBox(-3.0f, -1.5f, 0.0f, 3.0f, 3.0f, 0.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.fin_left, (Object)this.fin_right, (Object)this.tail, (Object)this.tail_fin, (Object)this.nose);
    }

    public void setupAnim(EntityBlobfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        if (!entity.m_20096_()) {
            this.body.rotateAngleX = headPitch * ((float)Math.PI / 180);
        }
        float swimSpeed = 1.5f;
        float swimDegree = 0.85f;
        this.swing(this.tail, swimSpeed, swimDegree * 0.5f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.tail_fin, swimSpeed, swimDegree * 0.5f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.fin_left, swimSpeed, swimDegree, false, 3.0f, -0.3f, limbSwing, limbSwingAmount);
        this.flap(this.fin_right, swimSpeed, swimDegree, true, 3.0f, -0.3f, limbSwing, limbSwingAmount);
        float lvt_6_1_ = Mth.m_14179_((float)Minecraft.m_91087_().m_91296_(), (float)entity.prevSquishFactor, (float)entity.squishFactor);
        float lvt_7_1_ = 1.0f / (lvt_6_1_ + 1.0f);
        float squishScale = 1.0f / lvt_7_1_;
        this.body.setScale(1.0f, squishScale, 1.0f);
        this.body.rotationPointY += lvt_6_1_ * -5.0f;
        this.body.setShouldScaleChildren(true);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
