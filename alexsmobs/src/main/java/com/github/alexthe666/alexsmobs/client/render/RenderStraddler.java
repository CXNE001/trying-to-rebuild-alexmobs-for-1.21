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

import com.github.alexthe666.alexsmobs.client.model.ModelStraddler;
import com.github.alexthe666.alexsmobs.client.model.ModelStradpole;
import com.github.alexthe666.alexsmobs.client.render.RenderStradpole;
import com.github.alexthe666.alexsmobs.entity.EntityStraddler;
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

public class RenderStraddler
extends MobRenderer<EntityStraddler, ModelStraddler> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/straddler.png");
    private static final ModelStradpole STRADPOLE_MODEL = new ModelStradpole();

    public RenderStraddler(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelStraddler(), 0.6f);
        this.m_115326_(new StradpoleLayer(this));
    }

    protected void scale(EntityStraddler entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(1.2f, 1.2f, 1.2f);
    }

    public ResourceLocation getTextureLocation(EntityStraddler entity) {
        return TEXTURE;
    }

    static class StradpoleLayer
    extends RenderLayer<EntityStraddler, ModelStraddler> {
        public StradpoleLayer(RenderStraddler p_i50928_1_) {
            super((RenderLayerParent)p_i50928_1_);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityStraddler straddler, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            int t = straddler.getAnimationTick();
            if (straddler.getAnimation() == EntityStraddler.ANIMATION_LAUNCH && t < 20 && t > 6) {
                matrixStackIn.m_85836_();
                this.translateToModel(matrixStackIn);
                float back = t <= 15 ? (float)(t - 6) * 0.05f : 0.25f;
                matrixStackIn.m_252880_(0.0f, -2.5f + back * 0.5f, 0.35f + back);
                VertexConsumer ivertexbuilder = bufferIn.m_6299_(RenderType.m_110473_((ResourceLocation)RenderStradpole.TEXTURE));
                STRADPOLE_MODEL.m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)straddler, (float)0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
                matrixStackIn.m_85849_();
            }
        }

        protected void translateToModel(PoseStack matrixStack) {
            ((ModelStraddler)this.m_117386_()).root.translateAndRotate(matrixStack);
            ((ModelStraddler)this.m_117386_()).body.translateAndRotate(matrixStack);
        }
    }
}
