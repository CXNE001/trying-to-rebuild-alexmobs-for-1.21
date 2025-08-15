/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.AbstractGlassBlock
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.material.MapColor
 */
package com.github.alexthe666.alexsmobs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MapColor;

public class BlockRainbowGlass
extends AbstractGlassBlock {
    public static final BooleanProperty UP = BooleanProperty.m_61465_((String)"up");
    public static final BooleanProperty DOWN = BooleanProperty.m_61465_((String)"down");
    public static final BooleanProperty EAST = BooleanProperty.m_61465_((String)"east");
    public static final BooleanProperty WEST = BooleanProperty.m_61465_((String)"west");
    public static final BooleanProperty NORTH = BooleanProperty.m_61465_((String)"north");
    public static final BooleanProperty SOUTH = BooleanProperty.m_61465_((String)"south");

    protected BlockRainbowGlass() {
        super(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60911_(0.97f).m_60978_(0.2f).m_60953_(i -> 11).m_60918_(SoundType.f_56744_).m_60955_().m_60922_(BlockRainbowGlass::noOption).m_60924_(BlockRainbowGlass::noOption).m_60960_(BlockRainbowGlass::noOption).m_60971_(BlockRainbowGlass::noOption).m_60991_(BlockRainbowGlass::yes));
        this.m_49959_((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)UP, (Comparable)Boolean.valueOf(false))).m_61124_((Property)DOWN, (Comparable)Boolean.valueOf(false))).m_61124_((Property)EAST, (Comparable)Boolean.valueOf(false))).m_61124_((Property)WEST, (Comparable)Boolean.valueOf(false))).m_61124_((Property)NORTH, (Comparable)Boolean.valueOf(false))).m_61124_((Property)SOUTH, (Comparable)Boolean.valueOf(false)));
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> p_58032_) {
        p_58032_.m_61104_(new Property[]{UP, DOWN, NORTH, SOUTH, EAST, WEST});
    }

    public BlockState m_5573_(BlockPlaceContext context) {
        Level levelreader = context.m_43725_();
        BlockPos blockpos = context.m_8083_();
        BlockPos n = blockpos.m_122012_();
        BlockPos e = blockpos.m_122029_();
        BlockPos s = blockpos.m_122019_();
        BlockPos w = blockpos.m_122024_();
        BlockPos u = blockpos.m_7494_();
        BlockPos d = blockpos.m_7495_();
        BlockState northState = levelreader.m_8055_(n);
        BlockState eastState = levelreader.m_8055_(e);
        BlockState southState = levelreader.m_8055_(s);
        BlockState westState = levelreader.m_8055_(w);
        BlockState upState = levelreader.m_8055_(u);
        BlockState downState = levelreader.m_8055_(d);
        return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.m_49966_().m_61124_((Property)NORTH, (Comparable)Boolean.valueOf(northState.m_60713_((Block)this)))).m_61124_((Property)NORTH, (Comparable)Boolean.valueOf(northState.m_60713_((Block)this)))).m_61124_((Property)EAST, (Comparable)Boolean.valueOf(eastState.m_60713_((Block)this)))).m_61124_((Property)SOUTH, (Comparable)Boolean.valueOf(southState.m_60713_((Block)this)))).m_61124_((Property)WEST, (Comparable)Boolean.valueOf(westState.m_60713_((Block)this)))).m_61124_((Property)UP, (Comparable)Boolean.valueOf(upState.m_60713_((Block)this)))).m_61124_((Property)DOWN, (Comparable)Boolean.valueOf(downState.m_60713_((Block)this)));
    }

    public BlockState m_7417_(BlockState state, Direction direction, BlockState state2, LevelAccessor levelreader, BlockPos blockpos, BlockPos pos2) {
        BlockPos n = blockpos.m_122012_();
        BlockPos e = blockpos.m_122029_();
        BlockPos s = blockpos.m_122019_();
        BlockPos w = blockpos.m_122024_();
        BlockPos u = blockpos.m_7494_();
        BlockPos d = blockpos.m_7495_();
        BlockState northState = levelreader.m_8055_(n);
        BlockState eastState = levelreader.m_8055_(e);
        BlockState southState = levelreader.m_8055_(s);
        BlockState westState = levelreader.m_8055_(w);
        BlockState upState = levelreader.m_8055_(u);
        BlockState downState = levelreader.m_8055_(d);
        return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)state.m_61124_((Property)NORTH, (Comparable)Boolean.valueOf(northState.m_60713_((Block)this)))).m_61124_((Property)NORTH, (Comparable)Boolean.valueOf(northState.m_60713_((Block)this)))).m_61124_((Property)EAST, (Comparable)Boolean.valueOf(eastState.m_60713_((Block)this)))).m_61124_((Property)SOUTH, (Comparable)Boolean.valueOf(southState.m_60713_((Block)this)))).m_61124_((Property)WEST, (Comparable)Boolean.valueOf(westState.m_60713_((Block)this)))).m_61124_((Property)UP, (Comparable)Boolean.valueOf(upState.m_60713_((Block)this)))).m_61124_((Property)DOWN, (Comparable)Boolean.valueOf(downState.m_60713_((Block)this)));
    }

    private static Boolean noOption(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
        return false;
    }

    private static Boolean yes(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
        return false;
    }

    private static Boolean noOption(BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_, EntityType<?> p_50782_) {
        return false;
    }
}
