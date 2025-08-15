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

import com.github.alexthe666.alexsmobs.entity.EntityRoadrunner;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelRoadrunner
extends AdvancedEntityModel<EntityRoadrunner> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox left_wing;
    private final AdvancedModelBox right_wing;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox neck;
    private final AdvancedModelBox beak;
    private final AdvancedModelBox right_spin;
    private final AdvancedModelBox left_spin;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox left_knee;
    private final AdvancedModelBox left_foot;
    private final AdvancedModelBox right_leg;
    private final AdvancedModelBox right_knee;
    private final AdvancedModelBox right_foot;

    public ModelRoadrunner() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -7.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(23, 14).addBox(-2.0f, -2.0f, -3.0f, 4.0f, 4.0f, 7.0f, 0.0f, false);
        this.left_wing = new AdvancedModelBox((AdvancedEntityModel)this, "left_wing");
        this.left_wing.setRotationPoint(2.0f, -1.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.left_wing);
        this.setRotationAngle(this.left_wing, -0.0873f, 0.1309f, -0.1745f);
        this.left_wing.setTextureOffset(0, 14).addBox(0.0f, 0.0f, 0.0f, 0.0f, 4.0f, 11.0f, 0.0f, false);
        this.right_wing = new AdvancedModelBox((AdvancedEntityModel)this, "right_wing");
        this.right_wing.setRotationPoint(-2.0f, -1.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.right_wing);
        this.setRotationAngle(this.right_wing, -0.0873f, -0.1309f, 0.1745f);
        this.right_wing.setTextureOffset(0, 14).addBox(0.0f, 0.0f, 0.0f, 0.0f, 4.0f, 11.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, -1.6f, 4.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.setRotationAngle(this.tail, 0.6545f, 0.0f, 0.0f);
        this.tail.setTextureOffset(0, 0).addBox(-4.0f, 0.0f, 0.0f, 8.0f, 0.0f, 13.0f, 0.0f, false);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setRotationPoint(0.0f, -0.7f, -2.9f);
        this.body.addChild((BasicModelPart)this.neck);
        this.setRotationAngle(this.neck, 0.6545f, 0.0f, 0.0f);
        this.neck.setTextureOffset(0, 0).addBox(-1.5f, -6.0f, -1.3f, 3.0f, 8.0f, 3.0f, 0.0f, false);
        this.neck.setTextureOffset(0, 14).addBox(0.0f, -8.0f, -1.3f, 0.0f, 4.0f, 5.0f, 0.0f, false);
        this.beak = new AdvancedModelBox((AdvancedEntityModel)this, "beak");
        this.beak.setRotationPoint(0.0f, -4.5f, -0.8f);
        this.neck.addChild((BasicModelPart)this.beak);
        this.setRotationAngle(this.beak, -0.3491f, 0.0f, 0.0f);
        this.beak.setTextureOffset(12, 14).addBox(-0.5f, -0.5f, -4.2f, 1.0f, 1.0f, 4.0f, 0.0f, false);
        this.beak.setTextureOffset(47, 22).addBox(-1.0f, -0.1f, -2.1f, 2.0f, 1.0f, 2.0f, 0.0f, false);
        this.right_spin = new AdvancedModelBox((AdvancedEntityModel)this, "right_spin");
        this.right_spin.setRotationPoint(-1.5f, 4.5f, 1.5f);
        this.body.addChild((BasicModelPart)this.right_spin);
        this.setRotationAngle(this.right_spin, 0.5236f, 0.0f, 0.0f);
        this.right_spin.setTextureOffset(42, 9).addBox(-1.0f, -2.5f, -2.5f, 2.0f, 5.0f, 5.0f, 0.0f, true);
        this.left_spin = new AdvancedModelBox((AdvancedEntityModel)this, "left_spin");
        this.left_spin.setRotationPoint(1.5f, 4.5f, 1.5f);
        this.body.addChild((BasicModelPart)this.left_spin);
        this.setRotationAngle(this.left_spin, 0.5236f, 0.0f, 0.0f);
        this.left_spin.setTextureOffset(42, 9).addBox(-1.0f, -2.5f, -2.5f, 2.0f, 5.0f, 5.0f, 0.0f, false);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setRotationPoint(1.5f, 2.0f, 3.0f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.setRotationAngle(this.left_leg, 0.5672f, 0.0f, 0.0f);
        this.left_leg.setTextureOffset(0, 0).addBox(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, 0.0f, false);
        this.left_knee = new AdvancedModelBox((AdvancedEntityModel)this, "left_knee");
        this.left_knee.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.left_leg.addChild((BasicModelPart)this.left_knee);
        this.setRotationAngle(this.left_knee, -1.1781f, 0.0f, 0.0f);
        this.left_knee.setTextureOffset(0, 14).addBox(-0.5f, 0.0f, 0.0f, 1.0f, 4.0f, 0.0f, 0.0f, false);
        this.left_foot = new AdvancedModelBox((AdvancedEntityModel)this, "left_foot");
        this.left_foot.setRotationPoint(0.0f, 4.0f, 0.0f);
        this.left_knee.addChild((BasicModelPart)this.left_foot);
        this.setRotationAngle(this.left_foot, -0.9599f, 0.0f, 0.0f);
        this.left_foot.setTextureOffset(23, 14).addBox(-1.5f, -2.5f, 0.0f, 3.0f, 5.0f, 0.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setRotationPoint(-1.5f, 2.0f, 3.0f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.setRotationAngle(this.right_leg, 0.5672f, 0.0f, 0.0f);
        this.right_leg.setTextureOffset(0, 0).addBox(-0.5f, 0.0f, 0.0f, 1.0f, 2.0f, 0.0f, 0.0f, true);
        this.right_knee = new AdvancedModelBox((AdvancedEntityModel)this, "right_knee");
        this.right_knee.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.right_leg.addChild((BasicModelPart)this.right_knee);
        this.setRotationAngle(this.right_knee, -1.1781f, 0.0f, 0.0f);
        this.right_knee.setTextureOffset(0, 14).addBox(-0.5f, 0.0f, 0.0f, 1.0f, 4.0f, 0.0f, 0.0f, true);
        this.right_foot = new AdvancedModelBox((AdvancedEntityModel)this, "right_foot");
        this.right_foot.setRotationPoint(0.0f, 4.0f, 0.0f);
        this.right_knee.addChild((BasicModelPart)this.right_foot);
        this.setRotationAngle(this.right_foot, -0.9599f, 0.0f, 0.0f);
        this.right_foot.setTextureOffset(23, 14).addBox(-1.5f, -2.5f, 0.0f, 3.0f, 5.0f, 0.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityRoadrunner entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netneckYaw, float neckPitch) {
        this.resetToDefaultPose();
        float walkSpeed = 0.9f;
        float walkDegree = 0.4f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.4f;
        float runProgress = 5.0f * limbSwingAmount;
        float partialTick = Minecraft.m_91087_().m_91296_();
        boolean spinnyLegs = limbSwingAmount > 0.5f && entityIn.isMeep();
        float biteProgress = entityIn.prevAttackProgress + (entityIn.attackProgress - entityIn.prevAttackProgress) * partialTick;
        this.progressRotationPrev(this.neck, biteProgress, Maths.rad(55.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, runProgress, Maths.rad(-5.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.neck, runProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg, runProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg, runProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, runProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_wing, runProgress, Maths.rad(-10.0), Maths.rad(-30.0), Maths.rad(40.0), 5.0f);
        this.progressRotationPrev(this.left_wing, runProgress, Maths.rad(-10.0), Maths.rad(30.0), Maths.rad(-40.0), 5.0f);
        this.swing(this.tail, idleSpeed, idleDegree, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.neck, idleSpeed, idleDegree * 0.2f, false, 0.0f, -0.1f, ageInTicks, 1.0f);
        this.walk(this.right_leg, walkSpeed, walkDegree * 1.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.left_leg, walkSpeed, walkDegree * 1.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.right_knee, walkSpeed, walkDegree * 0.5f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.left_knee, walkSpeed, walkDegree * 0.5f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.left_wing, walkSpeed, walkDegree, true, 2.0f, 0.1f, limbSwing, limbSwingAmount);
        this.flap(this.right_wing, walkSpeed, walkDegree, false, 2.0f, 0.1f, limbSwing, limbSwingAmount);
        this.left_foot.rotateAngleX = -(this.left_leg.rotateAngleX + this.left_knee.rotateAngleX + this.body.rotateAngleX) - 1.5707964f;
        this.right_foot.rotateAngleX = -(this.right_leg.rotateAngleX + this.right_knee.rotateAngleX + this.body.rotateAngleX) - 1.5707964f;
        this.left_leg.rotationPointY += 1.5f * (float)(Math.sin((double)(limbSwing * walkSpeed) + 2.0) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
        this.right_leg.rotationPointY += 1.5f * (float)(Math.sin(-((double)(limbSwing * walkSpeed)) - 2.0) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
        float partialTicks = Minecraft.m_91087_().m_91296_();
        float f = Mth.m_14179_((float)partialTicks, (float)entityIn.oFlap, (float)entityIn.wingRotation);
        float f1 = Mth.m_14179_((float)partialTicks, (float)entityIn.oFlapSpeed, (float)entityIn.destPos);
        float wingSwing = (Mth.m_14031_((float)f) + 1.0f) * f1;
        this.flap(this.left_wing, 0.95f, 0.9f, true, 0.0f, 0.2f, wingSwing, wingSwing > 0.0f ? 1.0f : 0.0f);
        this.flap(this.right_wing, 0.95f, 0.9f, false, 0.0f, 0.2f, wingSwing, wingSwing > 0.0f ? 1.0f : 0.0f);
        this.faceTarget(netneckYaw, neckPitch, 1.0f, new AdvancedModelBox[]{this.neck});
        if (spinnyLegs) {
            this.right_spin.showModel = true;
            this.left_spin.showModel = true;
            this.right_leg.showModel = false;
            this.left_leg.showModel = false;
            float wobbleXZ = 1.0f + (1.0f + (float)Math.sin(ageInTicks * 0.6f - 3.0f)) * 0.6f;
            float wobbleY = 1.0f + (1.0f + (float)Math.sin(ageInTicks * 0.6f - 2.0f)) * 0.6f;
            this.right_spin.setScale(1.0f, wobbleY, wobbleXZ);
            this.left_spin.setScale(1.0f, wobbleY, wobbleXZ);
            this.right_spin.rotateAngleX += limbSwingAmount * ageInTicks * 2.0f;
            this.left_spin.rotateAngleX += limbSwingAmount * ageInTicks * 2.0f;
            this.bob(this.body, walkSpeed, walkDegree * 5.0f, true, limbSwing, limbSwingAmount);
        } else {
            this.right_spin.showModel = false;
            this.left_spin.showModel = false;
            this.right_leg.showModel = true;
            this.left_leg.showModel = true;
            this.walk(this.tail, walkSpeed, walkDegree, false, 2.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.neck, walkSpeed, walkDegree * 0.7f, false, 1.0f, -0.2f, limbSwing, limbSwingAmount);
            this.bob(this.body, walkSpeed * 2.0f, walkDegree * 2.0f, false, limbSwing, limbSwingAmount);
        }
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.75f;
            this.neck.setScale(f, f, f);
            this.neck.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.neck.setScale(1.0f, 1.0f, 1.0f);
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.neck, (Object)this.beak, (Object)this.left_leg, (Object)this.right_leg, (Object)this.left_wing, (Object)this.right_wing, (Object)this.left_leg, (Object)this.tail, (Object)this.right_spin, (Object)this.left_spin, (Object[])new AdvancedModelBox[]{this.beak, this.left_knee, this.right_knee, this.left_foot, this.right_foot});
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
