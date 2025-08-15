/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.blockentity.BlockEntityRenderer
 *  net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider$Context
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.core.Direction
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.block.state.properties.Property
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 */
package com.github.alexthe666.alexsmobs.client.render.tile;

import com.github.alexthe666.alexsmobs.block.BlockTransmutationTable;
import com.github.alexthe666.alexsmobs.client.model.ModelTransmutationTable;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityTransmutationTable;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.Property;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class RenderTransmutationTable<T extends TileEntityTransmutationTable>
implements BlockEntityRenderer<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/farseer/transmutation_table.png");
    private static final ResourceLocation OVERLAY = new ResourceLocation("alexsmobs:textures/entity/farseer/transmutation_table_overlay.png");
    private static final ResourceLocation GLOW_TEXTURE = new ResourceLocation("alexsmobs:textures/entity/farseer/transmutation_table_glow.png");
    private static final ModelTransmutationTable MODEL = new ModelTransmutationTable(0.0f);
    private static final ModelTransmutationTable OVERLAY_MODEL = new ModelTransmutationTable(0.01f);

    public RenderTransmutationTable(BlockEntityRendererProvider.Context rendererDispatcherIn) {
    }

    public void render(T tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.m_85836_();
        Direction dir = (Direction)tileEntityIn.m_58900_().m_61143_((Property)BlockTransmutationTable.FACING);
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
        float ageInTicks = partialTicks + (float)((TileEntityTransmutationTable)((Object)tileEntityIn)).ticksExisted;
        matrixStackIn.m_252781_(dir.m_122424_().m_253075_());
        matrixStackIn.m_252781_(Axis.f_252529_.m_252977_(90.0f));
        matrixStackIn.m_85836_();
        MODEL.animate((TileEntityTransmutationTable)((Object)tileEntityIn), partialTicks);
        MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(RenderType.m_110473_((ResourceLocation)TEXTURE)), combinedLightIn, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 1.0f);
        MODEL.m_7695_(matrixStackIn, bufferIn.m_6299_(AMRenderTypes.getEyesAlphaEnabled(GLOW_TEXTURE)), 240, combinedOverlayIn, 1.0f, 1.0f, 1.0f, 0.5f + (float)Math.sin(ageInTicks * 0.05f) * 0.25f);
        VertexConsumer staticyOverlay = AMRenderTypes.createMergedVertexConsumer(bufferIn.m_6299_(AMRenderTypes.STATIC_PORTAL), bufferIn.m_6299_(RenderType.m_110458_((ResourceLocation)OVERLAY)));
        OVERLAY_MODEL.animate((TileEntityTransmutationTable)((Object)tileEntityIn), partialTicks);
        OVERLAY_MODEL.m_7695_(matrixStackIn, staticyOverlay, combinedLightIn, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.m_85849_();
        matrixStackIn.m_85849_();
    }

    private static void vertex(VertexConsumer p_114090_, Matrix4f p_114091_, Matrix3f p_114092_, int p_114093_, float p_114094_, float p_114095_, int p_114096_, int p_114097_) {
        p_114090_.m_252986_(p_114091_, p_114094_, p_114095_, 0.0f).m_6122_(255, 255, 255, 100).m_7421_((float)p_114096_, (float)p_114097_).m_86008_(OverlayTexture.f_118083_).m_85969_(p_114093_).m_252939_(p_114092_, 0.0f, 1.0f, 0.0f).m_5752_();
    }
}
