/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityFlyingFish;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

private class EntityFlyingFish.GlideGoal
extends Goal {
    private final EntityFlyingFish fish;
    private final Level level;
    private BlockPos surface;
    private BlockPos glide;

    public EntityFlyingFish.GlideGoal(EntityFlyingFish fish) {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.fish = fish;
        this.level = fish.m_9236_();
    }

    public boolean m_8036_() {
        BlockPos glideTo;
        BlockPos found;
        if (!this.fish.m_20072_()) {
            return false;
        }
        if ((this.fish.glideIn == 0 || this.fish.m_217043_().m_188503_(80) == 0) && (found = this.findSurfacePos()) != null && (glideTo = this.findGlideToPos(this.fish.m_20183_(), found)) != null) {
            this.surface = found;
            this.glide = glideTo;
            this.fish.glideIn = 0;
            return true;
        }
        return false;
    }

    private BlockPos findSurfacePos() {
        BlockPos fishPos = this.fish.m_20183_();
        for (int i = 0; i < 15; ++i) {
            BlockPos offset = fishPos.m_7918_(this.fish.f_19796_.m_188503_(16) - 8, 0, this.fish.f_19796_.m_188503_(16) - 8);
            while (this.level.m_46801_(offset) && offset.m_123342_() < this.level.m_151558_()) {
                offset = offset.m_7494_();
            }
            if (this.level.m_46801_(offset) || !this.level.m_46801_(offset.m_7495_()) || !this.fish.canSeeBlock(offset)) continue;
            return offset;
        }
        return null;
    }

    private BlockPos findGlideToPos(BlockPos fishPos, BlockPos surface) {
        Vec3 sub = Vec3.m_82528_((Vec3i)surface.m_121996_((Vec3i)fishPos)).m_82541_();
        for (double scale = EntityFlyingFish.this.f_19796_.m_188500_() * 8.0 + 1.0; scale > 2.0; scale -= 1.0) {
            Vec3 scaled = sub.m_82490_(scale);
            BlockPos at = surface.m_7918_((int)scaled.f_82479_, 0, (int)scaled.f_82481_);
            if (this.level.m_46801_(at) || !this.level.m_46801_(at.m_7495_()) || !this.fish.canSeeBlock(at)) continue;
            return at;
        }
        return null;
    }

    public boolean m_8045_() {
        return this.surface != null && this.glide != null && (!this.fish.m_20096_() || this.fish.m_20072_());
    }

    public void m_8056_() {
    }

    public void m_8041_() {
        this.surface = null;
        this.glide = null;
        this.fish.glideIn = EntityFlyingFish.this.f_19796_.m_188503_(75) + 150;
        this.fish.setGliding(false);
    }

    public void m_8037_() {
        if (this.fish.m_20072_() && this.fish.m_20238_(Vec3.m_82512_((Vec3i)this.surface)) > 3.0) {
            this.fish.m_21573_().m_26519_((double)((float)this.surface.m_123341_() + 0.5f), (double)((float)this.surface.m_123342_() + 1.0f), (double)((float)this.surface.m_123343_() + 0.5f), (double)1.2f);
            if (this.fish.isGliding()) {
                this.m_8041_();
            }
        } else {
            this.fish.m_21573_().m_26573_();
            Vec3 face = Vec3.m_82512_((Vec3i)this.glide).m_82546_(Vec3.m_82512_((Vec3i)this.surface));
            if (face.m_82553_() < (double)0.2f) {
                face = this.fish.m_20154_();
            }
            Vec3 target = face.m_82541_().m_82490_((double)0.1f);
            double y = 0.0;
            if (!this.fish.isGliding()) {
                y = 0.4f + EntityFlyingFish.this.f_19796_.m_188501_() * 0.2f;
            } else if (this.fish.isGliding() && this.fish.m_20072_()) {
                this.m_8041_();
            }
            Vec3 move = this.fish.m_20184_().m_82520_(target.f_82479_, y, target.f_82480_);
            this.fish.m_20256_(move);
            double d0 = move.m_165924_();
            this.fish.m_146926_((float)(-Mth.m_14136_((double)move.f_82480_, (double)d0) * 57.2957763671875));
            this.fish.m_146922_((float)Mth.m_14136_((double)move.f_82481_, (double)move.f_82479_) * 57.295776f - 90.0f);
            this.fish.setGliding(true);
        }
    }
}
