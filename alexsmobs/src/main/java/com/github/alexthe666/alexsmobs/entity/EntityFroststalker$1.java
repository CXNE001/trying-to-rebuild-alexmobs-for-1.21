/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.FloatGoal;

class EntityFroststalker.1
extends FloatGoal {
    EntityFroststalker.1(Mob p_25230_) {
        super(p_25230_);
    }

    public void m_8037_() {
        if (EntityFroststalker.this.m_217043_().m_188501_() < 0.8f) {
            if (EntityFroststalker.this.hasSpikes()) {
                EntityFroststalker.this.jumpUnderwater();
            } else {
                EntityFroststalker.this.m_21569_().m_24901_();
            }
        }
    }
}
