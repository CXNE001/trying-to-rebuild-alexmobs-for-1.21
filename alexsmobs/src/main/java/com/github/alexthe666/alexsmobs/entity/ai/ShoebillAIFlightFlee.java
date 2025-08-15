/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityShoebill;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

public class ShoebillAIFlightFlee
extends Goal {
    private final EntityShoebill bird;
    private BlockPos currentTarget = null;
    private int executionTime = 0;

    public ShoebillAIFlightFlee(EntityShoebill bird) {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.bird = bird;
    }

    public void m_8041_() {
        this.currentTarget = null;
        this.executionTime = 0;
        this.bird.setFlying(false);
    }

    public boolean m_8045_() {
        return this.bird.isFlying() && (this.executionTime < 15 || !this.bird.m_20096_());
    }

    public boolean m_8036_() {
        return this.bird.revengeCooldown > 0 && this.bird.m_20096_();
    }

    public void m_8056_() {
        if (this.bird.m_20096_()) {
            this.bird.setFlying(true);
        }
    }

    public void m_8037_() {
        ++this.executionTime;
        if (this.currentTarget == null) {
            this.currentTarget = this.bird.revengeCooldown == 0 ? this.getBlockGrounding(this.bird.m_20182_()) : this.getBlockInViewAway(this.bird.m_20182_());
        }
        if (this.currentTarget != null) {
            this.bird.m_21573_().m_26519_((double)((float)this.currentTarget.m_123341_() + 0.5f), (double)((float)this.currentTarget.m_123342_() + 0.5f), (double)((float)this.currentTarget.m_123343_() + 0.5f), 1.0);
            if (this.bird.m_20238_(Vec3.m_82512_((Vec3i)this.currentTarget)) < 4.0) {
                this.currentTarget = null;
            }
        }
        if (this.bird.revengeCooldown == 0 && (this.bird.m_20069_() || !this.bird.m_9236_().m_46859_(this.bird.m_20183_().m_7495_()))) {
            this.m_8041_();
            this.bird.setFlying(false);
        }
    }

    public BlockPos getBlockInViewAway(Vec3 fleePos) {
        float radius = -9.45f - (float)this.bird.m_217043_().m_188503_(24);
        float neg = this.bird.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.bird.f_20883_;
        float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f + this.bird.m_217043_().m_188501_() * neg;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = AMBlockPos.fromCoords(fleePos.m_7096_() + extraX, 0.0, fleePos.m_7094_() + extraZ);
        BlockPos ground = this.bird.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, radialPos);
        int distFromGround = (int)this.bird.m_20186_() - ground.m_123342_();
        int flightHeight = 4 + this.bird.m_217043_().m_188503_(10);
        BlockPos newPos = radialPos.m_6630_(distFromGround > 8 ? flightHeight : (int)this.bird.m_20186_() + this.bird.m_217043_().m_188503_(6) + 1);
        if (!this.bird.isTargetBlocked(Vec3.m_82512_((Vec3i)newPos)) && this.bird.m_20238_(Vec3.m_82512_((Vec3i)newPos)) > 6.0) {
            return newPos;
        }
        return null;
    }

    public BlockPos getBlockGrounding(Vec3 fleePos) {
        float radius = -9.45f - (float)this.bird.m_217043_().m_188503_(24);
        float neg = this.bird.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.bird.f_20883_;
        float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f + this.bird.m_217043_().m_188501_() * neg;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = AMBlockPos.fromCoords(fleePos.m_7096_() + extraX, 0.0, fleePos.m_7094_() + extraZ);
        BlockPos ground = this.bird.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, radialPos);
        if (!this.bird.isTargetBlocked(Vec3.m_82512_((Vec3i)ground.m_7494_()))) {
            return ground;
        }
        return null;
    }
}
