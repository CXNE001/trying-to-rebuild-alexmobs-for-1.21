/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.alexthe666.citadel.animation.Animation
 *  com.github.alexthe666.citadel.animation.IAnimatedEntity
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityWarpedMosco;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntityWarpedMosco.AttackGoal
extends Goal {
    private int upTicks = 0;
    private int dashCooldown = 0;
    private boolean ranged = false;
    private BlockPos farTarget = null;

    public boolean m_8036_() {
        return EntityWarpedMosco.this.m_5448_() != null;
    }

    public void m_8037_() {
        if (this.dashCooldown > 0) {
            --this.dashCooldown;
        }
        if (EntityWarpedMosco.this.m_5448_() != null) {
            LivingEntity target = EntityWarpedMosco.this.m_5448_();
            this.ranged = EntityWarpedMosco.this.shouldRangeAttack(target);
            if (EntityWarpedMosco.this.isFlying() || this.ranged || EntityWarpedMosco.this.m_20270_((Entity)target) > 12.0f && !EntityWarpedMosco.this.isTargetBlocked(target.m_20182_().m_82520_(0.0, (double)(target.m_20206_() * 0.6f), 0.0))) {
                float speedRush = 5.0f;
                ++this.upTicks;
                EntityWarpedMosco.this.setFlying(true);
                if (this.ranged) {
                    if (this.farTarget == null || EntityWarpedMosco.this.m_20238_(Vec3.m_82512_((Vec3i)this.farTarget)) < 9.0) {
                        this.farTarget = this.getAvoidTarget(target);
                    }
                    if (this.farTarget != null) {
                        EntityWarpedMosco.this.m_21566_().m_6849_((double)this.farTarget.m_123341_(), (double)((float)this.farTarget.m_123342_() + target.m_20192_() * 0.6f), (double)this.farTarget.m_123343_(), 3.0);
                    }
                    EntityWarpedMosco.this.setAnimation(ANIMATION_SPIT);
                    if (this.upTicks % 30 == 0) {
                        EntityWarpedMosco.this.m_5634_(1.0f);
                    }
                    int tick = EntityWarpedMosco.this.getAnimationTick();
                    switch (tick) {
                        case 10: 
                        case 20: 
                        case 30: 
                        case 40: {
                            EntityWarpedMosco.this.spit(target);
                        }
                    }
                } else if (this.upTicks > 20 || EntityWarpedMosco.this.m_20270_((Entity)target) < 6.0f) {
                    EntityWarpedMosco.this.m_21566_().m_6849_(target.m_20185_(), target.m_20186_() + (double)(target.m_20192_() * 0.6f), target.m_20189_(), (double)speedRush);
                } else {
                    EntityWarpedMosco.this.m_21566_().m_6849_(EntityWarpedMosco.this.m_20185_(), EntityWarpedMosco.this.m_20186_() + 3.0, EntityWarpedMosco.this.m_20189_(), 0.5);
                }
            } else {
                EntityWarpedMosco.this.m_21573_().m_5624_((Entity)EntityWarpedMosco.this.m_5448_(), 1.25);
            }
            if (EntityWarpedMosco.this.isFlying()) {
                if (EntityWarpedMosco.this.m_20270_((Entity)target) < 4.3f) {
                    if (this.dashCooldown == 0 || target.m_20096_() || target.m_20077_() || target.m_20069_()) {
                        target.m_6469_(EntityWarpedMosco.this.m_269291_().m_269333_((LivingEntity)EntityWarpedMosco.this), 5.0f);
                        EntityWarpedMosco.this.knockbackRidiculous(target, 1.0f);
                        this.dashCooldown = 30;
                    }
                    float groundHeight = EntityWarpedMosco.this.getMoscoGround(EntityWarpedMosco.this.m_20183_()).m_123342_();
                    if (Math.abs(EntityWarpedMosco.this.m_20186_() - (double)groundHeight) < 3.0 && !EntityWarpedMosco.this.isOverLiquid()) {
                        EntityWarpedMosco.this.timeFlying += 300;
                        EntityWarpedMosco.this.setFlying(false);
                    }
                }
            } else if (EntityWarpedMosco.this.m_20270_((Entity)target) < 4.0f && EntityWarpedMosco.this.getAnimation() == IAnimatedEntity.NO_ANIMATION) {
                Animation animation = EntityWarpedMosco.getRandomAttack(EntityWarpedMosco.this.f_19796_);
                if (animation == ANIMATION_SUCK && target.m_20159_()) {
                    animation = ANIMATION_SLAM;
                }
                EntityWarpedMosco.this.setAnimation(animation);
            }
        }
    }

    public BlockPos getAvoidTarget(LivingEntity target) {
        float radius = 10 + EntityWarpedMosco.this.m_217043_().m_188503_(8);
        float angle = (float)Math.PI / 180 * (target.f_20885_ + 90.0f + (float)EntityWarpedMosco.this.m_217043_().m_188503_(180));
        double extraX = radius * Mth.m_14031_((float)((float)Math.PI + angle));
        double extraZ = radius * Mth.m_14089_((float)angle);
        BlockPos radialPos = AMBlockPos.fromCoords(target.m_20185_() + extraX, target.m_20186_() + 1.0, target.m_20189_() + extraZ);
        BlockPos ground = radialPos;
        if (EntityWarpedMosco.this.m_20238_(Vec3.m_82512_((Vec3i)ground)) > 30.0 && !EntityWarpedMosco.this.isTargetBlocked(Vec3.m_82512_((Vec3i)ground)) && EntityWarpedMosco.this.m_20238_(Vec3.m_82512_((Vec3i)ground)) > 6.0) {
            return ground;
        }
        return EntityWarpedMosco.this.m_20183_();
    }

    public void m_8041_() {
        this.upTicks = 0;
        this.dashCooldown = 0;
        this.ranged = false;
    }
}
