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

public static class EventLivingRenderer.AccessToBufferSource
extends EventLivingRenderer {
    private final float bodyYRot;
    private final MultiBufferSource bufferSource;
    private final int packedLight;

    public EventLivingRenderer.AccessToBufferSource(LivingEntity entity, EntityModel model, PoseStack poseStack, float bodyYRot, float partialTicks, MultiBufferSource bufferSource, int packedLight) {
        super(entity, model, poseStack, partialTicks);
        this.bodyYRot = bodyYRot;
        this.bufferSource = bufferSource;
        this.packedLight = packedLight;
    }

    public float getBodyYRot() {
        return this.bodyYRot;
    }

    public MultiBufferSource getBufferSource() {
        return this.bufferSource;
    }

    public int getPackedLight() {
        return this.packedLight;
    }
}
