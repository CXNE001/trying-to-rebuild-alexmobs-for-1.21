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

class EntityRaccoon.StrollGoal
extends MoveThroughVillageGoal {
    public EntityRaccoon.StrollGoal(int p_i50726_3_) {
        super((PathfinderMob)EntityRaccoon.this, 1.0, true, p_i50726_3_, () -> false);
    }

    public void m_8056_() {
        super.m_8056_();
    }

    public boolean m_8036_() {
        return super.m_8036_() && this.canFoxMove();
    }

    public boolean m_8045_() {
        return super.m_8045_() && this.canFoxMove();
    }

    private boolean canFoxMove() {
        return !EntityRaccoon.this.isWashing() && !EntityRaccoon.this.isSitting() && EntityRaccoon.this.m_5448_() == null;
    }
}
