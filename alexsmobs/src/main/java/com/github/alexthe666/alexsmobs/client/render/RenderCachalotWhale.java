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

import com.github.alexthe666.alexsmobs.client.model.ModelCachalotWhale;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerCachalotWhaleCapturedSquid;
import com.github.alexthe666.alexsmobs.entity.EntityCachalotPart;
import com.github.alexthe666.alexsmobs.entity.EntityCachalotWhale;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class RenderCachalotWhale
extends MobRenderer<EntityCachalotWhale, ModelCachalotWhale> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/cachalot/cachalot_whale.png");
    private static final ResourceLocation TEXTURE_SLEEPING = new ResourceLocation("alexsmobs:textures/entity/cachalot/cachalot_whale_sleeping.png");
    private static final ResourceLocation TEXTURE_ALBINO = new ResourceLocation("alexsmobs:textures/entity/cachalot/cachalot_whale_albino.png");
    private static final ResourceLocation TEXTURE_ALBINO_SLEEPING = new ResourceLocation("alexsmobs:textures/entity/cachalot/cachalot_whale_albino_sleeping.png");

    public RenderCachalotWhale(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelCachalotWhale(), 4.2f);
        this.m_115326_(new LayerCachalotWhaleCapturedSquid(this));
    }

    protected void scale(EntityCachalotWhale entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }

    public boolean shouldRender(EntityCachalotWhale livingEntityIn, Frustum camera, double camX, double camY, double camZ) {
        if (super.m_5523_((Mob)livingEntityIn, camera, camX, camY, camZ)) {
            return true;
        }
        for (EntityCachalotPart part : livingEntityIn.whaleParts) {
            if (!camera.m_113029_(part.m_20191_())) continue;
            return true;
        }
        return false;
    }

    public ResourceLocation getTextureLocation(EntityCachalotWhale entity) {
        if (entity.isAlbino()) {
            return entity.m_5803_() || entity.isBeached() ? TEXTURE_ALBINO_SLEEPING : TEXTURE_ALBINO;
        }
        return entity.m_5803_() || entity.isBeached() ? TEXTURE_SLEEPING : TEXTURE;
    }
}
