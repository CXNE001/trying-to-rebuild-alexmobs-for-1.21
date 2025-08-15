/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.LevelAccessor
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityDropBear;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.LevelAccessor;

private class EntityDropBear.AIDropMelee
extends Goal {
    private boolean prevOnGround = false;

    public EntityDropBear.AIDropMelee() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        return EntityDropBear.this.m_5448_() != null;
    }

    public void m_8037_() {
        LivingEntity target = EntityDropBear.this.m_5448_();
        if (target != null) {
            double dist = EntityDropBear.this.m_20270_((Entity)target);
            if (EntityDropBear.this.isUpsideDown()) {
                double d0 = EntityDropBear.this.m_20185_() - target.m_20185_();
                double d2 = EntityDropBear.this.m_20189_() - target.m_20189_();
                double xzDistSqr = d0 * d0 + d2 * d2;
                BlockPos ceilingPos = new BlockPos((int)target.m_20185_(), (int)(EntityDropBear.this.m_20186_() - 3.0 - (double)EntityDropBear.this.f_19796_.m_188503_(3)), (int)target.m_20189_());
                BlockPos lowestPos = EntityDropBear.getLowestPos((LevelAccessor)EntityDropBear.this.m_9236_(), ceilingPos);
                EntityDropBear.this.m_21566_().m_6849_((double)((float)lowestPos.m_123341_() + 0.5f), (double)ceilingPos.m_123342_(), (double)((float)lowestPos.m_123343_() + 0.5f), 1.1);
                if (xzDistSqr < 2.5) {
                    EntityDropBear.this.setUpsideDown(false);
                }
            } else if (EntityDropBear.this.m_20096_()) {
                EntityDropBear.this.m_21573_().m_5624_((Entity)target, 1.2);
            }
            if (dist < 3.0) {
                EntityDropBear.this.m_7327_((Entity)target);
            }
        }
    }
}
