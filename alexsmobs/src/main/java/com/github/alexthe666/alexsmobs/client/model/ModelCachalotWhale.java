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
 *  net.minecraft.util.Mth
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCachalotWhale;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class ModelCachalotWhale
extends AdvancedEntityModel<EntityCachalotWhale> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox top_fin;
    public final AdvancedModelBox arm_left;
    public final AdvancedModelBox arm_right;
    public final AdvancedModelBox tail1;
    public final AdvancedModelBox tail2;
    public final AdvancedModelBox tail3;
    public final AdvancedModelBox head;
    public final AdvancedModelBox jaw;
    public final AdvancedModelBox teeth;

    public ModelCachalotWhale() {
        this.texWidth = 512;
        this.texHeight = 512;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -30.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-21.0f, -30.0f, -60.0f, 42.0f, 60.0f, 112.0f, 0.0f, false);
        this.top_fin = new AdvancedModelBox((AdvancedEntityModel)this, "top_fin");
        this.top_fin.setPos(0.0f, -34.0f, 42.0f);
        this.body.addChild((BasicModelPart)this.top_fin);
        this.top_fin.setTextureOffset(0, 0).addBox(-3.0f, -4.0f, -10.0f, 6.0f, 8.0f, 20.0f, 0.0f, false);
        this.arm_left = new AdvancedModelBox((AdvancedEntityModel)this, "arm_left");
        this.arm_left.setPos(21.0f, 26.0f, -38.0f);
        this.body.addChild((BasicModelPart)this.arm_left);
        this.arm_left.setTextureOffset(304, 220).addBox(0.0f, -2.0f, -3.0f, 36.0f, 4.0f, 21.0f, 0.0f, false);
        this.arm_right = new AdvancedModelBox((AdvancedEntityModel)this, "arm_right");
        this.arm_right.setPos(-21.0f, 26.0f, -38.0f);
        this.body.addChild((BasicModelPart)this.arm_right);
        this.arm_right.setTextureOffset(304, 220).addBox(-36.0f, -2.0f, -3.0f, 36.0f, 4.0f, 21.0f, 0.0f, true);
        this.tail1 = new AdvancedModelBox((AdvancedEntityModel)this, "tail1");
        this.tail1.setPos(0.0f, -1.0f, 52.0f);
        this.body.addChild((BasicModelPart)this.tail1);
        this.tail1.setTextureOffset(163, 227).addBox(-15.0f, -22.0f, 0.0f, 30.0f, 45.0f, 80.0f, 0.0f, false);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this, "tail2");
        this.tail2.setPos(0.0f, -1.0f, 80.0f);
        this.tail1.addChild((BasicModelPart)this.tail2);
        this.tail2.setTextureOffset(197, 0).addBox(-9.0f, -14.0f, 0.0f, 18.0f, 28.0f, 65.0f, 0.0f, false);
        this.tail3 = new AdvancedModelBox((AdvancedEntityModel)this, "tail3");
        this.tail3.setPos(0.0f, 2.0f, 56.0f);
        this.tail2.addChild((BasicModelPart)this.tail3);
        this.tail3.setTextureOffset(158, 173).addBox(-33.0f, -5.0f, -5.0f, 66.0f, 9.0f, 37.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -2.0f, -60.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 173).addBox(-18.0f, -28.0f, -85.0f, 36.0f, 48.0f, 85.0f, 0.0f, false);
        this.jaw = new AdvancedModelBox((AdvancedEntityModel)this, "jaw");
        this.jaw.setPos(0.0f, 20.0f, 0.0f);
        this.head.addChild((BasicModelPart)this.jaw);
        this.jaw.setTextureOffset(293, 23).addBox(-7.0f, 0.0f, -71.0f, 14.0f, 9.0f, 71.0f, 0.0f, false);
        this.teeth = new AdvancedModelBox((AdvancedEntityModel)this, "teeth");
        this.teeth.setPos(0.0f, 0.0f, -7.0f);
        this.jaw.addChild((BasicModelPart)this.teeth);
        this.teeth.setTextureOffset(32, 370).addBox(-4.0f, -4.0f, -59.0f, 8.0f, 4.0f, 60.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.head, (Object)this.body, (Object)this.tail1, (Object)this.tail2, (Object)this.tail3, (Object)this.top_fin, (Object)this.jaw, (Object)this.teeth, (Object)this.arm_left, (Object)this.arm_right);
    }

    public void setupAnim(EntityCachalotWhale entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float partialTicks = ageInTicks - (float)entity.f_19797_;
        float renderYaw = (float)entity.getMovementOffsets(0, partialTicks)[0];
        float properPitch = entity.f_19860_ + (entity.m_146909_() - entity.f_19860_) * partialTicks;
        float chargeProgress = entity.prevChargingProgress + (entity.chargeProgress - entity.prevChargingProgress) * partialTicks;
        float sleepProgress = entity.prevSleepProgress + (entity.sleepProgress - entity.prevSleepProgress) * partialTicks;
        float beachedProgress = entity.prevBeachedProgress + (entity.beachedProgress - entity.prevBeachedProgress) * partialTicks;
        float grabProgress = entity.prevGrabProgress + (entity.grabProgress - entity.prevGrabProgress) * partialTicks;
        float f = Mth.m_14036_((float)((float)entity.getMovementOffsets(7, partialTicks)[0] - renderYaw), (float)-50.0f, (float)50.0f);
        this.tail1.rotateAngleY += Mth.m_14036_((float)((float)entity.getMovementOffsets(15, partialTicks)[0] - renderYaw), (float)-50.0f, (float)50.0f) * ((float)Math.PI / 180);
        this.tail2.rotateAngleY += Mth.m_14036_((float)((float)entity.getMovementOffsets(17, partialTicks)[0] - renderYaw), (float)-50.0f, (float)50.0f) * ((float)Math.PI / 180);
        this.body.rotateAngleX += Math.min(properPitch, sleepProgress * -9.0f) * ((float)Math.PI / 180);
        this.body.rotateAngleZ += f * ((float)Math.PI / 180);
        this.head.rotateAngleY = (float)((double)this.head.rotateAngleY + Math.sin(((float)entity.grabTime + partialTicks) * 0.3f) * (double)0.1f * (double)grabProgress);
        AdvancedModelBox[] tailBoxes = new AdvancedModelBox[]{this.tail1, this.tail2, this.tail3};
        float swimSpeed = 0.2f;
        float swimDegree = 0.4f;
        float beachedSpeed = 0.05f;
        float beachedIdle = 0.4f;
        this.progressRotationPrev(this.jaw, Math.max(chargeProgress, grabProgress * 0.8f), Maths.rad(30.0), 0.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.jaw, beachedProgress, Maths.rad(20.0), Maths.rad(5.0), 0.0f, 10.0f);
        this.progressRotationPrev(this.body, beachedProgress, 0.0f, 0.0f, Maths.rad(80.0), 10.0f);
        this.progressRotationPrev(this.tail1, beachedProgress, Maths.rad(-30.0), Maths.rad(10.0), 0.0f, 10.0f);
        this.progressRotationPrev(this.tail2, beachedProgress, Maths.rad(-30.0), Maths.rad(-30.0), Maths.rad(-30.0), 10.0f);
        this.progressRotationPrev(this.tail3, beachedProgress, 0.0f, Maths.rad(-10.0), Maths.rad(-60.0), 10.0f);
        this.progressRotationPrev(this.head, beachedProgress, 0.0f, Maths.rad(-10.0), 0.0f, 10.0f);
        this.progressRotationPrev(this.arm_right, beachedProgress, 0.0f, 0.0f, Maths.rad(-110.0), 10.0f);
        this.progressRotationPrev(this.arm_left, beachedProgress, 0.0f, 0.0f, Maths.rad(110.0), 10.0f);
        this.progressPositionPrev(this.tail1, beachedProgress, -2.0f, -1.0f, -10.0f, 10.0f);
        this.progressPositionPrev(this.tail2, beachedProgress, 0.0f, -1.0f, -4.0f, 10.0f);
        this.progressPositionPrev(this.tail3, beachedProgress, 0.0f, 2.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.body, beachedProgress, 0.0f, 5.0f, 0.0f, 10.0f);
        this.progressPositionPrev(this.head, beachedProgress, 0.0f, 0.0f, 3.0f, 10.0f);
        if (beachedProgress > 0.0f) {
            this.swing(this.arm_left, beachedSpeed, beachedIdle * 0.2f, true, 1.0f, 0.0f, ageInTicks, 1.0f);
            this.flap(this.arm_right, beachedSpeed, beachedIdle * 0.2f, true, 3.0f, 0.06f, ageInTicks, 1.0f);
            this.walk(this.jaw, beachedSpeed, beachedIdle * 0.2f, true, 2.0f, 0.06f, ageInTicks, 1.0f);
            this.walk(this.tail1, beachedSpeed, beachedIdle * 0.2f, false, 4.0f, 0.06f, ageInTicks, 1.0f);
            this.walk(this.tail2, beachedSpeed, beachedIdle * 0.2f, false, 4.0f, 0.06f, ageInTicks, 1.0f);
        } else {
            this.walk(this.jaw, swimSpeed * 0.4f, swimDegree * 0.15f, true, 1.0f, -0.01f, ageInTicks, 1.0f);
            this.flap(this.arm_left, swimSpeed * 0.4f, swimDegree * 0.5f, true, 2.5f, -0.4f, ageInTicks, 1.0f);
            this.flap(this.arm_right, swimSpeed * 0.4f, swimDegree * 0.5f, false, 2.5f, -0.4f, ageInTicks, 1.0f);
            this.swing(this.arm_left, swimSpeed, swimDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.arm_right, swimSpeed, swimDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.arm_left, swimSpeed, swimDegree * 1.4f, true, 2.5f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.arm_right, swimSpeed, swimDegree * 1.4f, false, 2.5f, 0.0f, limbSwing, limbSwingAmount);
            this.bob(this.body, swimSpeed, swimDegree * 20.0f, false, limbSwing, limbSwingAmount);
            this.chainWave(tailBoxes, swimSpeed, swimDegree * 0.8f, -2.0, limbSwing, limbSwingAmount);
            this.walk(this.head, swimSpeed, swimDegree * 0.1f, false, 2.0f, 0.0f, limbSwing, limbSwingAmount);
            this.tail1.rotationPointZ -= 4.0f * limbSwingAmount;
            this.tail2.rotationPointZ -= 2.0f * limbSwingAmount;
        }
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.25f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
