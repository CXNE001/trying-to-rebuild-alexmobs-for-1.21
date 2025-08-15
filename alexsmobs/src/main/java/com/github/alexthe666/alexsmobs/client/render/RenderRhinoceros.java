/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelRhinoceros;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.entity.EntityRhinoceros;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RenderRhinoceros
extends MobRenderer<EntityRhinoceros, ModelRhinoceros> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/rhinoceros.png");
    private static final ResourceLocation TEXTURE_ANGRY = new ResourceLocation("alexsmobs:textures/entity/rhinoceros_angry.png");
    private static final ResourceLocation TEXTURE_POTION = new ResourceLocation("alexsmobs:textures/entity/rhinoceros_potion.png");

    public RenderRhinoceros(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelRhinoceros(), 0.9f);
        this.m_115326_(new PotionLayer(this));
    }

    protected void scale(EntityRhinoceros rabbit, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(1.1f, 1.1f, 1.1f);
    }

    public ResourceLocation getTextureLocation(EntityRhinoceros entity) {
        return entity.isAngry() ? TEXTURE_ANGRY : TEXTURE;
    }

    private static class PotionLayer
    extends RenderLayer<EntityRhinoceros, ModelRhinoceros> {
        public PotionLayer(RenderRhinoceros parent) {
            super((RenderLayerParent)parent);
        }

        public void render(PoseStack p_225628_1_, MultiBufferSource p_225628_2_, int p_225628_3_, EntityRhinoceros rhino, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
            int color = rhino.getPotionColor();
            if (color != -1 && !rhino.m_20145_()) {
                float r = (float)(color >> 16 & 0xFF) / 255.0f;
                float g = (float)(color >> 8 & 0xFF) / 255.0f;
                float b = (float)(color & 0xFF) / 255.0f;
                ((ModelRhinoceros)this.m_117386_()).m_7695_(p_225628_1_, p_225628_2_.m_6299_(AMRenderTypes.m_110458_((ResourceLocation)TEXTURE_POTION)), p_225628_3_, OverlayTexture.f_118083_, r, g, b, 1.0f);
            }
        }
    }
}
