/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.CarpetBlock
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.MapColor
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.github.alexthe666.alexsmobs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockBisonCarpet
extends CarpetBlock {
    protected static final VoxelShape SELECTION_SHAPE = Block.m_49796_((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)2.0, (double)16.0);

    public BlockBisonCarpet() {
        super(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283748_).m_60913_(0.6f, 1.0f).m_60918_(SoundType.f_56745_));
    }

    public VoxelShape m_5909_(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SELECTION_SHAPE;
    }
}
