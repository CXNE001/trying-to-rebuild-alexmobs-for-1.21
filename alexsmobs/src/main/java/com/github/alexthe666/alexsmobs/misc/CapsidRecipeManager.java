/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.Lists
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.resources.ResourceManager
 *  net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener
 *  net.minecraft.util.RandomSource
 *  net.minecraft.util.profiling.ProfilerFiller
 *  net.minecraft.world.item.ItemStack
 *  org.apache.logging.log4j.Level
 */
package com.github.alexthe666.alexsmobs.misc;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.misc.CapsidRecipe;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.util.List;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.RandomSource;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.Level;

public class CapsidRecipeManager
extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(CapsidRecipe.class, (Object)new CapsidRecipe.Deserializer()).create();
    private static final RandomSource RANDOM = RandomSource.m_216327_();
    private final List<CapsidRecipe> capsidRecipes = Lists.newArrayList();

    public CapsidRecipeManager() {
        super(GSON, "capsid_recipes");
    }

    protected void apply(Map<ResourceLocation, JsonElement> jsonMap, ResourceManager resourceManager, ProfilerFiller profile) {
        this.capsidRecipes.clear();
        ImmutableMap.Builder builder = ImmutableMap.builder();
        AlexsMobs.LOGGER.log(Level.ALL, "Loading in capsid_recipes jsons...");
        jsonMap.forEach((resourceLocation, jsonElement) -> {
            try {
                CapsidRecipe capsidRecipe = (CapsidRecipe)GSON.fromJson(jsonElement, CapsidRecipe.class);
                builder.put(resourceLocation, (Object)capsidRecipe);
            }
            catch (Exception exception) {
                AlexsMobs.LOGGER.error("Couldn't parse capsid recipe {}", resourceLocation, (Object)exception);
            }
        });
        ImmutableMap immutablemap = builder.build();
        immutablemap.forEach((resourceLocation, capsidRecipe) -> this.capsidRecipes.add((CapsidRecipe)capsidRecipe));
    }

    public CapsidRecipe getRecipeFor(ItemStack stack) {
        for (CapsidRecipe recipe : this.capsidRecipes) {
            if (!recipe.matches(stack)) continue;
            return recipe;
        }
        return null;
    }

    public List<CapsidRecipe> getCapsidRecipes() {
        return this.capsidRecipes;
    }

    public String m_7812_() {
        return "CapsidRecipeManager";
    }
}
