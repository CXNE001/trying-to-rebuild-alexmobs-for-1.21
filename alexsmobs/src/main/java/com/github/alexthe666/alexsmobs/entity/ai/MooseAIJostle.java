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

import com.github.alexthe666.alexsmobs.entity.EntityMoose;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;

public class MooseAIJostle
extends Goal {
    private static final TargetingConditions JOSTLE_PREDICATE = TargetingConditions.m_148353_().m_26883_(16.0).m_148355_();
    protected EntityMoose targetMoose;
    private EntityMoose moose;
    private Level world;
    private float angle;

    public MooseAIJostle(EntityMoose moose) {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.TARGET));
        this.moose = moose;
        this.world = moose.m_9236_();
    }

    public boolean m_8036_() {
        if (this.moose.isJostling() || !this.moose.isAntlered() || this.moose.m_6162_() || this.moose.m_5448_() != null || this.moose.jostleCooldown > 0) {
            return false;
        }
        if (this.moose.instantlyTriggerJostleAI || this.moose.m_217043_().m_188503_(30) == 0) {
            this.moose.instantlyTriggerJostleAI = false;
            if (this.moose.getJostlingPartner() instanceof EntityMoose) {
                this.targetMoose = (EntityMoose)this.moose.getJostlingPartner();
                return this.targetMoose.jostleCooldown == 0;
            }
            EntityMoose possiblePartner = this.getNearbyMoose();
            if (possiblePartner != null) {
                this.moose.setJostlingPartner((Entity)possiblePartner);
                possiblePartner.setJostlingPartner((Entity)this.moose);
                this.targetMoose = possiblePartner;
                this.targetMoose.instantlyTriggerJostleAI = true;
                return true;
            }
        }
        return false;
    }

    public void m_8056_() {
        this.moose.jostleTimer = 0;
        this.angle = 0.0f;
        this.setJostleDirection(this.moose.m_217043_().m_188499_());
    }

    public void setJostleDirection(boolean dir) {
        this.moose.jostleDirection = dir;
        this.targetMoose.jostleDirection = dir;
    }

    public void m_8041_() {
        this.moose.setJostling(false);
        this.moose.setJostlingPartner(null);
        this.moose.jostleTimer = 0;
        this.angle = 0.0f;
        this.moose.m_21573_().m_26573_();
        if (this.targetMoose != null) {
            this.targetMoose.setJostling(false);
            this.targetMoose.setJostlingPartner(null);
            this.targetMoose.jostleTimer = 0;
            this.targetMoose = null;
        }
    }

    public void m_8037_() {
        if (this.targetMoose != null) {
            this.moose.m_21391_((Entity)this.targetMoose, 360.0f, 180.0f);
            this.moose.setJostling(true);
            float f = (float)(this.moose.m_20185_() - this.targetMoose.m_20185_());
            float f1 = Math.abs((float)(this.moose.m_20186_() - this.targetMoose.m_20186_()));
            float f2 = (float)(this.moose.m_20189_() - this.targetMoose.m_20189_());
            double distXZ = Math.sqrt(f * f + f2 * f2);
            if (distXZ < 4.0) {
                this.moose.m_21573_().m_26573_();
                this.moose.m_21566_().m_24988_(-0.5f, 0.0f);
            } else if (distXZ > 4.5) {
                this.moose.setJostling(false);
                this.moose.m_21573_().m_5624_((Entity)this.targetMoose, 1.0);
            } else {
                this.moose.m_21391_((Entity)this.targetMoose, 360.0f, 180.0f);
                if (this.moose.jostleDirection) {
                    if (this.angle < 30.0f) {
                        this.angle += 1.0f;
                    }
                    this.moose.m_21566_().m_24988_(0.0f, -0.2f);
                }
                if (!this.moose.jostleDirection) {
                    if (this.angle > -30.0f) {
                        this.angle -= 1.0f;
                    }
                    this.moose.m_21566_().m_24988_(0.0f, 0.2f);
                }
                if (this.moose.m_217043_().m_188503_(55) == 0 && this.moose.m_20096_()) {
                    this.moose.pushBackJostling(this.targetMoose, 0.2f);
                }
                if (this.moose.m_217043_().m_188503_(25) == 0 && this.moose.m_20096_()) {
                    this.moose.playJostleSound();
                }
                this.moose.setJostleAngle(this.angle);
                if (this.moose.jostleTimer % 60 == 0 || this.moose.m_217043_().m_188503_(80) == 0) {
                    this.setJostleDirection(!this.moose.jostleDirection);
                }
                ++this.moose.jostleTimer;
                ++this.targetMoose.jostleTimer;
                if (this.moose.jostleTimer > 1000 || f1 > 2.0f) {
                    this.moose.f_19812_ = true;
                    if (this.moose.m_20096_()) {
                        this.moose.pushBackJostling(this.targetMoose, 0.9f);
                    }
                    if (this.targetMoose.m_20096_()) {
                        this.targetMoose.pushBackJostling(this.moose, 0.9f);
                    }
                    this.moose.jostleTimer = 0;
                    this.targetMoose.jostleTimer = 0;
                    this.moose.jostleCooldown = 500 + this.moose.m_217043_().m_188503_(2000);
                    this.targetMoose.jostleTimer = 0;
                    this.targetMoose.jostleCooldown = 500 + this.targetMoose.m_217043_().m_188503_(2000);
                    this.m_8041_();
                }
            }
        }
    }

    public boolean m_8045_() {
        return !this.moose.m_6162_() && this.moose.isAntlered() && this.moose.m_5448_() == null && this.targetMoose != null && this.targetMoose.isAntlered() && this.targetMoose.m_6084_() && this.moose.jostleCooldown == 0 && this.targetMoose.jostleCooldown == 0;
    }

    @Nullable
    private EntityMoose getNearbyMoose() {
        List listOfMeese = this.world.m_45971_(EntityMoose.class, JOSTLE_PREDICATE, (LivingEntity)this.moose, this.moose.m_20191_().m_82400_(16.0));
        double lvt_2_1_ = Double.MAX_VALUE;
        EntityMoose lvt_4_1_ = null;
        for (EntityMoose lvt_6_1_ : listOfMeese) {
            if (!this.moose.canJostleWith(lvt_6_1_) || !(this.moose.m_20280_((Entity)lvt_6_1_) < lvt_2_1_)) continue;
            lvt_4_1_ = lvt_6_1_;
            lvt_2_1_ = this.moose.m_20280_((Entity)lvt_6_1_);
        }
        return lvt_4_1_;
    }
}
