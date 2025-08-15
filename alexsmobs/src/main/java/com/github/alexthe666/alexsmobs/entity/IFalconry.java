/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public interface IFalconry {
    public void onLaunch(Player var1, Entity var2);

    default public int getRidingFalcons(LivingEntity player) {
        int crowCount = 0;
        for (Entity e : player.m_20197_()) {
            if (!(e instanceof IFalconry)) continue;
            ++crowCount;
        }
        return crowCount;
    }

    public float getHandOffset();
}
