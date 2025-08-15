/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Holder
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.server.level.ChunkHolder
 *  net.minecraft.server.level.ServerChunkCache
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.flag.FeatureFlagSet
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.biome.BiomeManager
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.border.WorldBorder
 *  net.minecraft.world.level.chunk.ChunkAccess
 *  net.minecraft.world.level.chunk.ChunkSource
 *  net.minecraft.world.level.chunk.ChunkStatus
 *  net.minecraft.world.level.chunk.LevelChunk
 *  net.minecraft.world.level.chunk.LevelChunk$EntityCreationType
 *  net.minecraft.world.level.dimension.DimensionType
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.level.lighting.LevelLightEngine
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.level.material.Fluids
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.shapes.VoxelShape
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.github.alexthe666.citadel.server.entity.pathfinding.raycoms;

import com.github.alexthe666.citadel.server.world.WorldChunkUtil;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChunkCache
implements LevelReader {
    private final DimensionType dimType;
    protected int chunkX;
    protected int chunkZ;
    protected LevelChunk[][] chunkArray;
    protected boolean empty;
    protected Level world;
    private final int minBuildHeight;
    private final int maxBuildHeight;

    public ChunkCache(Level worldIn, BlockPos posFromIn, BlockPos posToIn, int subIn, DimensionType type) {
        this.world = worldIn;
        this.chunkX = posFromIn.m_123341_() - subIn >> 4;
        this.chunkZ = posFromIn.m_123343_() - subIn >> 4;
        int i = posToIn.m_123341_() + subIn >> 4;
        int j = posToIn.m_123343_() + subIn >> 4;
        this.chunkArray = new LevelChunk[i - this.chunkX + 1][j - this.chunkZ + 1];
        this.empty = true;
        for (int k = this.chunkX; k <= i; ++k) {
            for (int l = this.chunkZ; l <= j; ++l) {
                ChunkSource chunkSource;
                if (!WorldChunkUtil.isEntityChunkLoaded((LevelAccessor)this.world, new ChunkPos(k, l)) || !((chunkSource = worldIn.m_7726_()) instanceof ServerChunkCache)) continue;
                ServerChunkCache serverChunkCache = (ServerChunkCache)chunkSource;
                ChunkHolder holder = serverChunkCache.f_8325_.m_140327_(ChunkPos.m_45589_((int)k, (int)l));
                if (holder == null) continue;
                this.chunkArray[k - this.chunkX][l - this.chunkZ] = holder.m_140082_().getNow(ChunkHolder.f_139997_).left().orElse(null);
            }
        }
        this.dimType = type;
        this.minBuildHeight = worldIn.m_141937_();
        this.maxBuildHeight = worldIn.m_151558_();
    }

    public boolean isEmpty() {
        return this.empty;
    }

    @javax.annotation.Nullable
    public BlockEntity m_7702_(@NotNull BlockPos pos) {
        return this.getTileEntity(pos, LevelChunk.EntityCreationType.CHECK);
    }

    @javax.annotation.Nullable
    public BlockEntity getTileEntity(BlockPos pos, LevelChunk.EntityCreationType createType) {
        int j;
        int i = (pos.m_123341_() >> 4) - this.chunkX;
        if (!this.withinBounds(i, j = (pos.m_123343_() >> 4) - this.chunkZ)) {
            return null;
        }
        return this.chunkArray[i][j].m_5685_(pos, createType);
    }

    public int m_141937_() {
        return this.minBuildHeight;
    }

    public int m_151558_() {
        return this.maxBuildHeight;
    }

    @NotNull
    public BlockState m_8055_(BlockPos pos) {
        if (pos.m_123342_() >= this.m_141937_() && pos.m_123342_() < this.m_151558_()) {
            LevelChunk chunk;
            int i = (pos.m_123341_() >> 4) - this.chunkX;
            int j = (pos.m_123343_() >> 4) - this.chunkZ;
            if (i >= 0 && i < this.chunkArray.length && j >= 0 && j < this.chunkArray[i].length && (chunk = this.chunkArray[i][j]) != null) {
                return chunk.m_8055_(pos);
            }
        }
        return Blocks.f_50016_.m_49966_();
    }

    public FluidState m_6425_(BlockPos pos) {
        if (pos.m_123342_() >= this.m_141937_() && pos.m_123342_() < this.m_151558_()) {
            LevelChunk chunk;
            int i = (pos.m_123341_() >> 4) - this.chunkX;
            int j = (pos.m_123343_() >> 4) - this.chunkZ;
            if (i >= 0 && i < this.chunkArray.length && j >= 0 && j < this.chunkArray[i].length && (chunk = this.chunkArray[i][j]) != null) {
                return chunk.m_6425_(pos);
            }
        }
        return Fluids.f_76191_.m_76145_();
    }

    public Holder<Biome> m_203675_(int x, int y, int z) {
        return null;
    }

    public boolean m_46859_(BlockPos pos) {
        BlockState state = this.m_8055_(pos);
        return state.m_60795_();
    }

    @javax.annotation.Nullable
    public ChunkAccess m_6522_(int x, int z, ChunkStatus requiredStatus, boolean nonnull) {
        int i = x - this.chunkX;
        int j = z - this.chunkZ;
        if (i >= 0 && i < this.chunkArray.length && j >= 0 && j < this.chunkArray[i].length) {
            return this.chunkArray[i][j];
        }
        return null;
    }

    public boolean m_7232_(int chunkX, int chunkZ) {
        return false;
    }

    public BlockPos m_5452_(Heightmap.Types heightmapType, BlockPos pos) {
        return null;
    }

    public int m_6924_(Heightmap.Types heightmapType, int x, int z) {
        return 0;
    }

    public int m_7445_() {
        return 0;
    }

    public BiomeManager m_7062_() {
        return null;
    }

    public WorldBorder m_6857_() {
        return null;
    }

    public boolean m_5450_(@javax.annotation.Nullable Entity entityIn, VoxelShape shape) {
        return false;
    }

    public List<VoxelShape> m_183134_(@Nullable Entity p_186427_, AABB p_186428_) {
        return null;
    }

    public int m_277075_(BlockPos pos, Direction direction) {
        return this.m_8055_(pos).m_60775_((BlockGetter)this, pos, direction);
    }

    public RegistryAccess m_9598_() {
        return RegistryAccess.f_243945_;
    }

    public FeatureFlagSet m_246046_() {
        return FeatureFlagSet.m_246902_();
    }

    public boolean m_5776_() {
        return false;
    }

    public int m_5736_() {
        return 0;
    }

    @NotNull
    public DimensionType m_6042_() {
        return this.dimType;
    }

    private boolean withinBounds(int x, int z) {
        return x >= 0 && x < this.chunkArray.length && z >= 0 && z < this.chunkArray[x].length && this.chunkArray[x][z] != null;
    }

    public float m_7717_(Direction direction, boolean b) {
        return 0.0f;
    }

    public LevelLightEngine m_5518_() {
        return null;
    }
}
