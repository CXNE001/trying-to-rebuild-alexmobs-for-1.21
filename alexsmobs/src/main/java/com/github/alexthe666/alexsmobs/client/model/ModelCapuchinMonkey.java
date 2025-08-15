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
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCapuchinMonkey;
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
import net.minecraft.world.entity.LivingEntity;

public class ModelCapuchinMonkey
extends AdvancedEntityModel<EntityCapuchinMonkey> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox arm_left;
    public final AdvancedModelBox arm_right;
    public final AdvancedModelBox leg_left;
    public final AdvancedModelBox leg_right;
    public final AdvancedModelBox tail1;
    public final AdvancedModelBox tail2;
    public final AdvancedModelBox tail2_r1;
    public final AdvancedModelBox head;
    public final AdvancedModelBox hair;
    public final AdvancedModelBox snout;
    public ModelAnimator animator;

    public ModelCapuchinMonkey() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -9.9f, 3.9f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-3.0f, -2.1f, -9.9f, 6.0f, 5.0f, 11.0f, 0.0f, false);
        this.arm_left = new AdvancedModelBox((AdvancedEntityModel)this, "arm_left");
        this.arm_left.setPos(2.1f, 2.4f, -8.8f);
        this.body.addChild((BasicModelPart)this.arm_left);
        this.arm_left.setTextureOffset(28, 17).addBox(-1.0f, -1.5f, -1.0f, 2.0f, 9.0f, 2.0f, 0.0f, false);
        this.arm_right = new AdvancedModelBox((AdvancedEntityModel)this, "arm_right");
        this.arm_right.setPos(-2.1f, 2.4f, -8.8f);
        this.body.addChild((BasicModelPart)this.arm_right);
        this.arm_right.setTextureOffset(28, 17).addBox(-1.0f, -1.5f, -1.0f, 2.0f, 9.0f, 2.0f, 0.0f, true);
        this.leg_left = new AdvancedModelBox((AdvancedEntityModel)this, "leg_left");
        this.leg_left.setPos(2.0f, 2.9f, 0.1f);
        this.body.addChild((BasicModelPart)this.leg_left);
        this.leg_left.setTextureOffset(0, 0).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 7.0f, 2.0f, 0.0f, false);
        this.leg_right = new AdvancedModelBox((AdvancedEntityModel)this, "leg_right");
        this.leg_right.setPos(-2.0f, 2.9f, 0.1f);
        this.body.addChild((BasicModelPart)this.leg_right);
        this.leg_right.setTextureOffset(0, 0).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 7.0f, 2.0f, 0.0f, true);
        this.tail1 = new AdvancedModelBox((AdvancedEntityModel)this, "tail1");
        this.tail1.setPos(0.0f, -1.1f, 0.5f);
        this.body.addChild((BasicModelPart)this.tail1);
        this.setRotationAngle(this.tail1, 0.6981f, 0.0f, 0.0f);
        this.tail1.setTextureOffset(15, 22).addBox(-1.0f, -1.0f, 0.0f, 2.0f, 2.0f, 8.0f, 0.0f, false);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this, "tail2");
        this.tail2.setPos(0.0f, -0.2f, 7.7f);
        this.tail1.addChild((BasicModelPart)this.tail2);
        this.setRotationAngle(this.tail2, 0.6981f, 0.0f, 0.0f);
        this.tail2_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "tail2_r1");
        this.tail2_r1.setPos(0.0f, -0.1875f, -0.0791f);
        this.tail2.addChild((BasicModelPart)this.tail2_r1);
        this.setRotationAngle(this.tail2_r1, -1.4399f, 0.0f, 0.0f);
        this.tail2_r1.setTextureOffset(0, 17).addBox(-1.0f, -0.8125f, -0.2209f, 2.0f, 3.0f, 9.0f, -0.1f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -1.1f, -9.9f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(24, 0).addBox(-2.0f, -3.0f, -3.0f, 4.0f, 4.0f, 3.0f, 0.0f, false);
        this.hair = new AdvancedModelBox((AdvancedEntityModel)this, "hair");
        this.hair.setPos(0.0f, -1.0f, -2.0f);
        this.head.addChild((BasicModelPart)this.hair);
        this.hair.setTextureOffset(6, 38).addBox(-3.0f, -2.0f, 0.0f, 6.0f, 4.0f, 0.0f, 0.0f, false);
        this.snout = new AdvancedModelBox((AdvancedEntityModel)this, "snout");
        this.snout.setPos(0.0f, 1.0f, -3.0f);
        this.head.addChild((BasicModelPart)this.snout);
        this.snout.setTextureOffset(0, 17).addBox(-1.0f, -2.0f, -1.0f, 2.0f, 2.0f, 1.0f, 0.0f, false);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityCapuchinMonkey.ANIMATION_THROW);
        this.animator.startKeyframe(3);
        this.animator.move(this.body, 0.0f, 1.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(-35.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(30.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail1, Maths.rad(45.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(35.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(35.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-70.0), 0.0f, Maths.rad(25.0));
        this.animator.rotate(this.arm_left, Maths.rad(-70.0), 0.0f, Maths.rad(-25.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.move(this.body, 0.0f, 1.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(-45.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(35.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail1, Maths.rad(45.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(45.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(45.0), 0.0f, 0.0f);
        this.animator.move(this.arm_right, -1.0f, -2.0f, 1.0f);
        this.animator.move(this.arm_left, 1.0f, -2.0f, 1.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-160.0), 0.0f, Maths.rad(5.0));
        this.animator.rotate(this.arm_left, Maths.rad(-160.0), 0.0f, Maths.rad(-5.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(2);
        this.animator.move(this.body, 0.0f, 1.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail1, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.move(this.arm_right, -1.0f, -2.0f, -1.0f);
        this.animator.move(this.arm_left, 1.0f, -2.0f, -1.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-50.0), 0.0f, Maths.rad(5.0));
        this.animator.rotate(this.arm_left, Maths.rad(-50.0), 0.0f, Maths.rad(-5.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(4);
        this.animator.setAnimation(EntityCapuchinMonkey.ANIMATION_SCRATCH);
        this.animator.startKeyframe(3);
        this.animator.move(this.body, 0.0f, 1.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail1, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-50.0), 0.0f, Maths.rad(15.0));
        this.animator.rotate(this.arm_left, Maths.rad(10.0), 0.0f, Maths.rad(-15.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.move(this.body, 0.0f, 1.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail1, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(10.0), 0.0f, Maths.rad(15.0));
        this.animator.rotate(this.arm_left, Maths.rad(-50.0), 0.0f, Maths.rad(-15.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.move(this.body, 0.0f, 1.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail1, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-50.0), 0.0f, Maths.rad(15.0));
        this.animator.rotate(this.arm_left, Maths.rad(10.0), 0.0f, Maths.rad(-15.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.move(this.body, 0.0f, 1.0f, 0.0f);
        this.animator.rotate(this.body, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.head, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail1, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_left, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.leg_right, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(10.0), 0.0f, Maths.rad(15.0));
        this.animator.rotate(this.arm_left, Maths.rad(-50.0), 0.0f, Maths.rad(-15.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(5);
        this.animator.setAnimation(EntityCapuchinMonkey.ANIMATION_HEADTILT);
        this.animator.startKeyframe(5);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(25.0));
        this.animator.move(this.head, 0.0f, 1.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(2);
        this.animator.startKeyframe(5);
        this.animator.move(this.head, 0.0f, 1.0f, 0.0f);
        this.animator.rotate(this.head, 0.0f, 0.0f, Maths.rad(-25.0));
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(2);
        this.animator.resetKeyframe(5);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.leg_left, (Object)this.leg_right, (Object)this.tail1, (Object)this.tail2, (Object)this.tail2_r1, (Object)this.arm_left, (Object)this.arm_right, (Object)this.head, (Object)this.snout, (Object)this.hair, (Object[])new AdvancedModelBox[0]);
    }

    public void setupAnim(EntityCapuchinMonkey entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float idleSpeed = 0.2f;
        float idleDegree = 0.4f;
        float walkSpeed = 0.8f;
        float walkDegree = 0.7f;
        float stillProgress = 5.0f * (1.0f - limbSwingAmount);
        float partialTick = Minecraft.m_91087_().m_91296_();
        float sitProgress = entity.m_20159_() ? 0.0f : entity.prevSitProgress + (entity.sitProgress - entity.prevSitProgress) * partialTick;
        float rideProgress = entity.m_20159_() && entity.m_20202_() instanceof LivingEntity && entity.m_21830_((LivingEntity)entity.m_20202_()) ? 10.0f : 0.0f;
        this.progressPositionPrev(this.body, rideProgress, 3.0f, 12.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.body, rideProgress, 0.0f, Maths.rad(90.0), 0.0f, 10.0f);
        this.progressRotationPrev(this.head, rideProgress, 0.0f, Maths.rad(-90.0), 0.0f, 10.0f);
        this.progressRotationPrev(this.leg_right, rideProgress, 0.0f, 0.0f, Maths.rad(-15.0), 10.0f);
        this.progressRotationPrev(this.arm_right, rideProgress, 0.0f, 0.0f, Maths.rad(-15.0), 10.0f);
        this.progressRotationPrev(this.tail1, stillProgress, Maths.rad(-55.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail2, stillProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, sitProgress, 0.0f, 6.0f, 0.0f, 10.0f);
        this.progressRotationPrev(this.tail1, sitProgress, Maths.rad(-15.0), Maths.rad(15.0), Maths.rad(90.0), 10.0f);
        this.progressRotationPrev(this.arm_left, sitProgress, Maths.rad(-85.0), Maths.rad(-15.0), 0.0f, 10.0f);
        this.progressRotationPrev(this.arm_right, sitProgress, Maths.rad(-85.0), Maths.rad(15.0), 0.0f, 10.0f);
        this.progressRotationPrev(this.leg_left, sitProgress, Maths.rad(85.0), Maths.rad(-15.0), 0.0f, 10.0f);
        this.progressRotationPrev(this.leg_right, sitProgress, Maths.rad(85.0), Maths.rad(-15.0), 0.0f, 10.0f);
        this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.head});
        this.swing(this.tail1, idleSpeed, idleDegree * 0.2f, false, 0.3f, 0.0f, ageInTicks, 1.0f);
        this.swing(this.tail2, idleSpeed, idleDegree * 0.2f, false, 0.3f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.tail1, walkSpeed, walkDegree * 0.2f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.tail2, walkSpeed, walkDegree * 0.2f, false, 1.3f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.tail2_r1, walkSpeed, walkDegree * 0.2f, false, 1.5f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.body, walkSpeed, walkDegree * 0.2f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed, walkDegree * 2.0f, false, limbSwing, limbSwingAmount);
        this.walk(this.arm_left, walkSpeed, walkDegree, false, 1.4f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.arm_right, walkSpeed, walkDegree, false, 1.4f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.leg_left, walkSpeed, walkDegree, false, -2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.leg_right, walkSpeed, walkDegree, false, -2.0f, 0.0f, limbSwing, limbSwingAmount);
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.75f;
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

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
