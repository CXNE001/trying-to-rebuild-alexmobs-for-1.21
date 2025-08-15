/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityRaccoon;
import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

private class EntityBlueJay.AIFollowFeederOrRaccoon
extends Goal {
    private Entity following;

    EntityBlueJay.AIFollowFeederOrRaccoon() {
        this.m_7021_(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        Entity feeder;
        Entity raccoon;
        if (EntityBlueJay.this.m_20159_() || EntityBlueJay.this.m_5448_() != null && EntityBlueJay.this.m_5448_().m_6084_()) {
            return false;
        }
        if (EntityBlueJay.this.getRaccoonUUID() != null && (raccoon = EntityBlueJay.this.getRaccoon()) != null) {
            this.following = raccoon;
            return true;
        }
        if (EntityBlueJay.this.getFeedTime() > 0 && (feeder = EntityBlueJay.this.getLastFeeder()) != null) {
            this.following = feeder;
            return true;
        }
        return false;
    }

    public boolean m_8045_() {
        LivingEntity target = EntityBlueJay.this.m_5448_();
        return !(this.following == null || !this.following.m_6084_() || target != null && target.m_6084_() || !(this.following instanceof EntityRaccoon) && EntityBlueJay.this.getFeedTime() <= 0 || EntityBlueJay.this.m_20159_());
    }

    public void m_8037_() {
        Entity entity;
        double dist = EntityBlueJay.this.m_20270_(this.following);
        if (dist > 6.0 || EntityBlueJay.this.isFlying()) {
            EntityBlueJay.this.setFlying(true);
            EntityBlueJay.this.m_21566_().m_6849_(this.following.m_20185_(), this.following.m_20186_(), this.following.m_20189_(), 1.0);
        } else {
            EntityBlueJay.this.m_21573_().m_26519_(this.following.m_20185_(), this.following.m_20186_(), this.following.m_20189_(), 1.0);
        }
        if (EntityBlueJay.this.isFlying() && EntityBlueJay.this.m_20096_() && dist < 3.0) {
            EntityBlueJay.this.setFlying(false);
        }
        if ((entity = this.following) instanceof EntityRaccoon) {
            EntityRaccoon raccoon = (EntityRaccoon)entity;
            if (dist > 40.0) {
                EntityBlueJay.this.m_6021_(this.following.m_20185_(), this.following.m_20186_(), this.following.m_20189_());
            }
            if (dist < 2.5) {
                EntityBlueJay.this.m_21566_().m_6849_(this.following.m_20185_(), this.following.m_20186_(), this.following.m_20189_(), 1.0);
            }
            if (dist < 1.0 && raccoon.m_20197_().isEmpty()) {
                EntityBlueJay.this.m_7998_((Entity)raccoon, false);
            }
        }
    }
}
