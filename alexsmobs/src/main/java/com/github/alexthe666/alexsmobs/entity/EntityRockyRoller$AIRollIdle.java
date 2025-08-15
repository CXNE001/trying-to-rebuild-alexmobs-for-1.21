/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityRockyRoller;
import java.util.EnumSet;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;

class EntityRockyRoller.AIRollIdle
extends Goal {
    EntityRockyRoller rockyRoller;

    public EntityRockyRoller.AIRollIdle(EntityRockyRoller p_29328_) {
        this.rockyRoller = p_29328_;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }

    public boolean m_8036_() {
        if (this.rockyRoller.m_20096_()) {
            if (this.rockyRoller.isRolling() || this.rockyRoller.rollCooldown > 0 || this.rockyRoller.m_5448_() != null && this.rockyRoller.m_5448_().m_6084_()) {
                return false;
            }
            float f = this.rockyRoller.m_146908_() * ((float)Math.PI / 180);
            int i = 0;
            int j = 0;
            float f1 = -Mth.m_14031_((float)f);
            float f2 = Mth.m_14089_((float)f);
            if ((double)Math.abs(f1) > 0.5) {
                i = (int)((float)i + f1 / Math.abs(f1));
            }
            if ((double)Math.abs(f2) > 0.5) {
                j = (int)((float)j + f2 / Math.abs(f2));
            }
            return this.rockyRoller.m_9236_().m_8055_(this.rockyRoller.m_20183_().m_7918_(i, -1, j)).m_60795_();
        }
        return false;
    }

    public boolean m_8045_() {
        return false;
    }

    public void m_8056_() {
        this.rockyRoller.rollFor(30 + EntityRockyRoller.this.f_19796_.m_188503_(30));
    }

    public boolean m_6767_() {
        return false;
    }
}
