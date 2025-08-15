/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.ItemStack
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

public interface ITargetsDroppedItems {
    public boolean canTargetItem(ItemStack var1);

    public void onGetItem(ItemEntity var1);

    default public void onFindTarget(ItemEntity e) {
    }

    default public double getMaxDistToItem() {
        return 2.0;
    }

    default public void setItemFlag(boolean itemAIFlag) {
    }

    default public void peck() {
    }

    default public void setFlying(boolean flying) {
    }

    default public boolean isFlying() {
        return false;
    }
}
