/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityCachalotWhale;
import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntityGiantSquid.AIAvoidWhales
extends Goal {
    private EntityCachalotWhale whale;
    private Vec3 moveTo;
    private int runDelay;

    public EntityGiantSquid.AIAvoidWhales() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        if (EntityGiantSquid.this.m_20072_() && !EntityGiantSquid.this.f_19862_ && !EntityGiantSquid.this.isCaptured() && this.runDelay-- <= 0) {
            EntityCachalotWhale closest = null;
            float dist = 50.0f;
            for (EntityCachalotWhale dude : EntityGiantSquid.this.m_9236_().m_45976_(EntityCachalotWhale.class, EntityGiantSquid.this.m_20191_().m_82400_((double)dist))) {
                if (closest != null && !(dude.m_20270_((Entity)EntityGiantSquid.this) < closest.m_20270_((Entity)EntityGiantSquid.this))) continue;
                closest = dude;
            }
            if (closest != null) {
                this.whale = closest;
                return true;
            }
            this.runDelay = 50 + EntityGiantSquid.this.f_19796_.m_188503_(50);
        }
        return false;
    }

    public boolean m_8045_() {
        return this.whale != null && this.whale.m_6084_() && !EntityGiantSquid.this.f_19862_ && EntityGiantSquid.this.m_20270_((Entity)this.whale) < 60.0f;
    }

    public void m_8037_() {
        if (this.whale != null && this.whale.m_6084_()) {
            double dist = EntityGiantSquid.this.m_20270_((Entity)this.whale);
            Vec3 vec = EntityGiantSquid.this.m_20182_().m_82546_(this.whale.m_20182_()).m_82541_();
            Vec3 vec2 = EntityGiantSquid.this.m_20182_().m_82549_(vec.m_82490_((double)(12 + EntityGiantSquid.this.f_19796_.m_188503_(5))));
            EntityGiantSquid.this.m_21573_().m_26519_(vec2.f_82479_, vec2.f_82480_, vec2.f_82481_, dist < 20.0 ? (double)1.9f : (double)1.3f);
        }
    }

    public void m_8041_() {
        this.whale = null;
        this.moveTo = null;
    }
}
