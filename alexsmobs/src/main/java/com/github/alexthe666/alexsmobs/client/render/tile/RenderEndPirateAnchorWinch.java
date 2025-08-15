/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.blockentity.BlockEntityRenderer
 *  net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider$Context
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.block.state.properties.Property
 */
package com.github.alexthe666.alexsmobs.client.render.tile;

import com.github.alexthe666.alexsmobs.block.BlockEndPirateAnchorWinch;
import com.github.alexthe666.alexsmobs.client.model.ModelEndPirateAnchorChain;
import com.github.alexthe666.alexsmobs.client.model.ModelEndPirateAnchorWinch;
import com.github.alexthe666.alexsmobs.client.render.tile.RenderEndPirateAnchor;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateAnchorWinch;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.Property;

public class RenderEndPirateAnchorWinch<T extends TileEntityEndPirateAnchorWinch>
implements BlockEntityRenderer<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/end_pirate/anchor_winch.png");
    private static final ResourceLocation TEXTURE_CHAIN = new ResourceLocation("alexsmobs:textures/entity/end_pirate/anchor_chain.png");
    private static final ModelEndPirateAnchorWinch WINCH_MODEL = new ModelEndPirateAnchorWinch();
    private static final ModelEndPirateAnchorChain CHAIN_MODEL = new ModelEndPirateAnchorChain();

    public RenderEndPirateAnchorWinch(BlockEntityRendererProvider.Context rendererDispatcherIn) {
    }

    public void render(T tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.m_85836_();
        boolean east = (Boolean)tileEntityIn.m_58900_().m_61143_((Property)BlockEndPirateAnchorWinch.EASTORWEST);
        matrixStackIn.m_252880_(0.5f, 1.5f, 0.5f);
        matrixStackIn.m_85836_();
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(180.0f));
        if (east) {
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(90.0f));
        }
        boolean flag = false;
        matrixStackIn.m_85836_();
        if (!((TileEntityEndPirateAnchorWinch)((Object)tileEntityIn)).isAnchorEW()) {
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(90.0f));
        }
        float bottomOfChain = ((TileEntityEndPirateAnchorWinch)((Object)tileEntityIn)).getChainLength(partialTicks);
        for (float i = 0.0f; i < ((TileEntityEndPirateAnchorWinch)((Object)tileEntityIn)).getChainLengthForRender(); i += 0.5f) {
            matrixStackIn.m_85836_();
            float moveDown = Math.max(bottomOfChain - i, 0.0f);
            matrixStackIn.m_252880_(0.0f, 0.1f + moveDown, 0.0f);
            if (flag) {
                matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(90.0f));
            }
            if (moveDown <= 1.0f) {
                float modulatedScale = 0.5f + moveDown * 0.5f;
                matrixStackIn.m_252880_(0.0f, (1.0f - moveDown) * 0.5f, 0.0f);
                matrixStackIn.m_85841_(modulatedScale, modulatedScale, modulatedScale);
            }
            CHAIN_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110452_((ResourceLocation)TEXTURE_CHAIN)), combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
            CHAIN_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110488_((ResourceLocation)TEXTURE_CHAIN)), combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
            matrixStackIn.m_85849_();
            flag = !flag;
        }
        matrixStackIn.m_85849_();
        WINCH_MODEL.renderAnchor((TileEntityEndPirateAnchorWinch)((Object)tileEntityIn), partialTicks, east);
        WINCH_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110452_((ResourceLocation)TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
        WINCH_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110488_((ResourceLocation)TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.m_85849_();
        matrixStackIn.m_85849_();
        if (((TileEntityEndPirateAnchorWinch)((Object)tileEntityIn)).hasAnchor()) {
            matrixStackIn.m_85836_();
            matrixStackIn.m_252880_(0.5f, -1.5f - bottomOfChain, 0.5f);
            matrixStackIn.m_85836_();
            matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(180.0f));
            if (((TileEntityEndPirateAnchorWinch)((Object)tileEntityIn)).isAnchorEW()) {
                matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(90.0f));
            }
            RenderEndPirateAnchor.ANCHOR_MODEL.resetToDefaultPose();
            RenderEndPirateAnchor.ANCHOR_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110452_((ResourceLocation)RenderEndPirateAnchor.TEXTURE_ANCHOR)), combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
            RenderEndPirateAnchor.ANCHOR_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110488_((ResourceLocation)RenderEndPirateAnchor.TEXTURE_ANCHOR_GLOW)), combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
            matrixStackIn.m_85849_();
            matrixStackIn.m_85849_();
        }
    }

    public boolean shouldRenderOffScreen(T entity) {
        return true;
    }

    public int m_142163_() {
        return 256;
    }
}
