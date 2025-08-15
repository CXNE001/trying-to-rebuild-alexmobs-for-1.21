/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.LaviathanAIRandomSwimming;
import net.minecraft.world.entity.PathfinderMob;

class EntityLaviathan.2
extends LaviathanAIRandomSwimming {
    EntityLaviathan.2(PathfinderMob creature, double speed, int chance) {
        super(creature, speed, chance);
    }

    @Override
    public boolean m_8036_() {
        return super.m_8036_() && !EntityLaviathan.this.hasHeadGear() && !EntityLaviathan.this.hasBodyGear();
    }
}
