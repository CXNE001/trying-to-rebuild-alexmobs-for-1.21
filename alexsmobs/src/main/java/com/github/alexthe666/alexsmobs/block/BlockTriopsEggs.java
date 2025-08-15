/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.FrogspawnBlock
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.state.BlockBehaviour$OffsetType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.MapColor
 */
package com.github.alexthe666.alexsmobs.block;

import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityTriops;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FrogspawnBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class BlockTriopsEggs
extends FrogspawnBlock {
    public BlockTriopsEggs() {
        super(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283750_).m_60966_().m_60955_().m_60910_().m_60918_(SoundType.f_222466_).m_222979_(BlockBehaviour.OffsetType.XZ));
    }

    public void m_213897_(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (!this.m_7898_(blockState, (LevelReader)serverLevel, blockPos)) {
            serverLevel.m_46961_(blockPos, false);
        } else if (serverLevel.m_6425_(blockPos.m_7495_()).m_205070_(FluidTags.f_13131_)) {
            serverLevel.m_46961_(blockPos, false);
            int i = 2 + randomSource.m_188503_(2);
            for (int j = 1; j <= i; ++j) {
                EntityTriops tadpole = (EntityTriops)((EntityType)AMEntityRegistry.TRIOPS.get()).m_20615_((Level)serverLevel);
                if (tadpole == null) continue;
                double d0 = blockPos.m_123341_();
                double d1 = blockPos.m_123343_();
                int k = randomSource.m_216339_(1, 361);
                tadpole.m_7678_(d0, (double)blockPos.m_123342_() - 0.5, d1, k, 0.0f);
                tadpole.m_21530_();
                tadpole.setBabyAge(-12000);
                serverLevel.m_7967_((Entity)tadpole);
            }
        }
    }
}
