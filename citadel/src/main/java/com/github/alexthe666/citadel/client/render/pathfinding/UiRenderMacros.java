/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.Lighting
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.BufferBuilder
 *  com.mojang.blaze3d.vertex.DefaultVertexFormat
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.Tesselator
 *  com.mojang.blaze3d.vertex.VertexFormat$Mode
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GameRenderer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.MultiBufferSource$BufferSource
 *  net.minecraft.client.renderer.entity.EntityRenderDispatcher
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  org.joml.Matrix4f
 *  org.joml.Quaternionf
 */
package com.github.alexthe666.citadel.client.render.pathfinding;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

public class UiRenderMacros {
    public static final double HALF_BIAS = 0.5;

    public static void drawLineRectGradient(PoseStack ps, int x, int y, int w, int h, int argbColorStart, int argbColorEnd) {
        UiRenderMacros.drawLineRectGradient(ps, x, y, w, h, argbColorStart, argbColorEnd, 1);
    }

    public static void drawLineRectGradient(PoseStack ps, int x, int y, int w, int h, int argbColorStart, int argbColorEnd, int lineWidth) {
        UiRenderMacros.drawLineRectGradient(ps, x, y, w, h, argbColorStart >> 16 & 0xFF, argbColorEnd >> 16 & 0xFF, argbColorStart >> 8 & 0xFF, argbColorEnd >> 8 & 0xFF, argbColorStart & 0xFF, argbColorEnd & 0xFF, argbColorStart >> 24 & 0xFF, argbColorEnd >> 24 & 0xFF, lineWidth);
    }

