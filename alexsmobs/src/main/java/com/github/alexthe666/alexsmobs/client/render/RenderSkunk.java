/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelSkunk;
import com.github.alexthe666.alexsmobs.entity.EntitySkunk;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderSkunk
extends MobRenderer<EntitySkunk, ModelSkunk> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/skunk.png");

    public RenderSkunk(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelSkunk(), 0.45f);
    }

    public ResourceLocation getTextureLocation(EntitySkunk entity) {
        return TEXTURE;
    }
}
