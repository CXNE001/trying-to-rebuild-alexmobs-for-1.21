/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

private class EntityFly.AnnoyZombieGoal
extends Goal {
    protected final Sorter theNearestAttackableTargetSorter;
    protected final Predicate<? super Entity> targetEntitySelector;
    protected int executionChance = 8;
    protected boolean mustUpdate;
    private Entity targetEntity;
    private int cooldown = 0;

    EntityFly.AnnoyZombieGoal() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.theNearestAttackableTargetSorter = new Sorter((Entity)EntityFly.this);
        this.targetEntitySelector = new Predicate<Entity>(){

            public boolean apply(@Nullable Entity e) {
                return e.m_6084_() && e.m_6095_().m_204039_(AMTagRegistry.FLY_TARGETS) && (!(e instanceof LivingEntity) || (double)((LivingEntity)e).m_21223_() >= 2.0);
            }
        };
    }

    public boolean m_8036_() {
        List list;
        if (EntityFly.this.m_20159_() || EntityFly.this.m_20160_()) {
            return false;
        }
        if (!this.mustUpdate) {
            long worldTime = EntityFly.this.m_9236_().m_46467_() % 10L;
            if (EntityFly.this.m_21216_() >= 100 && worldTime != 0L) {
                return false;
            }
            if (EntityFly.this.m_217043_().m_188503_(this.executionChance) != 0 && worldTime != 0L) {
                return false;
            }
        }
        if ((list = EntityFly.this.m_9236_().m_6443_(Entity.class, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector)).isEmpty()) {
            return false;
        }
        Collections.sort(list, this.theNearestAttackableTargetSorter);
        this.targetEntity = (Entity)list.get(0);
        this.mustUpdate = false;
        return true;
    }

    public boolean m_8045_() {
        return this.targetEntity != null;
    }

    public void m_8041_() {
        this.targetEntity = null;
    }

    public void m_8037_() {
        if (this.cooldown > 0) {
            --this.cooldown;
        }
        if (this.targetEntity != null) {
            if (EntityFly.this.m_21573_().m_26571_()) {
                int i = EntityFly.this.m_217043_().m_188503_(3) - 1;
                int k = EntityFly.this.m_217043_().m_188503_(3) - 1;
                int l = (int)((double)(EntityFly.this.m_217043_().m_188503_(3) - 1) * Math.ceil(this.targetEntity.m_20206_()));
                EntityFly.this.m_21573_().m_26519_(this.targetEntity.m_20185_() + (double)i, this.targetEntity.m_20186_() + (double)l, this.targetEntity.m_20189_() + (double)k, 1.0);
            }
            if (EntityFly.this.m_20280_(this.targetEntity) < 3.0) {
                if (this.targetEntity instanceof LivingEntity && (double)((LivingEntity)this.targetEntity).m_21223_() > 2.0) {
                    if (this.cooldown == 0) {
                        this.targetEntity.m_6469_(EntityFly.this.m_269291_().m_269264_(), 1.0f);
                        this.cooldown = 100;
                    }
                } else {
                    this.m_8041_();
                }
            }
        }
    }

    protected double getTargetDistance() {
        return 16.0;
    }

    protected AABB getTargetableArea(double targetDistance) {
        Vec3 renderCenter = new Vec3(EntityFly.this.m_20185_() + 0.5, EntityFly.this.m_20186_() + 0.5, EntityFly.this.m_20189_() + 0.5);
        double renderRadius = 5.0;
        AABB aabb = new AABB(-renderRadius, -renderRadius, -renderRadius, renderRadius, renderRadius, renderRadius);
        return aabb.m_82383_(renderCenter);
    }

    public record Sorter(Entity theEntity) implements Comparator<Entity>
    {
        @Override
        public int compare(Entity p_compare_1_, Entity p_compare_2_) {
            double d0 = this.theEntity.m_20280_(p_compare_1_);
            double d1 = this.theEntity.m_20280_(p_compare_2_);
            return Double.compare(d0, d1);
        }
    }
}
