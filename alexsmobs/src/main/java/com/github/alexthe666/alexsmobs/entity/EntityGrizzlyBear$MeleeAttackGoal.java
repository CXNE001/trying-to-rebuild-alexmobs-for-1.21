/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.MeleeAttackGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

class EntityGrizzlyBear.MeleeAttackGoal
extends MeleeAttackGoal {
    public EntityGrizzlyBear.MeleeAttackGoal() {
        super((PathfinderMob)EntityGrizzlyBear.this, 1.25, true);
    }

    protected void m_6739_(LivingEntity enemy, double distToEnemySqr) {
        double d0 = this.m_6639_(enemy);
        if (distToEnemySqr <= d0 && (EntityGrizzlyBear.this.getAnimation() == IAnimatedEntity.NO_ANIMATION || EntityGrizzlyBear.this.getAnimation() == ANIMATION_SNIFF)) {
            EntityGrizzlyBear.this.setAnimation(EntityGrizzlyBear.this.f_19796_.m_188499_() ? ANIMATION_MAUL : (EntityGrizzlyBear.this.f_19796_.m_188499_() ? ANIMATION_SWIPE_L : ANIMATION_SWIPE_R));
        }
    }

    public void m_8041_() {
        EntityGrizzlyBear.this.setStanding(false);
        super.m_8041_();
    }

    protected double m_6639_(LivingEntity attackTarget) {
        return 3.0f + attackTarget.m_20205_();
    }
}
