/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.animal.AbstractFish
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityBaldEagle;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.phys.Vec3;

private class EntityBaldEagle.AITackle
extends Goal {
    protected EntityBaldEagle eagle;
    private int circleTime;
    private int maxCircleTime = 10;

    public EntityBaldEagle.AITackle() {
        this.eagle = EntityBaldEagle.this;
    }

    public boolean m_8036_() {
        return this.eagle.m_5448_() != null && !this.eagle.controlledFlag && !this.eagle.m_20160_();
    }

    public void m_8056_() {
        this.eagle.orbitPos = null;
    }

    public void m_8041_() {
        this.circleTime = 0;
        this.maxCircleTime = 60 + EntityBaldEagle.this.f_19796_.m_188503_(60);
    }

    public void m_8037_() {
        boolean smallPrey;
        LivingEntity target = this.eagle.m_5448_();
        boolean bl = smallPrey = target != null && target.m_20206_() < 1.0f && target.m_20205_() < 0.7f && !(target instanceof EntityBaldEagle) || target instanceof AbstractFish;
        if (this.eagle.orbitPos != null && this.circleTime < this.maxCircleTime) {
            Vec3 vec;
            ++this.circleTime;
            this.eagle.setTackling(false);
            this.eagle.setFlying(true);
            if (target != null) {
                int i = 0;
                int up = 2 + this.eagle.m_217043_().m_188503_(4);
                this.eagle.orbitPos = target.m_20183_().m_6630_((int)target.m_20206_());
                while (this.eagle.m_9236_().m_46859_(this.eagle.orbitPos) && i < up) {
                    ++i;
                    this.eagle.orbitPos = this.eagle.orbitPos.m_7494_();
                }
            }
            if ((vec = this.eagle.getOrbitVec(Vec3.f_82478_, 4 + EntityBaldEagle.this.f_19796_.m_188503_(2))) != null) {
                this.eagle.m_21566_().m_6849_(vec.f_82479_, vec.f_82480_, vec.f_82481_, (double)1.2f);
            }
        } else if (target != null) {
            if (this.eagle.isFlying() || this.eagle.m_20072_()) {
                double d0 = this.eagle.m_20185_() - target.m_20185_();
                double d2 = this.eagle.m_20189_() - target.m_20189_();
                double xzDist = Math.sqrt(d0 * d0 + d2 * d2);
                double yAddition = target.m_20206_();
                if (xzDist > 15.0) {
                    yAddition = 3.0;
                }
                this.eagle.setTackling(true);
                this.eagle.m_21566_().m_6849_(target.m_20185_(), target.m_20186_() + yAddition, target.m_20189_(), this.eagle.m_20072_() ? (double)1.3f : 1.0);
            } else {
                this.eagle.m_21573_().m_5624_((Entity)target, 1.0);
            }
            if (this.eagle.m_20270_((Entity)target) < target.m_20205_() + 2.5f) {
                if (this.eagle.isTackling()) {
                    if (smallPrey) {
                        this.eagle.setFlying(true);
                        this.eagle.timeFlying = 0;
                        float radius = 0.3f;
                        float angle = (float)Math.PI / 180 * this.eagle.f_20883_;
                        double extraX = 0.3f * Mth.m_14031_((float)((float)Math.PI + angle));
                        double extraZ = 0.3f * Mth.m_14089_((float)angle);
                        target.m_146922_(this.eagle.f_20883_ + 90.0f);
                        if (target instanceof LivingEntity) {
                            LivingEntity living = target;
                            living.f_20883_ = this.eagle.f_20883_ + 90.0f;
                        }
                        target.m_6034_(this.eagle.m_20185_() + extraX, this.eagle.m_20186_() - (double)0.4f + (double)(target.m_20206_() * 0.45f), this.eagle.m_20189_() + extraZ);
                        target.m_7998_((Entity)this.eagle, true);
                    } else {
                        target.m_6469_(this.eagle.m_269291_().m_269333_((LivingEntity)this.eagle), 5.0f);
                        this.eagle.setFlying(false);
                        this.eagle.orbitPos = target.m_20183_().m_6630_(2);
                        this.circleTime = 0;
                        this.maxCircleTime = 60 + EntityBaldEagle.this.f_19796_.m_188503_(60);
                    }
                } else {
                    this.eagle.m_7327_((Entity)target);
                }
            } else if (this.eagle.m_20270_((Entity)target) > 12.0f || target.m_20072_()) {
                this.eagle.setFlying(true);
            }
        }
        if (this.eagle.isLaunched()) {
            this.eagle.setFlying(true);
        }
    }
}
