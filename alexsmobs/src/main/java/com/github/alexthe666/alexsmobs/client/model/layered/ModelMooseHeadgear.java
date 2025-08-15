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
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
public class ModelMooseHeadgear
extends HumanoidModel {
    public ModelMooseHeadgear(ModelPart p_170677_) {
        super(p_170677_);
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.m_170681_((CubeDeformation)deformation, (float)0.0f);
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition head = partdefinition.m_171597_("head");
        head.m_171599_("hornL", CubeListBuilder.m_171558_().m_171514_(3, 17).m_171488_(0.0f, -5.5f, -4.0f, 10.0f, 6.0f, 8.0f, deformation), PartPose.m_171419_((float)5.0f, (float)-8.0f, (float)1.0f));
        head.m_171599_("hornR", CubeListBuilder.m_171558_().m_171514_(3, 17).m_171480_().m_171488_(-10.0f, -5.5f, -4.0f, 10.0f, 6.0f, 8.0f, deformation), PartPose.m_171419_((float)-5.0f, (float)-8.0f, (float)1.0f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)64, (int)32);
    }
}
