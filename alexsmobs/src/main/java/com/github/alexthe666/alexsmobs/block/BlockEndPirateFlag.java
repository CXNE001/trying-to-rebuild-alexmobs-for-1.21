/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.BaseEntityBlock
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.HorizontalDirectionalBlock
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.RenderShape
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityTicker
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.DirectionProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.material.MapColor
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.github.alexthe666.alexsmobs.block;

import com.github.alexthe666.alexsmobs.tileentity.AMTileEntityRegistry;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateFlag;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockEndPirateFlag
extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.f_54117_;
    private static final VoxelShape AABB = Block.m_49796_((double)6.0, (double)0.0, (double)6.0, (double)10.0, (double)16.0, (double)10.0);

    public BlockEndPirateFlag() {
        super(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283919_).m_60955_().m_60918_(SoundType.f_56736_).m_60978_(1.0f).m_60953_(i -> 15).m_60910_().m_60999_());
        this.m_49959_((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)FACING, (Comparable)Direction.NORTH));
    }

    public RenderShape m_7514_(BlockState p_49232_) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    public BlockState m_7417_(BlockState state, Direction direction, BlockState state2, LevelAccessor level, BlockPos pos, BlockPos p_52801_) {
        return !state.m_60710_((LevelReader)level, pos) ? Blocks.f_50016_.m_49966_() : super.m_7417_(state, direction, state2, level, pos, p_52801_);
    }

    public VoxelShape m_5940_(BlockState p_54561_, BlockGetter p_54562_, BlockPos p_54563_, CollisionContext p_54564_) {
        return AABB;
    }

    public boolean m_7898_(BlockState state, LevelReader world, BlockPos pos) {
        return BlockEndPirateFlag.m_49863_((LevelReader)world, (BlockPos)pos.m_7495_(), (Direction)Direction.UP) || BlockEndPirateFlag.m_49863_((LevelReader)world, (BlockPos)pos.m_7494_(), (Direction)Direction.DOWN);
    }

    @Nullable
    public BlockEntity m_142194_(BlockPos pos, BlockState state) {
        return new TileEntityEndPirateFlag(pos, state);
    }

    public BlockState m_5573_(BlockPlaceContext context) {
        return (BlockState)this.m_49966_().m_61124_((Property)FACING, (Comparable)context.m_8125_());
    }

    public BlockState m_6843_(BlockState state, Rotation rot) {
        return (BlockState)state.m_61124_((Property)FACING, (Comparable)rot.m_55954_((Direction)state.m_61143_((Property)FACING)));
    }

    public BlockState m_6943_(BlockState state, Mirror mirrorIn) {
        return state.m_60717_(mirrorIn.m_54846_((Direction)state.m_61143_((Property)FACING)));
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
        builder.m_61104_(new Property[]{FACING});
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> m_142354_(Level p_152180_, BlockState p_152181_, BlockEntityType<T> p_152182_) {
        return BlockEndPirateFlag.m_152132_(p_152182_, (BlockEntityType)((BlockEntityType)AMTileEntityRegistry.END_PIRATE_FLAG.get()), TileEntityEndPirateFlag::commonTick);
    }

    public void animateTick(BlockState p_53094_, Level p_53095_, BlockPos p_53096_, Random p_53097_) {
        if (p_53097_.nextInt(5) == 0) {
            double d0 = (double)p_53096_.m_123341_() + 0.55 - (double)(p_53097_.nextFloat() * 0.1f);
            double d1 = (double)p_53096_.m_123342_() + 0.55 - (double)(p_53097_.nextFloat() * 0.1f);
            double d2 = (double)p_53096_.m_123343_() + 0.55 - (double)(p_53097_.nextFloat() * 0.1f);
            double d3 = 0.4f - (p_53097_.nextFloat() + p_53097_.nextFloat()) * 0.4f;
            p_53095_.m_7106_((ParticleOptions)ParticleTypes.f_123810_, d0, d1 + d3, d2, p_53097_.nextGaussian() * 0.005, p_53097_.nextGaussian() * 0.005, p_53097_.nextGaussian() * 0.005);
        }
    }
}
