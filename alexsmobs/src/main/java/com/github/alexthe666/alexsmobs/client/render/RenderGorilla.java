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

import com.github.alexthe666.alexsmobs.client.model.ModelGorilla;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerGorillaItem;
import com.github.alexthe666.alexsmobs.entity.EntityGorilla;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderGorilla
extends MobRenderer<EntityGorilla, ModelGorilla> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/gorilla.png");
    private static final ResourceLocation TEXTURE_SILVERBACK = new ResourceLocation("alexsmobs:textures/entity/gorilla_silverback.png");
    private static final ResourceLocation TEXTURE_DK = new ResourceLocation("alexsmobs:textures/entity/gorilla_dk.png");
    private static final ResourceLocation TEXTURE_FUNKY = new ResourceLocation("alexsmobs:textures/entity/gorilla_funky.png");

    public RenderGorilla(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelGorilla(), 0.7f);
        this.m_115326_(new LayerGorillaItem(this));
    }

    protected void scale(EntityGorilla entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(entitylivingbaseIn.getGorillaScale(), entitylivingbaseIn.getGorillaScale(), entitylivingbaseIn.getGorillaScale());
    }

    public ResourceLocation getTextureLocation(EntityGorilla entity) {
        return entity.isFunkyKong() ? TEXTURE_FUNKY : (entity.isDonkeyKong() ? TEXTURE_DK : (entity.isSilverback() ? TEXTURE_SILVERBACK : TEXTURE));
    }
}
