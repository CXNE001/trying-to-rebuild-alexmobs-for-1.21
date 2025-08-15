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

import com.github.alexthe666.alexsmobs.entity.EntityAlligatorSnappingTurtle;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;

public class ModelAlligatorSnappingTurtle
extends AdvancedEntityModel<EntityAlligatorSnappingTurtle> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox arm_left;
    private final AdvancedModelBox arm_right;
    private final AdvancedModelBox leg_left;
    private final AdvancedModelBox leg_right;
    private final AdvancedModelBox shell;
    private final AdvancedModelBox spikes_left;
    private final AdvancedModelBox spikes_right;
    private final AdvancedModelBox neck;
    private final AdvancedModelBox head;
    private final AdvancedModelBox head_inside;
    private final AdvancedModelBox jaw;
    private final AdvancedModelBox tail;

    public ModelAlligatorSnappingTurtle() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, 0.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 22).addBox(-7.0f, -6.0f, -8.0f, 14.0f, 6.0f, 16.0f, 0.0f, false);
        this.arm_left = new AdvancedModelBox((AdvancedEntityModel)this, "arm_left");
        this.arm_left.setPos(6.1f, -1.7f, -6.4f);
        this.body.addChild((BasicModelPart)this.arm_left);
        this.setRotationAngle(this.arm_left, 0.0f, 0.5672f, 0.0436f);
        this.arm_left.setTextureOffset(47, 45).addBox(-0.5f, -1.5f, -2.0f, 9.0f, 3.0f, 4.0f, 0.0f, false);
        this.arm_right = new AdvancedModelBox((AdvancedEntityModel)this, "arm_right");
        this.arm_right.setPos(-6.1f, -1.7f, -6.4f);
        this.body.addChild((BasicModelPart)this.arm_right);
        this.setRotationAngle(this.arm_right, 0.0f, -0.5672f, -0.0436f);
        this.arm_right.setTextureOffset(47, 45).addBox(-8.5f, -1.5f, -2.0f, 9.0f, 3.0f, 4.0f, 0.0f, true);
        this.leg_left = new AdvancedModelBox((AdvancedEntityModel)this, "leg_left");
        this.body.addChild((BasicModelPart)this.leg_left);
        this.leg_left.setPos(6.1f, -1.7f, 6.6f);
        this.setRotationAngle(this.leg_left, 0.0f, -0.6109f, 0.0436f);
        this.leg_left.setTextureOffset(45, 22).addBox(-0.5f, -1.5f, -3.0f, 8.0f, 3.0f, 5.0f, 0.0f, false);
        this.leg_right = new AdvancedModelBox((AdvancedEntityModel)this, "leg_right");
        this.leg_right.setPos(-6.1f, -1.7f, 6.6f);
        this.body.addChild((BasicModelPart)this.leg_right);
        this.setRotationAngle(this.leg_right, 0.0f, 0.6109f, -0.0436f);
        this.leg_right.setTextureOffset(45, 22).addBox(-7.5f, -1.5f, -3.0f, 8.0f, 3.0f, 5.0f, 0.0f, true);
        this.shell = new AdvancedModelBox((AdvancedEntityModel)this, "shell");
        this.shell.setPos(0.0f, -6.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.shell);
        this.shell.setTextureOffset(0, 0).addBox(-8.0f, -1.0f, -9.0f, 16.0f, 3.0f, 18.0f, 0.0f, false);
        this.spikes_left = new AdvancedModelBox((AdvancedEntityModel)this, "spikes_left");
        this.spikes_left.setPos(4.0f, -2.0f, 0.0f);
        this.shell.addChild((BasicModelPart)this.spikes_left);
        this.spikes_left.setTextureOffset(0, 45).addBox(-4.0f, -1.0f, -8.0f, 7.0f, 2.0f, 16.0f, 0.0f, false);
        this.spikes_right = new AdvancedModelBox((AdvancedEntityModel)this, "spikes_right");
        this.spikes_right.setPos(-4.0f, -2.0f, 0.0f);
        this.shell.addChild((BasicModelPart)this.spikes_right);
        this.spikes_right.setTextureOffset(0, 45).addBox(-3.0f, -1.0f, -8.0f, 7.0f, 2.0f, 16.0f, 0.0f, true);
        this.neck = new AdvancedModelBox((AdvancedEntityModel)this, "neck");
        this.neck.setPos(0.0f, -2.0f, -8.0f);
        this.body.addChild((BasicModelPart)this.neck);
        this.neck.setTextureOffset(51, 9).addBox(-3.5f, -3.0f, -3.0f, 7.0f, 5.0f, 3.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -0.75f, -3.05f);
        this.neck.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(51, 0).addBox(-3.0f, -2.25f, -4.95f, 6.0f, 3.0f, 5.0f, 0.0f, false);
        this.head_inside = new AdvancedModelBox((AdvancedEntityModel)this, "head_inside");
        this.head_inside.setPos(0.0f, 0.0f, 0.0f);
        this.head.addChild((BasicModelPart)this.head_inside);
        this.head_inside.setTextureOffset(73, 0).addBox(-3.0f, -2.25f, -4.95f, 6.0f, 3.0f, 5.0f, 0.0f, false);
        this.jaw = new AdvancedModelBox((AdvancedEntityModel)this, "jaw");
        this.jaw.setPos(0.0f, 1.15f, 0.15f);
        this.head.addChild((BasicModelPart)this.jaw);
        this.setRotationAngle(this.jaw, -0.2182f, 0.0f, 0.0f);
        this.jaw.setTextureOffset(51, 53).addBox(-2.5f, -0.5f, -5.0f, 5.0f, 2.0f, 5.0f, 0.0f, false);
        this.tail = new AdvancedModelBox((AdvancedEntityModel)this, "tail");
        this.tail.setPos(0.0f, -2.5f, 8.0f);
        this.body.addChild((BasicModelPart)this.tail);
        this.tail.setTextureOffset(31, 45).addBox(-1.5f, -1.5f, 0.0f, 3.0f, 3.0f, 9.0f, 0.0f, false);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityAlligatorSnappingTurtle entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float idleSpeed = 0.05f;
        float idleDegree = 0.25f;
        float walkSpeed = entityIn.m_20069_() ? 0.5f : 1.0f;
        float walkDegree = 0.75f;
        float partialTicks = Minecraft.m_91087_().m_91296_();
        float openProgress = entityIn.prevOpenMouthProgress + (entityIn.openMouthProgress - entityIn.prevOpenMouthProgress) * partialTicks;
        float snapProgress = entityIn.prevAttackProgress + (entityIn.attackProgress - entityIn.prevAttackProgress) * partialTicks;
        this.progressRotationPrev(this.neck, openProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.head, openProgress, Maths.rad(-35.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.jaw, openProgress, Maths.rad(65.0), 0.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.jaw, openProgress, 0.0f, -1.0f, 0.0f, 5.0f);
        this.progressPositionPrev(this.neck, snapProgress, 0.0f, 0.0f, 0.0f, 5.0f);
        this.neck.setScale(1.0f - snapProgress * 0.05f, 1.0f - snapProgress * 0.05f, 1.0f + snapProgress * 0.5f);
        this.head.rotationPointZ -= 1.45f * snapProgress;
        this.progressRotationPrev(this.head, snapProgress, Maths.rad(10.0), 0.0f, 0.0f, 5.0f);
        this.progressRotationPrev(this.jaw, snapProgress, Maths.rad(-10.0), 0.0f, 0.0f, 5.0f);
        this.swing(this.tail, idleSpeed, idleDegree * 1.15f, false, 3.0f, 0.0f, ageInTicks, 1.0f);
        this.swing(this.leg_right, walkSpeed, walkDegree, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.leg_left, walkSpeed, walkDegree, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.arm_right, walkSpeed, walkDegree, false, 0.0f, 0.1f, limbSwing, limbSwingAmount);
        this.swing(this.arm_left, walkSpeed, walkDegree, true, 0.0f, 0.1f, limbSwing, limbSwingAmount);
        this.swing(this.tail, walkSpeed * 1.35f, walkDegree * 1.15f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.neck, walkSpeed * 0.75f, walkDegree * 0.15f, false, -2.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.head, walkSpeed * 0.75f, walkDegree * 0.15f, false, -2.0f, 0.0f, limbSwing, limbSwingAmount);
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.head_inside.setScale(0.99f, 0.99f, 0.99f);
        if (this.f_102610_) {
            this.head.setScale(1.5f, 1.5f, 1.5f);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.25f, 0.25f, 0.25f);
            matrixStackIn.m_85837_(0.0, 4.5, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        } else {
            this.head.setScale(1.0f, 1.0f, 1.0f);
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.shell, (Object)this.spikes_left, (Object)this.spikes_right, (Object)this.neck, (Object)this.jaw, (Object)this.head, (Object)this.head_inside, (Object)this.leg_left, (Object)this.leg_right, (Object)this.arm_left, (Object[])new AdvancedModelBox[]{this.arm_right, this.tail});
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
