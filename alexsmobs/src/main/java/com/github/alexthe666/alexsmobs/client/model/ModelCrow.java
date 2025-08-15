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
 *  net.minecraft.client.Minecraft
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCrow;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;

public class ModelCrow
extends AdvancedEntityModel<EntityCrow> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox leg_left;
    public final AdvancedModelBox leg_right;
    public final AdvancedModelBox wing_left;
    public final AdvancedModelBox wing_right;
    public final AdvancedModelBox tail;
    public final AdvancedModelBox head;
    public final AdvancedModelBox beak;

    public ModelCrow() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -2.1f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.setRotationAngle(this.body, 1.0036f, 0.0f, 0.0f);
        this.body.setTextureOffset(0, 0).addBox(-1.5f, -5.0f, 0.0f, 3.0f, 5.0f, 3.0f, 0.0f, false);
        this.leg_left = new AdvancedModelBox((AdvancedEntityModel)this, "leg_left");
        this.leg_left.setPos(0.9f, 0.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.leg_left);
        this.setRotationAngle(this.leg_left, 0.5672f, 0.0f, 0.0f);
        this.leg_left.setTextureOffset(0, 17).addBox(-0.5f, -2.0f, -2.0f, 1.0f, 2.0f, 3.0f, 0.0f, false);
        this.leg_right = new AdvancedModelBox((AdvancedEntityModel)this, "leg_right");
        this.leg_right.setPos(-0.9f, 0.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.leg_right);
        this.setRotationAngle(this.leg_right, 0.5672f, 0.0f, 0.0f);
        this.leg_right.setTextureOffset(0, 17).addBox(-0.5f, -2.0f, -2.0f, 1.0f, 2.0f, 3.0f, 0.0f, true);
        this.wing_left = new AdvancedModelBox((AdvancedEntityModel)this, "wing_left");
        this.wing_left.setPos(1.5f, -4.9f, 1.7f);
        this.body.addChild((BasicModelPart)this.wing_left);
        this.setRotationAngle(this.wing_left, 0.0436f, 0.0f, 0.0f);
        this.wing_left.setTextureOffset(13, 13).addBox(-0.5f, 0.0f, -1.7f, 1.0f, 6.0f, 3.0f, 0.0f, false);
        this.wing_right = new AdvancedModelBox((AdvancedEntityModel)this, "wing_right");
        this.wing_right.setPos(-1.5f, -4.9f, 1.7f);
        this.body.addChild((BasicModelPart)this.wing_right);
        this.setRotationAngle(this.wing_right, 0.0436f, 0.0f, 0.0f);
        this.wing_right.setTextureOffset(13, 13).addBox(-0.5f, 0.0f, -1.7f, 1.0f, 6.0f, 3.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, -0.1f, 3.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.setRotationAngle(this.tail, -0.1309f, 0.0f, 0.0f);
        this.tail.setTextureOffset(13, 0).addBox(-1.5f, 0.0f, -2.0f, 3.0f, 4.0f, 2.0f, -0.1f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -4.8f, 1.7f);
        this.body.addChild((BasicModelPart)this.head);
        this.setRotationAngle(this.head, -0.7418f, 0.0f, 0.0f);
        this.head.setTextureOffset(0, 9).addBox(-1.5f, -2.8f, -1.5f, 3.0f, 4.0f, 3.0f, -0.2f, false);
        this.beak = new AdvancedModelBox((AdvancedEntityModel)this, "beak");
        this.beak.setPos(0.0f, -1.4f, -1.9f);
        this.head.addChild((BasicModelPart)this.beak);
        this.beak.setTextureOffset(13, 7).addBox(-0.5f, -1.0f, -1.8f, 1.0f, 2.0f, 3.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.head, (Object)this.beak, (Object)this.leg_left, (Object)this.leg_right, (Object)this.tail, (Object)this.body, (Object)this.wing_left, (Object)this.wing_right);
    }

    public void setupAnim(EntityCrow entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float flapSpeed = 0.8f;
        float flapDegree = 0.2f;
        float walkSpeed = 1.2f;
        float walkDegree = 0.78f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float flyProgress = entity.prevFlyProgress + (entity.flyProgress - entity.prevFlyProgress) * partialTick;
        float sitProgress = entity.prevSitProgress + (entity.sitProgress - entity.prevSitProgress) * partialTick;
        float runProgress = Math.max(0.0f, limbSwingAmount * 5.0f - flyProgress);
        float biteProgress = entity.prevAttackProgress + (entity.attackProgress - entity.prevAttackProgress) * partialTick;
        this.progressRotationPrev(this.head, biteProgress, Maths.rad(60.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, biteProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_left, biteProgress, Maths.rad(-25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_right, biteProgress, Maths.rad(-25.0), 0.0f, 0.0f, 5.0f);
        this.walk(this.head, idleSpeed * 0.7f, idleDegree, false, -1.0f, 0.05f, ageInTicks, 1.0f);
        this.walk(this.tail, idleSpeed * 0.7f, idleDegree, false, 1.0f, 0.05f, ageInTicks, 1.0f);
        this.progressRotationPrev(this.body, flyProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, flyProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_left, flyProgress, Maths.rad(55.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_right, flyProgress, Maths.rad(55.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.wing_right, flyProgress, Maths.rad(-90.0), Maths.rad(90.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.wing_left, flyProgress, Maths.rad(-90.0), Maths.rad(-90.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.wing_right, flyProgress, 0.0f, 2.0f, 1.0f, 5.0f);
        this.progressPositionPrev(this.wing_left, flyProgress, 0.0f, 2.0f, 1.0f, 5.0f);
        this.progressRotationPrev(this.body, runProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, runProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_left, runProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_right, runProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        if (flyProgress > 0.0f) {
            this.swing(this.wing_right, flapSpeed, flapDegree * 5.0f, true, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.swing(this.wing_left, flapSpeed, flapDegree * 5.0f, false, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.bob(this.body, flapSpeed * 0.5f, flapDegree * 4.0f, true, ageInTicks, 1.0f);
            this.walk(this.head, flapSpeed, flapDegree * 0.2f, true, 2.0f, -0.1f, ageInTicks, 1.0f);
        } else {
            this.bob(this.body, walkSpeed * 1.0f, walkDegree * 1.3f, true, limbSwing, limbSwingAmount);
            this.walk(this.leg_right, walkSpeed, walkDegree * 1.85f, false, 0.0f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.leg_left, walkSpeed, walkDegree * 1.85f, true, 0.0f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.head, walkSpeed, walkDegree * 0.4f, false, 2.0f, -0.01f, limbSwing, limbSwingAmount);
            this.flap(this.tail, walkSpeed, walkDegree * 0.2f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        }
        this.progressRotationPrev(this.body, sitProgress, Maths.rad(-25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_left, sitProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_right, sitProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, sitProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.head.rotateAngleY += netHeadYaw / 57.295776f;
        this.head.rotateAngleZ += headPitch / 57.295776f;
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.45f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.0);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(0.9f, 0.9f, 0.9f);
        } else {
            this.head.setScale(0.9f, 0.9f, 0.9f);
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
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
