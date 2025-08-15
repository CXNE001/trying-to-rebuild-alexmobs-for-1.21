/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityCrimsonMosquito;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

static class EntityCrimsonMosquito.RandomFlyGoal
extends Goal {
    private final EntityCrimsonMosquito parentEntity;
    private BlockPos target = null;

    public EntityCrimsonMosquito.RandomFlyGoal(EntityCrimsonMosquito mosquito) {
        this.parentEntity = mosquito;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        MoveControl movementcontroller = this.parentEntity.m_21566_();
        if (!this.parentEntity.isFlying() || this.parentEntity.m_5448_() != null || this.parentEntity.hasLuringLaviathan() || this.parentEntity.getFleeingEntityId() != -1) {
            return false;
        }
        if (!movementcontroller.m_24995_() || this.target == null) {
            this.target = this.getBlockInViewMosquito();
            if (this.target != null) {
                this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, 1.0);
            }
            return true;
        }
        return false;
    }

    public boolean m_8045_() {
        return this.target != null && this.parentEntity.isFlying() && this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)this.target)) > 2.4 && this.parentEntity.m_21566_().m_24995_() && !this.parentEntity.f_19862_;
    }

    public void m_8041_() {
        this.target = null;
    }

    public void m_8037_() {
        if (this.target == null) {
            this.target = this.getBlockInViewMosquito();
        }
        if (this.target != null) {
            this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, 1.0);
            if (this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)this.target)) < 2.5) {
                this.target = null;
            }
        }
    }

    public BlockPos getBlockInViewMosquito() {
        float radius = 1 + this.parentEntity.m_217043_().m_188503_(5);
        float neg = this.parentEntity.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.parentEntity.f_20883_;
        float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f + this.parentEntity.m_217043_().m_188501_() * neg;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = AMBlockPos.fromCoords(this.parentEntity.m_20185_() + extraX, this.parentEntity.m_20186_() + 2.0, this.parentEntity.m_20189_() + extraZ);
        BlockPos ground = this.parentEntity.getGroundPosition(radialPos);
        int up = this.parentEntity.isSick() ? 2 : 6;
        BlockPos newPos = ground.m_6630_(1 + this.parentEntity.m_217043_().m_188503_(up));
        if (!this.parentEntity.isTargetBlocked(Vec3.m_82512_((Vec3i)newPos)) && this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)newPos)) > 6.0) {
            return newPos;
        }
        return null;
    }
}
