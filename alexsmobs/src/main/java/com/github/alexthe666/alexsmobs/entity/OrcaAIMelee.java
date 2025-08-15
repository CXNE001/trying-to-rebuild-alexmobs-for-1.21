/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.MeleeAttackGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityOrca;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class OrcaAIMelee
extends MeleeAttackGoal {
    public OrcaAIMelee(EntityOrca orca, double v, boolean b) {
        super((PathfinderMob)orca, v, b);
    }

    public boolean m_8036_() {
        if (this.f_25540_.m_5448_() == null || ((EntityOrca)this.f_25540_).shouldUseJumpAttack(this.f_25540_.m_5448_())) {
            return false;
        }
        return super.m_8036_();
    }
}
