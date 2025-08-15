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

import com.github.alexthe666.alexsmobs.client.model.ModelCaiman;
import com.github.alexthe666.alexsmobs.entity.EntityCaiman;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderCaiman
extends MobRenderer<EntityCaiman, ModelCaiman> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/caiman.png");

    public RenderCaiman(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelCaiman(), 0.4f);
    }

    public ResourceLocation getTextureLocation(EntityCaiman entity) {
        return TEXTURE;
    }
}
