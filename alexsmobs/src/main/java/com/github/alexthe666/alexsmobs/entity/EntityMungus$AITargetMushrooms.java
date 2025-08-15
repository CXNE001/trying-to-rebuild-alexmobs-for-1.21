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

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

class EntityMungus.AITargetMushrooms
extends Goal {
    private final int searchLength;
    protected BlockPos destinationBlock;
    protected int runDelay = 70;

    private EntityMungus.AITargetMushrooms() {
        this.searchLength = 20;
    }

    public boolean m_8045_() {
        return this.destinationBlock != null && EntityMungus.this.isMushroomTarget((BlockPos)this.destinationBlock.m_122032_()) && this.isCloseToShroom(32.0);
    }

    public boolean isCloseToShroom(double dist) {
        return this.destinationBlock == null || EntityMungus.this.m_20238_(Vec3.m_82512_((Vec3i)this.destinationBlock)) < dist * dist;
    }

    public boolean m_8036_() {
        if (EntityMungus.this.getBeamTarget() != null || EntityMungus.this.beamCounter < 0 || EntityMungus.this.getMushroomCount() <= 0) {
            return false;
        }
        if (this.runDelay > 0) {
            --this.runDelay;
            return false;
        }
        this.runDelay = 70 + EntityMungus.this.f_19796_.m_188503_(150);
        return this.searchForDestination();
    }

    public void m_8056_() {
    }

    public void m_8037_() {
        if (this.destinationBlock == null || !EntityMungus.this.isMushroomTarget(this.destinationBlock) || EntityMungus.this.beamCounter < 0) {
            this.m_8041_();
        } else if (!EntityMungus.this.hasLineOfSightMushroom(this.destinationBlock)) {
            EntityMungus.this.m_21573_().m_26519_((double)this.destinationBlock.m_123341_(), (double)this.destinationBlock.m_123342_(), (double)this.destinationBlock.m_123343_(), 1.0);
        } else {
            EntityMungus.this.setBeamTarget(this.destinationBlock);
            if (!EntityMungus.this.m_27593_()) {
                EntityMungus.this.m_21573_().m_26573_();
            }
        }
    }

    public void m_8041_() {
        EntityMungus.this.setBeamTarget(null);
    }

    protected boolean searchForDestination() {
        int lvt_1_1_ = this.searchLength;
        BlockPos lvt_3_1_ = EntityMungus.this.m_20183_();
        BlockPos.MutableBlockPos lvt_4_1_ = new BlockPos.MutableBlockPos();
        for (int lvt_5_1_ = -5; lvt_5_1_ <= 5; ++lvt_5_1_) {
            for (int lvt_6_1_ = 0; lvt_6_1_ < lvt_1_1_; ++lvt_6_1_) {
                int lvt_7_1_ = 0;
                while (lvt_7_1_ <= lvt_6_1_) {
                    int lvt_8_1_;
                    int n = lvt_8_1_ = lvt_7_1_ < lvt_6_1_ && lvt_7_1_ > -lvt_6_1_ ? lvt_6_1_ : 0;
                    while (lvt_8_1_ <= lvt_6_1_) {
                        lvt_4_1_.m_122154_((Vec3i)lvt_3_1_, lvt_7_1_, lvt_5_1_ - 1, lvt_8_1_);
                        if (this.isMushroom(EntityMungus.this.m_9236_(), lvt_4_1_)) {
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

    private boolean isMushroom(Level world, BlockPos.MutableBlockPos lvt_4_1_) {
        return EntityMungus.this.isMushroomTarget((BlockPos)lvt_4_1_);
    }
}
