/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.BufferBuilder
 *  com.mojang.blaze3d.vertex.DefaultVertexFormat
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.Tesselator
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.blaze3d.vertex.VertexFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$Mode
 *  it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap
 *  net.minecraft.Util
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Font
 *  net.minecraft.client.gui.Font$DisplayMode
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.MultiBufferSource$BufferSource
 *  net.minecraft.client.renderer.RenderStateShard$DepthTestStateShard
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.RenderType$CompositeState
 *  net.minecraft.client.renderer.entity.EntityRenderDispatcher
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.FormattedText
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.world.phys.AABB
 *  org.joml.Matrix4f
 */
package com.github.alexthe666.citadel.client.render.pathfinding;

import com.github.alexthe666.citadel.client.render.pathfinding.UiRenderMacros;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.phys.AABB;
import org.joml.Matrix4f;

public class WorldRenderMacros
extends UiRenderMacros {
    private static final int MAX_DEBUG_TEXT_RENDER_DIST_SQUARED = 1024;
    public static final RenderType LINES = RenderTypes.LINES;
    public static final RenderType LINES_WITH_WIDTH = RenderTypes.LINES_WITH_WIDTH;
    public static final RenderType GLINT_LINES = RenderTypes.GLINT_LINES;
    public static final RenderType GLINT_LINES_WITH_WIDTH = RenderTypes.GLINT_LINES_WITH_WIDTH;
    public static final RenderType COLORED_TRIANGLES = RenderTypes.COLORED_TRIANGLES;
    public static final RenderType COLORED_TRIANGLES_NC_ND = RenderTypes.COLORED_TRIANGLES_NC_ND;
    private static final LinkedList<RenderType> buffers = new LinkedList();
    private static MultiBufferSource.BufferSource bufferSource;

    public static void putBufferHead(RenderType bufferType) {
        buffers.addFirst(bufferType);
        bufferSource = null;
    }

    public static void putBufferTail(RenderType bufferType) {
        buffers.addLast(bufferType);
        bufferSource = null;
    }

    public static void putBufferBefore(RenderType bufferType, RenderType putBefore) {
        buffers.add(Math.max(0, buffers.indexOf(putBefore)), bufferType);
        bufferSource = null;
    }

    public static void putBufferAfter(RenderType bufferType, RenderType putAfter) {
        int index = buffers.indexOf(putAfter);
        if (index == -1) {
            buffers.add(bufferType);
        } else {
            buffers.add(index + 1, bufferType);
        }
        bufferSource = null;
    }

    public static MultiBufferSource.BufferSource getBufferSource() {
        if (bufferSource == null) {
            bufferSource = MultiBufferSource.m_109900_((Map)((Map)Util.m_137469_((Object)new Object2ObjectLinkedOpenHashMap(), map -> buffers.forEach(type -> map.put(type, (Object)new BufferBuilder(type.m_110507_()))))), (BufferBuilder)Tesselator.m_85913_().m_85915_());
        }
        return bufferSource;
    }

    public static void renderBlackLineBox(MultiBufferSource.BufferSource buffer, PoseStack ps, BlockPos posA, BlockPos posB, float lineWidth) {
        WorldRenderMacros.renderLineBox(buffer.m_6299_(LINES_WITH_WIDTH), ps, posA, posB, 0, 0, 0, 255, lineWidth);
    }

    public static void renderRedGlintLineBox(MultiBufferSource.BufferSource buffer, PoseStack ps, BlockPos posA, BlockPos posB, float lineWidth) {
        WorldRenderMacros.renderLineBox(buffer.m_6299_(GLINT_LINES_WITH_WIDTH), ps, posA, posB, 255, 0, 0, 255, lineWidth);
    }

    public static void renderWhiteLineBox(MultiBufferSource.BufferSource buffer, PoseStack ps, BlockPos posA, BlockPos posB, float lineWidth) {
        WorldRenderMacros.renderLineBox(buffer.m_6299_(LINES_WITH_WIDTH), ps, posA, posB, 255, 255, 255, 255, lineWidth);
    }

    public static void renderLineAABB(VertexConsumer buffer, PoseStack ps, AABB aabb, int argbColor, float lineWidth) {
        WorldRenderMacros.renderLineAABB(buffer, ps, aabb, argbColor >> 16 & 0xFF, argbColor >> 8 & 0xFF, argbColor & 0xFF, argbColor >> 24 & 0xFF, lineWidth);
    }

    public static void renderLineAABB(VertexConsumer buffer, PoseStack ps, AABB aabb, int red, int green, int blue, int alpha, float lineWidth) {
        WorldRenderMacros.renderLineBox(buffer, ps, (float)aabb.f_82288_, (float)aabb.f_82289_, (float)aabb.f_82290_, (float)aabb.f_82291_, (float)aabb.f_82292_, (float)aabb.f_82293_, red, green, blue, alpha, lineWidth);
    }

    public static void renderLineBox(VertexConsumer buffer, PoseStack ps, BlockPos pos, int argbColor, float lineWidth) {
        WorldRenderMacros.renderLineBox(buffer, ps, pos, pos, argbColor >> 16 & 0xFF, argbColor >> 8 & 0xFF, argbColor & 0xFF, argbColor >> 24 & 0xFF, lineWidth);
    }

    public static void renderLineBox(VertexConsumer buffer, PoseStack ps, BlockPos posA, BlockPos posB, int argbColor, float lineWidth) {
        WorldRenderMacros.renderLineBox(buffer, ps, posA, posB, argbColor >> 16 & 0xFF, argbColor >> 8 & 0xFF, argbColor & 0xFF, argbColor >> 24 & 0xFF, lineWidth);
    }

    public static void renderLineBox(VertexConsumer buffer, PoseStack ps, BlockPos posA, BlockPos posB, int red, int green, int blue, int alpha, float lineWidth) {
        WorldRenderMacros.renderLineBox(buffer, ps, Math.min(posA.m_123341_(), posB.m_123341_()), Math.min(posA.m_123342_(), posB.m_123342_()), Math.min(posA.m_123343_(), posB.m_123343_()), Math.max(posA.m_123341_(), posB.m_123341_()) + 1, Math.max(posA.m_123342_(), posB.m_123342_()) + 1, Math.max(posA.m_123343_(), posB.m_123343_()) + 1, red, green, blue, alpha, lineWidth);
    }

    public static void renderLineBox(VertexConsumer buffer, PoseStack ps, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int red, int green, int blue, int alpha, float lineWidth) {
        if (alpha == 0) {
            return;
        }
        float halfLine = lineWidth / 2.0f;
        float minX2 = (minX -= halfLine) + lineWidth;
        float minY2 = (minY -= halfLine) + lineWidth;
        float minZ2 = (minZ -= halfLine) + lineWidth;
        float maxX2 = (maxX += halfLine) - lineWidth;
        float maxY2 = (maxY += halfLine) - lineWidth;
        float maxZ2 = (maxZ += halfLine) - lineWidth;
        Matrix4f m = ps.m_85850_().m_252922_();
        buffer.m_7404_(red, green, blue, alpha);
        WorldRenderMacros.populateRenderLineBox(minX, minY, minZ, minX2, minY2, minZ2, maxX, maxY, maxZ, maxX2, maxY2, maxZ2, m, buffer);
        buffer.m_141991_();
    }

    public static void populateRenderLineBox(float minX, float minY, float minZ, float minX2, float minY2, float minZ2, float maxX, float maxY, float maxZ, float maxX2, float maxY2, float maxZ2, Matrix4f m, VertexConsumer buf) {
        buf.m_252986_(m, minX, minY, minZ).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ).m_5752_();
        buf.m_252986_(m, maxX, minY, minZ).m_5752_();
        buf.m_252986_(m, minX, minY, minZ).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ).m_5752_();
        buf.m_252986_(m, minX, minY, minZ).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ).m_5752_();
        buf.m_252986_(m, minX, minY, minZ).m_5752_();
        buf.m_252986_(m, minX, maxY, minZ).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, minZ).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ).m_5752_();
        buf.m_252986_(m, minX, maxY, minZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, minZ).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, minZ).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, minZ).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, minZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, minZ).m_5752_();
        buf.m_252986_(m, maxX, minY, minZ).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ).m_5752_();
        buf.m_252986_(m, minX, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, minX, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, minX, minY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, minY, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY, minZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX, minY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY, minZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY, minZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY, minZ2).m_5752_();
        buf.m_252986_(m, minX, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, minY, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY, maxZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX, minY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, minY, maxZ).m_5752_();
        buf.m_252986_(m, maxX2, minY2, maxZ).m_5752_();
        buf.m_252986_(m, minX, minY, maxZ).m_5752_();
        buf.m_252986_(m, maxX2, minY2, maxZ).m_5752_();
        buf.m_252986_(m, minX2, minY2, maxZ).m_5752_();
        buf.m_252986_(m, minX, minY, maxZ).m_5752_();
        buf.m_252986_(m, minX2, minY2, maxZ).m_5752_();
        buf.m_252986_(m, minX2, maxY2, maxZ).m_5752_();
        buf.m_252986_(m, minX, minY, maxZ).m_5752_();
        buf.m_252986_(m, minX2, maxY2, maxZ).m_5752_();
        buf.m_252986_(m, minX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, minX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, minX2, maxY2, maxZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, minX2, maxY2, maxZ).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, maxZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, maxZ).m_5752_();
        buf.m_252986_(m, maxX2, minY2, maxZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, maxX2, minY2, maxZ).m_5752_();
        buf.m_252986_(m, maxX, minY, maxZ).m_5752_();
        buf.m_252986_(m, minX, minY, minZ).m_5752_();
        buf.m_252986_(m, minX, minY, maxZ).m_5752_();
        buf.m_252986_(m, minX, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX, minY, minZ).m_5752_();
        buf.m_252986_(m, minX, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX, minY2, minZ2).m_5752_();
        buf.m_252986_(m, minX, minY, minZ).m_5752_();
        buf.m_252986_(m, minX, minY2, minZ2).m_5752_();
        buf.m_252986_(m, minX, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, minX, minY, minZ).m_5752_();
        buf.m_252986_(m, minX, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, minX, maxY, minZ).m_5752_();
        buf.m_252986_(m, minX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, minX, maxY, minZ).m_5752_();
        buf.m_252986_(m, minX, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, minX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, minX, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, minX, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, minX, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, minX, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX, minY, maxZ).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, minY, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, minY, minZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, minY, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, maxZ).m_5752_();
        buf.m_252986_(m, minX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, maxZ).m_5752_();
        buf.m_252986_(m, minX2, minY2, maxZ).m_5752_();
        buf.m_252986_(m, minX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY, minZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, minZ).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, minZ).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, maxZ).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, maxZ).m_5752_();
        buf.m_252986_(m, maxX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, maxZ).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY, maxZ2).m_5752_();
        buf.m_252986_(m, maxX, minY, minZ).m_5752_();
        buf.m_252986_(m, maxX, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX, minY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, minY, minZ).m_5752_();
        buf.m_252986_(m, maxX, minY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX, minY, minZ).m_5752_();
        buf.m_252986_(m, maxX, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX, minY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX, minY, minZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, minZ).m_5752_();
        buf.m_252986_(m, maxX, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY, minZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, minY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX, minY, minZ).m_5752_();
        buf.m_252986_(m, minX2, minY, maxZ2).m_5752_();
        buf.m_252986_(m, minX, minY, maxZ).m_5752_();
        buf.m_252986_(m, minX, minY, minZ).m_5752_();
        buf.m_252986_(m, minX2, minY, minZ2).m_5752_();
        buf.m_252986_(m, minX2, minY, maxZ2).m_5752_();
        buf.m_252986_(m, minX, minY, minZ).m_5752_();
        buf.m_252986_(m, maxX2, minY, minZ2).m_5752_();
        buf.m_252986_(m, minX2, minY, minZ2).m_5752_();
        buf.m_252986_(m, minX, minY, minZ).m_5752_();
        buf.m_252986_(m, maxX, minY, minZ).m_5752_();
        buf.m_252986_(m, maxX2, minY, minZ2).m_5752_();
        buf.m_252986_(m, maxX, minY, maxZ).m_5752_();
        buf.m_252986_(m, maxX2, minY, minZ2).m_5752_();
        buf.m_252986_(m, maxX, minY, minZ).m_5752_();
        buf.m_252986_(m, maxX, minY, maxZ).m_5752_();
        buf.m_252986_(m, maxX2, minY, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY, minZ2).m_5752_();
        buf.m_252986_(m, maxX, minY, maxZ).m_5752_();
        buf.m_252986_(m, minX2, minY, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY, maxZ2).m_5752_();
        buf.m_252986_(m, maxX, minY, maxZ).m_5752_();
        buf.m_252986_(m, minX, minY, maxZ).m_5752_();
        buf.m_252986_(m, minX2, minY, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, minX, minY2, minZ2).m_5752_();
        buf.m_252986_(m, minX, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, minX, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, maxZ).m_5752_();
        buf.m_252986_(m, maxX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, maxZ).m_5752_();
        buf.m_252986_(m, minX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, minY2, maxZ).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX, minY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX, minY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, minZ).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, minZ).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, minX, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, maxZ).m_5752_();
        buf.m_252986_(m, minX2, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, maxZ).m_5752_();
        buf.m_252986_(m, minX2, maxY2, maxZ).m_5752_();
        buf.m_252986_(m, minX2, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, minZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY2, maxZ2).m_5752_();
        buf.m_252986_(m, minX, maxY, minZ).m_5752_();
        buf.m_252986_(m, minX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, minX2, maxY, maxZ2).m_5752_();
        buf.m_252986_(m, minX, maxY, minZ).m_5752_();
        buf.m_252986_(m, minX2, maxY, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY, minZ2).m_5752_();
        buf.m_252986_(m, minX, maxY, minZ).m_5752_();
        buf.m_252986_(m, minX2, maxY, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY, minZ2).m_5752_();
        buf.m_252986_(m, minX, maxY, minZ).m_5752_();
        buf.m_252986_(m, maxX2, maxY, minZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY, minZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, minZ).m_5752_();
        buf.m_252986_(m, maxX2, maxY, minZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, maxX2, maxY, minZ2).m_5752_();
        buf.m_252986_(m, maxX2, maxY, maxZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, maxX2, maxY, maxZ2).m_5752_();
        buf.m_252986_(m, minX2, maxY, maxZ2).m_5752_();
        buf.m_252986_(m, maxX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, minX2, maxY, maxZ2).m_5752_();
        buf.m_252986_(m, minX, maxY, maxZ).m_5752_();
    }

    public static void renderBox(MultiBufferSource.BufferSource buffer, PoseStack ps, BlockPos posA, BlockPos posB, int argbColor) {
        WorldRenderMacros.renderBox(buffer.m_6299_(COLORED_TRIANGLES), ps, posA, posB, argbColor >> 16 & 0xFF, argbColor >> 8 & 0xFF, argbColor & 0xFF, argbColor >> 24 & 0xFF);
    }

    public static void renderBox(VertexConsumer buffer, PoseStack ps, BlockPos posA, BlockPos posB, int red, int green, int blue, int alpha) {
        if (alpha == 0) {
            return;
        }
        float minX = Math.min(posA.m_123341_(), posB.m_123341_());
        float minY = Math.min(posA.m_123342_(), posB.m_123342_());
        float minZ = Math.min(posA.m_123343_(), posB.m_123343_());
        float maxX = Math.max(posA.m_123341_(), posB.m_123341_()) + 1;
        float maxY = Math.max(posA.m_123342_(), posB.m_123342_()) + 1;
        float maxZ = Math.max(posA.m_123343_(), posB.m_123343_()) + 1;
        Matrix4f m = ps.m_85850_().m_252922_();
        buffer.m_7404_(red, green, blue, alpha);
        WorldRenderMacros.populateCuboid(minX, minY, minZ, maxX, maxY, maxZ, m, buffer);
        buffer.m_141991_();
    }

    public static void populateCuboid(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, Matrix4f m, VertexConsumer buf) {
        buf.m_252986_(m, minX, maxY, minZ).m_5752_();
        buf.m_252986_(m, maxX, minY, minZ).m_5752_();
        buf.m_252986_(m, minX, minY, minZ).m_5752_();
        buf.m_252986_(m, minX, maxY, minZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, minZ).m_5752_();
        buf.m_252986_(m, maxX, minY, minZ).m_5752_();
        buf.m_252986_(m, minX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, minX, minY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, minY, maxZ).m_5752_();
        buf.m_252986_(m, minX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, minY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, minX, minY, maxZ).m_5752_();
        buf.m_252986_(m, minX, minY, minZ).m_5752_();
        buf.m_252986_(m, maxX, minY, minZ).m_5752_();
        buf.m_252986_(m, minX, minY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, minY, minZ).m_5752_();
        buf.m_252986_(m, maxX, minY, maxZ).m_5752_();
        buf.m_252986_(m, minX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, minZ).m_5752_();
        buf.m_252986_(m, minX, maxY, minZ).m_5752_();
        buf.m_252986_(m, minX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, minZ).m_5752_();
        buf.m_252986_(m, minX, minY, maxZ).m_5752_();
        buf.m_252986_(m, minX, maxY, minZ).m_5752_();
        buf.m_252986_(m, minX, minY, minZ).m_5752_();
        buf.m_252986_(m, minX, minY, maxZ).m_5752_();
        buf.m_252986_(m, minX, maxY, maxZ).m_5752_();
        buf.m_252986_(m, minX, maxY, minZ).m_5752_();
        buf.m_252986_(m, maxX, minY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, minY, minZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, minZ).m_5752_();
        buf.m_252986_(m, maxX, minY, maxZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, minZ).m_5752_();
        buf.m_252986_(m, maxX, maxY, maxZ).m_5752_();
    }

    public static void renderFillRectangle(MultiBufferSource.BufferSource buffer, PoseStack ps, int x, int y, int z, int w, int h, int argbColor) {
        WorldRenderMacros.populateRectangle(x, y, z, w, h, argbColor >> 16 & 0xFF, argbColor >> 8 & 0xFF, argbColor & 0xFF, argbColor >> 24 & 0xFF, buffer.m_6299_(COLORED_TRIANGLES_NC_ND), ps.m_85850_().m_252922_());
    }

    public static void populateRectangle(int x, int y, int z, int w, int h, int red, int green, int blue, int alpha, VertexConsumer buffer, Matrix4f m) {
        if (alpha == 0) {
            return;
        }
        buffer.m_252986_(m, (float)x, (float)y, (float)z).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)x, (float)(y + h), (float)z).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)(y + h), (float)z).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)x, (float)y, (float)z).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)(y + h), (float)z).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)y, (float)z).m_6122_(red, green, blue, alpha).m_5752_();
    }

    public static void renderDebugText(BlockPos pos, List<String> text, PoseStack matrixStack, boolean forceWhite, int mergeEveryXListElements, MultiBufferSource buffer) {
        if (mergeEveryXListElements < 1) {
            throw new IllegalArgumentException("mergeEveryXListElements is less than 1");
        }
        EntityRenderDispatcher erm = Minecraft.m_91087_().m_91290_();
        int cap = text.size();
        if (cap > 0 && erm.m_114378_((double)pos.m_123341_(), (double)pos.m_123342_(), (double)pos.m_123343_()) <= 1024.0) {
            Font fontrenderer = Minecraft.m_91087_().f_91062_;
            matrixStack.m_85836_();
            matrixStack.m_85837_((double)pos.m_123341_() + 0.5, (double)pos.m_123342_() + 0.75, (double)pos.m_123343_() + 0.5);
            matrixStack.m_252781_(erm.m_253208_());
            matrixStack.m_85841_(-0.014f, -0.014f, 0.014f);
            matrixStack.m_85837_(0.0, 18.0, 0.0);
            float backgroundTextOpacity = Minecraft.m_91087_().f_91066_.m_92141_(0.25f);
            int alphaMask = (int)(backgroundTextOpacity * 255.0f) << 24;
            Matrix4f rawPosMatrix = matrixStack.m_85850_().m_252922_();
            for (int i = 0; i < cap; i += mergeEveryXListElements) {
                MutableComponent renderText = Component.m_237113_((String)(mergeEveryXListElements == 1 ? text.get(i) : text.subList(i, Math.min(i + mergeEveryXListElements, cap)).toString()));
                float textCenterShift = -fontrenderer.m_92852_((FormattedText)renderText) / 2;
                fontrenderer.m_272077_((Component)renderText, textCenterShift, 0.0f, forceWhite ? -1 : 0x20FFFFFF, false, rawPosMatrix, buffer, Font.DisplayMode.SEE_THROUGH, alphaMask, 0xF000F0);
                if (!forceWhite) {
                    fontrenderer.m_272077_((Component)renderText, textCenterShift, 0.0f, -1, false, rawPosMatrix, buffer, Font.DisplayMode.NORMAL, 0, 0xF000F0);
                }
                Objects.requireNonNull(fontrenderer);
                matrixStack.m_85837_(0.0, (double)(9 + 1), 0.0);
            }
            matrixStack.m_85849_();
        }
    }

    static {
        WorldRenderMacros.putBufferTail(COLORED_TRIANGLES);
        WorldRenderMacros.putBufferTail(LINES);
        WorldRenderMacros.putBufferTail(LINES_WITH_WIDTH);
        WorldRenderMacros.putBufferTail(GLINT_LINES);
        WorldRenderMacros.putBufferTail(GLINT_LINES_WITH_WIDTH);
        WorldRenderMacros.putBufferTail(COLORED_TRIANGLES_NC_ND);
    }

    private static final class RenderTypes
    extends RenderType {
        private static final RenderType GLINT_LINES = RenderTypes.m_173215_((String)"structurize_glint_lines", (VertexFormat)DefaultVertexFormat.f_85815_, (VertexFormat.Mode)VertexFormat.Mode.DEBUG_LINES, (int)4096, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_(f_110147_).m_173292_(f_173104_).m_110685_(f_110137_).m_110663_(f_110111_).m_110661_(f_110110_).m_110671_(f_110153_).m_110677_(f_110155_).m_110669_(f_110117_).m_110675_(f_110123_).m_110683_(f_110148_).m_110687_(f_110115_).m_110691_(false));
        private static final RenderType GLINT_LINES_WITH_WIDTH = RenderTypes.m_173215_((String)"structurize_glint_lines_with_width", (VertexFormat)DefaultVertexFormat.f_85815_, (VertexFormat.Mode)VertexFormat.Mode.TRIANGLES, (int)8192, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_(f_110147_).m_173292_(f_173104_).m_110685_(f_110137_).m_110663_(AlwaysDepthTestStateShard.ALWAYS_DEPTH_TEST).m_110661_(f_110158_).m_110671_(f_110153_).m_110677_(f_110155_).m_110669_(f_110117_).m_110675_(f_110123_).m_110683_(f_110148_).m_110687_(f_110114_).m_110691_(false));
        private static final RenderType LINES = RenderTypes.m_173215_((String)"structurize_lines", (VertexFormat)DefaultVertexFormat.f_85815_, (VertexFormat.Mode)VertexFormat.Mode.DEBUG_LINES, (int)16384, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_(f_110147_).m_173292_(f_173104_).m_110685_(f_110139_).m_110663_(f_110113_).m_110661_(f_110110_).m_110671_(f_110153_).m_110677_(f_110155_).m_110669_(f_110117_).m_110675_(f_110123_).m_110683_(f_110148_).m_110687_(f_110115_).m_110691_(false));
        private static final RenderType LINES_WITH_WIDTH = RenderTypes.m_173215_((String)"structurize_lines_with_width", (VertexFormat)DefaultVertexFormat.f_85815_, (VertexFormat.Mode)VertexFormat.Mode.TRIANGLES, (int)8192, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_(f_110147_).m_173292_(f_173104_).m_110685_(f_110139_).m_110663_(f_110113_).m_110661_(f_110158_).m_110671_(f_110153_).m_110677_(f_110155_).m_110669_(f_110117_).m_110675_(f_110123_).m_110683_(f_110148_).m_110687_(f_110114_).m_110691_(false));
        private static final RenderType COLORED_TRIANGLES = RenderTypes.m_173215_((String)"structurize_colored_triangles", (VertexFormat)DefaultVertexFormat.f_85815_, (VertexFormat.Mode)VertexFormat.Mode.TRIANGLES, (int)8192, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_(f_110147_).m_173292_(f_173104_).m_110685_(f_110139_).m_110663_(f_110113_).m_110661_(f_110158_).m_110671_(f_110153_).m_110677_(f_110155_).m_110669_(f_110117_).m_110675_(f_110123_).m_110683_(f_110148_).m_110687_(f_110114_).m_110691_(false));
        private static final RenderType COLORED_TRIANGLES_NC_ND = RenderTypes.m_173215_((String)"structurize_colored_triangles_nc_nd", (VertexFormat)DefaultVertexFormat.f_85815_, (VertexFormat.Mode)VertexFormat.Mode.TRIANGLES, (int)4096, (boolean)false, (boolean)false, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173290_(f_110147_).m_173292_(f_173104_).m_110685_(f_110139_).m_110663_(f_110111_).m_110661_(f_110110_).m_110671_(f_110153_).m_110677_(f_110155_).m_110669_(f_110117_).m_110675_(f_110123_).m_110683_(f_110148_).m_110687_(f_110115_).m_110691_(false));

        private RenderTypes(String nameIn, VertexFormat formatIn, VertexFormat.Mode drawModeIn, int bufferSizeIn, boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
            super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
            throw new IllegalStateException();
        }
    }

    public static class AlwaysDepthTestStateShard
    extends RenderStateShard.DepthTestStateShard {
        public static final RenderStateShard.DepthTestStateShard ALWAYS_DEPTH_TEST = new AlwaysDepthTestStateShard();

        private AlwaysDepthTestStateShard() {
            super("true_always", -1);
            this.f_110131_ = () -> {
                RenderSystem.enableDepthTest();
                RenderSystem.depthFunc((int)519);
            };
        }
    }
}
