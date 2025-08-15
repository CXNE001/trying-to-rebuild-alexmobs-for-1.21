/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.AdvancedEntityModel
 *  com.github.alexthe666.citadel.client.model.AdvancedModelBox
 *  com.github.alexthe666.citadel.client.model.basic.BasicModelPart
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityCentipedeBody;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.LivingEntity;

public class ModelCaveCentipede<T extends LivingEntity>
extends AdvancedEntityModel<T> {
    private final int type;
    private final AdvancedModelBox root;
    private AdvancedModelBox body;
    private AdvancedModelBox leftLegBodyF;
    private AdvancedModelBox leftLeg2BodyF;
    private AdvancedModelBox rightLegBodyF;
    private AdvancedModelBox rightLegBodyF2;
    private AdvancedModelBox leftLegBodyB;
    private AdvancedModelBox leftLeg2BodyB;
    private AdvancedModelBox rightLegBodyB;
    private AdvancedModelBox rightLegBodyB2;
    private AdvancedModelBox tail;
    private AdvancedModelBox leftLegTailF;
    private AdvancedModelBox leftLeg2TailF;
    private AdvancedModelBox rightLegTailF;
    private AdvancedModelBox rightLeg2TailF;
    private AdvancedModelBox leftLegTailB;
    private AdvancedModelBox leftLegTailB2;
    private AdvancedModelBox rightLegTailB;
    private AdvancedModelBox rightLegTailB2;
    private AdvancedModelBox leftTail;
    private AdvancedModelBox leftTailEnd;
    private AdvancedModelBox rightTail;
    private AdvancedModelBox rightTailEnd;
    private AdvancedModelBox head;
    private AdvancedModelBox head2;
    private AdvancedModelBox fangs;
    private AdvancedModelBox antenna_left;
    private AdvancedModelBox antenna_left_r1;
    private AdvancedModelBox antenna_right;
    private AdvancedModelBox antenna_right_r1;

    public ModelCaveCentipede(int type) {
        this.texWidth = 128;
        this.texHeight = 128;
        this.type = type;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setRotationPoint(0.0f, 24.0f, 21.0f);
        switch (type) {
            case 0: {
                this.head = new AdvancedModelBox((AdvancedEntityModel)this, "        head");
                this.head.setRotationPoint(0.0f, -7.875f, -20.625f);
                this.root.addChild((BasicModelPart)this.head);
                this.head.setTextureOffset(0, 62).addBox(-7.0f, -3.125f, -5.375f, 14.0f, 7.0f, 13.0f, 0.0f, false);
                this.head2 = new AdvancedModelBox((AdvancedEntityModel)this, "        head2");
                this.head2.setRotationPoint(0.0f, -2.125f, -6.375f);
                this.head.addChild((BasicModelPart)this.head2);
                this.head2.setTextureOffset(0, 0).addBox(-2.0f, -1.0f, -1.0f, 4.0f, 2.0f, 2.0f, 0.0f, false);
                this.antenna_left = new AdvancedModelBox((AdvancedEntityModel)this, "        antenna_left");
                this.antenna_left.setRotationPoint(1.2f, -2.125f, -5.775f);
                this.head.addChild((BasicModelPart)this.antenna_left);
                this.setRotationAngle(this.antenna_left, -0.2618f, 0.48f, -0.2618f);
                this.antenna_left_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "        antenna_left_r1");
                this.antenna_left_r1.setRotationPoint(0.5f, 0.0f, 0.0f);
                this.antenna_left.addChild((BasicModelPart)this.antenna_left_r1);
                this.setRotationAngle(this.antenna_left_r1, 0.1309f, 0.0f, 0.0873f);
                this.antenna_left_r1.setTextureOffset(55, 17).addBox(-1.0f, 0.0f, -1.0f, 23.0f, 0.0f, 10.0f, 0.0f, false);
                this.antenna_right = new AdvancedModelBox((AdvancedEntityModel)this, "        antenna_right");
                this.antenna_right.setRotationPoint(-1.2f, -2.125f, -5.775f);
                this.head.addChild((BasicModelPart)this.antenna_right);
                this.setRotationAngle(this.antenna_right, -0.2618f, -0.48f, 0.2618f);
                this.antenna_right_r1 = new AdvancedModelBox((AdvancedEntityModel)this, "        antenna_right_r1");
                this.antenna_right_r1.setRotationPoint(-0.5f, 0.0f, 0.0f);
                this.antenna_right.addChild((BasicModelPart)this.antenna_right_r1);
                this.setRotationAngle(this.antenna_right_r1, 0.1309f, 0.0f, -0.0873f);
                this.antenna_right_r1.setTextureOffset(55, 17).addBox(-22.0f, 0.0f, -1.0f, 23.0f, 0.0f, 10.0f, 0.0f, true);
                this.fangs = new AdvancedModelBox((AdvancedEntityModel)this, "        fangs");
                this.fangs.setRotationPoint(0.0f, 1.875f, -6.375f);
                this.head.addChild((BasicModelPart)this.fangs);
                this.fangs.setTextureOffset(62, 28).addBox(-7.0f, 0.0f, -5.0f, 14.0f, 0.0f, 6.0f, 0.0f, false);
                break;
            }
            case 1: {
                this.body = new AdvancedModelBox((AdvancedEntityModel)this, "        body");
                this.body.setRotationPoint(0.0f, -7.6f, -21.0f);
                this.root.addChild((BasicModelPart)this.body);
                this.body.setTextureOffset(0, 0).addBox(-8.0f, -5.4f, -8.0f, 16.0f, 10.0f, 16.0f, 0.0f, false);
                this.leftLegBodyF = new AdvancedModelBox((AdvancedEntityModel)this, "        leftLegBodyF");
                this.leftLegBodyF.setRotationPoint(7.6f, 3.6f, 5.0f);
                this.body.addChild((BasicModelPart)this.leftLegBodyF);
                this.setRotationAngle(this.leftLegBodyF, 0.0f, 0.0f, -0.5672f);
                this.leftLegBodyF.setTextureOffset(42, 62).addBox(0.0f, -2.0f, -1.0f, 10.0f, 3.0f, 2.0f, 0.0f, false);
                this.leftLeg2BodyF = new AdvancedModelBox((AdvancedEntityModel)this, "        leftLeg2BodyF");
                this.leftLeg2BodyF.setRotationPoint(9.1f, 0.5f, 0.1f);
                this.leftLegBodyF.addChild((BasicModelPart)this.leftLeg2BodyF);
                this.setRotationAngle(this.leftLeg2BodyF, 0.0f, 0.0f, 1.4835f);
                this.leftLeg2BodyF.setTextureOffset(0, 53).addBox(-5.0f, -4.0f, -0.1f, 15.0f, 6.0f, 0.0f, 0.0f, false);
                this.rightLegBodyF = new AdvancedModelBox((AdvancedEntityModel)this, "        rightLegBodyF");
                this.rightLegBodyF.setRotationPoint(-7.6f, 3.6f, 5.0f);
                this.body.addChild((BasicModelPart)this.rightLegBodyF);
                this.setRotationAngle(this.rightLegBodyF, 0.0f, 0.0f, 0.5672f);
                this.rightLegBodyF.setTextureOffset(42, 62).addBox(-10.0f, -2.0f, -1.0f, 10.0f, 3.0f, 2.0f, 0.0f, true);
                this.rightLegBodyF2 = new AdvancedModelBox((AdvancedEntityModel)this, "        rightLegBodyF2");
                this.rightLegBodyF2.setRotationPoint(-9.1f, 0.5f, 0.1f);
                this.rightLegBodyF.addChild((BasicModelPart)this.rightLegBodyF2);
                this.setRotationAngle(this.rightLegBodyF2, 0.0f, 0.0f, -1.4835f);
                this.rightLegBodyF2.setTextureOffset(0, 53).addBox(-10.0f, -4.0f, -0.1f, 15.0f, 6.0f, 0.0f, 0.0f, true);
                this.leftLegBodyB = new AdvancedModelBox((AdvancedEntityModel)this, "        leftLegBodyB");
                this.leftLegBodyB.setRotationPoint(7.6f, 3.6f, -5.0f);
                this.body.addChild((BasicModelPart)this.leftLegBodyB);
                this.setRotationAngle(this.leftLegBodyB, 0.0f, 0.0f, -0.5672f);
                this.leftLegBodyB.setTextureOffset(42, 62).addBox(0.0f, -2.0f, -1.0f, 10.0f, 3.0f, 2.0f, 0.0f, false);
                this.leftLeg2BodyB = new AdvancedModelBox((AdvancedEntityModel)this, "        leftLeg2BodyB");
                this.leftLeg2BodyB.setRotationPoint(9.1f, 0.5f, 0.1f);
                this.leftLegBodyB.addChild((BasicModelPart)this.leftLeg2BodyB);
                this.setRotationAngle(this.leftLeg2BodyB, 0.0f, 0.0f, 1.4835f);
                this.leftLeg2BodyB.setTextureOffset(0, 53).addBox(-5.0f, -4.0f, -0.1f, 15.0f, 6.0f, 0.0f, 0.0f, false);
                this.rightLegBodyB = new AdvancedModelBox((AdvancedEntityModel)this, "        rightLegBodyB");
                this.rightLegBodyB.setRotationPoint(-7.6f, 3.6f, -5.0f);
                this.body.addChild((BasicModelPart)this.rightLegBodyB);
                this.setRotationAngle(this.rightLegBodyB, 0.0f, 0.0f, 0.5672f);
                this.rightLegBodyB.setTextureOffset(42, 62).addBox(-10.0f, -2.0f, -1.0f, 10.0f, 3.0f, 2.0f, 0.0f, true);
                this.rightLegBodyB2 = new AdvancedModelBox((AdvancedEntityModel)this, "        rightLegBodyB2");
                this.rightLegBodyB2.setRotationPoint(-9.1f, 0.5f, 0.1f);
                this.rightLegBodyB.addChild((BasicModelPart)this.rightLegBodyB2);
                this.setRotationAngle(this.rightLegBodyB2, 0.0f, 0.0f, -1.4835f);
                this.rightLegBodyB2.setTextureOffset(0, 53).addBox(-10.0f, -4.0f, -0.1f, 15.0f, 6.0f, 0.0f, 0.0f, true);
                break;
            }
            case 2: {
                this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "        tail");
                this.tail.setRotationPoint(0.0f, -7.6f, -21.0f);
                this.root.addChild((BasicModelPart)this.tail);
                this.tail.setTextureOffset(0, 27).addBox(-7.0f, -4.2f, -8.0f, 14.0f, 9.0f, 16.0f, 0.0f, false);
                this.leftLegTailF = new AdvancedModelBox((AdvancedEntityModel)this, "        leftLegTailF");
                this.leftLegTailF.setRotationPoint(6.6f, 3.6f, -5.0f);
                this.tail.addChild((BasicModelPart)this.leftLegTailF);
                this.setRotationAngle(this.leftLegTailF, 0.2269f, -0.1833f, -0.5585f);
                this.leftLegTailF.setTextureOffset(42, 62).addBox(0.0f, -2.0f, -1.0f, 10.0f, 3.0f, 2.0f, 0.0f, false);
                this.leftLeg2TailF = new AdvancedModelBox((AdvancedEntityModel)this, "        leftLeg2TailF");
                this.leftLeg2TailF.setRotationPoint(9.1f, 0.5f, 0.1f);
                this.leftLegTailF.addChild((BasicModelPart)this.leftLeg2TailF);
                this.setRotationAngle(this.leftLeg2TailF, 0.0f, 0.0f, 1.4835f);
                this.leftLeg2TailF.setTextureOffset(0, 53).addBox(-5.0f, -4.0f, 0.0f, 15.0f, 6.0f, 0.0f, 0.0f, false);
                this.rightLegTailF = new AdvancedModelBox((AdvancedEntityModel)this, "        rightLegTailF");
                this.rightLegTailF.setRotationPoint(-6.6f, 3.6f, -5.0f);
                this.tail.addChild((BasicModelPart)this.rightLegTailF);
                this.setRotationAngle(this.rightLegTailF, 0.2269f, 0.1833f, 0.5585f);
                this.rightLegTailF.setTextureOffset(42, 62).addBox(-10.0f, -2.0f, -1.0f, 10.0f, 3.0f, 2.0f, 0.0f, true);
                this.rightLeg2TailF = new AdvancedModelBox((AdvancedEntityModel)this, "        rightLeg2TailF");
                this.rightLeg2TailF.setRotationPoint(-9.1f, 0.5f, 0.1f);
                this.rightLegTailF.addChild((BasicModelPart)this.rightLeg2TailF);
                this.setRotationAngle(this.rightLeg2TailF, 0.0f, 0.0f, -1.4835f);
                this.rightLeg2TailF.setTextureOffset(0, 53).addBox(-10.0f, -4.0f, 0.0f, 15.0f, 6.0f, 0.0f, 0.0f, true);
                this.leftLegTailB = new AdvancedModelBox((AdvancedEntityModel)this, "        leftLegTailB");
                this.leftLegTailB.setRotationPoint(6.6f, 3.6f, 4.0f);
                this.tail.addChild((BasicModelPart)this.leftLegTailB);
                this.setRotationAngle(this.leftLegTailB, 0.4977f, -0.6749f, -0.7314f);
                this.leftLegTailB.setTextureOffset(42, 62).addBox(0.0f, -2.0f, -1.0f, 10.0f, 3.0f, 2.0f, 0.0f, false);
                this.leftLegTailB2 = new AdvancedModelBox((AdvancedEntityModel)this, "        leftLegTailB2");
                this.leftLegTailB2.setRotationPoint(9.1f, 0.5f, 0.1f);
                this.leftLegTailB.addChild((BasicModelPart)this.leftLegTailB2);
                this.setRotationAngle(this.leftLegTailB2, 0.0f, 0.0f, 1.4835f);
                this.leftLegTailB2.setTextureOffset(0, 53).addBox(-5.0f, -4.0f, 0.0f, 15.0f, 6.0f, 0.0f, 0.0f, false);
                this.rightLegTailB = new AdvancedModelBox((AdvancedEntityModel)this, "        rightLegTailB");
                this.rightLegTailB.setRotationPoint(-6.6f, 3.6f, 4.0f);
                this.tail.addChild((BasicModelPart)this.rightLegTailB);
                this.setRotationAngle(this.rightLegTailB, 0.4977f, 0.6749f, 0.7314f);
                this.rightLegTailB.setTextureOffset(42, 62).addBox(-10.0f, -2.0f, -1.0f, 10.0f, 3.0f, 2.0f, 0.0f, true);
                this.rightLegTailB2 = new AdvancedModelBox((AdvancedEntityModel)this, "        rightLegTailB2");
                this.rightLegTailB2.setRotationPoint(-9.1f, 0.5f, 0.1f);
                this.rightLegTailB.addChild((BasicModelPart)this.rightLegTailB2);
                this.setRotationAngle(this.rightLegTailB2, 0.0f, 0.0f, -1.4835f);
                this.rightLegTailB2.setTextureOffset(0, 53).addBox(-10.0f, -4.0f, 0.0f, 15.0f, 6.0f, 0.0f, 0.0f, true);
                this.leftTail = new AdvancedModelBox((AdvancedEntityModel)this, "        leftTail");
                this.leftTail.setRotationPoint(2.5f, -0.1f, 8.0f);
                this.tail.addChild((BasicModelPart)this.leftTail);
                this.setRotationAngle(this.leftTail, 0.3054f, 0.3927f, 0.0f);
                this.leftTail.setTextureOffset(62, 35).addBox(-0.5f, -1.1f, -1.0f, 2.0f, 3.0f, 12.0f, 0.0f, false);
                this.leftTailEnd = new AdvancedModelBox((AdvancedEntityModel)this, "        leftTailEnd");
                this.leftTailEnd.setRotationPoint(0.0f, 0.0f, 11.0f);
                this.leftTail.addChild((BasicModelPart)this.leftTailEnd);
                this.setRotationAngle(this.leftTailEnd, -0.5672f, 0.0f, 0.0f);
                this.leftTailEnd.setTextureOffset(38, 30).addBox(0.5f, -1.1f, -1.0f, 0.0f, 8.0f, 23.0f, 0.0f, false);
                this.rightTail = new AdvancedModelBox((AdvancedEntityModel)this, "        rightTail");
                this.rightTail.setRotationPoint(-2.5f, -0.1f, 8.0f);
                this.tail.addChild((BasicModelPart)this.rightTail);
                this.setRotationAngle(this.rightTail, 0.3054f, -0.3927f, 0.0f);
                this.rightTail.setTextureOffset(62, 35).addBox(-1.5f, -1.1f, -1.0f, 2.0f, 3.0f, 12.0f, 0.0f, true);
                this.rightTailEnd = new AdvancedModelBox((AdvancedEntityModel)this, "        rightTailEnd");
                this.rightTailEnd.setRotationPoint(0.0f, 0.0f, 11.0f);
                this.rightTail.addChild((BasicModelPart)this.rightTailEnd);
                this.setRotationAngle(this.rightTailEnd, -0.5672f, 0.0f, 0.0f);
                this.rightTailEnd.setTextureOffset(38, 30).addBox(-0.5f, -1.1f, -1.0f, 0.0f, 8.0f, 23.0f, 0.0f, true);
            }
        }
        this.updateDefaultPose();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float partialTick = ageInTicks - (float)entity.f_19797_;
        float walkSpeed = 1.5f;
        float walkDegree = 0.85f;
        float idleSpeed = 0.25f;
        float idleDegree = 0.35f;
        if (entity.f_20919_ > 0) {
            limbSwing = ageInTicks;
            limbSwingAmount = 1.0f;
        }
        if (this.type == 0) {
            this.swing(this.antenna_left, idleSpeed, idleDegree, true, 1.0f, -0.1f, ageInTicks, 1.0f);
            this.swing(this.antenna_right, idleSpeed, idleDegree, false, 1.0f, -0.1f, ageInTicks, 1.0f);
            this.swing(this.fangs, idleSpeed, idleDegree * 0.1f, false, 0.0f, 0.0f, ageInTicks, 1.0f);
            this.fangs.rotationPointZ = -6.2f;
        } else if (this.type == 1) {
            if (entity instanceof EntityCentipedeBody) {
                float offset = (float)((double)(((EntityCentipedeBody)entity).getBodyIndex() + 1) * Math.PI * 0.5);
                double walkOffset = (double)offset * Math.PI * 0.5;
                this.swing(this.leftLegBodyF, walkSpeed, walkDegree, true, offset, 0.0f, limbSwing, limbSwingAmount);
                this.flap(this.leftLeg2BodyF, walkSpeed, walkDegree * 0.5f, true, offset, 0.1f, limbSwing, limbSwingAmount);
                this.swing(this.leftLegBodyB, walkSpeed, walkDegree, true, offset + 0.5f, 0.0f, limbSwing, limbSwingAmount);
                this.flap(this.leftLeg2BodyB, walkSpeed, walkDegree * 0.5f, true, offset + 0.5f, 0.1f, limbSwing, limbSwingAmount);
                this.swing(this.rightLegBodyF, walkSpeed, walkDegree, false, offset, 0.0f, limbSwing, limbSwingAmount);
                this.flap(this.rightLegBodyF2, walkSpeed, walkDegree * 0.5f, false, offset, 0.1f, limbSwing, limbSwingAmount);
                this.swing(this.rightLegBodyB, walkSpeed, walkDegree, false, offset + 0.5f, 0.0f, limbSwing, limbSwingAmount);
                this.flap(this.rightLegBodyB2, walkSpeed, walkDegree * 0.5f, false, offset + 0.5f, 0.1f, limbSwing, limbSwingAmount);
                this.body.rotationPointY += (float)(Math.sin((double)(limbSwing * walkSpeed) - walkOffset) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
                this.body.rotationPointY += (float)(Math.sin((double)ageInTicks * 0.1 - walkOffset) * 0.01);
            }
        } else if (entity instanceof EntityCentipedeBody) {
            float offset = (float)((double)(((EntityCentipedeBody)entity).getBodyIndex() + 1) * Math.PI * 0.5);
            double walkOffset = (double)offset * Math.PI * 0.5;
            this.swing(this.leftLegTailF, walkSpeed, walkDegree, true, offset, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.leftLeg2TailF, walkSpeed, walkDegree * 0.5f, true, offset, 0.1f, limbSwing, limbSwingAmount);
            this.swing(this.leftLegTailB, walkSpeed, walkDegree, true, offset + 0.5f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.leftLegTailB2, walkSpeed, walkDegree * 0.5f, true, offset + 0.5f, 0.1f, limbSwing, limbSwingAmount);
            this.swing(this.rightLegTailF, walkSpeed, walkDegree, false, offset, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.rightLeg2TailF, walkSpeed, walkDegree * 0.5f, false, offset, 0.1f, limbSwing, limbSwingAmount);
            this.swing(this.rightLegTailB, walkSpeed, walkDegree, false, offset + 0.5f, 0.0f, limbSwing, limbSwingAmount);
            this.flap(this.rightLegTailB2, walkSpeed, walkDegree * 0.5f, false, offset + 0.5f, 0.1f, limbSwing, limbSwingAmount);
            this.tail.rotationPointY += (float)(Math.sin((double)(limbSwing * walkSpeed) - walkOffset) * (double)limbSwingAmount * (double)walkDegree - (double)(limbSwingAmount * walkDegree));
            this.tail.rotationPointY += (float)(Math.sin((double)ageInTicks * 0.1 - walkOffset) * 0.01);
            this.swing(this.leftTail, walkSpeed, walkDegree * 0.2f, true, offset + 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.swing(this.rightTail, walkSpeed, walkDegree * 0.2f, false, offset + 1.0f, 0.0f, limbSwing, limbSwingAmount);
            this.walk(this.leftTail, idleSpeed, idleDegree, true, offset + 1.5f, -0.5f, ageInTicks, 1.0f);
            this.walk(this.rightTail, idleSpeed, idleDegree, false, offset + 1.5f, 0.5f, ageInTicks, 1.0f);
        }
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return switch (this.type) {
            case 0 -> ImmutableList.of((Object)this.root, (Object)this.head, (Object)this.head2, (Object)this.fangs, (Object)this.antenna_left, (Object)this.antenna_left_r1, (Object)this.antenna_right, (Object)this.antenna_right_r1);
            case 1 -> ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.leftLegBodyF, (Object)this.leftLeg2BodyF, (Object)this.rightLegBodyF, (Object)this.rightLegBodyF2, (Object)this.leftLegBodyB, (Object)this.leftLeg2BodyB, (Object)this.rightLegBodyB, (Object)this.rightLegBodyB2);
            case 2 -> ImmutableList.of((Object)this.root, (Object)this.tail, (Object)this.leftLegTailF, (Object)this.leftLeg2TailF, (Object)this.rightLegTailF, (Object)this.rightLeg2TailF, (Object)this.leftLegTailB, (Object)this.leftLegTailB2, (Object)this.rightLegTailB, (Object)this.rightLegTailB2, (Object)this.leftTail, (Object)this.leftTailEnd, (Object[])new AdvancedModelBox[]{this.rightTail, this.rightTailEnd});
            default -> ImmutableList.of((Object)this.root);
        };
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
