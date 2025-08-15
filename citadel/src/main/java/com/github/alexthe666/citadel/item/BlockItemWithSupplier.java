/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.ItemUtils
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.ShulkerBoxBlock
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.citadel.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraftforge.registries.RegistryObject;

public class BlockItemWithSupplier
extends BlockItem {
    private final RegistryObject<Block> blockSupplier;

    public BlockItemWithSupplier(RegistryObject<Block> blockSupplier, Item.Properties props) {
        super(null, props);
        this.blockSupplier = blockSupplier;
    }

    public Block m_40614_() {
        return (Block)this.blockSupplier.get();
    }

    public boolean m_142095_() {
        return !(this.blockSupplier.get() instanceof ShulkerBoxBlock);
    }

    public void m_142023_(ItemEntity p_150700_) {
        ItemStack itemstack;
        CompoundTag compoundtag;
        if (this.blockSupplier.get() instanceof ShulkerBoxBlock && (compoundtag = BlockItemWithSupplier.m_186336_((ItemStack)(itemstack = p_150700_.m_32055_()))) != null && compoundtag.m_128425_("Items", 9)) {
            ListTag listtag = compoundtag.m_128437_("Items", 10);
            ItemUtils.m_150952_((ItemEntity)p_150700_, listtag.stream().map(CompoundTag.class::cast).map(ItemStack::m_41712_));
        }
    }
}
