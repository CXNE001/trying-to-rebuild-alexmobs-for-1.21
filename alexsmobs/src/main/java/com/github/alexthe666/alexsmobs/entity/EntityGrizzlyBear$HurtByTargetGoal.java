/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityGrizzlyBear;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

class EntityGrizzlyBear.HurtByTargetGoal
extends HurtByTargetGoal {
    public EntityGrizzlyBear.HurtByTargetGoal() {
        super((PathfinderMob)EntityGrizzlyBear.this, new Class[0]);
    }

    public void m_8056_() {
        super.m_8056_();
        if (EntityGrizzlyBear.this.m_6162_()) {
            this.m_26047_();
            this.m_8041_();
        }
    }

    protected void m_5766_(Mob mobIn, LivingEntity targetIn) {
        if (mobIn instanceof EntityGrizzlyBear && !mobIn.m_6162_()) {
            super.m_5766_(mobIn, targetIn);
        }
    }
}
