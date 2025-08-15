/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.animal.Pufferfish
 *  net.minecraft.world.entity.monster.Creeper
 *  net.minecraft.world.entity.monster.Guardian
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityMimicOctopus;
import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

private class EntityMimicOctopus.AIMimicNearbyMobs
extends Goal {
    protected final EntityMimicOctopus.EntitySorter theNearestAttackableTargetSorter;
    protected final Predicate<? super Entity> targetEntitySelector;
    protected int executionChance = 30;
    protected boolean mustUpdate;
    private Entity targetEntity;
    private Vec3 flightTarget = null;
    private int cooldown = 0;

    EntityMimicOctopus.AIMimicNearbyMobs() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.theNearestAttackableTargetSorter = new EntityMimicOctopus.EntitySorter((Entity)EntityMimicOctopus.this);
        this.targetEntitySelector = new Predicate<Entity>(){

            public boolean apply(@Nullable Entity e) {
                return e.m_6084_() && (e instanceof Creeper || e instanceof Guardian || e instanceof Pufferfish);
            }
        };
    }

    public boolean m_8036_() {
        List list;
        if (EntityMimicOctopus.this.m_20159_() || EntityMimicOctopus.this.m_20160_() || EntityMimicOctopus.this.getMimicState() != EntityMimicOctopus.MimicState.OVERLAY || EntityMimicOctopus.this.mimicCooldown > 0) {
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
        return this.targetEntity != null && EntityMimicOctopus.this.m_20270_(this.targetEntity) < 10.0f && EntityMimicOctopus.this.getMimicState() == EntityMimicOctopus.MimicState.OVERLAY;
    }

    public void m_8041_() {
        EntityMimicOctopus.this.m_21573_().m_26573_();
        this.flightTarget = null;
        this.targetEntity = null;
    }

    public void m_8037_() {
        if (this.cooldown > 0) {
            --this.cooldown;
        }
        if (this.targetEntity != null) {
            EntityMimicOctopus.this.m_21573_().m_5624_(this.targetEntity, (double)1.2f);
            if (EntityMimicOctopus.this.m_20270_(this.targetEntity) > 20.0f) {
                this.m_8041_();
                EntityMimicOctopus.this.setMimicState(EntityMimicOctopus.MimicState.OVERLAY);
                EntityMimicOctopus.this.setMimickedBlock(null);
            } else if (EntityMimicOctopus.this.m_20270_(this.targetEntity) < 5.0f && EntityMimicOctopus.this.m_142582_(this.targetEntity)) {
                int i;
                EntityMimicOctopus.this.stopMimicCooldown = i = 1200;
                EntityMimicOctopus.this.camoCooldown = i + 40;
                EntityMimicOctopus.this.mimicCooldown = 40;
                if (this.targetEntity instanceof Creeper) {
                    EntityMimicOctopus.this.setMimicState(EntityMimicOctopus.MimicState.CREEPER);
                } else if (this.targetEntity instanceof Guardian) {
                    EntityMimicOctopus.this.setMimicState(EntityMimicOctopus.MimicState.GUARDIAN);
                } else if (this.targetEntity instanceof Pufferfish) {
                    EntityMimicOctopus.this.setMimicState(EntityMimicOctopus.MimicState.PUFFERFISH);
                } else {
                    EntityMimicOctopus.this.setMimicState(EntityMimicOctopus.MimicState.OVERLAY);
                    EntityMimicOctopus.this.setMimickedBlock(null);
                }
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
