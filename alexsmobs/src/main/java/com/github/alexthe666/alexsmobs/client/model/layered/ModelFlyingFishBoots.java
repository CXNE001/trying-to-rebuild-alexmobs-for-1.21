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
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 */
package com.github.alexthe666.alexsmobs.client.model.layered;

import com.github.alexthe666.alexsmobs.entity.util.FlyingFishBootsUtil;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;

public class ModelFlyingFishBoots
extends HumanoidModel {
    private final ModelPart rightFish;
    private final ModelPart leftFish;
    private final ModelPart rightWingOuter;
    private final ModelPart leftWingOuter;
    private final ModelPart rightWingInner;
    private final ModelPart leftWingInner;

    public ModelFlyingFishBoots(ModelPart root) {
        super(root);
        this.rightFish = root.m_171324_("right_leg").m_171324_("RBoot");
        this.leftFish = root.m_171324_("left_leg").m_171324_("LBoot");
        this.rightWingOuter = this.rightFish.m_171324_("RwingR");
        this.leftWingOuter = this.leftFish.m_171324_("LwingL");
        this.rightWingInner = this.rightFish.m_171324_("RwingL");
        this.leftWingInner = this.leftFish.m_171324_("LwingR");
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.m_170681_((CubeDeformation)deformation, (float)1.0f);
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition leftleg = partdefinition.m_171597_("left_leg");
        PartDefinition rightleg = partdefinition.m_171597_("right_leg");
        PartDefinition RBoot = rightleg.m_171599_("RBoot", CubeListBuilder.m_171558_().m_171514_(18, 12).m_171480_().m_171488_(-1.9f, -3.0f, -2.0f, 4.0f, 3.0f, 4.0f, new CubeDeformation(0.3f)).m_171555_(false).m_171514_(0, 25).m_171480_().m_171488_(0.0f, -2.0f, 2.0f, 0.0f, 4.0f, 5.0f, new CubeDeformation(0.0f)).m_171555_(false).m_171514_(0, 0).m_171480_().m_171488_(-2.5f, 0.0f, -5.0f, 5.0f, 2.0f, 9.0f, new CubeDeformation(0.0f)).m_171555_(false), PartPose.m_171419_((float)-0.1f, (float)10.0f, (float)0.0f));
        RBoot.m_171599_("RwingR", CubeListBuilder.m_171558_().m_171514_(9, 47).m_171480_().m_171488_(0.0f, -3.0f, 0.0f, 0.0f, 4.0f, 8.0f, new CubeDeformation(0.0f)).m_171555_(false), PartPose.m_171423_((float)-2.5f, (float)1.0f, (float)-3.0f, (float)0.0f, (float)-0.5672f, (float)0.0f));
        RBoot.m_171599_("RwingL", CubeListBuilder.m_171558_().m_171514_(0, 42).m_171480_().m_171488_(0.0f, -3.0f, 0.0f, 0.0f, 4.0f, 8.0f, new CubeDeformation(0.0f)).m_171555_(false), PartPose.m_171423_((float)2.5f, (float)1.0f, (float)-3.0f, (float)0.0f, (float)0.5672f, (float)0.0f));
        PartDefinition LBoot = leftleg.m_171599_("LBoot", CubeListBuilder.m_171558_().m_171514_(18, 12).m_171488_(-2.1f, -3.0f, -2.0f, 4.0f, 3.0f, 4.0f, new CubeDeformation(0.3f)).m_171514_(0, 25).m_171488_(0.0f, -2.0f, 2.0f, 0.0f, 4.0f, 5.0f, new CubeDeformation(0.0f)).m_171514_(0, 0).m_171488_(-2.5f, 0.0f, -5.0f, 5.0f, 2.0f, 9.0f, new CubeDeformation(0.0f)), PartPose.m_171419_((float)0.1f, (float)10.0f, (float)0.0f));
        LBoot.m_171599_("LwingL", CubeListBuilder.m_171558_().m_171514_(9, 47).m_171488_(0.0f, -3.0f, 0.0f, 0.0f, 4.0f, 8.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)2.5f, (float)1.0f, (float)-3.0f, (float)0.0f, (float)0.5672f, (float)0.0f));
        LBoot.m_171599_("LwingR", CubeListBuilder.m_171558_().m_171514_(0, 42).m_171488_(0.0f, -3.0f, 0.0f, 0.0f, 4.0f, 8.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)-2.5f, (float)1.0f, (float)-3.0f, (float)0.0f, (float)-0.5672f, (float)0.0f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)64, (int)64);
    }

    public ModelFlyingFishBoots withAnimations(LivingEntity entity) {
        if (entity != null) {
            boolean flying;
            float partialTick = Minecraft.m_91087_().m_91296_();
            float ageInTicks = (float)entity.f_19797_ + partialTick;
            float fly = Mth.m_14089_((float)(ageInTicks * 0.2f)) * 0.1f;
            float fly2 = fly * 0.35f;
            boolean bl = flying = FlyingFishBootsUtil.getBoostTicks(entity) > 0;
            if (flying) {
                fly2 = fly = (1.0f + Mth.m_14031_((float)(ageInTicks * 1.2f))) * 0.8f;
            }
            this.rightWingOuter.f_104204_ = -0.5672f - fly;
            this.leftWingOuter.f_104204_ = 0.5672f + fly;
            this.rightWingInner.f_104204_ = 0.5672f + fly2;
            this.leftWingInner.f_104204_ = -0.5672f - fly2;
            if (flying || entity.m_20089_() == Pose.SWIMMING) {
                this.leftFish.f_104203_ = Maths.rad(-45.0);
                this.rightFish.f_104203_ = Maths.rad(-45.0);
                this.rightFish.f_104201_ = 11.0f;
                this.leftFish.f_104201_ = 11.0f;
                this.rightFish.f_104202_ = -1.5f;
                this.leftFish.f_104202_ = -1.5f;
            } else if (entity.m_20089_() == Pose.CROUCHING) {
                this.leftFish.f_104203_ = 0.0f;
                this.rightFish.f_104203_ = 0.0f;
                this.rightFish.f_104201_ = 8.0f;
                this.leftFish.f_104201_ = 8.0f;
                this.rightFish.f_104202_ = 0.0f;
                this.leftFish.f_104202_ = 0.0f;
            } else {
                this.leftFish.f_104203_ = 0.0f;
                this.rightFish.f_104203_ = 0.0f;
                this.rightFish.f_104201_ = 10.0f;
                this.leftFish.f_104201_ = 10.0f;
                this.rightFish.f_104202_ = 0.0f;
                this.leftFish.f_104202_ = 0.0f;
            }
        }
        return this;
    }
}
