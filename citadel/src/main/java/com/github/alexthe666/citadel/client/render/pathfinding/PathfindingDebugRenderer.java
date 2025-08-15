/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Font
 *  net.minecraft.client.gui.Font$DisplayMode
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.MultiBufferSource$BufferSource
 *  net.minecraft.client.renderer.RenderBuffers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.entity.Entity
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Matrix4f
 */
package com.github.alexthe666.citadel.client.render.pathfinding;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.client.render.pathfinding.WorldEventContext;
import com.github.alexthe666.citadel.client.render.pathfinding.WorldRenderMacros;
import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.MNode;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class PathfindingDebugRenderer {
    public static final RenderBuffers renderBuffers = new RenderBuffers();
    private static final MultiBufferSource.BufferSource renderBuffer = renderBuffers.m_110104_();
    public static Set<MNode> lastDebugNodesVisited = new HashSet<MNode>();
    public static Set<MNode> lastDebugNodesNotVisited = new HashSet<MNode>();
    public static Set<MNode> lastDebugNodesPath = new HashSet<MNode>();

    public static void render(WorldEventContext ctx) {
        try {
            for (MNode n : lastDebugNodesVisited) {
                PathfindingDebugRenderer.debugDrawNode(n, -65536, ctx);
            }
            for (MNode n : lastDebugNodesNotVisited) {
                PathfindingDebugRenderer.debugDrawNode(n, -16776961, ctx);
            }
            for (MNode n : lastDebugNodesPath) {
                if (n.isReachedByWorker()) {
                    PathfindingDebugRenderer.debugDrawNode(n, -39424, ctx);
                    continue;
                }
                PathfindingDebugRenderer.debugDrawNode(n, -16711936, ctx);
            }
        }
        catch (ConcurrentModificationException exc) {
            Citadel.LOGGER.catching((Throwable)exc);
        }
    }

    private static void debugDrawNode(MNode n, int argbColor, WorldEventContext ctx) {
        ctx.poseStack.m_85836_();
        ctx.poseStack.m_85837_((double)n.pos.m_123341_() + 0.375, (double)n.pos.m_123342_() + 0.375, (double)n.pos.m_123343_() + 0.375);
        Entity entity = Minecraft.m_91087_().m_91288_();
        if (n.pos.m_123314_((Vec3i)entity.m_20183_(), 5.0)) {
            PathfindingDebugRenderer.renderDebugText(n, ctx);
        }
        ctx.poseStack.m_85841_(0.25f, 0.25f, 0.25f);
        WorldRenderMacros.renderBox(ctx.bufferSource, ctx.poseStack, BlockPos.f_121853_, BlockPos.f_121853_, argbColor);
        if (n.parent != null) {
            Matrix4f lineMatrix = ctx.poseStack.m_85850_().m_252922_();
            float pdx = (float)(n.parent.pos.m_123341_() - n.pos.m_123341_()) + 0.125f;
            float pdy = (float)(n.parent.pos.m_123342_() - n.pos.m_123342_()) + 0.125f;
            float pdz = (float)(n.parent.pos.m_123343_() - n.pos.m_123343_()) + 0.125f;
            VertexConsumer buffer = ctx.bufferSource.m_6299_(WorldRenderMacros.LINES);
            buffer.m_252986_(lineMatrix, 0.5f, 0.5f, 0.5f).m_85950_(0.75f, 0.75f, 0.75f, 1.0f).m_5752_();
            buffer.m_252986_(lineMatrix, pdx / 0.25f, pdy / 0.25f, pdz / 0.25f).m_85950_(0.75f, 0.75f, 0.75f, 1.0f).m_5752_();
        }
        ctx.poseStack.m_85849_();
    }

    private static void renderDebugText(@NotNull MNode n, WorldEventContext ctx) {
        Font fontrenderer = Minecraft.m_91087_().f_91062_;
        String s1 = String.format("F: %.3f [%d]", n.getCost(), n.getCounterAdded());
        String s2 = String.format("G: %.3f [%d]", n.getScore(), n.getCounterVisited());
        int i = Math.max(fontrenderer.m_92895_(s1), fontrenderer.m_92895_(s2)) / 2;
        ctx.poseStack.m_85836_();
        ctx.poseStack.m_252880_(0.0f, 0.75f, 0.0f);
        ctx.poseStack.m_252781_(Minecraft.m_91087_().m_91290_().m_253208_());
        ctx.poseStack.m_85841_(-0.014f, -0.014f, 0.014f);
        ctx.poseStack.m_252880_(0.0f, 18.0f, 0.0f);
        Matrix4f mat = ctx.poseStack.m_85850_().m_252922_();
        WorldRenderMacros.renderFillRectangle(ctx.bufferSource, ctx.poseStack, -i - 1, -5, 0, 2 * i + 2, 17, 0x7F000000);
        ctx.poseStack.m_252880_(0.0f, -5.0f, -0.1f);
        fontrenderer.m_271703_(s1, (float)(-fontrenderer.m_92895_(s1)) / 2.0f, 1.0f, -1, false, mat, (MultiBufferSource)ctx.bufferSource, Font.DisplayMode.NORMAL, 0, 0xF000F0);
        ctx.poseStack.m_252880_(0.0f, 8.0f, -0.1f);
        fontrenderer.m_271703_(s2, (float)(-fontrenderer.m_92895_(s2)) / 2.0f, 1.0f, -1, false, mat, (MultiBufferSource)ctx.bufferSource, Font.DisplayMode.NORMAL, 0, 0xF000F0);
        ctx.poseStack.m_85849_();
    }
}
