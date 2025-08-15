/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 *  javax.annotation.Nullable
 *  net.minecraft.core.Holder
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.GsonHelper
 *  net.minecraft.world.level.biome.Biome
 */
package com.github.alexthe666.citadel.config.biome;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.config.biome.BiomeEntryType;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.biome.Biome;

@Deprecated(since="2.6.2")
public class SpawnBiomeData {
    private List<List<SpawnBiomeEntry>> biomes = new ArrayList<List<SpawnBiomeEntry>>();

    public SpawnBiomeData() {
    }

    private SpawnBiomeData(SpawnBiomeEntry[][] biomesRead) {
        this.biomes = new ArrayList<List<SpawnBiomeEntry>>();
        for (SpawnBiomeEntry[] innerArray : biomesRead) {
            this.biomes.add(Arrays.asList(innerArray));
        }
    }

    public SpawnBiomeData addBiomeEntry(BiomeEntryType type, boolean negate, String value, int pool) {
        if (this.biomes.isEmpty() || this.biomes.size() < pool + 1) {
            this.biomes.add(new ArrayList());
        }
        this.biomes.get(pool).add(new SpawnBiomeEntry(type, negate, value));
        return this;
    }

    public boolean matches(@Nullable Holder<Biome> biomeHolder, ResourceLocation registryName) {
        for (List<SpawnBiomeEntry> all : this.biomes) {
            boolean overall = true;
            for (SpawnBiomeEntry cond : all) {
                if (cond.matches(biomeHolder, registryName)) continue;
                overall = false;
            }
            if (!overall) continue;
            return true;
        }
        return false;
    }

    private class SpawnBiomeEntry {
        BiomeEntryType type;
        boolean negate;
        String value;

        public SpawnBiomeEntry(BiomeEntryType type, boolean remove, String value) {
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

    public static class Deserializer
    implements JsonDeserializer<SpawnBiomeData>,
    JsonSerializer<SpawnBiomeData> {
        public SpawnBiomeData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonobject = json.getAsJsonObject();
            SpawnBiomeEntry[][] biomesRead = (SpawnBiomeEntry[][])GsonHelper.m_13845_((JsonObject)jsonobject, (String)"biomes", (Object)new SpawnBiomeEntry[0][0], (JsonDeserializationContext)context, SpawnBiomeEntry[][].class);
            return new SpawnBiomeData(biomesRead);
        }

        public JsonElement serialize(SpawnBiomeData src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonobject = new JsonObject();
            jsonobject.add("biomes", context.serialize(src.biomes));
            return jsonobject;
        }
    }
}
