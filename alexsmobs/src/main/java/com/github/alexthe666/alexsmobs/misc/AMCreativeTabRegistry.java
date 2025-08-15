/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.item.CreativeModeTab
 *  net.minecraft.world.item.CreativeModeTabs
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.alexsmobs.misc;

import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.item.CustomTabBehavior;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AMCreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> DEF_REG = DeferredRegister.create((ResourceKey)Registries.f_279569_, (String)"alexsmobs");
    public static final RegistryObject<CreativeModeTab> TAB = DEF_REG.register("alexsmobs", () -> CreativeModeTab.builder().m_257941_((Component)Component.m_237115_((String)"itemGroup.alexsmobs")).withTabsBefore(new ResourceKey[]{CreativeModeTabs.f_256731_}).m_257737_(() -> new ItemStack((ItemLike)AMItemRegistry.TAB_ICON.get())).m_257501_((enabledFeatures, output) -> {
        for (RegistryObject item : AMItemRegistry.DEF_REG.getEntries()) {
            Object patt1315$temp = item.get();
            if (patt1315$temp instanceof CustomTabBehavior) {
                CustomTabBehavior customTabBehavior = (CustomTabBehavior)patt1315$temp;
                customTabBehavior.fillItemCategory(output);
                continue;
            }
            output.m_246326_((ItemLike)item.get());
        }
    }).m_257652_());
}
