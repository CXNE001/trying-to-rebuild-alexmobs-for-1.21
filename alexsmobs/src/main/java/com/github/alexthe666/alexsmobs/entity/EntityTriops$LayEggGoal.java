/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

class EntityTriops.LayEggGoal
extends Goal {
    private BlockPos eggPos;

    EntityTriops.LayEggGoal() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public void m_8041_() {
        this.eggPos = null;
    }

    public boolean m_8036_() {
        BlockPos egg;
        if (EntityTriops.this.pregnant && EntityTriops.this.m_217043_().m_188503_(30) == 0 && (egg = this.getEggLayPos()) != null) {
            this.eggPos = egg;
            return true;
        }
        return false;
    }

    public boolean m_8045_() {
        return this.eggPos != null && EntityTriops.this.pregnant && EntityTriops.this.m_9236_().m_8055_(this.eggPos).m_60795_();
    }

    public boolean isValidPos(BlockPos pos) {
        BlockState state = EntityTriops.this.m_9236_().m_8055_(pos);
        FluidState stateBelow = EntityTriops.this.m_9236_().m_6425_(pos.m_7495_());
        return stateBelow.m_205070_(FluidTags.f_13131_) && state.m_60795_();
    }

    public BlockPos getEggLayPos() {
        for (int i = 0; i < 10; ++i) {
            BlockPos offset = EntityTriops.this.m_20183_().m_7918_(EntityTriops.this.m_217043_().m_188503_(10) - 5, 10, EntityTriops.this.m_217043_().m_188503_(10) - 5);
            while (EntityTriops.this.m_9236_().m_8055_(offset.m_7495_()).m_60795_() && offset.m_123342_() > EntityTriops.this.m_9236_().m_141937_()) {
                offset = offset.m_7495_();
            }
            if (!this.isValidPos(offset)) continue;
            return offset;
        }
        return null;
    }

    public void m_8037_() {
        super.m_8037_();
        EntityTriops.this.m_21573_().m_26519_((double)this.eggPos.m_123341_(), (double)this.eggPos.m_123342_(), (double)this.eggPos.m_123343_(), 1.0);
        if (EntityTriops.this.m_20238_(Vec3.m_82539_((Vec3i)this.eggPos)) < 2.0) {
            EntityTriops.this.pregnant = false;
            EntityTriops.this.m_9236_().m_46597_(this.eggPos, ((Block)AMBlockRegistry.TRIOPS_EGGS.get()).m_49966_());
        }
    }
}
