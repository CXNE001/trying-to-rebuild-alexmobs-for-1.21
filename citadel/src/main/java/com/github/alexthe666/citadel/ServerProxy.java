/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.event.TickEvent$ServerTickEvent
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.EventPriority
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 *  org.jetbrains.annotations.Nullable
 */
package com.github.alexthe666.citadel;

import com.github.alexthe666.citadel.server.entity.IDancesToJukebox;
import com.github.alexthe666.citadel.server.event.EventChangeEntityTickRate;
import com.github.alexthe666.citadel.server.tick.ServerTickRateTracker;
import com.github.alexthe666.citadel.server.world.CitadelServerData;
import com.github.alexthe666.citadel.server.world.ModifiableTickRateServer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ServerProxy {
    private static MinecraftServer minecraftServer;

    public void onPreInit() {
    }

    public void handleAnimationPacket(int entityId, int index) {
    }

    public void handlePropertiesPacket(String propertyID, CompoundTag compound, int entityID) {
    }

    public void handleClientTickRatePacket(CompoundTag compound) {
    }

    public void handleJukeboxPacket(Level level, int entityId, BlockPos jukeBox, boolean dancing) {
        Entity entity = level.m_6815_(entityId);
        if (entity instanceof IDancesToJukebox) {
            IDancesToJukebox dancer = (IDancesToJukebox)entity;
            dancer.setDancing(dancing);
            dancer.setJukeboxPos((BlockPos)(dancing ? jukeBox : null));
        }
    }

    public void openBookGUI(ItemStack book) {
    }

    public Object getISTERProperties() {
        return null;
    }

    public void onClientInit() {
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.START && event.getServer().m_130010_()) {
            ServerTickRateTracker tickRateTracker = CitadelServerData.get(event.getServer()).getOrCreateTickRateTracker();
            MinecraftServer minecraftServer = event.getServer();
            if (minecraftServer instanceof ModifiableTickRateServer) {
                ModifiableTickRateServer modifiableServer = (ModifiableTickRateServer)minecraftServer;
                long l = tickRateTracker.getServerTickLengthMs();
                if (l == 50L) {
                    modifiableServer.resetGlobalTickLengthMs();
                } else {
                    modifiableServer.setGlobalTickLengthMs(tickRateTracker.getServerTickLengthMs());
                }
                if (!event.getServer().m_129782_()) {
                    tickRateTracker.masterTick();
                }
            }
        }
    }

    public boolean canEntityTickClient(Level level, Entity entity) {
        return true;
    }

    public boolean canEntityTickServer(Level level, Entity entity) {
        if (level instanceof ServerLevel) {
            ServerTickRateTracker tracker = ServerTickRateTracker.getForServer(((ServerLevel)level).m_7654_());
            if (tracker.isTickingHandled(entity)) {
                return false;
            }
            if (!tracker.hasNormalTickRate(entity)) {
                EventChangeEntityTickRate event = new EventChangeEntityTickRate(entity, tracker.getEntityTickLengthModifier(entity));
                MinecraftForge.EVENT_BUS.post((Event)event);
                if (event.isCanceled()) {
                    return true;
                }
                tracker.addTickBlockedEntity(entity);
                return false;
            }
        }
        return true;
    }

    public boolean isGamePaused() {
        return false;
    }

    public float getMouseOverProgress(ItemStack itemStack) {
        return 0.0f;
    }

    public Player getClientSidePlayer() {
        return null;
    }

    @Nullable
    public MinecraftServer getMinecraftServer() {
        return minecraftServer;
    }

    public static void setMinecraftServer(MinecraftServer server) {
        minecraftServer = server;
    }
}
