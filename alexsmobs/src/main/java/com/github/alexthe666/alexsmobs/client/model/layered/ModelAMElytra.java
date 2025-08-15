/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 *  net.minecraft.client.player.AbstractClientPlayer
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.client.model.layered;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class ModelAMElytra
extends HumanoidModel {
    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public ModelAMElytra(ModelPart part) {
        super(part);
        this.leftWing = part.m_171324_("body").m_171324_("left_wing");
        this.rightWing = part.m_171324_("body").m_171324_("right_wing");
    }

    public static LayerDefinition createLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.m_170681_((CubeDeformation)deformation, (float)0.0f);
        PartDefinition partdefinition = meshdefinition.m_171576_().m_171597_("body");
        CubeDeformation cubedeformation = new CubeDeformation(1.0f);
        partdefinition.m_171599_("left_wing", CubeListBuilder.m_171558_().m_171514_(32, 32).m_171488_(-10.0f, 0.0f, 0.0f, 10.0f, 20.0f, 2.0f, cubedeformation), PartPose.m_171423_((float)5.0f, (float)0.0f, (float)0.0f, (float)0.2617994f, (float)0.0f, (float)-0.2617994f));
        partdefinition.m_171599_("right_wing", CubeListBuilder.m_171558_().m_171514_(32, 32).m_171480_().m_171488_(0.0f, 0.0f, 0.0f, 10.0f, 20.0f, 2.0f, cubedeformation), PartPose.m_171423_((float)-5.0f, (float)0.0f, (float)0.0f, (float)0.2617994f, (float)0.0f, (float)0.2617994f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)64, (int)64);
    }

    public ModelAMElytra withAnimations(LivingEntity entity) {
        if (entity != null) {
            float partialTick = Minecraft.m_91087_().m_91296_();
            float limbSwingAmount = entity.f_267362_.m_267711_(partialTick);
            float limbSwing = entity.f_267362_.m_267756_() + partialTick;
            this.m_6973_(entity, limbSwing, limbSwingAmount, (float)entity.f_19797_ + partialTick, 0.0f, 0.0f);
        }
        return this;
    }

    public void m_6973_(LivingEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = 0.2617994f;
        float f1 = -0.2617994f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        if (entityIn.m_21255_()) {
            float f4 = 1.0f;
            Vec3 vector3d = entityIn.m_20184_();
            if (vector3d.f_82480_ < 0.0) {
                Vec3 vector3d1 = vector3d.m_82541_();
                f4 = 1.0f - (float)Math.pow(-vector3d1.f_82480_, 1.5);
            }
            f = f4 * 0.34906584f + (1.0f - f4) * f;
            f1 = f4 * -1.5707964f + (1.0f - f4) * f1;
        } else if (entityIn.m_6047_()) {
            f = 0.6981317f;
            f1 = -0.7853982f;
            f2 = -1.0f;
            f3 = 0.08726646f;
        }
        this.leftWing.f_104200_ = 5.0f;
        this.leftWing.f_104201_ = f2;
        if (entityIn instanceof AbstractClientPlayer) {
            AbstractClientPlayer abstractclientplayerentity = (AbstractClientPlayer)entityIn;
            abstractclientplayerentity.f_108542_ = (float)((double)abstractclientplayerentity.f_108542_ + (double)(f - abstractclientplayerentity.f_108542_) * 0.1);
            abstractclientplayerentity.f_108543_ = (float)((double)abstractclientplayerentity.f_108543_ + (double)(f3 - abstractclientplayerentity.f_108543_) * 0.1);
            abstractclientplayerentity.f_108544_ = (float)((double)abstractclientplayerentity.f_108544_ + (double)(f1 - abstractclientplayerentity.f_108544_) * 0.1);
            this.leftWing.f_104203_ = abstractclientplayerentity.f_108542_;
            this.leftWing.f_104204_ = abstractclientplayerentity.f_108543_;
            this.leftWing.f_104205_ = abstractclientplayerentity.f_108544_;
        } else {
            this.leftWing.f_104203_ = f;
            this.leftWing.f_104205_ = f1;
            this.leftWing.f_104204_ = f3;
        }
        this.rightWing.f_104200_ = -this.leftWing.f_104200_;
        this.rightWing.f_104204_ = -this.leftWing.f_104204_;
        this.rightWing.f_104201_ = this.leftWing.f_104201_;
        this.rightWing.f_104203_ = this.leftWing.f_104203_;
        this.rightWing.f_104205_ = -this.leftWing.f_104205_;
    }
}
