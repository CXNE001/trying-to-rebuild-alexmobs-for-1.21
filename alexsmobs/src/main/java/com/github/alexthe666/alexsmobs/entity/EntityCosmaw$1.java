/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.item.crafting.Ingredient
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.AnimalAITemptDistance;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.crafting.Ingredient;

class EntityCosmaw.1
extends AnimalAITemptDistance {
    EntityCosmaw.1(PathfinderMob p_25939_, double p_25940_, Ingredient p_25941_, boolean p_25942_, double distance) {
        super(p_25939_, p_25940_, p_25941_, p_25942_, distance);
    }

    @Override
    public boolean m_8036_() {
        return super.m_8036_() && EntityCosmaw.this.m_21205_().m_41619_();
    }

    @Override
    public boolean m_8045_() {
        return super.m_8045_() && EntityCosmaw.this.m_21205_().m_41619_();
    }
}
