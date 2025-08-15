/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityRaccoon;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class RaccoonAIWash
extends Goal {
    private final EntityRaccoon raccoon;
    private BlockPos waterPos;
    private BlockPos targetPos;
    private int washTime = 0;
    private int executionChance = 30;
    private Direction[] HORIZONTALS = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

    public RaccoonAIWash(EntityRaccoon creature) {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.raccoon = creature;
    }

    public boolean m_8036_() {
        if (this.raccoon.m_21205_().m_41619_()) {
            return false;
        }
        if (this.raccoon.lookForWaterBeforeEatingTimer > 0) {
            this.waterPos = this.generateTarget();
            if (this.waterPos != null) {
                this.targetPos = this.getLandPos(this.waterPos);
                return this.targetPos != null;
            }
        }
        return false;
    }

    public void m_8056_() {
        this.raccoon.lookForWaterBeforeEatingTimer = 1800;
    }

    public void m_8041_() {
        this.targetPos = null;
        this.waterPos = null;
        this.washTime = 0;
        this.raccoon.setWashPos(null);
        this.raccoon.setWashing(false);
        this.raccoon.lookForWaterBeforeEatingTimer = 100;
        this.raccoon.m_21573_().m_26573_();
    }

    public void m_8037_() {
        if (this.targetPos != null && this.waterPos != null) {
            double dist = this.raccoon.m_20238_(Vec3.m_82512_((Vec3i)this.waterPos));
            if (dist > 2.0 && this.raccoon.isWashing()) {
                this.raccoon.setWashing(false);
            }
            if (dist <= 1.0) {
                double d0 = (double)this.waterPos.m_123341_() + 0.5 - this.raccoon.m_20185_();
                double d2 = (double)this.waterPos.m_123343_() + 0.5 - this.raccoon.m_20189_();
                float yaw = (float)(Mth.m_14136_((double)d2, (double)d0) * 57.2957763671875) - 90.0f;
                this.raccoon.m_146922_(yaw);
                this.raccoon.f_20885_ = yaw;
                this.raccoon.f_20883_ = yaw;
                this.raccoon.m_21573_().m_26573_();
                this.raccoon.setWashing(true);
                this.raccoon.setWashPos(this.waterPos);
                this.raccoon.lookForWaterBeforeEatingTimer = 0;
                if (this.washTime % 10 == 0) {
                    this.raccoon.m_146850_(GameEvent.f_223702_);
                    this.raccoon.m_5496_(SoundEvents.f_11918_, 0.7f, 0.5f + this.raccoon.m_217043_().m_188501_());
                }
                ++this.washTime;
                if (this.washTime > 100 || this.raccoon.isHoldingSugar() && this.washTime > 20) {
                    this.m_8041_();
                    if (!this.raccoon.isHoldingSugar()) {
                        this.raccoon.onEatItem();
                    }
                    this.raccoon.postWashItem(this.raccoon.m_21205_());
                    if (this.raccoon.m_21205_().hasCraftingRemainingItem()) {
                        this.raccoon.m_19983_(this.raccoon.m_21205_().getCraftingRemainingItem());
                    }
                    this.raccoon.m_21205_().m_41774_(1);
                }
            } else {
                this.raccoon.m_21573_().m_26519_((double)this.waterPos.m_123341_(), (double)this.waterPos.m_123342_(), (double)this.waterPos.m_123343_(), 1.2);
            }
        }
    }

    public boolean m_8045_() {
        if (this.raccoon.m_21205_().m_41619_()) {
            return false;
        }
        return this.targetPos != null && !this.raccoon.m_20069_() && EntityRaccoon.isRaccoonFood(this.raccoon.m_21205_());
    }

    public BlockPos generateTarget() {
        BlockPos blockpos = null;
        RandomSource random = this.raccoon.m_217043_();
        int range = 32;
        for (int i = 0; i < 15; ++i) {
            BlockPos blockpos1 = this.raccoon.m_20183_().m_7918_(random.m_188503_(range) - range / 2, 3, random.m_188503_(range) - range / 2);
            while (this.raccoon.m_9236_().m_46859_(blockpos1) && blockpos1.m_123342_() > this.raccoon.m_9236_().m_141937_()) {
                blockpos1 = blockpos1.m_7495_();
            }
            if (!this.isConnectedToLand(blockpos1)) continue;
            blockpos = blockpos1;
        }
        return blockpos;
    }

    public boolean isConnectedToLand(BlockPos pos) {
        if (this.raccoon.m_9236_().m_6425_(pos).m_205070_(FluidTags.f_13131_)) {
            for (Direction dir : this.HORIZONTALS) {
                BlockPos offsetPos = pos.m_121945_(dir);
                if (!this.raccoon.m_9236_().m_6425_(offsetPos).m_76178_() || !this.raccoon.m_9236_().m_6425_(offsetPos.m_7494_()).m_76178_()) continue;
                return true;
            }
        }
        return false;
    }

    public BlockPos getLandPos(BlockPos pos) {
        if (this.raccoon.m_9236_().m_6425_(pos).m_205070_(FluidTags.f_13131_)) {
            for (Direction dir : this.HORIZONTALS) {
                BlockPos offsetPos = pos.m_121945_(dir);
                if (!this.raccoon.m_9236_().m_6425_(offsetPos).m_76178_() || !this.raccoon.m_9236_().m_6425_(offsetPos.m_7494_()).m_76178_()) continue;
                return offsetPos;
            }
        }
        return null;
    }
}
