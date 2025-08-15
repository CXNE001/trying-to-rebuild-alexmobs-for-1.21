/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityTriops;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.ai.goal.Goal;

private class EntityTriops.BreedGoal
extends Goal {
    private final Predicate<Entity> validBreedPartner;
    private EntityTriops breedPartner;
    private int executionCooldown = 50;

    public EntityTriops.BreedGoal() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.validBreedPartner = shrimp -> {
            EntityTriops otherFish;
            return shrimp instanceof EntityTriops && (otherFish = (EntityTriops)shrimp).m_19879_() != EntityTriops.this.m_19879_() && otherFish.isSearchingForMate();
        };
    }

    public boolean m_8036_() {
        if (!EntityTriops.this.m_20072_() || !EntityTriops.this.fedCarrot || EntityTriops.this.breedCooldown > 0 || EntityTriops.this.breedWith != null) {
            return false;
        }
        if (this.executionCooldown > 0) {
            --this.executionCooldown;
        } else {
            EntityTriops closestPupfish;
            this.executionCooldown = 50 + EntityTriops.this.f_19796_.m_188503_(50);
            List list = EntityTriops.this.m_9236_().m_6443_(EntityTriops.class, EntityTriops.this.m_20191_().m_82377_(10.0, 8.0, 10.0), EntitySelector.f_20408_.and(this.validBreedPartner));
            list.sort(Comparator.comparingDouble(arg_0 -> ((EntityTriops)EntityTriops.this).m_20280_(arg_0)));
            if (!list.isEmpty() && (closestPupfish = (EntityTriops)list.get(0)) != null) {
                this.breedPartner = closestPupfish;
                this.breedPartner.breedWith = EntityTriops.this;
                return true;
            }
        }
        return false;
    }

    public boolean m_8045_() {
        return this.breedPartner != null && !EntityTriops.this.pregnant && !this.breedPartner.pregnant && EntityTriops.this.breedWith == null && this.breedPartner.isSearchingForMate() && EntityTriops.this.isSearchingForMate();
    }

    public void m_8056_() {
    }

    public void m_8041_() {
        EntityTriops.this.fedCarrot = false;
        EntityTriops.this.breedCooldown = 1200 + EntityTriops.this.f_19796_.m_188503_(3600);
    }

    public void m_8037_() {
        EntityTriops.this.m_21573_().m_5624_((Entity)this.breedPartner, 1.0);
        this.breedPartner.m_21573_().m_5624_((Entity)EntityTriops.this, 1.0);
        if (EntityTriops.this.m_20270_((Entity)this.breedPartner) < 1.2f) {
            EntityTriops.this.m_9236_().m_7605_((Entity)EntityTriops.this, (byte)68);
            EntityTriops.this.pregnant = true;
        }
    }
}
