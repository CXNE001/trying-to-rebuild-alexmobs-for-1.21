/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.network.PlayMessages$SpawnEntity
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityFlutter;
import com.github.alexthe666.alexsmobs.entity.EntityMobProjectile;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

public class EntityPollenBall
extends EntityMobProjectile {
    public EntityPollenBall(EntityType type, Level level) {
        super(type, level);
    }

    public EntityPollenBall(Level worldIn, EntityFlutter flutter) {
        super((EntityType)AMEntityRegistry.POLLEN_BALL.get(), worldIn, (Mob)flutter);
        Vec3 vec3 = flutter.m_20182_().m_82549_(this.calcOffsetVec(new Vec3(0.0, (double)(0.4f * flutter.m_6134_()), 0.0), flutter.getFlutterPitch(), flutter.m_146908_()));
        this.m_6034_(vec3.f_82479_, vec3.f_82480_, vec3.f_82481_);
    }

    public EntityPollenBall(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this((EntityType)AMEntityRegistry.POLLEN_BALL.get(), world);
    }

    public boolean m_20068_() {
        return true;
    }

    @Override
    public void doBehavior() {
        Entity entity = this.getShooter();
        if (entity instanceof Mob && ((Mob)entity).m_5448_() != null) {
            LivingEntity target = ((Mob)entity).m_5448_();
            if (target == null) {
                this.m_6074_();
            }
            double d0 = target.m_20185_() - this.m_20185_();
            double d1 = target.m_20186_() + (double)(target.m_20206_() * 0.5f) - this.m_20186_();
            double d2 = target.m_20189_() - this.m_20189_();
            float speed = 0.35f;
            this.shoot(d0, d1, d2, 0.35f, 0.0f);
            this.m_146922_(-((float)Mth.m_14136_((double)d0, (double)d2)) * 57.295776f);
        }
        if (this.m_9236_().f_46443_ && this.f_19796_.m_188503_(2) == 0) {
            float r1 = (this.f_19796_.m_188501_() - 0.5f) * 0.5f;
            float r2 = (this.f_19796_.m_188501_() - 0.5f) * 0.5f;
            float r3 = (this.f_19796_.m_188501_() - 0.5f) * 0.5f;
            this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123782_, this.m_20185_() + (double)r1, this.m_20186_() + (double)r2, this.m_20189_() + (double)r3, (double)(r1 * 0.1f), (double)(r2 * 0.1f), (double)(r3 * 0.1f));
        }
    }

    @Override
    protected float getDamage() {
        return 3.0f;
    }
}
