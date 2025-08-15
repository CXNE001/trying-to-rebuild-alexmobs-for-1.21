/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvents
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
 *  net.minecraft.world.item.UseAnim
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.entity.EntityHemolymph;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import java.util.function.Predicate;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class ItemHemolymphBlaster
extends Item {
    public static final Predicate<ItemStack> HEMOLYMPH = stack -> stack.m_41720_() == AMItemRegistry.HEMOLYMPH_SAC.get();

    public ItemHemolymphBlaster(Item.Properties properties) {
        super(properties);
    }

    public int m_8105_(ItemStack stack) {
        return ItemHemolymphBlaster.isUsable(stack) ? Integer.MAX_VALUE : 0;
    }

    public boolean m_142522_(ItemStack itemStack) {
        return super.m_142522_(itemStack) && ItemHemolymphBlaster.isUsable(itemStack);
    }

    public UseAnim m_6164_(ItemStack stack) {
        return UseAnim.BOW;
    }

    public static boolean isUsable(ItemStack stack) {
        return stack.m_41773_() < stack.m_41776_() - 1;
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.m_21120_(handIn);
        playerIn.m_6672_(handIn);
        if (!ItemHemolymphBlaster.isUsable(itemstack)) {
            ItemStack ammo = this.findAmmo(playerIn);
            boolean flag = playerIn.m_7500_();
            if (!ammo.m_41619_()) {
                ammo.m_41774_(1);
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
            return ItemStack.f_41583_;
        }
        for (int i = 0; i < entity.m_150109_().m_6643_(); ++i) {
            ItemStack itemstack1 = entity.m_150109_().m_8020_(i);
            if (!HEMOLYMPH.test(itemstack1)) continue;
            return itemstack1;
        }
        return ItemStack.f_41583_;
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return !ItemStack.m_41656_((ItemStack)oldStack, (ItemStack)newStack);
    }

    public void m_5929_(Level worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        if (ItemHemolymphBlaster.isUsable(stack)) {
            if (count % 2 == 0) {
                boolean left = false;
                if (livingEntityIn.m_7655_() == InteractionHand.OFF_HAND && livingEntityIn.m_5737_() == HumanoidArm.RIGHT || livingEntityIn.m_7655_() == InteractionHand.MAIN_HAND && livingEntityIn.m_5737_() == HumanoidArm.LEFT) {
                    left = true;
                }
                EntityHemolymph blood = new EntityHemolymph(worldIn, livingEntityIn, !left);
                Vec3 vector3d = livingEntityIn.m_20252_(1.0f);
                RandomSource rand = worldIn.m_213780_();
                livingEntityIn.m_146850_(GameEvent.f_223698_);
                livingEntityIn.m_5496_(SoundEvents.f_12032_, 1.0f, 0.5f + (rand.m_188501_() - rand.m_188501_()) * 0.2f);
                blood.shoot(vector3d.m_7096_(), vector3d.m_7098_(), vector3d.m_7094_(), 1.0f, 3.0f);
                if (!worldIn.f_46443_) {
                    worldIn.m_7967_((Entity)blood);
                }
                stack.m_41622_(1, livingEntityIn, player -> player.m_21190_(livingEntityIn.m_7655_()));
            }
        } else if (livingEntityIn instanceof Player) {
            ItemStack ammo = this.findAmmo((Player)livingEntityIn);
            boolean flag = ((Player)livingEntityIn).m_7500_();
            if (!ammo.m_41619_()) {
                ammo.m_41774_(1);
                flag = true;
            }
            if (flag) {
                ((Player)livingEntityIn).m_36335_().m_41524_((Item)this, 20);
                stack.m_41721_(0);
            }
            livingEntityIn.m_5810_();
        }
    }
}
