/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.AdvancedPathNavigate
 *  com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.AdvancedPathNavigate$MovementType
 *  com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.PathingStuckHandler
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.Level
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.AdvancedPathNavigate;
import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.PathingStuckHandler;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

public class AdvancedPathNavigateNoTeleport
extends AdvancedPathNavigate {
    private final boolean wide;

    public AdvancedPathNavigateNoTeleport(Mob entity, Level world, AdvancedPathNavigate.MovementType type, boolean climbing, boolean wide) {
        super(entity, world, type, entity.m_20205_(), entity.m_20206_(), PathingStuckHandler.createStuckHandler());
        this.getPathingOptions().setCanClimb(climbing);
        this.wide = wide;
    }

    public AdvancedPathNavigateNoTeleport(Mob entity, Level world, boolean wide) {
        this(entity, world, AdvancedPathNavigate.MovementType.WALKING, false, wide);
    }

    protected boolean m_7632_() {
        return true;
    }

    protected float calculateMaxDistanceToWaypoint() {
        return this.wide ? this.f_26494_.m_20205_() * 0.75f : super.calculateMaxDistanceToWaypoint();
    }
}
