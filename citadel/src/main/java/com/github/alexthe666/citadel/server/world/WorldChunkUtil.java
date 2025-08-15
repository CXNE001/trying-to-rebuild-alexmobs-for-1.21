/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ChunkHolder
 *  net.minecraft.server.level.ServerChunkCache
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.chunk.ChunkStatus
 */
package com.github.alexthe666.citadel.server.world;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.chunk.ChunkStatus;

public class WorldChunkUtil {
    public static boolean isBlockLoaded(LevelAccessor world, BlockPos pos) {
        return WorldChunkUtil.isChunkLoaded(world, pos.m_123341_() >> 4, pos.m_123343_() >> 4);
    }

    public static boolean isChunkLoaded(LevelAccessor world, int x, int z) {
        if (world.m_7726_() instanceof ServerChunkCache) {
            ChunkHolder holder = ((ServerChunkCache)world.m_7726_()).f_8325_.m_140327_(ChunkPos.m_45589_((int)x, (int)z));
            if (holder != null) {
                return holder.m_140082_().getNow(ChunkHolder.f_139997_).left().isPresent();
            }
            return false;
        }
        return world.m_6522_(x, z, ChunkStatus.f_62326_, false) != null;
    }

    public static boolean isChunkLoaded(LevelAccessor world, ChunkPos pos) {
        return WorldChunkUtil.isChunkLoaded(world, pos.f_45578_, pos.f_45579_);
    }

    public static boolean isEntityBlockLoaded(LevelAccessor world, BlockPos pos) {
        return WorldChunkUtil.isEntityChunkLoaded(world, pos.m_123341_() >> 4, pos.m_123343_() >> 4);
    }

    public static boolean isEntityChunkLoaded(LevelAccessor world, int x, int z) {
        return WorldChunkUtil.isEntityChunkLoaded(world, new ChunkPos(x, z));
    }

    public static boolean isEntityChunkLoaded(LevelAccessor world, ChunkPos pos) {
        if (world instanceof ServerLevel) {
            return WorldChunkUtil.isChunkLoaded(world, pos) && ((ServerLevel)world).m_143340_(pos.m_45615_());
        }
        return WorldChunkUtil.isChunkLoaded(world, pos);
    }
}
