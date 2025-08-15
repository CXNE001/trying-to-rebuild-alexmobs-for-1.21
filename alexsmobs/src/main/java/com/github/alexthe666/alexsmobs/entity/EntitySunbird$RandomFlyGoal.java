/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntitySunbird;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

static class EntitySunbird.RandomFlyGoal
extends Goal {
    private final EntitySunbird parentEntity;
    private BlockPos target = null;

    public EntitySunbird.RandomFlyGoal(EntitySunbird sunbird) {
        this.parentEntity = sunbird;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        MoveControl movementcontroller = this.parentEntity.m_21566_();
        if (!movementcontroller.m_24995_() || this.target == null) {
            this.target = this.parentEntity.beaconPos != null ? this.getBlockInViewBeacon(this.parentEntity.beaconPos, 5 + this.parentEntity.f_19796_.m_188503_(1)) : this.getBlockInViewSunbird();
            if (this.target != null) {
                this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, this.parentEntity.beaconPos != null ? 0.8 : 1.0);
            }
            return true;
        }
        return false;
    }

    public boolean m_8045_() {
        return this.target != null && this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)this.target)) > 2.4 && this.parentEntity.m_21566_().m_24995_() && !this.parentEntity.f_19862_;
    }

    public void m_8041_() {
        this.target = null;
    }

    public void m_8037_() {
        if (this.target == null) {
            this.target = this.parentEntity.beaconPos != null ? this.getBlockInViewBeacon(this.parentEntity.beaconPos, 5 + this.parentEntity.f_19796_.m_188503_(1)) : this.getBlockInViewSunbird();
        }
        if (this.parentEntity.beaconPos != null && this.parentEntity.f_19796_.m_188503_(100) == 0) {
            this.parentEntity.orbitClockwise = this.parentEntity.f_19796_.m_188499_();
        }
        if (this.target != null) {
            this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, this.parentEntity.beaconPos != null ? 0.8 : 1.0);
            if (this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)this.target)) < 2.5) {
                this.target = null;
            }
        }
    }

    private BlockPos getBlockInViewBeacon(BlockPos orbitPos, float gatheringCircleDist) {
        float angle = 0.15707964f * (float)(this.parentEntity.orbitClockwise ? -this.parentEntity.f_19797_ : this.parentEntity.f_19797_);
        double extraX = gatheringCircleDist * Mth.m_14031_((float)angle);
        double extraZ = gatheringCircleDist * Mth.m_14089_((float)angle);
        if (orbitPos != null) {
            BlockPos pos = AMBlockPos.fromCoords((double)orbitPos.m_123341_() + extraX, orbitPos.m_123342_() + this.parentEntity.f_19796_.m_188503_(2) + 2, (double)orbitPos.m_123343_() + extraZ);
            if (this.parentEntity.m_9236_().m_46859_(new BlockPos((Vec3i)pos))) {
                return pos;
            }
        }
        return null;
    }

    public BlockPos getBlockInViewSunbird() {
        float radius = -9.45f - (float)this.parentEntity.m_217043_().m_188503_(24);
        float neg = this.parentEntity.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.parentEntity.f_20883_;
        float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f + this.parentEntity.m_217043_().m_188501_() * neg;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = AMBlockPos.fromCoords(this.parentEntity.m_20185_() + extraX, 0.0, this.parentEntity.m_20189_() + extraZ);
        BlockPos ground = this.parentEntity.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, radialPos);
        int distFromGround = (int)this.parentEntity.m_20186_() - ground.m_123342_();
        int flightHeight = Math.max(ground.m_123342_(), 230 + this.parentEntity.m_217043_().m_188503_(40)) - ground.m_123342_();
        BlockPos newPos = radialPos.m_6630_(distFromGround > 16 ? flightHeight : (int)this.parentEntity.m_20186_() + this.parentEntity.m_217043_().m_188503_(16) + 1);
        if (!this.parentEntity.isTargetBlocked(Vec3.m_82512_((Vec3i)newPos)) && this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)newPos)) > 6.0) {
            return newPos;
        }
        return null;
    }
}
