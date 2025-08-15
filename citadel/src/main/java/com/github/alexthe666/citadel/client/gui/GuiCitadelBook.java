/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.citadel.client.gui;

import com.github.alexthe666.citadel.client.gui.GuiBasicBook;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
public class GuiCitadelBook
extends GuiBasicBook {
    public GuiCitadelBook(ItemStack bookStack) {
        super(bookStack, (Component)Component.m_237115_((String)"citadel_guide_book.title"));
    }

    @Override
    protected int getBindingColor() {
        return 6595195;
    }

    @Override
    public ResourceLocation getRootPage() {
        return new ResourceLocation("citadel:book/citadel_book/root.json");
    }

    @Override
    public String getTextFileDirectory() {
        return "citadel:book/citadel_book/";
    }
}
