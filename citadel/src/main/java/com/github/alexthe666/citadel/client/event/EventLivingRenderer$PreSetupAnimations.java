/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.citadel.client.event;

import com.github.alexthe666.citadel.client.event.EventLivingRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;

public static class EventLivingRenderer.PreSetupAnimations
extends EventLivingRenderer.AccessToBufferSource {
    public EventLivingRenderer.PreSetupAnimations(LivingEntity entity, EntityModel model, PoseStack poseStack, float bodyYRot, float partialTicks, MultiBufferSource bufferSource, int packedLight) {
        super(entity, model, poseStack, bodyYRot, partialTicks, bufferSource, packedLight);
    }
}
