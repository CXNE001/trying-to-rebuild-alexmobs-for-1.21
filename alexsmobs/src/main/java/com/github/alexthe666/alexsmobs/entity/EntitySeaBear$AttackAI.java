/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntitySeaBear;
import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

private class EntitySeaBear.AttackAI
extends Goal {
    public EntitySeaBear.AttackAI() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        return EntitySeaBear.this.m_5448_() != null && EntitySeaBear.this.m_5448_().m_20072_() && EntitySeaBear.this.m_5448_().m_6084_() && (EntitySeaBear.this.circleCooldown == 0 || EntitySeaBear.this.getAnimation() == ANIMATION_POINT);
    }

    public void m_8037_() {
        LivingEntity enemy = EntitySeaBear.this.m_5448_();
        if (EntitySeaBear.this.getAnimation() == ANIMATION_POINT) {
            EntitySeaBear.this.m_21573_().m_26573_();
            EntitySeaBear.this.m_20256_(EntitySeaBear.this.m_20184_().m_82542_(0.0, 1.0, 0.0));
            EntitySeaBear.this.m_21391_((Entity)enemy, 360.0f, 50.0f);
        } else if (EntitySeaBear.isMobSafe((Entity)enemy) && EntitySeaBear.this.m_20270_((Entity)enemy) < 6.0f) {
            EntitySeaBear.this.circleCooldown = 100 + EntitySeaBear.this.f_19796_.m_188503_(100);
            EntitySeaBear.this.setAnimation(ANIMATION_POINT);
            EntitySeaBear.this.m_21391_((Entity)enemy, 360.0f, 50.0f);
            EntitySeaBear.this.lastCircle = enemy.m_20183_();
        } else {
            EntitySeaBear.this.m_21573_().m_26519_(enemy.m_20185_(), enemy.m_20227_(0.5), enemy.m_20189_(), 1.6);
            if (EntitySeaBear.this.m_142582_((Entity)enemy) && EntitySeaBear.this.m_20270_((Entity)enemy) < 3.5f) {
                EntitySeaBear.this.setAnimation(ANIMATION_ATTACK);
                if (EntitySeaBear.this.getAnimationTick() % 5 == 0) {
                    enemy.m_6469_(EntitySeaBear.this.m_269291_().m_269333_((LivingEntity)EntitySeaBear.this), 6.0f);
                }
            }
        }
    }
}
