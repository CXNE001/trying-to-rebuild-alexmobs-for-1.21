/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

class EntityAlligatorSnappingTurtle.1
extends HurtByTargetGoal {
    EntityAlligatorSnappingTurtle.1(PathfinderMob p_26039_, Class ... p_26040_) {
        super(p_26039_, p_26040_);
    }

    public boolean m_8045_() {
        return EntityAlligatorSnappingTurtle.this.chaseTime >= 0 && super.m_8045_();
    }
}
