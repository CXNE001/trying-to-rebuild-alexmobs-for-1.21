/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.particle.ParticleProvider
 *  net.minecraft.client.particle.ParticleRenderType
 *  net.minecraft.client.particle.SimpleAnimatedParticle
 *  net.minecraft.client.particle.SpriteSet
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.alexsmobs.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParticleDna
extends SimpleAnimatedParticle {
    private ParticleDna(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ, SpriteSet sprites) {
        super(world, x, y, z, sprites, 0.0f);
        this.f_107215_ = (float)motionX;
        this.f_107216_ = (float)motionY;
        this.f_107217_ = (float)motionZ;
        this.f_107663_ *= 1.5f + this.f_107223_.m_188501_() * 0.6f;
        this.f_107225_ = 15 + this.f_107223_.m_188503_(15);
        this.f_107226_ = 0.1f;
        int color = 15916745;
        float lvt_18_1_ = (float)(color >> 16 & 0xFF) / 255.0f;
        float lvt_19_1_ = (float)(color >> 8 & 0xFF) / 255.0f;
        float lvt_20_1_ = (float)(color & 0xFF) / 255.0f;
        this.m_107253_(lvt_18_1_, lvt_19_1_, lvt_20_1_);
        this.m_108339_(sprites);
        this.f_107230_ = 0.0f;
        this.f_107231_ = (float)(1.5707963267948966 * (double)this.f_107223_.m_188501_());
    }

    public int m_6355_(float p_189214_1_) {
        int lvt_2_1_ = super.m_6355_(p_189214_1_);
        int lvt_4_1_ = lvt_2_1_ >> 16 & 0xFF;
        return 0xF0 | lvt_4_1_ << 16;
    }

    public void m_5989_() {
        super.m_5989_();
        this.f_107204_ = this.f_107231_;
        this.f_107215_ += (double)((this.f_107223_.m_188501_() - this.f_107223_.m_188501_()) * 0.05f);
        this.f_107216_ += (double)((this.f_107223_.m_188501_() - this.f_107223_.m_188501_()) * 0.05f);
        this.f_107217_ += (double)((this.f_107223_.m_188501_() - this.f_107223_.m_188501_()) * 0.05f);
        float subAlpha = 1.0f;
        if (this.f_107224_ > 5) {
            subAlpha = 1.0f - (float)(this.f_107224_ - 5) / (float)(this.m_107273_() - 5);
        }
        this.f_107230_ = subAlpha;
        this.m_108339_(this.f_107644_);
        this.f_107231_ += (float)Math.random() * (0.9424779f * this.f_107230_);
    }

    public ParticleRenderType m_7556_() {
        return ParticleRenderType.f_107431_;
    }

    @OnlyIn(value=Dist.CLIENT)
    public static class Factory
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            ParticleDna p = new ParticleDna(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
            return p;
        }
    }
}
