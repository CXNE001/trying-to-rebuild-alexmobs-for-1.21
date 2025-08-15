/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.pathfinder.PathFinder
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityTiger;
import com.github.alexthe666.alexsmobs.entity.ai.GroundPathNavigatorWide;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathFinder;

static class EntityTiger.Navigator
extends GroundPathNavigatorWide {
    public EntityTiger.Navigator(Mob mob, Level world) {
        super(mob, world, 1.2f);
    }

    protected PathFinder m_5532_(int i) {
        this.f_26508_ = new EntityTiger.TigerNodeEvaluator();
        return new PathFinder(this.f_26508_, i);
    }
}
