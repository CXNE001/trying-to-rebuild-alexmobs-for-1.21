/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 */
package com.github.alexthe666.alexsmobs.item;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

class ItemFalconryGlove.1
implements Predicate<Entity> {
    ItemFalconryGlove.1() {
    }

    public boolean apply(@Nullable Entity entity) {
        return entity != null && entity.m_6087_() && (entity instanceof Player || entity instanceof LivingEntity);
    }
}
