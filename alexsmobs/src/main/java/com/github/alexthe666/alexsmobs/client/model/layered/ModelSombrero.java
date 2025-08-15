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
public class ModelSombrero
extends HumanoidModel {
    public ModelPart sombrero;

    public ModelSombrero(ModelPart p_170677_) {
        super(p_170677_);
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.m_170681_((CubeDeformation)deformation, (float)0.0f);
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition head = partdefinition.m_171597_("head");
        head.m_171599_("sombrero", CubeListBuilder.m_171558_().m_171514_(0, 64).m_171488_(-4.0f, -11.0f, -4.0f, 8.0f, 6.0f, 8.0f, deformation), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)0.0f));
        head.m_171599_("sombrero2", CubeListBuilder.m_171558_().m_171514_(22, 73).m_171488_(-11.0f, -8.0f, -11.0f, 22.0f, 3.0f, 22.0f, deformation), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)0.0f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)128, (int)128);
    }

    public static LayerDefinition createArmorLayerAprilFools(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.m_170681_((CubeDeformation)deformation, (float)0.0f);
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition head = partdefinition.m_171597_("head");
        head.m_171599_("sombrero", CubeListBuilder.m_171558_().m_171514_(0, 64).m_171488_(-4.0f, 7.0f, -4.0f, 8.0f, 6.0f, 8.0f, deformation), PartPose.m_171423_((float)0.0f, (float)0.0f, (float)0.0f, (float)((float)Math.PI), (float)0.0f, (float)0.31415927f));
        head.m_171599_("sombrero2", CubeListBuilder.m_171558_().m_171514_(22, 73).m_171488_(-11.0f, 10.0f, -11.0f, 22.0f, 3.0f, 22.0f, deformation), PartPose.m_171423_((float)0.0f, (float)0.0f, (float)0.0f, (float)((float)Math.PI), (float)0.0f, (float)0.31415927f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)128, (int)128);
    }
}
