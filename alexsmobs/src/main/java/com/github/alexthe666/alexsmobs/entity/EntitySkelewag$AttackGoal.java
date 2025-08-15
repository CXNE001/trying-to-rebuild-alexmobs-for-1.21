/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntitySkelewag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

private class EntitySkelewag.AttackGoal
extends Goal {
    private final EntitySkelewag fish;
    private boolean isCharging = false;

    public EntitySkelewag.AttackGoal(EntitySkelewag skelewag) {
        this.fish = skelewag;
    }

    public boolean m_8036_() {
        return this.fish.m_5448_() != null;
    }

    public void m_8037_() {
        LivingEntity target = this.fish.m_5448_();
        if (target != null) {
            double dist = this.fish.m_20270_((Entity)target);
            if (dist > 5.0) {
                this.isCharging = true;
            }
            this.fish.m_21573_().m_5624_((Entity)target, this.isCharging ? (double)1.3f : (double)0.8f);
            if (dist < (double)(5.0f + target.m_20205_() / 2.0f)) {
                this.fish.setAnimation(this.isCharging ? ANIMATION_STAB : (EntitySkelewag.this.f_19796_.m_188499_() ? ANIMATION_SLASH : ANIMATION_STAB));
                this.isCharging = false;
            }
        }
    }

    public void m_8041_() {
        this.isCharging = false;
    }
}
