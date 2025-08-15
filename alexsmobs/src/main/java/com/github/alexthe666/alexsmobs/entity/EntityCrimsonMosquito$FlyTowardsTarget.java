/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.entity.EntityCrimsonMosquito;
import com.github.alexthe666.alexsmobs.message.MessageMosquitoMountPlayer;
import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;

public static class EntityCrimsonMosquito.FlyTowardsTarget
extends Goal {
    private final EntityCrimsonMosquito parentEntity;

    public EntityCrimsonMosquito.FlyTowardsTarget(EntityCrimsonMosquito mosquito) {
        this.parentEntity = mosquito;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        if (!this.parentEntity.isFlying() || this.parentEntity.getBloodLevel() > 0 || this.parentEntity.drinkTime < 0 || this.parentEntity.getFleeingEntityId() != -1) {
            return false;
        }
        return !this.parentEntity.m_20159_() && this.parentEntity.m_5448_() != null && !this.isBittenByMosquito((Entity)this.parentEntity.m_5448_());
    }

    public boolean m_8045_() {
        return this.parentEntity.drinkTime >= 0 && this.parentEntity.getFleeingEntityId() == -1 && this.parentEntity.m_5448_() != null && !this.isBittenByMosquito((Entity)this.parentEntity.m_5448_()) && !this.parentEntity.f_19862_ && this.parentEntity.getBloodLevel() == 0 && this.parentEntity.isFlying() && this.parentEntity.m_21566_().m_24995_();
    }

    public boolean isBittenByMosquito(Entity entity) {
        for (Entity e : entity.m_20197_()) {
            if (!(e instanceof EntityCrimsonMosquito)) continue;
            return true;
        }
        return false;
    }

    public void m_8041_() {
    }

    public void m_8037_() {
        if (this.parentEntity.m_5448_() != null) {
            this.parentEntity.m_21566_().m_6849_(this.parentEntity.m_5448_().m_20185_(), this.parentEntity.m_5448_().m_20186_(), this.parentEntity.m_5448_().m_20189_(), 1.0);
            if (this.parentEntity.m_20191_().m_82377_((double)0.3f, (double)0.3f, (double)0.3f).m_82381_(this.parentEntity.m_5448_().m_20191_()) && !this.isBittenByMosquito((Entity)this.parentEntity.m_5448_()) && this.parentEntity.drinkTime == 0) {
                this.parentEntity.m_7998_((Entity)this.parentEntity.m_5448_(), true);
                if (!this.parentEntity.m_9236_().f_46443_) {
                    AlexsMobs.sendMSGToAll(new MessageMosquitoMountPlayer(this.parentEntity.m_19879_(), this.parentEntity.m_5448_().m_19879_()));
                }
            }
        }
    }
}
