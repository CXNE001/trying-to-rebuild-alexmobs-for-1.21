/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.AvoidEntityGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;

class EntityJerboa.1
extends AvoidEntityGoal {
    EntityJerboa.1(PathfinderMob p_25027_, Class p_25028_, float p_25029_, double p_25030_, double p_25031_) {
        super(p_25027_, p_25028_, p_25029_, p_25030_, p_25031_);
    }

    public boolean m_8036_() {
        return !EntityJerboa.this.isBefriended() && super.m_8036_();
    }
}
