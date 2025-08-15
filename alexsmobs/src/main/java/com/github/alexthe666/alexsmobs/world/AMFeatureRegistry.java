/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  net.minecraft.world.level.levelgen.feature.Feature
 *  net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.alexsmobs.world;

import com.github.alexthe666.alexsmobs.world.FeatureLeafcutterAnthill;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class AMFeatureRegistry {
    public static final DeferredRegister<Feature<?>> DEF_REG = DeferredRegister.create((IForgeRegistry)ForgeRegistries.FEATURES, (String)"alexsmobs");
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> LEAFCUTTER_ANTHILL = DEF_REG.register("leafcutter_anthill", () -> new FeatureLeafcutterAnthill((Codec<NoneFeatureConfiguration>)NoneFeatureConfiguration.f_67815_));
}
