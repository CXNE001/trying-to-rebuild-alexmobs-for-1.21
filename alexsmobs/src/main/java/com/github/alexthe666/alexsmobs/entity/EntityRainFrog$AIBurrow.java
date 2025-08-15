/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;

private class EntityRainFrog.AIBurrow
extends Goal {
    private BlockPos sand = null;
    private int burrowedTime = 0;

    public EntityRainFrog.AIBurrow() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        if (!EntityRainFrog.this.isBurrowed() && EntityRainFrog.this.burrowCooldown == 0 && EntityRainFrog.this.f_19796_.m_188503_(200) == 0) {
            this.burrowedTime = 0;
            this.sand = this.findSand();
            return this.sand != null;
        }
        return false;
    }

    public boolean m_8045_() {
        return this.burrowedTime < 300;
    }

    public BlockPos findSand() {
        BlockPos blockpos = null;
        for (BlockPos blockpos1 : BlockPos.m_121976_((int)Mth.m_14107_((double)(EntityRainFrog.this.m_20185_() - 4.0)), (int)Mth.m_14107_((double)(EntityRainFrog.this.m_20186_() - 1.0)), (int)Mth.m_14107_((double)(EntityRainFrog.this.m_20189_() - 4.0)), (int)Mth.m_14107_((double)(EntityRainFrog.this.m_20185_() + 4.0)), (int)EntityRainFrog.this.m_146904_(), (int)Mth.m_14107_((double)(EntityRainFrog.this.m_20189_() + 4.0)))) {
            if (!EntityRainFrog.this.m_9236_().m_8055_(blockpos1).m_204336_(BlockTags.f_13029_)) continue;
            blockpos = blockpos1;
            break;
        }
        return blockpos;
    }

    public void m_8037_() {
        if (EntityRainFrog.this.isBurrowed()) {
            ++this.burrowedTime;
            if (!EntityRainFrog.this.m_20075_().m_204336_(BlockTags.f_13029_)) {
                EntityRainFrog.this.setBurrowed(false);
            }
        } else if (this.sand != null) {
            EntityRainFrog.this.m_21573_().m_26519_((double)((float)this.sand.m_123341_() + 0.5f), (double)((float)this.sand.m_123342_() + 1.0f), (double)((float)this.sand.m_123343_() + 0.5f), 1.0);
            if (EntityRainFrog.this.m_20075_().m_204336_(BlockTags.f_13029_)) {
                EntityRainFrog.this.setBurrowed(true);
                EntityRainFrog.this.m_21573_().m_26573_();
                this.sand = null;
            } else {
                EntityRainFrog.this.setBurrowed(false);
            }
        }
    }

    public void m_8041_() {
        EntityRainFrog.this.setBurrowed(false);
        EntityRainFrog.this.burrowCooldown = 120 + EntityRainFrog.this.f_19796_.m_188503_(1200);
        this.sand = null;
    }
}
