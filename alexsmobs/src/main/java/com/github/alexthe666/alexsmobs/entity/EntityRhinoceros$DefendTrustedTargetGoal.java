/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

class EntityRhinoceros.DefendTrustedTargetGoal
extends NearestAttackableTargetGoal<LivingEntity> {
    private LivingEntity trustedLastHurtBy;
    private LivingEntity trustedLastHurt;
    private LivingEntity trusted;
    private int timestamp;

    public EntityRhinoceros.DefendTrustedTargetGoal(Class<LivingEntity> entities, @Nullable boolean b, boolean b2, Predicate<LivingEntity> pred) {
        super((Mob)EntityRhinoceros.this, entities, 10, b, b2, pred);
    }

    public boolean m_8036_() {
        if (this.f_26049_ > 0 && this.f_26135_.m_217043_().m_188503_(this.f_26049_) != 0 || this.f_26135_.m_6162_()) {
            return false;
        }
        for (UUID uuid : EntityRhinoceros.this.getTrustedUUIDs()) {
            LivingEntity livingentity;
            Entity entity;
            if (uuid == null || !(EntityRhinoceros.this.m_9236_() instanceof ServerLevel) || !((entity = ((ServerLevel)EntityRhinoceros.this.m_9236_()).m_8791_(uuid)) instanceof LivingEntity)) continue;
            this.trusted = livingentity = (LivingEntity)entity;
            this.trustedLastHurtBy = livingentity.m_21188_();
            this.trustedLastHurt = livingentity.m_21214_();
            int i = livingentity.m_21213_();
            int j = livingentity.m_21215_();
            if (i != this.timestamp && this.m_26150_(this.trustedLastHurtBy, this.f_26051_)) {
                return true;
            }
            if (j == this.timestamp || !this.m_26150_(this.trustedLastHurt, this.f_26051_)) continue;
            return true;
        }
        return false;
    }

    public void m_8056_() {
        if (this.trustedLastHurtBy != null) {
            this.m_26070_(this.trustedLastHurtBy);
            this.f_26050_ = this.trustedLastHurtBy;
            if (this.trusted != null) {
                this.timestamp = this.trusted.m_21213_();
            }
        } else {
            this.m_26070_(this.trustedLastHurt);
            this.f_26050_ = this.trustedLastHurt;
            if (this.trusted != null) {
                this.timestamp = this.trusted.m_21215_();
            }
        }
        super.m_8056_();
    }
}
