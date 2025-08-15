/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityAnaconda;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

private class EntityAnaconda.AIMelee
extends Goal {
    private final EntityAnaconda snake;
    private int jumpAttemptCooldown = 0;

    public EntityAnaconda.AIMelee() {
        this.snake = EntityAnaconda.this;
    }

    public boolean m_8036_() {
        return this.snake.m_5448_() != null && this.snake.m_5448_().m_6084_();
    }

    public void m_8037_() {
        LivingEntity target;
        if (this.jumpAttemptCooldown > 0) {
            --this.jumpAttemptCooldown;
        }
        if ((target = this.snake.m_5448_()) != null && target.m_6084_()) {
            if (this.jumpAttemptCooldown == 0 && this.snake.m_20270_((Entity)target) < 1.0f + target.m_20205_() && !this.snake.isStrangling()) {
                target.m_6469_(this.snake.m_269291_().m_269333_((LivingEntity)this.snake), 4.0f);
                this.snake.setStrangling(target.m_20205_() <= 2.0f && !(target instanceof EntityAnaconda));
                this.snake.m_5496_((SoundEvent)AMSoundRegistry.ANACONDA_ATTACK.get(), this.snake.m_6121_(), this.snake.m_6100_());
                this.jumpAttemptCooldown = 5 + EntityAnaconda.this.f_19796_.m_188503_(5);
            }
            if (this.snake.isStrangling()) {
                this.snake.m_21573_().m_26573_();
            } else {
                try {
                    this.snake.m_21573_().m_5624_((Entity)target, (double)1.3f);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void m_8041_() {
        this.snake.setStrangling(false);
    }
}
