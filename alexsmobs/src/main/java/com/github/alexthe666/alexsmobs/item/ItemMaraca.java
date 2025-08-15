/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.gameevent.GameEvent
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import java.util.Random;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class ItemMaraca
extends Item {
    private final Random random = new Random();

    public ItemMaraca(Item.Properties property) {
        super(property);
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.m_21120_(handIn);
        playerIn.m_146850_(GameEvent.f_223698_);
        worldIn.m_6263_(null, playerIn.m_20185_(), playerIn.m_20186_(), playerIn.m_20189_(), (SoundEvent)AMSoundRegistry.MARACA.get(), SoundSource.PLAYERS, 0.5f, this.random.nextFloat() * 0.4f + 0.8f);
        playerIn.m_36335_().m_41524_((Item)this, 3);
        playerIn.m_36246_(Stats.f_12982_.m_12902_((Object)this));
        return InteractionResultHolder.m_19090_((Object)itemstack);
    }
}
