/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityEnderiophage;
import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

private class EntityEnderiophage.AIWalkIdle
extends Goal {
    protected final EntityEnderiophage phage;
    protected double x;
    protected double y;
    protected double z;
    private boolean flightTarget = false;

    public EntityEnderiophage.AIWalkIdle() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.phage = EntityEnderiophage.this;
    }

    public boolean m_8036_() {
        Vec3 lvt_1_1_;
        if (this.phage.m_20160_() || this.phage.m_5448_() != null && this.phage.m_5448_().m_6084_() || this.phage.m_20159_()) {
            return false;
        }
        if (this.phage.m_217043_().m_188503_(30) != 0 && !this.phage.m_29443_() && this.phage.fleeAfterStealTime == 0) {
            return false;
        }
        if (this.phage.m_20096_()) {
            this.flightTarget = EntityEnderiophage.this.f_19796_.m_188503_(12) == 0;
        } else {
            boolean bl = this.flightTarget = EntityEnderiophage.this.f_19796_.m_188503_(5) > 0 && this.phage.timeFlying < 100;
        }
        if (this.phage.fleeAfterStealTime > 0) {
            this.flightTarget = true;
        }
        if ((lvt_1_1_ = this.getPosition()) == null) {
            return false;
        }
        this.x = lvt_1_1_.f_82479_;
        this.y = lvt_1_1_.f_82480_;
        this.z = lvt_1_1_.f_82481_;
        return true;
    }

    public void m_8037_() {
        if (this.flightTarget) {
            this.phage.m_21566_().m_6849_(this.x, this.y, this.z, EntityEnderiophage.this.fleeAfterStealTime == 0 ? (double)1.3f : 1.0);
        } else {
            this.phage.m_21573_().m_26519_(this.x, this.y, this.z, EntityEnderiophage.this.fleeAfterStealTime == 0 ? (double)1.3f : 1.0);
        }
        if (!this.flightTarget && EntityEnderiophage.this.m_29443_() && this.phage.m_20096_()) {
            this.phage.setFlying(false);
        }
        if (EntityEnderiophage.this.m_29443_() && this.phage.m_20096_() && this.phage.timeFlying > 100 && this.phage.fleeAfterStealTime == 0) {
            this.phage.setFlying(false);
        }
    }

    @Nullable
    protected Vec3 getPosition() {
        Vec3 vector3d = this.phage.m_20182_();
        if (this.phage.isOverWaterOrVoid()) {
            this.flightTarget = true;
        }
        if (this.flightTarget) {
            if (this.phage.timeFlying < 50 || EntityEnderiophage.this.fleeAfterStealTime > 0 || this.phage.isOverWaterOrVoid()) {
                return this.phage.getBlockInViewAway(vector3d, 0.0f);
            }
            return this.phage.getBlockGrounding(vector3d);
        }
        return LandRandomPos.m_148488_((PathfinderMob)this.phage, (int)10, (int)7);
    }

    public boolean m_8045_() {
        if (this.flightTarget) {
            return this.phage.m_29443_() && this.phage.m_20275_(this.x, this.y, this.z) > 2.0;
        }
        return !this.phage.m_21573_().m_26571_() && !this.phage.m_20160_();
    }

    public void m_8056_() {
        if (this.flightTarget) {
            this.phage.setFlying(true);
            this.phage.m_21566_().m_6849_(this.x, this.y, this.z, EntityEnderiophage.this.fleeAfterStealTime == 0 ? (double)1.3f : 1.0);
        } else {
            this.phage.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
        }
    }

    public void m_8041_() {
        this.phage.m_21573_().m_26573_();
        super.m_8041_();
    }
}
