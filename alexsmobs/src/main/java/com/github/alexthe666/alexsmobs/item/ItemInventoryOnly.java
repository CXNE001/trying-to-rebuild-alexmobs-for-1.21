/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.CreativeModeTab$Output
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.item.CustomTabBehavior;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class ItemInventoryOnly
extends Item
implements CustomTabBehavior {
    public ItemInventoryOnly(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void fillItemCategory(CreativeModeTab.Output contents) {
    }
}
