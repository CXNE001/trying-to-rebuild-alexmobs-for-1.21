/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.BushBlock
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.state.BlockBehaviour$OffsetType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.github.alexthe666.alexsmobs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockBananaPeel
extends BushBlock {
    protected static final VoxelShape SHAPE_COLLISON = Block.m_49796_((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)9.0, (double)16.0);
    protected static final VoxelShape SHAPE = Block.m_49796_((double)2.0, (double)0.0, (double)2.0, (double)14.0, (double)4.0, (double)14.0);

    public BlockBananaPeel() {
        super(BlockBehaviour.Properties.m_284310_().m_60988_().m_60918_(SoundType.f_56752_).m_60910_().m_60999_().m_60978_(0.2f).m_60911_(1.0f));
    }

    public void m_7892_(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
    }

    protected boolean m_6266_(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return BlockBananaPeel.m_49936_((BlockGetter)worldIn, (BlockPos)pos);
    }

    public BlockBehaviour.OffsetType getOffsetType() {
        return BlockBehaviour.OffsetType.XZ;
    }

    @Deprecated
    public VoxelShape m_5940_(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public VoxelShape m_5939_(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE_COLLISON;
    }

    public VoxelShape m_7947_(BlockState state, BlockGetter reader, BlockPos pos) {
        return SHAPE_COLLISON;
    }

    public VoxelShape m_5909_(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
