/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityGiantSquid;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntityGiantSquid.AIMelee
extends Goal {
    private EntityGiantSquid.AIMelee() {
    }

    public boolean m_8036_() {
        return EntityGiantSquid.this.m_20072_() && EntityGiantSquid.this.m_5448_() != null && EntityGiantSquid.this.m_5448_().m_6084_();
    }

    public void m_8037_() {
        EntityGiantSquid squid = EntityGiantSquid.this;
        LivingEntity target = EntityGiantSquid.this.m_5448_();
        double dist = squid.m_20270_((Entity)target);
        if (squid.m_142582_((Entity)target) && dist < 7.0) {
            squid.setGrabbing(true);
        } else {
            Vec3 moveBodyTo = target.m_20182_();
            squid.m_21573_().m_26519_(moveBodyTo.f_82479_, moveBodyTo.f_82480_, moveBodyTo.f_82481_, 1.0);
        }
        if (dist < 14.0) {
            squid.f_19804_.m_135381_(OVERRIDE_BODYROT, (Object)true);
        } else {
            squid.f_19804_.m_135381_(OVERRIDE_BODYROT, (Object)false);
        }
    }

    public void m_8041_() {
        EntityGiantSquid.this.f_19804_.m_135381_(OVERRIDE_BODYROT, (Object)false);
        EntityGiantSquid.this.setGrabbing(false);
    }
}
