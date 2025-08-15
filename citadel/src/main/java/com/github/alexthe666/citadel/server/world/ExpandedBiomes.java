/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.dimension.LevelStem
 */
package com.github.alexthe666.citadel.server.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.LevelStem;

@Deprecated(since="2.6.0")
public class ExpandedBiomes {
    private static final Map<ResourceKey<LevelStem>, List<ResourceKey<Biome>>> biomes = new HashMap<ResourceKey<LevelStem>, List<ResourceKey<Biome>>>();

    @Deprecated(since="2.6.0")
    public static void addExpandedBiome(ResourceKey<Biome> biome, ResourceKey<LevelStem> dimension) {
        List<Object> list = !biomes.containsKey(dimension) ? new ArrayList() : biomes.get(dimension);
        if (!list.contains(biome)) {
            list.add(biome);
        }
        biomes.put(dimension, list);
    }
}
