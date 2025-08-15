/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityLeafcutterAnt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

class EntityLeafcutterAnt.AngerGoal
extends HurtByTargetGoal {
    EntityLeafcutterAnt.AngerGoal(EntityLeafcutterAnt beeIn) {
        super((PathfinderMob)beeIn, new Class[0]);
        this.m_26044_(new Class[]{EntityLeafcutterAnt.class});
    }

    public boolean m_8045_() {
        return EntityLeafcutterAnt.this.m_21660_() && super.m_8045_();
    }

    protected void m_5766_(Mob mobIn, LivingEntity targetIn) {
        if (mobIn instanceof EntityLeafcutterAnt && this.f_26135_.m_142582_((Entity)targetIn)) {
            mobIn.m_6710_(targetIn);
        }
    }
}
