/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;

class EntityBunfungus.3
extends LookAtPlayerGoal {
    EntityBunfungus.3(Mob p_25520_, Class p_25521_, float p_25522_) {
        super(p_25520_, p_25521_, p_25522_);
    }

    public boolean m_8036_() {
        return super.m_8036_() && EntityBunfungus.this.canUseComplexAI();
    }
}
