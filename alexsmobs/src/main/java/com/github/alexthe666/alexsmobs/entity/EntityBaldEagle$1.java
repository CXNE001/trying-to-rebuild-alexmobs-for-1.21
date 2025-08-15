/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.FloatGoal;

class EntityBaldEagle.1
extends FloatGoal {
    EntityBaldEagle.1(Mob p_25230_) {
        super(p_25230_);
    }

    public boolean m_8036_() {
        return super.m_8036_() && (EntityBaldEagle.this.m_20146_() < 30 || EntityBaldEagle.this.m_5448_() == null || !EntityBaldEagle.this.m_5448_().m_20072_() && EntityBaldEagle.this.m_20186_() > EntityBaldEagle.this.m_5448_().m_20186_());
    }
}
