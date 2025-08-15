/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.LecternBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult
 */
package com.github.alexthe666.citadel.server.block;

import com.github.alexthe666.citadel.server.block.CitadelLecternBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class CitadelLecternBlock
extends LecternBlock {
    public CitadelLecternBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public BlockEntity m_142194_(BlockPos pos, BlockState blockState) {
        return new CitadelLecternBlockEntity(pos, blockState);
    }

    public InteractionResult m_6227_(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack book;
        CitadelLecternBlockEntity lecternBlockEntity;
        BlockEntity blockEntity = level.m_7702_(pos);
        if (level.f_46443_ && blockEntity instanceof CitadelLecternBlockEntity && (lecternBlockEntity = (CitadelLecternBlockEntity)blockEntity).hasBook() && !(book = lecternBlockEntity.getBook()).m_41619_() && !player.m_36335_().m_41519_(book.m_41720_())) {
            book.m_41682_(level, player, hand);
        }
        return InteractionResult.m_19078_((boolean)level.f_46443_);
    }

    public int m_6782_(BlockState state, Level level, BlockPos pos) {
        BlockEntity blockentity;
        if (((Boolean)state.m_61143_((Property)f_54467_)).booleanValue() && (blockentity = level.m_7702_(pos)) instanceof CitadelLecternBlockEntity) {
            return ((CitadelLecternBlockEntity)blockentity).getRedstoneSignal();
        }
        return 0;
    }

    public void m_6810_(BlockState state, Level level, BlockPos pos, BlockState replaceState, boolean b) {
        if (!state.m_60713_(replaceState.m_60734_())) {
            if (((Boolean)state.m_61143_((Property)f_54467_)).booleanValue()) {
                this.popCitadelBook(state, level, pos);
            }
            if (((Boolean)state.m_61143_((Property)f_54466_)).booleanValue()) {
                level.m_46672_(pos.m_7495_(), (Block)this);
            }
            super.m_6810_(state, level, pos, replaceState, b);
        }
    }

    private void popCitadelBook(BlockState state, Level level, BlockPos pos) {
        BlockEntity blockentity = level.m_7702_(pos);
        if (blockentity instanceof CitadelLecternBlockEntity) {
            CitadelLecternBlockEntity lecternblockentity = (CitadelLecternBlockEntity)blockentity;
            Direction direction = (Direction)state.m_61143_((Property)f_54465_);
            ItemStack itemstack = lecternblockentity.getBook().m_41777_();
            float f = 0.25f * (float)direction.m_122429_();
            float f1 = 0.25f * (float)direction.m_122431_();
            ItemEntity itementity = new ItemEntity(level, (double)pos.m_123341_() + 0.5 + (double)f, (double)(pos.m_123342_() + 1), (double)pos.m_123343_() + 0.5 + (double)f1, itemstack);
            itementity.m_32060_();
            level.m_7967_((Entity)itementity);
            lecternblockentity.m_6211_();
        }
    }

    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack((ItemLike)Items.f_42774_);
    }
}
