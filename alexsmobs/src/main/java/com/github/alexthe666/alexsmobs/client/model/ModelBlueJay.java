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

import com.github.alexthe666.alexsmobs.entity.EntityBlueJay;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelBlueJay
extends AdvancedEntityModel<EntityBlueJay> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox leftLeg;
    private final AdvancedModelBox rightLeg;
    private final AdvancedModelBox tail;
    private final AdvancedModelBox leftWing;
    private final AdvancedModelBox rightWing;
    private final AdvancedModelBox head;
    private final AdvancedModelBox crest;

    public ModelBlueJay() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setRotationPoint(0.0f, -3.2f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.setRotateAngle(this.body, -0.1309f, 0.0f, 0.0f);
        this.body.setTextureOffset(0, 0).addBox(-2.0f, -4.0f, -4.0f, 4.0f, 4.0f, 7.0f, 0.0f, false);
        this.leftLeg = new AdvancedModelBox((AdvancedEntityModel)this, "leftLeg");
        this.leftLeg.setRotationPoint(1.5f, 0.0f, 1.0f);
        this.body.addChild((BasicModelPart)this.leftLeg);
        this.setRotateAngle(this.leftLeg, 0.1309f, 0.0f, 0.0f);
        this.leftLeg.setTextureOffset(26, 10).addBox(-1.5f, 0.0f, -2.0f, 3.0f, 3.0f, 2.0f, 0.0f, false);
        this.rightLeg = new AdvancedModelBox((AdvancedEntityModel)this, "rightLeg");
        this.rightLeg.setRotationPoint(-1.5f, 0.0f, 1.0f);
        this.body.addChild((BasicModelPart)this.rightLeg);
        this.setRotateAngle(this.rightLeg, 0.1309f, 0.0f, 0.0f);
        this.rightLeg.setTextureOffset(26, 10).addBox(-1.5f, 0.0f, -2.0f, 3.0f, 3.0f, 2.0f, 0.0f, true);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setRotationPoint(0.0f, -3.0f, 3.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(0, 22).addBox(-1.5f, 0.0f, 0.0f, 3.0f, 1.0f, 7.0f, 0.0f, false);
        this.leftWing = new AdvancedModelBox((AdvancedEntityModel)this, "leftWing");
        this.leftWing.setRotationPoint(2.0f, -3.0f, -2.0f);
        this.body.addChild((BasicModelPart)this.leftWing);
        this.leftWing.setTextureOffset(15, 14).addBox(0.0f, -1.0f, -1.0f, 1.0f, 3.0f, 8.0f, 0.0f, false);
        this.rightWing = new AdvancedModelBox((AdvancedEntityModel)this, "rightWing");
        this.rightWing.setRotationPoint(-2.0f, -3.0f, -2.0f);
        this.body.addChild((BasicModelPart)this.rightWing);
        this.rightWing.setTextureOffset(15, 14).addBox(-1.0f, -1.0f, -1.0f, 1.0f, 3.0f, 8.0f, 0.0f, true);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setRotationPoint(0.0f, -3.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.setRotateAngle(this.head, 0.2182f, 0.0f, 0.0f);
        this.head.setTextureOffset(23, 0).addBox(-2.5f, -3.0f, -3.0f, 5.0f, 4.0f, 5.0f, 0.0f, false);
        this.head.setTextureOffset(26, 16).addBox(-1.0f, -1.0f, -6.0f, 2.0f, 2.0f, 3.0f, 0.0f, false);
        this.crest = new AdvancedModelBox((AdvancedEntityModel)this, "crest");
        this.crest.setRotationPoint(0.0f, -2.0f, -1.0f);
        this.head.addChild((BasicModelPart)this.crest);
        this.setRotateAngle(this.crest, 0.3491f, 0.0f, 0.0f);
        this.crest.setTextureOffset(0, 12).addBox(-2.5f, -1.0f, 0.0f, 5.0f, 3.0f, 6.0f, -0.01f, false);
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setupAnim(EntityBlueJay entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float flapSpeed = 0.6f;
        float flapDegree = 0.2f;
        float walkSpeed = 0.95f;
        float walkDegree = 0.6f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float flyProgress = entity.prevFlyProgress + (entity.flyProgress - entity.prevFlyProgress) * partialTick;
        float flapAmount = flyProgress * 0.2f * (entity.prevFlapAmount + (entity.flapAmount - entity.prevFlapAmount) * partialTick);
        float crestAmount = entity.prevCrestAmount + (entity.crestAmount - entity.prevCrestAmount) * partialTick;
        float biteProgress = entity.prevAttackProgress + (entity.attackProgress - entity.prevAttackProgress) * partialTick;
        float birdPitch = entity.prevBirdPitch + (entity.birdPitch - entity.prevBirdPitch) * partialTick;
        this.progressRotationPrev(this.rightWing, flyProgress, Maths.rad(-20.0), 0.0f, Maths.rad(20.0), 5.0f);
        this.progressRotationPrev(this.leftWing, flyProgress, Maths.rad(-20.0), 0.0f, Maths.rad(-20.0), 5.0f);
        this.progressRotationPrev(this.body, flyProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.leftLeg, flyProgress, Maths.rad(40.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.rightLeg, flyProgress, Maths.rad(40.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.head, flyProgress, 0.0f, 1.0f, -1.0f, 5.0f);
        this.progressPositionPrev(this.body, flyProgress, 0.0f, 1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.rightWing, flyProgress, 0.0f, 1.0f, 1.0f, 5.0f);
        this.progressPositionPrev(this.leftWing, flyProgress, 0.0f, 1.0f, 1.0f, 5.0f);
        this.progressPositionPrev(this.rightLeg, flyProgress, 0.0f, -2.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.leftLeg, flyProgress, 0.0f, -2.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.rightWing, flapAmount, Maths.rad(-70.0), 0.0f, Maths.rad(70.0), 1.0f);
        this.progressRotationPrev(this.leftWing, flapAmount, Maths.rad(-70.0), 0.0f, Maths.rad(-70.0), 1.0f);
        this.progressRotationPrev(this.crest, crestAmount, Maths.rad(20.0), 0.0f, 0.0f, 1.0f);
        this.progressRotationPrev(this.head, biteProgress, Maths.rad(60.0), 0.0f, 0.0f, 5.0f);
        this.leftWing.setScale(1.0f + flyProgress * 0.1f, 1.0f + flyProgress * 0.1f, 1.0f + flyProgress * 0.1f);
        this.rightWing.setScale(1.0f + flyProgress * 0.1f, 1.0f + flyProgress * 0.1f, 1.0f + flyProgress * 0.1f);
        this.flap(this.leftWing, flapSpeed, flapDegree * 5.0f, true, -1.0f, 0.0f, ageInTicks, flapAmount);
        this.flap(this.rightWing, flapSpeed, flapDegree * 5.0f, false, -1.0f, 0.0f, ageInTicks, flapAmount);
        this.swing(this.leftWing, flapSpeed, flapDegree * 2.0f, false, 0.0f, 0.0f, ageInTicks, flapAmount);
        this.swing(this.rightWing, flapSpeed, flapDegree * 2.0f, false, 0.0f, 0.0f, ageInTicks, flapAmount);
        this.walk(this.leftWing, flapSpeed, flapDegree * 2.0f, false, 1.0f, 0.0f, ageInTicks, flapAmount);
        this.walk(this.rightWing, flapSpeed, flapDegree * 2.0f, false, 1.0f, 0.0f, ageInTicks, flapAmount);
        this.walk(this.tail, flapSpeed, flapDegree * 0.3f, false, -3.0f, -0.1f, ageInTicks, flapAmount);
        this.bob(this.body, flapSpeed, flapDegree * 10.0f, false, ageInTicks, flapAmount);
        this.bob(this.head, flapSpeed, flapDegree * -6.0f, false, ageInTicks, flapAmount);
        if (flyProgress <= 0.0f) {
            this.bob(this.body, walkSpeed * 1.0f, walkDegree * 1.3f, true, limbSwing, limbSwingAmount);
            this.walk(this.rightLeg, walkSpeed, walkDegree * 1.85f, false, 0.0f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.leftLeg, walkSpeed, walkDegree * 1.85f, true, 0.0f, 0.2f, limbSwing, limbSwingAmount);
            this.walk(this.head, walkSpeed, walkDegree * 0.2f, false, 2.0f, -0.01f, limbSwing, limbSwingAmount);
            this.walk(this.tail, walkSpeed, walkDegree * 0.5f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        }
        this.walk(this.tail, idleSpeed, idleDegree, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        this.walk(this.crest, idleSpeed, idleDegree, false, 2.0f, 0.0f, ageInTicks, 1.0f);
        this.bob(this.head, idleSpeed, idleDegree * 1.5f, true, ageInTicks, 1.0f);
        this.faceTarget(netHeadYaw, headPitch, 1.3f, new AdvancedModelBox[]{this.head});
        this.body.rotateAngleX += birdPitch * flyProgress * 0.2f * ((float)Math.PI / 180);
        if (entity.getFeedTime() > 0) {
            this.flap(this.head, 0.4f, 0.4f, false, 1.0f, 0.0f, ageInTicks, 1.0f);
        }
        if (entity.getSingTime() > 0) {
            this.flap(this.head, 0.4f, 0.4f, false, 1.0f, 0.0f, ageInTicks, 1.0f);
            this.walk(this.crest, 0.4f, 0.3f, false, 1.0f, 0.1f, ageInTicks, 1.0f);
            this.swing(this.head, 0.4f, 0.4f, false, 2.0f, 0.0f, ageInTicks, 1.0f);
            this.head.rotationPointZ += (float)Math.sin((double)ageInTicks * -0.4 - 1.0);
        }
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.35f;
            this.head.setScale(f, f, f);
            this.head.setShouldScaleChildren(true);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.0);
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

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.crest, (Object)this.head, (Object)this.tail, (Object)this.leftLeg, (Object)this.rightLeg, (Object)this.leftWing, (Object)this.rightWing);
    }
}
