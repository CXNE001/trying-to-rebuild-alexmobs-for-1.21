/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  it.unimi.dsi.fastutil.objects.ObjectList
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement
 *  net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
 */
package com.github.alexthe666.citadel.server.generation;

import com.github.alexthe666.citadel.Citadel;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class VillageHouseManager {
    public static final List<ResourceLocation> VILLAGE_REPLACEMENT_POOLS = List.of(new ResourceLocation("minecraft:village/plains/houses"), new ResourceLocation("minecraft:village/desert/houses"), new ResourceLocation("minecraft:village/savanna/houses"), new ResourceLocation("minecraft:village/snowy/houses"), new ResourceLocation("minecraft:village/taiga/houses"));
    private static final List<Pair<ResourceLocation, Consumer<StructureTemplatePool>>> REGISTRY = new ArrayList<Pair<ResourceLocation, Consumer<StructureTemplatePool>>>();

    public static void register(ResourceLocation pool, Consumer<StructureTemplatePool> addToPool) {
        REGISTRY.add((Pair<ResourceLocation, Consumer<StructureTemplatePool>>)new Pair((Object)pool, addToPool));
        Citadel.LOGGER.debug("registered addition to pool: " + pool);
    }

    public static StructureTemplatePool addToPool(StructureTemplatePool pool, StructurePoolElement element, int weight) {
        ObjectArrayList templates;
        if (weight > 0 && pool != null && !(templates = new ObjectArrayList((ObjectList)pool.f_210560_)).contains((Object)element)) {
            for (int i = 0; i < weight; ++i) {
                templates.add((Object)element);
            }
            ArrayList<Pair> rawTemplates = new ArrayList<Pair>(pool.f_210559_);
            rawTemplates.add(new Pair((Object)element, (Object)weight));
            pool.f_210560_ = templates;
            pool.f_210559_ = rawTemplates;
            Citadel.LOGGER.info("Added to village structure pool");
        }
        return pool;
    }

    public static void addAllHouses(RegistryAccess registryAccess) {
        try {
            for (ResourceLocation villagePool : VILLAGE_REPLACEMENT_POOLS) {
                StructureTemplatePool pool = registryAccess.m_175515_(Registries.f_256948_).m_6612_(villagePool).orElse(null);
                if (pool == null) continue;
                for (Pair<ResourceLocation, Consumer<StructureTemplatePool>> pair : REGISTRY) {
                    if (!villagePool.equals(pair.getFirst())) continue;
                    ((Consumer)pair.getSecond()).accept(pool);
                }
            }
        }
        catch (Exception e) {
            Citadel.LOGGER.error("Could not add village houses!");
            e.printStackTrace();
        }
    }
}
