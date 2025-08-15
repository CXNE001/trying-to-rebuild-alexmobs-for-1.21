/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.monster.EnderMan
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.entity.EntityEnderiophage;
import com.github.alexthe666.alexsmobs.message.MessageMosquitoMountPlayer;
import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.EnderMan;

public static class EntityEnderiophage.FlyTowardsTarget
extends Goal {
    private final EntityEnderiophage parentEntity;

    public EntityEnderiophage.FlyTowardsTarget(EntityEnderiophage phage) {
        this.parentEntity = phage;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        return !this.parentEntity.m_20159_() && this.parentEntity.m_5448_() != null && !this.isBittenByPhage((Entity)this.parentEntity.m_5448_()) && this.parentEntity.fleeAfterStealTime == 0;
    }

    public boolean m_8045_() {
        return this.parentEntity.m_5448_() != null && !this.isBittenByPhage((Entity)this.parentEntity.m_5448_()) && !this.parentEntity.f_19862_ && !this.parentEntity.m_20159_() && this.parentEntity.m_29443_() && this.parentEntity.m_21566_().m_24995_() && this.parentEntity.fleeAfterStealTime == 0 && (this.parentEntity.m_5448_() instanceof EnderMan || !this.parentEntity.isMissingEye());
    }

    public boolean isBittenByPhage(Entity entity) {
        int phageCount = 0;
        for (Entity e : entity.m_20197_()) {
            if (!(e instanceof EntityEnderiophage)) continue;
            ++phageCount;
        }
        return phageCount > 3;
    }

    public void m_8041_() {
    }

    public void m_8037_() {
        if (this.parentEntity.m_5448_() != null) {
            boolean isWithinReach;
            float width = this.parentEntity.m_5448_().m_20205_() + this.parentEntity.m_20205_() + 2.0f;
            boolean bl = isWithinReach = this.parentEntity.m_20280_((Entity)this.parentEntity.m_5448_()) < (double)(width * width);
            if (this.parentEntity.m_29443_() || isWithinReach) {
                this.parentEntity.m_21566_().m_6849_(this.parentEntity.m_5448_().m_20185_(), this.parentEntity.m_5448_().m_20186_(), this.parentEntity.m_5448_().m_20189_(), isWithinReach ? 1.6 : 1.0);
            } else {
                this.parentEntity.m_21573_().m_26519_(this.parentEntity.m_5448_().m_20185_(), this.parentEntity.m_5448_().m_20186_(), this.parentEntity.m_5448_().m_20189_(), 1.2);
            }
            if (this.parentEntity.m_5448_().m_20186_() > this.parentEntity.m_20186_() + (double)1.2f) {
                this.parentEntity.setFlying(true);
            }
            if (this.parentEntity.dismountCooldown == 0 && this.parentEntity.m_20191_().m_82377_(0.3, 0.3, 0.3).m_82381_(this.parentEntity.m_5448_().m_20191_()) && !this.isBittenByPhage((Entity)this.parentEntity.m_5448_())) {
                this.parentEntity.m_7998_((Entity)this.parentEntity.m_5448_(), true);
                if (!this.parentEntity.m_9236_().f_46443_) {
                    AlexsMobs.sendMSGToAll(new MessageMosquitoMountPlayer(this.parentEntity.m_19879_(), this.parentEntity.m_5448_().m_19879_()));
                }
            }
        }
    }
}
