/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.navigation.PathNavigation
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.LeavesBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.pathfinder.BlockPathTypes
 *  net.minecraft.world.level.pathfinder.WalkNodeEvaluator
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.IFollower;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

public class FlyingAIFollowOwner
extends Goal {
    private final TamableAnimal tameable;
    private LivingEntity owner;
    private final LevelReader world;
    private final double followSpeed;
    private final PathNavigation navigator;
    private int timeToRecalcPath;
    private final float maxDist;
    private final float minDist;
    private float oldWaterCost;
    private final boolean teleportToLeaves;
    private final IFollower follower;

    public FlyingAIFollowOwner(TamableAnimal tameable, double speed, float minDist, float maxDist, boolean teleportToLeaves) {
        this.tameable = tameable;
        this.world = tameable.m_9236_();
        this.followSpeed = speed;
        this.navigator = tameable.m_21573_();
        this.minDist = minDist;
        this.maxDist = maxDist;
        this.teleportToLeaves = teleportToLeaves;
        this.follower = (IFollower)tameable;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        LivingEntity livingentity = this.tameable.m_269323_();
        if (livingentity == null) {
            return false;
        }
        if (livingentity.m_5833_()) {
            return false;
        }
        if (this.tameable.m_21827_()) {
            return false;
        }
        if (this.tameable.m_20280_((Entity)livingentity) < (double)(this.minDist * this.minDist) || this.isInCombat()) {
            return false;
        }
        this.owner = livingentity;
        return this.follower.shouldFollow();
    }

    public boolean m_8045_() {
        if (this.tameable.m_21827_() || this.isInCombat()) {
            return false;
        }
        return this.tameable.m_20280_((Entity)this.owner) > (double)(this.maxDist * this.maxDist);
    }

    private boolean isInCombat() {
        LivingEntity owner = this.tameable.m_269323_();
        if (owner != null) {
            return this.tameable.m_20270_((Entity)owner) < 30.0f && this.tameable.m_5448_() != null && this.tameable.m_5448_().m_6084_();
        }
        return false;
    }

    public void m_8056_() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.tameable.m_21439_(BlockPathTypes.WATER);
        this.tameable.m_21441_(BlockPathTypes.WATER, 0.0f);
    }

    public void m_8041_() {
        this.owner = null;
        this.navigator.m_26573_();
        this.tameable.m_21441_(BlockPathTypes.WATER, this.oldWaterCost);
    }

    public void m_8037_() {
        this.tameable.m_21563_().m_24960_((Entity)this.owner, 10.0f, (float)this.tameable.m_8132_());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!this.tameable.m_21523_() && !this.tameable.m_20159_()) {
                if (this.tameable.m_20280_((Entity)this.owner) >= 144.0) {
                    this.tryToTeleportNearEntity();
                }
                this.follower.followEntity(this.tameable, this.owner, this.followSpeed);
            }
        }
    }

    private void tryToTeleportNearEntity() {
        BlockPos blockpos = this.owner.m_20183_();
        for (int i = 0; i < 10; ++i) {
            int j = this.getRandomNumber(-3, 3);
            int k = this.getRandomNumber(-1, 1);
            int l = this.getRandomNumber(-3, 3);
            boolean flag = this.tryToTeleportToLocation(blockpos.m_123341_() + j, blockpos.m_123342_() + k, blockpos.m_123343_() + l);
            if (!flag) continue;
            return;
        }
    }

    private boolean tryToTeleportToLocation(int x, int y, int z) {
        if (Math.abs((double)x - this.owner.m_20185_()) < 2.0 && Math.abs((double)z - this.owner.m_20189_()) < 2.0) {
            return false;
        }
        if (!this.isTeleportFriendlyBlock(new BlockPos(x, y, z))) {
            return false;
        }
        this.tameable.m_7678_((double)x + 0.5, (double)y, (double)z + 0.5, this.tameable.m_146908_(), this.tameable.m_146909_());
        this.navigator.m_26573_();
        return true;
    }

    private boolean isTeleportFriendlyBlock(BlockPos pos) {
        if (this.world.m_8055_(pos).m_60795_()) {
            BlockPos blockpos = pos.m_121996_((Vec3i)this.tameable.m_20183_());
            return this.world.m_45756_((Entity)this.tameable, this.tameable.m_20191_().m_82338_(blockpos));
        }
        BlockPathTypes pathnodetype = WalkNodeEvaluator.m_77604_((BlockGetter)this.world, (BlockPos.MutableBlockPos)pos.m_122032_());
        if (pathnodetype != BlockPathTypes.WALKABLE) {
            return false;
        }
        BlockState blockstate = this.world.m_8055_(pos.m_7495_());
        if (!this.teleportToLeaves && blockstate.m_60734_() instanceof LeavesBlock) {
            return false;
        }
        BlockPos blockpos = pos.m_121996_((Vec3i)this.tameable.m_20183_());
        return this.world.m_45756_((Entity)this.tameable, this.tameable.m_20191_().m_82338_(blockpos));
    }

    private int getRandomNumber(int min, int max) {
        return this.tameable.m_217043_().m_188503_(max - min + 1) + min;
    }
}
