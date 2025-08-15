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

import com.github.alexthe666.alexsmobs.client.model.ModelGazelle;
import com.github.alexthe666.alexsmobs.entity.EntityGazelle;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderGazelle
extends MobRenderer<EntityGazelle, ModelGazelle> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/gazelle.png");

    public RenderGazelle(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelGazelle(), 0.4f);
    }

    protected void scale(EntityGazelle entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(0.8f, 0.8f, 0.8f);
    }

    public ResourceLocation getTextureLocation(EntityGazelle entity) {
        return TEXTURE;
    }
}
