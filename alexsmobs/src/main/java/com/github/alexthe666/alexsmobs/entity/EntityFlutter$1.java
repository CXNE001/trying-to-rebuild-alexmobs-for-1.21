/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.item.crafting.Ingredient
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.TameableAITempt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.crafting.Ingredient;

class EntityFlutter.1
extends TameableAITempt {
    EntityFlutter.1(Animal tameable, double speedIn, Ingredient temptItemsIn, boolean scaredByPlayerMovementIn) {
        super(tameable, speedIn, temptItemsIn, scaredByPlayerMovementIn);
    }

    @Override
    public boolean shouldFollowAM(LivingEntity le) {
        return EntityFlutter.this.canEatFlower(le.m_21205_()) || EntityFlutter.this.canEatFlower(le.m_21206_()) || super.shouldFollowAM(le);
    }
}
