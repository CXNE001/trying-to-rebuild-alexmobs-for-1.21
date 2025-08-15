/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.navigation.FlyingPathNavigation
 *  net.minecraft.world.level.Level
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.level.Level;

class EntitySkreecher.2
extends FlyingPathNavigation {
    EntitySkreecher.2(Mob p_26424_, Level p_26425_) {
        super(p_26424_, p_26425_);
    }

    public boolean m_6342_(BlockPos pos) {
        int airAbove = 0;
        while (EntitySkreecher.this.m_9236_().m_8055_(pos).m_60795_() && (float)airAbove < 6.0f) {
            pos = pos.m_7494_();
            ++airAbove;
        }
        return (float)airAbove < Math.min(4.0f, (float)EntitySkreecher.this.f_19796_.m_188503_(4));
    }
}
