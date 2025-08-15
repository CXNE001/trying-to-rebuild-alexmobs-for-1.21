/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.Camera
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.particle.ParticleProvider
 *  net.minecraft.client.particle.ParticleRenderType
 *  net.minecraft.client.renderer.MultiBufferSource$BufferSource
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.util.Mth
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.alexsmobs.client.particle;

import com.github.alexthe666.alexsmobs.client.model.ModelGrizzlyBear;
import com.github.alexthe666.alexsmobs.client.render.AMRenderTypes;
import com.github.alexthe666.alexsmobs.client.render.RenderGrizzlyBear;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParticleBearFreddy
extends Particle {
    private final ModelGrizzlyBear model = new ModelGrizzlyBear();

    ParticleBearFreddy(ClientLevel lvl, double x, double y, double z) {
        super(lvl, x, y, z);
        this.m_107250_(2.0f, 2.0f);
        this.f_107226_ = 0.0f;
        this.f_107225_ = 15;
    }

    public ParticleRenderType m_7556_() {
        return ParticleRenderType.f_107433_;
    }

    public void m_5744_(VertexConsumer vertexConsumer, Camera camera, float partialTick) {
        float fogBefore = RenderSystem.getShaderFogEnd();
        RenderSystem.setShaderFogEnd((float)40.0f);
        float f = ((float)this.f_107224_ + partialTick) / (float)this.f_107225_;
        float initalFlip = Math.min(f, 0.1f) / 0.1f;
        float laterFlip = Mth.m_14036_((float)(f - 0.1f), (float)0.0f, (float)0.1f) / 0.1f;
        float scale = 1.0f;
        PoseStack posestack = new PoseStack();
        posestack.m_252781_(camera.m_253121_());
        posestack.m_85837_(0.0, -1.0, 0.0);
        posestack.m_252781_(Axis.f_252529_.m_252977_(10.0f - laterFlip * 35.0f));
        posestack.m_85841_(-scale, -scale, scale);
        posestack.m_85837_(0.0, 0.5, (double)(2.0f + (1.0f - initalFlip)));
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.m_91087_().m_91269_().m_110104_();
        VertexConsumer vertexconsumer = multibuffersource$buffersource.m_6299_(AMRenderTypes.getFreddy(RenderGrizzlyBear.TEXTURE_FREDDY));
        posestack.m_252781_(Axis.f_252529_.m_252977_(initalFlip * 20.0f - 5.0f));
        float swing = laterFlip * (float)Math.sin(((float)this.f_107224_ + partialTick) * 0.3f) * 20.0f;
        posestack.m_252781_(Axis.f_252403_.m_252977_((1.0f - initalFlip) * 45.0f + swing));
        boolean baby = this.model.f_102610_;
        this.model.f_102610_ = false;
        this.model.positionForParticle(partialTick, (float)this.f_107224_ + partialTick);
        this.model.m_7695_(posestack, vertexconsumer, 240, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        this.model.f_102610_ = baby;
        multibuffersource$buffersource.m_109911_();
        RenderSystem.setShaderFogEnd((float)fogBefore);
    }

    @OnlyIn(value=Dist.CLIENT)
    public static class Factory
    implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new ParticleBearFreddy(worldIn, x, y, z);
        }
    }
}
