/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.PanicGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.PanicGoal;

class EntityGrizzlyBear.PanicGoal
extends PanicGoal {
    public EntityGrizzlyBear.PanicGoal() {
        super((PathfinderMob)EntityGrizzlyBear.this, 2.0);
    }

    public boolean m_8036_() {
        return (EntityGrizzlyBear.this.m_6162_() || EntityGrizzlyBear.this.m_6060_()) && super.m_8036_();
    }
}
