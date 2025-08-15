/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.particle.ParticleProvider
 *  net.minecraft.client.particle.SpriteSet
 *  net.minecraft.core.particles.SimpleParticleType
 */
package com.github.alexthe666.alexsmobs.client.particle;

import com.github.alexthe666.alexsmobs.client.particle.ParticleBirdSong;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

public static class ParticleBirdSong.Factory
implements ParticleProvider<SimpleParticleType> {
    private final SpriteSet spriteSet;

    public ParticleBirdSong.Factory(SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
    }

    public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        return new ParticleBirdSong(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
    }
}
