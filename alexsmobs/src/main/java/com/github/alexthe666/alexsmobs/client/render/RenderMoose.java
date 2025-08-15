/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelMoose;
import com.github.alexthe666.alexsmobs.entity.EntityMoose;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class RenderMoose
extends MobRenderer<EntityMoose, ModelMoose> {
    private static final ResourceLocation TEXTURE_ANTLERED = new ResourceLocation("alexsmobs:textures/entity/moose_antlered.png");
    private static final ResourceLocation TEXTURE_SNOWY_ANTLERED = new ResourceLocation("alexsmobs:textures/entity/moose_snowy_antlered.png");
    private static final ResourceLocation TEXTURE_SNOWY = new ResourceLocation("alexsmobs:textures/entity/moose_snowy.png");
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/moose.png");

    public RenderMoose(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelMoose(), 0.8f);
        this.m_115326_(new LayerSnow());
    }

    protected void scale(EntityMoose entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }

    public ResourceLocation getTextureLocation(EntityMoose entity) {
        return entity.isAntlered() && !entity.m_6162_() ? TEXTURE_ANTLERED : TEXTURE;
    }

    class LayerSnow
    extends RenderLayer<EntityMoose, ModelMoose> {
        public LayerSnow() {
            super((RenderLayerParent)RenderMoose.this);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityMoose entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            if (entitylivingbaseIn.isSnowy()) {
                VertexConsumer ivertexbuilder = bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)(entitylivingbaseIn.isAntlered() && !entitylivingbaseIn.m_6162_() ? TEXTURE_SNOWY_ANTLERED : TEXTURE_SNOWY)));
                ((ModelMoose)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
            }
        }
    }
}
