/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mezz.jei.api.IModPlugin
 *  mezz.jei.api.JeiPlugin
 *  mezz.jei.api.helpers.IGuiHelper
 *  mezz.jei.api.helpers.IJeiHelpers
 *  mezz.jei.api.recipe.RecipeType
 *  mezz.jei.api.recipe.category.IRecipeCategory
 *  mezz.jei.api.registration.IRecipeCatalystRegistration
 *  mezz.jei.api.registration.IRecipeCategoryRegistration
 *  mezz.jei.api.registration.IRecipeRegistration
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  org.jetbrains.annotations.Nullable
 */
package com.github.alexthe666.alexsmobs.compat.jei;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.compat.jei.CapsidRecipeCategory;
import com.github.alexthe666.alexsmobs.misc.CapsidRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

@JeiPlugin
public class AlexMobsJEIPlugin
implements IModPlugin {
    public static final ResourceLocation MOD = new ResourceLocation("alexsmobs:alexsmobs");
    @Nullable
    private IRecipeCategory<CapsidRecipe> capsidCategory;
    public static final RecipeType<CapsidRecipe> CAPID_RECIPE_TYPE = RecipeType.create((String)"alexsmobs", (String)"capsid", CapsidRecipe.class);

    public ResourceLocation getPluginUid() {
        return MOD;
    }

    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        IRecipeCategory[] iRecipeCategoryArray = new IRecipeCategory[1];
        this.capsidCategory = new CapsidRecipeCategory(guiHelper);
        iRecipeCategoryArray[0] = this.capsidCategory;
        registration.addRecipeCategories(iRecipeCategoryArray);
    }

    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(CAPID_RECIPE_TYPE, AlexsMobs.PROXY.getCapsidRecipeManager().getCapsidRecipes());
    }

    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack((ItemLike)AMBlockRegistry.CAPSID.get()), new RecipeType[]{CAPID_RECIPE_TYPE});
    }
}
