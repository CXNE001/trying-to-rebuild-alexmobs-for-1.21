/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.ItemStack
 */
package com.github.alexthe666.citadel.item;

import net.minecraft.world.item.ItemStack;

public interface ItemWithHoverAnimation {
    public float getMaxHoverOverTime(ItemStack var1);

    public boolean canHoverOver(ItemStack var1);
}
