/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mezz.jei.api.gui.builder.IRecipeLayoutBuilder
 *  mezz.jei.api.gui.drawable.IDrawable
 *  mezz.jei.api.helpers.IGuiHelper
 *  mezz.jei.api.recipe.IFocusGroup
 *  mezz.jei.api.recipe.RecipeIngredientRole
 *  mezz.jei.api.recipe.RecipeType
 *  mezz.jei.api.recipe.category.IRecipeCategory
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.block.Block
 */
package com.github.alexthe666.alexsmobs.compat.jei;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.compat.jei.AlexMobsJEIPlugin;
import com.github.alexthe666.alexsmobs.compat.jei.CapsidDrawable;
import com.github.alexthe666.alexsmobs.misc.CapsidRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class CapsidRecipeCategory
implements IRecipeCategory<CapsidRecipe> {
    private final IDrawable background = new CapsidDrawable();
    private final IDrawable icon;

    public CapsidRecipeCategory(IGuiHelper guiHelper) {
        this.icon = guiHelper.createDrawableItemStack(new ItemStack((ItemLike)AMBlockRegistry.CAPSID.get()));
    }

    public RecipeType<CapsidRecipe> getRecipeType() {
        return AlexMobsJEIPlugin.CAPID_RECIPE_TYPE;
    }

    public Component getTitle() {
        return ((Block)AMBlockRegistry.CAPSID.get()).m_49954_().m_7220_((Component)Component.m_237113_((String)" ")).m_7220_((Component)Component.m_237115_((String)"alexsmobs.gui.capsid_transformation"));
    }

    public IDrawable getBackground() {
        return this.background;
    }

    public IDrawable getIcon() {
        return this.icon;
    }

    public void setRecipe(IRecipeLayoutBuilder builder, CapsidRecipe recipe, IFocusGroup focuses) {
        for (int i = 0; i < recipe.getIngredients().size(); ++i) {
            Ingredient ingredient = (Ingredient)recipe.getIngredients().get(i);
            builder.addSlot(RecipeIngredientRole.INPUT, 21 + i * 15, 23).addIngredients(ingredient);
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 94, 23).addItemStack(recipe.getResult());
    }

    public boolean isHandled(CapsidRecipe recipe) {
        return true;
    }
}
