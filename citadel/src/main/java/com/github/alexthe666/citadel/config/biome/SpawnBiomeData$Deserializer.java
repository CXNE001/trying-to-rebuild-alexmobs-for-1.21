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
 *  net.minecraft.util.GsonHelper
 */
package com.github.alexthe666.citadel.config.biome;

import com.github.alexthe666.citadel.config.biome.SpawnBiomeData;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.util.GsonHelper;

public static class SpawnBiomeData.Deserializer
implements JsonDeserializer<SpawnBiomeData>,
JsonSerializer<SpawnBiomeData> {
    public SpawnBiomeData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonobject = json.getAsJsonObject();
        SpawnBiomeData.SpawnBiomeEntry[][] biomesRead = (SpawnBiomeData.SpawnBiomeEntry[][])GsonHelper.m_13845_((JsonObject)jsonobject, (String)"biomes", (Object)new SpawnBiomeData.SpawnBiomeEntry[0][0], (JsonDeserializationContext)context, SpawnBiomeData.SpawnBiomeEntry[][].class);
        return new SpawnBiomeData(biomesRead);
    }

    public JsonElement serialize(SpawnBiomeData src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonobject = new JsonObject();
        jsonobject.add("biomes", context.serialize(src.biomes));
        return jsonobject;
    }
}
