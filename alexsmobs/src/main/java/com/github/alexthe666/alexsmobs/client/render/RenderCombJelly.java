/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  javax.annotation.Nullable
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelCombJelly;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.entity.EntityCombJelly;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import javax.annotation.Nullable;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RenderCombJelly
extends MobRenderer<EntityCombJelly, ModelCombJelly> {
    private static final ResourceLocation TEXTURE_0 = new ResourceLocation("alexsmobs:textures/entity/comb_jelly_blue.png");
    private static final ResourceLocation TEXTURE_1 = new ResourceLocation("alexsmobs:textures/entity/comb_jelly_green.png");
    private static final ResourceLocation TEXTURE_2 = new ResourceLocation("alexsmobs:textures/entity/comb_jelly_red.png");
    private static final ResourceLocation TEXTURE_OVERLAY = new ResourceLocation("alexsmobs:textures/entity/comb_jelly_overlay.png");
    private static final ModelCombJelly STRIPES_MODEL = new ModelCombJelly(0.05f);

    public RenderCombJelly(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelCombJelly(0.0f), 0.3f);
        this.m_115326_(new RainbowLayer(this));
    }

    protected void scale(EntityCombJelly jelly, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(jelly.getJellyScale(), jelly.getJellyScale(), jelly.getJellyScale());
    }

    protected float getFlipDegrees(EntityCombJelly jelly) {
        return 0.0f;
    }

    @Nullable
    protected RenderType getRenderType(EntityCombJelly jelly, boolean normal, boolean invis, boolean outline) {
        ResourceLocation resourcelocation = this.getTextureLocation(jelly);
        if (invis) {
            return RenderType.m_110467_((ResourceLocation)resourcelocation);
        }
        if (normal) {
            return RenderType.m_110473_((ResourceLocation)resourcelocation);
        }
        return outline ? RenderType.m_110491_((ResourceLocation)resourcelocation) : null;
    }

    public ResourceLocation getTextureLocation(EntityCombJelly entity) {
        return entity.getVariant() == 0 ? TEXTURE_0 : (entity.getVariant() == 1 ? TEXTURE_1 : TEXTURE_2);
    }

    static class RainbowLayer
    extends RenderLayer<EntityCombJelly, ModelCombJelly> {
        public RainbowLayer(RenderCombJelly render) {
            super((RenderLayerParent)render);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityCombJelly entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            VertexConsumer rainbow = AMRenderTypes.createMergedVertexConsumer(bufferIn.m_6299_(AMRenderTypes.COMBJELLY_RAINBOW_GLINT), bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)TEXTURE_OVERLAY)));
            STRIPES_MODEL.setupAnim(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            STRIPES_MODEL.m_7695_(matrixStackIn, rainbow, packedLightIn, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}
