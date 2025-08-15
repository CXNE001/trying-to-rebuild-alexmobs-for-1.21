/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.resources.sounds.SoundInstance
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package com.github.alexthe666.citadel.client.tick;

import com.github.alexthe666.citadel.server.tick.TickRateTracker;
import com.github.alexthe666.citadel.server.tick.modifier.TickRateModifier;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientTickRateTracker
extends TickRateTracker {
    public static final Logger LOGGER = LogManager.getLogger((String)"citadel-client-tick");
    private static final Map<Minecraft, ClientTickRateTracker> dataMap = new HashMap<Minecraft, ClientTickRateTracker>();
    public Minecraft client;
    private static final float MS_PER_TICK = 50.0f;

    public ClientTickRateTracker(Minecraft client) {
        this.client = client;
    }

    public void syncFromServer(CompoundTag tag) {
        this.tickRateModifierList.clear();
        this.fromTag(tag);
    }

    public static ClientTickRateTracker getForClient(Minecraft minecraft) {
        if (!dataMap.containsKey(minecraft)) {
            ClientTickRateTracker tracker = new ClientTickRateTracker(minecraft);
            dataMap.put(minecraft, tracker);
            return tracker;
        }
        return dataMap.get(minecraft);
    }

    @Override
    public void masterTick() {
        super.masterTick();
        this.client.f_90991_.f_92521_ = this.getClientTickRate();
    }

    public float getClientTickRate() {
        float f = 50.0f;
        if (Minecraft.m_91087_().f_91073_ == null || Minecraft.m_91087_().f_91074_ == null) {
            return f;
        }
        for (TickRateModifier modifier : this.tickRateModifierList) {
            if (!modifier.appliesTo((Level)Minecraft.m_91087_().f_91073_, Minecraft.m_91087_().f_91074_.m_20185_(), Minecraft.m_91087_().f_91074_.m_20186_(), Minecraft.m_91087_().f_91074_.m_20189_())) continue;
            f *= modifier.getTickRateMultiplier();
        }
        return Math.max(1.0f, f * this.getEntityTickLengthModifier((Entity)Minecraft.m_91087_().f_91074_));
    }

    public float modifySoundPitch(SoundInstance soundInstance) {
        float f = 1.0f;
        if (Minecraft.m_91087_().f_91073_ == null || Minecraft.m_91087_().f_91074_ == null) {
            return f;
        }
        for (TickRateModifier modifier : this.tickRateModifierList) {
            if (!modifier.appliesTo((Level)Minecraft.m_91087_().f_91073_, Minecraft.m_91087_().f_91074_.m_20185_(), Minecraft.m_91087_().f_91074_.m_20186_(), Minecraft.m_91087_().f_91074_.m_20189_())) continue;
            f /= modifier.getTickRateMultiplier();
        }
        return Math.max(1.0f, f * this.getEntityTickLengthModifier((Entity)Minecraft.m_91087_().f_91074_));
    }

    @Override
    public void tickEntityAtCustomRate(Entity entity) {
        if (entity.m_9236_().f_46443_ && entity.m_9236_() instanceof ClientLevel) {
            ((ClientLevel)entity.m_9236_()).m_104639_(entity);
        }
    }
}
