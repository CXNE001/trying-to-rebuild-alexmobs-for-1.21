/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraftforge.eventbus.api.Event$HasResult
 */
package com.github.alexthe666.citadel.client.event;

import com.github.alexthe666.citadel.client.event.EventRenderSplashText;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.eventbus.api.Event;

@Event.HasResult
public static class EventRenderSplashText.Pre
extends EventRenderSplashText {
    private int splashTextColor;

    public EventRenderSplashText.Pre(String splashText, GuiGraphics guiGraphics, float partialTicks, int splashTextColor) {
        super(splashText, guiGraphics, partialTicks);
        this.splashTextColor = splashTextColor;
    }

    public int getSplashTextColor() {
        return this.splashTextColor;
    }

    public void setSplashTextColor(int splashTextColor) {
        this.splashTextColor = splashTextColor;
    }
}
