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

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.entity.EntityBaldEagle;
import com.github.alexthe666.alexsmobs.message.MessageMosquitoMountPlayer;
import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

private class EntityBaldEagle.AILandOnGlove
extends Goal {
    protected EntityBaldEagle eagle;
    private int seperateTime = 0;

    public EntityBaldEagle.AILandOnGlove() {
        this.eagle = EntityBaldEagle.this;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        return this.eagle.isLaunched() && !this.eagle.controlledFlag && this.eagle.m_21824_() && !this.eagle.m_20159_() && !this.eagle.m_20160_() && (this.eagle.m_5448_() == null || !this.eagle.m_5448_().m_6084_());
    }

    public void m_8037_() {
        LivingEntity owner;
        if (this.eagle.m_20184_().m_82556_() < 0.03) {
            ++this.seperateTime;
        }
        if ((owner = this.eagle.m_269323_()) != null) {
            if (this.seperateTime > 200) {
                this.seperateTime = 0;
                this.eagle.m_20359_((Entity)owner);
            }
            this.eagle.setFlying(true);
            double d0 = this.eagle.m_20185_() - owner.m_20185_();
            double d2 = this.eagle.m_20189_() - owner.m_20189_();
            double xzDist = Math.sqrt(d0 * d0 + d2 * d2);
            double yAdd = xzDist > 14.0 ? 5.0 : 0.0;
            this.eagle.m_21566_().m_6849_(owner.m_20185_(), owner.m_20186_() + yAdd + (double)owner.m_20192_(), owner.m_20189_(), 1.0);
            if ((double)this.eagle.m_20270_((Entity)owner) < (double)owner.m_20205_() + 1.4) {
                this.eagle.setLaunched(false);
                if (this.eagle.getRidingFalcons(owner) <= 0) {
                    this.eagle.m_20329_((Entity)owner);
                    if (!this.eagle.m_9236_().f_46443_) {
                        AlexsMobs.sendMSGToAll(new MessageMosquitoMountPlayer(this.eagle.m_19879_(), owner.m_19879_()));
                    }
                } else {
                    this.eagle.setCommand(2);
                    this.eagle.m_21839_(true);
                }
            }
        }
    }

    public void m_8041_() {
        this.seperateTime = 0;
    }
}
