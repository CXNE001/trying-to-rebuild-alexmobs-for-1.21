/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

class EntityCosmaw.2
extends HurtByTargetGoal {
    EntityCosmaw.2(PathfinderMob p_26039_, Class ... p_26040_) {
        super(p_26039_, p_26040_);
    }

    public boolean m_8036_() {
        LivingEntity livingentity = this.f_26135_.m_21188_();
        if (livingentity != null && EntityCosmaw.this.m_21830_(livingentity)) {
            return false;
        }
        return super.m_8036_();
    }
}
