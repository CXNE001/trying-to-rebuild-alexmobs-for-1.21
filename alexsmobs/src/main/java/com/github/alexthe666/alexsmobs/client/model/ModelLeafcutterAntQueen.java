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
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityLeafcutterAnt;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelLeafcutterAntQueen
extends AdvancedEntityModel<EntityLeafcutterAnt> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox legront_left;
    private final AdvancedModelBox legront_right;
    private final AdvancedModelBox legmid_left;
    private final AdvancedModelBox legmid_right;
    private final AdvancedModelBox legback_left;
    private final AdvancedModelBox legback_right;
    private final AdvancedModelBox abdomen;
    private final AdvancedModelBox head;
    private final AdvancedModelBox antenna_left;
    private final AdvancedModelBox antenna_right;
    private final AdvancedModelBox fangs;
    private ModelAnimator animator;

    public ModelLeafcutterAntQueen() {
        this.texWidth = 128;
        this.texHeight = 128;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -10.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 25).addBox(-3.5f, -5.0f, -5.0f, 7.0f, 9.0f, 11.0f, 0.0f, false);
        this.legront_left = new AdvancedModelBox((AdvancedEntityModel)this, "legront_left");
        this.legront_left.setPos(3.5f, 4.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.legront_left);
        this.setRotationAngle(this.legront_left, 0.0f, 0.3927f, 0.0f);
        this.legront_left.setTextureOffset(38, 46).addBox(0.0f, -2.0f, 0.0f, 9.0f, 8.0f, 0.0f, 0.0f, false);
        this.legront_right = new AdvancedModelBox((AdvancedEntityModel)this, "legront_right");
        this.legront_right.setPos(-3.5f, 4.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.legront_right);
        this.setRotationAngle(this.legront_right, 0.0f, -0.3927f, 0.0f);
        this.legront_right.setTextureOffset(38, 46).addBox(-9.0f, -2.0f, 0.0f, 9.0f, 8.0f, 0.0f, 0.0f, true);
        this.legmid_left = new AdvancedModelBox((AdvancedEntityModel)this, "legmid_left");
        this.legmid_left.setPos(3.5f, 4.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.legmid_left);
        this.legmid_left.setTextureOffset(19, 46).addBox(0.0f, -2.0f, 0.0f, 9.0f, 8.0f, 0.0f, 0.0f, false);
        this.legmid_right = new AdvancedModelBox((AdvancedEntityModel)this, "legmid_right");
        this.legmid_right.setPos(-3.5f, 4.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.legmid_right);
        this.legmid_right.setTextureOffset(19, 46).addBox(-9.0f, -2.0f, 0.0f, 9.0f, 8.0f, 0.0f, 0.0f, true);
        this.legback_left = new AdvancedModelBox((AdvancedEntityModel)this, "legback_left");
        this.legback_left.setPos(3.5f, 4.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.legback_left);
        this.setRotationAngle(this.legback_left, 0.0f, -0.3927f, 0.0f);
        this.legback_left.setTextureOffset(0, 46).addBox(0.0f, -2.0f, 0.0f, 9.0f, 8.0f, 0.0f, 0.0f, false);
        this.legback_right = new AdvancedModelBox((AdvancedEntityModel)this, "legback_right");
        this.legback_right.setPos(-3.5f, 4.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.legback_right);
        this.setRotationAngle(this.legback_right, 0.0f, 0.3927f, 0.0f);
        this.legback_right.setTextureOffset(0, 46).addBox(-9.0f, -2.0f, 0.0f, 9.0f, 8.0f, 0.0f, 0.0f, true);
        this.abdomen = new AdvancedModelBox((AdvancedEntityModel)this, "abdomen");
        this.abdomen.setPos(0.0f, -1.0f, 6.0f);
        this.body.addChild((BasicModelPart)this.abdomen);
        this.abdomen.setTextureOffset(0, 0).addBox(-4.5f, -2.0f, 0.0f, 9.0f, 9.0f, 15.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, 0.0f, -5.0f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(37, 25).addBox(-4.5f, -4.0f, -7.0f, 9.0f, 7.0f, 7.0f, 0.0f, false);
        this.antenna_left = new AdvancedModelBox((AdvancedEntityModel)this, "antenna_left");
        this.antenna_left.setPos(0.5f, -4.0f, -7.0f);
        this.head.addChild((BasicModelPart)this.antenna_left);
        this.setRotationAngle(this.antenna_left, 0.0f, -0.5672f, 0.3054f);
        this.antenna_left.setTextureOffset(34, 0).addBox(0.0f, 0.0f, -12.0f, 9.0f, 0.0f, 12.0f, 0.0f, false);
        this.antenna_right = new AdvancedModelBox((AdvancedEntityModel)this, "antenna_right");
        this.antenna_right.setPos(-0.5f, -4.0f, -7.0f);
        this.head.addChild((BasicModelPart)this.antenna_right);
        this.setRotationAngle(this.antenna_right, 0.0f, 0.5672f, -0.3054f);
        this.antenna_right.setTextureOffset(34, 0).addBox(-9.0f, 0.0f, -12.0f, 9.0f, 0.0f, 12.0f, 0.0f, true);
        this.fangs = new AdvancedModelBox((AdvancedEntityModel)this, "fangs");
        this.fangs.setPos(0.0f, 2.0f, -7.0f);
        this.head.addChild((BasicModelPart)this.fangs);
        this.fangs.setTextureOffset(37, 40).addBox(-4.5f, -1.0f, -3.0f, 9.0f, 2.0f, 3.0f, 0.0f, false);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.legback_left, (Object)this.legback_right, (Object)this.root, (Object)this.body, (Object)this.legront_left, (Object)this.legront_right, (Object)this.legmid_left, (Object)this.legmid_right, (Object)this.abdomen, (Object)this.head, (Object)this.antenna_left, (Object)this.antenna_right, (Object[])new AdvancedModelBox[]{this.fangs});
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityLeafcutterAnt.ANIMATION_BITE);
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, -5.0f);
        this.animator.move(this.fangs, 0.0f, 0.0f, 1.0f);
        this.animator.rotate(this.head, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.abdomen, Maths.rad(5.0), 0.0f, 0.0f);
        this.animator.rotate(this.antenna_left, Maths.rad(-25.0), Maths.rad(-25.0), 0.0f);
        this.animator.rotate(this.antenna_right, Maths.rad(-25.0), Maths.rad(25.0), 0.0f);
        this.animator.endKeyframe();
        this.animator.startKeyframe(5);
        this.animator.move(this.fangs, 0.0f, 0.0f, 0.0f);
        this.animator.move(this.body, 0.0f, 0.0f, 2.0f);
        this.animator.rotate(this.head, Maths.rad(25.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(3);
    }

    public void setupAnim(EntityLeafcutterAnt entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float idleSpeed = 0.25f;
        float idleDegree = 0.25f;
        float walkSpeed = 1.0f;
        float walkDegree = 1.0f;
        this.swing(this.antenna_left, idleSpeed, idleDegree, true, 1.0f, 0.1f, ageInTicks, 1.0f);
        this.swing(this.antenna_right, idleSpeed, idleDegree, false, 1.0f, 0.1f, ageInTicks, 1.0f);
        this.walk(this.antenna_left, idleSpeed, idleDegree * 0.25f, false, -1.0f, -0.05f, ageInTicks, 1.0f);
        this.walk(this.antenna_right, idleSpeed, idleDegree * 0.25f, false, -1.0f, -0.05f, ageInTicks, 1.0f);
        this.swing(this.legback_right, walkSpeed, walkDegree * 1.2f, false, 0.0f, 0.2f, limbSwing, limbSwingAmount);
        this.flap(this.legback_right, walkSpeed, walkDegree * 0.8f, false, -1.5f, 0.4f, limbSwing, limbSwingAmount);
        this.swing(this.legront_right, walkSpeed, walkDegree, false, 0.0f, -0.3f, limbSwing, limbSwingAmount);
        this.flap(this.legront_right, walkSpeed, walkDegree * 0.8f, false, -1.5f, 0.4f, limbSwing, limbSwingAmount);
        this.swing(this.legmid_left, walkSpeed, walkDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.legmid_left, walkSpeed, walkDegree * 0.8f, false, -1.5f, -0.4f, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed * 2.0f, walkDegree * -0.6f, false, limbSwing, limbSwingAmount);
        float offsetleft = 2.0f;
        this.swing(this.legback_left, walkSpeed, -walkDegree * 1.2f, false, offsetleft, -0.2f, limbSwing, limbSwingAmount);
        this.flap(this.legback_left, walkSpeed, walkDegree * 0.8f, false, offsetleft - 1.5f, -0.4f, limbSwing, limbSwingAmount);
        this.swing(this.legront_left, walkSpeed, -walkDegree, false, offsetleft, 0.3f, limbSwing, limbSwingAmount);
        this.flap(this.legront_left, walkSpeed, walkDegree * 0.8f, false, offsetleft + 1.5f, -0.4f, limbSwing, limbSwingAmount);
        this.swing(this.legmid_right, walkSpeed, -walkDegree, false, offsetleft, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.legmid_right, walkSpeed, walkDegree * 0.8f, false, offsetleft - 1.5f, 0.4f, limbSwing, limbSwingAmount);
        this.swing(this.abdomen, walkSpeed, walkDegree * 0.4f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
        this.faceTarget(netHeadYaw, headPitch, 1.2f, new AdvancedModelBox[]{this.head});
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.5f;
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
