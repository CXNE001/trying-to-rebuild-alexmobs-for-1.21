/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.particle.ParticleProvider
 *  net.minecraft.client.particle.SpriteSet
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.alexsmobs.client.particle;

import com.github.alexthe666.alexsmobs.client.particle.ParticleBunfungusTransformation;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
public static class ParticleBunfungusTransformation.Factory
implements ParticleProvider<SimpleParticleType> {
    private final SpriteSet sprites;

    public ParticleBunfungusTransformation.Factory(SpriteSet p_107528_) {
        this.sprites = p_107528_;
    }

    public Particle createParticle(SimpleParticleType p_107539_, ClientLevel p_107540_, double p_107541_, double p_107542_, double p_107543_, double p_107544_, double p_107545_, double p_107546_) {
        ParticleBunfungusTransformation particle = new ParticleBunfungusTransformation(p_107540_, p_107541_, p_107542_, p_107543_, p_107544_, p_107545_, p_107546_, this.sprites);
        particle.m_107253_(0.427451f, 0.4470588f, 0.5764706f);
        return particle;
    }
}
