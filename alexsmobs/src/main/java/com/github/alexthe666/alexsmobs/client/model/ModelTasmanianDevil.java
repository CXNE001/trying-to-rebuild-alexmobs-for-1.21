/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.ModelAnimator
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.Minecraft
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityTasmanianDevil;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;

public class ModelTasmanianDevil
extends AdvancedEntityModel<EntityTasmanianDevil> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox armLeft;
    private final AdvancedModelBox armRight;
    private final AdvancedModelBox legLeft;
    private final AdvancedModelBox legRight;
    private final AdvancedModelBox head;
    private final AdvancedModelBox earLeft;
    private final AdvancedModelBox earRight;
    private final AdvancedModelBox tail;
    public ModelAnimator animator;

    public ModelTasmanianDevil() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this);
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this);
        this.body.setRotationPoint(0.0f, -6.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-3.5f, -3.0f, -5.0f, 7.0f, 6.0f, 11.0f, 0.0f, false);
        this.armLeft = new AdvancedModelBox((AdvancedEntityModel)this);
        this.armLeft.setRotationPoint(2.6f, 3.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.armLeft);
        this.armLeft.setTextureOffset(26, 18).addBox(-1.0f, -1.0f, -1.0f, 2.0f, 4.0f, 2.0f, 0.0f, false);
        this.armRight = new AdvancedModelBox((AdvancedEntityModel)this);
        this.armRight.setRotationPoint(-2.6f, 3.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.armRight);
        this.armRight.setTextureOffset(26, 18).addBox(-1.0f, -1.0f, -1.0f, 2.0f, 4.0f, 2.0f, 0.0f, true);
        this.legLeft = new AdvancedModelBox((AdvancedEntityModel)this);
        this.legLeft.setRotationPoint(2.6f, 3.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.legLeft);
        this.legLeft.setTextureOffset(0, 0).addBox(-1.0f, -1.0f, -1.0f, 2.0f, 4.0f, 2.0f, 0.0f, false);
        this.legRight = new AdvancedModelBox((AdvancedEntityModel)this);
        this.legRight.setRotationPoint(-2.6f, 3.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.legRight);
        this.legRight.setTextureOffset(0, 0).addBox(-1.0f, -1.0f, -1.0f, 2.0f, 4.0f, 2.0f, 0.0f, true);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this);
        this.head.setRotationPoint(0.0f, -2.0f, -6.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 18).addBox(-3.0f, -2.0f, -3.0f, 6.0f, 4.0f, 4.0f, 0.0f, false);
        this.head.setTextureOffset(26, 0).addBox(-2.0f, -2.0f, -6.0f, 4.0f, 4.0f, 3.0f, 0.0f, false);
        this.earLeft = new AdvancedModelBox((AdvancedEntityModel)this);
        this.earLeft.setRotationPoint(2.0f, -1.0f, 0.0f);
        this.head.addChild((BasicModelPart)this.earLeft);
        this.setRotationAngle(this.earLeft, 0.2182f, 0.0f, 0.3054f);
        this.earLeft.setTextureOffset(0, 27).addBox(-1.0f, -3.0f, -1.0f, 3.0f, 3.0f, 1.0f, 0.0f, false);
        this.earRight = new AdvancedModelBox((AdvancedEntityModel)this);
        this.earRight.setRotationPoint(-2.0f, -1.0f, 0.0f);
        this.head.addChild((BasicModelPart)this.earRight);
        this.setRotationAngle(this.earRight, 0.2182f, 0.0f, -0.3054f);
        this.earRight.setTextureOffset(0, 27).addBox(-2.0f, -3.0f, -1.0f, 3.0f, 3.0f, 1.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this);
        this.tail.setRotationPoint(0.0f, -2.0f, 6.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.setRotationAngle(this.tail, -0.5236f, 0.0f, 0.0f);
        this.tail.setTextureOffset(15, 21).addBox(-1.0f, 0.0f, 0.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.head, (Object)this.tail, (Object)this.earLeft, (Object)this.earRight, (Object)this.legLeft, (Object)this.legRight, (Object)this.armLeft, (Object)this.armRight);
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityTasmanianDevil.ANIMATION_ATTACK);
        this.animator.startKeyframe(3);
        this.animator.rotate(this.head, Maths.rad(10.0), 0.0f, 0.0f);
        this.animator.move(this.head, 0.0f, -1.0f, 1.5f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.rotate(this.head, Maths.rad(-15.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(3);
        this.animator.endKeyframe();
        this.animator.setAnimation(EntityTasmanianDevil.ANIMATION_HOWL);
        this.animator.startKeyframe(10);
        this.animator.rotate(this.head, Maths.rad(-45.0), Maths.rad(45.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(20);
        this.animator.rotate(this.head, Maths.rad(-45.0), Maths.rad(-45.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(5);
        this.animator.resetKeyframe(5);
    }

    public void setupAnim(EntityTasmanianDevil entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 1.0f;
        float walkDegree = 0.5f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float baskProgress0 = entity.prevBaskProgress + (entity.baskProgress - entity.prevBaskProgress) * partialTick;
        float sitProgress = entity.prevSitProgress + (entity.sitProgress - entity.prevSitProgress) * partialTick;
        float baskProgress = Math.max(0.0f, baskProgress0 - sitProgress);
        this.progressRotationPrev(this.tail, limbSwingAmount, Maths.rad(10.0), 0.0f, 0.0f, 1.0f);
        this.progressRotationPrev(this.body, sitProgress, Maths.rad(-25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.armRight, sitProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.armLeft, sitProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, sitProgress, Maths.rad(40.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, sitProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.legRight, sitProgress, Maths.rad(-40.0), Maths.rad(40.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.legLeft, sitProgress, Maths.rad(-40.0), Maths.rad(-40.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.body, sitProgress, 0.0f, 0.5f, 0.0f, 5.0f);
        this.progressPositionPrev(this.armRight, sitProgress, 0.0f, 1.0f, 1.2f, 5.0f);
        this.progressPositionPrev(this.armLeft, sitProgress, 0.0f, 1.0f, 1.2f, 5.0f);
        this.progressPositionPrev(this.legLeft, sitProgress, 1.0f, -1.5f, 0.0f, 5.0f);
        this.progressPositionPrev(this.legRight, sitProgress, -1.0f, -1.5f, 0.0f, 5.0f);
        this.progressRotationPrev(this.armRight, baskProgress, Maths.rad(-80.0), Maths.rad(40.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.armLeft, baskProgress, Maths.rad(-80.0), Maths.rad(-40.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.legRight, baskProgress, Maths.rad(80.0), Maths.rad(-20.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.legLeft, baskProgress, Maths.rad(80.0), Maths.rad(20.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, baskProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, baskProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.earRight, baskProgress, 0.0f, Maths.rad(40.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.earLeft, baskProgress, 0.0f, Maths.rad(-40.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.body, baskProgress, 0.0f, 3.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, baskProgress, 0.0f, 1.2f, 0.0f, 5.0f);
        this.progressPositionPrev(this.armRight, baskProgress, 0.0f, -1.0f, -1.2f, 5.0f);
        this.progressPositionPrev(this.armLeft, baskProgress, 0.0f, -1.0f, -1.2f, 5.0f);
        this.progressPositionPrev(this.legLeft, baskProgress, 1.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.legRight, baskProgress, -1.0f, -1.0f, 0.0f, 5.0f);
        this.walk(this.armRight, walkSpeed, walkDegree * 1.1f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.armRight, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.walk(this.armLeft, walkSpeed, walkDegree * 1.1f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.armLeft, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.walk(this.legRight, walkSpeed, walkDegree * 1.1f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.legRight, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.walk(this.legLeft, walkSpeed, walkDegree * 1.1f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.legLeft, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.swing(this.body, walkSpeed, walkDegree * 0.6f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.tail, walkSpeed, walkDegree * 0.6f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.head, walkSpeed, walkDegree * 0.6f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.body, walkSpeed, walkDegree * 0.05f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.tail, walkSpeed, walkDegree * 0.6f, true, -1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.bob(this.head, walkSpeed, walkDegree * 0.6f, false, limbSwing, limbSwingAmount);
        this.swing(this.earRight, walkSpeed, walkDegree * 0.6f, false, -1.0f, 0.3f, limbSwing, limbSwingAmount);
        this.swing(this.earLeft, walkSpeed, walkDegree * 0.6f, true, -1.0f, 0.3f, limbSwing, limbSwingAmount);
        this.swing(this.tail, idleSpeed, idleDegree * 0.9f, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.head});
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.65f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.0);
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

    public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z) {
        advancedModelBox.rotateAngleX = x;
        advancedModelBox.rotateAngleY = y;
        advancedModelBox.rotateAngleZ = z;
    }
}
