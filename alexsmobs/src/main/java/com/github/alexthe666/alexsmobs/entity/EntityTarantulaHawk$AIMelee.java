/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityTarantulaHawk;
import com.github.alexthe666.alexsmobs.message.MessageTarantulaHawkSting;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

private class EntityTarantulaHawk.AIMelee
extends Goal {
    private final EntityTarantulaHawk hawk;
    private int orbitCooldown = 0;
    private boolean clockwise = false;
    private Vec3 orbitVec = null;
    private BlockPos sandPos = null;

    public EntityTarantulaHawk.AIMelee() {
        this.hawk = EntityTarantulaHawk.this;
    }

    public boolean m_8036_() {
        return this.hawk.m_5448_() != null && !this.hawk.isSitting() && !this.hawk.isScared() && this.hawk.m_5448_().m_6084_() && !this.hawk.isDragging() && !this.hawk.isDigging() && !this.hawk.m_5448_().f_19794_ && !this.hawk.m_5448_().m_20159_();
    }

    public void m_8056_() {
        this.hawk.setDragging(false);
        this.clockwise = EntityTarantulaHawk.this.f_19796_.m_188499_();
    }

    public void m_8037_() {
        boolean paralizedWithChild;
        LivingEntity target = this.hawk.m_5448_();
        boolean paralized = target != null && target.m_6336_() == MobType.f_21642_ && !target.f_19794_ && target.m_21023_((MobEffect)AMEffectRegistry.DEBILITATING_STING.get());
        boolean bl = paralizedWithChild = paralized && target.m_21124_((MobEffect)AMEffectRegistry.DEBILITATING_STING.get()).m_19564_() > 0;
        if (this.sandPos == null || !EntityTarantulaHawk.this.m_9236_().m_8055_(this.sandPos).m_204336_(BlockTags.f_13029_)) {
            this.sandPos = this.hawk.genSandPos(target.m_20183_());
        }
        if (this.orbitCooldown > 0) {
            --this.orbitCooldown;
            this.hawk.setFlying(true);
            if (target != null && (this.orbitVec == null || this.hawk.m_20238_(this.orbitVec) < 4.0 || !this.hawk.m_21566_().m_24995_())) {
                this.orbitVec = this.hawk.getOrbitVec(target.m_20182_().m_82520_(0.0, (double)target.m_20206_(), 0.0), 10 + EntityTarantulaHawk.this.f_19796_.m_188503_(2), false);
                if (this.orbitVec != null) {
                    this.hawk.m_21566_().m_6849_(this.orbitVec.f_82479_, this.orbitVec.f_82480_, this.orbitVec.f_82481_, 1.0);
                }
            }
        } else if ((paralized && !this.hawk.m_21824_() || paralizedWithChild && this.hawk.bredBuryFlag) && this.sandPos != null) {
            if (this.hawk.m_20096_()) {
                this.hawk.setFlying(false);
                this.hawk.m_21573_().m_5624_((Entity)target, 1.0);
            } else {
                Vec3 vector3d = this.hawk.getBlockGrounding(this.hawk.m_20182_());
                if (vector3d != null && this.hawk.isFlying()) {
                    this.hawk.m_21566_().m_6849_(vector3d.f_82479_, vector3d.f_82480_, vector3d.f_82481_, 1.0);
                }
            }
            if (this.hawk.m_20270_((Entity)target) < target.m_20205_() + 1.5f && !target.m_20159_()) {
                this.hawk.setDragging(true);
                this.hawk.setFlying(false);
                target.m_7998_((Entity)this.hawk, true);
            }
        } else if (target != null && !paralizedWithChild) {
            double dist = this.hawk.m_20270_((Entity)target);
            if (dist < 10.0 && !this.hawk.isFlying()) {
                if (this.hawk.m_20096_()) {
                    this.hawk.setFlying(false);
                }
                this.hawk.m_21573_().m_5624_((Entity)target, 1.0);
            } else {
                this.hawk.setFlying(true);
                this.hawk.m_21566_().m_6849_(target.m_20185_(), target.m_20188_(), target.m_20189_(), 1.0);
            }
            if (dist < (double)(target.m_20205_() + 2.5f)) {
                if ((Integer)this.hawk.f_19804_.m_135370_(ATTACK_TICK) == 0 && this.hawk.attackProgress == 0.0f) {
                    this.hawk.f_19804_.m_135381_(ATTACK_TICK, (Object)7);
                }
                if (this.hawk.attackProgress == 5.0f) {
                    this.hawk.m_7327_((Entity)target);
                    if (this.hawk.bredBuryFlag && target.m_21223_() <= 1.0f) {
                        target.m_5634_(5.0f);
                    }
                    target.m_7292_(new MobEffectInstance((MobEffect)AMEffectRegistry.DEBILITATING_STING.get(), target.m_6336_() == MobType.f_21642_ ? 2400 : 600, this.hawk.bredBuryFlag ? 1 : 0));
                    if (!this.hawk.m_9236_().f_46443_ && target.m_6336_() == MobType.f_21642_) {
                        AlexsMobs.sendMSGToAll(new MessageTarantulaHawkSting(this.hawk.m_19879_(), target.m_19879_()));
                    }
                    this.orbitCooldown = target.m_6336_() == MobType.f_21642_ ? 200 + EntityTarantulaHawk.this.f_19796_.m_188503_(200) : 10 + EntityTarantulaHawk.this.f_19796_.m_188503_(20);
                }
            }
        }
    }

    public void m_8041_() {
        this.orbitCooldown = 0;
        this.hawk.bredBuryFlag = false;
        this.clockwise = EntityTarantulaHawk.this.f_19796_.m_188499_();
        this.orbitVec = null;
        if (this.hawk.m_20197_().isEmpty()) {
            this.hawk.m_6710_(null);
        }
    }
}
