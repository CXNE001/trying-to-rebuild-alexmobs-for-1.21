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

import com.github.alexthe666.alexsmobs.entity.EntityLaviathan;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelLaviathan
extends AdvancedEntityModel<EntityLaviathan> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox leftArm;
    private final AdvancedModelBox rightArm;
    private final AdvancedModelBox leftLeg;
    private final AdvancedModelBox rightLeg;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox neck;
    private final AdvancedModelBox neck2;
    private final AdvancedModelBox head;
    private final AdvancedModelBox bottom_jaw;
    private final AdvancedModelBox top_jaw;
    private final AdvancedModelBox shell;
    private final AdvancedModelBox vent1;
    private final AdvancedModelBox vent2;
    private final AdvancedModelBox vent3;
    private final AdvancedModelBox vent4;

    public ModelLaviathan() {
        this.texHeight = 256;
        this.texWidth = 256;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-20.0f, -27.0f, -31.0f, 40.0f, 27.0f, 64.0f, 0.0f, false);
        this.leftArm = new AdvancedModelBox((AdvancedEntityModel)this, "leftArm");
        this.leftArm.setRotationPoint(20.0f, -9.0f, -28.0f);
        this.body.addChild((BasicModelPart)this.leftArm);
        this.leftArm.setTextureOffset(150, 151).addBox(0.0f, -2.0f, -3.0f, 21.0f, 4.0f, 13.0f, 0.0f, false);
        this.leftArm.setTextureOffset(0, 49).addBox(13.0f, -1.9f, 1.0f, 10.0f, 0.0f, 14.0f, 0.0f, false);
        this.rightArm = new AdvancedModelBox((AdvancedEntityModel)this, "rightArm");
        this.rightArm.setRotationPoint(-20.0f, -9.0f, -28.0f);
        this.body.addChild((BasicModelPart)this.rightArm);
        this.rightArm.setTextureOffset(150, 151).addBox(-21.0f, -2.0f, -3.0f, 21.0f, 4.0f, 13.0f, 0.0f, true);
        this.rightArm.setTextureOffset(0, 49).addBox(-23.0f, -1.9f, 1.0f, 10.0f, 0.0f, 14.0f, 0.0f, true);
        this.leftLeg = new AdvancedModelBox((AdvancedEntityModel)this, "leftLeg");
        this.leftLeg.setRotationPoint(20.0f, -8.0f, 21.0f);
        this.body.addChild((BasicModelPart)this.leftLeg);
        this.leftLeg.setTextureOffset(65, 151).addBox(0.0f, -2.0f, -5.0f, 25.0f, 4.0f, 17.0f, 0.0f, false);
        this.leftLeg.setTextureOffset(0, 30).addBox(23.0f, -1.9f, -4.0f, 4.0f, 0.0f, 18.0f, 0.0f, false);
        this.rightLeg = new AdvancedModelBox((AdvancedEntityModel)this, "rightLeg");
        this.rightLeg.setRotationPoint(-20.0f, -8.0f, 21.0f);
        this.body.addChild((BasicModelPart)this.rightLeg);
        this.rightLeg.setTextureOffset(65, 151).addBox(-25.0f, -2.0f, -5.0f, 25.0f, 4.0f, 17.0f, 0.0f, true);
        this.rightLeg.setTextureOffset(0, 30).addBox(-27.0f, -1.9f, -4.0f, 4.0f, 0.0f, 18.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, -20.0f, 33.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(92, 92).addBox(-7.0f, -4.0f, 0.0f, 14.0f, 9.0f, 49.0f, 0.0f, false);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setRotationPoint(0.0f, -19.0f, -32.0f);
        this.body.addChild((BasicModelPart)this.neck);
        this.neck.setTextureOffset(0, 138).addBox(-7.0f, -5.0f, -35.0f, 14.0f, 11.0f, 36.0f, 0.0f, false);
        this.neck.setTextureOffset(0, 0).addBox(-1.0f, -7.0f, -34.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.neck.setTextureOffset(0, 0).addBox(-1.0f, -7.0f, -25.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.neck.setTextureOffset(0, 0).addBox(-1.0f, -7.0f, -16.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.neck.setTextureOffset(0, 0).addBox(-1.0f, -7.0f, -7.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.neck2 = new AdvancedModelBox((AdvancedEntityModel)this, "neck2");
        this.neck2.setRotationPoint(0.0f, 0.0f, -35.0f);
        this.neck.addChild((BasicModelPart)this.neck2);
        this.neck2.setTextureOffset(145, 0).addBox(-5.0f, -4.0f, -39.0f, 10.0f, 9.0f, 39.0f, 0.0f, false);
        this.neck2.setTextureOffset(0, 0).addBox(-1.0f, -6.0f, -35.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.neck2.setTextureOffset(0, 0).addBox(-1.0f, -6.0f, -26.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.neck2.setTextureOffset(0, 0).addBox(-1.0f, -6.0f, -17.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.neck2.setTextureOffset(0, 0).addBox(-1.0f, -6.0f, -8.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -1.0f, -40.0f);
        this.neck2.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 0).addBox(-6.0f, -5.0f, -16.0f, 12.0f, 12.0f, 17.0f, 0.0f, false);
        this.head.setTextureOffset(0, 49).addBox(1.0f, -10.0f, -14.0f, 3.0f, 5.0f, 3.0f, 0.0f, false);
        this.head.setTextureOffset(0, 41).addBox(-3.0f, -7.0f, -8.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
        this.head.setTextureOffset(27, 30).addBox(0.0f, -8.0f, -4.0f, 2.0f, 3.0f, 2.0f, 0.0f, false);
        this.bottom_jaw = new AdvancedModelBox((AdvancedEntityModel)this, "bottom_jaw");
        this.bottom_jaw.setRotationPoint(0.0f, 2.0f, -16.0f);
        this.head.addChild((BasicModelPart)this.bottom_jaw);
        this.bottom_jaw.setTextureOffset(27, 30).addBox(-4.0f, 0.0f, -9.0f, 8.0f, 3.0f, 9.0f, 0.0f, false);
        this.bottom_jaw.setTextureOffset(0, 92).addBox(-3.5f, -2.0f, -9.0f, 7.0f, 2.0f, 9.0f, 0.0f, false);
        this.top_jaw = new AdvancedModelBox((AdvancedEntityModel)this, "top_jaw");
        this.top_jaw.setRotationPoint(0.0f, 2.0f, -16.0f);
        this.head.addChild((BasicModelPart)this.top_jaw);
        this.top_jaw.setTextureOffset(103, 92).addBox(-3.0f, -4.0f, -9.0f, 6.0f, 4.0f, 9.0f, 0.0f, false);
        this.top_jaw.setTextureOffset(0, 104).addBox(-3.0f, 0.0f, -9.0f, 6.0f, 2.0f, 9.0f, 0.0f, false);
        this.top_jaw.setTextureOffset(0, 30).addBox(-2.0f, -4.0f, -11.0f, 4.0f, 8.0f, 2.0f, 0.0f, false);
        this.shell = new AdvancedModelBox((AdvancedEntityModel)this, "shell");
        this.shell.setRotationPoint(0.0f, -27.0f, 1.0f);
        this.body.addChild((BasicModelPart)this.shell);
        this.shell.setTextureOffset(0, 92).addBox(-16.0f, -7.0f, -19.0f, 32.0f, 7.0f, 38.0f, 0.0f, false);
        this.vent1 = new AdvancedModelBox((AdvancedEntityModel)this, "vent1");
        this.vent1.setRotationPoint(10.5f, -27.0f, -26.5f);
        this.body.addChild((BasicModelPart)this.vent1);
        this.vent1.setTextureOffset(224, 65).addBox(-2.5f, -10.0f, -2.5f, 5.0f, 10.0f, 5.0f, 0.0f, false);
        this.vent2 = new AdvancedModelBox((AdvancedEntityModel)this, "vent2");
        this.vent2.setRotationPoint(7.0f, -27.0f, 27.0f);
        this.body.addChild((BasicModelPart)this.vent2);
        this.vent2.setTextureOffset(216, 104).addBox(-2.0f, -7.0f, -2.0f, 4.0f, 7.0f, 4.0f, 0.0f, false);
        this.vent3 = new AdvancedModelBox((AdvancedEntityModel)this, "vent3");
        this.vent3.setRotationPoint(-6.5f, -27.0f, 24.5f);
        this.body.addChild((BasicModelPart)this.vent3);
        this.vent3.setTextureOffset(182, 103).addBox(-2.5f, -14.0f, -2.5f, 5.0f, 14.0f, 5.0f, 0.0f, false);
        this.vent4 = new AdvancedModelBox((AdvancedEntityModel)this, "vent4");
        this.vent4.setRotationPoint(-6.0f, -27.0f, -23.0f);
        this.body.addChild((BasicModelPart)this.vent4);
        this.vent4.setTextureOffset(226, 124).addBox(-3.0f, -13.0f, -3.0f, 6.0f, 13.0f, 6.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void translateRotate(PoseStack stack) {
        this.head.translateRotate(stack);
        this.neck2.translateRotate(stack);
        this.neck.translateRotate(stack);
        this.body.translateRotate(stack);
        this.root.translateRotate(stack);
    }

    public void setupAnim(EntityLaviathan entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float partialTick = Minecraft.m_91087_().m_91296_();
        float hh1 = entity.prevHeadHeight;
        float hh2 = entity.getHeadHeight();
        float rawHeadHeight = (hh1 + (hh2 - hh1) * partialTick) / 3.0f;
        float clampedNeckRot = Mth.m_14036_((float)(-rawHeadHeight), (float)-1.0f, (float)1.0f);
        float headStillProgress = 1.0f - Math.abs(clampedNeckRot);
        float swimProgress = entity.prevSwimProgress + (entity.swimProgress - entity.prevSwimProgress) * partialTick;
        float onLandProgress = Math.max(0.0f, 5.0f - swimProgress);
        float biteProgress = entity.prevBiteProgress + (entity.biteProgress - entity.prevBiteProgress) * partialTick;
        this.neck.rotateAngleX += clampedNeckRot;
        this.neck.rotationPointZ += Math.abs(clampedNeckRot) * 2.0f;
        this.neck2.rotateAngleX -= clampedNeckRot * 0.4f;
        this.neck2.rotationPointZ += Math.abs(clampedNeckRot) * 2.0f;
        this.head.rotateAngleX -= clampedNeckRot * 0.6f;
        this.head.rotationPointZ += Math.abs(clampedNeckRot) * 2.0f;
        this.neck.rotationPointY -= Mth.m_14036_((float)(Math.abs(entity.getHeadYaw(partialTick)) / 50.0f), (float)0.0f, (float)1.0f);
        this.neck.rotateAngleY = (float)((double)this.neck.rotateAngleY + Math.toRadians(entity.getHeadYaw(partialTick) * 0.65f));
        this.neck2.rotateAngleY = (float)((double)this.neck2.rotateAngleY + Math.toRadians(entity.getHeadYaw(partialTick) * 0.6f));
        this.head.rotateAngleY = (float)((double)this.head.rotateAngleY + Math.toRadians(entity.getHeadYaw(partialTick) * 0.45f));
        this.progressRotationPrev(this.rightLeg, onLandProgress, 0.0f, 0.0f, Maths.rad(-15.0), 5.0f);
        this.progressRotationPrev(this.leftLeg, onLandProgress, 0.0f, 0.0f, Maths.rad(15.0), 5.0f);
        this.progressRotationPrev(this.rightArm, onLandProgress, 0.0f, 0.0f, Maths.rad(-20.0), 5.0f);
        this.progressRotationPrev(this.leftArm, onLandProgress, 0.0f, 0.0f, Maths.rad(20.0), 5.0f);
        this.progressRotationPrev(this.head, biteProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.top_jaw, biteProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.bottom_jaw, biteProgress, Maths.rad(40.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.neck, biteProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.bottom_jaw, biteProgress, 0.0f, 2.0f, -1.0f, 5.0f);
        this.progressPositionPrev(this.neck, biteProgress, 0.0f, 0.0f, 2.0f, 5.0f);
        float idleSpeed = 0.04f;
        float idleDegree = 0.3f;
        float walkSpeed = 0.9f;
        if (entity.swimProgress >= 5.0f) {
            walkSpeed = 0.3f;
        }
        float walkDegree = 0.5f + swimProgress * 0.05f;
        AdvancedModelBox[] neckBoxes = new AdvancedModelBox[]{this.neck, this.neck2, this.head};
        this.chainWave(neckBoxes, idleSpeed, idleDegree * 0.2f, 9.0, ageInTicks, 1.0f);
        this.walk(this.tail, idleSpeed, idleDegree * 0.4f, false, 1.3f, -0.2f, ageInTicks, 1.0f);
        this.walk(this.bottom_jaw, idleSpeed * 2.0f, idleDegree * 0.4f, false, 1.0f, 0.1f, ageInTicks, 1.0f);
        this.swing(this.leftLeg, walkSpeed, walkDegree, true, 2.0f, 0.2f, limbSwing, limbSwingAmount * onLandProgress * 0.2f);
        this.swing(this.rightLeg, walkSpeed, walkDegree, false, 2.0f, 0.2f, limbSwing, limbSwingAmount * onLandProgress * 0.2f);
        this.swing(this.leftArm, walkSpeed, walkDegree, false, 2.0f, -0.25f, limbSwing, limbSwingAmount * onLandProgress * 0.2f);
        this.swing(this.rightArm, walkSpeed, walkDegree, true, 2.0f, -0.25f, limbSwing, limbSwingAmount * onLandProgress * 0.2f);
        this.bob(this.body, -walkSpeed * 0.5f, walkDegree * 3.0f, true, limbSwing, limbSwingAmount);
        this.chainSwing(neckBoxes, walkSpeed, walkDegree * 0.3f, -21.0, limbSwing, limbSwingAmount * swimProgress * 0.2f * headStillProgress);
        this.swing(this.tail, walkSpeed, walkDegree * 0.5f, false, -3.0f, 0.0f, limbSwing, limbSwingAmount * swimProgress * 0.2f);
        this.flap(this.leftLeg, walkSpeed, walkDegree, true, 2.0f, 0.2f, limbSwing, limbSwingAmount * swimProgress * 0.2f);
        this.flap(this.rightLeg, walkSpeed, walkDegree, false, 2.0f, 0.2f, limbSwing, limbSwingAmount * swimProgress * 0.2f);
        this.flap(this.leftArm, walkSpeed, walkDegree, false, 2.0f, -0.25f, limbSwing, limbSwingAmount * swimProgress * 0.2f);
        this.flap(this.rightArm, walkSpeed, walkDegree, true, 2.0f, -0.25f, limbSwing, limbSwingAmount * swimProgress * 0.2f);
        this.flap(this.leftLeg, idleSpeed, idleDegree, false, 1.0f, 0.2f, ageInTicks, swimProgress * 0.2f);
        this.flap(this.rightLeg, idleSpeed, idleDegree, true, 1.0f, 0.2f, ageInTicks, swimProgress * 0.2f);
        this.flap(this.leftArm, idleSpeed, idleDegree, false, 1.0f, 0.2f, ageInTicks, swimProgress * 0.2f);
        this.flap(this.rightArm, idleSpeed, idleDegree, true, 1.0f, 0.2f, ageInTicks, swimProgress * 0.2f);
        this.tail.rotationPointZ -= limbSwingAmount * swimProgress * 0.2f;
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.leftArm, (Object)this.rightArm, (Object)this.leftLeg, (Object)this.rightLeg, (Object)this.tail, (Object)this.neck, (Object)this.neck2, (Object)this.head, (Object)this.bottom_jaw, (Object)this.top_jaw, (Object[])new AdvancedModelBox[]{this.shell, this.vent1, this.vent2, this.vent3, this.vent4});
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.45f;
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
}
