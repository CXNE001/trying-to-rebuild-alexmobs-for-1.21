/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntitySoulVulture;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

class EntitySoulVulture.AICirclePerch
extends Goal {
    private final EntitySoulVulture vulture;
    float speed = 1.0f;
    float circlingTime = 0.0f;
    float circleDistance = 5.0f;
    float maxCirclingTime = 80.0f;
    boolean clockwise = false;
    private BlockPos targetPos;
    private int yLevel = 1;

    public EntitySoulVulture.AICirclePerch(EntitySoulVulture vulture) {
        this.vulture = vulture;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        return !this.vulture.shouldSwoop() && this.vulture.m_29443_() && this.vulture.getPerchPos() != null;
    }

    public void m_8056_() {
        this.circlingTime = 0.0f;
        this.speed = 0.8f + EntitySoulVulture.this.f_19796_.m_188501_() * 0.4f;
        this.yLevel = this.vulture.f_19796_.m_188503_(3);
        this.maxCirclingTime = 360 + this.vulture.f_19796_.m_188503_(80);
        this.circleDistance = 5.0f + this.vulture.f_19796_.m_188501_() * 5.0f;
        this.clockwise = this.vulture.f_19796_.m_188499_();
    }

    public void m_8041_() {
        this.circlingTime = 0.0f;
        this.speed = 0.8f + EntitySoulVulture.this.f_19796_.m_188501_() * 0.4f;
        this.yLevel = this.vulture.f_19796_.m_188503_(3);
        this.maxCirclingTime = 360 + this.vulture.f_19796_.m_188503_(80);
        this.circleDistance = 5.0f + this.vulture.f_19796_.m_188501_() * 5.0f;
        this.clockwise = this.vulture.f_19796_.m_188499_();
        this.vulture.tackleCooldown = 0;
    }

    public void m_8037_() {
        BlockPos encircle = this.vulture.getPerchPos();
        double localSpeed = this.speed;
        if (this.vulture.m_5448_() != null) {
            localSpeed *= 1.55;
        }
        if (encircle != null) {
            this.circlingTime += 1.0f;
            if (this.circlingTime > 360.0f) {
                this.vulture.m_21566_().m_6849_((double)encircle.m_123341_() + 0.5, (double)encircle.m_123342_() + 1.1, (double)encircle.m_123343_() + 0.5, localSpeed);
                if (this.vulture.f_19863_ || this.vulture.m_20275_((double)encircle.m_123341_() + 0.5, (double)encircle.m_123342_() + 1.1, (double)encircle.m_123343_() + 0.5) < 1.0) {
                    this.vulture.setFlying(false);
                    this.vulture.m_20256_(Vec3.f_82478_);
                    this.vulture.landingCooldown = 400 + EntitySoulVulture.this.f_19796_.m_188503_(1200);
                    this.m_8041_();
                }
            } else {
                BlockPos circlePos = this.getVultureCirclePos(encircle);
                if (circlePos != null) {
                    this.vulture.m_21566_().m_6849_((double)circlePos.m_123341_() + 0.5, (double)circlePos.m_123342_() + 0.5, (double)circlePos.m_123343_() + 0.5, localSpeed);
                }
            }
        }
    }

    public boolean m_8045_() {
        return this.m_8036_();
    }

    public BlockPos getVultureCirclePos(BlockPos target) {
        float angle = 0.05235988f * (this.clockwise ? -this.circlingTime : this.circlingTime);
        double extraX = this.circleDistance * Mth.m_14031_((float)angle);
        double extraZ = this.circleDistance * Mth.m_14089_((float)angle);
        BlockPos pos = new BlockPos((int)((double)target.m_123341_() + extraX), target.m_123342_() + 1 + this.yLevel, (int)((double)target.m_123343_() + extraZ));
        if (this.vulture.m_9236_().m_46859_(pos)) {
            return pos;
        }
        return null;
    }
}
