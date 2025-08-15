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

import com.github.alexthe666.alexsmobs.entity.EntityCrimsonMosquito;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public static class EntityCrimsonMosquito.FlyAwayFromTarget
extends Goal {
    private final EntityCrimsonMosquito parentEntity;
    private int spitCooldown = 0;
    private BlockPos shootPos = null;

    public EntityCrimsonMosquito.FlyAwayFromTarget(EntityCrimsonMosquito mosquito) {
        this.parentEntity = mosquito;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        if (!this.parentEntity.isFlying() || this.parentEntity.getBloodLevel() <= 0 && this.parentEntity.drinkTime >= 0 || this.parentEntity.getFleeingEntityId() != -1) {
            return false;
        }
        if (!this.parentEntity.m_20159_() && this.parentEntity.m_5448_() != null) {
            this.shootPos = this.getBlockInTargetsViewMosquito(this.parentEntity.m_5448_());
            return true;
        }
        return false;
    }

    public boolean m_8045_() {
        return this.parentEntity.m_5448_() != null && (this.parentEntity.getBloodLevel() > 0 || this.parentEntity.drinkTime < 0) && this.parentEntity.isFlying() && !this.parentEntity.f_19862_;
    }

    public void m_8041_() {
        this.spitCooldown = 20;
    }

    public void m_8037_() {
        if (this.spitCooldown > 0) {
            --this.spitCooldown;
        }
        if (this.parentEntity.m_5448_() != null) {
            if (this.shootPos == null) {
                this.shootPos = this.getBlockInTargetsViewMosquito(this.parentEntity.m_5448_());
            } else {
                this.parentEntity.m_21566_().m_6849_((double)this.shootPos.m_123341_() + 0.5, (double)this.shootPos.m_123342_() + 0.5, (double)this.shootPos.m_123343_() + 0.5, 1.0);
                this.parentEntity.m_21391_((Entity)this.parentEntity.m_5448_(), 30.0f, 30.0f);
                if (this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)this.shootPos)) < 2.5) {
                    if (this.spitCooldown == 0 && this.parentEntity.getBloodLevel() > 0) {
                        this.parentEntity.setupShooting();
                        this.spitCooldown = 20;
                    }
                    this.shootPos = null;
                }
            }
        }
    }

    public BlockPos getBlockInTargetsViewMosquito(LivingEntity target) {
        float radius = 4 + this.parentEntity.m_217043_().m_188503_(5);
        float angle = (float)Math.PI / 180 * (target.f_20885_ + 90.0f + (float)this.parentEntity.m_217043_().m_188503_(180));
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos ground = AMBlockPos.fromCoords(target.m_20185_() + extraX, target.m_20186_() + 1.0, target.m_20189_() + extraZ);
        if (this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)ground)) > 30.0 && !this.parentEntity.isTargetBlocked(Vec3.m_82512_((Vec3i)ground)) && this.parentEntity.m_20238_(Vec3.m_82512_((Vec3i)ground)) > 6.0) {
            return ground;
        }
        return this.parentEntity.m_20183_();
    }
}
