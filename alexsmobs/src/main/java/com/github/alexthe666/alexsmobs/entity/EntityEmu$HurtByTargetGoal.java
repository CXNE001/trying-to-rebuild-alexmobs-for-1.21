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

import com.github.alexthe666.alexsmobs.entity.EntityEmu;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

class EntityEmu.HurtByTargetGoal
extends HurtByTargetGoal {
    public EntityEmu.HurtByTargetGoal() {
        super((PathfinderMob)EntityEmu.this, new Class[0]);
    }

    public void m_8056_() {
        if (EntityEmu.this.m_6162_() || !EntityEmu.this.emuAttackedDirectly) {
            this.m_26047_();
            this.m_8041_();
        } else {
            super.m_8056_();
        }
    }

    protected void m_5766_(Mob mobIn, LivingEntity targetIn) {
        if (mobIn instanceof EntityEmu && !mobIn.m_6162_() && !EntityEmu.this.emuAttackedDirectly && ((EntityEmu)mobIn).revengeCooldown <= 0) {
            super.m_5766_(mobIn, targetIn);
        }
    }
}
