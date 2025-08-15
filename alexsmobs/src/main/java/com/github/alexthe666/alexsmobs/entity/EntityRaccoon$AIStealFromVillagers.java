/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.util.DefaultRandomPos
 *  net.minecraft.world.entity.animal.IronGolem
 *  net.minecraft.world.entity.npc.AbstractVillager
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.trading.MerchantOffer
 *  net.minecraft.world.item.trading.MerchantOffers
 *  net.minecraft.world.phys.Vec3
 */
package com.github.alexthe666.alexsmobs.entity;

import com.github.alexthe666.alexsmobs.entity.EntityRaccoon;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.phys.Vec3;

private class EntityRaccoon.AIStealFromVillagers
extends Goal {
    EntityRaccoon raccoon;
    AbstractVillager target;
    int golemCheckTime = 0;
    int cooldown = 0;
    int fleeTime = 0;

    private EntityRaccoon.AIStealFromVillagers(EntityRaccoon raccoon) {
        this.raccoon = raccoon;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        if (this.cooldown > 0) {
            --this.cooldown;
            return false;
        }
        if (this.raccoon != null && this.raccoon.stealCooldown == 0 && this.raccoon.m_21205_() != null && this.raccoon.m_21205_().m_41619_()) {
            AbstractVillager villager = this.getNearbyVillagers();
            if (!this.isGolemNearby() && villager != null) {
                this.target = villager;
            }
            this.cooldown = 150;
            return this.target != null;
        }
        return false;
    }

    public boolean m_8045_() {
        return this.target != null && this.raccoon != null;
    }

    public void m_8041_() {
        this.target = null;
        this.cooldown = 200 + EntityRaccoon.this.f_19796_.m_188503_(200);
        this.golemCheckTime = 0;
        this.fleeTime = 0;
    }

    public void m_8037_() {
        if (this.target != null) {
            ++this.golemCheckTime;
            if (this.fleeTime > 0) {
                Vec3 fleevec;
                --this.fleeTime;
                if (this.raccoon.m_21573_().m_26571_() && (fleevec = DefaultRandomPos.m_148407_((PathfinderMob)this.raccoon, (int)16, (int)7, (Vec3)this.raccoon.m_20182_())) != null) {
                    this.raccoon.m_21573_().m_26519_(fleevec.f_82479_, fleevec.f_82480_, fleevec.f_82481_, (double)1.3f);
                }
                if (this.fleeTime == 0) {
                    this.m_8041_();
                }
            } else {
                this.raccoon.m_21573_().m_5624_((Entity)this.target, 1.0);
                if (this.raccoon.m_20270_((Entity)this.target) < 1.7f) {
                    this.raccoon.setStanding(true);
                    this.raccoon.maxStandTime = 15;
                    MerchantOffers offers = this.target.m_6616_();
                    if (offers == null || offers.isEmpty() || offers.size() < 1) {
                        this.m_8041_();
                    } else {
                        MerchantOffer offer = (MerchantOffer)offers.get(offers.size() <= 1 ? 0 : this.raccoon.m_217043_().m_188503_(offers.size() - 1));
                        if (offer != null) {
                            ItemStack stealStack;
                            ItemStack itemStack = stealStack = offer.m_45368_().m_41720_() == Items.f_42616_ ? offer.m_45352_() : offer.m_45368_();
                            if (stealStack.m_41619_()) {
                                this.m_8041_();
                            } else {
                                offer.m_45374_();
                                ItemStack copy = stealStack.m_41777_();
                                copy.m_41764_(1);
                                this.raccoon.m_21008_(InteractionHand.MAIN_HAND, copy);
                                this.fleeTime = 60 + EntityRaccoon.this.f_19796_.m_188503_(60);
                                this.raccoon.m_21573_().m_26573_();
                                EntityRaccoon.this.lookForWaterBeforeEatingTimer = 120 + EntityRaccoon.this.f_19796_.m_188503_(60);
                                this.target.m_6469_(EntityRaccoon.this.m_269291_().m_269264_(), 0.0f);
                                this.raccoon.stealCooldown = 24000 + EntityRaccoon.this.f_19796_.m_188503_(48000);
                            }
                        }
                    }
                }
                if (this.golemCheckTime % 30 == 0 && EntityRaccoon.this.f_19796_.m_188499_() && this.isGolemNearby()) {
                    this.m_8041_();
                }
            }
        }
    }

    @Nullable
    private boolean isGolemNearby() {
        List lvt_1_1_ = this.raccoon.m_9236_().m_45971_(IronGolem.class, IRON_GOLEM_PREDICATE, (LivingEntity)this.raccoon, this.raccoon.m_20191_().m_82400_(25.0));
        return !lvt_1_1_.isEmpty();
    }

    @Nullable
    private AbstractVillager getNearbyVillagers() {
        List lvt_1_1_ = this.raccoon.m_9236_().m_45971_(AbstractVillager.class, VILLAGER_STEAL_PREDICATE, (LivingEntity)this.raccoon, this.raccoon.m_20191_().m_82400_(20.0));
        double lvt_2_1_ = 10000.0;
        AbstractVillager lvt_4_1_ = null;
        for (AbstractVillager lvt_6_1_ : lvt_1_1_) {
            if (!(lvt_6_1_.m_21223_() > 2.0f) || lvt_6_1_.m_6616_().isEmpty() || !(this.raccoon.m_20280_((Entity)lvt_6_1_) < lvt_2_1_)) continue;
            lvt_4_1_ = lvt_6_1_;
            lvt_2_1_ = this.raccoon.m_20280_((Entity)lvt_6_1_);
        }
        return lvt_4_1_;
    }
}
