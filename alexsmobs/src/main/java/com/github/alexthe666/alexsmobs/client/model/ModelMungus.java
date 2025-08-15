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
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityMungus;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class ModelMungus
extends AdvancedEntityModel<EntityMungus> {
    public final AdvancedModelBox root;
    public final AdvancedModelBox body;
    public final AdvancedModelBox hair;
    public final AdvancedModelBox eye;
    public final AdvancedModelBox leg_left;
    public final AdvancedModelBox leg_right;
    public final AdvancedModelBox nose;
    public final AdvancedModelBox sack;

    public ModelMungus(float f) {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox((AdvancedEntityModel)this, "root");
        this.root.setPos(0.0f, 24.0f, 0.0f);
        this.body = new AdvancedModelBox((AdvancedEntityModel)this, "body");
        this.body.setPos(0.0f, -7.0f, 0.0f);
        this.root.addChild((BasicModelPart)this.body);
        this.body.setTextureOffset(0, 0).addBox(-6.0f, -16.0f, -4.0f, 12.0f, 16.0f, 8.0f, f, false);
        this.hair = new AdvancedModelBox((AdvancedEntityModel)this, "hair");
        this.hair.setPos(0.0f, -16.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.hair);
        this.hair.setTextureOffset(33, 0).addBox(-5.0f, -5.0f, 0.0f, 10.0f, 5.0f, 0.0f, f, false);
        this.eye = new AdvancedModelBox((AdvancedEntityModel)this, "eye");
        this.eye.setPos(0.0f, -11.0f, -4.1f);
        this.body.addChild((BasicModelPart)this.eye);
        this.eye.setTextureOffset(0, 0).addBox(-1.0f, -1.0f, 0.0f, 2.0f, 2.0f, 1.0f, f, false);
        this.leg_left = new AdvancedModelBox((AdvancedEntityModel)this, "leg_left");
        this.leg_left.setPos(3.0f, 0.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.leg_left);
        this.leg_left.setTextureOffset(0, 39).addBox(-2.0f, 0.0f, -3.0f, 5.0f, 7.0f, 6.0f, f, false);
        this.leg_right = new AdvancedModelBox((AdvancedEntityModel)this, "leg_right");
        this.leg_right.setPos(-3.0f, 0.0f, 0.0f);
        this.body.addChild((BasicModelPart)this.leg_right);
        this.leg_right.setTextureOffset(0, 25).addBox(-3.0f, 0.0f, -3.0f, 5.0f, 7.0f, 6.0f, f, false);
        this.nose = new AdvancedModelBox((AdvancedEntityModel)this, "nose");
        this.nose.setPos(0.0f, -9.0f, -4.0f);
        this.body.addChild((BasicModelPart)this.nose);
        this.nose.setTextureOffset(35, 43).addBox(-1.0f, 0.0f, -2.0f, 2.0f, 5.0f, 2.0f, f, false);
        this.sack = new AdvancedModelBox((AdvancedEntityModel)this, "sack");
        this.sack.setPos(0.0f, -7.0f, 4.0f);
        this.body.addChild((BasicModelPart)this.sack);
        this.sack.setTextureOffset(23, 25).addBox(-4.0f, -8.0f, 0.0f, 8.0f, 10.0f, 3.0f, f, false);
        this.updateDefaultPose();
    }

    public void setupAnim(EntityMungus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float walkSpeed = 0.7f;
        float walkDegree = 0.6f;
        float idleSpeed = 0.1f;
        float idleDegree = 0.1f;
        float swell = Math.min(entity.prevSwellProgress + (entity.swellProgress - entity.prevSwellProgress) * Minecraft.m_91087_().m_91296_(), 10.0f);
        float glowyBob = swell * 0.22f + 0.95f + (Mth.m_14089_((float)(ageInTicks * (0.1f + swell * 0.2f))) + 1.0f) * (0.05f + swell * 0.02f);
        BlockPos targetPos = entity.getBeamTarget();
        if (targetPos == null) {
            Entity look = Minecraft.m_91087_().m_91288_();
            if (look != null) {
                Vec3 vector3d = look.m_20299_(0.0f);
                Vec3 vector3d1 = entity.m_20299_(0.0f);
                double d0 = vector3d.f_82480_ - vector3d1.f_82480_;
                this.eye.rotationPointY = d0 > 0.0 ? -11.0f : -10.0f;
                Vec3 vector3d2 = entity.m_20252_(0.0f);
                vector3d2 = new Vec3(vector3d2.f_82479_, 0.0, vector3d2.f_82481_);
                Vec3 vector3d3 = new Vec3(vector3d1.f_82479_ - vector3d.f_82479_, 0.0, vector3d1.f_82481_ - vector3d.f_82481_).m_82541_().m_82524_(1.5707964f);
                double d1 = vector3d2.m_82526_(vector3d3);
                this.eye.rotationPointX += Mth.m_14116_((float)((float)Math.abs(d1))) * 2.0f * (float)Math.signum(d1);
            }
        } else {
            Vec3 vector3d = Vec3.m_82512_((Vec3i)targetPos);
            Vec3 vector3d1 = entity.m_20299_(0.0f);
            double d0 = vector3d.f_82480_ - vector3d1.f_82480_;
            this.eye.rotationPointY = d0 > 0.0 ? -11.0f : -10.0f;
            Vec3 vector3d2 = entity.m_20252_(0.0f);
            vector3d2 = new Vec3(vector3d2.f_82479_, 0.0, vector3d2.f_82481_);
            Vec3 vector3d3 = new Vec3(vector3d1.f_82479_ - vector3d.f_82479_, 0.0, vector3d1.f_82481_ - vector3d.f_82481_).m_82541_().m_82524_(1.5707964f);
            double d1 = vector3d2.m_82526_(vector3d3);
            this.eye.rotationPointX += Mth.m_14116_((float)((float)Math.abs(d1))) * 2.0f * (float)Math.signum(d1);
        }
        this.walk(this.hair, idleSpeed, idleDegree, false, 1.0f, -0.1f, ageInTicks, 1.0f);
        this.flap(this.nose, idleSpeed, idleDegree, false, 0.0f, 0.0f, ageInTicks, 1.0f);
        this.sack.setScale(glowyBob, glowyBob, glowyBob + swell * 0.2f);
        this.sack.rotationPointZ += swell * 0.02f;
        this.progressRotationPrev(this.hair, limbSwingAmount, Maths.rad(-23.0), 0.0f, 0.0f, 1.0f);
        this.walk(this.leg_right, walkSpeed, walkDegree * 1.1f, true, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.leg_right, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.walk(this.leg_left, walkSpeed, walkDegree * 1.1f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.leg_left, walkSpeed, walkDegree, false, limbSwing, limbSwingAmount);
        this.flap(this.body, walkSpeed, walkDegree * 0.4f, false, 0.5f, 0.0f, limbSwing, limbSwingAmount);
        this.flap(this.nose, walkSpeed, walkDegree * 0.2f, false, 1.0f, 0.0f, limbSwing, limbSwingAmount);
        this.bob(this.body, walkSpeed, walkDegree * 3.0f, true, limbSwing, limbSwingAmount);
    }

    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of((Object)this.root);
    }

    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of((Object)this.root, (Object)this.body, (Object)this.hair, (Object)this.eye, (Object)this.leg_left, (Object)this.leg_right, (Object)this.sack, (Object)this.nose);
    }

    public void m_7695_(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            this.eye.setScale(1.5f, 1.5f, 1.5f);
            this.nose.setScale(1.5f, 1.5f, 1.5f);
            matrixStackIn.m_85836_();
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        } else {
            this.eye.setScale(1.0f, 1.0f, 1.0f);
            this.nose.setScale(1.0f, 1.0f, 1.0f);
            matrixStackIn.m_85836_();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.m_85849_();
        }
    }

    public void renderShoes() {
        this.leg_left.setScale(1.3f, 1.3f, 1.3f);
        this.leg_right.setScale(1.3f, 1.3f, 1.3f);
    }

    public void postRenderShoes() {
        this.leg_left.setScale(1.0f, 1.0f, 1.0f);
        this.leg_right.setScale(1.0f, 1.0f, 1.0f);
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox2, float x, float y, float z) {
        AdvancedModelBox2.rotateAngleX = x;
        AdvancedModelBox2.rotateAngleY = y;
        AdvancedModelBox2.rotateAngleZ = z;
    }
}
