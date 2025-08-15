/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 */
package com.github.alexthe666.alexsmobs.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemFuel
extends Item {
    private final int burnTime;

    public ItemFuel(Item.Properties props, int burnTime) {
        super(props);
        this.burnTime = burnTime;
    }

    public int getBurnTime(ItemStack itemStack) {
        return this.burnTime;
    }
}
