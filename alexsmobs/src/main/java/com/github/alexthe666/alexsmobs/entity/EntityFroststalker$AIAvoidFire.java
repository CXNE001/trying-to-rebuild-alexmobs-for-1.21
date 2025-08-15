/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.util.LandRandomPos
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

private class EntityFroststalker.AIAvoidFire
extends Goal {
    private final int searchLength;
    private final int verticalSearchRange;
    protected BlockPos destinationBlock;
    protected int runDelay = 20;
    private Vec3 fleeTarget;

    private EntityFroststalker.AIAvoidFire() {
        this.searchLength = 20;
        this.verticalSearchRange = 1;
    }

    public boolean m_8045_() {
        return this.destinationBlock != null && this.isFire(EntityFroststalker.this.m_9236_(), this.destinationBlock.m_122032_()) && this.isCloseToFire(16.0);
    }

    public boolean isCloseToFire(double dist) {
        return this.destinationBlock == null || EntityFroststalker.this.m_20238_(Vec3.m_82512_((Vec3i)this.destinationBlock)) < dist * dist;
    }

    public boolean m_8036_() {
        if (this.runDelay > 0) {
            --this.runDelay;
            return false;
        }
        this.runDelay = 30 + EntityFroststalker.this.f_19796_.m_188503_(100);
        return this.searchForDestination();
    }

    public void m_8056_() {
        EntityFroststalker.this.fleeFireFlag = 200;
        Vec3 vec = LandRandomPos.m_148521_((PathfinderMob)EntityFroststalker.this, (int)15, (int)5, (Vec3)Vec3.m_82512_((Vec3i)this.destinationBlock));
        if (vec != null) {
            EntityFroststalker.this.standFor(100 + EntityFroststalker.this.f_19796_.m_188503_(100));
            this.fleeTarget = vec;
            EntityFroststalker.this.m_21573_().m_26519_(vec.f_82479_, vec.f_82480_, vec.f_82481_, (double)1.2f);
        }
    }

    public void m_8037_() {
        if (this.isCloseToFire(16.0)) {
            Vec3 vec;
            EntityFroststalker.this.fleeFireFlag = 200;
            if ((this.fleeTarget == null || EntityFroststalker.this.m_20238_(this.fleeTarget) < 2.0) && (vec = LandRandomPos.m_148521_((PathfinderMob)EntityFroststalker.this, (int)15, (int)5, (Vec3)Vec3.m_82512_((Vec3i)this.destinationBlock))) != null) {
                this.fleeTarget = vec;
            }
            if (this.fleeTarget != null) {
                EntityFroststalker.this.m_21573_().m_26519_(this.fleeTarget.f_82479_, this.fleeTarget.f_82480_, this.fleeTarget.f_82481_, 1.0);
            }
        }
    }

    public void m_8041_() {
        this.fleeTarget = null;
    }

    protected boolean searchForDestination() {
        int lvt_1_1_ = this.searchLength;
        int lvt_2_1_ = this.verticalSearchRange;
        BlockPos lvt_3_1_ = EntityFroststalker.this.m_20183_();
        BlockPos.MutableBlockPos lvt_4_1_ = new BlockPos.MutableBlockPos();
        for (int lvt_5_1_ = -8; lvt_5_1_ <= 2; ++lvt_5_1_) {
            for (int lvt_6_1_ = 0; lvt_6_1_ < lvt_1_1_; ++lvt_6_1_) {
                int lvt_7_1_ = 0;
                while (lvt_7_1_ <= lvt_6_1_) {
                    int lvt_8_1_;
                    int n = lvt_8_1_ = lvt_7_1_ < lvt_6_1_ && lvt_7_1_ > -lvt_6_1_ ? lvt_6_1_ : 0;
                    while (lvt_8_1_ <= lvt_6_1_) {
                        lvt_4_1_.m_122154_((Vec3i)lvt_3_1_, lvt_7_1_, lvt_5_1_ - 1, lvt_8_1_);
                        if (this.isFire(EntityFroststalker.this.m_9236_(), lvt_4_1_)) {
                            this.destinationBlock = lvt_4_1_;
                            return true;
                        }
                        lvt_8_1_ = lvt_8_1_ > 0 ? -lvt_8_1_ : 1 - lvt_8_1_;
                    }
                    lvt_7_1_ = lvt_7_1_ > 0 ? -lvt_7_1_ : 1 - lvt_7_1_;
                }
            }
        }
        return false;
    }

    private boolean isFire(Level world, BlockPos.MutableBlockPos lvt_4_1_) {
        return world.m_8055_((BlockPos)lvt_4_1_).m_204336_(AMTagRegistry.FROSTSTALKER_FEARS);
    }
}
