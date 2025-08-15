/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.AnimalAILeapRandomly;
import net.minecraft.world.entity.PathfinderMob;

class EntityWarpedToad.1
extends AnimalAILeapRandomly {
    EntityWarpedToad.1(PathfinderMob mob, int chance, int maxLeapDistance) {
        super(mob, chance, maxLeapDistance);
    }

    @Override
    public boolean m_8036_() {
        return super.m_8036_() && !EntityWarpedToad.this.m_21827_();
    }
}
