/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Direction
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.animal.Animal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;

class EntitySugarGlider.2
extends BreedGoal {
    EntitySugarGlider.2(Animal p_25122_, double p_25123_) {
        super(p_25122_, p_25123_);
    }

    public void m_8056_() {
        super.m_8056_();
        EntitySugarGlider.this.f_19804_.m_135381_(ATTACHED_FACE, (Object)Direction.DOWN);
    }
}
