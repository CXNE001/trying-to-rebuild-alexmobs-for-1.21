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

import com.github.alexthe666.alexsmobs.entity.EntityWarpedMosco;
import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

private class EntityWarpedMosco.AIWalkIdle
extends Goal {
    protected final EntityWarpedMosco mosco;
    protected double x;
    protected double y;
    protected double z;
    private boolean flightTarget = false;

    public EntityWarpedMosco.AIWalkIdle() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.mosco = EntityWarpedMosco.this;
    }

    public boolean m_8036_() {
        if (this.mosco.m_20160_() || this.mosco.m_5448_() != null && this.mosco.m_5448_().m_6084_() || this.mosco.m_20159_()) {
            return false;
        }
        if (this.mosco.m_217043_().m_188503_(30) != 0 && !this.mosco.isFlying()) {
            return false;
        }
        this.flightTarget = this.mosco.m_20096_() ? EntityWarpedMosco.this.f_19796_.m_188503_(8) == 0 : EntityWarpedMosco.this.f_19796_.m_188503_(5) > 0 && this.mosco.timeFlying < 200;
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
            this.mosco.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
        } else {
            this.mosco.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
        }
        if (!this.flightTarget && EntityWarpedMosco.this.isFlying() && this.mosco.m_20096_()) {
            this.mosco.setFlying(false);
        }
        if (EntityWarpedMosco.this.isFlying() && this.mosco.m_20096_() && this.mosco.timeFlying > 10) {
            this.mosco.setFlying(false);
        }
    }

    @Nullable
    protected Vec3 getPosition() {
        Vec3 vector3d = this.mosco.m_20182_();
        if (this.mosco.isOverLiquid()) {
            this.flightTarget = true;
        }
        if (this.flightTarget) {
            if (this.mosco.timeFlying < 50 || this.mosco.isOverLiquid()) {
                return this.mosco.getBlockInViewAway(vector3d, 0.0f);
            }
            return this.mosco.getBlockGrounding(vector3d);
        }
        return LandRandomPos.m_148488_((PathfinderMob)this.mosco, (int)20, (int)7);
    }

    public boolean m_8045_() {
        if (this.flightTarget) {
            return this.mosco.isFlying() && this.mosco.m_20275_(this.x, this.y, this.z) > 20.0 && !this.mosco.f_19862_;
        }
        return !this.mosco.m_21573_().m_26571_() && !this.mosco.m_20160_();
    }

    public void m_8056_() {
        if (this.flightTarget) {
            this.mosco.setFlying(true);
            this.mosco.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
        } else {
            this.mosco.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
        }
    }

    public void m_8041_() {
        this.mosco.m_21573_().m_26573_();
        super.m_8041_();
    }
}
