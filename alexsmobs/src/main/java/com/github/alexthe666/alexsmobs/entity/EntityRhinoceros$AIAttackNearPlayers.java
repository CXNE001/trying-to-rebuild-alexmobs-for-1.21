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

class EntityRhinoceros.AIAttackNearPlayers
extends NearestAttackableTargetGoal<Player> {
    public EntityRhinoceros.AIAttackNearPlayers() {
        super((Mob)EntityRhinoceros.this, Player.class, 80, true, true, null);
    }

    public boolean m_8036_() {
        if (EntityRhinoceros.this.m_6162_() || EntityRhinoceros.this.m_27593_() || EntityRhinoceros.this.trustsAny()) {
            return false;
        }
        return super.m_8036_();
    }

    protected double m_7623_() {
        return 3.0;
    }
}
