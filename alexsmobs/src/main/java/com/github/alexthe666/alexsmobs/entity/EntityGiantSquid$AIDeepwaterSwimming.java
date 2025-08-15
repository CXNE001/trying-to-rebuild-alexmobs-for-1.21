/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;

private class EntityGiantSquid.AIDeepwaterSwimming
extends Goal {
    private BlockPos moveTo;

    public EntityGiantSquid.AIDeepwaterSwimming() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        BlockPos found;
        if (EntityGiantSquid.this.m_20160_() || EntityGiantSquid.this.m_5448_() != null && !EntityGiantSquid.this.isGrabbing() || !EntityGiantSquid.this.m_20069_() && !EntityGiantSquid.this.m_20077_()) {
            return false;
        }
        if ((EntityGiantSquid.this.m_21573_().m_26571_() || EntityGiantSquid.this.m_217043_().m_188503_(30) == 0) && (found = this.findTargetPos()) != null) {
            this.moveTo = found;
            return true;
        }
        return false;
    }

    private BlockPos findTargetPos() {
        RandomSource r = EntityGiantSquid.this.m_217043_();
        for (int i = 0; i < 15; ++i) {
            BlockPos pos = EntityGiantSquid.this.m_20183_().m_7918_(r.m_188503_(16) - 8, r.m_188503_(32) - 16, r.m_188503_(16) - 8);
            if (!EntityGiantSquid.this.m_9236_().m_46801_(pos) || !EntityGiantSquid.this.canFitAt(pos)) continue;
            return this.getDeeperTarget(pos);
        }
        return null;
    }

    private BlockPos getDeeperTarget(BlockPos waterAtPos) {
        BlockPos surface = new BlockPos((Vec3i)waterAtPos);
        BlockPos seafloor = new BlockPos((Vec3i)waterAtPos);
        while (EntityGiantSquid.this.m_9236_().m_46801_(surface) && surface.m_123342_() < 320) {
            surface = surface.m_7494_();
        }
        while (EntityGiantSquid.this.m_9236_().m_46801_(seafloor) && seafloor.m_123342_() > -64) {
            seafloor = seafloor.m_7495_();
        }
        int distance = surface.m_123342_() - seafloor.m_123342_();
        if (distance < 10) {
            return waterAtPos;
        }
        int i = (int)((double)distance * 0.4);
        return seafloor.m_6630_(1 + EntityGiantSquid.this.m_217043_().m_188503_(i));
    }

    public void m_8056_() {
        EntityGiantSquid.this.m_21573_().m_26519_((double)((float)this.moveTo.m_123341_() + 0.5f), (double)((float)this.moveTo.m_123342_() + 0.5f), (double)((float)this.moveTo.m_123343_() + 0.5f), 1.0);
    }

    public boolean m_8045_() {
        return false;
    }
}
