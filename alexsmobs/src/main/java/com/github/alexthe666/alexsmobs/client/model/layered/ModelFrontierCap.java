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
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.alexsmobs.client.model.layered;

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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
public class ModelFrontierCap
extends HumanoidModel {
    public ModelPart tail;
    public ModelPart hat;

    public ModelFrontierCap(ModelPart p_170677_) {
        super(p_170677_);
        this.hat = p_170677_.m_171324_("head").m_171324_("frontierhat");
        this.tail = this.hat.m_171324_("tail");
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.m_170681_((CubeDeformation)deformation, (float)0.0f);
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition head = partdefinition.m_171597_("head");
        PartDefinition front = head.m_171599_("frontierhat", CubeListBuilder.m_171558_().m_171514_(32, 32).m_171488_(-4.0f, -10.5f, -4.0f, 8.0f, 4.0f, 8.0f, deformation), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)0.0f));
        front.m_171599_("tail", CubeListBuilder.m_171558_().m_171514_(36, 46).m_171488_(-1.5f, -0.3f, -1.5f, 3.0f, 13.0f, 3.0f, deformation), PartPose.m_171423_((float)4.4f, (float)-7.5f, (float)4.5f, (float)0.19565141f, (float)-0.039095376f, (float)-0.11728612f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)64, (int)64);
    }

    public ModelFrontierCap withAnimations(LivingEntity entity) {
        if (entity != null) {
            float partialTick = Minecraft.m_91087_().m_91296_();
            float limbSwingAmount = entity.f_267362_.m_267711_(partialTick);
            float limbSwing = entity.f_267362_.m_267756_() + partialTick;
            this.tail.f_104203_ = 0.19565141f + limbSwingAmount * Maths.rad(80.0) + Mth.m_14089_((float)(limbSwing * 0.3f)) * 0.2f * limbSwingAmount;
            this.tail.f_104204_ = -0.039095376f + limbSwingAmount * Maths.rad(10.0) - Mth.m_14089_((float)(limbSwing * 0.4f)) * 0.3f * limbSwingAmount;
            this.tail.f_104205_ = -0.11728612f + limbSwingAmount * Maths.rad(10.0);
        }
        return this;
    }
}
