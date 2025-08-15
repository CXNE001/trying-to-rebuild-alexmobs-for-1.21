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

public class AnimalAIFollowParentRanged
extends Goal {
    private final Animal childAnimal;
    private Animal parentAnimal;
    private final double moveSpeed;
    private int delayCounter;
    private float range = 8.0f;
    private float minDist = 3.0f;

    public AnimalAIFollowParentRanged(Animal p_i1626_1_, double p_i1626_2_, float range, float minDist) {
        this.childAnimal = p_i1626_1_;
        this.moveSpeed = p_i1626_2_;
        this.range = range;
        this.minDist = minDist;
    }

    public boolean m_8036_() {
        if (this.childAnimal.m_146764_() >= 0) {
            return false;
        }
        List lvt_1_1_ = this.childAnimal.m_9236_().m_45976_(this.childAnimal.getClass(), this.childAnimal.m_20191_().m_82377_((double)this.range, (double)this.range * 0.5, (double)this.range));
        Animal lvt_2_1_ = null;
        double lvt_3_1_ = Double.MAX_VALUE;
        for (Animal lvt_6_1_ : lvt_1_1_) {
            double lvt_7_1_;
            if (lvt_6_1_.m_146764_() < 0 || !((lvt_7_1_ = this.childAnimal.m_20280_((Entity)lvt_6_1_)) <= lvt_3_1_)) continue;
            lvt_3_1_ = lvt_7_1_;
            lvt_2_1_ = lvt_6_1_;
        }
        if (lvt_2_1_ == null) {
            return false;
        }
        if (lvt_3_1_ < (double)(this.minDist * this.minDist)) {
            return false;
        }
        this.parentAnimal = lvt_2_1_;
        return true;
    }

    public boolean m_8045_() {
        if (this.childAnimal.m_146764_() >= 0) {
            return false;
        }
        if (!this.parentAnimal.m_6084_()) {
            return false;
        }
        double lvt_1_1_ = this.childAnimal.m_20280_((Entity)this.parentAnimal);
        return lvt_1_1_ >= (double)(this.minDist * this.minDist) && lvt_1_1_ <= (double)(this.range * this.range);
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
    }
}
