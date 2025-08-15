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

import com.github.alexthe666.alexsmobs.entity.EntityBaldEagle;
import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

private class EntityBaldEagle.AIWanderIdle
extends Goal {
    protected final EntityBaldEagle eagle;
    protected double x;
    protected double y;
    protected double z;
    private boolean flightTarget = false;
    private int orbitResetCooldown = 0;
    private int maxOrbitTime = 360;
    private int orbitTime = 0;

    public EntityBaldEagle.AIWanderIdle() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.eagle = EntityBaldEagle.this;
    }

    public boolean m_8036_() {
        if (this.orbitResetCooldown < 0) {
            ++this.orbitResetCooldown;
        }
        if (this.eagle.m_5448_() != null && this.eagle.m_5448_().m_6084_() && !this.eagle.m_20160_() || this.eagle.m_20159_() || this.eagle.isSitting() || this.eagle.controlledFlag) {
            return false;
        }
        if (this.eagle.m_217043_().m_188503_(15) != 0 && !this.eagle.isFlying()) {
            return false;
        }
        if (this.eagle.m_6162_()) {
            this.flightTarget = false;
        } else if (this.eagle.m_20072_()) {
            this.flightTarget = true;
        } else if (this.eagle.hasCap()) {
            this.flightTarget = false;
        } else if (this.eagle.m_20096_()) {
            this.flightTarget = EntityBaldEagle.this.f_19796_.m_188499_();
        } else {
            if (this.orbitResetCooldown == 0 && EntityBaldEagle.this.f_19796_.m_188503_(6) == 0) {
                this.orbitResetCooldown = 400;
                this.eagle.orbitPos = this.eagle.m_20183_();
                this.eagle.orbitDist = 4 + EntityBaldEagle.this.f_19796_.m_188503_(5);
                this.eagle.orbitClockwise = EntityBaldEagle.this.f_19796_.m_188499_();
                this.orbitTime = 0;
                this.maxOrbitTime = (int)(360.0f + 360.0f * EntityBaldEagle.this.f_19796_.m_188501_());
            }
            this.flightTarget = this.eagle.m_20160_() || EntityBaldEagle.this.f_19796_.m_188503_(7) > 0 && this.eagle.timeFlying < 700;
        }
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
        if (this.orbitResetCooldown > 0) {
            --this.orbitResetCooldown;
        }
        if (this.orbitResetCooldown < 0) {
            ++this.orbitResetCooldown;
        }
        if (this.orbitResetCooldown > 0 && this.eagle.orbitPos != null) {
            if (this.orbitTime < this.maxOrbitTime && !this.eagle.m_20072_()) {
                ++this.orbitTime;
            } else {
                this.orbitTime = 0;
                this.eagle.orbitPos = null;
                this.orbitResetCooldown = -400 - EntityBaldEagle.this.f_19796_.m_188503_(400);
            }
        }
        if (this.eagle.f_19862_ && !this.eagle.m_20096_()) {
            this.m_8041_();
        }
        if (this.flightTarget) {
            this.eagle.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
        } else if (!this.eagle.m_20096_() && this.eagle.isFlying()) {
            if (!this.eagle.m_20072_()) {
                this.eagle.m_20256_(this.eagle.m_20184_().m_82542_((double)1.2f, (double)0.6f, (double)1.2f));
            }
        } else {
            this.eagle.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
        }
        if (!this.flightTarget && this.eagle.m_20096_() && EntityBaldEagle.this.isFlying()) {
            this.eagle.setFlying(false);
            this.orbitTime = 0;
            this.eagle.orbitPos = null;
            this.orbitResetCooldown = -400 - EntityBaldEagle.this.f_19796_.m_188503_(400);
        }
        if (this.eagle.timeFlying > 30 && EntityBaldEagle.this.isFlying() && (!EntityBaldEagle.this.m_9236_().m_46859_(this.eagle.m_20099_()) || this.eagle.m_20096_()) && !this.eagle.m_20072_()) {
            this.eagle.setFlying(false);
            this.orbitTime = 0;
            this.eagle.orbitPos = null;
            this.orbitResetCooldown = -400 - EntityBaldEagle.this.f_19796_.m_188503_(400);
        }
    }

    @Nullable
    protected Vec3 getPosition() {
        Vec3 vector3d = this.eagle.m_20182_();
        if (this.eagle.m_21824_() && this.eagle.getCommand() == 1 && this.eagle.m_269323_() != null) {
            vector3d = this.eagle.m_269323_().m_20182_();
            this.eagle.orbitPos = this.eagle.m_269323_().m_20183_();
        }
        if (this.orbitResetCooldown > 0 && this.eagle.orbitPos != null) {
            return this.eagle.getOrbitVec(vector3d, 4 + EntityBaldEagle.this.f_19796_.m_188503_(2));
        }
        if (this.eagle.m_20160_() || this.eagle.isOverWaterOrVoid()) {
            this.flightTarget = true;
        }
        if (this.flightTarget) {
            if (this.eagle.timeFlying < 500 || this.eagle.m_20160_() || this.eagle.isOverWaterOrVoid()) {
                return this.eagle.getBlockInViewAway(vector3d, 0.0f);
            }
            return this.eagle.getBlockGrounding(vector3d);
        }
        return LandRandomPos.m_148488_((PathfinderMob)this.eagle, (int)10, (int)7);
    }

    public boolean m_8045_() {
        if (this.eagle.isSitting()) {
            return false;
        }
        if (this.flightTarget) {
            return this.eagle.isFlying() && this.eagle.m_20275_(this.x, this.y, this.z) > 2.0;
        }
        return !this.eagle.m_21573_().m_26571_() && !this.eagle.m_20160_();
    }

    public void m_8056_() {
        if (this.flightTarget) {
            this.eagle.setFlying(true);
            this.eagle.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
        } else {
            this.eagle.m_21573_().m_26519_(this.x, this.y, this.z, 1.0);
        }
    }

    public void m_8041_() {
        this.eagle.m_21573_().m_26573_();
        super.m_8041_();
    }
}
