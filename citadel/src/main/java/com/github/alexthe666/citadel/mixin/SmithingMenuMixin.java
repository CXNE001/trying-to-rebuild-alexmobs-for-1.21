/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.inventory.SmithingMenu
 *  net.minecraft.world.item.crafting.RecipeManager
 *  net.minecraft.world.item.crafting.RecipeType
 *  net.minecraft.world.item.crafting.SmithingRecipe
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package com.github.alexthe666.citadel.mixin;

import com.github.alexthe666.citadel.server.item.CitadelRecipes;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={SmithingMenu.class})
public class SmithingMenuMixin {
    @Redirect(method={"Lnet/minecraft/world/inventory/SmithingMenu;createResult()V"}, remap=true, at=@At(value="INVOKE", target="Lnet/minecraft/world/item/crafting/RecipeManager;getRecipesFor(Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/world/Container;Lnet/minecraft/world/level/Level;)Ljava/util/List;"))
    private List<SmithingRecipe> citadel_getRecipesFor(RecipeManager recipeManager, RecipeType<SmithingRecipe> type, Container container, Level level) {
        ArrayList<SmithingRecipe> list = new ArrayList<SmithingRecipe>();
        list.addAll(recipeManager.m_44056_(type, container, level));
        if (type == RecipeType.f_44113_ && container.m_6643_() >= 2 && !container.m_8020_(0).m_41619_() && !container.m_8020_(1).m_41619_()) {
            list.addAll(CitadelRecipes.getSmithingRecipes());
        }
        return list;
    }
}
