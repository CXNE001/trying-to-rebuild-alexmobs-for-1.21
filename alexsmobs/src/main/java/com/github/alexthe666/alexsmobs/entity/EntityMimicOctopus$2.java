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

class EntityMimicOctopus.2
extends HurtByTargetGoal {
    EntityMimicOctopus.2(PathfinderMob p_26039_, Class ... p_26040_) {
        super(p_26039_, p_26040_);
    }

    public boolean m_8036_() {
        return EntityMimicOctopus.this.m_21824_() && super.m_8036_();
    }
}
