/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

private class EntityPotoo.AIMelee
extends Goal {
    private int biteCooldown = 0;

    public EntityPotoo.AIMelee() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        return !EntityPotoo.this.m_5803_() && !EntityPotoo.this.m_20159_() && EntityPotoo.this.m_5448_() != null && EntityPotoo.this.m_5448_().m_6084_();
    }

    public void m_8037_() {
        LivingEntity entity;
        if (this.biteCooldown > 0) {
            --this.biteCooldown;
        }
        if ((entity = EntityPotoo.this.m_5448_()) != null) {
            EntityPotoo.this.setFlying(true);
            EntityPotoo.this.setPerching(false);
            EntityPotoo.this.m_21566_().m_6849_(entity.m_20185_(), entity.m_20227_(0.5), entity.m_20189_(), 1.5);
            if (EntityPotoo.this.m_20270_((Entity)entity) < 1.4f) {
                if (this.biteCooldown == 0) {
                    EntityPotoo.this.openMouth(7);
                    this.biteCooldown = 10;
                }
                if (EntityPotoo.this.mouthProgress >= 4.5f) {
                    entity.m_6469_(EntityPotoo.this.m_269291_().m_269333_((LivingEntity)EntityPotoo.this), 2.0f);
                    if (entity.m_20205_() <= 0.5f) {
                        entity.m_142687_(Entity.RemovalReason.KILLED);
                    }
                }
            }
        }
    }
}
