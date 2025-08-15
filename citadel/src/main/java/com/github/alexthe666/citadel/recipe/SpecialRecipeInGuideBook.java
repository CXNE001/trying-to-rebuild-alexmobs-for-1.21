/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.NonNullList
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 */
package com.github.alexthe666.citadel.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public interface SpecialRecipeInGuideBook {
    public NonNullList<Ingredient> getDisplayIngredients();

    public ItemStack getDisplayResultFor(NonNullList<ItemStack> var1);
}
