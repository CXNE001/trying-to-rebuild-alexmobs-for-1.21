/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.animal.Animal
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;

public class AnimalAIRideParent
extends Goal {
    private final Animal childAnimal;
    private Animal parentAnimal;
    private final double moveSpeed;
    private int delayCounter;

    public AnimalAIRideParent(Animal animal, double speed) {
        this.childAnimal = animal;
        this.moveSpeed = speed;
    }

    public boolean m_8036_() {
        if (this.childAnimal.m_146764_() >= 0 || this.childAnimal.m_20159_()) {
            return false;
        }
        List list = this.childAnimal.m_9236_().m_45976_(this.childAnimal.getClass(), this.childAnimal.m_20191_().m_82377_(8.0, 4.0, 8.0));
        Animal animalentity = null;
        double d0 = Double.MAX_VALUE;
        for (Animal animalentity1 : list) {
            double d1;
            if (animalentity1.m_146764_() < 0 || !animalentity1.m_20197_().isEmpty() || (d1 = this.childAnimal.m_20280_((Entity)animalentity1)) > d0) continue;
            d0 = d1;
            animalentity = animalentity1;
        }
        if (animalentity == null) {
            return false;
        }
        if (d0 < 2.0) {
            return false;
        }
        this.parentAnimal = animalentity;
        return true;
    }

    public boolean m_8045_() {
        if (this.childAnimal.m_146764_() >= 0) {
            return false;
        }
        if (this.parentAnimal == null || !this.parentAnimal.m_6084_() || !this.parentAnimal.m_20197_().isEmpty()) {
            return false;
        }
        double d0 = this.childAnimal.m_20280_((Entity)this.parentAnimal);
        return !(d0 < 2.0) && !(d0 > 256.0) && !this.childAnimal.m_20365_((Entity)this.parentAnimal);
    }

    public void m_8056_() {
        this.delayCounter = 0;
    }

    public void m_8041_() {
        this.parentAnimal = null;
    }

    public void m_8037_() {
        if (--this.delayCounter <= 0) {
            this.delayCounter = 10;
            this.childAnimal.m_21573_().m_5624_((Entity)this.parentAnimal, this.moveSpeed);
        }
        if ((double)this.childAnimal.m_20270_((Entity)this.parentAnimal) < 2.0) {
            this.childAnimal.m_7998_((Entity)this.parentAnimal, false);
            this.m_8041_();
        }
    }
}
