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
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityBaldEagle;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelBaldEagle
extends AdvancedEntityModel<EntityBaldEagle> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox legL;
    private final AdvancedModelBox footL;
    private final AdvancedModelBox legR;
    private final AdvancedModelBox footR;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox headPivot;
    private final AdvancedModelBox head;
    private final AdvancedModelBox topHead;
    private final AdvancedModelBox beak1;
    private final AdvancedModelBox beak2;
    private final AdvancedModelBox hood_tie;
    private final AdvancedModelBox hood;
    private final AdvancedModelBox wingR;
    private final AdvancedModelBox feathersR;
    private final AdvancedModelBox tipR;
    private final AdvancedModelBox wingL;
    private final AdvancedModelBox feathersL;
    private final AdvancedModelBox tipL;

    public ModelBaldEagle() {
        this.texWidth = 64;
        this.texHeight = 32;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -9.3f, -2.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.setRotationAngle(this.body, 0.8727f, 0.0f, 0.0f);
        this.body.setTextureOffset(1, 12).addBox(-2.0f, 0.0f, -1.8f, 4.0f, 8.0f, 5.0f, 0.0f, false);
        this.legL = new AdvancedModelBox((AdvancedEntityModel)this, "legL");
        this.legL.setPos(-1.4f, 5.5f, -1.15f);
        this.body.addChild((BasicModelPart)this.legL);
        this.setRotationAngle(this.legL, -0.8727f, 0.1745f, 0.0f);
        this.legL.setTextureOffset(0, 26).addBox(-0.5f, 0.2f, -0.5f, 1.0f, 4.0f, 1.0f, 0.0f, false);
        this.footL = new AdvancedModelBox((AdvancedEntityModel)this, "footL");
        this.footL.setPos(0.0f, 4.2f, 0.5f);
        this.legL.addChild((BasicModelPart)this.footL);
        this.setRotationAngle(this.footL, 0.0f, 0.1745f, -0.1745f);
        this.footL.setTextureOffset(5, 25).addBox(-1.5f, 0.0f, -1.9f, 3.0f, 2.0f, 4.0f, 0.0f, false);
        this.legR = new AdvancedModelBox((AdvancedEntityModel)this, "legR");
        this.legR.setPos(1.4f, 5.5f, -1.15f);
        this.body.addChild((BasicModelPart)this.legR);
        this.setRotationAngle(this.legR, -0.8727f, -0.1745f, 0.0f);
        this.legR.setTextureOffset(0, 26).addBox(-0.5f, 0.2f, -0.5f, 1.0f, 4.0f, 1.0f, 0.0f, false);
        this.footR = new AdvancedModelBox((AdvancedEntityModel)this, "footR");
        this.footR.setPos(0.0f, 4.2f, 0.5f);
        this.legR.addChild((BasicModelPart)this.footR);
        this.setRotationAngle(this.footR, 0.0f, -0.1745f, 0.1745f);
        this.footR.setTextureOffset(5, 25).addBox(-1.5f, 0.0f, -1.9f, 3.0f, 2.0f, 4.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, 8.07f, 1.36f);
        this.body.addChild((BasicModelPart)this.tail);
        this.setRotationAngle(this.tail, 0.392f, 0.0f, 0.0f);
        this.tail.setTextureOffset(24, 1).addBox(-1.5f, -1.0f, -1.0f, 3.0f, 7.0f, 1.0f, 0.0f, false);
        this.headPivot = new AdvancedModelBox((AdvancedEntityModel)this, "headPivot");
        this.headPivot.setPos(0.0f, -0.51f, 0.64f);
        this.body.addChild((BasicModelPart)this.headPivot);
        this.setRotationAngle(this.headPivot, -0.576f, 0.0f, 0.0f);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.headPivot.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(1, 3).addBox(-1.5f, -4.4f, -1.0f, 3.0f, 6.0f, 3.0f, 0.0f, false);
        this.topHead = new AdvancedModelBox((AdvancedEntityModel)this, "topHead");
        this.topHead.setPos(0.0f, -4.9f, -0.5f);
        this.head.addChild((BasicModelPart)this.topHead);
        this.topHead.setTextureOffset(10, 0).addBox(-1.5f, -0.5f, -1.5f, 3.0f, 1.0f, 4.0f, 0.0f, false);
        this.beak1 = new AdvancedModelBox((AdvancedEntityModel)this, "beak1");
        this.beak1.setPos(0.0f, -3.5f, -0.7f);
        this.head.addChild((BasicModelPart)this.beak1);
        this.setRotationAngle(this.beak1, -0.1745f, 0.0f, 0.0f);
        this.beak1.setTextureOffset(21, 9).addBox(-0.5f, -1.0f, -1.94f, 1.0f, 2.0f, 2.0f, 0.0f, false);
        this.beak2 = new AdvancedModelBox((AdvancedEntityModel)this, "beak2");
        this.beak2.setPos(0.0f, -1.0f, -2.54f);
        this.beak1.addChild((BasicModelPart)this.beak2);
        this.beak2.setTextureOffset(16, 9).addBox(-0.5f, 0.01f, -0.4f, 1.0f, 3.0f, 1.0f, 0.0f, false);
        this.hood_tie = new AdvancedModelBox((AdvancedEntityModel)this, "hood_tie");
        this.hood_tie.setPos(0.0f, -3.65f, 1.85f);
        this.head.addChild((BasicModelPart)this.hood_tie);
        this.setRotationAngle(this.hood_tie, -0.2215f, 0.0f, 0.0f);
        this.hood_tie.setTextureOffset(40, -4).addBox(0.0f, -4.0f, -2.0f, 0.0f, 5.0f, 4.0f, 0.0f, false);
        this.hood = new AdvancedModelBox((AdvancedEntityModel)this, "hood");
        this.hood.setPos(0.0f, -4.9f, -0.5f);
        this.head.addChild((BasicModelPart)this.hood);
        this.hood.setTextureOffset(36, 7).addBox(-1.5f, -0.5f, -1.5f, 3.0f, 4.0f, 4.0f, 0.0f, false);
        this.hood.setScale(1.1f, 1.1f, 1.1f);
        this.wingR = new AdvancedModelBox((AdvancedEntityModel)this, "wingR");
        this.wingR.setPos(-1.9f, 0.0f, 2.0f);
        this.body.addChild((BasicModelPart)this.wingR);
        this.setRotationAngle(this.wingR, 0.576f, 0.1571f, 0.1396f);
        this.wingR.setTextureOffset(20, 14).addBox(-0.5f, -1.0f, -4.5f, 1.0f, 12.0f, 5.0f, 0.0f, true);
        this.feathersR = new AdvancedModelBox((AdvancedEntityModel)this, "feathersR");
        this.feathersR.setPos(-0.5f, 0.8f, 1.6f);
        this.wingR.addChild((BasicModelPart)this.feathersR);
        this.feathersR.setTextureOffset(52, 10).addBox(0.5f, -1.5f, -3.1f, 0.0f, 16.0f, 2.0f, 0.0f, true);
        this.tipR = new AdvancedModelBox((AdvancedEntityModel)this, "tipR");
        this.tipR.setPos(-0.51f, 11.8f, 0.5f);
        this.wingR.addChild((BasicModelPart)this.tipR);
        this.setRotationAngle(this.tipR, 0.0f, 0.0f, 0.0873f);
        this.tipR.setTextureOffset(36, 10).addBox(0.5f, -6.0f, -6.0f, 0.0f, 13.0f, 8.0f, 0.0f, true);
        this.wingL = new AdvancedModelBox((AdvancedEntityModel)this, "wingL");
        this.wingL.setPos(1.9f, 0.0f, 2.0f);
        this.body.addChild((BasicModelPart)this.wingL);
        this.setRotationAngle(this.wingL, 0.576f, -0.1571f, -0.1396f);
        this.wingL.setTextureOffset(20, 14).addBox(-0.5f, -1.0f, -4.5f, 1.0f, 12.0f, 5.0f, 0.0f, false);
        this.feathersL = new AdvancedModelBox((AdvancedEntityModel)this, "feathersL");
        this.feathersL.setPos(0.5f, 0.8f, 1.6f);
        this.wingL.addChild((BasicModelPart)this.feathersL);
        this.feathersL.setTextureOffset(52, 10).addBox(-0.5f, -1.5f, -3.1f, 0.0f, 16.0f, 2.0f, 0.0f, false);
        this.tipL = new AdvancedModelBox((AdvancedEntityModel)this, "tipL");
        this.tipL.setPos(0.51f, 11.8f, 0.5f);
        this.wingL.addChild((BasicModelPart)this.tipL);
        this.setRotationAngle(this.tipL, 0.0f, 0.0f, -0.0873f);
        this.tipL.setTextureOffset(36, 10).addBox(-0.5f, -6.0f, -6.0f, 0.0f, 13.0f, 8.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityBaldEagle entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float flapSpeed = 0.4f;
        float flapDegree = 0.2f;
        float walkSpeed = 0.5f;
        float walkDegree = 0.5f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        float partialTicks = ageInTicks - (float)entity.f_19797_;
        float flyProgress = entity.prevFlyProgress + (entity.flyProgress - entity.prevFlyProgress) * partialTicks;
        float perchProgress = entity.prevSitProgress + (entity.sitProgress - entity.prevSitProgress) * partialTicks;
        float tackleProgress = Math.min(entity.prevTackleProgress + (entity.tackleProgress - entity.prevTackleProgress) * partialTicks, flyProgress);
        float swoopProgress = Math.min(entity.prevSwoopProgress + (entity.swoopProgress - entity.prevSwoopProgress) * partialTicks, flyProgress);
        float flyFeetProgress = Math.max(0.0f, flyProgress - tackleProgress);
        float biteProgress = entity.prevAttackProgress + (entity.attackProgress - entity.prevAttackProgress) * partialTicks;
        float flapAmount = (entity.prevFlapAmount + (entity.flapAmount - entity.prevFlapAmount) * partialTicks) * flyProgress * 0.2f * (5.0f - swoopProgress) * 0.2f;
        this.progressRotationPrev(this.body, flyProgress, Maths.rad(40.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, flyProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legL, flyFeetProgress, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legR, flyFeetProgress, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.footL, flyFeetProgress, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.footR, flyFeetProgress, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.legL, flyFeetProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.legR, flyFeetProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.wingR, flyProgress, Maths.rad(-120.0), Maths.rad(90.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.wingR, flyProgress, -1.0f, 5.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.wingL, flyProgress, Maths.rad(-120.0), Maths.rad(-90.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.wingL, flyProgress, 1.0f, 5.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, flyProgress, Maths.rad(-30.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, flyProgress, 0.0f, 0.0f, -1.5f, 5.0f);
        this.progressPositionPrev(this.body, flyProgress, 0.0f, 4.0f, -1.5f, 5.0f);
        this.progressRotationPrev(this.body, perchProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legL, perchProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legR, perchProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, perchProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, perchProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, tackleProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, tackleProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legL, tackleProgress, Maths.rad(-60.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legR, tackleProgress, Maths.rad(-60.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.legL, tackleProgress, 0.0f, -1.0f, 1.0f, 5.0f);
        this.progressPositionPrev(this.legR, tackleProgress, 0.0f, -1.0f, 1.0f, 5.0f);
        this.progressRotationPrev(this.wingL, swoopProgress, Maths.rad(60.0), Maths.rad(50.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.wingL, swoopProgress, -2.0f, -2.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.wingR, swoopProgress, Maths.rad(60.0), Maths.rad(-50.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.wingR, swoopProgress, 2.0f, -2.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, swoopProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, biteProgress, Maths.rad(70.0), 0.0f, 0.0f, 2.5f);
        if (flyProgress > 0.0f) {
            this.bob(this.body, flapSpeed * 0.5f, flapDegree * 4.0f, true, ageInTicks, 1.0f);
            this.swing(this.wingL, flapSpeed, flapDegree * 3.0f, true, 0.0f, 0.0f, ageInTicks, flapAmount);
            this.swing(this.wingR, flapSpeed, flapDegree * 3.0f, false, 0.0f, 0.0f, ageInTicks, flapAmount);
            this.bob(this.body, flapSpeed * 0.5f, flapDegree * 4.0f, true, ageInTicks, flapAmount);
        } else {
            float walk = Math.min(limbSwingAmount, 1.0f);
            this.progressRotationPrev(this.body, walk, Maths.rad(15.0), 0.0f, 0.0f, 1.0f);
            this.progressRotationPrev(this.legL, walk, Maths.rad(-15.0), 0.0f, 0.0f, 1.0f);
            this.progressRotationPrev(this.legR, walk, Maths.rad(-15.0), 0.0f, 0.0f, 1.0f);
            this.progressRotationPrev(this.wingL, walk, Maths.rad(-15.0), 0.0f, 0.0f, 1.0f);
            this.progressRotationPrev(this.wingR, walk, Maths.rad(-15.0), 0.0f, 0.0f, 1.0f);
            this.progressPositionPrev(this.body, walk, 0.0f, 1.0f, 0.0f, 1.0f);
            this.bob(this.body, walkSpeed, walkDegree * 1.3f, true, limbSwing, limbSwingAmount);
            this.walk(this.legL, walkSpeed, walkDegree * 1.85f, false, 0.0f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.legR, walkSpeed, walkDegree * 1.85f, true, 0.0f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.footL, walkSpeed, walkDegree * 0.4f, true, 2.0f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.footR, walkSpeed, walkDegree * 0.4f, false, 2.0f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.head, walkSpeed, walkDegree * 0.3f, false, 1.0f, -0.2f, limbSwing, limbSwingAmount);
            this.flap(this.tail, walkSpeed, walkDegree * 0.2f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.body, walkSpeed, walkDegree * 0.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        }
        this.walk(this.head, idleSpeed * 0.7f, idleDegree, false, -1.0f, 0.05f, ageInTicks, 1.0f);
        this.walk(this.tail, idleSpeed * 0.7f, idleDegree, false, 1.0f, 0.05f, ageInTicks, 1.0f);
        if (!entity.m_20160_()) {
            this.head.rotateAngleY += netHeadYaw / 57.295776f;
            this.head.rotateAngleZ += headPitch / 57.295776f;
        }
        float birdPitch = entity.prevBirdPitch + (entity.birdPitch - entity.prevBirdPitch) * partialTicks;
        this.body.rotateAngleX += birdPitch * flyProgress * 0.2f * ((float)Math.PI / 180);
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.35f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.0);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(1.0f, 1.0f, 1.0f);
        } else {
            this.head.setScale(1.0f, 1.0f, 1.0f);
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.tail, (Object)this.head, (Object)this.headPivot, (Object)this.wingL, (Object)this.wingR, (Object)this.beak1, (Object)this.beak2, (Object)this.hood, (Object)this.tipL, (Object)this.tipR, (Object[])new AdvancedModelBox[]{this.hood_tie, this.feathersL, this.feathersR, this.legL, this.legR, this.footL, this.footR, this.topHead});
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
