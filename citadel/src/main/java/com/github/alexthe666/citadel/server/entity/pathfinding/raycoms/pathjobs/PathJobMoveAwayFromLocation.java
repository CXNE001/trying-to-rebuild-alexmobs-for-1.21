/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.pathfinder.Path
 */
package com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.pathjobs;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.MNode;
import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.Pathfinding;
import com.github.alexthe666.citadel.server.entity.pathfinding.raycoms.pathjobs.AbstractPathJob;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;

public class PathJobMoveAwayFromLocation
extends AbstractPathJob {
    protected final BlockPos avoid;
    protected final int avoidDistance;

    public PathJobMoveAwayFromLocation(Level world, BlockPos start, BlockPos avoid, int avoidDistance, int range, LivingEntity entity) {
        super(world, start, avoid, range, entity);
        this.avoid = new BlockPos((Vec3i)avoid);
        this.avoidDistance = avoidDistance;
    }

    @Override
    @Nullable
    protected Path search() {
        if (Pathfinding.isDebug()) {
            Citadel.LOGGER.info(String.format("Pathfinding from [%d,%d,%d] away from [%d,%d,%d]", this.start.m_123341_(), this.start.m_123342_(), this.start.m_123343_(), this.avoid.m_123341_(), this.avoid.m_123342_(), this.avoid.m_123343_()));
        }
        return super.search();
    }

    @Override
    protected double computeHeuristic(BlockPos pos) {
        return -this.avoid.m_123331_((Vec3i)pos);
    }

    @Override
    protected boolean isAtDestination(MNode n) {
        return Math.sqrt(this.avoid.m_123331_((Vec3i)n.pos)) > (double)this.avoidDistance;
    }

    @Override
    protected double getNodeResultScore(MNode n) {
        return -this.avoid.m_123331_((Vec3i)n.pos);
    }
}
