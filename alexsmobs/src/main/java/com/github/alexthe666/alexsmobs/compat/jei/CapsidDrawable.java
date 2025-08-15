/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mezz.jei.api.gui.drawable.IDrawable
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.resources.ResourceLocation
 */
package com.github.alexthe666.alexsmobs.compat.jei;

import mezz.jei.api.gui.drawable.IDrawable;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class CapsidDrawable
implements IDrawable {
    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs", "textures/gui/capsid_jei_representation.png");

    public int getWidth() {
        return 125;
    }

    public int getHeight() {
        return 59;
    }

    public void draw(GuiGraphics guiGraphics, int xOffset, int yOffset) {
        int i = xOffset;
        int j = yOffset;
        guiGraphics.m_280163_(TEXTURE, i, j, 0.0f, 0.0f, 125, 59, 256, 256);
    }
}
