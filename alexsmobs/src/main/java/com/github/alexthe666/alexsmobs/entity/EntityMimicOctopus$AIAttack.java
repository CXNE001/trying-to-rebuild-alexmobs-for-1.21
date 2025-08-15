/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityMimicOctopus;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntityMimicOctopus.AIAttack
extends Goal {
    private int executionCooldown = 0;
    private int scareMobTime = 0;
    private Vec3 fleePosition = null;

    public EntityMimicOctopus.AIAttack() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        if (this.executionCooldown > 0) {
            EntityMimicOctopus.this.f_19804_.m_135381_(UPGRADED_LASER_ENTITY_ID, (Object)-1);
            --this.executionCooldown;
        }
        if (EntityMimicOctopus.this.isStopChange() && EntityMimicOctopus.this.getMimicState() == EntityMimicOctopus.MimicState.OVERLAY) {
            return false;
        }
        return this.executionCooldown == 0 && EntityMimicOctopus.this.m_21824_() && EntityMimicOctopus.this.m_5448_() != null && EntityMimicOctopus.this.m_5448_().m_6084_();
    }

    public void m_8041_() {
        this.fleePosition = null;
        this.scareMobTime = 0;
        this.executionCooldown = 100 + EntityMimicOctopus.this.f_19796_.m_188503_(200);
        if (EntityMimicOctopus.this.isUpgraded()) {
            this.executionCooldown = 30;
        } else {
            EntityMimicOctopus.this.m_6703_(null);
            EntityMimicOctopus.this.m_6710_(null);
        }
        if (EntityMimicOctopus.this.stopMimicCooldown <= 0) {
            EntityMimicOctopus.this.mimicEnvironment();
        }
        EntityMimicOctopus.this.f_19804_.m_135381_(UPGRADED_LASER_ENTITY_ID, (Object)-1);
    }

    public Vec3 generateFleePosition(LivingEntity fleer) {
        for (int i = 0; i < 15; ++i) {
            BlockPos pos = fleer.m_20183_().m_7918_(EntityMimicOctopus.this.f_19796_.m_188503_(32) - 16, EntityMimicOctopus.this.f_19796_.m_188503_(16), EntityMimicOctopus.this.f_19796_.m_188503_(32) - 16);
            while (fleer.m_9236_().m_46859_(pos) && pos.m_123342_() > 1) {
                pos = pos.m_7495_();
            }
            if (fleer instanceof PathfinderMob) {
                if (!(((PathfinderMob)fleer).m_21692_(pos) >= 0.0f)) continue;
                return Vec3.m_82512_((Vec3i)pos);
            }
            return Vec3.m_82512_((Vec3i)pos);
        }
        return null;
    }

    public void m_8037_() {
        LivingEntity target = EntityMimicOctopus.this.m_5448_();
        if (target != null) {
            if (this.scareMobTime > 0) {
                if (this.fleePosition == null || target.m_20238_(this.fleePosition) < (double)(target.m_20205_() * target.m_20205_() * 2.0f)) {
                    this.fleePosition = this.generateFleePosition(target);
                }
                if (target instanceof Mob && this.fleePosition != null) {
                    ((Mob)target).m_21573_().m_26519_(this.fleePosition.f_82479_, this.fleePosition.f_82480_, this.fleePosition.f_82481_, 1.5);
                    ((Mob)target).m_21566_().m_6849_(this.fleePosition.f_82479_, this.fleePosition.f_82480_, this.fleePosition.f_82481_, 1.5);
                    ((Mob)target).m_6710_(null);
                }
                EntityMimicOctopus.this.camoCooldown = Math.max(EntityMimicOctopus.this.camoCooldown, 20);
                EntityMimicOctopus.this.stopMimicCooldown = Math.max(EntityMimicOctopus.this.stopMimicCooldown, 20);
                --this.scareMobTime;
                if (this.scareMobTime == 0) {
                    this.m_8041_();
                    return;
                }
            }
            double dist = EntityMimicOctopus.this.m_20270_((Entity)target);
            boolean move = true;
            if (dist < 7.0 && EntityMimicOctopus.this.m_142582_((Entity)target) && EntityMimicOctopus.this.getMimicState() == EntityMimicOctopus.MimicState.GUARDIAN && EntityMimicOctopus.this.isUpgraded()) {
                EntityMimicOctopus.this.f_19804_.m_135381_(UPGRADED_LASER_ENTITY_ID, (Object)target.m_19879_());
                move = false;
            }
            if (dist < 3.0) {
                EntityMimicOctopus.this.f_19804_.m_135381_(LAST_SCARED_MOB_ID, (Object)target.m_19879_());
                if (move) {
                    move = EntityMimicOctopus.this.isUpgraded() && dist > 2.0;
                }
                EntityMimicOctopus.this.m_21573_().m_26573_();
                if (!EntityMimicOctopus.this.isStopChange()) {
                    EntityMimicOctopus.this.setMimickedBlock(null);
                    EntityMimicOctopus.MimicState prev = EntityMimicOctopus.this.getMimicState();
                    if (EntityMimicOctopus.this.m_20072_()) {
                        if (prev != EntityMimicOctopus.MimicState.GUARDIAN && prev != EntityMimicOctopus.MimicState.PUFFERFISH) {
                            if (EntityMimicOctopus.this.f_19796_.m_188499_()) {
                                EntityMimicOctopus.this.setMimicState(EntityMimicOctopus.MimicState.GUARDIAN);
                            } else {
                                EntityMimicOctopus.this.setMimicState(EntityMimicOctopus.MimicState.PUFFERFISH);
                            }
                        }
                    } else {
                        EntityMimicOctopus.this.setMimicState(EntityMimicOctopus.MimicState.CREEPER);
                    }
                }
                if (EntityMimicOctopus.this.getMimicState() != EntityMimicOctopus.MimicState.OVERLAY) {
                    EntityMimicOctopus.this.mimicCooldown = 40;
                    EntityMimicOctopus.this.stopMimicCooldown = Math.max(EntityMimicOctopus.this.stopMimicCooldown, 60);
                }
                if (EntityMimicOctopus.this.isUpgraded() && EntityMimicOctopus.this.transProgress >= 5.0f) {
                    if (EntityMimicOctopus.this.getMimicState() == EntityMimicOctopus.MimicState.PUFFERFISH && EntityMimicOctopus.this.m_20191_().m_82363_(2.0, 1.3, 2.0).m_82381_(target.m_20191_())) {
                        target.m_6469_(EntityMimicOctopus.this.m_269291_().m_269333_((LivingEntity)EntityMimicOctopus.this), 4.0f);
                        target.m_7292_(new MobEffectInstance(MobEffects.f_19614_, 400, 2));
                    }
                    if (EntityMimicOctopus.this.getMimicState() == EntityMimicOctopus.MimicState.GUARDIAN) {
                        if (EntityMimicOctopus.this.m_20191_().m_82363_(1.0, 1.0, 1.0).m_82381_(target.m_20191_())) {
                            target.m_6469_(EntityMimicOctopus.this.m_269291_().m_269333_((LivingEntity)EntityMimicOctopus.this), 1.0f);
                        }
                        EntityMimicOctopus.this.f_19804_.m_135381_(UPGRADED_LASER_ENTITY_ID, (Object)target.m_19879_());
                    }
                    if (EntityMimicOctopus.this.getMimicState() == EntityMimicOctopus.MimicState.CREEPER) {
                        EntityMimicOctopus.this.creeperExplode();
                        EntityMimicOctopus.this.m_9236_().m_7605_((Entity)EntityMimicOctopus.this, (byte)69);
                        this.executionCooldown = 300;
                    }
                }
                if (this.scareMobTime == 0) {
                    EntityMimicOctopus.this.m_9236_().m_7605_((Entity)EntityMimicOctopus.this, (byte)68);
                    this.scareMobTime = 60 + EntityMimicOctopus.this.f_19796_.m_188503_(60);
                }
            }
            if (move) {
                EntityMimicOctopus.this.m_21391_((Entity)target, 30.0f, 30.0f);
                EntityMimicOctopus.this.m_21573_().m_5624_((Entity)target, (double)1.2f);
            }
        }
    }
}
