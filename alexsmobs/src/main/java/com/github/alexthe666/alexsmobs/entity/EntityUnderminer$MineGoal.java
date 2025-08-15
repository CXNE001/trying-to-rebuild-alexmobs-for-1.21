/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.misc.AMAdvancementTriggerRegistry;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

private class EntityUnderminer.MineGoal
extends Goal {
    private BlockPos minePretendPos = null;
    private BlockState minePretendStartState = null;
    private int mineTime = 0;

    public EntityUnderminer.MineGoal() {
        this.m_7021_(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        if (EntityUnderminer.this.mineCooldown == 0 && EntityUnderminer.this.hasPick() && !EntityUnderminer.this.isHiding() && !EntityUnderminer.this.isActuallyInAWall() && EntityUnderminer.this.m_217043_().m_188503_(30) == 0) {
            List<BlockPos> obscuredOres = EntityUnderminer.this.getNearbyObscuredOres(16, 8);
            BlockPos nearest = null;
            double nearestDist = Double.MAX_VALUE;
            if (!obscuredOres.isEmpty()) {
                for (BlockPos obscuredPos : obscuredOres) {
                    double dist = EntityUnderminer.this.m_20182_().m_82554_(Vec3.m_82512_((Vec3i)obscuredPos));
                    if (!(nearestDist > dist)) continue;
                    nearest = obscuredPos;
                    nearestDist = dist;
                }
            }
            EntityUnderminer.this.mineAIFlag = false;
            this.minePretendPos = nearest;
            return this.minePretendPos != null;
        }
        return false;
    }

    public boolean m_8045_() {
        return this.minePretendPos != null && EntityUnderminer.this.hasPick() && !EntityUnderminer.this.isHiding() && !EntityUnderminer.this.mineAIFlag && this.minePretendStartState != null && this.minePretendStartState.equals(EntityUnderminer.this.m_9236_().m_8055_(this.minePretendPos)) && this.mineTime < 200;
    }

    public void m_8056_() {
        if (this.minePretendPos != null) {
            this.minePretendStartState = EntityUnderminer.this.m_9236_().m_8055_(this.minePretendPos);
        }
    }

    public void m_8041_() {
        if (this.minePretendPos != null && this.minePretendStartState != null && !this.minePretendStartState.equals(EntityUnderminer.this.m_9236_().m_8055_(this.minePretendPos))) {
            for (ServerPlayer serverplayerentity : EntityUnderminer.this.m_9236_().m_45976_(ServerPlayer.class, EntityUnderminer.this.m_20191_().m_82377_(12.0, 12.0, 12.0))) {
                AMAdvancementTriggerRegistry.UNDERMINE_UNDERMINER.trigger(serverplayerentity);
            }
        }
        this.minePretendPos = null;
        this.minePretendStartState = null;
        this.mineTime = 0;
        EntityUnderminer.this.f_19804_.m_135381_(VISUALLY_MINING, (Object)false);
        EntityUnderminer.this.setMiningPos(null);
        EntityUnderminer.this.setMiningProgress(0.0f);
        EntityUnderminer.this.mineCooldown = EntityUnderminer.this.resetStackTime > 0 ? 40 : 200 + EntityUnderminer.this.f_19796_.m_188503_(200);
    }

    public void m_8037_() {
        if (this.minePretendPos != null && this.minePretendStartState != null) {
            ++this.mineTime;
            double distSqr = EntityUnderminer.this.m_20275_((float)this.minePretendPos.m_123341_() + 0.5f, (float)this.minePretendPos.m_123342_() + 0.5f, (float)this.minePretendPos.m_123343_() + 0.5f);
            if (distSqr < 6.5) {
                EntityUnderminer.this.m_21573_().m_26573_();
                if (EntityUnderminer.this.m_21573_().m_26571_()) {
                    EntityUnderminer.this.setMiningPos(this.minePretendPos);
                    EntityUnderminer.this.setMiningProgress((1.0f + (float)Math.cos((double)((float)this.mineTime * 0.1f) + Math.PI)) * 0.5f);
                    double d1 = (double)((float)this.minePretendPos.m_123343_() + 0.5f) - EntityUnderminer.this.m_20189_();
                    double d3 = (double)((float)this.minePretendPos.m_123342_() + 0.5f) - EntityUnderminer.this.m_20186_();
                    double d2 = (double)((float)this.minePretendPos.m_123341_() + 0.5f) - EntityUnderminer.this.m_20185_();
                    float f = Mth.m_14116_((float)((float)(d2 * d2 + d1 * d1)));
                    EntityUnderminer.this.m_146922_(-((float)Mth.m_14136_((double)d2, (double)d1)) * 57.295776f);
                    EntityUnderminer.this.m_146926_((float)(Mth.m_14136_((double)d3, (double)f) * 57.2957763671875) + (float)Math.sin((float)EntityUnderminer.this.f_19797_ * 0.1f));
                    EntityUnderminer.this.f_19804_.m_135381_(VISUALLY_MINING, (Object)true);
                    if (this.mineTime % 10 == 0) {
                        SoundType soundType = this.minePretendStartState.m_60734_().getSoundType(this.minePretendStartState, (LevelReader)EntityUnderminer.this.m_9236_(), this.minePretendPos, (Entity)EntityUnderminer.this);
                        EntityUnderminer.this.m_216990_(soundType.m_56778_());
                    }
                }
            } else {
                EntityUnderminer.this.f_19804_.m_135381_(VISUALLY_MINING, (Object)false);
                EntityUnderminer.this.setMiningPos(null);
                EntityUnderminer.this.m_21573_().m_26519_((double)((float)this.minePretendPos.m_123341_() + 0.5f), (double)((float)this.minePretendPos.m_123342_() + 0.5f), (double)((float)this.minePretendPos.m_123343_() + 0.5f), 1.0);
            }
        }
    }
}
