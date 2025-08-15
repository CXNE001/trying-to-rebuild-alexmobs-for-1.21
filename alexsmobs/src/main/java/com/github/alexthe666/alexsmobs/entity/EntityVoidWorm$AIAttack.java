/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Direction
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityVoidWorm;
import com.github.alexthe666.alexsmobs.entity.EntityVoidWormPart;
import java.util.EnumSet;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class EntityVoidWorm.AIAttack
extends Goal {
    private EntityVoidWorm.AttackMode mode = EntityVoidWorm.AttackMode.CIRCLE;
    private int modeTicks = 0;
    private int maxCircleTime = 500;
    private Vec3 moveTo = null;

    public EntityVoidWorm.AIAttack() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        return EntityVoidWorm.this.m_5448_() != null && EntityVoidWorm.this.m_5448_().m_6084_();
    }

    public void m_8041_() {
        this.mode = EntityVoidWorm.AttackMode.CIRCLE;
        this.modeTicks = 0;
    }

    public void m_8056_() {
        this.mode = EntityVoidWorm.AttackMode.CIRCLE;
        this.maxCircleTime = 60 + EntityVoidWorm.this.f_19796_.m_188503_(200);
    }

    public void m_8037_() {
        LivingEntity target = EntityVoidWorm.this.m_5448_();
        boolean flag = false;
        float speed = 1.0f;
        for (Entity entity : EntityVoidWorm.this.m_9236_().m_45976_(LivingEntity.class, EntityVoidWorm.this.m_20191_().m_82400_(2.0))) {
            if (entity.m_7306_((Entity)EntityVoidWorm.this) || entity instanceof EntityVoidWormPart || entity.m_7307_((Entity)EntityVoidWorm.this) || entity == EntityVoidWorm.this) continue;
            if (EntityVoidWorm.this.isMouthOpen()) {
                EntityVoidWorm.this.launch(entity, true);
                flag = true;
                EntityVoidWorm.this.wormAttack(entity, EntityVoidWorm.this.m_269291_().m_269333_((LivingEntity)EntityVoidWorm.this), 8.0f + EntityVoidWorm.this.f_19796_.m_188501_() * 8.0f);
                continue;
            }
            EntityVoidWorm.this.openMouth(15);
        }
        if (target != null) {
            if (this.mode == EntityVoidWorm.AttackMode.CIRCLE) {
                int interval;
                if (this.moveTo == null || EntityVoidWorm.this.m_20238_(this.moveTo) < 16.0 || EntityVoidWorm.this.f_19862_) {
                    this.moveTo = EntityVoidWorm.this.getBlockInViewAway(target.m_20182_(), 0.4f + EntityVoidWorm.this.f_19796_.m_188501_() * 0.2f);
                }
                int n = interval = EntityVoidWorm.this.m_21223_() < EntityVoidWorm.this.m_21233_() && !EntityVoidWorm.this.isSplitter() ? 15 : 40;
                if (this.modeTicks % interval == 0) {
                    EntityVoidWorm.this.spit(new Vec3(3.0, 3.0, 0.0), false);
                    EntityVoidWorm.this.spit(new Vec3(-3.0, 3.0, 0.0), false);
                    EntityVoidWorm.this.spit(new Vec3(3.0, -3.0, 0.0), false);
                    EntityVoidWorm.this.spit(new Vec3(-3.0, -3.0, 0.0), false);
                }
                ++this.modeTicks;
                if (this.modeTicks > this.maxCircleTime) {
                    this.maxCircleTime = 60 + EntityVoidWorm.this.f_19796_.m_188503_(200);
                    this.mode = EntityVoidWorm.AttackMode.SLAM_RISE;
                    this.modeTicks = 0;
                    this.moveTo = null;
                }
            } else if (this.mode == EntityVoidWorm.AttackMode.SLAM_RISE) {
                if (this.moveTo == null) {
                    this.moveTo = EntityVoidWorm.this.getBlockInViewAwaySlam(target.m_20182_(), 20 + EntityVoidWorm.this.f_19796_.m_188503_(20));
                }
                if (this.moveTo != null && EntityVoidWorm.this.m_20186_() > target.m_20186_() + 15.0) {
                    this.moveTo = null;
                    this.modeTicks = 0;
                    this.mode = EntityVoidWorm.AttackMode.SLAM_FALL;
                }
            } else if (this.mode == EntityVoidWorm.AttackMode.SLAM_FALL) {
                speed = 2.0f;
                EntityVoidWorm.this.m_21391_((Entity)target, 360.0f, 360.0f);
                this.moveTo = target.m_20182_();
                if (EntityVoidWorm.this.f_19862_) {
                    this.moveTo = new Vec3(target.m_20185_(), EntityVoidWorm.this.m_20186_() + 3.0, target.m_20189_());
                }
                EntityVoidWorm.this.openMouth(20);
                if (EntityVoidWorm.this.m_20238_(this.moveTo) < 4.0 || flag) {
                    this.mode = EntityVoidWorm.AttackMode.CIRCLE;
                    this.moveTo = null;
                    this.modeTicks = 0;
                }
            }
        }
        if (!EntityVoidWorm.this.m_142582_((Entity)target) && EntityVoidWorm.this.f_19796_.m_188503_(100) == 0 && EntityVoidWorm.this.makePortalCooldown == 0) {
            Vec3 to = new Vec3(target.m_20185_(), target.m_20191_().f_82292_ + 0.1, target.m_20189_());
            EntityVoidWorm.this.createPortal(EntityVoidWorm.this.m_20182_().m_82549_(EntityVoidWorm.this.m_20154_().m_82490_(20.0)), to, Direction.UP);
            EntityVoidWorm.this.makePortalCooldown = 50;
            this.mode = EntityVoidWorm.AttackMode.SLAM_FALL;
        }
        if (this.moveTo != null && EntityVoidWorm.this.portalTarget == null) {
            EntityVoidWorm.this.m_21566_().m_6849_(this.moveTo.f_82479_, this.moveTo.f_82480_, this.moveTo.f_82481_, (double)speed);
        }
    }
}
