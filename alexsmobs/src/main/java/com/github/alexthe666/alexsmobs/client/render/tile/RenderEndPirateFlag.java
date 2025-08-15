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
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.block.state.properties.Property
 */
package com.github.alexthe666.alexsmobs.client.render.tile;

import com.github.alexthe666.alexsmobs.block.BlockEndPirateFlag;
import com.github.alexthe666.alexsmobs.client.model.ModelEndPirateFlag;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateFlag;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.Property;

public class RenderEndPirateFlag<T extends TileEntityEndPirateFlag>
implements BlockEntityRenderer<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/end_pirate/flag.png");
    private static final ModelEndPirateFlag FLAG_MODEL = new ModelEndPirateFlag();

    public RenderEndPirateFlag(BlockEntityRendererProvider.Context rendererDispatcherIn) {
    }

    public void render(T tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.m_85836_();
        Direction dir = (Direction)tileEntityIn.m_58900_().m_61143_((Property)BlockEndPirateFlag.FACING);
        switch (dir) {
            case NORTH: {
                matrixStackIn.m_85837_(0.5, 1.5, 0.5);
                break;
            }
            case EAST: {
                matrixStackIn.m_252880_(0.5f, 1.5f, 0.5f);
                break;
            }
            case SOUTH: {
                matrixStackIn.m_85837_(0.5, 1.5, 0.5);
                break;
            }
            case WEST: {
                matrixStackIn.m_252880_(0.5f, 1.5f, 0.5f);
            }
        }
        matrixStackIn.m_252781_(dir.m_122424_().m_253075_());
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(90.0f));
        matrixStackIn.m_252781_(Axis.f_252392_.m_252977_(dir.m_122434_() == Direction.Axis.Y ? -90.0f : 90.0f));
        matrixStackIn.m_85836_();
        FLAG_MODEL.renderFlag((TileEntityEndPirateFlag)((Object)tileEntityIn), partialTicks);
        FLAG_MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.m_85849_();
        matrixStackIn.m_85849_();
    }
}
