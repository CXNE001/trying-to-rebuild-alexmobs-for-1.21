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

import com.github.alexthe666.alexsmobs.entity.EntityMimicube;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelMimicube
extends AdvancedEntityModel<EntityMimicube> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox innerbody;
    public final AdvancedModelBox mouth;
    public final AdvancedModelBox eye_left;
    public final AdvancedModelBox eye_right;

    public ModelMimicube() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, 0.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-8.0f, -14.0f, -8.0f, 16.0f, 14.0f, 16.0f, 0.0f, false);
        this.innerbody = new AdvancedModelBox((AdvancedEntityModel)this, "innerbody");
        this.innerbody.setPos(0.0f, -7.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.innerbody);
        this.innerbody.setTextureOffset(0, 31).addBox(-4.0f, -4.0f, -4.0f, 8.0f, 8.0f, 8.0f, 0.0f, false);
        this.mouth = new AdvancedModelBox((AdvancedEntityModel)this, "mouth");
        this.mouth.setPos(2.0f, 4.0f, -5.0f);
        this.innerbody.addChild((BasicModelPart)this.mouth);
        this.mouth.setTextureOffset(0, 12).addBox(-2.0f, -2.0f, 0.0f, 2.0f, 2.0f, 1.0f, 0.0f, false);
        this.eye_left = new AdvancedModelBox((AdvancedEntityModel)this, "eye_left");
        this.eye_left.setPos(3.5f, -1.5f, -4.0f);
        this.innerbody.addChild((BasicModelPart)this.eye_left);
        this.setRotationAngle(this.eye_left, 0.0f, 0.0f, 0.3054f);
        this.eye_left.setTextureOffset(0, 6).addBox(-1.5f, -1.5f, -1.0f, 3.0f, 3.0f, 2.0f, 0.0f, false);
        this.eye_right = new AdvancedModelBox((AdvancedEntityModel)this, "eye_right");
        this.eye_right.setPos(-3.5f, -0.5f, -4.0f);
        this.innerbody.addChild((BasicModelPart)this.eye_right);
        this.setRotationAngle(this.eye_right, 0.0f, 0.0f, -0.3927f);
        this.eye_right.setTextureOffset(0, 0).addBox(-1.5f, -1.5f, -1.0f, 3.0f, 3.0f, 2.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.innerbody, (Object)this.eye_left, (Object)this.eye_right, (Object)this.mouth);
    }

    public void setupAnim(EntityMimicube entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.1f;
        float idleDegree = 1.0f;
        this.bob(this.innerbody, idleDegree, idleSpeed, false, limbSwing, limbSwingAmount);
        this.flap(this.innerbody, idleSpeed * 1.3f, idleDegree * 0.05f, false, 2.0f, 0.0f, ageInTicks, 1.0f);
        float lvt_6_1_ = Mth.m_14179_((float)Minecraft.m_91087_().m_91296_(), (float)entity.prevSquishFactor, (float)entity.squishFactor);
        float lvt_7_1_ = 1.0f / (lvt_6_1_ + 1.0f);
        float squishScale = 1.0f / lvt_7_1_;
        this.innerbody.rotationPointY += lvt_6_1_ * -5.0f;
        this.body.setScale(1.0f, squishScale, 1.0f);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
