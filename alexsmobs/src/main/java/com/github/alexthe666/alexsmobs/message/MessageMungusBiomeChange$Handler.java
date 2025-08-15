/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.QuartPos
 *  net.minecraft.core.Registry
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.chunk.LevelChunk
 *  net.minecraft.world.level.chunk.LevelChunkSection
 *  net.minecraft.world.level.chunk.PalettedContainer
 *  net.minecraftforge.fml.LogicalSide
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.github.alexthe666.alexsmobs.message;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.EntityMungus;
import com.github.alexthe666.alexsmobs.message.MessageMungusBiomeChange;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.PalettedContainer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public static class MessageMungusBiomeChange.Handler {
    public static void handle(MessageMungusBiomeChange message, Supplier<NetworkEvent.Context> context) {
        context.get().setPacketHandled(true);
        context.get().enqueueWork(() -> {
            ServerPlayer player = ((NetworkEvent.Context)context.get()).getSender();
            if (((NetworkEvent.Context)context.get()).getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                player = AlexsMobs.PROXY.getClientSidePlayer();
            }
            if (player != null && player.m_9236_() != null) {
                Entity entity = player.m_9236_().m_6815_(message.mungusID);
                Registry registry = player.m_9236_().m_9598_().m_175515_(Registries.f_256952_);
                Biome biome = (Biome)registry.m_7745_(new ResourceLocation(message.biomeOption));
                ResourceKey resourceKey = registry.m_7854_((Object)biome).orElse(null);
                Holder holder = registry.m_203636_(resourceKey).orElse(null);
                if (AMConfig.mungusBiomeTransformationType == 2 && entity instanceof EntityMungus && entity.m_20275_((double)message.posX, entity.m_20186_(), (double)message.posZ) < 1000.0 && biome != null) {
                    LevelChunk chunk = player.m_9236_().m_46745_(new BlockPos(message.posX, 0, message.posZ));
                    int i = QuartPos.m_175400_((int)chunk.m_141937_());
                    int k = i + QuartPos.m_175400_((int)chunk.m_141928_()) - 1;
                    int l = Mth.m_14045_((int)QuartPos.m_175400_((int)((int)entity.m_20186_())), (int)i, (int)k);
                    int j = chunk.m_151564_(QuartPos.m_175402_((int)l));
                    LevelChunkSection section = chunk.m_183278_(j);
                    if (section != null) {
                        PalettedContainer container = section.m_187996_().m_238334_();
                        for (int biomeX = 0; biomeX < 4; ++biomeX) {
                            for (int biomeY = 0; biomeY < 4; ++biomeY) {
                                for (int biomeZ = 0; biomeZ < 4; ++biomeZ) {
                                    container.m_63127_(biomeX, biomeY, biomeZ, (Object)holder);
                                }
                            }
                        }
                        section.f_187995_ = container;
                    }
                    AlexsMobs.PROXY.updateBiomeVisuals(message.posX, message.posZ);
                }
            }
        });
    }
}
