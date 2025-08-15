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

import com.github.alexthe666.alexsmobs.block.BlockEndPirateAnchor;
import com.github.alexthe666.alexsmobs.client.model.ModelEndPirateAnchor;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateAnchor;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.Property;

public class RenderEndPirateAnchor<T extends TileEntityEndPirateAnchor>
implements BlockEntityRenderer<T> {
    protected static final ResourceLocation TEXTURE_ANCHOR = new ResourceLocation("alexsmobs:textures/entity/end_pirate/anchor.png");
    protected static final ResourceLocation TEXTURE_ANCHOR_GLOW = new ResourceLocation("alexsmobs:textures/entity/end_pirate/anchor_glow.png");
    protected static final ModelEndPirateAnchor ANCHOR_MODEL = new ModelEndPirateAnchor();

    public RenderEndPirateAnchor(BlockEntityRendererProvider.Context rendererDispatcherIn) {
    }

    public void render(T tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.m_85836_();
        boolean east = (Boolean)tileEntityIn.m_58900_().m_61143_((Property)BlockEndPirateAnchor.EASTORWEST);
        matrixStackIn.m_252880_(0.5f, 1.5f, 0.5f);
        matrixStackIn.m_85836_();
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(180.0f));
        if (east) {
            matrixStackIn.m_252781_(Axis.f_252436_.m_252977_(90.0f));
        }
        ANCHOR_MODEL.renderAnchor((TileEntityEndPirateAnchor)((Object)tileEntityIn), partialTicks, east);
        ANCHOR_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110452_((ResourceLocation)TEXTURE_ANCHOR)), combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
        ANCHOR_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110488_((ResourceLocation)TEXTURE_ANCHOR_GLOW)), combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.m_85849_();
        matrixStackIn.m_85849_();
    }

    public boolean shouldRenderOffScreen(T p_112306_) {
        return true;
    }

    public int m_142163_() {
        return 256;
    }
}
