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

import com.github.alexthe666.alexsmobs.entity.EntityVoidWormPart;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class ModelVoidWormBody
extends AdvancedEntityModel<EntityVoidWormPart> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox frillstop_left;
    private final AdvancedModelBox frillstop_left_r1;
    private final AdvancedModelBox frillstop_right;
    private final AdvancedModelBox frillstop_right_r1;
    private final AdvancedModelBox frillsbottom_left;
    private final AdvancedModelBox frillsbottom_left_r1;
    private final AdvancedModelBox frillsbottom_right;
    private final AdvancedModelBox frillsbottom_right_r1;

    public ModelVoidWormBody(float f) {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -14.0f, -10.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-7.0f, -12.0f, 0.0f, 14.0f, 26.0f, 21.0f, f, false);
        this.frillstop_left = new AdvancedModelBox((AdvancedEntityModel)this, "frillstop_left");
        this.frillstop_left.setPos(7.0f, -12.0f, 10.0f);
        this.body.addChild((BasicModelPart)this.frillstop_left);
        this.frillstop_left_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "frillstop_left_r1");
        this.frillstop_left_r1.setPos(0.0f, 0.0f, 0.0f);
        this.frillstop_left.addChild((BasicModelPart)this.frillstop_left_r1);
        this.setRotationAngle(this.frillstop_left_r1, 0.0f, 0.0f, 0.7854f);
        this.frillstop_left_r1.setTextureOffset(0, 48).addBox(0.0f, -8.0f, -10.0f, 0.0f, 8.0f, 21.0f, f, false);
        this.frillstop_right = new AdvancedModelBox((AdvancedEntityModel)this, "frillstop_right");
        this.frillstop_right.setPos(-7.0f, -12.0f, 10.0f);
        this.body.addChild((BasicModelPart)this.frillstop_right);
        this.frillstop_right_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "frillstop_right_r1");
        this.frillstop_right_r1.setPos(0.0f, 0.0f, 0.0f);
        this.frillstop_right.addChild((BasicModelPart)this.frillstop_right_r1);
        this.setRotationAngle(this.frillstop_right_r1, 0.0f, 0.0f, -0.7854f);
        this.frillstop_right_r1.setTextureOffset(0, 48).addBox(0.0f, -8.0f, -10.0f, 0.0f, 8.0f, 21.0f, f, true);
        this.frillsbottom_left = new AdvancedModelBox((AdvancedEntityModel)this, "frillsbottom_left");
        this.frillsbottom_left.setPos(7.0f, 14.0f, 10.0f);
        this.body.addChild((BasicModelPart)this.frillsbottom_left);
        this.setRotationAngle(this.frillsbottom_left, 0.0f, 0.0f, 1.6581f);
        this.frillsbottom_left_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "frillsbottom_left_r1");
        this.frillsbottom_left_r1.setPos(0.0f, 0.0f, 0.0f);
        this.frillsbottom_left.addChild((BasicModelPart)this.frillsbottom_left_r1);
        this.setRotationAngle(this.frillsbottom_left_r1, 0.0f, 0.0f, 0.7854f);
        this.frillsbottom_left_r1.setTextureOffset(0, 48).addBox(0.0f, -8.0f, -10.0f, 0.0f, 8.0f, 21.0f, f, false);
        this.frillsbottom_right = new AdvancedModelBox((AdvancedEntityModel)this, "frillsbottom_right");
        this.frillsbottom_right.setPos(-7.0f, 14.0f, 10.0f);
        this.body.addChild((BasicModelPart)this.frillsbottom_right);
        this.setRotationAngle(this.frillsbottom_right, 0.0f, 0.0f, -1.6581f);
        this.frillsbottom_right_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "frillsbottom_right_r1");
        this.frillsbottom_right_r1.setPos(0.0f, 0.0f, 0.0f);
        this.frillsbottom_right.addChild((BasicModelPart)this.frillsbottom_right_r1);
        this.setRotationAngle(this.frillsbottom_right_r1, 0.0f, 0.0f, -0.7854f);
        this.frillsbottom_right_r1.setTextureOffset(0, 48).addBox(0.0f, -8.0f, -10.0f, 0.0f, 8.0f, 21.0f, f, true);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityVoidWormPart entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.root.rotateAngleX += headPitch * ((float)Math.PI / 180);
        float yawAmount = (entityIn.prevWormAngle + (entityIn.getWormAngle() - entityIn.prevWormAngle) * (ageInTicks - (float)entityIn.f_19797_)) / 57.295776f * 0.5f;
        this.body.rotateAngleZ += yawAmount;
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.frillsbottom_left, (Object)this.frillsbottom_left_r1, (Object)this.frillsbottom_right, (Object)this.frillsbottom_right_r1, (Object)this.frillstop_left, (Object)this.frillstop_left_r1, (Object)this.frillstop_right, (Object)this.frillstop_right_r1);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
