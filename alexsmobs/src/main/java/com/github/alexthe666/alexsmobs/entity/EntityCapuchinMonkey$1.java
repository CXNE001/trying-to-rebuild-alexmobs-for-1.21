/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.item.crafting.Ingredient
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityCapuchinMonkey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.item.crafting.Ingredient;

class EntityCapuchinMonkey.1
extends TemptGoal {
    EntityCapuchinMonkey.1(PathfinderMob p_25939_, double p_25940_, Ingredient p_25941_, boolean p_25942_) {
        super(p_25939_, p_25940_, p_25941_, p_25942_);
    }

    public void m_8037_() {
        super.m_8037_();
        if (this.f_25924_.m_20280_((Entity)this.f_25925_) < 6.25 && this.f_25924_.m_217043_().m_188503_(14) == 0) {
            ((EntityCapuchinMonkey)this.f_25924_).setAnimation(ANIMATION_HEADTILT);
        }
    }
}
