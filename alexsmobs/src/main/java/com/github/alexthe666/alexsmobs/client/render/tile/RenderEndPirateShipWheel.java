/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.blockentity.BlockEntityRenderer
 *  net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider$Context
 *  net.minecraft.core.Direction
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.block.state.properties.Property
 */
package com.github.alexthe666.alexsmobs.client.render.tile;

import com.github.alexthe666.alexsmobs.block.BlockEndPirateShipWheel;
import com.github.alexthe666.alexsmobs.client.model.ModelEndPirateShipWheel;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateShipWheel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.Property;

public class RenderEndPirateShipWheel<T extends TileEntityEndPirateShipWheel>
implements BlockEntityRenderer<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/end_pirate/ship_wheel.png");
    private static final ResourceLocation TEXTURE_GLOW = new ResourceLocation("alexsmobs:textures/entity/end_pirate/ship_wheel_glow.png");
    private static final ModelEndPirateShipWheel WHEEL_MODEL = new ModelEndPirateShipWheel();

    public RenderEndPirateShipWheel(BlockEntityRendererProvider.Context rendererDispatcherIn) {
    }

    public void render(T tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.m_85836_();
        Direction dir = (Direction)tileEntityIn.m_58900_().m_61143_((Property)BlockEndPirateShipWheel.FACING);
        switch (dir) {
            case UP: {
                matrixStackIn.m_252880_(0.5f, 1.5f, 0.5f);
                break;
            }
            case DOWN: {
                matrixStackIn.m_252880_(0.5f, -0.5f, 0.5f);
                break;
            }
            case NORTH: {
                matrixStackIn.m_85837_(0.5, 0.5, -0.5);
                break;
            }
            case EAST: {
                matrixStackIn.m_252880_(1.5f, 0.5f, 0.5f);
                break;
            }
            case SOUTH: {
                matrixStackIn.m_85837_(0.5, 0.5, 1.5);
                break;
            }
            case WEST: {
                matrixStackIn.m_252880_(-0.5f, 0.5f, 0.5f);
            }
        }
        matrixStackIn.m_252781_(dir.m_122424_().m_253075_());
        matrixStackIn.m_85836_();
        WHEEL_MODEL.renderWheel((TileEntityEndPirateShipWheel)((Object)tileEntityIn), partialTicks);
        WHEEL_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
        WHEEL_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(AMRenderTypes.m_110458_((ResourceLocation)TEXTURE_GLOW)), 240, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.m_85849_();
        matrixStackIn.m_85849_();
    }
}
