/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.Animation
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityBison;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;

private class EntityBison.AIChargeFurthest
extends Goal {
    public EntityBison.AIChargeFurthest() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        if (EntityBison.this.isValidCharging()) {
            if (EntityBison.this.chargePartner != null && EntityBison.this.chargePartner.isValidCharging() && EntityBison.this.chargePartner != EntityBison.this) {
                EntityBison.this.chargePartner.chargePartner = EntityBison.this;
                return true;
            }
            if (EntityBison.this.f_19796_.m_188503_(100) == 0) {
                EntityBison furthest = null;
                for (EntityBison bison : EntityBison.this.m_9236_().m_45976_(EntityBison.class, EntityBison.this.m_20191_().m_82400_(15.0))) {
                    if (bison.chargeCooldown != 0 || bison.m_6162_() || bison.m_7306_((Entity)EntityBison.this) || furthest != null && !(EntityBison.this.m_20270_((Entity)furthest) < EntityBison.this.m_20270_((Entity)bison))) continue;
                    furthest = bison;
                }
                if (furthest != null && furthest != EntityBison.this) {
                    EntityBison.this.chargePartner = furthest;
                    furthest.chargePartner = EntityBison.this;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean m_8045_() {
        return EntityBison.this.isValidCharging() && EntityBison.this.chargePartner != null && EntityBison.this.chargePartner.isValidCharging() && !EntityBison.this.chargePartner.m_7306_((Entity)EntityBison.this);
    }

    public void m_8037_() {
        EntityBison.this.m_21391_((Entity)EntityBison.this.chargePartner, 30.0f, 30.0f);
        EntityBison.this.f_20883_ = EntityBison.this.m_146908_();
        if (!EntityBison.this.isCharging()) {
            Animation bisonAnimation = EntityBison.this.getAnimation();
            if (bisonAnimation == IAnimatedEntity.NO_ANIMATION || bisonAnimation == ANIMATION_PREPARE_CHARGE && EntityBison.this.getAnimationTick() > 35) {
                EntityBison.this.setCharging(true);
            }
        } else {
            float dist = EntityBison.this.m_20270_((Entity)EntityBison.this.chargePartner);
            EntityBison.this.m_21573_().m_5624_((Entity)EntityBison.this.chargePartner, 1.0);
            if (EntityBison.this.m_142582_((Entity)EntityBison.this.chargePartner)) {
                float flingAnimAt = EntityBison.this.m_20205_() + 1.0f;
                if (dist < flingAnimAt && EntityBison.this.getAnimation() == ANIMATION_ATTACK) {
                    if (EntityBison.this.getAnimationTick() > 8) {
                        boolean flag = false;
                        if (EntityBison.this.m_20096_()) {
                            EntityBison.this.pushBackJostling(EntityBison.this.chargePartner, 0.2f);
                            flag = true;
                        }
                        if (EntityBison.this.chargePartner.m_20096_()) {
                            EntityBison.this.chargePartner.pushBackJostling(EntityBison.this, 0.9f);
                            flag = true;
                        }
                        if (flag) {
                            EntityBison.this.resetChargeCooldown();
                        }
                    }
                } else {
                    float startFlingAnimAt = EntityBison.this.m_20205_() + 3.0f;
                    if (dist < startFlingAnimAt && EntityBison.this.getAnimation() != ANIMATION_ATTACK) {
                        EntityBison.this.setAnimation(ANIMATION_ATTACK);
                    }
                }
            }
        }
    }
}
