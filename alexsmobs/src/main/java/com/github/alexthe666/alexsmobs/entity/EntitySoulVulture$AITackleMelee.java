/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntitySoulVulture;
import java.util.EnumSet;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;

private class EntitySoulVulture.AITackleMelee
extends Goal {
    private final EntitySoulVulture vulture;

    public EntitySoulVulture.AITackleMelee(EntitySoulVulture vulture) {
        this.vulture = vulture;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        if (this.vulture.m_5448_() != null && this.vulture.shouldSwoop()) {
            this.vulture.setFlying(true);
            return true;
        }
        return false;
    }

    public void m_8041_() {
        this.vulture.setTackling(false);
    }

    public void m_8037_() {
        if (this.vulture.m_29443_()) {
            this.vulture.setTackling(true);
        } else {
            this.vulture.setTackling(false);
        }
        if (this.vulture.m_5448_() != null) {
            this.vulture.m_21566_().m_6849_(this.vulture.m_5448_().m_20185_(), this.vulture.m_5448_().m_20186_() + (double)this.vulture.m_5448_().m_20192_(), this.vulture.m_5448_().m_20189_(), 2.0);
            double d0 = this.vulture.m_20185_() - this.vulture.m_5448_().m_20185_();
            double d2 = this.vulture.m_20189_() - this.vulture.m_5448_().m_20189_();
            float f = (float)(Mth.m_14136_((double)d2, (double)d0) * 57.2957763671875) - 90.0f;
            this.vulture.m_146922_(f);
            this.vulture.f_20883_ = this.vulture.m_146908_();
            if (this.vulture.m_20191_().m_82377_((double)0.3f, (double)0.3f, (double)0.3f).m_82381_(this.vulture.m_5448_().m_20191_()) && this.vulture.tackleCooldown == 0) {
                EntitySoulVulture.this.tackleCooldown = 100 + EntitySoulVulture.this.f_19796_.m_188503_(200);
                float dmg = (float)this.vulture.m_21051_(Attributes.f_22281_).m_22135_();
                if (this.vulture.m_5448_().m_6469_(this.vulture.m_269291_().m_269333_((LivingEntity)this.vulture), dmg) && this.vulture.m_21223_() < this.vulture.m_21233_() - dmg && this.vulture.getSoulLevel() < 5) {
                    this.vulture.setSoulLevel(this.vulture.getSoulLevel() + 1);
                    this.vulture.m_5634_(dmg);
                    this.vulture.m_9236_().m_7605_((Entity)this.vulture, (byte)68);
                }
                this.m_8041_();
            }
        }
    }
}
