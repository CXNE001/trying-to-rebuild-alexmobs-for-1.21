/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityGust;
import com.github.alexthe666.alexsmobs.entity.EntityGuster;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelGuster
extends AdvancedEntityModel<EntityGuster> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox tornado;
    private final AdvancedModelBox tornado2;
    private final AdvancedModelBox tornadomid;
    private final AdvancedModelBox tornado3;
    private final AdvancedModelBox tornado4;
    private final AdvancedModelBox eyes;
    private final AdvancedModelBox eye_left;
    private final AdvancedModelBox eye_right;

    public ModelGuster() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.tornado = new AdvancedModelBox((AdvancedEntityModel)this, "tornado");
        this.tornado.setPos(0.0f, -4.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.tornado);
        this.tornado.setTextureOffset(65, 72).addBox(-4.0f, -4.0f, -4.0f, 8.0f, 8.0f, 8.0f, 0.0f, false);
        this.tornado2 = new AdvancedModelBox((AdvancedEntityModel)this, "tornado2");
        this.tornado2.setPos(0.0f, -8.0f, 0.0f);
        this.tornado.addChild((BasicModelPart)this.tornado2);
        this.tornado2.setTextureOffset(0, 72).addBox(-8.0f, -4.0f, -8.0f, 16.0f, 8.0f, 16.0f, 0.0f, false);
        this.tornadomid = new AdvancedModelBox((AdvancedEntityModel)this, "tornadomid");
        this.tornadomid.setPos(0.0f, 6.0f, 0.0f);
        this.tornado2.addChild((BasicModelPart)this.tornadomid);
        this.tornadomid.setTextureOffset(16, 96).addBox(-14.0f, -4.0f, -14.0f, 28.0f, 4.0f, 28.0f, 0.0f, false);
        this.tornado3 = new AdvancedModelBox((AdvancedEntityModel)this, "tornado3");
        this.tornado3.setPos(0.0f, -8.0f, 0.0f);
        this.tornado2.addChild((BasicModelPart)this.tornado3);
        this.tornado3.setTextureOffset(0, 39).addBox(-12.0f, -4.0f, -12.0f, 24.0f, 8.0f, 24.0f, 0.0f, false);
        this.tornado4 = new AdvancedModelBox((AdvancedEntityModel)this, "tornado4");
        this.tornado4.setPos(0.0f, -8.0f, 0.0f);
        this.tornado3.addChild((BasicModelPart)this.tornado4);
        this.tornado4.setTextureOffset(0, 0).addBox(-15.0f, -4.0f, -15.0f, 30.0f, 8.0f, 30.0f, 0.0f, false);
        this.eyes = new AdvancedModelBox((AdvancedEntityModel)this, "eyes");
        this.eyes.setPos(0.0f, -18.0f, -15.0f);
        this.root.addChild((BasicModelPart)this.eyes);
        this.eye_left = new AdvancedModelBox((AdvancedEntityModel)this, "eye_left");
        this.eye_left.setPos(4.0f, 0.0f, 0.0f);
        this.eyes.addChild((BasicModelPart)this.eye_left);
        this.eye_left.setTextureOffset(8, 13).addBox(-3.0f, -4.0f, 0.0f, 6.0f, 8.0f, 0.0f, 0.0f, false);
        this.eye_right = new AdvancedModelBox((AdvancedEntityModel)this, "eye_right");
        this.eye_right.setPos(-4.0f, 0.0f, 0.0f);
        this.eyes.addChild((BasicModelPart)this.eye_right);
        this.eye_right.setTextureOffset(8, 13).addBox(-3.0f, -4.0f, 0.0f, 6.0f, 8.0f, 0.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.eyes, (Object)this.eye_left, (Object)this.eye_right, (Object)this.tornado, (Object)this.tornado2, (Object)this.tornado3, (Object)this.tornado4, (Object)this.tornadomid);
    }

    public void setupAnim(EntityGuster entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        AdvancedModelBox[] tornadoBoxes = new AdvancedModelBox[]{this.tornado, this.tornado2, this.tornado3, this.tornado4};
        float idleSpeed = 0.1f;
        float idleDegree = 1.0f;
        float walkSpeed = 0.5f;
        float walkDegree = 1.0f;
        this.bob(this.root, walkSpeed, walkDegree * 3.0f, false, limbSwing, limbSwingAmount);
        this.chainFlap(tornadoBoxes, walkSpeed, walkDegree * 0.1f, -2.0, limbSwing, limbSwingAmount);
        this.bob(this.root, idleSpeed, idleDegree * 3.0f, false, ageInTicks, 1.0f);
        if (entity.isGooglyEyes()) {
            this.eye_left.rotationPointY += (float)(Math.sin((double)ageInTicks * 0.7 - 2.0) * 1.9);
            this.eye_right.rotationPointY += (float)(Math.sin((double)ageInTicks * 0.7 + 2.0) * 1.9);
        } else {
            this.eye_left.rotationPointY += (float)(Math.sin((double)ageInTicks * 0.1 - 2.0) * 0.9);
            this.eye_right.rotationPointY += (float)(Math.sin((double)ageInTicks * 0.1 + 2.0) * 0.9);
        }
        this.bob(this.eyes, idleSpeed, idleDegree * -3.2f, false, ageInTicks, 1.0f);
        this.eyes.rotateAngleY += netHeadYaw * 0.5f * ((float)Math.PI / 180);
        this.eyes.rotateAngleX += headPitch * 0.8f * ((float)Math.PI / 180);
        this.tornado.rotationPointX = (float)((double)this.tornado.rotationPointX + Math.cos(ageInTicks * 0.7f) * 4.0);
        this.tornado.rotationPointZ = (float)((double)this.tornado.rotationPointZ + Math.sin(ageInTicks * 0.7f) * 4.0);
        this.tornado.rotationPointX = (float)((double)this.tornado.rotationPointX + (Math.cos(ageInTicks * 0.3f) * 2.0 - (double)this.tornado.rotationPointX));
        this.tornado.rotationPointZ = (float)((double)this.tornado.rotationPointZ + (Math.sin(ageInTicks * 0.3f) * 2.0 - (double)this.tornado.rotationPointZ));
        this.tornadomid.rotateAngleZ = (float)((double)this.tornadomid.rotateAngleZ + Math.sin(ageInTicks * 0.2f) * 0.1);
        this.tornado.rotateAngleY -= ageInTicks * 1.0f;
        this.tornado2.rotateAngleY -= this.tornado.rotateAngleY + ageInTicks * 0.3f;
        this.tornado3.rotateAngleY -= this.tornado.rotateAngleY + this.tornado2.rotateAngleY + ageInTicks * 0.2f;
        this.tornado4.rotateAngleY -= this.tornado.rotateAngleY + this.tornado2.rotateAngleY + this.tornado3.rotateAngleY + ageInTicks * 0.34f;
        this.tornadomid.rotateAngleY += ageInTicks * 0.5f;
        this.eyes.rotationPointZ = (float)((double)this.eyes.rotationPointZ - (2.0 + Math.cos(this.tornado3.rotateAngleY)));
    }

    public void animateGust(EntityGust entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
        this.resetToDefaultPose();
        AdvancedModelBox[] tornadoBoxes = new AdvancedModelBox[]{this.tornado, this.tornado2, this.tornado3, this.tornado4};
        float idleSpeed = 0.1f;
        float idleDegree = 1.0f;
        float walkSpeed = 0.5f;
        float walkDegree = 1.0f;
        this.bob(this.root, walkSpeed, walkDegree * 3.0f, false, limbSwing, limbSwingAmount);
        this.chainFlap(tornadoBoxes, walkSpeed, walkDegree * 0.1f, -2.0, limbSwing, limbSwingAmount);
        this.bob(this.root, idleSpeed, idleDegree * 3.0f, false, ageInTicks, 1.0f);
        this.bob(this.eyes, idleSpeed, idleDegree * -3.2f, false, ageInTicks, 1.0f);
        this.tornado.rotationPointX = (float)((double)this.tornado.rotationPointX + Math.cos(ageInTicks * 0.7f) * 4.0);
        this.tornado.rotationPointZ = (float)((double)this.tornado.rotationPointZ + Math.sin(ageInTicks * 0.7f) * 4.0);
        this.tornado.rotationPointX = (float)((double)this.tornado.rotationPointX + (Math.cos(ageInTicks * 0.3f) * 2.0 - (double)this.tornado.rotationPointX));
        this.tornado.rotationPointZ = (float)((double)this.tornado.rotationPointZ + (Math.sin(ageInTicks * 0.3f) * 2.0 - (double)this.tornado.rotationPointZ));
        this.tornadomid.rotateAngleZ = (float)((double)this.tornadomid.rotateAngleZ + Math.sin(ageInTicks * 0.2f) * 0.1);
        this.tornado.rotateAngleY -= ageInTicks * 1.0f;
        this.tornado2.rotateAngleY -= this.tornado.rotateAngleY + ageInTicks * 0.3f;
        this.tornado3.rotateAngleY -= this.tornado.rotateAngleY + this.tornado2.rotateAngleY + ageInTicks * 0.2f;
        this.tornado4.rotateAngleY -= this.tornado.rotateAngleY + this.tornado2.rotateAngleY + this.tornado3.rotateAngleY + ageInTicks * 0.34f;
        this.tornadomid.rotateAngleY += ageInTicks * 0.5f;
        this.eyes.rotationPointZ = (float)((double)this.eyes.rotationPointZ - (2.0 + Math.cos(this.tornado3.rotateAngleY)));
    }

    public void hideEyes() {
        this.eyes.showModel = false;
        this.eye_left.showModel = false;
        this.eye_right.showModel = false;
    }

    public void showEyes() {
        this.eyes.showModel = true;
        this.eye_left.showModel = true;
        this.eye_right.showModel = true;
    }

    public void m_7695_(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
