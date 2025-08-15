/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Font
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.Button$OnPress
 *  net.minecraft.client.renderer.GameRenderer
 *  net.minecraft.client.renderer.entity.ItemRenderer
 *  net.minecraft.client.resources.sounds.SimpleSoundInstance
 *  net.minecraft.client.resources.sounds.SoundInstance
 *  net.minecraft.client.sounds.SoundManager
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.FormattedCharSequence
 *  net.minecraft.util.Mth
 *  net.minecraft.world.item.ItemStack
 */
package com.github.alexthe666.citadel.client.gui;

import com.github.alexthe666.citadel.client.gui.BookBlit;
import com.github.alexthe666.citadel.client.gui.GuiBasicBook;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

public class LinkButton
extends Button {
    public ItemStack previewStack;
    public GuiBasicBook book;

    public LinkButton(GuiBasicBook book, int x, int y, int width, int height, Component component, ItemStack previewStack, Button.OnPress onPress) {
        super(x, y, width + (previewStack.m_41619_() ? 0 : 6), height, component, onPress, Button.f_252438_);
        this.previewStack = previewStack;
        this.book = book;
    }

    public LinkButton(GuiBasicBook book, int x, int y, int width, int height, Component component, Button.OnPress onPress) {
        this(book, x, y, width, height, component, ItemStack.f_41583_, onPress);
    }

    public int getFGColor() {
        return this.f_93622_ ? this.book.getWidgetColor() : (this.f_93623_ ? 9729114 : 0xA0A0A0);
    }

    private int getTextureY() {
        int i = 1;
        if (!this.f_93623_) {
            i = 0;
        } else if (this.m_198029_()) {
            i = 2;
        }
        return 46 + i * 20;
    }

    public void m_87963_(GuiGraphics guiGraphics, int guiX, int guiY, float partialTicks) {
        int itemTextOffset;
        Minecraft minecraft = Minecraft.m_91087_();
        Font font = minecraft.f_91062_;
        RenderSystem.setShader(GameRenderer::m_172817_);
        RenderSystem.setShaderTexture((int)0, (ResourceLocation)this.book.getBookButtonsTexture());
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)this.f_93625_);
        int i = this.getTextureY();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        guiGraphics.m_280218_(this.book.getBookButtonsTexture(), this.m_252754_(), this.m_252907_(), 0, 46 + i * 20, this.f_93618_ / 2, this.f_93619_);
        guiGraphics.m_280218_(this.book.getBookButtonsTexture(), this.m_252754_() + this.f_93618_ / 2, this.m_252907_(), 200 - this.f_93618_ / 2, 46 + i * 20, this.f_93618_ / 2, this.f_93619_);
        if (this.f_93622_) {
            int color = this.book.getWidgetColor();
            int r = (color & 0xFF0000) >> 16;
            int g = (color & 0xFF00) >> 8;
            int b = color & 0xFF;
            i = 3;
            BookBlit.blitWithColor(guiGraphics, this.book.getBookButtonsTexture(), this.m_252754_(), this.m_252907_(), 0.0f, 46 + i * 20, this.f_93618_ / 2, this.f_93619_, 256, 256, r, g, b, 255);
            BookBlit.blitWithColor(guiGraphics, this.book.getBookButtonsTexture(), this.m_252754_() + this.f_93618_ / 2, this.m_252907_(), 200 - this.f_93618_ / 2, 46 + i * 20, this.f_93618_ / 2, this.f_93619_, 256, 256, r, g, b, 255);
        }
        int j = this.getFGColor();
        int n = itemTextOffset = this.previewStack.m_41619_() ? 0 : 8;
        if (!this.previewStack.m_41619_()) {
            ItemRenderer itemRenderer = Minecraft.m_91087_().m_91291_();
            guiGraphics.m_280480_(this.previewStack, this.m_252754_() + 2, this.m_252907_() + 1);
        }
        LinkButton.drawTextOf(guiGraphics, font, this.m_6035_(), this.m_252754_() + itemTextOffset + this.f_93618_ / 2, this.m_252907_() + (this.f_93619_ - 8) / 2, j | Mth.m_14167_((float)(this.f_93625_ * 255.0f)) << 24);
    }

    public static void drawTextOf(GuiGraphics guiGraphics, Font font, Component component, int x, int y, int color) {
        FormattedCharSequence formattedcharsequence = component.m_7532_();
        guiGraphics.drawString(font, formattedcharsequence, (float)(x - font.m_92724_(formattedcharsequence) / 2), (float)y, color, false);
    }

    public void m_7435_(SoundManager soundManager) {
        soundManager.m_120367_((SoundInstance)SimpleSoundInstance.m_119752_((SoundEvent)SoundEvents.f_11713_, (float)1.0f));
    }
}
