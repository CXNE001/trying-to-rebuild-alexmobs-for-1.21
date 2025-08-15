/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIWanderRanged;
import net.minecraft.world.entity.PathfinderMob;

class EntityBunfungus.1
extends AnimalAIWanderRanged {
    EntityBunfungus.1(PathfinderMob creature, int chance, double speedIn, int xzRange, int yRange) {
        super(creature, chance, speedIn, xzRange, yRange);
    }

    @Override
    public boolean m_8036_() {
        return super.m_8036_() && EntityBunfungus.this.canUseComplexAI();
    }
}
