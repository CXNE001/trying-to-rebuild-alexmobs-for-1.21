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

import com.github.alexthe666.alexsmobs.entity.EntityTiger;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

class EntityTiger.AngerGoal
extends HurtByTargetGoal {
    EntityTiger.AngerGoal(EntityTiger beeIn) {
        super((PathfinderMob)beeIn, new Class[0]);
    }

    public boolean m_8045_() {
        return EntityTiger.this.m_21660_() && super.m_8045_();
    }

    public void m_8056_() {
        super.m_8056_();
        if (EntityTiger.this.m_6162_()) {
            this.m_26047_();
            this.m_8041_();
        }
    }

    protected void m_5766_(Mob mobIn, LivingEntity targetIn) {
        if (!mobIn.m_6162_()) {
            super.m_5766_(mobIn, targetIn);
        }
    }
}
