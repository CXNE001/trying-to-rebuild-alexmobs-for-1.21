/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraftforge.client.extensions.common.IClientItemExtensions
 */
package com.github.alexthe666.citadel.item;

import com.github.alexthe666.citadel.Citadel;
import java.util.function.Consumer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class ItemCustomRender
extends Item {
    public ItemCustomRender(Item.Properties props) {
        super(props);
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions)Citadel.PROXY.getISTERProperties());
    }
}
