/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;

class EntitySkreecher.MoveController
extends MoveControl {
    private final Mob parentEntity;

    public EntitySkreecher.MoveController() {
        super((Mob)EntitySkreecher.this);
        this.parentEntity = EntitySkreecher.this;
    }

    public void m_8126_() {
        if (this.f_24981_ == MoveControl.Operation.MOVE_TO) {
            Vec3 vector3d = new Vec3(this.f_24975_ - this.parentEntity.m_20185_(), this.f_24976_ - this.parentEntity.m_20186_(), this.f_24977_ - this.parentEntity.m_20189_());
            double d0 = vector3d.m_82553_();
            double width = this.parentEntity.m_20191_().m_82309_();
            Vec3 vector3d1 = vector3d.m_82490_(this.f_24978_ * 0.035 / d0);
            float verticalSpeed = 0.15f;
            this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82549_(vector3d1.m_82542_(1.0, (double)verticalSpeed, 1.0)));
            if (this.parentEntity.m_5448_() != null) {
                double d1 = this.parentEntity.m_5448_().m_20189_() - this.parentEntity.m_20189_();
                double d3 = this.parentEntity.m_5448_().m_20186_() - this.parentEntity.m_20186_();
                double d2 = this.parentEntity.m_5448_().m_20185_() - this.parentEntity.m_20185_();
                float f = Mth.m_14116_((float)((float)(d2 * d2 + d1 * d1)));
                this.parentEntity.m_146922_(-((float)Mth.m_14136_((double)d2, (double)d1)) * 57.295776f);
                this.parentEntity.m_146926_((float)(Mth.m_14136_((double)d3, (double)f) * 57.2957763671875));
                this.parentEntity.f_20883_ = this.parentEntity.m_146908_();
            } else if (d0 >= width) {
                this.parentEntity.m_146922_(-((float)Mth.m_14136_((double)vector3d1.f_82479_, (double)vector3d1.f_82481_)) * 57.295776f);
            }
        }
    }
}
