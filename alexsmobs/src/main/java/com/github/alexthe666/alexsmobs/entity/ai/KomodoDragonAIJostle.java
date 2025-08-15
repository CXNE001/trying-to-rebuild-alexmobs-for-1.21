/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.targeting.TargetingConditions
 *  net.minecraft.world.level.Level
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityKomodoDragon;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;

public class KomodoDragonAIJostle
extends Goal {
    private static final TargetingConditions JOSTLE_PREDICATE = TargetingConditions.m_148353_().m_26883_(16.0).m_148355_();
    protected EntityKomodoDragon targetKomodoDragon;
    private final EntityKomodoDragon komodo;
    private final Level world;
    private float angle;

    public KomodoDragonAIJostle(EntityKomodoDragon moose) {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.TARGET));
        this.komodo = moose;
        this.world = moose.m_9236_();
    }

    public boolean m_8036_() {
        if (this.komodo.isJostling() || this.komodo.m_27593_() || this.komodo.m_21827_() || this.komodo.m_20160_() || this.komodo.shouldFollow() || this.komodo.m_20159_() || this.komodo.m_6162_() || this.komodo.m_5448_() != null || this.komodo.jostleCooldown > 0) {
            return false;
        }
        if (this.komodo.instantlyTriggerJostleAI || this.komodo.m_217043_().m_188503_(30) == 0) {
            this.komodo.instantlyTriggerJostleAI = false;
            if (this.komodo.getJostlingPartner() instanceof EntityKomodoDragon) {
                this.targetKomodoDragon = (EntityKomodoDragon)this.komodo.getJostlingPartner();
                return this.targetKomodoDragon.jostleCooldown == 0;
            }
            EntityKomodoDragon possiblePartner = this.getNearbyKomodoDragon();
            if (possiblePartner != null) {
                this.komodo.setJostlingPartner((Entity)possiblePartner);
                possiblePartner.setJostlingPartner((Entity)this.komodo);
                this.targetKomodoDragon = possiblePartner;
                this.targetKomodoDragon.instantlyTriggerJostleAI = true;
                return true;
            }
        }
        return false;
    }

    public void m_8056_() {
        this.komodo.jostleTimer = 0;
        this.angle = 0.0f;
        this.setJostleDirection(this.komodo.m_217043_().m_188499_());
    }

    public void setJostleDirection(boolean dir) {
        this.komodo.jostleDirection = dir;
        this.targetKomodoDragon.jostleDirection = dir;
    }

    public void m_8041_() {
        this.komodo.setJostling(false);
        this.komodo.setJostlingPartner(null);
        this.komodo.jostleTimer = 0;
        this.angle = 0.0f;
        this.komodo.m_21573_().m_26573_();
        if (this.targetKomodoDragon != null) {
            this.targetKomodoDragon.setJostling(false);
            this.targetKomodoDragon.setJostlingPartner(null);
            this.targetKomodoDragon.jostleTimer = 0;
            this.targetKomodoDragon = null;
        }
    }

    public void m_8037_() {
        if (this.targetKomodoDragon != null) {
            this.komodo.m_21391_((Entity)this.targetKomodoDragon, 360.0f, 180.0f);
            this.komodo.setJostling(true);
            float x = (float)(this.komodo.m_20185_() - this.targetKomodoDragon.m_20185_());
            float y = Math.abs((float)(this.komodo.m_20186_() - this.targetKomodoDragon.m_20186_()));
            float z = (float)(this.komodo.m_20189_() - this.targetKomodoDragon.m_20189_());
            double distXZ = Math.sqrt(x * x + z * z);
            if (distXZ < (double)1.8f) {
                this.komodo.m_21573_().m_26573_();
                this.komodo.m_21566_().m_24988_(-0.5f, 0.0f);
            } else if (distXZ > (double)2.4f) {
                this.komodo.setJostling(false);
                this.komodo.m_21573_().m_5624_((Entity)this.targetKomodoDragon, 1.0);
            } else {
                this.komodo.m_21391_((Entity)this.targetKomodoDragon, 360.0f, 180.0f);
                float f = this.komodo.m_217043_().m_188501_() - 0.5f;
                if (this.komodo.jostleDirection) {
                    if (this.angle < 10.0f) {
                        this.angle += 1.0f;
                    } else {
                        this.komodo.jostleDirection = false;
                    }
                    this.komodo.m_21566_().m_24988_(f * 1.0f, -0.4f);
                }
                if (!this.komodo.jostleDirection) {
                    if (this.angle > -10.0f) {
                        this.angle -= 1.0f;
                    } else {
                        this.komodo.jostleDirection = true;
                    }
                    this.komodo.m_21566_().m_24988_(f * 1.0f, 0.4f);
                }
                if (this.komodo.m_217043_().m_188503_(15) == 0 && this.komodo.m_20096_()) {
                    this.komodo.pushBackJostling(this.targetKomodoDragon, 0.1f);
                }
                this.komodo.nextJostleAngleFromServer = this.angle;
                ++this.komodo.jostleTimer;
                ++this.targetKomodoDragon.jostleTimer;
                if (this.komodo.jostleTimer > 500 || y > 2.0f) {
                    this.komodo.f_19812_ = true;
                    if (this.komodo.m_20096_()) {
                        this.komodo.pushBackJostling(this.targetKomodoDragon, 0.4f);
                    }
                    if (this.targetKomodoDragon.m_20096_()) {
                        this.targetKomodoDragon.pushBackJostling(this.komodo, 0.4f);
                    }
                    this.komodo.jostleTimer = 0;
                    this.targetKomodoDragon.jostleTimer = 0;
                    this.komodo.jostleCooldown = 700 + this.komodo.m_217043_().m_188503_(2000);
                    this.targetKomodoDragon.jostleTimer = 0;
                    this.targetKomodoDragon.jostleCooldown = 700 + this.targetKomodoDragon.m_217043_().m_188503_(2000);
                    this.m_8041_();
                }
            }
        }
    }

    public boolean m_8045_() {
        return !this.komodo.m_6162_() && !this.komodo.m_27593_() && !this.komodo.m_20160_() && !this.komodo.m_21827_() && this.komodo.m_5448_() == null && this.targetKomodoDragon != null && this.targetKomodoDragon.m_6084_() && this.komodo.jostleCooldown == 0 && this.targetKomodoDragon.jostleCooldown == 0;
    }

    @Nullable
    private EntityKomodoDragon getNearbyKomodoDragon() {
        List komodoDragons = this.world.m_45971_(EntityKomodoDragon.class, JOSTLE_PREDICATE, (LivingEntity)this.komodo, this.komodo.m_20191_().m_82400_(16.0));
        double lvt_2_1_ = Double.MAX_VALUE;
        EntityKomodoDragon lvt_4_1_ = null;
        for (EntityKomodoDragon lvt_6_1_ : komodoDragons) {
            if (!this.komodo.canJostleWith(lvt_6_1_) || !(this.komodo.m_20280_((Entity)lvt_6_1_) < lvt_2_1_)) continue;
            lvt_4_1_ = lvt_6_1_;
            lvt_2_1_ = this.komodo.m_20280_((Entity)lvt_6_1_);
        }
        return lvt_4_1_;
    }
}
