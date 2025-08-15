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

class EntityGrizzlyBear.AttackPlayerGoal
extends NearestAttackableTargetGoal<Player> {
    public EntityGrizzlyBear.AttackPlayerGoal() {
        super((Mob)EntityGrizzlyBear.this, Player.class, 3, true, true, null);
    }

    public boolean m_8036_() {
        if (EntityGrizzlyBear.this.m_6162_() || EntityGrizzlyBear.this.getAprilFoolsFlag() >= 1 || EntityGrizzlyBear.this.isHoneyed()) {
            return false;
        }
        return super.m_8036_();
    }

    protected double m_7623_() {
        return 5.0;
    }
}
