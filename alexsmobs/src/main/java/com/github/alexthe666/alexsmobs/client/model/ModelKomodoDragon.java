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

import com.github.alexthe666.alexsmobs.entity.EntityKomodoDragon;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;

public class ModelKomodoDragon
extends AdvancedEntityModel<EntityKomodoDragon> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox neck;
    private final AdvancedModelBox head;
    private final AdvancedModelBox tongue;
    private final AdvancedModelBox tail1;
    private final AdvancedModelBox tail2;
    private final AdvancedModelBox left_shoulder;
    private final AdvancedModelBox left_arm;
    private final AdvancedModelBox right_shoulder;
    private final AdvancedModelBox right_arm;
    private final AdvancedModelBox left_hip;
    private final AdvancedModelBox left_leg;
    private final AdvancedModelBox right_hip;
    private final AdvancedModelBox right_leg;

    public ModelKomodoDragon(float scale) {
        this.texHeight = 128;
        this.texWidth = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -11.0f, -1.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-5.0f, -4.0f, -11.0f, 10.0f, 9.0f, 23.0f, scale, false);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setRotationPoint(0.0f, -1.0f, -10.0f);
        this.body.addChild((BasicModelPart)this.neck);
        this.neck.setTextureOffset(0, 60).addBox(-3.5f, -3.0f, -7.0f, 7.0f, 7.0f, 6.0f, scale, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -1.0f, -7.0f);
        this.neck.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(44, 0).addBox(-3.5f, -2.0f, -10.0f, 7.0f, 5.0f, 10.0f, scale, false);
        this.tongue = new AdvancedModelBox((AdvancedEntityModel)this, "tongue");
        this.tongue.setRotationPoint(0.0f, 1.0f, -10.0f);
        this.head.addChild((BasicModelPart)this.tongue);
        this.tongue.setTextureOffset(60, 26).addBox(-1.5f, 0.0f, -7.0f, 3.0f, 0.0f, 7.0f, scale, false);
        this.tail1 = new AdvancedModelBox((AdvancedEntityModel)this, "tail1");
        this.tail1.setRotationPoint(0.0f, -1.0f, 11.0f);
        this.body.addChild((BasicModelPart)this.tail1);
        this.tail1.setTextureOffset(0, 33).addBox(-3.0f, -2.0f, 1.0f, 6.0f, 6.0f, 20.0f, scale, false);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this, "tail2");
        this.tail2.setRotationPoint(0.0f, 1.0f, 20.0f);
        this.tail1.addChild((BasicModelPart)this.tail2);
        this.tail2.setTextureOffset(35, 42).addBox(-1.5f, -2.0f, 1.0f, 3.0f, 4.0f, 18.0f, scale, false);
        this.left_shoulder = new AdvancedModelBox((AdvancedEntityModel)this, "left_shoulder");
        this.left_shoulder.setRotationPoint(1.5f, 2.0f, -5.5f);
        this.body.addChild((BasicModelPart)this.left_shoulder);
        this.left_arm = new AdvancedModelBox((AdvancedEntityModel)this, "left_arm");
        this.left_arm.setRotationPoint(4.5f, 0.0f, -0.5f);
        this.left_shoulder.addChild((BasicModelPart)this.left_arm);
        this.left_arm.setTextureOffset(0, 33).addBox(-2.0f, -3.0f, -2.0f, 4.0f, 12.0f, 4.0f, scale, false);
        this.left_arm.setTextureOffset(57, 34).addBox(-4.0f, 8.99f, -5.0f, 8.0f, 0.0f, 7.0f, scale, false);
        this.right_shoulder = new AdvancedModelBox((AdvancedEntityModel)this, "right_shoulder");
        this.right_shoulder.setRotationPoint(-1.5f, 2.0f, -5.5f);
        this.body.addChild((BasicModelPart)this.right_shoulder);
        this.right_arm = new AdvancedModelBox((AdvancedEntityModel)this, "right_arm");
        this.right_arm.setRotationPoint(-4.5f, 0.0f, -0.5f);
        this.right_shoulder.addChild((BasicModelPart)this.right_arm);
        this.right_arm.setTextureOffset(0, 33).addBox(-2.0f, -3.0f, -2.0f, 4.0f, 12.0f, 4.0f, scale, true);
        this.right_arm.setTextureOffset(57, 34).addBox(-4.0f, 8.99f, -5.0f, 8.0f, 0.0f, 7.0f, scale, true);
        this.left_hip = new AdvancedModelBox((AdvancedEntityModel)this, "left_hip");
        this.left_hip.setRotationPoint(1.5f, 2.0f, 7.5f);
        this.body.addChild((BasicModelPart)this.left_hip);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setRotationPoint(2.5f, 0.0f, 0.5f);
        this.left_hip.addChild((BasicModelPart)this.left_leg);
        this.left_leg.setTextureOffset(0, 0).addBox(-1.0f, -3.0f, -2.0f, 4.0f, 12.0f, 5.0f, scale, false);
        this.left_leg.setTextureOffset(33, 33).addBox(-3.0f, 8.99f, -5.0f, 8.0f, 0.0f, 7.0f, scale, false);
        this.right_hip = new AdvancedModelBox((AdvancedEntityModel)this, "right_hip");
        this.right_hip.setRotationPoint(-1.5f, 2.0f, 7.5f);
        this.body.addChild((BasicModelPart)this.right_hip);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setRotationPoint(-2.5f, 0.0f, 0.5f);
        this.right_hip.addChild((BasicModelPart)this.right_leg);
        this.right_leg.setTextureOffset(0, 0).addBox(-3.0f, -3.0f, -2.0f, 4.0f, 12.0f, 5.0f, scale, true);
        this.right_leg.setTextureOffset(33, 33).addBox(-5.0f, 8.99f, -5.0f, 8.0f, 0.0f, 7.0f, scale, true);
        this.setRotationAngle(this.tail1, Maths.rad(-23.0), 0.0f, 0.0f);
        this.setRotationAngle(this.tail2, Maths.rad(23.0), 0.0f, 0.0f);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityKomodoDragon entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.7f;
        float idleDegree = 0.7f;
        float walkSpeed = 0.5f;
        float walkDegree = 0.7f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float sitProgress = entityIn.prevSitProgress + (entityIn.sitProgress - entityIn.prevSitProgress) * partialTick;
        float jostleProgress = entityIn.prevJostleProgress + (entityIn.jostleProgress - entityIn.prevJostleProgress) * partialTick;
        float jostleAngle = entityIn.prevJostleAngle + (entityIn.getJostleAngle() - entityIn.prevJostleAngle) * partialTick;
        float toungeF = (float)Math.min(Math.sin(ageInTicks * 0.3f), 0.0) * 9.0f;
        float toungeMinus = (float)Math.max(Math.sin(ageInTicks * 0.3f), 0.0);
        this.progressRotationPrev(this.tail1, limbSwingAmount, Maths.rad(13.0), 0.0f, 0.0f, 1.0f);
        this.progressRotationPrev(this.tail2, limbSwingAmount, Maths.rad(-13.0), 0.0f, 0.0f, 1.0f);
        this.progressPositionPrev(this.body, sitProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_leg, sitProgress, 0.0f, 3.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_leg, sitProgress, 0.0f, 3.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.left_arm, sitProgress, 0.0f, 1.5f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_arm, sitProgress, 0.0f, 1.5f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, sitProgress, Maths.rad(-25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, sitProgress, Maths.rad(8.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail1, sitProgress, Maths.rad(35.0), Maths.rad(15.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg, sitProgress, Maths.rad(25.0), Maths.rad(-25.0), Maths.rad(70.0), 5.0f);
        this.progressRotationPrev(this.left_leg, sitProgress, Maths.rad(25.0), Maths.rad(25.0), Maths.rad(-70.0), 5.0f);
        this.progressRotationPrev(this.right_arm, sitProgress, Maths.rad(25.0), Maths.rad(-15.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, sitProgress, Maths.rad(25.0), Maths.rad(15.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.body, jostleProgress, Maths.rad(-55.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, jostleProgress, 0.0f, -6.0f, -3.0f, 5.0f);
        this.progressRotationPrev(this.right_leg, jostleProgress, Maths.rad(55.0), -Maths.rad(15.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg, jostleProgress, Maths.rad(55.0), Maths.rad(15.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.tail1, jostleProgress, Maths.rad(60.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.neck, jostleProgress, Maths.rad(2.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, jostleProgress, Maths.rad(14.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_arm, jostleProgress, 0.0f, 0.0f, -2.0f, 5.0f);
        this.progressPositionPrev(this.left_arm, jostleProgress, 0.0f, 0.0f, -2.0f, 5.0f);
        this.progressRotationPrev(this.right_arm, jostleProgress, Maths.rad(-10.0), Maths.rad(90.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.left_arm, jostleProgress, Maths.rad(-10.0), Maths.rad(-90.0), 0.0f, 5.0f);
        this.flap(this.body, walkSpeed, walkDegree * 0.5f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.body, walkSpeed, walkDegree * 0.5f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.neck, walkSpeed, walkDegree * -0.25f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.neck, walkSpeed, walkDegree * -0.25f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.head, walkSpeed, walkDegree * -0.25f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.head, walkSpeed, walkDegree * -0.25f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.tail1, walkSpeed, walkDegree * -0.5f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.tail1, walkSpeed, walkDegree * 0.5f, false, 2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.tail2, walkSpeed, walkDegree * 0.5f, false, 2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.left_arm, walkSpeed, walkDegree * 1.2f, false, -2.5f, -0.25f, limbSwing, limbSwingAmount);
        this.walk(this.right_arm, walkSpeed, walkDegree * 1.2f, true, -2.5f, 0.25f, limbSwing, limbSwingAmount);
        this.walk(this.right_leg, walkSpeed, walkDegree * 1.2f, false, -2.5f, 0.25f, limbSwing, limbSwingAmount);
        this.walk(this.left_leg, walkSpeed, walkDegree * 1.2f, true, -2.5f, -0.25f, limbSwing, limbSwingAmount);
        this.flap(this.left_arm, walkSpeed, walkDegree, false, -2.5f, -0.25f, limbSwing, limbSwingAmount);
        this.flap(this.right_arm, walkSpeed, walkDegree, false, -2.5f, 0.25f, limbSwing, limbSwingAmount);
        this.flap(this.right_leg, walkSpeed, walkDegree, false, -2.5f, 0.25f, limbSwing, limbSwingAmount);
        this.flap(this.left_leg, walkSpeed, walkDegree, false, -2.5f, -0.25f, limbSwing, limbSwingAmount);
        this.left_arm.rotationPointY += 1.5f * (float)(Math.sin((double)(limbSwing * walkSpeed) - 2.5) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
        this.right_arm.rotationPointY += 1.5f * (float)(Math.sin(-((double)(limbSwing * walkSpeed)) + 2.5) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
        this.left_leg.rotationPointY += 1.5f * (float)(Math.sin((double)(limbSwing * walkSpeed) - 2.5) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
        this.right_leg.rotationPointY += 1.5f * (float)(Math.sin(-((double)(limbSwing * walkSpeed)) + 2.5) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
        this.walk(this.tongue, idleSpeed * 2.0f, idleDegree, false, 0.0f, 0.0f, ageInTicks, toungeMinus);
        this.walk(this.neck, idleSpeed * 0.1f, idleDegree * 0.1f, false, 2.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.head, idleSpeed * 0.1f, idleDegree * 0.1f, false, 4.0f, 0.0f, ageInTicks, 1.0f);
        this.tongue.rotationPointZ -= toungeF;
        this.progressPositionPrev(this.neck, jostleProgress, 0.0f, 0.0f, 1.0f, 5.0f);
        this.progressPositionPrev(this.head, jostleProgress, 0.0f, 0.0f, 1.0f, 5.0f);
        if (jostleProgress > 0.0f) {
            float jostleScale = 2.5f;
            float yawAmount = jostleAngle / 57.295776f * jostleProgress * 0.2f * jostleScale;
            float inv = (10.0f - Math.abs(jostleAngle)) / 57.295776f * jostleProgress * 0.2f * jostleScale * 0.5f;
            this.right_shoulder.rotateAngleX += yawAmount;
            this.left_shoulder.rotateAngleX += yawAmount;
            this.neck.rotateAngleY += yawAmount;
            this.head.rotateAngleY += yawAmount;
            this.neck.rotateAngleX -= inv;
            this.head.rotateAngleX -= inv;
        } else {
            this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.neck, this.head});
        }
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.75f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.35f, 0.35f, 0.35f);
            matrixStackIn.m_85837_(0.0, 2.75, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
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
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.tail2, (Object)this.tail1, (Object)this.left_leg, (Object)this.right_leg, (Object)this.left_shoulder, (Object)this.right_shoulder, (Object)this.left_arm, (Object)this.right_arm, (Object)this.left_hip, (Object)this.right_hip, (Object[])new AdvancedModelBox[]{this.neck, this.head, this.tongue});
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
