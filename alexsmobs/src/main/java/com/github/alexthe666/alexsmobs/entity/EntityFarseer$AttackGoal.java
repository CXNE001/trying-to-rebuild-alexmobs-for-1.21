/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.message.MessageSendVisualFlagFromServer;
import com.github.alexthe666.alexsmobs.misc.AMDamageTypes;
import com.github.alexthe666.alexsmobs.misc.AMSoundRegistry;
import java.util.EnumSet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntityFarseer.AttackGoal
extends Goal {
    private boolean attackDecision = true;
    private int timeSinceLastSuccessfulAttack = 0;
    private int laserCooldown = 0;
    private int laserUseTime = 0;
    private int lasersShot = 0;

    public EntityFarseer.AttackGoal() {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        return EntityFarseer.this.m_5448_() != null && EntityFarseer.this.m_5448_().m_6084_();
    }

    public void m_8041_() {
        this.lasersShot = 0;
        this.laserCooldown = 0;
        this.laserUseTime = 0;
        this.attackDecision = EntityFarseer.this.m_217043_().m_188499_();
        EntityFarseer.this.f_19804_.m_135381_(LASER_ENTITY_ID, (Object)-1);
        this.timeSinceLastSuccessfulAttack = 0;
        EntityFarseer.this.setAngry(false);
    }

    public void m_8037_() {
        super.m_8037_();
        LivingEntity target = EntityFarseer.this.m_5448_();
        if (this.laserCooldown > 0) {
            --this.laserCooldown;
        }
        ++this.timeSinceLastSuccessfulAttack;
        if (this.timeSinceLastSuccessfulAttack > 100) {
            this.timeSinceLastSuccessfulAttack = 0;
            boolean bl = this.attackDecision = !this.attackDecision;
        }
        if (target != null) {
            double dist = EntityFarseer.this.m_20270_((Entity)target);
            boolean canLaserHit = this.willLaserHit(target);
            if (this.laserCooldown == 0 && this.attackDecision && canLaserHit && dist > 2.0) {
                EntityFarseer.this.setAngry(true);
                EntityFarseer.this.f_19804_.m_135381_(LASER_ENTITY_ID, (Object)target.m_19879_());
                if (this.laserUseTime == 0) {
                    EntityFarseer.this.m_5496_((SoundEvent)AMSoundRegistry.FARSEER_BEAM.get(), EntityFarseer.this.m_6121_(), EntityFarseer.this.m_6100_());
                }
                ++this.laserUseTime;
                if (this.laserUseTime > 10) {
                    this.laserUseTime = 0;
                    if (canLaserHit) {
                        float healthTenth = target.m_21233_() * 0.1f;
                        if (target.m_6469_(AMDamageTypes.causeFarseerDamage((LivingEntity)EntityFarseer.this), (float)EntityFarseer.this.f_19796_.m_188503_(2) + Math.max(6.0f, healthTenth)) && !target.m_6084_()) {
                            AlexsMobs.sendMSGToAll(new MessageSendVisualFlagFromServer(target.m_19879_(), 87));
                        }
                        this.timeSinceLastSuccessfulAttack = 0;
                    }
                    if (this.lasersShot++ > 5) {
                        this.lasersShot = 0;
                        this.laserCooldown = 80 + EntityFarseer.this.f_19796_.m_188503_(40);
                        EntityFarseer.this.f_19804_.m_135381_(LASER_ENTITY_ID, (Object)-1);
                        this.attackDecision = EntityFarseer.this.m_217043_().m_188499_();
                    }
                }
                EntityFarseer.this.f_19804_.m_135381_(LASER_ATTACK_LVL, (Object)this.laserUseTime);
                EntityFarseer.this.m_21391_((Entity)target, 180.0f, 180.0f);
                if (dist < 17.0 && canLaserHit) {
                    EntityFarseer.this.m_21573_().m_26573_();
                } else {
                    EntityFarseer.this.m_21573_().m_5624_((Entity)target, 1.0);
                }
                EntityFarseer.this.f_19804_.m_135381_(MELEEING, (Object)false);
            } else {
                if (!canLaserHit && dist > 10.0) {
                    EntityFarseer.this.setAngry(false);
                }
                if (EntityFarseer.this.hasLaser()) {
                    EntityFarseer.this.f_19804_.m_135381_(LASER_ENTITY_ID, (Object)-1);
                }
                EntityFarseer.this.f_19804_.m_135381_(MELEEING, (Object)(dist < 4.0 ? 1 : 0));
                if (dist < 4.0) {
                    this.timeSinceLastSuccessfulAttack = 0;
                } else {
                    EntityFarseer.this.m_21573_().m_5624_((Entity)target, 1.0);
                    EntityFarseer.this.f_21342_.m_6849_(target.m_20185_(), target.m_20188_(), target.m_20189_(), 1.0);
                }
            }
        }
    }

    private boolean willLaserHit(LivingEntity target) {
        Vec3 vec = EntityFarseer.this.calculateLaserHit(target.m_146892_());
        return vec.m_82554_(target.m_146892_()) < 1.0 && EntityFarseer.this.canUseLaser();
    }
}
