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

public class ModelSpikedTurtleShell
extends HumanoidModel {
    public ModelSpikedTurtleShell(ModelPart p_170677_) {
        super(p_170677_);
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.m_170681_((CubeDeformation)deformation, (float)0.0f);
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition head = partdefinition.m_171597_("head");
        head.m_171599_("spikes1", CubeListBuilder.m_171558_().m_171514_(34, 15).m_171488_(0.0f, -33.0f, -4.5f, 4.0f, 1.0f, 9.0f, deformation), PartPose.m_171419_((float)0.0f, (float)24.0f, (float)0.0f));
        head.m_171599_("spikes2", CubeListBuilder.m_171558_().m_171514_(34, 15).m_171488_(-4.0f, -33.0f, -4.5f, 4.0f, 1.0f, 9.0f, deformation), PartPose.m_171419_((float)0.0f, (float)24.0f, (float)0.0f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)64, (int)32);
    }
}
