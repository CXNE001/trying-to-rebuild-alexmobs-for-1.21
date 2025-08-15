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

import com.github.alexthe666.alexsmobs.entity.EntityToucan;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;

public class ModelToucan
extends AdvancedEntityModel<EntityToucan> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox tail;
    public final AdvancedModelBox left_wing;
    public final AdvancedModelBox left_wingtip;
    public final AdvancedModelBox right_wing;
    public final AdvancedModelBox right_wingtip;
    public final AdvancedModelBox left_leg;
    public final AdvancedModelBox right_leg;
    public final AdvancedModelBox head;
    public final AdvancedModelBox beak;

    public ModelToucan() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -4.3f, 0.6f);
        this.root.addChild((BasicModelPart)this.body);
        this.setRotationAngle(this.body, -0.3491f, 0.0f, 0.0f);
        this.body.setTextureOffset(0, 0).addBox(-2.0f, -3.0f, -5.0f, 4.0f, 4.0f, 7.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, -2.0f, 2.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.setRotationAngle(this.tail, 0.3927f, 0.0f, 0.0f);
        this.tail.setTextureOffset(18, 7).addBox(-1.5f, -1.0f, 0.0f, 3.0f, 2.0f, 5.0f, 0.0f, false);
        this.left_wing = new AdvancedModelBox((AdvancedEntityModel)this, "left_wing");
        this.left_wing.setRotationPoint(2.0f, -2.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.left_wing);
        this.setRotationAngle(this.left_wing, 0.1309f, 0.0f, 0.0f);
        this.left_wing.setTextureOffset(11, 16).addBox(0.0f, -1.0f, -1.0f, 1.0f, 4.0f, 6.0f, 0.0f, false);
        this.left_wingtip = new AdvancedModelBox((AdvancedEntityModel)this, "left_wingtip");
        this.left_wingtip.setRotationPoint(0.3f, 0.1f, 3.0f);
        this.left_wing.addChild((BasicModelPart)this.left_wingtip);
        this.left_wingtip.setTextureOffset(20, 21).addBox(0.0f, -1.0f, -1.0f, 0.0f, 3.0f, 6.0f, 0.0f, false);
        this.right_wing = new AdvancedModelBox((AdvancedEntityModel)this, "right_wing");
        this.right_wing.setRotationPoint(-2.0f, -2.0f, -3.0f);
        this.body.addChild((BasicModelPart)this.right_wing);
        this.setRotationAngle(this.right_wing, 0.1309f, 0.0f, 0.0f);
        this.right_wing.setTextureOffset(11, 16).addBox(-1.0f, -1.0f, -1.0f, 1.0f, 4.0f, 6.0f, 0.0f, true);
        this.right_wingtip = new AdvancedModelBox((AdvancedEntityModel)this, "right_wingtip");
        this.right_wingtip.setRotationPoint(-0.3f, 0.1f, 3.0f);
        this.right_wing.addChild((BasicModelPart)this.right_wingtip);
        this.right_wingtip.setTextureOffset(20, 21).addBox(0.0f, -1.0f, -1.0f, 0.0f, 3.0f, 6.0f, 0.0f, true);
        this.left_leg = new AdvancedModelBox((AdvancedEntityModel)this, "left_leg");
        this.left_leg.setRotationPoint(1.5f, 1.0f, 1.0f);
        this.body.addChild((BasicModelPart)this.left_leg);
        this.setRotationAngle(this.left_leg, 0.3491f, 0.0f, 0.0f);
        this.left_leg.setTextureOffset(0, 0).addBox(-0.5f, 0.0f, -2.0f, 1.0f, 3.0f, 2.0f, 0.0f, false);
        this.right_leg = new AdvancedModelBox((AdvancedEntityModel)this, "right_leg");
        this.right_leg.setRotationPoint(-1.5f, 1.0f, 1.0f);
        this.body.addChild((BasicModelPart)this.right_leg);
        this.setRotationAngle(this.right_leg, 0.3491f, 0.0f, 0.0f);
        this.right_leg.setTextureOffset(0, 0).addBox(-0.5f, 0.0f, -2.0f, 1.0f, 3.0f, 2.0f, 0.0f, true);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -2.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.setRotationAngle(this.head, 0.48f, 0.0f, 0.0f);
        this.head.setTextureOffset(0, 24).addBox(-1.5f, -4.0f, -2.0f, 3.0f, 5.0f, 3.0f, 0.0f, false);
        this.beak = new AdvancedModelBox((AdvancedEntityModel)this, "beak");
        this.beak.setRotationPoint(0.0f, -4.0f, -1.8f);
        this.head.addChild((BasicModelPart)this.beak);
        this.beak.setTextureOffset(0, 12).addBox(-1.0f, 0.0f, -6.0f, 2.0f, 3.0f, 6.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.tail, (Object)this.left_wing, (Object)this.left_wingtip, (Object)this.right_wing, (Object)this.right_wingtip, (Object)this.right_leg, (Object)this.left_leg, (Object)this.head, (Object)this.beak);
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.24f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.0);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha));
            matrixStackIn.m_85849_();
            this.head.setScale(0.9f, 0.9f, 0.9f);
        } else {
            this.head.setScale(0.9f, 0.9f, 0.9f);
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, buffer, packedLight, packedOverlay, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setupAnim(EntityToucan entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float flapSpeed = 1.0f;
        float flapDegree = 0.2f;
        float walkSpeed = 1.2f;
        float walkDegree = 0.78f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        float partialTick = Minecraft.m_91087_().m_91296_();
        float flyProgress = entity.prevFlyProgress + (entity.flyProgress - entity.prevFlyProgress) * partialTick;
        float runProgress = Math.max(0.0f, limbSwingAmount * 5.0f - flyProgress);
        float biteProgress = entity.prevPeckProgress + (entity.peckProgress - entity.prevPeckProgress) * partialTick;
        this.progressRotationPrev(this.head, biteProgress, Maths.rad(90.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, biteProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg, biteProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg, biteProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, biteProgress, 0.0f, 1.0f, -2.0f, 5.0f);
        this.progressPositionPrev(this.left_leg, biteProgress, 0.0f, -0.15f, 0.0f, 5.0f);
        this.progressPositionPrev(this.right_leg, biteProgress, 0.0f, -0.15f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, runProgress, Maths.rad(-20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, runProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg, runProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg, runProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.body, flyProgress, Maths.rad(20.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, flyProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.left_leg, flyProgress, Maths.rad(55.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.tail, flyProgress, Maths.rad(-15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_leg, flyProgress, Maths.rad(55.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_wing, flyProgress, Maths.rad(-90.0), 0.0f, Maths.rad(90.0), 5.0f);
        this.progressRotationPrev(this.left_wing, flyProgress, Maths.rad(-90.0), 0.0f, Maths.rad(-90.0), 5.0f);
        this.progressPositionPrev(this.right_wing, flyProgress, 0.0f, 0.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.left_wing, flyProgress, 0.0f, 0.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.right_wingtip, flyProgress, 0.0f, 0.0f, 2.0f, 5.0f);
        this.progressPositionPrev(this.left_wingtip, flyProgress, 0.0f, 0.0f, 2.0f, 5.0f);
        this.progressRotationPrev(this.left_wingtip, flyProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.right_wingtip, flyProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        if (flyProgress > 0.0f) {
            this.flap(this.right_wing, flapSpeed, flapDegree * 5.0f, true, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.flap(this.left_wing, flapSpeed, flapDegree * 5.0f, false, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.swing(this.right_wingtip, flapSpeed, flapDegree * 0.5f, false, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.swing(this.left_wingtip, flapSpeed, flapDegree * 0.5f, true, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.bob(this.body, flapSpeed * 0.5f, flapDegree * 12.0f, true, ageInTicks, 1.0f);
            this.walk(this.head, flapSpeed, flapDegree * 0.2f, true, 2.0f, -0.1f, ageInTicks, 1.0f);
        } else {
            this.walk(this.head, idleSpeed * 0.7f, idleDegree, false, 1.0f, 0.05f, ageInTicks, 1.0f);
            this.walk(this.tail, idleSpeed * 0.7f, idleDegree, false, -1.0f, 0.05f, ageInTicks, 1.0f);
            this.bob(this.body, walkSpeed * 1.0f, walkDegree * 1.3f, true, limbSwing, limbSwingAmount);
            this.walk(this.right_leg, walkSpeed, walkDegree * 1.85f, false, 0.0f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.left_leg, walkSpeed, walkDegree * 1.85f, true, 0.0f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.head, walkSpeed, walkDegree * 0.4f, false, 2.0f, -0.01f, limbSwing, limbSwingAmount);
            this.swing(this.tail, walkSpeed, walkDegree * 0.5f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        }
        this.faceTarget(netHeadYaw, headPitch, 1.0f, new AdvancedModelBox[]{this.head});
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
