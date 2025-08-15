/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelCrimsonMosquito;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerCrimsonMosquitoBlood;
import com.github.alexthe666.alexsmobs.entity.EntityCrimsonMosquito;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class RenderCrimsonMosquito
extends MobRenderer<EntityCrimsonMosquito, ModelCrimsonMosquito> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/crimson_mosquito.png");
    private static final ResourceLocation TEXTURE_SICK = new ResourceLocation("alexsmobs:textures/entity/crimson_mosquito_blue.png");
    private static final ResourceLocation TEXTURE_FLY = new ResourceLocation("alexsmobs:textures/entity/crimson_mosquito_fly.png");
    private static final ResourceLocation TEXTURE_SICK_FLY = new ResourceLocation("alexsmobs:textures/entity/crimson_mosquito_fly_blue.png");

    public RenderCrimsonMosquito(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelCrimsonMosquito(), 0.6f);
        this.m_115326_(new LayerCrimsonMosquitoBlood(this));
    }

    protected void scale(EntityCrimsonMosquito entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        float mosScale = entitylivingbaseIn.prevMosquitoScale + (entitylivingbaseIn.getMosquitoScale() - entitylivingbaseIn.prevMosquitoScale) * partialTickTime;
        matrixStackIn.m_85841_(mosScale * 1.2f, mosScale * 1.2f, mosScale * 1.2f);
    }

    protected boolean isShaking(EntityCrimsonMosquito fly) {
        return fly.isSick() || fly.getFleeingEntityId() != -1;
    }

    protected void setupRotations(EntityCrimsonMosquito entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if (this.isShaking(entityLiving)) {
            rotationYaw += (float)(Math.cos((double)entityLiving.f_19797_ * 7.0) * Math.PI * (double)0.9f);
            float vibrate = 0.05f * entityLiving.getMosquitoScale();
            matrixStackIn.m_252880_((entityLiving.m_217043_().m_188501_() - 0.5f) * vibrate, (entityLiving.m_217043_().m_188501_() - 0.5f) * vibrate, (entityLiving.m_217043_().m_188501_() - 0.5f) * vibrate);
        }
        super.m_7523_((LivingEntity)entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    public ResourceLocation getTextureLocation(EntityCrimsonMosquito entity) {
        if (entity.isSick()) {
            return entity.isFromFly() ? TEXTURE_SICK_FLY : TEXTURE_SICK;
        }
        return entity.isFromFly() ? TEXTURE_FLY : TEXTURE;
    }
}
