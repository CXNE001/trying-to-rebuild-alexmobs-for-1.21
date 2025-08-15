/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.JsonSyntaxException
 *  com.google.gson.reflect.TypeToken
 *  com.google.gson.stream.JsonReader
 *  javax.annotation.Nullable
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.Item
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.apache.commons.lang3.StringUtils
 */
package com.github.alexthe666.citadel.client.model.container;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;

public class JsonUtils {
    public static boolean isString(JsonObject json, String memberName) {
        return JsonUtils.isJsonPrimitive(json, memberName) && json.getAsJsonPrimitive(memberName).isString();
    }

    @OnlyIn(value=Dist.CLIENT)
    public static boolean isString(JsonElement json) {
        return json.isJsonPrimitive() && json.getAsJsonPrimitive().isString();
    }

    public static boolean isNumber(JsonElement json) {
        return json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber();
    }

    @OnlyIn(value=Dist.CLIENT)
    public static boolean isBoolean(JsonObject json, String memberName) {
        return JsonUtils.isJsonPrimitive(json, memberName) && json.getAsJsonPrimitive(memberName).isBoolean();
    }

    public static boolean isJsonArray(JsonObject json, String memberName) {
        return JsonUtils.hasField(json, memberName) && json.get(memberName).isJsonArray();
    }

    public static boolean isJsonPrimitive(JsonObject json, String memberName) {
        return JsonUtils.hasField(json, memberName) && json.get(memberName).isJsonPrimitive();
    }

    public static boolean hasField(JsonObject json, String memberName) {
        if (json == null) {
            return false;
        }
        return json.get(memberName) != null;
    }

