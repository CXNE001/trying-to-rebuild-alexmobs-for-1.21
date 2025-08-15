/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.util.random.WeightedRandomList
 *  net.minecraft.world.entity.MobCategory
 *  net.minecraft.world.level.StructureManager
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.biome.MobSpawnSettings$SpawnerData
 *  net.minecraft.world.level.chunk.ChunkGenerator
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.Event$Result
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package com.github.alexthe666.citadel.mixin;

import com.github.alexthe666.citadel.server.event.EventMergeStructureSpawns;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ChunkGenerator.class})
public class ChunkGeneratorMixin {
    @Inject(at={@At(value="RETURN")}, remap=true, cancellable=true, method={"Lnet/minecraft/world/level/chunk/ChunkGenerator;getMobsAt(Lnet/minecraft/core/Holder;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/entity/MobCategory;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/util/random/WeightedRandomList;"})
    private void citadel_getMobsAt(Holder<Biome> biome, StructureManager structureManager, MobCategory mobCategory, BlockPos pos, CallbackInfoReturnable<WeightedRandomList<MobSpawnSettings.SpawnerData>> cir) {
        WeightedRandomList biomeSpawns = ((Biome)biome.m_203334_()).m_47518_().m_151798_(mobCategory);
        if (biomeSpawns != cir.getReturnValue()) {
            EventMergeStructureSpawns event = new EventMergeStructureSpawns(structureManager, pos, mobCategory, (WeightedRandomList<MobSpawnSettings.SpawnerData>)((WeightedRandomList)cir.getReturnValue()), (WeightedRandomList<MobSpawnSettings.SpawnerData>)biomeSpawns);
            MinecraftForge.EVENT_BUS.post((Event)event);
            if (event.getResult() == Event.Result.ALLOW) {
                cir.setReturnValue(event.getStructureSpawns());
            }
        }
    }
}
