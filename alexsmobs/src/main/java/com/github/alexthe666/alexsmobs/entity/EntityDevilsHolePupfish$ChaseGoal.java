/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityDevilsHolePupfish;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntityDevilsHolePupfish.ChaseGoal
extends Goal {
    private final EntityDevilsHolePupfish pupfish;
    private final Predicate<Entity> validChasePartner;
    private int executionCooldown = 50;

    public EntityDevilsHolePupfish.ChaseGoal(EntityDevilsHolePupfish pupfish) {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.pupfish = pupfish;
        this.validChasePartner = pupfish1 -> {
            EntityDevilsHolePupfish otherFish;
            return pupfish1 instanceof EntityDevilsHolePupfish && (otherFish = (EntityDevilsHolePupfish)((Object)pupfish1)).m_19879_() != this.pupfish.m_19879_() && otherFish.chasePartner == null && otherFish.chaseCooldown <= 0;
        };
    }

    public boolean m_8036_() {
        if (!this.pupfish.m_20072_() || this.pupfish.chaseTime > this.pupfish.maxChaseTime || this.pupfish.chaseCooldown > 0) {
            return false;
        }
        if (this.pupfish.chasePartner != null && this.pupfish.chasePartner.m_6084_()) {
            return true;
        }
        if (this.executionCooldown > 0) {
            --this.executionCooldown;
        } else {
            this.executionCooldown = 50 + EntityDevilsHolePupfish.this.f_19796_.m_188503_(50);
            if (this.pupfish.chasePartner == null || !this.pupfish.chasePartner.m_6084_()) {
                EntityDevilsHolePupfish closestPupfish;
                List list = this.pupfish.m_9236_().m_6443_(EntityDevilsHolePupfish.class, this.pupfish.m_20191_().m_82377_(10.0, 8.0, 10.0), EntitySelector.f_20408_.and(this.validChasePartner));
                list.sort(Comparator.comparingDouble(arg_0 -> ((EntityDevilsHolePupfish)this.pupfish).m_20280_(arg_0)));
                if (!list.isEmpty() && (closestPupfish = (EntityDevilsHolePupfish)((Object)list.get(0))) != null) {
                    this.pupfish.chasePartner = closestPupfish;
                    closestPupfish.chasePartner = this.pupfish;
                    this.pupfish.chaseDriver = true;
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public boolean m_8045_() {
        return this.pupfish.chasePartner != null && this.pupfish.chasePartner.m_6084_() && this.pupfish.chaseTime < this.pupfish.maxChaseTime;
    }

    public void m_8056_() {
        this.pupfish.chaseDriver = !this.pupfish.chasePartner.chaseDriver;
        this.pupfish.chaseTime = 0;
        this.pupfish.maxChaseTime = 600;
    }

    public void m_8041_() {
        this.pupfish.chaseTime = 0;
        this.pupfish.chaseCooldown = 100 + EntityDevilsHolePupfish.this.f_19796_.m_188503_(100);
        this.executionCooldown = 50 + EntityDevilsHolePupfish.this.f_19796_.m_188503_(20);
        if (this.pupfish.breedNextChase) {
            this.pupfish.spawnBabiesWith(this.pupfish.chasePartner);
            this.pupfish.chasePartner.breedNextChase = false;
            this.pupfish.breedNextChase = false;
        }
        this.pupfish.chasePartner = null;
    }

    public void m_8037_() {
        ++this.pupfish.chaseTime;
        if (this.pupfish.chasePartner == null || !this.pupfish.chaseDriver) {
            return;
        }
        float chaserSpeed = 1.2f + EntityDevilsHolePupfish.this.f_19796_.m_188501_() * 0.45f;
        float chasedSpeed = 0.2f + chaserSpeed * 0.7f;
        EntityDevilsHolePupfish flee = this.pupfish.chaseDriver ? this.pupfish.chasePartner : this.pupfish;
        EntityDevilsHolePupfish driver = this.pupfish.chaseDriver ? this.pupfish : this.pupfish.chasePartner;
        driver.m_21573_().m_26519_(flee.m_20185_(), flee.m_20227_(0.5), flee.m_20189_(), (double)chaserSpeed);
        Vec3 from = flee.m_20182_().m_82520_((double)(EntityDevilsHolePupfish.this.f_19796_.m_188501_() - 0.5f), (double)(EntityDevilsHolePupfish.this.f_19796_.m_188501_() - 0.5f), (double)(EntityDevilsHolePupfish.this.f_19796_.m_188501_() - 0.5f)).m_82546_(driver.m_20182_()).m_82541_().m_82490_((double)(2.0f + EntityDevilsHolePupfish.this.f_19796_.m_188501_() * 2.0f));
        Vec3 to = flee.m_20182_().m_82549_(from);
        flee.m_21573_().m_26519_(to.f_82479_, to.f_82480_, to.f_82481_, (double)chasedSpeed);
        if (EntityDevilsHolePupfish.this.f_19796_.m_188503_(50) == 0) {
            this.pupfish.chaseDriver = !this.pupfish.chaseDriver;
            this.pupfish.chasePartner.chaseDriver = !this.pupfish.chasePartner.chaseDriver;
        }
    }
}
