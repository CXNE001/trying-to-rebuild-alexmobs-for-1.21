/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.util.Mth
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.MultifaceBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.entity.EntitySkunk;
import com.github.alexthe666.alexsmobs.misc.AMAdvancementTriggerRegistry;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.Collection;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

private class EntitySkunk.SprayGoal
extends Goal {
    private int actualSprayTime = 0;

    public EntitySkunk.SprayGoal() {
        this.m_7021_(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        return EntitySkunk.this.getSprayTime() > 0;
    }

    public void m_8041_() {
        this.actualSprayTime = 0;
    }

    public void m_8037_() {
        EntitySkunk.this.m_21573_().m_26573_();
        Vec3 sprayAt = this.getSprayAt();
        double d0 = EntitySkunk.this.m_20185_() - sprayAt.f_82479_;
        double d2 = EntitySkunk.this.m_20189_() - sprayAt.f_82481_;
        float f = (float)(Mth.m_14136_((double)d2, (double)d0) * 57.2957763671875) - 90.0f;
        EntitySkunk.this.setSprayYaw(f);
        if (EntitySkunk.this.sprayProgress >= 5.0f) {
            EntitySkunk.this.m_9236_().m_7605_((Entity)EntitySkunk.this, (byte)48);
            if (this.actualSprayTime > 10 && EntitySkunk.this.f_19796_.m_188503_(2) == 0) {
                Vec3 skunkPos = new Vec3(EntitySkunk.this.m_20185_(), EntitySkunk.this.m_20188_(), EntitySkunk.this.m_20189_());
                float xAdd = EntitySkunk.this.f_19796_.m_188501_() * 20.0f - 10.0f;
                float yAdd = EntitySkunk.this.f_19796_.m_188501_() * 20.0f - 10.0f;
                float maxSprayDist = 5.0f;
                Vec3 modelBack = new Vec3(0.0, 0.0, -5.0).m_82496_((xAdd - EntitySkunk.this.m_146909_()) * ((float)Math.PI / 180)).m_82524_((yAdd - EntitySkunk.this.m_146908_()) * ((float)Math.PI / 180));
                BlockHitResult hitResult = EntitySkunk.this.m_9236_().m_45547_(new ClipContext(skunkPos, skunkPos.m_82549_(modelBack), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)EntitySkunk.this));
                if (hitResult != null) {
                    Direction dir;
                    BlockPos pos;
                    if (hitResult instanceof BlockHitResult) {
                        BlockHitResult block = hitResult;
                        pos = block.m_82425_().m_121945_(block.m_82434_());
                        dir = block.m_82434_().m_122424_();
                    } else {
                        pos = AMBlockPos.fromVec3(hitResult.m_82450_());
                        dir = Direction.UP;
                    }
                    BlockState currentState = EntitySkunk.this.m_9236_().m_8055_(pos);
                    BlockState sprayState = ((MultifaceBlock)AMBlockRegistry.SKUNK_SPRAY.get()).m_153940_(EntitySkunk.this.m_9236_().m_8055_(pos), (BlockGetter)EntitySkunk.this.m_9236_(), pos, dir);
                    if ((currentState.m_60795_() || currentState.m_247087_()) && sprayState != null && sprayState.m_60713_((Block)AMBlockRegistry.SKUNK_SPRAY.get())) {
                        EntitySkunk.this.m_9236_().m_46597_(pos, sprayState);
                    }
                    double sprayDist = hitResult.m_82450_().m_82546_(skunkPos).m_82553_() / 5.0;
                    AABB poisonBox = new AABB(skunkPos, skunkPos.m_82549_(modelBack.m_82490_(sprayDist)).m_82520_(0.0, 1.5, 0.0)).m_82400_(1.0);
                    Collection collection = EntitySkunk.this.m_21220_();
                    for (LivingEntity entity : EntitySkunk.this.m_9236_().m_45976_(LivingEntity.class, poisonBox)) {
                        if (entity instanceof EntitySkunk) continue;
                        entity.m_7292_(new MobEffectInstance(MobEffects.f_19604_, 300));
                        if (entity instanceof ServerPlayer) {
                            ServerPlayer serverPlayer = (ServerPlayer)entity;
                            AMAdvancementTriggerRegistry.SKUNK_SPRAY.trigger(serverPlayer);
                        }
                        for (MobEffectInstance mobeffectinstance : collection) {
                            entity.m_7292_(new MobEffectInstance(mobeffectinstance));
                        }
                    }
                }
            }
            ++this.actualSprayTime;
        }
    }

    private Vec3 getSprayAt() {
        LivingEntity last = EntitySkunk.this.m_21188_();
        if (EntitySkunk.this.sprayAt != null) {
            return EntitySkunk.this.sprayAt;
        }
        if (last != null) {
            return last.m_20182_();
        }
        Vec3 modelBack = new Vec3(0.0, (double)0.4f, -1.0).m_82496_(-EntitySkunk.this.m_146909_() * ((float)Math.PI / 180)).m_82524_(-EntitySkunk.this.m_146908_() * ((float)Math.PI / 180));
        return EntitySkunk.this.m_20182_().m_82549_(modelBack);
    }
}
