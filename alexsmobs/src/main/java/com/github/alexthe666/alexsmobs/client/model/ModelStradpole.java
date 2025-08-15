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

import com.github.alexthe666.alexsmobs.entity.EntityStradpole;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;

public class ModelStradpole
extends AdvancedEntityModel<EntityStradpole> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox hair_left;
    private final AdvancedModelBox hair_right;
    private final AdvancedModelBox tail;

    public ModelStradpole() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -4.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-4.0f, -4.0f, -4.0f, 8.0f, 8.0f, 8.0f, 0.0f, false);
        this.hair_left = new AdvancedModelBox((AdvancedEntityModel)this, "hair_left");
        this.hair_left.setPos(4.0f, -4.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.hair_left);
        this.setRotationAngle(this.hair_left, 0.0f, 0.0f, 1.1345f);
        this.hair_left.setTextureOffset(0, 17).addBox(0.0f, 0.0f, -3.0f, 9.0f, 0.0f, 8.0f, 0.0f, false);
        this.hair_right = new AdvancedModelBox((AdvancedEntityModel)this, "hair_right");
        this.hair_right.setPos(-4.0f, -4.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.hair_right);
        this.setRotationAngle(this.hair_right, 0.0f, 0.0f, -1.1345f);
        this.hair_right.setTextureOffset(0, 17).addBox(-9.0f, 0.0f, -3.0f, 9.0f, 0.0f, 8.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, 0.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(24, 24).addBox(0.0f, -4.0f, 0.0f, 0.0f, 8.0f, 14.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityStradpole entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float walkSpeed = 1.0f;
        float walkDegree = 0.4f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.25f;
        this.flap(this.hair_right, idleSpeed, idleDegree, true, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.hair_left, idleSpeed, idleDegree, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.flap(this.body, walkSpeed, walkDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.body, walkSpeed, walkDegree * 0.4f, true, 2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.tail, walkSpeed * 1.4f, walkDegree * 2.0f, false, 2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.faceTarget(netHeadYaw, headPitch, 1.2f, new AdvancedModelBox[]{this.body});
        float partialTick = Minecraft.m_91087_().m_91296_();
        float birdPitch = entity.prevSwimPitch + (entity.swimPitch - entity.prevSwimPitch) * partialTick;
        this.body.rotateAngleX += birdPitch * ((float)Math.PI / 180);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.tail, (Object)this.body, (Object)this.hair_left, (Object)this.hair_right);
    }

    public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z) {
        advancedModelBox.rotateAngleX = x;
        advancedModelBox.rotateAngleY = y;
        advancedModelBox.rotateAngleZ = z;
    }
}
