/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityFlutter;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntityFlutter.FlyAwayFromTarget
extends Goal {
    private final EntityFlutter parentEntity;
    private int spitCooldown = 0;
    private BlockPos shootPos = null;

    public EntityFlutter.FlyAwayFromTarget(EntityFlutter entityFlutter2) {
        this.parentEntity = entityFlutter2;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        return !this.parentEntity.isSitting() && this.parentEntity.m_5448_() != null && this.parentEntity.m_5448_().m_6084_() && !this.parentEntity.m_6162_();
    }

    public void m_8037_() {
        if (this.spitCooldown > 0) {
            --this.spitCooldown;
        }
        if (this.parentEntity.m_5448_() != null) {
            this.parentEntity.setFlying(true);
            if (this.shootPos == null || this.parentEntity.m_20270_((Entity)this.parentEntity.m_5448_()) >= 10.0f || this.parentEntity.m_5448_().m_20275_((double)((float)this.shootPos.m_123341_() + 0.5f), (double)this.shootPos.m_123342_(), (double)((float)this.shootPos.m_123343_() + 0.5f)) < 4.0) {
                this.shootPos = this.getShootFromPos(this.parentEntity.m_5448_());
            }
            if (this.shootPos != null) {
                this.parentEntity.m_21566_().m_6849_((double)this.shootPos.m_123341_() + 0.5, (double)this.shootPos.m_123342_() + 0.5, (double)this.shootPos.m_123343_() + 0.5, 1.5);
            }
            if (this.parentEntity.m_20270_((Entity)this.parentEntity.m_5448_()) < 25.0f) {
                this.parentEntity.m_21391_((Entity)this.parentEntity.m_5448_(), 30.0f, 30.0f);
                if (this.spitCooldown == 0) {
                    this.parentEntity.setupShooting();
                    this.spitCooldown = 10 + EntityFlutter.this.f_19796_.m_188503_(10);
                }
                this.shootPos = null;
            }
        }
    }

    public BlockPos getShootFromPos(LivingEntity target) {
        float radius = 3 + this.parentEntity.m_217043_().m_188503_(5);
        float angle = (float)Math.PI / 180 * (target.f_20885_ + 90.0f + (float)this.parentEntity.m_217043_().m_188503_(180));
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = AMBlockPos.fromCoords(target.m_20185_() + extraX, target.m_20186_() + 2.0, target.m_20189_() + extraZ);
        if (!this.parentEntity.isTargetBlocked(Vec3.m_82512_((Vec3i)radialPos))) {
            return radialPos;
        }
        return this.parentEntity.m_20183_().m_6630_((int)Math.ceil(target.m_20206_() + 1.0f));
    }
}
