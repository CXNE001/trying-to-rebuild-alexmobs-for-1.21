/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.AnimalAILeapRandomly;
import net.minecraft.world.entity.PathfinderMob;

class EntityBunfungus.2
extends AnimalAILeapRandomly {
    EntityBunfungus.2(PathfinderMob mob, int chance, int maxLeapDistance) {
        super(mob, chance, maxLeapDistance);
    }

    @Override
    public boolean m_8036_() {
        return super.m_8036_() && EntityBunfungus.this.canUseComplexAI();
    }
}
