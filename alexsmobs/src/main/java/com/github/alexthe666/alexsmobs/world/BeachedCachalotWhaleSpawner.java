/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Vec3i
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.SpawnPlacements$Type
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.NaturalSpawner
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 */
package com.github.alexthe666.alexsmobs.world;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.config.BiomeConfig;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityCachalotWhale;
import com.github.alexthe666.alexsmobs.world.AMWorldData;
import com.github.alexthe666.alexsmobs.world.AMWorldRegistry;
import java.util.Iterator;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;

public class BeachedCachalotWhaleSpawner {
    private final Random random = new Random();
    private final ServerLevel world;
    private int timer;
    private int delay;
    private int chance;

    public BeachedCachalotWhaleSpawner(ServerLevel p_i50177_1_) {
        this.world = p_i50177_1_;
        this.timer = 1200;
        AMWorldData worldinfo = AMWorldData.get((Level)p_i50177_1_);
        this.delay = worldinfo.getBeachedCachalotSpawnDelay();
        this.chance = worldinfo.getBeachedCachalotSpawnChance();
        if (this.delay == 0 && this.chance == 0) {
            this.delay = AMConfig.beachedCachalotWhaleSpawnDelay;
            worldinfo.setBeachedCachalotSpawnDelay(this.delay);
            this.chance = 25;
            worldinfo.setBeachedCachalotSpawnChance(this.chance);
        }
    }

    public void tick() {
        if (AMConfig.beachedCachalotWhales && --this.timer <= 0 && this.world.m_46470_()) {
            this.timer = 1200;
            AMWorldData worldinfo = AMWorldData.get((Level)this.world);
            this.delay -= 1200;
            if (this.delay < 0) {
                this.delay = 0;
            }
            worldinfo.setBeachedCachalotSpawnDelay(this.delay);
            if (this.delay <= 0) {
                this.delay = AMConfig.beachedCachalotWhaleSpawnDelay;
                if (this.world.m_46469_().m_46207_(GameRules.f_46134_)) {
                    int i = this.chance;
                    this.chance = Mth.m_14045_((int)(this.chance + AMConfig.beachedCachalotWhaleSpawnChance), (int)5, (int)100);
                    worldinfo.setBeachedCachalotSpawnChance(this.chance);
                    if (this.random.nextInt(100) <= i && this.attemptSpawnWhale()) {
                        this.chance = AMConfig.beachedCachalotWhaleSpawnChance;
                    }
                }
            }
        }
    }

    private boolean attemptSpawnWhale() {
        ServerPlayer playerentity = this.world.m_8890_();
        if (playerentity == null) {
            return true;
        }
        if (this.random.nextInt(5) != 0) {
            return false;
        }
        BlockPos blockpos = playerentity.m_20183_();
        BlockPos blockpos2 = this.func_221244_a(blockpos, 84);
        if (blockpos2 != null && this.func_226559_a_(blockpos2) && blockpos2.m_123331_((Vec3i)blockpos) > 225.0) {
            BlockPos upPos = new BlockPos(blockpos2.m_123341_(), blockpos2.m_123342_() + 2, blockpos2.m_123343_());
            EntityCachalotWhale whale = (EntityCachalotWhale)((EntityType)AMEntityRegistry.CACHALOT_WHALE.get()).m_20615_((Level)this.world);
            whale.m_7678_((double)upPos.m_123341_() + 0.5, (double)upPos.m_123342_() + 0.5, (double)upPos.m_123343_() + 0.5, this.random.nextFloat() * 360.0f - 180.0f, 0.0f);
            whale.m_6518_((ServerLevelAccessor)this.world, this.world.m_6436_(upPos), MobSpawnType.SPAWNER, null, null);
            whale.setBeached(true);
            AMWorldData worldinfo = AMWorldData.get((Level)this.world);
            worldinfo.setBeachedCachalotID(whale.m_20148_());
            whale.m_21446_(upPos, 16);
            whale.setDespawnBeach(true);
            this.world.m_7967_((Entity)whale);
            return true;
        }
        return false;
    }

    @Nullable
    private BlockPos func_221244_a(BlockPos p_221244_1_, int p_221244_2_) {
        BlockPos blockpos = null;
        for (int i = 0; i < 10; ++i) {
            int k;
            int l;
            int j = p_221244_1_.m_123341_() + this.random.nextInt(p_221244_2_ * 2) - p_221244_2_;
            BlockPos blockpos1 = new BlockPos(j, l = this.world.m_6924_(Heightmap.Types.WORLD_SURFACE, j, k = p_221244_1_.m_123343_() + this.random.nextInt(p_221244_2_ * 2) - p_221244_2_), k);
            if (!AMWorldRegistry.testBiome(BiomeConfig.cachalot_whale_beached_spawns, (Holder<Biome>)this.world.m_204166_(blockpos1)) || !NaturalSpawner.m_47051_((SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (LevelReader)this.world, (BlockPos)blockpos1, (EntityType)EntityType.f_20494_)) continue;
            blockpos = blockpos1;
            break;
        }
        return blockpos;
    }

    private boolean func_226559_a_(BlockPos p_226559_1_) {
        BlockPos blockpos;
        Iterator var2 = BlockPos.m_121940_((BlockPos)p_226559_1_, (BlockPos)p_226559_1_.m_7918_(1, 2, 1)).iterator();
        do {
            if (var2.hasNext()) continue;
            return true;
        } while (this.world.m_8055_(blockpos = (BlockPos)var2.next()).m_60816_((BlockGetter)this.world, blockpos).m_83281_() && this.world.m_6425_(blockpos).m_76178_());
        return false;
    }
}
