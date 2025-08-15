/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.animal.Animal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;

class EntityEndergrade.1
extends BreedGoal {
    EntityEndergrade.1(Animal p_25122_, double p_25123_) {
        super(p_25122_, p_25123_);
    }

    public void m_8056_() {
        super.m_8056_();
        EntityEndergrade.this.stopWandering = true;
    }

    public void m_8041_() {
        super.m_8041_();
        EntityEndergrade.this.stopWandering = false;
    }
}
