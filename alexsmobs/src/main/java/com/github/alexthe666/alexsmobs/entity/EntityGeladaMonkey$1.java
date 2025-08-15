/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.MeleeAttackGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

class EntityGeladaMonkey.1
extends MeleeAttackGoal {
    EntityGeladaMonkey.1(PathfinderMob p_25552_, double p_25553_, boolean p_25554_) {
        super(p_25552_, p_25553_, p_25554_);
    }

    protected double m_6639_(LivingEntity attackTarget) {
        return super.m_6639_(attackTarget) + 1.5;
    }

    public boolean m_8036_() {
        return super.m_8036_() && EntityGeladaMonkey.this.revengeCooldown <= 0;
    }

    public boolean m_8045_() {
        return super.m_8045_() && EntityGeladaMonkey.this.revengeCooldown <= 0;
    }
}
