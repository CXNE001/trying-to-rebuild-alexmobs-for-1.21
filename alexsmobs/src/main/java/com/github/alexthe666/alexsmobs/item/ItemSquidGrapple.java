/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.item.UseAnim
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.entity.EntitySquidGrapple;
import com.github.alexthe666.alexsmobs.entity.util.SquidGrappleUtil;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class ItemSquidGrapple
extends Item {
    public ItemSquidGrapple(Item.Properties properties) {
        super(properties);
    }

    public int m_8105_(ItemStack p_40680_) {
        return 72000;
    }

    public UseAnim m_6164_(ItemStack stack) {
        return UseAnim.BOW;
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level p_40672_, Player p_40673_, InteractionHand p_40674_) {
        ItemStack itemstack = p_40673_.m_21120_(p_40674_);
        p_40673_.m_6672_(p_40674_);
        return InteractionResultHolder.m_19098_((Object)itemstack);
    }

    public void m_5929_(Level worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
    }

    public void m_5551_(ItemStack stack, Level worldIn, LivingEntity livingEntityIn, int i) {
        if (livingEntityIn.m_21255_()) {
            return;
        }
        livingEntityIn.m_5496_((SoundEvent)AMSoundRegistry.GIANT_SQUID_TENTACLE.get(), 1.0f, 1.0f + (livingEntityIn.m_217043_().m_188501_() - livingEntityIn.m_217043_().m_188501_()) * 0.2f);
        livingEntityIn.m_146850_(GameEvent.f_223697_);
        if (!worldIn.f_46443_) {
            boolean left = false;
            if (livingEntityIn.m_7655_() == InteractionHand.OFF_HAND && livingEntityIn.m_5737_() == HumanoidArm.RIGHT || livingEntityIn.m_7655_() == InteractionHand.MAIN_HAND && livingEntityIn.m_5737_() == HumanoidArm.LEFT) {
                left = true;
            }
            int power = this.m_8105_(stack) - i;
            EntitySquidGrapple hook = new EntitySquidGrapple(worldIn, livingEntityIn, !left);
            Vec3 vector3d = livingEntityIn.m_20252_(1.0f);
            hook.shoot(vector3d.m_7096_(), vector3d.m_7098_(), vector3d.m_7094_(), ItemSquidGrapple.getPowerForTime(power) * 3.0f, 1.0f);
            hook.m_146926_(livingEntityIn.m_146909_());
            hook.m_146922_(livingEntityIn.m_146908_());
            if (!worldIn.f_46443_) {
                worldIn.m_7967_((Entity)hook);
            }
            stack.m_41622_(1, livingEntityIn, playerIn -> livingEntityIn.m_21190_(playerIn.m_7655_()));
            SquidGrappleUtil.onFireHook(livingEntityIn, hook.m_20148_());
        }
    }

    public boolean m_6832_(ItemStack s, ItemStack s1) {
        return s1.m_150930_((Item)AMItemRegistry.LOST_TENTACLE.get());
    }

    public static float getPowerForTime(int p) {
        float f = (float)p / 20.0f;
        if ((f = (f * f + f + f * 2.0f) / 4.0f) > 1.0f) {
            f = 1.0f;
        }
        return f;
    }

    public void m_7373_(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add((Component)Component.m_237115_((String)"item.alexsmobs.squid_grapple.desc").m_130940_(ChatFormatting.GRAY));
    }
}
