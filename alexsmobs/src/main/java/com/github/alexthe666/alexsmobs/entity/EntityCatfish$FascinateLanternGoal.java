/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityCatfish;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

private class EntityCatfish.FascinateLanternGoal
extends Goal {
    private final int searchLength;
    private final int verticalSearchRange;
    protected BlockPos destinationBlock;
    private final EntityCatfish fish;
    private int runDelay = 70;
    private int chillTime = 0;
    private int maxChillTime = 200;

    private EntityCatfish.FascinateLanternGoal(EntityCatfish fish) {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.fish = fish;
        this.searchLength = 16;
        this.verticalSearchRange = 6;
    }

    public boolean m_8045_() {
        return this.destinationBlock != null && this.isSeaLantern(this.fish.m_9236_(), this.destinationBlock.m_122032_()) && this.isCloseToLantern(16.0) && !this.fish.isFull();
    }

    public boolean isCloseToLantern(double dist) {
        return this.destinationBlock == null || this.fish.m_20238_(Vec3.m_82512_((Vec3i)this.destinationBlock)) < dist * dist;
    }

    public boolean m_8036_() {
        if (!this.fish.m_20072_()) {
            return false;
        }
        if (this.runDelay > 0) {
            --this.runDelay;
            return false;
        }
        this.runDelay = 70 + this.fish.f_19796_.m_188503_(70);
        return !this.fish.isFull() && this.searchForDestination();
    }

    public void m_8056_() {
        this.chillTime = 0;
        this.maxChillTime = 10 + EntityCatfish.this.f_19796_.m_188503_(20);
    }

    public void m_8037_() {
        Vec3 vec = Vec3.m_82512_((Vec3i)this.destinationBlock);
        this.fish.m_21573_().m_26519_(vec.f_82479_, vec.f_82480_, vec.f_82481_, 1.0);
        if (this.fish.m_20238_(vec) < (double)(1.0f + this.fish.m_20205_() * 0.6f)) {
            Vec3 face = vec.m_82546_(this.fish.m_20182_());
            this.fish.m_20256_(this.fish.m_20184_().m_82549_(face.m_82541_().m_82490_((double)0.1f)));
            if (this.chillTime++ > this.maxChillTime) {
                this.destinationBlock = null;
            }
        }
    }

    public void m_8041_() {
        this.destinationBlock = null;
    }

    protected boolean searchForDestination() {
        int lvt_1_1_ = this.searchLength;
        BlockPos lvt_3_1_ = this.fish.m_20183_();
        BlockPos.MutableBlockPos lvt_4_1_ = new BlockPos.MutableBlockPos();
        for (int lvt_5_1_ = -8; lvt_5_1_ <= 2; ++lvt_5_1_) {
            for (int lvt_6_1_ = 0; lvt_6_1_ < lvt_1_1_; ++lvt_6_1_) {
                int lvt_7_1_ = 0;
                while (lvt_7_1_ <= lvt_6_1_) {
                    int lvt_8_1_;
                    int n = lvt_8_1_ = lvt_7_1_ < lvt_6_1_ && lvt_7_1_ > -lvt_6_1_ ? lvt_6_1_ : 0;
                    while (lvt_8_1_ <= lvt_6_1_) {
                        lvt_4_1_.m_122154_((Vec3i)lvt_3_1_, lvt_7_1_, lvt_5_1_ - 1, lvt_8_1_);
                        if (this.isSeaLantern(this.fish.m_9236_(), lvt_4_1_) && this.fish.canSeeBlock((BlockPos)lvt_4_1_)) {
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

    private boolean isSeaLantern(Level world, BlockPos.MutableBlockPos pos) {
        return world.m_8055_((BlockPos)pos).m_204336_(AMTagRegistry.CATFISH_BLOCK_FASCINATIONS);
    }
}
