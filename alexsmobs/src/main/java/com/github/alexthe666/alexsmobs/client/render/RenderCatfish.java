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

import com.github.alexthe666.alexsmobs.client.model.ModelCatfishLarge;
import com.github.alexthe666.alexsmobs.client.model.ModelCatfishMedium;
import com.github.alexthe666.alexsmobs.client.model.ModelCatfishSmall;
import com.github.alexthe666.alexsmobs.entity.EntityCatfish;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderCatfish
extends MobRenderer<EntityCatfish, EntityModel<EntityCatfish>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/catfish_small.png");
    private static final ResourceLocation TEXTURE_MEDIUM = new ResourceLocation("alexsmobs:textures/entity/catfish_medium.png");
    private static final ResourceLocation TEXTURE_LARGE = new ResourceLocation("alexsmobs:textures/entity/catfish_large.png");
    private static final ResourceLocation TEXTURE_SPIT = new ResourceLocation("alexsmobs:textures/entity/catfish_small_spit.png");
    private static final ResourceLocation TEXTURE_SPIT_MEDIUM = new ResourceLocation("alexsmobs:textures/entity/catfish_medium_spit.png");
    private static final ResourceLocation TEXTURE_SPIT_LARGE = new ResourceLocation("alexsmobs:textures/entity/catfish_large_spit.png");
    private final ModelCatfishSmall modelSmall = new ModelCatfishSmall();
    private final ModelCatfishMedium modelMedium = new ModelCatfishMedium();
    private final ModelCatfishLarge modelLarge = new ModelCatfishLarge();

    public RenderCatfish(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelCatfishSmall(), 0.5f);
    }

    protected void scale(EntityCatfish entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        this.f_115290_ = entitylivingbaseIn.getCatfishSize() == 2 ? this.modelLarge : (entitylivingbaseIn.getCatfishSize() == 1 ? this.modelMedium : this.modelSmall);
    }

    public ResourceLocation getTextureLocation(EntityCatfish entity) {
        if (entity.getCatfishSize() == 2) {
            return entity.isSpitting() ? TEXTURE_SPIT_LARGE : TEXTURE_LARGE;
        }
        if (entity.getCatfishSize() == 1) {
            return entity.isSpitting() ? TEXTURE_SPIT_MEDIUM : TEXTURE_MEDIUM;
        }
        return entity.isSpitting() ? TEXTURE_SPIT : TEXTURE;
    }
}
