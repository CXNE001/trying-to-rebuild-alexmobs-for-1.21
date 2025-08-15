/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.inventory.CraftingContainer
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.CraftingBookCategory
 *  net.minecraft.world.item.crafting.CustomRecipe
 *  net.minecraft.world.item.crafting.RecipeSerializer
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 */
package com.github.alexthe666.alexsmobs.misc;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.misc.AMRecipeRegistry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class RecipeBisonUpgrade
extends CustomRecipe {
    public RecipeBisonUpgrade(ResourceLocation idIn, CraftingBookCategory category) {
        super(idIn, category);
    }

    private ItemStack createBoots(Container container) {
        ItemStack itemstack1;
        int j;
        ItemStack boots = ItemStack.f_41583_;
        int fur = 0;
        for (j = 0; j < container.m_6643_(); ++j) {
            itemstack1 = container.m_8020_(j);
            if (!itemstack1.m_150930_(((Block)AMBlockRegistry.BISON_FUR_BLOCK.get()).m_5456_())) continue;
            ++fur;
        }
        if (fur == 1) {
            for (j = 0; j < container.m_6643_(); ++j) {
                boolean notFurred;
                itemstack1 = container.m_8020_(j);
                boolean bl = notFurred = !itemstack1.m_41782_() || itemstack1.m_41783_() != null && !itemstack1.m_41783_().m_128471_("BisonFur");
                if (itemstack1.m_41619_() || !notFurred || LivingEntity.m_147233_((ItemStack)itemstack1) != EquipmentSlot.FEET) continue;
                boots = itemstack1;
            }
            if (!boots.m_41619_()) {
                ItemStack stack = boots.m_41777_();
                CompoundTag tag = stack.m_41784_();
                tag.m_128379_("BisonFur", true);
                stack.m_41751_(tag);
                return stack;
            }
        }
        return ItemStack.f_41583_;
    }

    public boolean matches(CraftingContainer inv, Level worldIn) {
        return !this.createBoots((Container)inv).m_41619_();
    }

    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        return this.createBoots((Container)container);
    }

    public boolean m_8004_(int x, int y) {
        return x * y >= 2;
    }

    public RecipeSerializer<?> m_7707_() {
        return (RecipeSerializer)AMRecipeRegistry.BISON_UPGRADE.get();
    }
}
