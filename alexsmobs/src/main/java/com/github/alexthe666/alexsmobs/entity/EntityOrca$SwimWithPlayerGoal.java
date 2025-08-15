/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.player.Player
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityOrca;
import java.util.EnumSet;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

static class EntityOrca.SwimWithPlayerGoal
extends Goal {
    private final EntityOrca dolphin;
    private final double speed;
    private Player targetPlayer;

    EntityOrca.SwimWithPlayerGoal(EntityOrca dolphinIn, double speedIn) {
        this.dolphin = dolphinIn;
        this.speed = speedIn;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        this.targetPlayer = this.dolphin.m_9236_().m_45946_(PLAYER_PREDICATE, (LivingEntity)this.dolphin);
        if (this.targetPlayer == null) {
            return false;
        }
        return this.targetPlayer.m_6069_() && this.dolphin.m_5448_() != this.targetPlayer;
    }

    public boolean m_8045_() {
        return this.targetPlayer != null && this.dolphin.m_5448_() != this.targetPlayer && this.targetPlayer.m_6069_() && this.dolphin.m_20280_((Entity)this.targetPlayer) < 256.0;
    }

    public void m_8056_() {
    }

    public void m_8041_() {
        this.targetPlayer = null;
        this.dolphin.m_21573_().m_26573_();
    }

    public void m_8037_() {
        this.dolphin.m_21563_().m_24960_((Entity)this.targetPlayer, (float)(this.dolphin.m_8085_() + 20), (float)this.dolphin.m_8132_());
        if (this.dolphin.m_20280_((Entity)this.targetPlayer) < 10.0) {
            this.dolphin.m_21573_().m_26573_();
        } else {
            this.dolphin.m_21573_().m_5624_((Entity)this.targetPlayer, this.speed);
        }
        if (this.targetPlayer.m_6069_() && this.targetPlayer.m_9236_().f_46441_.m_188503_(6) == 0) {
            this.targetPlayer.m_7292_(new MobEffectInstance((MobEffect)AMEffectRegistry.ORCAS_MIGHT.get(), 1000));
        }
    }
}
