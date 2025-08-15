/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;

private class EntityGuster.MeleeGoal
extends Goal {
    public boolean m_8036_() {
        return EntityGuster.this.m_5448_() != null;
    }

    public void m_8037_() {
        Entity thrownEntity = EntityGuster.this.getLiftedEntity();
        if (EntityGuster.this.m_5448_() != null) {
            if (thrownEntity != null && thrownEntity.m_19879_() == EntityGuster.this.m_5448_().m_19879_()) {
                EntityGuster.this.m_21573_().m_26573_();
            } else {
                EntityGuster.this.m_21573_().m_5624_((Entity)EntityGuster.this.m_5448_(), 1.25);
            }
        }
    }
}
