/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.Mth
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelHeightAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.chunk.ChunkGenerator
 *  net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator
 *  net.minecraft.world.level.levelgen.NoiseGeneratorSettings
 *  net.minecraft.world.level.levelgen.NoiseSettings
 *  net.minecraft.world.level.levelgen.RandomState
 *  net.minecraft.world.level.saveddata.SavedData
 *  net.minecraft.world.level.storage.DimensionDataStorage
 */
package com.github.alexthe666.alexsmobs.world;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

public class AMWorldData
extends SavedData {
    private static final String IDENTIFIER = "alexsmobs_world_data";
    private ServerLevel level;
    private int tickCounter;
    private int beachedCachalotSpawnDelay;
    private int beachedCachalotSpawnChance;
    private UUID beachedCachalotID;
    private ChunkPos pupfishChunk;
    private int pupfishChunkTime = 0;
    private int pupfishSeedAddition = 0;
    private long startPupfishSearchTimestamp = -1L;
    private boolean noPupfishChunk;
    private static final Map<Level, AMWorldData> dataMap = new HashMap<Level, AMWorldData>();
    private static final Predicate<BlockState> IS_WATER = state -> state.m_60713_(Blocks.f_49990_);

    public static AMWorldData get(Level world) {
        if (world instanceof ServerLevel) {
            ServerLevel overworld = world.m_7654_().m_129880_(Level.f_46428_);
            AMWorldData fromMap = dataMap.get(overworld);
            if (fromMap == null) {
                DimensionDataStorage storage = overworld.m_8895_();
                AMWorldData data = (AMWorldData)storage.m_164861_(AMWorldData::load, AMWorldData::new, IDENTIFIER);
                if (data != null) {
                    data.level = overworld;
                    data.m_77762_();
                }
                dataMap.put(world, data);
                return data;
            }
            return fromMap;
        }
        return null;
    }

    public static AMWorldData load(CompoundTag nbt) {
        AMWorldData data = new AMWorldData();
        if (nbt.m_128425_("BeachedCachalotSpawnDelay", 99)) {
            data.beachedCachalotSpawnDelay = nbt.m_128451_("BeachedCachalotSpawnDelay");
        }
        if (nbt.m_128425_("BeachedCachalotSpawnChance", 99)) {
            data.beachedCachalotSpawnChance = nbt.m_128451_("BeachedCachalotSpawnChance");
        }
        if (nbt.m_128425_("BeachedCachalotId", 8)) {
            data.beachedCachalotID = UUID.fromString(nbt.m_128461_("BeachedCachalotId"));
        }
        if (nbt.m_128441_("PupfishChunkX") && nbt.m_128441_("PupfishChunkZ")) {
            data.pupfishChunk = new ChunkPos(nbt.m_128451_("PupfishChunkX"), nbt.m_128451_("PupfishChunkZ"));
        }
        if (nbt.m_128441_("NoPupfishChunk")) {
            data.noPupfishChunk = nbt.m_128471_("NoPupfishChunk");
        }
        return data;
    }

    public int getBeachedCachalotSpawnDelay() {
        return this.beachedCachalotSpawnDelay;
    }

    public void setBeachedCachalotSpawnDelay(int delay) {
        this.beachedCachalotSpawnDelay = delay;
    }

    public int getBeachedCachalotSpawnChance() {
        return this.beachedCachalotSpawnChance;
    }

    public void setBeachedCachalotSpawnChance(int chance) {
        this.beachedCachalotSpawnChance = chance;
    }

    public void setBeachedCachalotID(UUID id) {
        this.beachedCachalotID = id;
    }

    public void debug() {
    }

    public void tick() {
        ++this.tickCounter;
    }

    public CompoundTag m_7176_(CompoundTag compound) {
        compound.m_128405_("beachedCachalotSpawnDelay", this.beachedCachalotSpawnDelay);
        compound.m_128405_("beachedCachalotSpawnChance", this.beachedCachalotSpawnChance);
        if (this.beachedCachalotID != null) {
            compound.m_128359_("beachedCachalotId", this.beachedCachalotID.toString());
        }
        if (this.pupfishChunk != null) {
            compound.m_128405_("PupfishChunkX", this.pupfishChunk.f_45578_);
            compound.m_128405_("PupfishChunkZ", this.pupfishChunk.f_45579_);
        }
        if (this.noPupfishChunk) {
            compound.m_128379_("NoPupfishChunk", this.noPupfishChunk);
        }
        return compound;
    }

    @Nullable
    public ChunkPos getPupfishChunk() {
        return this.pupfishChunk;
    }

    public boolean isInPupfishChunk(BlockPos pos) {
        if (this.pupfishChunk != null) {
            return pos.m_123341_() >= this.pupfishChunk.m_45604_() && pos.m_123341_() <= this.pupfishChunk.m_45608_() && pos.m_123343_() >= this.pupfishChunk.m_45605_() && pos.m_123343_() <= this.pupfishChunk.m_45609_();
        }
        return false;
    }

    public void tickPupfish() {
        if (AMConfig.restrictPupfishSpawns && !this.noPupfishChunk) {
            if (this.pupfishChunk == null && this.startPupfishSearchTimestamp == -1L) {
                this.startPupfishSearchTimestamp = System.currentTimeMillis();
            }
            if (this.pupfishChunk == null && this.pupfishChunkTime % 10 == 0) {
                long seconds = (System.currentTimeMillis() - this.startPupfishSearchTimestamp) / 1000L;
                if (seconds / 60L > 5L) {
                    AlexsMobs.LOGGER.info("Giving up search for pupfish chunk after " + seconds / 60L + " minutes. no pupfish will spawn in this world :( ");
                    this.noPupfishChunk = true;
                } else {
                    this.searchForPupfishChunk();
                }
            }
            ++this.pupfishChunkTime;
        }
    }

    private void searchForPupfishChunk() {
        ChunkGenerator chunkGenerator;
        if (this.level != null && (chunkGenerator = this.level.m_7726_().m_8481_()) instanceof NoiseBasedChunkGenerator) {
            NoiseBasedChunkGenerator chunkGenerator2 = (NoiseBasedChunkGenerator)chunkGenerator;
            Random random = new Random(this.level.m_7328_() + (long)this.pupfishSeedAddition);
            int randomXCoord = random.nextInt(AMConfig.pupfishChunkSpawnDistance * 2) - AMConfig.pupfishChunkSpawnDistance;
            int randomZCoord = random.nextInt(AMConfig.pupfishChunkSpawnDistance * 2) - AMConfig.pupfishChunkSpawnDistance;
            ChunkPos checkPos = new ChunkPos(randomXCoord >> 4, randomZCoord >> 4);
            BlockPos center = new BlockPos(checkPos.m_151390_(), chunkGenerator2.m_6337_(), checkPos.m_151393_());
            int maxWater = this.getWaterHeight(chunkGenerator2, this.level.m_7726_().m_214994_(), center.m_123341_(), center.m_123343_(), (LevelHeightAccessor)this.level);
            if (maxWater > 31 && maxWater < 63) {
                this.pupfishChunk = checkPos;
                AlexsMobs.LOGGER.info("Found Pupfish chunk at " + this.pupfishChunk.m_45608_() + " ~ " + this.pupfishChunk.m_45605_() + " after " + this.pupfishSeedAddition + " tries");
            }
        }
        ++this.pupfishSeedAddition;
    }

    public int getWaterHeight(NoiseBasedChunkGenerator generator, RandomState rand, int x, int z, LevelHeightAccessor level) {
        NoiseSettings noisesettings = ((NoiseGeneratorSettings)generator.f_64318_.m_203334_()).f_64439_();
        int i = Math.max(noisesettings.f_158688_(), level.m_141937_());
        int j = Math.min(noisesettings.f_158688_() + noisesettings.f_64508_(), level.m_151558_());
        int k = Mth.m_14042_((int)i, (int)noisesettings.m_189212_());
        int l = Mth.m_14042_((int)(j - i), (int)noisesettings.m_189212_());
        return generator.m_224239_(level, rand, x, z, null, IS_WATER).orElse(level.m_141937_());
    }
}
