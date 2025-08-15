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

class EntityRattlesnake.ShortDistanceTarget
extends NearestAttackableTargetGoal<Player> {
    public EntityRattlesnake.ShortDistanceTarget() {
        super((Mob)EntityRattlesnake.this, Player.class, 3, true, true, TARGETABLE_PREDICATE);
    }

    public boolean m_8036_() {
        if (EntityRattlesnake.this.m_6162_()) {
            return false;
        }
        return super.m_8036_();
    }

    public void m_8056_() {
        super.m_8056_();
        EntityRattlesnake.this.setCurled(false);
        EntityRattlesnake.this.setRattling(true);
    }

    protected double m_7623_() {
        return 2.0;
    }
}
