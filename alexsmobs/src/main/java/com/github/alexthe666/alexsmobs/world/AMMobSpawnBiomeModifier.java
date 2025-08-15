/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  net.minecraft.core.Holder
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraftforge.common.world.BiomeModifier
 *  net.minecraftforge.common.world.BiomeModifier$Phase
 *  net.minecraftforge.common.world.ModifiableBiomeInfo$BiomeInfo$Builder
 *  net.minecraftforge.registries.ForgeRegistries$Keys
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.alexsmobs.world;

import com.github.alexthe666.alexsmobs.world.AMWorldRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AMMobSpawnBiomeModifier
implements BiomeModifier {
    private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create((ResourceLocation)new ResourceLocation("alexsmobs", "am_mob_spawns"), (ResourceKey)ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, (String)"alexsmobs");

    public void modify(Holder<Biome> biome, BiomeModifier.Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == BiomeModifier.Phase.ADD) {
            AMWorldRegistry.addBiomeSpawns(biome, builder);
        }
    }

    public Codec<? extends BiomeModifier> codec() {
        return (Codec)SERIALIZER.get();
    }

    public static Codec<AMMobSpawnBiomeModifier> makeCodec() {
        return Codec.unit(AMMobSpawnBiomeModifier::new);
    }
}
