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

import com.github.alexthe666.alexsmobs.entity.EntityMudskipper;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelMudskipper
extends AdvancedEntityModel<EntityMudskipper> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox head;
    private final AdvancedModelBox eyes;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox dorsalFin;
    private final AdvancedModelBox tailFin;
    private final AdvancedModelBox leftFin;
    private final AdvancedModelBox rightFin;

    public ModelMudskipper() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -2.0f, -5.0f);
        this.root.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 15).addBox(-3.5f, -3.0f, -4.0f, 7.0f, 5.0f, 6.0f, 0.0f, false);
        this.eyes = new AdvancedModelBox((AdvancedEntityModel)this, "eyes");
        this.eyes.setRotationPoint(0.0f, -3.0f, -1.5f);
        this.head.addChild((BasicModelPart)this.eyes);
        this.eyes.setTextureOffset(19, 0).addBox(-2.5f, -2.0f, -1.5f, 5.0f, 2.0f, 3.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, 0.0f, 3.0f);
        this.head.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 0).addBox(-2.0f, -2.0f, -1.0f, 4.0f, 4.0f, 10.0f, 0.0f, false);
        this.tail.setTextureOffset(23, 9).addBox(0.0f, -4.0f, 4.0f, 0.0f, 2.0f, 6.0f, 0.0f, false);
        this.dorsalFin = new AdvancedModelBox((AdvancedEntityModel)this, "dorsalFin");
        this.dorsalFin.setRotationPoint(0.0f, -2.0f, -2.0f);
        this.tail.addChild((BasicModelPart)this.dorsalFin);
        this.dorsalFin.setTextureOffset(0, 27).addBox(0.0f, -5.0f, -1.0f, 0.0f, 5.0f, 7.0f, 0.0f, false);
        this.tailFin = new AdvancedModelBox((AdvancedEntityModel)this, "tailFin");
        this.tailFin.setRotationPoint(0.0f, 0.0f, 9.0f);
        this.tail.addChild((BasicModelPart)this.tailFin);
        this.tailFin.setTextureOffset(20, 20).addBox(0.0f, -3.0f, -1.0f, 0.0f, 6.0f, 7.0f, 0.0f, false);
        this.leftFin = new AdvancedModelBox((AdvancedEntityModel)this, "leftFin");
        this.leftFin.setRotationPoint(2.0f, 2.0f, -1.0f);
        this.tail.addChild((BasicModelPart)this.leftFin);
        this.leftFin.setTextureOffset(28, 18).addBox(0.0f, 0.0f, 0.0f, 3.0f, 0.0f, 3.0f, 0.0f, false);
        this.rightFin = new AdvancedModelBox((AdvancedEntityModel)this, "rightFin");
        this.rightFin.setRotationPoint(-2.0f, 2.0f, -1.0f);
        this.tail.addChild((BasicModelPart)this.rightFin);
        this.rightFin.setTextureOffset(28, 18).addBox(-3.0f, 0.0f, 0.0f, 3.0f, 0.0f, 3.0f, 0.0f, true);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.head, (Object)this.eyes, (Object)this.tail, (Object)this.dorsalFin, (Object)this.tailFin, (Object)this.leftFin, (Object)this.rightFin);
    }

    public void setupAnim(EntityMudskipper entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float blinkAmount = Math.max(0.0f, ((float)Math.sin(ageInTicks * 0.1f) - 0.5f) * 2.0f);
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float displayProgress = entity.prevDisplayProgress + (entity.displayProgress - entity.prevDisplayProgress) * partialTick;
        float swimProgress = entity.prevSwimProgress + (entity.swimProgress - entity.prevSwimProgress) * partialTick;
        float sitProgress = entity.prevSitProgress + (entity.sitProgress - entity.prevSitProgress) * partialTick;
        float walkSpeed = 1.0f;
        float walkDegree = 0.7f;
        float swimSpeed = 0.4f;
        float swimDegree = 0.5f;
        float displaySpeed = 0.3f;
        float displayDegree = 0.4f;
        this.progressPositionPrev(this.head, entity.prevMudProgress + (entity.mudProgress - entity.prevMudProgress) * partialTick, 0.0f, -2.0f, 0.0f, 1.0f);
        this.progressPositionPrev(this.eyes, blinkAmount, 0.0f, 1.5f, 0.0f, 1.0f);
        this.progressPositionPrev(this.dorsalFin, 5.0f - displayProgress, 0.0f, 2.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, displayProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, displayProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.dorsalFin, displayProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.rightFin, displayProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leftFin, displayProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tailFin, displayProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, displayProgress, 0.0f, -0.5f, 0.0f, 5.0f);
        this.progressPositionPrev(this.rightFin, displayProgress, 0.0f, -0.5f, 0.0f, 5.0f);
        this.progressPositionPrev(this.leftFin, displayProgress, 0.0f, -0.5f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, sitProgress, 0.0f, Maths.rad(-10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, sitProgress, 0.0f, Maths.rad(30.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.tailFin, sitProgress, 0.0f, Maths.rad(10.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.leftFin, sitProgress, 0.0f, Maths.rad(-20.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.rightFin, sitProgress, 0.0f, Maths.rad(-20.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.rightFin, sitProgress, 0.5f, 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.leftFin, sitProgress, 0.0f, 0.0f, 1.0f, 5.0f);
        float walkSwingAmount = limbSwingAmount * (1.0f - 0.2f * swimProgress);
        float swimSwingAmount = limbSwingAmount * 0.2f * swimProgress;
        this.swing(this.head, swimSpeed, 0.4f * swimDegree, true, 1.0f, 0.0f, limbSwing, swimSwingAmount);
        this.swing(this.tail, swimSpeed, swimDegree, false, 0.0f, 0.0f, limbSwing, swimSwingAmount);
        this.swing(this.tailFin, swimSpeed, swimDegree, false, -1.0f, 0.0f, limbSwing, swimSwingAmount);
        this.flap(this.rightFin, swimSpeed, swimDegree, false, 2.0f, 0.1f, limbSwing, swimSwingAmount);
        this.flap(this.leftFin, swimSpeed, swimDegree, true, 2.0f, 0.1f, limbSwing, swimSwingAmount);
        this.bob(this.head, swimSpeed, swimDegree, false, limbSwing, swimSwingAmount);
        this.swing(this.head, displaySpeed, displayDegree * 0.3f, false, 0.0f, 0.0f, ageInTicks, displayProgress * 0.2f);
        this.swing(this.tail, displaySpeed, displayDegree, true, 0.0f, 0.0f, ageInTicks, displayProgress * 0.2f);
        this.swing(this.tailFin, displaySpeed, displayDegree, true, 0.0f, 0.0f, ageInTicks, displayProgress * 0.2f);
        this.flap(this.dorsalFin, displaySpeed, displayDegree, true, 0.0f, 0.0f, ageInTicks, displayProgress * 0.2f);
        float f = walkSpeed;
        float f1 = walkDegree * 0.15f;
        float headUp = 1.6f * Math.min(0.0f, (float)(Math.sin(limbSwing * f) * (double)walkSwingAmount * (double)f1 * 9.0 - (double)(walkSwingAmount * f1) * 9.0));
        this.head.rotationPointY += headUp;
        this.head.rotationPointZ += (float)(Math.sin(limbSwing * f - 1.5f) * (double)walkSwingAmount * (double)f1 * 9.0 - (double)(walkSwingAmount * f1) * 9.0);
        this.rightFin.rotationPointY += headUp;
        this.leftFin.rotationPointY += headUp;
        this.walk(this.tail, walkSpeed, walkDegree * 0.5f, true, 1.0f, 0.04f, limbSwing, walkSwingAmount);
        this.walk(this.tailFin, walkSpeed, walkDegree * 0.65f, false, 2.0f, -0.04f, limbSwing, walkSwingAmount);
        this.walk(this.head, walkSpeed, walkDegree * 0.5f, false, 0.0f, 0.04f, limbSwing, walkSwingAmount);
        this.flap(this.rightFin, walkSpeed, walkDegree, true, 3.0f, -0.3f, limbSwing, walkSwingAmount);
        this.flap(this.leftFin, walkSpeed, walkDegree, false, 3.0f, -0.3f, limbSwing, walkSwingAmount);
        this.swing(this.rightFin, walkSpeed, walkDegree, false, 2.0f, -0.3f, limbSwing, walkSwingAmount);
        this.swing(this.leftFin, walkSpeed, walkDegree, true, 2.0f, -0.3f, limbSwing, walkSwingAmount);
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.45f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.4, 0.0);
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
}
