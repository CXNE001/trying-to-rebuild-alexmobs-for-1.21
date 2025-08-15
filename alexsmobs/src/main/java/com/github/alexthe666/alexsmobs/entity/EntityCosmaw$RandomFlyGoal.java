/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityCosmaw;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

static class EntityCosmaw.RandomFlyGoal
extends Goal {
    private final EntityCosmaw parentEntity;
    private BlockPos target = null;

    public EntityCosmaw.RandomFlyGoal(EntityCosmaw mosquito) {
        this.parentEntity = mosquito;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        if (this.parentEntity.m_21573_().m_26571_() && this.parentEntity.shouldWander() && this.parentEntity.m_5448_() == null && this.parentEntity.m_217043_().m_188503_(4) == 0) {
            this.target = this.getBlockInViewCosmaw();
            if (this.target != null) {
                this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, 1.0);
                return true;
            }
        }
        return false;
    }

    public boolean m_8045_() {
        return this.target != null && this.parentEntity.shouldWander() && this.parentEntity.m_5448_() == null;
    }

    public void m_8041_() {
        this.target = null;
    }

    public void m_8037_() {
        if (this.target != null) {
            this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, 1.0);
            if (this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)this.target)) < 4.0 || this.parentEntity.f_19862_) {
                this.target = null;
            }
        }
    }

    public BlockPos getBlockInViewCosmaw() {
        float radius = 5 + this.parentEntity.m_217043_().m_188503_(10);
        float neg = this.parentEntity.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.parentEntity.m_146908_();
        float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f * (this.parentEntity.m_217043_().m_188501_() * neg);
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = AMBlockPos.fromCoords(this.parentEntity.m_20185_() + extraX, this.parentEntity.m_20186_(), this.parentEntity.m_20189_() + extraZ);
        BlockPos ground = this.parentEntity.getCosmawGround(radialPos);
        if (!this.parentEntity.isTargetBlocked(Vec3.m_82512_((Vec3i)(ground = ground.m_123342_() <= 1 ? ground.m_6630_(70 + this.parentEntity.f_19796_.m_188503_(4)) : ground.m_6630_(2 + this.parentEntity.f_19796_.m_188503_(2))).m_7494_()))) {
            return ground;
        }
        return null;
    }
}
