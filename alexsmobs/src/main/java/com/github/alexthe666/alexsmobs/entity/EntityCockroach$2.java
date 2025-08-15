/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIFleeLight;
import net.minecraft.world.entity.PathfinderMob;

class EntityCockroach.2
extends AnimalAIFleeLight {
    EntityCockroach.2(PathfinderMob p_i1623_1_, double p_i1623_2_) {
        super(p_i1623_1_, p_i1623_2_);
    }

    @Override
    public boolean m_8036_() {
        return !EntityCockroach.this.isBreaded() && super.m_8036_();
    }
}
