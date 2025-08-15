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

import com.github.alexthe666.alexsmobs.entity.EntityBananaSlug;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class ModelBananaSlug
extends AdvancedEntityModel<EntityBananaSlug> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox goo;
    private final AdvancedModelBox leftAntenna;
    private final AdvancedModelBox rightAntenna;
    private final AdvancedModelBox tail;

    public ModelBananaSlug() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -2.0f, -2.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(18, 23).addBox(-2.5f, -2.0f, -4.0f, 5.0f, 4.0f, 7.0f, 0.0f, false);
        this.goo = new AdvancedModelBox((AdvancedEntityModel)this, "goo");
        this.goo.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.goo);
        this.goo.setTextureOffset(0, 0).addBox(-2.5f, -0.001f, 0.0f, 5.0f, 0.0f, 17.0f, 0.0f, false);
        this.leftAntenna = new AdvancedModelBox((AdvancedEntityModel)this, "leftAntenna");
        this.leftAntenna.setRotationPoint(2.0f, -1.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.leftAntenna);
        this.setRotationAngle(this.leftAntenna, 0.0f, 0.0f, -0.0873f);
        this.leftAntenna.setTextureOffset(0, 0).addBox(0.0f, -1.0f, -5.0f, 0.0f, 3.0f, 5.0f, 0.0f, false);
        this.rightAntenna = new AdvancedModelBox((AdvancedEntityModel)this, "rightAntenna");
        this.rightAntenna.setRotationPoint(-2.0f, -1.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.rightAntenna);
        this.setRotationAngle(this.rightAntenna, 0.0f, 0.0f, 0.0873f);
        this.rightAntenna.setTextureOffset(0, 0).addBox(0.0f, -1.0f, -5.0f, 0.0f, 3.0f, 5.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, 0.0f, 3.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 18).addBox(-2.0f, -1.0f, 0.0f, 4.0f, 3.0f, 8.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setupAnim(EntityBananaSlug entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.25f;
        float idleDegree = 0.25f;
        float walkSpeed = 1.0f;
        float walkDegree = 0.2f;
        float partialTick = ageInTicks - (float)entity.f_19797_;
        this.swing(this.leftAntenna, idleSpeed, idleDegree * 0.2f, true, 1.0f, 0.1f, ageInTicks, 1.0f);
        this.swing(this.rightAntenna, idleSpeed, idleDegree * 0.2f, false, 1.0f, 0.1f, ageInTicks, 1.0f);
        this.walk(this.leftAntenna, idleSpeed, idleDegree * 0.5f, true, 3.0f, 0.1f, ageInTicks, 1.0f);
        this.walk(this.rightAntenna, idleSpeed, idleDegree * 0.5f, true, 3.0f, 0.1f, ageInTicks, 1.0f);
        this.swing(this.tail, walkSpeed, walkDegree, true, 3.0f, 0.0f, limbSwing, limbSwingAmount);
        float antennaBack = -0.5f + (float)Math.sin((double)(ageInTicks * idleSpeed) + 3.0) * 0.2f;
        this.leftAntenna.rotationPointZ -= antennaBack;
        this.rightAntenna.rotationPointZ -= antennaBack;
        float stretch1 = (float)(Math.sin(limbSwing * -walkSpeed) * (double)limbSwingAmount) + limbSwingAmount;
        float stretch2 = (float)(Math.sin(limbSwing * -walkSpeed + 1.0f) * (double)limbSwingAmount) + limbSwingAmount;
        this.body.setScale(1.0f, 1.0f - stretch1 * 0.025f, 1.0f + stretch1 * 0.25f);
        this.tail.setScale(1.0f, 1.0f - stretch2 * 0.05f, 1.0f + stretch2 * 0.5f);
        this.body.setShouldScaleChildren(false);
        this.body.rotationPointZ -= stretch1 * 2.0f;
        this.leftAntenna.rotationPointZ -= stretch1 * 1.0f;
        this.rightAntenna.rotationPointZ -= stretch1 * 1.0f;
        this.leftAntenna.rotateAngleY += netHeadYaw * 0.6f * ((float)Math.PI / 180);
        this.leftAntenna.rotateAngleX += headPitch * 0.3f * ((float)Math.PI / 180);
        this.rightAntenna.rotateAngleY += netHeadYaw * 0.6f * ((float)Math.PI / 180);
        this.rightAntenna.rotateAngleX += headPitch * 0.3f * ((float)Math.PI / 180);
        float yaw = entity.f_20884_ + (entity.f_20883_ - entity.f_20884_) * partialTick;
        float slimeYaw = Mth.m_14177_((float)(entity.prevTrailYaw + (entity.trailYaw - entity.prevTrailYaw) * partialTick - yaw)) * 0.65f;
        this.goo.rotationPointX = Mth.m_14031_((float)(limbSwing * -walkSpeed - 1.0f)) * limbSwingAmount;
        this.goo.rotateAngleY += Maths.rad(slimeYaw);
        this.tail.rotateAngleY += Maths.rad(slimeYaw * 0.8f);
        this.goo.setScale(1.0f, 0.0f, 1.0f + limbSwingAmount);
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.65f, 0.65f, 0.65f);
            matrixStackIn.m_85837_(0.0, 0.8, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.goo, (Object)this.tail, (Object)this.leftAntenna, (Object)this.rightAntenna);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
