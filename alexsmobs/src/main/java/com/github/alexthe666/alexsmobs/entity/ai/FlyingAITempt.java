/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.item.crafting.Ingredient
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.ITargetsDroppedItems;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.item.crafting.Ingredient;

public class FlyingAITempt
extends TemptGoal {
    public FlyingAITempt(PathfinderMob mob, double speed, Ingredient ingredient, boolean skittish) {
        super(mob, speed, ingredient, skittish);
    }

    public void m_8037_() {
        super.m_8037_();
        PathfinderMob pathfinderMob = this.f_25924_;
        if (pathfinderMob instanceof ITargetsDroppedItems) {
            ITargetsDroppedItems hasFlyingItemAI = (ITargetsDroppedItems)pathfinderMob;
            if (this.f_25924_.m_20096_()) {
                hasFlyingItemAI.setFlying(false);
            }
        }
    }
}
