/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityLeafcutterAnt;
import java.util.function.Predicate;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

private class EntityAnteater.AITargetAnts
extends NearestAttackableTargetGoal {
    private static final Predicate<EntityLeafcutterAnt> QUEEN_ANT = entity -> !entity.isQueen();

    public EntityAnteater.AITargetAnts() {
        super((Mob)EntityAnteater.this, EntityLeafcutterAnt.class, 30, true, false, QUEEN_ANT);
    }

    public boolean m_8036_() {
        return EntityAnteater.this.shouldTargetAnts() && !EntityAnteater.this.m_6162_() && !EntityAnteater.this.hasAntOnTongue() && !EntityAnteater.this.isStanding() && super.m_8036_();
    }

    public boolean m_8045_() {
        return EntityAnteater.this.shouldTargetAnts() && !EntityAnteater.this.hasAntOnTongue() && !EntityAnteater.this.isStanding() && super.m_8045_();
    }
}
