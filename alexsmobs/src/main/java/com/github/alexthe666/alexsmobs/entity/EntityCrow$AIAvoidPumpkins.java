/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

private class EntityCrow.AIAvoidPumpkins
extends Goal {
    private final int searchLength;
    private final int verticalSearchRange;
    protected BlockPos destinationBlock;
    protected int runDelay = 70;
    private Vec3 flightTarget;

    private EntityCrow.AIAvoidPumpkins() {
        this.searchLength = 20;
        this.verticalSearchRange = 1;
    }

    public boolean m_8045_() {
        return this.destinationBlock != null && this.isPumpkin(EntityCrow.this.m_9236_(), this.destinationBlock.m_122032_()) && this.isCloseToPumpkin(16.0);
    }

    public boolean isCloseToPumpkin(double dist) {
        return this.destinationBlock == null || EntityCrow.this.m_20238_(Vec3.m_82512_((Vec3i)this.destinationBlock)) < dist * dist;
    }

    public boolean m_8036_() {
        if (EntityCrow.this.m_21824_()) {
            return false;
        }
        if (this.runDelay > 0) {
            --this.runDelay;
            return false;
        }
        this.runDelay = 70 + EntityCrow.this.f_19796_.m_188503_(150);
        return this.searchForDestination();
    }

    public void m_8056_() {
        EntityCrow.this.fleePumpkinFlag = 200;
        Vec3 vec = EntityCrow.this.getBlockInViewAway(Vec3.m_82512_((Vec3i)this.destinationBlock), 10.0f);
        if (vec != null) {
            this.flightTarget = vec;
            EntityCrow.this.setFlying(true);
            EntityCrow.this.m_21566_().m_6849_(vec.f_82479_, vec.f_82480_, vec.f_82481_, 1.0);
        }
    }

    public void m_8037_() {
        if (this.isCloseToPumpkin(16.0)) {
            Vec3 vec;
            EntityCrow.this.fleePumpkinFlag = 200;
            if ((this.flightTarget == null || EntityCrow.this.m_20238_(this.flightTarget) < 2.0) && (vec = EntityCrow.this.getBlockInViewAway(Vec3.m_82512_((Vec3i)this.destinationBlock), 10.0f)) != null) {
                this.flightTarget = vec;
                EntityCrow.this.setFlying(true);
            }
            if (this.flightTarget != null) {
                EntityCrow.this.m_21566_().m_6849_(this.flightTarget.f_82479_, this.flightTarget.f_82480_, this.flightTarget.f_82481_, 1.0);
            }
        }
    }

    public void m_8041_() {
        this.flightTarget = null;
    }

    protected boolean searchForDestination() {
        int lvt_1_1_ = this.searchLength;
        BlockPos lvt_3_1_ = EntityCrow.this.m_20183_();
        BlockPos.MutableBlockPos lvt_4_1_ = new BlockPos.MutableBlockPos();
        for (int lvt_5_1_ = -8; lvt_5_1_ <= 2; ++lvt_5_1_) {
            for (int lvt_6_1_ = 0; lvt_6_1_ < lvt_1_1_; ++lvt_6_1_) {
                int lvt_7_1_ = 0;
                while (lvt_7_1_ <= lvt_6_1_) {
                    int lvt_8_1_;
                    int n = lvt_8_1_ = lvt_7_1_ < lvt_6_1_ && lvt_7_1_ > -lvt_6_1_ ? lvt_6_1_ : 0;
                    while (lvt_8_1_ <= lvt_6_1_) {
                        lvt_4_1_.m_122154_((Vec3i)lvt_3_1_, lvt_7_1_, lvt_5_1_ - 1, lvt_8_1_);
                        if (this.isPumpkin(EntityCrow.this.m_9236_(), lvt_4_1_)) {
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

    private boolean isPumpkin(Level world, BlockPos.MutableBlockPos lvt_4_1_) {
        return world.m_8055_((BlockPos)lvt_4_1_).m_204336_(AMTagRegistry.CROW_FEARS);
    }
}
