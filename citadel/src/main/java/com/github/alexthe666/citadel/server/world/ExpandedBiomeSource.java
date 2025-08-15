/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Holder
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.biome.Biome
 */
package com.github.alexthe666.citadel.server.world;

import java.util.Map;
import java.util.Set;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

@Deprecated(since="2.6.0")
public interface ExpandedBiomeSource {
    public void setResourceKeyMap(Map<ResourceKey<Biome>, Holder<Biome>> var1);

    public Map<ResourceKey<Biome>, Holder<Biome>> getResourceKeyMap();

    public void expandBiomesWith(Set<Holder<Biome>> var1);
}
