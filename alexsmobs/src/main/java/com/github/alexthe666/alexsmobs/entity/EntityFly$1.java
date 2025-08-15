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

class EntityFly.1
extends FlyingPathNavigation {
    EntityFly.1(Mob p_26424_, Level p_26425_) {
        super(p_26424_, p_26425_);
    }

    public boolean m_6342_(BlockPos pos) {
        return !this.f_26495_.m_8055_(pos.m_7495_()).m_60795_();
    }
}
