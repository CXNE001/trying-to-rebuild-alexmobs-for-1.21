/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.ItemStack
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

class CreatureAITargetItems.1
implements Predicate<ItemEntity> {
    CreatureAITargetItems.1() {
    }

    public boolean apply(@Nullable ItemEntity item) {
        ItemStack stack = item.m_32055_();
        return !stack.m_41619_() && CreatureAITargetItems.this.hunter.canTargetItem(stack) && item.f_19797_ > CreatureAITargetItems.this.tickThreshold;
    }
}
