/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraftforge.client.gui.widget.ForgeSlider
 */
package com.github.alexthe666.citadel.client.gui;

import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.widget.ForgeSlider;

class GuiCitadelPatreonConfig.3
extends ForgeSlider {
    GuiCitadelPatreonConfig.3(int x, int y, int width, int height, Component prefix, Component suffix, double minValue, double maxValue, double currentValue, double stepSize, int precision, boolean drawString) {
        super(x, y, width, height, prefix, suffix, minValue, maxValue, currentValue, stepSize, precision, drawString);
    }

    protected void m_5697_() {
        GuiCitadelPatreonConfig.this.setSliderValue(2, (float)this.getValue());
    }
}
