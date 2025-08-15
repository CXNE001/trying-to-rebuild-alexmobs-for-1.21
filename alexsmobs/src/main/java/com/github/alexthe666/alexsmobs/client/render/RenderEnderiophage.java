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
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelEnderiophage;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.entity.EntityEnderiophage;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import javax.annotation.Nullable;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RenderEnderiophage
extends MobRenderer<EntityEnderiophage, ModelEnderiophage> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/enderiophage.png");
    private static final ResourceLocation TEXTURE_GLOW = new ResourceLocation("alexsmobs:textures/entity/enderiophage_glow.png");
    private static final ResourceLocation TEXTURE_OVERWORLD = new ResourceLocation("alexsmobs:textures/entity/enderiophage_overworld.png");
    private static final ResourceLocation TEXTURE_OVERWORLD_GLOW = new ResourceLocation("alexsmobs:textures/entity/enderiophage_overworld_glow.png");
    private static final ResourceLocation TEXTURE_NETHER = new ResourceLocation("alexsmobs:textures/entity/enderiophage_nether.png");
    private static final ResourceLocation TEXTURE_NETHER_GLOW = new ResourceLocation("alexsmobs:textures/entity/enderiophage_nether_glow.png");

    public RenderEnderiophage(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelEnderiophage(), 0.5f);
        this.m_115326_((RenderLayer)new EnderiophageEyesLayer(this));
    }

    @Nullable
    protected RenderType getRenderType(EntityEnderiophage p_230496_1_, boolean p_230496_2_, boolean p_230496_3_, boolean p_230496_4_) {
        ResourceLocation resourcelocation = this.getTextureLocation(p_230496_1_);
        if (p_230496_3_) {
            return RenderType.m_110467_((ResourceLocation)resourcelocation);
        }
        if (p_230496_2_) {
            return RenderType.m_110473_((ResourceLocation)resourcelocation);
        }
        return p_230496_4_ ? RenderType.m_110491_((ResourceLocation)resourcelocation) : null;
    }

    protected void scale(EntityEnderiophage entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        float scale = entitylivingbaseIn.prevEnderiophageScale + (entitylivingbaseIn.getPhageScale() - entitylivingbaseIn.prevEnderiophageScale) * partialTickTime;
        matrixStackIn.m_85841_(0.8f * scale, 0.8f * scale, 0.8f * scale);
    }

    public ResourceLocation getTextureLocation(EntityEnderiophage entity) {
        return entity.getVariant() == 2 ? TEXTURE_NETHER : (entity.getVariant() == 1 ? TEXTURE_OVERWORLD : TEXTURE);
    }

    static class EnderiophageEyesLayer
    extends EyesLayer<EntityEnderiophage, ModelEnderiophage> {
        public EnderiophageEyesLayer(RenderEnderiophage p_i50928_1_) {
            super((RenderLayerParent)p_i50928_1_);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityEnderiophage entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            VertexConsumer ivertexbuilder = bufferIn.m_6299_(this.getRenderType(entitylivingbaseIn));
            ((ModelEnderiophage)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, 0xF00000, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        }

        public RenderType m_5708_() {
            return AMRenderTypes.getGhost(TEXTURE_GLOW);
        }

        public RenderType getRenderType(EntityEnderiophage entity) {
            return AMRenderTypes.getGhost(entity.getVariant() == 2 ? TEXTURE_NETHER_GLOW : (entity.getVariant() == 1 ? TEXTURE_OVERWORLD_GLOW : TEXTURE_GLOW));
        }
    }
}
