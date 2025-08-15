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

import com.github.alexthe666.alexsmobs.entity.EntityHummingbird;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;

public class ModelHummingbird
extends AdvancedEntityModel<EntityHummingbird> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox head;
    private final AdvancedModelBox wingL;
    private final AdvancedModelBox wingL_r1;
    private final AdvancedModelBox wingR;
    private final AdvancedModelBox wingR_r1;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox legL;
    private final AdvancedModelBox legR;

    public ModelHummingbird() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -0.5f, -0.5f);
        this.root.addChild((BasicModelPart)this.body);
        this.setRotationAngle(this.body, 0.2618f, 0.0f, 0.0f);
        this.body.setTextureOffset(0, 6).addBox(-1.5f, -4.7f, -1.4f, 3.0f, 5.0f, 3.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -4.4f, 0.6f);
        this.body.addChild((BasicModelPart)this.head);
        this.setRotationAngle(this.head, -0.2182f, 0.0f, 0.0f);
        this.head.setTextureOffset(10, 12).addBox(-1.5f, -3.1f, -2.1f, 3.0f, 3.0f, 3.0f, 0.1f, false);
        this.head.setTextureOffset(12, 0).addBox(-0.5f, -2.1f, -5.2f, 1.0f, 1.0f, 3.0f, 0.0f, false);
        this.wingL = new AdvancedModelBox((AdvancedEntityModel)this, "wingL");
        this.wingL.setPos(1.5f, -4.5f, 0.5f);
        this.body.addChild((BasicModelPart)this.wingL);
        this.wingL_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "wingL_r1");
        this.wingL_r1.setPos(-0.3f, 0.0f, -1.0f);
        this.wingL.addChild((BasicModelPart)this.wingL_r1);
        this.setRotationAngle(this.wingL_r1, 0.0f, 0.0f, -0.0873f);
        this.wingL_r1.setTextureOffset(0, 15).addBox(0.0f, 0.0f, -0.1f, 1.0f, 5.0f, 2.0f, 0.0f, false);
        this.wingR = new AdvancedModelBox((AdvancedEntityModel)this, "wingR");
        this.wingR.setPos(-1.5f, -4.5f, 0.5f);
        this.body.addChild((BasicModelPart)this.wingR);
        this.wingR_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "wingR_r1");
        this.wingR_r1.setPos(0.3f, 0.0f, -1.0f);
        this.wingR.addChild((BasicModelPart)this.wingR_r1);
        this.setRotationAngle(this.wingR_r1, 0.0f, 0.0f, 0.0873f);
        this.wingR_r1.setTextureOffset(0, 15).addBox(-1.0f, 0.0f, -0.1f, 1.0f, 5.0f, 2.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, -0.1f, 1.5f);
        this.body.addChild((BasicModelPart)this.tail);
        this.setRotationAngle(this.tail, -0.48f, 0.0f, 0.0f);
        this.tail.setTextureOffset(0, 0).addBox(-1.5f, 0.0f, 0.0f, 3.0f, 0.0f, 5.0f, 0.0f, false);
        this.legL = new AdvancedModelBox((AdvancedEntityModel)this, "legL");
        this.legL.setPos(0.9f, -0.7f, -1.1f);
        this.body.addChild((BasicModelPart)this.legL);
        this.setRotationAngle(this.legL, -0.2618f, 0.0f, 0.0f);
        this.legL.setTextureOffset(0, 0).addBox(-0.5f, 0.0f, -1.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.legR = new AdvancedModelBox((AdvancedEntityModel)this, "legR");
        this.legR.setPos(-0.9f, -0.7f, -1.1f);
        this.body.addChild((BasicModelPart)this.legR);
        this.setRotationAngle(this.legR, -0.2618f, 0.0f, 0.0f);
        this.legR.setTextureOffset(0, 0).addBox(-0.5f, 0.0f, -1.0f, 1.0f, 1.0f, 1.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityHummingbird entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float flySpeed = 1.0f;
        float flyDegree = 0.8f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float flyProgress = entityIn.prevFlyProgress + (entityIn.flyProgress - entityIn.prevFlyProgress) * partialTick;
        float zoomProgress = entityIn.prevMovingProgress + (entityIn.movingProgress - entityIn.prevMovingProgress) * partialTick;
        float sipProgress = entityIn.prevSipProgress + (entityIn.sipProgress - entityIn.prevSipProgress) * partialTick;
        this.progressRotationPrev(this.body, flyProgress, Maths.rad(30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, flyProgress, Maths.rad(-30.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.wingL, flyProgress, Maths.rad(15.0), Maths.rad(55.0), Maths.rad(-100.0), 5.0f);
        this.progressRotationPrev(this.wingR, flyProgress, Maths.rad(15.0), Maths.rad(-55.0), Maths.rad(100.0), 5.0f);
        this.progressRotationPrev(this.tail, flyProgress, Maths.rad(-45.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legL, flyProgress, Maths.rad(-45.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legR, flyProgress, Maths.rad(-45.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.wingL, flyProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.wingR, flyProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.legL, flyProgress, 0.0f, -0.4f, 0.0f, 5.0f);
        this.progressPositionPrev(this.legR, flyProgress, 0.0f, -0.4f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, flyProgress, 0.0f, -0.5f, -1.0f, 5.0f);
        if (flyProgress > 0.0f) {
            this.progressRotationPrev(this.body, zoomProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
            this.progressRotationPrev(this.head, zoomProgress, Maths.rad(-25.0), 0.0f, 0.0f, 5.0f);
            this.progressRotationPrev(this.wingL, zoomProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
            this.progressRotationPrev(this.wingR, zoomProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        }
        this.progressPositionPrev(this.body, sipProgress, 0.0f, -1.0f, 3.0f, 5.0f);
        this.progressRotationPrev(this.head, sipProgress, Maths.rad(60.0), 0.0f, 0.0f, 5.0f);
        if (entityIn.isFlying()) {
            this.flap(this.wingL, flySpeed * 2.3f, flyDegree * 2.3f, false, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.walk(this.wingL, flySpeed * 2.3f, flyDegree, false, 0.0f, -0.4f, ageInTicks, 1.0f);
            this.flap(this.wingR, flySpeed * 2.3f, flyDegree * 2.3f, true, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.walk(this.wingR, flySpeed * 2.3f, flyDegree, false, 0.0f, -0.4f, ageInTicks, 1.0f);
            this.bob(this.legL, flySpeed * 0.3f, flyDegree * -0.2f, false, ageInTicks, 1.0f);
            this.bob(this.legR, flySpeed * 0.3f, flyDegree * -0.2f, false, ageInTicks, 1.0f);
            this.walk(this.body, flySpeed * 0.3f, flyDegree * 0.05f, false, 0.0f, 0.1f, ageInTicks, 1.0f);
            this.walk(this.tail, flySpeed * 0.3f, flyDegree * 0.1f, false, 1.0f, 0.1f, ageInTicks, 1.0f);
            this.walk(this.head, flySpeed * 0.3f, flyDegree * 0.05f, true, 2.0f, 0.1f, ageInTicks, 1.0f);
            this.bob(this.body, flySpeed * 0.3f, flyDegree * 0.3f, true, ageInTicks, 1.0f);
        }
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.head, (Object)this.wingL, (Object)this.wingL_r1, (Object)this.wingR, (Object)this.wingR_r1, (Object)this.tail, (Object)this.legL, (Object)this.legR);
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

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
