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
 *  net.minecraft.world.entity.animal.Sheep
 *  net.minecraft.world.item.DyeColor
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelRaccoon;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerRaccoonEyes;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerRaccoonItem;
import com.github.alexthe666.alexsmobs.entity.EntityRaccoon;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;

public class RenderRaccoon
extends MobRenderer<EntityRaccoon, ModelRaccoon> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/raccoon.png");
    private static final ResourceLocation TEXTURE_RIGBY = new ResourceLocation("alexsmobs:textures/entity/raccoon_rigby.png");
    private static final ResourceLocation TEXTURE_BANDANA = new ResourceLocation("alexsmobs:textures/entity/raccoon_bandana.png");

    public RenderRaccoon(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelRaccoon(), 0.4f);
        this.m_115326_(new LayerRaccoonEyes(this));
        this.m_115326_(new LayerRaccoonItem(this));
        this.m_115326_(new BandanaLayer(this));
    }

    protected void scale(EntityRaccoon entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(0.75f, 0.75f, 0.75f);
    }

    public ResourceLocation getTextureLocation(EntityRaccoon entity) {
        return entity.isRigby() ? TEXTURE_RIGBY : TEXTURE;
    }

    private static class BandanaLayer
    extends RenderLayer<EntityRaccoon, ModelRaccoon> {
        public BandanaLayer(RenderRaccoon renderRaccoon) {
            super((RenderLayerParent)renderRaccoon);
        }

        public void render(PoseStack p_225628_1_, MultiBufferSource p_225628_2_, int p_225628_3_, EntityRaccoon raccoon, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
            if (raccoon.getColor() != null && !raccoon.m_20145_()) {
                float lvt_13_2_;
                float lvt_12_2_;
                float lvt_11_2_;
                if (raccoon.m_8077_() && "jeb_".equals(raccoon.m_7755_().m_214077_())) {
                    int lvt_15_1_ = raccoon.f_19797_ / 25 + raccoon.m_19879_();
                    int lvt_16_1_ = DyeColor.values().length;
                    int lvt_17_1_ = lvt_15_1_ % lvt_16_1_;
                    int lvt_18_1_ = (lvt_15_1_ + 1) % lvt_16_1_;
                    float lvt_19_1_ = ((float)(raccoon.f_19797_ % 25) + p_225628_7_) / 25.0f;
                    float[] lvt_20_1_ = Sheep.m_29829_((DyeColor)DyeColor.m_41053_((int)lvt_17_1_));
                    float[] lvt_21_1_ = Sheep.m_29829_((DyeColor)DyeColor.m_41053_((int)lvt_18_1_));
                    lvt_11_2_ = lvt_20_1_[0] * (1.0f - lvt_19_1_) + lvt_21_1_[0] * lvt_19_1_;
                    lvt_12_2_ = lvt_20_1_[1] * (1.0f - lvt_19_1_) + lvt_21_1_[1] * lvt_19_1_;
                    lvt_13_2_ = lvt_20_1_[2] * (1.0f - lvt_19_1_) + lvt_21_1_[2] * lvt_19_1_;
                } else {
                    float[] lvt_14_2_ = Sheep.m_29829_((DyeColor)raccoon.getColor());
                    lvt_11_2_ = lvt_14_2_[0];
                    lvt_12_2_ = lvt_14_2_[1];
                    lvt_13_2_ = lvt_14_2_[2];
                }
                ((ModelRaccoon)this.m_117386_()).m_7695_(p_225628_1_, p_225628_2_.m_6299_(AMRenderTypes.m_110458_((ResourceLocation)TEXTURE_BANDANA)), p_225628_3_, OverlayTexture.f_118083_, lvt_11_2_, lvt_12_2_, lvt_13_2_, 1.0f);
            }
        }
    }
}
