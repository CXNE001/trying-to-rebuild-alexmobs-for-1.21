/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.alchemy.Potions
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.BlockStateProperties
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.level.material.Fluids
 *  net.minecraft.world.level.material.MapColor
 *  net.minecraft.world.level.material.PushReaction
 *  net.minecraft.world.level.pathfinder.PathComputationType
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.github.alexthe666.alexsmobs.block;

import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockHummingbirdFeeder
extends Block {
    public static final IntegerProperty CONTENTS = IntegerProperty.m_61631_((String)"contents", (int)0, (int)3);
    public static final BooleanProperty HANGING = BlockStateProperties.f_61435_;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.f_61362_;
    private static final VoxelShape AABB = Block.m_49796_((double)4.0, (double)0.0, (double)4.0, (double)12.0, (double)12.0, (double)12.0);
    private static final VoxelShape AABB_HANGING = Block.m_49796_((double)4.0, (double)0.0, (double)4.0, (double)12.0, (double)16.0, (double)12.0);

    public BlockHummingbirdFeeder() {
        super(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283750_).m_60918_(SoundType.f_56762_).m_60978_(0.5f).m_60977_().m_60955_());
        this.m_49959_((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)CONTENTS, (Comparable)Integer.valueOf(0))).m_61124_((Property)HANGING, (Comparable)Boolean.valueOf(false)));
    }

    @Deprecated
    public VoxelShape m_5940_(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return (Boolean)state.m_61143_((Property)HANGING) != false ? AABB_HANGING : AABB;
    }

    @Nullable
    public BlockState m_5573_(BlockPlaceContext context) {
        FluidState fluidstate = context.m_43725_().m_6425_(context.m_8083_());
        for (Direction direction : context.m_6232_()) {
            BlockState blockstate;
            if (direction.m_122434_() != Direction.Axis.Y || !(blockstate = (BlockState)this.m_49966_().m_61124_((Property)HANGING, (Comparable)Boolean.valueOf(direction == Direction.UP))).m_60710_((LevelReader)context.m_43725_(), context.m_8083_())) continue;
            return (BlockState)blockstate.m_61124_((Property)WATERLOGGED, (Comparable)Boolean.valueOf(fluidstate.m_76152_() == Fluids.f_76193_));
        }
        return null;
    }

    protected static Direction getBlockConnected(BlockState state) {
        return (Boolean)state.m_61143_((Property)HANGING) != false ? Direction.DOWN : Direction.UP;
    }

    public InteractionResult m_6227_(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        int contents = (Integer)state.m_61143_((Property)CONTENTS);
        ItemStack waterBottle = AMEffectRegistry.createPotion(Potions.f_43599_);
        ItemStack itemStack = player.m_21120_(handIn);
        int setContent = -1;
        if (contents == 0) {
            if (itemStack.m_204117_(AMTagRegistry.HUMMINGNBIRD_FEEDER_SWEETENERS)) {
                setContent = 2;
                this.useItem(player, itemStack, false);
            } else if (itemStack.m_41720_() == waterBottle.m_41720_() && ItemStack.m_150942_((ItemStack)waterBottle, (ItemStack)itemStack)) {
                setContent = 1;
                this.useItem(player, itemStack, true);
            }
        } else if (contents == 1) {
            if (itemStack.m_204117_(AMTagRegistry.HUMMINGNBIRD_FEEDER_SWEETENERS)) {
                setContent = 3;
                this.useItem(player, itemStack, false);
            }
        } else if (contents == 2 && itemStack.m_41720_() == waterBottle.m_41720_() && ItemStack.m_150942_((ItemStack)waterBottle, (ItemStack)itemStack)) {
            setContent = 3;
            this.useItem(player, itemStack, true);
        }
        if (setContent >= 0) {
            worldIn.m_46597_(pos, (BlockState)state.m_61124_((Property)CONTENTS, (Comparable)Integer.valueOf(setContent)));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    public void useItem(Player playerEntity, ItemStack stack, boolean dropBottle) {
        if (!playerEntity.m_7500_()) {
            if (dropBottle) {
                playerEntity.m_36356_(new ItemStack((ItemLike)Items.f_42590_));
            }
            stack.m_41774_(1);
        }
    }

    public boolean m_7898_(BlockState state, LevelReader worldIn, BlockPos pos) {
        Direction direction = BlockHummingbirdFeeder.getBlockConnected(state).m_122424_();
        return Block.m_49863_((LevelReader)worldIn, (BlockPos)pos.m_121945_(direction), (Direction)direction.m_122424_());
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    public BlockState m_7417_(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (((Boolean)stateIn.m_61143_((Property)WATERLOGGED)).booleanValue()) {
            worldIn.m_186469_(currentPos, (Fluid)Fluids.f_76193_, Fluids.f_76193_.m_6718_((LevelReader)worldIn));
        }
        return BlockHummingbirdFeeder.getBlockConnected(stateIn).m_122424_() == facing && !stateIn.m_60710_((LevelReader)worldIn, currentPos) ? Blocks.f_50016_.m_49966_() : super.m_7417_(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public FluidState m_5888_(BlockState state) {
        return (Boolean)state.m_61143_((Property)WATERLOGGED) != false ? Fluids.f_76193_.m_76068_(false) : super.m_5888_(state);
    }

    public boolean m_7357_(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
        return false;
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
        builder.m_61104_(new Property[]{CONTENTS, HANGING, WATERLOGGED});
    }
}
