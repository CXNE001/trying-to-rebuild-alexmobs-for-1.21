/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import com.google.common.base.Predicate;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

private class EntityCrow.AIScatter
extends Goal {
    protected final Sorter theNearestAttackableTargetSorter;
    protected final Predicate<? super Entity> targetEntitySelector;
    protected int executionChance = 8;
    protected boolean mustUpdate;
    private Entity targetEntity;
    private Vec3 flightTarget = null;
    private int cooldown = 0;

    EntityCrow.AIScatter() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.theNearestAttackableTargetSorter = new Sorter((Entity)EntityCrow.this);
        this.targetEntitySelector = new Predicate<Entity>(){

            public boolean apply(@Nullable Entity e) {
                return e.m_6084_() && e.m_6095_().m_204039_(AMTagRegistry.SCATTERS_CROWS) || e instanceof Player && !((Player)e).m_7500_();
            }
        };
    }

    public boolean m_8036_() {
        List list;
        long worldTime;
        if (EntityCrow.this.m_20159_() || EntityCrow.this.aiItemFlag || EntityCrow.this.m_20160_() || EntityCrow.this.m_21824_()) {
            return false;
        }
        if (!this.mustUpdate && (worldTime = EntityCrow.this.m_9236_().m_46467_() % 10L) != 0L) {
            if (EntityCrow.this.m_21216_() >= 100) {
                return false;
            }
            if (EntityCrow.this.m_217043_().m_188503_(this.executionChance) != 0) {
                return false;
            }
        }
        if ((list = EntityCrow.this.m_9236_().m_6443_(Entity.class, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector)).isEmpty()) {
            return false;
        }
        list.sort(this.theNearestAttackableTargetSorter);
        this.targetEntity = (Entity)list.get(0);
        this.mustUpdate = false;
        return true;
    }

    public boolean m_8045_() {
        return this.targetEntity != null && !EntityCrow.this.m_21824_();
    }

    public void m_8041_() {
        this.flightTarget = null;
        this.targetEntity = null;
    }

    public void m_8037_() {
        if (this.cooldown > 0) {
            --this.cooldown;
        }
        if (this.flightTarget != null) {
            EntityCrow.this.setFlying(true);
            EntityCrow.this.m_21566_().m_6849_(this.flightTarget.f_82479_, this.flightTarget.f_82480_, this.flightTarget.f_82481_, 1.0);
            if (this.cooldown == 0 && EntityCrow.this.isTargetBlocked(this.flightTarget)) {
                this.cooldown = 30;
                this.flightTarget = null;
            }
        }
        if (this.targetEntity != null) {
            Vec3 vec;
            if ((EntityCrow.this.m_20096_() || this.flightTarget == null || EntityCrow.this.m_20238_(this.flightTarget) < 3.0) && (vec = EntityCrow.this.getBlockInViewAway(this.targetEntity.m_20182_(), 0.0f)) != null && vec.m_7098_() > EntityCrow.this.m_20186_()) {
                this.flightTarget = vec;
            }
            if (EntityCrow.this.m_20270_(this.targetEntity) > 20.0f) {
                this.m_8041_();
            }
        }
    }

    protected double getTargetDistance() {
        return 4.0;
    }

    protected AABB getTargetableArea(double targetDistance) {
        Vec3 renderCenter = new Vec3(EntityCrow.this.m_20185_(), EntityCrow.this.m_20186_() + 0.5, EntityCrow.this.m_20189_());
        AABB aabb = new AABB(-2.0, -2.0, -2.0, 2.0, 2.0, 2.0);
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
