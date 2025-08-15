/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.ThrowableItemProjectile
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.gameevent.GameEvent
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.entity.EntityCockroachEgg;
import com.github.alexthe666.alexsmobs.entity.EntityEmuEgg;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import java.util.Random;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class ItemAnimalEgg
extends Item {
    private final Random random = new Random();

    public ItemAnimalEgg(Item.Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.m_21120_(handIn);
        playerIn.m_146850_(GameEvent.f_223698_);
        worldIn.m_6263_((Player)null, playerIn.m_20185_(), playerIn.m_20186_(), playerIn.m_20189_(), SoundEvents.f_11877_, SoundSource.PLAYERS, 0.5f, 0.4f / (this.random.nextFloat() * 0.4f + 0.8f));
        if (!worldIn.f_46443_) {
            ThrowableItemProjectile eggentity = this == AMItemRegistry.EMU_EGG.get() ? new EntityEmuEgg(worldIn, (LivingEntity)playerIn) : new EntityCockroachEgg(worldIn, (LivingEntity)playerIn);
            eggentity.m_37446_(itemstack);
            eggentity.m_37251_((Entity)playerIn, playerIn.m_146909_(), playerIn.m_146908_(), 0.0f, 1.5f, 1.0f);
            worldIn.m_7967_((Entity)eggentity);
        }
        playerIn.m_36246_(Stats.f_12982_.m_12902_((Object)this));
        if (!playerIn.m_150110_().f_35937_) {
            itemstack.m_41774_(1);
        }
        return InteractionResultHolder.m_19092_((Object)itemstack, (boolean)worldIn.m_5776_());
    }
}
