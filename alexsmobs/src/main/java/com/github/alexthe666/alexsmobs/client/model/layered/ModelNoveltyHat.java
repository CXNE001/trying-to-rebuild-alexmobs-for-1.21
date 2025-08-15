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

public class ModelNoveltyHat
extends HumanoidModel {
    public ModelNoveltyHat(ModelPart part) {
        super(part);
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.m_170681_((CubeDeformation)deformation, (float)0.0f);
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition head = partdefinition.m_171597_("head");
        PartDefinition hat = head.m_171599_("hat", CubeListBuilder.m_171558_().m_171514_(0, 30).m_171488_(-4.5f, -5.0f, -4.5f, 9.0f, 6.0f, 9.0f, new CubeDeformation(0.0f)).m_171514_(31, 55).m_171488_(4.5f, -2.0f, -2.5f, 4.0f, 5.0f, 4.0f, new CubeDeformation(0.0f)).m_171514_(31, 55).m_171480_().m_171488_(-8.5f, -2.0f, -2.5f, 4.0f, 5.0f, 4.0f, new CubeDeformation(0.0f)).m_171555_(false).m_171514_(0, 46).m_171488_(-5.5f, 1.0f, -9.5f, 11.0f, 0.0f, 8.0f, new CubeDeformation(0.0f)), PartPose.m_171419_((float)0.0f, (float)-6.0f, (float)0.0f));
        PartDefinition pipes = hat.m_171599_("pipes", CubeListBuilder.m_171558_().m_171514_(0, 55).m_171488_(-7.5f, 0.0f, 0.0f, 15.0f, 9.0f, 0.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)0.0f, (float)2.0f, (float)-0.5f, (float)-0.6545f, (float)0.0f, (float)0.0f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)64, (int)64);
    }
}
