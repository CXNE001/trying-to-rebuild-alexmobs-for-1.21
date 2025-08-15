/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityHammerheadShark;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

private static class EntityHammerheadShark.CirclePreyGoal
extends Goal {
    EntityHammerheadShark shark;
    float speed;
    float circlingTime = 0.0f;
    float circleDistance = 5.0f;
    float maxCirclingTime = 80.0f;
    boolean clockwise = false;

    public EntityHammerheadShark.CirclePreyGoal(EntityHammerheadShark shark, float speed) {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.shark = shark;
        this.speed = speed;
    }

    public boolean m_8036_() {
        return this.shark.m_5448_() != null;
    }

    public boolean m_8045_() {
        return this.shark.m_5448_() != null;
    }

    public void m_8056_() {
        this.circlingTime = 0.0f;
        this.maxCirclingTime = 360 + this.shark.f_19796_.m_188503_(80);
        this.circleDistance = 5.0f + this.shark.f_19796_.m_188501_() * 5.0f;
        this.clockwise = this.shark.f_19796_.m_188499_();
    }

    public void m_8041_() {
        this.circlingTime = 0.0f;
        this.maxCirclingTime = 360 + this.shark.f_19796_.m_188503_(80);
        this.circleDistance = 5.0f + this.shark.f_19796_.m_188501_() * 5.0f;
        this.clockwise = this.shark.f_19796_.m_188499_();
    }

    public void m_8037_() {
        LivingEntity prey = this.shark.m_5448_();
        if (prey != null) {
            double dist = this.shark.m_20270_((Entity)prey);
            if (this.circlingTime >= this.maxCirclingTime) {
                this.shark.m_21391_((Entity)prey, 30.0f, 30.0f);
                this.shark.m_21573_().m_5624_((Entity)prey, 1.5);
                if (dist < 2.0) {
                    this.shark.m_7327_((Entity)prey);
                    if (this.shark.f_19796_.m_188501_() < 0.3f) {
                        this.shark.m_19983_(new ItemStack((ItemLike)AMItemRegistry.SHARK_TOOTH.get()));
                    }
                    this.m_8041_();
                }
            } else if (dist <= 25.0) {
                this.circlingTime += 1.0f;
                BlockPos circlePos = this.getSharkCirclePos(prey);
                if (circlePos != null) {
                    this.shark.m_21573_().m_26519_((double)circlePos.m_123341_() + 0.5, (double)circlePos.m_123342_() + 0.5, (double)circlePos.m_123343_() + 0.5, 0.6);
                }
            } else {
                this.shark.m_21391_((Entity)prey, 30.0f, 30.0f);
                this.shark.m_21573_().m_5624_((Entity)prey, 0.8);
            }
        }
    }

    public BlockPos getSharkCirclePos(LivingEntity target) {
        float angle = (float)Math.PI / 180 * (this.clockwise ? -this.circlingTime : this.circlingTime);
        double extraX = this.circleDistance * Mth.m_14031_((float)angle);
        double extraZ = this.circleDistance * Mth.m_14089_((float)angle);
        BlockPos ground = AMBlockPos.fromCoords(target.m_20185_() + 0.5 + extraX, this.shark.m_20186_(), target.m_20189_() + 0.5 + extraZ);
        if (this.shark.m_9236_().m_6425_(ground).m_205070_(FluidTags.f_13131_)) {
            return ground;
        }
        return null;
    }
}
