/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.projectile.ProjectileUtil
 *  net.minecraft.world.item.BowItem
 *  net.minecraft.world.item.CrossbowItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Items
 */
package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityMimicube;
import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class MimiCubeAIRangedAttack
extends Goal {
    private final EntityMimicube entity;
    private final double moveSpeedAmp;
    private int attackCooldown;
    private final float maxAttackDistance;
    private int attackTime = -1;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;
    private int crossbowCooldown = 0;

    public MimiCubeAIRangedAttack(EntityMimicube mob, double moveSpeedAmpIn, int attackCooldownIn, float maxAttackDistanceIn) {
        this.entity = mob;
        this.moveSpeedAmp = moveSpeedAmpIn;
        this.attackCooldown = attackCooldownIn;
        this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public void setAttackCooldown(int attackCooldownIn) {
        this.attackCooldown = attackCooldownIn;
    }

    public boolean m_8036_() {
        return this.entity.m_5448_() == null ? false : this.isBowInMainhand();
    }

    protected boolean isBowInMainhand() {
        return this.entity.shouldShoot();
    }

    public boolean m_8045_() {
        return (this.m_8036_() || !this.entity.m_21573_().m_26571_()) && this.isBowInMainhand();
    }

    public void m_8056_() {
        super.m_8056_();
        this.entity.m_21561_(true);
    }

    public void m_8041_() {
        super.m_8041_();
        this.entity.m_21561_(false);
        this.seeTime = 0;
        this.attackTime = -1;
        this.entity.m_5810_();
    }

    public void m_8037_() {
        LivingEntity livingentity = this.entity.m_5448_();
        if (this.crossbowCooldown > 0) {
            --this.crossbowCooldown;
        }
        if (livingentity != null) {
            boolean flag1;
            double d0 = this.entity.m_20275_(livingentity.m_20185_(), livingentity.m_20186_(), livingentity.m_20189_());
            boolean flag = this.entity.m_142582_((Entity)livingentity);
            boolean bl = flag1 = this.seeTime > 0;
            if (flag != flag1) {
                this.seeTime = 0;
            }
            this.seeTime = flag ? ++this.seeTime : --this.seeTime;
            if (!(d0 > (double)this.maxAttackDistance) && this.seeTime >= 20) {
                this.entity.m_21573_().m_26573_();
                ++this.strafingTime;
            } else {
                this.entity.m_21573_().m_5624_((Entity)livingentity, this.moveSpeedAmp);
                this.strafingTime = -1;
            }
            if (this.strafingTime >= 20) {
                if ((double)this.entity.m_217043_().m_188501_() < 0.3) {
                    boolean bl2 = this.strafingClockwise = !this.strafingClockwise;
                }
                if ((double)this.entity.m_217043_().m_188501_() < 0.3) {
                    this.strafingBackwards = !this.strafingBackwards;
                }
                this.strafingTime = 0;
            }
            if (this.strafingTime > -1) {
                if (d0 > (double)(this.maxAttackDistance * 0.75f)) {
                    this.strafingBackwards = false;
                } else if (d0 < (double)(this.maxAttackDistance * 0.25f)) {
                    this.strafingBackwards = true;
                }
                this.entity.m_21566_().m_24988_(this.strafingBackwards ? -0.5f : 0.5f, this.strafingClockwise ? 0.5f : -0.5f);
                this.entity.m_21391_((Entity)livingentity, 30.0f, 30.0f);
            } else {
                this.entity.m_21563_().m_24960_((Entity)livingentity, 30.0f, 30.0f);
            }
            boolean crossbow = this.entity.m_21205_().m_41720_() instanceof CrossbowItem;
            if (this.entity.m_6117_() || crossbow) {
                int i;
                if (!flag && this.seeTime < -60) {
                    this.entity.m_5810_();
                } else if (flag && ((i = this.entity.m_21252_()) >= 20 || crossbow && this.crossbowCooldown == 0)) {
                    this.entity.m_5810_();
                    this.entity.m_6504_(livingentity, BowItem.m_40661_((int)i));
                    this.attackTime = this.attackCooldown;
                    this.crossbowCooldown = 20;
                }
            } else if (--this.attackTime <= 0 && this.seeTime >= -60) {
                this.entity.m_6672_(ProjectileUtil.m_37297_((LivingEntity)this.entity, (Item)Items.f_42411_));
            }
        }
    }
}
