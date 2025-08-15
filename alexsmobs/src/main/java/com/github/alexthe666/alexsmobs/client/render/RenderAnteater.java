/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Mob
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelAnteater;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerAnteaterBaby;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerAnteaterTongueItem;
import com.github.alexthe666.alexsmobs.entity.EntityAnteater;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class RenderAnteater
extends MobRenderer<EntityAnteater, ModelAnteater> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/anteater.png");
    private static final ResourceLocation TEXTURE_PETER = new ResourceLocation("alexsmobs:textures/entity/anteater_peter.png");

    public RenderAnteater(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelAnteater(), 0.45f);
        this.m_115326_(new LayerAnteaterTongueItem(this));
        this.m_115326_(new LayerAnteaterBaby(this));
    }

    public boolean shouldRender(EntityAnteater anteater, Frustum p_225626_2_, double p_225626_3_, double p_225626_5_, double p_225626_7_) {
        if (anteater.m_6162_() && anteater.m_20159_() && anteater.m_20202_() instanceof EntityAnteater) {
            return false;
        }
        return super.m_5523_((Mob)anteater, p_225626_2_, p_225626_3_, p_225626_5_, p_225626_7_);
    }

    protected void scale(EntityAnteater entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }

    public ResourceLocation getTextureLocation(EntityAnteater entity) {
        return entity.isPeter() ? TEXTURE_PETER : TEXTURE;
    }
}
