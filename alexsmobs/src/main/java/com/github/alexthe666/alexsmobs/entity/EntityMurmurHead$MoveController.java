/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;

class EntityMurmurHead.MoveController
extends MoveControl {
    private final Mob parentEntity;

    public EntityMurmurHead.MoveController() {
        super((Mob)EntityMurmurHead.this);
        this.parentEntity = EntityMurmurHead.this;
    }

    public void m_8126_() {
        if (EntityMurmurHead.this.isPulledIn()) {
            return;
        }
        float angle = (float)Math.PI / 180 * (this.parentEntity.f_20883_ + 90.0f);
        float radius = (float)Math.sin((float)this.parentEntity.f_19797_ * 0.2f) * 2.0f;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraY = (double)radius * -Math.cos((double)angle - 1.5707963267948966);
        double extraZ = radius * Mth.m_14089_((float)angle);
        Vec3 strafPlus = new Vec3(extraX, extraY, extraZ);
        if (this.f_24981_ == MoveControl.Operation.MOVE_TO) {
            Vec3 vector3d = new Vec3(this.f_24975_ - this.parentEntity.m_20185_(), this.f_24976_ - this.parentEntity.m_20186_(), this.f_24977_ - this.parentEntity.m_20189_());
            double d0 = vector3d.m_82553_();
            double width = this.parentEntity.m_20191_().m_82309_();
            Vec3 shimmy = Vec3.f_82478_;
            LivingEntity attackTarget = this.parentEntity.m_5448_();
            if (attackTarget != null && this.parentEntity.f_19862_) {
                shimmy = new Vec3(0.0, 0.005, 0.0);
            }
            Vec3 vector3d1 = vector3d.m_82490_(this.f_24978_ * 0.05 / d0);
            this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82549_(vector3d1.m_82549_(strafPlus.m_82490_(0.003 * Math.min(d0, 100.0)).m_82549_(shimmy))));
            if (attackTarget == null) {
                if (d0 >= width) {
                    Vec3 deltaMovement = this.parentEntity.m_20184_();
                    this.parentEntity.m_146922_(-((float)Mth.m_14136_((double)deltaMovement.f_82479_, (double)deltaMovement.f_82481_)) * 57.295776f);
                    this.parentEntity.f_20883_ = this.parentEntity.m_146908_();
                }
            } else {
                double d2 = attackTarget.m_20185_() - this.parentEntity.m_20185_();
                double d1 = attackTarget.m_20189_() - this.parentEntity.m_20189_();
                this.parentEntity.m_146922_(-((float)Mth.m_14136_((double)d2, (double)d1)) * 57.295776f);
                this.parentEntity.f_20883_ = this.parentEntity.m_146908_();
            }
        } else if (this.f_24981_ == MoveControl.Operation.WAIT) {
            this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82549_(strafPlus.m_82490_(0.003)));
        }
    }
}
