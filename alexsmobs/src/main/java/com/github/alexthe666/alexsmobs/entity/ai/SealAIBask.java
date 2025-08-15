/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntitySeal;
import java.util.EnumSet;
import net.minecraft.world.entity.ai.goal.Goal;

public class SealAIBask
extends Goal {
    private final EntitySeal seal;

    public SealAIBask(EntitySeal seal) {
        this.seal = seal;
        this.m_7021_(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    public boolean m_8045_() {
        return this.seal.isBasking() && !this.seal.m_20072_();
    }

    public boolean m_8036_() {
        if (this.seal.m_20072_()) {
            return false;
        }
        return this.seal.m_21188_() == null && this.seal.m_5448_() == null && this.seal.isBasking();
    }

    public void m_8037_() {
        this.seal.m_21573_().m_26573_();
    }

    public void m_8041_() {
        this.seal.setBasking(false);
    }
}
