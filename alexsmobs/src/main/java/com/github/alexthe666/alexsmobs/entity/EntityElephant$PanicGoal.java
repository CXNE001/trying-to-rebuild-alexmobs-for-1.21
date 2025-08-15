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

class EntityElephant.PanicGoal
extends PanicGoal {
    public EntityElephant.PanicGoal() {
        super((PathfinderMob)EntityElephant.this, 1.0);
    }

    public boolean m_8036_() {
        return (EntityElephant.this.m_6162_() || !EntityElephant.this.isTusked() || EntityElephant.this.m_6060_()) && super.m_8036_();
    }
}
