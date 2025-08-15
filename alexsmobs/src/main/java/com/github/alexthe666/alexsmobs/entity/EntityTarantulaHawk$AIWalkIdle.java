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

import com.github.alexthe666.alexsmobs.entity.EntityTarantulaHawk;
import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

private class EntityTarantulaHawk.AIWalkIdle
extends Goal {
    protected final EntityTarantulaHawk hawk;
    protected double x;
    protected double y;
    protected double z;
    private boolean flightTarget = false;

    public EntityTarantulaHawk.AIWalkIdle() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.hawk = EntityTarantulaHawk.this;
    }

    public boolean m_8036_() {
        if (this.hawk.m_20160_() || this.hawk.isScared() || this.hawk.isDragging() || EntityTarantulaHawk.this.getCommand() == 1 || this.hawk.m_5448_() != null && this.hawk.m_5448_().m_6084_() || this.hawk.m_20159_() || this.hawk.isSitting()) {
            return false;
        }
        if (this.hawk.m_217043_().m_188503_(30) != 0 && !this.hawk.isFlying()) {
            return false;
        }
        this.flightTarget = this.hawk.m_20096_() ? EntityTarantulaHawk.this.f_19796_.m_188499_() : EntityTarantulaHawk.this.f_19796_.m_188503_(5) > 0 && this.hawk.timeFlying < 200;
        Vec3 lvt_1_1_ = this.getPosition();
        if (lvt_1_1_ == null) {
            return false;
        }
        this.x = lvt_1_1_.f_82479_;
        this.y = lvt_1_1_.f_82480_;
        this.z = lvt_1_1_.f_82481_;
        return true;
    }

    public void m_8037_() {
        if (this.flightTarget) {
            this.hawk.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
        } else {
            this.hawk.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
        }
        if (!this.flightTarget && EntityTarantulaHawk.this.isFlying() && this.hawk.m_20096_()) {
            this.hawk.setFlying(false);
        }
        if (EntityTarantulaHawk.this.isFlying() && this.hawk.m_20096_() && this.hawk.timeFlying > 10) {
            this.hawk.setFlying(false);
        }
    }

    @Nullable
    protected Vec3 getPosition() {
        Vec3 vector3d = this.hawk.m_20182_();
        if (this.hawk.isOverWater()) {
            this.flightTarget = true;
        }
        if (this.flightTarget) {
            if (this.hawk.timeFlying < 50 || this.hawk.isOverWater()) {
                return this.hawk.getBlockInViewAway(vector3d, 0.0f);
            }
            return this.hawk.getBlockGrounding(vector3d);
        }
        return LandRandomPos.m_148488_((PathfinderMob)this.hawk, (int)10, (int)7);
    }

    public boolean m_8045_() {
        if (this.hawk.isSitting() || EntityTarantulaHawk.this.getCommand() == 1) {
            return false;
        }
        if (this.flightTarget) {
            return this.hawk.isFlying() && this.hawk.m_20275_(this.x, this.y, this.z) > 2.0;
        }
        return !this.hawk.m_21573_().m_26571_() && !this.hawk.m_20160_();
    }

    public void m_8056_() {
        if (this.flightTarget) {
            this.hawk.setFlying(true);
            this.hawk.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
        } else {
            this.hawk.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
        }
    }

    public void m_8041_() {
        this.hawk.m_21573_().m_26573_();
        super.m_8041_();
    }
}
