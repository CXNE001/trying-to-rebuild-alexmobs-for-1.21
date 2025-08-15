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

public class ModelFroststalkerHelmet
extends HumanoidModel {
    public ModelFroststalkerHelmet(ModelPart part) {
        super(part);
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.m_170681_((CubeDeformation)deformation, (float)0.0f);
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition head = partdefinition.m_171597_("head");
        head.m_171599_("frost", CubeListBuilder.m_171558_().m_171514_(0, 17).m_171488_(-3.0f, -10.2f, -2.8f, 6.0f, 4.0f, 9.0f, deformation), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)0.0f));
        head.m_171599_("horn", CubeListBuilder.m_171558_().m_171514_(29, 29).m_171488_(-1.0f, -7.0f, 2.0f, 2.0f, 8.0f, 2.0f, new CubeDeformation(0.2f)), PartPose.m_171423_((float)0.0f, (float)-6.0f, (float)-3.0f, (float)1.0472f, (float)0.0f, (float)0.0f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)64, (int)64);
    }
}
