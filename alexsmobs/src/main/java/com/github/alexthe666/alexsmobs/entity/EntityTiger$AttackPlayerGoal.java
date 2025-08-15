/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.player.Player
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;

class EntityTiger.AttackPlayerGoal
extends NearestAttackableTargetGoal<Player> {
    public EntityTiger.AttackPlayerGoal() {
        super((Mob)EntityTiger.this, Player.class, 100, false, true, NO_BLESSING_EFFECT);
    }

    public boolean m_8036_() {
        if (EntityTiger.this.m_6162_()) {
            return false;
        }
        return super.m_8036_();
    }

    protected double m_7623_() {
        return 4.0;
    }
}
