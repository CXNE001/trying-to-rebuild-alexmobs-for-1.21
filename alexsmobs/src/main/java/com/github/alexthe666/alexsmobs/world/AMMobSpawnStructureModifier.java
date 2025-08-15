/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  net.minecraft.core.Holder
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.levelgen.structure.Structure
 *  net.minecraftforge.common.world.ModifiableStructureInfo$StructureInfo$Builder
 *  net.minecraftforge.common.world.StructureModifier
 *  net.minecraftforge.common.world.StructureModifier$Phase
 *  net.minecraftforge.registries.ForgeRegistries$Keys
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.alexsmobs.world;

import com.github.alexthe666.alexsmobs.world.AMWorldRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.world.ModifiableStructureInfo;
import net.minecraftforge.common.world.StructureModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AMMobSpawnStructureModifier
implements StructureModifier {
    private static final RegistryObject<Codec<? extends StructureModifier>> SERIALIZER = RegistryObject.create((ResourceLocation)new ResourceLocation("alexsmobs", "am_structure_spawns"), (ResourceKey)ForgeRegistries.Keys.STRUCTURE_MODIFIER_SERIALIZERS, (String)"alexsmobs");

    public void modify(Holder<Structure> structure, StructureModifier.Phase phase, ModifiableStructureInfo.StructureInfo.Builder builder) {
        if (phase == StructureModifier.Phase.ADD) {
            AMWorldRegistry.modifyStructure(structure, builder);
        }
    }

    public Codec<? extends StructureModifier> codec() {
        return (Codec)SERIALIZER.get();
    }

    public static Codec<AMMobSpawnStructureModifier> makeCodec() {
        return Codec.unit(AMMobSpawnStructureModifier::new);
    }
}
