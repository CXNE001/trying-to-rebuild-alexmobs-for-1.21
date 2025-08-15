/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.level.block.Block
 *  net.minecraftforge.client.extensions.common.IClientItemExtensions
 *  net.minecraftforge.registries.RegistryObject
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.item.AMBlockItem;
import java.util.function.Consumer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.RegistryObject;

public class BlockItemAMRender
extends AMBlockItem {
    public BlockItemAMRender(RegistryObject<Block> blockSupplier, Item.Properties props) {
        super(blockSupplier, props);
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions)AlexsMobs.PROXY.getISTERProperties());
    }
}
