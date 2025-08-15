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

import com.github.alexthe666.alexsmobs.client.model.ModelKangaroo;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerKangarooArmor;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerKangarooBaby;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerKangarooItem;
import com.github.alexthe666.alexsmobs.entity.EntityKangaroo;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class RenderKangaroo
extends MobRenderer<EntityKangaroo, ModelKangaroo> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/kangaroo.png");

    public RenderKangaroo(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelKangaroo(), 0.5f);
        this.m_115326_(new LayerKangarooItem(this));
        this.m_115326_(new LayerKangarooArmor(this, renderManagerIn));
        this.m_115326_(new LayerKangarooBaby(this));
    }

    public boolean shouldRender(EntityKangaroo kangaroo, Frustum p_225626_2_, double p_225626_3_, double p_225626_5_, double p_225626_7_) {
        if (kangaroo.m_6162_() && kangaroo.m_20159_() && kangaroo.m_20202_() instanceof EntityKangaroo) {
            return false;
        }
        return super.m_5523_((Mob)kangaroo, p_225626_2_, p_225626_3_, p_225626_5_, p_225626_7_);
    }

    protected void scale(EntityKangaroo entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }

    public ResourceLocation getTextureLocation(EntityKangaroo entity) {
        return TEXTURE;
    }
}
