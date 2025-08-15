/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityLeafcutterAnt;
import java.util.EnumSet;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

private class EntityAnteater.AIMelee
extends Goal {
    public EntityAnteater.AIMelee() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        return EntityAnteater.this.m_5448_() != null && EntityAnteater.this.m_5448_().m_6084_() && !EntityAnteater.this.m_6162_();
    }

    public void m_8037_() {
        LivingEntity enemy = EntityAnteater.this.m_5448_();
        if (enemy != null) {
            double attackReachSqr = this.getAttackReachSqr(enemy);
            double distToEnemySqr = EntityAnteater.this.m_20270_((Entity)enemy);
            EntityAnteater.this.m_21391_((Entity)enemy, 100.0f, 5.0f);
            if (enemy instanceof EntityLeafcutterAnt) {
                if (distToEnemySqr <= attackReachSqr + 1.5) {
                    EntityAnteater.this.setAnimation(ANIMATION_TOUNGE_IDLE);
                } else {
                    EntityAnteater.this.m_21391_((Entity)enemy, 5.0f, 5.0f);
                }
                EntityAnteater.this.m_21573_().m_5624_((Entity)enemy, 1.0);
            } else {
                if (distToEnemySqr <= attackReachSqr) {
                    EntityAnteater.this.m_21573_().m_5624_((Entity)enemy, 1.0);
                    EntityAnteater.this.setAnimation(EntityAnteater.this.m_217043_().m_188499_() ? ANIMATION_SLASH_L : ANIMATION_SLASH_R);
                }
                double x = enemy.m_20185_() - EntityAnteater.this.m_20185_();
                double z = enemy.m_20189_() - EntityAnteater.this.m_20189_();
                float f = (float)(Mth.m_14136_((double)z, (double)x) * 57.2957763671875) - 90.0f;
                EntityAnteater.this.m_146922_(f);
                EntityAnteater.this.f_20883_ = f;
                EntityAnteater.this.setStanding(true);
            }
        }
    }

    public void m_8041_() {
        EntityAnteater.this.setStanding(false);
        super.m_8041_();
    }

    protected double getAttackReachSqr(LivingEntity attackTarget) {
        return 2.0f + attackTarget.m_20205_();
    }
}
