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

import com.github.alexthe666.alexsmobs.client.model.ModelGuster;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.entity.EntityGuster;
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

public class RenderGuster
extends MobRenderer<EntityGuster, ModelGuster> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/guster.png");
    private static final ResourceLocation TEXTURE_GOOGLY = new ResourceLocation("alexsmobs:textures/entity/guster_silly.png");
    private static final ResourceLocation TEXTURE_EYES = new ResourceLocation("alexsmobs:textures/entity/guster_eye.png");
    private static final ResourceLocation TEXTURE_RED = new ResourceLocation("alexsmobs:textures/entity/guster_red.png");
    private static final ResourceLocation TEXTURE_SOUL = new ResourceLocation("alexsmobs:textures/entity/guster_soul.png");
    private static final ResourceLocation TEXTURE_SOUL_EYES = new ResourceLocation("alexsmobs:textures/entity/guster_eye_soul.png");

    public RenderGuster(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelGuster(), 0.25f);
        this.m_115326_((RenderLayer)new GusterEyesLayer(this));
    }

    @Nullable
    protected RenderType getRenderType(EntityGuster p_230496_1_, boolean p_230496_2_, boolean p_230496_3_, boolean p_230496_4_) {
        ResourceLocation resourcelocation = this.getTextureLocation(p_230496_1_);
        if (p_230496_3_) {
            return RenderType.m_110473_((ResourceLocation)resourcelocation);
        }
        if (p_230496_2_) {
            return RenderType.m_110473_((ResourceLocation)resourcelocation);
        }
        return p_230496_4_ ? RenderType.m_110491_((ResourceLocation)resourcelocation) : null;
    }

    public ResourceLocation getTextureLocation(EntityGuster entity) {
        return entity.isGooglyEyes() ? TEXTURE_GOOGLY : (entity.getVariant() == 2 ? TEXTURE_SOUL : (entity.getVariant() == 1 ? TEXTURE_RED : TEXTURE));
    }

    static class GusterEyesLayer
    extends EyesLayer<EntityGuster, ModelGuster> {
        public GusterEyesLayer(RenderGuster p_i50928_1_) {
            super((RenderLayerParent)p_i50928_1_);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityGuster entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            if (!entitylivingbaseIn.isGooglyEyes()) {
                VertexConsumer ivertexbuilder = bufferIn.m_6299_(entitylivingbaseIn.getVariant() == 2 ? AMRenderTypes.getEyesNoCull(TEXTURE_SOUL_EYES) : AMRenderTypes.getEyesNoCull(TEXTURE_EYES));
                ((ModelGuster)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, 0xF00000, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
            }
        }

        public RenderType m_5708_() {
            return AMRenderTypes.getEyesNoCull(TEXTURE_EYES);
        }
    }
}
