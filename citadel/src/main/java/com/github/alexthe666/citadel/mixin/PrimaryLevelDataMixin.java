/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Registry
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator
 *  net.minecraft.world.level.storage.PrimaryLevelData
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.github.alexthe666.citadel.mixin;

import com.github.alexthe666.citadel.server.generation.NoiseGeneratorSettingsAccessor;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.storage.PrimaryLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={PrimaryLevelData.class})
public class PrimaryLevelDataMixin {
    @Inject(at={@At(value="HEAD")}, remap=true, method={"Lnet/minecraft/world/level/storage/PrimaryLevelData;setTagData(Lnet/minecraft/core/RegistryAccess;Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/nbt/CompoundTag;)V"})
    private void citadel_preSetTagData(RegistryAccess registryAccess, CompoundTag compoundTag, CompoundTag compoundTag1, CallbackInfo ci) {
        this.citadelUpdateSurfaceRules(registryAccess, true);
    }

    @Inject(at={@At(value="TAIL")}, remap=true, method={"Lnet/minecraft/world/level/storage/PrimaryLevelData;setTagData(Lnet/minecraft/core/RegistryAccess;Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/nbt/CompoundTag;)V"})
    private void citadel_postSetTagData(RegistryAccess registryAccess, CompoundTag compoundTag, CompoundTag compoundTag1, CallbackInfo ci) {
        this.citadelUpdateSurfaceRules(registryAccess, false);
    }

    @Unique
    private void citadelUpdateSurfaceRules(RegistryAccess registryAccess, boolean saving) {
        LevelStem levelstem;
        Object object;
        Registry registry = registryAccess.m_175515_(Registries.f_256862_);
        if (registry.m_142003_(LevelStem.f_63971_) && (object = (levelstem = (LevelStem)registry.m_6246_(LevelStem.f_63971_)).f_63976_()) instanceof NoiseBasedChunkGenerator) {
            NoiseBasedChunkGenerator noiseBasedChunkGenerator = (NoiseBasedChunkGenerator)object;
            if (noiseBasedChunkGenerator.f_64318_.m_203633_() && (object = noiseBasedChunkGenerator.f_64318_.get()) instanceof NoiseGeneratorSettingsAccessor) {
                NoiseGeneratorSettingsAccessor accessor = (NoiseGeneratorSettingsAccessor)object;
                accessor.onSaveData(saving);
            }
        }
    }
}
