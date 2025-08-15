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

class EntityBaldEagle.2
extends LookAtPlayerGoal {
    EntityBaldEagle.2(Mob p_25520_, Class p_25521_, float p_25522_) {
        super(p_25520_, p_25521_, p_25522_);
    }

    public boolean m_8036_() {
        return EntityBaldEagle.this.returnControlTime == 0 && super.m_8036_();
    }
}
