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

import com.github.alexthe666.alexsmobs.entity.EntitySoulVulture;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelSoulVulture
extends AdvancedEntityModel<EntitySoulVulture> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox leftWing;
    private final AdvancedModelBox rightWing;
    private final AdvancedModelBox heart;
    private final AdvancedModelBox leftLeg;
    private final AdvancedModelBox leftFoot;
    private final AdvancedModelBox rightLeg;
    private final AdvancedModelBox rightFoot;
    private final AdvancedModelBox neck;
    private final AdvancedModelBox head;

    public ModelSoulVulture() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -5.0f, 4.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.setRotationAngle(this.body, -0.3054f, 0.0f, 0.0f);
        this.body.setTextureOffset(0, 15).addBox(-3.0f, -6.0f, -9.0f, 6.0f, 6.0f, 10.0f, 0.0f, false);
        this.body.setTextureOffset(26, 25).addBox(-3.5f, -6.5f, -9.2f, 7.0f, 6.0f, 7.0f, 0.0f, false);
        this.leftWing = new AdvancedModelBox((AdvancedEntityModel)this, "leftWing");
        this.leftWing.setRotationPoint(3.0f, -2.0f, -7.0f);
        this.body.addChild((BasicModelPart)this.leftWing);
        this.setRotationAngle(this.leftWing, 1.1345f, -1.3265f, 0.3491f);
        this.leftWing.setTextureOffset(36, 15).addBox(-1.0f, -1.0f, -1.0f, 7.0f, 2.0f, 2.0f, 0.0f, false);
        this.leftWing.setTextureOffset(0, 0).addBox(-1.0f, 0.0f, 0.0f, 24.0f, 0.0f, 14.0f, 0.0f, false);
        this.rightWing = new AdvancedModelBox((AdvancedEntityModel)this, "rightWing");
        this.rightWing.setRotationPoint(-3.0f, -2.0f, -7.0f);
        this.body.addChild((BasicModelPart)this.rightWing);
        this.setRotationAngle(this.rightWing, 1.1345f, 1.3265f, -0.3491f);
        this.rightWing.setTextureOffset(36, 15).addBox(-6.0f, -1.0f, -1.0f, 7.0f, 2.0f, 2.0f, 0.0f, true);
        this.rightWing.setTextureOffset(0, 0).addBox(-23.0f, 0.0f, 0.0f, 24.0f, 0.0f, 14.0f, 0.0f, true);
        this.heart = new AdvancedModelBox((AdvancedEntityModel)this, "heart");
        this.heart.setRotationPoint(0.0f, 0.0f, -6.0f);
        this.body.addChild((BasicModelPart)this.heart);
        this.heart.setTextureOffset(0, 15).addBox(-1.0f, -2.0f, -1.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
        this.leftLeg = new AdvancedModelBox((AdvancedEntityModel)this, "leftWing");
        this.leftLeg.setRotationPoint(3.0f, 0.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.leftLeg);
        this.setRotationAngle(this.leftLeg, 0.7418f, 0.0f, 0.0f);
        this.leftLeg.setTextureOffset(0, 6).addBox(-1.0f, -1.0f, -4.0f, 1.0f, 4.0f, 4.0f, 0.0f, false);
        this.leftFoot = new AdvancedModelBox((AdvancedEntityModel)this, "leftFoot");
        this.leftFoot.setRotationPoint(0.0f, 3.0f, -4.0f);
        this.leftLeg.addChild((BasicModelPart)this.leftFoot);
        this.setRotationAngle(this.leftFoot, -0.4363f, 0.0f, 0.0f);
        this.leftFoot.setTextureOffset(0, 0).addBox(-2.0f, 0.0f, -2.0f, 3.0f, 2.0f, 3.0f, 0.0f, false);
        this.rightLeg = new AdvancedModelBox((AdvancedEntityModel)this, "rightLeg");
        this.rightLeg.setRotationPoint(-3.0f, 0.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.rightLeg);
        this.setRotationAngle(this.rightLeg, 0.7418f, 0.0f, 0.0f);
        this.rightLeg.setTextureOffset(0, 6).addBox(0.0f, -1.0f, -4.0f, 1.0f, 4.0f, 4.0f, 0.0f, true);
        this.rightFoot = new AdvancedModelBox((AdvancedEntityModel)this, "rightFoot");
        this.rightFoot.setRotationPoint(0.0f, 3.0f, -4.0f);
        this.rightLeg.addChild((BasicModelPart)this.rightFoot);
        this.setRotationAngle(this.rightFoot, -0.4363f, 0.0f, 0.0f);
        this.rightFoot.setTextureOffset(0, 0).addBox(-1.0f, 0.0f, -2.0f, 3.0f, 2.0f, 3.0f, 0.0f, true);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setRotationPoint(0.0f, -2.5f, -10.0f);
        this.body.addChild((BasicModelPart)this.neck);
        this.setRotationAngle(this.neck, 0.48f, 0.0f, 0.0f);
        this.neck.setTextureOffset(17, 39).addBox(-1.5f, -1.5f, -7.0f, 3.0f, 3.0f, 8.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -2.5f, -6.0f);
        this.neck.addChild((BasicModelPart)this.head);
        this.setRotationAngle(this.head, -0.1745f, 0.0f, 0.0f);
        this.head.setTextureOffset(0, 32).addBox(-2.5f, -3.0f, -5.0f, 5.0f, 4.0f, 7.0f, 0.0f, false);
        this.head.setTextureOffset(23, 15).addBox(-1.5f, -2.0f, -11.0f, 3.0f, 3.0f, 6.0f, 0.0f, false);
        this.head.setTextureOffset(32, 39).addBox(-1.5f, 1.0f, -11.0f, 3.0f, 1.0f, 2.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.neck, (Object)this.heart, (Object)this.head, (Object)this.rightFoot, (Object)this.leftFoot, (Object)this.rightWing, (Object)this.leftWing, (Object)this.leftLeg, (Object)this.rightLeg);
    }

    public void setupAnim(EntitySoulVulture entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        float flapSpeed = 0.4f;
        float flapDegree = 0.2f;
        float walkSpeed = 0.7f;
        float walkDegree = 0.4f;
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float flyProgress = entity.prevFlyProgress + (entity.flyProgress - entity.prevFlyProgress) * partialTick;
        float tackleProgress = entity.prevTackleProgress + (entity.tackleProgress - entity.prevTackleProgress) * partialTick;
        this.progressRotationPrev(this.body, flyProgress, Maths.rad(15.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.neck, flyProgress, Maths.rad(-35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, flyProgress, Maths.rad(25.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leftLeg, flyProgress, Maths.rad(55.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.rightLeg, flyProgress, Maths.rad(55.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.rightWing, flyProgress, Maths.rad(-70.0), Maths.rad(-90.0), 0.0f, 5.0f);
        this.progressRotationPrev(this.leftWing, flyProgress, Maths.rad(-70.0), Maths.rad(90.0), 0.0f, 5.0f);
        this.progressPositionPrev(this.rightWing, flyProgress, 0.0f, -2.0f, -1.0f, 5.0f);
        this.progressPositionPrev(this.leftWing, flyProgress, 0.0f, -2.0f, -1.0f, 5.0f);
        this.progressPositionPrev(this.body, flyProgress, 0.0f, 2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.leftLeg, flyProgress, 0.0f, -3.0f, 1.0f, 5.0f);
        this.progressPositionPrev(this.rightLeg, flyProgress, 0.0f, -3.0f, 1.0f, 5.0f);
        this.progressPositionPrev(this.head, flyProgress, 0.0f, 3.0f, -2.0f, 5.0f);
        this.progressRotationPrev(this.body, tackleProgress, -Maths.rad(55.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.neck, tackleProgress, Maths.rad(-35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, tackleProgress, Maths.rad(90.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.rightLeg, tackleProgress, Maths.rad(-100.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leftLeg, tackleProgress, Maths.rad(-100.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.leftLeg, tackleProgress, 0.0f, 3.0f, -2.0f, 5.0f);
        this.progressPositionPrev(this.rightLeg, tackleProgress, 0.0f, 3.0f, -2.0f, 5.0f);
        if (flyProgress > 0.0f) {
            this.walk(this.rightLeg, walkSpeed, walkDegree * 0.4f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.leftLeg, walkSpeed, walkDegree * 0.4f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.bob(this.body, flapSpeed * 0.5f, flapDegree * 8.0f, true, ageInTicks, 1.0f);
            this.walk(this.neck, flapSpeed, flapDegree * 0.5f, false, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.walk(this.head, flapSpeed, flapDegree * 1.0f, true, 0.0f, -0.1f, ageInTicks, 1.0f);
            this.flap(this.rightWing, flapSpeed, flapDegree * 5.0f, true, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.flap(this.leftWing, flapSpeed, flapDegree * 5.0f, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        } else {
            this.walk(this.body, walkSpeed, walkDegree * 0.5f, false, 2.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.neck, walkSpeed, walkDegree * 0.4f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.rightWing, walkSpeed, walkDegree * 0.4f, true, 1.0f, 0.2f, limbSwing, limbSwingAmount);
            this.swing(this.leftWing, walkSpeed, walkDegree * 0.4f, false, 1.0f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.rightLeg, walkSpeed, walkDegree * 1.85f, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.leftLeg, walkSpeed, walkDegree * 1.85f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        }
        this.walk(this.heart, idleSpeed, idleDegree, false, 2.0f, 0.0f, ageInTicks, 1.0f);
        this.bob(this.heart, idleSpeed, idleDegree * 4.0f, false, ageInTicks, 1.0f);
        this.walk(this.neck, idleSpeed, idleDegree, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.head, idleSpeed, idleDegree, false, -1.0f, 0.2f, ageInTicks, 1.0f);
        this.faceTarget(netHeadYaw, headPitch, 2.0f, new AdvancedModelBox[]{this.neck, this.head});
        float bloatScale = 1.0f + Math.min(1.0f, (float)entity.getSoulLevel() * 0.5f);
        this.heart.setScale(bloatScale, bloatScale, bloatScale);
    }

    public void m_7695_(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
