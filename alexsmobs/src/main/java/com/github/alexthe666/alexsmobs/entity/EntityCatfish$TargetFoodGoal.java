/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityCatfish;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

private class EntityCatfish.TargetFoodGoal
extends Goal {
    private final EntityCatfish catfish;
    private Entity food;
    private int executionCooldown = 50;

    public EntityCatfish.TargetFoodGoal(EntityCatfish catfish) {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
        this.catfish = catfish;
    }

    public boolean m_8036_() {
        if (!this.catfish.m_20072_() || this.catfish.eatCooldown > 0) {
            return false;
        }
        if (this.executionCooldown > 0) {
            --this.executionCooldown;
        } else {
            this.executionCooldown = 50 + EntityCatfish.this.f_19796_.m_188503_(50);
            if (!this.catfish.isFull()) {
                List list = this.catfish.m_9236_().m_6443_(Entity.class, this.catfish.m_20191_().m_82377_(8.0, 8.0, 8.0), EntitySelector.f_20408_.and(entity -> entity != this.catfish && this.catfish.isFood((Entity)entity)));
                list.sort(Comparator.comparingDouble(arg_0 -> ((EntityCatfish)this.catfish).m_20280_(arg_0)));
                if (!list.isEmpty()) {
                    this.food = (Entity)list.get(0);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean m_8045_() {
        return this.food != null && this.food.m_6084_() && !this.catfish.isFull();
    }

    public void m_8041_() {
        this.executionCooldown = 5;
    }

    public void m_8037_() {
        this.catfish.m_21573_().m_26519_(this.food.m_20185_(), this.food.m_20227_(0.5), this.food.m_20189_(), 1.0);
        float eatDist = this.catfish.m_20205_() * 0.65f + this.food.m_20205_();
        if (this.catfish.m_20270_(this.food) < eatDist + 3.0f && this.catfish.m_142582_(this.food)) {
            Vec3 delta = this.catfish.getMouthVec().m_82546_(this.food.m_20182_()).m_82541_().m_82490_((double)0.1f);
            this.food.m_20256_(this.food.m_20184_().m_82549_(delta));
            if (this.catfish.m_20270_(this.food) < eatDist) {
                if (this.food instanceof Player) {
                    this.food.m_6469_(this.catfish.m_269291_().m_269333_((LivingEntity)this.catfish), 12000.0f);
                } else if (this.catfish.swallowEntity(this.food)) {
                    this.catfish.m_146850_(GameEvent.f_157806_);
                    this.catfish.m_5496_(SoundEvents.f_11912_, this.catfish.m_6121_(), this.catfish.m_6100_());
                    this.food.m_146870_();
                }
            }
        }
    }
}
