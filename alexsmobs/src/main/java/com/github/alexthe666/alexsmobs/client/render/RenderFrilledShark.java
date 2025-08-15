/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
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

import com.github.alexthe666.alexsmobs.client.model.ModelFrilledShark;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.entity.EntityFrilledShark;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RenderFrilledShark
extends MobRenderer<EntityFrilledShark, ModelFrilledShark> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/frilled_shark.png");
    private static final ResourceLocation TEXTURE_DEPRESSURIZED = new ResourceLocation("alexsmobs:textures/entity/frilled_shark_depressurized.png");
    private static final ResourceLocation TEXTURE_KAIJU = new ResourceLocation("alexsmobs:textures/entity/frilled_shark_kaiju.png");
    private static final ResourceLocation TEXTURE_KAIJU_DEPRESSURIZED = new ResourceLocation("alexsmobs:textures/entity/frilled_shark_kaiju_depressurized.png");
    private static final ResourceLocation TEXTURE_TEETH = new ResourceLocation("alexsmobs:textures/entity/frilled_shark_teeth.png");

    public RenderFrilledShark(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelFrilledShark(), 0.4f);
        this.m_115326_(new TeethLayer(this));
    }

    protected void scale(EntityFrilledShark entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        matrixStackIn.m_85841_(0.85f, 0.85f, 0.85f);
    }

    public ResourceLocation getTextureLocation(EntityFrilledShark entity) {
        return entity.isKaiju() ? (entity.isDepressurized() ? TEXTURE_KAIJU_DEPRESSURIZED : TEXTURE_KAIJU) : (entity.isDepressurized() ? TEXTURE_DEPRESSURIZED : TEXTURE);
    }

    static class TeethLayer
    extends RenderLayer<EntityFrilledShark, ModelFrilledShark> {
        public TeethLayer(RenderFrilledShark render) {
            super((RenderLayerParent)render);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource buffer, int packedLightIn, EntityFrilledShark entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            VertexConsumer glintBuilder = buffer.m_6299_(AMRenderTypes.getEyesFlickering(TEXTURE_TEETH, 240.0f));
            ((ModelFrilledShark)this.m_117386_()).m_7695_(matrixStackIn, glintBuilder, 240, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}
