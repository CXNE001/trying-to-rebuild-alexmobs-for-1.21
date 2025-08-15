/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntitySkreecher.FollowTargetGoal
extends Goal {
    public EntitySkreecher.FollowTargetGoal() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        return EntitySkreecher.this.m_5448_() != null && EntitySkreecher.this.m_5448_().m_6084_() && EntitySkreecher.this.clingCooldown <= 0;
    }

    public void m_8056_() {
        EntitySkreecher.this.m_5496_((SoundEvent)AMSoundRegistry.SKREECHER_DETECT.get(), EntitySkreecher.this.m_6121_() * 6.0f, EntitySkreecher.this.m_6100_());
    }

    public void m_8037_() {
        LivingEntity target = EntitySkreecher.this.m_5448_();
        if (target != null) {
            if (EntitySkreecher.this.isClinging()) {
                BlockPos ceilAbove = EntitySkreecher.this.getCeilingOf(target.m_20183_().m_7494_());
                EntitySkreecher.this.m_21573_().m_26519_(target.m_20185_(), (double)((float)ceilAbove.m_123342_() - EntitySkreecher.this.f_19796_.m_188501_() * 4.0f), target.m_20189_(), (double)1.2f);
            } else {
                EntitySkreecher.this.m_21573_().m_26519_(target.m_20185_(), target.m_20186_(), target.m_20189_(), 1.0);
            }
            Vec3 vec = target.m_20182_().m_82546_(EntitySkreecher.this.m_20182_());
            EntitySkreecher.this.m_21563_().m_24960_((Entity)target, 360.0f, 180.0f);
            if (vec.m_165924_() < 2.5 && EntitySkreecher.this.clingCooldown == 0) {
                EntitySkreecher.this.setClapping(true);
            }
        }
    }
}
