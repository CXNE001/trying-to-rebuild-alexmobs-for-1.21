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

import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

private class EntityBlueJay.AIFlyIdle
extends Goal {
    protected double x;
    protected double y;
    protected double z;
    private boolean flightTarget;

    public EntityBlueJay.AIFlyIdle() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        if (EntityBlueJay.this.m_20160_() || EntityBlueJay.this.m_5448_() != null && EntityBlueJay.this.m_5448_().m_6084_() || EntityBlueJay.this.m_20159_() || EntityBlueJay.this.aiItemFlag || EntityBlueJay.this.getSingTime() > 0) {
            return false;
        }
        if (EntityBlueJay.this.m_217043_().m_188503_(45) != 0 && !EntityBlueJay.this.isFlying()) {
            return false;
        }
        this.flightTarget = EntityBlueJay.this.m_20096_() ? EntityBlueJay.this.f_19796_.m_188499_() : EntityBlueJay.this.f_19796_.m_188503_(5) > 0 && EntityBlueJay.this.timeFlying < 200;
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
            EntityBlueJay.this.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
        } else {
            EntityBlueJay.this.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
        }
        if (!this.flightTarget && EntityBlueJay.this.isFlying() && EntityBlueJay.this.m_20096_()) {
            EntityBlueJay.this.setFlying(false);
        }
        if (EntityBlueJay.this.isFlying() && EntityBlueJay.this.m_20096_() && EntityBlueJay.this.timeFlying > 10) {
            EntityBlueJay.this.setFlying(false);
        }
    }

    @Nullable
    protected Vec3 getPosition() {
        Vec3 vector3d = EntityBlueJay.this.m_20182_();
        if (EntityBlueJay.this.isOverWaterOrVoid()) {
            this.flightTarget = true;
        }
        if (this.flightTarget) {
            if (EntityBlueJay.this.timeFlying < 200 || EntityBlueJay.this.isOverWaterOrVoid()) {
                return EntityBlueJay.this.getBlockInViewAway(vector3d, 0.0f);
            }
            return EntityBlueJay.this.getBlockGrounding(vector3d);
        }
        return LandRandomPos.m_148488_((PathfinderMob)EntityBlueJay.this, (int)10, (int)7);
    }

    public boolean m_8045_() {
        if (this.flightTarget) {
            return EntityBlueJay.this.isFlying() && EntityBlueJay.this.m_20275_(this.x, this.y, this.z) > 5.0;
        }
        return !EntityBlueJay.this.m_21573_().m_26571_() && !EntityBlueJay.this.m_20160_();
    }

    public void m_8056_() {
        if (this.flightTarget) {
            EntityBlueJay.this.setFlying(true);
            EntityBlueJay.this.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
        } else {
            EntityBlueJay.this.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
        }
    }

    public void m_8041_() {
        EntityBlueJay.this.m_21573_().m_26573_();
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        super.m_8041_();
    }
}
