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

import com.github.alexthe666.alexsmobs.client.model.ModelPlatypus;
import com.github.alexthe666.alexsmobs.entity.EntityPlatypus;
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

public class RenderPlatypus
extends MobRenderer<EntityPlatypus, ModelPlatypus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/platypus.png");
    private static final ResourceLocation TEXTURE_PERRY = new ResourceLocation("alexsmobs:textures/entity/platypus_perry.png");

    public RenderPlatypus(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelPlatypus(), 0.45f);
        this.m_115326_(new FedoraLayer(this));
    }

    protected void scale(EntityPlatypus entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(0.9f, 0.9f, 0.9f);
    }

    public ResourceLocation getTextureLocation(EntityPlatypus entity) {
        return entity.isPerry() ? TEXTURE_PERRY : TEXTURE;
    }

    static class FedoraLayer
    extends RenderLayer<EntityPlatypus, ModelPlatypus> {
        private final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/platypus_fedora.png");

        public FedoraLayer(RenderPlatypus renderGrizzlyBear) {
            super((RenderLayerParent)renderGrizzlyBear);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityPlatypus entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            if (entitylivingbaseIn.hasFedora()) {
                VertexConsumer ivertexbuilder = bufferIn.m_6299_(RenderType.m_110452_((ResourceLocation)this.TEXTURE));
                ((ModelPlatypus)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
            }
        }
    }
}
