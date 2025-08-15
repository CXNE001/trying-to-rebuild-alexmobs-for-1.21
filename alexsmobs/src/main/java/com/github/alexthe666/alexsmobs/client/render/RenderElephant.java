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

import com.github.alexthe666.alexsmobs.client.model.ModelElephant;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerElephantItem;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerElephantOverlays;
import com.github.alexthe666.alexsmobs.entity.EntityElephant;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderElephant
extends MobRenderer<EntityElephant, ModelElephant> {
    private static final ResourceLocation TEXTURE_TUSK = new ResourceLocation("alexsmobs:textures/entity/elephant/elephant_tusks.png");
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/elephant/elephant.png");

    public RenderElephant(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelElephant(0.0f), 1.4f);
        this.m_115326_(new LayerElephantOverlays(this));
        this.m_115326_(new LayerElephantItem(this));
    }

    protected void scale(EntityElephant entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        if (entitylivingbaseIn.isTusked()) {
            matrixStackIn.m_85841_(1.1f, 1.1f, 1.1f);
        }
    }

    public ResourceLocation getTextureLocation(EntityElephant entity) {
        return entity.isTusked() && !entity.m_6162_() ? TEXTURE_TUSK : TEXTURE;
    }
}
