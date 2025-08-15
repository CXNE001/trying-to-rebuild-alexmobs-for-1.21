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
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityEnderiophage;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class ModelEnderiophage
extends AdvancedEntityModel<EntityEnderiophage> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox mouth;
    private final AdvancedModelBox sheath;
    private final AdvancedModelBox collar;
    private final AdvancedModelBox capsid;
    private final AdvancedModelBox eye;
    private final AdvancedModelBox tailmid_left;
    private final AdvancedModelBox tailmid_right;
    private final AdvancedModelBox tailback_left;
    private final AdvancedModelBox tailback_right;
    private final AdvancedModelBox tailfront_left;
    private final AdvancedModelBox tailfront_right;
    private final AdvancedModelBox tailmid_leftPivot;
    private final AdvancedModelBox tailmid_rightPivot;
    private final AdvancedModelBox tailback_leftPivot;
    private final AdvancedModelBox tailback_rightPivot;
    private final AdvancedModelBox tailfront_leftPivot;
    private final AdvancedModelBox tailfront_rightPivot;

    public ModelEnderiophage() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -11.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 30).addBox(-4.0f, -2.0f, -4.0f, 8.0f, 3.0f, 8.0f, 0.0f, false);
        this.mouth = new AdvancedModelBox((AdvancedEntityModel)this, "mouth");
        this.mouth.setPos(0.0f, 1.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.mouth);
        this.mouth.setTextureOffset(0, 0).addBox(-1.0f, -5.0f, -1.0f, 2.0f, 7.0f, 2.0f, 0.0f, false);
        this.sheath = new AdvancedModelBox((AdvancedEntityModel)this, "sheath");
        this.sheath.setPos(0.0f, -2.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.sheath);
        this.sheath.setTextureOffset(50, 43).addBox(-2.0f, -14.0f, -2.0f, 4.0f, 14.0f, 4.0f, 0.0f, false);
        this.collar = new AdvancedModelBox((AdvancedEntityModel)this, "collar");
        this.collar.setPos(0.0f, -14.0f, 0.0f);
        this.sheath.addChild((BasicModelPart)this.collar);
        this.collar.setTextureOffset(0, 55).addBox(-3.0f, -1.0f, -3.0f, 6.0f, 1.0f, 6.0f, 0.0f, false);
        this.capsid = new AdvancedModelBox((AdvancedEntityModel)this, "capsid");
        this.capsid.setPos(0.0f, -1.0f, 0.0f);
        this.collar.addChild((BasicModelPart)this.capsid);
        this.capsid.setTextureOffset(0, 0).addBox(-7.0f, -15.0f, -7.0f, 14.0f, 15.0f, 14.0f, 0.0f, false);
        this.eye = new AdvancedModelBox((AdvancedEntityModel)this, "eye");
        this.eye.setPos(0.0f, -8.0f, 0.0f);
        this.capsid.addChild((BasicModelPart)this.eye);
        this.eye.setTextureOffset(43, 0).addBox(-3.0f, -3.0f, -3.0f, 6.0f, 6.0f, 6.0f, 0.0f, false);
        this.tailmid_leftPivot = new AdvancedModelBox((AdvancedEntityModel)this, "tailmid_leftPivot");
        this.tailmid_leftPivot.setPos(4.0f, -1.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.tailmid_leftPivot);
        this.tailmid_left = new AdvancedModelBox((AdvancedEntityModel)this, "tailmid_left");
        this.tailmid_leftPivot.addChild((BasicModelPart)this.tailmid_left);
        this.tailmid_left.setTextureOffset(25, 43).addBox(0.0f, 0.0f, 0.0f, 12.0f, 12.0f, 0.0f, 0.0f, false);
        this.tailmid_rightPivot = new AdvancedModelBox((AdvancedEntityModel)this, "tailmid_rightPivot");
        this.tailmid_rightPivot.setPos(-4.0f, -1.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.tailmid_rightPivot);
        this.tailmid_right = new AdvancedModelBox((AdvancedEntityModel)this, "tailmid_right");
        this.tailmid_rightPivot.addChild((BasicModelPart)this.tailmid_right);
        this.tailmid_right.setTextureOffset(25, 43).addBox(-12.0f, 0.0f, 0.0f, 12.0f, 12.0f, 0.0f, 0.0f, true);
        this.tailback_leftPivot = new AdvancedModelBox((AdvancedEntityModel)this, "tailback_leftPivot");
        this.tailback_leftPivot.setPos(4.0f, -1.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.tailback_leftPivot);
        this.setRotationAngle(this.tailback_leftPivot, 0.0f, -0.7854f, 0.0f);
        this.tailback_left = new AdvancedModelBox((AdvancedEntityModel)this, "tailback_left");
        this.tailback_leftPivot.addChild((BasicModelPart)this.tailback_left);
        this.tailback_left.setTextureOffset(33, 30).addBox(0.0f, 0.0f, 0.0f, 12.0f, 12.0f, 0.0f, 0.0f, false);
        this.tailback_rightPivot = new AdvancedModelBox((AdvancedEntityModel)this, "tailback_rightPivot");
        this.tailback_rightPivot.setPos(-4.0f, -1.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.tailback_rightPivot);
        this.setRotationAngle(this.tailback_rightPivot, 0.0f, 0.7854f, 0.0f);
        this.tailback_right = new AdvancedModelBox((AdvancedEntityModel)this, "tailback_right");
        this.tailback_rightPivot.addChild((BasicModelPart)this.tailback_right);
        this.tailback_right.setTextureOffset(33, 30).addBox(-12.0f, 0.0f, 0.0f, 12.0f, 12.0f, 0.0f, 0.0f, true);
        this.tailfront_leftPivot = new AdvancedModelBox((AdvancedEntityModel)this, "tailfront_leftPivot");
        this.tailfront_leftPivot.setPos(4.0f, -1.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.tailfront_leftPivot);
        this.setRotationAngle(this.tailfront_leftPivot, 0.0f, 0.6981f, 0.0f);
        this.tailfront_left = new AdvancedModelBox((AdvancedEntityModel)this, "tailfront_left");
        this.tailfront_leftPivot.addChild((BasicModelPart)this.tailfront_left);
        this.tailfront_left.setTextureOffset(0, 42).addBox(0.0f, 0.0f, 0.0f, 12.0f, 12.0f, 0.0f, 0.0f, false);
        this.tailfront_rightPivot = new AdvancedModelBox((AdvancedEntityModel)this, "tailfront_rightPivot");
        this.tailfront_rightPivot.setPos(-4.0f, -1.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.tailfront_rightPivot);
        this.setRotationAngle(this.tailfront_rightPivot, 0.0f, -0.6981f, 0.0f);
        this.tailfront_right = new AdvancedModelBox((AdvancedEntityModel)this, "tailfront_right");
        this.tailfront_rightPivot.addChild((BasicModelPart)this.tailfront_right);
        this.tailfront_right.setTextureOffset(0, 42).addBox(-12.0f, 0.0f, 0.0f, 12.0f, 12.0f, 0.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.tailback_left, (Object)this.tailback_right, (Object)this.tailfront_left, (Object)this.tailfront_right, (Object)this.tailmid_left, (Object)this.tailmid_right, (Object)this.tailback_leftPivot, (Object)this.tailback_rightPivot, (Object)this.tailfront_leftPivot, (Object)this.tailfront_rightPivot, (Object)this.tailmid_leftPivot, (Object[])new AdvancedModelBox[]{this.tailmid_rightPivot, this.body, this.capsid, this.eye, this.mouth, this.sheath, this.collar});
    }

    public void setupAnim(EntityEnderiophage entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        Entity look = Minecraft.m_91087_().m_91288_();
        float partialTicks = ageInTicks - (float)entity.f_19797_;
        float idleSpeed = 0.25f;
        float idleDegree = 0.1f;
        float walkSpeed = 2.0f;
        float walkDegree = 0.6f;
        float flyProgress = entity.prevFlyProgress + (entity.flyProgress - entity.prevFlyProgress) * partialTicks;
        float phagePitch = Maths.rad(Mth.m_14189_((float)partialTicks, (float)entity.prevPhagePitch, (float)entity.getPhagePitch()));
        float totalYaw = Maths.rad(Mth.m_14189_((float)partialTicks, (float)entity.f_20884_, (float)entity.f_20883_));
        float tentacleProgress = (5.0f - limbSwingAmount * 10.0f) * flyProgress * 0.2f;
        this.bob(this.eye, idleSpeed, idleDegree * -8.0f, false, ageInTicks, 1.0f);
        this.flap(this.tailback_left, idleSpeed, idleDegree, true, -2.0f, 0.5f, ageInTicks, 1.0f);
        this.flap(this.tailback_right, idleSpeed, idleDegree, false, -2.0f, 0.5f, ageInTicks, 1.0f);
        this.walk(this.tailback_left, idleSpeed, idleDegree, false, -2.0f, 0.25f, ageInTicks, 1.0f);
        this.walk(this.tailback_right, idleSpeed, idleDegree, false, -2.0f, 0.25f, ageInTicks, 1.0f);
        this.flap(this.tailmid_left, idleSpeed, idleDegree, true, -2.0f, 0.5f, ageInTicks, 1.0f);
        this.flap(this.tailmid_right, idleSpeed, idleDegree, false, -2.0f, 0.5f, ageInTicks, 1.0f);
        this.flap(this.tailfront_left, idleSpeed, idleDegree, true, -2.0f, 0.5f, ageInTicks, 1.0f);
        this.flap(this.tailfront_right, idleSpeed, idleDegree, false, -2.0f, 0.5f, ageInTicks, 1.0f);
        this.walk(this.tailfront_left, idleSpeed, idleDegree, true, -2.0f, 0.25f, ageInTicks, 1.0f);
        this.walk(this.tailfront_right, idleSpeed, idleDegree, true, -2.0f, 0.25f, ageInTicks, 1.0f);
        this.bob(this.body, idleSpeed, idleDegree * 8.0f, false, ageInTicks, 1.0f);
        this.body.rotationPointY += 8.0f;
        if (flyProgress != 5.0f) {
            this.walk(this.sheath, walkSpeed, walkDegree * 0.2f, true, 1.0f, 0.05f, limbSwing, limbSwingAmount *= 1.0f - flyProgress * 0.2f);
            this.swing(this.tailfront_right, walkSpeed, walkDegree * -1.2f, false, 0.0f, -0.3f, limbSwing, limbSwingAmount);
            this.swing(this.tailfront_left, walkSpeed, walkDegree * -1.2f, false, 0.0f, 0.3f, limbSwing, limbSwingAmount);
            this.flap(this.tailfront_right, walkSpeed, walkDegree * -1.6f, false, 0.0f, -0.2f, limbSwing, limbSwingAmount);
            this.flap(this.tailfront_left, walkSpeed, walkDegree * -1.6f, false, 0.0f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.tailfront_right, walkSpeed, walkDegree * -1.6f, true, 0.0f, 0.3f, limbSwing, limbSwingAmount);
            this.walk(this.tailfront_left, walkSpeed, walkDegree * -1.6f, false, 0.0f, -0.3f, limbSwing, limbSwingAmount);
            this.swing(this.tailmid_right, walkSpeed, walkDegree * -1.2f, false, -2.5f, 0.2f, limbSwing, limbSwingAmount);
            this.swing(this.tailmid_left, walkSpeed, walkDegree * -1.2f, false, -2.5f, -0.2f, limbSwing, limbSwingAmount);
            this.flap(this.tailmid_right, walkSpeed, walkDegree * -1.6f, false, -2.5f, 0.5f, limbSwing, limbSwingAmount);
            this.flap(this.tailmid_left, walkSpeed, walkDegree * -1.6f, false, -2.5f, -0.5f, limbSwing, limbSwingAmount);
            this.walk(this.tailmid_right, walkSpeed, walkDegree * -1.6f, true, -2.5f, 0.3f, limbSwing, limbSwingAmount);
            this.walk(this.tailmid_left, walkSpeed, walkDegree * -1.6f, false, -2.5f, -0.3f, limbSwing, limbSwingAmount);
            this.swing(this.tailback_right, walkSpeed, walkDegree * -1.2f, false, -5.0f, -0.2f, limbSwing, limbSwingAmount);
            this.swing(this.tailback_left, walkSpeed, walkDegree * -1.2f, false, -5.0f, 0.2f, limbSwing, limbSwingAmount);
            this.flap(this.tailback_right, walkSpeed, walkDegree * -1.6f, false, -5.0f, 0.5f, limbSwing, limbSwingAmount);
            this.flap(this.tailback_left, walkSpeed, walkDegree * -1.6f, false, -5.0f, -0.5f, limbSwing, limbSwingAmount);
            this.walk(this.tailback_right, walkSpeed, walkDegree * -1.6f, true, -5.0f, 0.3f, limbSwing, limbSwingAmount);
            this.walk(this.tailback_left, walkSpeed, walkDegree * -1.6f, false, -5.0f, -0.3f, limbSwing, limbSwingAmount);
            this.bob(this.body, walkSpeed * 1.5f, walkDegree * 6.0f, false, limbSwing, limbSwingAmount);
            this.progressRotationPrev(this.body, limbSwingAmount, Maths.rad(-15.0), 0.0f, 0.0f, 1.0f);
            this.progressRotationPrev(this.sheath, limbSwingAmount, Maths.rad(-15.0), 0.0f, 0.0f, 1.0f);
            this.progressRotationPrev(this.tailfront_left, limbSwingAmount, Maths.rad(15.0), 0.0f, 0.0f, 1.0f);
            this.progressRotationPrev(this.tailfront_right, limbSwingAmount, Maths.rad(15.0), 0.0f, 0.0f, 1.0f);
        }
        this.eye.showModel = !entity.isMissingEye();
        if (entity.m_20159_()) {
            this.body.rotateAngleX += 1.5707964f;
            this.body.rotateAngleY += 1.5707964f * (float)entity.passengerIndex;
            this.sheath.setScale(1.0f, (float)((double)0.85f + Math.sin(ageInTicks) * (double)0.15f), 1.0f);
            this.collar.rotationPointY -= (float)(Math.sin(ageInTicks) * (double)0.15f - (double)0.15f) * 12.0f;
            this.capsid.setScale((float)((double)0.85f + Math.sin(ageInTicks + 2.0f) * (double)0.15f), (float)(1.0 + Math.sin(ageInTicks) * (double)0.15f), (float)((double)0.85f + Math.sin(ageInTicks + 2.0f) * (double)0.15f));
            this.mouth.rotationPointY = (float)((double)this.mouth.rotationPointY + (Math.sin(ageInTicks) + 1.0) * 2.0);
            tentacleProgress = -2.0f;
        } else {
            this.sheath.setScale(1.0f, 1.0f, 1.0f);
            this.capsid.setScale(1.0f, 1.0f, 1.0f);
            this.body.rotateAngleX -= phagePitch * flyProgress * 0.2f;
        }
        this.progressPositionPrev(this.body, tentacleProgress, 0.0f, -6.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tailfront_left, tentacleProgress, 0.0f, 0.0f, Maths.rad(-45.0), 5.0f);
        this.progressRotationPrev(this.tailmid_left, tentacleProgress, 0.0f, 0.0f, Maths.rad(-45.0), 5.0f);
        this.progressRotationPrev(this.tailback_left, tentacleProgress, 0.0f, 0.0f, Maths.rad(-45.0), 5.0f);
        this.progressRotationPrev(this.tailfront_right, tentacleProgress, 0.0f, 0.0f, Maths.rad(45.0), 5.0f);
        this.progressRotationPrev(this.tailmid_right, tentacleProgress, 0.0f, 0.0f, Maths.rad(45.0), 5.0f);
        this.progressRotationPrev(this.tailback_right, tentacleProgress, 0.0f, 0.0f, Maths.rad(45.0), 5.0f);
        if (look != null) {
            Vec3 vector3d = look.m_20299_(partialTicks);
            Vec3 vector3d1 = entity.m_20299_(partialTicks);
            Vec3 vector3d2 = vector3d.m_82546_(vector3d1);
            float f = Mth.m_14116_((float)((float)(vector3d2.f_82479_ * vector3d2.f_82479_ + vector3d2.f_82481_ * vector3d2.f_82481_))) - totalYaw;
            this.eye.rotateAngleY += -((float)Mth.m_14136_((double)vector3d2.f_82479_, (double)vector3d2.f_82481_)) - totalYaw;
            this.eye.rotateAngleX = (float)((double)this.eye.rotateAngleX + (-Mth.m_14008_((double)(vector3d2.f_82480_ * 0.5), (double)-1.5707963267948966, (double)1.5707963267948966) + (double)(phagePitch * flyProgress * 0.2f)));
        }
    }

    public void m_7695_(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z) {
        advancedModelBox.rotateAngleX = x;
        advancedModelBox.rotateAngleY = y;
        advancedModelBox.rotateAngleZ = z;
    }
}
