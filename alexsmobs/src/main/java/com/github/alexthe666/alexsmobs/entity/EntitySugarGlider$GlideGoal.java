/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.Vec3i
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

private class EntitySugarGlider.GlideGoal
extends Goal {
    private boolean climbing;
    private int climbTime = 0;
    private int leapSearchCooldown = 0;
    private int climbTimeout = 0;
    private BlockPos climb;
    private BlockPos glide;
    private boolean itsOver = false;
    private int airtime = 0;
    private Direction climbOffset = Direction.UP;

    private EntitySugarGlider.GlideGoal() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        if (EntitySugarGlider.this.getForagingTime() <= 0 && !EntitySugarGlider.this.m_6162_() && !EntitySugarGlider.this.m_21827_() && EntitySugarGlider.this.m_217043_().m_188503_(45) == 0) {
            this.climb = EntitySugarGlider.this.getAttachmentFacing() != Direction.DOWN ? EntitySugarGlider.this.m_20183_().m_121945_(EntitySugarGlider.this.getAttachmentFacing()) : this.findClimbPos();
            return this.climb != null;
        }
        return false;
    }

    public boolean m_8045_() {
        return this.climb != null && !this.itsOver && this.climbTimeout < 30 && (!this.climbing || !EntitySugarGlider.this.m_9236_().m_46859_(this.climb) && !EntitySugarGlider.this.m_21573_().m_26577_()) && EntitySugarGlider.this.getForagingTime() <= 0 && !EntitySugarGlider.this.m_21827_();
    }

    public void m_8056_() {
        this.climbTimeout = 0;
        this.leapSearchCooldown = 0;
        this.airtime = 0;
        this.climbing = true;
        this.climbTime = 0;
        EntitySugarGlider.this.m_21573_().m_26573_();
    }

    public void m_8041_() {
        this.climbTimeout = 0;
        this.climb = null;
        this.glide = null;
        this.itsOver = false;
        EntitySugarGlider.this.stopClimbing = false;
        EntitySugarGlider.this.setGliding(false);
        EntitySugarGlider.this.m_21573_().m_26573_();
    }

    public void m_8037_() {
        if (this.leapSearchCooldown > 0) {
            --this.leapSearchCooldown;
        }
        if (this.climbing) {
            float inDir = EntitySugarGlider.this.getAttachmentFacing() == Direction.DOWN && EntitySugarGlider.this.m_20186_() > (double)((float)this.climb.m_123342_() + 0.3f) ? 0.5f + EntitySugarGlider.this.m_20205_() * 0.5f : 0.5f;
            Vec3 offset = Vec3.m_82512_((Vec3i)this.climb).m_82492_(0.0, 0.0, 0.0).m_82520_((double)((float)this.climbOffset.m_122429_() * inDir), (double)((float)this.climbOffset.m_122430_() * inDir), (double)((float)this.climbOffset.m_122431_() * inDir));
            double d0 = (double)((float)this.climb.m_123341_() + 0.5f) - EntitySugarGlider.this.m_20185_();
            double d2 = (double)((float)this.climb.m_123343_() + 0.5f) - EntitySugarGlider.this.m_20189_();
            double xzDistSqr = d0 * d0 + d2 * d2;
            if (EntitySugarGlider.this.m_20186_() > offset.f_82480_ - (double)0.3f - (double)EntitySugarGlider.this.m_20206_()) {
                EntitySugarGlider.this.stopClimbing = true;
            }
            if (xzDistSqr < 3.0 && EntitySugarGlider.this.getAttachmentFacing() != Direction.DOWN) {
                Vec3 silly = new Vec3(d0, 0.0, d2).m_82541_().m_82490_(0.1);
                EntitySugarGlider.this.m_20256_(EntitySugarGlider.this.m_20184_().m_82549_(silly));
            } else {
                EntitySugarGlider.this.m_21573_().m_26519_(offset.f_82479_, offset.f_82480_, offset.f_82481_, 1.0);
            }
            if (EntitySugarGlider.this.getAttachmentFacing() == Direction.DOWN) {
                ++this.climbTimeout;
                this.climbTime = 0;
            } else {
                this.climbTimeout = 0;
                ++this.climbTime;
                if (this.climbTime > 40 && this.leapSearchCooldown == 0) {
                    BlockPos leapTo = this.findLeapPos(EntitySugarGlider.this.shouldForage() && EntitySugarGlider.this.f_19796_.m_188503_(5) != 0);
                    this.leapSearchCooldown = 5 + EntitySugarGlider.this.m_217043_().m_188503_(10);
                    if (leapTo != null) {
                        EntitySugarGlider.this.stopClimbing = false;
                        EntitySugarGlider.this.setGliding(true);
                        EntitySugarGlider.this.m_21573_().m_26573_();
                        EntitySugarGlider.this.f_19804_.m_135381_(ATTACHED_FACE, (Object)Direction.DOWN);
                        this.glide = leapTo;
                        this.climbing = false;
                    }
                }
            }
        } else if (this.glide != null) {
            EntitySugarGlider.this.stopClimbing = false;
            EntitySugarGlider.this.setGliding(true);
            if (this.airtime > 5 && (EntitySugarGlider.this.f_19862_ || EntitySugarGlider.this.m_20096_() || Math.sqrt(EntitySugarGlider.this.m_20238_(Vec3.m_82512_((Vec3i)this.glide))) < (double)1.1f)) {
                EntitySugarGlider.this.setGliding(false);
                EntitySugarGlider.this.detachCooldown = 20 + EntitySugarGlider.this.f_19796_.m_188503_(80);
                this.itsOver = true;
            }
            Vec3 fly = Vec3.m_82512_((Vec3i)this.glide).m_82546_(EntitySugarGlider.this.m_20182_()).m_82541_().m_82490_((double)0.3f);
            EntitySugarGlider.this.m_20256_(fly);
            Vec3 move = EntitySugarGlider.this.m_20184_();
            double d0 = move.m_165924_();
            EntitySugarGlider.this.m_146926_((float)(-Mth.m_14136_((double)move.f_82480_, (double)d0) * 57.2957763671875));
            EntitySugarGlider.this.m_146922_((float)Mth.m_14136_((double)move.f_82481_, (double)move.f_82479_) * 57.295776f - 90.0f);
            ++this.airtime;
        }
    }

    private BlockPos findClimbPos() {
        BlockPos mobPos = EntitySugarGlider.this.m_20183_();
        for (int i = 0; i < 15; ++i) {
            BlockPos offset = mobPos.m_7918_(EntitySugarGlider.this.f_19796_.m_188503_(16) - 8, EntitySugarGlider.this.f_19796_.m_188503_(4) + 1, EntitySugarGlider.this.f_19796_.m_188503_(16) - 8);
            double d0 = (double)((float)offset.m_123341_() + 0.5f) - EntitySugarGlider.this.m_20185_();
            double d2 = (double)((float)offset.m_123343_() + 0.5f) - EntitySugarGlider.this.m_20189_();
            double xzDistSqr = d0 * d0 + d2 * d2;
            Vec3 blockVec = Vec3.m_82512_((Vec3i)offset);
            BlockHitResult result = EntitySugarGlider.this.m_9236_().m_45547_(new ClipContext(EntitySugarGlider.this.m_146892_(), blockVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)EntitySugarGlider.this));
            if (result.m_6662_() == HitResult.Type.MISS || !(xzDistSqr > 4.0) || result.m_82434_().m_122434_() == Direction.Axis.Y || this.getDistanceOffGround(result.m_82425_().m_121945_(result.m_82434_())) <= 3 || !this.isPositionEasilyClimbable(result.m_82425_())) continue;
            this.climbOffset = result.m_82434_();
            return result.m_82425_();
        }
        return null;
    }

    private BlockPos findLeapPos(boolean leavesOnly) {
        BlockPos mobPos = EntitySugarGlider.this.m_20183_().m_121945_(this.climbOffset.m_122424_());
        for (int i = 0; i < 15; ++i) {
            BlockPos offset = mobPos.m_7918_(EntitySugarGlider.this.f_19796_.m_188503_(32) - 16, -1 - EntitySugarGlider.this.f_19796_.m_188503_(4), EntitySugarGlider.this.f_19796_.m_188503_(32) - 16);
            Vec3 blockVec = Vec3.m_82512_((Vec3i)offset);
            BlockHitResult result = EntitySugarGlider.this.m_9236_().m_45547_(new ClipContext(EntitySugarGlider.this.m_146892_(), blockVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)EntitySugarGlider.this));
            if (result.m_6662_() == HitResult.Type.MISS || !(result.m_82425_().m_123331_((Vec3i)mobPos) > 4.0) || leavesOnly && !EntitySugarGlider.this.m_9236_().m_8055_(result.m_82425_()).m_204336_(BlockTags.f_13035_)) continue;
            return result.m_82425_();
        }
        return null;
    }

    private int getDistanceOffGround(BlockPos pos) {
        int dist = 0;
        while (pos.m_123342_() > -64 && EntitySugarGlider.this.m_9236_().m_46859_(pos)) {
            pos = pos.m_7495_();
            ++dist;
        }
        return dist;
    }

    private boolean isPositionEasilyClimbable(BlockPos pos) {
        pos = pos.m_7495_();
        while ((double)pos.m_123342_() > EntitySugarGlider.this.m_20186_() && !EntitySugarGlider.this.m_9236_().m_46859_(pos)) {
            pos = pos.m_7495_();
        }
        return (double)pos.m_123342_() <= EntitySugarGlider.this.m_20186_();
    }
}
