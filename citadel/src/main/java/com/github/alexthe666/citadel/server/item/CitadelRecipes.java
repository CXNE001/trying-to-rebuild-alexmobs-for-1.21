/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.crafting.SmithingRecipe
 */
package com.github.alexthe666.citadel.server.item;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.item.crafting.SmithingRecipe;

public class CitadelRecipes {
    private static final List<SmithingRecipe> smithingRecipes = new ArrayList<SmithingRecipe>();

    public static void registerSmithingRecipe(SmithingRecipe recipe) {
        smithingRecipes.add(recipe);
    }

    public static List<SmithingRecipe> getSmithingRecipes() {
        return smithingRecipes;
    }
}
