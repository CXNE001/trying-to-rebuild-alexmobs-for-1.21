/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.registries.ForgeRegistries
 */
package com.github.alexthe666.citadel.server.block;

import com.github.alexthe666.citadel.Citadel;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class LecternBooks {
    public static Map<ResourceLocation, BookData> BOOKS = new HashMap<ResourceLocation, BookData>();

    public static void init() {
        BOOKS.put(Citadel.CITADEL_BOOK.getId(), new BookData(6595195, 0xD6D6D6));
    }

    public static boolean isLecternBook(ItemStack stack) {
        return BOOKS.containsKey(ForgeRegistries.ITEMS.getKey((Object)stack.m_41720_()));
    }

    public static class BookData {
        int bindingColor;
        int pageColor;

        public BookData(int bindingColor, int pageColor) {
            this.bindingColor = bindingColor;
            this.pageColor = pageColor;
        }

        public int getBindingColor() {
            return this.bindingColor;
        }

        public int getPageColor() {
            return this.pageColor;
        }
    }
}
