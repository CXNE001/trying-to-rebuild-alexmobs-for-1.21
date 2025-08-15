/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelBlueJay;
import com.github.alexthe666.alexsmobs.client.model.ModelRaccoon;
import com.github.alexthe666.alexsmobs.entity.EntityBlueJay;
import com.github.alexthe666.alexsmobs.entity.EntityRaccoon;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class RenderBlueJay
extends MobRenderer<EntityBlueJay, ModelBlueJay> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/blue_jay.png");
    private static final ResourceLocation TEXTURE_SHINY = new ResourceLocation("alexsmobs:textures/entity/blue_jay_shiny.png");

    public RenderBlueJay(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, (EntityModel)new ModelBlueJay(), 0.2f);
        this.m_115326_(new LayerShiny());
    }

    protected void scale(EntityBlueJay mob, PoseStack matrixStackIn, float partialTicks) {
        Entity entity;
        matrixStackIn.m_85841_(0.9f, 0.9f, 0.9f);
        if (mob.m_20159_() && mob.m_20202_() != null && (entity = mob.m_20202_()) instanceof EntityRaccoon) {
            EntityModel entityModel;
            EntityRaccoon entityRaccoon = (EntityRaccoon)entity;
            EntityRenderer raccoonRenderer = Minecraft.m_91087_().m_91290_().m_114382_((Entity)entityRaccoon);
            if (raccoonRenderer instanceof LivingEntityRenderer && (entityModel = ((LivingEntityRenderer)raccoonRenderer).m_7200_()) instanceof ModelRaccoon) {
                ModelRaccoon raccoonModel = (ModelRaccoon)entityModel;
                float begProgress = entityRaccoon.prevBegProgress + (entityRaccoon.begProgress - entityRaccoon.prevBegProgress) * partialTicks;
                float standProgress0 = entityRaccoon.prevStandProgress + (entityRaccoon.standProgress - entityRaccoon.prevStandProgress) * partialTicks;
                float sitProgress = entityRaccoon.prevSitProgress + (entityRaccoon.sitProgress - entityRaccoon.prevSitProgress) * partialTicks;
                float standProgress = Math.max(Math.max(begProgress, standProgress0) - sitProgress, 0.0f);
                matrixStackIn.m_252880_(0.0f, -1.03f - sitProgress * 0.01f, 0.0f);
                Vec3 vec = raccoonModel.getRidingPosition(new Vec3(0.0, 0.0, (double)(-0.1f + standProgress * 0.1f)));
                matrixStackIn.m_85837_(vec.f_82479_, vec.f_82480_, vec.f_82481_);
            }
        }
    }

    public ResourceLocation getTextureLocation(EntityBlueJay entity) {
        return TEXTURE;
    }

    class LayerShiny
    extends RenderLayer<EntityBlueJay, ModelBlueJay> {
        public LayerShiny() {
            super((RenderLayerParent)RenderBlueJay.this);
        }

        public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityBlueJay entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            if (entitylivingbaseIn.getFeedTime() > 0) {
                VertexConsumer ivertexbuilder = bufferIn.m_6299_(RenderType.m_110473_((ResourceLocation)TEXTURE_SHINY));
                float alpha = (float)(1.0 + Math.sin(ageInTicks * 0.3f)) * 0.1f + 0.8f;
                ((ModelBlueJay)this.m_117386_()).m_7695_(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f), 1.0f, 1.0f, 1.0f, alpha);
            }
        }
    }
}
