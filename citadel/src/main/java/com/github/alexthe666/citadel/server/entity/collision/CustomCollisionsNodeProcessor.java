/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.level.pathfinder.WalkNodeEvaluator
 */
package com.github.alexthe666.citadel.server.entity.collision;

import com.github.alexthe666.citadel.server.entity.collision.ICustomCollisions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

public class CustomCollisionsNodeProcessor
extends WalkNodeEvaluator {
    public static BlockPathTypes getBlockPathTypeStatic(BlockGetter p_237231_0_, BlockPos.MutableBlockPos p_237231_1_) {
        int i = p_237231_1_.m_123341_();
        int j = p_237231_1_.m_123342_();
        int k = p_237231_1_.m_123343_();
        BlockPathTypes pathnodetype = CustomCollisionsNodeProcessor.getNodes(p_237231_0_, (BlockPos)p_237231_1_);
        if (pathnodetype == BlockPathTypes.OPEN && j >= 1) {
            BlockPathTypes pathnodetype1 = CustomCollisionsNodeProcessor.getNodes(p_237231_0_, (BlockPos)p_237231_1_.m_122178_(i, j - 1, k));
            BlockPathTypes blockPathTypes = pathnodetype = pathnodetype1 != BlockPathTypes.WALKABLE && pathnodetype1 != BlockPathTypes.OPEN && pathnodetype1 != BlockPathTypes.WATER && pathnodetype1 != BlockPathTypes.LAVA ? BlockPathTypes.WALKABLE : BlockPathTypes.OPEN;
            if (pathnodetype1 == BlockPathTypes.DAMAGE_FIRE) {
                pathnodetype = BlockPathTypes.DAMAGE_FIRE;
            }
            if (pathnodetype1 == BlockPathTypes.DAMAGE_OTHER) {
                pathnodetype = BlockPathTypes.DAMAGE_OTHER;
            }
            if (pathnodetype1 == BlockPathTypes.STICKY_HONEY) {
                pathnodetype = BlockPathTypes.STICKY_HONEY;
            }
        }
        if (pathnodetype == BlockPathTypes.WALKABLE) {
            pathnodetype = CustomCollisionsNodeProcessor.m_77607_((BlockGetter)p_237231_0_, (BlockPos.MutableBlockPos)p_237231_1_.m_122178_(i, j, k), (BlockPathTypes)pathnodetype);
        }
        return pathnodetype;
    }

    protected static BlockPathTypes getNodes(BlockGetter p_237238_0_, BlockPos p_237238_1_) {
        BlockState blockstate = p_237238_0_.m_8055_(p_237238_1_);
        BlockPathTypes type = blockstate.getBlockPathType(p_237238_0_, p_237238_1_, null);
        if (type != null) {
            return type;
        }
        if (blockstate.m_60795_()) {
            return BlockPathTypes.OPEN;
        }
        if (blockstate.m_60734_() == Blocks.f_50571_) {
            return BlockPathTypes.OPEN;
        }
        return CustomCollisionsNodeProcessor.m_77643_((BlockGetter)p_237238_0_, (BlockPos)p_237238_1_);
    }

    public BlockPathTypes m_8086_(BlockGetter blockaccessIn, int x, int y, int z) {
        return CustomCollisionsNodeProcessor.getBlockPathTypeStatic(blockaccessIn, new BlockPos.MutableBlockPos(x, y, z));
    }

    protected BlockPathTypes m_264405_(BlockGetter world, BlockPos pos, BlockPathTypes nodeType) {
        BlockState state = world.m_8055_(pos);
        return ((ICustomCollisions)this.f_77313_).canPassThrough(pos, state, state.m_60816_(world, pos)) ? BlockPathTypes.OPEN : super.m_264405_(world, pos, nodeType);
    }
}
