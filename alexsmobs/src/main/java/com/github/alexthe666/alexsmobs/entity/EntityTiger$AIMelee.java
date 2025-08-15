/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  net.minecraft.util.Mth
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityTiger;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.EnumSet;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntityTiger.AIMelee
extends Goal {
    private final EntityTiger tiger;
    private int jumpAttemptCooldown = 0;

    public EntityTiger.AIMelee() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.tiger = EntityTiger.this;
    }

    public boolean m_8036_() {
        return this.tiger.m_5448_() != null && this.tiger.m_5448_().m_6084_();
    }

    public void m_8037_() {
        LivingEntity target;
        if (this.jumpAttemptCooldown > 0) {
            --this.jumpAttemptCooldown;
        }
        if ((target = this.tiger.m_5448_()) != null && target.m_6084_()) {
            double dist = this.tiger.m_20270_((Entity)target);
            if (dist < 10.0 && this.tiger.m_21188_() != null && this.tiger.m_21188_().m_6084_()) {
                this.tiger.setStealth(false);
            } else if (dist > 20.0) {
                this.tiger.setRunning(false);
                this.tiger.setStealth(true);
            }
            if (dist <= 20.0) {
                this.tiger.setStealth(false);
                this.tiger.setRunning(true);
                if (((Integer)this.tiger.f_19804_.m_135370_(LAST_SCARED_MOB_ID)).intValue() != target.m_19879_()) {
                    this.tiger.f_19804_.m_135381_(LAST_SCARED_MOB_ID, (Object)target.m_19879_());
                    target.m_7292_(new MobEffectInstance((MobEffect)AMEffectRegistry.FEAR.get(), 100, 0, true, false));
                }
            }
            if (dist < 12.0 && this.tiger.getAnimation() == IAnimatedEntity.NO_ANIMATION && this.tiger.m_20096_() && this.jumpAttemptCooldown == 0 && !this.tiger.isHolding()) {
                this.tiger.setAnimation(ANIMATION_LEAP);
                this.jumpAttemptCooldown = 70;
            }
            if ((this.jumpAttemptCooldown > 0 || this.tiger.m_20072_()) && !this.tiger.isHolding() && this.tiger.getAnimation() == IAnimatedEntity.NO_ANIMATION && dist < (double)(4.0f + target.m_20205_())) {
                this.tiger.setAnimation(this.tiger.m_217043_().m_188499_() ? ANIMATION_PAW_L : ANIMATION_PAW_R);
            }
            if (dist < (double)(4.0f + target.m_20205_()) && (this.tiger.getAnimation() == ANIMATION_PAW_L || this.tiger.getAnimation() == ANIMATION_PAW_R) && this.tiger.getAnimationTick() == 8) {
                target.m_6469_(this.tiger.m_269291_().m_269333_((LivingEntity)this.tiger), (float)(7 + this.tiger.m_217043_().m_188503_(5)));
            }
            if (this.tiger.getAnimation() == ANIMATION_LEAP) {
                this.tiger.m_21573_().m_26573_();
                Vec3 vec = target.m_20182_().m_82546_(this.tiger.m_20182_());
                this.tiger.m_146922_(-((float)Mth.m_14136_((double)vec.f_82479_, (double)vec.f_82481_)) * 57.295776f);
                this.tiger.f_20883_ = this.tiger.m_146908_();
                if (this.tiger.getAnimationTick() >= 5 && this.tiger.getAnimationTick() < 11 && this.tiger.m_20096_()) {
                    Vec3 vector3d1 = new Vec3(target.m_20185_() - this.tiger.m_20185_(), 0.0, target.m_20189_() - this.tiger.m_20189_());
                    if (vector3d1.m_82556_() > 1.0E-7) {
                        vector3d1 = vector3d1.m_82541_().m_82490_(Math.min(dist, 15.0) * (double)0.2f);
                    }
                    this.tiger.m_20334_(vector3d1.f_82479_, vector3d1.f_82480_ + (double)0.3f + (double)0.1f * Mth.m_14008_((double)(target.m_20188_() - this.tiger.m_20186_()), (double)0.0, (double)2.0), vector3d1.f_82481_);
                }
                if (dist < (double)(target.m_20205_() + 3.0f) && this.tiger.getAnimationTick() >= 15) {
                    target.m_6469_(this.tiger.m_269291_().m_269333_((LivingEntity)this.tiger), 2.0f);
                    this.tiger.setRunning(false);
                    this.tiger.setStealth(false);
                    this.tiger.setHolding(true);
                }
            } else if (target != null) {
                this.tiger.m_21573_().m_5624_((Entity)target, this.tiger.isStealth() ? 0.75 : 1.0);
            }
        }
    }

    public void m_8041_() {
        this.tiger.setStealth(false);
        this.tiger.setRunning(false);
        this.tiger.setHolding(false);
    }
}
