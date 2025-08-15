/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntityPotoo.AIPerch
extends Goal {
    private BlockPos perch = null;
    private Direction perchDirection = null;
    private int perchingTime = 0;
    private int runCooldown = 0;
    private int pathRecalcTime = 0;

    public EntityPotoo.AIPerch() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        if (EntityPotoo.this.m_5448_() != null && EntityPotoo.this.m_5448_().m_6084_()) {
            return false;
        }
        if (this.runCooldown > 0) {
            --this.runCooldown;
        } else if (!EntityPotoo.this.isPerching() && EntityPotoo.this.perchCooldown == 0 && EntityPotoo.this.f_19796_.m_188503_(35) == 0) {
            this.perchingTime = 0;
            if (EntityPotoo.this.getPerchPos() != null && EntityPotoo.this.isValidPerchFromSide(EntityPotoo.this.getPerchPos(), EntityPotoo.this.getPerchDirection())) {
                this.perch = EntityPotoo.this.getPerchPos();
                this.perchDirection = EntityPotoo.this.getPerchDirection();
            } else {
                this.findPerch();
            }
            this.runCooldown = 120 + EntityPotoo.this.m_217043_().m_188503_(140);
            return this.perch != null && this.perchDirection != null;
        }
        return false;
    }

    private void findPerch() {
        RandomSource random = EntityPotoo.this.m_217043_();
        Direction[] horiz = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
        if (EntityPotoo.this.isValidPerchFromSide(EntityPotoo.this.m_20099_(), EntityPotoo.this.m_6350_())) {
            this.perch = EntityPotoo.this.m_20099_();
            this.perchDirection = EntityPotoo.this.m_6350_();
            return;
        }
        for (Direction dir : horiz) {
            if (!EntityPotoo.this.isValidPerchFromSide(EntityPotoo.this.m_20099_(), dir)) continue;
            this.perch = EntityPotoo.this.m_20099_();
            this.perchDirection = dir;
            return;
        }
        int range = 14;
        for (int i = 0; i < 15; ++i) {
            Direction dir;
            BlockPos blockpos1 = EntityPotoo.this.m_20183_().m_7918_(random.m_188503_(range) - range / 2, 3, random.m_188503_(range) - range / 2);
            if (!EntityPotoo.this.m_9236_().m_46749_(blockpos1)) continue;
            while (EntityPotoo.this.m_9236_().m_46859_(blockpos1) && blockpos1.m_123342_() > -64) {
                blockpos1 = blockpos1.m_7495_();
            }
            dir = Direction.m_122407_((int)random.m_188503_(3));
            if (!EntityPotoo.this.isValidPerchFromSide(blockpos1, dir)) continue;
            this.perch = blockpos1;
            this.perchDirection = dir;
            break;
        }
    }

    public void m_8056_() {
        this.pathRecalcTime = 0;
    }

    public boolean m_8045_() {
        return !(this.perchingTime >= 300 && !EntityPotoo.this.m_9236_().m_46461_() || EntityPotoo.this.m_5448_() != null && EntityPotoo.this.m_5448_().m_6084_() || EntityPotoo.this.m_20159_());
    }

    public void m_8037_() {
        if (EntityPotoo.this.isPerching()) {
            ++this.perchingTime;
            EntityPotoo.this.m_21573_().m_26573_();
            Vec3 block = Vec3.m_82514_((Vec3i)EntityPotoo.this.getPerchPos(), (double)1.0);
            Vec3 onBlock = block.m_82520_((double)((float)EntityPotoo.this.getPerchDirection().m_122429_() * 0.35f), 0.0, (double)((float)EntityPotoo.this.getPerchDirection().m_122431_() * 0.35f));
            double dist = EntityPotoo.this.m_20238_(onBlock);
            Vec3 dirVec = block.m_82546_(EntityPotoo.this.m_20182_());
            if (this.perchingTime > 10 && (dist > (double)2.3f || !EntityPotoo.this.isValidPerchFromSide(EntityPotoo.this.getPerchPos(), EntityPotoo.this.getPerchDirection()))) {
                EntityPotoo.this.setPerching(false);
            } else if (dist > 1.0) {
                EntityPotoo.this.slideTowardsPerch();
                if ((double)((float)EntityPotoo.this.getPerchPos().m_123342_() + 1.2f) > EntityPotoo.this.m_20191_().f_82289_) {
                    EntityPotoo.this.m_20256_(EntityPotoo.this.m_20184_().m_82520_(0.0, (double)0.2f, 0.0));
                }
                float f = -((float)(Mth.m_14136_((double)dirVec.f_82479_, (double)dirVec.f_82481_) * 57.2957763671875));
                EntityPotoo.this.m_146922_(f);
                EntityPotoo.this.f_20885_ = f;
                EntityPotoo.this.f_20883_ = f;
            }
        } else if (this.perch != null) {
            double distZ;
            double distX;
            if (EntityPotoo.this.m_20238_(Vec3.m_82512_((Vec3i)this.perch)) > 100.0) {
                EntityPotoo.this.setFlying(true);
            }
            if ((distX = (double)((float)this.perch.m_123341_() + 0.5f) - EntityPotoo.this.m_20185_()) * distX + (distZ = (double)((float)this.perch.m_123343_() + 0.5f) - EntityPotoo.this.m_20189_()) * distZ < 1.0 || !EntityPotoo.this.isFlying()) {
                if (this.pathRecalcTime <= 0) {
                    this.pathRecalcTime = EntityPotoo.this.m_217043_().m_188503_(30) + 30;
                    EntityPotoo.this.m_21573_().m_26519_((double)((float)this.perch.m_123341_() + 0.5f), (double)((float)this.perch.m_123342_() + 1.5f), (double)((float)this.perch.m_123343_() + 0.5f), 1.0);
                }
                if (EntityPotoo.this.m_21573_().m_26571_()) {
                    EntityPotoo.this.m_21566_().m_6849_((double)((float)this.perch.m_123341_() + 0.5f), (double)((float)this.perch.m_123342_() + 1.5f), (double)((float)this.perch.m_123343_() + 0.5f), 1.0);
                }
            } else if (this.pathRecalcTime <= 0) {
                this.pathRecalcTime = EntityPotoo.this.m_217043_().m_188503_(30) + 30;
                EntityPotoo.this.m_21573_().m_26519_((double)((float)this.perch.m_123341_() + 0.5f), (double)((float)this.perch.m_123342_() + 2.5f), (double)((float)this.perch.m_123343_() + 0.5f), 1.0);
            }
            if (EntityPotoo.this.m_20099_().equals((Object)this.perch)) {
                EntityPotoo.this.m_20256_(Vec3.f_82478_);
                EntityPotoo.this.setPerching(true);
                EntityPotoo.this.setFlying(false);
                EntityPotoo.this.setPerchPos(this.perch);
                EntityPotoo.this.setPerchDirection(this.perchDirection);
                EntityPotoo.this.m_21573_().m_26573_();
                this.perch = null;
            } else {
                EntityPotoo.this.setPerching(false);
            }
        }
        if (this.pathRecalcTime > 0) {
            --this.pathRecalcTime;
        }
    }

    public void m_8041_() {
        EntityPotoo.this.setPerching(false);
        EntityPotoo.this.perchCooldown = 120 + EntityPotoo.this.f_19796_.m_188503_(1200);
        this.perch = null;
        this.perchDirection = null;
    }
}
