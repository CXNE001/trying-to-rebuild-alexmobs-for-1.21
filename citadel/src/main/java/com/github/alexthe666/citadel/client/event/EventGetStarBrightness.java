/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.Event$HasResult
 */
package com.github.alexthe666.citadel.client.event;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.Event;

@OnlyIn(value=Dist.CLIENT)
@Event.HasResult
public class EventGetStarBrightness
extends Event {
    private final ClientLevel clientLevel;
    private float brightness;
    private final float partialTicks;

    public EventGetStarBrightness(ClientLevel clientLevel, float brightness, float partialTicks) {
        this.clientLevel = clientLevel;
        this.brightness = brightness;
        this.partialTicks = partialTicks;
    }

    public ClientLevel getLevel() {
        return this.clientLevel;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }

    public float getBrightness() {
        return this.brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }
}
