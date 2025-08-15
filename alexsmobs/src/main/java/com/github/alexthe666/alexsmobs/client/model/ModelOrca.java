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
 *  net.minecraft.util.Mth
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityOrca;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class ModelOrca
extends AdvancedEntityModel<EntityOrca> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox fintop;
    private final AdvancedModelBox fin_left;
    private final AdvancedModelBox fin_right;
    private final AdvancedModelBox tail1;
    private final AdvancedModelBox tail2;
    private final AdvancedModelBox tailend;
    private final AdvancedModelBox head;
    private final AdvancedModelBox jaw;
    private final ModelAnimator animator;

    public ModelOrca() {
        this.texWidth = 256;
        this.texHeight = 256;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -1.3333f, -0.0833f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-9.0f, -14.6667f, -16.9167f, 18.0f, 16.0f, 33.0f, 0.0f, false);
        this.fintop = new AdvancedModelBox((AdvancedEntityModel)this, "fintop");
        this.fintop.setPos(0.0f, -14.6667f, -2.4167f);
        this.body.addChild((BasicModelPart)this.fintop);
        this.setRotationAngle(this.fintop, -0.2182f, 0.0f, 0.0f);
        this.fintop.setTextureOffset(0, 0).addBox(-1.0f, -16.0f, -1.5f, 2.0f, 18.0f, 8.0f, 0.0f, false);
        this.fin_left = new AdvancedModelBox((AdvancedEntityModel)this, "fin_left");
        this.fin_left.setPos(8.5f, -0.1667f, -8.9167f);
        this.body.addChild((BasicModelPart)this.fin_left);
        this.setRotationAngle(this.fin_left, -0.6109f, 1.2217f, 0.0f);
        this.fin_left.setTextureOffset(0, 92).addBox(-7.5f, -1.5f, -3.0f, 12.0f, 2.0f, 17.0f, 0.0f, false);
        this.fin_right = new AdvancedModelBox((AdvancedEntityModel)this, "fin_right");
        this.fin_right.setPos(-8.5f, -0.1667f, -8.9167f);
        this.body.addChild((BasicModelPart)this.fin_right);
        this.setRotationAngle(this.fin_right, -0.6109f, -1.2217f, 0.0f);
        this.fin_right.setTextureOffset(0, 92).addBox(-4.5f, -1.5f, -3.0f, 12.0f, 2.0f, 17.0f, 0.0f, true);
        this.tail1 = new AdvancedModelBox((AdvancedEntityModel)this, "tail1");
        this.tail1.setPos(0.0f, -5.9167f, 15.5833f);
        this.body.addChild((BasicModelPart)this.tail1);
        this.tail1.setTextureOffset(70, 0).addBox(-7.0f, -6.75f, 0.5f, 14.0f, 13.0f, 18.0f, 0.0f, false);
        this.tail2 = new AdvancedModelBox((AdvancedEntityModel)this, "tail2");
        this.tail2.setPos(0.0f, 0.25f, 16.5f);
        this.tail1.addChild((BasicModelPart)this.tail2);
        this.tail2.setTextureOffset(43, 97).addBox(-5.0f, -4.0f, 2.0f, 10.0f, 9.0f, 16.0f, 0.0f, false);
        this.tailend = new AdvancedModelBox((AdvancedEntityModel)this, "tailend");
        this.tailend.setPos(0.0f, 0.5f, 16.5f);
        this.tail2.addChild((BasicModelPart)this.tailend);
        this.tailend.setTextureOffset(0, 50).addBox(-16.0f, -1.0f, -2.5f, 32.0f, 2.0f, 13.0f, 0.0f, false);
        this.head = new AdvancedModelBox((AdvancedEntityModel)this, "head");
        this.head.setPos(0.0f, -5.6667f, -16.9167f);
        this.body.addChild((BasicModelPart)this.head);
        this.head.setTextureOffset(71, 71).addBox(-8.0f, -8.0f, -17.0f, 16.0f, 8.0f, 17.0f, 0.0f, false);
        this.head.setTextureOffset(96, 97).addBox(-7.0f, 0.0f, -15.0f, 14.0f, 1.0f, 15.0f, 0.0f, false);
        this.jaw = new AdvancedModelBox((AdvancedEntityModel)this, "jaw");
        this.jaw.setPos(0.0f, 1.0f, 0.0f);
        this.head.addChild((BasicModelPart)this.jaw);
        this.jaw.setTextureOffset(73, 50).addBox(-7.0f, -2.0f, -15.0f, 14.0f, 1.0f, 18.0f, 0.0f, false);
        this.jaw.setTextureOffset(0, 66).addBox(-8.0f, -1.0f, -16.0f, 16.0f, 6.0f, 19.0f, 0.0f, false);
        this.updateDefaultPose();
        this.animator = ModelAnimator.create();
    }

    private static double horizontalMag(Vec3 vec) {
        return vec.f_82479_ * vec.f_82479_ + vec.f_82481_ * vec.f_82481_;
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        this.animator.update(entity);
        this.animator.setAnimation(EntityOrca.ANIMATION_BITE);
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, 0.0f, -5.0f);
        this.animator.rotate(this.head, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.rotate(this.jaw, Maths.rad(60.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.resetKeyframe(3);
        this.animator.setAnimation(EntityOrca.ANIMATION_TAILSWING);
        this.animator.startKeyframe(5);
        this.animator.move(this.body, 0.0f, -6.0f, 15.0f);
        this.animator.rotate(this.body, Maths.rad(-140.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail1, Maths.rad(-35.0), 0.0f, 0.0f);
        this.animator.rotate(this.tail2, Maths.rad(-35.0), 0.0f, 0.0f);
        this.animator.rotate(this.tailend, Maths.rad(-25.0), 0.0f, 0.0f);
        this.animator.endKeyframe();
        this.animator.setStaticKeyframe(3);
        this.animator.resetKeyframe(12);
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.35f, 0.35f, 0.35f);
            matrixStackIn.m_85837_(0.0, 2.75, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        } else {
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void setupAnim(EntityOrca entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float swimSpeed = 0.2f;
        float swimDegree = 0.4f;
        AdvancedModelBox[] tailBoxes = new AdvancedModelBox[]{this.tail1, this.tail2, this.tailend};
        this.walk(this.body, swimSpeed, swimDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.body, swimSpeed, swimDegree * 5.0f, false, limbSwing, limbSwingAmount);
        this.chainWave(tailBoxes, swimSpeed, swimDegree, 0.2f, limbSwing, limbSwingAmount);
        this.swing(this.fin_left, swimSpeed, swimDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.swing(this.fin_right, swimSpeed, swimDegree * 0.2f, true, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.fin_left, swimSpeed, swimDegree * 1.4f, true, 3.0f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.fin_right, swimSpeed, swimDegree * 1.4f, false, 3.0f, 0.0f, limbSwing, limbSwingAmount);
        this.body.rotateAngleX += headPitch * ((float)Math.PI / 180);
        this.body.rotateAngleY += netHeadYaw * ((float)Math.PI / 180);
        if (ModelOrca.horizontalMag(entityIn.m_20184_()) > 1.0E-7) {
            this.body.rotateAngleX += -0.05f + -0.05f * Mth.m_14089_((float)(ageInTicks * 0.3f));
            this.tail1.rotateAngleX += -0.1f * Mth.m_14089_((float)(ageInTicks * 0.3f));
            this.tailend.rotateAngleX += -0.2f * Mth.m_14089_((float)(ageInTicks * 0.3f));
        }
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.fintop, (Object)this.head, (Object)this.fin_left, (Object)this.fin_right, (Object)this.jaw, (Object)this.tail1, (Object)this.tail2, (Object)this.tailend);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