    public static void drawLineRectGradient(PoseStack ps, int x, int y, int w, int h, int redStart, int redEnd, int greenStart, int greenEnd, int blueStart, int blueEnd, int alphaStart, int alphaEnd, int lineWidth) {
        if (lineWidth < 1 || alphaStart == 0 && alphaEnd == 0) {
            return;
        }
        RenderSystem.setShader(GameRenderer::m_172811_);
        if (alphaStart != 255 || alphaEnd != 255) {
            RenderSystem.enableBlend();
        } else {
            RenderSystem.disableBlend();
        }
        Matrix4f m = ps.m_85850_().m_252922_();
        BufferBuilder buffer = Tesselator.m_85913_().m_85915_();
        buffer.m_166779_(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.f_85815_);
        buffer.m_252986_(m, (float)x, (float)y, 0.0f).m_6122_(redStart, greenStart, blueStart, alphaStart).m_5752_();
        buffer.m_252986_(m, (float)x, (float)(y + h), 0.0f).m_6122_(redEnd, greenEnd, blueEnd, alphaEnd).m_5752_();
        buffer.m_252986_(m, (float)(x + lineWidth), (float)(y + h - lineWidth), 0.0f).m_6122_(redEnd, greenEnd, blueEnd, alphaEnd).m_5752_();
        buffer.m_252986_(m, (float)(x + lineWidth), (float)(y + lineWidth), 0.0f).m_6122_(redStart, greenStart, blueStart, alphaStart).m_5752_();
        buffer.m_252986_(m, (float)(x + w - lineWidth), (float)(y + lineWidth), 0.0f).m_6122_(redStart, greenStart, blueStart, alphaStart).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)y, 0.0f).m_6122_(redStart, greenStart, blueStart, alphaStart).m_5752_();
        Tesselator.m_85913_().m_85914_();
        buffer.m_166779_(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.f_85815_);
        buffer.m_252986_(m, (float)(x + w), (float)(y + h), 0.0f).m_6122_(redEnd, greenEnd, blueEnd, alphaEnd).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)y, 0.0f).m_6122_(redStart, greenStart, blueStart, alphaStart).m_5752_();
        buffer.m_252986_(m, (float)(x + w - lineWidth), (float)(y + lineWidth), 0.0f).m_6122_(redStart, greenStart, blueStart, alphaStart).m_5752_();
        buffer.m_252986_(m, (float)(x + w - lineWidth), (float)(y + h - lineWidth), 0.0f).m_6122_(redEnd, greenEnd, blueEnd, alphaEnd).m_5752_();
        buffer.m_252986_(m, (float)(x + lineWidth), (float)(y + h - lineWidth), 0.0f).m_6122_(redEnd, greenEnd, blueEnd, alphaEnd).m_5752_();
        buffer.m_252986_(m, (float)x, (float)(y + h), 0.0f).m_6122_(redEnd, greenEnd, blueEnd, alphaEnd).m_5752_();
        Tesselator.m_85913_().m_85914_();
        RenderSystem.disableBlend();
    }

    public static void drawLineRect(PoseStack ps, int x, int y, int w, int h, int argbColor) {
        UiRenderMacros.drawLineRect(ps, x, y, w, h, argbColor, 1);
    }

    public static void drawLineRect(PoseStack ps, int x, int y, int w, int h, int argbColor, int lineWidth) {
        UiRenderMacros.drawLineRect(ps, x, y, w, h, argbColor >> 16 & 0xFF, argbColor >> 8 & 0xFF, argbColor & 0xFF, argbColor >> 24 & 0xFF, lineWidth);
    }

    public static void drawLineRect(PoseStack ps, int x, int y, int w, int h, int red, int green, int blue, int alpha, int lineWidth) {
        if (lineWidth < 1 || alpha == 0) {
            return;
        }
        RenderSystem.setShader(GameRenderer::m_172811_);
        if (alpha != 255) {
            RenderSystem.enableBlend();
        } else {
            RenderSystem.disableBlend();
        }
        Matrix4f m = ps.m_85850_().m_252922_();
        BufferBuilder buffer = Tesselator.m_85913_().m_85915_();
        buffer.m_166779_(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.f_85815_);
        buffer.m_252986_(m, (float)x, (float)y, 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)x, (float)(y + h), 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + lineWidth), (float)(y + h - lineWidth), 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + lineWidth), (float)(y + lineWidth), 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + w - lineWidth), (float)(y + lineWidth), 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)y, 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        Tesselator.m_85913_().m_85914_();
        buffer.m_166779_(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.f_85815_);
        buffer.m_252986_(m, (float)(x + w), (float)(y + h), 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)y, 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + w - lineWidth), (float)(y + lineWidth), 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + w - lineWidth), (float)(y + h - lineWidth), 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + lineWidth), (float)(y + h - lineWidth), 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)x, (float)(y + h), 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        Tesselator.m_85913_().m_85914_();
        RenderSystem.disableBlend();
    }

    public static void fill(PoseStack ps, int x, int y, int w, int h, int argbColor) {
        UiRenderMacros.fill(ps, x, y, w, h, argbColor >> 16 & 0xFF, argbColor >> 8 & 0xFF, argbColor & 0xFF, argbColor >> 24 & 0xFF);
    }

    public static void fill(PoseStack ps, int x, int y, int w, int h, int red, int green, int blue, int alpha) {
        if (alpha == 0) {
            return;
        }
        RenderSystem.setShader(GameRenderer::m_172811_);
        if (alpha != 255) {
            RenderSystem.enableBlend();
        } else {
            RenderSystem.disableBlend();
        }
        Matrix4f m = ps.m_85850_().m_252922_();
        BufferBuilder buffer = Tesselator.m_85913_().m_85915_();
        buffer.m_166779_(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.f_85815_);
        buffer.m_252986_(m, (float)x, (float)y, 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)x, (float)(y + h), 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)(y + h), 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)y, 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        Tesselator.m_85913_().m_85914_();
        RenderSystem.disableBlend();
    }

    public static void fillGradient(PoseStack ps, int x, int y, int w, int h, int argbColorStart, int argbColorEnd) {
        UiRenderMacros.fillGradient(ps, x, y, w, h, argbColorStart >> 16 & 0xFF, argbColorEnd >> 16 & 0xFF, argbColorStart >> 8 & 0xFF, argbColorEnd >> 8 & 0xFF, argbColorStart & 0xFF, argbColorEnd & 0xFF, argbColorStart >> 24 & 0xFF, argbColorEnd >> 24 & 0xFF);
    }

    public static void fillGradient(PoseStack ps, int x, int y, int w, int h, int redStart, int redEnd, int greenStart, int greenEnd, int blueStart, int blueEnd, int alphaStart, int alphaEnd) {
        if (alphaStart == 0 && alphaEnd == 0) {
            return;
        }
        RenderSystem.setShader(GameRenderer::m_172811_);
        if (alphaStart != 255 || alphaEnd != 255) {
            RenderSystem.enableBlend();
        } else {
            RenderSystem.disableBlend();
        }
        Matrix4f m = ps.m_85850_().m_252922_();
        BufferBuilder buffer = Tesselator.m_85913_().m_85915_();
        buffer.m_166779_(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.f_85815_);
        buffer.m_252986_(m, (float)x, (float)y, 0.0f).m_6122_(redStart, greenStart, blueStart, alphaStart).m_5752_();
        buffer.m_252986_(m, (float)x, (float)(y + h), 0.0f).m_6122_(redEnd, greenEnd, blueEnd, alphaEnd).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)(y + h), 0.0f).m_6122_(redEnd, greenEnd, blueEnd, alphaEnd).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)y, 0.0f).m_6122_(redStart, greenStart, blueStart, alphaStart).m_5752_();
        Tesselator.m_85913_().m_85914_();
        RenderSystem.disableBlend();
    }

    public static void hLine(PoseStack ps, int x, int xEnd, int y, int argbColor) {
        UiRenderMacros.line(ps, x, y, xEnd, y, argbColor >> 16 & 0xFF, argbColor >> 8 & 0xFF, argbColor & 0xFF, argbColor >> 24 & 0xFF);
    }

    public static void hLine(PoseStack ps, int x, int xEnd, int y, int red, int green, int blue, int alpha) {
        UiRenderMacros.line(ps, x, y, xEnd, y, red, green, blue, alpha);
    }

    public static void vLine(PoseStack ps, int x, int y, int yEnd, int argbColor) {
        UiRenderMacros.line(ps, x, y, x, yEnd, argbColor >> 16 & 0xFF, argbColor >> 8 & 0xFF, argbColor & 0xFF, argbColor >> 24 & 0xFF);
    }

    public static void vLine(PoseStack ps, int x, int y, int yEnd, int red, int green, int blue, int alpha) {
        UiRenderMacros.line(ps, x, y, x, yEnd, red, green, blue, alpha);
    }

    public static void line(PoseStack ps, int x, int y, int xEnd, int yEnd, int argbColor) {
        UiRenderMacros.line(ps, x, y, xEnd, yEnd, argbColor >> 16 & 0xFF, argbColor >> 8 & 0xFF, argbColor & 0xFF, argbColor >> 24 & 0xFF);
    }

    public static void line(PoseStack ps, int x, int y, int xEnd, int yEnd, int red, int green, int blue, int alpha) {
        if (alpha == 0) {
            return;
        }
        RenderSystem.setShader(GameRenderer::m_172811_);
        if (alpha != 255) {
            RenderSystem.enableBlend();
        } else {
            RenderSystem.disableBlend();
        }
        Matrix4f m = ps.m_85850_().m_252922_();
        BufferBuilder buffer = Tesselator.m_85913_().m_85915_();
        buffer.m_166779_(VertexFormat.Mode.DEBUG_LINES, DefaultVertexFormat.f_85815_);
        buffer.m_252986_(m, (float)x, (float)y, 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)xEnd, (float)yEnd, 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        Tesselator.m_85913_().m_85914_();
        RenderSystem.disableBlend();
    }

    public static void blit(PoseStack ps, ResourceLocation rl, int x, int y, int w, int h) {
        UiRenderMacros.blit(ps, rl, x, y, w, h, 0.0f, 0.0f, 1.0f, 1.0f);
    }

    public static void blit(PoseStack ps, ResourceLocation rl, int x, int y, int w, int h, int u, int v, int mapW, int mapH) {
        UiRenderMacros.blit(ps, rl, x, y, w, h, (float)u / (float)mapW, (float)v / (float)mapH, (float)(u + w) / (float)mapW, (float)(v + h) / (float)mapH);
    }

    public static void blit(PoseStack ps, ResourceLocation rl, int x, int y, int w, int h, int u, int v, int uW, int vH, int mapW, int mapH) {
        UiRenderMacros.blit(ps, rl, x, y, w, h, (float)u / (float)mapW, (float)v / (float)mapH, (float)(u + uW) / (float)mapW, (float)(v + vH) / (float)mapH);
    }

    public static void blit(PoseStack ps, ResourceLocation rl, int x, int y, int w, int h, float uMin, float vMin, float uMax, float vMax) {
        Minecraft.m_91087_().m_91097_().m_174784_(rl);
        RenderSystem.setShaderTexture((int)0, (ResourceLocation)rl);
        RenderSystem.setShader(GameRenderer::m_172817_);
        Matrix4f m = ps.m_85850_().m_252922_();
        BufferBuilder buffer = Tesselator.m_85913_().m_85915_();
        buffer.m_166779_(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.f_85817_);
        buffer.m_252986_(m, (float)x, (float)y, 0.0f).m_7421_(uMin, vMin).m_5752_();
        buffer.m_252986_(m, (float)x, (float)(y + h), 0.0f).m_7421_(uMin, vMax).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)(y + h), 0.0f).m_7421_(uMax, vMax).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)y, 0.0f).m_7421_(uMax, vMin).m_5752_();
        Tesselator.m_85913_().m_85914_();
    }

    protected static void blitRepeatable(PoseStack ps, ResourceLocation rl, int x, int y, int width, int height, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight, int uRepeat, int vRepeat, int repeatWidth, int repeatHeight) {
        if (uRepeat < 0 || vRepeat < 0 || uRepeat >= uWidth || vRepeat >= vHeight || repeatWidth < 1 || repeatHeight < 1 || repeatWidth > uWidth - uRepeat || repeatHeight > vHeight - vRepeat) {
            throw new IllegalArgumentException("Repeatable box is outside of texture box");
        }
        int repeatCountX = Math.max(1, Math.max(0, width - (uWidth - repeatWidth)) / repeatWidth);
        int repeatCountY = Math.max(1, Math.max(0, height - (vHeight - repeatHeight)) / repeatHeight);
        Matrix4f mat = ps.m_85850_().m_252922_();
        BufferBuilder buffer = Tesselator.m_85913_().m_85915_();
        buffer.m_166779_(VertexFormat.Mode.TRIANGLES, DefaultVertexFormat.f_85817_);
        for (int i = 0; i < repeatCountX; ++i) {
            int uAdjust = i == 0 ? 0 : uRepeat;
            int xStart = x + uAdjust + i * repeatWidth;
            int w = Math.min(repeatWidth + uRepeat - uAdjust, width - (uWidth - uRepeat - repeatWidth));
            float minU = (float)(u + uAdjust) / (float)textureWidth;
            float maxU = (float)(u + uAdjust + w) / (float)textureWidth;
            for (int j = 0; j < repeatCountY; ++j) {
                int vAdjust = j == 0 ? 0 : vRepeat;
                int yStart = y + vAdjust + j * repeatHeight;
                int h = Math.min(repeatHeight + vRepeat - vAdjust, height - (vHeight - vRepeat - repeatHeight));
                float minV = (float)(v + vAdjust) / (float)textureHeight;
                float maxV = (float)(v + vAdjust + h) / (float)textureHeight;
                UiRenderMacros.populateBlitTriangles(buffer, mat, xStart, xStart + w, yStart, yStart + h, minU, maxU, minV, maxV);
            }
        }
        int xEnd = x + Math.min(uRepeat + repeatCountX * repeatWidth, width - (uWidth - uRepeat - repeatWidth));
        int yEnd = y + Math.min(vRepeat + repeatCountY * repeatHeight, height - (vHeight - vRepeat - repeatHeight));
        int uLeft = width - (xEnd - x);
        int vLeft = height - (yEnd - y);
        float restMinU = (float)(u + uWidth - uLeft) / (float)textureWidth;
        float restMaxU = (float)(u + uWidth) / (float)textureWidth;
        float restMinV = (float)(v + vHeight - vLeft) / (float)textureHeight;
        float restMaxV = (float)(v + vHeight) / (float)textureHeight;
        for (int i = 0; i < repeatCountX; ++i) {
            int uAdjust = i == 0 ? 0 : uRepeat;
            int xStart = x + uAdjust + i * repeatWidth;
            int w = Math.min(repeatWidth + uRepeat - uAdjust, width - uLeft);
            float minU = (float)(u + uAdjust) / (float)textureWidth;
            float maxU = (float)(u + uAdjust + w) / (float)textureWidth;
            UiRenderMacros.populateBlitTriangles(buffer, mat, xStart, xStart + w, yEnd, yEnd + vLeft, minU, maxU, restMinV, restMaxV);
        }
        for (int j = 0; j < repeatCountY; ++j) {
            int vAdjust = j == 0 ? 0 : vRepeat;
            int yStart = y + vAdjust + j * repeatHeight;
            int h = Math.min(repeatHeight + vRepeat - vAdjust, height - vLeft);
            float minV = (float)(v + vAdjust) / (float)textureHeight;
            float maxV = (float)(v + vAdjust + h) / (float)textureHeight;
            UiRenderMacros.populateBlitTriangles(buffer, mat, xEnd, xEnd + uLeft, yStart, yStart + h, restMinU, restMaxU, minV, maxV);
        }
        UiRenderMacros.populateBlitTriangles(buffer, mat, xEnd, xEnd + uLeft, yEnd, yEnd + vLeft, restMinU, restMaxU, restMinV, restMaxV);
        Minecraft.m_91087_().m_91097_().m_174784_(rl);
        RenderSystem.setShaderTexture((int)0, (ResourceLocation)rl);
        RenderSystem.setShader(GameRenderer::m_172817_);
        Tesselator.m_85913_().m_85914_();
    }

    public static void populateFillTriangles(Matrix4f m, BufferBuilder buffer, int x, int y, int w, int h, int red, int green, int blue, int alpha) {
        buffer.m_252986_(m, (float)x, (float)y, 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)x, (float)(y + h), 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)y, 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)y, 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)x, (float)(y + h), 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)(y + h), 0.0f).m_6122_(red, green, blue, alpha).m_5752_();
    }

    public static void populateFillGradientTriangles(Matrix4f m, BufferBuilder buffer, int x, int y, int w, int h, int redStart, int redEnd, int greenStart, int greenEnd, int blueStart, int blueEnd, int alphaStart, int alphaEnd) {
        buffer.m_252986_(m, (float)x, (float)y, 0.0f).m_6122_(redStart, greenStart, blueStart, alphaStart).m_5752_();
        buffer.m_252986_(m, (float)x, (float)(y + h), 0.0f).m_6122_(redEnd, greenEnd, blueEnd, alphaEnd).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)y, 0.0f).m_6122_(redStart, greenStart, blueStart, alphaStart).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)y, 0.0f).m_6122_(redStart, greenStart, blueStart, alphaStart).m_5752_();
        buffer.m_252986_(m, (float)x, (float)(y + h), 0.0f).m_6122_(redEnd, greenEnd, blueEnd, alphaEnd).m_5752_();
        buffer.m_252986_(m, (float)(x + w), (float)(y + h), 0.0f).m_6122_(redEnd, greenEnd, blueEnd, alphaEnd).m_5752_();
    }

    public static void populateBlitTriangles(BufferBuilder buffer, Matrix4f mat, float xStart, float xEnd, float yStart, float yEnd, float uMin, float uMax, float vMin, float vMax) {
        buffer.m_252986_(mat, xStart, yStart, 0.0f).m_7421_(uMin, vMin).m_5752_();
        buffer.m_252986_(mat, xStart, yEnd, 0.0f).m_7421_(uMin, vMax).m_5752_();
        buffer.m_252986_(mat, xEnd, yStart, 0.0f).m_7421_(uMax, vMin).m_5752_();
        buffer.m_252986_(mat, xEnd, yStart, 0.0f).m_7421_(uMax, vMin).m_5752_();
        buffer.m_252986_(mat, xStart, yEnd, 0.0f).m_7421_(uMin, vMax).m_5752_();
        buffer.m_252986_(mat, xEnd, yEnd, 0.0f).m_7421_(uMax, vMax).m_5752_();
    }

    public static void drawEntity(PoseStack poseStack, int x, int y, double scale, float headYaw, float yaw, float pitch, Entity entity) {
        LivingEntity livingEntity = entity instanceof LivingEntity ? (LivingEntity)entity : null;
        Minecraft mc = Minecraft.m_91087_();
        if (entity.m_9236_() == null) {
            return;
        }
        poseStack.m_85836_();
        poseStack.m_252880_((float)x, (float)y, 1050.0f);
        poseStack.m_85841_(1.0f, 1.0f, -1.0f);
        poseStack.m_85837_(0.0, 0.0, 1000.0);
        poseStack.m_85841_((float)scale, (float)scale, (float)scale);
        Quaternionf pitchRotation = Axis.f_252529_.m_252977_(pitch);
        poseStack.m_252781_(Axis.f_252403_.m_252977_(180.0f));
        poseStack.m_252781_(pitchRotation);
        float oldYaw = entity.m_146908_();
        float oldPitch = entity.m_146909_();
        float oldYawOffset = livingEntity == null ? 0.0f : livingEntity.f_20883_;
        float oldPrevYawHead = livingEntity == null ? 0.0f : livingEntity.f_20886_;
        float oldYawHead = livingEntity == null ? 0.0f : livingEntity.f_20885_;
        entity.m_146922_(180.0f + headYaw);
        entity.m_146926_(-pitch);
        if (livingEntity != null) {
            livingEntity.f_20883_ = 180.0f + yaw;
            livingEntity.f_20885_ = entity.m_146908_();
            livingEntity.f_20886_ = entity.m_146908_();
        }
        Lighting.m_166384_();
        EntityRenderDispatcher dispatcher = mc.m_91290_();
        pitchRotation.conjugate();
        dispatcher.m_252923_(pitchRotation);
        dispatcher.m_114468_(false);
        MultiBufferSource.BufferSource buffers = mc.m_91269_().m_110104_();
        RenderSystem.runAsFancy(() -> dispatcher.m_114384_(entity, 0.0, 0.0, 0.0, 0.0f, 1.0f, poseStack, (MultiBufferSource)buffers, 0xF000F0));
        buffers.m_109911_();
        dispatcher.m_114468_(true);
        entity.m_146922_(oldYaw);
        entity.m_146926_(oldPitch);
        if (livingEntity != null) {
            livingEntity.f_20883_ = oldYawOffset;
            livingEntity.f_20886_ = oldPrevYawHead;
            livingEntity.f_20885_ = oldYawHead;
        }
        poseStack.m_85849_();
        Lighting.m_84931_();
    }
}
