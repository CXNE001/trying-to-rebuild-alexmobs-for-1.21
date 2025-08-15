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

import com.github.alexthe666.alexsmobs.entity.EntityEndergrade;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;

public class ModelEndergrade
extends AdvancedEntityModel<EntityEndergrade> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox bodymain;
    private final AdvancedModelBox legbackL;
    private final AdvancedModelBox legbackR;
    private final AdvancedModelBox legmidL;
    private final AdvancedModelBox legmidR;
    private final AdvancedModelBox bodyfront;
    private final AdvancedModelBox head;
    private final AdvancedModelBox mouth;
    private final AdvancedModelBox legfrontL;
    private final AdvancedModelBox legfrontR;
    private final AdvancedModelBox tail;

    public ModelEndergrade() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.bodymain = new AdvancedModelBox((AdvancedEntityModel)this, "bodymain");
        this.bodymain.setPos(0.0f, -9.0f, -1.0f);
        this.root.addChild((BasicModelPart)this.bodymain);
        this.bodymain.setTextureOffset(0, 0).addBox(-4.5f, -3.5f, 0.0f, 9.0f, 9.0f, 10.0f, 0.0f, false);
        this.legbackL = new AdvancedModelBox((AdvancedEntityModel)this, "legbackL");
        this.legbackL.setPos(3.5f, 3.5f, 7.0f);
        this.bodymain.addChild((BasicModelPart)this.legbackL);
        this.legbackL.setTextureOffset(11, 45).addBox(-1.5f, -1.5f, -2.0f, 3.0f, 7.0f, 4.0f, 0.0f, false);
        this.legbackR = new AdvancedModelBox((AdvancedEntityModel)this, "legbackR");
        this.legbackR.setPos(-3.5f, 3.5f, 7.0f);
        this.bodymain.addChild((BasicModelPart)this.legbackR);
        this.legbackR.setTextureOffset(11, 45).addBox(-1.5f, -1.5f, -2.0f, 3.0f, 7.0f, 4.0f, 0.0f, true);
        this.legmidL = new AdvancedModelBox((AdvancedEntityModel)this, "legmidL");
        this.legmidL.setPos(3.5f, 3.5f, 1.0f);
        this.bodymain.addChild((BasicModelPart)this.legmidL);
        this.legmidL.setTextureOffset(39, 0).addBox(-1.5f, -1.5f, -2.0f, 3.0f, 7.0f, 4.0f, 0.0f, false);
        this.legmidR = new AdvancedModelBox((AdvancedEntityModel)this, "legmidR");
        this.legmidR.setPos(-3.5f, 3.5f, 1.0f);
        this.bodymain.addChild((BasicModelPart)this.legmidR);
        this.legmidR.setTextureOffset(39, 0).addBox(-1.5f, -1.5f, -2.0f, 3.0f, 7.0f, 4.0f, 0.0f, true);
        this.bodyfront = new AdvancedModelBox((AdvancedEntityModel)this, "bodyfront");
        this.bodyfront.setPos(0.0f, 0.5f, 0.0f);
        this.bodymain.addChild((BasicModelPart)this.bodyfront);
        this.bodyfront.setTextureOffset(25, 29).addBox(-4.0f, -3.5f, -8.0f, 8.0f, 8.0f, 8.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -0.5f, -8.0f);
        this.bodyfront.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(35, 16).addBox(-3.0f, -2.0f, -4.0f, 6.0f, 6.0f, 4.0f, 0.0f, false);
        this.mouth = new AdvancedModelBox((AdvancedEntityModel)this, "mouth");
        this.mouth.setPos(0.0f, 1.5f, -4.5f);
        this.head.addChild((BasicModelPart)this.mouth);
        this.mouth.setTextureOffset(26, 46).addBox(-1.5f, -1.5f, -2.5f, 3.0f, 3.0f, 3.0f, 0.0f, false);
        this.legfrontL = new AdvancedModelBox((AdvancedEntityModel)this, "legfrontL");
        this.legfrontL.setPos(3.5f, 3.0f, -5.0f);
        this.bodyfront.addChild((BasicModelPart)this.legfrontL);
        this.legfrontL.setTextureOffset(0, 37).addBox(-1.5f, -1.5f, -2.0f, 3.0f, 7.0f, 4.0f, 0.0f, false);
        this.legfrontR = new AdvancedModelBox((AdvancedEntityModel)this, "legfrontR");
        this.legfrontR.setPos(-3.5f, 3.0f, -5.0f);
        this.bodyfront.addChild((BasicModelPart)this.legfrontR);
        this.legfrontR.setTextureOffset(0, 37).addBox(-1.5f, -1.5f, -2.0f, 3.0f, 7.0f, 4.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.5f, -1.0f, 9.9f);
        this.bodymain.addChild((BasicModelPart)this.tail);
        this.setRotationAngle(this.tail, -0.1745f, 0.0f, 0.0f);
        this.tail.setTextureOffset(0, 20).addBox(-4.0f, -1.5f, -2.4f, 7.0f, 7.0f, 9.0f, 0.0f, false);
        this.updateDefaultPose();
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

    public void setupAnim(EntityEndergrade entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        AdvancedModelBox[] bodyParts = new AdvancedModelBox[]{this.bodyfront, this.bodymain, this.tail};
        AdvancedModelBox[] legPartsRight = new AdvancedModelBox[]{this.legfrontR, this.legmidR, this.legbackR};
        AdvancedModelBox[] legPartsLeft = new AdvancedModelBox[]{this.legfrontL, this.legmidL, this.legbackL};
        float walkSpeed = 1.7f;
        float walkDegree = 0.7f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float birdPitch = entityIn.prevTartigradePitch + (entityIn.tartigradePitch - entityIn.prevTartigradePitch) * partialTick;
        float biteProgress = entityIn.prevBiteProgress + (entityIn.biteProgress - entityIn.prevBiteProgress) * partialTick;
        this.mouth.setScale(1.0f, 1.0f, 1.0f + biteProgress * 0.4f);
        this.bodymain.rotateAngleX += birdPitch * ((float)Math.PI / 180);
        this.mouth.rotationPointZ = -3.0f - biteProgress * 0.2f;
        this.chainWave(bodyParts, walkSpeed, walkDegree * 0.3f, -1.0, limbSwing, limbSwingAmount);
        this.chainWave(legPartsRight, walkSpeed, walkDegree, -1.0, limbSwing, limbSwingAmount);
        this.chainWave(legPartsLeft, walkSpeed, walkDegree, -1.0, limbSwing, limbSwingAmount);
        this.chainFlap(legPartsRight, walkSpeed, walkDegree, 3.0, limbSwing, limbSwingAmount);
        this.chainFlap(legPartsLeft, walkSpeed, -walkDegree, 3.0, limbSwing, limbSwingAmount);
        this.swing(this.tail, walkSpeed, walkDegree * 0.5f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.bodymain, (Object)this.legbackL, (Object)this.legbackR, (Object)this.legfrontL, (Object)this.legfrontR, (Object)this.legmidL, (Object)this.legmidR, (Object)this.bodyfront, (Object)this.head, (Object)this.mouth, (Object)this.tail, (Object[])new AdvancedModelBox[0]);
    }

    public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z) {
        advancedModelBox.rotateAngleX = x;
        advancedModelBox.rotateAngleY = y;
        advancedModelBox.rotateAngleZ = z;
    }
}
