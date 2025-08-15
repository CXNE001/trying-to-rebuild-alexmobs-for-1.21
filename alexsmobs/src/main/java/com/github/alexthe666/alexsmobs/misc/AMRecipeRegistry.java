/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.item.crafting.RecipeSerializer
 *  net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.alexsmobs.misc;

import com.github.alexthe666.alexsmobs.misc.RecipeBisonUpgrade;
import com.github.alexthe666.alexsmobs.misc.RecipeMimicreamRepair;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AMRecipeRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> DEF_REG = DeferredRegister.create((ResourceKey)Registries.f_256764_, (String)"alexsmobs");
    public static final RegistryObject<RecipeSerializer<?>> MIMICREAM_RECIPE = DEF_REG.register("mimicream_repair", () -> new SimpleCraftingRecipeSerializer(RecipeMimicreamRepair::new));
    public static final RegistryObject<RecipeSerializer<?>> BISON_UPGRADE = DEF_REG.register("bison_upgrade", () -> new SimpleCraftingRecipeSerializer(RecipeBisonUpgrade::new));

    public static void init() {
    }
}
