/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.ItemStack
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;

public interface ILootsChests {
    public boolean isLootable(Container var1);

    public boolean shouldLootItem(ItemStack var1);
}
