/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.TagParser
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraftforge.registries.ForgeRegistries
 */
package com.github.alexthe666.citadel.client.gui.data;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

public class LinkData {
    private String linked_page;
    private String text;
    private int x;
    private int y;
    private final int page;
    private String item = null;
    private String item_tag = null;

    public LinkData(String linkedPage, String titleText, int x, int y, int page) {
        this(linkedPage, titleText, x, y, page, null, null);
    }

    public LinkData(String linkedPage, String titleText, int x, int y, int page, String item, String itemTag) {
        this.linked_page = linkedPage;
        this.text = titleText;
        this.x = x;
        this.y = y;
        this.page = page;
        this.item = item;
        this.item_tag = itemTag;
    }

    public String getLinkedPage() {
        return this.linked_page;
    }

    public void setLinkedPage(String linkedPage) {
        this.linked_page = linkedPage;
    }

    public String getTitleText() {
        return this.text;
    }

    public void setTitleText(String titleText) {
        this.text = titleText;
    }

    public int getPage() {
        return this.page;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ItemStack getDisplayItem() {
        if (this.item == null || this.item.isEmpty()) {
            return ItemStack.f_41583_;
        }
        ItemStack stack = new ItemStack((ItemLike)ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.item)));
        if (this.item_tag != null && !this.item_tag.isEmpty()) {
            CompoundTag tag = null;
            try {
                tag = TagParser.m_129359_((String)this.item_tag);
            }
            catch (CommandSyntaxException e) {
                e.printStackTrace();
            }
            stack.m_41751_(tag);
        }
        return stack;
    }
}
