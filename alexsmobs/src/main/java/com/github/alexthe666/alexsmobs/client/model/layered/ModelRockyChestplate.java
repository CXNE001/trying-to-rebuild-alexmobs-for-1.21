/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.model.layered;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;

public class ModelRockyChestplate
extends HumanoidModel {
    private final ModelPart Body;
    private final ModelPart LeftArm;
    private final ModelPart RightArm;

    public ModelRockyChestplate(ModelPart root) {
        super(root);
        this.Body = root.m_171324_("body").m_171324_("BodyRocky");
        this.LeftArm = root.m_171324_("left_arm").m_171324_("LeftArmRocky");
        this.RightArm = root.m_171324_("right_arm").m_171324_("RightArmRocky");
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.m_170681_((CubeDeformation)new CubeDeformation(0.25f), (float)0.0f);
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition playerBody = partdefinition.m_171597_("body");
        PartDefinition playerLeftArm = partdefinition.m_171597_("left_arm");
        PartDefinition playerRightArm = partdefinition.m_171597_("right_arm");
        PartDefinition bodyRocky = playerBody.m_171599_("BodyRocky", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-5.0f, -0.5f, -2.0f, 10.0f, 13.0f, 9.0f, deformation).m_171514_(0, 23).m_171488_(-4.0f, 0.5f, 6.0f, 8.0f, 11.0f, 4.0f, deformation).m_171514_(25, 34).m_171488_(-2.0f, -0.5f, 6.0f, 4.0f, 13.0f, 4.0f, deformation), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)0.0f));
        PartDefinition leftArmRocky = playerLeftArm.m_171599_("LeftArmRocky", CubeListBuilder.m_171558_().m_171514_(25, 23).m_171488_(-1.0f, -5.0f, -2.1f, 6.0f, 4.0f, 6.0f, deformation).m_171514_(0, 39).m_171488_(0.0f, -7.1f, -1.1f, 7.0f, 6.0f, 4.0f, deformation), PartPose.m_171419_((float)-1.0f, (float)2.0f, (float)0.0f));
        PartDefinition rightArmRocky = playerRightArm.m_171599_("RightArmRocky", CubeListBuilder.m_171558_().m_171514_(25, 23).m_171480_().m_171488_(-5.0f, -5.0f, -2.1f, 6.0f, 4.0f, 6.0f, deformation).m_171555_(false).m_171514_(0, 39).m_171480_().m_171488_(-7.0f, -7.1f, -1.1f, 7.0f, 6.0f, 4.0f, deformation).m_171555_(false), PartPose.m_171419_((float)1.0f, (float)2.0f, (float)0.0f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)64, (int)64);
    }

    public void m_6973_(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}
