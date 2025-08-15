/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.CreatureAITargetItems;
import net.minecraft.world.entity.PathfinderMob;

class EntityPlatypus.3
extends CreatureAITargetItems {
    EntityPlatypus.3(PathfinderMob creature, boolean checkSight, boolean onlyNearby, int tickThreshold, int radius) {
        super(creature, checkSight, onlyNearby, tickThreshold, radius);
    }

    @Override
    public boolean m_8036_() {
        return super.m_8036_() && !EntityPlatypus.this.isSensing();
    }

    @Override
    public boolean m_8045_() {
        return super.m_8045_() && !EntityPlatypus.this.isSensing();
    }
}
