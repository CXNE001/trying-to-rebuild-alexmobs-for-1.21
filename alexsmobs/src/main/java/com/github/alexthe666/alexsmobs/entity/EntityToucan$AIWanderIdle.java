/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityToucan;
import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

private class EntityToucan.AIWanderIdle
extends Goal {
    protected final EntityToucan toucan;
    protected double x;
    protected double y;
    protected double z;
    private boolean flightTarget = false;

    public EntityToucan.AIWanderIdle() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.toucan = EntityToucan.this;
    }

    public boolean m_8036_() {
        if (this.toucan.m_20160_() || this.toucan.getSaplingState() != null || EntityToucan.this.aiItemFlag || this.toucan.m_5448_() != null && this.toucan.m_5448_().m_6084_() || this.toucan.m_20159_()) {
            return false;
        }
        if (this.toucan.m_217043_().m_188503_(45) != 0 && !this.toucan.isFlying()) {
            return false;
        }
        this.flightTarget = this.toucan.m_20096_() ? EntityToucan.this.f_19796_.m_188503_(6) == 0 : EntityToucan.this.f_19796_.m_188503_(5) != 0 && this.toucan.timeFlying < 200;
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
            this.toucan.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
        } else {
            this.toucan.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
        }
        if (!this.flightTarget && EntityToucan.this.isFlying() && this.toucan.m_20096_()) {
            this.toucan.setFlying(false);
        }
        if (EntityToucan.this.isFlying() && this.toucan.m_20096_() && this.toucan.timeFlying > 10) {
            this.toucan.setFlying(false);
        }
    }

    @Nullable
    protected Vec3 getPosition() {
        Vec3 vector3d = this.toucan.m_20182_();
        if (this.toucan.isOverWaterOrVoid()) {
            this.flightTarget = true;
        }
        if (this.flightTarget) {
            if (this.toucan.timeFlying > 50 && this.toucan.isOverLeaves() && !this.toucan.m_20096_()) {
                return this.toucan.getBlockGrounding(vector3d);
            }
            if (this.toucan.timeFlying < 200 || this.toucan.isOverWaterOrVoid()) {
                return this.toucan.getBlockInViewAway(vector3d, 0.0f);
            }
            return this.toucan.getBlockGrounding(vector3d);
        }
        if (!this.toucan.m_20096_()) {
            return this.toucan.getBlockGrounding(vector3d);
        }
        if (this.toucan.isOverLeaves()) {
            for (int i = 0; i < 15; ++i) {
                BlockPos pos = this.toucan.m_20183_().m_7918_(EntityToucan.this.f_19796_.m_188503_(16) - 8, EntityToucan.this.f_19796_.m_188503_(8) - 4, EntityToucan.this.f_19796_.m_188503_(16) - 8);
                if (this.toucan.m_9236_().m_8055_(pos.m_7494_()).m_280296_() || !this.toucan.m_9236_().m_8055_(pos).m_280296_() || !(this.toucan.m_21692_(pos) >= 0.0f)) continue;
                return Vec3.m_82539_((Vec3i)pos);
            }
        }
        return LandRandomPos.m_148488_((PathfinderMob)this.toucan, (int)16, (int)7);
    }

    public boolean m_8045_() {
        if (this.toucan.aiItemFlag) {
            return false;
        }
        if (this.flightTarget) {
            return this.toucan.isFlying() && this.toucan.m_20275_(this.x, this.y, this.z) > 2.0;
        }
        return !this.toucan.m_21573_().m_26571_() && !this.toucan.m_20160_();
    }

    public void m_8056_() {
        if (this.flightTarget) {
            this.toucan.setFlying(true);
            this.toucan.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
        } else {
            this.toucan.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
        }
    }

    public void m_8041_() {
        this.toucan.m_21573_().m_26573_();
        super.m_8041_();
    }
}
