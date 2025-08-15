/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.BaseEntityBlock
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.RenderShape
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityTicker
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.BlockStateProperties
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.material.MapColor
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.github.alexthe666.alexsmobs.block;

import com.github.alexthe666.alexsmobs.block.AMSpecialRenderBlock;
import com.github.alexthe666.alexsmobs.tileentity.AMTileEntityRegistry;
import com.github.alexthe666.alexsmobs.tileentity.TileEntityEndPirateAnchorWinch;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockEndPirateAnchorWinch
extends BaseEntityBlock
implements AMSpecialRenderBlock {
    public static final BooleanProperty EASTORWEST = BooleanProperty.m_61465_((String)"eastorwest");
    public static final BooleanProperty POWERED = BlockStateProperties.f_61448_;
    protected static final VoxelShape FULL_AABB_EW = Block.m_49796_((double)3.0, (double)3.0, (double)0.0, (double)13.0, (double)13.0, (double)16.0);
    protected static final VoxelShape FULL_AABB_NS = Block.m_49796_((double)0.0, (double)3.0, (double)3.0, (double)16.0, (double)13.0, (double)13.0);

    protected BlockEndPirateAnchorWinch() {
        super(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283927_).m_60911_(0.97f).m_60978_(10.0f).m_60953_(i -> 6).m_60918_(SoundType.f_56742_).m_60955_());
        this.m_49959_((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)EASTORWEST, (Comparable)Boolean.valueOf(false))).m_61124_((Property)POWERED, (Comparable)Boolean.valueOf(false)));
    }

    public RenderShape m_7514_(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
        builder.m_61104_(new Property[]{EASTORWEST, POWERED});
    }

    public VoxelShape m_5940_(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return (Boolean)state.m_61143_((Property)EASTORWEST) != false ? FULL_AABB_EW : FULL_AABB_NS;
    }

    public BlockState m_5573_(BlockPlaceContext context) {
        boolean axis = context.m_43719_().m_122434_() == Direction.Axis.Y ? context.m_8125_().m_122434_() == Direction.Axis.X : context.m_43719_().m_122434_() != Direction.Axis.X;
        return (BlockState)this.m_49966_().m_61124_((Property)EASTORWEST, (Comparable)Boolean.valueOf(axis));
    }

    public void m_6402_(Level level, BlockPos pos, BlockState state, LivingEntity player, ItemStack stack) {
        BlockEntity blockEntity = level.m_7702_(pos);
        if (blockEntity instanceof TileEntityEndPirateAnchorWinch) {
            TileEntityEndPirateAnchorWinch winch = (TileEntityEndPirateAnchorWinch)blockEntity;
            winch.recalculateChains();
        }
    }

    public void m_6861_(BlockState state, Level level, BlockPos pos, Block block, BlockPos p_52780_, boolean p_52781_) {
        boolean flag = level.m_276867_(pos);
        BlockEntity blockEntity = level.m_7702_(pos);
        if (blockEntity instanceof TileEntityEndPirateAnchorWinch) {
            TileEntityEndPirateAnchorWinch winch = (TileEntityEndPirateAnchorWinch)blockEntity;
            if (flag != (Boolean)state.m_61143_((Property)POWERED)) {
                level.m_7731_(pos, (BlockState)state.m_61124_((Property)POWERED, (Comparable)Boolean.valueOf(flag)), 3);
            }
        }
    }

    @Nullable
    public BlockEntity m_142194_(BlockPos pos, BlockState state) {
        return new TileEntityEndPirateAnchorWinch(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> m_142354_(Level p_152180_, BlockState state, BlockEntityType<T> p_152182_) {
        return BlockEndPirateAnchorWinch.m_152132_(p_152182_, (BlockEntityType)((BlockEntityType)AMTileEntityRegistry.END_PIRATE_ANCHOR_WINCH.get()), TileEntityEndPirateAnchorWinch::commonTick);
    }
}
