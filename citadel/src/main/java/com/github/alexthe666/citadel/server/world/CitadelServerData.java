/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.saveddata.SavedData
 *  net.minecraft.world.level.storage.DimensionDataStorage
 */
package com.github.alexthe666.citadel.server.world;

import com.github.alexthe666.citadel.server.tick.ServerTickRateTracker;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

public class CitadelServerData
extends SavedData {
    private static final Map<MinecraftServer, CitadelServerData> dataMap = new HashMap<MinecraftServer, CitadelServerData>();
    private static final String IDENTIFIER = "citadel_world_data";
    private final MinecraftServer server;
    private ServerTickRateTracker tickRateTracker = null;

    public CitadelServerData(MinecraftServer server) {
        this.server = server;
    }

    public CitadelServerData(MinecraftServer server, CompoundTag tag) {
        this(server);
        this.tickRateTracker = tag.m_128441_("TickRateTracker") ? new ServerTickRateTracker(server, tag.m_128469_("TickRateTracker")) : new ServerTickRateTracker(server);
    }

    public static CitadelServerData get(MinecraftServer server) {
        CitadelServerData fromMap = dataMap.get(server);
        if (fromMap == null) {
            DimensionDataStorage storage = server.m_129880_(Level.f_46428_).m_8895_();
            CitadelServerData data = (CitadelServerData)storage.m_164861_(tag -> new CitadelServerData(server, (CompoundTag)tag), () -> new CitadelServerData(server), IDENTIFIER);
            if (data != null) {
                data.m_77762_();
            }
            dataMap.put(server, data);
            return data;
        }
        return fromMap;
    }

    public CompoundTag m_7176_(CompoundTag tag) {
        if (this.tickRateTracker != null) {
            tag.m_128365_("TickRateTracker", (Tag)this.tickRateTracker.toTag());
        }
        return tag;
    }

    public ServerTickRateTracker getOrCreateTickRateTracker() {
        if (this.tickRateTracker == null) {
            this.tickRateTracker = new ServerTickRateTracker(this.server);
        }
        return this.tickRateTracker;
    }
}
