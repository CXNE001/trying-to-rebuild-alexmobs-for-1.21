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

import com.github.alexthe666.alexsmobs.entity.EntityVoidWorm;
import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntityVoidWorm.AIFlyIdle
extends Goal {
    protected final EntityVoidWorm voidWorm;
    protected double x;
    protected double y;
    protected double z;

    public EntityVoidWorm.AIFlyIdle() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.voidWorm = EntityVoidWorm.this;
    }

    public boolean m_8036_() {
        if (this.voidWorm.m_20160_() || this.voidWorm.portalTarget != null || this.voidWorm.m_5448_() != null && this.voidWorm.m_5448_().m_6084_() || this.voidWorm.m_20159_()) {
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
        this.voidWorm.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
    }

    @Nullable
    protected Vec3 getPosition() {
        Vec3 vector3d = this.voidWorm.m_20182_();
        return this.voidWorm.getBlockInViewAway(vector3d, 1.0f);
    }

    public boolean m_8045_() {
        return this.voidWorm.m_20275_(this.x, this.y, this.z) > 20.0 && this.voidWorm.portalTarget == null && !this.voidWorm.f_19862_ && (this.voidWorm.m_5448_() == null || !this.voidWorm.m_5448_().m_6084_());
    }

    public void m_8056_() {
        this.voidWorm.m_21566_().m_6849_(this.x, this.y, this.z, 1.0);
    }

    public void m_8041_() {
        this.voidWorm.m_21573_().m_26573_();
        super.m_8041_();
    }
}
