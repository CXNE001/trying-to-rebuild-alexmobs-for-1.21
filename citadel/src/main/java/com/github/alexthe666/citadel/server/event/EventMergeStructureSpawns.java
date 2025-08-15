/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.tags.TagKey
 *  net.minecraft.util.random.WeightedRandomList
 *  net.minecraft.world.entity.MobCategory
 *  net.minecraft.world.level.StructureManager
 *  net.minecraft.world.level.biome.MobSpawnSettings$SpawnerData
 *  net.minecraft.world.level.levelgen.structure.Structure
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.Event$HasResult
 */
package com.github.alexthe666.citadel.server.event;

import java.util.ArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.eventbus.api.Event;

@Event.HasResult
public class EventMergeStructureSpawns
extends Event {
    private final StructureManager structureManager;
    private final BlockPos pos;
    private final MobCategory category;
    private WeightedRandomList<MobSpawnSettings.SpawnerData> structureSpawns;
    private final WeightedRandomList<MobSpawnSettings.SpawnerData> biomeSpawns;

    public EventMergeStructureSpawns(StructureManager structureManager, BlockPos pos, MobCategory category, WeightedRandomList<MobSpawnSettings.SpawnerData> structureSpawns, WeightedRandomList<MobSpawnSettings.SpawnerData> biomeSpawns) {
        this.structureManager = structureManager;
        this.pos = pos;
        this.category = category;
        this.structureSpawns = structureSpawns;
        this.biomeSpawns = biomeSpawns;
    }

    public StructureManager getStructureManager() {
        return this.structureManager;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public MobCategory getCategory() {
        return this.category;
    }

    public boolean isStructureTagged(TagKey<Structure> tagKey) {
        return this.structureManager.m_220491_(this.pos, tagKey).m_73603_();
    }

    public WeightedRandomList<MobSpawnSettings.SpawnerData> getStructureSpawns() {
        return this.structureSpawns;
    }

    public void setStructureSpawns(WeightedRandomList<MobSpawnSettings.SpawnerData> spawns) {
        this.structureSpawns = spawns;
    }

    public void mergeSpawns() {
        ArrayList<MobSpawnSettings.SpawnerData> list = new ArrayList<MobSpawnSettings.SpawnerData>(this.biomeSpawns.m_146338_());
        for (MobSpawnSettings.SpawnerData structureSpawn : this.structureSpawns.m_146338_()) {
            if (list.contains(structureSpawn)) continue;
            list.add(structureSpawn);
        }
        this.setStructureSpawns((WeightedRandomList<MobSpawnSettings.SpawnerData>)WeightedRandomList.m_146328_(list));
    }

    public WeightedRandomList<MobSpawnSettings.SpawnerData> getBiomeSpawns() {
        return this.biomeSpawns;
    }
}
