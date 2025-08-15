/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityFroststalker;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.Vec3;

public class FroststalkerAIMelee
extends Goal {
    private final EntityFroststalker froststalker;
    private boolean willJump = false;
    private boolean hasJumped = false;
    private boolean clockwise = false;
    private int pursuitTime = 0;
    private int maxPursuitTime = 0;
    private BlockPos pursuitPos = null;
    private int startingOrbit = 0;

    public FroststalkerAIMelee(EntityFroststalker froststalker) {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.froststalker = froststalker;
    }

    public boolean m_8036_() {
        if (this.froststalker.m_5448_() != null && this.froststalker.m_5448_().m_6084_()) {
            if (this.froststalker.isValidLeader(this.froststalker.m_5448_())) {
                return this.froststalker.m_21188_() != null && this.froststalker.m_21188_().equals((Object)this.froststalker.m_5448_());
            }
            return !this.froststalker.isFleeingFire();
        }
        return false;
    }

    public boolean m_8045_() {
        LivingEntity target = this.froststalker.m_5448_();
        return target != null && !this.froststalker.isValidLeader(target);
    }

    public void m_8056_() {
        this.willJump = this.froststalker.m_217043_().m_188503_(2) == 0;
        this.hasJumped = false;
        this.clockwise = this.froststalker.m_217043_().m_188499_();
        this.pursuitPos = null;
        this.pursuitTime = 0;
        this.maxPursuitTime = 40 + this.froststalker.m_217043_().m_188503_(40);
        this.startingOrbit = this.froststalker.m_217043_().m_188503_(360);
        this.froststalker.frostJump();
    }

    public void m_8037_() {
        this.froststalker.setBipedal(true);
        this.froststalker.standFor(20);
        LivingEntity target = this.froststalker.m_5448_();
        boolean flag = false;
        if ((this.hasJumped || this.froststalker.isTackling()) && this.froststalker.m_20096_()) {
            this.hasJumped = false;
            this.willJump = false;
            this.froststalker.setTackling(false);
        }
        if (target != null && target.m_6084_()) {
            if (this.pursuitTime < this.maxPursuitTime) {
                ++this.pursuitTime;
                this.pursuitPos = this.getBlockNearTarget(target);
                float extraSpeed = 0.2f * Math.max(5.0f - this.froststalker.m_20270_((Entity)target), 0.0f);
                if (this.pursuitPos != null) {
                    this.froststalker.m_21573_().m_26519_((double)this.pursuitPos.m_123341_(), (double)this.pursuitPos.m_123342_(), (double)this.pursuitPos.m_123343_(), (double)(1.0f + extraSpeed));
                } else {
                    this.froststalker.m_21573_().m_5624_((Entity)target, 1.0);
                }
            } else if (this.willJump && this.pursuitTime == this.maxPursuitTime) {
                this.froststalker.m_21391_((Entity)target, 180.0f, 10.0f);
                if (this.froststalker.m_20270_((Entity)target) > 10.0f) {
                    this.froststalker.m_21573_().m_5624_((Entity)target, 1.0);
                } else if (this.froststalker.m_20096_() && this.froststalker.m_142582_((Entity)target)) {
                    this.froststalker.setTackling(true);
                    this.hasJumped = true;
                    Vec3 vector3d = this.froststalker.m_20184_();
                    Vec3 vector3d1 = new Vec3(target.m_20185_() - this.froststalker.m_20185_(), 0.0, target.m_20189_() - this.froststalker.m_20189_());
                    if (vector3d1.m_82556_() > 1.0E-7) {
                        vector3d1 = vector3d1.m_82541_().m_82490_(0.9).m_82549_(vector3d.m_82490_(0.8));
                    }
                    this.froststalker.m_20334_(vector3d1.f_82479_, 0.6f, vector3d1.f_82481_);
                } else {
                    flag = true;
                }
            } else if (!this.froststalker.isTackling()) {
                this.froststalker.m_21573_().m_5624_((Entity)target, 1.0);
            }
            if (this.froststalker.isTackling() && this.froststalker.m_20270_((Entity)target) <= this.froststalker.m_20205_() + target.m_20205_() + 1.1f && this.froststalker.m_142582_((Entity)target)) {
                target.m_6469_(this.froststalker.m_269291_().m_269333_((LivingEntity)this.froststalker), (float)this.froststalker.m_21133_(Attributes.f_22281_));
                this.m_8056_();
            }
            if (!flag && this.froststalker.m_20270_((Entity)target) <= this.froststalker.m_20205_() + target.m_20205_() + 1.1f && this.froststalker.m_142582_((Entity)target) && this.pursuitTime == this.maxPursuitTime) {
                if (!this.froststalker.isTackling()) {
                    this.froststalker.m_7327_((Entity)target);
                }
                this.m_8056_();
            }
        }
        if (target != null && !this.froststalker.m_20096_()) {
            this.froststalker.m_21391_((Entity)target, 180.0f, 10.0f);
            this.froststalker.f_20883_ = this.froststalker.m_146908_();
        }
    }

    public BlockPos getBlockNearTarget(LivingEntity target) {
        float radius = (float)(this.froststalker.m_217043_().m_188503_(5) + 3) + target.m_20205_();
        int orbit = (int)((float)this.startingOrbit + (float)this.pursuitTime / (float)this.maxPursuitTime * 360.0f);
        float angle = (float)Math.PI / 180 * (float)(this.clockwise ? -orbit : orbit);
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos circlePos = AMBlockPos.fromCoords(target.m_20185_() + extraX, target.m_20188_(), target.m_20189_() + extraZ);
        while (!this.froststalker.m_9236_().m_8055_(circlePos).m_60795_() && circlePos.m_123342_() < this.froststalker.m_9236_().m_151558_()) {
            circlePos = circlePos.m_7494_();
        }
        while (!this.froststalker.m_9236_().m_8055_(circlePos.m_7495_()).m_60634_((BlockGetter)this.froststalker.m_9236_(), circlePos.m_7495_(), (Entity)this.froststalker) && circlePos.m_123342_() > 1) {
            circlePos = circlePos.m_7495_();
        }
        if (this.froststalker.m_21692_(circlePos) > -1.0f) {
            return circlePos;
        }
        return null;
    }

    public void m_8041_() {
        this.froststalker.setTackling(false);
    }
}
