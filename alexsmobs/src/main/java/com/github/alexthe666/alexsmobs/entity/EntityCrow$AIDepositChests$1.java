/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.decoration.ItemFrame
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraftforge.common.capabilities.ForgeCapabilities
 *  net.minecraftforge.common.util.LazyOptional
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityCrow;
import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;

class EntityCrow.AIDepositChests.1
implements Predicate<ItemFrame> {
    final /* synthetic */ EntityCrow val$this$0;

    EntityCrow.AIDepositChests.1() {
        this.val$this$0 = entityCrow;
    }

    public boolean apply(@Nullable ItemFrame e) {
        LazyOptional handler;
        BlockPos hangingPosition = e.m_31748_().m_121945_(e.m_6350_().m_122424_());
        BlockEntity entity = e.m_9236_().m_7702_(hangingPosition);
        if (entity != null && (handler = entity.getCapability(ForgeCapabilities.ITEM_HANDLER, e.m_6350_().m_122424_())) != null && handler.isPresent()) {
            return ItemStack.m_41656_((ItemStack)e.m_31822_(), (ItemStack)AIDepositChests.this.this$0.m_21205_());
        }
        return false;
    }
}
