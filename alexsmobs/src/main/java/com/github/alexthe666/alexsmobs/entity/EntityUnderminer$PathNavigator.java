/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.navigation.GroundPathNavigation
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityUnderminer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

private class EntityUnderminer.PathNavigator
extends GroundPathNavigation {
    public EntityUnderminer.PathNavigator(EntityUnderminer underminer, Level level) {
        super((Mob)underminer, EntityUnderminer.this.m_9236_());
    }

    protected boolean m_7632_() {
        return !this.f_26494_.m_20159_();
    }

    protected Vec3 m_7475_() {
        return this.f_26494_.m_20182_();
    }
}
