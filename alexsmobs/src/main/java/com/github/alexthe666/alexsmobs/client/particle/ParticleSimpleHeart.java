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
 *  net.minecraft.util.Mth
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.alexsmobs.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParticleSimpleHeart
extends TextureSheetParticle {
    protected ParticleSimpleHeart(ClientLevel world, double x, double y, double z) {
        super(world, x, y, z);
        this.f_107215_ *= (double)0.01f;
        this.f_107216_ *= (double)0.01f;
        this.f_107217_ *= (double)0.01f;
        this.f_107216_ += 0.1;
        this.f_107663_ *= 2.0f;
        this.f_107225_ = 32;
        this.f_107219_ = false;
    }

    public float m_5902_(float scaleFactor) {
        return this.f_107663_ * Mth.m_14036_((float)(((float)this.f_107224_ + scaleFactor) / (float)this.f_107225_ * 16.0f), (float)0.0f, (float)1.0f);
    }

    public void m_5989_() {
        this.f_107209_ = this.f_107212_;
        this.f_107210_ = this.f_107213_;
        this.f_107211_ = this.f_107214_;
        if (this.f_107224_++ >= this.f_107225_) {
            this.m_107274_();
        } else {
            this.m_6257_(this.f_107215_, this.f_107216_, this.f_107217_);
            if (this.f_107213_ == this.f_107210_) {
                this.f_107215_ *= 1.1;
                this.f_107217_ *= 1.1;
            }
            this.f_107215_ *= (double)0.86f;
            this.f_107216_ *= (double)0.86f;
            this.f_107217_ *= (double)0.86f;
            if (this.f_107218_) {
                this.f_107215_ *= (double)0.7f;
                this.f_107217_ *= (double)0.7f;
            }
        }
    }

    public ParticleRenderType m_7556_() {
        return ParticleRenderType.f_107430_;
    }

    @OnlyIn(value=Dist.CLIENT)
    public static class Factory
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            ParticleSimpleHeart heartparticle = new ParticleSimpleHeart(worldIn, x, y, z);
            heartparticle.m_108335_(this.spriteSet);
            return heartparticle;
        }
    }
}
