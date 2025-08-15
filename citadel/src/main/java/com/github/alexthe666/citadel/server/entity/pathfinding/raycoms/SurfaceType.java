/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.BambooSaplingBlock
 *  net.minecraft.world.level.block.BambooStalkBlock
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.CampfireBlock
 *  net.minecraft.world.level.block.CarpetBlock
 *  net.minecraft.world.level.block.DoorBlock
 *  net.minecraft.world.level.block.FenceBlock
 *  net.minecraft.world.level.block.FenceGateBlock
 *  net.minecraft.world.level.block.FireBlock
 *  net.minecraft.world.level.block.HorizontalDirectionalBlock
 *  net.minecraft.world.level.block.MagmaBlock
 *  net.minecraft.world.level.block.PowderSnowBlock
 *  net.minecraft.world.level.block.SignBlock
 *  net.minecraft.world.level.block.SnowLayerBlock
 *  net.minecraft.world.level.block.TrapDoorBlock
 *  net.minecraft.world.level.block.VineBlock
 *  net.minecraft.world.level.block.WallBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Half
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.level.material.Fluids
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.github.alexthe666.citadel.server.entity.pathfinding.raycoms;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BambooSaplingBlock;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.PowderSnowBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

public enum SurfaceType {
    WALKABLE,
    DROPABLE,
    NOT_PASSABLE,
    FLYABLE;


    public static SurfaceType getSurfaceType(BlockGetter world, BlockState blockState, BlockPos pos) {
        Block block = blockState.m_60734_();
        if (block instanceof FenceBlock || block instanceof FenceGateBlock || block instanceof WallBlock || block instanceof FireBlock || block instanceof CampfireBlock || block instanceof BambooStalkBlock || block instanceof BambooSaplingBlock || block instanceof DoorBlock || block instanceof MagmaBlock || block instanceof PowderSnowBlock) {
            return NOT_PASSABLE;
        }
        if (block instanceof TrapDoorBlock && !((Boolean)blockState.m_61143_((Property)TrapDoorBlock.f_57514_)).booleanValue()) {
            return WALKABLE;
        }
        VoxelShape shape = blockState.m_60808_(world, pos);
        if (shape.m_83297_(Direction.Axis.Y) > 1.0) {
            return NOT_PASSABLE;
        }
        FluidState fluid = world.m_6425_(pos);
        if (blockState.m_60734_() == Blocks.f_49991_ || !fluid.m_76178_() && (fluid.m_76152_() == Fluids.f_76195_ || fluid.m_76152_() == Fluids.f_76194_)) {
            return NOT_PASSABLE;
        }
        if (SurfaceType.isWater(world, pos, blockState, fluid)) {
            return WALKABLE;
        }
        if (block instanceof SignBlock || block instanceof VineBlock) {
            return DROPABLE;
        }
        if (blockState.m_280296_() && shape.m_83297_(Direction.Axis.X) - shape.m_83288_(Direction.Axis.X) > 0.75 && shape.m_83297_(Direction.Axis.Z) - shape.m_83288_(Direction.Axis.Z) > 0.75 || blockState.m_60734_() == Blocks.f_50125_ && (Integer)blockState.m_61143_((Property)SnowLayerBlock.f_56581_) > 1 || block instanceof CarpetBlock) {
            return WALKABLE;
        }
        return DROPABLE;
    }

    public static boolean isWater(LevelReader world, BlockPos pos) {
        return SurfaceType.isWater((BlockGetter)world, pos, null, null);
    }

    public static boolean isWater(BlockGetter world, BlockPos pos, @Nullable BlockState pState, @Nullable FluidState pFluidState) {
        BlockState state = pState;
        if (state == null) {
            state = world.m_8055_(pos);
        }
        if (state.m_60815_()) {
            return false;
        }
        if (state.m_60734_() == Blocks.f_49990_) {
            return true;
        }
        FluidState fluidState = pFluidState;
        if (fluidState == null) {
            fluidState = world.m_6425_(pos);
        }
        if (fluidState.m_76178_()) {
            return false;
        }
        if ((state.m_60734_() instanceof TrapDoorBlock || state.m_60734_() instanceof HorizontalDirectionalBlock) && state.m_61138_((Property)TrapDoorBlock.f_57514_) && !((Boolean)state.m_61143_((Property)TrapDoorBlock.f_57514_)).booleanValue() && state.m_61138_((Property)TrapDoorBlock.f_57515_) && state.m_61143_((Property)TrapDoorBlock.f_57515_) == Half.TOP) {
            return false;
        }
        Fluid fluid = fluidState.m_76152_();
        return fluid == Fluids.f_76193_ || fluid == Fluids.f_76192_;
    }
}
