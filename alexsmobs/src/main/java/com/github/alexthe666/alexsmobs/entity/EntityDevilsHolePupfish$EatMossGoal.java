/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityDevilsHolePupfish;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

private class EntityDevilsHolePupfish.EatMossGoal
extends Goal {
    private final int searchLength;
    private final int verticalSearchRange;
    protected BlockPos destinationBlock;
    private final EntityDevilsHolePupfish pupfish;
    private int runDelay = 70;
    private int maxFeedTime = 200;

    private EntityDevilsHolePupfish.EatMossGoal(EntityDevilsHolePupfish pupfish) {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.pupfish = pupfish;
        this.searchLength = 16;
        this.verticalSearchRange = 6;
    }

    public boolean m_8045_() {
        return this.destinationBlock != null && this.isMossBlock(this.pupfish.m_9236_(), this.destinationBlock.m_122032_()) && this.isCloseToMoss(16.0);
    }

    public boolean isCloseToMoss(double dist) {
        return this.destinationBlock == null || this.pupfish.m_20238_(Vec3.m_82512_((Vec3i)this.destinationBlock)) < dist * dist;
    }

    public boolean m_8036_() {
        if (!this.pupfish.m_20072_()) {
            return false;
        }
        if (this.runDelay > 0) {
            --this.runDelay;
            return false;
        }
        this.runDelay = 200 + this.pupfish.f_19796_.m_188503_(150);
        return this.searchForDestination();
    }

    public void m_8056_() {
        this.maxFeedTime = 60 + EntityDevilsHolePupfish.this.f_19796_.m_188503_(60);
    }

    public void m_8037_() {
        Vec3 vec = Vec3.m_82512_((Vec3i)this.destinationBlock);
        if (vec != null) {
            this.pupfish.m_21573_().m_26519_(vec.f_82479_, vec.f_82480_, vec.f_82481_, 1.0);
            if (this.pupfish.m_20238_(vec) < (double)1.15f) {
                this.pupfish.f_19804_.m_135381_(FEEDING_POS, Optional.of(this.destinationBlock));
                Vec3 face = vec.m_82546_(this.pupfish.m_20182_());
                this.pupfish.m_20256_(this.pupfish.m_20184_().m_82549_(face.m_82541_().m_82490_((double)0.1f)));
                this.pupfish.setFeedingTime(this.pupfish.getFeedingTime() + 1);
                if (this.pupfish.getFeedingTime() > this.maxFeedTime) {
                    List<ItemStack> lootList;
                    this.destinationBlock = null;
                    if (EntityDevilsHolePupfish.this.f_19796_.m_188503_(3) == 0 && !(lootList = EntityDevilsHolePupfish.getFoodLoot(this.pupfish)).isEmpty()) {
                        for (ItemStack stack : lootList) {
                            ItemEntity e = this.pupfish.m_19983_(stack.m_41777_());
                            e.f_19812_ = true;
                            e.m_20256_(e.m_20184_().m_82542_(0.2, 0.2, 0.2));
                        }
                    }
                    if (EntityDevilsHolePupfish.this.f_19796_.m_188503_(3) == 0 && !this.pupfish.m_6162_()) {
                        this.pupfish.breedNextChase = true;
                    }
                }
            } else {
                this.pupfish.f_19804_.m_135381_(FEEDING_POS, Optional.empty());
            }
        }
    }

    public void m_8041_() {
        this.pupfish.f_19804_.m_135381_(FEEDING_POS, Optional.empty());
        this.destinationBlock = null;
        this.pupfish.setFeedingTime(0);
    }

    protected boolean searchForDestination() {
        int lvt_1_1_ = this.searchLength;
        BlockPos lvt_3_1_ = this.pupfish.m_20183_();
        BlockPos.MutableBlockPos lvt_4_1_ = new BlockPos.MutableBlockPos();
        for (int lvt_5_1_ = -8; lvt_5_1_ <= 2; ++lvt_5_1_) {
            for (int lvt_6_1_ = 0; lvt_6_1_ < lvt_1_1_; ++lvt_6_1_) {
                int lvt_7_1_ = 0;
                while (lvt_7_1_ <= lvt_6_1_) {
                    int lvt_8_1_;
                    int n = lvt_8_1_ = lvt_7_1_ < lvt_6_1_ && lvt_7_1_ > -lvt_6_1_ ? lvt_6_1_ : 0;
                    while (lvt_8_1_ <= lvt_6_1_) {
                        lvt_4_1_.m_122154_((Vec3i)lvt_3_1_, lvt_7_1_, lvt_5_1_ - 1, lvt_8_1_);
                        if (this.isMossBlock(this.pupfish.m_9236_(), lvt_4_1_) && this.pupfish.canSeeBlock((BlockPos)lvt_4_1_)) {
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

    private boolean isMossBlock(Level world, BlockPos.MutableBlockPos pos) {
        return world.m_8055_((BlockPos)pos).m_204336_(AMTagRegistry.PUPFISH_EATABLES);
    }
}
