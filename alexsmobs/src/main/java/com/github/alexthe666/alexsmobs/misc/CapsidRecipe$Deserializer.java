/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.model.container.JsonUtils
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  net.minecraft.core.NonNullList
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.ShapedRecipe
 */
package com.github.alexthe666.alexsmobs.misc;

import com.github.alexthe666.alexsmobs.misc.CapsidRecipe;
import com.github.alexthe666.citadel.client.model.container.JsonUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;

public static class CapsidRecipe.Deserializer
implements JsonDeserializer<CapsidRecipe> {
    public CapsidRecipe deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonobject = json.getAsJsonObject();
        int time = JsonUtils.getInt((JsonObject)jsonobject, (String)"time");
        ItemStack result = ItemStack.f_41583_;
        if (jsonobject.has("result")) {
            result = ShapedRecipe.m_151274_((JsonObject)JsonUtils.getJsonObject((JsonObject)jsonobject, (String)"result"));
        }
        NonNullList<Ingredient> nonnulllist = CapsidRecipe.readIngredients(JsonUtils.getJsonArray((JsonObject)jsonobject, (String)"ingredients"));
        return new CapsidRecipe(nonnulllist, result, time);
    }
}
