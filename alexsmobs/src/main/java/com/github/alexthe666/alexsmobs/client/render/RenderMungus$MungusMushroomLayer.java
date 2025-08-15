/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.block.BlockRenderDispatcher
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.block.state.BlockState
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelMungus;
import com.github.alexthe666.alexsmobs.client.render.RenderMungus;
import com.github.alexthe666.alexsmobs.entity.EntityMungus;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;

static class RenderMungus.MungusMushroomLayer
extends RenderLayer<EntityMungus, ModelMungus> {
    public RenderMungus.MungusMushroomLayer(RenderMungus p_i50928_1_) {
        super((RenderLayerParent)p_i50928_1_);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityMungus entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        BlockRenderDispatcher blockrendererdispatcher = Minecraft.m_91087_().m_91289_();
        BlockState blockstate = entitylivingbaseIn.getMushroomState();
        if (blockstate == null) {
            return;
        }
        int i = LivingEntityRenderer.m_115338_((LivingEntity)entitylivingbaseIn, (float)0.0f);
        boolean altOrder = entitylivingbaseIn.isAltOrderMushroom();
        int mushroomCount = entitylivingbaseIn.getMushroomCount();
        matrixStackIn.m_85836_();
        if (entitylivingbaseIn.m_6162_()) {
            matrixStackIn.m_85841_(0.5f, 0.5f, 0.5f);
            matrixStackIn.m_85837_(0.0, 1.5, 0.0);
        }
        matrixStackIn.m_85836_();
        this.translateToBody(matrixStackIn);
        if (mushroomCount == 1 && !altOrder || mushroomCount >= 2) {
            matrixStackIn.m_85836_();
            matrixStackIn.m_85837_((double)0.2f, (double)-1.4f, 0.15);
            matrixStackIn.m_85841_(-1.0f, -1.0f, 1.0f);
            matrixStackIn.m_85837_(-0.5, -0.5, -0.5);
            blockrendererdispatcher.m_110912_(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            matrixStackIn.m_85849_();
        }
        if (mushroomCount == 1 && altOrder || mushroomCount >= 2) {
            matrixStackIn.m_85836_();
            matrixStackIn.m_85837_((double)-0.2f, -1.5, -0.2);
            matrixStackIn.m_85841_(-1.0f, -1.0f, 1.0f);
            matrixStackIn.m_85837_(-0.5, -0.5, -0.5);
            blockrendererdispatcher.m_110912_(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            matrixStackIn.m_85849_();
        }
        if (mushroomCount >= 3) {
            matrixStackIn.m_85836_();
            matrixStackIn.m_85837_((double)0.76f, (double)-0.4f, 0.1);
            matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(90.0f));
            matrixStackIn.m_85841_(-1.0f, -1.0f, 1.0f);
            matrixStackIn.m_85837_(-0.5, -0.5, -0.5);
            blockrendererdispatcher.m_110912_(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            matrixStackIn.m_85849_();
        }
        if (mushroomCount >= 4) {
            matrixStackIn.m_85836_();
            matrixStackIn.m_85837_((double)-0.76f, -1.0, 0.1);
            matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(-60.0f));
            matrixStackIn.m_85841_(-1.0f, -1.0f, 1.0f);
            matrixStackIn.m_85837_(-0.5, -0.5, -0.5);
            blockrendererdispatcher.m_110912_(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            matrixStackIn.m_85849_();
        }
        if (mushroomCount >= 5) {
            matrixStackIn.m_85836_();
            matrixStackIn.m_85837_((double)-0.76f, (double)-0.1f, 0.1);
            matrixStackIn.m_252781_(Axis.f_252403_.m_252977_(-100.0f));
            matrixStackIn.m_85841_(-1.0f, -1.0f, 1.0f);
            matrixStackIn.m_85837_(-0.5, -0.5, -0.5);
            blockrendererdispatcher.m_110912_(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            matrixStackIn.m_85849_();
        }
        matrixStackIn.m_85849_();
        matrixStackIn.m_85849_();
    }

    protected void translateToBody(PoseStack matrixStack) {
        ((ModelMungus)this.m_117386_()).root.translateAndRotate(matrixStack);
        ((ModelMungus)this.m_117386_()).body.translateAndRotate(matrixStack);
    }
}
