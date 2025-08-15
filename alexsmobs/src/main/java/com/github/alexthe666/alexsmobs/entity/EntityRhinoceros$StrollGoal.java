/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;

class EntityRhinoceros.StrollGoal
extends MoveThroughVillageGoal {
    public EntityRhinoceros.StrollGoal(int timr) {
        super((PathfinderMob)EntityRhinoceros.this, 1.0, true, timr, () -> false);
    }

    public void m_8056_() {
        super.m_8056_();
    }

    public boolean m_8036_() {
        return super.m_8036_() && this.canRhinoWander();
    }

    public boolean m_8045_() {
        return super.m_8045_() && this.canRhinoWander();
    }

    private boolean canRhinoWander() {
        return !EntityRhinoceros.this.getTrustedUUIDs().isEmpty();
    }
}
