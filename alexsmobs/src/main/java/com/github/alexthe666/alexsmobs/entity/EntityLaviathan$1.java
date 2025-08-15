/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.AnimalAIHerdPanic;
import net.minecraft.world.entity.PathfinderMob;

class EntityLaviathan.1
extends AnimalAIHerdPanic {
    EntityLaviathan.1(PathfinderMob creature, double speedIn) {
        super(creature, speedIn);
    }

    @Override
    public boolean m_8036_() {
        return super.m_8036_() && !EntityLaviathan.this.hasHeadGear();
    }
}
