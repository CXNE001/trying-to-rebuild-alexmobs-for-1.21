/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.PoseStack$Pose
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 */
package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.entity.EntityMudBall;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class RenderMudBall
extends EntityRenderer<EntityMudBall> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/mud_ball.png");

    public RenderMudBall(EntityRendererProvider.Context p_173962_) {
        super(p_173962_);
    }

    public void render(EntityMudBall entityMudBall, float f, float f2, PoseStack p_114083_, MultiBufferSource p_114084_, int p_114085_) {
        p_114083_.m_85836_();
        p_114083_.m_85841_(0.7f, 0.7f, 0.7f);
        p_114083_.m_252781_(this.f_114476_.m_253208_());
        p_114083_.m_252781_(Axis.f_252436_.m_252977_(180.0f));
        PoseStack.Pose $$6 = p_114083_.m_85850_();
        Matrix4f $$7 = $$6.m_252922_();
        Matrix3f $$8 = $$6.m_252943_();
        VertexConsumer $$9 = p_114084_.m_6299_(RenderType.m_110458_((ResourceLocation)TEXTURE));
        RenderMudBall.vertex($$9, $$7, $$8, p_114085_, 0.0f, 0, 0, 1);
        RenderMudBall.vertex($$9, $$7, $$8, p_114085_, 1.0f, 0, 1, 1);
        RenderMudBall.vertex($$9, $$7, $$8, p_114085_, 1.0f, 1, 1, 0);
        RenderMudBall.vertex($$9, $$7, $$8, p_114085_, 0.0f, 1, 0, 0);
        p_114083_.m_85849_();
        super.m_7392_((Entity)entityMudBall, f, f2, p_114083_, p_114084_, p_114085_);
    }

    private static void vertex(VertexConsumer p_114090_, Matrix4f p_114091_, Matrix3f p_114092_, int p_114093_, float p_114094_, int p_114095_, int p_114096_, int p_114097_) {
        p_114090_.m_252986_(p_114091_, p_114094_ - 0.5f, (float)p_114095_ - 0.25f, 0.0f).m_6122_(255, 255, 255, 255).m_7421_((float)p_114096_, (float)p_114097_).m_86008_(OverlayTexture.f_118083_).m_85969_(p_114093_).m_252939_(p_114092_, 0.0f, 1.0f, 0.0f).m_5752_();
    }

    public ResourceLocation getTextureLocation(EntityMudBall mudball) {
        return TEXTURE;
    }
}
