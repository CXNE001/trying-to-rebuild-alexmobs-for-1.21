/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityToucan;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

private class EntityToucan.AIPlantTrees
extends Goal {
    protected final EntityToucan toucan;
    protected BlockPos pos;
    private int runCooldown = 0;
    private int encircleTime = 0;
    private int plantTime = 0;
    private boolean clockwise;

    public EntityToucan.AIPlantTrees() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.toucan = EntityToucan.this;
    }

    public boolean m_8036_() {
        if (this.toucan.getSaplingState() != null && this.runCooldown-- <= 0) {
            BlockPos target = this.getSaplingPlantPos();
            this.runCooldown = this.resetCooldown();
            if (target != null) {
                this.pos = target;
                this.clockwise = EntityToucan.this.f_19796_.m_188499_();
                this.encircleTime = (this.toucan.isGolden() ? 20 : 100) + EntityToucan.this.f_19796_.m_188503_(100);
                return true;
            }
        }
        return false;
    }

    private int resetCooldown() {
        return this.toucan.isGolden() && !this.toucan.isEnchanted() ? 50 + EntityToucan.this.f_19796_.m_188503_(40) : 200 + EntityToucan.this.f_19796_.m_188503_(200);
    }

    public void m_8037_() {
        this.toucan.aiItemFlag = true;
        double up = 3.0;
        if (this.encircleTime > 0) {
            --this.encircleTime;
        }
        if (this.isWithinXZDist(this.pos, this.toucan.m_20182_(), 5.0) && this.encircleTime <= 0) {
            up = 0.0;
        }
        if (this.toucan.m_20238_(Vec3.m_82512_((Vec3i)this.pos)) < 3.0) {
            this.toucan.setFlying(false);
            this.toucan.peck();
            ++this.plantTime;
            if (this.plantTime > 60) {
                BlockState state = this.toucan.getSaplingState();
                if (state != null && state.m_60710_((LevelReader)this.toucan.m_9236_(), this.pos) && this.toucan.m_9236_().m_8055_(this.pos).m_247087_()) {
                    this.toucan.m_9236_().m_46597_(this.pos, state);
                    if (!this.toucan.isEnchanted()) {
                        this.toucan.setSaplingState(null);
                    }
                }
                this.m_8041_();
            }
        } else {
            BlockPos moveTo = this.pos;
            if (this.encircleTime > 0) {
                moveTo = this.getVultureCirclePos(this.pos, 3.0f, up);
            }
            if (moveTo != null) {
                if (this.encircleTime <= 0 && !this.toucan.hasLineOfSightSapling(this.pos)) {
                    this.toucan.setFlying(false);
                    this.toucan.m_21573_().m_26519_((double)((float)moveTo.m_123341_() + 0.5f), (double)moveTo.m_123342_() + up + 0.5, (double)((float)moveTo.m_123343_() + 0.5f), 1.0);
                } else {
                    this.toucan.setFlying(true);
                    this.toucan.m_21566_().m_6849_((double)((float)moveTo.m_123341_() + 0.5f), (double)moveTo.m_123342_() + up + 0.5, (double)((float)moveTo.m_123343_() + 0.5f), 1.0);
                }
            }
        }
    }

    public BlockPos getVultureCirclePos(BlockPos target, float circleDistance, double yLevel) {
        float angle = 0.13962634f * (float)(this.clockwise ? -this.encircleTime : this.encircleTime);
        double extraX = circleDistance * Mth.m_14031_((float)angle);
        double extraZ = circleDistance * Mth.m_14089_((float)angle);
        BlockPos pos = new BlockPos((int)((double)((float)target.m_123341_() + 0.5f) + extraX), (int)((double)(target.m_123342_() + 1) + yLevel), (int)((double)((float)target.m_123343_() + 0.5f) + extraZ));
        if (this.toucan.m_9236_().m_46859_(pos)) {
            return pos;
        }
        return null;
    }

    public void m_8041_() {
        this.toucan.aiItemFlag = false;
        this.pos = null;
        this.plantTime = 0;
        this.encircleTime = 0;
    }

    public boolean m_8045_() {
        return this.pos != null && this.toucan.getSaplingState() != null;
    }

    private boolean isWithinXZDist(BlockPos blockpos, Vec3 positionVec, double distance) {
        return blockpos.m_123331_((Vec3i)new BlockPos((int)positionVec.m_7096_(), blockpos.m_123342_(), (int)positionVec.m_7094_())) < distance * distance;
    }

    private BlockPos getSaplingPlantPos() {
        BlockState state = this.toucan.getSaplingState();
        if (state != null) {
            for (int i = 0; i < 15; ++i) {
                BlockPos pos = this.toucan.m_20183_().m_7918_(EntityToucan.this.f_19796_.m_188503_(10) - 8, EntityToucan.this.f_19796_.m_188503_(8) - 4, EntityToucan.this.f_19796_.m_188503_(16) - 8);
                if (!state.m_60710_((LevelReader)this.toucan.m_9236_(), pos) || !this.toucan.m_9236_().m_46859_(pos.m_7494_()) || !this.toucan.hasLineOfSightSapling(pos)) continue;
                return pos;
            }
        }
        return null;
    }
}
