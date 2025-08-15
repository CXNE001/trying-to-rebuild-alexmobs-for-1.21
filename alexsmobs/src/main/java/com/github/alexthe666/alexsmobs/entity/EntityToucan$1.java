/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.item.crafting.Ingredient
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.item.crafting.Ingredient;

class EntityToucan.1
extends TemptGoal {
    EntityToucan.1(PathfinderMob p_25939_, double p_25940_, Ingredient p_25941_, boolean p_25942_) {
        super(p_25939_, p_25940_, p_25941_, p_25942_);
    }

    public boolean m_8036_() {
        return !EntityToucan.this.aiItemFlag && super.m_8036_();
    }
}
