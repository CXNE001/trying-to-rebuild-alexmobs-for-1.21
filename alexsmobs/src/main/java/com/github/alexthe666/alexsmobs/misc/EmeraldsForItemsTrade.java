/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.npc.VillagerTrades$ItemListing
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.trading.MerchantOffer
 *  net.minecraft.world.level.ItemLike
 */
package com.github.alexthe666.alexsmobs.misc;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;

public class EmeraldsForItemsTrade
implements VillagerTrades.ItemListing {
    private final Item tradeItem;
    private final int count;
    private final int maxUses;
    private final int xpValue;
    private final float priceMultiplier;

    public EmeraldsForItemsTrade(ItemLike p_i50539_1_, int p_i50539_2_, int p_i50539_3_, int p_i50539_4_) {
        this.tradeItem = p_i50539_1_.m_5456_();
        this.count = p_i50539_2_;
        this.maxUses = p_i50539_3_;
        this.xpValue = p_i50539_4_;
        this.priceMultiplier = 0.05f;
    }

    public MerchantOffer m_213663_(Entity p_221182_1_, RandomSource p_221182_2_) {
        ItemStack lvt_3_1_ = new ItemStack((ItemLike)this.tradeItem, 1);
        return new MerchantOffer(lvt_3_1_, new ItemStack((ItemLike)Items.f_42616_, this.count), this.maxUses, this.xpValue, this.priceMultiplier);
    }
}
