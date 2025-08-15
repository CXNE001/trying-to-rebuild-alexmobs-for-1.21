/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntityPotoo.AIFlyIdle
extends Goal {
    protected double x;
    protected double y;
    protected double z;

    public EntityPotoo.AIFlyIdle() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        if (EntityPotoo.this.m_20160_() || EntityPotoo.this.isPerching() || EntityPotoo.this.m_5448_() != null && EntityPotoo.this.m_5448_().m_6084_() || EntityPotoo.this.m_20159_()) {
            return false;
        }
        if (EntityPotoo.this.m_217043_().m_188503_(45) != 0 && !EntityPotoo.this.isFlying()) {
            return false;
        }
        Vec3 lvt_1_1_ = this.getPosition();
        if (lvt_1_1_ == null) {
            return false;
        }
        this.x = lvt_1_1_.f_82479_;
        this.y = lvt_1_1_.f_82480_;
        this.z = lvt_1_1_.f_82481_;
        return true;
    }

    public void m_8037_() {
        EntityPotoo.this.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
        if (EntityPotoo.this.isFlying() && EntityPotoo.this.m_20096_() && EntityPotoo.this.timeFlying > 10) {
            EntityPotoo.this.setFlying(false);
        }
    }

    @Nullable
    protected Vec3 getPosition() {
        Vec3 vector3d = EntityPotoo.this.m_20182_();
        if (EntityPotoo.this.timeFlying < 200 || EntityPotoo.this.isOverWaterOrVoid()) {
            return EntityPotoo.this.getBlockInViewAway(vector3d, 0.0f);
        }
        return EntityPotoo.this.getBlockGrounding(vector3d);
    }

    public boolean m_8045_() {
        return EntityPotoo.this.isFlying() && EntityPotoo.this.m_20275_(this.x, this.y, this.z) > 5.0;
    }

    public void m_8056_() {
        EntityPotoo.this.setFlying(true);
        EntityPotoo.this.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
    }

    public void m_8041_() {
        EntityPotoo.this.m_21573_().m_26573_();
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        super.m_8041_();
    }
}
