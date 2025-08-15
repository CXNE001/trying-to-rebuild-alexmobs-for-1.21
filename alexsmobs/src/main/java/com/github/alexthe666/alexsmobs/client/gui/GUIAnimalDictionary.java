/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.client.gui.GuiBasicBook
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.github.alexthe666.alexsmobs.client.gui;

import com.github.alexthe666.alexsmobs.client.render.RenderLaviathan;
import com.github.alexthe666.alexsmobs.client.render.RenderMurmurBody;
import com.github.alexthe666.alexsmobs.client.render.RenderUnderminer;
import com.github.alexthe666.citadel.client.gui.GuiBasicBook;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
public class GUIAnimalDictionary
extends GuiBasicBook {
    private static final ResourceLocation ROOT = new ResourceLocation("alexsmobs:book/animal_dictionary/root.json");

    public GUIAnimalDictionary(ItemStack bookStack) {
        super(bookStack, (Component)Component.m_237115_((String)"animal_dictionary.title"));
    }

    public GUIAnimalDictionary(ItemStack bookStack, String page) {
        super(bookStack, (Component)Component.m_237115_((String)"animal_dictionary.title"));
        this.currentPageJSON = new ResourceLocation(this.getTextFileDirectory() + page + ".json");
    }

    public void m_88315_(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
        RenderLaviathan.renderWithoutShaking = true;
        RenderMurmurBody.renderWithHead = true;
        RenderUnderminer.renderWithPickaxe = true;
        super.m_88315_(guiGraphics, x, y, partialTicks);
        RenderLaviathan.renderWithoutShaking = false;
        RenderMurmurBody.renderWithHead = false;
        RenderUnderminer.renderWithPickaxe = false;
    }

    protected int getBindingColor() {
        return 6318886;
    }

    public ResourceLocation getRootPage() {
        return ROOT;
    }

    public String getTextFileDirectory() {
        return "alexsmobs:book/animal_dictionary/";
    }
}
