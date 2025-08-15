/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.MoveControl
 *  net.minecraft.world.entity.ai.control.MoveControl$Operation
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityEndergrade;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

static class EntityEndergrade.MoveHelperController
extends MoveControl {
    private final EntityEndergrade parentEntity;

    public EntityEndergrade.MoveHelperController(EntityEndergrade sunbird) {
        super((Mob)sunbird);
        this.parentEntity = sunbird;
    }

    public void m_8126_() {
        if (this.f_24981_ == MoveControl.Operation.STRAFE) {
            Vec3 vector3d = new Vec3(this.f_24975_ - this.parentEntity.m_20185_(), this.f_24976_ - this.parentEntity.m_20186_(), this.f_24977_ - this.parentEntity.m_20189_());
            double d0 = vector3d.m_82553_();
            this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82520_(0.0, vector3d.m_82490_(this.f_24978_ * 0.05 / d0).m_7098_(), 0.0));
            float f = (float)this.f_24974_.m_21133_(Attributes.f_22279_);
            float f1 = (float)this.f_24978_ * f;
            this.f_24979_ = 1.0f;
            this.f_24980_ = 0.0f;
            this.f_24974_.m_7910_(f1);
            this.f_24974_.m_21564_(this.f_24979_);
            this.f_24974_.m_21570_(this.f_24980_);
            this.f_24981_ = MoveControl.Operation.WAIT;
        } else if (this.f_24981_ == MoveControl.Operation.MOVE_TO) {
            Vec3 vector3d = new Vec3(this.f_24975_ - this.parentEntity.m_20185_(), this.f_24976_ - this.parentEntity.m_20186_(), this.f_24977_ - this.parentEntity.m_20189_());
            double d0 = vector3d.m_82553_();
            if (d0 < this.parentEntity.m_20191_().m_82309_()) {
                this.f_24981_ = MoveControl.Operation.WAIT;
                this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82490_(0.5));
            } else {
                double localSpeed = this.f_24978_;
                if (this.parentEntity.m_20160_()) {
                    localSpeed *= 1.5;
                }
                this.parentEntity.m_20256_(this.parentEntity.m_20184_().m_82549_(vector3d.m_82490_(localSpeed * 0.005 / d0)));
                if (this.parentEntity.m_5448_() == null) {
                    Vec3 vector3d1 = this.parentEntity.m_20184_();
                    this.parentEntity.m_146922_(-((float)Mth.m_14136_((double)vector3d1.f_82479_, (double)vector3d1.f_82481_)) * 57.295776f);
                    this.parentEntity.f_20883_ = this.parentEntity.m_146908_();
                } else {
                    double d2 = this.parentEntity.m_5448_().m_20185_() - this.parentEntity.m_20185_();
                    double d1 = this.parentEntity.m_5448_().m_20189_() - this.parentEntity.m_20189_();
                    this.parentEntity.m_146922_(-((float)Mth.m_14136_((double)d2, (double)d1)) * 57.295776f);
                    this.parentEntity.f_20883_ = this.parentEntity.m_146908_();
                }
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
