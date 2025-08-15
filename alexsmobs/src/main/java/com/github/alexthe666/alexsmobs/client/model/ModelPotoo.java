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

import com.github.alexthe666.alexsmobs.entity.EntityPotoo;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelPotoo
extends AdvancedEntityModel<EntityPotoo> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox left_wing;
    private final AdvancedModelBox right_wing;
    private final AdvancedModelBox left_foot;
    private final AdvancedModelBox right_foot;
    private final AdvancedModelBox head;
    private final AdvancedModelBox left_eye;
    private final AdvancedModelBox left_pupil;
    private final AdvancedModelBox right_eye;
    private final AdvancedModelBox right_pupil;

    public ModelPotoo() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -5.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-3.5f, -4.0f, -3.0f, 7.0f, 8.0f, 6.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, 4.0f, 2.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 26).addBox(-2.5f, 0.0f, -1.0f, 5.0f, 8.0f, 2.0f, 0.0f, false);
        this.left_wing = new AdvancedModelBox((AdvancedEntityModel)this, "left_wing");
        this.left_wing.setRotationPoint(3.5f, -2.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.left_wing);
        this.left_wing.setTextureOffset(22, 21).addBox(0.0f, -1.0f, -2.0f, 1.0f, 8.0f, 5.0f, 0.0f, false);
        this.right_wing = new AdvancedModelBox((AdvancedEntityModel)this, "right_wing");
        this.right_wing.setRotationPoint(-3.5f, -2.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.right_wing);
        this.right_wing.setTextureOffset(22, 21).addBox(-1.0f, -1.0f, -2.0f, 1.0f, 8.0f, 5.0f, 0.0f, true);
        this.left_foot = new AdvancedModelBox((AdvancedEntityModel)this, "left_foot");
        this.left_foot.setRotationPoint(2.5f, 3.9f, -2.0f);
        this.body.addChild((BasicModelPart)this.left_foot);
        this.left_foot.setTextureOffset(21, 0).addBox(-1.5f, 0.0f, -2.0f, 3.0f, 2.0f, 3.0f, 0.0f, false);
        this.right_foot = new AdvancedModelBox((AdvancedEntityModel)this, "right_foot");
        this.right_foot.setRotationPoint(-2.5f, 3.9f, -2.0f);
        this.body.addChild((BasicModelPart)this.right_foot);
        this.right_foot.setTextureOffset(21, 0).addBox(-1.5f, 0.0f, -2.0f, 3.0f, 2.0f, 3.0f, 0.0f, true);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -4.0f, 3.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 15).addBox(-3.5f, -4.0f, -6.0f, 7.0f, 4.0f, 6.0f, 0.0f, false);
        this.head.setTextureOffset(21, 9).addBox(-3.5f, -0.7f, -6.0f, 7.0f, 0.0f, 6.0f, 0.0f, false);
        this.head.setTextureOffset(0, 0).addBox(-0.5f, -1.0f, -7.0f, 1.0f, 2.0f, 1.0f, 0.0f, false);
        this.left_eye = new AdvancedModelBox((AdvancedEntityModel)this, "left_eye");
        this.left_eye.setRotationPoint(4.0f, -2.4f, -4.4f);
        this.head.addChild((BasicModelPart)this.left_eye);
        this.left_eye.setTextureOffset(30, 16).addBox(-1.0f, -1.5f, -1.5f, 2.0f, 3.0f, 3.0f, 0.0f, false);
        this.left_pupil = new AdvancedModelBox((AdvancedEntityModel)this, "left_pupil");
        this.left_pupil.setRotationPoint(0.1f, 0.0f, 0.0f);
        this.left_eye.addChild((BasicModelPart)this.left_pupil);
        this.left_pupil.setTextureOffset(21, 16).addBox(-1.08f, -0.5f, -0.5f, 2.0f, 1.0f, 1.0f, 0.0f, false);
        this.right_eye = new AdvancedModelBox((AdvancedEntityModel)this, "right_eye");
        this.right_eye.setRotationPoint(-4.0f, -2.4f, -4.4f);
        this.head.addChild((BasicModelPart)this.right_eye);
        this.right_eye.setTextureOffset(30, 16).addBox(-1.0f, -1.5f, -1.5f, 2.0f, 3.0f, 3.0f, 0.0f, true);
        this.right_pupil = new AdvancedModelBox((AdvancedEntityModel)this, "right_pupil");
        this.right_pupil.setRotationPoint(-0.1f, 0.0f, 0.0f);
        this.right_eye.addChild((BasicModelPart)this.right_pupil);
        this.right_pupil.setTextureOffset(21, 16).addBox(-0.92f, -0.5f, -0.5f, 2.0f, 1.0f, 1.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.tail, (Object)this.left_wing, (Object)this.right_wing, (Object)this.head, (Object)this.left_foot, (Object)this.left_pupil, (Object)this.left_eye, (Object)this.right_foot, (Object)this.right_pupil, (Object)this.right_eye, (Object[])new AdvancedModelBox[0]);
    }

    public void setupAnim(EntityPotoo entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float partialTick = Minecraft.m_91087_().m_91296_();
        float flyProgress = entity.prevFlyProgress + (entity.flyProgress - entity.prevFlyProgress) * partialTick;
        float perchProgress = entity.prevPerchProgress + (entity.perchProgress - entity.prevPerchProgress) * partialTick;
        float mouthProgress = entity.prevMouthProgress + (entity.mouthProgress - entity.prevMouthProgress) * partialTick;
        float flapSpeed = 0.8f;
        float flapDegree = 0.2f;
        float walkSpeed = 1.6f;
        float walkDegree = 0.8f;
        float eyeScale = Mth.m_14036_((float)((15.0f - entity.getEyeScale(10, partialTick)) / 15.0f), (float)0.0f, (float)1.0f);
        this.left_pupil.setScale(0.5f, 1.0f + eyeScale * 2.1f, 1.0f + eyeScale * 2.1f);
        this.left_pupil.rotationPointX += 0.5f;
        this.right_pupil.setScale(0.5f, 1.0f + eyeScale * 2.1f, 1.0f + eyeScale * 2.1f);
        this.right_pupil.rotationPointX -= 0.5f;
        if (entity.m_5803_()) {
            this.right_eye.showModel = false;
            this.right_pupil.showModel = false;
            this.left_eye.showModel = false;
            this.left_pupil.showModel = false;
        } else {
            this.right_eye.showModel = true;
            this.right_pupil.showModel = true;
            this.left_eye.showModel = true;
            this.left_pupil.showModel = true;
        }
        float walkAmount = (5.0f - flyProgress) * 0.2f;
        float walkSwingAmount = limbSwingAmount * walkAmount;
        this.progressRotationPrev(this.body, Math.min(walkSwingAmount, 0.5f), Maths.rad(20.0), 0.0f, 0.0f, 0.5f);
        this.progressRotationPrev(this.left_foot, Math.min(walkSwingAmount, 0.5f), Maths.rad(-20.0), 0.0f, 0.0f, 0.5f);
        this.progressRotationPrev(this.right_foot, Math.min(walkSwingAmount, 0.5f), Maths.rad(-20.0), 0.0f, 0.0f, 0.5f);
        this.progressRotationPrev(this.tail, 5.0f - perchProgress, Maths.rad(85.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, flyProgress * limbSwingAmount, Maths.rad(80.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_wing, flyProgress, Maths.rad(-90.0), Maths.rad(90.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.left_wing, flyProgress, Maths.rad(-90.0), Maths.rad(-90.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, flyProgress, Maths.rad(-60.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, mouthProgress, Maths.rad(-70.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, mouthProgress, 0.0f, 0.5f, -0.5f, 5.0f);
        this.flap(this.body, walkSpeed, walkDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, walkSwingAmount);
        if (flyProgress > 0.0f) {
            this.bob(this.body, flapSpeed * 0.5f, flapDegree * 4.0f, true, ageInTicks, 1.0f);
            this.swing(this.right_wing, flapSpeed, flapDegree * 5.0f, true, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.swing(this.left_wing, flapSpeed, flapDegree * 5.0f, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        }
        this.left_foot.rotationPointZ += 2.0f * (float)(Math.sin((double)(limbSwing * walkSpeed) - 2.5) * (double)walkSwingAmount * (double)walkDegree - (double)(walkSwingAmount * walkDegree));
        this.right_foot.rotationPointZ += 2.0f * (float)(Math.sin(-((double)(limbSwing * walkSpeed)) + 2.5) * (double)walkSwingAmount * (double)walkDegree - (double)(walkSwingAmount * walkDegree));
        this.left_foot.rotationPointY += (float)(Math.sin((double)(limbSwing * walkSpeed) - 2.5) * (double)walkSwingAmount * (double)walkDegree - (double)(walkSwingAmount * walkDegree));
        this.right_foot.rotationPointY += (float)(Math.sin(-((double)(limbSwing * walkSpeed)) + 2.5) * (double)walkSwingAmount * (double)walkDegree - (double)(walkSwingAmount * walkDegree));
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.25f;
            this.right_eye.setScale(f, f, f);
            this.left_eye.setScale(f, f, f);
            this.right_eye.setShouldScaleChildren(true);
            this.left_eye.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.right_eye.setScale(1.0f, 1.0f, 1.0f);
            this.left_eye.setScale(1.0f, 1.0f, 1.0f);
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }
}
