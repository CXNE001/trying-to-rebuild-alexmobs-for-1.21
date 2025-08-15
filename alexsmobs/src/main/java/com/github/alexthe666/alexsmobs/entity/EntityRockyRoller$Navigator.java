/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.pathfinder.PathFinder
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityRockyRoller;
import com.github.alexthe666.alexsmobs.entity.ai.AdvancedPathNavigateNoTeleport;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathFinder;

static class EntityRockyRoller.Navigator
extends AdvancedPathNavigateNoTeleport {
    public EntityRockyRoller.Navigator(Mob mob, Level world) {
        super(mob, world, true);
    }

    protected PathFinder m_5532_(int i) {
        this.f_26508_ = new EntityRockyRoller.RockyRollerNodeEvaluator();
        return new PathFinder(this.f_26508_, i);
    }
}
