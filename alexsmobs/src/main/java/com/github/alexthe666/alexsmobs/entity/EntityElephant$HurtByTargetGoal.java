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

import com.github.alexthe666.alexsmobs.entity.EntityElephant;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

class EntityElephant.HurtByTargetGoal
extends HurtByTargetGoal {
    public EntityElephant.HurtByTargetGoal() {
        super((PathfinderMob)EntityElephant.this, new Class[0]);
    }

    public void m_8056_() {
        if (EntityElephant.this.m_6162_() || !EntityElephant.this.isTusked()) {
            this.m_26047_();
            this.m_8041_();
        } else {
            super.m_8056_();
        }
    }

    protected void m_5766_(Mob mobIn, LivingEntity targetIn) {
        if (!(!(mobIn instanceof EntityElephant) || mobIn.m_6162_() && ((EntityElephant)mobIn).isTusked())) {
            super.m_5766_(mobIn, targetIn);
        }
    }
}
