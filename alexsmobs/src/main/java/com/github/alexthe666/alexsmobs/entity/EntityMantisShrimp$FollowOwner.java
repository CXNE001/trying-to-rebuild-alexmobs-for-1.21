/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.LeavesBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.level.pathfinder.WalkNodeEvaluator
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityMantisShrimp;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

public static class EntityMantisShrimp.FollowOwner
extends Goal {
    private final EntityMantisShrimp tameable;
    private final LevelReader world;
    private final double followSpeed;
    private final float maxDist;
    private final float minDist;
    private final boolean teleportToLeaves;
    private LivingEntity owner;
    private int timeToRecalcPath;
    private float oldWaterCost;

    public EntityMantisShrimp.FollowOwner(EntityMantisShrimp p_i225711_1_, double p_i225711_2_, float p_i225711_4_, float p_i225711_5_, boolean p_i225711_6_) {
        this.tameable = p_i225711_1_;
        this.world = p_i225711_1_.m_9236_();
        this.followSpeed = p_i225711_2_;
        this.minDist = p_i225711_4_;
        this.maxDist = p_i225711_5_;
        this.teleportToLeaves = p_i225711_6_;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        LivingEntity lvt_1_1_ = this.tameable.m_269323_();
        if (lvt_1_1_ == null) {
            return false;
        }
        if (lvt_1_1_.m_5833_()) {
            return false;
        }
        if (this.tameable.isSitting() || this.tameable.getCommand() != 1) {
            return false;
        }
        if (this.tameable.m_20280_((Entity)lvt_1_1_) < (double)(this.minDist * this.minDist)) {
            return false;
        }
        if (this.tameable.m_5448_() != null && this.tameable.m_5448_().m_6084_()) {
            return false;
        }
        this.owner = lvt_1_1_;
        return true;
    }

    public boolean m_8045_() {
        if (this.tameable.m_21573_().m_26571_()) {
            return false;
        }
        if (this.tameable.isSitting() || this.tameable.getCommand() != 1) {
            return false;
        }
        if (this.tameable.m_5448_() != null && this.tameable.m_5448_().m_6084_()) {
            return false;
        }
        return this.tameable.m_20280_((Entity)this.owner) > (double)(this.maxDist * this.maxDist);
    }

    public void m_8056_() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.tameable.m_21439_(BlockPathTypes.WATER);
        this.tameable.m_21441_(BlockPathTypes.WATER, 0.0f);
    }

    public void m_8041_() {
        this.owner = null;
        this.tameable.m_21573_().m_26573_();
        this.tameable.m_21441_(BlockPathTypes.WATER, this.oldWaterCost);
    }

    public void m_8037_() {
        this.tameable.m_21563_().m_24960_((Entity)this.owner, 10.0f, (float)this.tameable.m_8132_());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!this.tameable.m_21523_() && !this.tameable.m_20159_()) {
                if (this.tameable.m_20280_((Entity)this.owner) >= 144.0) {
                    this.tryToTeleportNearEntity();
                } else {
                    this.tameable.m_21573_().m_5624_((Entity)this.owner, this.followSpeed);
                }
            }
        }
    }

    private void tryToTeleportNearEntity() {
        BlockPos lvt_1_1_ = this.owner.m_20183_();
        for (int lvt_2_1_ = 0; lvt_2_1_ < 10; ++lvt_2_1_) {
            int lvt_3_1_ = this.getRandomNumber(-3, 3);
            int lvt_4_1_ = this.getRandomNumber(-1, 1);
            int lvt_5_1_ = this.getRandomNumber(-3, 3);
            boolean lvt_6_1_ = this.tryToTeleportToLocation(lvt_1_1_.m_123341_() + lvt_3_1_, lvt_1_1_.m_123342_() + lvt_4_1_, lvt_1_1_.m_123343_() + lvt_5_1_);
            if (!lvt_6_1_) continue;
            return;
        }
    }

    private boolean tryToTeleportToLocation(int p_226328_1_, int p_226328_2_, int p_226328_3_) {
        if (Math.abs((double)p_226328_1_ - this.owner.m_20185_()) < 2.0 && Math.abs((double)p_226328_3_ - this.owner.m_20189_()) < 2.0) {
            return false;
        }
        if (!this.isTeleportFriendlyBlock(new BlockPos(p_226328_1_, p_226328_2_, p_226328_3_))) {
            return false;
        }
        this.tameable.m_7678_((double)p_226328_1_ + 0.5, p_226328_2_, (double)p_226328_3_ + 0.5, this.tameable.m_146908_(), this.tameable.m_146909_());
        this.tameable.m_21573_().m_26573_();
        return true;
    }

    private boolean isTeleportFriendlyBlock(BlockPos p_226329_1_) {
        BlockPathTypes lvt_2_1_ = WalkNodeEvaluator.m_77604_((BlockGetter)this.world, (BlockPos.MutableBlockPos)p_226329_1_.m_122032_());
        if (this.world.m_6425_(p_226329_1_).m_205070_(FluidTags.f_13131_) || !this.world.m_6425_(p_226329_1_).m_205070_(FluidTags.f_13131_) && this.world.m_6425_(p_226329_1_.m_7495_()).m_205070_(FluidTags.f_13131_)) {
            return true;
        }
        if (lvt_2_1_ != BlockPathTypes.WALKABLE || this.tameable.getMoistness() < 2000) {
            return false;
        }
        BlockState lvt_3_1_ = this.world.m_8055_(p_226329_1_.m_7495_());
        if (!this.teleportToLeaves && lvt_3_1_.m_60734_() instanceof LeavesBlock) {
            return false;
        }
        BlockPos lvt_4_1_ = p_226329_1_.m_121996_((Vec3i)this.tameable.m_20183_());
        return this.world.m_45756_((Entity)this.tameable, this.tameable.m_20191_().m_82338_(lvt_4_1_));
    }

    private int getRandomNumber(int p_226327_1_, int p_226327_2_) {
        return this.tameable.m_217043_().m_188503_(p_226327_2_ - p_226327_1_ + 1) + p_226327_1_;
    }
}
