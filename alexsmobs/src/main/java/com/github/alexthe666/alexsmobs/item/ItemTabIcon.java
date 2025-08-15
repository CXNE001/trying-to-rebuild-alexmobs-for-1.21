/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.client.extensions.common.IClientItemExtensions
 *  net.minecraftforge.registries.ForgeRegistries
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.item.ItemInventoryOnly;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemTabIcon
extends ItemInventoryOnly {
    public ItemTabIcon(Item.Properties properties) {
        super(properties);
    }

    public static boolean hasCustomEntityDisplay(ItemStack stack) {
        return stack.m_41783_() != null && stack.m_41783_().m_128441_("DisplayEntityType");
    }

    public static String getCustomDisplayEntityString(ItemStack stack) {
        return stack.m_41783_().m_128461_("DisplayEntityType");
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions)AlexsMobs.PROXY.getISTERProperties());
    }

    @Nullable
    public static EntityType getEntityType(@Nullable CompoundTag tag) {
        if (tag != null && tag.m_128441_("DisplayEntityType")) {
            String entityType = tag.m_128461_("DisplayEntityType");
            return (EntityType)ForgeRegistries.ENTITY_TYPES.getValue(ResourceLocation.m_135820_((String)entityType));
        }
        return null;
    }
}
