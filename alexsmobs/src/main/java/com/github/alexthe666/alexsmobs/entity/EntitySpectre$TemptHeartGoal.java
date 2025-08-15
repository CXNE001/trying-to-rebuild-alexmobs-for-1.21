/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.targeting.TargetingConditions
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntitySpectre;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

static class EntitySpectre.TemptHeartGoal
extends Goal {
    protected final EntitySpectre creature;
    private final TargetingConditions ENTITY_PREDICATE = TargetingConditions.m_148353_().m_26883_(64.0).m_26893_().m_148355_();
    private final double speed;
    private final Ingredient temptItem;
    protected Player closestPlayer;
    private int delayTemptCounter;

    public EntitySpectre.TemptHeartGoal(EntitySpectre p_i47822_1_, double p_i47822_2_, Ingredient p_i47822_4_, boolean p_i47822_5_) {
        this(p_i47822_1_, p_i47822_2_, p_i47822_5_, p_i47822_4_);
    }

    public EntitySpectre.TemptHeartGoal(EntitySpectre p_i47823_1_, double p_i47823_2_, boolean p_i47823_4_, Ingredient p_i47823_5_) {
        this.creature = p_i47823_1_;
        this.speed = p_i47823_2_;
        this.temptItem = p_i47823_5_;
    }

    public boolean m_8036_() {
        if (this.delayTemptCounter > 0) {
            --this.delayTemptCounter;
            return false;
        }
        this.closestPlayer = this.creature.m_9236_().m_45946_(this.ENTITY_PREDICATE, (LivingEntity)this.creature);
        if (this.closestPlayer == null || this.creature.m_21524_() == this.closestPlayer) {
            return false;
        }
        return this.isTempting(this.closestPlayer.m_21205_()) || this.isTempting(this.closestPlayer.m_21206_());
    }

    protected boolean isTempting(ItemStack p_188508_1_) {
        return this.temptItem.test(p_188508_1_);
    }

    public boolean m_8045_() {
        return this.m_8036_();
    }

    public void m_8056_() {
        this.creature.lurePos = this.closestPlayer.m_20182_();
    }

    public void m_8041_() {
        this.closestPlayer = null;
        this.delayTemptCounter = 100;
        this.creature.lurePos = null;
    }

    public void m_8037_() {
        this.creature.m_21563_().m_24960_((Entity)this.closestPlayer, (float)(this.creature.m_8085_() + 20), (float)this.creature.m_8132_());
        if (this.creature.m_20280_((Entity)this.closestPlayer) < 6.25) {
            this.creature.m_21573_().m_26573_();
        } else {
            this.creature.m_21566_().m_6849_(this.closestPlayer.m_20185_(), this.closestPlayer.m_20186_() + (double)this.closestPlayer.m_20192_(), this.closestPlayer.m_20189_(), this.speed);
        }
    }
}
