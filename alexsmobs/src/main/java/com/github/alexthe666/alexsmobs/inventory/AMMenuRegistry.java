/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.flag.FeatureFlags
 *  net.minecraft.world.inventory.MenuType
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.alexsmobs.inventory;

import com.github.alexthe666.alexsmobs.inventory.MenuTransmutationTable;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class AMMenuRegistry {
    public static final DeferredRegister<MenuType<?>> DEF_REG = DeferredRegister.create((IForgeRegistry)ForgeRegistries.MENU_TYPES, (String)"alexsmobs");
    public static final RegistryObject<MenuType<MenuTransmutationTable>> TRANSMUTATION_TABLE = DEF_REG.register("transmutation_table", () -> new MenuType(MenuTransmutationTable::new, FeatureFlags.f_244332_));
}
