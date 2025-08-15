/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityWarpedToad;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public static class EntityWarpedToad.TongueAttack
extends Goal {
    private final EntityWarpedToad parentEntity;
    private int spitCooldown = 0;
    private BlockPos shootPos = null;

    public EntityWarpedToad.TongueAttack(EntityWarpedToad toad) {
        this.parentEntity = toad;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        return this.parentEntity.m_5448_() != null && this.parentEntity.m_20197_().isEmpty();
    }

    public boolean m_8045_() {
        return this.parentEntity.m_5448_() != null && this.parentEntity.m_20197_().isEmpty();
    }

    public void m_8041_() {
        this.spitCooldown = 20;
        this.parentEntity.m_21573_().m_26573_();
    }

    public void m_8037_() {
        LivingEntity entityIn;
        if (this.spitCooldown > 0) {
            --this.spitCooldown;
        }
        if ((entityIn = this.parentEntity.m_5448_()) != null) {
            double dist = this.parentEntity.m_20270_((Entity)entityIn);
            if (dist < 8.0 && this.parentEntity.m_142582_((Entity)entityIn) && !this.parentEntity.isTongueOut() && this.parentEntity.attackProgress == 0.0f && this.spitCooldown == 0) {
                this.parentEntity.setTongueLength((float)Math.max(1.0, dist + 2.0));
                this.spitCooldown = 10;
                this.parentEntity.setTongueOut(true);
            }
            this.parentEntity.m_21573_().m_5624_((Entity)entityIn, (double)1.4f);
        }
    }
}
