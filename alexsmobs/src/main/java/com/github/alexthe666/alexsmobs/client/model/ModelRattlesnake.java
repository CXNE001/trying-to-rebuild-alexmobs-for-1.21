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

import com.github.alexthe666.alexsmobs.entity.EntityRattlesnake;
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

public class ModelRattlesnake
extends AdvancedEntityModel<EntityRattlesnake> {
    private final AdvancedModelBox body;
    private final AdvancedModelBox tail1;
    private final AdvancedModelBox tail2;
    private final AdvancedModelBox neck1;
    private final AdvancedModelBox neck2;
    private final AdvancedModelBox head;
    private final AdvancedModelBox tongue;
    private ModelAnimator animator;

    public ModelRattlesnake() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, 24.0f, 0.0f);
        this.body.setTextureOffset(0, 0).addBox(-2.0f, -3.0f, -4.0f, 4.0f, 3.0f, 7.0f, 0.0f, false);
        this.tail1 = new AdvancedModelBox((AdvancedEntityModel)this, "tail1");
        this.tail1.setPos(0.0f, -1.75f, 2.95f);
        this.body.addChild((BasicModelPart)this.tail1);
        this.tail1.setTextureOffset(0, 11).addBox(-1.5f, -1.25f, 0.05f, 3.0f, 3.0f, 7.0f, 0.0f, false);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this, "tail2");
        this.tail2.setPos(0.0f, 0.45f, 7.05f);
        this.tail1.addChild((BasicModelPart)this.tail2);
        this.tail2.setTextureOffset(15, 16).addBox(-1.0f, -1.0f, 0.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.neck1 = new AdvancedModelBox((AdvancedEntityModel)this, "neck1");
        this.neck1.setPos(0.0f, -1.5f, -4.0f);
        this.body.addChild((BasicModelPart)this.neck1);
        this.neck1.setTextureOffset(18, 6).addBox(-1.5f, -1.5f, -5.0f, 3.0f, 3.0f, 5.0f, 0.0f, false);
        this.neck2 = new AdvancedModelBox((AdvancedEntityModel)this, "neck2");
        this.neck2.setPos(0.0f, 0.0f, -4.9f);
        this.neck1.addChild((BasicModelPart)this.neck2);
        this.neck2.setTextureOffset(12, 25).addBox(-1.0f, -1.5f, -5.1f, 2.0f, 3.0f, 5.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, 0.0f, -5.0f);
        this.neck2.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(0, 22).addBox(-2.0f, -1.0f, -3.8f, 4.0f, 2.0f, 4.0f, 0.0f, false);
        this.tongue = new AdvancedModelBox((AdvancedEntityModel)this, "tongue");
        this.tongue.setPos(0.0f, 0.0f, -3.8f);
        this.head.addChild((BasicModelPart)this.tongue);
        this.tongue.setTextureOffset(0, 0).addBox(-0.5f, 0.0f, -2.0f, 1.0f, 0.0f, 2.0f, 0.0f, false);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityRattlesnake.ANIMATION_BITE);
        this.animator.startKeyframe(7);
        this.animator.move(this.body, 0.0f, 0.0f, 2.0f);
        this.animator.rotate(this.neck1, Maths.rad(-45.0), 0.0f, 0.0f);
        this.animator.rotate(this.neck2, Maths.rad(15.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail1, 0.0f, Maths.rad(15.0), 0.0f);
        this.animator.rotate(this.tail2, 0.0f, Maths.rad(-15.0), 0.0f);
        this.animator.rotate(this.head, Maths.rad(20.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, -2.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(3);
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

    public void setupAnim(EntityRattlesnake entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 1.0f;
        float walkDegree = 0.4f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        AdvancedModelBox[] bodyParts = new AdvancedModelBox[]{this.neck1, this.neck2, this.body, this.tail1, this.tail2};
        float curlProgress = entity.prevCurlProgress + (entity.curlProgress - entity.prevCurlProgress) * partialTick;
        this.progressPositionPrev(this.body, curlProgress, 0.0f, 0.0f, 3.0f, 5.0f);
        this.progressRotationPrev(this.body, curlProgress, 0.0f, Maths.rad(-90.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.tail1, curlProgress, Maths.rad(-10.0), Maths.rad(-70.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.neck1, curlProgress, Maths.rad(-20.0), Maths.rad(60.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.neck2, curlProgress, Maths.rad(-20.0), Maths.rad(60.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.head, curlProgress, Maths.rad(20.0), Maths.rad(-30.0), Maths.rad(10.0), 5.0f);
        this.tongue.showModel = entity.randomToungeTick > 0;
        this.walk(this.tongue, 1.0f, 0.5f, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        if (entity.isRattling()) {
            this.progressRotationPrev(this.tail2, curlProgress, Maths.rad(70.0), Maths.rad(-60.0), 0.0f, 5.0f);
            this.walk(this.tail2, 18.0f, 0.1f, false, 1.0f, 0.2f, ageInTicks, 1.0f);
            this.swing(this.tail2, 18.0f, 0.1f, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        } else {
            this.progressRotationPrev(this.tail2, curlProgress, Maths.rad(10.0), Maths.rad(-90.0), 0.0f, 5.0f);
        }
        this.faceTarget(netHeadYaw, headPitch, 2.0f, new AdvancedModelBox[]{this.neck2, this.head});
        this.chainSwing(bodyParts, walkSpeed, walkDegree, -5.0, limbSwing, limbSwingAmount);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.body);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.body, (Object)this.tail1, (Object)this.tail2, (Object)this.neck1, (Object)this.neck2, (Object)this.head, (Object)this.tongue);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
