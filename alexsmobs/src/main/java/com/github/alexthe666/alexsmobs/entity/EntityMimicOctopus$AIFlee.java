/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.util.DefaultRandomPos
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityMimicOctopus;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

private class EntityMimicOctopus.AIFlee
extends Goal {
    protected final EntityMimicOctopus.EntitySorter theNearestAttackableTargetSorter;
    protected final Predicate<? super Entity> targetEntitySelector;
    protected int executionChance = 8;
    protected boolean mustUpdate;
    private Entity targetEntity;
    private Vec3 flightTarget = null;
    private int cooldown = 0;

    EntityMimicOctopus.AIFlee() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.theNearestAttackableTargetSorter = new EntityMimicOctopus.EntitySorter((Entity)EntityMimicOctopus.this);
        this.targetEntitySelector = new Predicate<Entity>(){

            public boolean apply(@Nullable Entity e) {
                return e.m_6084_() && e.m_6095_().m_204039_(AMTagRegistry.MIMIC_OCTOPUS_FEARS) || e instanceof Player && !((Player)e).m_7500_();
            }
        };
    }

    public boolean m_8036_() {
        List list;
        if (EntityMimicOctopus.this.m_20159_() || EntityMimicOctopus.this.m_20160_() || EntityMimicOctopus.this.m_21824_()) {
            return false;
        }
        if (!this.mustUpdate) {
            long worldTime = EntityMimicOctopus.this.m_9236_().m_46467_() % 10L;
            if (EntityMimicOctopus.this.m_21216_() >= 100 && worldTime != 0L) {
                return false;
            }
            if (EntityMimicOctopus.this.m_217043_().m_188503_(this.executionChance) != 0 && worldTime != 0L) {
                return false;
            }
        }
        if ((list = EntityMimicOctopus.this.m_9236_().m_6443_(Entity.class, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector)).isEmpty()) {
            return false;
        }
        Collections.sort(list, this.theNearestAttackableTargetSorter);
        this.targetEntity = (Entity)list.get(0);
        this.mustUpdate = false;
        return true;
    }

    public boolean m_8045_() {
        return this.targetEntity != null && !EntityMimicOctopus.this.m_21824_() && EntityMimicOctopus.this.m_20270_(this.targetEntity) < 20.0f;
    }

    public void m_8041_() {
        this.flightTarget = null;
        this.targetEntity = null;
        EntityMimicOctopus.this.setMimicState(EntityMimicOctopus.MimicState.OVERLAY);
        EntityMimicOctopus.this.setMimickedBlock(null);
    }

    public void m_8037_() {
        if (this.cooldown > 0) {
            --this.cooldown;
        }
        if (!EntityMimicOctopus.this.isActiveCamo()) {
            EntityMimicOctopus.this.mimicEnvironment();
        }
        if (this.flightTarget != null) {
            EntityMimicOctopus.this.m_21573_().m_26519_(this.flightTarget.f_82479_, this.flightTarget.f_82480_, this.flightTarget.f_82481_, (double)1.2f);
            if (this.cooldown == 0 && EntityMimicOctopus.this.isTargetBlocked(this.flightTarget)) {
                this.cooldown = 30;
                this.flightTarget = null;
            }
        }
        if (this.targetEntity != null) {
            Vec3 vec;
            if ((this.flightTarget == null || this.flightTarget != null && EntityMimicOctopus.this.m_20238_(this.flightTarget) < 6.0) && (vec = DefaultRandomPos.m_148407_((PathfinderMob)EntityMimicOctopus.this, (int)16, (int)7, (Vec3)this.targetEntity.m_20182_())) != null) {
                this.flightTarget = vec;
            }
            if (EntityMimicOctopus.this.m_20270_(this.targetEntity) > 20.0f) {
                this.m_8041_();
            }
        }
    }

    protected double getTargetDistance() {
        return 10.0;
    }

    protected AABB getTargetableArea(double targetDistance) {
        Vec3 renderCenter = new Vec3(EntityMimicOctopus.this.m_20185_(), EntityMimicOctopus.this.m_20186_() + 0.5, EntityMimicOctopus.this.m_20189_());
        AABB aabb = new AABB(-targetDistance, -targetDistance, -targetDistance, targetDistance, targetDistance, targetDistance);
        return aabb.m_82383_(renderCenter);
    }
}
