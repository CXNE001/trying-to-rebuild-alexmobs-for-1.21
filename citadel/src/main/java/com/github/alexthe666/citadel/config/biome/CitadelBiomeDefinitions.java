/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.config.biome;

import com.github.alexthe666.citadel.config.biome.BiomeEntryType;
import com.github.alexthe666.citadel.config.biome.SpawnBiomeData;

@Deprecated(since="2.6.2")
public class CitadelBiomeDefinitions {
    public static final SpawnBiomeData TEST = new SpawnBiomeData().addBiomeEntry(BiomeEntryType.BIOME_DICT, false, "overworld", 0).addBiomeEntry(BiomeEntryType.BIOME_DICT, true, "water", 0).addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "minecraft:nether_wastes", 1);
    public static final SpawnBiomeData TERRALITH_TEST = new SpawnBiomeData().addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "terralith:savanna_badlands", 0);
}
