/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.AbstractGlassBlock
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
 *  net.minecraft.world.level.material.MapColor
 */
package com.github.alexthe666.alexsmobs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractGlassBlock;
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
import net.minecraft.world.level.material.MapColor;

public class BlockEnderResidue
extends AbstractGlassBlock {
    public static final IntegerProperty AGE = BlockStateProperties.f_61407_;
    public static final BooleanProperty SLOW_DECAY = BooleanProperty.m_61465_((String)"slow_decay");

    public BlockEnderResidue() {
        super(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283869_).m_60955_().m_60982_((i, j, k) -> true).m_60991_((i, j, k) -> true).m_60953_(i -> 3).m_60978_(0.2f).m_60918_(SoundType.f_154654_).m_60977_().m_60955_());
        this.m_49959_((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)AGE, (Comparable)Integer.valueOf(0))).m_61124_((Property)SLOW_DECAY, (Comparable)Boolean.valueOf(false)));
    }

    public void m_213898_(BlockState p_53588_, ServerLevel p_53589_, BlockPos p_53590_, RandomSource p_53591_) {
        this.m_213897_(p_53588_, p_53589_, p_53590_, p_53591_);
    }

    public void m_213897_(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.m_188503_((Boolean)state.m_61143_((Property)SLOW_DECAY) != false ? 15 : 5) == 0) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
            for (Direction direction : Direction.values()) {
                blockpos$mutableblockpos.m_122159_((Vec3i)pos, direction);
                BlockState blockstate = level.m_8055_((BlockPos)blockpos$mutableblockpos);
                if (!blockstate.m_60713_((Block)this) || this.incrementAge(blockstate, (Level)level, (BlockPos)blockpos$mutableblockpos)) continue;
                level.m_186460_((BlockPos)blockpos$mutableblockpos, (Block)this, Mth.m_216271_((RandomSource)random, (int)20, (int)40));
            }
            this.incrementAge(state, (Level)level, pos);
        } else {
            level.m_186460_(pos, (Block)this, Mth.m_216271_((RandomSource)random, (int)20, (int)40));
        }
    }

    private boolean incrementAge(BlockState state, Level level, BlockPos pos) {
        int i = (Integer)state.m_61143_((Property)AGE);
        if (i < 3) {
            level.m_7731_(pos, (BlockState)state.m_61124_((Property)AGE, (Comparable)Integer.valueOf(i + 1)), 2);
            return false;
        }
        level.m_7731_(pos, Blocks.f_50016_.m_49966_(), 2);
        return true;
    }

    public void m_6861_(BlockState p_53579_, Level p_53580_, BlockPos p_53581_, Block p_53582_, BlockPos p_53583_, boolean p_53584_) {
        super.m_6861_(p_53579_, p_53580_, p_53581_, p_53582_, p_53583_, p_53584_);
    }

    private boolean fewerNeigboursThan(BlockGetter p_53566_, BlockPos p_53567_, int p_53568_) {
        int i = 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        for (Direction direction : Direction.values()) {
            blockpos$mutableblockpos.m_122159_((Vec3i)p_53567_, direction);
            if (!p_53566_.m_8055_((BlockPos)blockpos$mutableblockpos).m_60713_((Block)this) || ++i < p_53568_) continue;
            return false;
        }
        return true;
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> p_53586_) {
        p_53586_.m_61104_(new Property[]{AGE, SLOW_DECAY});
    }

    public ItemStack m_7397_(BlockGetter p_53570_, BlockPos p_53571_, BlockState p_53572_) {
        return ItemStack.f_41583_;
    }
}
