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

import com.github.alexthe666.alexsmobs.entity.EntityCrow;
import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

private class EntityCrow.AIWalkIdle
extends Goal {
    protected final EntityCrow crow;
    protected double x;
    protected double y;
    protected double z;
    private boolean flightTarget = false;

    public EntityCrow.AIWalkIdle() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.crow = EntityCrow.this;
    }

    public boolean m_8036_() {
        Vec3 lvt_1_1_;
        if (this.crow.m_20160_() || EntityCrow.this.getCommand() == 1 || EntityCrow.this.aiItemFlag || this.crow.m_5448_() != null && this.crow.m_5448_().m_6084_() || this.crow.m_20159_() || this.crow.isSitting()) {
            return false;
        }
        if (this.crow.m_217043_().m_188503_(30) != 0 && !this.crow.isFlying()) {
            return false;
        }
        if (this.crow.m_20096_()) {
            this.flightTarget = EntityCrow.this.f_19796_.m_188499_();
        } else {
            boolean bl = this.flightTarget = EntityCrow.this.f_19796_.m_188503_(5) > 0 && this.crow.timeFlying < 200;
        }
        if (this.crow.getCommand() == 3) {
            if (this.crow.aiItemFrameFlag) {
                return false;
            }
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
            this.crow.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
        } else {
            this.crow.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
            if (EntityCrow.this.isFlying() && this.crow.m_20096_()) {
                this.crow.setFlying(false);
            }
        }
        if (EntityCrow.this.isFlying() && this.crow.m_20096_() && this.crow.timeFlying > 10) {
            this.crow.setFlying(false);
        }
    }

    @Nullable
    protected Vec3 getPosition() {
        Vec3 vector3d = this.crow.m_20182_();
        if (this.crow.getCommand() == 3 && this.crow.getPerchPos() != null) {
            return this.crow.getGatheringVec(vector3d, 4 + EntityCrow.this.f_19796_.m_188503_(2));
        }
        if (this.crow.isOverWater()) {
            this.flightTarget = true;
        }
        if (this.flightTarget) {
            if (this.crow.timeFlying < 50 || this.crow.isOverWater()) {
                return this.crow.getBlockInViewAway(vector3d, 0.0f);
            }
            return this.crow.getBlockGrounding(vector3d);
        }
        return LandRandomPos.m_148488_((PathfinderMob)this.crow, (int)10, (int)7);
    }

    public boolean m_8045_() {
        if (this.crow.aiItemFlag || this.crow.isSitting() || EntityCrow.this.getCommand() == 1) {
            return false;
        }
        if (this.flightTarget) {
            return this.crow.isFlying() && this.crow.m_20275_(this.x, this.y, this.z) > 2.0;
        }
        return !this.crow.m_21573_().m_26571_() && !this.crow.m_20160_();
    }

    public void m_8056_() {
        if (this.flightTarget) {
            this.crow.setFlying(true);
            this.crow.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
        } else {
            this.crow.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
        }
    }

    public void m_8041_() {
        this.crow.m_21573_().m_26573_();
        super.m_8041_();
    }
}
