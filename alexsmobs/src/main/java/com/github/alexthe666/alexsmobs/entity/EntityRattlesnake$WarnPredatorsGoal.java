/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

class EntityRattlesnake.WarnPredatorsGoal
extends Goal {
    int executionChance = 20;
    Entity target = null;

    EntityRattlesnake.WarnPredatorsGoal() {
    }

    public boolean m_8036_() {
        if (EntityRattlesnake.this.m_217043_().m_188503_(this.executionChance) == 0) {
            double dist = 5.0;
            List list = EntityRattlesnake.this.m_9236_().m_6443_(LivingEntity.class, EntityRattlesnake.this.m_20191_().m_82377_(5.0, 5.0, 5.0), WARNABLE_PREDICATE);
            double d0 = Double.MAX_VALUE;
            Entity possibleTarget = null;
            for (Entity entity : list) {
                double d1 = EntityRattlesnake.this.m_20280_(entity);
                if (d1 > d0) continue;
                d0 = d1;
                possibleTarget = entity;
            }
            this.target = possibleTarget;
            return !list.isEmpty();
        }
        return false;
    }

    public boolean m_8045_() {
        return this.target != null && (double)EntityRattlesnake.this.m_20270_(this.target) < 5.0 && EntityRattlesnake.this.m_5448_() == null;
    }

    public void m_8041_() {
        this.target = null;
        EntityRattlesnake.this.setRattling(false);
    }

    public void m_8037_() {
        EntityRattlesnake.this.setRattling(true);
        EntityRattlesnake.this.setCurled(true);
        EntityRattlesnake.this.curlTime = 0;
        EntityRattlesnake.this.m_21563_().m_24960_(this.target, 30.0f, 30.0f);
    }
}
