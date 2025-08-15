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
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

private class EntityRockyRoller.AIMelee
extends Goal {
    private BlockPos rollFromPos = null;
    private int rollTimeout = 0;

    public EntityRockyRoller.AIMelee() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        return EntityRockyRoller.this.m_5448_() != null && EntityRockyRoller.this.m_5448_().m_6084_() && !EntityRockyRoller.this.isRolling();
    }

    public void m_8037_() {
        LivingEntity enemy = EntityRockyRoller.this.m_5448_();
        double d0 = this.validRollDistance(enemy);
        double distToEnemySqr = EntityRockyRoller.this.m_20270_((Entity)enemy);
        if (this.rollFromPos == null || enemy.m_20275_((double)((float)this.rollFromPos.m_123341_() + 0.5f), (double)((float)this.rollFromPos.m_123342_() + 0.5f), (double)this.rollFromPos.m_123343_() + 0.5) > 60.0 || !this.canEntitySeePosition(enemy, this.rollFromPos)) {
            this.rollFromPos = this.getRollAtPosition((Entity)enemy);
        }
        EntityRockyRoller.this.m_21391_((Entity)enemy, 100.0f, 5.0f);
        if (this.rollTimeout < 40 && this.rollFromPos != null && distToEnemySqr <= d0 && EntityRockyRoller.this.m_20275_((float)this.rollFromPos.m_123341_() + 0.5f, (float)this.rollFromPos.m_123342_() + 0.5f, (double)this.rollFromPos.m_123343_() + 0.5) > 2.25) {
            EntityRockyRoller.this.m_21573_().m_26519_((double)((float)this.rollFromPos.m_123341_() + 0.5f), (double)((float)this.rollFromPos.m_123342_() + 0.5f), (double)((float)this.rollFromPos.m_123343_() + 0.5f), 1.6);
            ++this.rollTimeout;
        } else {
            double d1 = enemy.m_20185_() - EntityRockyRoller.this.m_20185_();
            double d2 = enemy.m_20189_() - EntityRockyRoller.this.m_20189_();
            float f = (float)(Mth.m_14136_((double)d2, (double)d1) * 57.2957763671875) - 90.0f;
            EntityRockyRoller.this.m_146922_(f);
            EntityRockyRoller.this.f_20883_ = f;
            EntityRockyRoller.this.rollFor(30 + EntityRockyRoller.this.f_19796_.m_188503_(40));
        }
    }

    public void m_8041_() {
        super.m_8041_();
        this.rollTimeout = 0;
    }

    protected double validRollDistance(LivingEntity attackTarget) {
        return 3.0f + attackTarget.m_20205_();
    }

    private boolean canEntitySeePosition(LivingEntity entity, BlockPos destinationBlock) {
        Vec3 Vector3d = new Vec3(entity.m_20185_(), entity.m_20186_() + 0.5, entity.m_20189_());
        Vec3 blockVec = Vec3.m_82512_((Vec3i)destinationBlock);
        BlockHitResult result = entity.m_9236_().m_45547_(new ClipContext(Vector3d, blockVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)entity));
        return result != null && (result.m_82425_().equals((Object)destinationBlock) || entity.m_9236_().m_8055_(result.m_82425_()).m_60734_() == Blocks.f_152588_);
    }

    public BlockPos getRollAtPosition(Entity target) {
        float radius = (float)(EntityRockyRoller.this.m_217043_().m_188503_(2) + 6) + target.m_20205_();
        int orbit = EntityRockyRoller.this.m_217043_().m_188503_(360);
        float angle = (float)Math.PI / 180 * (float)orbit;
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos circlePos = new BlockPos((int)(target.m_20185_() + extraX), (int)target.m_20188_(), (int)(target.m_20189_() + extraZ));
        while (!EntityRockyRoller.this.m_9236_().m_8055_(circlePos).m_60795_() && circlePos.m_123342_() < EntityRockyRoller.this.m_9236_().m_151558_()) {
            circlePos = circlePos.m_7494_();
        }
        while (!EntityRockyRoller.this.m_9236_().m_8055_(circlePos.m_7495_()).m_60634_((BlockGetter)EntityRockyRoller.this.m_9236_(), circlePos.m_7495_(), (Entity)EntityRockyRoller.this) && circlePos.m_123342_() > 1) {
            circlePos = circlePos.m_7495_();
        }
        if (EntityRockyRoller.this.m_21692_(circlePos) > -1.0f) {
            return circlePos;
        }
        return null;
    }
}
