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

import com.github.alexthe666.alexsmobs.client.model.ModelFly;
import com.github.alexthe666.alexsmobs.entity.EntityFly;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class RenderFly
extends MobRenderer<EntityFly, ModelFly> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/fly.png");

    public RenderFly(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelFly(), 0.2f);
    }

    protected void scale(EntityFly entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }

    protected boolean isShaking(EntityFly fly) {
        return fly.isInNether();
    }

    protected void setupRotations(EntityFly entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if (this.isShaking(entityLiving)) {
            rotationYaw += (float)(Math.cos((double)entityLiving.f_19797_ * 7.0) * Math.PI * (double)0.9f);
            float vibrate = 0.05f;
            matrixStackIn.m_252880_((entityLiving.m_217043_().m_188501_() - 0.5f) * vibrate, (entityLiving.m_217043_().m_188501_() - 0.5f) * vibrate, (entityLiving.m_217043_().m_188501_() - 0.5f) * vibrate);
        }
        super.m_7523_((LivingEntity)entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    public ResourceLocation getTextureLocation(EntityFly entity) {
        return TEXTURE;
    }
}
