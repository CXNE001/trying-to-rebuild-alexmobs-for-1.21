/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.BaseEntityBlock
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.DirectionalBlock
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityTicker
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.BlockStateProperties
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.DirectionProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.material.MapColor
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.github.alexthe666.alexsmobs.block;

import com.github.alexthe666.alexsmobs.tileentity.AMTileEntityRegistry;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityVoidWormBeak;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockVoidWormBeak
extends BaseEntityBlock {
    public static final DirectionProperty FACING = DirectionalBlock.f_52588_;
    public static final BooleanProperty POWERED = BlockStateProperties.f_61448_;
    private static final VoxelShape AABB = Block.m_49796_((double)0.0, (double)4.0, (double)0.0, (double)16.0, (double)12.0, (double)16.0);
    private static final VoxelShape AABB_VERTICAL = Block.m_49796_((double)0.0, (double)0.0, (double)4.0, (double)16.0, (double)16.0, (double)12.0);

    public BlockVoidWormBeak() {
        super(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283889_).m_60955_().m_60918_(SoundType.f_56726_).m_60978_(1.0f).m_60910_().m_60999_());
        this.m_49959_((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)FACING, (Comparable)Direction.NORTH)).m_61124_((Property)POWERED, (Comparable)Boolean.valueOf(false)));
    }

    public VoxelShape m_5940_(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return ((Direction)state.m_61143_((Property)FACING)).m_122434_() == Direction.Axis.Y ? AABB_VERTICAL : AABB;
    }

    public void m_6861_(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.f_46443_) {
            this.updateState(state, worldIn, pos, blockIn);
        }
    }

    public void m_213898_(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        if (!worldIn.f_46443_) {
            this.updateState(state, (Level)worldIn, pos, state.m_60734_());
        }
    }

    public void updateState(BlockState state, Level worldIn, BlockPos pos, Block blockIn) {
        boolean flag = (Boolean)state.m_61143_((Property)POWERED);
        boolean flag1 = worldIn.m_276867_(pos);
        if (flag1 != flag) {
            worldIn.m_7731_(pos, (BlockState)state.m_61124_((Property)POWERED, (Comparable)Boolean.valueOf(flag1)), 3);
            worldIn.m_46672_(pos.m_7495_(), (Block)this);
        }
    }

    @Nullable
    public BlockEntity m_142194_(BlockPos pos, BlockState state) {
        return new TileEntityVoidWormBeak(pos, state);
    }

    public BlockState m_5573_(BlockPlaceContext context) {
        return (BlockState)((BlockState)this.m_49966_().m_61124_((Property)FACING, (Comparable)context.m_43719_())).m_61124_((Property)POWERED, (Comparable)Boolean.valueOf(context.m_43725_().m_276867_(context.m_8083_())));
    }

    public BlockState m_6843_(BlockState state, Rotation rot) {
        return (BlockState)state.m_61124_((Property)FACING, (Comparable)rot.m_55954_((Direction)state.m_61143_((Property)FACING)));
    }

    public BlockState m_6943_(BlockState state, Mirror mirrorIn) {
        return state.m_60717_(mirrorIn.m_54846_((Direction)state.m_61143_((Property)FACING)));
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
        builder.m_61104_(new Property[]{FACING, POWERED});
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> m_142354_(Level p_152180_, BlockState p_152181_, BlockEntityType<T> p_152182_) {
        return BlockVoidWormBeak.m_152132_(p_152182_, (BlockEntityType)((BlockEntityType)AMTileEntityRegistry.VOID_WORM_BEAK.get()), TileEntityVoidWormBeak::commonTick);
    }
}
