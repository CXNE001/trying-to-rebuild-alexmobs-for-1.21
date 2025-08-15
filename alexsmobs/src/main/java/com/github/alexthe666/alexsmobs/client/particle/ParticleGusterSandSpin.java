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
 *  net.minecraft.util.RandomSource
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
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParticleGusterSandSpin
extends TextureSheetParticle {
    private float targetX;
    private float targetY;
    private float targetZ;
    private float distX;
    private float distZ;
    private static final int[] POSSIBLE_COLORS = new int[]{15975305, 15709560, 16307354, 16770733, 0xFFFFCD};
    private static final int[] POSSIBLE_COLORS_RED = new int[]{13988150, 13000999, 12343841, 11686939, 10963729};
    private static final int[] POSSIBLE_COLORS_SOUL = new int[]{5456440, 5127475, 4009509, 4798252, 2826267};

    private ParticleGusterSandSpin(ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ, int variant) {
        super(world, x, y, z);
        int color = ParticleGusterSandSpin.selectColor(variant, this.f_107223_);
        float lvt_18_1_ = (float)(color >> 16 & 0xFF) / 255.0f;
        float lvt_19_1_ = (float)(color >> 8 & 0xFF) / 255.0f;
        float lvt_20_1_ = (float)(color & 0xFF) / 255.0f;
        this.m_107253_(lvt_18_1_, lvt_19_1_, lvt_20_1_);
        this.targetX = (float)motionX;
        this.targetY = (float)motionY;
        this.targetZ = (float)motionZ;
        this.f_107215_ = 0.0;
        this.f_107216_ = 0.0;
        this.f_107217_ = 0.0;
        this.f_107663_ *= 0.4f + this.f_107223_.m_188501_() * 1.4f;
        this.f_107225_ = 15 + this.f_107223_.m_188503_(15);
        this.distX = (float)(x - (double)this.targetX);
        this.distZ = (float)(z - (double)this.targetZ);
    }

    public static int selectColor(int variant, RandomSource rand) {
        if (variant == 2) {
            return POSSIBLE_COLORS_SOUL[rand.m_188503_(POSSIBLE_COLORS_SOUL.length - 1)];
        }
        if (variant == 1) {
            return POSSIBLE_COLORS_RED[rand.m_188503_(POSSIBLE_COLORS_RED.length - 1)];
        }
        return POSSIBLE_COLORS[rand.m_188503_(POSSIBLE_COLORS.length - 1)];
    }

    public void m_5989_() {
        super.m_5989_();
        float radius = 2.0f;
        float angle = this.f_107224_ * 2;
        double extraX = this.targetX + radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = this.targetZ + radius * Mth.m_14089_((float)angle);
        double d2 = extraX - this.f_107212_;
        double d3 = (double)this.targetY - this.f_107213_;
        double d4 = extraZ - this.f_107214_;
        float speed = 0.02f;
        this.f_107215_ += d2 * (double)speed;
        this.f_107216_ += d3 * (double)speed;
        this.f_107217_ += d4 * (double)speed;
    }

    public void m_6257_(double x, double y, double z) {
        this.m_107259_(this.m_107277_().m_82386_(x, y, z));
        this.m_107275_();
    }

    public ParticleRenderType m_7556_() {
        return ParticleRenderType.f_107430_;
    }

    @OnlyIn(value=Dist.CLIENT)
    public static class FactorySoul
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public FactorySoul(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            ParticleGusterSandSpin p = new ParticleGusterSandSpin(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 2);
            p.m_108335_(this.spriteSet);
            return p;
        }
    }

    @OnlyIn(value=Dist.CLIENT)
    public static class FactoryRed
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public FactoryRed(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            ParticleGusterSandSpin p = new ParticleGusterSandSpin(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 1);
            p.m_108335_(this.spriteSet);
            return p;
        }
    }

    @OnlyIn(value=Dist.CLIENT)
    public static class Factory
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            ParticleGusterSandSpin p = new ParticleGusterSandSpin(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 0);
            p.m_108335_(this.spriteSet);
            return p;
        }
    }
}
