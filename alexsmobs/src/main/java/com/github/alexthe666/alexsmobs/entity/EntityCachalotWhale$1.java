/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIRandomSwimming;
import net.minecraft.world.entity.PathfinderMob;

class EntityCachalotWhale.1
extends AnimalAIRandomSwimming {
    EntityCachalotWhale.1(PathfinderMob creature, double speed, int chance, int xzSpread, boolean submerged) {
        super(creature, speed, chance, xzSpread, submerged);
    }

    @Override
    public boolean m_8036_() {
        return !EntityCachalotWhale.this.m_5803_() && !EntityCachalotWhale.this.isBeached() && super.m_8036_();
    }
}
