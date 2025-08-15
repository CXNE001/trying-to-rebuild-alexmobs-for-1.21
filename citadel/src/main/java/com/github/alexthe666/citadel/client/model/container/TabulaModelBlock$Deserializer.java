/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  net.minecraft.client.renderer.block.model.BlockElement
 *  net.minecraft.client.renderer.block.model.ItemOverride
 *  net.minecraft.client.renderer.block.model.ItemTransforms
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.citadel.client.model.container;

import com.github.alexthe666.citadel.client.model.container.JsonUtils;
import com.github.alexthe666.citadel.client.model.container.TabulaModelBlock;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.block.model.BlockElement;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.resources.ResourceLocation;

public static class TabulaModelBlock.Deserializer
implements JsonDeserializer<TabulaModelBlock> {
    public TabulaModelBlock deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
        JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
        List<BlockElement> list = this.getModelElements(p_deserialize_3_, jsonobject);
        String s = this.getParent(jsonobject);
        Map<String, String> map = this.getTextures(jsonobject);
        boolean flag = this.getAmbientOcclusionEnabled(jsonobject);
        ItemTransforms itemcameratransforms = ItemTransforms.f_111786_;
        if (jsonobject.has("display")) {
            JsonObject jsonobject1 = JsonUtils.getJsonObject(jsonobject, "display");
            itemcameratransforms = (ItemTransforms)p_deserialize_3_.deserialize((JsonElement)jsonobject1, ItemTransforms.class);
        }
        List<ItemOverride> list1 = this.getItemOverrides(p_deserialize_3_, jsonobject);
        ResourceLocation resourcelocation = s.isEmpty() ? null : new ResourceLocation(s);
        return new TabulaModelBlock(resourcelocation, list, map, flag, true, itemcameratransforms, list1);
    }

    protected List<ItemOverride> getItemOverrides(JsonDeserializationContext deserializationContext, JsonObject object) {
        ArrayList list = Lists.newArrayList();
        if (object.has("overrides")) {
            for (JsonElement jsonelement : JsonUtils.getJsonArray(object, "overrides")) {
                list.add((ItemOverride)deserializationContext.deserialize(jsonelement, ItemOverride.class));
            }
        }
        return list;
    }

    private Map<String, String> getTextures(JsonObject object) {
        HashMap map = Maps.newHashMap();
        if (object.has("textures")) {
            JsonObject jsonobject = object.getAsJsonObject("textures");
            for (Map.Entry entry : jsonobject.entrySet()) {
                map.put((String)entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
            }
        }
        return map;
    }

    private String getParent(JsonObject object) {
        return JsonUtils.getString(object, "parent", "");
    }

    protected boolean getAmbientOcclusionEnabled(JsonObject object) {
        return JsonUtils.getBoolean(object, "ambientocclusion", true);
    }

    protected List<BlockElement> getModelElements(JsonDeserializationContext deserializationContext, JsonObject object) {
        ArrayList list = Lists.newArrayList();
        if (object.has("elements")) {
            for (JsonElement jsonelement : JsonUtils.getJsonArray(object, "elements")) {
                list.add((BlockElement)deserializationContext.deserialize(jsonelement, BlockElement.class));
            }
        }
        return list;
    }
}
