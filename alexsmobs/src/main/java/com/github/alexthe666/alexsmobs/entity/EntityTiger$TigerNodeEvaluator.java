/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.level.pathfinder.WalkNodeEvaluator
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

static class EntityTiger.TigerNodeEvaluator
extends WalkNodeEvaluator {
    EntityTiger.TigerNodeEvaluator() {
    }

    protected BlockPathTypes m_264405_(BlockGetter level, BlockPos pos, BlockPathTypes typeIn) {
        return typeIn == BlockPathTypes.LEAVES || level.m_8055_(pos).m_60734_() == Blocks.f_50571_ ? BlockPathTypes.OPEN : super.m_264405_(level, pos, typeIn);
    }
}
