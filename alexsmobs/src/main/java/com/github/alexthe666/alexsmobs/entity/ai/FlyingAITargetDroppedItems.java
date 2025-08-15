/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.PathfinderMob
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.ai.CreatureAITargetItems;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;

public class FlyingAITargetDroppedItems
extends CreatureAITargetItems {
    public FlyingAITargetDroppedItems(PathfinderMob creature, boolean checkSight, boolean onlyNearby, int tickThreshold, int radius) {
        super(creature, checkSight, onlyNearby, tickThreshold, radius);
        this.executionChance = 1;
    }

    @Override
    public void m_8041_() {
        super.m_8041_();
        this.hunter.setItemFlag(false);
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
        if (this.targetEntity != null) {
            this.hunter.setItemFlag(true);
            if (this.f_26135_.m_20270_((Entity)this.targetEntity) < 2.0f) {
                this.f_26135_.m_21566_().m_6849_(this.targetEntity.m_20185_(), this.targetEntity.m_20186_(), this.targetEntity.m_20189_(), 1.5);
                this.hunter.peck();
            }
            if (this.f_26135_.m_20270_((Entity)this.targetEntity) > 8.0f || this.hunter.isFlying()) {
                this.hunter.setFlying(true);
                float f = (float)(this.f_26135_.m_20185_() - this.targetEntity.m_20185_());
                float f1 = 1.8f;
                float f2 = (float)(this.f_26135_.m_20189_() - this.targetEntity.m_20189_());
                float xzDist = Mth.m_14116_((float)(f * f + f2 * f2));
                if (!this.f_26135_.m_142582_((Entity)this.targetEntity)) {
                    this.f_26135_.m_21566_().m_6849_(this.targetEntity.m_20185_(), 1.0 + this.f_26135_.m_20186_(), this.targetEntity.m_20189_(), 1.5);
                } else {
                    if (xzDist < 5.0f) {
                        f1 = 0.0f;
                    }
                    this.f_26135_.m_21566_().m_6849_(this.targetEntity.m_20185_(), (double)f1 + this.targetEntity.m_20186_(), this.targetEntity.m_20189_(), 1.5);
                }
            } else {
                this.f_26135_.m_21573_().m_26519_(this.targetEntity.m_20185_(), this.targetEntity.m_20186_(), this.targetEntity.m_20189_(), 1.5);
            }
        }
    }

    @Override
    public void m_8037_() {
        super.m_8037_();
        this.moveTo();
    }
}
