/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.client.Minecraft
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityLobster;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;

public class ModelLobster
extends AdvancedEntityModel<EntityLobster> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox antenna_left;
    private final AdvancedModelBox antenna_right;
    private final AdvancedModelBox arm_left;
    private final AdvancedModelBox hand_left;
    private final AdvancedModelBox arm_right;
    private final AdvancedModelBox hand_right;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox tail2;
    private final AdvancedModelBox legs_left;
    private final AdvancedModelBox legs_right;

    public ModelLobster() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -1.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 11).addBox(-2.0f, -1.4f, -7.0f, 4.0f, 2.0f, 7.0f, 0.0f, false);
        this.antenna_left = new AdvancedModelBox((AdvancedEntityModel)this, "antenna_left");
        this.antenna_left.setPos(1.4f, -0.5f, -7.0f);
        this.body.addChild((BasicModelPart)this.antenna_left);
        this.setRotationAngle(this.antenna_left, -0.3054f, -0.4363f, 0.0f);
        this.antenna_left.setTextureOffset(18, 18).addBox(0.0f, -0.9f, -5.0f, 0.0f, 1.0f, 5.0f, 0.0f, false);
        this.antenna_right = new AdvancedModelBox((AdvancedEntityModel)this, "antenna_right");
        this.antenna_right.setPos(-1.4f, -0.5f, -7.0f);
        this.body.addChild((BasicModelPart)this.antenna_right);
        this.setRotationAngle(this.antenna_right, -0.3054f, 0.4363f, 0.0f);
        this.antenna_right.setTextureOffset(18, 20).addBox(0.0f, -0.9f, -5.0f, 0.0f, 1.0f, 5.0f, 0.0f, true);
        this.arm_left = new AdvancedModelBox((AdvancedEntityModel)this, "arm_left");
        this.arm_left.setPos(1.7f, -0.4f, -5.0f);
        this.body.addChild((BasicModelPart)this.arm_left);
        this.setRotationAngle(this.arm_left, 0.0f, 0.6981f, 0.3491f);
        this.arm_left.setTextureOffset(15, 5).addBox(0.0f, 0.0f, -0.5f, 4.0f, 0.0f, 1.0f, 0.0f, false);
        this.hand_left = new AdvancedModelBox((AdvancedEntityModel)this, "hand_left");
        this.hand_left.setPos(4.0f, 0.4f, 0.4f);
        this.arm_left.addChild((BasicModelPart)this.hand_left);
        this.setRotationAngle(this.hand_left, 0.0f, 0.6981f, 0.0f);
        this.hand_left.setTextureOffset(0, 21).addBox(0.0f, -1.0f, -1.9f, 3.0f, 1.0f, 2.0f, 0.0f, false);
        this.arm_right = new AdvancedModelBox((AdvancedEntityModel)this, "arm_right");
        this.arm_right.setPos(-1.7f, -0.4f, -5.0f);
        this.body.addChild((BasicModelPart)this.arm_right);
        this.setRotationAngle(this.arm_right, 0.0f, -0.6981f, -0.3491f);
        this.arm_right.setTextureOffset(15, 6).addBox(-4.0f, 0.0f, -0.5f, 4.0f, 0.0f, 1.0f, 0.0f, true);
        this.hand_right = new AdvancedModelBox((AdvancedEntityModel)this, "hand_right");
        this.hand_right.setPos(-4.0f, 0.4f, 0.4f);
        this.arm_right.addChild((BasicModelPart)this.hand_right);
        this.setRotationAngle(this.hand_right, 0.0f, -0.6981f, 0.0f);
        this.hand_right.setTextureOffset(0, 25).addBox(-3.0f, -1.0f, -1.9f, 3.0f, 1.0f, 2.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, -0.4f, 0.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 0).addBox(-1.5f, -1.0f, 0.0f, 3.0f, 2.0f, 8.0f, 0.0f, false);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this, "tail2");
        this.tail2.setPos(0.0f, 0.0f, 6.0f);
        this.tail.addChild((BasicModelPart)this.tail2);
        this.tail2.setTextureOffset(15, 0).addBox(-4.0f, 0.0f, 0.0f, 8.0f, 0.0f, 4.0f, 0.0f, false);
        this.legs_left = new AdvancedModelBox((AdvancedEntityModel)this, "legs_left");
        this.legs_left.setPos(2.0f, 0.1f, -1.45f);
        this.body.addChild((BasicModelPart)this.legs_left);
        this.setRotationAngle(this.legs_left, 0.0f, 0.0f, 0.3054f);
        this.legs_left.setTextureOffset(16, 11).addBox(0.0f, 0.0f, -3.55f, 3.0f, 0.0f, 5.0f, 0.0f, false);
        this.legs_right = new AdvancedModelBox((AdvancedEntityModel)this, "legs_right");
        this.legs_right.setPos(-2.0f, 0.1f, -1.45f);
        this.body.addChild((BasicModelPart)this.legs_right);
        this.setRotationAngle(this.legs_right, 0.0f, 0.0f, -0.3054f);
        this.legs_right.setTextureOffset(25, 11).addBox(-3.0f, 0.0f, -3.55f, 3.0f, 0.0f, 5.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityLobster entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.1f;
        float idleDegree = 0.3f;
        float walkSpeed = 3.0f;
        float walkDegree = 0.6f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float attackProgress = entityIn.prevAttackProgress + (entityIn.attackProgress - entityIn.prevAttackProgress) * partialTick;
        this.progressRotationPrev(this.arm_left, attackProgress, 0.0f, Maths.rad(45.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_right, attackProgress, 0.0f, Maths.rad(-45.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.hand_left, attackProgress, 0.0f, Maths.rad(-30.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.hand_right, attackProgress, 0.0f, Maths.rad(30.0), 0.0f, 5.0f);
        this.walk(this.antenna_left, idleSpeed * 1.5f, idleDegree, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.antenna_right, idleSpeed * 1.5f, idleDegree, true, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.tail, idleSpeed, idleDegree * 0.2f, false, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.tail2, idleSpeed, idleDegree * 0.15f, false, 1.0f, 0.1f, ageInTicks, 1.0f);
        this.walk(this.legs_left, walkSpeed, walkDegree * 0.8f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.legs_left, walkSpeed, walkDegree * 1.0f, false, 1.0f, 0.1f, limbSwing, limbSwingAmount);
        this.walk(this.legs_right, walkSpeed, walkDegree * 0.8f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.legs_right, walkSpeed, walkDegree * 1.0f, true, 1.0f, 0.1f, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed * 0.5f, walkDegree * 4.0f, true, limbSwing, limbSwingAmount);
        this.swing(this.arm_left, walkSpeed, walkDegree * 1.0f, true, 2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.arm_right, walkSpeed, walkDegree * 1.0f, true, 2.0f, 0.0f, limbSwing, limbSwingAmount);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.antenna_left, (Object)this.antenna_right, (Object)this.arm_left, (Object)this.arm_right, (Object)this.hand_left, (Object)this.hand_right, (Object)this.tail, (Object)this.tail2, (Object)this.legs_left, (Object)this.legs_right, (Object[])new AdvancedModelBox[0]);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
