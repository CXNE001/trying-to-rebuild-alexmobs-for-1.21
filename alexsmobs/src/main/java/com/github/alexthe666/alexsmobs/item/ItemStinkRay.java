/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.UseAnim
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.client.extensions.common.IClientItemExtensions
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.entity.EntityFart;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import java.util.function.Consumer;
import java.util.function.Predicate;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class ItemStinkRay
extends Item {
    public static final Predicate<ItemStack> IS_FART_BOTTLE = stack -> stack.m_41720_() == AMItemRegistry.STINK_BOTTLE.get();

    public ItemStinkRay(Item.Properties properties) {
        super(properties);
    }

    public int m_8105_(ItemStack stack) {
        return ItemStinkRay.isUsable(stack) ? 72000 : 0;
    }

    public UseAnim m_6164_(ItemStack stack) {
        return UseAnim.BOW;
    }

    public static boolean isUsable(ItemStack stack) {
        return stack.m_41773_() < stack.m_41776_() - 1;
    }

    public boolean m_142522_(ItemStack itemStack) {
        return super.m_142522_(itemStack) && ItemStinkRay.isUsable(itemStack);
    }

    public static float getPowerForTime(int i) {
        float f = (float)i / 20.0f;
        if ((f = (f * f + f * 2.0f) / 3.0f) > 1.0f) {
            f = 1.0f;
        }
        return f;
    }

    public void m_5551_(ItemStack itemStack, Level level, LivingEntity entity, int time) {
        if (entity instanceof Player) {
            int i;
            Player player = (Player)entity;
            if (ItemStinkRay.isUsable(itemStack) && (i = this.m_8105_(itemStack) - time) >= 10) {
                boolean left = false;
                if (entity.m_7655_() == InteractionHand.OFF_HAND && entity.m_5737_() == HumanoidArm.RIGHT || entity.m_7655_() == InteractionHand.MAIN_HAND && entity.m_5737_() == HumanoidArm.LEFT) {
                    left = true;
                }
                EntityFart blood = new EntityFart(level, entity, !left);
                Vec3 vector3d = entity.m_20252_(1.0f);
                RandomSource rand = level.m_213780_();
                entity.m_146850_(GameEvent.f_223698_);
                entity.m_5496_((SoundEvent)AMSoundRegistry.STINK_RAY.get(), 1.0f, 0.9f + (rand.m_188501_() - rand.m_188501_()) * 0.2f);
                blood.shoot(vector3d.m_7096_(), vector3d.m_7098_(), vector3d.m_7094_(), 0.2f + ItemStinkRay.getPowerForTime(i) * 0.4f, 10.0f);
                if (!level.f_46443_) {
                    level.m_7967_((Entity)blood);
                }
                itemStack.m_41622_(1, entity, breaker -> breaker.m_21190_(entity.m_7655_()));
            }
        }
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.m_21120_(handIn);
        playerIn.m_6672_(handIn);
        if (!ItemStinkRay.isUsable(itemstack)) {
            ItemStack ammo = this.findAmmo(playerIn);
            boolean flag = playerIn.m_7500_();
            if (!ammo.m_41619_()) {
                ammo.m_41774_(1);
                ItemStack bottle = new ItemStack((ItemLike)Items.f_42590_);
                if (!playerIn.m_36356_(bottle)) {
                    playerIn.m_36176_(bottle, false);
                }
                flag = true;
            }
            if (flag) {
                itemstack.m_41721_(0);
            }
        }
        return InteractionResultHolder.m_19096_((Object)itemstack);
    }

    public ItemStack findAmmo(Player entity) {
        if (entity.m_7500_()) {
            return new ItemStack((ItemLike)AMItemRegistry.STINK_BOTTLE.get());
        }
        for (int i = 0; i < entity.m_150109_().m_6643_(); ++i) {
            ItemStack itemstack1 = entity.m_150109_().m_8020_(i);
            if (!IS_FART_BOTTLE.test(itemstack1)) continue;
            return itemstack1;
        }
        return ItemStack.f_41583_;
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return !ItemStack.m_41656_((ItemStack)oldStack, (ItemStack)newStack);
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions)AlexsMobs.PROXY.getISTERProperties());
    }
}
