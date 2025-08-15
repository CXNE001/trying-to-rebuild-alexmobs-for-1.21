/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.Holder
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.biome.Biome
 */
package com.github.alexthe666.citadel.config.biome;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.config.biome.BiomeEntryType;
import javax.annotation.Nullable;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

private class SpawnBiomeData.SpawnBiomeEntry {
    BiomeEntryType type;
    boolean negate;
    String value;

    public SpawnBiomeData.SpawnBiomeEntry(BiomeEntryType type, boolean remove, String value) {
        this.type = type;
        this.negate = remove;
        this.value = value;
    }

    public boolean matches(@Nullable Holder<Biome> biomeHolder, ResourceLocation registryName) {
        if (this.type.isDepreciated()) {
            Citadel.LOGGER.warn("biome config: BIOME_DICT and BIOME_CATEGORY are no longer valid in 1.19+. Please use BIOME_TAG instead.");
            return false;
        }
        if (this.type == BiomeEntryType.BIOME_TAG) {
            if (biomeHolder.getTagKeys().anyMatch(biomeTagKey -> biomeTagKey.f_203868_() != null && biomeTagKey.f_203868_().toString().equals(this.value))) {
                return !this.negate;
            }
            return this.negate;
        }
        if (registryName.toString().equals(this.value)) {
            return !this.negate;
        }
        return this.negate;
    }
}
