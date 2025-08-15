/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  net.minecraft.resources.ResourceKey
 *  net.minecraftforge.common.loot.IGlobalLootModifier
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries$Keys
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.alexsmobs.misc;

import com.github.alexthe666.alexsmobs.misc.AncientDartLootModifier;
import com.github.alexthe666.alexsmobs.misc.BananaLootModifier;
import com.github.alexthe666.alexsmobs.misc.BlossomLootModifier;
import com.github.alexthe666.alexsmobs.misc.PigshoesLootModifier;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AMLootRegistry {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> DEF_REG = DeferredRegister.create((ResourceKey)ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, (String)"alexsmobs");
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> BANANA_DROP = DEF_REG.register("banana_drop", BananaLootModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> BLOSSOM_DROP = DEF_REG.register("blossom_drop", BlossomLootModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ANCIENT_DART = DEF_REG.register("ancient_dart", AncientDartLootModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> PIGSHOES = DEF_REG.register("pigshoes", PigshoesLootModifier.CODEC);
}
