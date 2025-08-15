/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;

class EntityCaiman.2
extends OwnerHurtTargetGoal {
    EntityCaiman.2(TamableAnimal p_26114_) {
        super(p_26114_);
    }

    public void m_8056_() {
        super.m_8056_();
        EntityCaiman.this.tameAttackFlag = true;
    }

    public void m_8041_() {
        super.m_8056_();
        EntityCaiman.this.tameAttackFlag = false;
    }
}
