/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.block.state.BlockState
 */
package com.github.alexthe666.citadel.server.entity.pathfinding.raycoms;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public interface IPassabilityNavigator {
    public int maxSearchNodes();

    public boolean isBlockExplicitlyPassable(BlockState var1, BlockPos var2, BlockPos var3);

    public boolean isBlockExplicitlyNotPassable(BlockState var1, BlockPos var2, BlockPos var3);
}
