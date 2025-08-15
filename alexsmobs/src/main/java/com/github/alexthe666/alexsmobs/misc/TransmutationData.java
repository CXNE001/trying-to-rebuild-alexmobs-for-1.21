/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2DoubleMap
 *  it.unimi.dsi.fastutil.objects.Object2DoubleMap$Entry
 *  it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.world.item.ItemStack
 */
package com.github.alexthe666.alexsmobs.misc;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

public class TransmutationData {
    private final Object2DoubleMap<ItemStack> itemstackData = new Object2DoubleOpenHashMap();

    public void onTransmuteItem(ItemStack beingTransmuted, ItemStack turnedInto) {
        double fromWeight = this.getWeight(beingTransmuted);
        double toWeight = this.getWeight(turnedInto);
        this.putWeight(beingTransmuted, fromWeight + TransmutationData.calculateAddWeight(beingTransmuted.m_41613_()));
        this.putWeight(turnedInto, toWeight + TransmutationData.calculateRemoveWeight(turnedInto.m_41613_()));
    }

    public double getWeight(ItemStack stack) {
        for (Object2DoubleMap.Entry entry : this.itemstackData.object2DoubleEntrySet()) {
            if (!ItemStack.m_150942_((ItemStack)stack, (ItemStack)((ItemStack)entry.getKey()))) continue;
            return entry.getDoubleValue();
        }
        return 0.0;
    }

    private static double calculateAddWeight(int count) {
        return Math.log(Math.pow(count, AMConfig.transmutingWeightAddStep));
    }

    private static double calculateRemoveWeight(int count) {
        return -Math.log(Math.pow(count, AMConfig.transmutingWeightRemoveStep));
    }

    public void putWeight(ItemStack stack, double newWeight) {
        ItemStack replace = stack;
        for (ItemStack entry : this.itemstackData.keySet()) {
            if (!ItemStack.m_150942_((ItemStack)stack, (ItemStack)entry)) continue;
            replace = entry;
            break;
        }
        this.itemstackData.put((Object)replace, Math.max(newWeight, 0.0));
    }

    @Nullable
    public ItemStack getRandomItem(Random random) {
        ItemStack result = null;
        double bestValue = Double.MAX_VALUE;
        for (Object2DoubleMap.Entry entry : this.itemstackData.object2DoubleEntrySet()) {
            double value;
            if (entry.getDoubleValue() <= 0.0 || !((value = -Math.log(random.nextDouble()) / entry.getDoubleValue()) < bestValue)) continue;
            bestValue = value;
            result = ((ItemStack)entry.getKey()).m_41777_();
        }
        return result;
    }

    public CompoundTag saveAsNBT() {
        CompoundTag compound = new CompoundTag();
        ListTag listTag = new ListTag();
        for (Object2DoubleMap.Entry entry : this.itemstackData.object2DoubleEntrySet()) {
            CompoundTag tag = new CompoundTag();
            tag.m_128365_("Item", (Tag)((ItemStack)entry.getKey()).m_41739_(new CompoundTag()));
            tag.m_128347_("Weight", entry.getDoubleValue());
            listTag.add((Object)tag);
        }
        compound.m_128365_("TransmutationData", (Tag)listTag);
        return compound;
    }

    public static TransmutationData fromNBT(CompoundTag compound) {
        TransmutationData data = new TransmutationData();
        if (compound.m_128441_("TransmutationData")) {
            ListTag listtag = compound.m_128437_("TransmutationData", 10);
            for (int i = 0; i < listtag.size(); ++i) {
                CompoundTag innerTag = listtag.m_128728_(i);
                try {
                    ItemStack from = ItemStack.m_41712_((CompoundTag)innerTag.m_128469_("Item"));
                    if (from.m_41619_()) continue;
                    data.putWeight(from, innerTag.m_128459_("Weight"));
                    continue;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    public double getTotalWeight() {
        return this.itemstackData.values().doubleStream().sum();
    }
}
