/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.PathfinderMob
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityCrow;
import com.github.alexthe666.alexsmobs.entity.ai.CreatureAITargetItems;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;

private static class EntityCrow.AITargetItems
extends CreatureAITargetItems {
    public EntityCrow.AITargetItems(PathfinderMob creature, boolean checkSight, boolean onlyNearby, int tickThreshold, int radius) {
        super(creature, checkSight, onlyNearby, tickThreshold, radius);
        this.executionChance = 1;
    }

    @Override
    public void m_8041_() {
        super.m_8041_();
        ((EntityCrow)this.f_26135_).aiItemFlag = false;
    }

    @Override
    public boolean m_8036_() {
        return super.m_8036_() && !((EntityCrow)this.f_26135_).isSitting() && (this.f_26135_.m_5448_() == null || !this.f_26135_.m_5448_().m_6084_());
    }

    @Override
    public boolean m_8045_() {
        return super.m_8045_() && !((EntityCrow)this.f_26135_).isSitting() && (this.f_26135_.m_5448_() == null || !this.f_26135_.m_5448_().m_6084_());
    }

    @Override
    protected void moveTo() {
        EntityCrow crow = (EntityCrow)this.f_26135_;
        if (this.targetEntity != null) {
            crow.aiItemFlag = true;
            if (this.f_26135_.m_20270_((Entity)this.targetEntity) < 2.0f) {
                crow.m_21566_().m_6849_(this.targetEntity.m_20185_(), this.targetEntity.m_20186_(), this.targetEntity.m_20189_(), 1.0);
                crow.peck();
            }
            if (this.f_26135_.m_20270_((Entity)this.targetEntity) > 8.0f || crow.isFlying()) {
                crow.setFlying(true);
                if (!crow.m_142582_((Entity)this.targetEntity)) {
                    crow.m_21566_().m_6849_(this.targetEntity.m_20185_(), 1.0 + crow.m_20186_(), this.targetEntity.m_20189_(), 1.0);
                } else {
                    float f2;
                    float f = (float)(crow.m_20185_() - this.targetEntity.m_20185_());
                    float xzDist = Mth.m_14116_((float)(f * f + (f2 = (float)(crow.m_20189_() - this.targetEntity.m_20189_())) * f2));
                    float f1 = xzDist < 5.0f ? 0.0f : 1.8f;
                    crow.m_21566_().m_6849_(this.targetEntity.m_20185_(), (double)f1 + this.targetEntity.m_20186_(), this.targetEntity.m_20189_(), 1.0);
                }
            } else {
                this.f_26135_.m_21573_().m_26519_(this.targetEntity.m_20185_(), this.targetEntity.m_20186_(), this.targetEntity.m_20189_(), 1.0);
            }
        }
    }

    @Override
    public void m_8037_() {
        super.m_8037_();
        this.moveTo();
    }
}
