/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.Button$OnPress
 *  net.minecraft.client.resources.sounds.SimpleSoundInstance
 *  net.minecraft.client.resources.sounds.SoundInstance
 *  net.minecraft.client.sounds.SoundManager
 *  net.minecraft.network.chat.CommonComponents
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.citadel.client.gui;

import com.github.alexthe666.citadel.client.gui.BookBlit;
import com.github.alexthe666.citadel.client.gui.GuiBasicBook;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
public class BookPageButton
extends Button {
    private final boolean isForward;
    private final boolean playTurnSound;
    private final GuiBasicBook bookGUI;

    public BookPageButton(GuiBasicBook bookGUI, int p_i51079_1_, int p_i51079_2_, boolean p_i51079_3_, Button.OnPress p_i51079_4_, boolean p_i51079_5_) {
        super(p_i51079_1_, p_i51079_2_, 23, 13, CommonComponents.f_237098_, p_i51079_4_, f_252438_);
        this.isForward = p_i51079_3_;
        this.playTurnSound = p_i51079_5_;
        this.bookGUI = bookGUI;
    }

    public void m_87963_(GuiGraphics p_230431_1_, int p_230431_2_, int p_230431_3_, float p_230431_4_) {
        int lvt_5_1_ = 0;
        int lvt_6_1_ = 0;
        if (this.f_93622_) {
            lvt_5_1_ += 23;
        }
        if (!this.isForward) {
            lvt_6_1_ += 13;
        }
        this.drawNextArrow(p_230431_1_, this.m_252754_(), this.m_252907_(), lvt_5_1_, lvt_6_1_, 18, 12);
    }

    public void drawNextArrow(GuiGraphics p_238474_1_, int p_238474_2_, int p_238474_3_, int p_238474_4_, int p_238474_5_, int p_238474_6_, int p_238474_7_) {
        if (this.f_93622_) {
            int color = this.bookGUI.getWidgetColor();
            int r = (color & 0xFF0000) >> 16;
            int g = (color & 0xFF00) >> 8;
            int b = color & 0xFF;
            BookBlit.blitWithColor(p_238474_1_, this.bookGUI.getBookWidgetTexture(), p_238474_2_, p_238474_3_, 100, (float)p_238474_4_, (float)p_238474_5_, p_238474_6_, p_238474_7_, 256, 256, r, g, b, 255);
        } else {
            BookBlit.blitWithColor(p_238474_1_, this.bookGUI.getBookWidgetTexture(), p_238474_2_, p_238474_3_, 100, (float)p_238474_4_, (float)p_238474_5_, p_238474_6_, p_238474_7_, 256, 256, 255, 255, 255, 255);
        }
    }

    public void m_7435_(SoundManager p_230988_1_) {
        if (this.playTurnSound) {
            p_230988_1_.m_120367_((SoundInstance)SimpleSoundInstance.m_119752_((SoundEvent)SoundEvents.f_11713_, (float)1.0f));
        }
    }
}
