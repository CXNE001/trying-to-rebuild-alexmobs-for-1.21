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

class EntitySkunk.1
extends PanicGoal {
    EntitySkunk.1(PathfinderMob p_25691_, double p_25692_) {
        super(p_25691_, p_25692_);
    }

    public void m_8037_() {
        super.m_8037_();
        EntitySkunk.this.harassedTime += 10;
    }
}
