/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.citadel.client.event;

import com.github.alexthe666.citadel.client.event.EventLivingRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.LivingEntity;

public static class EventLivingRenderer.SetupRotations
extends EventLivingRenderer {
    private final float bodyYRot;

    public EventLivingRenderer.SetupRotations(LivingEntity entity, EntityModel model, PoseStack poseStack, float bodyYRot, float partialTicks) {
        super(entity, model, poseStack, partialTicks);
        this.bodyYRot = bodyYRot;
    }

    public float getBodyYRot() {
        return this.bodyYRot;
    }
}
