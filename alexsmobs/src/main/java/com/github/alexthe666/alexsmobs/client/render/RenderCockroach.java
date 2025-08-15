/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelCockroach;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerCockroachMaracas;
import com.github.alexthe666.alexsmobs.entity.EntityCockroach;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderCockroach
extends MobRenderer<EntityCockroach, ModelCockroach> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/cockroach.png");

    public RenderCockroach(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelCockroach(), 0.3f);
        this.m_115326_(new LayerCockroachMaracas(this, renderManagerIn));
    }

    protected void scale(EntityCockroach entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(0.85f, 0.85f, 0.85f);
    }

    public ResourceLocation getTextureLocation(EntityCockroach entity) {
        return TEXTURE;
    }
}
