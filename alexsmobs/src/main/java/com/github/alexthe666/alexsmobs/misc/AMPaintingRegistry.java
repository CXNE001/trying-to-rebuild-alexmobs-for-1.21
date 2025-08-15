/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.decoration.PaintingVariant
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.alexsmobs.misc;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class AMPaintingRegistry {
    public static final DeferredRegister<PaintingVariant> DEF_REG = DeferredRegister.create((IForgeRegistry)ForgeRegistries.PAINTING_VARIANTS, (String)"alexsmobs");
    public static final RegistryObject<PaintingVariant> NFT = DEF_REG.register("nft", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> DOG_POKER = DEF_REG.register("dog_poker", () -> new PaintingVariant(32, 16));
}
