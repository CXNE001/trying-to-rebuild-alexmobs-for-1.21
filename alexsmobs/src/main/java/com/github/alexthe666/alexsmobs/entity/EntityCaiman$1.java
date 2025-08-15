/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;

class EntityCaiman.1
extends OwnerHurtByTargetGoal {
    EntityCaiman.1(TamableAnimal p_26107_) {
        super(p_26107_);
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
