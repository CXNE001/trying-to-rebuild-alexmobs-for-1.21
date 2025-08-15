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
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCockroach;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelCockroach
extends AdvancedEntityModel<EntityCockroach> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox abdomen;
    public final AdvancedModelBox left_leg_front;
    public final AdvancedModelBox right_leg_front;
    public final AdvancedModelBox left_leg_back;
    public final AdvancedModelBox right_leg_back;
    public final AdvancedModelBox left_leg_mid;
    public final AdvancedModelBox right_leg_mid;
    public final AdvancedModelBox left_wing;
    public final AdvancedModelBox right_wing;
    public final AdvancedModelBox neck;
    public final AdvancedModelBox head;
    public final AdvancedModelBox left_antenna;
    public final AdvancedModelBox right_antenna;

    public ModelCockroach() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.abdomen = new AdvancedModelBox((AdvancedEntityModel)this, "abdomen");
        this.abdomen.setPos(0.0f, -1.6f, -1.0f);
        this.root.addChild((BasicModelPart)this.abdomen);
        this.abdomen.setTextureOffset(0, 12).addBox(-2.0f, -0.9f, -2.0f, 4.0f, 2.0f, 9.0f, 0.0f, false);
        this.left_leg_front = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg_front");
        this.left_leg_front.setPos(1.5f, 0.6f, -2.0f);
        this.abdomen.addChild((BasicModelPart)this.left_leg_front);
        this.setRotationAngle(this.left_leg_front, 0.0f, 0.0f, 0.1309f);
        this.left_leg_front.setTextureOffset(0, 24).addBox(0.0f, 0.0f, 0.0f, 7.0f, 0.0f, 3.0f, 0.0f, false);
        this.right_leg_front = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg_front");
        this.right_leg_front.setPos(-1.5f, 0.6f, -2.0f);
        this.abdomen.addChild((BasicModelPart)this.right_leg_front);
        this.setRotationAngle(this.right_leg_front, 0.0f, 0.0f, -0.1309f);
        this.right_leg_front.setTextureOffset(0, 24).addBox(-7.0f, 0.0f, 0.0f, 7.0f, 0.0f, 3.0f, 0.0f, true);
        this.left_leg_back = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg_back");
        this.left_leg_back.setPos(1.5f, 0.6f, 3.0f);
        this.abdomen.addChild((BasicModelPart)this.left_leg_back);
        this.setRotationAngle(this.left_leg_back, -0.0436f, -0.5236f, 0.1745f);
        this.left_leg_back.setTextureOffset(18, 12).addBox(0.0f, 0.0f, 0.0f, 7.0f, 0.0f, 5.0f, 0.0f, false);
        this.right_leg_back = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg_back");
        this.right_leg_back.setPos(-1.5f, 0.6f, 3.0f);
        this.abdomen.addChild((BasicModelPart)this.right_leg_back);
        this.setRotationAngle(this.right_leg_back, -0.0436f, 0.5236f, -0.1745f);
        this.right_leg_back.setTextureOffset(18, 12).addBox(-7.0f, 0.0f, 0.0f, 7.0f, 0.0f, 5.0f, 0.0f, true);
        this.left_leg_mid = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg_mid");
        this.left_leg_mid.setPos(1.5f, 0.6f, 0.0f);
        this.abdomen.addChild((BasicModelPart)this.left_leg_mid);
        this.setRotationAngle(this.left_leg_mid, -0.0436f, -0.2182f, 0.1309f);
        this.left_leg_mid.setTextureOffset(23, 20).addBox(0.0f, 0.0f, 0.0f, 7.0f, 0.0f, 4.0f, 0.0f, false);
        this.right_leg_mid = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg_mid");
        this.right_leg_mid.setPos(-1.5f, 0.6f, 0.0f);
        this.abdomen.addChild((BasicModelPart)this.right_leg_mid);
        this.setRotationAngle(this.right_leg_mid, -0.0436f, 0.2182f, -0.1309f);
        this.right_leg_mid.setTextureOffset(23, 20).addBox(-7.0f, 0.0f, 0.0f, 7.0f, 0.0f, 4.0f, 0.0f, true);
        this.left_wing = new AdvancedModelBox((AdvancedEntityModel)this, "left_wing");
        this.left_wing.setPos(0.0f, -1.4f, -2.0f);
        this.abdomen.addChild((BasicModelPart)this.left_wing);
        this.left_wing.setTextureOffset(0, 0).addBox(0.0f, 0.0f, 0.0f, 3.0f, 1.0f, 10.0f, 0.0f, false);
        this.right_wing = new AdvancedModelBox((AdvancedEntityModel)this, "right_wing");
        this.right_wing.setPos(0.0f, -1.4f, -2.0f);
        this.abdomen.addChild((BasicModelPart)this.right_wing);
        this.right_wing.setTextureOffset(0, 0).addBox(-3.0f, 0.0f, 0.0f, 3.0f, 1.0f, 10.0f, 0.0f, true);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setPos(0.0f, 0.0f, -2.0f);
        this.abdomen.addChild((BasicModelPart)this.neck);
        this.neck.setTextureOffset(21, 25).addBox(-2.5f, -1.6f, -2.0f, 5.0f, 3.0f, 2.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -0.1f, -2.0f);
        this.neck.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 28).addBox(-1.5f, -1.0f, -2.0f, 3.0f, 2.0f, 2.0f, 0.0f, false);
        this.left_antenna = new AdvancedModelBox((AdvancedEntityModel)this, "left_antenna");
        this.left_antenna.setPos(0.1f, -1.0f, -2.0f);
        this.head.addChild((BasicModelPart)this.left_antenna);
        this.setRotationAngle(this.left_antenna, -0.2182f, -0.2618f, 0.1309f);
        this.left_antenna.setTextureOffset(17, 0).addBox(0.0f, 0.0f, -8.0f, 5.0f, 0.0f, 8.0f, 0.0f, false);
        this.right_antenna = new AdvancedModelBox((AdvancedEntityModel)this, "right_antenna");
        this.right_antenna.setPos(-0.1f, -1.0f, -2.0f);
        this.head.addChild((BasicModelPart)this.right_antenna);
        this.setRotationAngle(this.right_antenna, -0.2182f, 0.2618f, -0.1309f);
        this.right_antenna.setTextureOffset(17, 0).addBox(-5.0f, 0.0f, -8.0f, 5.0f, 0.0f, 8.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.abdomen, (Object)this.neck, (Object)this.head, (Object)this.left_antenna, (Object)this.right_antenna, (Object)this.left_leg_front, (Object)this.right_leg_front, (Object)this.left_leg_mid, (Object)this.right_leg_mid, (Object)this.left_leg_back, (Object)this.right_leg_back, (Object[])new AdvancedModelBox[]{this.left_wing, this.right_wing});
    }

    public void setupAnim(EntityCockroach entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.25f;
        float idleDegree = 0.25f;
        float flySpeed = 0.5f;
        float flyDegree = 0.5f;
        float walkSpeed = 1.25f;
        float walkDegree = 0.5f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float danceProgress = entity.prevDanceProgress + (entity.danceProgress - entity.prevDanceProgress) * partialTick;
        this.progressRotationPrev(this.abdomen, danceProgress, Maths.rad(-70.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg_front, danceProgress, 0.0f, Maths.rad(-10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg_front, danceProgress, 0.0f, Maths.rad(10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg_mid, danceProgress, 0.0f, Maths.rad(-10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg_mid, danceProgress, 0.0f, Maths.rad(10.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.abdomen, danceProgress, 0.0f, -15.0f, 2.0f, 5.0f);
        if (danceProgress > 0.0f) {
            this.walk(this.left_antenna, 0.5f, 0.5f, false, -1.0f, -0.05f, ageInTicks, 1.0f);
            this.walk(this.right_antenna, 0.5f, 0.5f, false, -1.0f, -0.05f, ageInTicks, 1.0f);
            if (entity.hasMaracas()) {
                this.swing(this.abdomen, 0.5f, 0.15f, false, 0.0f, 0.0f, ageInTicks, 1.0f);
                this.flap(this.abdomen, 0.5f, 0.15f, false, 1.0f, 0.0f, ageInTicks, 1.0f);
                this.bob(this.abdomen, 0.25f, 10.0f, true, ageInTicks, 1.0f);
                this.swing(this.right_leg_front, 0.5f, 0.5f, false, 0.0f, -0.05f, ageInTicks, 1.0f);
                this.swing(this.left_leg_front, 0.5f, 0.5f, false, 0.0f, -0.05f, ageInTicks, 1.0f);
                this.swing(this.right_leg_mid, 0.5f, 0.5f, false, 2.0f, -0.05f, ageInTicks, 1.0f);
                this.swing(this.left_leg_mid, 0.5f, 0.5f, false, 2.0f, -0.05f, ageInTicks, 1.0f);
            } else {
                float spinDegree = Mth.m_14177_((float)(ageInTicks * 15.0f));
                this.abdomen.rotateAngleY = (float)(Math.toRadians(spinDegree) * (double)danceProgress * (double)0.2f);
                this.bob(this.abdomen, 0.25f, 10.0f, true, ageInTicks, 1.0f);
            }
        }
        this.swing(this.left_antenna, idleSpeed, idleDegree, true, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.swing(this.right_antenna, idleSpeed, idleDegree, false, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.left_antenna, idleSpeed, idleDegree * 0.25f, false, -1.0f, -0.05f, ageInTicks, 1.0f);
        this.walk(this.right_antenna, idleSpeed, idleDegree * 0.25f, false, -1.0f, -0.05f, ageInTicks, 1.0f);
        this.walk(this.head, idleSpeed * 0.5f, idleDegree * 0.5f, false, 0.0f, 0.1f, ageInTicks, 1.0f);
        if (entity.randomWingFlapTick > 0) {
            this.swing(this.left_wing, flySpeed * 3.3f, flyDegree * 0.6f, true, 0.0f, -0.2f, ageInTicks, 1.0f);
            this.swing(this.right_wing, flySpeed * 3.3f, flyDegree * 0.6f, false, 0.0f, -0.2f, ageInTicks, 1.0f);
        }
        this.swing(this.right_leg_front, walkSpeed, walkDegree, false, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.right_leg_back, walkSpeed, walkDegree, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.left_leg_mid, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.abdomen, walkSpeed, walkDegree * 2.5f, true, limbSwing, limbSwingAmount);
        this.swing(this.left_leg_front, walkSpeed, walkDegree, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.left_leg_back, walkSpeed, walkDegree, true, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.right_leg_mid, walkSpeed, walkDegree, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.head});
        if (entity.isHeadless()) {
            this.head.showModel = false;
            this.left_antenna.showModel = false;
            this.right_antenna.showModel = false;
        } else {
            this.head.showModel = true;
            this.left_antenna.showModel = true;
            this.right_antenna.showModel = true;
        }
        if (entity.m_6162_()) {
            this.left_wing.showModel = false;
            this.right_wing.showModel = false;
        } else {
            this.left_wing.showModel = true;
            this.right_wing.showModel = true;
        }
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            this.head.setScale(1.5f, 1.5f, 1.5f);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.65f, 0.65f, 0.65f);
            matrixStackIn.m_85837_(0.0, 0.815, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        } else {
            this.head.setScale(1.0f, 1.0f, 1.0f);
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z) {
        advancedModelBox.rotateAngleX = x;
        advancedModelBox.rotateAngleY = y;
        advancedModelBox.rotateAngleZ = z;
    }
}
