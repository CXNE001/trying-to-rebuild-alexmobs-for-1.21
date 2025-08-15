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

class EntityPlatypus.2
extends TemptGoal {
    EntityPlatypus.2(PathfinderMob p_25939_, double p_25940_, Ingredient p_25941_, boolean p_25942_) {
        super(p_25939_, p_25940_, p_25941_, p_25942_);
    }

    public boolean m_8036_() {
        return super.m_8036_() && !EntityPlatypus.this.isSensing();
    }
}
