/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  net.minecraft.core.Holder
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraftforge.common.world.BiomeModifier
 *  net.minecraftforge.common.world.BiomeModifier$Phase
 *  net.minecraftforge.common.world.ModifiableBiomeInfo$BiomeInfo$Builder
 *  net.minecraftforge.registries.ForgeRegistries$Keys
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.citadel.server.generation;

import com.github.alexthe666.citadel.config.ServerConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SpawnProbabilityModifier
implements BiomeModifier {
    private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create((ResourceLocation)new ResourceLocation("citadel:mob_spawn_probability"), (ResourceKey)ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, (String)"citadel");

    public void modify(Holder<Biome> biome, BiomeModifier.Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        float probability = (float)ServerConfig.chunkGenSpawnModifierVal * builder.getMobSpawnSettings().getProbability();
        if (phase == BiomeModifier.Phase.MODIFY) {
            builder.getMobSpawnSettings().m_48368_(Mth.m_14036_((float)probability, (float)0.0f, (float)1.0f));
        }
    }

    public Codec<? extends BiomeModifier> codec() {
        return (Codec)SERIALIZER.get();
    }

    public static Codec<SpawnProbabilityModifier> makeCodec() {
        return Codec.unit(SpawnProbabilityModifier::new);
    }
}
