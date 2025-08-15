/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityCosmicCod;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private static class EntityCosmicCod.AISwimIdle
extends Goal {
    private final EntityCosmicCod cod;
    float circleDistance = 5.0f;
    boolean clockwise = false;

    public EntityCosmicCod.AISwimIdle(EntityCosmicCod cod) {
        this.cod = cod;
    }

    public boolean m_8036_() {
        return this.cod.isGroupLeader() || this.cod.hasNoLeader() || this.cod.hasGroupLeader() && this.cod.groupLeader.circlePos != null;
    }

    public void m_8037_() {
        if (this.cod.circleTime > this.cod.maxCircleTime) {
            this.cod.circleTime = 0;
            this.cod.circlePos = null;
        }
        if (this.cod.circlePos != null && this.cod.circleTime <= this.cod.maxCircleTime) {
            ++this.cod.circleTime;
            Vec3 movePos = this.getSharkCirclePos(this.cod.circlePos);
            this.cod.m_21566_().m_6849_(movePos.m_7096_(), movePos.m_7098_(), movePos.m_7094_(), 1.0);
        } else if (this.cod.isGroupLeader()) {
            if (this.cod.baitballCooldown == 0) {
                this.cod.resetBaitballCooldown();
                if (this.cod.circlePos == null || this.cod.circleTime >= this.cod.maxCircleTime) {
                    this.cod.circleTime = 0;
                    this.cod.maxCircleTime = 360 + this.cod.f_19796_.m_188503_(80);
                    this.circleDistance = 1.0f + this.cod.f_19796_.m_188501_();
                    this.clockwise = this.cod.f_19796_.m_188499_();
                    this.cod.circlePos = this.cod.m_20183_().m_7494_();
                }
            }
        } else if (this.cod.f_19796_.m_188503_(40) == 0 || this.cod.hasNoLeader()) {
            Vec3 movepos = this.cod.m_20182_().m_82520_((double)(this.cod.f_19796_.m_188503_(4) - 2), this.cod.m_20186_() < 0.0 ? 1.0 : (double)(this.cod.f_19796_.m_188503_(4) - 2), (double)(this.cod.f_19796_.m_188503_(4) - 2));
            this.cod.m_21566_().m_6849_(movepos.f_82479_, movepos.f_82480_, movepos.f_82481_, 1.0);
        } else if (this.cod.hasGroupLeader() && this.cod.groupLeader.circlePos != null && this.cod.circlePos == null) {
            this.cod.circlePos = this.cod.groupLeader.circlePos;
            this.cod.circleTime = this.cod.groupLeader.circleTime;
            this.cod.maxCircleTime = this.cod.groupLeader.maxCircleTime;
            this.circleDistance = 1.0f + this.cod.f_19796_.m_188501_();
            this.clockwise = this.cod.f_19796_.m_188499_();
        }
    }

    public Vec3 getSharkCirclePos(BlockPos target) {
        float prog = 1.0f - (float)this.cod.circleTime / (float)this.cod.maxCircleTime;
        float angle = 0.17453292f * (float)(this.clockwise ? -this.cod.circleTime : this.cod.circleTime);
        float circleDistanceTimesProg = this.circleDistance * prog;
        double extraX = (circleDistanceTimesProg + 0.75f) * Mth.m_14031_((float)angle);
        double extraZ = (circleDistanceTimesProg + 0.75f) * prog * Mth.m_14089_((float)angle);
        return new Vec3((double)((float)target.m_123341_() + 0.5f) + extraX, (double)Math.max(target.m_123342_() + this.cod.f_19796_.m_188503_(4) - 2, -62), (double)((float)target.m_123343_() + 0.5f) + extraZ);
    }
}
