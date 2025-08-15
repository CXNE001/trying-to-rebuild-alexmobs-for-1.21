/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

private class EntityCosmaw.AIPickupOwner
extends Goal {
    private LivingEntity owner;

    private EntityCosmaw.AIPickupOwner() {
    }

    public boolean m_8036_() {
        if (EntityCosmaw.this.m_21824_() && EntityCosmaw.this.m_269323_() != null && !EntityCosmaw.this.isSitting() && !EntityCosmaw.this.m_269323_().m_20159_() && !EntityCosmaw.this.m_269323_().m_20096_() && EntityCosmaw.this.m_269323_().f_19789_ > 4.0f) {
            this.owner = EntityCosmaw.this.m_269323_();
            return true;
        }
        return false;
    }

    public void m_8037_() {
        if (this.owner != null && (!this.owner.m_21255_() || this.owner.m_20186_() < -30.0)) {
            double dist = EntityCosmaw.this.m_20270_((Entity)this.owner);
            if (dist < 3.0 || this.owner.m_20186_() <= -50.0) {
                this.owner.f_19789_ = 0.0f;
                this.owner.m_20329_((Entity)EntityCosmaw.this);
            } else if (dist > 100.0 || this.owner.m_20186_() <= -20.0) {
                EntityCosmaw.this.m_6021_(this.owner.m_20185_(), this.owner.m_20186_() - 1.0, this.owner.m_20189_());
            } else {
                EntityCosmaw.this.m_21573_().m_5624_((Entity)this.owner, 1.0 + Math.min(dist * (double)0.3f, 3.0));
            }
        }
    }
}
