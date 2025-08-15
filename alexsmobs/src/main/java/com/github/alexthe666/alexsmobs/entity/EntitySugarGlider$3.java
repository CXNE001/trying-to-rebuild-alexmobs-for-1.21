/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.navigation.WallClimberNavigation
 *  net.minecraft.world.level.Level
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntitySugarGlider;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.level.Level;

class EntitySugarGlider.3
extends WallClimberNavigation {
    EntitySugarGlider.3(Mob p_26580_, Level p_26581_) {
        super(p_26580_, p_26581_);
    }

    protected boolean m_7632_() {
        return super.m_7632_() || ((EntitySugarGlider)this.f_26494_).isBesideClimbableBlock() || this.f_26494_.f_20899_;
    }
}
