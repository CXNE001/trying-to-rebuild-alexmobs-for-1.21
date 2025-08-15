/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.Tuple
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.BucketPickup
 *  net.minecraft.world.level.block.HalfTransparentBlock
 *  net.minecraft.world.level.block.LiquidBlock
 *  net.minecraft.world.level.block.SimpleWaterloggedBlock
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BlockStateProperties
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.level.material.MapColor
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.Shapes
 *  net.minecraft.world.phys.shapes.VoxelShape
 *  org.jetbrains.annotations.NotNull
 */
package com.github.alexthe666.alexsmobs.block;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import com.google.common.collect.Lists;
import java.util.LinkedList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class BlockBananaSlugSlime
extends HalfTransparentBlock {
    protected static final VoxelShape SHAPE = Block.m_49796_((double)1.0, (double)1.0, (double)1.0, (double)15.0, (double)15.0, (double)15.0);
    private static final int MAXIMUM_BLOCKS_DRAINED = 64;
    public static final int MAX_FLUID_SPREAD = 6;

    public BlockBananaSlugSlime() {
        super(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283832_).m_60956_(0.4f).m_60967_(0.5f).m_60911_(0.8f).m_60918_(SoundType.f_56750_).m_60955_());
    }

    public VoxelShape m_5909_(BlockState p_48735_, BlockGetter p_48736_, BlockPos p_48737_, CollisionContext p_48738_) {
        return Shapes.m_83040_();
    }

    public VoxelShape m_5939_(BlockState p_54015_, BlockGetter p_54016_, BlockPos p_54017_, CollisionContext p_54018_) {
        return SHAPE;
    }

    public void m_7892_(BlockState state, Level level, BlockPos pos, Entity entity) {
        entity.m_20256_(entity.m_20184_().m_82490_(0.8));
        super.m_7892_(state, level, pos, entity);
    }

    public boolean isSlimeBlock(BlockState state) {
        return true;
    }

    public boolean isStickyBlock(BlockState state) {
        return true;
    }

    public boolean m_7420_(BlockState p_48740_, BlockGetter p_48741_, BlockPos p_48742_) {
        return true;
    }

    public boolean canStickTo(BlockState state, @NotNull BlockState other) {
        return !other.isStickyBlock() || other.m_60734_() == this;
    }

    public void m_6807_(BlockState p_56811_, Level p_56812_, BlockPos p_56813_, BlockState p_56814_, boolean p_56815_) {
        if (!p_56814_.m_60713_(p_56811_.m_60734_())) {
            this.tryAbsorbWater(p_56812_, p_56813_);
        }
    }

    public void m_6861_(BlockState p_56801_, Level p_56802_, BlockPos p_56803_, Block p_56804_, BlockPos p_56805_, boolean p_56806_) {
        this.tryAbsorbWater(p_56802_, p_56803_);
        super.m_6861_(p_56801_, p_56802_, p_56803_, p_56804_, p_56805_, p_56806_);
    }

    protected void tryAbsorbWater(Level level, BlockPos pos) {
        if (this.removeWaterBreadthFirstSearch(level, pos)) {
            level.m_5594_(null, pos, (SoundEvent)AMSoundRegistry.BANANA_SLUG_SLIME_EXPAND.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
        }
    }

    private boolean removeWaterBreadthFirstSearch(Level level, BlockPos pos) {
        LinkedList queue = Lists.newLinkedList();
        queue.add(new Tuple((Object)pos, (Object)0));
        int i = 0;
        int fullBlocks = 0;
        FluidState lastFluidState = null;
        while (!queue.isEmpty()) {
            Tuple tuple = (Tuple)queue.poll();
            BlockPos blockpos = (BlockPos)tuple.m_14418_();
            BlockState state = level.m_8055_(blockpos);
            int j = (Integer)tuple.m_14419_();
            if (!state.m_60819_().m_76178_()) {
                ++fullBlocks;
                if (state.m_60734_() instanceof BucketPickup) {
                    ((BucketPickup)state.m_60734_()).m_142598_((LevelAccessor)level, blockpos, state);
                    if (level.m_8055_(blockpos).m_60795_()) {
                        level.m_46597_(blockpos, ((Block)AMBlockRegistry.CRYSTALIZED_BANANA_SLUG_MUCUS.get()).m_49966_());
                    }
                } else {
                    level.m_46597_(blockpos, ((Block)AMBlockRegistry.CRYSTALIZED_BANANA_SLUG_MUCUS.get()).m_49966_());
                }
            }
            for (Direction direction : Direction.values()) {
                BlockPos blockpos1 = blockpos.m_121945_(direction);
                BlockState blockstate = level.m_8055_(blockpos1);
                FluidState fluidstate = level.m_6425_(blockpos1);
                if (lastFluidState != null && !fluidstate.m_76178_() && lastFluidState.getFluidType() != fluidstate.getFluidType()) continue;
                if (blockstate.m_60734_() instanceof SimpleWaterloggedBlock) {
                    if (!fluidstate.m_76178_()) {
                        lastFluidState = fluidstate;
                    }
                    ++i;
                    ++fullBlocks;
                    level.m_46597_(blockpos1, (BlockState)blockstate.m_61124_((Property)BlockStateProperties.f_61362_, (Comparable)Boolean.valueOf(false)));
                    if (j >= 6) continue;
                    queue.add(new Tuple((Object)blockpos1, (Object)(j + 1)));
                    continue;
                }
                if (blockstate.m_60734_() instanceof BucketPickup) {
                    if (!fluidstate.m_76178_()) {
                        lastFluidState = fluidstate;
                    }
                    ++i;
                    ++fullBlocks;
                    ((BucketPickup)blockstate.m_60734_()).m_142598_((LevelAccessor)level, blockpos1, blockstate);
                    if (level.m_8055_(blockpos).m_60795_()) {
                        level.m_46597_(blockpos, ((Block)AMBlockRegistry.CRYSTALIZED_BANANA_SLUG_MUCUS.get()).m_49966_());
                    }
                    if (j >= 6) continue;
                    queue.add(new Tuple((Object)blockpos1, (Object)(j + 1)));
                    continue;
                }
                if (!(blockstate.m_60734_() instanceof LiquidBlock)) continue;
                if (!fluidstate.m_76178_()) {
                    lastFluidState = fluidstate;
                }
                level.m_46597_(blockpos1, ((Block)AMBlockRegistry.CRYSTALIZED_BANANA_SLUG_MUCUS.get()).m_49966_());
                ++i;
                if (blockstate.m_60819_().m_76170_()) {
                    ++fullBlocks;
                }
                if (j >= 6) continue;
                queue.add(new Tuple((Object)blockpos1, (Object)(j + 1)));
            }
            if (i <= 64) continue;
            break;
        }
        return fullBlocks > 0;
    }
}
