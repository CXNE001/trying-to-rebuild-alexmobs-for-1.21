/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class RenderNothing
extends LivingEntityRenderer {
    public RenderNothing(EntityRendererProvider.Context context) {
        super(context, null, 0.0f);
    }

    public void m_7392_(LivingEntity entity, float f, float f1, PoseStack stack, MultiBufferSource buf, int i) {
    }

    protected boolean m_6512_(LivingEntity entity) {
        return super.m_6512_(entity) && (entity.m_6052_() || entity.m_8077_() && entity == this.f_114476_.f_114359_);
    }

    public ResourceLocation m_5478_(Entity entity) {
        return null;
    }
}
