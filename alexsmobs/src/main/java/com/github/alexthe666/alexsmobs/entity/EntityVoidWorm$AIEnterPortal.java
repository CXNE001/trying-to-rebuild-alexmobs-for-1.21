/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.EnumSet;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class EntityVoidWorm.AIEnterPortal
extends Goal {
    public EntityVoidWorm.AIEnterPortal() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        return EntityVoidWorm.this.portalTarget != null;
    }

    public void m_8037_() {
        if (EntityVoidWorm.this.portalTarget != null) {
            double d2;
            double d1;
            EntityVoidWorm.this.f_19794_ = true;
            double centerX = EntityVoidWorm.this.portalTarget.m_20185_();
            double centerY = EntityVoidWorm.this.portalTarget.m_20227_(0.5);
            double centerZ = EntityVoidWorm.this.portalTarget.m_20189_();
            double d0 = centerX - EntityVoidWorm.this.m_20185_();
            Vec3 vec = new Vec3(d0, d1 = centerY - EntityVoidWorm.this.m_20227_(0.5), d2 = centerZ - EntityVoidWorm.this.m_20189_());
            if (vec.m_82553_() > 1.0) {
                vec = vec.m_82541_();
            }
            vec = vec.m_82490_((double)0.4f);
            EntityVoidWorm.this.m_20256_(EntityVoidWorm.this.m_20184_().m_82549_(vec));
        }
    }

    public void m_8041_() {
        EntityVoidWorm.this.f_19794_ = false;
    }
}
