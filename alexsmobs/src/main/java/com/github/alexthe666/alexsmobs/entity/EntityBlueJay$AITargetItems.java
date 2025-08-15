/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.PathfinderMob
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityBlueJay;
import com.github.alexthe666.alexsmobs.entity.ai.CreatureAITargetItems;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;

private static class EntityBlueJay.AITargetItems
extends CreatureAITargetItems {
    public EntityBlueJay.AITargetItems(PathfinderMob creature, boolean checkSight, boolean onlyNearby, int tickThreshold, int radius) {
        super(creature, checkSight, onlyNearby, tickThreshold, radius);
        this.executionChance = 1;
    }

    @Override
    public void m_8041_() {
        super.m_8041_();
        ((EntityBlueJay)this.f_26135_).aiItemFlag = false;
    }

    @Override
    public boolean m_8036_() {
        return super.m_8036_() && (this.f_26135_.m_5448_() == null || !this.f_26135_.m_5448_().m_6084_());
    }

    @Override
    public boolean m_8045_() {
        return super.m_8045_() && (this.f_26135_.m_5448_() == null || !this.f_26135_.m_5448_().m_6084_());
    }

    @Override
    protected void moveTo() {
        EntityBlueJay jay = (EntityBlueJay)this.f_26135_;
        if (this.targetEntity != null) {
            jay.aiItemFlag = true;
            if (this.f_26135_.m_20270_((Entity)this.targetEntity) < 2.0f) {
                jay.m_21566_().m_6849_(this.targetEntity.m_20185_(), this.targetEntity.m_20186_(), this.targetEntity.m_20189_(), 1.0);
                jay.peck();
            }
            if (this.f_26135_.m_20270_((Entity)this.targetEntity) > 8.0f || jay.isFlying()) {
                jay.setFlying(true);
                float f = (float)(jay.m_20185_() - this.targetEntity.m_20185_());
                float f1 = 1.8f;
                float f2 = (float)(jay.m_20189_() - this.targetEntity.m_20189_());
                float xzDist = Mth.m_14116_((float)(f * f + f2 * f2));
                if (!jay.m_142582_((Entity)this.targetEntity)) {
                    jay.m_21566_().m_6849_(this.targetEntity.m_20185_(), 1.0 + jay.m_20186_(), this.targetEntity.m_20189_(), 1.0);
                } else {
                    if (xzDist < 5.0f) {
                        f1 = 0.0f;
                    }
                    jay.m_21566_().m_6849_(this.targetEntity.m_20185_(), (double)f1 + this.targetEntity.m_20186_(), this.targetEntity.m_20189_(), 1.0);
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
