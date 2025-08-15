/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.particle.ParticleProvider
 *  net.minecraft.client.particle.ParticleRenderType
 *  net.minecraft.client.particle.SpriteSet
 *  net.minecraft.client.particle.TextureSheetParticle
 *  net.minecraft.core.particles.SimpleParticleType
 */
package com.github.alexthe666.alexsmobs.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;

public class ParticleBirdSong
extends TextureSheetParticle {
    private ParticleBirdSong(ClientLevel world, double x, double y, double z, double xd, double yd, double zd, SpriteSet sprites) {
        super(world, x, y, z);
        this.f_172258_ = 0.7f;
        this.f_107226_ = 0.0f;
        this.f_172259_ = true;
        this.f_107215_ = xd;
        this.f_107216_ = yd;
        this.f_107217_ = zd;
        this.f_107663_ = 0.15f + this.f_107223_.m_188501_() * 0.2f;
        this.f_107225_ = 20 + this.f_107223_.m_188503_(20);
        this.m_108335_(sprites);
        this.f_107227_ = 0.294f;
        this.f_107228_ = 0.584f;
        this.f_107229_ = 1.0f;
    }

    public void m_5989_() {
        this.f_107209_ = this.f_107212_;
        this.f_107210_ = this.f_107213_;
        this.f_107211_ = this.f_107214_;
        this.f_107216_ = Math.sin((float)this.f_107224_ * 0.3f) * (double)0.3f;
        if (this.f_107224_++ >= this.f_107225_) {
            this.m_107274_();
        } else {
            this.m_6257_(this.f_107215_, this.f_107216_, this.f_107217_);
            this.f_107216_ -= (double)this.f_107226_;
        }
        float subAlpha = 1.0f;
        if (this.f_107224_ > 5) {
            subAlpha = 1.0f - (float)(this.f_107224_ - 5) / (float)(this.m_107273_() - 5);
        }
        this.f_107215_ *= 0.99;
        this.f_107216_ *= 0.99;
        this.f_107217_ *= 0.99;
        this.f_107230_ = subAlpha;
        this.f_107231_ = (float)Math.toRadians(Math.sin((float)this.f_107224_ * 0.01f) * 5.0);
    }

    public int m_6355_(float p_189214_1_) {
        int lvt_2_1_ = super.m_6355_(p_189214_1_);
        int lvt_4_1_ = lvt_2_1_ >> 16 & 0xFF;
        return 0xF0 | lvt_4_1_ << 16;
    }

    public ParticleRenderType m_7556_() {
        return ParticleRenderType.f_107431_;
    }

    public static class Factory
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new ParticleBirdSong(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}
