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

public class ModelUnsettlingKimono
extends HumanoidModel {
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;

    public ModelUnsettlingKimono(ModelPart root) {
        super(root);
        this.body = root.m_171324_("body");
        this.left_arm = root.m_171324_("left_arm");
        this.right_arm = root.m_171324_("right_arm");
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.m_170681_((CubeDeformation)new CubeDeformation(0.25f), (float)0.0f);
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition playerBody = partdefinition.m_171597_("body");
        PartDefinition playerLeftArm = partdefinition.m_171597_("left_arm");
        PartDefinition playerRightArm = partdefinition.m_171597_("right_arm");
        PartDefinition body = playerBody.m_171599_("body", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-4.0f, 0.0f, -2.0f, 8.0f, 17.0f, 4.0f, new CubeDeformation(0.75f)), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)0.0f));
        PartDefinition left_arm = playerLeftArm.m_171599_("left_arm", CubeListBuilder.m_171558_().m_171514_(21, 18).m_171488_(-1.0f, -2.0f, -2.0f, 4.0f, 16.0f, 4.0f, new CubeDeformation(0.6f)), PartPose.m_171419_((float)-0.5f, (float)0.0f, (float)0.0f));
        PartDefinition right_arm = playerRightArm.m_171599_("right_arm", CubeListBuilder.m_171558_().m_171514_(21, 18).m_171480_().m_171488_(-3.0f, -2.0f, -2.0f, 4.0f, 16.0f, 4.0f, new CubeDeformation(0.6f)).m_171555_(false), PartPose.m_171419_((float)0.5f, (float)0.0f, (float)0.0f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)64, (int)64);
    }

    public void m_6973_(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}
