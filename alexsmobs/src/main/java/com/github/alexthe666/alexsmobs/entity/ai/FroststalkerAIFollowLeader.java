/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DataFixUtils
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.player.Player
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityFroststalker;
import com.mojang.datafixers.DataFixUtils;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class FroststalkerAIFollowLeader
extends Goal {
    private static final int INTERVAL_TICKS = 200;
    private final EntityFroststalker mob;
    private int timeToRecalcPath;
    private int nextStartTick;

    public FroststalkerAIFollowLeader(EntityFroststalker froststalker) {
        this.mob = froststalker;
        this.nextStartTick = this.nextStartTick(froststalker);
    }

    protected int nextStartTick(EntityFroststalker froststalker) {
        return 100 + froststalker.m_217043_().m_188503_(200) % 40;
    }

    public boolean m_8036_() {
        if (this.mob.hasFollowers()) {
            return false;
        }
        if (this.mob.isFollower()) {
            return true;
        }
        if (this.nextStartTick > 0) {
            --this.nextStartTick;
            return false;
        }
        this.nextStartTick = this.nextStartTick(this.mob);
        Predicate<EntityFroststalker> froststalkerPredicate = p_25258_ -> p_25258_.canBeFollowed() || !p_25258_.isFollower();
        float range = 60.0f;
        List playerList = this.mob.m_9236_().m_6443_(Player.class, this.mob.m_20191_().m_82377_((double)range, (double)range, (double)range), EntityFroststalker.VALID_LEADER_PLAYERS);
        Player closestPlayer = null;
        for (Player player : playerList) {
            if (closestPlayer != null && !(player.m_20270_((Entity)this.mob) < closestPlayer.m_20270_((Entity)this.mob))) continue;
            closestPlayer = player;
        }
        if (closestPlayer == null) {
            List list = this.mob.m_9236_().m_6443_(EntityFroststalker.class, this.mob.m_20191_().m_82377_((double)range, (double)range, (double)range), froststalkerPredicate);
            EntityFroststalker entityFroststalker = (EntityFroststalker)DataFixUtils.orElse(list.stream().filter(EntityFroststalker::canBeFollowed).findAny(), (Object)this.mob);
            entityFroststalker.addFollowers(list.stream().filter(p_25255_ -> !p_25255_.isFollower()));
        } else {
            this.mob.startFollowing((LivingEntity)closestPlayer);
        }
        return this.mob.isFollower();
    }

    public boolean m_8045_() {
        return this.mob.isFollower() && this.mob.inRangeOfLeader();
    }

    public void m_8056_() {
        this.timeToRecalcPath = 0;
    }

    public void m_8041_() {
        this.mob.stopFollowing();
    }

    public void m_8037_() {
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            this.mob.pathToLeader();
        }
    }
}
