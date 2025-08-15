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
 *  net.minecraft.world.phys.Vec3
 *  org.joml.Matrix4fc
 *  org.joml.Vector4f
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityRaccoon;
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
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4fc;
import org.joml.Vector4f;

public class ModelRaccoon
extends AdvancedEntityModel<EntityRaccoon> {
    public AdvancedModelBox root;
    public AdvancedModelBox body;
    public AdvancedModelBox tail;
    public AdvancedModelBox arm_left;
    public AdvancedModelBox arm_right;
    public AdvancedModelBox leg_left;
    public AdvancedModelBox leg_right;
    public AdvancedModelBox head;
    public AdvancedModelBox ear_left;
    public AdvancedModelBox ear_right;
    public AdvancedModelBox snout;
    public ModelAnimator animator;

    public ModelRaccoon() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -11.0f, 0.5f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-5.5f, -4.0f, -7.5f, 11.0f, 8.0f, 15.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.5f, -1.0f, 7.5f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 24).addBox(-3.0f, -2.0f, 0.0f, 5.0f, 5.0f, 19.0f, 0.0f, false);
        this.arm_left = new AdvancedModelBox((AdvancedEntityModel)this, "arm_left");
        this.arm_left.setPos(3.0f, 4.0f, -5.5f);
        this.body.addChild((BasicModelPart)this.arm_left);
        this.arm_left.setTextureOffset(0, 24).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 7.0f, 2.0f, 0.0f, false);
        this.arm_right = new AdvancedModelBox((AdvancedEntityModel)this, "arm_right");
        this.arm_right.setPos(-3.0f, 4.0f, -5.5f);
        this.body.addChild((BasicModelPart)this.arm_right);
        this.arm_right.setTextureOffset(0, 24).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 7.0f, 2.0f, 0.0f, true);
        this.leg_left = new AdvancedModelBox((AdvancedEntityModel)this, "leg_left");
        this.leg_left.setPos(3.0f, 4.0f, 6.5f);
        this.body.addChild((BasicModelPart)this.leg_left);
        this.leg_left.setTextureOffset(9, 32).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 7.0f, 2.0f, 0.0f, false);
        this.leg_right = new AdvancedModelBox((AdvancedEntityModel)this, "leg_right");
        this.leg_right.setPos(-3.0f, 4.0f, 6.5f);
        this.body.addChild((BasicModelPart)this.leg_right);
        this.leg_right.setTextureOffset(9, 32).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 7.0f, 2.0f, 0.0f, true);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, 0.5f, -8.5f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(30, 30).addBox(-4.5f, -4.0f, -4.0f, 9.0f, 7.0f, 5.0f, 0.0f, false);
        this.ear_left = new AdvancedModelBox((AdvancedEntityModel)this, "ear_left");
        this.ear_left.setPos(3.5f, -4.0f, -2.0f);
        this.head.addChild((BasicModelPart)this.ear_left);
        this.ear_left.setTextureOffset(9, 24).addBox(-1.0f, -2.0f, 0.0f, 2.0f, 2.0f, 1.0f, 0.0f, false);
        this.ear_right = new AdvancedModelBox((AdvancedEntityModel)this, "ear_right");
        this.ear_right.setPos(-3.5f, -4.0f, -2.0f);
        this.head.addChild((BasicModelPart)this.ear_right);
        this.ear_right.setTextureOffset(9, 24).addBox(-1.0f, -2.0f, 0.0f, 2.0f, 2.0f, 1.0f, 0.0f, true);
        this.snout = new AdvancedModelBox((AdvancedEntityModel)this, "snout");
        this.snout.setPos(0.0f, 1.5f, -5.0f);
        this.head.addChild((BasicModelPart)this.snout);
        this.snout.setTextureOffset(0, 0).addBox(-2.0f, -1.5f, -2.0f, 4.0f, 3.0f, 3.0f, 0.0f, false);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.leg_left, (Object)this.leg_right, (Object)this.arm_left, (Object)this.arm_right, (Object)this.tail, (Object)this.head, (Object)this.ear_left, (Object)this.ear_right, (Object)this.snout);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityRaccoon.ANIMATION_ATTACK);
        this.animator.startKeyframe(3);
        this.animator.rotate(this.head, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-120.0), 0.0f, Maths.rad(30.0));
        this.animator.rotate(this.arm_left, Maths.rad(-40.0), 0.0f, Maths.rad(-20.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.head, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-40.0), 0.0f, Maths.rad(20.0));
        this.animator.rotate(this.arm_left, Maths.rad(-120.0), 0.0f, Maths.rad(-30.0));
        this.animator.endKeyframe();
        this.animator.startKeyframe(3);
        this.animator.rotate(this.head, Maths.rad(-20.0), 0.0f, 0.0f);
        this.animator.rotate(this.arm_right, Maths.rad(-120.0), 0.0f, Maths.rad(30.0));
        this.animator.rotate(this.arm_left, Maths.rad(-40.0), 0.0f, Maths.rad(-20.0));
        this.animator.endKeyframe();
        this.animator.resetKeyframe(3);
    }

    public void setupAnim(EntityRaccoon entityRaccoon, float limbSwing, float limbSwingAmount, float ageInTicks, float v3, float v4) {
        this.animate(entityRaccoon, limbSwing, limbSwingAmount, ageInTicks, v3, v4);
        float partialTicks = Minecraft.m_91087_().m_91296_();
        float normalProgress = 5.0f;
        float walkSpeed = 1.0f;
        float walkDegree = 0.8f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.2f;
        float runProgress = 5.0f * limbSwingAmount;
        float begProgress = entityRaccoon.prevBegProgress + (entityRaccoon.begProgress - entityRaccoon.prevBegProgress) * partialTicks;
        float standProgress0 = entityRaccoon.prevStandProgress + (entityRaccoon.standProgress - entityRaccoon.prevStandProgress) * partialTicks;
        float sitProgress = entityRaccoon.prevSitProgress + (entityRaccoon.sitProgress - entityRaccoon.prevSitProgress) * partialTicks;
        float standProgress = Math.max(Math.max(begProgress, standProgress0) - sitProgress, 0.0f);
        float washProgress = entityRaccoon.prevWashProgress + (entityRaccoon.washProgress - entityRaccoon.prevWashProgress) * partialTicks;
        this.progressRotationPrev(this.body, standProgress, Maths.rad(-70.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_left, standProgress, Maths.rad(70.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_right, standProgress, Maths.rad(70.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_left, standProgress, Maths.rad(70.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_right, standProgress, Maths.rad(70.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, standProgress, Maths.rad(70.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, sitProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, sitProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, sitProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_left, sitProgress, Maths.rad(-75.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_right, sitProgress, Maths.rad(-75.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_left, sitProgress, Maths.rad(-80.0), Maths.rad(-20.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_right, sitProgress, Maths.rad(-80.0), Maths.rad(20.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.body, sitProgress, 0.0f, 4.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.leg_left, sitProgress, 1.5f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.leg_right, sitProgress, -1.5f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.arm_left, sitProgress, 0.0f, 2.5f, 1.0f, 5.0f);
        this.progressPositionPrev(this.arm_right, sitProgress, 0.0f, 2.5f, 1.0f, 5.0f);
        this.progressPositionPrev(this.head, standProgress, 0.0f, -2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, standProgress, 0.0f, -3.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.leg_left, standProgress, 0.0f, -2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.leg_right, standProgress, 0.0f, -2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.arm_left, standProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.arm_right, standProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, standProgress, Maths.rad(80.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, normalProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, normalProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_left, normalProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_right, normalProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_left, normalProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_right, normalProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.body, normalProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.arm_left, normalProgress, 0.0f, -1.9f, 0.0f, 5.0f);
        this.progressPositionPrev(this.arm_right, normalProgress, 0.0f, -1.9f, 0.0f, 5.0f);
        this.progressPositionPrev(this.leg_left, normalProgress, 0.0f, 0.0f, -1.0f, 5.0f);
        this.progressPositionPrev(this.leg_right, normalProgress, 0.0f, 0.0f, -1.0f, 5.0f);
        this.progressRotationPrev(this.tail, 5.0f - runProgress, Maths.rad(-35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, runProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.ear_left, Math.max(runProgress, begProgress), Maths.rad(-20.0), 0.0f, Maths.rad(20.0), 5.0f);
        this.progressRotationPrev(this.ear_right, Math.max(runProgress, begProgress), Maths.rad(-20.0), 0.0f, Maths.rad(-20.0), 5.0f);
        this.progressRotationPrev(this.arm_right, begProgress, Maths.rad(-25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_left, begProgress, Maths.rad(-25.0), 0.0f, 0.0f, 5.0f);
        if (begProgress > 0.0f) {
            this.walk(this.head, 0.7f, 0.2f, false, 2.0f, -0.2f, ageInTicks, begProgress * 0.2f);
            this.walk(this.arm_right, 0.7f, 1.2f, false, 0.0f, -1.0f, ageInTicks, begProgress * 0.2f);
            this.flap(this.arm_right, 0.7f, 0.25f, false, -1.0f, 0.2f, ageInTicks, begProgress * 0.2f);
            this.walk(this.arm_left, 0.7f, 1.2f, false, 0.0f, -1.0f, ageInTicks, begProgress * 0.2f);
            this.flap(this.arm_left, 0.7f, 0.25f, true, -1.0f, 0.2f, ageInTicks, begProgress * 0.2f);
        }
        this.progressRotationPrev(this.body, washProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_left, washProgress, Maths.rad(-90.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.arm_right, washProgress, Maths.rad(-90.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_left, washProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leg_right, washProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, washProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, washProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, washProgress, 0.0f, -3.0f, 0.4f, 5.0f);
        this.progressPositionPrev(this.body, washProgress, 0.0f, 1.5f, -10.0f, 5.0f);
        this.progressPositionPrev(this.arm_left, washProgress, 0.0f, 1.0f, -1.4f, 5.0f);
        this.progressPositionPrev(this.arm_right, washProgress, 0.0f, 1.0f, -1.4f, 5.0f);
        if (washProgress > 0.0f) {
            this.arm_left.rotationPointY -= (float)(-Math.abs(Math.sin(ageInTicks * 0.5f) * (double)washProgress * 0.2 * 1.0));
            this.arm_left.rotationPointZ -= (float)(-Math.abs(Math.sin(ageInTicks * 0.25f) * (double)washProgress * 0.2 * 3.0));
            this.arm_right.rotationPointY -= (float)(-Math.abs(Math.sin(ageInTicks * 0.5f) * (double)washProgress * 0.2 * 1.0));
            this.arm_right.rotationPointZ -= (float)(-Math.abs(Math.cos(ageInTicks * 0.25f) * (double)washProgress * 0.2 * 3.0));
            this.swing(this.arm_right, 0.5f, 0.25f, false, 2.0f, -0.1f, ageInTicks, washProgress * 0.2f);
            this.swing(this.arm_left, 0.5f, 0.25f, true, 2.0f, -0.1f, ageInTicks, washProgress * 0.2f);
            float bodyFlap = (float)(Math.sin(ageInTicks * 0.5f) * (double)washProgress * 0.2 * (double)0.15f);
            this.body.rotateAngleZ += bodyFlap;
            this.tail.rotateAngleY += bodyFlap;
            this.head.rotateAngleZ -= bodyFlap;
            this.leg_left.rotateAngleZ -= bodyFlap;
            this.leg_right.rotateAngleZ -= bodyFlap;
        } else {
            this.faceTarget(v3, v4, 1.3f, new AdvancedModelBox[]{this.head});
        }
        if (standProgress <= 0.0f) {
            this.walk(this.arm_right, walkSpeed, walkDegree * 1.1f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.arm_left, walkSpeed, walkDegree * 1.1f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        }
        this.swing(this.tail, idleSpeed, idleDegree, false, 2.0f, 0.0f, ageInTicks, 1.0f);
        this.swing(this.body, walkSpeed, walkDegree * 0.3f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.tail, walkSpeed, walkDegree * 1.0f, false, 4.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.leg_right, walkSpeed, walkDegree * 1.1f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.walk(this.leg_left, walkSpeed, walkDegree * 1.1f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
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

    public Vec3 getRidingPosition(Vec3 offsetIn) {
        PoseStack armStack = new PoseStack();
        armStack.m_85836_();
        this.root.translateAndRotate(armStack);
        this.body.translateAndRotate(armStack);
        Vector4f armOffsetVec = new Vector4f((float)offsetIn.f_82479_, (float)offsetIn.f_82480_, (float)offsetIn.f_82481_, 1.0f);
        armOffsetVec.mul((Matrix4fc)armStack.m_85850_().m_252922_());
        Vec3 vec3 = new Vec3((double)armOffsetVec.x(), (double)armOffsetVec.y(), (double)armOffsetVec.z());
        armStack.m_85849_();
        return vec3;
    }
}
