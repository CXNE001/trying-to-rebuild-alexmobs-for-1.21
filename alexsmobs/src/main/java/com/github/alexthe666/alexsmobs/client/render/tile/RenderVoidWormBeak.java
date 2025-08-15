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

import com.github.alexthe666.alexsmobs.block.BlockVoidWormBeak;
import com.github.alexthe666.alexsmobs.client.model.ModelVoidWormBeak;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityVoidWormBeak;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.Property;

public class RenderVoidWormBeak<T extends TileEntityVoidWormBeak>
implements BlockEntityRenderer<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/void_worm/void_worm_beak.png");
    private static final ModelVoidWormBeak HEAD_MODEL = new ModelVoidWormBeak();

    public RenderVoidWormBeak(BlockEntityRendererProvider.Context rendererDispatcherIn) {
    }

    public void render(T tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.m_85836_();
        Direction dir = (Direction)tileEntityIn.m_58900_().m_61143_((Property)BlockVoidWormBeak.FACING);
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
        matrixStackIn.m_252880_(0.0f, -0.01f, 0.0f);
        HEAD_MODEL.renderBeak((TileEntityVoidWormBeak)((Object)tileEntityIn), partialTicks);
        HEAD_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.m_85849_();
        matrixStackIn.m_85849_();
    }
}
