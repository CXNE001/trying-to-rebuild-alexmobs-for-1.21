/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockSource
 *  net.minecraft.core.Direction
 *  net.minecraft.core.dispenser.DefaultDispenseItemBehavior
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.DispensibleContainerItem
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.DispenserBlock
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.BlockHitResult
 */
package com.github.alexthe666.alexsmobs.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

static class AMItemRegistry.6
extends DefaultDispenseItemBehavior {
    private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

    AMItemRegistry.6() {
    }

    public ItemStack m_7498_(BlockSource blockSource, ItemStack stack) {
        DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem)stack.m_41720_();
        BlockPos blockpos = blockSource.m_7961_().m_121945_((Direction)blockSource.m_6414_().m_61143_((Property)DispenserBlock.f_52659_));
        ServerLevel level = blockSource.m_7727_();
        if (dispensiblecontaineritem.m_142073_((Player)null, (Level)level, blockpos, (BlockHitResult)null)) {
            dispensiblecontaineritem.m_142131_((Player)null, (Level)level, stack, blockpos);
            return new ItemStack((ItemLike)Items.f_42446_);
        }
        return this.defaultDispenseItemBehavior.m_6115_(blockSource, stack);
    }
}
