/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.MeleeAttackGoal
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityCrocodile;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class CrocodileAIMelee
extends MeleeAttackGoal {
    private final EntityCrocodile crocodile;

    public CrocodileAIMelee(EntityCrocodile crocodile, double speedIn, boolean useLongMemory) {
        super((PathfinderMob)crocodile, speedIn, useLongMemory);
        this.crocodile = crocodile;
    }

    public boolean m_8036_() {
        return super.m_8036_() && this.crocodile.m_20197_().isEmpty();
    }

    public boolean m_8045_() {
        return super.m_8045_() && this.crocodile.m_20197_().isEmpty();
    }

    protected void m_6739_(LivingEntity enemy, double distToEnemySqr) {
        double d0 = this.m_6639_(enemy);
        if (distToEnemySqr <= d0) {
            this.m_25563_();
            this.f_25540_.m_6674_(InteractionHand.MAIN_HAND);
            this.f_25540_.m_7327_((Entity)enemy);
        }
    }
}
