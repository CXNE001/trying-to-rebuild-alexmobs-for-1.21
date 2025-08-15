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
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelSpectre;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.entity.EntitySpectre;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class RenderSpectre
extends MobRenderer<EntitySpectre, ModelSpectre> {
    private static final ResourceLocation TEXTURE_BONE = new ResourceLocation("alexsmobs:textures/entity/spectre_bone.png");
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/spectre.png");
    private static final ResourceLocation TEXTURE_EYES = new ResourceLocation("alexsmobs:textures/entity/spectre_glow.png");
    private static final ResourceLocation TEXTURE_LEAD = new ResourceLocation("alexsmobs:textures/entity/spectre_lead.png");

    public RenderSpectre(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelSpectre(), 0.5f);
        this.m_115326_((RenderLayer)new SpectreEyesLayer(this));
        this.m_115326_(new SpectreMembraneLayer(this));
    }

    protected void scale(EntitySpectre entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(1.3f, 1.3f, 1.3f);
    }

    protected int getBlockLightLevel(EntitySpectre entityIn, BlockPos partialTicks) {
        return 15;
    }

    public ResourceLocation getTextureLocation(EntitySpectre entity) {
        return TEXTURE_BONE;
    }

    public float getAlphaForRender(EntitySpectre entityIn, float partialTicks) {
        return ((float)Math.sin(((float)entityIn.f_19797_ + partialTicks) * 0.1f) + 1.5f) * 0.1f + 0.5f;
    }

    static class SpectreEyesLayer
    extends EyesLayer<EntitySpectre, ModelSpectre> {
        public SpectreEyesLayer(RenderSpectre p_i50928_1_) {
            super((RenderLayerParent)p_i50928_1_);
        }

        public RenderType m_5708_() {
            return RenderType.m_110488_((ResourceLocation)TEXTURE_EYES);
        }
    }

    class SpectreMembraneLayer
    extends RenderLayer<EntitySpectre, ModelSpectre> {
        public SpectreMembraneLayer(RenderSpectre p_i50928_1_) {
            super((RenderLayerParent)p_i50928_1_);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntitySpectre entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            VertexConsumer lvt_11_1_ = bufferIn.m_6299_(this.getRenderType());
            ((ModelSpectre)this.m_117386_()).m_7695_(matrixStackIn, lvt_11_1_, 0xF00000, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, RenderSpectre.this.getAlphaForRender(entitylivingbaseIn, partialTicks));
            if (entitylivingbaseIn.m_21523_()) {
                VertexConsumer lead = bufferIn.m_6299_(AMRenderTypes.m_110458_((ResourceLocation)TEXTURE_LEAD));
                ((ModelSpectre)this.m_117386_()).m_7695_(matrixStackIn, lead, 0xF00000, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
            }
        }

        public RenderType getRenderType() {
            return AMRenderTypes.getSpectreBones(TEXTURE);
        }
    }
}
