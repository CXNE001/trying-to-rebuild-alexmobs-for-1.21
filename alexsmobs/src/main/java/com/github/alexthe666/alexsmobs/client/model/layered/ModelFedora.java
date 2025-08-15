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

public class ModelFedora
extends HumanoidModel {
    public ModelFedora(ModelPart part) {
        super(part);
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.m_170681_((CubeDeformation)deformation, (float)0.0f);
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition head = partdefinition.m_171597_("head");
        head.m_171599_("fedora", CubeListBuilder.m_171558_().m_171514_(0, 44).m_171488_(-3.0f, -3.55f, -3.0f, 6.0f, 4.0f, 6.0f, deformation), PartPose.m_171419_((float)0.0f, (float)-8.0f, (float)0.0f));
        head.m_171599_("fedora_shade", CubeListBuilder.m_171558_().m_171514_(0, 32).m_171488_(-5.0f, -0.5f, -5.0f, 10.0f, 1.0f, 10.0f, deformation), PartPose.m_171419_((float)0.0f, (float)-8.05f, (float)0.0f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)64, (int)64);
    }
}
