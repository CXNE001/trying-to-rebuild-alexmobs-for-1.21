/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ItemParticleOption
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.projectile.ThrowableItemProjectile
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.phys.HitResult
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.network.NetworkHooks
 *  net.minecraftforge.network.PlayMessages$SpawnEntity
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityCockroach;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class EntityCockroachEgg
extends ThrowableItemProjectile {
    public EntityCockroachEgg(EntityType p_i50154_1_, Level p_i50154_2_) {
        super(p_i50154_1_, p_i50154_2_);
    }

    public EntityCockroachEgg(Level worldIn, LivingEntity throwerIn) {
        super((EntityType)AMEntityRegistry.COCKROACH_EGG.get(), throwerIn, worldIn);
    }

    public EntityCockroachEgg(Level worldIn, double x, double y, double z) {
        super((EntityType)AMEntityRegistry.COCKROACH_EGG.get(), x, y, z, worldIn);
    }

    public EntityCockroachEgg(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this((EntityType)AMEntityRegistry.COCKROACH_EGG.get(), world);
    }

    public Packet<ClientGamePacketListener> m_5654_() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    @OnlyIn(value=Dist.CLIENT)
    public void m_7822_(byte id) {
        if (id == 3) {
            for (int i = 0; i < 8; ++i) {
                this.m_9236_().m_7106_((ParticleOptions)new ItemParticleOption(ParticleTypes.f_123752_, this.m_7846_()), this.m_20185_(), this.m_20186_(), this.m_20189_(), ((double)this.f_19796_.m_188501_() - 0.5) * 0.08, ((double)this.f_19796_.m_188501_() - 0.5) * 0.08, ((double)this.f_19796_.m_188501_() - 0.5) * 0.08);
            }
        }
    }

    protected void m_6532_(HitResult result) {
        super.m_6532_(result);
        if (!this.m_9236_().f_46443_) {
            this.m_9236_().m_7605_((Entity)this, (byte)3);
            int i = this.f_19796_.m_188503_(3);
            for (int j = 0; j < i; ++j) {
                EntityCockroach croc = (EntityCockroach)((EntityType)AMEntityRegistry.COCKROACH.get()).m_20615_(this.m_9236_());
                croc.m_146762_(-24000);
                croc.m_7678_(this.m_20185_(), this.m_20186_(), this.m_20189_(), this.m_146908_(), 0.0f);
                croc.m_6518_((ServerLevelAccessor)((ServerLevel)this.m_9236_()), this.m_9236_().m_6436_(this.m_20183_()), MobSpawnType.TRIGGERED, null, null);
                croc.m_21446_(this.m_20183_(), 20);
                this.m_9236_().m_7967_((Entity)croc);
            }
            this.m_9236_().m_7605_((Entity)this, (byte)3);
            this.m_142687_(Entity.RemovalReason.DISCARDED);
        }
    }

    protected Item m_7881_() {
        return (Item)AMItemRegistry.COCKROACH_OOTHECA.get();
    }
}
