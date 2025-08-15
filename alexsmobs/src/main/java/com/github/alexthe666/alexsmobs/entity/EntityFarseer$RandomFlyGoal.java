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

import com.github.alexthe666.alexsmobs.entity.EntityFarseer;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private static class EntityFarseer.RandomFlyGoal
extends Goal {
    private final EntityFarseer parentEntity;
    private BlockPos target = null;
    private final float speed = 0.6f;

    public EntityFarseer.RandomFlyGoal(EntityFarseer mosquito) {
        this.parentEntity = mosquito;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        if (this.parentEntity.m_21573_().m_26571_() && this.parentEntity.m_5448_() == null && this.parentEntity.m_217043_().m_188503_(4) == 0) {
            this.target = this.getBlockInViewFarseer();
            if (this.target != null) {
                this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, (double)0.6f);
                return true;
            }
        }
        return false;
    }

    public boolean m_8045_() {
        return this.target != null && this.parentEntity.m_5448_() == null;
    }

    public void m_8041_() {
        this.target = null;
    }

    public void m_8037_() {
        if (this.target != null) {
            this.parentEntity.m_21566_().m_6849_((double)this.target.m_123341_() + 0.5, (double)this.target.m_123342_() + 0.5, (double)this.target.m_123343_() + 0.5, (double)0.6f);
            if (this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)this.target)) < 4.0 || this.parentEntity.f_19862_) {
                this.target = null;
            }
        }
    }

    private BlockPos getFarseerGround(BlockPos in) {
        BlockPos position = new BlockPos(in.m_123341_(), (int)this.parentEntity.m_20186_(), in.m_123343_());
        while (position.m_123342_() < 256 && !this.parentEntity.m_9236_().m_6425_(position).m_76178_()) {
            position = position.m_7494_();
        }
        while (position.m_123342_() > 1 && this.parentEntity.m_9236_().m_46859_(position)) {
            position = position.m_7495_();
        }
        return position;
    }

    public BlockPos getBlockInViewFarseer() {
        float radius = 5 + this.parentEntity.m_217043_().m_188503_(10);
        float neg = this.parentEntity.m_217043_().m_188499_() ? 1.0f : -1.0f;
        float renderYawOffset = this.parentEntity.m_146908_();
        float angle = (float)Math.PI / 180 * renderYawOffset + 3.15f * (this.parentEntity.m_217043_().m_188501_() * neg);
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = new BlockPos((int)(this.parentEntity.m_20185_() + extraX), (int)this.parentEntity.m_20186_(), (int)(this.parentEntity.m_20189_() + extraZ));
        BlockPos ground = this.getFarseerGround(radialPos).m_6630_(2 + this.parentEntity.f_19796_.m_188503_(2));
        if (!this.parentEntity.isTargetBlocked(Vec3.m_82512_((Vec3i)ground.m_7494_()))) {
            return ground;
        }
        return null;
    }
}
