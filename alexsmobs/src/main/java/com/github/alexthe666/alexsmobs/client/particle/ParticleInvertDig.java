/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.particle.ParticleProvider
 *  net.minecraft.client.particle.SimpleAnimatedParticle
 *  net.minecraft.client.particle.SpriteSet
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.alexsmobs.client.particle;

import com.github.alexthe666.alexsmobs.item.ItemDimensionalCarver;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParticleInvertDig
extends SimpleAnimatedParticle {
    private final Entity creator;

    protected ParticleInvertDig(ClientLevel world, double x, double y, double z, SpriteSet spriteWithAge, double creatorId) {
        super(world, x, y, z, spriteWithAge, 0.0f);
        this.f_107215_ = 0.0;
        this.f_107216_ = 0.0;
        this.f_107217_ = 0.0;
        this.f_107663_ = 0.1f;
        this.f_107230_ = 1.0f;
        this.f_107225_ = 200;
        this.f_107219_ = false;
        this.creator = world.m_6815_((int)creatorId);
    }

    public int m_6355_(float p_189214_1_) {
        return 240;
    }

    public void m_5989_() {
        this.f_107209_ = this.f_107212_;
        this.f_107210_ = this.f_107213_;
        this.f_107211_ = this.f_107214_;
        boolean live = false;
        this.f_107663_ = 0.1f + Math.min((float)this.f_107224_ / (float)this.f_107225_, 0.5f) * 0.5f;
        if (this.f_107224_++ >= this.f_107225_ || this.creator == null) {
            this.m_107274_();
        } else {
            Player player;
            ItemStack item;
            Entity entity = this.creator;
            if (entity instanceof Player && (item = (player = (Player)entity).m_21211_()).m_41720_() instanceof ItemDimensionalCarver) {
                this.f_107224_ = Mth.m_14045_((int)(this.f_107225_ - player.m_21212_()), (int)0, (int)this.f_107225_);
                live = true;
            }
        }
        if (!live) {
            this.m_107274_();
        }
        this.m_108339_(this.f_107644_);
    }

    @OnlyIn(value=Dist.CLIENT)
    public static class Factory
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            ParticleInvertDig heartparticle = new ParticleInvertDig(worldIn, x, y, z, this.spriteSet, xSpeed);
            heartparticle.m_108339_(this.spriteSet);
            return heartparticle;
        }
    }
}
