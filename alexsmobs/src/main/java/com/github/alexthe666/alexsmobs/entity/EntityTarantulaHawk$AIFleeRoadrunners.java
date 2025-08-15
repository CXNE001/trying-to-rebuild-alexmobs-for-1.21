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

import com.github.alexthe666.alexsmobs.entity.EntityRoadrunner;
import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntityTarantulaHawk.AIFleeRoadrunners
extends Goal {
    private int searchCooldown = 0;
    private LivingEntity fear = null;
    private Vec3 fearVec = null;

    private EntityTarantulaHawk.AIFleeRoadrunners() {
    }

    public boolean m_8036_() {
        if (this.searchCooldown <= 0) {
            this.searchCooldown = 100 + EntityTarantulaHawk.this.f_19796_.m_188503_(100);
            List list = EntityTarantulaHawk.this.m_9236_().m_45976_(EntityRoadrunner.class, EntityTarantulaHawk.this.m_20191_().m_82377_(15.0, 32.0, 15.0));
            for (EntityRoadrunner roadrunner : list) {
                if (this.fear != null && !(EntityTarantulaHawk.this.m_20270_((Entity)this.fear) > EntityTarantulaHawk.this.m_20270_((Entity)roadrunner))) continue;
                this.fear = roadrunner;
            }
        } else {
            --this.searchCooldown;
        }
        return EntityTarantulaHawk.this.m_6084_() && this.fear != null;
    }

    public boolean m_8045_() {
        return this.fear != null && this.fear.m_6084_() && EntityTarantulaHawk.this.m_20270_((Entity)this.fear) < 32.0f;
    }

    public void m_8056_() {
        super.m_8056_();
        EntityTarantulaHawk.this.setScared(true);
    }

    public void m_8037_() {
        if (this.fear != null) {
            if (this.fearVec == null || EntityTarantulaHawk.this.m_20238_(this.fearVec) < 4.0) {
                this.fearVec = EntityTarantulaHawk.this.getBlockInViewAway(this.fearVec == null ? this.fear.m_20182_() : this.fearVec, 12.0f);
            }
            if (this.fearVec != null) {
                EntityTarantulaHawk.this.setFlying(true);
                EntityTarantulaHawk.this.m_21566_().m_6849_(this.fearVec.f_82479_, this.fearVec.f_82480_, this.fearVec.f_82481_, (double)1.1f);
            }
        }
    }

    public void m_8041_() {
        EntityTarantulaHawk.this.setScared(false);
        this.fear = null;
        this.fearVec = null;
    }
}