    public static String getString(JsonElement json, String memberName) {
        if (json.isJsonPrimitive()) {
            return json.getAsString();
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be a string, was " + JsonUtils.toString(json));
    }

    public static String getString(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return JsonUtils.getString(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + ", expected to find a string");
    }

    public static String getString(JsonObject json, String memberName, String fallback) {
        return json.has(memberName) ? JsonUtils.getString(json.get(memberName), memberName) : fallback;
    }

    @Nullable
    public static Item getByNameOrId(String id) {
        Item item = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation(id));
        if (item == null) {
            try {
                return Item.m_41445_((int)Integer.parseInt(id));
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        return item;
    }

    public static Item getItem(JsonElement json, String memberName) {
        if (json.isJsonPrimitive()) {
            String s = json.getAsString();
            Item item = JsonUtils.getByNameOrId(s);
            if (item == null) {
                throw new JsonSyntaxException("Expected " + memberName + " to be an item, was unknown string '" + s + "'");
            }
            return item;
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be an item, was " + JsonUtils.toString(json));
    }

    public static Item getItem(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return JsonUtils.getItem(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + ", expected to find an item");
    }

    public static boolean getBoolean(JsonElement json, String memberName) {
        if (json.isJsonPrimitive()) {
            return json.getAsBoolean();
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be a Boolean, was " + JsonUtils.toString(json));
    }

    public static boolean getBoolean(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return JsonUtils.getBoolean(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + ", expected to find a Boolean");
    }

    public static boolean getBoolean(JsonObject json, String memberName, boolean fallback) {
        return json.has(memberName) ? JsonUtils.getBoolean(json.get(memberName), memberName) : fallback;
    }

    public static float getFloat(JsonElement json, String memberName) {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
            return json.getAsFloat();
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be a Float, was " + JsonUtils.toString(json));
    }

    public static float getFloat(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return JsonUtils.getFloat(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + ", expected to find a Float");
    }

    public static float getFloat(JsonObject json, String memberName, float fallback) {
        return json.has(memberName) ? JsonUtils.getFloat(json.get(memberName), memberName) : fallback;
    }

    public static int getInt(JsonElement json, String memberName) {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
            return json.getAsInt();
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be a Int, was " + JsonUtils.toString(json));
    }

    public static int getInt(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return JsonUtils.getInt(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + ", expected to find a Int");
    }

    public static int getInt(JsonObject json, String memberName, int fallback) {
        return json.has(memberName) ? JsonUtils.getInt(json.get(memberName), memberName) : fallback;
    }

    public static JsonObject getJsonObject(JsonElement json, String memberName) {
        if (json.isJsonObject()) {
            return json.getAsJsonObject();
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be a JsonObject, was " + JsonUtils.toString(json));
    }

    public static JsonObject getJsonObject(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return JsonUtils.getJsonObject(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + ", expected to find a JsonObject");
    }

    public static JsonObject getJsonObject(JsonObject json, String memberName, JsonObject fallback) {
        return json.has(memberName) ? JsonUtils.getJsonObject(json.get(memberName), memberName) : fallback;
    }

    public static JsonArray getJsonArray(JsonElement json, String memberName) {
        if (json.isJsonArray()) {
            return json.getAsJsonArray();
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be a JsonArray, was " + JsonUtils.toString(json));
    }

    public static JsonArray getJsonArray(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return JsonUtils.getJsonArray(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + ", expected to find a JsonArray");
    }

    public static JsonArray getJsonArray(JsonObject json, String memberName, @Nullable JsonArray fallback) {
        return json.has(memberName) ? JsonUtils.getJsonArray(json.get(memberName), memberName) : fallback;
    }

    public static <T> T deserializeClass(@Nullable JsonElement json, String memberName, JsonDeserializationContext context, Class<? extends T> adapter) {
        if (json != null) {
            return (T)context.deserialize(json, adapter);
        }
        throw new JsonSyntaxException("Missing " + memberName);
    }

    public static <T> T deserializeClass(JsonObject json, String memberName, JsonDeserializationContext context, Class<? extends T> adapter) {
        if (json.has(memberName)) {
            return JsonUtils.deserializeClass(json.get(memberName), memberName, context, adapter);
        }
        throw new JsonSyntaxException("Missing " + memberName);
    }

    public static <T> T deserializeClass(JsonObject json, String memberName, T fallback, JsonDeserializationContext context, Class<? extends T> adapter) {
        return json.has(memberName) ? JsonUtils.deserializeClass(json.get(memberName), memberName, context, adapter) : fallback;
    }

    public static String toString(JsonElement json) {
        String s = StringUtils.abbreviateMiddle((String)String.valueOf(json), (String)"...", (int)10);
        if (json == null) {
            return "null (missing)";
        }
        if (json.isJsonNull()) {
            return "null (json)";
        }
        if (json.isJsonArray()) {
            return "an array (" + s + ")";
        }
        if (json.isJsonObject()) {
            return "an object (" + s + ")";
        }
        if (json.isJsonPrimitive()) {
            JsonPrimitive jsonprimitive = json.getAsJsonPrimitive();
            if (jsonprimitive.isNumber()) {
                return "a number (" + s + ")";
            }
            if (jsonprimitive.isBoolean()) {
                return "a boolean (" + s + ")";
            }
        }
        return s;
    }

    @Nullable
    public static <T> T gsonDeserialize(Gson gsonIn, Reader readerIn, Class<T> adapter, boolean lenient) {
        try {
            JsonReader jsonreader = new JsonReader(readerIn);
            jsonreader.setLenient(lenient);
            return (T)gsonIn.getAdapter(adapter).read(jsonreader);
        }
        catch (IOException ioexception) {
            throw new JsonParseException((Throwable)ioexception);
        }
    }

    @Nullable
    public static <T> T fromJson(Gson gson, Reader p_193838_1_, Type p_193838_2_, boolean p_193838_3_) {
        try {
            JsonReader jsonreader = new JsonReader(p_193838_1_);
            jsonreader.setLenient(p_193838_3_);
            return (T)gson.getAdapter(TypeToken.get((Type)p_193838_2_)).read(jsonreader);
        }
        catch (IOException ioexception) {
            throw new JsonParseException((Throwable)ioexception);
        }
    }

    @Nullable
    public static <T> T fromJson(Gson p_193837_0_, String p_193837_1_, Type p_193837_2_, boolean p_193837_3_) {
        return JsonUtils.fromJson(p_193837_0_, new StringReader(p_193837_1_), p_193837_2_, p_193837_3_);
    }

    @Nullable
    public static <T> T gsonDeserialize(Gson gsonIn, String json, Class<T> adapter, boolean lenient) {
        return JsonUtils.gsonDeserialize(gsonIn, new StringReader(json), adapter, lenient);
    }

    @Nullable
    public static <T> T fromJson(Gson p_193841_0_, Reader p_193841_1_, Type p_193841_2_) {
        return JsonUtils.fromJson(p_193841_0_, p_193841_1_, p_193841_2_, false);
    }

    @Nullable
    public static <T> T gsonDeserialize(Gson p_193840_0_, String p_193840_1_, Type p_193840_2_) {
        return JsonUtils.fromJson(p_193840_0_, p_193840_1_, p_193840_2_, false);
    }

    @Nullable
    public static <T> T fromJson(Gson gson, Reader reader, Class<T> jsonClass) {
        return JsonUtils.gsonDeserialize(gson, reader, jsonClass, false);
    }

    @Nullable
    public static <T> T gsonDeserialize(Gson gsonIn, String json, Class<T> adapter) {
        return JsonUtils.gsonDeserialize(gsonIn, json, adapter, false);
    }
}
