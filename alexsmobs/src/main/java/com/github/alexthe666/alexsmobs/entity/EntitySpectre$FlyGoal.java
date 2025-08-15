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

import com.github.alexthe666.alexsmobs.entity.EntitySpectre;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

private class EntitySpectre.FlyGoal
extends Goal {
    private final EntitySpectre parentEntity;
    boolean island = false;
    float circlingTime = 0.0f;
    float circleDistance = 14.0f;
    float maxCirclingTime = 80.0f;
    boolean clockwise = false;
    private BlockPos target = null;
    private int islandCheckTime = 20;

    public EntitySpectre.FlyGoal(EntitySpectre sunbird) {
        this.parentEntity = sunbird;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        if (this.parentEntity.lurePos != null) {
            return false;
        }
        MoveControl movementcontroller = this.parentEntity.m_21566_();
        this.clockwise = EntitySpectre.this.f_19796_.m_188499_();
        this.circleDistance = 5 + EntitySpectre.this.f_19796_.m_188503_(10);
        if (!movementcontroller.m_24995_() || this.target == null) {
            BlockPos blockPos = this.target = this.island ? this.getIslandPos(this.parentEntity.m_20183_()) : this.getBlockFromDirection();
            if (this.target != null) {
                this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, 1.0);
            }
            return true;
        }
        return false;
    }

    public boolean m_8045_() {
        return this.parentEntity.lurePos == null;
    }

    public void m_8041_() {
        this.island = false;
        this.islandCheckTime = 0;
        this.circleDistance = 5 + EntitySpectre.this.f_19796_.m_188503_(10);
        this.circlingTime = 0.0f;
        this.clockwise = EntitySpectre.this.f_19796_.m_188499_();
        this.target = null;
    }

    public void m_8037_() {
        if (this.islandCheckTime-- <= 0) {
            this.islandCheckTime = 20;
            if (this.circlingTime == 0.0f) {
                boolean bl = this.island = this.parentEntity.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, this.parentEntity.m_20183_()).m_123342_() > 2;
                if (this.island) {
                    this.parentEntity.randomizeDirection();
                }
            }
        }
        if (this.island) {
            this.circlingTime += 1.0f;
            if (this.circlingTime > 100.0f) {
                this.island = false;
                this.islandCheckTime = 1200;
            }
        } else if (this.circlingTime > 0.0f) {
            this.circlingTime -= 1.0f;
        }
        if (this.target == null) {
            BlockPos blockPos = this.target = this.island ? this.getIslandPos(this.parentEntity.m_20183_()) : this.getBlockFromDirection();
        }
        if (!this.island) {
            this.parentEntity.m_146922_(this.parentEntity.getCardinalDirection().m_122435_());
        }
        if (this.target != null) {
            this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, 1.0);
            if (this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)this.target)) < 5.5) {
                this.target = null;
            }
        }
    }

    public BlockPos getBlockFromDirection() {
        float radius = 15.0f;
        BlockPos forwards = this.parentEntity.m_20183_().m_5484_(this.parentEntity.getCardinalDirection(), (int)Math.ceil(radius));
        int height = 0;
        height = EntitySpectre.this.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, forwards).m_123342_() < 15 ? 70 + EntitySpectre.this.f_19796_.m_188503_(2) : EntitySpectre.this.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, forwards).m_123342_() + 10 + EntitySpectre.this.f_19796_.m_188503_(10);
        return new BlockPos(forwards.m_123341_(), height, forwards.m_123343_());
    }

    public BlockPos getIslandPos(BlockPos orbit) {
        float angle = 0.05235988f * (this.clockwise ? -this.circlingTime : this.circlingTime);
        double extraX = this.circleDistance * Mth.m_14031_((float)angle);
        double extraZ = this.circleDistance * Mth.m_14089_((float)angle);
        int height = EntitySpectre.this.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, orbit).m_123342_();
        if (height < 3) {
            this.island = false;
            return this.getBlockFromDirection();
        }
        return new BlockPos((int)((double)orbit.m_123341_() + extraX), Math.min(height + 10, orbit.m_123342_() + EntitySpectre.this.f_19796_.m_188503_(3) - EntitySpectre.this.f_19796_.m_188503_(1)), (int)((double)orbit.m_123343_() + extraZ));
    }
}
