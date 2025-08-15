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
public class ModelRoadrunnerBoots
extends HumanoidModel {
    public ModelRoadrunnerBoots(ModelPart p_170677_) {
        super(p_170677_);
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.m_170681_((CubeDeformation)deformation, (float)1.0f);
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition leftleg = partdefinition.m_171597_("left_leg");
        PartDefinition rightleg = partdefinition.m_171597_("right_leg");
        rightleg.m_171599_("featherr", CubeListBuilder.m_171558_().m_171514_(20, 22).m_171488_(-3.0f, -7.5f, 0.0f, 3.0f, 8.0f, 0.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)-1.5f, (float)9.5f, (float)0.4f, (float)0.0f, (float)0.9773844f, (float)-0.312763f));
        leftleg.m_171599_("featherl", CubeListBuilder.m_171558_().m_171514_(20, 22).m_171480_().m_171488_(0.0f, -7.4f, 0.0f, 3.0f, 8.0f, 0.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)1.5f, (float)9.5f, (float)-0.4f, (float)0.0f, (float)-0.9773844f, (float)0.312763f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)64, (int)32);
    }
}
