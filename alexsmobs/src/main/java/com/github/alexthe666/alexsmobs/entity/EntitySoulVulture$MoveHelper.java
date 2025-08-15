/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntitySoulVulture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

static class EntitySoulVulture.MoveHelper
extends MoveControl {
    private final EntitySoulVulture parentEntity;

    public EntitySoulVulture.MoveHelper(EntitySoulVulture bird) {
        super((Mob)bird);
        this.parentEntity = bird;
    }

    public void m_8126_() {
        if (this.f_24981_ == MoveControl.Operation.MOVE_TO) {
            Vec3 vector3d = new Vec3(this.f_24975_ - this.parentEntity.m_20185_(), this.f_24976_ - this.parentEntity.m_20186_(), this.f_24977_ - this.parentEntity.m_20189_());
            double d5 = vector3d.m_82553_();
            if (d5 < 0.3) {
                this.f_24981_ = MoveControl.Operation.WAIT;
                this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82490_(0.5));
            } else {
                this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82549_(vector3d.m_82490_(this.f_24978_ * 0.05 / d5)));
                Vec3 vector3d1 = this.parentEntity.m_20184_();
                this.parentEntity.m_146922_(-((float)Mth.m_14136_((double)vector3d1.f_82479_, (double)vector3d1.f_82481_)) * 57.295776f);
                this.parentEntity.f_20883_ = this.parentEntity.m_146908_();
            }
        }
    }

    private boolean canReach(Vec3 p_220673_1_, int p_220673_2_) {
        AABB axisalignedbb = this.parentEntity.m_20191_();
        for (int i = 1; i < p_220673_2_; ++i) {
            axisalignedbb = axisalignedbb.m_82383_(p_220673_1_);
            if (this.parentEntity.m_9236_().m_45756_((Entity)this.parentEntity, axisalignedbb)) continue;
            return false;
        }
        return true;
    }
}
