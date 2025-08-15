/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;

class EntityBunfungus.4
extends RandomLookAroundGoal {
    EntityBunfungus.4(Mob p_25720_) {
        super(p_25720_);
    }

    public boolean m_8036_() {
        return super.m_8036_() && EntityBunfungus.this.canUseComplexAI();
    }
}
