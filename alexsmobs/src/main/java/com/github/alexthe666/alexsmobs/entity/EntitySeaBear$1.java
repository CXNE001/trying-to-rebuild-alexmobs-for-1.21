/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  net.minecraft.world.entity.PathfinderMob
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.ai.AnimalAISwimBottom;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.world.entity.PathfinderMob;

class EntitySeaBear.1
extends AnimalAISwimBottom {
    EntitySeaBear.1(PathfinderMob p_i48937_1_, double p_i48937_2_, int p_i48937_4_) {
        super(p_i48937_1_, p_i48937_2_, p_i48937_4_);
    }

    public boolean m_8036_() {
        return super.m_8036_() && EntitySeaBear.this.getAnimation() == IAnimatedEntity.NO_ANIMATION;
    }

    public boolean m_8045_() {
        return super.m_8045_() && EntitySeaBear.this.getAnimation() == IAnimatedEntity.NO_ANIMATION;
    }
}
