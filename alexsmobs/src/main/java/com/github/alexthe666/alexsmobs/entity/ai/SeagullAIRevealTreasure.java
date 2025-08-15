/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntitySeagull;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class SeagullAIRevealTreasure
extends Goal {
    private final EntitySeagull seagull;
    private BlockPos sitPos;

    public SeagullAIRevealTreasure(EntitySeagull entitySeagull) {
        this.seagull = entitySeagull;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.TARGET));
    }

    public boolean m_8036_() {
        return this.seagull.getTreasurePos() != null && this.seagull.treasureSitTime > 0;
    }

    public void m_8056_() {
        this.seagull.aiItemFlag = true;
        this.sitPos = this.seagull.getSeagullGround(this.seagull.getTreasurePos());
    }

    public void m_8041_() {
        this.sitPos = null;
        this.seagull.setSitting(false);
        this.seagull.aiItemFlag = false;
    }

    public void m_8037_() {
        if (this.sitPos != null) {
            Vec3 vec3 = new Vec3((double)((float)this.sitPos.m_123341_() + 0.5f), this.seagull.m_20186_(), (double)((float)this.sitPos.m_123343_() + 0.5f));
            if (this.seagull.m_20238_(vec3) > 2.5) {
                this.seagull.m_21566_().m_6849_((double)((float)this.sitPos.m_123341_() + 0.5f), (double)(this.sitPos.m_123342_() + 2), (double)((float)this.sitPos.m_123343_() + 0.5f), 1.0);
                if (!this.seagull.m_20096_()) {
                    this.seagull.setFlying(true);
                }
            } else {
                Vec3 vec = Vec3.m_82514_((Vec3i)this.sitPos, (double)1.0);
                if (vec.m_82546_(this.seagull.m_20182_()).m_82553_() > (double)0.04f) {
                    this.seagull.m_20256_(vec.m_82546_(this.seagull.m_20182_()).m_82490_((double)0.2f));
                }
                this.seagull.eatItem();
                this.seagull.treasureSitTime = Math.min(this.seagull.treasureSitTime, 100);
                this.seagull.setFlying(false);
                this.seagull.setSitting(true);
            }
        }
    }
}
