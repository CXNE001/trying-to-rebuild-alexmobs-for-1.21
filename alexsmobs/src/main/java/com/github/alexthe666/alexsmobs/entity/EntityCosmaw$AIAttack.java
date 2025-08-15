/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;

private class EntityCosmaw.AIAttack
extends Goal {
    public EntityCosmaw.AIAttack() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        return EntityCosmaw.this.m_5448_() != null && EntityCosmaw.this.m_5448_().m_6084_();
    }

    public void m_8037_() {
        double d = EntityCosmaw.this.m_20270_((Entity)EntityCosmaw.this.m_5448_());
        float f = EntityCosmaw.this.m_6162_() ? 0.5f : 1.0f;
        if (d < 3.0 * (double)f) {
            EntityCosmaw.this.m_7327_((Entity)EntityCosmaw.this.m_5448_());
        } else {
            EntityCosmaw.this.m_21573_().m_5624_((Entity)EntityCosmaw.this.m_5448_(), 1.0);
        }
    }
}
