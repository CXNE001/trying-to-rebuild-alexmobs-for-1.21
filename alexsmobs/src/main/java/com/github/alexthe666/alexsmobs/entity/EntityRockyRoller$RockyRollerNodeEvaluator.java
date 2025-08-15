/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.block.PointedDripstoneBlock
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.level.pathfinder.WalkNodeEvaluator
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

static class EntityRockyRoller.RockyRollerNodeEvaluator
extends WalkNodeEvaluator {
    EntityRockyRoller.RockyRollerNodeEvaluator() {
    }

    protected BlockPathTypes m_264405_(BlockGetter level, BlockPos pos, BlockPathTypes typeIn) {
        return level.m_8055_(pos).m_60734_() instanceof PointedDripstoneBlock ? BlockPathTypes.OPEN : super.m_264405_(level, pos, typeIn);
    }
}
