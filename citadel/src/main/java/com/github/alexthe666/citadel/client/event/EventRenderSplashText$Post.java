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
public static class EventRenderSplashText.Post
extends EventRenderSplashText {
    public EventRenderSplashText.Post(String splashText, GuiGraphics guiGraphics, float partialTicks) {
        super(splashText, guiGraphics, partialTicks);
    }
}
