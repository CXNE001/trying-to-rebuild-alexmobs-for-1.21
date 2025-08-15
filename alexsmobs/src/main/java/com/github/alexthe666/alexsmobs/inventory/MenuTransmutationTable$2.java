/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.Container
 *  net.minecraft.world.inventory.Slot
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.registries.ForgeRegistries
 */
package com.github.alexthe666.alexsmobs.inventory;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

class MenuTransmutationTable.2
extends Slot {
    MenuTransmutationTable.2(Container p_40223_, int p_40224_, int p_40225_, int p_40226_) {
        super(p_40223_, p_40224_, p_40225_, p_40226_);
    }

    public boolean m_5857_(ItemStack stack) {
        ResourceLocation name = ForgeRegistries.ITEMS.getKey((Object)stack.m_41720_());
        return stack.m_41741_() > 1 && (name == null || !AMConfig.transmutationBlacklist.contains(name.toString()));
    }
}
