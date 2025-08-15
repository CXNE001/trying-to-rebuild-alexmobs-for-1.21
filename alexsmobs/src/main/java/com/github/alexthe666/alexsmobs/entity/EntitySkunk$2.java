/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.AvoidEntityGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.function.Predicate;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;

class EntitySkunk.2
extends AvoidEntityGoal {
    EntitySkunk.2(PathfinderMob p_25040_, Class p_25041_, Predicate p_25042_, float p_25043_, double p_25044_, double p_25045_, Predicate p_25046_) {
        super(p_25040_, p_25041_, p_25042_, p_25043_, p_25044_, p_25045_, p_25046_);
    }

    public boolean m_8036_() {
        return super.m_8036_() && EntitySkunk.this.getSprayTime() <= 0;
    }

    public boolean m_8045_() {
        return super.m_8045_() && EntitySkunk.this.getSprayTime() <= 0;
    }

    public void m_8037_() {
        super.m_8037_();
        if (this.f_25016_ != null) {
            EntitySkunk.this.sprayAt = this.f_25016_.m_20182_();
        }
        EntitySkunk.this.harassedTime += 4;
    }
}
