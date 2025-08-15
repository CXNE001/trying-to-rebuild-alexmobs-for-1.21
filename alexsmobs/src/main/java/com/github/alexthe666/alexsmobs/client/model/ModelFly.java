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

import com.github.alexthe666.alexsmobs.entity.EntityFly;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelFly
extends AdvancedEntityModel<EntityFly> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox legs;
    private final AdvancedModelBox left_wing;
    private final AdvancedModelBox right_wing;
    private final AdvancedModelBox mouth;

    public ModelFly() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -3.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-2.0f, -2.0f, -3.0f, 4.0f, 4.0f, 6.0f, 0.0f, false);
        this.legs = new AdvancedModelBox((AdvancedEntityModel)this, "legs");
        this.legs.setPos(0.0f, 2.0f, -2.0f);
        this.body.addChild((BasicModelPart)this.legs);
        this.legs.setTextureOffset(0, 11).addBox(-1.5f, 0.0f, 0.0f, 3.0f, 1.0f, 5.0f, 0.0f, false);
        this.left_wing = new AdvancedModelBox((AdvancedEntityModel)this, "left_wing");
        this.left_wing.setPos(1.0f, -2.0f, -1.0f);
        this.body.addChild((BasicModelPart)this.left_wing);
        this.left_wing.setTextureOffset(12, 11).addBox(0.0f, 0.0f, -1.0f, 4.0f, 0.0f, 3.0f, 0.0f, false);
        this.right_wing = new AdvancedModelBox((AdvancedEntityModel)this, "right_wing");
        this.right_wing.setPos(-1.0f, -2.0f, -1.0f);
        this.body.addChild((BasicModelPart)this.right_wing);
        this.right_wing.setTextureOffset(12, 11).addBox(-4.0f, 0.0f, -1.0f, 4.0f, 0.0f, 3.0f, 0.0f, true);
        this.mouth = new AdvancedModelBox((AdvancedEntityModel)this, "mouth");
        this.mouth.setPos(0.0f, 0.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.mouth);
        this.mouth.setTextureOffset(15, 16).addBox(0.0f, 0.0f, -1.0f, 0.0f, 4.0f, 2.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.65f, 0.65f, 0.65f);
            matrixStackIn.m_85837_(0.0, 0.95, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setupAnim(EntityFly entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag;
        this.resetToDefaultPose();
        float flySpeed = 1.4f;
        float flyDegree = 0.8f;
        float idleSpeed = 1.4f;
        float idleDegree = 0.8f;
        this.walk(this.mouth, idleSpeed * 0.2f, idleDegree * 0.1f, false, -1.0f, 0.2f, ageInTicks, 1.0f);
        this.flap(this.mouth, idleSpeed * 0.2f, idleDegree * 0.05f, false, -2.0f, 0.0f, ageInTicks, 1.0f);
        boolean bl = flag = entityIn.m_20096_() && entityIn.m_20184_().m_82556_() < 1.0E-7;
        if (flag) {
            this.left_wing.rotateAngleZ = Maths.rad(-35.0);
            this.right_wing.rotateAngleZ = Maths.rad(35.0);
            this.swing(this.legs, flySpeed * 0.6f, flyDegree * 0.2f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        } else {
            this.flap(this.left_wing, flySpeed * 1.3f, flyDegree, true, 0.0f, 0.2f, ageInTicks, 1.0f);
            this.flap(this.right_wing, flySpeed * 1.3f, flyDegree, false, 0.0f, 0.2f, ageInTicks, 1.0f);
            this.walk(this.legs, flySpeed * 0.2f, flyDegree * 0.2f, false, 1.0f, 0.2f, ageInTicks, 1.0f);
        }
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.left_wing, (Object)this.right_wing, (Object)this.legs, (Object)this.mouth);
    }

    public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z) {
        advancedModelBox.rotateAngleX = x;
        advancedModelBox.rotateAngleY = y;
        advancedModelBox.rotateAngleZ = z;
    }
}
