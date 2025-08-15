/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  javax.annotation.Nullable
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelEndergrade;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerEndergradeSaddle;
import com.github.alexthe666.alexsmobs.entity.EntityEndergrade;
import com.mojang.blaze3d.vertex.PoseStack;
import javax.annotation.Nullable;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderEndergrade
extends MobRenderer<EntityEndergrade, ModelEndergrade> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/endergrade.png");

    public RenderEndergrade(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelEndergrade(), 0.6f);
        this.m_115326_(new LayerEndergradeSaddle(this));
    }

    @Nullable
    protected RenderType getRenderType(EntityEndergrade p_230496_1_, boolean p_230496_2_, boolean p_230496_3_, boolean p_230496_4_) {
        ResourceLocation resourcelocation = this.getTextureLocation(p_230496_1_);
        if (p_230496_3_) {
            return RenderType.m_110467_((ResourceLocation)resourcelocation);
        }
        if (p_230496_2_) {
            return RenderType.m_110473_((ResourceLocation)resourcelocation);
        }
        return p_230496_4_ ? RenderType.m_110491_((ResourceLocation)resourcelocation) : null;
    }

    protected void scale(EntityEndergrade entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(1.2f, 1.2f, 1.2f);
    }

    public ResourceLocation getTextureLocation(EntityEndergrade entity) {
        return TEXTURE;
    }
}
