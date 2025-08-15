/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.PanicGoal
 *  net.minecraft.world.entity.animal.Animal
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.animal.Animal;

public class AnimalAIPanicBaby
extends PanicGoal {
    private final Animal animal;

    public AnimalAIPanicBaby(Animal creatureIn, double speed) {
        super((PathfinderMob)creatureIn, speed);
        this.animal = creatureIn;
    }

    public boolean m_8036_() {
        return this.animal.m_6162_() && super.m_8036_();
    }
}
