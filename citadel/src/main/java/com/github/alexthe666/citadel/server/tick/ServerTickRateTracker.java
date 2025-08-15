/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package com.github.alexthe666.citadel.server.tick;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.message.SyncClientTickRateMessage;
import com.github.alexthe666.citadel.server.tick.TickRateTracker;
import com.github.alexthe666.citadel.server.tick.modifier.TickRateModifier;
import com.github.alexthe666.citadel.server.tick.modifier.TickRateModifierType;
import com.github.alexthe666.citadel.server.world.CitadelServerData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerTickRateTracker
extends TickRateTracker {
    public static final Logger LOGGER = LogManager.getLogger((String)"citadel-server-tick");
    public MinecraftServer server;

    public ServerTickRateTracker(MinecraftServer server) {
        this.server = server;
    }

    public ServerTickRateTracker(MinecraftServer server, CompoundTag tag) {
        this(server);
        this.fromTag(tag);
    }

    public void addTickRateModifier(TickRateModifier modifier) {
        this.tickRateModifierList.add(modifier);
        this.sync();
    }

    @Override
    public void tickEntityAtCustomRate(Entity entity) {
        if (!entity.m_9236_().f_46443_ && entity.m_9236_() instanceof ServerLevel) {
            ((ServerLevel)entity.m_9236_()).m_8647_(entity);
        }
    }

    @Override
    protected void sync() {
        Citadel.sendMSGToAll(new SyncClientTickRateMessage(this.toTag()));
    }

    public int getServerTickLengthMs() {
        int i = 50;
        for (TickRateModifier modifier : this.tickRateModifierList) {
            if (modifier.getType() != TickRateModifierType.GLOBAL) continue;
            i = (int)((float)i * modifier.getTickRateMultiplier());
        }
        if (i <= 0) {
            return 1;
        }
        return i;
    }

    public static ServerTickRateTracker getForServer(MinecraftServer server) {
        return CitadelServerData.get(server).getOrCreateTickRateTracker();
    }

    public static void modifyTickRate(Level level, TickRateModifier modifier) {
        if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            ServerTickRateTracker.getForServer(serverLevel.m_7654_()).addTickRateModifier(modifier);
        }
    }
}
